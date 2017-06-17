#!/bin/bash

DIR1=`find /JAVA_Files/ -name log`
DIR2=`find /JAVA_Files/ -name logs`
DIR3=`find /usr/local/ -name tomcat*8080`

mylist=(${DIR1} ${DIR2} ${DIR3}/logs)

for i in ${mylist[@]};do
        if [ -n $i ];then
                find $i/ -mtime +15 -exec rm -f {} \;
        fi
done
echo > ${DIR3}/logs/catalina.out
