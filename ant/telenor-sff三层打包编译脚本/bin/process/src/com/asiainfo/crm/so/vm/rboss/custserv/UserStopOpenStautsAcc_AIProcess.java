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
public class UserStopOpenStautsAcc_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(UserStopOpenStautsAcc_AIProcess.class);
  protected void executeInner(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String mainBillId,ISoOrderData aSoOrderData,ArrayList mainChildBillList,String REGION_ID,long BUSIOPER_ID,ArrayList childBillList,int childBillListIndex,String childBillId,String osType,int orderOfferIndex,long orderOfferId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int mainChildBillListIndex = 0;
	String mainChildBillId = "";
    mainBillId=com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId).getBillId(); aOVOrderCustomer=com.ai.omframe.util.SoServiceFactory.getOrderOvObjHelperSV().loadOrdCustSelf(customerOrderId); aSoOrderData= com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId); BUSIOPER_ID = aSoOrderData.getMainBusinessId();;
    mainChildBillList = (java.util.ArrayList)VMDataType.transfer(((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).getAccChildBills(aSoOrderData,aOVOrderCustomer),java.util.ArrayList.class);
    while(mainChildBillListIndex<mainChildBillList.size()){
      mainChildBillId = (String)mainChildBillList.get(mainChildBillListIndex);
      {//Call the sub-processes子用户停复机
        Map tmpMap10 = new HashMap();
        Object loopVar10 = null;
        int loopCount10 = 0;
        loopVar10 = new Object[]{null};
        loopCount10 = 1;
        for(int i=0;i < loopCount10;i++){
        tmpMap10.clear();
        tmpMap10.put("billId",mainChildBillId);
        tmpMap10.put("customerOrderId",new Long(customerOrderId));
        tmpMap10.put("REGION_ID",REGION_ID);
        tmpMap10.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap10.put("osType",osType);
        tmpMap10.put("aSoOrderData",aSoOrderData);
        tmpMap10.put("FLOWOBJECT_ID",new Long(customerOrderId));
        tmpMap10.put("needChgBusinessId","false");

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.custserv.UserStopOpenStatusChild",tmpMap10);

        tmpMap10.clear();
        }      }

      mainChildBillListIndex++;
    }
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).saveAgency(aSoOrderData,aOVOrderCustomer);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).osStatusSendToOpen(aOVOrderCustomer,aSoOrderData);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).osStatusSendToBilling(aOVOrderCustomer,aSoOrderData);
    {//Call the sub-processes转实例
      Map tmpMap60 = new HashMap();
      Object loopVar60 = null;
      int loopCount60 = 0;
      loopVar60 = new Object[]{null};
      loopCount60 = 1;
      for(int i=0;i < loopCount60;i++){
      tmpMap60.clear();
      tmpMap60.put("aOVOrderCustomer",aOVOrderCustomer);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.OrdTransIns",tmpMap60);
      aOVOrderCustomer = (tmpMap60.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap60.get("aOVOrderCustomer"),IOVOrderCustomer.class);

      tmpMap60.clear();
      }    }

    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IStopOpenOsStatusSV.class)).dealPreDestroyUser(aSoOrderData,aOVOrderCustomer);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdCustToFinish(aOVOrderCustomer);
  }
  public void execute(long customerOrderId,IOVOrderCustomer aOVOrderCustomer,String mainBillId,ISoOrderData aSoOrderData,ArrayList mainChildBillList,String REGION_ID,long BUSIOPER_ID,ArrayList childBillList,int childBillListIndex,String childBillId,String osType,int orderOfferIndex,long orderOfferId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("mainBillId",mainBillId);
    $inParameter.put("aSoOrderData",aSoOrderData);
    $inParameter.put("mainChildBillList",mainChildBillList);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("BUSIOPER_ID",new Long(BUSIOPER_ID));
    $inParameter.put("childBillList",childBillList);
    $inParameter.put("childBillListIndex",new Integer(childBillListIndex));
    $inParameter.put("childBillId",childBillId);
    $inParameter.put("osType",osType);
    $inParameter.put("orderOfferIndex",new Integer(orderOfferIndex));
    $inParameter.put("orderOfferId",new Long(orderOfferId));
try{
    executeInner( customerOrderId, aOVOrderCustomer, mainBillId, aSoOrderData, mainChildBillList, REGION_ID, BUSIOPER_ID, childBillList, childBillListIndex, childBillId, osType, orderOfferIndex, orderOfferId);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long BUSIOPER_ID = ($vmParameters.get("BUSIOPER_ID") == null)?0:((Long)VMDataType.transfer($vmParameters.get("BUSIOPER_ID"),long.class)).longValue();
    ArrayList childBillList = ($vmParameters.get("childBillList") == null)?new ArrayList():(ArrayList)VMDataType.transfer($vmParameters.get("childBillList"),ArrayList.class);
    int childBillListIndex = ($vmParameters.get("childBillListIndex") == null)?0:((Integer)VMDataType.transfer($vmParameters.get("childBillListIndex"),int.class)).intValue();
    String childBillId = ($vmParameters.get("childBillId") == null)?"":(String)VMDataType.transfer($vmParameters.get("childBillId"),String.class);
    String osType = ($vmParameters.get("osType") == null)?"":(String)VMDataType.transfer($vmParameters.get("osType"),String.class);
    int orderOfferIndex = ($vmParameters.get("orderOfferIndex") == null)?0:((Integer)VMDataType.transfer($vmParameters.get("orderOfferIndex"),int.class)).intValue();
    long orderOfferId = ($vmParameters.get("orderOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("orderOfferId"),long.class)).longValue();
execute(customerOrderId,aOVOrderCustomer,mainBillId,aSoOrderData,mainChildBillList,REGION_ID,BUSIOPER_ID,childBillList,childBillListIndex,childBillId,osType,orderOfferIndex,orderOfferId);
  return $vmParameters;
  }
}