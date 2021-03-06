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
public class OrdAddIncOfferLN_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdAddIncOfferLN_AIProcess.class);
  protected IOVOrderCustomer executeInner(long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,aOVOrderCustomer.getOrderCustomerValue().getCustomerOrderId()); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
    {//Call the sub-processes订购增值策划
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("aSoUserData",aSoUserData);
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("aSoOfferData",aSoOfferData);
      tmpMap3.put("aOrderValueChain",aOrderValueChain);
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewOffer",tmpMap3);
      aOVOrderOffer = (tmpMap3.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap3.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap3.clear();
      }    }

    {//Call the sub-processes家庭套餐变更添加成员
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = (((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).getSoUserDatasOfAddIncOffer(customerOrderId,aSoOfferData));
      if(loopVar6 == null){ loopVar6 = new Object[0]; loopCount6 = 0;}
else if(loopVar6 instanceof java.util.List){loopCount6 = ((java.util.List)loopVar6).size();
}else if(loopVar6.getClass().isArray()){loopCount6 = ((Object[])loopVar6).length;
}      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("customerOrderId",new Long(customerOrderId));
      tmpMap6.put("soUserDataKey",new Long(soUserDataKey));
      tmpMap6.put("REGION_ID",REGION_ID);
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.member.OrdRbossMemAdd",tmpMap6);

      tmpMap6.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(long soUserDataKey,IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    IOVOrderCustomer realReturn = executeInner( soUserDataKey, aOVOrderCustomer, REGION_ID, customerOrderId);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(soUserDataKey,aOVOrderCustomer,REGION_ID,customerOrderId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}