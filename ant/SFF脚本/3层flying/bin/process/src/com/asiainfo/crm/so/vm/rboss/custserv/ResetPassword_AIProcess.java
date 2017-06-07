package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class ResetPassword_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ResetPassword_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long userId = 0;
	IOVOrderOffer aOVOrderOffer = null;
	long orderOfferId = 0;
	ISoOrderData aSoOrderData = null;
	String encryptPassword = "";
	String billId = "";
	IOVOrderCustomer aOVOrderCustomer = null;
    aSoOrderData= com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId); billId=aSoOrderData.getBillId(); aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV.class)).unLockUserPassword(billId,REGION_ID);
    encryptPassword = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV.class)).passwordEncryption(customerOrderId);
    {//Call the sub-processes创建单点通用订单
      Map tmpMap20 = new HashMap();
      Object loopVar20 = null;
      int loopCount20 = 0;
      loopVar20 = new Object[]{null};
      loopCount20 = 1;
      for(int i=0;i < loopCount20;i++){
      tmpMap20.clear();
      tmpMap20.put("customerOrderId",new Long(customerOrderId));
      tmpMap20.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap20);
      aOVOrderOffer = (tmpMap20.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap20.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap20.clear();
      }    }

    orderOfferId=aOVOrderOffer.getOrderOfferValue().getOfferOrderId(); aOVOrderOffer.getOrderOfferValue().setOrderState(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0].setOrderState(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE); aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0].setPassword(encryptPassword);if(aOVOrderOffer.getOrderOfferValue().getBusinessId()==191001001004l){aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0].setPasswordType(0);}else{aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0].setPasswordType(1);};
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    aOVOrderCustomer.addOVOrderOffer(aOVOrderOffer);;
    {//Call the sub-processesTransInsUnit
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("orderOfferId",new Long(orderOfferId));
      tmpMap8.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsUnit",tmpMap8);

      tmpMap8.clear();
      }    }

    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IPasswordManageSV.class)).sendSms(aOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderOffer(orderOfferId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderCust(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).transOrderToHis(customerOrderId);
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