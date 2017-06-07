#!/bin/sh
export ANT_HOME=${HOME}/ant
export JAVA_HOME=${HOME}/jdk1.6.0_45
export CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

rm -rf ../dest/cs
mkdir ../dest/cs
../ant/bin/ant -f ../xml/build.xml compile-cs |tee logs/create-cs.log 
