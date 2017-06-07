package com.asiainfo.crm.so.vm.bboss.accrelSplit;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  java.util.List;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.appframe2.common.DataContainerInterface;
public class OrdChangeAccrelMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChangeAccrelMain_AIProcess.class);
  protected void executeInner(String soUserDataKey,long customerOrderId,String REGION_ID,List itemGrp,DataContainerInterface accRel) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long offerOrderId = 0;
	IOVOrderOffer aOVOrderOffer = null;
    {//Call the sub-processesOrdChangeAccrelBusi
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("soUserDataKey",soUserDataKey);
      tmpMap3.put("accRel",accRel);
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("itemGrp",itemGrp);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.bboss.accrelSplit.OrdChangeAccrelBusi",tmpMap3);
      aOVOrderOffer = (tmpMap3.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap3.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap3.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
  }
  public void execute(String soUserDataKey,long customerOrderId,String REGION_ID,List itemGrp,DataContainerInterface accRel) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soUserDataKey",soUserDataKey);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("itemGrp",itemGrp);
    $inParameter.put("accRel",accRel);
try{
    executeInner( soUserDataKey, customerOrderId, REGION_ID, itemGrp, accRel);
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
    String soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?"":(String)VMDataType.transfer($vmParameters.get("soUserDataKey"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    List itemGrp = ($vmParameters.get("itemGrp") == null)?new java.util.ArrayList():(List)VMDataType.transfer($vmParameters.get("itemGrp"),List.class);
    DataContainerInterface accRel = ($vmParameters.get("accRel") == null)?null:(DataContainerInterface)VMDataType.transfer($vmParameters.get("accRel"),DataContainerInterface.class);
execute(soUserDataKey,customerOrderId,REGION_ID,itemGrp,accRel);
  return $vmParameters;
  }
}