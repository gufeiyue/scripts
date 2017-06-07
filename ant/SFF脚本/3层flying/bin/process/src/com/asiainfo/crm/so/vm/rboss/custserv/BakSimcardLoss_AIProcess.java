package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IAm2CrmSV;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class BakSimcardLoss_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(BakSimcardLoss_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID,IOVOrderOffer aOVOrderOffer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOrderData aSoOrderData = null;
	IOVOrderCustomer aOVOrderCustomer = null;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap10 = new HashMap();
      Object loopVar10 = null;
      int loopCount10 = 0;
      loopVar10 = new Object[]{null};
      loopCount10 = 1;
      for(int i=0;i < loopCount10;i++){
      tmpMap10.clear();
      tmpMap10.put("customerOrderId",new Long(customerOrderId));
      tmpMap10.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap10);
      aOVOrderOffer = (tmpMap10.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap10.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap10.clear();
      }    }

    aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).initialDataForDel(aOVOrderOffer,customerOrderId);
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).bakSimcardLoss(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
  }
  public void execute(long customerOrderId,String REGION_ID,IOVOrderOffer aOVOrderOffer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderOffer",aOVOrderOffer);
try{
    executeInner( customerOrderId, REGION_ID, aOVOrderOffer);
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
    IOVOrderOffer aOVOrderOffer = ($vmParameters.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer($vmParameters.get("aOVOrderOffer"),IOVOrderOffer.class);
execute(customerOrderId,REGION_ID,aOVOrderOffer);
  return $vmParameters;
  }
}