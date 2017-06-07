package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  java.util.ArrayList;
import  com.ai.omframe.instance.ivalues.IInsUserValue;
public class UserStopOpenStatusUnion_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserStopOpenStatusUnion_AIProcess.class);
  protected void executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String mainBillId,ISoOrderData aSoOrderData,ArrayList mainChildBillList,ArrayList childBillList,int childBillListIndex,String childBillId,String REGION_ID,long BUSIOPER_ID,String oldNoticeFlag,IInsUserValue oldInsUser,String delayDays,String osType,int orderOfferIndex,long orderOfferId,boolean isAppoint,String osLevel) throws Exception{
	String _TASK_JUGE_RESULT = "";
	boolean isAcct = false;
	String osStatusType = "";
    aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId);;
    String decisionCond35 = String.valueOf(((IStopOpenOsStatusSV)ServiceFactory.getService(IStopOpenOsStatusSV.class)).getOsLevel(customerOrderId));
    if(decisionCond35.equals("product")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).doOsProduct(customerOrderId);
      ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    }
    else if(decisionCond35.equals("os")){
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).saveAppoint(customerOrderId);
      isAppoint = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).isAppoint(customerOrderId);
      String decisionCond17 = String.valueOf(isAppoint);
      if(decisionCond17.equals("true")){
        ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
      }
      else if(decisionCond17.equals("false")){
        {//Call the sub-processes停复机
          Map tmpMap6 = new HashMap();
          Object loopVar6 = null;
          int loopCount6 = 0;
          loopVar6 = new Object[]{null};
          loopCount6 = 1;
          for(int i=0;i < loopCount6;i++){
          tmpMap6.clear();
          tmpMap6.put("customerOrderId",new Long(customerOrderId));
          tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap6.put("mainBillId",mainBillId);
          tmpMap6.put("aSoOrderData",aSoOrderData);
          tmpMap6.put("mainChildBillList",mainChildBillList);
          tmpMap6.put("REGION_ID",REGION_ID);
          tmpMap6.put("BUSIOPER_ID",new Long(BUSIOPER_ID));
          tmpMap6.put("childBillList",childBillList);
          tmpMap6.put("childBillListIndex",new Integer(childBillListIndex));
          tmpMap6.put("childBillId",childBillId);
          tmpMap6.put("osType",osType);
          tmpMap6.put("orderOfferIndex",new Integer(orderOfferIndex));
          tmpMap6.put("orderOfferId",new Long(orderOfferId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.UserStopOpenStautsAcc",tmpMap6);

          tmpMap6.clear();
          }        }

      }
      else{logger.warn("是否都是预约/取消预约Conditions do not match");}
    }
    else{
      logger.warn("Configure the node (自动)");
      ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
    }
  }
  public void execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String mainBillId,ISoOrderData aSoOrderData,ArrayList mainChildBillList,ArrayList childBillList,int childBillListIndex,String childBillId,String REGION_ID,long BUSIOPER_ID,String oldNoticeFlag,IInsUserValue oldInsUser,String delayDays,String osType,int orderOfferIndex,long orderOfferId,boolean isAppoint,String osLevel) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("mainBillId",mainBillId);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("mainChildBillList",mainChildBillList);
    $inParameter.put("childBillList",childBillList);
    $inParameter.put("childBillListIndex",new Integer(childBillListIndex));
    $inParameter.put("childBillId",childBillId);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("BUSIOPER_ID",new Long(BUSIOPER_ID));
    $inParameter.put("oldNoticeFlag",oldNoticeFlag);
    $inParameter.put("oldInsUser",oldInsUser);
    $inParameter.put("delayDays",delayDays);
    $inParameter.put("osType",osType);
    $inParameter.put("orderOfferIndex",new Integer(orderOfferIndex));
    $inParameter.put("orderOfferId",new Long(orderOfferId));
    $inParameter.put("isAppoint",new Boolean(isAppoint));
    $inParameter.put("osLevel",osLevel);
try{
    executeInner( customerOrderId, aOVOrderCustomer, mainBillId, aSoOrderData, mainChildBillList, childBillList, childBillListIndex, childBillId, REGION_ID, BUSIOPER_ID, oldNoticeFlag, oldInsUser, delayDays, osType, orderOfferIndex, orderOfferId, isAppoint, osLevel);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    String mainBillId = ($vmParameters.get("mainBillId") == null)?"":(String)VMDataType.transfer($vmParameters.get("mainBillId"),String.class);
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
    ArrayList mainChildBillList = ($vmParameters.get("mainChildBillList") == null)?new ArrayList():(ArrayList)VMDataType.transfer($vmParameters.get("mainChildBillList"),ArrayList.class);
    ArrayList childBillList = ($vmParameters.get("childBillList") == null)?new ArrayList():(ArrayList)VMDataType.transfer($vmParameters.get("childBillList"),ArrayList.class);
    int childBillListIndex = ($vmParameters.get("childBillListIndex") == null)?0:((Integer)VMDataType.transfer($vmParameters.get("childBillListIndex"),int.class)).intValue();
    String childBillId = ($vmParameters.get("childBillId") == null)?"":(String)VMDataType.transfer($vmParameters.get("childBillId"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long BUSIOPER_ID = ($vmParameters.get("BUSIOPER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("BUSIOPER_ID"),long.class)).longValue();
    String oldNoticeFlag = ($vmParameters.get("oldNoticeFlag") == null)?"":(String)VMDataType.transfer($vmParameters.get("oldNoticeFlag"),String.class);
    IInsUserValue oldInsUser = ($vmParameters.get("oldInsUser") == null)?null:(IInsUserValue)VMDataType.transfer($vmParameters.get("oldInsUser"),IInsUserValue.class);
    String delayDays = ($vmParameters.get("delayDays") == null)?"":(String)VMDataType.transfer($vmParameters.get("delayDays"),String.class);
    String osType = ($vmParameters.get("osType") == null)?"":(String)VMDataType.transfer($vmParameters.get("osType"),String.class);
    int orderOfferIndex = ($vmParameters.get("orderOfferIndex") == null)?0:((Integer)VMDataType.transfer($vmParameters.get("orderOfferIndex"),int.class)).intValue();
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
    boolean isAppoint = ($vmParameters.get("isAppoint") == null)?false:((Boolean)VMDataType.transfer($vmParameters.get("isAppoint"),boolean.class)).booleanValue();
    String osLevel = ($vmParameters.get("osLevel") == null)?"":(String)VMDataType.transfer($vmParameters.get("osLevel"),String.class);
execute(customerOrderId,aOVOrderCustomer,mainBillId,aSoOrderData,mainChildBillList,childBillList,childBillListIndex,childBillId,REGION_ID,BUSIOPER_ID,oldNoticeFlag,oldInsUser,delayDays,osType,orderOfferIndex,orderOfferId,isAppoint,osLevel);
  return $vmParameters;
  }
}