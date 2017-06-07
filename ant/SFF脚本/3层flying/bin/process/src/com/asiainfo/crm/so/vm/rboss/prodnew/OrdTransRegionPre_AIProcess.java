package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  java.util.List;
public class OrdTransRegionPre_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransRegionPre_AIProcess.class);
  protected IOVOrderCustomer executeInner(long customerOrderId,IOVOrderOffer[] aOVOrderOffers,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	List ordUserIntransTrackList = new java.util.ArrayList();
    ordUserIntransTrackList=new java.util.ArrayList();;
    {//Call the sub-processesOrdQuitOfferMainForTrans
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = (((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoUserDatasOfMainOfferNewProd(customerOrderId));
      if(loopVar3 == null){ loopVar3 = new Object[0]; loopCount3 = 0;}
else if(loopVar3 instanceof java.util.List){loopCount3 = ((java.util.List)loopVar3).size();
}else if(loopVar3.getClass().isArray()){loopCount3 = ((Object[])loopVar3).length;
}      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("customerOrderId",new Long(customerOrderId));
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("ordUserIntransTrackList",ordUserIntransTrackList);
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodcancel.OrdQuitOfferMainForTrans",tmpMap3);
      aOVOrderCustomer = (tmpMap3.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap3.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processes省内携转携入处理
      Map tmpMap21 = new HashMap();
      Object loopVar21 = null;
      int loopCount21 = 0;
      loopVar21 = new Object[]{null};
      loopCount21 = 1;
      for(int i=0;i < loopCount21;i++){
      tmpMap21.clear();
      tmpMap21.put("customerOrderId",new Long(customerOrderId));
      tmpMap21.put("REGION_ID",REGION_ID);
      tmpMap21.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransferUser",tmpMap21);
      aOVOrderCustomer = (tmpMap21.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap21.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap21.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOrdxTransTrack(aOVOrderCustomer,ordUserIntransTrackList,REGION_ID);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).saveOfferOrderData(aOVOrderCustomer);
    {//Call the sub-processesOrdTransTackSave
      Map tmpMap35 = new HashMap();
      Object loopVar35 = null;
      int loopCount35 = 0;
      loopVar35 = new Object[]{null};
      loopCount35 = 1;
      for(int i=0;i < loopCount35;i++){
      tmpMap35.clear();
      tmpMap35.put("ordUserIntransTrackList",ordUserIntransTrackList);
      tmpMap35.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransTackSave",tmpMap35);

      tmpMap35.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long customerOrderId,IOVOrderOffer[] aOVOrderOffers,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderOffers",aOVOrderOffers);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( customerOrderId, aOVOrderOffers, REGION_ID);
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
    IOVOrderOffer[] aOVOrderOffers = ($vmParameters.get("aOVOrderOffers") == null)?null:(IOVOrderOffer[])VMDataType.transfer($vmParameters.get("aOVOrderOffers"),IOVOrderOffer[].class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(customerOrderId,aOVOrderOffers,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}