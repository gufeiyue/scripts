#!/bin/csh
#######################
#生产环境：重发APP&重启 
#######################
set ANT_HOME=ant160
set HOME=/home/poctest
set JAVA_HOME=${HOME}/java
set PATH=${HOME}/java/bin:$PATH

ant160/bin/ant -f ../xml/allinone-oss.xml build-web-no-jspc | tee  log/build-web.log

