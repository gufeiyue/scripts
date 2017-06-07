package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdChgOfferUnit_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChgOfferUnit_AIProcess.class);
  protected IOVOrderCustomer executeInner(ISoOfferData aNewSoOfferData,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long aOfferInstId = 0;
	ISoOfferData aOldSoOfferData = null;
	ISoUserData aNewSoUserData = null;
	IOVOrderOffer aOldOVOrderOffer = null;
	IOVOrderOffer aNewOVOrderOffer = null;
	String aBillId = "";
	long oldDropBusinessId = 0;
	long newCreateBusinessId = 0;
	long oldOfferId = 0;
	long newOfferId = 0;
	ISoUserData aOldSoUserData = null;
    aOfferInstId=aNewSoOfferData.getInsOfferId();;
    aNewSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getMainRoleSoUserData(aNewSoOfferData);
    aOldSoOfferData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createSoOfferDataFromChgOffer(aNewSoOfferData,REGION_ID);
    aBillId=aNewSoUserData.getBillId();;
    java.sql.Timestamp validDate=aNewSoOfferData.getValidDate(); aOldSoOfferData.setExpireDate(validDate); aOldSoOfferData.setParentBceData(aNewSoOfferData.getSoOrderData()); aNewSoOfferData.getSoOrderData().addSubData(aOldSoOfferData);;
    oldOfferId=aOldSoOfferData.getOfferId(); newOfferId=aNewSoOfferData.getOfferId();;
    aOldSoOfferData.setBusinessId(aNewSoOfferData.getBusinessId());;
    aOldSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getMainRoleSoUserData(aOldSoOfferData);
    {//Call the sub-processes退订增值策划
      Map tmpMap4 = new HashMap();
      Object loopVar4 = null;
      int loopCount4 = 0;
      loopVar4 = new Object[]{null};
      loopCount4 = 1;
      for(int i=0;i < loopCount4;i++){
      tmpMap4.clear();
      tmpMap4.put("aSoUserData",aOldSoUserData);
      tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap4.put("aSoOfferData",aOldSoOfferData);
      tmpMap4.put("aOrderValueChain",aOrderValueChain);
      tmpMap4.put("aBillId",aBillId);
      tmpMap4.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap4);
      aOldOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap4.clear();
      }    }

    {//Call the sub-processes订购增值策划
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("aSoUserData",aNewSoUserData);
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("aSoOfferData",aNewSoOfferData);
      tmpMap3.put("aOrderValueChain",aOrderValueChain);
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewOffer",tmpMap3);
      aNewOVOrderOffer = (tmpMap3.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap3.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap3.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(ISoOfferData aNewSoOfferData,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aNewSoOfferData",aNewSoOfferData);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( aNewSoOfferData, aOVOrderCustomer, aOrderValueChain, REGION_ID);
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
    ISoOfferData aNewSoOfferData = ($vmParameters.get("aNewSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aNewSoOfferData"),ISoOfferData.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(aNewSoOfferData,aOVOrderCustomer,aOrderValueChain,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}