#! /bin/sh
a=$#
b=$@
c=($b)
for  ((i=1;i<="$a";i++))
do   
    docker ps -a | grep ${c[i-1]} | awk '{print$1}' | while read pid
    do 
        echo "${pid}"
    done
done
