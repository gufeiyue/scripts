package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class OrdRelocationNew_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdRelocationNew_AIProcess.class);
  protected void executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,ISoOrderData aSoOrderData,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	ISoUserData aSoUserData = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	long aMainInsOfferId = 0;
    String decisionCond27 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).relocaDecision(customerOrderId));
    if(decisionCond27.equals("true")){
      aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).invokeChangeDispatch(customerOrderId);
    }
    else if(decisionCond27.equals("false")){
      aSoOfferData=com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().createSoOfferDataFromOfferInstId(aSoOrderData.getMainInsOfferId(),REGION_ID); aSoOfferData.setBusinessId(aSoOrderData.getMainBusinessId()); aSoOfferData.setParentBceData(aSoOrderData); aSoUserData = aSoOfferData.getSoRoleData()[0].getSoUserData()[0]; java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOrderData.getMainInsOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOrderData.getMainBusinessId()));aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);aMainInsOfferId=aSoOrderData.getMainInsOfferId();
      aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE); aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).setOfferInstStateLocked(aMainInsOfferId,true);
      aSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).modifySoUserData4BusiPrice(customerOrderId,aSoUserData,REGION_ID);
      aSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateSoUserData(aSoUserData,customerOrderId,REGION_ID,true);
      aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
      aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
      aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
      ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserExtValue(aOVOrdOffOrdUser,aSoUserData,REGION_ID,true);
      {//Call the sub-processes创建服务订单
        Map tmpMap30 = new HashMap();
        Object loopVar30 = null;
        int loopCount30 = 0;
        loopVar30 = new Object[]{null};
        loopCount30 = 1;
        for(int i=0;i < loopCount30;i++){
        tmpMap30.clear();
        tmpMap30.put("aSoUserData",aSoUserData);
        tmpMap30.put("aOrderValueChain",aOrderValueChain);
        tmpMap30.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
        tmpMap30.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewBatchSrvpkgs",tmpMap30);

        tmpMap30.clear();
        }      }

      {//Call the sub-processes新建营业费用
        Map tmpMap20 = new HashMap();
        Object loopVar20 = null;
        int loopCount20 = 0;
        loopVar20 = new Object[]{null};
        loopCount20 = 1;
        for(int i=0;i < loopCount20;i++){
        tmpMap20.clear();
        tmpMap20.put("aSoUserData",aSoUserData);
        tmpMap20.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
        tmpMap20.put("aOrderValueChain",aOrderValueChain);
        tmpMap20.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdOfferUserPrice",tmpMap20);

        tmpMap20.clear();
        }      }

    }
    else{logger.warn("自动判断Conditions do not match");}
    aOVOrderOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateUserInfo4Relocation(aOVOrderCustomer,aOVOrderOffer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).saveOfferOrders(aOVOrderCustomer,REGION_ID);
  }
  public void execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,ISoOrderData aSoOrderData,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, aOVOrderCustomer, aSoOrderData, REGION_ID);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,aOVOrderCustomer,aSoOrderData,REGION_ID);
  return $vmParameters;
  }
}