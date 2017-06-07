package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
public class Termination_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(Termination_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderCustomer aOVOrderCustomer = null;
	ISoOrderData aSoOrderData = null;
	long aOrgId = 0;
	long aOpId = 0;
	long aPreOrderId = 0;
	long aProdSpecId = 0;
	long aMainInsOfferId = 0;
	long aMainBusinessId = 0;
	long aUserId = 0;
	String aBillId = "";
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);aSoOrderData = com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId);aOrgId= aOVOrderCustomer.getOrderCustomerValue().getOrgId();aOpId = aOVOrderCustomer.getOrderCustomerValue().getOpId();aMainInsOfferId=aSoOrderData.getMainInsOfferId();aMainBusinessId=aSoOrderData.getMainBusinessId();aUserId=aSoOrderData.getMainInsProductId();aBillId=aSoOrderData.getBillId();;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).setOfferInstStateLocked(aMainInsOfferId,true);
    String decisionCond16 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).isTerminateVoip(customerOrderId,aUserId));
    if(decisionCond16.equals("true")){
      {//Call the sub-processesCancelVoipTermination
        Map tmpMap17 = new HashMap();
        Object loopVar17 = null;
        int loopCount17 = 0;
        loopVar17 = new Object[]{null};
        loopCount17 = 1;
        for(int i=0;i < loopCount17;i++){
        tmpMap17.clear();
        tmpMap17.put("customerOrderId",new Long(customerOrderId));
        tmpMap17.put("aBillId",aBillId);
        tmpMap17.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap17.put("aSoOrderData",aSoOrderData);
        tmpMap17.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.CancelVoipTermination",tmpMap17);

        tmpMap17.clear();
        }      }

      {//Call the sub-processes订单通用过程
        Map tmpMap7 = new HashMap();
        Object loopVar7 = null;
        int loopCount7 = 0;
        loopVar7 = new Object[]{null};
        loopCount7 = 1;
        for(int i=0;i < loopCount7;i++){
        tmpMap7.clear();
        tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdProcess",tmpMap7);

        tmpMap7.clear();
        }      }

    }
    else if(decisionCond16.equals("false")){
      {//Call the sub-processes拆机订单生成
        Map tmpMap1 = new HashMap();
        Object loopVar1 = null;
        int loopCount1 = 0;
        loopVar1 = new Object[]{null};
        loopCount1 = 1;
        for(int i=0;i < loopCount1;i++){
        tmpMap1.clear();
        tmpMap1.put("customerOrderId",new Long(customerOrderId));
        tmpMap1.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap1.put("aSoOrderData",aSoOrderData);
        tmpMap1.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdTermination",tmpMap1);

        tmpMap1.clear();
        }      }

      {//Call the sub-processes订单通用过程
        Map tmpMap7 = new HashMap();
        Object loopVar7 = null;
        int loopCount7 = 0;
        loopVar7 = new Object[]{null};
        loopCount7 = 1;
        for(int i=0;i < loopCount7;i++){
        tmpMap7.clear();
        tmpMap7.put("aOVOrderCustomer",aOVOrderCustomer);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.fixedline.OrdProcess",tmpMap7);

        tmpMap7.clear();
        }      }

    }
    else{logger.warn("是否只拆VOIPConditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, REGION_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,REGION_ID);
  return $vmParameters;
  }
}