echo "clearn ../svn/code/* finished ..."

svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/businesscommon/src ../svn/code/businesscommon/src
svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/businesscommon/config ../svn/code/businesscommon/config


svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/base/src ../svn/code/base/src
svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/base/config ../svn/code/base/config

svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewPaas/src ../svn/code/agentviewpaas/src
svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewPaas/config ../svn/code/agentviewpaas/config

svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/src ../svn/code/agentviewsaas/src
svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/config ../svn/code/agentviewsaas/config
svn -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/html/WEB-INF/lib ../lib/thirdlib/agentviewsaas
rm ../lib/thirdlib/agentviewsaas/*allcls*.jar
rm -rf ../lib/thirdlib/agentviewsaas/.svn
