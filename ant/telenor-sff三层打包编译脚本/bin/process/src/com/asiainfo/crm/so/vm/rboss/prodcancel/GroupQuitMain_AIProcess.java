package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
public class GroupQuitMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(GroupQuitMain_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,long soOfferDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOfferData aSoOfferData = null;
	long insOfferId = 0;
	String billId = "";
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOfferData=(com.ai.omframe.order.data.ivalues.ISoOfferData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soOfferDataKey,customerOrderId);insOfferId=aSoOfferData.getInsOfferId();billId=aSoOfferData.getSoOrderData().getBillId();java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processQuitProdOVChain(aOrderValueChain,map);
    {//Call the sub-processes销户
      Map tmpMap66 = new HashMap();
      Object loopVar66 = null;
      int loopCount66 = 0;
      loopVar66 = new Object[]{null};
      loopCount66 = 1;
      for(int i=0;i < loopCount66;i++){
      tmpMap66.clear();
      tmpMap66.put("aSoOfferData",aSoOfferData);
      tmpMap66.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap66.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap66.put("aOrderValueChain",aOrderValueChain);
      tmpMap66.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib2.OrdDropUser",tmpMap66);

      tmpMap66.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,long soOfferDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, soOfferDataKey, REGION_ID, aOVOrderCustomer);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(customerOrderId,soOfferDataKey,REGION_ID,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}