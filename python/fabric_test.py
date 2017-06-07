#! /user/bin/env python
from fabric.colors import *
from fabric.api import *

#env.users = {'gufytest1','gufytest2','gufytest3'}
#env.user = 'gufytest1'
env.roledefs = {
	'webservers': ['gufytest1@10.1.245.220','gufytest2@10.1.245.221'],
	'dbservers':['gufytest3@10.1.245.222']
}
env.passwords = {
	'gufytest1@10.1.245.220:22' : 'gufytest1',
	'gufytest2@10.1.245.221:22' : 'gufytest2',
	'gufytest3@10.1.245.222:22' : 'gufytest3'
}

@roles('webservers')
def webtask():
	local('ls -l | wc -l')
	with cd('~/'):
		run('pwd')
		run('rm -rf deploy')
		run('mkdir deploy')
	remote_tmp_tar = '~/deploy'
	put('test.tar.gz', remote_tmp_tar)
	with cd('~/deploy'):
		run('tar -xvf test.tar.gz')
		run('ls')

@roles('dbservers')
def dbtask():
	local('echo dbtask')
	with cd('~/'):
		run('pwd')
		run('rm -rf deploy')
		run('mkdir deploy')
	remote_tmp_tar = '~/deploy'
	put('test.tar.gz', remote_tmp_tar)
	with cd('~/deploy'):
		run('tar -xvf test.tar.gz')
		run('ls')

def deploy():
    execute(webtask)
    execute(dbtask)