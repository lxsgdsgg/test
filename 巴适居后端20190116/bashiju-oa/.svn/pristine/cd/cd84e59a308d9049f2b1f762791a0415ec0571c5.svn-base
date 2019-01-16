/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TaskManageService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年12月3日 下午4:55:14   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**  
 * 任务管理 
 * @ClassName:  TaskManageService   
 * @Description:任务管理
 * @author: wangpeng
 * @date:   2018年12月3日 下午4:55:14   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface TaskManageService {
	
	/**
	 * 条件任务信息
	 * @Title: queryTask   
	 * @Description:条件任务信息
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @param taskName 任务名称
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryTask(String taskName,int page,int limit);
	
	
	/**
	 * 逻辑删除任务
	 * @Title: delTask   
	 * @Description: 逻辑删除任务
	 * @param taskId 任务编号
	 * @return: boolean
	 */
	public boolean delTask(String taskId);
	
	/**
	 * 新增或修改任务
	 * @Title: addOrUpdTask   
	 * @Description: 新增或修改任务
	 * @param map
	 * @return: boolean
	 */
	public boolean addOrUpdTask(Map<String,Object> map);
	
	/**
	 * 分配任务
	 * @Title: taskDistributed   
	 * @Description: 分配任务
	 * @param paramMap
	 * @return: boolean
	 */
	public boolean taskDistributed(Map<String,Object> paramMap);
	
	/**
	 * 条件查询任务详情  
	 * @Title: queryTaskDetail   
	 * @Description: 条件查询任务详情  
	 * @param taskId 任务编号
	 * @param agentId 经纪人编号
	 * @param deptId 部门编号
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryTaskDetail(String taskId,String deptId,String agentId,int page,int limit);
	
	/**
	 * 条件查询经纪人任务详情   
	 * @Title: queryAgentDistributedTaskDetail   
	 * @Description: 条件查询经纪人任务详情   
	 * @param agentId 经纪人编号
	 * @param taskId 任务编号
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryAgentDistributedTaskDetail(String agentId,String taskId);
	
	/**
	 * 删除经纪人任务
	 * @Title: delAgentTask    
	 * @Description: 删除经纪人任务  
	 * @param id 任务详情编号
	 * @return: boolean
	 */
	public boolean delAgentTask(String id);
	
	/**
	 * 修改经纪人任务分配
	 * @Title: updateAgentTaskDistributed   
	 * @Description: 修改经纪人任务分配
	 * @param paramMap
	 * @return: boolean
	 */
	public boolean updateAgentTaskDistributed(Map<String,Object> paramMap);
	
	/**
	 * 
	 * @Title: queryAgentTaksCompletedNumber   
	 * @Description: 条件查询经纪人任务完成量
	 * @param operatorId 经纪人编号
	 * @param beginTime 任务开始时间
	 * @param endTime 任务结束时间
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryAgentTaksCompletedNumber(String operatorId,
			String beginTime,String endTime);
}
