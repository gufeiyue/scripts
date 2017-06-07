#!/bin/sh

echo "start cp rjil_businesscommon config... "
cd /home/aideploy/deploy_zw/svn/code/businesscommon/config/com
rm -rf ai
cd /home/aideploy/deploy_zw/svn/code/rjil_businesscommon/config/com/
cp -r ai /home/aideploy/deploy_zw/svn/code/businesscommon/config/com

echo "start cp rjil_businesscommon src... "
cd /home/aideploy/deploy_zw/svn/code/businesscommon/src/com
rm -rf ai
cd /home/aideploy/deploy_zw/svn/code/rjil_businesscommon/src/com
cp -r ai /home/aideploy/deploy_zw/svn/code/businesscommon/src/com

echo "start cp rjil_crmsaas config... "
cd /home/aideploy/deploy_zw/svn/code/crmsaas/config/com
rm -rf ai
cd /home/aideploy/deploy_zw/svn/code/rjil_crmsaas/config/com
cp -r ai /home/aideploy/deploy_zw/svn/code/crmsaas/config/com

echo "start cp rjil_crmsaas src... "
cd /home/aideploy/deploy_zw/svn/code/crmsaas/src/com/ai
rm -rf rjil
cd /home/aideploy/deploy_zw/svn/code/rjil_crmsaas/src/com/ai
cp -r rjil /home/aideploy/deploy_zw/svn/code/crmsaas/src/com/ai

echo "start cp rjil_order config... "
cd /home/aideploy/deploy_zw/svn/code/order/config/com
rm -rf ai
cd /home/aideploy/deploy_zw/svn/code/rjil_order/config/com
cp -r ai /home/aideploy/deploy_zw/svn/code/order/config/com

echo "start cp rjil_order src... "
cd /home/aideploy/deploy_zw/svn/code/order/src/com/ai
rm -rf rjil
cd /home/aideploy/deploy_zw/svn/code/rjil_order/src/com/ai
cp -r rjil /home/aideploy/deploy_zw/svn/code/order/src/com/ai

echo "start cp crmsaas template... "
cd /home/aideploy/deploy_zw/config/runtime-config/crm_app/crm_app_domain/domain_crmsaas/config
rm -rf com template
cd /home/aideploy/deploy_zw/svn/code/crmsaas/config
cp -r com /home/aideploy/deploy_zw/config/runtime-config/crm_app/crm_app_domain/domain_crmsaas/config
cp -r template /home/aideploy/deploy_zw/config/runtime-config/crm_app/crm_app_domain/domain_crmsaas/config