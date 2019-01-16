/*******************************************************************************************************/
/** *************************************** 后台管理--采集模板接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询采集模板
export const queryTemplatePageList = params=>{
  return onPost('manage/getAnalysisTemplet',params)
}

// 保存采集模板
export const saveAnalysisTemplet = (params,cfg)=>{
  return onPost('manage/saveAnalysisTemplet',params,cfg)
}

// 删除采集模板
export const delAnalysisTemplet = params=>{
  return onPost('manage/delAnalysisTemplet',params)
}


