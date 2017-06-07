package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class GroupChgProdAndMember_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(GroupChgProdAndMember_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupWFKitsSV.class)).dealAttrDataForTelenorGroupChgMem(customerOrderId);
    {//Call the sub-processes新增成员
      Map tmpMap30 = new HashMap();
      Object loopVar30 = null;
      int loopCount30 = 0;
      loopVar30 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,1));
      if(loopVar30 == null){ loopVar30 = new Object[0]; loopCount30 = 0;}
else if(loopVar30 instanceof java.util.List){loopCount30 = ((java.util.List)loopVar30).size();
}else if(loopVar30.getClass().isArray()){loopCount30 = ((Object[])loopVar30).length;
}      for(int i=0;i < loopCount30;i++){
      tmpMap30.clear();
      tmpMap30.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar30,i),"soUserDataKey"));
      tmpMap30.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar30,i),"realBusinessId"));
      tmpMap30.put("REGION_ID",REGION_ID);
      tmpMap30.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap30.put("customerOrderId",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdAddMem",tmpMap30);

      tmpMap30.clear();
      }    }

    {//Call the sub-processes成员修改
      Map tmpMap31 = new HashMap();
      Object loopVar31 = null;
      int loopCount31 = 0;
      loopVar31 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,2));
      if(loopVar31 == null){ loopVar31 = new Object[0]; loopCount31 = 0;}
else if(loopVar31 instanceof java.util.List){loopCount31 = ((java.util.List)loopVar31).size();
}else if(loopVar31.getClass().isArray()){loopCount31 = ((Object[])loopVar31).length;
}      for(int i=0;i < loopCount31;i++){
      tmpMap31.clear();
      tmpMap31.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar31,i),"soUserDataKey"));
      tmpMap31.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar31,i),"realBusinessId"));
      tmpMap31.put("REGION_ID",REGION_ID);
      tmpMap31.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap31.put("customerOrderId",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdModiMem",tmpMap31);

      tmpMap31.clear();
      }    }

    {//Call the sub-processes成员删除
      Map tmpMap32 = new HashMap();
      Object loopVar32 = null;
      int loopCount32 = 0;
      loopVar32 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasByOfferTypeAndRoleType(customerOrderId,true,false,3));
      if(loopVar32 == null){ loopVar32 = new Object[0]; loopCount32 = 0;}
else if(loopVar32 instanceof java.util.List){loopCount32 = ((java.util.List)loopVar32).size();
}else if(loopVar32.getClass().isArray()){loopCount32 = ((Object[])loopVar32).length;
}      for(int i=0;i < loopCount32;i++){
      tmpMap32.clear();
      tmpMap32.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar32,i),"soUserDataKey"));
      tmpMap32.put("realBusinessId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar32,i),"realBusinessId"));
      tmpMap32.put("REGION_ID",REGION_ID);
      tmpMap32.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap32.put("customerOrderId",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdDelMem",tmpMap32);

      tmpMap32.clear();
      }    }

    {//Call the sub-processesOrdModiAndDel
      Map tmpMap24 = new HashMap();
      Object loopVar24 = null;
      int loopCount24 = 0;
      loopVar24 = new Object[]{null};
      loopCount24 = 1;
      for(int i=0;i < loopCount24;i++){
      tmpMap24.clear();
      tmpMap24.put("REGION_ID",REGION_ID);
      tmpMap24.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap24.put("customerOrderId",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.reconfirm.OrdModiAndDel",tmpMap24);

      tmpMap24.clear();
      }    }

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