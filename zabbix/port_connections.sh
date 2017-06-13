#!/bin/bash
##################################

# Zabbix requested parameter
port="$1"
ip="$2"
port_status="$3"

port_CONNECTIONS_TOTAL=$(netstat -ant | grep "$2:$1" -c)
port_TIME_WAIT=$(netstat -ant | grep "$2:$1" | grep TIME_WAIT -c)
port_ESTABLISHED=$(netstat -ant | grep "$2:$1" | grep ESTABLISHED -c)
port_CLOSE_WAIT=$(netstat -ant | grep "$2:$1" | grep CLOSE_WAIT -c)
port_FIN_WAIT1=$(netstat -ant | grep "$2:$1" | grep FIN_WAIT1 -c)
port_FIN_WAIT2=$(netstat -ant | grep "$2:$1" | grep FIN_WAIT2 -c)

case $port_status in
  CONNECTIONS_TOTAL)	echo "$port_CONNECTIONS_TOTAL" ;;
  TIME_WAIT)   		echo "$port_TIME_WAIT" ;;
  ESTABLISHED)   	echo "$port_ESTABLISHED" ;;
  CLOSE_WAIT) 		echo "$port_CLOSE_WAIT" ;;
  FIN_WAIT1)            echo "$port_FIN_WAIT1" ;;
  FIN_WAIT2)            echo "$port_FIN_WAIT2" ;;
  *) 			echo ERROR_WRONG; exit 1;;
esac

exit 0
