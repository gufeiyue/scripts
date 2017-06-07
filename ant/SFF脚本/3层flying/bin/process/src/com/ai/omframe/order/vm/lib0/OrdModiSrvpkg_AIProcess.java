package com.ai.omframe.order.vm.lib0;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.ai.omframe.instance.service.interfaces.IInstanceQrySV;
import  com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater;
import  com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater;
import  com.ai.omframe.instance.service.interfaces.IInstPriceAttr;
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater;
import  com.ai.omframe.order.data.ivalues.ISoUserData;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.valuebean.OrderValueChain;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.instance.ivalues.IInsProdValue;
import  com.ai.omframe.order.data.ivalues.ISoServicePkgData;
import  com.ai.omframe.order.data.ivalues.ISoServiceData;
import  com.ai.omframe.order.ivalues.IOVOrdSrvPkgOrdService;
import  com.ai.omframe.instance.ivalues.IInsProdInsSrvValue;
import  com.ai.omframe.order.data.ivalues.ISoAttrData;
import  com.ai.omframe.instance.ivalues.IInsSrvAttrValue;
import  com.ai.omframe.instance.ivalues.IInsPriceAttrValue;
public class OrdModiSrvpkg_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdModiSrvpkg_AIProcess.class);
  protected void executeInner(ISoUserData aSoUserData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,ISoServicePkgData aSoServicePkgData,String REGION_ID,long offerInsId) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderServicePkg aOVOrderServicePkg = null;
	IInsProdValue aInsSrvpkgValue = null;
	long insOfferId = 0;
	long userId = 0;
	long aSrvpkgInsId = 0;
	long srvpkgId = 0;
	ISoServiceData aSoServiceData = null;
	IOVOrdSrvPkgOrdService aOVOrdSrvPkgOrdService = null;
	IInsProdInsSrvValue aInsSrvpkgInsSrvValue = null;
	long srvpkgSrvRelatId = 0;
	int srvpkgSrvIndex = 0;
	long aServiceId = 0;
	ISoAttrData aSoAttrData = null;
	IInsSrvAttrValue aInsSrvAttrValue = null;
	int srvAttrIndex = 0;
	long attrId = 0;
	int srvpkgPriceIndex = 0;
	ISoAttrData aSrvpkgPriceAttrData = null;
	IInsPriceAttrValue aInsPriceAttrValue = null;
	int price_inst_type = 1;
	long priceAttrId = 0;
	String userRegionId = "";
	Map aInsSrvAttrMap = new java.util.HashMap();
	int validTypeSrvpk = 0;
	int validTypeSrvpkSrv = 0;
	int validTypesrvAttr = 0;
	int validTypesrvpkPriceAttr = 0;
	long OfferUserRelatId = 0;
    OfferUserRelatId = aOVOrdOffOrdUser.getOrdOffOrdUserValue().getOfferUserRelatId();userId=aOVOrdOffOrdUser.getOrdOffOrdUserValue().getUserId(); insOfferId=aSoUserData.getSoRoleData().getSoOfferData().getInsOfferId(); srvpkgId=aSoServicePkgData.getServicePkgId();aSrvpkgInsId = aSoServicePkgData.getSrvPkgInstId(); userRegionId=com.ai.omframe.util.SoUtil.getRegionByUserId(String.valueOf(userId)); if(aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_MODIFY_STATE||aSoServicePkgData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE){ aOrderValueChain.setSTATE_SRV_ATTR((int)aSoServicePkgData.getState()); aOrderValueChain.setSTATE_PRICE_ATTR_SRVPKG((int)aSoServicePkgData.getState()); aOrderValueChain.setSTATE_SRVPKG_SRV((int)aSoServicePkgData.getState());}else{ aOrderValueChain.setSTATE_SRV_ATTR(0); aOrderValueChain.setSTATE_PRICE_ATTR_SRVPKG(0); aOrderValueChain.setSTATE_SRVPKG_SRV(0);} validTypeSrvpk=aOrderValueChain.getVALID_TYPE_SRVPKG(); validTypeSrvpkSrv=aOrderValueChain.getVALID_TYPE_SRVPKG_SRV(); validTypesrvAttr=aOrderValueChain.getVALID_TYPE_SRV_ATTR(); validTypesrvpkPriceAttr=aOrderValueChain.getVALID_TYPE_PRICE_ATTR_SRVPKG();;
    aInsSrvpkgValue = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstSrvpkgByInstPkgId(insOfferId,userId,srvpkgId,OfferUserRelatId,validTypeSrvpk,aSrvpkgInsId);
    String decisionCond6 = String.valueOf(null!=aInsSrvpkgValue);
    if(decisionCond6.equals("true")){
      aOVOrderServicePkg = ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).createOVOrderSrvPkgFromInstValue(aOVOrdOffOrdUser,aSoServicePkgData,aInsSrvpkgValue,aOrderValueChain,REGION_ID);
      aSrvpkgInsId=aInsSrvpkgValue.getProdInstId();;
      while(aSoServicePkgData.getServiceData()!=null&&srvpkgSrvIndex<aSoServicePkgData.getServiceData().length){
        aSoServiceData=aSoServicePkgData.getServiceData()[srvpkgSrvIndex]; aServiceId=aSoServiceData.getServiceId();
        aInsSrvpkgInsSrvValue = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstServpkgInstSrvByInstId(offerInsId,aSrvpkgInsId,userId,aServiceId,validTypeSrvpkSrv);
        String decisionCond23 = String.valueOf(null!=aInsSrvpkgInsSrvValue);
        if(decisionCond23.equals("false")){
        }
        else if(decisionCond23.equals("true")){
          aOVOrdSrvPkgOrdService = ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOVOrdSrvpkgOrdSrvFromInstValue(aOVOrderServicePkg,aSoServicePkgData,aInsSrvpkgInsSrvValue,aOrderValueChain,REGION_ID);
          srvpkgSrvRelatId=aInsSrvpkgInsSrvValue.getProdSrvRelatId();;
          aInsSrvAttrMap = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInstSrvAttrByRelatInsIdAndUserId(srvpkgSrvRelatId,userId,validTypesrvAttr);
          while(aSoServiceData.getServiceAttrDatas()!=null&&srvAttrIndex<aSoServiceData.getServiceAttrDatas().length){
            aSoAttrData=aSoServiceData.getServiceAttrDatas()[srvAttrIndex]; attrId=aSoAttrData.getAttrId();;
            String decisionCond30 = String.valueOf(aSoAttrData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
            if(decisionCond30.equals("true")){
              ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOrdSrvAttrValue(aOVOrdSrvPkgOrdService,aSoAttrData,aOrderValueChain,REGION_ID);
              ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueByAttr(aOVOrdOffOrdUser,aOVOrderServicePkg,aSoAttrData,aOrderValueChain,REGION_ID);
            }
            else if(decisionCond30.equals("false")){
              aInsSrvAttrValue = ((com.ai.omframe.instance.service.interfaces.IInstanceQrySV)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.interfaces.IInstanceQrySV",com.ai.omframe.instance.service.interfaces.IInstanceQrySV.class)).getInsSrvAttrValueFromMap(aInsSrvAttrMap,aSoAttrData);
              String decisionCond53 = String.valueOf(null!=aInsSrvAttrValue);
              if(decisionCond53.equals("false")){
              }
              else if(decisionCond53.equals("true")){
                ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOrdSrvAttrValueFromInstValue(aOVOrdSrvPkgOrdService,aSoAttrData,aInsSrvAttrValue,aOrderValueChain,REGION_ID);
                ((com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdOffOrdUserCreater",com.ai.omframe.order.service.interfaces.IOrdOffOrdUserCreater.class)).createOrdAccrelValueByAttr(aOVOrdOffOrdUser,aOVOrderServicePkg,aSoAttrData,aOrderValueChain,REGION_ID);
              }
              else{logger.warn("属性实例不为空Conditions do not match");}
            }
            else{logger.warn("新建属性Conditions do not match");}
            srvAttrIndex++;
          }
        }
        else{logger.warn("服务包包含的服务实例不为空Conditions do not match");}
        srvpkgSrvIndex++;
      }
      while(aSoServicePkgData.getPriceAttrData()!=null&&srvpkgPriceIndex<aSoServicePkgData.getPriceAttrData().length){
        aSrvpkgPriceAttrData=aSoServicePkgData.getPriceAttrData()[srvpkgPriceIndex]; priceAttrId=aSrvpkgPriceAttrData.getAttrId();;
        String decisionCond46 = String.valueOf(aSrvpkgPriceAttrData.getState()==com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE);
        if(decisionCond46.equals("false")){
          aInsPriceAttrValue = ((com.ai.omframe.instance.service.interfaces.IInstPriceAttr)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.instance.service.InstPriceAttr",com.ai.omframe.instance.service.interfaces.IInstPriceAttr.class)).getInstPriceAttrByRelatInstIdAndAttrId(aSrvpkgInsId,priceAttrId,userId,price_inst_type,validTypesrvpkPriceAttr,userRegionId);
          String decisionCond55 = String.valueOf(null!=aInsPriceAttrValue);
          if(decisionCond55.equals("true")){
            ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createSrvPkgOrderPriceAttrFromInstAndSoAttrData(aOVOrderServicePkg,aInsPriceAttrValue,aSrvpkgPriceAttrData,aOrderValueChain,REGION_ID);
          }
          else if(decisionCond55.equals("false")){
          }
          else{logger.warn("资费属性实例不为空Conditions do not match");}
        }
        else if(decisionCond46.equals("true")){
          ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createSrvPkgOrderPriceAttr(aOVOrderServicePkg,aSrvpkgPriceAttrData,aOrderValueChain,REGION_ID);
        }
        else{logger.warn("新建属性Conditions do not match");}
        srvpkgPriceIndex++;
      }
    }
    else if(decisionCond6.equals("false")){
    }
    else{logger.warn("服务包实例不为空Conditions do not match");}
  }
  public void execute(ISoUserData aSoUserData,IOVOrdOffOrdUser aOVOrdOffOrdUser,OrderValueChain aOrderValueChain,ISoServicePkgData aSoServicePkgData,String REGION_ID,long offerInsId) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aSoUserData",aSoUserData);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("aOrderValueChain",aOrderValueChain);
    $inParameter.put("aSoServicePkgData",aSoServicePkgData);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("offerInsId",new Long(offerInsId));
try{
    executeInner( aSoUserData, aOVOrdOffOrdUser, aOrderValueChain, aSoServicePkgData, REGION_ID, offerInsId);
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
    ISoServicePkgData aSoServicePkgData = ($vmParameters.get("aSoServicePkgData") == null)?null:(ISoServicePkgData)VMDataType.transfer($vmParameters.get("aSoServicePkgData"),ISoServicePkgData.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    long offerInsId = ($vmParameters.get("offerInsId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("offerInsId"),long.class)).longValue();
execute(aSoUserData,aOVOrdOffOrdUser,aOrderValueChain,aSoServicePkgData,REGION_ID,offerInsId);
  return $vmParameters;
  }
}