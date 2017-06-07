package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class EasyownStateMaintenance_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(EasyownStateMaintenance_AIProcess.class);
  protected void executeInner(String billId,long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = new Object[]{null};
      loopCount5 = 1;
      for(int i=0;i < loopCount5;i++){
      tmpMap5.clear();
      tmpMap5.put("customerOrderId",new Long(customerOrderId));
      tmpMap5.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap5);
      aOVOrderOffer = (tmpMap5.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap5.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap5.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).returnNormal(aOVOrderCustomer,aOVOrderOffer);
    {//Call the sub-processes转实例
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = new Object[]{null};
      loopCount6 = 1;
      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap6.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap6);
      aOVOrderCustomer = (tmpMap6.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap6.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap6.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).osStatusSendToOpen(aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).osStatusSendToBilling(aOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
  }
  public void execute(String billId,long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("billId",billId);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( billId, customerOrderId, REGION_ID);
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
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(billId,customerOrderId,REGION_ID);
  return $vmParameters;
  }
}