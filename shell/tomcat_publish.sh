#!/bin/bash

#$1 project name
#$2 env name
#$3 BUILD ID
#$4 roll back number
#sh $0 msapi|nonoweb|nonopay stb|sit|pre|prod $BUILD_ID $RollBackNo
PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin
if [ $# -lt 3 ] || [ $# -gt 4 ];then
	echo "Please Use *** $0 [ProjectName(msapi, nonoweb...)] [EnvName(stb, sit, pre, prod)] [BUILD_ID] [RollBackNo(if is necessary)]*** to start"
	echo "Now Exit with 1"
	exit 1
elif [ $# = 3 ];then
	Bid=$3
	RollBack=0
	echo "正常发布,发布编号是: $3"
elif [ $# = 4 ];then
	Bid=$4
	RollBack=1
	echo "本次发布执行回滚,回滚编号是: $4"
fi

Publish() {
	RemoteDir="/TomcatJobs/JK_BUILD_${JobName}_${Bid}"
	JKName=${RemoteDir%_*}
	echo "开始发布 ${IP}"
	if [ ${RollBack} = 1 ];then
		echo "开始执行回滚, 重建软链到 ${RemoteDir} 并重启tomcat"
#		sshpass ssh -o StrictHostKeychecking=no root@${IP} "ln -snf ${RemoteDir} ${TomcatLink}; bash /etc/init.d/restart_tomcat.sh ${JobName} ${JKName}"
		sshpass ssh -o StrictHostKeychecking=no root@${IP} "ln -snf ${RemoteDir} ${TomcatLink}; bash /etc/init.d/restart_tomcat.sh restart ${JKName}"
	else
		echo "开始正常发布, 在 ${IP} 上创建 ${RemoteDir}"
		sshpass ssh -o StrictHostKeychecking=no root@${IP} "mkdir -p ${RemoteDir}"
		echo "开始同步新 ${SourceDir} 到 ${IP}:${RemoteDir}"
		rsync -e ssh -avr --delete ${SourceDir}/ root@${IP}:${RemoteDir}
		echo "重建软链到 ${RemoteDir} 并重启tomcat"
#		sshpass ssh -o StrictHostKeychecking=no root@${IP} "rm -rf ${TomcatLink}*; ln -snf ${RemoteDir} ${TomcatLink}; bash /etc/init.d/restart_tomcat.sh ${JobName} ${JKName}"
		sshpass ssh -o StrictHostKeychecking=no root@${IP} "rm -rf ${TomcatLink}*; ln -snf ${RemoteDir} ${TomcatLink}; bash /etc/init.d/restart_tomcat.sh restart ${JKName} $1"
	fi
}

case $1 in
msapi)
	JobName="msapi"
	TomcatDir="/usr/local/tomcat-msapi-8080"
	TomcatLink=${TomcatDir}"/webapps/msapi"
	SourceDir="/JKS/jobs/msapi/workspace/target/msapi-1.0"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.117)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.32)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.31)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.31 172.16.0.32)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
nonoweb)
	JobName="nonoweb"
	TomcatDir="/usr/local/tomcat-nonoweb-8080"
	TomcatLink=${TomcatDir}"/webapps/nono-web"
	SourceDir="/JKS/jobs/nono_all/workspace/nono-app/nono-web/target/nono-web"
	PackageSize=$(ls -lh ${SourceDir}.war|awk '{print  $5}'|grep -o "[0-9]*")
	if [ $2 = "stb" ];then
		IPList=(192.168.3.158)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.37)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.34)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.81 172.16.0.82)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	if [ $PackageSize -gt 70 ];then
		for IP in ${IPList[@]};do
			Publish
		done
	else
		echo "nono_package fail!"
	fi
	;;
wcofin)
	JobName="nono-wcoFin"
	TomcatDir="/usr/local/tomcat-nonoweb-8080"
	TomcatLink=${TomcatDir}"/webapps/nono-wcoFin"
	SourceDir="/JKS/jobs/wcofin/workspace/nono-app/nono-web/target/nono-wcoFin"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.158)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.37)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.34)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.177 172.16.0.178)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
