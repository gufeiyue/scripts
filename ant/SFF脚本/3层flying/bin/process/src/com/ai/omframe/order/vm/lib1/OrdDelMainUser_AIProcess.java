package com.ai.omframe.order.vm.lib1;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class OrdDelMainUser_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelMainUser_AIProcess.class);
  protected IOVOrderOffer executeInner(IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String aBillId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	IOrdUserValue aOrdUserValue = null;
	long aOfferInstId = 0;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
    if (null==aOrderValueChain){ aOrderValueChain=new com.ai.omframe.order.valuebean.OrderValueChain(); } int delState=com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; if(aSoOfferData.getExpireDate()!=null&&aSoOfferData.getExpireDate().after(com.ai.common.util.TimeUtil.getPrimaryDataSourceSysDate())){ delState=com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE; } aOrderValueChain.setSTATE_OFFER(delState); aOrderValueChain.setSTATE_USER(delState); aOrderValueChain.setSTATE_OFFER_USER(delState);;
    aSoUserData = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getMainRoleSoUserData(aSoOfferData);
    aOfferInstId=aSoOfferData.getInsOfferId();;
    {//Call the sub-processes删除策划
      Map tmpMap21 = new HashMap();
      Object loopVar21 = null;
      int loopCount21 = 0;
      loopVar21 = new Object[]{null};
      loopCount21 = 1;
      for(int i=0;i < loopCount21;i++){
      tmpMap21.clear();
      tmpMap21.put("aSoOfferData",aSoOfferData);
      tmpMap21.put("aSoUserData",aSoUserData);
      tmpMap21.put("aOrderValueChain",aOrderValueChain);
      tmpMap21.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap21.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdDelOffer",tmpMap21);
      aOVOrderOffer = (tmpMap21.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap21.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap21.clear();
      }    }

    String decisionCond18 = String.valueOf(org.apache.commons.lang.StringUtils.isNotBlank(aBillId));
    if(decisionCond18.equals("false")){
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    }
    else if(decisionCond18.equals("true")){
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInstByBillId(aOVOrderOffer,aBillId,aSoUserData,aOrderValueChain,REGION_ID);
    }
    else{logger.warn("是否传入计费号Conditions do not match");}
    aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValueFromInst(aOrdUserValue,REGION_ID);
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrdUserOsStateValue,aOrderValueChain,REGION_ID);
    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdBusiLimitValue(aOVOrdOffOrdUser);
    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createDeleteOrdAccrelValueFromInsAll(aOVOrderOffer,aSoUserData,aOrdUserValue,aOrderValueChain,REGION_ID);
    {//Call the sub-processes删除产品订单
      Map tmpMap22 = new HashMap();
      Object loopVar22 = null;
      int loopCount22 = 0;
      loopVar22 = new Object[]{null};
      loopCount22 = 1;
      for(int i=0;i < loopCount22;i++){
      tmpMap22.clear();
      tmpMap22.put("aSoUserData",aSoUserData);
      tmpMap22.put("aOrderValueChain",aOrderValueChain);
      tmpMap22.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap22.put("insOfferId",new Long(aOfferInstId));
      tmpMap22.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdDelBatchSrvpkgs",tmpMap22);

      tmpMap22.clear();
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

    {//Call the sub-processes构建删除用户连带订购的策划订单
      Map tmpMap24 = new HashMap();
      Object loopVar24 = null;
      int loopCount24 = 0;
      loopVar24 = new Object[]{null};
      loopCount24 = 1;
      for(int i=0;i < loopCount24;i++){
      tmpMap24.clear();
      tmpMap24.put("aSoUserData",aSoUserData);
      tmpMap24.put("REGION_ID",REGION_ID);
      tmpMap24.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap24.put("aOrderValueChain",aOrderValueChain);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDelUserRelateOffer",tmpMap24);

      tmpMap24.clear();
      }    }

    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String aBillId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aBillId",aBillId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderOffer realReturn = executeInner( aOVOrderCustomer, aSoOfferData, aOrderValueChain, aBillId, REGION_ID);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String aBillId = ($vmParameters.get("aBillId") == null)?"":(String)VMDataType.transfer($vmParameters.get("aBillId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderOffer realReturn = execute(aOVOrderCustomer,aSoOfferData,aOrderValueChain,aBillId,REGION_ID);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}