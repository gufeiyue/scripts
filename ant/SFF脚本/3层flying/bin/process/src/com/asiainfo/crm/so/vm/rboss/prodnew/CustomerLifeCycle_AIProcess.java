package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class CustomerLifeCycle_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(CustomerLifeCycle_AIProcess.class);
  protected void executeInner(IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV.class)).saveGroupIdMapping(aOVOrderCustomer);
  }
  public void execute(IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aOVOrderCustomer, REGION_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aOVOrderCustomer,REGION_ID);
  return $vmParameters;
  }
}