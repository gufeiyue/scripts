package com.asiainfo.crm.so.vm.bboss.chgProd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdChgProdBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChgProdBusi_AIProcess.class);
  protected long executeInner(long customerOrderId,long soUserDataKey,String workflowId,String REGION_ID) throws Exception{
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoUserData aSoUserData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	long offerOrderId = 0;
	boolean _TASK_JUGE_RESULT = false;
	int aState = 2;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId);aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData();java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
    {//Call the sub-processes用户订购变更
      Map tmpMap15 = new HashMap();
      Object loopVar15 = null;
      int loopCount15 = 0;
      loopVar15 = new Object[]{null};
      loopCount15 = 1;
      for(int i=0;i < loopCount15;i++){
      tmpMap15.clear();
      tmpMap15.put("aSoUserData",aSoUserData);
      tmpMap15.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap15.put("aSoOfferData",aSoOfferData);
      tmpMap15.put("aOrderValueChain",aOrderValueChain);
      tmpMap15.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdModiUserOffer",tmpMap15);
      aOVOrderOffer = (tmpMap15.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap15.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap15.clear();
      }    }

    offerOrderId = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    return offerOrderId;
  }
  public long execute(long customerOrderId,long soUserDataKey,String workflowId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("workflowId",workflowId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    long realReturn = executeInner( customerOrderId, soUserDataKey, workflowId, REGION_ID);
    $returnParameter.put("offerOrderId",new Long(realReturn));
  return realReturn;
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
    String workflowId = ($vmParameters.get("workflowId") == null)?"":(String)VMDataType.transfer($vmParameters.get("workflowId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
long realReturn = execute(customerOrderId,soUserDataKey,workflowId,REGION_ID);
    $vmParameters.put("offerOrderId",new Long(realReturn));
    return $vmParameters;
  }
}