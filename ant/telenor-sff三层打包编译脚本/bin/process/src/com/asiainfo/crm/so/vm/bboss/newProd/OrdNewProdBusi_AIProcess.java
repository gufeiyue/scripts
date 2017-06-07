package com.asiainfo.crm.so.vm.bboss.newProd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.order.bboss.service.interfaces.IBbossSoVMInvokeSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdNewProdBusi_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewProdBusi_AIProcess.class);
  protected long executeInner(long customerOrderId,long soUserDataKey,String workflowId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	IOrdUserValue aOrdUserValue = null;
	IOVOrdOffOrdUser aOVOrdOffOrdUser = null;
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoUserData aSoUserData = null;
	long offerOrderId = 0;
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map); aOrderValueChain.setUSER_TYPE(8);;
    String decisionCond6 = String.valueOf(((IBbossSoVMInvokeSV)ServiceFactory.getService(IBbossSoVMInvokeSV.class)).judgeOfferType(aSoUserData));
    if(decisionCond6.equals("false")){
      {//Call the sub-processes订购增值策划
        Map tmpMap7 = new HashMap();
        Object loopVar7 = null;
        int loopCount7 = 0;
        loopVar7 = new Object[]{null};
        loopCount7 = 1;
        for(int i=0;i < loopCount7;i++){
        tmpMap7.clear();
        tmpMap7.put("aSoUserData",aSoUserData);
        tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap7.put("aSoOfferData",aSoOfferData);
        tmpMap7.put("aOrderValueChain",aOrderValueChain);
        tmpMap7.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewOffer",tmpMap7);
        aOVOrderOffer = (tmpMap7.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap7.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap7.clear();
        }      }

      offerOrderId = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    }
    else if(decisionCond6.equals("true")){
      {//Call the sub-processes开户
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

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewUser",tmpMap4);
        aOVOrderOffer = (tmpMap4.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap4.get("aOVOrderOffer"),IOVOrderOffer.class);

        tmpMap4.clear();
        }      }

      offerOrderId = ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    }
    else{logger.warn("If OFFER_PLAN_BBOSS Conditions do not match");}
    return offerOrderId;
  }
  public long execute(long customerOrderId,long soUserDataKey,String workflowId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("workflowId",workflowId);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    long realReturn = executeInner( customerOrderId, soUserDataKey, workflowId, REGION_ID);
    $returnParameter.put("offerOrderId",new Long(realReturn));
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
    long soUserDataKey = ($vmParameters.get("soUserDataKey") == null)?0:((Long)VMDataType.transfer($vmParameters.get("soUserDataKey"),long.class)).longValue();
    String workflowId = ($vmParameters.get("workflowId") == null)?"":(String)VMDataType.transfer($vmParameters.get("workflowId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
long realReturn = execute(customerOrderId,soUserDataKey,workflowId,REGION_ID);
    $vmParameters.put("offerOrderId",new Long(realReturn));
    return $vmParameters;
  }
}