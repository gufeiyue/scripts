#!/bin/bash

netstat -antp |awk '{print$5}'|grep -E ^[1-9]|sort|uniq -c |sort -nr|head -3|sed ':a;N;$!ba;s/\n/ /g' > /tmp/top_conn_output 2>/dev/null

localhost="$HOSTNAME"

top=$(cat /tmp/top_conn_output | sed -n '1p')

zabbix_sender -z 172.16.0.188 -p 10051 -s $localhost -k top_connect -o "$top"
