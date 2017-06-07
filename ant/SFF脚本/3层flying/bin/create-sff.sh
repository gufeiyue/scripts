#!/bin/sh
export ANT_HOME=ant160
export PATH=$HOME/java/bin:$PATH
rm -rf ../dest/sff
mkdir ../dest/sff
../ant/bin/ant -f ../xml/build.xml compile-sff > logs/create-sff.log &
