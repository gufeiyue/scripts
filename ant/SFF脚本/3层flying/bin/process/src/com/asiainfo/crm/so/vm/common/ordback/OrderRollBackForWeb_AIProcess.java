package com.asiainfo.crm.so.vm.common.ordback;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOrdCustValue;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrderRollBackForWeb_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrderRollBackForWeb_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOrdCustValue oldOrdCustValue = null;
	IOVOrderCustomer oldOVOrderCustomer = null;
	IOVOrderCustomer newOVOrderCustomer = null;
	long oldCustomerOrderId = 0;
    oldOrdCustValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createOldOrdCustValue(customerOrderId);
    oldCustomerOrderId=oldOrdCustValue.getCustomerOrderId();;
    oldOVOrderCustomer = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).createOldOVOrderCustomer(oldCustomerOrderId,oldOrdCustValue);
    newOVOrderCustomer = ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSV.class)).createNewOvOrderCustomer(customerOrderId,oldOVOrderCustomer);
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