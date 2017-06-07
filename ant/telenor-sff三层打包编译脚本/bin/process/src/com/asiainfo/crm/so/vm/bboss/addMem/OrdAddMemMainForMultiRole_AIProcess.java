package com.asiainfo.crm.so.vm.bboss.addMem;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV;
public class OrdAddMemMainForMultiRole_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdAddMemMainForMultiRole_AIProcess.class);
  protected void executeInner(long customerOrderId,long soUserDataKey,long realBusinessId,String REGION_ID,String TASK_ORG_ID,String TASK_STAFF_ID) throws Exception{
	long offerOrderId = 0;
	boolean _TASK_JUGE_RESULT = false;
    {//Call the sub-processes添加成员操作订单
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = new Object[]{null};
      loopCount6 = 1;
      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("realBusinessId",new Long(realBusinessId));
      tmpMap6.put("customerOrderId",new Long(customerOrderId));
      tmpMap6.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap6.put("FLOWOBJECT_TYPE","BBOSS_CUSTOMER_ORDER");
      tmpMap6.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap6.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.bboss.addMem.OrdAddMemBusi",tmpMap6);
      offerOrderId = (tmpMap6.get("offerOrderId") == null)?0:((Long)VMDataType.transfer(tmpMap6.get("offerOrderId"),long.class)).longValue();

      tmpMap6.clear();
      }    }

    {//Call the sub-processesTransInstAndOpen
      Map tmpMap11 = new HashMap();
      Object loopVar11 = null;
      int loopCount11 = 0;
      loopVar11 = (((com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV",com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV.class)).dealPromotionForMultiRole(customerOrderId,offerOrderId));
      if(loopVar11 == null){ loopVar11 = new Object[0]; loopCount11 = 0;}
else if(loopVar11 instanceof java.util.List){loopCount11 = ((java.util.List)loopVar11).size();
}else if(loopVar11.getClass().isArray()){loopCount11 = ((Object[])loopVar11).length;
}      for(int i=0;i < loopCount11;i++){
      tmpMap11.clear();
      tmpMap11.put("CUSTOMER_ORDER_ID",new Long(customerOrderId));
      tmpMap11.put("OFFER_ORDER_ID",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar11,i),"OFFER_ORDER_ID"));
      tmpMap11.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.bboss.formalSale.TransInstAndOpen",tmpMap11);

      tmpMap11.clear();
      }    }

  }
  public void execute(long customerOrderId,long soUserDataKey,long realBusinessId,String REGION_ID,String TASK_ORG_ID,String TASK_STAFF_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("realBusinessId",new Long(realBusinessId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("TASK_ORG_ID",TASK_ORG_ID);
    $inParameter.put("TASK_STAFF_ID",TASK_STAFF_ID);
try{
    executeInner( customerOrderId, soUserDataKey, realBusinessId, REGION_ID, TASK_ORG_ID, TASK_STAFF_ID);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long realBusinessId = ($vmParameters.get("realBusinessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("realBusinessId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    String TASK_ORG_ID = ($vmParameters.get("TASK_ORG_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("TASK_ORG_ID"),String.class);
    String TASK_STAFF_ID = ($vmParameters.get("TASK_STAFF_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("TASK_STAFF_ID"),String.class);
execute(customerOrderId,soUserDataKey,realBusinessId,REGION_ID,TASK_ORG_ID,TASK_STAFF_ID);
  return $vmParameters;
  }
}