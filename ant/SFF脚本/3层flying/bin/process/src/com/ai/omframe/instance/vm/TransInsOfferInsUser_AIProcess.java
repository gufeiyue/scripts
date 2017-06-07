package com.ai.omframe.instance.vm;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstProcessBase;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdOffOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOrdPriceAttrValue;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
import  com.ai.omframe.order.ivalues.IOrdOfferValue;
import  com.ai.omframe.order.ivalues.IOrdOfferRelatValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
public class TransInsOfferInsUser_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(TransInsOfferInsUser_AIProcess.class);
  protected void executeInner(IOVOrderOffer aOVOrderOffer,long offerInsId,IOrdOfferValue aOrdOfferValue,IOrdUserOsStateValue aOrdUserOsStateValue,String REGION_ID) throws Exception{
	int aIndex = 0;
	IOrdOffOrdUserValue aOrdOffOrdUserValue = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	boolean _TASK_JUGE_RESULT = false;
	IOrdPriceAttrValue aOrdPriceAttrValue = null;
	IOrdAccrelValue aOrdAccrelValue = null;
	IOrdOfferRelatValue aOrdOfferRelateValue = null;
	int bIndex = 0;
	String USER_REGION_ID = "";
    aOrdOfferRelateValue=aOVOrderOffer.getOrdOfferRelatValue();;
    while(null!=aOVOrderOffer.getOfferPriceAttrs()&&aIndex<aOVOrderOffer.getOfferPriceAttrs().length){
      aOrdPriceAttrValue=aOVOrderOffer.getOfferPriceAttrs()[aIndex];
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstPriceAttrByOffer(aOrdPriceAttrValue,aOrdOfferValue,REGION_ID);
      aIndex++;
    }
    aIndex=0;
    while(null!=aOVOrderOffer.getOVOrdOffOrdUser()&&aIndex<aOVOrderOffer.getOVOrdOffOrdUser().length){
      aOVOrdOffOrdUser=aOVOrderOffer.getOVOrdOffOrdUser()[aIndex]; aOrdOffOrdUserValue=aOVOrderOffer.getOVOrdOffOrdUser()[aIndex].getOrdOffOrdUserValue(); aOrdUserValue=aOVOrderOffer.getOVOrdOffOrdUser()[aIndex].getOrdUserValues()[0]; USER_REGION_ID=aOrdUserValue.getUserRegionId(); if(aOVOrdOffOrdUser.getOrdUserOsStateValues()!=null&&aOVOrdOffOrdUser.getOrdUserOsStateValues().length>0){aOrdUserOsStateValue=aOVOrdOffOrdUser.getOrdUserOsStateValues()[0];};
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstOffInsProd(aOrdOffOrdUserValue,offerInsId,REGION_ID);
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstBusiLimit(aOVOrdOffOrdUser,offerInsId,REGION_ID);
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstUsers(aOVOrdOffOrdUser,aOVOrderOffer);
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstCommitment(aOVOrdOffOrdUser,offerInsId,REGION_ID);
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstUserExts(aOVOrdOffOrdUser,REGION_ID);
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstUserOsState(aOrdUserOsStateValue,REGION_ID);
      while(null!=aOVOrderOffer.getOVOrdOffOrdUser()[aIndex].getOrdAccrelValues()&& bIndex<aOVOrderOffer.getOVOrdOffOrdUser()[aIndex].getOrdAccrelValues().length){
        aOrdAccrelValue=aOVOrderOffer.getOVOrdOffOrdUser()[aIndex].getOrdAccrelValues()[bIndex];
        ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInsAccrel(aOrdAccrelValue,offerInsId,REGION_ID);
        bIndex++;
      }
      {//Call the sub-processesTransInsSrv
        Map tmpMap9 = new HashMap();
        Object loopVar9 = null;
        int loopCount9 = 0;
        loopVar9 = new Object[]{null};
        loopCount9 = 1;
        for(int i=0;i < loopCount9;i++){
        tmpMap9.clear();
        tmpMap9.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
        tmpMap9.put("offerInsId",new Long(offerInsId));
        tmpMap9.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.instance.vm.TransInsSrv",tmpMap9);

        tmpMap9.clear();
        }      }

      aIndex++;
    }
    ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstOfferRelate(aOrdOfferRelateValue,REGION_ID);
  }
  public void execute(IOVOrderOffer aOVOrderOffer,long offerInsId,IOrdOfferValue aOrdOfferValue,IOrdUserOsStateValue aOrdUserOsStateValue,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderOffer",aOVOrderOffer);
    $inParameter.put("offerInsId",new Long(offerInsId));
    $inParameter.put("aOrdOfferValue",aOrdOfferValue);
    $inParameter.put("aOrdUserOsStateValue",aOrdUserOsStateValue);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aOVOrderOffer, offerInsId, aOrdOfferValue, aOrdUserOsStateValue, REGION_ID);
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
    IOVOrderOffer aOVOrderOffer = ($vmParameters.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer($vmParameters.get("aOVOrderOffer"),IOVOrderOffer.class);
    long offerInsId = ($vmParameters.get("offerInsId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerInsId"),long.class)).longValue();
    IOrdOfferValue aOrdOfferValue = ($vmParameters.get("aOrdOfferValue") == null)?null:(IOrdOfferValue)VMDataType.transfer($vmParameters.get("aOrdOfferValue"),IOrdOfferValue.class);
    IOrdUserOsStateValue aOrdUserOsStateValue = ($vmParameters.get("aOrdUserOsStateValue") == null)?null:(IOrdUserOsStateValue)VMDataType.transfer($vmParameters.get("aOrdUserOsStateValue"),IOrdUserOsStateValue.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aOVOrderOffer,offerInsId,aOrdOfferValue,aOrdUserOsStateValue,REGION_ID);
  return $vmParameters;
  }
}