package com.bashiju.manage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ErrorLogMapper   
 * @Description:错误日志映射接口
 * @author: wangpeng
 * @date:   2018年5月14日 下午12:02:43   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ErrorLogMapper {
	
	/**
	 * 
	 * @Title: queryErrorLog   
	 * @Description: 条件方法名查询错误信息
	 * @param paramMap 查询参数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryErrorLog(Map<String,Object> paramMap);

}
