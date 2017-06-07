package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.exe.tf.rboss.service.interfaces.IOrdxDestoryUserSV;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdSoDropUserMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdSoDropUserMain_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOrderData aSoOrderData = null;
	long userId = 0;
	IOVOrderCustomer aOVOrderCustomer = null;
    aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);userId=aSoOrderData.getMainUserId();aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOrderData.getMainInsOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOrderData.getMainBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);;
    ((com.asiainfo.crm.so.exe.tf.rboss.service.interfaces.IOrdxDestoryUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.exe.tf.rboss.service.interfaces.IOrdxDestoryUserSV",com.asiainfo.crm.so.exe.tf.rboss.service.interfaces.IOrdxDestoryUserSV.class)).saveSoDestoryUser(userId,false);
    logger.warn("Configure the node (记录业务操作日志)");
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
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