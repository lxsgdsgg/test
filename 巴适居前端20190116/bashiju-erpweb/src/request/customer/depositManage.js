/********************************定金管理后台接口**************************/
import {onPost} from "../main";

export  const  getDepositData  = params  =>{
  return onPost('customer/depositmanage/getDepositManagePage',params)
}


// 获取票据编号
export const getBillNoForCommission = params=>{
  return onPost('deal/deal/getBillNoForCommission',params)
}


//搜索框下拉数据源
export  const  depositManageDropdown  = params  =>{
  return onPost('customer/depositmanage/depositManageDropdown',params)
}

//退定金
export  const  retreatDeposit  = params  =>{
  return onPost('customer/depositmanage/retreatDeposit',params)
}

//确认定金
export  const  comfirmDeposit  = params  =>{
  return onPost('customer/depositmanage/comfirmDeposit',params)
}

//确认定金
export  const  rebackDepositRequest  = params  =>{
  return onPost('customer/depositmanage/rebackDepositRequest',params)
}

//保存定金支出数据
export  const  savePayOffData  = (params,depositId)  =>{
  return onPost('customer/depositmanage/savePayOffData',params,depositId)
}

//
export  const  customerFindHouseList  = params  =>{
  return onPost('customer/customerFindHouseList',params)
}


export  const  updateDepositData  = (params,cfg)  =>{
  return onPost('customer/depositmanage/updateDepositData',params,cfg)
}
