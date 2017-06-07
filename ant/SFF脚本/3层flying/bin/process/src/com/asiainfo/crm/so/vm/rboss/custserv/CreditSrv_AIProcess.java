package com.asiainfo.crm.so.vm.rboss.custserv;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV;
import  com.ai.omframe.order.ivalues.IOVOrderOffer;
import  com.ai.omframe.order.data.ivalues.ISoOrderData;
import  com.ai.appframe2.common.DataContainerInterface;
public class CreditSrv_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(CreditSrv_AIProcess.class);
  protected void executeInner(long customerOrderId,String REGION_ID) throws Exception{
	String _TASK_JUGE_RESULT = "";
	IOVOrderOffer aOVOrderOffer = null;
	ISoOrderData aSoOrderData = null;
	String billId = "";
	DataContainerInterface userMsg = null;
    aSoOrderData=com.ai.omframe.util.SoDataFactory.getSoOrderData(customerOrderId); billId=aSoOrderData.getBillId();;
    userMsg = ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).getUserCommonMsg(billId);
    String decisionCond5 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).isBrandOfQuanqiutong(userMsg));
    if(decisionCond5.equals("false")){
      String decisionCond17 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).checkUserBalance(userMsg));
      if(decisionCond17.equals("true")){
        String decisionCond18 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).checkAtNetYear(userMsg));
        if(decisionCond18.equals("false")){
          ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).sendSms4Brand("4",billId);
        }
        else if(decisionCond18.equals("true")){
          ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).recharge4User(userMsg);
          ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).addCreditLog(userMsg);
        }
        else{logger.warn("判断在网年限Conditions do not match");}
      }
      else if(decisionCond17.equals("false")){
        ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).sendSms4Brand("3",billId);
      }
      else{logger.warn("判断用户余额Conditions do not match");}
    }
    else if(decisionCond5.equals("true")){
      String decisionCond6 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).checkStopQuestion(userMsg));
      if(decisionCond6.equals("false")){
        ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).sendSms4Brand("1",billId);
      }
      else if(decisionCond6.equals("true")){
        String decisionCond9 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).checkAtNetYear(userMsg));
        if(decisionCond9.equals("false")){
          String decisionCond10 = String.valueOf(((ICreditSrvSV)ServiceFactory.getService(ICreditSrvSV.class)).isVIPCust(userMsg));
          if(decisionCond10.equals("false")){
            ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).sendSms4Brand("2",billId);
          }
          else if(decisionCond10.equals("true")){
            ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).dealOpen(billId);
            ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).addCreditLog(userMsg);
          }
          else{logger.warn("检查是否VIP客户Conditions do not match");}
        }
        else if(decisionCond9.equals("true")){
          ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).dealOpen(billId);
          ((com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV",com.asiainfo.crm.so.instance.rboss.service.interfaces.ICreditSrvSV.class)).addCreditLog(userMsg);
        }
        else{logger.warn("判断在网年限是否满足Conditions do not match");}
      }
      else{logger.warn("判断停机原因，能否进行复机Conditions do not match");}
    }
    else{logger.warn("判断用户的品牌是否全球通Conditions do not match");}
  }
  public void execute(long customerOrderId,String REGION_ID) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("customerOrderId",new Long(customerOrderId));
    $inParameter.put("REGION_ID",REGION_ID);
try{
    executeInner( customerOrderId, REGION_ID);
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
    long customerOrderId = ($vmParameters.get("customerOrderId") == null)?0:((Long)VMDataType.transfer($vmParameters.get("customerOrderId"),long.class)).longValue();
    String REGION_ID = ($vmParameters.get("REGION_ID") == null)?"":(String)VMDataType.transfer($vmParameters.get("REGION_ID"),String.class);
execute(customerOrderId,REGION_ID);
  return $vmParameters;
  }
}