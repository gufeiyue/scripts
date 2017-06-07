#!/bin/bash

#set -x

#rm -rf  ../svn/code/cs/*
#rm -rf  ../svn/code/nMsm/*
rm -rf  ../lib/thirdlib/nMsm/*
rm -rf  ../lib/thirdlib/pushc/*
rm -rf  ../lib/thirdlib/isub/*
rm -rf  ../lib/thirdlib/customerservice/*

echo "clearn  finished ..."

svn --no-auth-cache -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/src/main/config ../svn/code/nMsm/config
svn --no-auth-cache -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/src/main/java ../svn/code/nMsm/src

svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/src/main/config ../svn/code/customerservice/config
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/src/main/java  ../svn/code/customerservice/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib/pushcommunication-src.jar
  ../lib/thirdlib/customerservice/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib//isub.jar
  ../lib/thirdlib/customerservice/src



#svn --no-auth-cache -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/config ../svn/code/ucm/config
#svn --no-auth-cache -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/src  ../svn/code/ucm/src
#svn --no-auth-cache -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/lib  ../lib/thirdlib/ucm

lib-msm(){
mkdir msmlibtmp
svn --no-auth-cache -r HEAD --username zhouwei13 --password 123QWEasd checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CRM_6.0/nMsm662/lib msmlibtmp
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

lib-cs(){
mkdir cslibtmp
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib cslibtmp

ls ../lib/appenginelib/ > list4
ls cslibtmp/ > list5
list6=`comm -1 -2 list4 list5`

for i in ${list6}

do 
    rm cslibtmp/${i}
done

mv cslibtmp/*.jar ../lib/thirdlib/customerservice/
rm ../lib/thirdlib/customerservice/*allcls*.jar

rm -rf cslibtmp
rm list4 list5
}

#lib-ucm(){
#mkdir ucmlibtmp
#svn --no-auth-cache -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/lib  ../lib/thirdlib/ucm ucmlibtmp
#
#ls ../lib/appenginelib/ > list10
#ls ucmlibtmp/ > list11
#list15=`comm -1 -2 list13 list14`
#
#for i in ${list15}
#do 
#        rm cslibtmp/${i}
#done
#
#mv ucmlibtmp/*.jar ../lib/thirdlib/ucm/
#rm ../lib/thirdlib/ucm/*allcls*.jar
#
#rm -rf ucmlibtmp
#rm list13 list14
#}

lib-msm $
lib-cs $
#lib-ucm $

echo "checkout code successfull ..."
