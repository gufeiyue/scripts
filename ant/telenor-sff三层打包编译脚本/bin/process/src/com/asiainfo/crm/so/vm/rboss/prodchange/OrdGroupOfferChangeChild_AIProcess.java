package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdGroupOfferChangeChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdGroupOfferChangeChild_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	boolean existsCancel = false;
	long virtualRoleId = 0;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); virtualRoleId = com.asiainfo.crm.so.common.RBossConst.FAMILY_NET_VIRTUAL_ROLE_ID;;
    {//Call the sub-processes家庭套餐变更成员删除
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndRole(customerOrderId,"CHANGE",true,virtualRoleId));
      if(loopVar15 == null){ loopVar15 = new Object[0]; loopCount15 = 0;}
else if(loopVar15 instanceof java.util.List){loopCount15 = ((java.util.List)loopVar15).size();
}else if(loopVar15.getClass().isArray()){loopCount15 = ((Object[])loopVar15).length;
}      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("customerOrderId",new Long(customerOrderId));
      tmpMap15.put("REGION_ID",REGION_ID);
      tmpMap15.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap15.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar15,i),"soUserDataKey"));
      tmpMap15.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyChangeMemDel",tmpMap15);

      tmpMap15.clear();
      }    }

    {//Call the sub-processes换策划
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataByOperTypeAndIsMainAndRole(customerOrderId,"CHANGE",true,virtualRoleId));
      if(loopVar7 == null){ loopVar7 = new Object[0]; loopCount7 = 0;}
else if(loopVar7 instanceof java.util.List){loopCount7 = ((java.util.List)loopVar7).size();
}else if(loopVar7.getClass().isArray()){loopCount7 = ((Object[])loopVar7).length;
}      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar7,i),"soUserDataKey"));
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap7.put("REGION_ID",REGION_ID);
      tmpMap7.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdChgOffers",tmpMap7);
      aOVOrderCustomer = (tmpMap7.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap7.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap7.clear();
      }    }

    {//Call the sub-processes家庭套餐变更添加成员
      Map tmpMap16 = new HashMap();
      Object loopVar16 = null;
      int loopCount16 = 0;
      loopVar16 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferAddMem(customerOrderId));
      if(loopVar16 == null){ loopVar16 = new Object[0]; loopCount16 = 0;}
else if(loopVar16 instanceof java.util.List){loopCount16 = ((java.util.List)loopVar16).size();
}else if(loopVar16.getClass().isArray()){loopCount16 = ((Object[])loopVar16).length;
}      for(int i=0;i < loopCount16;i++){
      tmpMap16.clear();
      tmpMap16.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"customerOrderId"));
      tmpMap16.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"soUserDataKey"));
      tmpMap16.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar16,i),"realBusinessId"));
      tmpMap16.put("REGION_ID",REGION_ID);
      tmpMap16.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap16.put("mainMemberUserId","-1");
      tmpMap16.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdGroupAddMem",tmpMap16);

      tmpMap16.clear();
      }    }

    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
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