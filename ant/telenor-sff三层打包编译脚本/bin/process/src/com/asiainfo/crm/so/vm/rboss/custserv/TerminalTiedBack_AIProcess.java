package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class TerminalTiedBack_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(TerminalTiedBack_AIProcess.class);
  protected void executeInner(long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOrderData soOrderData = null;
    aOVOrderCustomer = (com.ai.omframe.order.ivalues.IOVOrderCustomer)com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);soOrderData =com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);;
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV.class)).dowithTerminalTiedBack(customerOrderId);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV.class)).proxyerMoneyBack(0);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV.class)).modifyTrmnalBack(0);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ITerminalSV.class)).stopTrmnalActive(0);
    com.ai.omframe.util.SoDataFactory.initSoData(customerOrderId ,soOrderData);;
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
  }
  public void execute(long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    executeInner( customerOrderId);
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
execute(customerOrderId);
  return $vmParameters;
  }
}