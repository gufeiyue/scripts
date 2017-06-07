package com.asiainfo.crm.so.vm.bboss.accrelSplit;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  java.util.List;
import  com.ai.appframe2.common.DataContainerInterface;
public class OrdChangeAccrelBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChangeAccrelBusi_AIProcess.class);
  protected IOVOrderOffer executeInner(String REGION_ID,String soUserDataKey,long customerOrderId,List itemGrp,DataContainerInterface accRel) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOrdUserValue aOrdUserValue = null;
	long businessId = 0;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	Map userDCMap = new java.util.HashMap();
	ISoOrderData aSoOrderData = null;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData = com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoOrderDataByCustOrdId(customerOrderId);businessId = aSoOrderData.getMainBusinessId(); aOrderValueChain = new com.ai.omframe.order.valuebean.OrderValueChain(); aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE); aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_OLD_STATE);;
    aSoUserData = com.asiainfo.crm.so.util.BbossSoOrderDataUtil.getSoUserData(customerOrderId,soUserDataKey); aSoOfferData = aSoUserData.getSoRoleData().getSoOfferData(); aSoOfferData.setParentBceData(aSoOrderData);aSoOfferData.setBusinessId(businessId);
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInstByMainUserRegion(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    aOrdUserValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
    ((com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV",com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV.class)).createOrdAccrelValue(aOVOrdOffOrdUser,itemGrp,accRel,REGION_ID);
    return aOVOrderOffer;
  }
  public IOVOrderOffer execute(String REGION_ID,String soUserDataKey,long customerOrderId,List itemGrp,DataContainerInterface accRel) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soUserDataKey",soUserDataKey);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("itemGrp",itemGrp);
    $inParameter.put("accRel",accRel);
try{
    IOVOrderOffer realReturn = executeInner( REGION_ID, soUserDataKey, customerOrderId, itemGrp, accRel);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    String soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?"":(String)VMDataType.transfer($vmParameters.get("soUserDataKey"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    List itemGrp = ($vmParameters.get("itemGrp") == null)?new java.util.ArrayList():(List)VMDataType.transfer($vmParameters.get("itemGrp"),List.class);
    DataContainerInterface accRel = ($vmParameters.get("accRel") == null)?null:(DataContainerInterface)VMDataType.transfer($vmParameters.get("accRel"),DataContainerInterface.class);
IOVOrderOffer realReturn = execute(REGION_ID,soUserDataKey,customerOrderId,itemGrp,accRel);
    $vmParameters.put("aOVOrderOffer",realReturn);
    return $vmParameters;
  }
}