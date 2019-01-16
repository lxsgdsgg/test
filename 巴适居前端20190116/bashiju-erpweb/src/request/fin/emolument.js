/*******************************************************************************************************/
/** *************************************** 财务管理--收入 ***************************************/
/*******************************************************************************************************/
import { onGet, onPost } from '../main'

//获取下拉值
export const querySelectValue = params=>{
  return onPost('fin/querySelectValue', params)
}

//获取福利数据
export const welfareConfigurate = params=>{
  return onPost('fin/welfareConfig/welfareConfigurate', params)
}
//保存福利方案
export const saveOrUpdateWelfareConfig = params=>{
  return onPost('fin/welfareConfig/saveOrUpdateWelfareConfig', params)
}
//保存福利方案明细
export const saveOrupdateWelfareConfigDetail = params=>{
  return onPost('fin/welfareConfig/saveOrupdateWelfareConfigDetail', params)
}
//删除福利方案
export const delWelfareConfig = params=>{
  return onPost('fin/welfareConfig/delWelfareConfig', params)
}

//删除福利方案明细
export const delWelfareConfigDetail = params=>{
  return onPost('fin/welfareConfig/delWelfareConfigDetail', params)
}

//获取社保数据
export const ssfConfigurateAll = params=>{
  return onPost('fin/ssfConfigurate/ssfConfigurateAll', params)
}
//保存社保方案
export const saveOrUpdateSsfConfigurate = params=>{
  return onPost('fin/ssfConfigurate/saveOrUpdateSsfConfigurate', params)
}
//保存社保方案明细
export const saveOrupdateSsfConfigurateDetail = params=>{
  return onPost('fin/ssfConfigurate/saveOrupdateSsfConfigurateDetail', params)
}
//删除社保方案
export const delSsfConfigurate = params=>{
  return onPost('fin/ssfConfigurate/delSsfConfigurate', params)
}

//删除社保方案明细
export const delSsfConfigurateDetail = params=>{
  return onPost('fin/welfareConfig/delSsfConfigurateDetail', params)
}

//获取员工奖罚信息
export const userDesertsInfoDatas = params=>{
  return onPost('fin/userDesertsInfo/userDesertsInfoDatas', params)
}

//保存、编辑奖罚信息
export const saveOrUpdateUserDesertsInfo = params=>{
  return onPost('fin/userDesertsInfo/saveOrUpdateUserDesertsInfo', params)
}
//删除奖罚
export const delUserDesertsInfo = params=>{
  return onPost('fin/userDesertsInfo/delUserDesertsInfo', params)
}

//审核奖罚
export const examineUserDesertsInfo = params=>{
  return onPost('fin/userDesertsInfo/examineUserDesertsInfo', params)
}

//获取一些配置方案
export const getSomeConfig = params=>{
  return onPost('fin/wagesConfig/getSomeConfig', params)
}

//获取员工薪资配置信息
export const employeeWagesConfigurate = params=>{
  return onPost('fin/wagesConfig/employeeWagesConfigurate', params)
}

//保存、编辑员工薪资配置信息
export const saveOrUpdateEmployeeWagesConfigurate = params=>{
  return onPost('fin/wagesConfig/saveOrUpdateEmployeeWagesConfigurate', params)
}

//删除
export const delEmployeeWagesConfigurate = params=>{
  return onPost('fin/wagesConfig/delEmployeeWagesConfigurate', params)
}

//获取业务配置信息
export const getBusinessType = params=>{
  return onPost('fin/businessType/businessType', params)
}

//编辑业务配置信息
export const updateBusinessType = params=>{
  return onPost('fin/businessType/updateBusinessType', params)
}

//获取提成方案页面业务配置信息
export const getConfigBusinessType = params=>{
  return onPost('fin/commissionsConfig/getBusinessType', params)
}
//获取提成方案信息
export const commissionsConfig = params=>{
  return onPost('fin/commissionsConfig/commissionsConfig', params)
}
//保存、编辑提成方案信息
export const saveOrUpdateCommissionsConfig = params=>{
  return onPost('fin/commissionsConfig/saveOrUpdateCommissionsConfig', params)
}

//删除提成方案信息
export const delCommissionsConfig = params=>{
  return onPost('fin/commissionsConfig/delCommissionsConfig', params)
}

//查询通提比例
export const queryUniversalRate = params=>{
  return onPost('fin/commissionsConfig/queryUniversalRate', params)
}

//保存、编辑通提比例
export const saveOrUpdateUniversalRate = params=>{
  return onPost('fin/commissionsConfig/saveOrUpdateUniversalRate', params)
}

//查询提成比列
export const queryCommConfigRate = params=>{
  return onPost('fin/commissionsConfig/queryCommConfigRate', params)
}


//更新提成比列
export const updateCommConfigRate = params=>{
  return onPost('fin/commissionsConfig/updateCommConfigRate', params)
}

//查询实收比列
export const queryCommConfigActualRate = params=>{
  return onPost('fin/commissionsConfig/queryCommConfigActualRate', params)
}

//更新实收比列
export const updateCommConfigActualRate = params=>{
  return onPost('fin/commissionsConfig/updateCommConfigActualRate', params)
}

//获取总部计提信息
export const companyComConfig = params=>{
  return onPost('fin/companyComConfig/companyComConfig', params)
}
//保存、编辑总部计提信息
export const saveOrUpdateCompanyComConfig = params=>{
  return onPost('fin/companyComConfig/saveOrUpdateCompanyComConfig', params)
}
//删除总部计提信息
export const delCompanyComConfig = params=>{
  return onPost('fin/companyComConfig/delCompanyComConfig', params)
}

//获取员工工资信息
export const getEmployeeWagesInfo = params=>{
  return onPost('fin/wagesInfo/getEmployeeWagesInfo', params)
}
//保存员工工资福利
export const updateEmployeeWelfarePay = params=>{
  return onPost('fin/wagesInfo/updateEmployeeWelfarePay', params)
}

//保存员工基本工资
export const updateEmployeeBasePay = params=>{
  return onPost('fin/wagesInfo/updateEmployeeBasePay', params)
}
// 获取公司选择下拉框数据信息
export const queryCompanySelectData = params => {
  return onPost('manage/company/queryCompanyNameAndId', params)
}
//生成员工工资
export const makeEmployeeWages = params=>{
  return onPost('fin/wagesInfo/makeEmployeeWages', params)
}
//审核员工工资
export const examineEmployeeWages = params=>{
  return onPost('fin/wagesInfo/examineEmployeeWages', params)
}
 //获取业务类型
export const getSelectBusinessType = params=>{
 return onPost('fin/commissionsInfo/getBusinessType', params)
}
 //获取员工提成信息
 export const getEmployeeCommissionsInfo = params=>{
   return onPost('fin/commissionsInfo/getEmployeeCommissionsInfo', params)
}
//生成员工提成信息
export const makeEmployeeCommissions = params=>{
  return onPost('fin/commissionsInfo/makeEmployeeCommissions', params)
}









