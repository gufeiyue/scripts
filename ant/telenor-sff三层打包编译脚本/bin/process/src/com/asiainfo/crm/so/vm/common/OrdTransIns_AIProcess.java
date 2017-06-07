package com.asiainfo.crm.so.vm.common;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdTransIns_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransIns_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer[] aOVOrderOffers = null;
	int aOVOrderOfferIdx = 0;
	IOVOrderOffer aIterOVOrderOffer = null;
	long aOrderOfferId = 0;
    aOVOrderOffers=aOVOrderCustomer.getOVOrderOffers();;
    while(aOVOrderOffers!=null&&aOVOrderOfferIdx<aOVOrderOffers.length){
      aIterOVOrderOffer=aOVOrderOffers[aOVOrderOfferIdx]; aOrderOfferId = aIterOVOrderOffer.getOrderOfferValue().getOfferOrderId();;
      {//Call the sub-processesTransInsUnit
        Map tmpMap3 = new HashMap();
        Object loopVar3 = null;
        int loopCount3 = 0;
        loopVar3 = new Object[]{null};
        loopCount3 = 1;
        for(int i=0;i < loopCount3;i++){
        tmpMap3.clear();
        tmpMap3.put("aIterOVOrderOffer",aIterOVOrderOffer);
        tmpMap3.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.TransInsUnit",tmpMap3);

        tmpMap3.clear();
        }      }

      aOVOrderOfferIdx++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, REGION_ID);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}