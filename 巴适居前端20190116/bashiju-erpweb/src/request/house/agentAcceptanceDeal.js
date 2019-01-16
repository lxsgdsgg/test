/**************************经纪人受理委托后台接口**********************/


import {onPost,onGet} from "../main";

// 查询经纪人受理列表数据
export const queryAgentAcceptanceList = params=>{
  return onPost('house/agentAcceptance/queryAgentAcceptanceList',params)
}

// 经纪人拒绝受理委托
export const refuseAgentAcceptanceEntrustById = params=>{
  return onPost('house/agentAcceptance/refuseAgentAcceptanceEntrustById',params)
}

// 查询客户房源委托列表数据
export const queryCustomerHouseEntrust = params=>{
  return onPost('house/agentAcceptance/queryCustomerHouseEntrust',params)
}

// 保存经纪人受理委托数据
export const saveAgentAcceptanceDealResult = params=>{
  return onPost('house/agentAcceptance/saveAgentAcceptanceDealResult',params)
}
