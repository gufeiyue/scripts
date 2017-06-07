package com.asiainfo.crm.so.vm.rboss.familyOffer;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoRoleData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdFamilyOfferChgCancelReverseMem_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdFamilyOfferChgCancelReverseMem_AIProcess.class);
  protected IOVOrderCustomer executeInner(ISoOfferData aSoOfferData,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoRoleData tempSoRoleData = null;
	ISoUserData tempUserData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	long realBusinessId = 0;
	ISoRoleData[] aSoRoleDatas = null;
	ISoUserData[] aSoUserDatas = null;
	int aRoleIdx = 0;
	int aUserIdx = 0;
    if(null!=aSoOfferData) { aSoRoleDatas = aSoOfferData.getSoRoleData(); realBusinessId =aSoOfferData.getBusinessId(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(realBusinessId)); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processAddMemOVChain(aOrderValueChain,map); };
    while(aSoRoleDatas!=null&&aRoleIdx<aSoRoleDatas.length){
      aSoUserDatas=aSoRoleDatas[aRoleIdx].getSoUserData(); aUserIdx=0;;
      while(aSoUserDatas!=null&&aUserIdx<aSoUserDatas.length){
        tempUserData=aSoUserDatas[aUserIdx];;
        {//Call the sub-processes删除成员
          Map tmpMap14 = new HashMap();
          Object loopVar14 = null;
          int loopCount14 = 0;
          loopVar14 = new Object[]{null};
          loopCount14 = 1;
          for(int i=0;i < loopCount14;i++){
          tmpMap14.clear();
          tmpMap14.put("aSoUserData",tempUserData);
          tmpMap14.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap14.put("aSoOfferData",aSoOfferData);
          tmpMap14.put("aOrderValueChain",aOrderValueChain);
          tmpMap14.put("FLOWOBJECT_ID",new Long(customerOrderId));
          tmpMap14.put("REGION_ID",REGION_ID);
          tmpMap14.put("realBusinessId",new Long(realBusinessId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDelMem",tmpMap14);

          tmpMap14.clear();
          }        }

        aUserIdx++;
      }
      aRoleIdx++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(ISoOfferData aSoOfferData,long customerOrderId,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( aSoOfferData, customerOrderId, REGION_ID, aOVOrderCustomer);
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
    ISoOfferData aSoOfferData = ($vmParameters.get("aSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aSoOfferData"),ISoOfferData.class);
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(aSoOfferData,customerOrderId,REGION_ID,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}