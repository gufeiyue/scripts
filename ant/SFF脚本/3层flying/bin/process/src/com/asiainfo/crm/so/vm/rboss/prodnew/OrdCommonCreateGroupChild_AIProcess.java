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
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdCommonCreateGroupChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdCommonCreateGroupChild_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	boolean existsCancel = false;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    {//Call the sub-processes
      Map tmpMap1 = new HashMap();
      Object loopVar1 = null;
      int loopCount1 = 0;
      loopVar1 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar1 == null){ loopVar1 = new Object[0]; loopCount1 = 0;}
else if(loopVar1 instanceof java.util.List){loopCount1 = ((java.util.List)loopVar1).size();
}else if(loopVar1.getClass().isArray()){loopCount1 = ((Object[])loopVar1).length;
}      for(int i=0;i < loopCount1;i++){
      tmpMap1.clear();
      tmpMap1.put("customerOrderId",new Long(customerOrderId));
      tmpMap1.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar1,i),"soUserDataKey"));
      tmpMap1.put("REGION_ID",REGION_ID);
      tmpMap1.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdGroupVirUserCreate",tmpMap1);
      aOVOrderCustomer = (tmpMap1.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap1.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap1.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV.class)).createMemberUserDataForTelenorGroupNew(customerOrderId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV.class)).dealAttrDataForTelenorGroupNew(customerOrderId);
    {//Call the sub-processes
      Map tmpMap2 = new HashMap();
      Object loopVar2 = null;
      int loopCount2 = 0;
      loopVar2 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferAddMem(customerOrderId));
      if(loopVar2 == null){ loopVar2 = new Object[0]; loopCount2 = 0;}
else if(loopVar2 instanceof java.util.List){loopCount2 = ((java.util.List)loopVar2).size();
}else if(loopVar2.getClass().isArray()){loopCount2 = ((Object[])loopVar2).length;
}      for(int i=0;i < loopCount2;i++){
      tmpMap2.clear();
      tmpMap2.put("customerOrderId",new Long(customerOrderId));
      tmpMap2.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar2,i),"soUserDataKey"));
      tmpMap2.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar2,i),"realBusinessId"));
      tmpMap2.put("REGION_ID",REGION_ID);
      tmpMap2.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdRbossMemAdd",tmpMap2);

      tmpMap2.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV.class)).groupCreateSendSms(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV.class)).createOfferRelate(aOVOrderCustomer,REGION_ID);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    {//Call the sub-processes
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = new Object[]{null};
      loopCount7 = 1;
      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap7);
      aOVOrderCustomer = (tmpMap7.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap7.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap7.clear();
      }    }

    {//Call the sub-processesCmLifeCycle Ins
      Map tmpMap29 = new HashMap();
      Object loopVar29 = null;
      int loopCount29 = 0;
      loopVar29 = new Object[]{null};
      loopCount29 = 1;
      for(int i=0;i < loopCount29;i++){
      tmpMap29.clear();
      tmpMap29.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap29.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.CustomerLifeCycle",tmpMap29);

      tmpMap29.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
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