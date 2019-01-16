/*******************************************************************************************************/
/** *************************************** 后台管理--标签接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询标签
export const queryLabelPageList = params=>{
  return onPost('manage/getLabelList',params)
}

// 保存标签
export const saveOrUpdateLabelData = (params,cfg)=>{
  return onPost('manage/saveLabel',params,cfg)
}

// 删除标签
export const delLabelData = params=>{
  return onPost('manage/delLabel',params)
}


