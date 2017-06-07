#!/bin/bash

echo "clearn ../svn/code/* finished ..."

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/businesscommon/src ../svn/code/businesscommon/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/businesscommon/config ../svn/code/businesscommon/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/customer/src ../svn/code/customer/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/customer/config ../svn/code/customer/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/marketingcampaign/src ../svn/code/marketingcampaign/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/marketingcampaign/config ../svn/code/marketingcampaign/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/marketingresource/src ../svn/code/marketingresource/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/marketingresource/config ../svn/code/marketingresource/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/order/src ../svn/code/order/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/order/config ../svn/code/order/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/product/src ../svn/code/product/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/product/config ../svn/code/product/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/productoffering/src ../svn/code/productoffering/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/productoffering/config ../svn/code/productoffering/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/resourcespecification/src ../svn/code/resourcespecification/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/resourcespecification/config ../svn/code/resourcespecification/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/base/src ../svn/code/base/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/base/config ../svn/code/base/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/AgentViewPaas/src ../svn/code/agentviewpaas/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/AgentViewPaas/config ../svn/code/agentviewpaas/config


svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMSaaS_SR2/staticWeb/src  ../svn/code/crmsaas/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMSaaS_SR2/staticWeb/config  ../svn/code/crmsaas/config

svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/integration/src   ../svn/code/integration/src
svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/V7.1-dev-SR0.5/CRM/verisCRMPaaS/integration/config  ../svn/code/integration/config

echo "checkout runtime_config........"
#svn --no-auth-cache -r HEAD --username deploy-scm70 --password deploy-scm70 checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/run-time-config/V7.1-rm-SR0.5/crm_app ../config/runtime-config/crm_app



echo "checkout code successfull ..."



