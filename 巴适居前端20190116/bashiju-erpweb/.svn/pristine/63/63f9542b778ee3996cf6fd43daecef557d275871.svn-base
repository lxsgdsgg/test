import {onPost,onGet} from "../main";

//添加房源成交
export const addHouseDeal = params =>{
  return onPost('house/dealHousing',params)
}

//查询合同信息
export const queryContractInfo = params =>{
  return onPost('deal/queryContractByHouseId',params)
}


//查询动态下拉
export const querySelectItems= params =>{
  return onPost('manage/dynamicConfigure/getSelectValue/one',params)
}

//查询佣金
export const queryCommissionPrice= params =>{
  return onPost('house/queryCommissionPrice',params)
}
