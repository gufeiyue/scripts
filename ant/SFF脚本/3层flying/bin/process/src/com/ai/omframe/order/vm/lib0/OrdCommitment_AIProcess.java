package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdCommitmentCreater;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoCommitmentData;
public class OrdCommitment_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdCommitment_AIProcess.class);
  protected void executeInner(ISoOfferData aSoOfferData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int commitmentIdx = 0;
	ISoCommitmentData aSoCommitmentData = null;
    while(aSoOfferData.getSoCommitmentDatas()!=null&&commitmentIdx<aSoOfferData.getSoCommitmentDatas().length){
      aSoCommitmentData=aSoOfferData.getSoCommitmentDatas()[commitmentIdx];
      String decisionCond15 = String.valueOf(aSoCommitmentData.getState()>com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
      if(decisionCond15.equals("false")){
        ((com.ai.omframe.order.service.interfaces.IOrdCommitmentCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdCommitmentCreater",com.ai.omframe.order.service.interfaces.IOrdCommitmentCreater.class)).createOrdCommitment(aSoOfferData,aSoCommitmentData,aOVOrdOffOrdUser,REGION_ID);
        commitmentIdx++;
      }
      else if(decisionCond15.equals("true")){
        ((com.ai.omframe.order.service.interfaces.IOrdCommitmentCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdCommitmentCreater",com.ai.omframe.order.service.interfaces.IOrdCommitmentCreater.class)).createOrdCommitmentFromInst(aSoOfferData,aSoCommitmentData,aOVOrdOffOrdUser,REGION_ID);
        commitmentIdx++;
      }
      else{logger.warn("judge ord commitment stateConditions do not match");}
    }
  }
  public void execute(ISoOfferData aSoOfferData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoOfferData, aOVOrdOffOrdUser, aOrderValueChain, REGION_ID);
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
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoOfferData,aOVOrdOffOrdUser,aOrderValueChain,REGION_ID);
  return $vmParameters;
  }
}