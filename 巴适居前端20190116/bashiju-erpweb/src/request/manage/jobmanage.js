/*******************************************************************************************************/
/** *************************************** 后台管理--定时任务接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询定时任务
export const queryJobConfigPageList = params=>{
  return onPost('manage/getJobConf',params)
}

// 保存定时任务
export const saveJobConfig = (params,cfg)=>{
  return onPost('manage/saveJobConf',params,cfg)
}

// 删除定时任务
export const delJobConfig = params=>{
  return onPost('manage/delJobConf',params)
}


// 启动/停止定时任务
export const startOrStopJobConf = params=>{
  return onPost('manage/controlJobConf',params)
}


