package com.ai.omframe.order.vm.lib1;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.ivalues.IOrdOfferValue;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOrdOffOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdProdValue;
public class OrdRenewMemProd_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdRenewMemProd_AIProcess.class);
  protected IOVOrderOffer executeInner(long oldOfferOrderId,long businessId,String regionId,String yearmonth,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOrdOfferValue oldOrderOffer = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	IOrdOffOrdUserValue oldOrdOffOrdUser = null;
	IOrdUserValue oldOrdUser = null;
	int targetOrdOffOrdUserState = 0;
	long oldUserOrderId = 0;
	IOrdProdValue[] oldOrdProds = null;
	IOrdProdValue aOldOrdProd = null;
	int indexOldOrdProd = 0;
    oldOrderOffer = com.ai.omframe.util.SoServiceFactory.getOrderOfferDao().getFinishOrderOfferByOrderOfferId( oldOfferOrderId, regionId, yearmonth ); oldOrdOffOrdUser = com.ai.omframe.util.SoServiceFactory.getOrderQryDao().getFinishOrdOffOrdUserByOrdOfferId(oldOfferOrderId, regionId, yearmonth)[0]; oldUserOrderId = oldOrdOffOrdUser.getUserOrderId(); oldOrdUser = com.ai.omframe.util.SoServiceFactory.getOrderUserDao().getFinishOrderUserByOrderUserId(oldUserOrderId, regionId, yearmonth); oldOrdProds = com.ai.omframe.util.SoServiceFactory.getOrderQryDao().getFinishOrdProdByOrdOfferIdAndOrdUserId(oldOfferOrderId, oldUserOrderId,regionId, yearmonth); if(oldOrdOffOrdUser != null){ if(oldOrdOffOrdUser.getState() == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE){ targetOrdOffOrdUserState = com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; }else { targetOrdOffOrdUserState = com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE; } };
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromFinishOrder(aOVOrderCustomer,oldOrderOffer,regionId);
    aOVOrderOffer.getOrderOfferValue().setBusinessId(businessId); String busiType = com.ai.omframe.util.SoServiceFactory.getCrmProductServce().getUpProductItem(businessId).getItemType(); aOVOrderOffer.getOrderOfferValue().setBusinessType(busiType);;
    oldOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromFinishOrder(aOVOrderOffer,oldOrdUser,regionId);
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFormFinsihOrder(aOVOrderOffer,oldOrdOffOrdUser,oldOrdUser,targetOrdOffOrdUserState,regionId);
    while(oldOrdProds!=null && indexOldOrdProd<oldOrdProds.length){
      aOldOrdProd = oldOrdProds[indexOldOrdProd];;
      {//Call the sub-processesOrdRenewProd
        Map tmpMap12 = new HashMap();
        Object loopVar12 = null;
        int loopCount12 = 0;
        loopVar12 = new Object[]{null};
        loopCount12 = 1;
        for(int i=0;i < loopCount12;i++){
        tmpMap12.clear();
        tmpMap12.put("aOVOrderOffer",aOVOrderOffer);
        tmpMap12.put("oldOrdProd",aOldOrdProd);
        tmpMap12.put("REGION_ID",regionId);
        tmpMap12.put("yearmonth",yearmonth);
        tmpMap12.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdRenewProd",tmpMap12);

        tmpMap12.clear();
        }      }

      indexOldOrdProd++;;
    }
    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(long oldOfferOrderId,long businessId,String regionId,String yearmonth,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("oldOfferOrderId",new Long(oldOfferOrderId));
    $inParameter.put("businessId",new Long(businessId));
    $inParameter.put("regionId",regionId);
    $inParameter.put("yearmonth",yearmonth);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderOffer realReturn = executeInner( oldOfferOrderId, businessId, regionId, yearmonth, aOVOrderCustomer);
    $returnParameter.put("aOVOrderOffer",realReturn);
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
    long oldOfferOrderId = ($vmParameters.get("oldOfferOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("oldOfferOrderId"),long.class)).longValue();
    long businessId = ($vmParameters.get("businessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("businessId"),long.class)).longValue();
    String regionId = ($vmParameters.get("regionId") == null)?"":(String)VMDataType.transfer($vmParameters.get("regionId"),String.class);
    String yearmonth = ($vmParameters.get("yearmonth") == null)?"":(String)VMDataType.transfer($vmParameters.get("yearmonth"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderOffer realReturn = execute(oldOfferOrderId,businessId,regionId,yearmonth,aOVOrderCustomer);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}