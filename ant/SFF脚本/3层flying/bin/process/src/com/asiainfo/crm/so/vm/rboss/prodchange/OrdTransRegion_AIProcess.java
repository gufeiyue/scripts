package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class OrdTransRegion_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransRegion_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOrderData aSoOrderData = null;
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).invokeOfferOperDispatch(customerOrderId);
    ((com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV",com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV.class)).doInstTransTrack(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV",com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV.class)).insertExpireDeal(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV",com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV.class)).acctTransTrack(aOVOrderCustomer,REGION_ID);
    logger.warn("Configure the node (营业送开通接口)");
    ((com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV",com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV.class)).unifybillingForTrans(aOVOrderCustomer);
    ((com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV",com.asiainfo.crm.so.common.party.service.interfaces.IInsxTransTrackSV.class)).sendAmForChangeRegion(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(aOVOrderCustomer);
    {//Call the sub-processes转实例
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = new Object[]{null};
      loopCount7 = 1;
      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("REGION_ID",REGION_ID);
      tmpMap7.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap7);
      aOVOrderCustomer = (tmpMap7.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap7.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap7.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
  }
  public void execute(long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, REGION_ID);
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
execute(customerOrderId,REGION_ID);
  return $vmParameters;
  }
}