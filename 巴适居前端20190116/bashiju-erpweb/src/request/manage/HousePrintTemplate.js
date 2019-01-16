
import {onGet,onPost} from "../main";

// 查询打印模板列表
export const queryTemplateList = params=>{
  return onPost('house/queryTemplateList',params)
}

// 查询打印模板详细
export const queryDetailed = params=>{
  return onPost('house/queryDetailed',params)
}
// 添加打印模板
export const addPrintTemplate = params=>{
  return onPost('house/addPrintTemplate',params)
}
// 修改打印模板
export const updatePrintTemplate = params=>{
  return onPost('house/updatePrintTemplate',params)
}
// 删除打印模板
export const deletePrintTemplate = params=>{
  return onPost('house/deletePrintTemplate',params)
}
// 查询背景图片列表
export const queryBackground = params=>{
  return onPost('house/queryBackground',params)
}
// 上传背景图片列表
export const uploadBackgroundPic = params=>{
  return onPost('house/uploadBackgroundPic',params)
}
// 上传背景图片列表
export const deleteBackGroundPic = params=>{
  return onPost('house/deleteBackGroundPic',params)
}
