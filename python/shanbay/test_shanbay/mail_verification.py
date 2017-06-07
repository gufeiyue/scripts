# -*- coding: utf-8 -*-

import poplib
import email
from email.parser import Parser
from email.header import decode_header
from email.utils import parseaddr
import re, json, cookielib
from termcolor import colored
import MySQLdb
import requests
import time

def guess_charset(msg):
    charset = msg.get_charset()
    if charset is None:
        content_type = msg.get('Content-Type', '').lower()
        pos = content_type.find('charset=')
        if pos >= 0:
            charset = content_type[pos + 8:].strip()
    return charset

def decode_str(s):
    value, charset = decode_header(s)[0]
    if charset:
        value = value.decode(charset)
    return value

def print_info(msg, email, indent=0):
    if indent == 0:
        for header in ['From', 'To', 'Subject']:
            value = msg.get(header, '')
            if value:
                if header=='Subject':
                    value = decode_str(value)
                else:
                    hdr, addr = parseaddr(value)
                    name = decode_str(hdr)
                    value = u'%s <%s>' % (name, addr)
            #print('%s%s: %s' % ('  ' * indent, header, value))
            #print value
    if (msg.is_multipart()):
        parts = msg.get_payload()
        for n, part in enumerate(parts):
            #print('%spart %s' % ('  ' * indent, n))
            #print('%s--------------------' % ('  ' * indent))
            print_info(part, email, indent + 1)
    else:
        content_type = msg.get_content_type()
        if content_type=='text/plain' or content_type=='text/html':
            content = msg.get_payload(decode=True)
            charset = guess_charset(msg)
            if charset:
                content = content.decode(charset)
                pattern = re.compile('http://www.shanbay.com/accounts/verify/(.*?)<br>')
                verify = pattern.findall(content)
                if len(verify) > 0:
                    verify_url = verify[0]
                    url = 'https://www.shanbay.com/accounts/verify/%s' % verify_url
                    print url
                    s = requests.get(url)
                    if s.status_code == 200:
                        print colored('帐号：%s   确认注册成功', 'blue') % email
                #print content
            #print('%sText: %s' % ('  ' * indent, content + '...'))
        else:
            print('%sAttachment: %s' % ('  ' * indent, content_type))


#############################################################
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
def db_update_verify(sub_email):
    update_sql = "update `test_account` set `is_verify`='Y' where `sub_email` = '%s';"
    sql = update_sql % sub_email
    #print sql
    conn=connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    conn.close()

#############################################################

sql = "select * from test_account where `is_available`='N' and `is_verify`='N';"
info = db_query(sql)
number = num(sql)
i = 0
while i < number:
    email = info[i][4]
    password = info[i][5]
    pop3_server = 'pop3.163.com'
    print email
    server = poplib.POP3(pop3_server)
    #server.set_debuglevel(1)
    #print(server.getwelcome())
    # 认证:
    server.user(email)
    server.pass_(password)
    #print('Messages: %s. Size: %s' % server.stat())
    resp, mails, octets = server.list()
    index = len(mails)
    j = index

    while 0 < j <= index:
        resp, lines, octets = server.retr(j)
        # 解析邮件:
        msg = Parser().parsestr('\r\n'.join(lines))
        print_info(msg,email)
        print colored('=========这是第 %s 封邮件===========', 'red') % j
        db_update_verify(email)
        j = j + 1
    i = i + 1
    #time.sleep(0.5)
    server.quit()
