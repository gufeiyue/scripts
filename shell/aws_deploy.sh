#!/bin/sh

module=$1

scpapp(){
#传ear包
for name in agentviewpaas businesscommon integrated marketingcampaign marketingsaas marketsegment process push pushsaas saleschannel troubleticket troubleticketsaas workforce;
do
	/data/transfer/sshpass -p "aiapp" scp ${name}.ear aiapp@172.31.2.138:/data/aiapp/app_deploy;
	/data/transfer/sshpass -p "aiapp" ssh aiapp@172.31.2.138 'cd /data/aiapp/${name}/app;rm *.jar;jar -xf /data/aiapp/app_deploy/${name}.ear'
done
}

scphub(){
#传ear包
	/data/transfer/sshpass -p "aisaashub" scp saashub.ear aisaashub@172.31.2.158:/data/aisaashub/hub_deploy;
	/data/transfer/sshpass -p "aipaashub" scp paashub.ear aipaashub@172.31.2.158:/data/aipaashub/hub_deploy;

#解压ear包
	/data/transfer/sshpass -p "aisaashub" ssh aisaashub@172.31.2.158 'cd /data/aisaashub/saashub/lib;rm *.jar;jar -xf /data/aisaashub/hub_deploy/saashub.ear'
	/data/transfer/sshpass -p "aipaashub" ssh aipaashub@172.31.2.158 'cd /data/aipaashub/paashub/lib;rm *.jar;jar -xf /data/aipaashub/hub_deploy/paashub.ear'
}

scpexe(){
#传ear包
	/data/transfer/sshpass -p "aiprc" scp aiprc.ear aiprc@172.31.2.137:/data/aiprc/exe_deploy;

#解压ear包
	/data/transfer/sshpass -p "aiprc" ssh aiprc@172.31.2.137 'cd /data/aiprc/lib;rm *.jar;jar -xf /data/aiprc/exe_deploy/aiprc.ear'
}

restartapp(){
/data/transfer/sshpass -p "aiapp" ssh aiapp@172.31.2.138 <<EOF
cd /data/aiapp/console
./stop.sh
./start.sh
EOF
}

restartsaashub(){
/data/transfer/sshpass -p "aisaashub" ssh aisaashub@172.31.2.158 <<EOF
cd /data/aisaashub/saashub/app/bin
./stop_APP_ENGINE_SAASHUB1.sh
./start_APP_ENGINE_SAASHUB1.sh
EOF
}

restartpaashub(){
/data/transfer/sshpass -p "aipaashub" ssh aipaashub@172.31.2.158 <<EOF
cd /data/aipaashub/paashub/app/bin
./stop_APP_ENGINE_PAASHUB1.sh
./start_APP_ENGINE_PAASHUB1.sh
EOF
}

restartexe(){
/data/transfer/sshpass -p "aiprc" ssh aiprc@172.31.2.137 <<EOF
cd /data/aiprc/console
./stop_all.sh
./start_all.sh
EOF
}


if [ ${module} = "scpapp" ];then
	scpapp $;
elif [ ${module} = "scphub" ];then
	scphub $;
elif [ ${module} = "scpexe" ];then
	scpexe $;
elif [ ${module} = "scpall" ];then
	scpapp $;
	scphub $;
	scpexe $;
elif [ ${module} = "restartapp" ];then
	restartapp $;	
elif [ ${module} = "restartsaashub" ];then
	restartsaashub $;
elif [ ${module} = "restartpaashub" ];then
	restartpaashub;
elif [ ${module} = "restartexe" ];then
	restartexe;
elif [ ${module} = "restartall" ];then
	restartapp $;
	restartsaashub $;
	restartpaashub;
	restartexe;
else echo "please input the right module."
fi