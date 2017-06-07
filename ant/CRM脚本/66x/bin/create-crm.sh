#!/bin/sh
export ANT_HOME=${HOME}/ant
export JAVA_HOME=${HOME}/jdk1.6.0_45
export CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

rm -rf ../dest/crm
mkdir ../dest/crm
${ANT_HOME}/bin/ant -f ../xml/build.xml compile-crm |tee  logs/create-crm.log
