package com.asiainfo.crm.so.vm.rboss.chgaccrel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdChangeAccrel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChangeAccrel_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long acctId = 0;
	long custId = 0;
	long userId = 0;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);custId=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().getCustAndContact(customerOrderId); userId=((com.ai.omframe.order.ivalues.IOrdCustValue)aOVOrderCustomer.getOrderCustomerValue()).getUserId();;
    acctId=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().createAcctAndContact(customerOrderId,custId);;
    {//Call the sub-processesOrdChangeAccrelBusi
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDatas4Accrel(customerOrderId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("userId",new Long(userId));
      tmpMap3.put("acctId",new Long(acctId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.chgaccrel.OrdChangeAccrelBusi",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processes转实例
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap10.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap10);
      aOVOrderCustomer = (tmpMap10.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap10.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap10.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, REGION_ID, aOVOrderCustomer);
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
IOVOrderCustomer realReturn = execute(customerOrderId,REGION_ID,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}