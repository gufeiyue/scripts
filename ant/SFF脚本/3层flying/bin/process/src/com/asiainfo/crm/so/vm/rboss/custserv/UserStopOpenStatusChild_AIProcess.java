package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class UserStopOpenStatusChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserStopOpenStatusChild_AIProcess.class);
  protected void executeInner(String billId,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,String osType,ISoOrderData aSoOrderData,boolean needChgBusinessId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOrdUserValue aOrdUserValue = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	long orderOfferId = 0;
    aOrderValueChain = new com.ai.omframe.order.valuebean.OrderValueChain(); aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
    aOVOrderOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).createOVOrderOfferChild(aOVOrderCustomer,aOrderValueChain,REGION_ID,billId,osType,needChgBusinessId);
    aOrdUserValue=aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0];;
    aOrdUserOsStateValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).createOrdUserOsStateForAcc(aOVOrderCustomer,aOrdUserValue);
    aOrdUserOsStateValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).chgUserOsStatus(aSoOrderData,aOrdUserOsStateValue,aOVOrderOffer);
  }
  public void execute(String billId,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,String osType,ISoOrderData aSoOrderData,boolean needChgBusinessId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("billId",billId);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("osType",osType);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("needChgBusinessId",new Boolean(needChgBusinessId));
try{
    executeInner( billId, customerOrderId, REGION_ID, aOVOrderCustomer, osType, aSoOrderData, needChgBusinessId);
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
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String osType = ($vmParameters.get("osType") == null)?"":(String)VMDataType.transfer($vmParameters.get("osType"),String.class);
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    boolean needChgBusinessId = ($vmParameters.get("needChgBusinessId") == null)?true:((Boolean)VMDataType.transfer($vmParameters.get("needChgBusinessId"),boolean.class)).booleanValue();
execute(billId,customerOrderId,REGION_ID,aOVOrderCustomer,osType,aSoOrderData,needChgBusinessId);
  return $vmParameters;
  }
}