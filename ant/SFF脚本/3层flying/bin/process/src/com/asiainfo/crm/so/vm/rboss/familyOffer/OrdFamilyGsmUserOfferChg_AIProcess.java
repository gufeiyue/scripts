package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyGsmUserOfferChg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyGsmUserOfferChg_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long soOfferChgUserDataKey,long userState,long mainMemberUserId,long soMemChgUserDataKey,long realBusinessId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long gsmSpecId = 0;
    gsmSpecId=com.asiainfo.crm.so.common.RBossConst.PROD_CATALOG_GSM;;
    String decisionCond10 = String.valueOf(soOfferChgUserDataKey>0);
    if(decisionCond10.equals("true")){
      {//Call the sub-processes家庭套餐GSM用户套餐变更
        Map tmpMap3 = new HashMap();
        Object loopVar3 = null;
        int loopCount3 = 0;
        loopVar3 = new Object[]{null};
        loopCount3 = 1;
        for(int i=0;i < loopCount3;i++){
        tmpMap3.clear();
        tmpMap3.put("soUserDataKey",new Long(soOfferChgUserDataKey));
        tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap3.put("REGION_ID",REGION_ID);
        tmpMap3.put("customerOrderId",new Long(customerOrderId));
        tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdFamilyGsmOfferChange",tmpMap3);
        aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

        tmpMap3.clear();
        }      }

      String decisionCond6 = String.valueOf(userState==1L);
      if(decisionCond6.equals("false")){
        {//Call the sub-processes家庭套餐成员删除
          Map tmpMap5 = new HashMap();
          Object loopVar5 = null;
          int loopCount5 = 0;
          loopVar5 = new Object[]{null};
          loopCount5 = 1;
          for(int i=0;i < loopCount5;i++){
          tmpMap5.clear();
          tmpMap5.put("customerOrderId",new Long(customerOrderId));
          tmpMap5.put("soUserDataKey",new Long(soMemChgUserDataKey));
          tmpMap5.put("realBusinessId",new Long(realBusinessId));
          tmpMap5.put("REGION_ID",REGION_ID);
          tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap5.put("mainMemberUserId",new Long(mainMemberUserId));
          tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyDelMem",tmpMap5);

          tmpMap5.clear();
          }        }

      }
      else if(decisionCond6.equals("true")){
        {//Call the sub-processes给存在的家庭套餐增加成员
          Map tmpMap4 = new HashMap();
          Object loopVar4 = null;
          int loopCount4 = 0;
          loopVar4 = new Object[]{null};
          loopCount4 = 1;
          for(int i=0;i < loopCount4;i++){
          tmpMap4.clear();
          tmpMap4.put("customerOrderId",new Long(customerOrderId));
          tmpMap4.put("soUserDataKey",new Long(soMemChgUserDataKey));
          tmpMap4.put("realBusinessId",new Long(realBusinessId));
          tmpMap4.put("REGION_ID",REGION_ID);
          tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap4.put("mainMemberUserId",new Long(mainMemberUserId));
          tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyAddMemForInst",tmpMap4);

          tmpMap4.clear();
          }        }

      }
      else{logger.warn("新增OR删除Conditions do not match");}
    }
    else if(decisionCond10.equals("false")){
      {//Call the sub-processes家庭套餐GSM用户开户
        Map tmpMap11 = new HashMap();
        Object loopVar11 = null;
        int loopCount11 = 0;
        loopVar11 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDataOfMainOfferBySpecAndKey(customerOrderId,gsmSpecId,soMemChgUserDataKey));
        if(loopVar11 == null){ loopVar11 = new Object[0]; loopCount11 = 0;}
else if(loopVar11 instanceof java.util.List){loopCount11 = ((java.util.List)loopVar11).size();
}else if(loopVar11.getClass().isArray()){loopCount11 = ((Object[])loopVar11).length;
}        for(int i=0;i < loopCount11;i++){
        tmpMap11.clear();
        tmpMap11.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar11,i),"customerOrderId"));
        tmpMap11.put("REGION_ID",REGION_ID);
        tmpMap11.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar11,i),"soUserDataKey"));
        tmpMap11.put("bill_id",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar11,i),"billId"));
        tmpMap11.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdFamilyGsmUserCreate",tmpMap11);

        tmpMap11.clear();
        }      }

      String decisionCond6 = String.valueOf(userState==1L);
      if(decisionCond6.equals("false")){
        {//Call the sub-processes家庭套餐成员删除
          Map tmpMap5 = new HashMap();
          Object loopVar5 = null;
          int loopCount5 = 0;
          loopVar5 = new Object[]{null};
          loopCount5 = 1;
          for(int i=0;i < loopCount5;i++){
          tmpMap5.clear();
          tmpMap5.put("customerOrderId",new Long(customerOrderId));
          tmpMap5.put("soUserDataKey",new Long(soMemChgUserDataKey));
          tmpMap5.put("realBusinessId",new Long(realBusinessId));
          tmpMap5.put("REGION_ID",REGION_ID);
          tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap5.put("mainMemberUserId",new Long(mainMemberUserId));
          tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyDelMem",tmpMap5);

          tmpMap5.clear();
          }        }

      }
      else if(decisionCond6.equals("true")){
        {//Call the sub-processes给存在的家庭套餐增加成员
          Map tmpMap4 = new HashMap();
          Object loopVar4 = null;
          int loopCount4 = 0;
          loopVar4 = new Object[]{null};
          loopCount4 = 1;
          for(int i=0;i < loopCount4;i++){
          tmpMap4.clear();
          tmpMap4.put("customerOrderId",new Long(customerOrderId));
          tmpMap4.put("soUserDataKey",new Long(soMemChgUserDataKey));
          tmpMap4.put("realBusinessId",new Long(realBusinessId));
          tmpMap4.put("REGION_ID",REGION_ID);
          tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap4.put("mainMemberUserId",new Long(mainMemberUserId));
          tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyAddMemForInst",tmpMap4);

          tmpMap4.clear();
          }        }

      }
      else{logger.warn("新增OR删除Conditions do not match");}
    }
    else{logger.warn("是否有换套餐Conditions do not match");}
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long soOfferChgUserDataKey,long userState,long mainMemberUserId,long soMemChgUserDataKey,long realBusinessId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("soOfferChgUserDataKey",new Long(soOfferChgUserDataKey));
    $inParameter.put("userState",new Long(userState));
    $inParameter.put("mainMemberUserId",new Long(mainMemberUserId));
    $inParameter.put("soMemChgUserDataKey",new Long(soMemChgUserDataKey));
    $inParameter.put("realBusinessId",new Long(realBusinessId));
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, REGION_ID, aOVOrderCustomer, soOfferChgUserDataKey, userState, mainMemberUserId, soMemChgUserDataKey, realBusinessId);
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long soOfferChgUserDataKey = ($vmParameters.get("soOfferChgUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferChgUserDataKey"),long.class)).longValue();
    long userState = ($vmParameters.get("userState") == null)?0:((Long)VMDataType.transfer($vmParameters.get("userState"),long.class)).longValue();
    long mainMemberUserId = ($vmParameters.get("mainMemberUserId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("mainMemberUserId"),long.class)).longValue();
    long soMemChgUserDataKey = ($vmParameters.get("soMemChgUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soMemChgUserDataKey"),long.class)).longValue();
    long realBusinessId = ($vmParameters.get("realBusinessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("realBusinessId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(customerOrderId,REGION_ID,aOVOrderCustomer,soOfferChgUserDataKey,userState,mainMemberUserId,soMemChgUserDataKey,realBusinessId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}