package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class UserStopOpenStatus_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserStopOpenStatus_AIProcess.class);
  protected void executeInner(String billId,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,String osType,ISoOrderData aSoOrderData,long BUSIOPER_ID ) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOrdUserValue aOrdUserValue = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	long orderOfferId = 0;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("customerOrderId",new Long(customerOrderId));
      tmpMap8.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap8);
      aOVOrderOffer = (tmpMap8.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap8.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap8.clear();
      }    }

    aOrdUserValue=aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0]; aOVOrderCustomer.addOVOrderOffer(aOVOrderOffer); aOrdUserValue.setState(4);
    aOrdUserOsStateValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).createOrdUserOsState(aOrdUserValue);
    aOrdUserOsStateValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).chgUserOsStatus(aSoOrderData,aOrdUserOsStateValue,aOVOrderOffer);
  }
  public void execute(String billId,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,String osType,ISoOrderData aSoOrderData,long BUSIOPER_ID ) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("billId",billId);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("osType",osType);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("BUSIOPER_ID ",new Long(BUSIOPER_ID ));
try{
    executeInner( billId, customerOrderId, REGION_ID, aOVOrderCustomer, osType, aSoOrderData, BUSIOPER_ID );
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
    String billId = ($vmParameters.get("billId") == null)?"":(String)VMDataType.transfer($vmParameters.get("billId"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String osType = ($vmParameters.get("osType") == null)?"":(String)VMDataType.transfer($vmParameters.get("osType"),String.class);
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    long BUSIOPER_ID  = ($vmParameters.get("BUSIOPER_ID ") == null)?0:((Long)VMDataType.transfer($vmParameters.get("BUSIOPER_ID "),long.class)).longValue();
execute(billId,customerOrderId,REGION_ID,aOVOrderCustomer,osType,aSoOrderData,BUSIOPER_ID );
  return $vmParameters;
  }
}