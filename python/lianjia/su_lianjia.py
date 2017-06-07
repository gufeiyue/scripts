#!/usr/bin/env python
#-*- coding:utf-8 -*-

import urllib2  
import time    
from bs4 import BeautifulSoup  
import re
import MySQLdb
import HTMLParser


def getpage(area):
  url = 'http://su.lianjia.com/ershoufang/'+str(area)
  page = urllib2.urlopen(url)
  soup = BeautifulSoup(page) 
  page = soup.find("a", gahref="results_totalpage")
  try:
    totalpage = page.get_text()
    return int(totalpage)
  except :
    return 1
  
  

def getnew(area, page):
  for i in range(1,page+1):
    print "当前区域:", area
    print "当前区域:", i
    url = 'http://su.lianjia.com/ershoufang/'+str(area)+'/d'+str(i)
    #print url
    page = urllib2.urlopen(url)
    soup = BeautifulSoup(page)  
    for link in soup.find_all('div','info-panel'):
      url = link.a.get('href')
      url1 = 'http://su.lianjia.com' + url
      title = link.a.get_text()
      #print title
      #print link.get_text()
    
      where_list = link.find_all('div','where')
      for where in where_list:
        where1 = where.get_text()
        xiaoqu_url = where.a.get('href')
        xiaoqu_url1 = 'http://su.lianjia.com/' + xiaoqu_url
        #print where1
        #print xiaoqu_url1
    
      other_list = link.find_all('div','other')
      for other in other_list:
        other1 = other.get_text()
        #print other1

      agency_list = link.find_all('div','left agency')
      for agency in agency_list:
        agency1 = agency.get_text()
        #print agency1

      totalprice_list = link.find_all('div','price')
      for totalprice in totalprice_list:
        total_Price1 = totalprice.span.string
        #print total_Price1

      price_list = link.find_all('div','price-pre')
      for price in price_list:
        unit_Price1 = price.get_text()
        #print unit_Price1

      db_insert(title, where1, other1, agency1, total_Price1, unit_Price1, url1, xiaoqu_url1, area)

def delete_sql1():
    sql1 = "UPDATE su_gongyeyuanqu SET `where`=REPLACE(`where`,CHAR(10),'');"
    sql2 = "UPDATE su_gongyeyuanqu SET `where`=REPLACE(`where`,CHAR(13),'');"
    sql3 = "UPDATE su_gongyeyuanqu SET `where`=REPLACE(`where`,' ','');"
    sql4 = "UPDATE su_gongyeyuanqu SET `other`=REPLACE(`other`,CHAR(10),'');"
    sql5 = "UPDATE su_gongyeyuanqu SET `other`=REPLACE(`other`,CHAR(13),'');"
    sql6 = "UPDATE su_gongyeyuanqu SET `other`=REPLACE(`other`,' ','');"
    sql7 = "UPDATE su_gongyeyuanqu SET `agency`=REPLACE(`agency`,CHAR(10),'');"
    sql8 = "UPDATE su_gongyeyuanqu SET `agency`=REPLACE(`agency`,CHAR(13),'');"
    sql9 = "UPDATE su_gongyeyuanqu SET `agency`=REPLACE(`agency`,'  ','');"
    delete(sql1)
    delete(sql2)
    delete(sql3)
    delete(sql4)
    delete(sql5)
    delete(sql6)
    delete(sql7)
    delete(sql8)
    delete(sql9)

###DB###############################################################

def calculation():
    sql = "select * from su_gongyeyuanqu where `proportion` is NULL;"
    info = db_query(sql)
    number = num(sql)
    i = number
    while i >= 1:
        unit_price = info[number-i][6]
        average_price = info[number-i][8]
        #print sub_password
        #print unit_price
        unit_price1 = float(unit_price)
        average_price1 = float(average_price)
        proportion = unit_price1/average_price1
        print proportion
        try:
          db_update_proportion(proportion, unit_price, average_price)
        except:
          pass
        i = i -1


def getprice(url):
  average_price2 = 1
  listing_price2 = 1
  page = urllib2.urlopen(url)  
  soup = BeautifulSoup(page)  
  for priceInfo in soup.find_all('div','priceInfo'):  
    for listing_price in priceInfo.find_all('div', 'item col1'):
      for listing_price1 in listing_price.find_all('span', 'p'):
        try:
          listing_price2 = listing_price1.get_text()
          #print listing_price2       
        except:
          pass

    for average_price in priceInfo.find_all('div', 'item'):
      for average_price1 in average_price.find_all('span', 'p'):
        try:
          average_price2 = average_price1.get_text()
          #print average_price2
        except:
          pass

  db_update_price(listing_price2, average_price2, url)


