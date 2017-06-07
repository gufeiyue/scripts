package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdFamilyOfferGsmNextChg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferGsmNextChg_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,long soUserDataKey,long realBusinessId,String billId,String REGION_ID,long mainMemberUserId,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processes家庭套餐处理成员下周期套餐
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = new Object[]{null};
      loopCount9 = 1;
      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("customerOrderId",new Long(customerOrderId));
      tmpMap9.put("REGION_ID",REGION_ID);
      tmpMap9.put("billId",billId);
      tmpMap9.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap9.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.familyOffer.OrdFamilyOfferDealNextOffer",tmpMap9);
      aOVOrderCustomer = (tmpMap9.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap9.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap9.clear();
      }    }

    {//Call the sub-processes家庭套餐成员删除
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("customerOrderId",new Long(customerOrderId));
      tmpMap8.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap8.put("realBusinessId",new Long(realBusinessId));
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("mainMemberUserId",new Long(mainMemberUserId));
      tmpMap8.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdFamilyDelMem",tmpMap8);

      tmpMap8.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,long soUserDataKey,long realBusinessId,String billId,String REGION_ID,long mainMemberUserId,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("realBusinessId",new Long(realBusinessId));
    $inParameter.put("billId",billId);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("mainMemberUserId",new Long(mainMemberUserId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, soUserDataKey, realBusinessId, billId, REGION_ID, mainMemberUserId, aOVOrderCustomer);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long realBusinessId = ($vmParameters.get("realBusinessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("realBusinessId"),long.class)).longValue();
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long mainMemberUserId = ($vmParameters.get("mainMemberUserId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("mainMemberUserId"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(customerOrderId,soUserDataKey,realBusinessId,billId,REGION_ID,mainMemberUserId,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}