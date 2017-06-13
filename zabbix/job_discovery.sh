#!/bin/bash
jobs=(`cat job_lists | awk '{print $1}' 2>/dev/null`)  
length=${#jobs[@]} 
printf "{\n"  
printf '\t'"\"data\":["  
for ((i=0;i<$length;i++))  
do  
        printf '\n\t\t{'  
        printf "\"{#JOB_NAME}\":\"${jobs[$i]}\"}"  
        if [ $i -lt $[$length-1] ];then  
                printf ','  
        fi  
done 
printf "\n\t\t]\n"  
printf "}\n"
