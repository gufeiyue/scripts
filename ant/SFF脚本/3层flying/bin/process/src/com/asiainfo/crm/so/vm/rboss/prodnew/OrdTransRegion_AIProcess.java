package com.asiainfo.crm.so.vm.rboss.prodnew;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  java.util.List;
public class OrdTransRegion_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransRegion_AIProcess.class);
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

    {//Call the sub-processes转实例
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = new Object[]{null};
      loopCount12 = 1;
      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap12.put("REGION_ID",REGION_ID);
      tmpMap12.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodnew.OrdTransInsForRegion",tmpMap12);
      aOVOrderCustomer = (tmpMap12.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap12.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap12.clear();
      }    }

    {//Call the sub-processes省内携转轨迹
      Map tmpMap32 = new HashMap();
      Object loopVar32 = null;
      int loopCount32 = 0;
      loopVar32 = new Object[]{null};
      loopCount32 = 1;
      for(int i=0;i < loopCount32;i++){
      tmpMap32.clear();
      tmpMap32.put("REGION_ID",REGION_ID);
      tmpMap32.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransTrack",tmpMap32);

      tmpMap32.clear();
      }    }

    logger.warn("Configure the node (其他数据转移)");
    logger.warn("Configure the node (修改客户地市)");
    logger.warn("Configure the node (修改账户地市)");
    logger.warn("Configure the node (修改cau)");
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
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