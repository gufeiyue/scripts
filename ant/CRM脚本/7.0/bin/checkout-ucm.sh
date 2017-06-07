#!/bin/bash

#set -x

#rm -rf  ../svn/code/cs/*
#rm -rf  ../svn/code/nMsm/*
#rm -rf  ../lib/thirdlib/nMsm/*
rm -rf  ../lib/thirdlib/ucm/*

echo "clearn  finished ..."

svn -r HEAD --username deploy --password 123 checkout  https://10.6.0.30/svn/UCM/code/appengine/UCM/config ../svn/code/ucm/config
svn -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/code/appengine/UCM/src  ../svn/code/ucm/src
#svn -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/code/appengine/UCM/lib  ../lib/thirdlib/ucm

lib-ucm(){
mkdir ucmlibtmp
svn -r HEAD --username deploy --password 123 checkout https://10.6.0.30/svn/UCM/code/appengine/UCM/lib  ../lib/thirdlib/ucm ucmlibtmp

ls ../lib/appenginelib/ > list13
ls ucmlibtmp/ > list14
list15=`comm -1 -2 list13 list14`

for i in ${list15}
do 
        rm ucmlibtmp/${i}
done

mv ucmlibtmp/*.jar ../lib/thirdlib/ucm/
rm ../lib/thirdlib/ucm/*allcls*.jar

rm -rf ucmlibtmp
rm list13 list14
}

lib-ucm $

echo "checkout code successfull ..."
