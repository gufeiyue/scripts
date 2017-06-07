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
import  com.ai.omframe.instance.service.interfaces.IInstanceQrySV;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.service.interfaces.IOrderVmHelperSV;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.instance.ivalues.IInsProdValue;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.order.ivalues.IOVOrdSrvPkgOrdService;
import  com.ai.omframe.instance.ivalues.IInsProdInsSrvValue;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.instance.ivalues.IInsSrvAttrValue;
import  com.ai.omframe.instance.ivalues.IInsPriceAttrValue;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
import  com.ai.omframe.order.data.ivalues.ISoServiceData;
public class OrdDelSrvpkg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdDelSrvpkg_AIProcess.class);
  protected void executeInner(OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,IInsProdValue aInsSrvpkgValue,ISoServicePkgData soServicepkgData,String REGION_ID,long offerInsId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int srvpkgSrvIndex = 0;
	IOVOrderServicePkg aOVOrderServicePkg = null;
	IOVOrdSrvPkgOrdService aOVOrdSrvPkgOrdService = null;
	IInsProdInsSrvValue[] iInsSrvpkgInsSrvValues = null;
	long userId = 0;
	long aInsSrvpkgId = 0;
	IInsProdInsSrvValue aInsSrvpkgInsSrvValue = null;
	int srvAttrIndex = 0;
	IInsSrvAttrValue[] insSrvAttrValues = null;
	IInsSrvAttrValue aInsSrvAttrValue = null;
	long insSrvpkgSrvRelateId = 0;
	int srvpkgPriceIndex = 0;
	IInsPriceAttrValue[] insPriceAttrValues = null;
	IInsPriceAttrValue aInsPriceAttrValue = null;
	int price_inst_type = 1;
	ISoAttrData aSoAttrData = null;
	ISoAttrData aSrvpkgPriceAttrData = null;
	String userRegionId = "";
	Map isBatchAttrMap = new java.util.HashMap();
	Boolean isBatchAttrMapObj = new Boolean(false);
	int validTypeSrvpkSrv = 0;
	int validTypesrvAttr = 0;
	int validTypesrvpkPriceAttr = 0;
	ISoServiceData soServiceData = null;
    userId=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getUserId(); userRegionId=com.ai.omframe.util.SoUtil.getRegionByUserId(String.valueOf(userId)); aSrvpkgPriceAttrData=new com.ai.omframe.order.data.valuebean.SoAttrData(); aSrvpkgPriceAttrData.setExpireDate(soServicepkgData.getExpireDate()); int delState=com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; java.sql.Timestamp sysdate = com.ai.common.util.TimeUtil.getPrimaryDataSourceSysDate(); if (soServicepkgData.getExpireDate() != null && soServicepkgData.getExpireDate().after(com.ai.common.util.TimeUtil.addOrMinusSecond(sysdate.getTime(), com.ai.omframe.util.SoUtil.getExpireDelayTime()))) {delState = com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE; } aOrderValueChain.setSTATE_SRVPKG(delState); aOrderValueChain.setSTATE_SRVPKG_SRV(delState); aOrderValueChain.setSTATE_SRV_ATTR(delState); aOrderValueChain.setSTATE_PRICE_ATTR_SRVPKG(delState);validTypeSrvpkSrv=aOrderValueChain.getVALID_TYPE_SRVPKG_SRV(); validTypesrvAttr=aOrderValueChain.getVALID_TYPE_SRV_ATTR(); validTypesrvpkPriceAttr=aOrderValueChain.getVALID_TYPE_PRICE_ATTR_SRVPKG();;
    String decisionCond7 = String.valueOf(null!=aInsSrvpkgValue);
    if(decisionCond7.equals("false")){
    }
    else if(decisionCond7.equals("true")){
      aInsSrvpkgId=aInsSrvpkgValue.getProdInstId();;
      aOVOrderServicePkg = ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).createOVOrderSrvPkgFromInstValue(aOVOrdOffOrdUser,soServicepkgData,aInsSrvpkgValue,aOrderValueChain,REGION_ID);
      iInsSrvpkgInsSrvValues = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstServpkgInstSrvByInstSrvpkgId(offerInsId,aInsSrvpkgId,userId,validTypeSrvpkSrv);
      while(iInsSrvpkgInsSrvValues!=null&&srvpkgSrvIndex<iInsSrvpkgInsSrvValues.length){
        aInsSrvpkgInsSrvValue=iInsSrvpkgInsSrvValues[srvpkgSrvIndex];insSrvpkgSrvRelateId=aInsSrvpkgInsSrvValue.getProdSrvRelatId();;
        soServiceData = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoServiceDataByServiceInstValue(soServicepkgData,aInsSrvpkgInsSrvValue);
        String decisionCond15 = String.valueOf(null!=aInsSrvpkgInsSrvValue);
        if(decisionCond15.equals("false")){
        }
        else if(decisionCond15.equals("true")){
          aOVOrdSrvPkgOrdService = ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOVOrdSrvpkgOrdSrvFromInstValue(aOVOrderServicePkg,soServicepkgData,aInsSrvpkgInsSrvValue,aOrderValueChain,REGION_ID);
          insSrvAttrValues = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstSrvAttrByRelateInstId(insSrvpkgSrvRelateId,userId,validTypesrvAttr);
          while(insSrvAttrValues!=null&&srvAttrIndex<insSrvAttrValues.length){
            aInsSrvAttrValue=insSrvAttrValues[srvAttrIndex];
            aSoAttrData = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).getSoAttrDataByInsSrvAttrValue(soServiceData,aInsSrvAttrValue);
            isBatchAttrMap = ((com.ai.omframe.order.service.interfaces.IOrderVmHelperSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrderVmHelperSV",com.ai.omframe.order.service.interfaces.IOrderVmHelperSV.class)).checkAttrBatch(aInsSrvAttrValue);
            isBatchAttrMapObj=(java.lang.Boolean)isBatchAttrMap.get(aInsSrvAttrValue.getAttrId()+"");aSoAttrData.setBatch(isBatchAttrMapObj.booleanValue());aSoAttrData.setExpireDate(soServicepkgData.getExpireDate());;
            String decisionCond24 = String.valueOf(null!=aInsSrvAttrValue);
            if(decisionCond24.equals("true")){
              ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOrdSrvAttrValueFromInst(aOVOrdSrvPkgOrdService,aInsSrvAttrValue,aSoAttrData,aOrderValueChain,REGION_ID);
              ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueByAttr(aOVOrdOffOrdUser,aOVOrderServicePkg,aSoAttrData,aOrderValueChain,REGION_ID);
            }
            else if(decisionCond24.equals("false")){
            }
            else{logger.warn("如果属性实例不为空Conditions do not match");}
            srvAttrIndex++;
          }
        }
        else{logger.warn("服务包包含的服务实例不为空Conditions do not match");}
        srvpkgSrvIndex++;
      }
      insPriceAttrValues = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstPriceAttrByRelatInstId(aInsSrvpkgId,userId,price_inst_type,validTypesrvpkPriceAttr);
      while(insPriceAttrValues!=null&&srvpkgPriceIndex<insPriceAttrValues.length){
        aInsPriceAttrValue=insPriceAttrValues[srvpkgPriceIndex];
        String decisionCond35 = String.valueOf(null!=aInsPriceAttrValue);
        if(decisionCond35.equals("false")){
        }
        else if(decisionCond35.equals("true")){
          ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createSrvPkgOrderPriceAttrFromInst(aOVOrderServicePkg,aInsPriceAttrValue,aSrvpkgPriceAttrData,aOrderValueChain,REGION_ID);
        }
        else{logger.warn("资费属性实例不为空Conditions do not match");}
        srvpkgPriceIndex++;
      }
    }
    else{logger.warn("服务包实例不为空Conditions do not match");}
  }
  public void execute(OrderValueChain aOrderValueChain,IOVOrdOffOrdUser aOVOrdOffOrdUser,IInsProdValue aInsSrvpkgValue,ISoServicePkgData soServicepkgData,String REGION_ID,long offerInsId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aInsSrvpkgValue",aInsSrvpkgValue);
    $inParameter.put("soServicepkgData",soServicepkgData);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("offerInsId",new Long(offerInsId));
try{
    executeInner( aOrderValueChain, aOVOrdOffOrdUser, aInsSrvpkgValue, soServicepkgData, REGION_ID, offerInsId);
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
    OrderValueChain aOrderValueChain = ($vmParameters.get("aOrderValueChain") == null)?new OrderValueChain():(OrderValueChain)VMDataType.transfer($vmParameters.get("aOrderValueChain"),OrderValueChain.class);
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    IInsProdValue aInsSrvpkgValue = ($vmParameters.get("aInsSrvpkgValue") == null)?null:(IInsProdValue)VMDataType.transfer($vmParameters.get("aInsSrvpkgValue"),IInsProdValue.class);
    ISoServicePkgData soServicepkgData = ($vmParameters.get("soServicepkgData") == null)?null:(ISoServicePkgData)VMDataType.transfer($vmParameters.get("soServicepkgData"),ISoServicePkgData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long offerInsId = ($vmParameters.get("offerInsId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerInsId"),long.class)).longValue();
execute(aOrderValueChain,aOVOrdOffOrdUser,aInsSrvpkgValue,soServicepkgData,REGION_ID,offerInsId);
  return $vmParameters;
  }
}