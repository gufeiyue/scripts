#!/usr/bin/env python
#-*- coding:utf-8 -*-

# Build-in / Std
import os, sys, time, platform, random
import re, json, cookielib
import logging
# requirements
import requests, termcolor
import urllib
import urllib2
import cookielib
import MySQLdb
from lxml import html
from os.path import join, abspath, dirname
import random


logging.basicConfig(level=logging.INFO,
                format='%(levelname)s %(message)s',
                filename=abspath(join(dirname(__file__), 'punch_in.log')),
                filemode='a')
logging.getLogger("requests").setLevel(logging.WARNING)

###登录###############################################################
headers = {
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36',
        'Origin':'http://www.shanbay.com',
        'Content-Type':'application/x-www-form-urlencoded',
        'Connection':'keep-alive',
        'Cache-Control':'no-cache'
        }

def login(uname, pwd):
    r = requests.get('http://www.shanbay.com/accounts/login/')
    cookie = dict(csrftoken=r.cookies['csrftoken'])
    #data = {'username': "gufeiyue238@126.com",'password':"feiyue548",'csrfmiddlewaretoken': r.cookies['csrftoken']}
    data = {'username': uname,'password':pwd,'csrfmiddlewaretoken': r.cookies['csrftoken']}
    p = requests.post('http://www.shanbay.com/accounts/login/',
        data = data,
        cookies = cookie,
        headers = headers,
        allow_redirects=False
        )
    ck = p.cookies
    #print ck['userid']
    if p.status_code == 302:
        return ck
    else:
        logging.warn('登录失败！,检查密码')

def list():
    r = requests.get('http://www.shanbay.com/api/v1/read/article/news/')
    pattern = re.compile('read/article/reviews/(\d{1,6})')
    news = pattern.findall(r.text)
    return news
    #print news[1]

def getshell(ck):
    url = 'http://www.shanbay.com/coins/account/'
    r = requests.get(url, cookies=ck, headers=headers)
    pattern = re.compile(r'label-info.*all>([0-9].*)</h1>')
    news = pattern.findall(r.text)
    return news[0]

###自动打卡###############################################################
def read(nid, ck):
    url = 'http://www.shanbay.com/api/v1/read/article/%s' % nid
    r = requests.get(url, cookies=ck, headers=headers)
    s = json.loads(r.text)
    #print s
    if 0 == s['status_code']:
        url = 'http://www.shanbay.com/api/v1/read/article/user/%s/' % nid
        num = random.randint(300,500)
        data = {'used_time': num, 'operation': 'finish'}
        r = requests.put(url, cookies=ck, data=data, headers=headers)
        s = json.loads(r.text)
        if (s['status_code'] == 0) or (s['status_code'] == 1):
            return True
        else:
            logging.warn('打开文章失败')
            logging.warn(r.text)
            return False
    else:
        logging.warn('完成阅读失败')
        logging.warn(r.text)
        return False

def punch(ck,sub_name):
    url = 'http://www.shanbay.com/api/v1/checkin/?for_web=true'
    data = {'for_web': 'true'}
    r = requests.post(url, cookies=ck, data=data, headers=headers)
    s = json.loads(r.text)
    #print s
    if s['status_code'] == 0 or s['status_code'] == 1:
        print "Punch result: %s success" % sub_name
        return True
    else:
        logging.warn('打卡失败')
        logging.warn(r.text)
        return False

def punch_in_days(ck):
    url = 'http://www.shanbay.com/api/v1/checkin'
    data = {'for_web': 'true'}
    r = requests.post(url, cookies=ck, data=data, headers=headers)
    s = json.loads(r.text)
    #print s
    days = s['data']['num_checkin_days']
    return days
###DB处理###############################################################
def connect():
    conn= MySQLdb.connect(
        host='127.0.0.1',
        port = 3306,
        user='shanbay',
        passwd='gufy123',
        db ='shanbay',
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

#更新打卡天数、状态
def db_update_days(sub_name,days,shells):
    update_sql = "update `test_account` set `times`='%s',`punch`='U',`shells`='%s' where `sub_name` = '%s';"
    sql = update_sql % (days,shells,sub_name)
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

#清除打卡状态
def clear():
    clear_sql = "update `test_account` set `punch`='E'"
    conn=connect()
    cur = conn.cursor()
    cur.execute(clear_sql)
    conn.commit()
    conn.close()

##########################################################################

# 搜索主号，对小号进行打卡
def test_parent_sub():
    parent_name = 'gufeiyue238@126.com'
    select_sql = "select * from test_account where `parent_name`='%s';"
    sql = select_sql % parent_name
    info = db_query(sql)
    number = num(sql)
    i = 1
    while i <= number:
        sub_name = info[number-i][3]
        sub_password = info[number-i][4]
        print "Punch account:",sub_name
        ck = login(sub_name, sub_password)
        news = list()
        read(news[1], ck)
        read(news[2], ck)
        punch(ck,sub_name)
        days = punch_in_days(ck)
        #print days
        day = db_update_days(sub_name,days)
        i = i +1

#搜索天数<8,且是否可以用状态为 N 的数据
def test_all():
    sql = "select * from test_account where `times`< 8 and `is_available`='N' and `punch`='E';"
    info = db_query(sql)
    number = num(sql)
    i = number
    while i >= 1:
        sub_name = info[number-i][3]
        sub_password = info[number-i][5]
        print "Punch account:",sub_name
        #print sub_password
        ck = login(sub_name, sub_password)
        news = list()
        read(news[2], ck)
        read(news[1], ck)
        punch(ck,sub_name)
        shells = getshell(ck)
        days = punch_in_days(ck)
        #print days
        day = db_update_days(sub_name,days,shells)
        i = i - 1


#单个帐号打卡，同时更新天数
def test_one():
    sub_name = 'sinabppjx0'
    sub_password = 'ww12345'
    ck = login(sub_name, sub_password)
    news = list()
    read(news[3], ck)
    read(news[4], ck)
    punch(ck,sub_name)
    days = punch_in_days(ck)
    #print days
    day = db_update_days(sub_name,days)

if __name__ == '__main__':
    test_all()
    #test_one()
    #test_parent_sub()

