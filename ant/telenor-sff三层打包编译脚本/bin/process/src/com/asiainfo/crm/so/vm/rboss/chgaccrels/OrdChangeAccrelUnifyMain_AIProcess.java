package com.asiainfo.crm.so.vm.rboss.chgaccrels;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdChangeAccrelSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdChangeAccrelUnifyMain_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdChangeAccrelUnifyMain_AIProcess.class);
  protected void executeInner(long soUserDataKey,long customerOrderId,String REGION_ID,long payUserId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	long userId = 0;
	ISoUserData aSoUserData = null;
	long businessId = 0;
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)(com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV()).getSoData(soUserDataKey,customerOrderId); userId=aSoUserData.getUserId(); businessId = aSoUserData.getSoRoleData().getSoOfferData().getBusinessId();
    ((com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.IAccrelSV.class)).addSimpleBusiLog(userId,payUserId,businessId);
    String decisionCond25 = String.valueOf(((IOrdChangeAccrelSV)ServiceFactory.getService(IOrdChangeAccrelSV.class)).getBusiOperTypeByOperId(businessId));
    if(decisionCond25.equals("false")){
    }
    else if(decisionCond25.equals("true")){
      {//Call the sub-processes家庭统一支付成员变更子流程
        Map tmpMap3 = new HashMap();
        Object loopVar3 = null;
        int loopCount3 = 0;
        loopVar3 = new Object[]{null};
        loopCount3 = 1;
        for(int i=0;i < loopCount3;i++){
        tmpMap3.clear();
        tmpMap3.put("REGION_ID",REGION_ID);
        tmpMap3.put("soUserDataKey",new Long(soUserDataKey));
        tmpMap3.put("customerOrderId",new Long(customerOrderId));
        tmpMap3.put("FLOWOBJECT_ID",new Long(customerOrderId));

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.chgaccrels.OrdChangeAccrelUnifyBusi",tmpMap3);

        tmpMap3.clear();
        }      }

    }
    else{logger.warn("判断是否需要操作Conditions do not match");}
  }
  public void execute(long soUserDataKey,long customerOrderId,String REGION_ID,long payUserId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("payUserId",new Long(payUserId));
try{
    executeInner( soUserDataKey, customerOrderId, REGION_ID, payUserId);
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long payUserId = ($vmParameters.get("payUserId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("payUserId"),long.class)).longValue();
execute(soUserDataKey,customerOrderId,REGION_ID,payUserId);
  return $vmParameters;
  }
}