package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdDelNextOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelNextOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	ISoOfferData aOldSoOfferData = null;
	ISoUserData aOldSoUserData = null;
	String aBillId = "";
    aOldSoOfferData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createSoOfferDataFromCancelNextOfferIns(aSoOfferData,REGION_ID);
    aOldSoOfferData.setParentBceData(aSoOfferData.getSoOrderData());aOldSoOfferData.setBusinessId(aSoOfferData.getBusinessId());;
    aOldSoUserData=aOldSoOfferData.getSoRoleData()[0].getSoUserData()[0];;
    aBillId=aOldSoUserData.getBillId();;
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
      tmpMap4.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap4);
      aOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap4.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,ISoOfferData aSoOfferData,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, aSoOfferData, aOrderValueChain, REGION_ID);
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
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,aSoOfferData,aOrderValueChain,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}