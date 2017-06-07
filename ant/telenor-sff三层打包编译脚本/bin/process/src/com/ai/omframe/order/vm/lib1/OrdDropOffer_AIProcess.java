package com.ai.omframe.order.vm.lib1;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
public class OrdDropOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDropOffer_AIProcess.class);
  protected IOVOrderOffer executeInner(ISoUserData aSoUserData,IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID,String aBillId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	IOrdUserValue aOrdUserValue = null;
	long aOfferInstId = 0;
    int delState=com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; if(aSoOfferData.getExpireDate()!=null&&aSoOfferData.getExpireDate().after(com.ai.common.util.TimeUtil.getPrimaryDataSourceSysDate())){ delState=com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE; } aOrderValueChain.setSTATE_OFFER(delState); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_OFFER_USER(delState);;
    if (null==aSoOfferData){ aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); } aOfferInstId=aSoOfferData.getInsOfferId();;
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    String decisionCond18 = String.valueOf(org.apache.commons.lang.StringUtils.isNotBlank(aBillId));
    if(decisionCond18.equals("true")){
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInstByBillId(aOVOrderOffer,aBillId,aSoUserData,aOrderValueChain,REGION_ID);
    }
    else if(decisionCond18.equals("false")){
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    }
    else{logger.warn("如果传入了用户计费号Conditions do not match");}
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdBusiLimitValue(aOVOrdOffOrdUser);
    {//Call the sub-processes删除产品订单
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
      tmpMap16.put("insOfferId",new Long(aOfferInstId));
      tmpMap16.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdDelBatchSrvpkgs",tmpMap16);

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
  public IOVOrderOffer execute(ISoUserData aSoUserData,IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID,String aBillId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aBillId",aBillId);
try{
    IOVOrderOffer realReturn = executeInner( aSoUserData, aOVOrderCustomer, aSoOfferData, aOrderValueChain, REGION_ID, aBillId);
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
    String aBillId = ($vmParameters.get("aBillId") == null)?"":(String)VMDataType.transfer($vmParameters.get("aBillId"),String.class);
IOVOrderOffer realReturn = execute(aSoUserData,aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID,aBillId);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}