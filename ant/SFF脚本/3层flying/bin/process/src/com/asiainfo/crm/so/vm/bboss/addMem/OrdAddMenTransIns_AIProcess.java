package com.asiainfo.crm.so.vm.bboss.addMem;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
public class OrdAddMenTransIns_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdAddMenTransIns_AIProcess.class);
  protected void executeInner(long OFFER_ORDER_ID,String REGION_ID,long CREATE_STAFF,String CUSTOMER_ORDER_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processesTransInsUnit
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = new Object[]{null};
      loopCount9 = 1;
      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("orderOfferId",new Long(OFFER_ORDER_ID));
      tmpMap9.put("FLOWOBJECT_ID",CUSTOMER_ORDER_ID);
      tmpMap9.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap9);

      tmpMap9.clear();
      }    }

  }
  public void execute(long OFFER_ORDER_ID,String REGION_ID,long CREATE_STAFF,String CUSTOMER_ORDER_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("OFFER_ORDER_ID",new Long(OFFER_ORDER_ID));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("CREATE_STAFF",new Long(CREATE_STAFF));
    $inParameter.put("CUSTOMER_ORDER_ID",CUSTOMER_ORDER_ID);
try{
    executeInner( OFFER_ORDER_ID, REGION_ID, CREATE_STAFF, CUSTOMER_ORDER_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long CREATE_STAFF = ($vmParameters.get("CREATE_STAFF") == null)?0:((Long)VMDataType.transfer($vmParameters.get("CREATE_STAFF"),long.class)).longValue();
    String CUSTOMER_ORDER_ID = ($vmParameters.get("CUSTOMER_ORDER_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("CUSTOMER_ORDER_ID"),String.class);
execute(OFFER_ORDER_ID,REGION_ID,CREATE_STAFF,CUSTOMER_ORDER_ID);
  return $vmParameters;
  }
}