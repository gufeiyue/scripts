package com.ai.omframe.order.vm.lib1;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
public class OrdOpenNewOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdOpenNewOffer_AIProcess.class);
  protected IOVOrderOffer executeInner(ISoUserData aSoUserData,IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	IOrdUserValue aOrdUserValue = null;
	String aBillId = "";
    aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);aBillId=aSoUserData.getBillId();;
    aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData();;
    {//Call the sub-processes新建策划
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("aSoOfferData",aSoOfferData);
      tmpMap8.put("aOrderValueChain",aOrderValueChain);
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewOffer",tmpMap8);
      aOVOrderOffer = (tmpMap8.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap8.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap8.clear();
      }    }

    aOVOrderOffer.getOrderOfferValue().setUserId(aSoUserData.getUserId());;
    aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInstByBillId(aOVOrderOffer,aBillId,aSoUserData,aOrderValueChain,REGION_ID);
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUser(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
    {//Call the sub-processesDeal Commitment
      Map tmpMap18 = new HashMap();
      Object loopVar18 = null;
      int loopCount18 = 0;
      loopVar18 = new Object[]{null};
      loopCount18 = 1;
      for(int i=0;i < loopCount18;i++){
      tmpMap18.clear();
      tmpMap18.put("aSoOfferData",aSoOfferData);
      tmpMap18.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap18.put("aOrderValueChain",aOrderValueChain);
      tmpMap18.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdCommitment",tmpMap18);

      tmpMap18.clear();
      }    }

    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdBusiLimitValue(aOVOrdOffOrdUser);
    {//Call the sub-processes新建多个产品
      Map tmpMap16 = new HashMap();
      Object loopVar16 = null;
      int loopCount16 = 0;
      loopVar16 = new Object[]{null};
      loopCount16 = 1;
      for(int i=0;i < loopCount16;i++){
      tmpMap16.clear();
      tmpMap16.put("aSoUserData",aSoUserData);
      tmpMap16.put("aOrderValueChain",aOrderValueChain);
      tmpMap16.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap16.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewBatchSrvpkgs",tmpMap16);

      tmpMap16.clear();
      }    }

    {//Call the sub-processes新建营业费用
      Map tmpMap14 = new HashMap();
      Object loopVar14 = null;
      int loopCount14 = 0;
      loopVar14 = new Object[]{null};
      loopCount14 = 1;
      for(int i=0;i < loopCount14;i++){
      tmpMap14.clear();
      tmpMap14.put("aSoUserData",aSoUserData);
      tmpMap14.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap14.put("aOrderValueChain",aOrderValueChain);
      tmpMap14.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdOfferUserPrice",tmpMap14);

      tmpMap14.clear();
      }    }

    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(ISoUserData aSoUserData,IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderOffer realReturn = executeInner( aSoUserData, aOVOrderCustomer, aSoOfferData, aOrderValueChain, REGION_ID);
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
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderOffer realReturn = execute(aSoUserData,aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}