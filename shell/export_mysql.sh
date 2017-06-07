#!/bin/sh
set -x
time="$(date +"%Y-%m-%d")"
deletetime="$(date -d "-3 day" +"%Y-%m-%d")"

cd ${HOME}/backup 
all_db="agr aics aict aikb aisr aiwf base ci comframe esper ins1 ins2 isub ord partner party product push res sec ssm ucp"
#all_db="aict"
for db in $all_db
do
   ${HOME}/mysql/bin/mysqldump -S${HOME}/logs/mysql.sock -uroot  $db  > $time.$db.sql   
   tar -zcvf $time.$db.tar.gz $time.$db.sql
   test -e "$time.$db.sql" && rm "$time.$db.sql"
   test -e "$deletetime.$db.tar.gz" && rm "$deletetime.$db.tar.gz"
done
