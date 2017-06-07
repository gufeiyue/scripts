package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdModifyOfferPriceAttr_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifyOfferPriceAttr_AIProcess.class);
  protected void executeInner(ISoOfferData aSoOfferData,String REGION_ID,OrderValueChain aOrderValueChain) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int offPrcAttrIndex = 0;
	ISoAttrData aSoAttrData = null;
	long aOrderOfferId = 0;
    aOrderOfferId=aSoOfferData.getOfferOrderId();
    while(aSoOfferData.getSoOfferPriceAttrData()!=null&&offPrcAttrIndex<aSoOfferData.getSoOfferPriceAttrData().length){
      aSoAttrData=aSoOfferData.getSoOfferPriceAttrData()[offPrcAttrIndex];
      String decisionCond9 = String.valueOf(aSoAttrData.getState());
      if(decisionCond9.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).addOfferOrderPriceAttr(aOrderOfferId,aSoAttrData,aOrderValueChain,REGION_ID);
      }
      else if(decisionCond9.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).updateOfferOrderPriceAttr(aOrderOfferId,aSoAttrData,aOrderValueChain,REGION_ID);
      }
      else if(decisionCond9.equals("4")){
      }
      else if(decisionCond9.equals("3")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).deleteOfferOrderPriceAttr(aOrderOfferId,aSoAttrData,aOrderValueChain,REGION_ID);
      }
      else{
      }
      offPrcAttrIndex++;
    }
  }
  public void execute(ISoOfferData aSoOfferData,String REGION_ID,OrderValueChain aOrderValueChain) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
try{
    executeInner( aSoOfferData, REGION_ID, aOrderValueChain);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
execute(aSoOfferData,REGION_ID,aOrderValueChain);
  return $vmParameters;
  }
}