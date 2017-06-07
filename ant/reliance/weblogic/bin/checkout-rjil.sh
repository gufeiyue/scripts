#!/bin/bash


svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_businesscommon/src ../svn/code/rjil_businesscommon/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_businesscommon/config ../svn/code/rjil_businesscommon/config

svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_order/src ../svn/code/rjil_order/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_order/config ../svn/code/rjil_order/config

svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCoreSAAS/RJIL_CRMSaaS/src  ../svn/code/rjil_crmsaas/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCoreSAAS/RJIL_CRMSaaS/config  ../svn/code/rjil_crmsaas/config

svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_integration/src ../svn/code/integration/src
svn --no-auth-cache -r HEAD --username deploy-scm --password buildUser checkout http://10.1.195.110:8080/svn/CODE-VERIS-CRM-RELIANCE/trunk/CRM/CRMCorePAAS/RJIL_integration/config ../svn/code/integration/config
