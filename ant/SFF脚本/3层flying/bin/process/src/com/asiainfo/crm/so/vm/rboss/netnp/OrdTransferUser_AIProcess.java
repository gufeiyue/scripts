package com.asiainfo.crm.so.vm.rboss.netnp;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class OrdTransferUser_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransferUser_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOrderData aSoOrderData = null;
    aSoOrderData= com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId);REGION_ID=java.lang.String.valueOf(aSoOrderData.getNormalRowsetInfo("frmNormalchangeNetForm")[0].getExtAttr("IN_REGION_ID"));;
    {//Call the sub-processes订购的主策划(子流程)
      Map tmpMap2 = new HashMap();
      Object loopVar2 = null;
      int loopCount2 = 0;
      loopVar2 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar2 == null){ loopVar2 = new Object[0]; loopCount2 = 0;}
else if(loopVar2 instanceof java.util.List){loopCount2 = ((java.util.List)loopVar2).size();
}else if(loopVar2.getClass().isArray()){loopCount2 = ((Object[])loopVar2).length;
}      for(int i=0;i < loopCount2;i++){
      tmpMap2.clear();
      tmpMap2.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap2.put("REGION_ID",REGION_ID);
      tmpMap2.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar2,i),"soUserDataKey"));
      tmpMap2.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar2,i),"customerOrderId"));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransferUserMain",tmpMap2);
      aOVOrderCustomer = (tmpMap2.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap2.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap2.clear();
      }    }

    {//Call the sub-processesOrdNewOffer
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMemOfferNewProdAndSetUserId(customerOrderId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"customerOrderId"));
      tmpMap3.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar3,i),"soUserDataKey"));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("REGION_ID",REGION_ID);
      tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdNewOffer",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

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