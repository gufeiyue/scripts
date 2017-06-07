package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV;
import  com.asiainfo.crm.so.common.service.interfaces.ISoReportSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdFamilyFixedMemDel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyFixedMemDel_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,String billId,long soUserDataKey) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	long userId = 0;
	IOVOrderOffer[] memOvOrderOffers = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); com.ai.omframe.instance.ivalues.IInsUserValue aInsUserValue = com.ai.omframe.util.InsServiceFactory.getInstanceQueryService().getInstUserByBillId(billId); if(null != aInsUserValue){userId = aInsUserValue.getUserId();};
    {//Call the sub-processes长流程处理成员添加、删除
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = new Object[]{null};
      loopCount12 = 1;
      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("customerOrderId",new Long(customerOrderId));
      tmpMap12.put("billId",billId);
      tmpMap12.put("REGION_ID",REGION_ID);
      tmpMap12.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyOfferMemAddandDel",tmpMap12);
      memOvOrderOffers = (tmpMap12.get("ovOrderOffers") == null)?null:(IOVOrderOffer[])VMDataType.transfer(tmpMap12.get("ovOrderOffers"),IOVOrderOffer[].class);

      tmpMap12.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).mergerOrderOffers(aOVOrderCustomer,memOvOrderOffers);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    {//Call the sub-processes转实例
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = new Object[]{null};
      loopCount9 = 1;
      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap9.put("REGION_ID",REGION_ID);
      tmpMap9.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap9);
      aOVOrderCustomer = (tmpMap9.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap9.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap9.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).accrelChg2Bank(aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).insertInsUserChgNotifyByOrdCust(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV.class)).saveUserOfferExtend(aOVOrderCustomer,null);
    ((com.asiainfo.crm.so.common.service.interfaces.ISoReportSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.ISoReportSV",com.asiainfo.crm.so.common.service.interfaces.ISoReportSV.class)).saveRpCmplInterface(aOVOrderCustomer);
  }
  public void execute(long customerOrderId,String REGION_ID,String billId,long soUserDataKey) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("billId",billId);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
try{
    executeInner( customerOrderId, REGION_ID, billId, soUserDataKey);
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
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
execute(customerOrderId,REGION_ID,billId,soUserDataKey);
  return $vmParameters;
  }
}