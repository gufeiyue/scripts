#!/bin/bash
#Fucation:zabbix low-level discovery
port1=($(netstat -tln  | grep "\btcp\b" | awk -F '[ :]+' '{print $5}'))
port2=($(netstat -tln  | grep "\btcp6\b" | awk -F '[:]+' '{print $2}'))
declare -a result_list
t=0
flag=0
#echo port=${port1[*]}
#echo port=${port2[*]}
for m in "${port1[@]}"
do
        for l in "${port2[@]}"
        do
                if [ "$m" == "$l" ]; then
                        flag=1
                        break
                fi
        done
        if [ $flag -eq 0 ]; then
                result_list[t]=$m
                t=$((t+1))
        else
                flag=0
        fi
done
declare -a port
port=(${result_list[*]} ${port2[*]})
#echo result_list=${result_list[*]}
#echo port=${port[*]}
	printf '{\n'
        printf '\t"data":[\n'
        	for key in ${!port[@]}
                do
                	if [[ "${#port[@]}" -gt 1 && "${key}" -ne "$((${#port[@]}-1))" ]];then
                		printf '\t {\n'
                		printf "\t\t\t\"{#MEMPORT}\":\"${port[${key}]}\"},\n"
                	else [[ "${key}" -eq "((${#port[@]}-1))" ]]
                        	printf '\t {\n'
                       		printf "\t\t\t\"{#MEMPORT}\":\"${port[${key}]}\"}\n"
                       fi
               done
printf '\t ]\n'
printf '}\n'
