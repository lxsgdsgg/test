/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IManageActionLogQueryService.java   
 * @Package com.bashiju.manage.service      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 后台操作日志查询服务接口
 * @ClassName:IManageActionLogQueryService
 * @Description:后台操作日志查询服务接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IManageActionLogQueryService {

	/**
	 * 查询后台日志--带分页
	 * @Title: auxManageActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询后台日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxManageActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 查询系统日志--带分页
	 * @Title: auxSystemActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询系统日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxSystemActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 根据后台编号查询后台操作日志 --带分页
	 * @Title: auxManageActionLogByManageIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据后台编号查询后台操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param id 后台管理编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxManageActionLogByManageId(int page,int limit,String id);
	/**
	 * 根据后台类型查询后台操作日志--带分页
	 * @Title: auxManageActionLogByManageIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据后台编号查询后台操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param type 后台管理类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxManageActionLogByManageType(
			int page,int limit,String type);
}
