package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.data.ivalues.ISoBusiPriceData;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class OrdCustPriceAdd_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdCustPriceAdd_AIProcess.class);
  protected void executeInner(ISoOrderData aSoOrderData,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoBusiPriceData aSoBusiPriceData = null;
	int yingyeIndex = 0;
    while(aSoOrderData.getCustSoBusiPrice()!=null&&yingyeIndex<aSoOrderData.getCustSoBusiPrice().length){
      aSoBusiPriceData=aSoOrderData.getCustSoBusiPrice()[yingyeIndex];
      ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createCustOrdBusiPriceValue(customerOrderId,aSoBusiPriceData);
      yingyeIndex++;
    }
  }
  public void execute(ISoOrderData aSoOrderData,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    executeInner( aSoOrderData, customerOrderId);
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
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
execute(aSoOrderData,customerOrderId);
  return $vmParameters;
  }
}