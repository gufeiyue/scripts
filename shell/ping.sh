#!/bin/bash
for i in $(seq 1 254)
do
	ping -c 1 172.16.0.$i &>/dev/null
	if [ $? -eq 0 ];then
		echo "$i is up "
	else
		echo "$i is down"
	fi
done