nonopay)
	JobName="nonopay"
	TomcatDir="/usr/local/tomcat-nonopay-8080"
	TomcatLink=${TomcatDir}"/webapps/nonobank-pay"
	SourceDir="/JKS/jobs/nono_pay/workspace/target/nonobank-pay-0.0.1-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.119)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.33)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.33)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.33 172.16.0.34)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
csmfin)
	JobName="csmfin"
	TomcatDir="/usr/local/tomcat-csmfin-8080"
	TomcatLink=${TomcatDir}"/webapps/nono-csmFin"
	SourceDir="/JKS/jobs/csmfin/workspace/nono-app/nono-web/target/nono-csmFin"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.54)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.11)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.63)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.128 172.16.0.129)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
unifi)
	JobName="unifi"
	TomcatDir="/usr/local/tomcat-unifi-8080"
	TomcatLink=${TomcatDir}"/webapps/nono-web"
	SourceDir="/JKS/jobs/unifi/workspace/nono-app/nono-web/target/nono-web"
#	if [ $2 = "stb" ];then
#		IPList=(192.168.3.54)
	if [ $2 = "sit" ];then
		IPList=(192.168.4.9)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.47)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.125 172.16.0.126)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
#########################################################################################
#重构 chonggou
#########################################################################################
g2-usr|usr)
	JobName="usr-app"
	TomcatDir="/usr/local/tomcat-usr-8080"
	TomcatLink=${TomcatDir}"/webapps/usr-app"
	SourceDir="/JKS/jobs/usr/workspace/user-app/target/user-app-1.0"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.168)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.118)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.58)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.216  172.16.0.217 172.16.0.240 172.16.0.242 172.16.0.95)
		#IPList=(172.16.0.95)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.95" ];then
			TomcatLink=${TomcatDir}"/webapps/usr-job"
		fi		
		Publish
	done
	;;
usr-api|usr-crm)
	JobName="usr-api"
	TomcatDir="/usr/local/tomcat-usr-api-8080"
	TomcatLink=${TomcatDir}"/webapps/usr-api"
	SourceDir="/JKS/jobs/usr_crm/workspace/user-app/target/user-app-1.0"
	if [ $2 = "prod" ];then
		IPList=(172.16.0.102)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
g2-mkt|mkt)
	JobName="mkt-app"
	TomcatDir="/usr/local/tomcat-mkt-8080"
	TomcatLink=${TomcatDir}"/webapps/mkt-app"
	SourceDir="/JKS/jobs/mkt/workspace/target/mkt-app"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.164)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.114)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.54)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.208 172.16.0.209 172.16.0.84)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.84" ];then
            TomcatLink=${TomcatDir}"/webapps/mkt-job"
        fi
		Publish
	done
	;;
g2-prod|prod)
	JobName="prod"
	TomcatDir="/usr/local/tomcat-prod-8080"
	TomcatLink=${TomcatDir}"/webapps/prod-app"
	SourceDir="/JKS/jobs/prod/workspace/prod-app/target/prod-app"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.166)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.116)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.56)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.212 172.16.0.213 172.16.0.91)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.91" ];then
            TomcatLink=${TomcatDir}"/webapps/prod-job"
        fi
		Publish
	done
	;;
g2-acc|acc)
	JobName="acc"
	TomcatDir="/usr/local/tomcat-acc-8080"
	TomcatLink=${TomcatDir}"/webapps/acc-app"
	SourceDir="/JKS/jobs/acc/workspace/target/acc-app-1.0.0-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.161)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.111)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.50)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.200 172.16.0.201 172.16.0.62 )
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.62" ];then
            TomcatLink=${TomcatDir}"/webapps/acc-job"
        fi
		Publish
	done
	;;
acc_fae)
	JobName="acc-fae"
	TomcatDir="/usr/local/tomcat-acc-fae-8080"
	TomcatLink=${TomcatDir}"/webapps/acc-fae-app"
	SourceDir="/JKS/jobs/acc_fae/workspace/target/acc-fae-app-1.0.0-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.67)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.67)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.85)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.85)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
acc-api|acc-crm)
	JobName="acc-api"
	TomcatDir="/usr/local/tomcat-acc-api-8080"
	TomcatLink=${TomcatDir}"/webapps/acc-api"
	SourceDir="/JKS/jobs/acc_crm/workspace/target/acc-crm-app-1.0.0-SNAPSHOT"
	if [ $2 = "prod" ];then
		IPList=(172.16.0.15)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
