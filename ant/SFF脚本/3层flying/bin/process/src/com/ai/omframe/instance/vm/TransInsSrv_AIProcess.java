package com.ai.omframe.instance.vm;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstProcessBase;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
public class TransInsSrv_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(TransInsSrv_AIProcess.class);
  protected void executeInner(IOVOrdOffOrdUser aOVOrdOffOrdUser,long offerInsId,String REGION_ID) throws Exception{
	int aIndex = 0;
	IOVOrderServicePkg aOVOrderServicePkg = null;
	boolean _TASK_JUGE_RESULT = false;
	long userId = 0;
    userId=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getUserId();;
    while(null!=aOVOrdOffOrdUser.getOVOrderSrvPkg()&&aIndex<aOVOrdOffOrdUser.getOVOrderSrvPkg().length){
      aOVOrderServicePkg=aOVOrdOffOrdUser.getOVOrderSrvPkg()[aIndex];
      ((com.ai.omframe.instance.service.interfaces.IInstProcessBase)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstProcessBase",com.ai.omframe.instance.service.interfaces.IInstProcessBase.class)).createInstSrvpkg(aOVOrdOffOrdUser,aOVOrderServicePkg,offerInsId,userId);
      aIndex++;
    }
  }
  public void execute(IOVOrdOffOrdUser aOVOrdOffOrdUser,long offerInsId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("offerInsId",new Long(offerInsId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aOVOrdOffOrdUser, offerInsId, REGION_ID);
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
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    long offerInsId = ($vmParameters.get("offerInsId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerInsId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aOVOrdOffOrdUser,offerInsId,REGION_ID);
  return $vmParameters;
  }
}