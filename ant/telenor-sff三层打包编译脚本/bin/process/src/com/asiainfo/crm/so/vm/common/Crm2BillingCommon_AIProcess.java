package com.asiainfo.crm.so.vm.common;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class Crm2BillingCommon_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(Crm2BillingCommon_AIProcess.class);
  protected void executeInner(IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
  }
  public void execute(IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    executeInner( aOVOrderCustomer);
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
execute(aOVOrderCustomer);
  return $vmParameters;
  }
}