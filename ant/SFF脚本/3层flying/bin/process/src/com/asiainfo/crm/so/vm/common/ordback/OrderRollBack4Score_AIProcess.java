package com.asiainfo.crm.so.vm.common.ordback;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrderRollBack4Score_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrderRollBack4Score_AIProcess.class);
  protected void executeInner(IOVOrderCustomer newOvOrderCustomer,IOVOrderCustomer oldOvOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).scoreManagerPort(oldOvOrderCustomer,newOvOrderCustomer);
  }
  public void execute(IOVOrderCustomer newOvOrderCustomer,IOVOrderCustomer oldOvOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("newOvOrderCustomer",newOvOrderCustomer);
    $inParameter.put("oldOvOrderCustomer",oldOvOrderCustomer);
try{
    executeInner( newOvOrderCustomer, oldOvOrderCustomer);
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
    IOVOrderCustomer newOvOrderCustomer = ($vmParameters.get("newOvOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("newOvOrderCustomer"),IOVOrderCustomer.class);
    IOVOrderCustomer oldOvOrderCustomer = ($vmParameters.get("oldOvOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("oldOvOrderCustomer"),IOVOrderCustomer.class);
execute(newOvOrderCustomer,oldOvOrderCustomer);
  return $vmParameters;
  }
}