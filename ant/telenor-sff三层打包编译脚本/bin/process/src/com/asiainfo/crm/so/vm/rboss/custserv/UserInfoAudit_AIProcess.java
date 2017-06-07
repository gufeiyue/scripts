package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class UserInfoAudit_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserInfoAudit_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	String billId = "";
	ISoOrderData aSoOrderData = null;
	IOVOrderCustomer aOVOrderCustomer = null;
    aSoOrderData= com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId); billId=aSoOrderData.getBillId(); aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV.class)).invokeUpdateCustInfoInter(aSoOrderData);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV.class)).invokeActiveCardInter(aSoOrderData);
    logger.warn("Configure the node (调用信用风险控制接口)");
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IUserInfoAuditSV.class)).saveUserInfoAuditLog(aSoOrderData);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
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