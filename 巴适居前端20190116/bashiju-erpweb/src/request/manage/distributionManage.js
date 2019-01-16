/*******************************************************************************************************************
 ***************************************************后台管理--开通城市管理**********************************************
 * *****************************************************************************************************************/

import { onGet,onPost} from "../main"

/**
 * 查询城市管理分配信息
 * @param params
 */
export const getResponsibilityData = params =>{
  return onPost('manage/responsibilityCity/getResponsibilityData',params)
}

/**
 * 保存城市管理分配
 * @param params
 */
export const saveResiposibilityCity = params=>{
  return onPost('manage/responsibilityCity/saveResiposibilityCity',params)
}

/**
 * 删除城市管理分配
 * @param params
 */
export const delResponsibilityCity = params=>{
  return onPost('manage/responsibilityCity/delResponsibilityCity',params)
}
