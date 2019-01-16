/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDealActionLogQueryService.java   
 * @Package com.bashiju.deal.service      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.deal.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 成交操作日志查询服务接口
 * @ClassName:IDealActionLogQueryService
 * @Description:成交操作日志查询服务接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IDealActionLogQueryService {

	/**
	 * 查询成交日志--带分页
	 * @Title: auxDealActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询成交日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxDealActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 根据成交编号查询成交操作日志 --带分页
	 * @Title: auxDealActionLogByDealIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据成交编号查询成交操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param dealId 成交编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxDealActionLogByDealId(int page,int limit,String dealId);
	/**
	 * 根据成交类型查询成交操作日志--带分页
	 * @Title: auxDealActionLogByDealIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据成交编号查询成交操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param dealType 成交类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxDealActionLogByDealType(
			int page,int limit,String dealType);
}
