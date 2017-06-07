#!/bin/ksh
set -x

#all_db="AICS AISR AIWF BASE BUSINESSCOMMON COMFRAME CUSTOMER CUSTOMERORDER ESHOP INTEGRATION ISUB JMS LOG MARKET PAASHUB PRODOFFER PRODUCT PUSH RES RESOURCESPEC SAASHUB SEC SSM"
all_db="aics aisr aiwf base businesscommon comframe customer customerorder eshop integration isub jms log market paashub prodoffer product push res resourc espec saashub sec ssm "
		
for db in $all_db
do
    #输出正在处理的数据库用户
    echo ${db}

    #oracle导入dmp文件
    expdp $db/Al4Fr0Uy schemas=$db dumpfile=$db.dmp DIRECTORY=impdp_dir; 
	#impdp $db/Al4Fr0Uy DIRECTORY=impdp_dir  DUMPFILE=$db.dmp SCHEMAS=$db;
done

#创建directory
#create directory impdp_dir as '/home/oracle/impdp_dir';
#grant read,write on directory impdp_dir to test(user);