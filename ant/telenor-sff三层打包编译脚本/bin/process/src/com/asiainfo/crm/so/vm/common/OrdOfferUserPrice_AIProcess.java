package com.asiainfo.crm.so.vm.common;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoBusiPriceData;
public class OrdOfferUserPrice_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdOfferUserPrice_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,ISoOrderData aSoOrderData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	ISoBusiPriceData aSoBusiPriceData = null;
	ISoBusiPriceData[] soBusiPrice = null;
	int yingyeIndex = 0;
    if(aSoUserData.getSoBusiPrice()!=null){soBusiPrice = aSoUserData.getSoBusiPrice();}else{soBusiPrice = aSoOrderData.getCustSoBusiPrice();};
    while(soBusiPrice!=null&&yingyeIndex<soBusiPrice.length){
      aSoBusiPriceData=soBusiPrice[yingyeIndex];
      ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createOrdBusiPriceValue(aOVOrdOffOrdUser,aSoBusiPriceData,aOrderValueChain,REGION_ID);
      yingyeIndex++;
    }
  }
  public void execute(ISoUserData aSoUserData,ISoOrderData aSoOrderData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aSoOrderData, aOVOrdOffOrdUser, aOrderValueChain, REGION_ID);
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
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aSoOrderData,aOVOrdOffOrdUser,aOrderValueChain,REGION_ID);
  return $vmParameters;
  }
}