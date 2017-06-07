package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
public class OrdModifySrvpkgPriceAttr_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifySrvpkgPriceAttr_AIProcess.class);
  protected void executeInner(ISoServicePkgData aSoServicePkgData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoAttrData aSoAttrData = null;
	int index = 0;
    while(null!=aSoServicePkgData.getPriceAttrData()&&index<aSoServicePkgData.getPriceAttrData().length){
      aSoAttrData=aSoServicePkgData.getPriceAttrData()[index];
      String decisionCond9 = String.valueOf(aSoAttrData.getState());
      if(decisionCond9.equals("4")){
      }
      else if(decisionCond9.equals("3")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).modifySrvPkgOrderPriceAttrFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond9.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).modifySrvPkgOrderPriceAttrFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond9.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).insertSrvPkgOrderPriceAttrFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else{
      }
      index++;
    }
  }
  public void execute(ISoServicePkgData aSoServicePkgData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoServicePkgData",aSoServicePkgData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoServicePkgData, aOrderValueChain, orderOfferId, REGION_ID);
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
    ISoServicePkgData aSoServicePkgData = ($vmParameters.get("aSoServicePkgData") == null)?null:(ISoServicePkgData)VMDataType.transfer($vmParameters.get("aSoServicePkgData"),ISoServicePkgData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoServicePkgData,aOrderValueChain,orderOfferId,REGION_ID);
  return $vmParameters;
  }
}