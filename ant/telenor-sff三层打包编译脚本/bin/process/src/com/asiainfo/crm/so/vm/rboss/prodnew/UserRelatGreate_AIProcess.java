package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class UserRelatGreate_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserRelatGreate_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	boolean existsCancel = false;
    {//Call the sub-processes订购增值策划
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDatasOfMemOfferNewProdAndSetUserId(customerOrderId));
      if(loopVar4 == null){ loopVar4 = new Object[0]; loopCount4 = 0;}
else if(loopVar4 instanceof java.util.List){loopCount4 = ((java.util.List)loopVar4).size();
}else if(loopVar4.getClass().isArray()){loopCount4 = ((Object[])loopVar4).length;
}      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar4,i),"soUserDataKey"));
      tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap4.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdAddIncOffer",tmpMap4);
      aOVOrderCustomer = (tmpMap4.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap4.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap4.clear();
      }    }

    {//Call the sub-processes家庭套餐变更添加成员
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDatasOfNotMainOfferAddMem(customerOrderId));
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
      tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdRbossMemAdd",tmpMap5);

      tmpMap5.clear();
      }    }

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