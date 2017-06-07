#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/UCP/src  ../svn/ucp/src
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/UCP/config  ../svn/ucp/config
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/UCP/html  ../svn/ucp/html
#svn co --force -r HEAD --username liumm --password liumm http://10.11.20.110/svn/VERIS-CRM-COREV6.6/branch/V6.6.2-rm-SR0.3/UCP/html/WEB-INF/lib  ../svn/ucp/lib

#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/ucp-app ../config/21/runtime-config/ucp_app
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/ucp-web ../config/21/runtime-config/ucp_web
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/ucp-exe ../config/21/runtime-config/ucp_exe
#svn co --force -r HEAD --username zhouwei13 --password zhouwei http://10.11.20.110/svn/VERIS-CRM-COREV6.6/run-time-config-v662/release-SR0.3/t_21/ucp_inter ../config/21/runtime-config/ucp_inter

svn --no-auth-cache up --force -r HEAD --username liumm --password liumm   ../svn/ucp/src
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm   ../svn/ucp/config
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm   ../svn/ucp/html
svn --no-auth-cache up --force -r HEAD --username liumm --password liumm   ../svn/ucp/lib

svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/ucp_app
svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/ucp_web
svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/ucp_exe
svn --no-auth-cache up --force -r HEAD --username zhouwei13 --password zhouwei  ../config/21/runtime-config/ucp_inter
