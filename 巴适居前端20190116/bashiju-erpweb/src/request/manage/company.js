/*****************公司数据接口******************/

import  {onGet,onPost} from '../main'
export const  queryCompanyData = params =>{
  return onPost('manage/company/getCompanyData',params)
}

export const delCompany = params =>{
  return onPost('manage/company/delCompany',params)
}

export  const  saveOrUpdateCompany = params =>{
  return onPost('manage/company/saveOrUpdateCompany',params)
}

export const  queryCompanyIdIsExist = params =>{
  return onPost('manage/company/queryCompanyIdIsExist',params)
}

//获取公司状态
export const  getCompanyStatus = params =>{
  return onPost('manage/company/getCompanyStatus',params)
}
