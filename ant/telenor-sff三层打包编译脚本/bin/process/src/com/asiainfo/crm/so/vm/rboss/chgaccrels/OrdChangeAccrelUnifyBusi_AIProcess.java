package com.asiainfo.crm.so.vm.rboss.chgaccrels;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdChangeAccrelUnifyBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChangeAccrelUnifyBusi_AIProcess.class);
  protected void executeInner(String REGION_ID,long soUserDataKey,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOrdUserValue aOrdUserValue = null;
	long businessId = 0;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	long offerOrderId = 0;
	Map userDCMap = new java.util.HashMap();
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); aOrderValueChain = new com.ai.omframe.order.valuebean.OrderValueChain(); aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData = aSoUserData.getSoRoleData().getSoOfferData(); businessId = aSoOfferData.getBusinessId(); userDCMap = com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().getAccrelUserDCMap(customerOrderId);;
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInstByMainUserRegion(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    String decisionCond21 = String.valueOf(aOrdUserValue.getNoticeFlag()==1 || aOrdUserValue.getNoticeFlag()==2 || aOrdUserValue.getNoticeFlag()==3);
    if(decisionCond21.equals("true")){
      aOrdUserValue.setNoticeFlag(0); aOrdUserValue.setState(2);;
      aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).createOrdAccrelValueForUnify(aOVOrdOffOrdUser,userDCMap,REGION_ID);
      offerOrderId = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(customerOrderId,offerOrderId);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).insertInsUserChgNotifyByOrdCust(aOVOrderCustomer,REGION_ID);
      {//Call the sub-processesTransInsUnit
        Map tmpMap14 = new HashMap();
        Object loopVar14 = null;
        int loopCount14 = 0;
        loopVar14 = new Object[]{null};
        loopCount14 = 1;
        for(int i=0;i < loopCount14;i++){
        tmpMap14.clear();
        tmpMap14.put("orderOfferId",new Long(offerOrderId));
        tmpMap14.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap14);

        tmpMap14.clear();
        }      }

      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).sendSms4AccrelUnify(aOVOrderOffer);
      ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderOffer(offerOrderId);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV.class)).AddBusiLogAndReport(customerOrderId,offerOrderId);
    }
    else if(decisionCond21.equals("false")){
      aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).createOrdAccrelValueForUnify(aOVOrdOffOrdUser,userDCMap,REGION_ID);
      offerOrderId = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(customerOrderId,offerOrderId);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).insertInsUserChgNotifyByOrdCust(aOVOrderCustomer,REGION_ID);
      {//Call the sub-processesTransInsUnit
        Map tmpMap14 = new HashMap();
        Object loopVar14 = null;
        int loopCount14 = 0;
        loopVar14 = new Object[]{null};
        loopCount14 = 1;
        for(int i=0;i < loopCount14;i++){
        tmpMap14.clear();
        tmpMap14.put("orderOfferId",new Long(offerOrderId));
        tmpMap14.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap14);

        tmpMap14.clear();
        }      }

      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).sendSms4AccrelUnify(aOVOrderOffer);
      ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderOffer(offerOrderId);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV.class)).AddBusiLogAndReport(customerOrderId,offerOrderId);
    }
    else{logger.warn("判断催停标志Conditions do not match");}
  }
  public void execute(String REGION_ID,long soUserDataKey,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    executeInner( REGION_ID, soUserDataKey, customerOrderId);
}catch(Exception ex ){
throw ex;
}catch(Throwable e ){
if(e instanceof RuntimeException){
 throw (RuntimeException)e ;
} else {
 throw new RuntimeException(e);
}
}
  }
  public Map execute(Map $vmParameters) throws Exception{
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
execute(REGION_ID,soUserDataKey,customerOrderId);
  return $vmParameters;
  }
}