package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyOfferQuitChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferQuitChild_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	long gsmSpecId = 0;
	long virtualSpecId = 0;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); gsmSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_GSM; virtualSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_FAMILY_VIRT_USER;;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).setFamilyQuitExpireDate(customerOrderId);
    {//Call the sub-processes家庭套餐拆网虚用户销户
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoOfferDatasByOfferType(customerOrderId,true));
      if(loopVar6 == null){ loopVar6 = new Object[0]; loopCount6 = 0;}
else if(loopVar6 instanceof java.util.List){loopCount6 = ((java.util.List)loopVar6).size();
}else if(loopVar6.getClass().isArray()){loopCount6 = ((Object[])loopVar6).length;
}      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar6,i),"customerOrderId"));
      tmpMap6.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar6,i),"soOfferDataKey"));
      tmpMap6.put("REGION_ID",REGION_ID);
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap6.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyOfferVirtualQuit",tmpMap6);
      aOVOrderCustomer = (tmpMap6.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap6.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap6.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).familyQuitChangeAccrel(customerOrderId,aOVOrderCustomer,REGION_ID);
    {//Call the sub-processes家庭套餐GSM用户套餐变更
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndSpec(customerOrderId,"CHANGE",false,gsmSpecId));
      if(loopVar5 == null){ loopVar5 = new Object[0]; loopCount5 = 0;}
else if(loopVar5 instanceof java.util.List){loopCount5 = ((java.util.List)loopVar5).size();
}else if(loopVar5.getClass().isArray()){loopCount5 = ((Object[])loopVar5).length;
}      for(int i=0;i < loopCount5;i++){
      tmpMap5.clear();
      tmpMap5.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar5,i),"soUserDataKey"));
      tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap5.put("REGION_ID",REGION_ID);
      tmpMap5.put("customerOrderId",new Long(customerOrderId));
      tmpMap5.put("billId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar5,i),"billId"));
      tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdFamilyGsmOfferChange",tmpMap5);
      aOVOrderCustomer = (tmpMap5.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap5.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap5.clear();
      }    }

    {//Call the sub-processes家庭套餐处理成员下周期套餐
      Map tmpMap19 = new HashMap();
      Object loopVar19 = null;
      int loopCount19 = 0;
      loopVar19 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getfamilyQuitNextValidMember(customerOrderId,gsmSpecId));
      if(loopVar19 == null){ loopVar19 = new Object[0]; loopCount19 = 0;}
else if(loopVar19 instanceof java.util.List){loopCount19 = ((java.util.List)loopVar19).size();
}else if(loopVar19.getClass().isArray()){loopCount19 = ((Object[])loopVar19).length;
}      for(int i=0;i < loopCount19;i++){
      tmpMap19.clear();
      tmpMap19.put("customerOrderId",new Long(customerOrderId));
      tmpMap19.put("REGION_ID",REGION_ID);
      tmpMap19.put("billId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar19,i),"billId"));
      tmpMap19.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap19.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyOfferDealNextOffer",tmpMap19);
      aOVOrderCustomer = (tmpMap19.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap19.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap19.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).modOrderOfferCustIdAndCustType(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).accrelChg2Bank(aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).insertInsUserChgNotifyByOrdCust(aOVOrderCustomer,REGION_ID);
    {//Call the sub-processes转实例
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap10.put("REGION_ID",REGION_ID);
      tmpMap10.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap10);
      aOVOrderCustomer = (tmpMap10.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap10.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap10.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).familyQuitSendSms(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).saveOVOrdOfferToFinish(aOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderCust(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).transOrderToHis(customerOrderId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).lockUserMainOfferByOVOrderCustomer(aOVOrderCustomer,"MEMBER_DEL",false);
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