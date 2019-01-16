/*******************************************************************************************************/
/** *************************************** 房源平台--房源跟进记录 *****************************************/
/*******************************************************************************************************/
import {onGet, onPost} from '../main'

// 查询房源跟进记录主页数据
export const queryHouseFollowRecordPageList = params => {
  return onPost('house/HsFollowRecord/queryHsFollowRecordData', params)
}

// 查询房源跟进记录详细
export const queryHouseFollowRecordDetailPageList = params => {
  return onPost('house/HsFollowRecord/queryAllHsFollowRecordByHouseId', params)
}

// 查询房源跟进房源交易类型
export const houseTransactionType = params => {
  return onPost('house/HsFollowRecord/houseTransactionType', params)
}

// 查询房源跟进状态
export const queryHouseStatus = params => {
  return onPost('house/HsFollowRecord/houseStatus', params)
}