def price_url():
  sql = "SELECT DISTINCT average_url FROM su_gongyeyuanqu where `average_price` is null;"
  info = db_query(sql)
  number = num(sql)
  #print number
  i = number
  while i >= 1:
    price_url = info[number-i][0]
    print price_url
    getprice(price_url)
    #print average_price
    i = i - 1


def delete_sql2():
    sql1 = "UPDATE su_gongyeyuanqu SET `average_price`=REPLACE(`average_price`,CHAR(10),'');"
    sql2 = "UPDATE su_gongyeyuanqu SET `average_price`=REPLACE(`average_price`,CHAR(13),'');"
    sql3 = "UPDATE su_gongyeyuanqu SET `average_price`=REPLACE(`average_price`,' ','');"
    sql4 = "UPDATE su_gongyeyuanqu SET `listing_price`=REPLACE(`listing_price`,CHAR(10),'');"
    sql5 = "UPDATE su_gongyeyuanqu SET `listing_price`=REPLACE(`listing_price`,CHAR(13),'');"
    sql6 = "UPDATE su_gongyeyuanqu SET `listing_price`=REPLACE(`listing_price`,' ','');"
    sql7 = "UPDATE su_gongyeyuanqu SET `unit_Price`=replace(`unit_Price`,'元/平','');"
    delete(sql1)
    delete(sql2)
    delete(sql3)
    delete(sql4)
    delete(sql5)
    delete(sql6)
    delete(sql7)





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


def db_insert(title, where1, other1, agency1, total_Price1, unit_Price1, url1, xiaoqu_url1, area):
    update_sql = "INSERT INTO `su_gongyeyuanqu` (`id`, `title`, `where`, `other`, `agency`, `total_price`, `unit_Price`, `average_price`, `proportion`, `url`, `average_url`, `area`, `remark`) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', NULL, NULL, '%s', '%s', '%s', '');"
    sql = update_sql % (title, where1, other1, agency1, total_Price1, unit_Price1, url1, xiaoqu_url1, area)
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

def db_update_proportion(proportion, unit_price, average_price):
    update_sql = "update `su_gongyeyuanqu` set `proportion`='%s' where `unit_Price` = '%s' and `average_price` = '%s';"
    sql = update_sql % (proportion, unit_price, average_price)
    #print sql
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

def db_update_price(listing_price, average_price, average_url):
    update_sql = "update `su_gongyeyuanqu` set `listing_price`='%s',`average_price`='%s' where `average_url` = '%s';"
    sql = update_sql % (listing_price, average_price, average_url)
    print sql
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
    su_gaoxin1 = ('fengqiao1', 'huguan111', 'hengtang1', 'kejicheng1', 'xushuguanjingjijishukaifaqu', 'shishan1', 'shengtaicheng1', 'tongan111', 'yangshanshengtaiqu1')
    su_gusu1 = ('canglangxincheng', 'fengmen1', 'guanqianjie1', 'huqiuchengqu', 'jinchangxincheng', 'jinmen', 'loumen1', 'liuyuanxiyuan1', 'nanmen', 'pingjianglu', 'pingjiangxincheng', 'qimen1', 'shilu1', 'shiquanjie1', 'suzhoudaxue1', 'taohuawu1', 'xujiang')
    su_wuzhong1 = ('chengnan1', 'dongshan1', 'guangfu', 'guoxiang', 'linhu', 'longxi2', 'luzhi', 'mudu', 'nanshuanghu', 'suyuan', 'taihuxinchengwuzhong', 'wusongjiangchanyeyuan', 'xukou', 'xishan22', 'xiangshan', 'yinshanhu', 'yuexi')
    su_gongyeyuanqu = ('baitang', 'chefang', 'donghuan1', 'dongshahu', 'dushuhugaojiaoqu', 'yuanhudong', 'hudong', 'huxicbd', 'kuatang', 'loufeng1', 'linglong12', 'qingjianhu', 'shuanghu', 'shengpu', 'xietang', 'weiting')
    su_wujiang1 = ('fenhu', 'hengshan', 'pingwang', 'songling', 'shengze', 'taihuxinchengwujiang', 'tongli', 'taoyuan5', 'zhenze')
    for area in su_gongyeyuanqu:
        page = getpage(area)
        getnew(area, page)
        delete_sql1()
    print "======================================================================================================================"
    print "======================================================================================================================"
    print "======================================================================================================================"
    price_url()
    delete_sql2()
    calculation()








