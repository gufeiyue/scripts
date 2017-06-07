package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdNewOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,long offerOrderId,String workflowId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	ISoUserData aSoUserData = null;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData();aSoOfferData.setBusinessId(191000000004L); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);aOrderValueChain.setUSER_TYPE(1);
    {//Call the sub-processes订购增值策划
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = new Object[]{null};
      loopCount5 = 1;
      for(int i=0;i < loopCount5;i++){
      tmpMap5.clear();
      tmpMap5.put("aSoUserData",aSoUserData);
      tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap5.put("aSoOfferData",aSoOfferData);
      tmpMap5.put("aOVOrderOffer",aOVOrderOffer);
      tmpMap5.put("aOrderValueChain",aOrderValueChain);
      tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap5.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdOpenNewOffer",tmpMap5);
      aOVOrderCustomer = (tmpMap5.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap5.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap5.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,long offerOrderId,String workflowId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("offerOrderId",new Long(offerOrderId));
    $inParameter.put("workflowId",workflowId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, soUserDataKey, aOVOrderCustomer, offerOrderId, workflowId, REGION_ID);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long offerOrderId = ($vmParameters.get("offerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerOrderId"),long.class)).longValue();
    String workflowId = ($vmParameters.get("workflowId") == null)?"":(String)VMDataType.transfer($vmParameters.get("workflowId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(customerOrderId,soUserDataKey,aOVOrderCustomer,offerOrderId,workflowId,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}