#!/bin/sh

REGISTRY="registry.docker:443"
IMAGES_LIST="mysql redis nginx portal kmv mcd dbi ctem uib task-management"
DATA_CONTAINTER="mysql redis nginx"

usages() {

    echo "docker-run COMMAND MODULE"
    echo "    COMMAND:"
    echo "        start   - start module of rti;"
    echo "        restart - remove current MODULE contain, start MODULE again;"
    echo "        stop    - stop MODULE container;"
    echo "        status  - show status info of MODULE contain;"
    echo "        pull    - pull MODULE image from registry;"
    echo "        remove  - remove MODULE container and image;"
    echo "    MODULE:"
    for m in $IMAGES_LIST
    do
        echo "        $m"
    done
    echo ""
}

pull_image() {
    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    local IMAGE_NAME=$1
    local CHECK_FLAG=$2

    local IMAGE=$(docker images --format "{{.Repository}}" $IMAGE_NAME)

    # check if the image of mysql is exist.
    if [[ $IMAGE == "" ]]; then
        # try to pull the image
        if [ ! -z $CHECK_FLAG ]; then
            echo "WRN: There is no $IMAGE_NAME image, try to pull from registry."
        fi

        docker pull $REGISTRY/$IMAGE_NAME

        if [ ! $? = "0" ]; then
            echo "ERR: can not pulll image of $IMAGE_NAME from ${REGISTRY}."
            exit 1
        fi
        
        #retag image registry.docker:443/mysql-base to mysql
        docker tag $REGISTRY/$IMAGE_NAME $IMAGE_NAME
        docker rmi -f $REGISTRY/$IMAGE_NAME

        if [ -z $CHECK_FLAG ]; then
            docker images $IMAGE_NAME 
        fi

    else
        if [ -z $CHECK_FLAG ]; then
            echo "INF: the image of $IMAGE_NAME is exist."
            exit 0 
        fi
    fi

}

get_opts() {

    local RTI_CONF="rti.conf"
    local TMP_OPTS=''

    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    case "$1" in
        "mysql" )
            eval "$2='-p 3306:3306'"
            ;;

        "redis" )
            eval "$2='-p 6379:6379'"
            ;;

        "nginx" )
            #
            # check config file.
            #
            if [ ! -f ./$RTI_CONF ]; then
                echo "ERR: there is no $RTI_CONF file, exit."
                exit 1
            else
                # read config info from rti.conf file
                source ./$RTI_CONF
            fi

            if [[ (-z $PORTAL_TC_IPADDR) || (-z $KMV_TC_IPADDR) || (-z $MCD_TC_IPADDR) || (-z $DBI_TC_IPADDR) || \
                (-z $CTEM_TC_IPADDR) || (-z $TM_TC_IPADDR) || (-z $UIB_TC_IPADDR) ]]; then

                echo "ERR: some variables is not seted in the $RTI_CONF file."
                echo "ERR: please check the variables below:"
                echo "PORTAL_TC_IPADDR = $PORTAL_TC_IPADDR"
                echo "KMV_TC_IPADDR    = $KMV_TC_IPADDR"
                echo "MCD_TC_IPADDR    = $MCD_TC_IPADDR"
                echo "DBI_TC_IPADDR    = $DBI_TC_IPADDR"
                echo "CTEM_TC_IPADDR   = $CTEM_TC_IPADDR"
                echo "TM_TC_IPADDR     = $TM_TC_IPADDR"
                echo "UIB_TC_IPADDR    = $UIB_TC_IPADDR"
                exit 1
            fi

            if [ ! -f ./base_service.conf ]; then
                echo "ERR: there is no base_service.conf file, exit."
                exit 1
            fi

            TMP_OPTS="-p 15050:15050"
            TMP_OPTS="$TMP_OPTS --add-host rti.com:$PORTAL_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host kmv.rti.com:$KMV_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host mcd.rti.com:$MCD_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host dbi.rti.com:$DBI_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host ctem.rti.com:$CTEM_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host tm.rti.com:$TM_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host uib.rti.com:$UIB_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS -v $(pwd)/base_service.conf:/opt/nginx/conf/base_service.conf"

            eval "$2='$TMP_OPTS'"
            ;;
			
		"ctem" )
			#
            # check config file.
            #
            if [ ! -f ./$RTI_CONF ]; then
                echo "ERR: there is no $RTI_CONF file, exit."
                exit 1
            else
                # read config info from rti.conf file
                source ./$RTI_CONF
            fi
			if [[ (-z $MYSQL_IPADDR) || (-z $ZOOKEEPER_IPADDR) || (-z $REDIS_IPADDR) || (-z $ROCKETMQ_IPADDR) || \
                (-z $PORTAL_TC_IPADDR) ]]; then

                echo "ERR: some variables is not seted in the $RTI_CONF file."
                echo "ERR: please check the variables below:"
                echo "MYSQL_IPADDR = $MYSQL_IPADDR"
                echo "ZOOKEEPER_IPADDR    = $ZOOKEEPER_IPADDR"
                echo "REDIS_IPADDR    = $REDIS_IPADDR"
                echo "ROCKETMQ_IPADDR    = $ROCKETMQ_IPADDR"
                echo "PORTAL_TC_IPADDR   = $PORTAL_TC_IPADDR"
                exit 1
            fi
			
			TMP_OPTS=" -p 15058:8080"
            TMP_OPTS="$TMP_OPTS --add-host rti.com:$PORTAL_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host mysql.rti.com:$MYSQL_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host zookeeper.rti.com:$ZOOKEEPER_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host redis.rti.com:$REDIS_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host rocketmq.rti.com:$ROCKETMQ_IPADDR"
			
			# Set portal param, when login failed, redirect the address
			TMP_OPTS="$TMP_OPTS -e PORTAL_HOST=$PORTAL_TC_IPADDR -e PORTAL_PORT=$PORTAL_PORT"
			
			eval "$2='$TMP_OPTS'"
			;;
			
		"task-management" )
			#
            # check config file.
            #
            if [ ! -f ./$RTI_CONF ]; then
                echo "ERR: there is no $RTI_CONF file, exit."
                exit 1
            else
                # read config info from rti.conf file
                source ./$RTI_CONF
            fi
			if [[ (-z $MYSQL_IPADDR) || (-z $ZOOKEEPER_IPADDR) || (-z $REDIS_IPADDR) || (-z $ROCKETMQ_IPADDR) || \
                (-z $PORTAL_TC_IPADDR) ]]; then

                echo "ERR: some variables is not seted in the $RTI_CONF file."
                echo "ERR: please check the variables below:"
                echo "MYSQL_IPADDR = $MYSQL_IPADDR"
                echo "ZOOKEEPER_IPADDR    = $ZOOKEEPER_IPADDR"
                echo "REDIS_IPADDR    = $REDIS_IPADDR"
                echo "ROCKETMQ_IPADDR    = $ROCKETMQ_IPADDR"
                echo "PORTAL_TC_IPADDR   = $PORTAL_TC_IPADDR"
                exit 1
            fi
			
			TMP_OPTS=" -p 15059:8080"
            TMP_OPTS="$TMP_OPTS --add-host rti.com:$PORTAL_TC_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host mysql.rti.com:$MYSQL_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host zookeeper.rti.com:$ZOOKEEPER_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host redis.rti.com:$REDIS_IPADDR"
            TMP_OPTS="$TMP_OPTS --add-host rocketmq.rti.com:$ROCKETMQ_IPADDR"
			
			# Set portal param, when login failed, redirect the address
			TMP_OPTS="$TMP_OPTS -e PORTAL_HOST=$PORTAL_TC_IPADDR -e PORTAL_PORT=$PORTAL_PORT"
			
			eval "$2='$TMP_OPTS'"
			;;

        * )
            echo "ERR: unkonwn argument."
            exit 1
            ;;
		
    esac

}

