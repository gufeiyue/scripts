package com.asiainfo.crm.so.vm.rboss.netnp;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV;
import  java.util.List;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrdTransTrack_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransTrack_AIProcess.class);
  protected void executeInner(String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
	List ordTransTrackList = new java.util.ArrayList();
    ordTransTrackList = new java.util.ArrayList();;
    ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdTransRegionWithNumSV.class)).createOrdxTransTrack(aOVOrderCustomer,ordTransTrackList,REGION_ID);
    {//Call the sub-processes转省内携号轨迹实例
      Map tmpMap12 = new HashMap();
      Object loopVar12 = null;
      int loopCount12 = 0;
      loopVar12 = new Object[]{null};
      loopCount12 = 1;
      for(int i=0;i < loopCount12;i++){
      tmpMap12.clear();
      tmpMap12.put("ordTransTrackList",ordTransTrackList);
      tmpMap12.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransTrackIns",tmpMap12);

      tmpMap12.clear();
      }    }

    {//Call the sub-processes携转轨迹订单竣工
      Map tmpMap13 = new HashMap();
      Object loopVar13 = null;
      int loopCount13 = 0;
      loopVar13 = new Object[]{null};
      loopCount13 = 1;
      for(int i=0;i < loopCount13;i++){
      tmpMap13.clear();
      tmpMap13.put("ordTransTrackList",ordTransTrackList);
      tmpMap13.put("REGION_ID",REGION_ID);

      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.rboss.netnp.OrdTransTrackFinish",tmpMap13);

      tmpMap13.clear();
      }    }

  }
  public void execute(String REGION_ID,IOVOrderCustomer aOVOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrderCustomer",aOVOrderCustomer);
try{
    executeInner( REGION_ID, aOVOrderCustomer);
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
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrderCustomer aOVOrderCustomer = ($vmParameters.get("aOVOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("aOVOrderCustomer"),IOVOrderCustomer.class);
execute(REGION_ID,aOVOrderCustomer);
  return $vmParameters;
  }
}