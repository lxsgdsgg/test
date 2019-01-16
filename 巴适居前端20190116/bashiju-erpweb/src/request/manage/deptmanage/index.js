/*******************************************************************************************************/
/** *************************************** 后台管理--部门管理 ***************************************/
/*******************************************************************************************************/

import {onPost, onGet} from '../../main'

// 获取部门管理中公司选择下拉框数据信息
export const queryCompanySelectData = params => {
  return onPost('manage/company/queryCompanyNameAndId', params)
}

// 获取部管理部门类型下拉选择框数据
export const queryDeptType = params => {
  return onPost('manage/deptType/queryDeptTypeIdAndName', params)
}

// 获取部管理部门类型下拉选择框数据（从枚举中取值）
export const queryDeptTypeFromEnum = params => {
  return onPost('manage/deptType/queryDeptTypeFromEnum', params)
}

// 【部门管理】--部门数据列表
export const queryDeptList = params => {
  return onPost('manage/dept/getDeptData', params)
}

// 新增/或更新部门
export const saveOrUpdateDept = params => {
  return onPost('manage/dept/saveOrUpdateDept', params)
}

// 删除部门
export const delDepartment = params => {
  return onPost('manage/dept/delDept', params)
}

// 查询部门ID和名称
export const queryDeptIdAndDeptName = params =>{
  return onPost('manage/dept/queryDeptIdAndDeptName',params)
}

// 查询部门跨店信息
export const enterAcrossPermissionNew = params =>{
  return onGet('manage/dept/enterAcrossPermissionNew',params)
}

// 保存部门跨店设置
export const saveAcrossStorePermission = params =>{
  return onPost('manage/dept/saveAcrossStorePermission',params)
}

// 区域 片区
export const queryAreaAndRegion = params => {
  return onGet('manage/commonselect/queryAreaAndRegion', params)
}


// 根据片区ID查询小区
export const queryCommunityByRegionId = params => {
  return onGet('manage/commonselect/queryCommunityByRegionId', params)
}

//获取责任盘信息
export const queryResponsibilityDisk = params => {
  return onPost('manage/dept/queryResponsibilityDisk', params)
}
//获取部门的城市信息
export const queryCityInfoByDept = params => {
  return onPost('manage/dept/queryCityForDept', params)
}

//新增或修改责任盘
export const saveUpdateResponsbilityDisc = params => {
  return onPost('manage/dept/saveResponsbilityDisc', params)
}

