import {onPost} from "../main";

/*************任务管理后台接口**********/
//查询任务信息
export  const queryTask = params =>{
  return  onPost('oa/task/queryTask',params)
}
//逻辑删除任务
export  const delTask = params =>{
  return  onPost('oa/task/delTask',params)
}
//逻辑删除任务
export  const addOrUpdTask = params =>{
  return  onPost('oa/task/addOrUpdTask',params)
}
//分配任务
export  const taskDistributed = params =>{
  return  onPost('oa/task/taskDistributed',params)
}
// 根据权限查询部门、用户
export  const queryDepartmentUserList = params =>{
  return  onPost('manage/commonselect/queryDepartmentUserList',params)
}
//查询任务详情
export  const queryTaskDetail = params =>{
  return  onPost('oa/task/queryTaskDetail',params)
}
//查询经纪人的任务详情
export  const queryAgentDistributedTaskDetail = params =>{
  return  onPost('oa/task/queryAgentDistributedTaskDetail',params)
}
//逻辑删除经纪人任务分配
export  const delAgentTask = params =>{
  return  onPost('oa/task/delAgentTask',params)
}
//修改经纪人任务分配
export  const updateAgentTaskDistributed = params =>{
  return  onPost('oa/task/updateAgentTaskDistributed',params)
}
//根据任务时间 查询经纪人任务完成量
export  const queryAgentTaksCompletedNumber = params =>{
  return  onPost('oa/task/queryAgentTaksCompletedNumber',params)
}
