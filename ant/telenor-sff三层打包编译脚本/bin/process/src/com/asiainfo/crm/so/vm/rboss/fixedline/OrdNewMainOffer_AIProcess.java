package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.appframe2.common.DataContainerInterface;
public class OrdNewMainOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewMainOffer_AIProcess.class);
  protected DataContainerInterface executeInner(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,ISoOrderData aSoOrderData) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	IOVOrderOffer aOVOrderOffer = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	DataContainerInterface aBaseInfoDC = null;
	long aOrdOfferId = 0;
	long aMobileUserId = 0;
	String aMobileBillId = "";
	String aAddrInfo = "";
	String aUserRelat = "";
	String aStaffInfo = "";
	String aBillId = "";
    aSoUserData=(com.ai.omframe.order.data.ivalues.ISoUserData)com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getSoData(soUserDataKey,customerOrderId); aSoOfferData=aSoUserData.getSoRoleData().getSoOfferData(); java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processNewPrdOVChain(aOrderValueChain,map);;
    aBaseInfoDC = aSoOrderData.getNormalRowsetInfo("frmNormalBasicInfo_"+aSoOfferData.getOfferId()+"_"+aSoUserData.getBillId())[0];aAddrInfo = aBaseInfoDC.getAsString("ADDR_INFO");aUserRelat = aBaseInfoDC.getAsString("USER_RELAT");aStaffInfo = aBaseInfoDC.getAsString("STAFF_INFO");aBillId=aBaseInfoDC.getAsString("BILL_ID");;
    aSoUserData = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).createAcctAndContact(aSoUserData,aOVOrderCustomer);
    {//Call the sub-processes开户
      Map tmpMap6 = new HashMap();
      Object loopVar6 = null;
      int loopCount6 = 0;
      loopVar6 = new Object[]{null};
      loopCount6 = 1;
      for(int i=0;i < loopCount6;i++){
      tmpMap6.clear();
      tmpMap6.put("aSoUserData",aSoUserData);
      tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
      tmpMap6.put("aSoOfferData",aSoOfferData);
      tmpMap6.put("aOrderValueChain",aOrderValueChain);
      tmpMap6.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewUser",tmpMap6);
      aOVOrderOffer = (tmpMap6.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap6.get("aOVOrderOffer"),IOVOrderOffer.class);

      tmpMap6.clear();
      }    }

    aOVOrderOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateUserInfo4New(aOVOrderOffer,aBaseInfoDC);
    aOVOrderOffer = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).updateAddrInfo(aOVOrderCustomer,aOVOrderOffer,aAddrInfo);
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV.class)).putCau(aBillId,REGION_ID);
    ((com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.OMWorkflowCommonSV",com.ai.omframe.teaminvoke.service.interfaces.IOMWorkflowCommonSV.class)).saveOVOrdOffer(aOVOrderOffer,REGION_ID);
    return aBaseInfoDC;
  }
  public DataContainerInterface execute(long customerOrderId,long soUserDataKey,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,ISoOrderData aSoOrderData) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("soUserDataKey",new Long(soUserDataKey));
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aSoOrderData",aSoOrderData);
try{
    DataContainerInterface realReturn = executeInner( customerOrderId, soUserDataKey, REGION_ID, aOVOrderCustomer, aSoOrderData);
    $returnParameter.put("aBaseInfoDC",realReturn);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    ISoOrderData aSoOrderData = ($vmParameters.get("aSoOrderData") == null)?null:(ISoOrderData)VMDataType.transfer($vmParameters.get("aSoOrderData"),ISoOrderData.class);
DataContainerInterface realReturn = execute(customerOrderId,soUserDataKey,REGION_ID,aOVOrderCustomer,aSoOrderData);
    $vmParameters.put("aBaseInfoDC",realReturn);
    return $vmParameters;
  }
}