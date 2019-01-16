/*******************************************************************************************************/
/** *************************************** 后台管理--决策配置接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询定时任务
export const queryDecisionConfiguratePageList = params=>{
  return onPost('manage/decisionConfigurate/getData',params)
}

// 保存定时任务
export const saveDecisisionConfigurate = (params,cfg)=>{
  return onPost('manage/decisionConfigurate/saveDecisisionCOnfigurate',params,cfg)
}

// // 删除定时任务
// export const delJobConfig = params=>{
//   return onPost('manage/decisionConfigurate',params)
// }


