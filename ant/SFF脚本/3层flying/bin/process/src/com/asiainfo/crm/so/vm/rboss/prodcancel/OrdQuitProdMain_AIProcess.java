package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdQuitProdMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdQuitProdMain_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,long soOfferDataKey,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long offerOrderId = 0;
	int aState = 1;
    {//Call the sub-processesOrdQuitProd
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("soOfferDataKey",new Long(soOfferDataKey));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("offerOrderId",new Long(offerOrderId));
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodcancel.OrdQuitProd",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,long soOfferDataKey,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, aOVOrderCustomer, soOfferDataKey, REGION_ID);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(customerOrderId,aOVOrderCustomer,soOfferDataKey,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}