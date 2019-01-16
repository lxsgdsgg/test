package com.bashiju.quartz.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName:  ScheduleJob   
 * @Description:计划任务  
 * @author: zhaobin
 * @date:   2018年4月15日 下午10:11:40   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class ScheduleJob implements Serializable{
	
	private static final long serialVersionUID = -6268330198581041868L;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getJobDescription() {
		return JobDescription;
	}
	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	public String getTargetParam() {
		return targetParam;
	}
	public void setTargetParam(String targetParam) {
		this.targetParam = targetParam;
	}
	/** 任务ID */
	private String id;
    /** 记录是否可用  1可用 0删除*/
    private String isValid;
    /** 任务状态 0禁用 1启用 */
    private String status;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务内容 */
    private String JobDescription;
    /** 任务类型，与rabbit 的routingKey挂钩 */
    private String routingKey;
    /** 参数 */
    private String targetParam;
 
}
