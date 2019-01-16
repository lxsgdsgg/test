package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  LoginRecordService   
 * @Description:登陆日志接口
 * @author: wangpeng
 * @date:   2018年5月14日 上午11:53:11   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface LoginRecordService {
	/**
	 * 
	 * @Title: queryLogin_record   
	 * @Description: 查询登陆日志
	 * @param phone 电话
	 * @param loginTime 登陆时间
	 * @param operator 操作人
	 * @param areaName 区域名称
	 * @param name 登陆名字
	 * @param beginAddTime 开始时间
	 * @param endAddTime 结束时间
	 * @param pageNum 当前页
	 * @param pageSize 每页要显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>>  queryLoginRecord(Map<String,Object> paramMap,int pageNum,int pageSize);
}
