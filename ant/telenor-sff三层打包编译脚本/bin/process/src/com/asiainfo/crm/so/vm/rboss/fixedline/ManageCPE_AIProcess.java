package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
public class ManageCPE_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ManageCPE_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID,String CPE_SN,long OFFER_INST_ID,String STATE,String PENALTY_FLAG) throws Exception{
	String _TASK_JUGE_RESULT = "";
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).changeCpeState(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID,STATE);
    String decisionCond74 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isBroken4Cpe(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID,STATE));
    if(decisionCond74.equals("false")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).orderCompletion(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
    }
    else if(decisionCond74.equals("true")){
      String decisionCond77 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isPenalty4Cpe(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID,PENALTY_FLAG));
      if(decisionCond77.equals("true")){
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).calculateCPEPenalty4Broken(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID,STATE);
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).sendPenalty2Billing(customerOrderId,USER_ID);
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).deProvisioningCPE(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      }
      else if(decisionCond77.equals("false")){
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).deProvisioningCPE(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      }
      else{logger.warn("Cpe PenaltyConditions do not match");}
    }
    else{logger.warn("Cpe BrokenConditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID,String CPE_SN,long OFFER_INST_ID,String STATE,String PENALTY_FLAG) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("USER_ID",new Long(USER_ID));
    $inParameter.put("BILL_ID",BILL_ID);
    $inParameter.put("CPE_SN",CPE_SN);
    $inParameter.put("OFFER_INST_ID",new Long(OFFER_INST_ID));
    $inParameter.put("STATE",STATE);
    $inParameter.put("PENALTY_FLAG",PENALTY_FLAG);
try{
    executeInner( customerOrderId, REGION_ID, USER_ID, BILL_ID, CPE_SN, OFFER_INST_ID, STATE, PENALTY_FLAG);
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
    String CPE_SN = ($vmParameters.get("CPE_SN") == null)?"":(String)VMDataType.transfer($vmParameters.get("CPE_SN"),String.class);
    long OFFER_INST_ID = ($vmParameters.get("OFFER_INST_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("OFFER_INST_ID"),long.class)).longValue();
    String STATE = ($vmParameters.get("STATE") == null)?"":(String)VMDataType.transfer($vmParameters.get("STATE"),String.class);
    String PENALTY_FLAG = ($vmParameters.get("PENALTY_FLAG") == null)?"":(String)VMDataType.transfer($vmParameters.get("PENALTY_FLAG"),String.class);
execute(customerOrderId,REGION_ID,USER_ID,BILL_ID,CPE_SN,OFFER_INST_ID,STATE,PENALTY_FLAG);
  return $vmParameters;
  }
}