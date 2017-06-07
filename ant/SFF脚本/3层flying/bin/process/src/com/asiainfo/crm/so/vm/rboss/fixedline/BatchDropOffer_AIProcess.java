package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class BatchDropOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(BatchDropOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(ISoOfferData[] aSoOfferDatas,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,ISoUserData aSoUserData,OrderValueChain aOrderValueChain) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	int aOfferIndex = 0;
    while(aSoOfferDatas!=null&&aOfferIndex<aSoOfferDatas.length){
      aSoOfferData=aSoOfferDatas[aOfferIndex];
      {//Call the sub-processes退订增值策划
        Map tmpMap4 = new HashMap();
        Object loopVar4 = null;
        int loopCount4 = 0;
        loopVar4 = new Object[]{null};
        loopCount4 = 1;
        for(int i=0;i < loopCount4;i++){
        tmpMap4.clear();
        tmpMap4.put("aSoUserData",aSoUserData);
        tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap4.put("aSoOfferData",aSoOfferData);
        tmpMap4.put("aOrderValueChain",aOrderValueChain);
        tmpMap4.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap4);
        aOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap4.clear();
        }      }

      aOfferIndex++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(ISoOfferData[] aSoOfferDatas,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,ISoUserData aSoUserData,OrderValueChain aOrderValueChain) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferDatas",aSoOfferDatas);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
try{
    IOVOrderCustomer realReturn = executeInner( aSoOfferDatas, REGION_ID, aOVOrderCustomer, aSoUserData, aOrderValueChain);
    $returnParameter.put("aOVOrderCustomer",realReturn);
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
    ISoOfferData[] aSoOfferDatas = ($vmParameters.get("aSoOfferDatas") == null)?null:(ISoOfferData[])VMDataType.transfer($vmParameters.get("aSoOfferDatas"),ISoOfferData[].class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
IOVOrderCustomer realReturn = execute(aSoOfferDatas,REGION_ID,aOVOrderCustomer,aSoUserData,aOrderValueChain);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}