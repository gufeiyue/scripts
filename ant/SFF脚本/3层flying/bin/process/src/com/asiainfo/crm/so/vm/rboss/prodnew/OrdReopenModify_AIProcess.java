package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV;
import  com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class OrdReopenModify_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdReopenModify_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long userId = 0;
	IOVOrderOffer aOVOrderOffer = null;
	long orderOfferId = 0;
	ISoOrderData aSoOrderData = null;
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderCustomer aOvOrderCustToBilling = null;
	IOrdUserValue aOrdUserValue = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);;
    {//Call the sub-processesOrdModifyUserProds
      Map tmpMap52 = new HashMap();
      Object loopVar52 = null;
      int loopCount52 = 0;
      loopVar52 = new Object[]{null};
      loopCount52 = 1;
      for(int i=0;i < loopCount52;i++){
      tmpMap52.clear();
      tmpMap52.put("customerOrderId",new Long(customerOrderId));
      tmpMap52.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap52.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdModifyUserProds",tmpMap52);

      tmpMap52.clear();
      }    }

    {//Call the sub-processesOrdCustPriceAdd
      Map tmpMap53 = new HashMap();
      Object loopVar53 = null;
      int loopCount53 = 0;
      loopVar53 = new Object[]{null};
      loopCount53 = 1;
      for(int i=0;i < loopCount53;i++){
      tmpMap53.clear();
      tmpMap53.put("customerOrderId",new Long(customerOrderId));
      tmpMap53.put("aSoOrderData",aSoOrderData);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdCustPriceAdd",tmpMap53);

      tmpMap53.clear();
      }    }

    aOrdUserValue=aOVOrderCustomer.getOVOrderOffers()[0].getOVOrdOffOrdUser()[0].getOrdUserValues()[0];;
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).dealUserStateByPayType(customerOrderId,aOVOrderCustomer);
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).dealUserPwd(aOVOrderCustomer);
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).dealNewSimCard(customerOrderId,aOVOrderCustomer);
    {//Call the sub-processes营业送计费通用流程
      Map tmpMap21 = new HashMap();
      Object loopVar21 = null;
      int loopCount21 = 0;
      loopVar21 = new Object[]{null};
      loopCount21 = 1;
      for(int i=0;i < loopCount21;i++){
      tmpMap21.clear();
      tmpMap21.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.Crm2BillingCommon",tmpMap21);

      tmpMap21.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
    {//Call the sub-processes转实例
      Map tmpMap28 = new HashMap();
      Object loopVar28 = null;
      int loopCount28 = 0;
      loopVar28 = new Object[]{null};
      loopCount28 = 1;
      for(int i=0;i < loopCount28;i++){
      tmpMap28.clear();
      tmpMap28.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap28.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap28);
      aOVOrderCustomer = (tmpMap28.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap28.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap28.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    logger.warn("Configure the node (入网短信发送)");
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).dealScore(aOVOrderCustomer);
    ((com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV",com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV.class)).addlog(aOVOrderCustomer);
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