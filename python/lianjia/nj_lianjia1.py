#!/usr/bin/env python
#-*- coding:utf-8 -*-

import urllib2  
import time    
from bs4 import BeautifulSoup  
import re
import simplejson
import MySQLdb

#time.clock()  
def getinfo(area):
	for i in range(100):
		url = 'http://nj.lianjia.com/ershoufang/'+str(area)+'/pg'+str(i)+'/'
		#print url
		page = urllib2.urlopen(url)
		soup = BeautifulSoup(page)  
		for link in soup.find_all('div','info clear'):
			url1 = link.a.get('href')
			#print url1
		
			title_list = link.find_all('div','title')
			for title in title_list:
				title1 = title.string
				#print title1
		
			houseinfo_list = link.find_all('div','houseInfo')
			for houseinfo in houseinfo_list:
				houseinfo1 = houseinfo.get_text()
				average_url = houseinfo.a.get('href')
				#print houseinfo1
				average_price = getprice(average_url)
				#print average_price

			# a_price_list = link.find_all('div','tooltip bottom')
			# for average_price in a_price_list:
			# 	average_price1 = average_price.get_text()
			# 	print average_price

			tag_list = link.find_all('div','tag')
			for tag in tag_list:
				tag1 = tag.get_text()
				#print tag1

			price_list = link.find_all('div','unitPrice')
			for price in price_list:
				unit_Price1 = price.span.string
				#unit_Price2 = unit_Price1[2:7]

			db_insert(title1, houseinfo1, tag1, unit_Price2, average_price, url1)

#print(time.clock()) 

def getnew(area, page):
	for i in range(1,page+1):
		#url = 'http://hz.lianjia.com/ershoufang/'+str(area)+'/pg'+str(i)+'/'
		#http://nj.lianjia.com/ershoufang/gulou/pg3co32hu1nb1sf1/
		url = 'http://nj.lianjia.com/ershoufang/'+str(area)+'/pg'+str(i)+'co32hu1nb1y2sf1/'
		print url
		page = urllib2.urlopen(url)
		soup = BeautifulSoup(page)  
		for link in soup.find_all('div','info clear'):
			url1 = link.a.get('href')
			#print url1
		
			title_list = link.find_all('div','title')
			for title in title_list:
				title1 = title.get_text()
				#print title1
		
			houseinfo_list = link.find_all('div','houseInfo')
			for houseinfo in houseinfo_list:
				houseinfo1 = houseinfo.get_text()
				average_url = houseinfo.a.get('href')
				#print houseinfo1

			tag_list = link.find_all('div','tag')
			for tag in tag_list:
				tag1 = tag.get_text()
				#print tag1

			price_list = link.find_all('div','unitPrice')
			for price in price_list:
				unit_Price1 = price.span.string
				#unit_Price2 = unit_Price1[2:7]

			db_insert(title1, houseinfo1, tag1, unit_Price1, average_url, url1, area)


def getpage(area):
	#url = 'http://hz.lianjia.com/ershoufang/'+str(area)+'/'
	url = 'http://nj.lianjia.com/ershoufang/'+str(area)+'/co32hu1nb1y2sf1/'
	page = urllib2.urlopen(url)
	soup = BeautifulSoup(page)
	for link in soup.find_all('div','page-box house-lst-page-box'):
			url1 = link['page-data']
			ret_dict = simplejson.loads(url1)
			page = ret_dict['totalPage']
			return page


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


def db_insert(title, houseinfo, tag, unit_Price1, average_price, url, area):
    update_sql = "INSERT INTO `nj_all_0128` (`id`, `title`, `houseinfo`, `tag`, `unit_Price`, `average_price`, `url`, `average_url`, `remark`) VALUES (NULL, '%s', '%s', '%s', '%s', NULL, '%s', '%s', '%s');"
    sql = update_sql % (title, houseinfo, tag, unit_Price1, url, average_price, area)
    print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()



##########################################################################

if __name__ == '__main__':
	#hangzhou = ('chengzhan', 'daxuelu1', 'fenghuangshan', 'fuxing', 'gulou2', 'hubin1', 'jinjiang1', 'nanxing', 'qingbo', 'sijiqing1', 'wangjiang', 'wushan1', 'xiaoying1', 'xiongzhenlou', 'zhijiang', 'baoshan1111','changshouqiao','sichoucheng1','dayingpan1','moyaying111','chaoming11','changqing1112','tianshui1','wulin11','wenhui1')
	#hangzhou = ('sichoucheng1','dayingpan1','moyaying111','chaoming11','changqing1112','tianshui1','changshouqiao')
	# hangzhou = ('baoshan1111', 'dayingpan1')
	nanjing = ('gulou', 'qinhuai', 'xuanwu', 'jiangning')
	#nanjing = ('jianye', 'yuhuatai')
	for area in nanjing:
		page = getpage(area)
		print page
		getnew(area, page)
		#print page

	#getnew('nanjing', 93)









