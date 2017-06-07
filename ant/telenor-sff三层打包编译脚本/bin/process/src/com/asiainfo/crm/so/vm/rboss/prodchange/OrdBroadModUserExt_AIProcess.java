package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdBroadModUserExt_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdBroadModUserExt_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer[] OVOrderOffers = null;
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	long orderOfferId = 0;
	boolean isNeedFalg = false;
	String userId = "0";
    OVOrderOffers=aOVOrderCustomer.getOVOrderOffers();userId = String.valueOf(((com.ai.omframe.order.ivalues.IOrdCustValue)aOVOrderCustomer.getOrderCustomerValue()).getUserId());;
    isNeedFalg = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyBusiSV.class)).isNeedCreateUserExtOrder(customerOrderId,userId);
    String decisionCond18 = String.valueOf(isNeedFalg);
    if(decisionCond18.equals("false")){
    }
    else if(decisionCond18.equals("true")){
      aSoOfferData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createSoOfferDataByOfferIns(customerOrderId,REGION_ID);
      aSoUserData=((com.ai.omframe.order.data.ivalues.ISoRoleData)aSoOfferData.getSoRoleData()[0]).getSoUserData()[0];java.util.Map map=new java.util.HashMap();map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId()));map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId()));aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
      {//Call the sub-processes用户订购变更
        Map tmpMap13 = new HashMap();
        Object loopVar13 = null;
        int loopCount13 = 0;
        loopVar13 = new Object[]{null};
        loopCount13 = 1;
        for(int i=0;i < loopCount13;i++){
        tmpMap13.clear();
        tmpMap13.put("aSoUserData",aSoUserData);
        tmpMap13.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap13.put("aSoOfferData",aSoOfferData);
        tmpMap13.put("aOrderValueChain",aOrderValueChain);
        tmpMap13.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdModiUserOffer",tmpMap13);
        aOVOrderOffer = (tmpMap13.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap13.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap13.clear();
        }      }

      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOfferExtendSV.class)).createOrdUserExtValue(aOVOrderOffer,aSoUserData,REGION_ID,true);
    }
    else{logger.warn("条件判断Conditions do not match");}
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, REGION_ID, aOVOrderCustomer);
    $returnParameter.put("aOVOrderCustomer",realReturn);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(customerOrderId,REGION_ID,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}