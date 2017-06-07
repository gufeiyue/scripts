#!/bin/bash
set -x

NowDate=`date +"%Y-%m-%d"`

#all_db="agr aics aikb aisr aiwf base ci comframe ins1 ins2 isub ord partner party product push res sec ssm ucp"
all_db="agr aics aikb aict aisr aiwf base ci comframe esper ins1 ins2 isub ord partner party product push res sec ssm ucp"
#all_db="aict"
for db in $all_db
do
    #输出正在处理的数据库用户
    echo ${NowDate}.${db}
    #解压.tar.gz文件
	tar -zxvf $NowDate.$db.tar.gz

  #执行sql语句
  mysql -u root -S $HOME/logs/mysql.sock "$db" -e "source $HOME/backup/$NowDate.$db.sql1"

  #删除上一次执行的文件
  rm -rf $NowDate*.sql

done