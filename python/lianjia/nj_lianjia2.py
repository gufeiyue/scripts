#!/usr/bin/env python
#-*- coding:utf-8 -*-

import urllib2  
import time    
from bs4 import BeautifulSoup  
import re
import MySQLdb

#time.clock()  

def calculation():
    sql = "select * from nj_all_0128;"
    info = db_query(sql)
    number = num(sql)
    i = number
    while i >= 1:
        unit_price = info[number-i][4]
        average_price = info[number-i][5]
        #print sub_password
        #print unit_price
        unit_price1 = float(unit_price)
        average_price1 = float(average_price)
        proportion = unit_price1/average_price1
        #print proportion
        try:
        	db_update_proportion(proportion, unit_price, average_price)
        except:
        	pass
        #db_update(proportion, unit_price, average_price)
       # print unit_price1
        #print average_price1
        i = i -1


def getprice(url):
	page = urllib2.urlopen(url)  
	soup = BeautifulSoup(page)  
	for link in soup.find_all('span','xiaoquUnitPrice'):  
		try:
			average_price = link.get_text()
			return(average_price)
		except:
			return 0

def average_price():
	sql = "SELECT DISTINCT average_url FROM nj_all_0128 where `average_price` is null;"
	info = db_query(sql)
	number = num(sql)
	#print number
	i = number
	while i >= 1:
		average_url = info[number-i][0]
		#print average_url
		average_price = getprice(average_url)
		#print average_price
		db_update_price(average_price, average_url)
		i = i - 1


###DB###############################################################
def connect():
    conn= MySQLdb.connect(
        host='127.0.0.1',
        port = 3306,
        user='lianjia',
        passwd='gufy123',
        db ='lianjia',
        use_unicode=True, 
        charset="utf8"
        )
    return conn

#按照条件搜索一共多少条数据
def num(select_sql):
    conn=connect()
    cur = conn.cursor()
    #获得表中有多少条数据
    number = cur.execute(select_sql)
    return number
    conn.close()

#按照条件搜索所有表中数据
def db_query(sql):
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    info = cur.fetchall()
    return info
    conn.close()

def db_update_proportion(proportion, unit_price, average_price):
    update_sql = "update `nj_all_0128` set `proportion`='%s' where `unit_price` = '%s' and `average_price` = '%s';"
    sql = update_sql % (proportion, unit_price, average_price)
    print sql
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

def db_update_price(average_price, average_url):
    update_sql = "update `nj_all_0128` set `average_price`='%s' where `average_url` = '%s';"
    sql = update_sql % (average_price, average_url)
    #print sql
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

def delete(sql):
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()



##########################################################################


if __name__ == '__main__':
    sql1 = "update nj_all_0128 set unit_Price=replace(unit_Price,'单价','');"
    sql2 = "update nj_all_0128 set unit_Price=replace(unit_Price,'元/平米','');"
    delete(sql1)
    delete(sql2)
    average_price()
    calculation()
    










