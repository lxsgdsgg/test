/*******************************************************************************************************/
/** *************************************** 一手房源 ***************************************/
/*******************************************************************************************************/
import {onGet, onPost, BASE_URL} from '../main'

// 获取新房列表
export const queryNewHouseList = params => {
  return onPost('house/newHouse/queryNewHouseList', params)
}

// 新增、楼盘
export const addNewHouse = params => {
  return onPost('house/newHouse/addNewHouse', params)
}

// 修改楼盘
export const updateHousing = params => {
  return onPost('house/newHouse/updateHousing', params)
}

// 设置新房楼盘同步状态
export const setSynchron = params => {
  return onPost('house/newHouse/setSynchron', params)
}

// 设置新房楼盘推荐状态
export const setIsGood = params => {
  return onPost('house/newHouse/setIsGood', params)
}

// 删除楼盘
export const deleteNewHouse = params => {
  return onPost('house/newHouse/deleteNewHouse', params)
}

// 获取楼盘详情信息
export const getNewHouseInfo = params => {
  return onGet('house/newHouse/getNewHouseInfo', params)
}

// 设置全景看房地址
export const setOverallView  = params => {
  return onPost('house/newHouse/setOverallview', params)
}

// 获取楼盘动态
export const queryPropertyDynamicByProjectId = params => {
  return onGet('house/propertyDynamic/queryPropertyDynamicByProjectId', params)
}

// 新增楼盘动态
export const saveOrUpdatePropertyDynamic = params => {
  return onPost('house/propertyDynamic/saveOrUpdatePropertyDynamic', params)
}

// 删除楼盘动态
export const delPropertyDynamic = params => {
  return onPost('house/propertyDynamic/delPropertyDynamic', params)
}

// 查询预售证号
export const queryPresalePermitListInfoById = params => {
  return onGet('house/propertyDynamic/queryPresalePermitListInfoByProjectId', params)
}

// 新增或修改预售证信息
export const saveOrUpdatePresalePermitListInfo = params => {
  return onPost('house/propertyDynamic/saveOrUpdatePresalePermitListInfo', params)
}

// 删除预售证号
export const delPresalePermitListInfo = params => {
  return onPost('house/propertyDynamic/delPresalePermitListInfo', params)
}

// 查询楼盘开盘日期
export const queryPropertyOpenDateByProjectId = params => {
  return onGet('house/propertyDynamic/queryPropertyOpenDateByProjectId', params)
}

//  新增或修改楼盘开盘日期
export const saveOrUpdatePropertyOpenDate = params => {
  return onPost('house/propertyDynamic/saveOrUpdatePropertyOpenDate', params)
}

// 删除楼盘开盘日期日期
export const delPropertyOpenDateById = params => {
  return onPost('house/propertyDynamic/delPropertyOpenDateById', params)
}

// 查询楼盘价格历史
export const queryPropertyPriceTrendByProjectId = params => {
  return onGet('house/propertyDynamic/queryPropertyPriceTrendByProjectId', params)
}

// 删除楼盘价格历史
export const delPropertyPriceTrendByProjectId = params => {
  return onPost('house/propertyDynamic/delPropertyPriceTrendByProjectId', params)
}

// 新增或修改楼盘价格历史
export const saveOrUpdatePropertyPriceTrend = params => {
  return onPost('house/propertyDynamic/saveOrUpdatePropertyPriceTrend', params)
}

// 查询楼盘交房日期
export const queryRoomTimeByProjectId = params => {
  return onGet('house/propertyDynamic/queryRoomTimeByProjectId', params)
}

// 删除楼盘交房日期
export const delRoomTimeByProjectId = params => {
  return onPost('house/propertyDynamic/delRoomTimeByProjectId', params)
}

// 新增或修改楼盘交房日期
export const saveOrUpdateRoomTime = params => {
  return onPost('house/propertyDynamic/saveOrUpdateRoomTime', params)
}

// 查询楼盘带看记录
export const getLookHouse = params => {
  return onGet('house/newHouse/getLookHouse', params)
}


// 查询新房楼盘户型图
export const getHousetypeImg = params => {
  return onPost('house/newHouse/getHousetypeImg', params)
}

// 删除新房楼盘户型图
export const delHousetypeImg = params => {
  return onPost('house/newHouse/delHousetypeImg', params)
}

// 编辑新房户型图信息
export const updateHousetypeImg = (params, cfg) => {
  return onPost('house/newHouse/updateHousetypeImg', params, cfg)
}

// 上传户型图路径
export const saveHousetypeImgUrl = BASE_URL + 'house/newHouse/saveHousetypeImg'

// 修改户型图路径
export const updateHousetypePictureUrl = BASE_URL + 'house/newHouse/updateHousetypePicture'

// 上传户型视频地址
export const saveHousetypeVideo = BASE_URL + 'house/newHouse/saveHousetypeVideo'

// 添加新房成交
export const addNewHouseDeal = params =>{
  return onPost('house/newHouse/dealNewHousing',params)
}

// 修改楼盘佣金
export const updateCommissions = params =>{
  return onPost('house/newHouse/updateCommissions',params)
}

// 修改楼盘维护人
export const updateCustServicer = params =>{
  return onPost('house/newHouse/updateCustServicer',params)
}

// 修改楼盘维护人
export const queryCommissionPrice = params =>{
  return onPost('house/newHouse/queryCommissionPrice',params)
}

