#!/bin/sh
export ANT_HOME=../ant
export PATH=$HOME/java/bin:$PATH
../ant/bin/ant -Dproject.list=$2 -f ../xml/$1 compile > logs/createnv.log &
