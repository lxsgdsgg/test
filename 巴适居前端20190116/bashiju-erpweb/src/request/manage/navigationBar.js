/*******************************************************************************************************/
/** *************************************** 后台管理--导航栏接口地址 **************************************/
/*******************************************************************************************************/

import { onGet, onPost} from "../main"

// 查询导航栏
export const queryNavigationBarData = params=>{
  return onPost('manage/navigationBar/queryNavigationBarData',params)
}

// 判断导航编号是否存在
export const queryIsExistNavigationCode = params=>{
  return onPost('manage/navigationBar/queryIsExistNavigateCode',params)
}

// 根据等级查询多导航栏
export const queryAllNavigationBarNameByLevel = params=>{
  return onPost('manage/navigationBar/queryAllNavigationBarNameByLevel',params)
}

// 根据等级查询多导航栏
export const queryNavigationBarById = params=>{
  return onPost('manage/navigationBar/queryNavigationBarById',params)
}

// 保存导航栏
export const saveOrUpdataNavigationBar = (params,cfg)=>{
  return onPost('manage/navigationBar/saveOrUpdataNavigationBar',params,cfg)
}

// 删除导航栏
export const delNavigationBarById = params=>{
  return onPost('manage/navigationBar/delNavigationBarById',params)
}


