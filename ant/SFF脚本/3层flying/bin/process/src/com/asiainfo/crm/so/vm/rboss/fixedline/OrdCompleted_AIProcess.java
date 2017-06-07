package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdCompleted_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdCompleted_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).sendOrderInfo2SoBusiLogOfComplete(customerOrderId,USER_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).transOrdxUserOperateToHis(customerOrderId,USER_ID,BILL_ID,REGION_ID);
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).getOVOrderCustomer(customerOrderId,USER_ID);
    String decisionCond10 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isOrderComplete(customerOrderId,USER_ID));
    if(decisionCond10.equals("true")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).transDataToHis(customerOrderId,USER_ID);
    }
    else if(decisionCond10.equals("false")){
    }
    else{logger.warn("允许订单竣工Conditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("USER_ID",new Long(USER_ID));
    $inParameter.put("BILL_ID",BILL_ID);
try{
    executeInner( customerOrderId, REGION_ID, USER_ID, BILL_ID);
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
    long USER_ID = ($vmParameters.get("USER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("USER_ID"),long.class)).longValue();
    String BILL_ID = ($vmParameters.get("BILL_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("BILL_ID"),String.class);
execute(customerOrderId,REGION_ID,USER_ID,BILL_ID);
  return $vmParameters;
  }
}