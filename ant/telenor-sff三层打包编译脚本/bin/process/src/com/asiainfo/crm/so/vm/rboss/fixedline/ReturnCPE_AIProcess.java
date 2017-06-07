package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
public class ReturnCPE_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ReturnCPE_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID,String CPE_SN,long OFFER_INST_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
    String decisionCond74 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isReturnInShop(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID));
    if(decisionCond74.equals("false")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).changeCpeStateRequestedReturn(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).returnCPE(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
    }
    else if(decisionCond74.equals("true")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).changeCpeStateReturnedInShop(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      String decisionCond78 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isChargePenalty4Return(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID));
      if(decisionCond78.equals("false")){
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).createSapOrder(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).orderCompletion(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      }
      else if(decisionCond78.equals("true")){
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).creditBackPenalty4Return(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).createSapOrder(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).orderCompletion(customerOrderId,USER_ID,CPE_SN,OFFER_INST_ID);
      }
      else{logger.warn("Charge PenaltyConditions do not match");}
    }
    else{logger.warn("Return In ShopConditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID,String CPE_SN,long OFFER_INST_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("USER_ID",new Long(USER_ID));
    $inParameter.put("BILL_ID",BILL_ID);
    $inParameter.put("CPE_SN",CPE_SN);
    $inParameter.put("OFFER_INST_ID",new Long(OFFER_INST_ID));
try{
    executeInner( customerOrderId, REGION_ID, USER_ID, BILL_ID, CPE_SN, OFFER_INST_ID);
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
execute(customerOrderId,REGION_ID,USER_ID,BILL_ID,CPE_SN,OFFER_INST_ID);
  return $vmParameters;
  }
}