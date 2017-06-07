package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater;
import  com.ai.omframe.order.data.ivalues.ISoServiceData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
public class OrdModifySrvAttr_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifySrvAttr_AIProcess.class);
  protected void executeInner(ISoServiceData aSoServiceData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int srvAttrIndex = 0;
	ISoAttrData aSoAttrData = null;
    while(null!=aSoServiceData.getServiceAttrDatas()&&srvAttrIndex<aSoServiceData.getServiceAttrDatas().length){
      aSoAttrData=aSoServiceData.getServiceAttrDatas()[srvAttrIndex];
      String decisionCond9 = String.valueOf(aSoAttrData.getState());
      if(decisionCond9.equals("3")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).modifyOrdSrvAttrValueFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond9.equals("4")){
      }
      else if(decisionCond9.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).insertOrdSrvAttrValueFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond9.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).modifyOrdSrvAttrValueFromOrd(aSoAttrData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else{
      }
      srvAttrIndex++;
    }
  }
  public void execute(ISoServiceData aSoServiceData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoServiceData",aSoServiceData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoServiceData, aOrderValueChain, orderOfferId, REGION_ID);
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
    ISoServiceData aSoServiceData = ($vmParameters.get("aSoServiceData") == null)?null:(ISoServiceData)VMDataType.transfer($vmParameters.get("aSoServiceData"),ISoServiceData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoServiceData,aOrderValueChain,orderOfferId,REGION_ID);
  return $vmParameters;
  }
}