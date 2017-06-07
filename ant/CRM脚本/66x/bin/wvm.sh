#!/bin/sh
export ANT_HOME=${HOME}/ant
export PATH=$HOME/java/bin:$PATH
${ANT_HOME}/bin/ant -f ../xml/wvm.xml init | tee logs/wvm.log
