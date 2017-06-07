package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater;
import  com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.order.data.ivalues.ISoServiceData;
import  com.ai.omframe.order.ivalues.IOVOrdSrvPkgOrdService;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
public class OrdNewSrvpkg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewSrvpkg_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,ISoServicePkgData aSoServicePkgData,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderServicePkg aOVOrderServicePkg = null;
	ISoServiceData aSoServiceData = null;
	IOVOrdSrvPkgOrdService aOVOrdSrvPkgOrdService = null;
	int srvpkgSrvIndex = 0;
	ISoAttrData aSoAttrData = null;
	ISoAttrData aSrvpkgPriceAttrData = null;
	int srvAttrIndex = 0;
	int prcplnIndex = 0;
    aOVOrderServicePkg = ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).createOVOrderSrvPkg(aOVOrdOffOrdUser,aSoServicePkgData,aOrderValueChain,REGION_ID);
    String decisionCond4 = String.valueOf(null!=aOVOrderServicePkg&&aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
    if(decisionCond4.equals("true")){
      while(aSoServicePkgData!=null&&aSoServicePkgData.getServiceData().length>0&&srvpkgSrvIndex<aSoServicePkgData.getServiceData().length){
        aSoServiceData=aSoServicePkgData.getServiceData()[srvpkgSrvIndex];
        aOVOrdSrvPkgOrdService = ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOVOrdSrvpkgOrdSrv(aOVOrderServicePkg,aSoServiceData,aOrderValueChain,REGION_ID);
        while(null!=aSoServiceData.getServiceAttrDatas()&&srvAttrIndex<aSoServiceData.getServiceAttrDatas().length){
          aSoAttrData=aSoServiceData.getServiceAttrDatas()[srvAttrIndex];;
          ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOrdSrvAttrValue(aOVOrdSrvPkgOrdService,aSoAttrData,aOrderValueChain,REGION_ID);
          ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueByAttr(aOVOrdOffOrdUser,aOVOrderServicePkg,aSoAttrData,aOrderValueChain,REGION_ID);
          srvAttrIndex++;
        }
        srvpkgSrvIndex++;
      }
      while(aSoServicePkgData.getPriceAttrData()!=null&&prcplnIndex<aSoServicePkgData.getPriceAttrData().length){
        aSrvpkgPriceAttrData=aSoServicePkgData.getPriceAttrData()[prcplnIndex];
        ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createSrvPkgOrderPriceAttr(aOVOrderServicePkg,aSrvpkgPriceAttrData,aOrderValueChain,REGION_ID);
        prcplnIndex++;
      }
    }
    else if(decisionCond4.equals("false")){
    }
    else{logger.warn("服务包订单非空且服务包为新装Conditions do not match");}
  }
  public void execute(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,ISoServicePkgData aSoServicePkgData,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aSoServicePkgData",aSoServicePkgData);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aOrderValueChain, aOVOrdOffOrdUser, aSoServicePkgData, REGION_ID);
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
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    ISoServicePkgData aSoServicePkgData = ($vmParameters.get("aSoServicePkgData") == null)?null:(ISoServicePkgData)VMDataType.transfer($vmParameters.get("aSoServicePkgData"),ISoServicePkgData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aOrderValueChain,aOVOrdOffOrdUser,aSoServicePkgData,REGION_ID);
  return $vmParameters;
  }
}