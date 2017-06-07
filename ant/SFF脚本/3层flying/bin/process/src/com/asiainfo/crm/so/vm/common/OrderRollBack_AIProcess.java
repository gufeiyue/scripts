package com.asiainfo.crm.so.vm.common;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4IndiviInsSV;
import  com.asiainfo.crm.so.common.party.service.interfaces.IRbossPartySV;
import  com.ai.omframe.order.ivalues.IOrdCustValue;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.appframe2.common.DataContainerInterface;
public class OrderRollBack_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrderRollBack_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOrdCustValue oldOrdCustValue = null;
	IOVOrderCustomer oldOVOrderCustomer = null;
	IOVOrderCustomer newOVOrderCustomer = null;
	long oldCustomerOrderId = 0;
	DataContainerInterface rollBackParaData = null;
	HashMap toBillIngMap = new java.util.HashMap();
    oldOrdCustValue = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).createOldOrdCustValue(customerOrderId);
    rollBackParaData = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).getRollBackParameterInterface(customerOrderId);
    oldCustomerOrderId=oldOrdCustValue.getCustomerOrderId();;
    oldOVOrderCustomer = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).createOldOVOrderCustomer(oldCustomerOrderId,oldOrdCustValue);
    newOVOrderCustomer = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).createNewOvOrderCustomer(customerOrderId,oldOVOrderCustomer);
    {//Call the sub-processes信用服务外部接口流程
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = new Object[]{null};
      loopCount15 = 1;
      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("newOvOrderCustomer",newOVOrderCustomer);
      tmpMap15.put("oldOvOrderCustomer",oldOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Interface",tmpMap15);

      tmpMap15.clear();
      }    }

    {//Call the sub-processes转实例
      Map tmpMap13 = new HashMap();
      Object loopVar13 = null;
      int loopCount13 = 0;
      loopVar13 = new Object[]{null};
      loopCount13 = 1;
      for(int i=0;i < loopCount13;i++){
      tmpMap13.clear();
      tmpMap13.put("aOVOrderCustomer",newOVOrderCustomer);
      tmpMap13.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap13);
      newOVOrderCustomer = (tmpMap13.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap13.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap13.clear();
      }    }

    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4IndiviInsSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4IndiviInsSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4IndiviInsSV.class)).IndiviInsChange(oldOVOrderCustomer,newOVOrderCustomer);
    ((com.asiainfo.crm.so.common.party.service.interfaces.IRbossPartySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IRbossPartySV",com.asiainfo.crm.so.common.party.service.interfaces.IRbossPartySV.class)).saveAssureInfoMapForRollBack(oldCustomerOrderId,oldOVOrderCustomer);
    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).dealOrderRedoBilling(oldOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(newOVOrderCustomer);
    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).updateOldOrderCustomerState(oldCustomerOrderId);
    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).setNewCustOrderState(customerOrderId);
  }
  public void execute(long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, REGION_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,REGION_ID);
  return $vmParameters;
  }
}