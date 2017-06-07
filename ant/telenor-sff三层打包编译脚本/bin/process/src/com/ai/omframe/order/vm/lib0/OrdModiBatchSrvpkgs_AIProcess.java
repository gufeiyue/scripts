package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstanceQrySV;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.instance.ivalues.IInsProdValue;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
public class OrdModiBatchSrvpkgs_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModiBatchSrvpkgs_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
	IOVOrderServicePkg aOVOrderServicePkg = null;
	IInsProdValue aInsSrvpkgValue = null;
	int srvpkgIndex = 0;
	ISoServicePkgData aSoServicePkgData = null;
	long insOfferId = 0;
	long userId = 0;
	long srvpkgId = 0;
	long srvpkgSrvRelatId = 0;
	boolean _TASK_JUGE_RESULT = false;
	int validTypeSrvpk = 0;
	long OfferUserRelatId = 0;
	long insSrvPkgId = 0;
    OfferUserRelatId = aOVOrdOffOrdUser.getOrdOffOrdUserValue().getOfferUserRelatId();userId=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getUserId();insOfferId=aSoUserData.getSoRoleData().getSoOfferData().getInsOfferId();validTypeSrvpk=aOrderValueChain.getVALID_TYPE_SRVPKG();;
    while(aSoUserData.getSoServicePkgData()!=null&&srvpkgIndex<aSoUserData.getSoServicePkgData().length){
      aSoServicePkgData=aSoUserData.getSoServicePkgData()[srvpkgIndex]; insSrvPkgId = aSoServicePkgData.getSrvPkgInstId();srvpkgId = aSoServicePkgData.getServicePkgId(); aOrderValueChain.setSTATE_SRVPKG(0); aOrderValueChain.setSTATE_SRVPKG_SRV(0); aOrderValueChain.setSTATE_SRV_ATTR(0); aOrderValueChain.setSTATE_PRICE_ATTR_SRVPKG(0);;
      String decisionCond11 = String.valueOf(aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_IGNORE_STATE);
      if(decisionCond11.equals("false")){
        String decisionCond18 = String.valueOf(aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
        if(decisionCond18.equals("false")){
          String decisionCond21 = String.valueOf(aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE);
          if(decisionCond21.equals("true")){
            aInsSrvpkgValue = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstSrvpkgByInstPkgId(insOfferId,userId,srvpkgId,OfferUserRelatId,validTypeSrvpk,insSrvPkgId);
            {//Call the sub-processes删除产品
              Map tmpMap22 = new HashMap();
              Object loopVar22 = null;
              int loopCount22 = 0;
              loopVar22 = new Object[]{null};
              loopCount22 = 1;
              for(int i=0;i < loopCount22;i++){
              tmpMap22.clear();
              tmpMap22.put("aOrderValueChain",aOrderValueChain);
              tmpMap22.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
              tmpMap22.put("aInsSrvpkgValue",aInsSrvpkgValue);
              tmpMap22.put("soServicepkgData",aSoServicePkgData);
              tmpMap22.put("REGION_ID",REGION_ID);
              tmpMap22.put("offerInsId",new Long(insOfferId));

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdDelSrvpkg",tmpMap22);

              tmpMap22.clear();
              }            }

          }
          else if(decisionCond21.equals("false")){
            {//Call the sub-processes修改单个产品
              Map tmpMap17 = new HashMap();
              Object loopVar17 = null;
              int loopCount17 = 0;
              loopVar17 = new Object[]{null};
              loopCount17 = 1;
              for(int i=0;i < loopCount17;i++){
              tmpMap17.clear();
              tmpMap17.put("aSoUserData",aSoUserData);
              tmpMap17.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
              tmpMap17.put("aOrderValueChain",aOrderValueChain);
              tmpMap17.put("aSoServicePkgData",aSoServicePkgData);
              tmpMap17.put("REGION_ID",REGION_ID);
              tmpMap17.put("offerInsId",new Long(insOfferId));

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdModiSrvpkg",tmpMap17);

              tmpMap17.clear();
              }            }

          }
          else{logger.warn("是否是删除的服务包Conditions do not match");}
        }
        else if(decisionCond18.equals("true")){
          {//Call the sub-processes新增服务包
            Map tmpMap20 = new HashMap();
            Object loopVar20 = null;
            int loopCount20 = 0;
            loopVar20 = new Object[]{null};
            loopCount20 = 1;
            for(int i=0;i < loopCount20;i++){
            tmpMap20.clear();
            tmpMap20.put("aSoUserData",aSoUserData);
            tmpMap20.put("aOrderValueChain",aOrderValueChain);
            tmpMap20.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
            tmpMap20.put("aSoServicePkgData",aSoServicePkgData);
            tmpMap20.put("REGION_ID",REGION_ID);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.ai.omframe.order.vm.lib0.OrdNewSrvpkg",tmpMap20);

            tmpMap20.clear();
            }          }

        }
        else{logger.warn("是否是新增的服务包Conditions do not match");}
      }
      else if(decisionCond11.equals("true")){
      }
      else{logger.warn("不需要处理的服务包Conditions do not match");}
      srvpkgIndex++;
    }
  }
  public void execute(ISoUserData aSoUserData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( aSoUserData, aOVOrdOffOrdUser, aOrderValueChain, REGION_ID);
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
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(aSoUserData,aOVOrdOffOrdUser,aOrderValueChain,REGION_ID);
  return $vmParameters;
  }
}