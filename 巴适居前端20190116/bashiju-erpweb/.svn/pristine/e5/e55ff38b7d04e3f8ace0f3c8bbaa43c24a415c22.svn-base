/*******************************************************************************************************/
/** *************************************** 后台管理--门店支出管理 *****************************************/
/*******************************************************************************************************/


import {onGet, onPost} from "../main";

//查询费用类型
export const queryCostTypePageList = params=>{
  return onPost('manage/storeConfiguration/queryFinanceCostTypeData',params)
}

// 查询费用项目
export const queryCostItemPageList = params=>{
  return onPost('manage/storeConfiguration/queryFinanceCostProjData',params)
}

//保存费用项目
export const saveFinanceCostProj = params=>{
  return onPost('manage/storeConfiguration/saveOrUpdateFinanceCostProj',params)
}

// 保存费用类型
export const saveFinanceCostType = params=>{
  return onPost('manage/storeConfiguration/saveOrUpdateFinanceCostType',params)
}

// 删除费用项目
export const delFinanceCostProj = params=>{
  return onPost('manage/storeConfiguration/delFinanceCostProj',params)
}

// 删除费用项目
export const deleteFinanceCostType = params=>{
  return onPost('manage/storeConfiguration/deleteFinanceCostType',params)
}
