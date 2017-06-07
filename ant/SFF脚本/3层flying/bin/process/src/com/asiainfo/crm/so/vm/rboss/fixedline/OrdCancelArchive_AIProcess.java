package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.service.interfaces.IOrderOfferSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdCancelArchive_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdCancelArchive_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	long aMainInsOfferId = 0;
	long aMainBusinessId = 0;
	long aPreOrderId = 0;
	long aProdSpecId = 0;
	long aOrgId = 0;
	long aOpId = 0;
    aOVOrderCustomer=com.ai.omframe.util.SoDataFactory.getOVOrderCustomerByCustOrderId(customerOrderId);aMainInsOfferId=aOVOrderCustomer.getOVOrderOffers()[0].getOrderOfferValue().getOfferInstId();aMainBusinessId=aOVOrderCustomer.getOrderCustomerValue().getBusinessId();aOpId=aOVOrderCustomer.getOrderCustomerValue().getOpId();aOrgId=aOVOrderCustomer.getOrderCustomerValue().getOrgId();aProdSpecId=aOVOrderCustomer.getOVOrderOffers()[0].getOVOrdOffOrdUser()[0].getOrdUserValues()[0].getProdCatalogId();aPreOrderId=Long.valueOf(aOVOrderCustomer.getOrderCustomerValue().getPreSoId());;
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).cancelOrderCustDirectly(customerOrderId);
    ((com.ai.omframe.order.service.interfaces.IOrderOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.interfaces.IOrderOfferSV",com.ai.omframe.order.service.interfaces.IOrderOfferSV.class)).setOfferInstStateLocked(aMainInsOfferId,aMainBusinessId,false);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).PreOccupyCancel(aPreOrderId,customerOrderId,aOrgId,aOpId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).NumCancelOccupy(customerOrderId,aMainBusinessId,BILL_ID,REGION_ID,aOrgId,aOpId);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).cancelInsxUserRelat(customerOrderId,USER_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).cancelInsxUserAddress(customerOrderId,USER_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).sendMegRepealFixNet(customerOrderId,USER_ID,BILL_ID,REGION_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).cancelArchiveSpecial(customerOrderId,USER_ID);
  }
  public void execute(long customerOrderId,String REGION_ID,long USER_ID,String BILL_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("USER_ID",new Long(USER_ID));
    $inParameter.put("BILL_ID",BILL_ID);
try{
    executeInner( customerOrderId, REGION_ID, USER_ID, BILL_ID);
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
    long USER_ID = ($vmParameters.get("USER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("USER_ID"),long.class)).longValue();
    String BILL_ID = ($vmParameters.get("BILL_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("BILL_ID"),String.class);
execute(customerOrderId,REGION_ID,USER_ID,BILL_ID);
  return $vmParameters;
  }
}