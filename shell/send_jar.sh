#!/bin/sh

module=$1
#crmsaas/lib 需添加integrated_flysving.jar、customer.jar，productoffering.jar，resourcespecification.jar。
crmsaas(){
sshpass -p "aiapp" scp ../dest/integrated/integrated_flysving.jar aiapp@10.1.226.87:./crmsaas/app
sshpass -p "aiapp" scp ../dest/customer/customer.jar aiapp@10.1.226.87:./crmsaas/app
sshpass -p "aiapp" scp ../dest/productoffering/productoffering.jar aiapp@10.1.226.87:./crmsaas/app
sshpass -p "aiapp" scp ../dest/resourcespecification/resourcespecification.jar aiapp@10.1.226.87:./crmsaas/app
}

#saashub svpkg_domain.xml中sv的domain全部修改为16
saashub(){
sshpass -p "aisaashub" ssh aisaashub@10.1.228.85<<EOF
cd ~/saashub/config/system/service
sed -i 's/domain=".*"/domain="16"/g' svpkg_domain.xml
EOF
}

template(){
cd /home/aiveris7/work/veris72Full-test/svn/code/crmsaas/config
jar -cf busi_meta_template.jar template
cp busi_meta_template.jar /home/aiveris7/work/veris72Full-test/dest/common
}


scphub(){
sshpass -p "aipaashub" scp ../dest/common/busi_meta_template.jar aipaashub@10.1.228.85:./paashub/lib
sshpass -p "aisaashub" scp ../dest/common/busi_meta_template.jar aisaashub@10.1.228.85:./saashub/lib
sshpass -p "allinhub" scp ../dest/common/busi_meta_template.jar allinhub@10.1.228.85:./allinhub/lib
sshpass -p "aiprc" scp ../dest/common/busi_meta_template.jar aiprc@10.1.228.86:./lib

}

scpaiprc(){
sshpass -p "aiprc" scp ../dest/common/busi_meta_template.jar aiprc@10.1.228.86:./lib
}

intgrtrppaas(){
sshpass -p "aipaashub" ssh aipaashub@10.1.228.85<<EOF
cd ~/paashub/config/system/service
sed -i  '/Intgrt/s/domain=".*"/domain="9"/g' svpkg_domain.xml
EOF
}

intgrtrpsaas(){
sshpass -p "aisaashub" ssh aisaashub@10.1.228.85<<EOF
cd ~/saashub/config/system/service
sed -i  '/Intgrt/s/domain=".*"/domain="9"/g' svpkg_domain.xml
EOF
}





scpallhub(){
#传ear包
for name in authframe businesscommon crmsaas customer integrated integration marketingcampaign marketingresource order paashub product productoffering resourcespecification saashub;
do
	sshpass -p "allinhub" scp /home/aiveris7/work/veris72Full-test/dest/ear/${name}.ear allinhub@10.1.228.85:./allinhub/lib;
done

#传模板包
sshpass -p "allinhub" scp ../dest/common/busi_meta_template.jar allinhub@10.1.228.85:./lib
#sshpass -p "allinhub" scp ../dest/ear/busi_meta_template.jar allinhub@10.1.228.86:./lib

#解压ear包
sshpass -p "allinhub" ssh allinhub@10.1.228.85 'cd /home/allinhub/allinhub/lib;find ./ -name "*.ear" -exec jar -xf {} \;'

#删除config、ear包
sshpass -p "allinhub" ssh allinhub@10.1.228.85 'cd /home/allinhub/allinhub/lib;rm *_config.jar;rm *.ear'
}

restart(){
sshpass -p "allinhub" ssh allinhub@10.1.228.85 <<EOF
cd ~/allinhub/app/bin
./stop_APP_ENGINE_ALLINHUB.sh
./start_APP_ENGINE_ALLINHUB.sh
EOF
}


if [ ${module} = "crmsaas" ];then
	crmsaas $;
elif [ ${module} = "saashub" ];then
	saashub $;
elif [ ${module} = "template" ];then
	template $;
elif [ ${module} = "scphub" ];then
	scphub $;	
elif [ ${module} = "scpaiprc" ];then
	scpaiprc $;
elif [ ${module} = "scpallhub" ];then
	scpallhub $;
elif [ ${module} = "restart" ];then
	restart $;
elif [ ${module} = "intgrtrppaas" ];then
        intgrtrppaas $;
elif [ ${module} = "intgrtrpsaas" ];then
        intgrtrpsaas $;
else echo "please input the right module."
fi
