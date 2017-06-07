package com.ai.omframe.order.vm.lib2;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstanceQrySV;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.teaminvoke.service.interfaces.ICrmProductSV;
import  com.ai.omframe.instance.ivalues.IQOfferUserValue;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
public class OrdQuitOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdQuitOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(ISoOfferData aSoOfferData,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,ISoUserData aSoUserData) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IQOfferUserValue[] instOfferUserValues = null;
	long aOfferInstId = 0;
	String[] billIds = null;
	int validType = -1;
	long aRoleId = -1;
	int offerUserIdx = 0;
	IQOfferUserValue aInstOfferUserValue = null;
	String aBillId = "";
	String aMainBillId = "";
	IOVOrderOffer aOVOrderOffer = null;
	long aDeletMemBusinessId = 0;
	long aOfferId = 0;
	long aMainBusinessId = 0;
	ISoUserData memSoUserData = null;
	String offerRegionId = "";
    billIds=new String[0];aOfferInstId=aSoOfferData.getInsOfferId();aOfferId=aSoOfferData.getOfferId();aMainBusinessId=aSoOfferData.getBusinessId();offerRegionId=com.ai.common.util.CenterUtil.getRegionIdByBillId(aSoOfferData.getMainBillId());System.out.println("===================="+offerRegionId+"====================");;
    instOfferUserValues = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getOfferUsersByInsOfferIdRoleId(aOfferInstId,aRoleId,billIds,validType,offerRegionId);
    aDeletMemBusinessId = ((com.ai.omframe.teaminvoke.service.interfaces.ICrmProductSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.teaminvoke.service.interfaces.ICrmProductSV",com.ai.omframe.teaminvoke.service.interfaces.ICrmProductSV.class)).getOfferDeletMemBusiId(aOfferId);
    while(null!=instOfferUserValues&&instOfferUserValues.length>0&&offerUserIdx<instOfferUserValues.length){
      aInstOfferUserValue=instOfferUserValues[offerUserIdx];aBillId=aInstOfferUserValue.getBillId();;
      String decisionCond10 = String.valueOf(aInstOfferUserValue.getIsMainOffer()==0);
      if(decisionCond10.equals("true")){
        memSoUserData = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).createMemRoleSoUserData(aInstOfferUserValue,aSoOfferData);
        {//Call the sub-processes删除成员
          Map tmpMap17 = new HashMap();
          Object loopVar17 = null;
          int loopCount17 = 0;
          loopVar17 = new Object[]{null};
          loopCount17 = 1;
          for(int i=0;i < loopCount17;i++){
          tmpMap17.clear();
          tmpMap17.put("aSoUserData",memSoUserData);
          tmpMap17.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap17.put("aSoOfferData",aSoOfferData);
          tmpMap17.put("aOrderValueChain",aOrderValueChain);
          tmpMap17.put("aBillId",aBillId);
          tmpMap17.put("REGION_ID",REGION_ID);
          tmpMap17.put("realBusinessId",new Long(aDeletMemBusinessId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDelMem",tmpMap17);
          aOVOrderOffer = (tmpMap17.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap17.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap17.clear();
          }        }

      }
      else if(decisionCond10.equals("false")){
        {//Call the sub-processes退订增值策划
          Map tmpMap23 = new HashMap();
          Object loopVar23 = null;
          int loopCount23 = 0;
          loopVar23 = new Object[]{null};
          loopCount23 = 1;
          for(int i=0;i < loopCount23;i++){
          tmpMap23.clear();
          tmpMap23.put("aSoUserData",aSoUserData);
          tmpMap23.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap23.put("aSoOfferData",aSoOfferData);
          tmpMap23.put("aOrderValueChain",aOrderValueChain);
          tmpMap23.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap23);
          aOVOrderOffer = (tmpMap23.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap23.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap23.clear();
          }        }

      }
      else{logger.warn("非主角色用户Conditions do not match");}
      offerUserIdx++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(ISoOfferData aSoOfferData,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,ISoUserData aSoUserData) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferData",aSoOfferData);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aSoUserData",aSoUserData);
try{
    IOVOrderCustomer realReturn = executeInner( aSoOfferData, aOVOrderCustomer, aOrderValueChain, REGION_ID, aSoUserData);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
IOVOrderCustomer realReturn = execute(aSoOfferData,aOVOrderCustomer,aOrderValueChain,REGION_ID,aSoUserData);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}