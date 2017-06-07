package com.asiainfo.crm.so.vm.rboss.netnp;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV;
import  java.util.List;
import  com.asiainfo.crm.so.order.rboss.ivalues.IBOOrdxTransTrackValue;
public class OrdTransTrackFinish_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransTrackFinish_AIProcess.class);
  protected void executeInner(List ordTransTrackList,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int aOrdxTransTrackIdx = 0;
	IBOOrdxTransTrackValue aIterOrdxTransTrack = null;
    ;
    while(ordTransTrackList != null && aOrdxTransTrackIdx < ordTransTrackList.size()){
      aIterOrdxTransTrack = (com.asiainfo.crm.so.order.rboss.ivalues.IBOOrdxTransTrackValue)ordTransTrackList.get(aOrdxTransTrackIdx);REGION_ID=aIterOrdxTransTrack.getRegionId();;
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV.class)).saveOrdxTransTrackToFinish(aIterOrdxTransTrack,REGION_ID);
      aOrdxTransTrackIdx++;
    }
  }
  public void execute(List ordTransTrackList,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("ordTransTrackList",ordTransTrackList);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( ordTransTrackList, REGION_ID);
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
    List ordTransTrackList = ($vmParameters.get("ordTransTrackList") == null)?new java.util.ArrayList():(List)VMDataType.transfer($vmParameters.get("ordTransTrackList"),List.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(ordTransTrackList,REGION_ID);
  return $vmParameters;
  }
}