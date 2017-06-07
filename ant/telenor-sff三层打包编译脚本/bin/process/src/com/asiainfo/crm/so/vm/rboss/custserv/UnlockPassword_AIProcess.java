package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class UnlockPassword_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UnlockPassword_AIProcess.class);
  protected String executeInner(String regionId,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	String billId = "";
	ISoOrderData aSoOrderData = null;
	long ret = 0;
	String msg = "";
    aSoOrderData= com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId); billId=aSoOrderData.getBillId(); System.out.println(billId);;
    ret = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV.class)).unLockUserPassword(billId,regionId);
    if(ret==0){msg=com.ai.omframe.util.LocaleResourceFactory.getResource("SOS7105027");} if(ret==1){msg=com.ai.omframe.util.LocaleResourceFactory.getResource("SOS7105028");} System.out.println(msg);;
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderCust(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).transOrderToHis(customerOrderId);
    return msg;
  }
  public String execute(String regionId,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("regionId",regionId);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    String realReturn = executeInner( regionId, customerOrderId);
    $returnParameter.put("msg",realReturn);
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
    String regionId = ($vmParameters.get("regionId") == null)?"":(String)VMDataType.transfer($vmParameters.get("regionId"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
String realReturn = execute(regionId,customerOrderId);
    $vmParameters.put("msg",realReturn);
    return $vmParameters;
  }
}