#/bin/sh
 
disk=$1
item=$2
 
case $item in
         rrqm)
            cat /tmp/iostat_output |grep "\b$disk\b"|tail -1|awk '{print $2}'
            ;;
         wrqm)
            cat /tmp/iostat_output |grep "\b$disk\b"|tail -1|awk '{print $3}'
            ;;
          rps)
            cat /tmp/iostat_output |grep "\b$disk\b"|tail -1|awk '{print $4}'
            ;;
          wps)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $5}'
            ;;
        rKBps)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $6}'
            ;;
        wKBps)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $7}'
            ;;
     avgrq-sz)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $8}'
            ;;
     avgqu-sz)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $9}'
            ;;
        await)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $10}'
            ;;
        r-await)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $11}'
            ;;
        w-await)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $12}'
            ;;
        svctm)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $13}'
            ;;
         util)
            cat /tmp/iostat_output |grep "\b$disk\b" |tail -1|awk '{print $14}'
            ;;
esac
