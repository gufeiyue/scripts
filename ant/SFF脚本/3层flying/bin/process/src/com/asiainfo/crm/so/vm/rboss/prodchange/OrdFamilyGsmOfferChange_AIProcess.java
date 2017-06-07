package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyGsmOfferChange_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyGsmOfferChange_AIProcess.class);
  protected IOVOrderCustomer executeInner(long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long customerOrderId,String billId) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processes换策划
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = new Object[]{null};
      loopCount7 = 1;
      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("REGION_ID",REGION_ID);
      tmpMap7.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdChgOffers",tmpMap7);
      aOVOrderCustomer = (tmpMap7.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap7.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap7.clear();
      }    }

    {//Call the sub-processes换策划
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getUserOfferPlanVasDataByOperType(customerOrderId,soUserDataKey,"CHANGE_OFFER"));
      if(loopVar9 == null){ loopVar9 = new Object[0]; loopCount9 = 0;}
else if(loopVar9 instanceof java.util.List){loopCount9 = ((java.util.List)loopVar9).size();
}else if(loopVar9.getClass().isArray()){loopCount9 = ((Object[])loopVar9).length;
}      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar9,i),"soUserDataKey"));
      tmpMap9.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap9.put("REGION_ID",REGION_ID);
      tmpMap9.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdChgOffers",tmpMap9);
      aOVOrderCustomer = (tmpMap9.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap9.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap9.clear();
      }    }

    {//Call the sub-processes订购增值策划
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getUserOfferPlanVasDataByBillId(customerOrderId,soUserDataKey));
      if(loopVar8 == null){ loopVar8 = new Object[0]; loopCount8 = 0;}
else if(loopVar8 instanceof java.util.List){loopCount8 = ((java.util.List)loopVar8).size();
}else if(loopVar8.getClass().isArray()){loopCount8 = ((Object[])loopVar8).length;
}      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar8,i),"soUserDataKey"));
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdAddIncOffer",tmpMap8);
      aOVOrderCustomer = (tmpMap8.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap8.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap8.clear();
      }    }

    {//Call the sub-processes退订增值策划
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getUserOfferPlanVasDataByOperType(customerOrderId,soUserDataKey,"LOGOUT"));
      if(loopVar10 == null){ loopVar10 = new Object[0]; loopCount10 = 0;}
else if(loopVar10 instanceof java.util.List){loopCount10 = ((java.util.List)loopVar10).size();
}else if(loopVar10.getClass().isArray()){loopCount10 = ((Object[])loopVar10).length;
}      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar10,i),"soOfferDataKey"));
      tmpMap10.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap10.put("REGION_ID",REGION_ID);
      tmpMap10.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdDelIncOffer",tmpMap10);
      aOVOrderCustomer = (tmpMap10.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap10.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap10.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long customerOrderId,String billId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("billId",billId);
try{
    IOVOrderCustomer realReturn = executeInner( soUserDataKey, aOVOrderCustomer, REGION_ID, customerOrderId, billId);
    $returnParameter.put("aOVOrderCustomer",realReturn);
  return realReturn;
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
IOVOrderCustomer realReturn = execute(soUserDataKey,aOVOrderCustomer,REGION_ID,customerOrderId,billId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}