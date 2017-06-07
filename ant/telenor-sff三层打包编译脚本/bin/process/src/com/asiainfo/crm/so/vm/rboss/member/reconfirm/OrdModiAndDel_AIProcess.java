package com.asiainfo.crm.so.vm.rboss.member.reconfirm;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdModiAndDel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModiAndDel_AIProcess.class);
  protected void executeInner(String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV.class)).setOpBillIdForUpdate(aOVOrderCustomer,REGION_ID);
    {//Call the sub-processesOrdSubsequentDeal
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = new Object[]{null};
      loopCount9 = 1;
      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("REGION_ID",REGION_ID);
      tmpMap9.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap9.put("customerOrderId",new Long(customerOrderId));
      tmpMap9.put("FLOWOBJECT_ID",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar9,i),"customerOrderId"));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.reconfirm.OrdSubsequentDeal",tmpMap9);

      tmpMap9.clear();
      }    }

  }
  public void execute(String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    executeInner( REGION_ID, aOVOrderCustomer, customerOrderId);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
execute(REGION_ID,aOVOrderCustomer,customerOrderId);
  return $vmParameters;
  }
}