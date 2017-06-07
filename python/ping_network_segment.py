#!/usr/bin/python
#coding=utf-8

import threading,subprocess
from time import ctime,sleep,time
import Queue

queue=Queue.Queue()

class ThreadUrl(threading.Thread):
    def __init__(self,queue):
        threading.Thread.__init__(self)
        self.queue=queue


    def run(self):
        while True:
            host=self.queue.get()
            ret=subprocess.call('ping -c 1 '+host,shell=True,stdout=open('/dev/null','w'))
            if ret:
                info = "%s is down" % host
                write_ip(info)
            else:
                info = "%s is up" % host
                write_ip(info)
            self.queue.task_done()



def main():
    for i in range(100):
        t=ThreadUrl(queue)
        t.setDaemon(True)
        t.start()
    for host in b:
        queue.put(host)
    queue.join()


def write_ip(info):
    f = open('ip.txt', 'a')
    f.write(info)
    f.write('\n')
    f.close()



ip_file = open('ip.txt', 'wb')
ip_file.close()


b=['172.16.0.'+str(x) for x in range(1,255)]
start=time()
main()
