package com.bashiju.manage.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  LoginRecordMapper   
 * @Description:用户登录日志映射接口   
 * @author: wangpeng
 * @date:   2018年5月14日 上午11:45:17   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface LoginRecordMapper {
	/**
	 * 
	 * @Title: queryLoginRecord   
	 * @Description: 条件查询用户登录日志
	 * @param paramMap 查询参数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryLoginRecord(Map<String,Object> paramMap);

}
