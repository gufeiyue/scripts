package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
public class OrdNewBatchSrvpkgs_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdNewBatchSrvpkgs_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,String REGION_ID) throws Exception{
	ISoServicePkgData aSoServicePkgData = null;
	int srvpkgIndex = 0;
	IOVOrderServicePkg aOVOrderServicePkg = null;
	boolean _TASK_JUGE_RESULT = false;
    while(aSoUserData.getSoServicePkgData()!=null&&srvpkgIndex<aSoUserData.getSoServicePkgData().length){
      aSoServicePkgData=aSoUserData.getSoServicePkgData()[srvpkgIndex];
      {//Call the sub-processes新建单个产品
        Map tmpMap17 = new HashMap();
        Object loopVar17 = null;
        int loopCount17 = 0;
        loopVar17 = new Object[]{null};
        loopCount17 = 1;
        for(int i=0;i < loopCount17;i++){
        tmpMap17.clear();
        tmpMap17.put("aSoUserData",aSoUserData);
        tmpMap17.put("aOrderValueChain",aOrderValueChain);
        tmpMap17.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
        tmpMap17.put("aSoServicePkgData",aSoServicePkgData);
        tmpMap17.put("REGION_ID",REGION_ID);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewSrvpkg",tmpMap17);

        tmpMap17.clear();
        }      }

      srvpkgIndex++;
    }
  }
  public void execute(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aOrderValueChain, aOVOrdOffOrdUser, REGION_ID);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aOrderValueChain,aOVOrdOffOrdUser,REGION_ID);
  return $vmParameters;
  }
}