package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdMainOfferCreateUserMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdMainOfferCreateUserMain_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long custId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoUserData.setUserId(((com.ai.omframe.order.ivalues.IOrdCustValue)aOVOrderCustomer.getOrderCustomerValue()).getUserId());aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);aOrderValueChain.setUSER_TYPE(1);aOrderValueChain=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().updateUserInstState(aSoUserData,aOrderValueChain);;
    {//Call the sub-processes销户重入网
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = new Object[]{null};
      loopCount15 = 1;
      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("aSoUserData",aSoUserData);
      tmpMap15.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap15.put("aSoOfferData",aSoOfferData);
      tmpMap15.put("aOrderValueChain",aOrderValueChain);
      tmpMap15.put("REGION_ID",REGION_ID);
      tmpMap15.put("custId",new Long(custId));
      tmpMap15.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.OrdMainOfferCreateUser",tmpMap15);

      tmpMap15.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long custId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("custId",new Long(custId));
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, soUserDataKey, REGION_ID, aOVOrderCustomer, custId);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long custId = ($vmParameters.get("custId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("custId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(customerOrderId,soUserDataKey,REGION_ID,aOVOrderCustomer,custId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}