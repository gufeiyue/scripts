package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
public class OrdReviseDelNextOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdReviseDelNextOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,ISoOfferData aReviseSoOfferData,String REGION_ID,long aReplaceInsOfferId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aNextSoOfferData = null;
    aNextSoOfferData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createDelSoOfferDatasFromReviseOffer(aReviseSoOfferData,REGION_ID,aReplaceInsOfferId);
    String decisionCond9 = String.valueOf(null!=aNextSoOfferData);
    if(decisionCond9.equals("false")){
    }
    else if(decisionCond9.equals("true")){
      aNextSoOfferData.setBusinessId(aReviseSoOfferData.getBusinessId());;
      {//Call the sub-processes退订下周期策划
        Map tmpMap3 = new HashMap();
        Object loopVar3 = null;
        int loopCount3 = 0;
        loopVar3 = new Object[]{null};
        loopCount3 = 1;
        for(int i=0;i < loopCount3;i++){
        tmpMap3.clear();
        tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap3.put("aSoOfferData",aNextSoOfferData);
        tmpMap3.put("aOrderValueChain",aOrderValueChain);
        tmpMap3.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdDelNextOffer",tmpMap3);
        aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

        tmpMap3.clear();
        }      }

    }
    else{logger.warn("是否有下周期策划Conditions do not match");}
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,ISoOfferData aReviseSoOfferData,String REGION_ID,long aReplaceInsOfferId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aReviseSoOfferData",aReviseSoOfferData);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aReplaceInsOfferId",new Long(aReplaceInsOfferId));
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, aOrderValueChain, aReviseSoOfferData, REGION_ID, aReplaceInsOfferId);
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
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    ISoOfferData aReviseSoOfferData = ($vmParameters.get("aReviseSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aReviseSoOfferData"),ISoOfferData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long aReplaceInsOfferId = ($vmParameters.get("aReplaceInsOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("aReplaceInsOfferId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,aOrderValueChain,aReviseSoOfferData,REGION_ID,aReplaceInsOfferId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}