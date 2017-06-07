package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
public class OrdQuitProd_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdQuitProd_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,long soOfferDataKey,IOVOrderCustomer aOVOrderCustomer,IOrdUserOsStateValue aOrdUserOsStateValue,long offerOrderId,String workflowId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	ISoOrderData aSoOrderData = null;
	ISoUserData aSoUserData = null;
	IOrdAccrelValue aOrdAccRelValue = null;
    aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);aSoOfferData=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().getSoOfferData(soOfferDataKey,0);aSoOfferData.setBusinessId(com.asiainfo.crm.so.common.RBossConst.BUSI_ID_QUIT_RBOSS_USER);aSoUserData = aSoOfferData.getSoRoleData()[0].getSoUserData()[0];java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));if (null==aOrderValueChain){ aOrderValueChain=new com.ai.omframe.order.valuebean.OrderValueChain(); } int delState=com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE; aOrderValueChain.setSTATE_OFFER(delState); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE); aOrderValueChain.setSTATE_OFFER_USER(delState);;
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValue(aOrdUserValue,REGION_ID);
    aOrdUserOsStateValue.setOsStatusPre(aOrdUserOsStateValue.getOsStatus());aOrdUserOsStateValue.setOsStatus(com.asiainfo.crm.so.util.RbossServiceFactory.getStopOpenOsStatusSV().setOsStatus(aOrdUserOsStateValue.getOsStatus(),6,"1"));aOrdUserValue.setInsState(Integer.parseInt(com.asiainfo.crm.so.common.RBossConst.USER_STATE_SO_PRE_DESTROY));aOrdUserValue.setPreDestoryTime(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate());;
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUser(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrdUserOsStateValue,aOrderValueChain,REGION_ID);
    aOVOrdOffOrdUser.getOrdOffOrdUserValue().setState(4);;
    String decisionCond17 = String.valueOf(aOVOrderOffer.getOrderOfferValue().getOfferType().startsWith("OFFER_PLAN"));
    if(decisionCond17.equals("false")){
    }
    else if(decisionCond17.equals("true")){
      aOrdAccRelValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createDeleteOrdAccrelValueFromIns(aOVOrderOffer,aSoUserData,aOrdUserValue,aOrderValueChain,REGION_ID);
      aOrdAccRelValue.setObjExpireDate(aOVOrderOffer.getOrderOfferValue().getExpireDate());aOrdAccRelValue.setState(4);;
    }
    else{logger.warn("条件判断Conditions do not match");}
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,long soOfferDataKey,IOVOrderCustomer aOVOrderCustomer,IOrdUserOsStateValue aOrdUserOsStateValue,long offerOrderId,String workflowId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrdUserOsStateValue",aOrdUserOsStateValue);
    $inParameter.put("offerOrderId",new Long(offerOrderId));
    $inParameter.put("workflowId",workflowId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, soOfferDataKey, aOVOrderCustomer, aOrdUserOsStateValue, offerOrderId, workflowId, REGION_ID);
    $returnParameter.put("aOVOrderCustomer",realReturn);
  return realReturn;
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    IOrdUserOsStateValue aOrdUserOsStateValue = ($vmParameters.get("aOrdUserOsStateValue") == null)?null:(IOrdUserOsStateValue)VMDataType.transfer($vmParameters.get("aOrdUserOsStateValue"),IOrdUserOsStateValue.class);
    long offerOrderId = ($vmParameters.get("offerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerOrderId"),long.class)).longValue();
    String workflowId = ($vmParameters.get("workflowId") == null)?"":(String)VMDataType.transfer($vmParameters.get("workflowId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(customerOrderId,soOfferDataKey,aOVOrderCustomer,aOrdUserOsStateValue,offerOrderId,workflowId,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}