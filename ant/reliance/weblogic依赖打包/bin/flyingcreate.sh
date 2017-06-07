#!/bin/sh
export JAVA_HOME=${HOME}/java
export ANT_HOME=/home/aiveris7/work/reliance-pit/ant
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib
/home/aiveris7/work/reliance-pit/ant/bin/ant -Dproject.list=$2 -f /home/aiveris7/work/reliance-pit/xml/flying/$1 compile > logs/build_flying.log & 
