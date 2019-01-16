/*******************************************************************************************************/
/** *************************************** 后台管理--经纪人能力认定接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询经纪人能力认定
export const queryAgentAbilityidentifyDate = params=>{
  return onPost('manage/agentAbilityidentify/queryAgentAbilityidentifyDate',params)
}

// 保存经纪人能力认定
export const saveOrUpdateAgentAbilityidentify = (params,cfg)=>{
  return onPost('manage/agentAbilityidentify/saveOrUpdateAgentAbilityidentify',params,cfg)
}

// 删除经纪人能力认定
export const delAgentAbilityidentify = params=>{
  return onPost('manage/agentAbilityidentify/delAgentAbilityidentify',params)
}


