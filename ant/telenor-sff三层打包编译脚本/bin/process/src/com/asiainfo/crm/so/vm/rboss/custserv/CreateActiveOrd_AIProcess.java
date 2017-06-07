package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class CreateActiveOrd_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(CreateActiveOrd_AIProcess.class);
  protected void executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = new Object[]{null};
      loopCount4 = 1;
      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("customerOrderId",new Long(customerOrderId));
      tmpMap4.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap4);
      aOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap4.clear();
      }    }

    aOrdUserValue=aOVOrderOffer.getOVOrdOffOrdUser()[0].getOrdUserValues()[0];;
    aOrdUserValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IActiveCardSV.class)).modifyOrderUserData(aOVOrderOffer);
    aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValue(aOrdUserValue,REGION_ID);
    aOrdUserOsStateValue.setState(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
    aOVOrderCustomer.addOVOrderOffer(aOVOrderOffer); aOVOrderOffer.getOVOrdOffOrdUser()[0].addOrdUserOsStateValue(aOrdUserOsStateValue);;
  }
  public void execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, aOVOrderCustomer, REGION_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,aOVOrderCustomer,REGION_ID);
  return $vmParameters;
  }
}