g2-invt|invt)
	JobName="invt"
	TomcatDir="/usr/local/tomcat-invt-8080"
	TomcatLink=${TomcatDir}"/webapps/invt-app"
	SourceDir="/JKS/jobs/invt/workspace/target/invt-app"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.162)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.112)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.52)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.204 172.16.0.205 172.16.0.68 172.16.0.116)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.68" ];then
            TomcatLink=${TomcatDir}"/webapps/invt-job"
        fi
		Publish
	done
	;;
g2-pay|pay)
	JobName="pay"
	TomcatDir="/usr/local/tomcat-pay-8080"
	TomcatLink=${TomcatDir}"/webapps/pay-app"
	SourceDir="/JKS/jobs/pay/workspace/target/pay-app"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.165)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.115)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.55)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.58 172.16.0.211 172.16.0.90)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.90" ];then
            TomcatLink=${TomcatDir}"/webapps/pay-job"
        fi
		Publish
	done
	;;
pay-api|pay-crm)
	JobName="pay-api"
	TomcatDir="/usr/local/tomcat-pay-api-8080"
	TomcatLink=${TomcatDir}"/webapps/pay-api"
	SourceDir="/JKS/jobs/pay_crm/workspace/target/pay-crm-app"
	if [ $2 = "prod" ];then
		IPList=(172.16.0.17)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
g2-trd|trd)
	JobName="trd"
	TomcatDir="/usr/local/tomcat-trd-8080"
	TomcatLink=${TomcatDir}"/webapps/trd-app"
	SourceDir="/JKS/jobs/trd/workspace/target/trd-0.0.1-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.167)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.117)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.57)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.214 172.16.0.215 172.16.0.92)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.92" ];then
            TomcatLink=${TomcatDir}"/webapps/trd-job"
        fi
		Publish
	done
	;;
trd_fae)
	JobName="trd-fae"
	TomcatDir="/usr/local/tomcat-trd-fae-8080"
	TomcatLink=${TomcatDir}"/webapps/trd-fae-app"
	SourceDir="/JKS/jobs/trd_fae/workspace/target/trd-0.0.1-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.68)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.68)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.86)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.86)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
g2-loan|loan)
	JobName="loan"
	TomcatDir="/usr/local/tomcat-loan-8080"
	TomcatLink=${TomcatDir}"/webapps/loan-app"
	SourceDir="/JKS/jobs/loan/workspace/target/loan-app-0.0.1-SNAPSHOT"
	
	if [ $2 = "stb" ];then
		IPList=(192.168.3.163)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.113)
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.53)
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.206 172.16.0.207 172.16.0.69)	
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		if [ ${IP} = "172.16.0.69" ];then
			TomcatLink=${TomcatDir}"/webapps/loan-job"
		fi
		Publish
	done
	;;
loan-api|loan-crm)
	JobName="loan-api"
	TomcatDir="/usr/local/tomcat-loan-api-8080"
	TomcatLink=${TomcatDir}"/webapps/loan-api"
	SourceDir="/JKS/jobs/loan_crm/workspace/target/loan-app-0.0.1-SNAPSHOT"
	if [ $2 = "prod" ];then
		IPList=(172.16.0.16)
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish
	done
	;;
g2-risk|risk)
	JobName="risk"
	TomcatDir="/usr/local/tomcat-risk-8080"
	TomcatLink=${TomcatDir}"/webapps/risk-app"
	SourceDir="/JKS/jobs/risk/workspace/target/risk-app-0.0.1-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.126)
