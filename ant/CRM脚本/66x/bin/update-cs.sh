#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/CS/src/main/java ../svn/cs/src
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/CS/src/main/config ../svn/cs/config
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/CS/WebContent ../svn/cs/html
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/CS/WebContent/WEB-INF/lib ../svn/cs/lib

#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/cs_app ../config/21/runtime-config/cs_app
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/cs_web ../config/21/runtime-config/cs_web
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/cs_exe ../config/21/runtime-config/cs_exe

svn --no-auth-cache up --force -r HEAD --username liumm --password liumm  ../svn/cs/src
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm  ../svn/cs/config
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm  ../svn/cs/html
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm  ../svn/cs/lib

svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/cs_app
svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/cs_web
svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/cs_exe
