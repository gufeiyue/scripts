echo "BEGIN TO CHECKOUT ALL!!!!"

svn --no-auth-cache co -r HEAD --username deploy-scm --password ailkscm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3.1/CRM ../svn/CRM

echo "BEGIN TO INIT integration floder!!!!"
rm -rf ../svn/crm
mkdir -p ../svn/crm/src
mkdir -p ../svn/crm/config
mkdir -p ../svn/crm/lib
mkdir -p ../svn/crm/html
echo "BEGIN TO RESOLVE nAgreement!!!!"
cp  -rf  ../svn/CRM/nAgreement/src/main/java/*             ../svn/crm/src
cp  -rf  ../svn/CRM/nAgreement/src/main/config/*   ../svn/crm/config

echo "BEGIN TO RESOLVE nCustInt662!!!!"
cp  -rf  ../svn/CRM/nCustInt662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nCustInt662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nCustMgr662!!!!"
cp  -rf  ../svn/CRM/nCustMgr662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nCustMgr662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nMsm662!!!!"
cp  -rf  ../svn/CRM/nMsm662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nMsm662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nOrder662!!!!"
cp  -rf  ../svn/CRM/nOrder662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nOrder662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nOther662!!!!"
cp  -rf  ../svn/CRM/nOther662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nOther662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nPartner662!!!!"
cp  -rf  ../svn/CRM/nPartner662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nPartner662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nUip662!!!!"
cp  -rf  ../svn/CRM/nUip662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nUip662/src/main/config/* 	../svn/crm/config

echo "BEGIN TO RESOLVE nUcm662!!!!"
cp  -rf  ../svn/CRM/nUcm662/src/main/java/*                 ../svn/crm/src
cp  -rf  ../svn/CRM/nUcm662/src/main/config/*       ../svn/crm/config

echo "BEGIN TO RESOLVE nWeb662!!!!"
cp  -rf  ../svn/CRM/nWeb662/src/main/java/* 		../svn/crm/src
cp  -rf  ../svn/CRM/nWeb662/src/main/config/* 	../svn/crm/config
cp  -rf  ../svn/CRM/nWeb662/lib/*/* 					../svn/crm/lib
cp  -rf  ../svn/CRM/nWeb662/WebRoot/* 					../svn/crm/html

echo "BEGIN TO RESOLVE nWeb662 LIB OF aibusi!!!!"
rm -rf  ../svn/crm/lib/aibusi

echo "BEGIN TO DELETE .svn floder!!!!"
find ../svn/crm -name '.svn'|xargs rm -rf

#################################################
#echo "BEGIN TO EXPORT RUNTIMECONFIG!!!!"
#rm -rf ../config/21/runtime-config/crm*

#svn co -r HEAD --username deploy-scm --password ailkscm  http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3.1/t_21  ../config/21/runtime-config

svn --no-auth-cache co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3.1/t_21/crm_app ../config/21/runtime-config/crm_app
svn --no-auth-cache co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3.1/t_21/crm_web ../config/21/runtime-config/crm_web
svn --no-auth-cache co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3.1/t_21/crm_exe ../config/21/runtime-config/crm_exe
svn --no-auth-cache co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3.1/t_21/crm_inter ../config/21/runtime-config/crm_inter

#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_01/crm_app ../config/01/runtime-config/crm_app
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_01/crm_web ../config/01/runtime-config/crm_web
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_01/crm_exe ../config/01/runtime-config/crm_exe
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_01/crm_inter ../config/01/runtime-config/crm_inter


#将最新的crm代码打包并拷贝到ftp目录，供测试使用
#ftp_dir=/home/vsftpd/crm_tmp
#cd ${BUILD_ROOT}/svn
#tar -czf crm.tar.gz crm
#mv crm.tar.gz ${ftp_dir}/


