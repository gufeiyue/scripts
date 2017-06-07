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
import  com.ai.omframe.order.service.interfaces.IOrdPriceCreater;
import  com.ai.omframe.order.ivalues.IOrdProdValue;
import  com.ai.omframe.order.ivalues.IOrdProdOrdSrvValue;
import  com.ai.omframe.order.ivalues.IOrdPriceAttrValue;
import  com.ai.omframe.order.ivalues.IOrdSrvAttrValue;
import  com.ai.omframe.order.ivalues.IOVOrderServicePkg;
import  com.ai.omframe.order.ivalues.IOVOrdOffOrdUser;
import  com.ai.omframe.order.ivalues.IOVOrdSrvPkgOrdService;
public class OrdRenewProd_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrdRenewProd_AIProcess.class);
  protected void executeInner(String aOVOrderOffer,IOrdProdValue oldOrdProd,String REGION_ID,IOVOrdOffOrdUser aOVOrdOffOrdUser,long oldUserOrderId,String yearmonth) throws Exception{
	String _TASK_JUGE_RESULT = "";
	int oldProdState = 0;
	int oldProdAttrState = 0;
	int oldSrvAttrState = 0;
	int newProdState = 0;
	int newProdAttrState = 0;
	int newSrvAttrState = 0;
	IOrdProdOrdSrvValue[] oldOrdProdOrdSrvs = null;
	IOrdPriceAttrValue[] oldOrdPriceAttrs = null;
	IOrdSrvAttrValue[] oldOrdSrvAttrs = null;
	int indexOrdProdOrdSrv = 0;
	int indexOrdSrvAttr = 0;
	int indexOrdProdAttr = 0;
	IOVOrderServicePkg aOVOrderServicePkg = null;
	IOrdProdOrdSrvValue oldOrdProdOrdSrv = null;
	IOrdSrvAttrValue oldOrdSrvAttr = null;
	IOVOrdSrvPkgOrdService aOVOrdProdOrdSrv = null;
	IOrdPriceAttrValue oldOrdPriceAttr = null;
	int oldOrdSrvState = 0;
	int newOrdSrvState = 0;
    oldProdState = oldOrdProd.getState(); if(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE){ newProdState = com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; }else if(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE){ newProdState = com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE; };
    String decisionCond15 = String.valueOf(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE || oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE);
    if(decisionCond15.equals("false")){
    }
    else if(decisionCond15.equals("true")){
      oldOrdProdOrdSrvs = com.ai.omframe.util.SoServiceFactory.getOrderQryDao().getFinishOrdProdOrdSrvByOrdProdId(oldOrdProd.getProdOrderId(), REGION_ID, yearmonth); oldOrdPriceAttrs = com.ai.omframe.util.SoServiceFactory.getOrderQryDao().getFinishOrdPriceAttrByOrdPordId(oldOrdProd.getProdOrderId(), REGION_ID, yearmonth);;
      aOVOrderServicePkg = ((com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvPkgCreater",com.ai.omframe.order.service.interfaces.IOrdSrvPkgCreater.class)).createOVOrderSrvPkgFromFinishOrder(aOVOrdOffOrdUser,oldOrdProd,newProdState);
      while(oldOrdProdOrdSrvs!=null && indexOrdProdOrdSrv<oldOrdProdOrdSrvs.length){
        oldOrdProdOrdSrv = oldOrdProdOrdSrvs[indexOrdProdOrdSrv]; oldOrdSrvAttrs = com.ai.omframe.util.SoServiceFactory.getOrderQryDao().getFinishOrdSrvAttrByOrdProdSrvId(oldOrdProdOrdSrv.getProdSrvOrderId(),REGION_ID, yearmonth);;
        aOVOrdProdOrdSrv = ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOVOrdSrvpkgOrdSrvFromFinishOrder(aOVOrderServicePkg,oldOrdProdOrdSrv,newProdState);
        while(oldOrdSrvAttrs!=null && indexOrdSrvAttr<oldOrdSrvAttrs.length){
          oldOrdSrvAttr = oldOrdSrvAttrs[indexOrdSrvAttr]; oldOrdSrvState = oldOrdSrvAttr.getState(); if(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE){ newOrdSrvState = com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE;newSrvAttrState= newOrdSrvState;}else if(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE){ newOrdSrvState = com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE;newSrvAttrState= newOrdSrvState;};
          String decisionCond33 = String.valueOf(oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE || oldProdState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE);
          if(decisionCond33.equals("false")){
          }
          else if(decisionCond33.equals("true")){
            ((com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdSrvpkgOrdSrvCreater",com.ai.omframe.order.service.interfaces.IOrdSrvpkgOrdSrvCreater.class)).createOrdSrvAttrFromFinishOrder(aOVOrdProdOrdSrv,oldOrdSrvAttr,newSrvAttrState);
          }
          else{logger.warn("判断属性状态Conditions do not match");}
          indexOrdSrvAttr++;
        }
        indexOrdProdOrdSrv++;
      }
      while(oldOrdPriceAttrs!=null && indexOrdProdAttr< oldOrdPriceAttrs.length){
        oldOrdPriceAttr = oldOrdPriceAttrs[indexOrdProdAttr]; oldProdAttrState = oldOrdPriceAttr.getState(); if(oldProdAttrState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE){ newProdAttrState = com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE; }else if(oldProdAttrState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE){ newProdAttrState = com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE; };
        String decisionCond39 = String.valueOf(oldProdAttrState == com.ai.omframe.order.valuebean.OrderConst.DATA_NEW_STATE || oldProdAttrState == com.ai.omframe.order.valuebean.OrderConst.DATA_DELETE_STATE);
        if(decisionCond39.equals("true")){
          ((com.ai.omframe.order.service.interfaces.IOrdPriceCreater)com.ai.comframe.vm.common.ServiceUtil.getService("com.ai.omframe.order.service.OrdPriceCreater",com.ai.omframe.order.service.interfaces.IOrdPriceCreater.class)).createSrvPkgOrderPriceAttrFromFinishOrder(aOVOrderServicePkg,oldOrdPriceAttr,newProdAttrState);
        }
        else if(decisionCond39.equals("false")){
        }
        else{logger.warn("判断资费属性Conditions do not match");}
        indexOrdProdAttr++;
      }
    }
    else{logger.warn("判断产品状态Conditions do not match");}
  }
  public void execute(String aOVOrderOffer,IOrdProdValue oldOrdProd,String REGION_ID,IOVOrdOffOrdUser aOVOrdOffOrdUser,long oldUserOrderId,String yearmonth) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("aOVOrderOffer",aOVOrderOffer);
    $inParameter.put("oldOrdProd",oldOrdProd);
    $inParameter.put("REGION_ID",REGION_ID);
    $inParameter.put("aOVOrdOffOrdUser",aOVOrdOffOrdUser);
    $inParameter.put("oldUserOrderId",new Long(oldUserOrderId));
    $inParameter.put("yearmonth",yearmonth);
try{
    executeInner( aOVOrderOffer, oldOrdProd, REGION_ID, aOVOrdOffOrdUser, oldUserOrderId, yearmonth);
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
    String aOVOrderOffer = ($vmParameters.get("aOVOrderOffer") == null)?"":(String)VMDataType.transfer($vmParameters.get("aOVOrderOffer"),String.class);
    IOrdProdValue oldOrdProd = ($vmParameters.get("oldOrdProd") == null)?null:(IOrdProdValue)VMDataType.transfer($vmParameters.get("oldOrdProd"),IOrdProdValue.class);
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
    IOVOrdOffOrdUser aOVOrdOffOrdUser = ($vmParameters.get("aOVOrdOffOrdUser") == null)?null:(IOVOrdOffOrdUser)VMDataType.transfer($vmParameters.get("aOVOrdOffOrdUser"),IOVOrdOffOrdUser.class);
    long oldUserOrderId = ($vmParameters.get("oldUserOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("oldUserOrderId"),long.class)).longValue();
    String yearmonth = ($vmParameters.get("yearmonth") == null)?"":(String)VMDataType.transfer($vmParameters.get("yearmonth"),String.class);
execute(aOVOrderOffer,oldOrdProd,REGION_ID,aOVOrdOffOrdUser,oldUserOrderId,yearmonth);
  return $vmParameters;
  }
}