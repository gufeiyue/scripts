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
public class OrdTransTackSave_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdTransTackSave_AIProcess.class);
  protected void executeInner(List ordUserIntransTrackList,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int aOrdxTransTrackIdx = 0;
	IBOOrdxTransTrackValue aIterOrdxTransTrack = null;
    ;
    while(ordUserIntransTrackList != null && aOrdxTransTrackIdx < ordUserIntransTrackList.size()){
      aIterOrdxTransTrack = (com.asiainfo.crm.so.order.rboss.ivalues.IBOOrdxTransTrackValue)ordUserIntransTrackList.get(aOrdxTransTrackIdx);REGION_ID=aIterOrdxTransTrack.getRegionId();;
      ((com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV",com.asiainfo.crm.so.order.rboss.service.interfaces.IOrdxTransTrackSV.class)).saveOrdxTransTrack(aIterOrdxTransTrack,REGION_ID);
      aOrdxTransTrackIdx++;
    }
  }
  public void execute(List ordUserIntransTrackList,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("ordUserIntransTrackList",ordUserIntransTrackList);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( ordUserIntransTrackList, REGION_ID);
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
    List ordUserIntransTrackList = ($vmParameters.get("ordUserIntransTrackList") == null)?new java.util.ArrayList():(List)VMDataType.transfer($vmParameters.get("ordUserIntransTrackList"),List.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(ordUserIntransTrackList,REGION_ID);
  return $vmParameters;
  }
}