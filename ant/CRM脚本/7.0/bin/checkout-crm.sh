echo "clearn ../svn/code/* finished ..."

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/businesscommon/src ../svn/code/businesscommon/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/businesscommon/config ../svn/code/businesscommon/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/customer/src ../svn/code/customer/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/customer/config ../svn/code/customer/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/marketingcampaign/src ../svn/code/marketingcampaign/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/marketingcampaign/config ../svn/code/marketingcampaign/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/marketingresource/src ../svn/code/marketingresource/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/marketingresource/config ../svn/code/marketingresource/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/order/src ../svn/code/order/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/order/config ../svn/code/order/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/product/src ../svn/code/product/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/product/config ../svn/code/product/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/productoffering/src ../svn/code/productoffering/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/productoffering/config ../svn/code/productoffering/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/resourcespecification/src ../svn/code/resourcespecification/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/resourcespecification/config ../svn/code/resourcespecification/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/base/src ../svn/code/base/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMPaaS/base/config ../svn/code/base/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewPaas/src ../svn/code/agentviewpaas/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewPaas/config ../svn/code/agentviewpaas/config

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/src ../svn/code/agentviewsaas/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/config ../svn/code/agentviewsaas/config
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/AgentViewSaaS2/html/WEB-INF/lib ../lib/thirdlib/agentviewsaas
rm ../lib/thirdlib/agentviewsaas/*allcls*.jar
rm -rf ../lib/thirdlib/agentviewsaas/.svn

svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMSaaS_SR2/staticWeb/src  ../svn/code/crmsaas/src
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMSaaS_SR2/staticWeb/config  ../svn/code/crmsaas/config

lib-crmsaas(){
mkdir crmsaaslibtmp
svn --no-auth-cache -r HEAD --username wusk --password wusk checkout http://10.11.20.110/svn/VERIS-CRM-COREV7.0/trunk/verisCRMSaaS_SR2/staticWeb/html/WEB-INF/lib crmsaaslibtmp

ls ../lib/appenginelib/ > list1
ls crmsaaslibtmp/ > list2
list3=`comm -1 -2 list1 list2`

for i in ${list3}

do 
    rm crmsaaslibtmp/${3}
done

mv crmsaaslibtmp/*.jar ../lib/thirdlib/customerservice/
rm ../lib/thirdlib/customerservice/*allcls*.jar

rm -rf crmsaaslibtmp
rm list1 list2
}

lib-crmsaas $

echo "checkout code successfull ..."



