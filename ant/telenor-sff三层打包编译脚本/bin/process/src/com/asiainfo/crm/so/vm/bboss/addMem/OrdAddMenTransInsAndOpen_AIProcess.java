package com.asiainfo.crm.so.vm.bboss.addMem;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV;
public class OrdAddMenTransInsAndOpen_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdAddMenTransInsAndOpen_AIProcess.class);
  protected void executeInner(long OFFER_ORDER_ID,long BUSIOPER_ID,long OFFER_ID,String REGION_ID,String TASK_ORG_ID,String TASK_STAFF_ID,String CUSTOMER_ORDER_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
    String decisionCond25 = String.valueOf(((IBbossSoVMInvokeSV)ServiceFactory.getService(IBbossSoVMInvokeSV.class)).detectOrderCancel(((Long)VMDataType.transfer(CUSTOMER_ORDER_ID,long.class)).longValue()));
    if(decisionCond25.equals("false")){
      {//Call the sub-processesOrdAddMenTransIns
        Map tmpMap15 = new HashMap();
        Object loopVar15 = null;
        int loopCount15 = 0;
        loopVar15 = new Object[]{null};
        loopCount15 = 1;
        for(int i=0;i < loopCount15;i++){
        tmpMap15.clear();
        tmpMap15.put("orderOfferId",new Long(OFFER_ORDER_ID));
        tmpMap15.put("REGION_ID",REGION_ID);
        tmpMap15.put("CUSTOMER_ORDER_ID",CUSTOMER_ORDER_ID);
        tmpMap15.put("FLOWOBJECT_ID",CUSTOMER_ORDER_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap15);

        tmpMap15.clear();
        }      }

      ((com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV",com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV.class)).sendDeductRemindSms(((Long)VMDataType.transfer(CUSTOMER_ORDER_ID,long.class)).longValue(),OFFER_ORDER_ID);
    }
    else if(decisionCond25.equals("true")){
    }
    else{logger.warn("Customer Order Cancellation检测Conditions do not match");}
  }
  public void execute(long OFFER_ORDER_ID,long BUSIOPER_ID,long OFFER_ID,String REGION_ID,String TASK_ORG_ID,String TASK_STAFF_ID,String CUSTOMER_ORDER_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("OFFER_ORDER_ID",new Long(OFFER_ORDER_ID));
    $inParameter.put("BUSIOPER_ID",new Long(BUSIOPER_ID));
    $inParameter.put("OFFER_ID",new Long(OFFER_ID));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("TASK_ORG_ID",TASK_ORG_ID);
    $inParameter.put("TASK_STAFF_ID",TASK_STAFF_ID);
    $inParameter.put("CUSTOMER_ORDER_ID",CUSTOMER_ORDER_ID);
try{
    executeInner( OFFER_ORDER_ID, BUSIOPER_ID, OFFER_ID, REGION_ID, TASK_ORG_ID, TASK_STAFF_ID, CUSTOMER_ORDER_ID);
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
    long OFFER_ORDER_ID = ($vmParameters.get("OFFER_ORDER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("OFFER_ORDER_ID"),long.class)).longValue();
    long BUSIOPER_ID = ($vmParameters.get("BUSIOPER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("BUSIOPER_ID"),long.class)).longValue();
    long OFFER_ID = ($vmParameters.get("OFFER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("OFFER_ID"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    String TASK_ORG_ID = ($vmParameters.get("TASK_ORG_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("TASK_ORG_ID"),String.class);
    String TASK_STAFF_ID = ($vmParameters.get("TASK_STAFF_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("TASK_STAFF_ID"),String.class);
    String CUSTOMER_ORDER_ID = ($vmParameters.get("CUSTOMER_ORDER_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("CUSTOMER_ORDER_ID"),String.class);
execute(OFFER_ORDER_ID,BUSIOPER_ID,OFFER_ID,REGION_ID,TASK_ORG_ID,TASK_STAFF_ID,CUSTOMER_ORDER_ID);
  return $vmParameters;
  }
}