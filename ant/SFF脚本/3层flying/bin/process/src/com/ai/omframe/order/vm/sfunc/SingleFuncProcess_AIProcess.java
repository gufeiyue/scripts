package com.ai.omframe.order.vm.sfunc;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.ISFuncDealSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class SingleFuncProcess_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(SingleFuncProcess_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,IOVOrderOffer aOVOrderOffer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processes创建单点通用订单
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("customerOrderId",new Long(customerOrderId));
      tmpMap10.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap10);
      aOVOrderOffer = (tmpMap10.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap10.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap10.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    ((com.ai.omframe.order.service.interfaces.ISFuncDealSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.SFuncDealSV",com.ai.omframe.order.service.interfaces.ISFuncDealSV.class)).saveOrders(customerOrderId);
    {//Call the sub-processes子流程模板
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = (((com.ai.omframe.order.service.interfaces.ISFuncDealSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.SFuncDealSV",com.ai.omframe.order.service.interfaces.ISFuncDealSV.class)).doOtherThing(customerOrderId));
      if(loopVar8 == null){ loopVar8 = new Object[0]; loopCount8 = 0;}
else if(loopVar8 instanceof java.util.List){loopCount8 = ((java.util.List)loopVar8).size();
}else if(loopVar8.getClass().isArray()){loopCount8 = ((Object[])loopVar8).length;
}      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("customerOrderId",new Long(customerOrderId));
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("FLOWOBJECT_CHILD_TASK_CODE",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar8,i),"WORKFLOW_CODE"));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.sfunc.SingleFuncChildTemplate",tmpMap8);

      tmpMap8.clear();
      }    }

    ((com.ai.omframe.order.service.interfaces.ISFuncDealSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.SFuncDealSV",com.ai.omframe.order.service.interfaces.ISFuncDealSV.class)).transToIns(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).setOrderCustAndExtendOrdersFinished(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).transOrderToHis(customerOrderId);
  }
  public void execute(long customerOrderId,String REGION_ID,IOVOrderOffer aOVOrderOffer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderOffer",aOVOrderOffer);
try{
    executeInner( customerOrderId, REGION_ID, aOVOrderOffer);
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
    IOVOrderOffer aOVOrderOffer = ($vmParameters.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer($vmParameters.get("aOVOrderOffer"),IOVOrderOffer.class);
execute(customerOrderId,REGION_ID,aOVOrderOffer);
  return $vmParameters;
  }
}