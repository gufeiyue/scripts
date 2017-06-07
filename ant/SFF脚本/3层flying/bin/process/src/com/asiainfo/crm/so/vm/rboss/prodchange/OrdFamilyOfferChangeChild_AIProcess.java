package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyOfferChangeChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferChangeChild_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	long gsmSpecId = 0;
	long virtualSpecId = 0;
	long mainMemberUserId = 0;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); gsmSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_GSM; virtualSpecId = com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_FAMILY_VIRT_USER;;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).setFamilyOfferChgEffectiveAndExpireDate(customerOrderId);
    {//Call the sub-processes家庭套餐GSM用户套餐变更
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndSpec(customerOrderId,"CHANGE",false,gsmSpecId));
      if(loopVar4 == null){ loopVar4 = new Object[0]; loopCount4 = 0;}
else if(loopVar4 instanceof java.util.List){loopCount4 = ((java.util.List)loopVar4).size();
}else if(loopVar4.getClass().isArray()){loopCount4 = ((Object[])loopVar4).length;
}      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"soUserDataKey"));
      tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap4.put("REGION_ID",REGION_ID);
      tmpMap4.put("customerOrderId",new Long(customerOrderId));
      tmpMap4.put("billId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"billId"));
      tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdFamilyGsmOfferChange",tmpMap4);
      aOVOrderCustomer = (tmpMap4.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap4.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap4.clear();
      }    }

    {//Call the sub-processes家庭套餐变更成员删除
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndSpec(customerOrderId,"CHANGE",true,virtualSpecId));
      if(loopVar12 == null){ loopVar12 = new Object[0]; loopCount12 = 0;}
else if(loopVar12 instanceof java.util.List){loopCount12 = ((java.util.List)loopVar12).size();
}else if(loopVar12.getClass().isArray()){loopCount12 = ((Object[])loopVar12).length;
}      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("customerOrderId",new Long(customerOrderId));
      tmpMap12.put("REGION_ID",REGION_ID);
      tmpMap12.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap12.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar12,i),"soUserDataKey"));
      tmpMap12.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyChangeMemDel",tmpMap12);

      tmpMap12.clear();
      }    }

    {//Call the sub-processes换策划
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndSpec(customerOrderId,"CHANGE",true,virtualSpecId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdChgOffers",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    mainMemberUserId = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getMainMemberUserId(customerOrderId);
    {//Call the sub-processes家庭套餐变更添加成员
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferAddMem(customerOrderId));
      if(loopVar5 == null){ loopVar5 = new Object[0]; loopCount5 = 0;}
else if(loopVar5 instanceof java.util.List){loopCount5 = ((java.util.List)loopVar5).size();
}else if(loopVar5.getClass().isArray()){loopCount5 = ((Object[])loopVar5).length;
}      for(int i=0;i < loopCount5;i++){
      tmpMap5.clear();
      tmpMap5.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar5,i),"customerOrderId"));
      tmpMap5.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar5,i),"soUserDataKey"));
      tmpMap5.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar5,i),"realBusinessId"));
      tmpMap5.put("REGION_ID",REGION_ID);
      tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap5.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyAddMem",tmpMap5);

      tmpMap5.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).modOrderOfferCustIdAndCustType(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    {//Call the sub-processes转实例
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap8);
      aOVOrderCustomer = (tmpMap8.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap8.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap8.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV.class)).sendToAms(aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).saveOVOrdOfferToFinish(aOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).finishOrderCust(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).transOrderToHis(customerOrderId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).lockUserMainOfferByOVOrderCustomer(aOVOrderCustomer,"MEMBER_ADD",false);
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