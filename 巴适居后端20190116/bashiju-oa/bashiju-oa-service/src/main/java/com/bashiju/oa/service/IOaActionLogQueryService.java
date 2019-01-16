/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IManageActionLogQueryService.java   
 * @Package com.bashiju.oa.service      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 办公平台操作日志查询服务接口
 * @ClassName:IManageActionLogQueryService
 * @Description:办公平台操作日志查询服务接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IOaActionLogQueryService {

	/**
	 * 查询办公日志--带分页
	 * @Title: auxManageActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询办公日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxOaActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
}