start_image() {
    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    local IMAGE_NAME=$1
    local WITH_DATA="$2"
    local OPTS="$3"
    local SRV_CONTAINER="${IMAGE_NAME}_server"

    # check if the image of mysql is exist.
    pull_image $IMAGE_NAME "check"

    if [[ $WITH_DATA == "yes" ]]; then

        local DATA_CONTAINER="${IMAGE_NAME}_data"

        # check if data container is exist
        local CONTAINER_NAME=$(docker inspect --type=container --format "{{.Name}}" $DATA_CONTAINER 2>/dev/null)
        if [ -z $CONTAINER_NAME ]; then
            # there is no mysql_data container, try to create one.
            echo "WRN: there is no $DATA_CONTAINER container."
            echo "INF: try to create $DATA_CONTAINER from $IMAGE_NAME image."

            docker run --name $DATA_CONTAINER \
                --entrypoint /bin/echo \
                $IMAGE_NAME \
                "INFO: Data-only container of $DATA_CONTAINER is created for $SRV_CONTAINER"

            if [ ! $? = "0" ]; then
                echo "ERR: fail to create $DATA_CONTAINER container, exit."
                exit 1
            else
                echo "INF: success to create $DATA_CONTAINER contariner."
            fi

        fi

        OPTS="$OPTS --volumes-from $DATA_CONTAINER"
    fi

    local SERVER=$(docker inspect --type=container --format "{{.State.Running}}" $SRV_CONTAINER 2>/dev/null)
    case "$SERVER" in
        "true" )
            echo "WRN: the $SRV_CONTAINER container is running now, can not start or restart it again."
            exit 0
            ;;

        "false" )
            echo "WRN: the $SRV_CONTAINER container is exist, try to restart it."

            docker restart $SRV_CONTAINER

            if [ ! $? = "0" ]; then
                echo "ERR: can no restart $SRV_CONTAINER container, try to start it."
                docker rm -f $SRV_CONTAINER
                docker run -d $OPTS --name $SRV_CONTAINER $IMAGE_NAME
                if [ ! -z $? ]; then
                    echo "ERR: can not start $SRV_CONTAINER, please check error message above."
                    exit 1
                fi
                echo "INF: success to start $SRV_CONTAINER container."
                exit 0
            fi
            echo "INF: success to restart $SRV_CONTAINER container."
            ;;

        * )
            docker run -d $OPTS --name $SRV_CONTAINER $IMAGE_NAME
            if [ ! $? = "0" ]; then
                echo "ERR: can not start $SRV_CONTAINER, please check error message above."
                exit 1
            fi
            echo "INF: success to start $SRV_CONTAINER container."
            ;;

    esac
}

