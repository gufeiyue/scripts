#!/bin/csh
#######################
#�����������ط�APP&���� 
#######################
set ANT_HOME=ant160
set HOME=/home/poctest
set JAVA_HOME=${HOME}/java
set PATH=${HOME}/java/bin:$PATH


ant160/bin/ant -f ../xml/allinone-oss.xml svn-full-down-dev | tee  log/checkout-sff-dev.log

