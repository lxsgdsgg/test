/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TaskManageMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年12月3日 下午4:47:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  TaskManageMapper   
 * @Description:任务管理映射器
 * @author: wangpeng
 * @date:   2018年12月3日 下午4:47:06   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface TaskManageMapper {
	
	/**
	 * 条件任务信息
	 * @Title: queryTask   
	 * @Description: 条件任务信息
	 * @param taskName
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryTask(@Param("taskName")String taskName);
	
	/**
	 *  条件查询部门下的用户
	 * @Title: queryUserBelongDept   
	 * @Description: 条件查询部门下的用户
	 * @param deptId 部门编号
	 * @return: Map<String,Object>
	 */
	public List<Map<String,Object>> queryUserBelongDept(@Param("deptId")String deptId);
	
	/**
	 *  根任务编号和部门编号查询已分配任务的人
	 * @Title: queryAlreadyDistributedTaskUser   
	 * @Description: 根任务编号和部门编号查询已分配任务的人
	 * @param taskId 任务编号
	 * @param deptId 部门编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAlreadyDistributedTaskUser(
			@Param("taskId")String taskId,@Param("deptId")String deptId);
	
	/**
	 * 查询本公司已是否已创建任务
	 * @Title: queryTaskIsExist   
	 * @Description: 查询本公司已是否已创建任务
	 * @param companyId 公司编号
	 * @param beginTime 任务开始时间
	 * @param endTime 任务结束时间
	 * @return: long
	 */
	public long queryTaskIsExist(@Param("companyId")String companyId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	/**
	 *条件查询所有经纪人
	 * @Title: queryAllAgent   
	 * @Description: 条件查询所有经纪人
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAllAgent(@Param("companyId")String companyId);
	
	/**
	 * 查询经纪人是否已分配任务
	 * @Title: queryAgentIsExistTask   
	 * @Description: 查询经纪人是否已分配任务
	 * @param taskId 任务编号
	 * @param agentId 经纪人（用户）编号
	 * @return: long 
	 */
	public long queryAgentIsExistTask(@Param("taskId")String taskId,@Param("agentId")String agentId);
	
	/**
	 *  条件查询经纪人信息
	 * @Title: queryAgentInfoById   
	 * @Description: 条件查询经纪人信息
	 * @param agentId 经纪人编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryAgentInfoById(@Param("agentId")String agentId);
	
	/**
	 * 条件查询任务详情  
	 * @Title: queryTaskDetail   
	 * @Description: 条件查询任务详情  
	 * @param taskId 任务编号
	 * @param agentId 经纪人编号
	 * @param deptId 部门编号
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryTaskDetail(@Param("taskId")String taskId,@Param("deptId")String deptId,@Param("agentId")String agentId);
	
	/**
	 * 查询经纪人任务详情
	 * @Title: queryAgentDistributedTaskDetail   
	 * @Description: 查询经纪人任务详情
	 * @param agentId 经纪人编号
	 * @param taskId 任务编号
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryAgentDistributedTaskDetail(@Param("agentId")String agentId,@Param("taskId")String taskId);
	
	
	/**
	 * 
	 * @Title: queryAgentTaksCompletedNumber   
	 * @Description: 条件查询经纪人任务完成量
	 * @param agentId 经纪人编号
	 * @param beginTime 任务开始时间
	 * @param endTime 任务结束时间
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryAgentTaksCompletedNumber(@Param("agentId")String agentId,
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
