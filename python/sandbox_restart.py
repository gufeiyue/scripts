#! /user/bin/env python
from fabric.colors import *
from fabric.api import *


env.roledefs = {
    'hub': ['aisaashub@10.19.18.51','aipaashub@10.19.18.51'],
    'app':['aiapp@10.19.18.52']
}
env.passwords = {
    'aisaashub@10.19.18.51:22022' : 'taaisaashub',
    'aipaashub@10.19.18.51:22022' : 'taaipaashub',
    'aiapp@10.19.18.52:22022' : 'taaiapp'
}
env.port = 22022

@roles('hub')
def restarthub():
    local('echo restart hub')
    with cd('~/'):
        run('pwd')
    #     run('rm -rf deploy')
    #     run('mkdir deploy')
    # remote_tmp_tar = '~/deploy'
    # put('test.tar.gz', remote_tmp_tar)
    # with cd('~/deploy'):
    #     run('tar -xvf test.tar.gz')
    #     run('ls')

@roles('app')
def restartapp():
    local('echo restart app')
    with cd('~/'):
        run('pwd')
    #     run('rm -rf deploy')
    #     run('mkdir deploy')
    # remote_tmp_tar = '~/deploy'
    # put('test.tar.gz', remote_tmp_tar)
    # with cd('~/deploy'):
    #     run('tar -xvf test.tar.gz')
    #     run('ls')

def restart():
    execute(restarthub)
    execute(restartapp)