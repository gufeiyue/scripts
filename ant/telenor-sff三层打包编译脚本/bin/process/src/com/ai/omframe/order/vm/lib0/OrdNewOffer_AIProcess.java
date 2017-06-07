package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
public class OrdNewOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewOffer_AIProcess.class);
  protected IOVOrderOffer executeInner(ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	int offPrcAttrIndex = 0;
	ISoAttrData aSoAttrData = null;
    aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);;
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOffer(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    while(aSoOfferData.getSoOfferPriceAttrData()!=null&&offPrcAttrIndex<aSoOfferData.getSoOfferPriceAttrData().length){
      aSoAttrData=aSoOfferData.getSoOfferPriceAttrData()[offPrcAttrIndex];aOrderValueChain.setSTATE_PRICE_ATTR_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);;
      ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createOfferOrderPriceAttr(aOVOrderOffer,aSoAttrData,aOrderValueChain,REGION_ID);
      offPrcAttrIndex++;
    }
    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderOffer realReturn = executeInner( aSoOfferData, aOrderValueChain, aOVOrderCustomer, REGION_ID);
    $returnParameter.put("aOVOrderOffer",realReturn);
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
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderOffer realReturn = execute(aSoOfferData,aOrderValueChain,aOVOrderCustomer,REGION_ID);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}