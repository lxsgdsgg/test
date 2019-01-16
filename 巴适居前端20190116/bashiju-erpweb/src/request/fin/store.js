/*******************************************************************************************************/
/** *************************************** 门店--门店分摊管理 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

//获取门店分摊管理列表页面数据
export const queryStoreSharePageList = params=>{
  return onPost('fin/storeShare/queryStoreSharePageList', params)
}

// 添加或修改门店住配置信息
export const saveOrUpdateStoreShareData = params=>{
  return onPost('fin/storeShare/saveOrUpdateStoreShareData', params)
}

// 保存分摊详细配置
export const saveStoreShareDetailData = params=>{
  return onPost('fin/storeShare/saveStoreShareDetailData', params)
}

// 根据id删除分摊配置信息
export const deleteStoreShareDataById = params=>{
  return onPost('fin/storeShare/deleteStoreShareDataById', params)
}

// 根据住配置ID查询门店分摊详细信息
export const queryStoreShareDetailByShareId = params=>{
  return onPost('fin/storeShare/queryStoreShareDetailByShareId', params)
}

// 根据部门ID查询下属门店信息
export const querySubordinateStoreDataByDeptId = params=>{
  return onPost('fin/storeShare/querySubordinateStoreDataByDeptId', params)
}
