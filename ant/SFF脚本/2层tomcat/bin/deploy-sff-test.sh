#!/bin/sh

set -x

module=$1

#deploy web
sff_web(){
sshpass -p "aiweb" scp ../dest/oss_rl.war aiweb@10.1.229.136:./ftptmp
sshpass -p "aiweb" ssh aiweb@10.1.229.136 <<EOF
cd ~/console
./deploy_web.sh
EOF
}

#deploy aisff
sff_inter(){
sshpass -p "aisff" scp ../dest/local/sff_config.jar aisff@10.1.229.136:./ftptmp
sshpass -p "aisff" scp ../dest/local/sff_lib.jar aisff@10.1.229.136:./ftptmp
sshpass -p "aisff" ssh aisff@10.1.229.136 <<EOF
cd ~/console
./deploy_queue.sh
EOF
}


if [ ${module} = "web" ];then
	sff_web $;
elif [ ${module} = "aisff" ];then
	sff_inter $;
else echo "please input the right module."
fi
