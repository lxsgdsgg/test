/*******************************************************************************************************/
/** *************************************** 后台管理--部门管理 ***************************************/
/*******************************************************************************************************/

import {onPost} from '../../main'

// 获取部门管理中公司选择下拉框数据信息
export const queryCompanySelectData = params => {
  return onPost('manage/company/queryCompanyNameAndId', params)
}

// 获取部管理部门类型下拉选择框数据
export const queryDeptType = params => {
  return onPost('manage/deptType/queryDeptTypeIdAndName', params)
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
