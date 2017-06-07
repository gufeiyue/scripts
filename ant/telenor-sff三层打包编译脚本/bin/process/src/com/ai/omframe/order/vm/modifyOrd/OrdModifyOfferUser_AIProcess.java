package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdModifyOfferUser_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifyOfferUser_AIProcess.class);
  protected void executeInner(ISoOfferData aSoOfferData,ISoUserData aSoUserData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int state = 0;
    state=4;
    String decisionCond12 = String.valueOf(state);
    if(decisionCond12.equals("4")){
    }
    else if(decisionCond12.equals("2")){
      logger.warn("Configure the node (更新用户策划关系订单)");
    }
    else{
    }
    {//Call the sub-processesOrdModifySrvpkg
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = new Object[]{null};
      loopCount15 = 1;
      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("aSoUserData",aSoUserData);
      tmpMap15.put("aOrderValueChain",aOrderValueChain);
      tmpMap15.put("orderOfferId",new Long(orderOfferId));
      tmpMap15.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifySrvpkg",tmpMap15);

      tmpMap15.clear();
      }    }

  }
  public void execute(ISoOfferData aSoOfferData,ISoUserData aSoUserData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoOfferData, aSoUserData, aOrderValueChain, orderOfferId, REGION_ID);
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
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoOfferData,aSoUserData,aOrderValueChain,orderOfferId,REGION_ID);
  return $vmParameters;
  }
}