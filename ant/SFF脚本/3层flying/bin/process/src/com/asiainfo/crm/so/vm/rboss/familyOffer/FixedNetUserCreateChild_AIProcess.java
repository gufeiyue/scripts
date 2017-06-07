package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.appframe2.common.DataContainerInterface;
public class FixedNetUserCreateChild_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(FixedNetUserCreateChild_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long soUserDataKey,String bill_id) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOrderData aSoOrderData = null;
	DataContainerInterface aBaseInfoDC = null;
	long aOrgId = 0;
	long aOpId = 0;
	long aBusinessId = 0;
	long aCertType = 0;
	String aCertNo = "";
	long userId = 0;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData = com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId);aOrgId= aOVOrderCustomer.getOrderCustomerValue().getOrgId();aOpId = aOVOrderCustomer.getOrderCustomerValue().getOpId();aBusinessId=aOVOrderCustomer.getOrderCustomerValue().getBusinessId();;
    {//Call the sub-processesNewMainOffer
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = new Object[]{null};
      loopCount4 = 1;
      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("customerOrderId",new Long(customerOrderId));
      tmpMap4.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap4.put("REGION_ID",REGION_ID);
      tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap4.put("aSoOrderData",aSoOrderData);
      tmpMap4.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdNewMainOffer",tmpMap4);
      aBaseInfoDC = (tmpMap4.get("aBaseInfoDC") == null)?null:(DataContainerInterface)VMDataType.transfer(tmpMap4.get("aBaseInfoDC"),DataContainerInterface.class);

      tmpMap4.clear();
      }    }

    aCertType=aBaseInfoDC.getAsLong("CUST_CERT_TYPE");aCertNo=aBaseInfoDC.getAsString("CUST_CERT_CODE");;
    {//Call the sub-processesNewOtherOffer
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getMemberUserOfferPlanVasData(customerOrderId,bill_id,aOVOrderCustomer));
      if(loopVar12 == null){ loopVar12 = new Object[0]; loopCount12 = 0;}
else if(loopVar12 instanceof java.util.List){loopCount12 = ((java.util.List)loopVar12).size();
}else if(loopVar12.getClass().isArray()){loopCount12 = ((Object[])loopVar12).length;
}      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("customerOrderId",new Long(customerOrderId));
      tmpMap12.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap12.put("REGION_ID",REGION_ID);
      tmpMap12.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap12.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdNewOtherOffer",tmpMap12);

      tmpMap12.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).NumOccupy(customerOrderId,aBusinessId,bill_id,REGION_ID,aCertType,aCertNo,aOrgId,aOpId);
  }
  public void execute(long customerOrderId,String REGION_ID,long soUserDataKey,String bill_id) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("bill_id",bill_id);
try{
    executeInner( customerOrderId, REGION_ID, soUserDataKey, bill_id);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    String bill_id = ($vmParameters.get("bill_id") == null)?"":(String)VMDataType.transfer($vmParameters.get("bill_id"),String.class);
execute(customerOrderId,REGION_ID,soUserDataKey,bill_id);
  return $vmParameters;
  }
}