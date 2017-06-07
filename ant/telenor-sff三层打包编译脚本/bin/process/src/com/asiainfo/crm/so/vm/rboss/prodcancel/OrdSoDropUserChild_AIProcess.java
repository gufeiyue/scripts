package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdSoDropUserChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdSoDropUserChild_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,long soUserDataKey) throws Exception{
	boolean _TASK_JUGE_RESULT = false;
	ISoOrderData aSoOrderData = null;
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
    aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData();aSoOfferData.setBusinessId(aSoOrderData.getMainBusinessId());;
    {//Call the sub-processeslib2.OrdDropUser
      Map tmpMap20 = new HashMap();
      Object loopVar20 = null;
      int loopCount20 = 0;
      loopVar20 = new Object[]{null};
      loopCount20 = 1;
      for(int i=0;i < loopCount20;i++){
      tmpMap20.clear();
      tmpMap20.put("aSoOfferData",aSoOfferData);
      tmpMap20.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap20.put("FLOWOBJECT_TYPE","BBOSS_CUSTOMER_ORDER");
      tmpMap20.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap20.put("aOrderValueChain",aOrderValueChain);
      tmpMap20.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib2.OrdDropUser",tmpMap20);

      tmpMap20.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,long soUserDataKey) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, aOVOrderCustomer, aOrderValueChain, REGION_ID, soUserDataKey);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(customerOrderId,aOVOrderCustomer,aOrderValueChain,REGION_ID,soUserDataKey);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}