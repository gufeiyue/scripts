rm -rf ../lib/projextlib/customerservice/*
rm -rf ../lib/projextlib/nMsm/*

echo "BEGIN TO checout!!!!"
svn export --force -r HEAD --username deploy-scm --password buildUser http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib/oneframe.jar ../lib/projextlib/nMsm/oneframe.jar 
svn export --force -r HEAD --username deploy-scm --password buildUser http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib/pushcommunication.jar ../lib/projextlib/nMsm/pushcommunication.jar
svn export --force -r HEAD --username deploy-scm --password buildUser http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib/isub.jar ../lib/projextlib/customerservice/isub.jar
svn export --force -r HEAD --username deploy-scm --password buildUser http://10.1.195.110:8080/svn/CODE-VERIS-CRMV6.6-to-V7.0/trunk/CS/WebContent/WEB-INF/lib/pushcommunication.jar ../lib/projextlib/customerservice/pushcommunication.jar