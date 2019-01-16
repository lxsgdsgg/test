/************************房产文章分类后台接口*****************************/

import {onPost,onGet} from "../main";

export const queryHouseArticleClassify = params =>{
  return onPost('manage/housePropertyArticleClasses/queryHousePropertyArticleClasses',params)
}

export const saveOrUpdatehouseArticleClassify = params =>{
  return onPost('manage/housePropertyArticleClasses/saveOrUpdateHousePropertyArticleClasses',params)
}

export const delHouseArticleClassify = params =>{
  return onPost('manage/housePropertyArticleClasses/delHousePropertyArticleClasses',params)
}

export const getParenLevel = params =>{
  return onPost('manage/housePropertyArticleClasses/queryHousePropertyArticleClassesNameByLevel',params)
}

// 查询文章类型树形结构信息
export const queryArticleClassTree = params =>{
  return onPost('manage/housePropertyArticleClasses/queryArticleClassTree',params)
}
// 保存城市勾选的文章类型信息
export const saveCityArticleClasses = params =>{
  return onPost('manage/housePropertyArticleClasses/saveCityArticleClasses',params)
}