stop_image() {

    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    local IMAGE_NAME=$1
    local SRV_CONTAINER="${IMAGE_NAME}_server"

    local SERVER=$(docker inspect --type=container --format "{{.State.Running}}" $SRV_CONTAINER 2>/dev/null)
    case "$SERVER" in
        "true" )
            if [[ $IMAGE_NAME == "mysql" ]]; then
                docker exec -i -t $SRV_CONTAINER /etc/init.d/$IMAGE_NAME stop
            elif [[ $IMAGE_NAME == "nginx" ]]; then
                docker exec -i -t $SRV_CONTAINER $IMAGE_NAME -s stop
            else
                docker stop $SRV_CONTAINER
            fi

            if [[ $? == "1" ]]; then
                echo
                echo "ERR: fail to stop $SRV_CONTAINER container, please check error message above."
                exit 1
            fi
            echo
            echo "INF: the $SRV_CONTAINER container is stoped."
            ;;

        "false" )
            echo "WRN: the $SRV_CONTAINER container is stoped, can not stop again."
            exit 1
            ;;

        * )
            echo "ERR: there is no $SRV_CONTAINER container."
            exit 1
            ;;

    esac
}

status_image() {
    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    local IMAGE_NAME=$1
    local DATA_CONTAINER="${IMAGE_NAME}_data"
    local SRV_CONTAINER="${IMAGE_NAME}_server"

    local SERVER=$(docker inspect --type=container --format "{{.State.Running}}" $SRV_CONTAINER 2>/dev/null)
    case "$SERVER" in
        "true" | "false" )
            docker ps -a -f "name=$SRV_CONTAINER" -f "name=$DATA_CONTAINER"
            ;;

        * )
            echo "ERR: there is no $SRV_CONTAINER container."
            exit 1
            ;;

    esac
}

remove_container() {
    if [ -z $1 ]; then
        echo "ERR: there is no container name as argument, exit."
        exit 1
    fi

    local CONTAINER_NAME=$1

    # check if container is exist
    local CONTAINER=$(docker inspect --type=container --format "{{.Name}}" $CONTAINER_NAME 2>/dev/null)
    if [ ! -z $CONTAINER ]; then
        local SERVER=$(docker inspect --type=container --format "{{.State.Running}}" $CONTAINER_NAME 2>/dev/null)
        if [[ "$SERVER" == "true" ]]; then
            stop_image `echo "$CONTAINER_NAME" | awk -F_ '{print $1}'`
        fi
        docker rm -f $CONTAINER_NAME
        if [ ! $? = "0" ]; then
            echo "ERR: fail to remove $CONTAINER_NAME, exit."
        fi
    fi

}

remove_image() {
    if [ -z $1 ]; then
        echo "ERR: there is no image name as argument, exit."
        exit 1
    fi

    local IMAGE_NAME=$1
    docker rmi -f "$IMAGE_NAME"

}

mode=$1
target=''
opts=''
with_data="no"

# find target image in the list.
for t in $IMAGES_LIST
do
    if [[ "$t" == "$2" ]]; then
        target="$2"
        break
    fi
done

if [ -z $target ]; then
    echo "ERR: invalid parameters: $@"
    usages
    exit 1
fi

# find with data container flag in the list.
for t in $DATA_CONTAINTER
do
    if [[ "$t" == "$2" ]]; then
        with_data="yes"
        break
    fi
done

case $mode in
    "start" )
        echo "Begin to start $target server."
        get_opts $target opts
        start_image $target "$with_data" "$opts"
        ;;

    "restart" )
        echo "Begin to restart $target server."
        remove_container "${target}_server"
        get_opts $target opts
        start_image $target "$with_data" "$opts"
        ;;

    "stop" )
        echo "Begin to stop $target server."
        stop_image $target
        ;;

    "status" )
        status_image "$target"
        ;;

    "pull" )
        pull_image $target
        ;;

    "remove" )
        remove_container "${target}_server"
        if [[ $with_data == "yes" ]]; then
            remove_container "${target}_data"
        fi
        remove_image "$target"
        ;;
    * )
        echo "ERR: unkonwn parameter:$@"
        usages
        exit 1
        ;;

esac

exit 0

