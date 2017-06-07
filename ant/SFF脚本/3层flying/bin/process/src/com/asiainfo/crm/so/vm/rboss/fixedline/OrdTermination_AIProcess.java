package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOfferCreater;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  java.sql.Timestamp;
public class OrdTermination_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTermination_AIProcess.class);
  protected void executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,ISoOrderData aSoOrderData,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	ISoUserData aSoUserData = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	Timestamp aCurDateTime = null;
	long aOfferInstId = 0;
	long aUserId = 0;
    aSoOfferData=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().createSoOfferDataByOfferInstIdIngoreValidType(aSoOrderData.getMainInsOfferId(),REGION_ID); aSoOfferData.setBusinessId(aSoOrderData.getMainBusinessId()); aSoOfferData.setParentBceData(aSoOrderData); aSoUserData = aSoOfferData.getSoRoleData()[0].getSoUserData()[0]; java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOrderData.getMainInsOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOrderData.getMainBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map); aCurDateTime=com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate();aUserId=aSoOrderData.getMainUserId();aOfferInstId=aSoOrderData.getMainInsOfferId();;
    aOrderValueChain = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).initOrderValueChain4Remove(aSoOfferData,aOrderValueChain);
    aSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).modifySoUserData4BusiPrice(customerOrderId,aSoUserData,REGION_ID);
    aOVOrderOffer = ((com.ai.omframe.order.service.interfaces.IOrdOfferCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOfferCreater",com.ai.omframe.order.service.interfaces.IOrdOfferCreater.class)).createOVOrderOfferFromInst(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    aOrdUserValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).createOrdUserValueFromInst(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    aOrdUserValue.setInsState(com.ai.omframe.order.valuebean.OrderConst.INST_USER_STATE_TRADE_PRE_LOGOUT); aOrdUserValue.setPreDestoryTime(aCurDateTime);;
    aOVOrdOffOrdUser = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOVOrderOfferUserFromInst(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrderValueChain,REGION_ID);
    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserExtValue(aOVOrdOffOrdUser,aSoUserData,REGION_ID,true);
    aOrdUserOsStateValue = ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdUserOsStateValue(aOrdUserValue);
    aOrdUserOsStateValue.setOsStatus(com.asiainfo.crm.so.util.RbossServiceFactory.getStopOpenOsStatusSV().setOsStatus(aOrdUserOsStateValue.getOsStatus(),8,"1")); aOVOrdOffOrdUser.addOrdUserOsStateValue(aOrdUserOsStateValue);;
    com.ai.omframe.instance.ivalues.IInsAccrelValue[] insAccRelValues=com.ai.omframe.util.InsServiceFactory.getInstanceAccrelService().getInstAccrelByUserId(aSoUserData.getUserId(),com.ai.omframe.order.valuebean.OrderConst.ACCT_BASE_PAY_TYPE); if(insAccRelValues!=null&&insAccRelValues.length>0){ com.ai.appframe2.common.DataContainerInterface[] acctDCs = new com.ai.appframe2.bo.DataContainer[1]; com.ai.appframe2.common.DataContainerInterface acctDc = new com.ai.appframe2.bo.DataContainer(); acctDc.set("ACCT_ID",String.valueOf(insAccRelValues[0].getAcctId())); acctDCs[0] = acctDc; aSoUserData.setAcctInfoDC(acctDCs); };
    ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueFromIns(aOVOrderOffer,aSoUserData,aOrdUserValue,aOrderValueChain,REGION_ID);
    {//Call the sub-processes新建营业费用
      Map tmpMap21 = new HashMap();
      Object loopVar21 = null;
      int loopCount21 = 0;
      loopVar21 = new Object[]{null};
      loopCount21 = 1;
      for(int i=0;i < loopCount21;i++){
      tmpMap21.clear();
      tmpMap21.put("aSoUserData",aSoUserData);
      tmpMap21.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap21.put("aOrderValueChain",aOrderValueChain);
      tmpMap21.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdOfferUserPrice",tmpMap21);

      tmpMap21.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateInsxUserRelatState(aUserId);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
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