package com.ai.omframe.order.vm.lib1;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.data.ivalues.ISoOfferData;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
import  com.ai.omframe.order.valuebean.OrderValueChain;
public class OrdDelUserRelateOffer_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelUserRelateOffer_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain) throws Exception{
	String _TASK_JUGE_RESULT = "";
	ISoOfferData[] aRelatSoOfferDatas = null;
	ISoOfferData aRelatSoOfferData = null;
	ISoUserData aRelatSoUserData = null;
	String aBillId = "";
	int offerDataIndex = 0;
    aRelatSoOfferDatas = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getRelateSoOfferDatas(aSoUserData,REGION_ID);
    while(null!=aRelatSoOfferDatas&&aRelatSoOfferDatas.length>0&&offerDataIndex<aRelatSoOfferDatas.length){
      aRelatSoOfferData=aRelatSoOfferDatas[offerDataIndex];aRelatSoUserData=aRelatSoOfferData.getSoRoleData()[0].getSoUserData()[0];aRelatSoUserData.setExpireDate(aSoUserData.getExpireDate());;
      aRelatSoOfferData.setBusinessId(com.ai.omframe.util.SoServiceFactory.getOrderVmHelperSV().getOfferBusinessId(aRelatSoOfferData.getOfferId(), com.ai.omframe.util.SoServiceFactory.getOmConstSV().getOperTypeLogout()));;
      {//Call the sub-processes退订增值策划
        Map tmpMap6 = new HashMap();
        Object loopVar6 = null;
        int loopCount6 = 0;
        loopVar6 = new Object[]{null};
        loopCount6 = 1;
        for(int i=0;i < loopCount6;i++){
        tmpMap6.clear();
        tmpMap6.put("aSoUserData",aRelatSoUserData);
        tmpMap6.put("aOVOrderCustomer",aOVOrderCustomer);
        tmpMap6.put("aSoOfferData",aRelatSoOfferData);
        tmpMap6.put("aOrderValueChain",aOrderValueChain);
        tmpMap6.put("REGION_ID",REGION_ID);
        tmpMap6.put("aBillId",aBillId);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib1.OrdDropOffer",tmpMap6);

        tmpMap6.clear();
        }      }

      offerDataIndex++;;
    }
  }
  public void execute(ISoUserData aSoUserData,String REGION_ID,IOVOrderCustomer aOVOrderCustomer,OrderValueChain aOrderValueChain) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
try{
    executeInner( aSoUserData, REGION_ID, aOVOrderCustomer, aOrderValueChain);
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
    ISoUserData aSoUserData = ($vmParameters.get("aSoUserData") == null)?null:(ISoUserData)VMDataType.transfer($vmParameters.get("aSoUserData"),ISoUserData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
execute(aSoUserData,REGION_ID,aOVOrderCustomer,aOrderValueChain);
  return $vmParameters;
  }
}