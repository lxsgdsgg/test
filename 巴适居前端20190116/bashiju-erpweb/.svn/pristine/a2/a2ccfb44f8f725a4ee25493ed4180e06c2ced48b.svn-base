/*******************************************************************************************************/
/** *************************************** 后台管理--部门类型管理 *****************************************/
/*******************************************************************************************************/

import {onPost} from "../../main";

// 查询部门类型列表数据信息
export const queryDeptTypePageList = (params)=>{
  return onPost('manage/deptType/deptTypeListPage',params)
}

// 获取部管理部门类型下拉选择框数据
export const queryDeptType = params => {
  return onPost('manage/deptType/queryDeptTypeIdAndName', params)
}

// 添加或修改部门类型
export const saveOrUpdateDeptType = params =>{
  return onPost('manage/deptType/saveOrUpdateDeptType',params)
}
// 删除部门类型数据信息
export const deleteDeptTypeById = params=>{
  return onPost('manage/deptType/delDeptType',params)
}
