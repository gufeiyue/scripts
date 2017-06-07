package com.asiainfo.crm.so.vm.rboss.member;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdFamilyAddMem_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyAddMem_AIProcess.class);
  protected void executeInner(long customerOrderId,long soUserDataKey,long realBusinessId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long mainMemberUserId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(realBusinessId)); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processAddMemOVChain(aOrderValueChain,map);
    {//Call the sub-processes个人群组业务增加成员
      Map tmpMap8 = new HashMap();
      Object loopVar8 = null;
      int loopCount8 = 0;
      loopVar8 = new Object[]{null};
      loopCount8 = 1;
      for(int i=0;i < loopCount8;i++){
      tmpMap8.clear();
      tmpMap8.put("aSoUserData",aSoUserData);
      tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap8.put("aSoOfferData",aSoOfferData);
      tmpMap8.put("aOrderValueChain",aOrderValueChain);
      tmpMap8.put("realBusinessId",new Long(realBusinessId));
      tmpMap8.put("REGION_ID",REGION_ID);
      tmpMap8.put("FLOWOBJECT_ID",new Long(customerOrderId));

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdAddMemForRbossGroup",tmpMap8);
      aOVOrderOffer = (tmpMap8.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap8.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap8.clear();
      }    }

    aOVOrdOffOrdUser = aOVOrderOffer.getOVOrdOffOrdUser()[0];;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).createFamilyOrdAccrelValue(aOVOrdOffOrdUser,mainMemberUserId,REGION_ID);
  }
  public void execute(long customerOrderId,long soUserDataKey,long realBusinessId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,long mainMemberUserId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("realBusinessId",new Long(realBusinessId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("mainMemberUserId",new Long(mainMemberUserId));
try{
    executeInner( customerOrderId, soUserDataKey, realBusinessId, REGION_ID, aOVOrderCustomer, mainMemberUserId);
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    long realBusinessId = ($vmParameters.get("realBusinessId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("realBusinessId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    long mainMemberUserId = ($vmParameters.get("mainMemberUserId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("mainMemberUserId"),long.class)).longValue();
execute(customerOrderId,soUserDataKey,realBusinessId,REGION_ID,aOVOrderCustomer,mainMemberUserId);
  return $vmParameters;
  }
}