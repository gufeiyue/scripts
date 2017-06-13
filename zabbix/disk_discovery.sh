#!/bin/bash
disks=(`iostat -xtc|grep -E "\bvd[abcdefg]\b|\bsd[abcdefg]\b|\bhio[abcdefg]\b"|awk '{print $1}'|sort|uniq  2>/dev/null`)  
length=${#disks[@]} 
printf "{\n"  
printf '\t'"\"data\":["  
for ((i=0;i<$length;i++))  
do  
        printf '\n\t\t{'  
        printf "\"{#DISK_NAME}\":\"${disks[$i]}\"}"  
        if [ $i -lt $[$length-1] ];then  
                printf ','  
        fi  
done 
printf "\n\t\t]\n"  
printf "}\n" 

