/************************************地图找房、地铁找房数据请求封装**********************************************/


import { onPost} from "../main";

// 地图找房接口
export const queryHouseInfoByMap = params=>{
  return onPost('house/housefindbymap/queryHouseInfoByMapAndZoomNew',params)
}

// 地铁找房接口
export const queryHouseInfoByMetro = params=>{
  return onPost('house/houseseekbymetro/queryHouseSourceStatisticsInfo',params)
}

// 查询地铁站点信息
export const queryStationOpts = params=>{
  return onPost('house/houseseekbymetro/queryStationInfoForSelect',params)
}

// 查询房源详细信息
export const queryHouseDetailByConditions = params=>{
  return onPost('house/housefindbymap/queryHouseDetailByConditions',params)
}

// 查询单条数据信息，用于地图点击点位
export const queryOneObjectForLocation = params=>{
  return onPost('house/housefindbymap/queryOneObjectForLocation',params)
}


