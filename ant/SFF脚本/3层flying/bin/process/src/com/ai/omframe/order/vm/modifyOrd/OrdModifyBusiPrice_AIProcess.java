package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoBusiPriceData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdModifyBusiPrice_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifyBusiPrice_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int index = 0;
	ISoBusiPriceData aSoBusiPriceData = null;
    while(null!=aSoUserData.getSoBusiPrice()&&index<aSoUserData.getSoBusiPrice().length){
      aSoBusiPriceData=aSoUserData.getSoBusiPrice()[index];
      String decisionCond7 = String.valueOf(aSoBusiPriceData.getState());
      if(decisionCond7.equals("4")){
      }
      else if(decisionCond7.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).insertOrdBusiPriceValueFromOrd(aSoBusiPriceData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond7.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).modifyOrdBusiPriceValueFromOrd(aSoBusiPriceData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else{
      }
      index++;
    }
  }
  public void execute(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aOrderValueChain, orderOfferId, REGION_ID);
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
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aOrderValueChain,orderOfferId,REGION_ID);
  return $vmParameters;
  }
}