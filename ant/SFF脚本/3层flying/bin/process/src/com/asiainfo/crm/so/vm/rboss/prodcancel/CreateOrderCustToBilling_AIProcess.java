package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.instance.ivalues.IInsOfferValue;
import  java.sql.Timestamp;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class CreateOrderCustToBilling_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(CreateOrderCustToBilling_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOvOrderCustToBilling) throws Exception{
	IOVOrderOffer[] sOVOrderOffers = null;
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOrderData aSoOrderData = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	int offerIndex = 0;
	IInsOfferValue[] sInsOffers = null;
	IInsOfferValue aInsOffer = null;
	Timestamp aCurDateTime = null;
	IOrdAccrelValue aOrdAccRelValue = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
    aOvOrderCustToBilling=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId); sInsOffers=com.ai.omframe.util.InsServiceFactory.getIInstOffer().getInstOfferByUserId(aSoOrderData.getMainUserId(),-1, null, -99); if(sInsOffers!=null){sOVOrderOffers= new com.ai.omframe.order.ivalues.IOVOrderOffer[sInsOffers.length];} java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOrderData.getMainInsOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOrderData.getMainBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map); aCurDateTime=com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate(); int offerPlanCount = 0; for(int i=0;sInsOffers!=null&&i<sInsOffers.length;i++){ if("OFFER_PLAN_GSM".equals(sInsOffers[i].getOfferType())){ offerPlanCount++; } } if(offerPlanCount!=1){ };
    while(sInsOffers!=null&&offerIndex<sInsOffers.length){
      aInsOffer=sInsOffers[offerIndex]; long tmpOfferInstId = aInsOffer.getOfferInstId(); aSoOfferData=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper ().createSoOfferDataByOfferInstIdIngoreValidType(tmpOfferInstId,REGION_ID); aSoOfferData.setBusinessId(aSoOrderData.getMainBusinessId()); aSoOfferData.setParentBceData(aSoOrderData); aSoUserData = aSoOfferData.getSoRoleData() [0].getSoUserData()[0]; map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf (tmpOfferInstId)); map.put("BUSINESS_ID",String.valueOf(aSoOrderData.getMainBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain (aOrderValueChain,map); aOrderValueChain.setVALID_TYPE_BUSI_PRICE(-99); aOrderValueChain.setVALID_TYPE_OFFER(-99); aOrderValueChain.setVALID_TYPE_OFFER_USER(-99); aOrderValueChain.setVALID_TYPE_PRICE_ATTR_OFFER(-99); aOrderValueChain.setVALID_TYPE_PRICE_ATTR_SRVPKG(-99); aOrderValueChain.setVALID_TYPE_SRV_ATTR(-99); aOrderValueChain.setVALID_TYPE_SRVPKG(-99); aOrderValueChain.setVALID_TYPE_SRVPKG_SRV(-99); aOrderValueChain.setVALID_TYPE_USER(- 99);aOrderValueChain.setSTATE_USER(3);;
      {//Call the sub-processes修改策划
        Map tmpMap5 = new HashMap();
        Object loopVar5 = null;
        int loopCount5 = 0;
        loopVar5 = new Object[]{null};
        loopCount5 = 1;
        for(int i=0;i < loopCount5;i++){
        tmpMap5.clear();
        tmpMap5.put("aSoOfferData",aSoOfferData);
        tmpMap5.put("aSoUserData",aSoUserData);
        tmpMap5.put("aOrderValueChain",aOrderValueChain);
        tmpMap5.put("aOVOrderCustomer",aOvOrderCustToBilling);
        tmpMap5.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdModiOffer",tmpMap5);
        aOVOrderOffer = (tmpMap5.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap5.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap5.clear();
        }      }

      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
      aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
      {//Call the sub-processes修改多个产品
        Map tmpMap8 = new HashMap();
        Object loopVar8 = null;
        int loopCount8 = 0;
        loopVar8 = new Object[]{null};
        loopCount8 = 1;
        for(int i=0;i < loopCount8;i++){
        tmpMap8.clear();
        tmpMap8.put("aSoUserData",aSoUserData);
        tmpMap8.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
        tmpMap8.put("aOrderValueChain",aOrderValueChain);
        tmpMap8.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdModiBatchSrvpkgs",tmpMap8);

        tmpMap8.clear();
        }      }

      String decisionCond19 = String.valueOf(offerIndex==0);
      if(decisionCond19.equals("true")){
        aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValue(aOrdUserValue,REGION_ID);
        aOVOrdOffOrdUser.addOrdUserOsStateValue(aOrdUserOsStateValue);;
        aOrdAccRelValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueFromIns(aOVOrderOffer,aSoUserData,aOrdUserValue,aOrderValueChain,REGION_ID);
      }
      else if(decisionCond19.equals("false")){
      }
      else{logger.warn("判断是否为第一个策划Conditions do not match");}
      sOVOrderOffers[offerIndex]=aOVOrderOffer; offerIndex++;;
    }
    return aOvOrderCustToBilling;
  }
  public IOVOrderCustomer execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOvOrderCustToBilling) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOvOrderCustToBilling",aOvOrderCustToBilling);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, REGION_ID, aOvOrderCustToBilling);
    $returnParameter.put("aOvOrderCustToBilling",realReturn);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOvOrderCustToBilling = ($vmParameters.get("aOvOrderCustToBilling") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOvOrderCustToBilling"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(customerOrderId,REGION_ID,aOvOrderCustToBilling);
    $vmParameters.put("aOvOrderCustToBilling",realReturn);
    return $vmParameters;
  }
}