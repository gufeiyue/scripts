package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV;
import  com.ai.omframe.instance.ivalues.IInsUserValue;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdActiveCardMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdActiveCardMain_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long userId = 0;
	IInsUserValue aInsUserValue = null;
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderCustomer aOvOrderCustToBilling = null;
	ISoOrderData aSoOrderData = null;
	IOVOrderOffer aOVOrderOffer = null;
    aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); userId=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId).getMainUserId();aInsUserValue = com.ai.omframe.util.InsServiceFactory.getInstanceQueryService().getInstUserByBillId(aOVOrderCustomer.getOrderCustomerValue().getBillId());;
    {//Call the sub-processes创建主订单流程
      Map tmpMap49 = new HashMap();
      Object loopVar49 = null;
      int loopCount49 = 0;
      loopVar49 = new Object[]{null};
      loopCount49 = 1;
      for(int i=0;i < loopCount49;i++){
      tmpMap49.clear();
      tmpMap49.put("customerOrderId",new Long(customerOrderId));
      tmpMap49.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap49.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.CreateActiveOrd",tmpMap49);

      tmpMap49.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV.class)).createOrdBusiPrice(aOVOrderCustomer,aInsUserValue);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
    ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOvOrderCustToBilling);
    {//Call the sub-processes转实例
      Map tmpMap25 = new HashMap();
      Object loopVar25 = null;
      int loopCount25 = 0;
      loopVar25 = new Object[]{null};
      loopCount25 = 1;
      for(int i=0;i < loopCount25;i++){
      tmpMap25.clear();
      tmpMap25.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap25.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap25);
      aOVOrderCustomer = (tmpMap25.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap25.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap25.clear();
      }    }

    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    ((com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV",com.asiainfo.crm.so.common.service.interfaces.ISoBusiLogSV.class)).addlog(aOVOrderCustomer);
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