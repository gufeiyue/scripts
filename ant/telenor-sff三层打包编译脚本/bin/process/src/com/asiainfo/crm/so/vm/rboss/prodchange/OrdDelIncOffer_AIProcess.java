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
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdDelIncOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelIncOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(long soOfferDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	ISoOfferData aOperSoOfferData = null;
	String aOperType = "";
	ISoOfferData[] aSoOfferDatas = null;
	int aOfferIdx = 0;
    aOperSoOfferData=(com.ai.omframe.order.data.ivalues.ISoOfferData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soOfferDataKey,aOVOrderCustomer.getOrderCustomerValue().getCustomerOrderId()); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aOperSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aOperSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
    aSoOfferDatas = (com.ai.omframe.order.data.ivalues.ISoOfferData[])VMDataType.transfer(((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createSoOfferDatasFromOfferIns(aOperSoOfferData,REGION_ID),com.ai.omframe.order.data.ivalues.ISoOfferData[].class);
    while(aSoOfferDatas!=null&&aOfferIdx<aSoOfferDatas.length){
      aSoOfferData=aSoOfferDatas[aOfferIdx];aOperType=aSoOfferData.getOperType();;
      aSoOfferData.setParentBceData(aOperSoOfferData.getSoOrderData());aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];;
      String decisionCond13 = String.valueOf(aOperType!=null&&"UPDATE".equals(aOperType));
      if(decisionCond13.equals("true")){
        {//Call the sub-processes修改策划
          Map tmpMap14 = new HashMap();
          Object loopVar14 = null;
          int loopCount14 = 0;
          loopVar14 = new Object[]{null};
          loopCount14 = 1;
          for(int i=0;i < loopCount14;i++){
          tmpMap14.clear();
          tmpMap14.put("aSoUserData",aSoUserData);
          tmpMap14.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap14.put("aSoOfferData",aSoOfferData);
          tmpMap14.put("aOrderValueChain",aOrderValueChain);
          tmpMap14.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdModiOffer",tmpMap14);
          aOVOrderOffer = (tmpMap14.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap14.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap14.clear();
          }        }

      }
      else if(decisionCond13.equals("false")){
        {//Call the sub-processes退订增值策划
          Map tmpMap4 = new HashMap();
          Object loopVar4 = null;
          int loopCount4 = 0;
          loopVar4 = new Object[]{null};
          loopCount4 = 1;
          for(int i=0;i < loopCount4;i++){
          tmpMap4.clear();
          tmpMap4.put("aSoUserData",aSoUserData);
          tmpMap4.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap4.put("aSoOfferData",aSoOfferData);
          tmpMap4.put("aOrderValueChain",aOrderValueChain);
          tmpMap4.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap4);
          aOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap4.clear();
          }        }

      }
      else{logger.warn("条件判断Conditions do not match");}
      aOfferIdx++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long soOfferDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soOfferDataKey",new Long(soOfferDataKey));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    IOVOrderCustomer realReturn = executeInner( soOfferDataKey, aOVOrderCustomer, REGION_ID);
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
    long soOfferDataKey = ($vmParameters.get("soOfferDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soOfferDataKey"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
IOVOrderCustomer realReturn = execute(soOfferDataKey,aOVOrderCustomer,REGION_ID);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}