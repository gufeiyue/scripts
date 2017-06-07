package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class OrdReopenMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdReopenMain_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,boolean isNewOffer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOrderData soOrderData = null;
    soOrderData=com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId);;
    isNewOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).isNewOfferForReopen(customerOrderId);
    String decisionCond17 = String.valueOf(isNewOffer==true);
    if(decisionCond17.equals("true")){
      {//Call the sub-processesOrdReopenModify
        Map tmpMap19 = new HashMap();
        Object loopVar19 = null;
        int loopCount19 = 0;
        loopVar19 = new Object[]{null};
        loopCount19 = 1;
        for(int i=0;i < loopCount19;i++){
        tmpMap19.clear();
        tmpMap19.put("customerOrderId",new Long(customerOrderId));
        tmpMap19.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdReopenModify",tmpMap19);

        tmpMap19.clear();
        }      }

    }
    else if(decisionCond17.equals("false")){
      {//Call the sub-processesOrdReopenNew
        Map tmpMap18 = new HashMap();
        Object loopVar18 = null;
        int loopCount18 = 0;
        loopVar18 = new Object[]{null};
        loopCount18 = 1;
        for(int i=0;i < loopCount18;i++){
        tmpMap18.clear();
        tmpMap18.put("customerOrderId",new Long(customerOrderId));
        tmpMap18.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdReopenNew",tmpMap18);

        tmpMap18.clear();
        }      }

    }
    else{logger.warn("条件判断Conditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID,boolean isNewOffer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("isNewOffer",new Boolean(isNewOffer));
try{
    executeInner( customerOrderId, REGION_ID, isNewOffer);
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
    boolean isNewOffer = ($vmParameters.get("isNewOffer") == null)?true:((Boolean)VMDataType.transfer($vmParameters.get("isNewOffer"),boolean.class)).booleanValue();
execute(customerOrderId,REGION_ID,isNewOffer);
  return $vmParameters;
  }
}