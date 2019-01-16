/**********************************客户留言评价*********************************/

import {onPost,onGet} from "../main";

export  const queryAllAgentInfoPages = params =>{
  return onPost('customer/agentDetail/queryAllAgentInfoPages',params)
}

//
export  const queryAllCustomerLeavingMsg = params =>{
  return onPost('customer/agentDetail/queryAllCustomerLeavingMsg',params)
}

//查询经纪人留言
export  const queryAllUnreviewedLeavingMsg = params =>{
  return onPost('customer/agentDetail/queryAllCustomerComments',params)
}
//删除客户留言
export  const deleteCustmerLeavingMsg = params =>{
  return onPost('customer/agentDetail/deleteCustmerLeavingMsg',params)
}

//删除客户星级评价
export  const deleteCustmerStarEvaluation = params =>{
  return onPost('customer/agentDetail/deleteCustmerComments',params)
}

//标记已读
export  const markReaded = params =>{
  return onPost('customer/agentDetail/markCustomerLeavingMsgReaded',params)
}

//审核经纪人星级评价
export  const checkAgentStarEvaluation = params =>{
  return onPost('customer/agentDetail/checkCustmerComments',params)
}

