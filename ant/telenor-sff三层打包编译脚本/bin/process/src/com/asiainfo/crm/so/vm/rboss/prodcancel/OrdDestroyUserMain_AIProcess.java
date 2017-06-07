package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class OrdDestroyUserMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDestroyUserMain_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	IOrdUserValue aOrdUserValue = null;
	IOrdAccrelValue aOrdAccrelValue = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	IOVOrderCustomer aOVOrderCustomerToBilling = null;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.CreateCommOrder",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV.class)).destroyQuitGroup(customerOrderId);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV.class)).destoryLogoutOfferVas(customerOrderId);
    aOrdUserValue = aOVOrderCustomer.getOVOrderOffers()[0].getOVOrdOffOrdUser()[0].getOrdUserValues()[0];aOrdAccrelValue = aOVOrderCustomer.getOVOrderOffers()[0].getOVOrdOffOrdUser()[0].getOrdAccrelValues()[0];;
    aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValueFromInst(aOrdUserValue,REGION_ID);
    aOVOrderCustomer.getOVOrderOffers()[0].getOVOrdOffOrdUser()[0].addOrdUserOsStateValue(aOrdUserOsStateValue);aOrdUserOsStateValue.setOsStatusPre(aOrdUserOsStateValue.getOsStatus());aOrdUserOsStateValue.setOsStatus(com.asiainfo.crm.so.util.RbossServiceFactory.getStopOpenOsStatusSV().setOsStatus(aOrdUserOsStateValue.getOsStatus(),29,"0"));aOrdUserOsStateValue.setOsStatus(com.asiainfo.crm.so.util.RbossServiceFactory.getStopOpenOsStatusSV().setOsStatus(aOrdUserOsStateValue.getOsStatus(),31,"1"));aOrdAccrelValue.setState(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE);String insState=com.asiainfo.crm.so.common.RBossConst.USER_STATE_SO_DESTROY;long businessId=aOVOrderCustomer.getOrderCustomerValue().getBusinessId(); if(businessId==com.asiainfo.crm.so.common.RBossConst.BUSI_ID_ACCT_DESTROY){insState=com.asiainfo.crm.so.common.RBossConst.USER_STATE_ACCT_DESTROY;} aOrdUserValue.setInsState(Integer.parseInt(insState));aOrdUserValue.setObjExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate());aOrdAccrelValue.setObjExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate());;
    aOVOrderCustomerToBilling=(com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.bce.util.BceSVUtil.depthClone(aOVOrderCustomer);;
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV.class)).unifyBillingForDestroyUser(customerOrderId,aOVOrderCustomerToBilling);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV.class)).unifyOpenForDestroyUser(customerOrderId);
    {//Call the sub-processes转实例
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = new Object[]{null};
      loopCount6 = 1;
      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap6.put("REGION_ID",REGION_ID);
      tmpMap6.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap6);
      aOVOrderCustomer = (tmpMap6.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap6.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap6.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IQuitProdSV.class)).dealBusinessForDestroyUser(customerOrderId,aOVOrderCustomer);
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, aOVOrderCustomer, REGION_ID);
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(customerOrderId,aOVOrderCustomer,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}