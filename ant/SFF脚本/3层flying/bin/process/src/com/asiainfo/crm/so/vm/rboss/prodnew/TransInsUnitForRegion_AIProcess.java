package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstProcessBase;
import  com.ai.omframe.order.ivalues.IOrdOfferValue;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class TransInsUnitForRegion_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(TransInsUnitForRegion_AIProcess.class);
  protected void executeInner(IOVOrderOffer aIterOVOrderOffer,long orderOfferId,String REGION_ID) throws Exception{
	int insIndex = 0;
	IOrdOfferValue aOrdOfferValue = null;
	IOVOrderOffer aOVOrderOffer = null;
	long offerInsId = 0;
	boolean _TASK_JUGE_RESULT = false;
    aOVOrderOffer=aIterOVOrderOffer;;
    REGION_ID=aIterOVOrderOffer.getOrderOfferValue().getRegionId();;
    aOrdOfferValue=aOVOrderOffer.getOrderOfferValue();
    ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstOffer(aOrdOfferValue,REGION_ID);
    offerInsId=aOrdOfferValue.getOfferInstId();
    {//Call the sub-processesTransInsOfferInsUser
      Map tmpMap7 = new HashMap();
      Object loopVar7 = null;
      int loopCount7 = 0;
      loopVar7 = new Object[]{null};
      loopCount7 = 1;
      for(int i=0;i < loopCount7;i++){
      tmpMap7.clear();
      tmpMap7.put("aOVOrderOffer",aOVOrderOffer);
      tmpMap7.put("offerInsId",new Long(offerInsId));
      tmpMap7.put("aOrdOfferValue",aOrdOfferValue);
      tmpMap7.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsOfferInsUser",tmpMap7);

      tmpMap7.clear();
      }    }

  }
  public void execute(IOVOrderOffer aIterOVOrderOffer,long orderOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aIterOVOrderOffer",aIterOVOrderOffer);
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aIterOVOrderOffer, orderOfferId, REGION_ID);
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
    IOVOrderOffer aIterOVOrderOffer = ($vmParameters.get("aIterOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer($vmParameters.get("aIterOVOrderOffer"),IOVOrderOffer.class);
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aIterOVOrderOffer,orderOfferId,REGION_ID);
  return $vmParameters;
  }
}