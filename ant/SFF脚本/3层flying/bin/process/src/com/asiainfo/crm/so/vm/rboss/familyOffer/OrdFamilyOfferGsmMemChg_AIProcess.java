package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyOfferGsmMemChg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferGsmMemChg_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	long gsmSpecId = 0;
	long virtualSpecId = 0;
	long mainMemberUserId = 0;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); gsmSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_GSM; virtualSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_FAMILY_VIRT_USER;;
    mainMemberUserId = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getFamilyOfferMemChgMainUser(customerOrderId);
    {//Call the sub-processes家庭套餐GSM成员删除或增加
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getOfferChgAndMemberChgSoUserDataKeys(customerOrderId,1,gsmSpecId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("soOfferChgUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soOfferDataKey"));
      tmpMap3.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"realBusinessId"));
      tmpMap3.put("userState","1");
      tmpMap3.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap3.put("soMemChgUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyGsmUserOfferChg",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processes家庭套餐GSM成员删除或增加
      Map tmpMap16 = new HashMap();
      Object loopVar16 = null;
      int loopCount16 = 0;
      loopVar16 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getOfferChgAndMemberChgSoUserDataKeys(customerOrderId,3,gsmSpecId));
      if(loopVar16 == null){ loopVar16 = new Object[0]; loopCount16 = 0;}
else if(loopVar16 instanceof java.util.List){loopCount16 = ((java.util.List)loopVar16).size();
}else if(loopVar16.getClass().isArray()){loopCount16 = ((Object[])loopVar16).length;
}      for(int i=0;i < loopCount16;i++){
      tmpMap16.clear();
      tmpMap16.put("customerOrderId",new Long(customerOrderId));
      tmpMap16.put("REGION_ID",REGION_ID);
      tmpMap16.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap16.put("soOfferChgUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"soOfferDataKey"));
      tmpMap16.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"realBusinessId"));
      tmpMap16.put("userState","3");
      tmpMap16.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap16.put("soMemChgUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"soUserDataKey"));
      tmpMap16.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyGsmUserOfferChg",tmpMap16);
      aOVOrderCustomer = (tmpMap16.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap16.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap16.clear();
      }    }

    {//Call the sub-processesGSM下周期生效成员退出
      Map tmpMap17 = new HashMap();
      Object loopVar17 = null;
      int loopCount17 = 0;
      loopVar17 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getNextExpireDelMember(customerOrderId,gsmSpecId));
      if(loopVar17 == null){ loopVar17 = new Object[0]; loopCount17 = 0;}
else if(loopVar17 instanceof java.util.List){loopCount17 = ((java.util.List)loopVar17).size();
}else if(loopVar17.getClass().isArray()){loopCount17 = ((Object[])loopVar17).length;
}      for(int i=0;i < loopCount17;i++){
      tmpMap17.clear();
      tmpMap17.put("customerOrderId",new Long(customerOrderId));
      tmpMap17.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar17,i),"soUserDataKey"));
      tmpMap17.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar17,i),"realBusinessId"));
      tmpMap17.put("billId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar17,i),"billId"));
      tmpMap17.put("REGION_ID",REGION_ID);
      tmpMap17.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap17.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap17.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyOfferGsmNextChg",tmpMap17);
      aOVOrderCustomer = (tmpMap17.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap17.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap17.clear();
      }    }

    String decisionCond14 = String.valueOf(null!=aOVOrderCustomer.getOVOrderOffers() && aOVOrderCustomer.getOVOrderOffers().length>0);
    if(decisionCond14.equals("false")){
    }
    else if(decisionCond14.equals("true")){
      aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).modOrderOfferCustIdAndCustType(aOVOrderCustomer,REGION_ID);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBillingSplit(aOVOrderCustomer);
      {//Call the sub-processes转实例
        Map tmpMap12 = new HashMap();
        Object loopVar12 = null;
        int loopCount12 = 0;
        loopVar12 = new Object[]{null};
        loopCount12 = 1;
        for(int i=0;i < loopCount12;i++){
        tmpMap12.clear();
        tmpMap12.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap12.put("REGION_ID",REGION_ID);
        tmpMap12.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap12);
        aOVOrderCustomer = (tmpMap12.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap12.get("aOVOrderCustomer"),IOVOrderCustomer.class);

        tmpMap12.clear();
        }      }

      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(aOVOrderCustomer);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).accrelChg2Bank(aOVOrderCustomer);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrdOffOrdUserCreaterSV.class)).insertInsUserChgNotifyByOrdCust(aOVOrderCustomer,REGION_ID);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).familyMemberChangeSendSms(aOVOrderCustomer,REGION_ID);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).saveOVOrdOfferToFinish(aOVOrderCustomer);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).lockChgMemberOfferInstByCustomerOrderId(customerOrderId,gsmSpecId,"",false);
    }
    else{logger.warn("条件判断Conditions do not match");}
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