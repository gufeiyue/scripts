package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstSrvpkg;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.instance.ivalues.IInsProdValue;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  java.sql.Timestamp;
public class OrdDelBatchSrvpkgs_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelBatchSrvpkgs_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,long insOfferId,String REGION_ID) throws Exception{
	int srvpkgIndex = 0;
	IOVOrderServicePkg aOVOrderServicePkg = null;
	IInsProdValue[] insSrvpkgValues = null;
	long userId = 0;
	IInsProdValue aInsSrvpkgValue = null;
	boolean _TASK_JUGE_RESULT = false;
	ISoServicePkgData soServicepkgData = null;
	String userRegionId = "";
	long OfferUserRelatId = 0;
	ISoServicePkgData[] aOperSoServicePkgDatas = null;
	Timestamp expireDate = null;
	Timestamp validDate = null;
	long state = 0;
    String decisionCond18 = String.valueOf(null!=aOVOrdOffOrdUser);
    if(decisionCond18.equals("false")){
    }
    else if(decisionCond18.equals("true")){
      OfferUserRelatId = aOVOrdOffOrdUser.getOrdOffOrdUserValue().getOfferUserRelatId();userId=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getUserId(); expireDate=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getObjExpireDate();userRegionId=com.ai.omframe.util.SoUtil.getRegionByUserId(String.valueOf(userId)); int delState=com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; java.sql.Timestamp sysdate = com.ai.common.util.TimeUtil.getPrimaryDataSourceSysDate(); if (expireDate != null && expireDate.after(com.ai.common.util.TimeUtil.addOrMinusSecond(sysdate.getTime(), com.ai.omframe.util.SoUtil.getExpireDelayTime()))) {delState = com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE; } aOrderValueChain.setSTATE_SRVPKG(delState); aOperSoServicePkgDatas = aSoUserData.getSoServicePkgData();validDate=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getObjEffectiveDate();;
      insSrvpkgValues = ((com.ai.omframe.instance.service.interfaces.IInstSrvpkg)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstSrvpkg",com.ai.omframe.instance.service.interfaces.IInstSrvpkg.class)).getInstSrvpkgByInstOffAndUserId(insOfferId,userId,OfferUserRelatId,userRegionId);
      while(insSrvpkgValues!=null&&srvpkgIndex<insSrvpkgValues.length){
        aInsSrvpkgValue=insSrvpkgValues[srvpkgIndex];;
        soServicepkgData = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoServicePkgDataByInsProdValue(aOperSoServicePkgDatas,aInsSrvpkgValue);
        if (soServicepkgData.getState()<=0){state=aOrderValueChain.getSTATE_SRVPKG();} else {state=soServicepkgData.getState();};soServicepkgData.setExpireDate(expireDate);soServicepkgData.setValidDate(validDate);soServicepkgData.setState(state);;
        {//Call the sub-processes删除产品
          Map tmpMap17 = new HashMap();
          Object loopVar17 = null;
          int loopCount17 = 0;
          loopVar17 = new Object[]{null};
          loopCount17 = 1;
          for(int i=0;i < loopCount17;i++){
          tmpMap17.clear();
          tmpMap17.put("aOrderValueChain",aOrderValueChain);
          tmpMap17.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
          tmpMap17.put("aInsSrvpkgValue",aInsSrvpkgValue);
          tmpMap17.put("soServicepkgData",soServicepkgData);
          tmpMap17.put("REGION_ID",REGION_ID);
          tmpMap17.put("FLOWOBJECT_TYPE",new Long(insOfferId));
          tmpMap17.put("offerInsId",new Long(insOfferId));

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdDelSrvpkg",tmpMap17);

          tmpMap17.clear();
          }        }

        srvpkgIndex++;
      }
    }
    else{logger.warn("aOVOrdOffOrdUser不为空Conditions do not match");}
  }
  public void execute(ISoUserData aSoUserData,OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,long insOfferId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("insOfferId",new Long(insOfferId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aOrderValueChain, aOVOrdOffOrdUser, insOfferId, REGION_ID);
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
    long insOfferId = ($vmParameters.get("insOfferId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("insOfferId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aOrderValueChain,aOVOrdOffOrdUser,insOfferId,REGION_ID);
  return $vmParameters;
  }
}