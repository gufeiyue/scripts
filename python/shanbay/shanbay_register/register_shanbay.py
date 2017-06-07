#!/usr/bin/env python
#-*- coding:utf-8 -*-

# Build-in / Std
import os, sys, time, platform, random
import re, json, cookielib
import logging
# requirements
import requests, termcolor
from termcolor import colored
import urllib
import urllib2
import cookielib
import MySQLdb
from lxml import html
from os.path import join, abspath, dirname



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

###自动打卡###############################################################
def read(nid, ck):
    url = 'http://www.shanbay.com/api/v1/read/article/%s' % nid
    r = requests.get(url, cookies=ck, headers=headers)
    s = json.loads(r.text)
    #print s
    if 0 == s['status_code']:
        url = 'http://www.shanbay.com/api/v1/read/article/user/%s/' % nid
        data = {'used_time': 300, 'operation': 'finish'}
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


###获取需要充值帐号邀请链接###############################################################

def invite_url(ck):
	url = 'https://www.shanbay.com/referral/invite/'
	r = requests.get(url, cookies=ck, headers=headers)
	pattern = re.compile('<a href="http://www.shanbay.com/referral/ref/(.*?)/">')
	invite = pattern.findall(r.text)
	return invite[0]

######################################################################################
def download_captcha_0():
    url = "https://www.shanbay.com/accounts/register"
    r = requests.get(url)
    pattern = re.compile('<img src="/captcha/image/(.*?)/"')
    pattern = pattern.findall(r.text)
    captcha_0 = pattern[0]
    return captcha_0

def download_captcha_1(captcha_0):
    url = "https://www.shanbay.com/captcha/image/%s/" % captcha_0
    r = requests.get(url)
    if int(r.status_code) != 200:
        raise NetworkError(u"验证码请求失败")
    image_name = u"verify"
    open( image_name, "wb").write(r.content)
    logging.info(u"正在调用外部程序渲染验证码 ... ")
    if platform.system() == "Linux":
        logging.info(u"Command: xdg-open %s &" % image_name )
        os.system("xdg-open %s &" % image_name )
    elif platform.system() == "Darwin":
        logging.info(u"Command: open %s &" % image_name )
        os.system("open %s &" % image_name )
    else:
        logging.info(u"我们无法探测你的作业系统，请自行打开验证码 %s 文件，并输入验证码。" % os.path.join(os.getcwd(), image_name) )

    sys.stdout.write(termcolor.colored(u"请输入验证码: ", "cyan") )
    captcha_1 = raw_input( )
    return captcha_1

def judge(sub_name, sub_password, sub_email,ref_url):
	lists = [1, 2]
	captcha_0 = download_captcha_0()
	captcha_1 = download_captcha_1(captcha_0)
	captcha_url = 'https://www.shanbay.com/api/v1/common/captcha/?res=%s&key=%s' % (captcha_1,captcha_0)
	captcha = requests.get(captcha_url)
	s = json.loads(captcha.text)
    #print s['status_code']
	if s['status_code'] == 0:
		lists[0] = captcha_0
		lists[1] = captcha_1
		r = zhuce(sub_name, sub_password, sub_email, captcha_0, captcha_1, ref_url)
	else:
		judge(sub_name, sub_password, sub_email,ref_url)



def zhuce(uname, pwd, email, captcha_0, captcha_1, ref_url):
    r = requests.get('https://www.shanbay.com/accounts/register/?ref_code=e93082dfc0')
    cookie = dict(csrftoken=r.cookies['csrftoken'])
    #print r.cookies['csrftoken']
    agree = 'on'
    #data = {'username': "gufeiyue238@126.com",'password':"feiyue548",'csrfmiddlewaretoken': r.cookies['csrftoken']}
    data = {'username': uname,'email': email,'password1':pwd,'password2': pwd,'captcha_0': captcha_0, 'captcha_1': captcha_1, 'agree': agree, 'csrfmiddlewaretoken': r.cookies['csrftoken']}
    r = requests.post(ref_url,
        data = data,
        cookies = cookie,
        headers = headers
        )
    #s = json.loads(r.text)
    return r



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


#更新账户是否可用状态
def db_update_available(sub_name):
    update_sql = "update `shanbay_account` set `is_available`='N' where `sub_name` = '%s';"
    sql = update_sql % sub_name
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

#更新已使用帐号的主帐号
def db_update_parent(parent_name,parent_password,sub_name):
    update_sql = "update `shanbay_account` set `parent_name`='%s',`parent_password`='%s' where `sub_name`='%s';"
    sql = update_sql % (parent_name,parent_password,sub_name)
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

#清除打卡状态
def clear():
    clear_sql = "update `shanbay_account` set `punch`='E'"
    conn=connect()
    cur = conn.cursor()
    cur.execute(clear_sql)
    conn.commit()
    conn.close()

##########################################################################

def test():
    shell_num = 10000
    
    parent_name = "gufeiyue238@126.com"
    parent_password = "feiyue941!"
    number = shell_num/500
    #获取 parent 帐号的邀请链接

    ck = login(parent_name, parent_password)
    ref_code = invite_url(ck)
    ref_url = "https://www.shanbay.com/accounts/register/?ref_code=%s" % ref_code

    #ref_url = "https://www.shanbay.com/accounts/register/?ref_code=c715b32abb" 


    select_sql = "select * from shanbay_account where `is_available`='Y'  limit %s;"
    sql = select_sql % number
    info = db_query(sql)
    
	#print info
    i = number
    while i >= 1:
		print colored('=========这是第 %s 个帐号===========', 'red') % i
		sub_name = info[number-i][3]
		sub_email = info[number-i][4]
		sub_password = info[number-i][5]
		print "register name:",sub_name
		print "register email:",sub_email
		print "register password:",sub_password
		judge(sub_name, sub_password, sub_email,ref_url)
		db_update_available(sub_name)
		db_update_parent(parent_name,parent_password,sub_name)	
		i = i - 1


if __name__ == '__main__':
    test()

