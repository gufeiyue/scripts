package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.appframe2.common.DataContainerInterface;
public class Installation_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(Installation_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOrderData aSoOrderData = null;
	DataContainerInterface aBaseInfoDC = null;
	long aOrgId = 0;
	long aOpId = 0;
	long aPreOrderId = 0;
	long aBusinessId = 0;
	long aUserId = 0;
	String aBillId = "";
	long aCertType = 0;
	String aCertNo = "";
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData = com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId);aOrgId= aOVOrderCustomer.getOrderCustomerValue().getOrgId();aOpId = aOVOrderCustomer.getOrderCustomerValue().getOpId();aBusinessId=aOVOrderCustomer.getOrderCustomerValue().getBusinessId();aUserId=aOVOrderCustomer.getOrderCustomerValue().getUserId();;
    {//Call the sub-processesNewMainOffer
      Map tmpMap1 = new HashMap();
      Object loopVar1 = null;
      int loopCount1 = 0;
      loopVar1 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar1 == null){ loopVar1 = new Object[0]; loopCount1 = 0;}
else if(loopVar1 instanceof java.util.List){loopCount1 = ((java.util.List)loopVar1).size();
}else if(loopVar1.getClass().isArray()){loopCount1 = ((Object[])loopVar1).length;
}      for(int i=0;i < loopCount1;i++){
      tmpMap1.clear();
      tmpMap1.put("customerOrderId",new Long(customerOrderId));
      tmpMap1.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar1,i),"soUserDataKey"));
      tmpMap1.put("REGION_ID",REGION_ID);
      tmpMap1.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap1.put("aSoOrderData",aSoOrderData);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdNewMainOffer",tmpMap1);
      aBaseInfoDC = (tmpMap1.get("aBaseInfoDC") == null)?null:(DataContainerInterface)VMDataType.transfer(tmpMap1.get("aBaseInfoDC"),DataContainerInterface.class);

      tmpMap1.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateOrderCustInfo4New(aOVOrderCustomer);
    aPreOrderId=aBaseInfoDC.getAsLong("PRE_ORDER_ID");aBillId=aBaseInfoDC.getAsString("BILL_ID");aCertType=aBaseInfoDC.getAsLong("CUST_CERT_TYPE");aCertNo=aBaseInfoDC.getAsString("CUST_CERT_CODE");;
    {//Call the sub-processesNewOtherOffer
      Map tmpMap2 = new HashMap();
      Object loopVar2 = null;
      int loopCount2 = 0;
      loopVar2 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMemOfferNewProdAndSetUserId(customerOrderId));
      if(loopVar2 == null){ loopVar2 = new Object[0]; loopCount2 = 0;}
else if(loopVar2 instanceof java.util.List){loopCount2 = ((java.util.List)loopVar2).size();
}else if(loopVar2.getClass().isArray()){loopCount2 = ((Object[])loopVar2).length;
}      for(int i=0;i < loopCount2;i++){
      tmpMap2.clear();
      tmpMap2.put("customerOrderId",new Long(customerOrderId));
      tmpMap2.put("soUserDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar2,i),"soUserDataKey"));
      tmpMap2.put("REGION_ID",REGION_ID);
      tmpMap2.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdNewOtherOffer",tmpMap2);

      tmpMap2.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).PreOccupy(aPreOrderId,customerOrderId,aOrgId,aOpId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).NumOccupy(customerOrderId,aBusinessId,aBillId,REGION_ID,aCertType,aCertNo,aOrgId,aOpId);
    {//Call the sub-processes订单通用过程
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = new Object[]{null};
      loopCount7 = 1;
      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdProcess",tmpMap7);

      tmpMap7.clear();
      }    }

  }
  public void execute(long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, REGION_ID);
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
execute(customerOrderId,REGION_ID);
  return $vmParameters;
  }
}