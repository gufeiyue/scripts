package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class ChgCustOwerOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ChgCustOwerOffer_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long custId,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    {//Call the sub-processesOrdReCreateUserMain
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"customerOrderId"));
      tmpMap3.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("custId",new Long(custId));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.OrdMainOfferCreateUserMain",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processesOrdQuitOffer
      Map tmpMap25 = new HashMap();
      Object loopVar25 = null;
      int loopCount25 = 0;
      loopVar25 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IReCreateUserSV.class)).getAllInstOfferList(customerOrderId));
      if(loopVar25 == null){ loopVar25 = new Object[0]; loopCount25 = 0;}
else if(loopVar25 instanceof java.util.List){loopCount25 = ((java.util.List)loopVar25).size();
}else if(loopVar25.getClass().isArray()){loopCount25 = ((Object[])loopVar25).length;
}      for(int i=0;i < loopCount25;i++){
      tmpMap25.clear();
      tmpMap25.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap25.put("customerOrderId",new Long(customerOrderId));
      tmpMap25.put("REGION_ID",REGION_ID);
      tmpMap25.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar25,i),"soOfferDataKey"));
      tmpMap25.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodcancel.OrdQuitOffer",tmpMap25);
      aOVOrderCustomer = (tmpMap25.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap25.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap25.clear();
      }    }

  }
  public void execute(long customerOrderId,String REGION_ID,long custId,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("custId",new Long(custId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    executeInner( customerOrderId, REGION_ID, custId, aOVOrderCustomer);
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
    long custId = ($vmParameters.get("custId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("custId"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
execute(customerOrderId,REGION_ID,custId,aOVOrderCustomer);
  return $vmParameters;
  }
}