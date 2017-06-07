package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdModifySrvpkg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifySrvpkg_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,long orderOfferId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int srvpkgIndex = 0;
	ISoServicePkgData aSoServicePkgData = null;
    while(null!=aSoUserData.getSoServicePkgData()&&srvpkgIndex<aSoUserData.getSoServicePkgData().length){
      aSoServicePkgData=aSoUserData.getSoServicePkgData()[srvpkgIndex];
      String decisionCond11 = String.valueOf(aSoServicePkgData.getState());
      if(decisionCond11.equals("1")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).insertOrderSrvPkgFromOrd(aSoServicePkgData,aOrderValueChain,orderOfferId,REGION_ID);
      }
      else if(decisionCond11.equals("3")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).deleteOrderSrvPkgFromOrd(aSoServicePkgData,orderOfferId,REGION_ID);
      }
      else if(decisionCond11.equals("2")){
        ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).modifyOrderSrvPkgFromOrd(aSoServicePkgData,orderOfferId,REGION_ID);
      }
      else{
      }
      {//Call the sub-processesOrdModifySrvpkgSrv
        Map tmpMap6 = new HashMap();
        Object loopVar6 = null;
        int loopCount6 = 0;
        loopVar6 = new Object[]{null};
        loopCount6 = 1;
        for(int i=0;i < loopCount6;i++){
        tmpMap6.clear();
        tmpMap6.put("aSoServicePkgData",aSoServicePkgData);
        tmpMap6.put("aOrderValueChain",aOrderValueChain);
        tmpMap6.put("orderOfferId",new Long(orderOfferId));
        tmpMap6.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifySrvpkgSrv",tmpMap6);

        tmpMap6.clear();
        }      }

      {//Call the sub-processesOrdModifySrvpkgPriceAttr
        Map tmpMap7 = new HashMap();
        Object loopVar7 = null;
        int loopCount7 = 0;
        loopVar7 = new Object[]{null};
        loopCount7 = 1;
        for(int i=0;i < loopCount7;i++){
        tmpMap7.clear();
        tmpMap7.put("aSoServicePkgData",aSoServicePkgData);
        tmpMap7.put("aOrderValueChain",aOrderValueChain);
        tmpMap7.put("orderOfferId",new Long(orderOfferId));
        tmpMap7.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.modifyOrd.OrdModifySrvpkgPriceAttr",tmpMap7);

        tmpMap7.clear();
        }      }

      srvpkgIndex++;
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