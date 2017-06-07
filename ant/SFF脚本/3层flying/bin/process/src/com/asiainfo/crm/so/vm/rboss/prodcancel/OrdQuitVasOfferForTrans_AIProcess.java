package com.asiainfo.crm.so.vm.rboss.prodcancel;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdOfferValue;
public class OrdQuitVasOfferForTrans_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdQuitVasOfferForTrans_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,long customerOrderId,String REGION_ID,long soOfferDataKey) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
	IOrdUserValue aOrdUserValue = null;
	long offerOrderId = 0;
	String aBillId = "";
	IOrdOfferValue aOrdOfferValue = null;
    aOrderValueChain = new com.ai.omframe.order.valuebean.OrderValueChain(); aSoOfferData= com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().createSoOfferDataFromOfferInstId(soOfferDataKey,REGION_ID); aSoOfferData.setBusinessId(com.asiainfo.crm.so.common.RBossConst.BUSI_ID_DEL_INC_OFFER); aSoOfferData.setExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate()); aSoUserData = aSoOfferData.getSoRoleData()[0].getSoUserData()[0]; aSoUserData.setExpireDate(com.ai.appframe2.common.ServiceManager.getIdGenerator().getSysDate()); aBillId="";;
    {//Call the sub-processesOrdQuitVasOffDtlForTrans
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = new Object[]{null};
      loopCount12 = 1;
      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("aSoUserData",aSoUserData);
      tmpMap12.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap12.put("aSoOfferData",aSoOfferData);
      tmpMap12.put("aOrderValueChain",aOrderValueChain);
      tmpMap12.put("aBillId",aBillId);
      tmpMap12.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodcancel.OrdQuitVasOffDtlForTrans",tmpMap12);
      aOVOrderOffer = (tmpMap12.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap12.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap12.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,long customerOrderId,String REGION_ID,long soOfferDataKey) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, customerOrderId, REGION_ID, soOfferDataKey);
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
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,customerOrderId,REGION_ID,soOfferDataKey);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}