package com.asiainfo.crm.so.vm.rboss.prodchange;

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
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OtherGroupChgProdAndMember_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OtherGroupChgProdAndMember_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	IOVOrderCustomer aOVOrderCustomer = null;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    {//Call the sub-processes新增成员
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,1));
      if(loopVar8 == null){ loopVar8 = new Object[0]; loopCount8 = 0;}
else if(loopVar8 instanceof java.util.List){loopCount8 = ((java.util.List)loopVar8).size();
}else if(loopVar8.getClass().isArray()){loopCount8 = ((Object[])loopVar8).length;
}      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar8,i),"customerOrderId"));
      tmpMap8.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar8,i),"soUserDataKey"));
      tmpMap8.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar8,i),"realBusinessId"));
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdAddMem",tmpMap8);

      tmpMap8.clear();
      }    }

    {//Call the sub-processes成员修改
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,2));
      if(loopVar10 == null){ loopVar10 = new Object[0]; loopCount10 = 0;}
else if(loopVar10 instanceof java.util.List){loopCount10 = ((java.util.List)loopVar10).size();
}else if(loopVar10.getClass().isArray()){loopCount10 = ((Object[])loopVar10).length;
}      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar10,i),"customerOrderId"));
      tmpMap10.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar10,i),"soUserDataKey"));
      tmpMap10.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar10,i),"realBusinessId"));
      tmpMap10.put("REGION_ID",REGION_ID);
      tmpMap10.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap10.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdModiMem",tmpMap10);

      tmpMap10.clear();
      }    }

    {//Call the sub-processes成员删除
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,3));
      if(loopVar9 == null){ loopVar9 = new Object[0]; loopCount9 = 0;}
else if(loopVar9 instanceof java.util.List){loopCount9 = ((java.util.List)loopVar9).size();
}else if(loopVar9.getClass().isArray()){loopCount9 = ((Object[])loopVar9).length;
}      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar9,i),"customerOrderId"));
      tmpMap9.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar9,i),"soUserDataKey"));
      tmpMap9.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar9,i),"realBusinessId"));
      tmpMap9.put("REGION_ID",REGION_ID);
      tmpMap9.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap9.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdDelMem",tmpMap9);

      tmpMap9.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
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