#!/usr/bin/python
#coding=utf-8

import threading,subprocess
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
                print (info)
            else:
                info = "%s is up" % host
                print (info)
            self.queue.task_done()

def main():
    for i in range(100):
        t=ThreadUrl(queue)
        t.setDaemon(True)
        t.start()
    for ip in ip_lists:
        queue.put(ip)
    queue.join()


if __name__ == '__main__':
    ip_lists=['172.16.0.'+str(x) for x in range(1,255)]
    main()
