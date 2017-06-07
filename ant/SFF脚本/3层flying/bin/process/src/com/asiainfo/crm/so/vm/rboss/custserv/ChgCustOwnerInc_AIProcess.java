package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdOfferValue;
public class ChgCustOwnerInc_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ChgCustOwnerInc_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,long customerOrderId,String REGION_ID,long soOfferDataKey,long custId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
	IOrdUserValue aOrdUserValue = null;
	long offerOrderId = 0;
	String aBillId = "";
	IOrdOfferValue aOrdOfferValue = null;
    aOrderValueChain = new com.ai.omframe.order.valuebean.OrderValueChain(); aSoOfferData=(com.ai.omframe.order.data.ivalues.ISoOfferData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soOfferDataKey,customerOrderId); aSoUserData=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().createSoUserDataFromSoOfferData(soOfferDataKey,customerOrderId); aBillId="";;
    aSoOfferData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).modifySoOfferDataValidateDate(aSoOfferData);
    String decisionCond10 = String.valueOf(null!=aSoOfferData.getOperType() && aSoOfferData.getOperType().equals("UPDATE"));
    if(decisionCond10.equals("true")){
      aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
      aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
      ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
      aOrdOfferValue = aOVOrderOffer.getOrderOfferValue(); aOrdOfferValue.setCustId(custId);aOrdUserValue.setLastTransDate(aOrdUserValue.getDoneDate());;
    }
    else if(decisionCond10.equals("false")){
      aSoOfferData.setExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate());aSoUserData.setExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate());;
      {//Call the sub-processes退订增值策划
        Map tmpMap11 = new HashMap();
        Object loopVar11 = null;
        int loopCount11 = 0;
        loopVar11 = new Object[]{null};
        loopCount11 = 1;
        for(int i=0;i < loopCount11;i++){
        tmpMap11.clear();
        tmpMap11.put("aSoUserData",aSoUserData);
        tmpMap11.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap11.put("aSoOfferData",aSoOfferData);
        tmpMap11.put("aOrderValueChain",aOrderValueChain);
        tmpMap11.put("aBillId",aBillId);
        tmpMap11.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap11);
        aOVOrderOffer = (tmpMap11.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap11.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap11.clear();
        }      }

    }
    else{logger.warn("是否过户Conditions do not match");}
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,long customerOrderId,String REGION_ID,long soOfferDataKey,long custId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("custId",new Long(custId));
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, customerOrderId, REGION_ID, soOfferDataKey, custId);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    long custId = ($vmParameters.get("custId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("custId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,customerOrderId,REGION_ID,soOfferDataKey,custId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}