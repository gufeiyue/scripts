package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV;
import  com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdReopenNew_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdReopenNew_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
    {//Call the sub-processesOrdReCreateUserMain
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"customerOrderId"));
      tmpMap3.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdReCreateUserMain",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processesOrdNewProdMain
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMemOfferNewProdAndSetUserId(customerOrderId));
      if(loopVar7 == null){ loopVar7 = new Object[0]; loopCount7 = 0;}
else if(loopVar7 instanceof java.util.List){loopCount7 = ((java.util.List)loopVar7).size();
}else if(loopVar7.getClass().isArray()){loopCount7 = ((Object[])loopVar7).length;
}      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"customerOrderId"));
      tmpMap7.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"soUserDataKey"));
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("REGION_ID",REGION_ID);
      tmpMap7.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdNewOffer",tmpMap7);
      aOVOrderCustomer = (tmpMap7.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap7.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap7.clear();
      }    }

    {//Call the sub-processesOrdQuitOffer
      Map tmpMap25 = new HashMap();
      Object loopVar25 = null;
      int loopCount25 = 0;
      loopVar25 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).getAllInstOfferList(customerOrderId));
      if(loopVar25 == null){ loopVar25 = new Object[0]; loopCount25 = 0;}
else if(loopVar25 instanceof java.util.List){loopCount25 = ((java.util.List)loopVar25).size();
}else if(loopVar25.getClass().isArray()){loopCount25 = ((Object[])loopVar25).length;
}      for(int i=0;i < loopCount25;i++){
      tmpMap25.clear();
      tmpMap25.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap25.put("customerOrderId",new Long(customerOrderId));
      tmpMap25.put("REGION_ID",REGION_ID);
      tmpMap25.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar25,i),"soOfferDataKey"));
      tmpMap25.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodcancel.OrdQuitOffer",tmpMap25);
      aOVOrderCustomer = (tmpMap25.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap25.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap25.clear();
      }    }

    {//Call the sub-processes营业送计费通用流程
      Map tmpMap28 = new HashMap();
      Object loopVar28 = null;
      int loopCount28 = 0;
      loopVar28 = new Object[]{null};
      loopCount28 = 1;
      for(int i=0;i < loopCount28;i++){
      tmpMap28.clear();
      tmpMap28.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.Crm2BillingCommon",tmpMap28);

      tmpMap28.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
    {//Call the sub-processes转实例
      Map tmpMap11 = new HashMap();
      Object loopVar11 = null;
      int loopCount11 = 0;
      loopVar11 = new Object[]{null};
      loopCount11 = 1;
      for(int i=0;i < loopCount11;i++){
      tmpMap11.clear();
      tmpMap11.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap11.put("REGION_ID",REGION_ID);
      tmpMap11.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap11);
      aOVOrderCustomer = (tmpMap11.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap11.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap11.clear();
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