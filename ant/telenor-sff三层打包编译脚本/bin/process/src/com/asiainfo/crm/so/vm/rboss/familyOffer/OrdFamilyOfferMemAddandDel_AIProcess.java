package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdFamilyOfferMemAddandDel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferMemAddandDel_AIProcess.class);
  protected IOVOrderOffer[] executeInner(long customerOrderId,String billId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long mainMemberUserId = 0;
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderOffer[] ovOrderOffers = null;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    mainMemberUserId = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getFamilyOfferMemChgMainUser(customerOrderId);
    {//Call the sub-processes给存在的家庭套餐增加成员
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getSoUserDatasBySpecAndStateAndBillId(customerOrderId,false,1,-1,billId));
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
      tmpMap4.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyAddMemForInst",tmpMap4);

      tmpMap4.clear();
      }    }

    {//Call the sub-processes家庭套餐成员删除
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).getSoUserDatasBySpecAndStateAndBillId(customerOrderId,false,3,-1,billId));
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

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyDelMem",tmpMap5);

      tmpMap5.clear();
      }    }

    ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();;
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
    return ovOrderOffers;
  }
  public IOVOrderOffer[] execute(long customerOrderId,String billId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("billId",billId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderOffer[] realReturn = executeInner( customerOrderId, billId, REGION_ID);
    $returnParameter.put("ovOrderOffers",realReturn);
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderOffer[] realReturn = execute(customerOrderId,billId,REGION_ID);
    $vmParameters.put("ovOrderOffers",realReturn);
    return $vmParameters;
  }
}