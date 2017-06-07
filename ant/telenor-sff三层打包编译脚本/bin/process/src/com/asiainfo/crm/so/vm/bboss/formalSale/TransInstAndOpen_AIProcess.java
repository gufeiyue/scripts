package com.asiainfo.crm.so.vm.bboss.formalSale;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IPrjOrderSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class TransInstAndOpen_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(TransInstAndOpen_AIProcess.class);
  protected void executeInner(long BUSIOPER_ID,long OFFER_ID,long CUSTOMER_ORDER_ID,long OFFER_ORDER_ID,String REGION_ID) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	IOVOrderCustomer aOVOrderCustomer = null;
    {//Call the sub-processesTransInsUnit
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("orderOfferId",new Long(OFFER_ORDER_ID));
      tmpMap10.put("FLOWOBJECT_TYPE","BBOSS_CUSTOMER_ORDER");
      tmpMap10.put("FLOWOBJECT_ID",new Long(CUSTOMER_ORDER_ID));
      tmpMap10.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap10);

      tmpMap10.clear();
      }    }

    String decisionCond28 = String.valueOf(((IPrjOrderSV)ServiceFactory.getService(IPrjOrderSV.class)).isNeedRestoreInsOfferState(OFFER_ORDER_ID));
    if(decisionCond28.equals("true")){
      ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).setInstOfferState(OFFER_ORDER_ID,1);
    }
    else if(decisionCond28.equals("false")){
    }
    else{logger.warn("If Need Restore offer instance statusConditions do not match");}
  }
  public void execute(long BUSIOPER_ID,long OFFER_ID,long CUSTOMER_ORDER_ID,long OFFER_ORDER_ID,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("BUSIOPER_ID",new Long(BUSIOPER_ID));
    $inParameter.put("OFFER_ID",new Long(OFFER_ID));
    $inParameter.put("CUSTOMER_ORDER_ID",new Long(CUSTOMER_ORDER_ID));
    $inParameter.put("OFFER_ORDER_ID",new Long(OFFER_ORDER_ID));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( BUSIOPER_ID, OFFER_ID, CUSTOMER_ORDER_ID, OFFER_ORDER_ID, REGION_ID);
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
    long BUSIOPER_ID = ($vmParameters.get("BUSIOPER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("BUSIOPER_ID"),long.class)).longValue();
    long OFFER_ID = ($vmParameters.get("OFFER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("OFFER_ID"),long.class)).longValue();
    long CUSTOMER_ORDER_ID = ($vmParameters.get("CUSTOMER_ORDER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("CUSTOMER_ORDER_ID"),long.class)).longValue();
    long OFFER_ORDER_ID = ($vmParameters.get("OFFER_ORDER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("OFFER_ORDER_ID"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(BUSIOPER_ID,OFFER_ID,CUSTOMER_ORDER_ID,OFFER_ORDER_ID,REGION_ID);
  return $vmParameters;
  }
}