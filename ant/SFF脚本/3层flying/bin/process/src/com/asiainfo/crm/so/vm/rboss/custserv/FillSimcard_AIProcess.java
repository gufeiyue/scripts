package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.ivalues.IOrdUserValue;
public class FillSimcard_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(FillSimcard_AIProcess.class);
  protected void executeInner(long customerOrderId,long soUserDataKey,String REGION_ID,boolean isActiveCardNow,String ICCID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	IOVOrderOffer aOVOrderOffer = null;
	long userId = 0;
	ISoOrderData aSoOrderData = null;
	IOrdUserValue aOrdUserValue = null;
	long orderOfferId = 0;
    {//Call the sub-processes创建单点通用订单
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = new Object[]{null};
      loopCount12 = 1;
      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("customerOrderId",new Long(customerOrderId));
      tmpMap12.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.CreateCommOrder",tmpMap12);
      aOVOrderOffer = (tmpMap12.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap12.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap12.clear();
      }    }

    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); aOVOrderCustomer.addOVOrderOffer(aOVOrderOffer);;
    isActiveCardNow = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).isActiveCardNow(customerOrderId);
    String decisionCond36 = String.valueOf(isActiveCardNow);
    if(decisionCond36.equals("false")){
      aOVOrderCustomer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).setAgencyInfo(customerOrderId,aOVOrderCustomer);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).bakSimcardAccept(customerOrderId,ICCID);
    }
    else if(decisionCond36.equals("true")){
      aOVOrderOffer = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).setOrdUserSubBillIdAndBillId(customerOrderId,aOVOrderOffer,ICCID);
      ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IBakSimcardSV.class)).fillSimcard(customerOrderId,ICCID);
      aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createOVOrderOfferFromInst(customerOrderId,aOVOrderCustomer);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IBilling2CrmSV.class)).sendOrderInfoToBilling(aOVOrderCustomer);
      ((com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV",com.asiainfo.crm.so.teaminvoke.in.service.interfaces.IOpen2CrmSV.class)).sendOrderInfoToOpen(aOVOrderCustomer);
      {//Call the sub-processes转实例
        Map tmpMap23 = new HashMap();
        Object loopVar23 = null;
        int loopCount23 = 0;
        loopVar23 = new Object[]{null};
        loopCount23 = 1;
        for(int i=0;i < loopCount23;i++){
        tmpMap23.clear();
        tmpMap23.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap23.put("REGION_ID",REGION_ID);
        tmpMap23.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap23);
        aOVOrderCustomer = (tmpMap23.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap23.get("aOVOrderCustomer"),IOVOrderCustomer.class);

        tmpMap23.clear();
        }      }

    }
    else{logger.warn("判断是否是预约Conditions do not match");}
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    aOVOrderCustomer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).outInstStore(customerOrderId,aOVOrderCustomer,ICCID);
  }
  public void execute(long customerOrderId,long soUserDataKey,String REGION_ID,boolean isActiveCardNow,String ICCID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("isActiveCardNow",new Boolean(isActiveCardNow));
    $inParameter.put("ICCID",ICCID);
try{
    executeInner( customerOrderId, soUserDataKey, REGION_ID, isActiveCardNow, ICCID);
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
    boolean isActiveCardNow = ($vmParameters.get("isActiveCardNow") == null)?false:((Boolean)VMDataType.transfer($vmParameters.get("isActiveCardNow"),boolean.class)).booleanValue();
    String ICCID = ($vmParameters.get("ICCID") == null)?"":(String)VMDataType.transfer($vmParameters.get("ICCID"),String.class);
execute(customerOrderId,soUserDataKey,REGION_ID,isActiveCardNow,ICCID);
  return $vmParameters;
  }
}