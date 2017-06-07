package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoServiceData;
public class OrdModifySrvpkgSrv_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifySrvpkgSrv_AIProcess.class);
  protected void executeInner(ISoServicePkgData aSoServicePkgData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int srvpkgSrvIndex = 0;
	ISoServiceData aSoServiceData = null;
	long srvpkgSrvState = 0;
    while(null!=aSoServicePkgData.getServiceData()&&srvpkgSrvIndex<aSoServicePkgData.getServiceData().length){
      aSoServiceData=aSoServicePkgData.getServiceData()[srvpkgSrvIndex];srvpkgSrvState=aSoServicePkgData.getState();
      String decisionCond14 = String.valueOf(srvpkgSrvState);
      if(decisionCond14.equals("3")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).deleteOrdSrvpkgOrdSrvValueFromOrd(aSoServiceData,orderOfferId,REGION_ID);
      }
      else if(decisionCond14.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).modifyOrdSrvpkgOrdSrvValueFromOrd(aSoServiceData,orderOfferId,REGION_ID);
      }
      else if(decisionCond14.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).insertOrdSrvpkgOrdSrvValueFromOrd(aSoServiceData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else{
      }
      {//Call the sub-processesOrdModifySrvAttr
        Map tmpMap8 = new HashMap();
        Object loopVar8 = null;
        int loopCount8 = 0;
        loopVar8 = new Object[]{null};
        loopCount8 = 1;
        for(int i=0;i < loopCount8;i++){
        tmpMap8.clear();
        tmpMap8.put("aSoServiceData",aSoServiceData);
        tmpMap8.put("aOrderValueChain",aOrderValueChain);
        tmpMap8.put("orderOfferId",new Long(orderOfferId));
        tmpMap8.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifySrvAttr",tmpMap8);

        tmpMap8.clear();
        }      }

      srvpkgSrvIndex++;
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