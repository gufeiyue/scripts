package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
public class ChgCustOwner_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(ChgCustOwner_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long soUserDataKey,boolean isAppoint,boolean isChangeOffer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long custId = 0;
	long userId = 0;
	ISoUserData aSoUserData = null;
	long acctId = 0;
	ISoOrderData aSoOrderData = null;
	ISoOfferData aSoOfferData = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); userId=((com.ai.omframe.order.ivalues.IOrdCustValue)aOVOrderCustomer.getOrderCustomerValue()).getUserId();aSoOrderData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);;
    isAppoint = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV.class)).isAppoint(customerOrderId);
    String decisionCond44 = String.valueOf(isAppoint);
    if(decisionCond44.equals("true")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV.class)).saveAppoint(customerOrderId);
    }
    else if(decisionCond44.equals("false")){
      custId=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().createCustAndContact(customerOrderId,userId,REGION_ID);;
      acctId = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).createAcctAndContact(customerOrderId,custId);
      isChangeOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV.class)).isChangeOffer(customerOrderId);
      String decisionCond50 = String.valueOf(isChangeOffer);
      if(decisionCond50.equals("true")){
        {//Call the sub-processes过户处理策划
          Map tmpMap49 = new HashMap();
          Object loopVar49 = null;
          int loopCount49 = 0;
          loopVar49 = new Object[]{null};
          loopCount49 = 1;
          for(int i=0;i < loopCount49;i++){
          tmpMap49.clear();
          tmpMap49.put("customerOrderId",new Long(customerOrderId));
          tmpMap49.put("REGION_ID",REGION_ID);
          tmpMap49.put("custId",new Long(custId));
          tmpMap49.put("aOVOrderCustomer",aOVOrderCustomer);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.ChgCustOwerOffer",tmpMap49);

          tmpMap49.clear();
          }        }

      }
      else if(decisionCond50.equals("false")){
        ((com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV.class)).createAndInitOrdOffer(aSoOrderData);
        {//Call the sub-processesChgCustOwnerBase
          Map tmpMap51 = new HashMap();
          Object loopVar51 = null;
          int loopCount51 = 0;
          loopVar51 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoOfferDatasByOfferType(customerOrderId,true));
          if(loopVar51 == null){ loopVar51 = new Object[0]; loopCount51 = 0;}
else if(loopVar51 instanceof java.util.List){loopCount51 = ((java.util.List)loopVar51).size();
}else if(loopVar51.getClass().isArray()){loopCount51 = ((Object[])loopVar51).length;
}          for(int i=0;i < loopCount51;i++){
          tmpMap51.clear();
          tmpMap51.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap51.put("customerOrderId",new Long(customerOrderId));
          tmpMap51.put("REGION_ID",REGION_ID);
          tmpMap51.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar51,i),"soOfferDataKey"));
          tmpMap51.put("custId",new Long(custId));
          tmpMap51.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.ChgCustOwnerBase",tmpMap51);
          aOVOrderCustomer = (tmpMap51.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap51.get("aOVOrderCustomer"),IOVOrderCustomer.class);

          tmpMap51.clear();
          }        }

        {//Call the sub-processesChgCustOwnerInc
          Map tmpMap52 = new HashMap();
          Object loopVar52 = null;
          int loopCount52 = 0;
          loopVar52 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoOfferDatasByOfferType(customerOrderId,false));
          if(loopVar52 == null){ loopVar52 = new Object[0]; loopCount52 = 0;}
else if(loopVar52 instanceof java.util.List){loopCount52 = ((java.util.List)loopVar52).size();
}else if(loopVar52.getClass().isArray()){loopCount52 = ((Object[])loopVar52).length;
}          for(int i=0;i < loopCount52;i++){
          tmpMap52.clear();
          tmpMap52.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap52.put("customerOrderId",new Long(customerOrderId));
          tmpMap52.put("REGION_ID",REGION_ID);
          tmpMap52.put("soOfferDataKey",com.ai.comframe.vm.common.VMUtil.getObjectProperty(com.ai.comframe.vm.common.VMUtil.getObjectByIndex(loopVar52,i),"soOfferDataKey"));
          tmpMap52.put("custId",new Long(custId));
          tmpMap52.put("FLOWOBJECT_ID",new Long(customerOrderId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.ChgCustOwnerInc",tmpMap52);
          aOVOrderCustomer = (tmpMap52.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap52.get("aOVOrderCustomer"),IOVOrderCustomer.class);

          tmpMap52.clear();
          }        }

      }
      else{logger.warn("判断是否换套餐Conditions do not match");}
      {//Call the sub-processesOrdCustPriceAdd
        Map tmpMap48 = new HashMap();
        Object loopVar48 = null;
        int loopCount48 = 0;
        loopVar48 = new Object[]{null};
        loopCount48 = 1;
        for(int i=0;i < loopCount48;i++){
        tmpMap48.clear();
        tmpMap48.put("aSoOrderData",aSoOrderData);
        tmpMap48.put("customerOrderId",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdCustPriceAdd",tmpMap48);

        tmpMap48.clear();
        }      }

      aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).setUserPWD(aOVOrderCustomer);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).reSetCreditCtrlFlag(userId);
      aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).dealFamilyHostForTranCust(aOVOrderCustomer,userId,custId,acctId,REGION_ID);
      aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).resetAcctState(aOVOrderCustomer);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).sendOrderInfo2Billing(aOVOrderCustomer,userId,custId,acctId,REGION_ID);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
      {//Call the sub-processes转实例
        Map tmpMap19 = new HashMap();
        Object loopVar19 = null;
        int loopCount19 = 0;
        loopVar19 = new Object[]{null};
        loopCount19 = 1;
        for(int i=0;i < loopCount19;i++){
        tmpMap19.clear();
        tmpMap19.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap19.put("REGION_ID",REGION_ID);
        tmpMap19.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap19);
        aOVOrderCustomer = (tmpMap19.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap19.get("aOVOrderCustomer"),IOVOrderCustomer.class);

        tmpMap19.clear();
        }      }

      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).dealZhangQiForTrans(userId,customerOrderId);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IChgCustUserSV.class)).sendPwdResetSmsForTrans(userId);
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IChgOwnerShipSV.class)).changeMainOfferForTransfer(customerOrderId);
    }
    else{logger.warn("判断是否是预约Conditions do not match");}
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long soUserDataKey,boolean isAppoint,boolean isChangeOffer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("isAppoint",new Boolean(isAppoint));
    $inParameter.put("isChangeOffer",new Boolean(isChangeOffer));
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, REGION_ID, aOVOrderCustomer, soUserDataKey, isAppoint, isChangeOffer);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    boolean isAppoint = ($vmParameters.get("isAppoint") == null)?false:((Boolean)VMDataType.transfer($vmParameters.get("isAppoint"),boolean.class)).booleanValue();
    boolean isChangeOffer = ($vmParameters.get("isChangeOffer") == null)?false:((Boolean)VMDataType.transfer($vmParameters.get("isChangeOffer"),boolean.class)).booleanValue();
IOVOrderCustomer realReturn = execute(customerOrderId,REGION_ID,aOVOrderCustomer,soUserDataKey,isAppoint,isChangeOffer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}