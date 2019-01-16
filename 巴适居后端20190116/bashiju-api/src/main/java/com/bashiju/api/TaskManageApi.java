package com.bashiju.api;

import java.util.Date;

/**   
 * @ClassName  TaskManageApi   
 * @DescriptionTODO(这里用一句话描述这个类的作用)   
 * @author zhaobin
 * @date   2018年5月16日 下午4:56:59   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface TaskManageApi {
	/**
	 * 
	 * @Title addTask   
	 * @Description 添加任务   
	 * @param  day 几天后执行
	 * @param  taskContext 执行的内容
	 * @param  routingKey 队列路由键
	 * @param  targetParam 参数
	 * @return long 返回记录Id
	 */
	public long addTask(int day,String taskContext,String routingKey,String targetParam);
	/*public long updateTask();*/
	/**
	 * 
	 * @Title delTask   
	 * @Description 逻辑删除任务   
	 * @param  id 需要删除的主键
	 * @return long 返回受影响的行数
	 */
	public boolean delTask(long id);
}
