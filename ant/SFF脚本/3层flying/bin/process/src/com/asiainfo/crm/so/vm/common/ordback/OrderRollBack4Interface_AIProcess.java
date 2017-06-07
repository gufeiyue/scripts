package com.asiainfo.crm.so.vm.common.ordback;

import  java.util.Map;
import  java.util.HashMap;
import  org.apache.commons.logging.Log;
import  org.apache.commons.logging.LogFactory;
import  com.ai.appframe2.service.ServiceFactory;
import  com.ai.comframe.vm.common.VMDataType;
import  com.ai.comframe.vm.processflow.ProcessInstance;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV;
import  com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV;
import  com.ai.omframe.order.ivalues.IOVOrderCustomer;
public class OrderRollBack4Interface_AIProcess implements ProcessInstance{
  private static transient Log logger = LogFactory.getLog(OrderRollBack4Interface_AIProcess.class);
  protected void executeInner(IOVOrderCustomer newOvOrderCustomer,IOVOrderCustomer oldOvOrderCustomer) throws Exception{
	String _TASK_JUGE_RESULT = "";
    String decisionCond7 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillingInterface(oldOvOrderCustomer,newOvOrderCustomer));
    if(decisionCond7.equals("false")){
      String decisionCond9 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedOpenInterface(oldOvOrderCustomer,newOvOrderCustomer));
      if(decisionCond9.equals("false")){
        String decisionCond15 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedResInterface(oldOvOrderCustomer,newOvOrderCustomer));
        if(decisionCond15.equals("true")){
          {//Call the sub-processes资源接口
            Map tmpMap16 = new HashMap();
            Object loopVar16 = null;
            int loopCount16 = 0;
            loopVar16 = new Object[]{null};
            loopCount16 = 1;
            for(int i=0;i < loopCount16;i++){
            tmpMap16.clear();
            tmpMap16.put("newOvOrderCustomer",newOvOrderCustomer);
            tmpMap16.put("oldOvOrderCustomer",oldOvOrderCustomer);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Res",tmpMap16);

            tmpMap16.clear();
            }          }

          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else if(decisionCond15.equals("false")){
          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else{logger.warn("是否需要调用资源接口Conditions do not match");}
      }
      else if(decisionCond9.equals("true")){
        {//Call the sub-processes调用开通接口
          Map tmpMap10 = new HashMap();
          Object loopVar10 = null;
          int loopCount10 = 0;
          loopVar10 = new Object[]{null};
          loopCount10 = 1;
          for(int i=0;i < loopCount10;i++){
          tmpMap10.clear();
          tmpMap10.put("newOvOrderCustomer",newOvOrderCustomer);
          tmpMap10.put("oldOvOrderCustomer",oldOvOrderCustomer);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Open",tmpMap10);

          tmpMap10.clear();
          }        }

        String decisionCond15 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedResInterface(oldOvOrderCustomer,newOvOrderCustomer));
        if(decisionCond15.equals("true")){
          {//Call the sub-processes资源接口
            Map tmpMap16 = new HashMap();
            Object loopVar16 = null;
            int loopCount16 = 0;
            loopVar16 = new Object[]{null};
            loopCount16 = 1;
            for(int i=0;i < loopCount16;i++){
            tmpMap16.clear();
            tmpMap16.put("newOvOrderCustomer",newOvOrderCustomer);
            tmpMap16.put("oldOvOrderCustomer",oldOvOrderCustomer);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Res",tmpMap16);

            tmpMap16.clear();
            }          }

          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else if(decisionCond15.equals("false")){
          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else{logger.warn("是否需要调用资源接口Conditions do not match");}
      }
      else{logger.warn("是否需要调用开通接口Conditions do not match");}
    }
    else if(decisionCond7.equals("true")){
      {//Call the sub-processes调用计费接口
        Map tmpMap8 = new HashMap();
        Object loopVar8 = null;
        int loopCount8 = 0;
        loopVar8 = new Object[]{null};
        loopCount8 = 1;
        for(int i=0;i < loopCount8;i++){
        tmpMap8.clear();
        tmpMap8.put("newOvOrderCustomer",newOvOrderCustomer);
        tmpMap8.put("oldOvOrderCustomer",oldOvOrderCustomer);

        com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.orderRollBack4Billing",tmpMap8);

        tmpMap8.clear();
        }      }

      String decisionCond9 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedOpenInterface(oldOvOrderCustomer,newOvOrderCustomer));
      if(decisionCond9.equals("false")){
        String decisionCond15 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedResInterface(oldOvOrderCustomer,newOvOrderCustomer));
        if(decisionCond15.equals("true")){
          {//Call the sub-processes资源接口
            Map tmpMap16 = new HashMap();
            Object loopVar16 = null;
            int loopCount16 = 0;
            loopVar16 = new Object[]{null};
            loopCount16 = 1;
            for(int i=0;i < loopCount16;i++){
            tmpMap16.clear();
            tmpMap16.put("newOvOrderCustomer",newOvOrderCustomer);
            tmpMap16.put("oldOvOrderCustomer",oldOvOrderCustomer);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Res",tmpMap16);

            tmpMap16.clear();
            }          }

          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else if(decisionCond15.equals("false")){
          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else{logger.warn("是否需要调用资源接口Conditions do not match");}
      }
      else if(decisionCond9.equals("true")){
        {//Call the sub-processes调用开通接口
          Map tmpMap10 = new HashMap();
          Object loopVar10 = null;
          int loopCount10 = 0;
          loopVar10 = new Object[]{null};
          loopCount10 = 1;
          for(int i=0;i < loopCount10;i++){
          tmpMap10.clear();
          tmpMap10.put("newOvOrderCustomer",newOvOrderCustomer);
          tmpMap10.put("oldOvOrderCustomer",oldOvOrderCustomer);

          com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Open",tmpMap10);

          tmpMap10.clear();
          }        }

        String decisionCond15 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedResInterface(oldOvOrderCustomer,newOvOrderCustomer));
        if(decisionCond15.equals("true")){
          {//Call the sub-processes资源接口
            Map tmpMap16 = new HashMap();
            Object loopVar16 = null;
            int loopCount16 = 0;
            loopVar16 = new Object[]{null};
            loopCount16 = 1;
            for(int i=0;i < loopCount16;i++){
            tmpMap16.clear();
            tmpMap16.put("newOvOrderCustomer",newOvOrderCustomer);
            tmpMap16.put("oldOvOrderCustomer",oldOvOrderCustomer);

            com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Res",tmpMap16);

            tmpMap16.clear();
            }          }

          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else if(decisionCond15.equals("false")){
          String decisionCond11 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedBillManagerInterface(oldOvOrderCustomer,newOvOrderCustomer));
          if(decisionCond11.equals("true")){
            {//Call the sub-processes调用帐管接口
              Map tmpMap12 = new HashMap();
              Object loopVar12 = null;
              int loopCount12 = 0;
              loopVar12 = new Object[]{null};
              loopCount12 = 1;
              for(int i=0;i < loopCount12;i++){
              tmpMap12.clear();
              tmpMap12.put("newOvOrderCustomer",newOvOrderCustomer);
              tmpMap12.put("oldOvOrderCustomer",oldOvOrderCustomer);

              com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4BillManager",tmpMap12);

              tmpMap12.clear();
              }            }

            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else if(decisionCond11.equals("false")){
            String decisionCond13 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedScoreInterface(oldOvOrderCustomer,newOvOrderCustomer));
            if(decisionCond13.equals("false")){
              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else if(decisionCond13.equals("true")){
              {//Call the sub-processes调用积分接口
                Map tmpMap14 = new HashMap();
                Object loopVar14 = null;
                int loopCount14 = 0;
                loopVar14 = new Object[]{null};
                loopCount14 = 1;
                for(int i=0;i < loopCount14;i++){
                tmpMap14.clear();
                tmpMap14.put("newOvOrderCustomer",newOvOrderCustomer);
                tmpMap14.put("oldOvOrderCustomer",oldOvOrderCustomer);

                com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Score",tmpMap14);

                tmpMap14.clear();
                }              }

              String decisionCond18 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedTerminalPort(oldOvOrderCustomer));
              if(decisionCond18.equals("false")){
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else if(decisionCond18.equals("true")){
                ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealUserOfferExtend(oldOvOrderCustomer,newOvOrderCustomer);
                String decisionCond22 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedSmsSendPort(oldOvOrderCustomer));
                if(decisionCond22.equals("true")){
                  ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBackSmsSendSV.class)).offerChangeSendSms(oldOvOrderCustomer,newOvOrderCustomer);
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else if(decisionCond22.equals("false")){
                  String decisionCond6 = String.valueOf(((IOrderRollBack4InterfaceSV)ServiceFactory.getService(IOrderRollBack4InterfaceSV.class)).isNeedCustomerInterface(oldOvOrderCustomer,newOvOrderCustomer));
                  if(decisionCond6.equals("false")){
                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else if(decisionCond6.equals("true")){
                    {//Call the sub-processes客户管理接口
                      Map tmpMap4 = new HashMap();
                      Object loopVar4 = null;
                      int loopCount4 = 0;
                      loopVar4 = new Object[]{null};
                      loopCount4 = 1;
                      for(int i=0;i < loopCount4;i++){
                      tmpMap4.clear();
                      tmpMap4.put("newOvOrderCustomer",newOvOrderCustomer);
                      tmpMap4.put("oldOvOrderCustomer",oldOvOrderCustomer);

                      com.ai.comframe.vm.processflow.ProcessEngineFactory.getProcessEngine().executeProcess("com.asiainfo.crm.so.vm.common.ordback.OrderRollBack4Customer",tmpMap4);

                      tmpMap4.clear();
                      }                    }

                    ((com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV)com.ai.comframe.vm.common.ServiceUtil.getService("com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV",com.asiainfo.crm.so.common.service.interfaces.IOrderRollBack4InterfaceSV.class)).dealChildOrder(oldOvOrderCustomer,newOvOrderCustomer);
                  }
                  else{logger.warn("是否需要调用客户管理接口Conditions do not match");}
                }
                else{logger.warn("是否调用发送短信接口Conditions do not match");}
              }
              else{logger.warn("是否调用外部扩展接口Conditions do not match");}
            }
            else{logger.warn("是否调用积分接口Conditions do not match");}
          }
          else{logger.warn("是否调用帐管接口Conditions do not match");}
        }
        else{logger.warn("是否需要调用资源接口Conditions do not match");}
      }
      else{logger.warn("是否需要调用开通接口Conditions do not match");}
    }
    else{logger.warn("是否需要调用计费接口Conditions do not match");}
  }
  public void execute(IOVOrderCustomer newOvOrderCustomer,IOVOrderCustomer oldOvOrderCustomer) throws Exception{
    Map $inParameter = new HashMap();
    Map $returnParameter = new HashMap();
    $inParameter.put("newOvOrderCustomer",newOvOrderCustomer);
    $inParameter.put("oldOvOrderCustomer",oldOvOrderCustomer);
try{
    executeInner( newOvOrderCustomer, oldOvOrderCustomer);
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
    IOVOrderCustomer newOvOrderCustomer = ($vmParameters.get("newOvOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("newOvOrderCustomer"),IOVOrderCustomer.class);
    IOVOrderCustomer oldOvOrderCustomer = ($vmParameters.get("oldOvOrderCustomer") == null)?null:(IOVOrderCustomer)VMDataType.transfer($vmParameters.get("oldOvOrderCustomer"),IOVOrderCustomer.class);
execute(newOvOrderCustomer,oldOvOrderCustomer);
  return $vmParameters;
  }
}