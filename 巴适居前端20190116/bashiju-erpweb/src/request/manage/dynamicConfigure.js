/*******************************************************************************************************/
/** *************************************** 普通配置管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

// 验证编号
export const validateWidgetCode = params=>{
  return onPost('manage/dynamicConfigure/validateWidgetCode', params)
}
// 保存配置项（下拉或区间）
export const setWidget = params=>{
  return onPost('manage/dynamicConfigure/setWidget', params)
}
// 获取下拉配置项
export const getSelectType = params=>{
  return onPost('manage/dynamicConfigure/getSelectType', params)
}
// 获取下拉值
export const getSelectItem = params=>{
  return onPost('manage/dynamicConfigure/getSelectItem', params)
}
// 新增 编辑保存下拉值
export const saveOrUpdateSelectItem = params=>{
  return onPost('manage/dynamicConfigure/setSelectItem', params)
}
// 修改保存sql下拉
export const saveSelectSql = params=>{
  return onPost('manage/dynamicConfigure/saveSelectSql', params)
}
// 删除动态配置项的值
export const delDropdownValue = params=>{
  return onPost('manage/dynamicConfigure/delDropdownValue', params)
}
// 获取区间配置项
export const getRangeType = params=>{
  return onPost('manage/dynamicConfigure/getRangeType', params)
}
// 获取区间值
export const getRange = params=>{
  return onPost('manage/dynamicConfigure/getRange', params)
}
// 新增 编辑保存区间值
export const saveOrUpdateRangeItem = params=>{
  return onPost('manage/dynamicConfigure/setRange', params)
}
// 获取动态表单配置项
export const getDynamicFormList = params=>{
  return onPost('manage/dynamicConfigure/getDynamicFormList', params)
}
// 获取动态表单配置字段
export const getDynamicFormFieldList = params=>{
  return onPost('manage/dynamicConfigure/getDynamicFormFieldList', params)
}
// 保存、编辑动态表单字段
export const saveFormField = params=>{
  return onPost('manage/dynamicConfigure/saveFormField', params)
}
// 删除动态表单字段
export const delFormField = params=>{
  return onPost('manage/dynamicConfigure/delFormField', params)
}
//查询已有字段供选择
export const queryCityDynamicFormFieldSelect = params=>{
  return onPost('manage/dynamicConfigure/queryCityDynamicFormFieldSelect', params)
}

//以下是用户表单

// 获取用户表单配置项
export const queryCompanyDynamicFormList = params=>{
  return onPost('manage/dynamicConfigure/queryCompanyDynamicFormList', params)
}
// 获取用户表单配置字段
export const queryCompanyDynamicFormFieldList = params=>{
  return onPost('manage/dynamicConfigure/queryCompanyDynamicFormFieldList', params)
}
// 保存、编辑用户表单字段
export const saveCompanyFormField = params=>{
  return onPost('manage/dynamicConfigure/saveCompanyFormField', params)
}
// 删除用户表单字段
export const delCompanyFormField = params=>{
  return onPost('manage/dynamicConfigure/delCompanyFormField', params)
}
//查询已有用户字段供选择
export const queryCompanyDynamicFormFieldSelect = params=>{
  return onPost('manage/dynamicConfigure/queryCompanyDynamicFormFieldSelect', params)
}
