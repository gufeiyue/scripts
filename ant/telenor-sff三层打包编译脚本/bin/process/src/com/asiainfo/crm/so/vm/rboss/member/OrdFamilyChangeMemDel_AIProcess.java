package com.asiainfo.crm.so.vm.rboss.member;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdFamilyChangeMemDel_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyChangeMemDel_AIProcess.class);
  protected void executeInner(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData OrdSoOfferData = null;
	ISoUserData OrdSoUserData = null;
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	int userIds = 0;
	ISoUserData[] OfferMemberUserDatas = null;
	IOVOrderOffer aOVOrderOffer = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
    OrdSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); OrdSoOfferData=OrdSoUserData.getSoRoleData().getSoOfferData();;
    OfferMemberUserDatas = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IGroupSV.class)).createSoUserDataFromChangeOffer(OrdSoOfferData,REGION_ID);
    if(OfferMemberUserDatas.length>0) { aSoOfferData=OfferMemberUserDatas[0].getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(OrdSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processDelMemOVChain(aOrderValueChain,map); };
    while(userIds<OfferMemberUserDatas.length){
      aSoUserData = OfferMemberUserDatas[userIds];
      {//Call the sub-processes删除成员
        Map tmpMap5 = new HashMap();
        Object loopVar5 = null;
        int loopCount5 = 0;
        loopVar5 = new Object[]{null};
        loopCount5 = 1;
        for(int i=0;i < loopCount5;i++){
        tmpMap5.clear();
        tmpMap5.put("aSoUserData",aSoUserData);
        tmpMap5.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap5.put("aSoOfferData",aSoOfferData);
        tmpMap5.put("aOrderValueChain",aOrderValueChain);
        tmpMap5.put("FLOWOBJECT_ID",new Long(customerOrderId));
        tmpMap5.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDelMem",tmpMap5);
        aOVOrderOffer = (tmpMap5.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap5.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap5.clear();
        }      }

      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFamilyOfferSV.class)).deleteFamilyOrdAccrelValue(aOVOrderOffer,aSoUserData,REGION_ID);
      userIds++;
    }
  }
  public void execute(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    executeInner( customerOrderId, soUserDataKey, REGION_ID, aOVOrderCustomer);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
execute(customerOrderId,soUserDataKey,REGION_ID,aOVOrderCustomer);
  return $vmParameters;
  }
}