#/bin/sh
 
job=$1
item=$2
 
case $item in
         status)
            cat /tmp/jobstatus_output |grep -w "\b$job\b"|tail -1|awk '{print $2}'
            ;;
esac
