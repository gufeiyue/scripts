package com.asiainfo.crm.so.vm.rboss.member;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdModiMem_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModiMem_AIProcess.class);
  protected void executeInner(long soUserDataKey,long realBusinessId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(realBusinessId)); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processAddMemOVChain(aOrderValueChain,map);
    {//Call the sub-processes修改成员
      Map tmpMap5 = new HashMap();
      Object loopVar5 = null;
      int loopCount5 = 0;
      loopVar5 = new Object[]{null};
      loopCount5 = 1;
      for(int i=0;i < loopCount5;i++){
      tmpMap5.clear();
      tmpMap5.put("aSoUserData",aSoUserData);
      tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap5.put("aSoOfferData",aSoOfferData);
      tmpMap5.put("aOrderValueChain",aOrderValueChain);
      tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap5.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdModiMem",tmpMap5);

      tmpMap5.clear();
      }    }

  }
  public void execute(long soUserDataKey,long realBusinessId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("realBusinessId",new Long(realBusinessId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    executeInner( soUserDataKey, realBusinessId, REGION_ID, aOVOrderCustomer, customerOrderId);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long realBusinessId = ($vmParameters.get("realBusinessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("realBusinessId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
execute(soUserDataKey,realBusinessId,REGION_ID,aOVOrderCustomer,customerOrderId);
  return $vmParameters;
  }
}