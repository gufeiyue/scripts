#!/usr/bin/env python
#-*- coding:utf-8 -*-

import sys

reload(sys)
sys.setdefaultencoding('utf-8')

try:
    import json
except ImportError:
    import simplejson as json
import cookielib
import urllib2
import requests
import BeautifulSoup
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
import fileinput
import random
import requests
import datetime
from itertools import islice

# 创建对象的基类:
Base = declarative_base()
# 初始化数据库连接:
engine = create_engine('mysql://root:gufy123@localhost:3306/hosts_manage?charset=utf8', encoding='utf-8')
# 创建ession类型:
ession = sessionmaker(bind=engine)
session = ession()



def execute_sql(sql):
    hosts = session.execute(sql)
    session.commit()
    session.close()


def all_files():
    sql = "select * from `ty_server`;"
    files = session.execute(sql)
    return files


cookie = '''Hm_lvt_eae8678115723816e132ad60d7b40aa0=1489456472,1489462549,1489469983,1489471799; Hm_lpvt_eae8678115723816e132ad60d7b40aa0=1489472376; JSESSIONID=4C5C591B4339A27500FAF60826D50A89; Language=zh; ty_server_uid=5ddadb4a914b7be807cd41370f28e70c; jsessionid-server=a85ce0f1-e509-4a5e-bd0d-d6c83cd64e3d; jsessionid-alarm=708d0c10-c2ad-4910-9105-6aa65deece34; ty_alarm_uid=a884e769f6b56edcb952f47ce3e70004; ty_saas_uid=bc72a6a2eb662d7f3c9f9e7221485bc2; jsessionid-saas=58146f7c-0075-49a5-ad44-d57588e4a7e1; Hm_lvt_93daecc2db29efa5d83566cf580e84af=1489113994,1489143616,1489456451,1489469975; Hm_lpvt_93daecc2db29efa5d83566cf580e84af=1489471795; ty_app_uid=db406bd97e937900c46b6388b91ee471; jsessionid-mobile=f9652c93-4a5c-467b-b9ed-a71e8da0b839; nTalk_CACHE_DATA={uid:kf_9212_ISME9754_guestTEMPA823-1116-E7,tid:1489469976077811}; NTKF_T2D_CLIENTID=guestTEMPA823-1116-E7BE-425A-B7E6096026EB'''

header = {
'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36',
'Connection': 'keep-alive',
'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
'Cookie': cookie}





# data = {'username': uname, 'password': pwd, 'csrfmiddlewaretoken': r.cookies['csrftoken']}

#url = 'https://report.tingyun.com/mobile/mobileApp/userAnalysis/initUserInfo'
if __name__ == '__main__':
	all_files = all_files()
	for all_file in all_files:
		print all_file
	# url = 'https://report.tingyun.com/server/report/exportWebAction/149162/exportWebAction/-1/31/33/1'
	# xlsdata = requests.get(url,headers=header, stream=False)
	# f = open("14962" + '.xls', 'ab')
	# f.write(xlsdata.content)
	# f.close()





