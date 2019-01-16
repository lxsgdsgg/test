import {onPost} from "../main";

/***************房源回访周期设置后台接口***********************/
export const  queryResHouseReturnCycleData = params  =>{
  return onPost('manage/resHouseReturnCycle/queryResHouseReturnCycleData')
}

export const  saveOrUpdateResHouseReturnCycle = params  => {
  return onPost('manage/resHouseReturnCycle/saveOrUpdateResHouseReturnCycle', params)
}
