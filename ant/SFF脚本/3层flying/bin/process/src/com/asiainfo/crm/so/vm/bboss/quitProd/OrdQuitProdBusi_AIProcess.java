package com.asiainfo.crm.so.vm.bboss.quitProd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdQuitProdBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdQuitProdBusi_AIProcess.class);
  protected long[] executeInner(long customerOrderId,long soOfferDataKey,String REGION_ID,long[] ordOfferIds) throws Exception{
	IOVOrderCustomer aOVOrderCustomer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	String billId = "";
	long insOfferId = 0;
	boolean _TASK_JUGE_RESULT = false;
	IOVOrderOffer[] ovOrderOffers = null;
	int aState = 2;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOfferData=(com.ai.omframe.order.data.ivalues.ISoOfferData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soOfferDataKey,customerOrderId);insOfferId=aSoOfferData.getInsOfferId();billId=aSoOfferData.getSoOrderData().getBillId();java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processQuitProdOVChain(aOrderValueChain,map);
    {//Call the sub-processes销户
      Map tmpMap14 = new HashMap();
      Object loopVar14 = null;
      int loopCount14 = 0;
      loopVar14 = new Object[]{null};
      loopCount14 = 1;
      for(int i=0;i < loopCount14;i++){
      tmpMap14.clear();
      tmpMap14.put("aSoOfferData",aSoOfferData);
      tmpMap14.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap14.put("FLOWOBJECT_TYPE","BBOSS_CUSTOMER_ORDER");
      tmpMap14.put("FLOWOBJECT_ID",new Long(customerOrderId));
      tmpMap14.put("aOrderValueChain",aOrderValueChain);
      tmpMap14.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib2.OrdDropUser",tmpMap14);

      tmpMap14.clear();
      }    }

    ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();
    ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
    return ordOfferIds;
  }
  public long[] execute(long customerOrderId,long soOfferDataKey,String REGION_ID,long[] ordOfferIds) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("ordOfferIds",ordOfferIds);
try{
    long[] realReturn = executeInner( customerOrderId, soOfferDataKey, REGION_ID, ordOfferIds);
    $returnParameter.put("ordOfferIds",realReturn);
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
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long[] ordOfferIds = ($vmParameters.get("ordOfferIds") == null)?null:(long[])VMDataType.transfer($vmParameters.get("ordOfferIds"),long[].class);
long[] realReturn = execute(customerOrderId,soOfferDataKey,REGION_ID,ordOfferIds);
    $vmParameters.put("ordOfferIds",realReturn);
    return $vmParameters;
  }
}