#!/bin/sh

set -x

module=$1

#deploy web
sff_web(){
sshpass -p "aiweb" scp ../dest/sff/sff_web.war aiweb@10.1.229.118:./sff-web/sff-web-g1/app
sshpass -p "aiweb" ssh aiweb@10.1.229.118 <<EOF
cd ~/sff-web/sff-web-g1/bin
./stopallsrv.sh
cd ~/sff-web/sff-web-g1/app
jar -xf ~/sff-web/sff-web-g1/app/sff_web.war
rm -rf ~/sff-web/sff-web-g1/app/sff_web.war
cd ~/sff-web/sff-web-g1/bin
./startallsrv.sh
EOF
}

#deploy app
sff_app(){
sshpass -p "aiapp" scp ../dest/sff/sff_app_0.ear aiapp@10.1.229.117:./sff-app/sff-app-g1/app
sshpass -p "aiapp" ssh aiapp@10.1.229.117 <<EOF
cd ~/sff-app/sff-app-g1/bin
./stopallsrv.sh
cd ~/sff-app/sff-app-g1/app
jar -xf ~/sff-app/sff-app-g1/app/sff_app_0.ear
rm -rf ~/sff-app/sff-app-g1/app/sff_app_0.ear
cd ~/sff-app/sff-app-g1/bin
./startallsrv.sh
EOF
}

#deploy aisff
sff_aisff(){
sshpass -p "aisff" scp ../dest/sff/config_0.jar aisff@10.1.229.129:./config
sshpass -p "aisff" scp ../dest/sff/sff_lib.jar aisff@10.1.229.129:./lib
sshpass -p "aisff" ssh aisff@10.1.229.129 <<EOF
cd ~/app
./stop_all_queue.sh
cd ~/config
jar -xf ~/config/config_0.jar
rm -rf ~/config/config_0.jar
cd ~/lib
jar -xf ~/lib/sff_lib.jar
rm -rf ~/lib/sff_lib.jar
cd ~/app
./start_all_queue.sh
EOF
}

#deploy inter
sff_inter(){
sshpass -p "aiintersff" scp ../dest/sff/sff_configext.jar aiintersff@10.1.229.129:./config
sshpass -p "aiintersff" scp ../dest/sff/sff_lib.jar aiintersff@10.1.229.129:./lib
sshpass -p "aiintersff" ssh aiintersff@10.1.229.129 <<EOF
cd ~/app
./stop_all_queue.sh
cd ~/config
jar -xf ~/config/sff_configext.jar
rm -rf ~/config/sff_configext.jar
cd ~/lib
jar -xf ~/lib/sff_lib.jar
rm -rf ~/lib/sff_lib.jar
cd ~/app
./start_all_queue.sh
EOF
}


if [ ${module} = "web" ];then
	sff_web $;
elif [ ${module} = "app" ];then
	sff_app $;
elif [ ${module} = "aisff" ];then
	sff_aisff $;
elif [ ${module} = "inter" ];then
	sff_inter $;
else echo "please input the right module."
fi
