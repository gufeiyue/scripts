package com.ai.omframe.order.vm.modifyOrd;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
public class OrdModifyUser_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModifyUser_AIProcess.class);
  protected void executeInner() throws Exception{
	String _TASK_JUGE_RESULT = "";
	int userState = 0;
    userState=4;
    String decisionCond6 = String.valueOf(userState);
    if(decisionCond6.equals("2")){
      logger.warn("Configure the node (更新用户订单数据)");
    }
    else if(decisionCond6.equals("4")){
    }
    else{
    }
  }
  public void execute() throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
try{
    executeInner();
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
execute();
  return $vmParameters;
  }
}