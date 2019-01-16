/*******************************************************************************************************/
/** *************************************** 过户流程管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

// 获取过户流程管理列表
export const queryTransferProcessList = params=>{
  return onGet('manage/transferProcess/queryTransferProcessList', params)
}

// 新增 编辑保存过户流程管理
export const saveOrUpdateTranserProc = params=>{
  return onPost('manage/transferProcess/saveOrUpdateTranserProc', params)
}

// 删除过户流程
export const delTransferProc = params=>{
  return onPost('manage/transferProcess/delTransferProc', params)
}

// 保存过户流程设置 材料明细
export const saveOrUpdateTransferProcDetail = params=>{
  return onPost('manage/transferProcess/saveOrUpdateTransferProcDetail', params)
}

// 获取过户配置 材料明细信息
export const queryTransferProcSetDataList = params=>{
  return onGet('manage/transferProcess/queryTransferProcSetDataList', params)
}

// 获取过户流程名称
export const queryTransferProcName = params=>{
  return onGet('manage/transferProcess/queryTransferProcName', params)
}
