package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV;
import  com.asiainfo.crm.so.common.service.interfaces.ISoReportSV;
public class OrdFamilyProcessCancel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyProcessCancel_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,String aUnnormalFamilyId,String userIdStr) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.common.service.interfaces.ISoReportSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.ISoReportSV",com.asiainfo.crm.so.common.service.interfaces.ISoReportSV.class)).saveCancelRpGWInfoInterfaceByUserId(customerOrderId,userIdStr);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV.class)).deleteUnusualFamilyRecById(aUnnormalFamilyId);
  }
  public void execute(long customerOrderId,String REGION_ID,String aUnnormalFamilyId,String userIdStr) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aUnnormalFamilyId",aUnnormalFamilyId);
    $inParameter.put("userIdStr",userIdStr);
try{
    executeInner( customerOrderId, REGION_ID, aUnnormalFamilyId, userIdStr);
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
    String aUnnormalFamilyId = ($vmParameters.get("aUnnormalFamilyId") == null)?"":(String)VMDataType.transfer($vmParameters.get("aUnnormalFamilyId"),String.class);
    String userIdStr = ($vmParameters.get("userIdStr") == null)?"":(String)VMDataType.transfer($vmParameters.get("userIdStr"),String.class);
execute(customerOrderId,REGION_ID,aUnnormalFamilyId,userIdStr);
  return $vmParameters;
  }
}