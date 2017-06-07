#!/bin/sh
export ANT_HOME=${HOME}/ant
export PATH=$HOME/java/bin:$PATH
rm -rf ../dist/crm
mkdir ../dist/crm
${ANT_HOME}/bin/ant -f ../xml/jsp.xml init > logs/jsp.log &
