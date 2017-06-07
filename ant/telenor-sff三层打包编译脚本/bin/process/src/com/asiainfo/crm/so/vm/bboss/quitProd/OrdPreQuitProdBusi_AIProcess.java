package com.asiainfo.crm.so.vm.bboss.quitProd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdPreQuitProdBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdPreQuitProdBusi_AIProcess.class);
  protected long[] executeInner(IOrdUserOsStateValue aOrdUserOsStateValue,long customerOrderId,long soOfferDataKey,String REGION_ID,long[] ordOfferIds) throws Exception{
	ISoOrderData aSoOrderData = null;
	ISoUserData aSoUserData = null;
	IOrdAccrelValue aOrdAccRelValue = null;
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
    com.ai.bce.util.BceCommonStore.clearBakForOM();aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOfferData=(com.ai.omframe.order.data.ivalues.ISoOfferData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soOfferDataKey,customerOrderId);insOfferId=aSoOfferData.getInsOfferId();billId=aSoOfferData.getSoOrderData().getBillId();java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processQuitProdOVChain(aOrderValueChain,map);
    String decisionCond27 = String.valueOf(((IBbossSoVMInvokeSV)ServiceFactory.getService(IBbossSoVMInvokeSV.class)).judgeOfferType(aSoUserData));
    if(decisionCond27.equals("true")){
      {//Call the sub-processes删除主用户
        Map tmpMap20 = new HashMap();
        Object loopVar20 = null;
        int loopCount20 = 0;
        loopVar20 = new Object[]{null};
        loopCount20 = 1;
        for(int i=0;i < loopCount20;i++){
        tmpMap20.clear();
        tmpMap20.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap20.put("aSoOfferData",aSoOfferData);
        tmpMap20.put("aOrderValueChain",aOrderValueChain);
        tmpMap20.put("aBillId",billId);
        tmpMap20.put("FLOWOBJECT_TYPE","BBOSS_CUSTOMER_ORDER");
        tmpMap20.put("FLOWOBJECT_ID",new Long(customerOrderId));
        tmpMap20.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDelMainUser",tmpMap20);
        aOVOrderOffer = (tmpMap20.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap20.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap20.clear();
        }      }

      String decisionCond22 = String.valueOf(((IBbossSoVMInvokeSV)ServiceFactory.getService(IBbossSoVMInvokeSV.class)).judgeOfferType(aSoOfferData));
      if(decisionCond22.equals("PRE_LOGOUT_RECOVER")){
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();aOrderValueChain=com.asiainfo.crm.so.util.BBossProcUtil.processPreQuitProdOVChain(aOrderValueChain,null);com.asiainfo.crm.so.util.BBossProcUtil.changeOVOrdOfferValueState(ovOrderOffers,aOrderValueChain);com.asiainfo.crm.so.util.BBossProcUtil.changeUserPreLogoutRecoverState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
      else if(decisionCond22.equals("PRE_LOGOUT")){
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();aOrderValueChain=com.asiainfo.crm.so.util.BBossProcUtil.processPreQuitProdOVChain(aOrderValueChain,null);com.asiainfo.crm.so.util.BBossProcUtil.changeOVOrdOfferValueState(ovOrderOffers,aOrderValueChain);com.asiainfo.crm.so.util.BBossProcUtil.changeUserPreQuitState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
      else{
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();com.asiainfo.crm.so.util.BBossProcUtil.changeUserState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
    }
    else if(decisionCond27.equals("false")){
      {//Call the sub-processes退订增值策划
        Map tmpMap28 = new HashMap();
        Object loopVar28 = null;
        int loopCount28 = 0;
        loopVar28 = new Object[]{null};
        loopCount28 = 1;
        for(int i=0;i < loopCount28;i++){
        tmpMap28.clear();
        tmpMap28.put("aSoUserData",aSoUserData);
        tmpMap28.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap28.put("aSoOfferData",aSoOfferData);
        tmpMap28.put("aOrderValueChain",aOrderValueChain);
        tmpMap28.put("aBillId",billId);
        tmpMap28.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap28);
        aOVOrderOffer = (tmpMap28.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap28.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap28.clear();
        }      }

      String decisionCond22 = String.valueOf(((IBbossSoVMInvokeSV)ServiceFactory.getService(IBbossSoVMInvokeSV.class)).judgeOfferType(aSoOfferData));
      if(decisionCond22.equals("PRE_LOGOUT_RECOVER")){
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();aOrderValueChain=com.asiainfo.crm.so.util.BBossProcUtil.processPreQuitProdOVChain(aOrderValueChain,null);com.asiainfo.crm.so.util.BBossProcUtil.changeOVOrdOfferValueState(ovOrderOffers,aOrderValueChain);com.asiainfo.crm.so.util.BBossProcUtil.changeUserPreLogoutRecoverState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
      else if(decisionCond22.equals("PRE_LOGOUT")){
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();aOrderValueChain=com.asiainfo.crm.so.util.BBossProcUtil.processPreQuitProdOVChain(aOrderValueChain,null);com.asiainfo.crm.so.util.BBossProcUtil.changeOVOrdOfferValueState(ovOrderOffers,aOrderValueChain);com.asiainfo.crm.so.util.BBossProcUtil.changeUserPreQuitState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
      else{
        ovOrderOffers=aOVOrderCustomer.getOVOrderOffers();com.asiainfo.crm.so.util.BBossProcUtil.changeUserState(ovOrderOffers);;
        ordOfferIds = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffers(ovOrderOffers,REGION_ID);
      }
    }
    else{logger.warn("If Offer Plan BbossConditions do not match");}
    return ordOfferIds;
  }
  public long[] execute(IOrdUserOsStateValue aOrdUserOsStateValue,long customerOrderId,long soOfferDataKey,String REGION_ID,long[] ordOfferIds) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOrdUserOsStateValue",aOrdUserOsStateValue);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("ordOfferIds",ordOfferIds);
try{
    long[] realReturn = executeInner( aOrdUserOsStateValue, customerOrderId, soOfferDataKey, REGION_ID, ordOfferIds);
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
    IOrdUserOsStateValue aOrdUserOsStateValue = ($vmParameters.get("aOrdUserOsStateValue") == null)?null:(IOrdUserOsStateValue)VMDataType.transfer($vmParameters.get("aOrdUserOsStateValue"),IOrdUserOsStateValue.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long[] ordOfferIds = ($vmParameters.get("ordOfferIds") == null)?null:(long[])VMDataType.transfer($vmParameters.get("ordOfferIds"),long[].class);
long[] realReturn = execute(aOrdUserOsStateValue,customerOrderId,soOfferDataKey,REGION_ID,ordOfferIds);
    $vmParameters.put("ordOfferIds",realReturn);
    return $vmParameters;
  }
}