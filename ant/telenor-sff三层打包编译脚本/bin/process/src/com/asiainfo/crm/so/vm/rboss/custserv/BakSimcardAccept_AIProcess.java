package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.appframe2.privilege.UserInfoInterface;
public class BakSimcardAccept_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(BakSimcardAccept_AIProcess.class);
  protected void executeInner(long customerOrderId,long soUserDataKey,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderOffer aOVOrderOffer = null;
	long userId = 0;
	ISoOrderData aSoOrderData = null;
	IOrdUserValue aOrdUserValue = null;
	long offerOrderId = 0;
	UserInfoInterface userInfo = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).setAgencyInfo(customerOrderId,aOVOrderCustomer);
    {//Call the sub-processes创建单点通用订单
      Map tmpMap23 = new HashMap();
      Object loopVar23 = null;
      int loopCount23 = 0;
      loopVar23 = new Object[]{null};
      loopCount23 = 1;
      for(int i=0;i < loopCount23;i++){
      tmpMap23.clear();
      tmpMap23.put("customerOrderId",new Long(customerOrderId));
      tmpMap23.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap23);
      aOVOrderOffer = (tmpMap23.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap23.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap23.clear();
      }    }

    aOVOrderCustomer.addOVOrderOffer(aOVOrderOffer);;
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).bakSimcardAccept(customerOrderId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).outInstStore(customerOrderId,aOVOrderCustomer);
  }
  public void execute(long customerOrderId,long soUserDataKey,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, soUserDataKey, REGION_ID);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,soUserDataKey,REGION_ID);
  return $vmParameters;
  }
}