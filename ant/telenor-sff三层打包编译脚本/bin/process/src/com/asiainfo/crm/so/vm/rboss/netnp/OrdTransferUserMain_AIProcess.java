package com.asiainfo.crm.so.vm.rboss.netnp;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOrdAccrelValue;
import  com.ai.omframe.order.ivalues.IOrdUserOsStateValue;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdTransferUserMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransferUserMain_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long soUserDataKey,long customerOrderId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOrdUserValue aOrdUserValue = null;
	IOrdAccrelValue aOrdAccrelValue = null;
	IOrdUserOsStateValue aOrdUserOsStateValue = null;
	IOVOrderOffer aOVOrderOffer = null;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData();aSoOfferData.setBusinessId(191000000001L); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);aOrderValueChain.setUSER_TYPE(1);aOrderValueChain=com.asiainfo.crm.so.util.RbossServiceFactory.getPrjOrderVmHelper().updateUserInstState(aSoUserData,aOrderValueChain);aOrderValueChain.setSTATE_OFFER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE); aOrderValueChain.setSTATE_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE); aOrderValueChain.setSTATE_OFFER_USER(com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);System.out.println("------"+aOVOrderCustomer.getOrderCustomerValue());;
    {//Call the sub-processes新建策划
      Map tmpMap3 = new HashMap();
      Object loopVar3 = null;
      int loopCount3 = 0;
      loopVar3 = new Object[]{null};
      loopCount3 = 1;
      for(int i=0;i < loopCount3;i++){
      tmpMap3.clear();
      tmpMap3.put("aSoOfferData",aSoOfferData);
      tmpMap3.put("aOrderValueChain",aOrderValueChain);
      tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap3.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewOffer",tmpMap3);
      aOVOrderOffer = (tmpMap3.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap3.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap3.clear();
      }    }

    aOrdUserValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOrdUserValue(aOVOrderOffer,aSoUserData,aOrderValueChain,REGION_ID);
    aOrdUserOsStateValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOrdUserOsStateValue(aOrdUserValue,REGION_ID);
    aOVOrdOffOrdUser = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOVOrderOfferUser(aOVOrderOffer,aOrdUserValue,aSoUserData,aOrdUserOsStateValue,aOrderValueChain,REGION_ID);
    aOrdAccrelValue = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOrdAccrelValue(aOVOrderOffer,aSoUserData,aOrdUserValue,aOrderValueChain,REGION_ID);
    {//Call the sub-processes新建多个产品
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("aSoUserData",aSoUserData);
      tmpMap8.put("aOrderValueChain",aOrderValueChain);
      tmpMap8.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap8.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewBatchSrvpkgs",tmpMap8);

      tmpMap8.clear();
      }    }

    {//Call the sub-processes新建营业费用
      Map tmpMap9 = new HashMap();
      Object loopVar9 = null;
      int loopCount9 = 0;
      loopVar9 = new Object[]{null};
      loopCount9 = 1;
      for(int i=0;i < loopCount9;i++){
      tmpMap9.clear();
      tmpMap9.put("aSoUserData",aSoUserData);
      tmpMap9.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
      tmpMap9.put("aOrderValueChain",aOrderValueChain);
      tmpMap9.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdOfferUserPrice",tmpMap9);

      tmpMap9.clear();
      }    }

    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,String REGION_ID,long soUserDataKey,long customerOrderId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("customerOrderId",new Long(customerOrderId));
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, REGION_ID, soUserDataKey, customerOrderId);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,REGION_ID,soUserDataKey,customerOrderId);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}