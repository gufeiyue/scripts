package com.asiainfo.crm.so.vm.rboss.prodchange;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdBackCurrentOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdBackCurrentOffer_AIProcess.class);
  protected IOVOrderCustomer executeInner(IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,ISoOfferData aNextSoOfferData) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData aSoOfferData = null;
	ISoUserData aSoUserData = null;
	IOVOrderOffer aOVOrderOffer = null;
	ISoOfferData[] aSoOfferDatas = null;
	int aOfferIdx = 0;
	String aOperType = "";
    aSoOfferDatas = ((com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IPrjOrderVmHelperSV.class)).createSoOfferDataFromNextOfferIns(aNextSoOfferData,REGION_ID);
    while(aSoOfferDatas!=null&&aOfferIdx<aSoOfferDatas.length){
      aSoOfferData=aSoOfferDatas[aOfferIdx];aOperType=aSoOfferData.getOperType();;
      aSoOfferData.setParentBceData(aNextSoOfferData.getSoOrderData());aSoUserData=aSoOfferData.getSoRoleData()[0].getSoUserData()[0];;
      String decisionCond14 = String.valueOf(aOperType!=null&&"UPDATE".equals(aOperType));
      if(decisionCond14.equals("false")){
        String decisionCond15 = String.valueOf(aOperType!=null&&"CREATE".equals(aOperType));
        if(decisionCond15.equals("true")){
          {//Call the sub-processes订购增值策划
            Map tmpMap16 = new HashMap();
            Object loopVar16 = null;
            int loopCount16 = 0;
            loopVar16 = new Object[]{null};
            loopCount16 = 1;
            for(int i=0;i < loopCount16;i++){
            tmpMap16.clear();
            tmpMap16.put("aSoUserData",aSoUserData);
            tmpMap16.put("aOVOrderCustomer",aOVOrderCustomer);
            tmpMap16.put("aSoOfferData",aSoOfferData);
            tmpMap16.put("aOrderValueChain",aOrderValueChain);
            tmpMap16.put("REGION_ID",REGION_ID);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdOpenNewOffer",tmpMap16);
            aOVOrderOffer = (tmpMap16.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap16.get("aOVOrderOffer"),IOVOrderOffer.class);

            tmpMap16.clear();
            }          }

        }
        else if(decisionCond15.equals("false")){
          String decisionCond20 = String.valueOf(aOperType!=null&&("LOGOUT".equals(aOperType)||"UNSUBSCRIBE".equals(aOperType)));
          if(decisionCond20.equals("false")){
          }
          else if(decisionCond20.equals("true")){
            {//Call the sub-processes设置主策划对象
              Map tmpMap17 = new HashMap();
              Object loopVar17 = null;
              int loopCount17 = 0;
              loopVar17 = new Object[]{null};
              loopCount17 = 1;
              for(int i=0;i < loopCount17;i++){
              tmpMap17.clear();
              tmpMap17.put("aSoOfferData",aSoOfferData);
              tmpMap17.put("aOVOrderCustomer",aOVOrderCustomer);
              tmpMap17.put("aOrderValueChain",aOrderValueChain);
              tmpMap17.put("REGION_ID",REGION_ID);
              tmpMap17.put("aSoUserData",aSoUserData);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib2.OrdQuitOffer",tmpMap17);
              aOVOrderCustomer = (tmpMap17.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer(tmpMap17.get("aOVOrderCustomer"),IOVOrderCustomer.class);

              tmpMap17.clear();
              }            }

          }
          else{logger.warn("条件判断Conditions do not match");}
        }
        else{logger.warn("条件判断Conditions do not match");}
      }
      else if(decisionCond14.equals("true")){
        {//Call the sub-processes用户订购变更
          Map tmpMap3 = new HashMap();
          Object loopVar3 = null;
          int loopCount3 = 0;
          loopVar3 = new Object[]{null};
          loopCount3 = 1;
          for(int i=0;i < loopCount3;i++){
          tmpMap3.clear();
          tmpMap3.put("aSoUserData",aSoUserData);
          tmpMap3.put("aOVOrderCustomer",aOVOrderCustomer);
          tmpMap3.put("aSoOfferData",aSoOfferData);
          tmpMap3.put("aOrderValueChain",aOrderValueChain);
          tmpMap3.put("REGION_ID",REGION_ID);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.prodchange.OrdModiOffer",tmpMap3);
          aOVOrderOffer = (tmpMap3.get("aOVOrderOffer") == null)?null:(IOVOrderOffer)VMDataType.transfer(tmpMap3.get("aOVOrderOffer"),IOVOrderOffer.class);

          tmpMap3.clear();
          }        }

      }
      else{logger.warn("条件判断Conditions do not match");}
      aOfferIdx++;
    }
    return aOVOrderCustomer;
  }
  public IOVOrderCustomer execute(IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain,String REGION_ID,ISoOfferData aNextSoOfferData) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aNextSoOfferData",aNextSoOfferData);
try{
    IOVOrderCustomer realReturn = executeInner( aOVOrderCustomer, aOrderValueChain, REGION_ID, aNextSoOfferData);
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
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    ISoOfferData aNextSoOfferData = ($vmParameters.get("aNextSoOfferData") == null)?null:(ISoOfferData)VMDataType.transfer($vmParameters.get("aNextSoOfferData"),ISoOfferData.class);
IOVOrderCustomer realReturn = execute(aOVOrderCustomer,aOrderValueChain,REGION_ID,aNextSoOfferData);
    $vmParameters.put("aOVOrderCustomer",realReturn);
    return $vmParameters;
  }
}