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
import  com.ai.omframe.instance.service.interfaces.IInstPriceAttr;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
import  com.ai.omframe.instance.ivalues.IInsPriceAttrValue;
public class OrdModiOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModiOffer_AIProcess.class);
  protected IOVOrderOffer executeInner(ISoOfferData aSoOfferData,ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	int offPrcAttrIndex = 0;
	ISoAttrData aSoAttrData = null;
	IInsPriceAttrValue aOfferPrcAttrValue = null;
	long priceAttrId = 0;
	int price_inst_type = 2;
	long aOfferInstId = 0;
	long userId = 0;
	int validTypePriceAttrOffer = 0;
    aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);aOfferInstId=aSoOfferData.getInsOfferId();userId=aSoUserData.getUserId();validTypePriceAttrOffer=aOrderValueChain.getVALID_TYPE_PRICE_ATTR_OFFER();;
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    while(aSoOfferData.getSoOfferPriceAttrData()!=null&&offPrcAttrIndex<aSoOfferData.getSoOfferPriceAttrData().length){
      aSoAttrData=aSoOfferData.getSoOfferPriceAttrData()[offPrcAttrIndex];priceAttrId=aSoAttrData.getAttrId();;
      String decisionCond11 = String.valueOf(aSoAttrData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
      if(decisionCond11.equals("false")){
        aOfferPrcAttrValue = ((com.ai.omframe.instance.service.interfaces.IInstPriceAttr)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstPriceAttr",com.ai.omframe.instance.service.interfaces.IInstPriceAttr.class)).getInstPriceAttrByRelatInstIdAndAttrId(aOfferInstId,priceAttrId,userId,price_inst_type,validTypePriceAttrOffer,REGION_ID);
        String decisionCond15 = String.valueOf(null!=aOfferPrcAttrValue);
        if(decisionCond15.equals("true")){
          ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createOfferOrderPriceAttrFromInstAndSoData(aOVOrderOffer,aOfferPrcAttrValue,aSoAttrData,aOrderValueChain,REGION_ID);
        }
        else if(decisionCond15.equals("false")){
        }
        else{logger.warn("属性实例不为空?Conditions do not match");}
      }
      else if(decisionCond11.equals("true")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createOfferOrderPriceAttr(aOVOrderOffer,aSoAttrData,aOrderValueChain,REGION_ID);
      }
      else{logger.warn("新增属性?Conditions do not match");}
      offPrcAttrIndex++;
    }
    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(ISoOfferData aSoOfferData,ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderOffer realReturn = executeInner( aSoOfferData, aSoUserData, aOrderValueChain, aOVOrderCustomer, REGION_ID);
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
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderOffer realReturn = execute(aSoOfferData,aSoUserData,aOrderValueChain,aOVOrderCustomer,REGION_ID);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}