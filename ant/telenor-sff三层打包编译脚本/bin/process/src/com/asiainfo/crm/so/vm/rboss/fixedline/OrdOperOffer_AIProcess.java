package com.asiainfo.crm.so.vm.rboss.fixedline;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IFixedLineVmSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
public class OrdOperOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdOperOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(ISoOfferData[] aSoOfferDatas,String aOper,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoUserData aSoUserData = null;
	ISoOfferData aSoOfferData = null;
	OrderValueChain aOrderValueChain = new OrderValueChain();
	IOVOrderOffer aOVOrderOffer = null;
	int aOfferIndex = 0;
    ;
    while(aSoOfferDatas!=null&&aOfferIndex<aSoOfferDatas.length){
      aSoOfferData=aSoOfferDatas[aOfferIndex];;
      String decisionCond15 = String.valueOf(((IFixedLineVmSV)ServiceFactory.getService(IFixedLineVmSV.class)).transOper(aOper));
      if(decisionCond15.equals("a")){
        aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
        {//Call the sub-processes订购增值策划
          Map tmpMap20 = new HashMap();
          Object loopVar20 = null;
          int loopCount20 = 0;
          loopVar20 = new Object[]{null};
          loopCount20 = 1;
          for(int i=0;i < loopCount20;i++){
          tmpMap20.clear();
          tmpMap20.put("aSoUserData",aSoUserData);
          tmpMap20.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap20.put("aSoOfferData",aSoOfferData);
          tmpMap20.put("aOrderValueChain",aOrderValueChain);
          tmpMap20.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewOffer",tmpMap20);
          aOVOrderOffer = (tmpMap20.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap20.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap20.clear();
          }        }

      }
      else if(decisionCond15.equals("u")){
        aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);;
        {//Call the sub-processes用户订购变更
          Map tmpMap21 = new HashMap();
          Object loopVar21 = null;
          int loopCount21 = 0;
          loopVar21 = new Object[]{null};
          loopCount21 = 1;
          for(int i=0;i < loopCount21;i++){
          tmpMap21.clear();
          tmpMap21.put("aSoUserData",aSoUserData);
          tmpMap21.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap21.put("aSoOfferData",aSoOfferData);
          tmpMap21.put("aOrderValueChain",aOrderValueChain);
          tmpMap21.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdModiUserOffer",tmpMap21);
          aOVOrderOffer = (tmpMap21.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap21.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap21.clear();
          }        }

      }
      else if(decisionCond15.equals("d")){
        aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];java.util.Map map=new java.util.HashMap(); map.put("OFFER_ID",String.valueOf(aSoOfferData.getOfferId())); map.put("BUSINESS_ID",String.valueOf(aSoOfferData.getBusinessId())); aOrderValueChain=com.ai.omframe.util.OrderProcUtil.processChgProdOVChain(aOrderValueChain,map);aOrderValueChain.setINST_STATE_OFFER_USER(7);;
        {//Call the sub-processes退订增值策划
          Map tmpMap8 = new HashMap();
          Object loopVar8 = null;
          int loopCount8 = 0;
          loopVar8 = new Object[]{null};
          loopCount8 = 1;
          for(int i=0;i < loopCount8;i++){
          tmpMap8.clear();
          tmpMap8.put("aSoUserData",aSoUserData);
          tmpMap8.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap8.put("aSoOfferData",aSoOfferData);
          tmpMap8.put("aOrderValueChain",aOrderValueChain);
          tmpMap8.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap8);
          aOVOrderOffer = (tmpMap8.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap8.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap8.clear();
          }        }

      }
      else{
      }
      aOfferIndex++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(ISoOfferData[] aSoOfferDatas,String aOper,String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoOfferDatas",aSoOfferDatas);
    $inParameter.put("aOper",aOper);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    IOVOrderCustomer realReturn = executeInner( aSoOfferDatas, aOper, REGION_ID, aOVOrderCustomer);
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
    ISoOfferData[] aSoOfferDatas = ($vmParameters.get("aSoOfferDatas") == null)?null:(ISoOfferData[])VMDataType.transfer($vmParameters.get("aSoOfferDatas"),ISoOfferData[].class);
    String aOper = ($vmParameters.get("aOper") == null)?"":(String)VMDataType.transfer($vmParameters.get("aOper"),String.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
IOVOrderCustomer realReturn = execute(aSoOfferDatas,aOper,REGION_ID,aOVOrderCustomer);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}