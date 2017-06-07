package com.ai.omframe.order.vm.modifyOrd;

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
public class OrdModifyOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifyOffer_AIProcess.class);
  protected void executeInner(IOVOrderCustomer aOVOrderCustomer,ISoUserData aSoUserData,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int offState = 0;
	long orderOfferId = 0;
    offState=4;orderOfferId=aSoOfferData.getOfferOrderId();
    String decisionCond9 = String.valueOf(offState);
    if(decisionCond9.equals("2")){
      logger.warn("Configure the node (更新策划订单)");
    }
    else if(decisionCond9.equals("4")){
    }
    else{
    }
    {//Call the sub-processesOrdModifyOfferUser
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = new Object[]{null};
      loopCount4 = 1;
      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("aSoOfferData",aSoOfferData);
      tmpMap4.put("aSoUserData",aSoUserData);
      tmpMap4.put("aOrderValueChain",aOrderValueChain);
      tmpMap4.put("orderOfferId",new Long(orderOfferId));
      tmpMap4.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifyOfferUser",tmpMap4);

      tmpMap4.clear();
      }    }

    {//Call the sub-processesOrdModifyUser
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifyUser",tmpMap10);

      tmpMap10.clear();
      }    }

    {//Call the sub-processesOrdModifyBusiPrice
      Map tmpMap17 = new HashMap();
      Object loopVar17 = null;
      int loopCount17 = 0;
      loopVar17 = new Object[]{null};
      loopCount17 = 1;
      for(int i=0;i < loopCount17;i++){
      tmpMap17.clear();
      tmpMap17.put("aSoUserData",aSoUserData);
      tmpMap17.put("aOrderValueChain",aOrderValueChain);
      tmpMap17.put("orderOfferId",new Long(orderOfferId));
      tmpMap17.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifyBusiPrice",tmpMap17);

      tmpMap17.clear();
      }    }

  }
  public void execute(IOVOrderCustomer aOVOrderCustomer,ISoUserData aSoUserData,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aOVOrderCustomer, aSoUserData, aSoOfferData, aOrderValueChain, REGION_ID);
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
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aOVOrderCustomer,aSoUserData,aSoOfferData,aOrderValueChain,REGION_ID);
  return $vmParameters;
  }
}