#		rsync --delete ${SourceDir}/WEB-INF/classes/config_file/stb/ /etc/ansible/source_files/g2_config_files/risk/config_stb
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.126)
	#	rsync --delete ${SourceDir}/WEB-INF/classes/config_file/sit/ /etc/ansible/source_files/g2_config_files/risk/config_sit
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.126)
#		rsync --delete ${SourceDir}/WEB-INF/classes/config_file/pre/ /etc/ansible/source_files/g2_config_files/risk/config_pre
		#rsync -avl /etc/ansible/source_files/g2_config_files/risk/config_pre/* 172.16.3.126:/JAVA_Files/risk/config/
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.123 172.16.0.124)
#		rsync --delete ${SourceDir}/WEB-INF/classes/config_file/prod/ /etc/ansible/source_files/g2_config_files/risk/config_prod
		rsync -avl /etc/ansible/source_files/g2_config_files/risk/config_prod/* 172.16.0.123:/JAVA_Files/risk/config/
        rsync -avl /etc/ansible/source_files/g2_config_files/risk/config_prod/* 172.16.0.124:/JAVA_Files/risk/config/
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish $TomcatDir
	done
	;;
risk_front)
	JobName="risk-frontal"
	#TomcatDir="/usr/local/tomcat-risk-8080"
	TomcatDir="/usr/local/tomcat-risk-frontal-8081"
	TomcatLink=${TomcatDir}"/webapps/risk-frontal-app"
	SourceDir="/JKS/jobs/risk_front/workspace/target/risk-frontal-app-0.0.1-SNAPSHOT"
	if [ $2 = "stb" ];then
		IPList=(192.168.3.126)
	elif [ $2 = "sit" ];then
		IPList=(192.168.4.126)
		rsync -avl /etc/ansible/source_files/g2_config_files/risk-frontal/config_sit/* 192.168.4.126:/JAVA_Files/risk-frontal/config/
	elif [ $2 = "pre" ];then
		IPList=(172.16.3.126)
		rsync -avl /etc/ansible/source_files/g2_config_files/risk-frontal/config_pre/* 172.16.3.126:/JAVA_Files/risk-frontal/config/
	elif [ $2 = "prod" ];then
		IPList=(172.16.0.123 172.16.0.124)
		rsync -avl /etc/ansible/source_files/g2_config_files/risk-frontal/config_prod/* 172.16.0.123:/JAVA_Files/risk-frontal/config/
		rsync -avl /etc/ansible/source_files/g2_config_files/risk-frontal/config_prod/* 172.16.0.124:/JAVA_Files/risk-frontal/config/
	else
		echo "Unknown EnvName $1 $2, Please Check"
		echo "Now Exit with 2"
		exit 2
	fi
	for IP in ${IPList[@]};do
		Publish $TomcatDir
	done
	;;
rulecode)
    JobName="rulecode"
    TomcatDir="/usr/local/tomcat-rulecode-7070"
    TomcatLink=${TomcatDir}"/webapps/rulecode-app"
    SourceDir="/JKS/jobs/rulecode/workspace/target/rulecode-app-0.0.1-SNAPSHOT"
    if [ $2 = "stb" ];then
        IPList=(192.168.3.126)
    elif [ $2 = "sit" ];then
        IPList=(192.168.4.126)
    elif [ $2 = "pre" ];then
        IPList=(172.16.3.87)
    elif [ $2 = "prod" ];then
        IPList=(172.16.0.123 172.16.0.124)
		rsync -avl /etc/ansible/source_files/g2_config_files/risk-rulecode/config_prod/* 172.16.0.123:/JAVA_Files/rulecode/config/
        rsync -avl /etc/ansible/source_files/g2_config_files/risk-rulecode/config_prod/* 172.16.0.124:/JAVA_Files/rulecode/config/
    else
        exit 2
    fi
    for IP in ${IPList[@]};do
        Publish $TomcatDir
    done
    ;;
rule_app)
    JobName="rule_app"
    TomcatDir="/usr/local/tomcat-rule-8080"
    TomcatLink=${TomcatDir}"/webapps/rule-app"
    SourceDir="/JKS/jobs/rule_app/workspace/target/rule-app-0.0.1-SNAPSHOT"
    if [ $2 = "stb" ];then
        IPList=(192.168.3.183)
    elif [ $2 = "sit" ];then
        IPList=(192.168.4.47)
    elif [ $2 = "pre" ];then
        IPList=(172.16.3.87)
    elif [ $2 = "prod" ];then
        IPList=(172.16.0.220)
    else
        exit 2
    fi
    for IP in ${IPList[@]};do
        Publish $TomcatDir
    done
    ;;
*)
	echo "Unknown ProjectName $1, Please Check"
	echo "Now Exit with 3"
	exit 3
	;;
esac

#for IP in ${IPList[@]};do
#	Publish
#done
