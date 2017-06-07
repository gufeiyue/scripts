#!/bin/bash

#set -x

#rm -rf  ../svn/code/cs/*
#rm -rf  ../svn/code/nMsm/*
rm -rf  ../lib/thirdlib/nMsm/*

echo "clearn  finished ..."
#svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev-SR0.3/CS/src/main/config ../svn/code/cs/config
#svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV6.6/trunk/V6.6.2-dev-SR0.3/CS/src/main/java ../svn/code/cs/src


#svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.2/CRM_6.0/nMsm662/src/main/config ../svn/code/nMsm/config
#svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.2/CRM_6.0/nMsm662/src/main/java ../svn/code/nMsm/src

svn -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/src/main/config ../svn/code/nMsm/config
svn -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/src/main/java ../svn/code/nMsm/src

svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/src/main/config ../svn/code/customerservice/config
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/src/main/java  ../svn/code/customerservice/src
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib  ../lib/thirdlib/customerservice

svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/PUSH/config ../svn/code/pushc/config
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/PUSH/src  ../svn/code/pushc/src
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/PUSH/WebRoot/WEB-INF/lib  ../lib/thirdlib/pushc

svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/ISUB/config ../svn/code/isub/config
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/ISUB/src  ../svn/code/isub/src
svn -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/ISUB/WebRoot/WEB-INF/lib  ../lib/thirdlib/isub


lib-msm(){
mkdir msmlibtmp
svn -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/lib msmlibtmp
mv msmlibtmp/*/*.jar msmlibtmp/
rm -rf msmlibtmp/aibusi
rm -rf msmlibtmp/apache
rm -rf msmlibtmp/appframe
rm -rf msmlibtmp/other
rm -rf msmlibtmp/runtime
rm -rf msmlibtmp/tomcat

ls ../lib/appenginelib/ > list1
ls msmlibtmp/ > list2
list3=`comm -1 -2 list1 list2`

for i in ${list3}
do 
	rm msmlibtmp/${i}
done

mv msmlibtmp/*.jar ../lib/thirdlib/nMsm/
rm ../lib/thirdlib/nMsm/*allcls*.jar

rm -rf msmlibtmp
rm list1 list2
}

lib-msm $
echo "checkout code successfull ..."
