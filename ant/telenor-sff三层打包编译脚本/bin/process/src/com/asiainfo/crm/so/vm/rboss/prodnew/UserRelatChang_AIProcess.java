package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class UserRelatChang_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserRelatChang_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processes新增成员
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,false,false,1));
      if(loopVar4 == null){ loopVar4 = new Object[0]; loopCount4 = 0;}
else if(loopVar4 instanceof java.util.List){loopCount4 = ((java.util.List)loopVar4).size();
}else if(loopVar4.getClass().isArray()){loopCount4 = ((Object[])loopVar4).length;
}      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"customerOrderId"));
      tmpMap4.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"soUserDataKey"));
      tmpMap4.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"realBusinessId"));
      tmpMap4.put("REGION_ID",REGION_ID);
      tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdAddMem",tmpMap4);

      tmpMap4.clear();
      }    }

    {//Call the sub-processes成员修改
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,false,false,2));
      if(loopVar6 == null){ loopVar6 = new Object[0]; loopCount6 = 0;}
else if(loopVar6 instanceof java.util.List){loopCount6 = ((java.util.List)loopVar6).size();
}else if(loopVar6.getClass().isArray()){loopCount6 = ((Object[])loopVar6).length;
}      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar6,i),"customerOrderId"));
      tmpMap6.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar6,i),"soUserDataKey"));
      tmpMap6.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar6,i),"realBusinessId"));
      tmpMap6.put("REGION_ID",REGION_ID);
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap6.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdModiMem",tmpMap6);

      tmpMap6.clear();
      }    }

    {//Call the sub-processes成员删除
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,false,false,3));
      if(loopVar7 == null){ loopVar7 = new Object[0]; loopCount7 = 0;}
else if(loopVar7 instanceof java.util.List){loopCount7 = ((java.util.List)loopVar7).size();
}else if(loopVar7.getClass().isArray()){loopCount7 = ((Object[])loopVar7).length;
}      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"customerOrderId"));
      tmpMap7.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"soUserDataKey"));
      tmpMap7.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"realBusinessId"));
      tmpMap7.put("REGION_ID",REGION_ID);
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdDelMem",tmpMap7);

      tmpMap7.clear();
      }    }

    {//Call the sub-processesUserRelatDelIncOffer
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).delMemChangAccrel(customerOrderId,false,false,3,REGION_ID));
      if(loopVar15 == null){ loopVar15 = new Object[0]; loopCount15 = 0;}
else if(loopVar15 instanceof java.util.List){loopCount15 = ((java.util.List)loopVar15).size();
}else if(loopVar15.getClass().isArray()){loopCount15 = ((Object[])loopVar15).length;
}      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("customerOrderId",new Long(customerOrderId));
      tmpMap15.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar15,i),"soUserDataKey"));
      tmpMap15.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap15.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.UserRelatDelIncOffer",tmpMap15);

      tmpMap15.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).dealUserRelatProdExpireDate(aOVOrderCustomer,customerOrderId);
  }
  public void execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    executeInner( customerOrderId, REGION_ID, aOVOrderCustomer);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
execute(customerOrderId,REGION_ID,aOVOrderCustomer);
  return $vmParameters;
  }
}