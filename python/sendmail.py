# /usr/bin/python
# -*- coding: utf-8 -*-
# import sys,os,stat,time,datetime,shutil
# sys.path.append(os.getcwd()+'/../')

def sendmails(server_ip,port,server_username,server_pass,server_addr,to_addr,subject,s_res,f_path=''):
    import sys
    import smtplib
    from email.mime.text import MIMEText
    from email.MIMEBase import MIMEBase
    from email.mime.multipart import MIMEMultipart
    from email import Encoders
    
    #创建SMTP的对象，用于发送邮件
    smtp = smtplib.SMTP(timeout=20)
    smtp.connect(server_ip,port)
    smtp.login(server_username,server_pass)
    
    msg = MIMEMultipart()
    
    msg['From'] = server_addr
    msg['To'] = to_addr
    msg['Subject'] = subject
    
    txt = MIMEText(s_res, _subtype='html', _charset='utf-8')
    
    #加上暗送的号码
    real_to_addr = to_addr
    msg.attach(txt)
    
    if f_path != "":
        # 读入文件内容并格式化，此处文件为当前目录下，也可指定目录 例如：open(r'/tmp/123.txt','rb')
        for f_path_t in f_path.split(','):
            part = MIMEBase('application', 'octet-stream')
#             print f_path_t
            part.set_payload(open(f_path_t,'rb').read())
            Encoders.encode_base64(part)
            ## 设置附件头
            part.add_header('Content-Disposition', 'attachment; filename='+f_path_t.split('/')[len(f_path_t.split('/'))-1])
            msg.attach(part)
#             part.set_payload(open(f_path_t,'rb').read())
    
    print "Mail : \n"
    print 'From:%s'%( server_addr)
    print 'To:%s'%( to_addr)
    print 'Subject:%s'%( subject)
    
    res = smtp.sendmail(server_addr,real_to_addr.split(';'),msg.as_string())

    if len(res) == 0 :
        print "邮件发送成功\n"
        
    #全部发送完毕退出
    smtp.quit()

def main(myip,myport,myacct,appip,appacct,url,tomail):
    #正文规则，从配置文件获取，如果需要定制化，修改该文件即可
    content = "您的AppEngine Dev Env已开通!"+"\n"
    content +="MySqlINFO："
    content += myip + " "
    content += myport + " "
    content += myacct + "\n"
    content +="APPServerINFO: "
    content += appip + " "
    content += appacct + "\r"
    content +="WEBURL："
    content += url
	
    toemail= tomail
    #邮件标题
    submit = "AppEngine Dev Env开通通知"


    sendmails('mail.asiainfo.com','25','taci','a1s2d3f4!','taci@asiainfo.com',toemail,submit,content)
    
    
if __name__ == '__main__':
    import sys,os
    myip = sys.argv[1]
    myport = sys.argv[2]
    myacct = sys.argv[3]
    appip = sys.argv[4]
    appacct = sys.argv[5]
    url = sys.argv[6]
    tomail = sys.argv[7]
    main(myip,myport,myacct,appip,appacct,url,tomail)
    
    
