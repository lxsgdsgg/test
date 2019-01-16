/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDealActionLogQueryService.java   
 * @Package com.bashiju.deal.service      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 客源操作日志查询服务接口
 * @ClassName:ICustomerActionLogQueryService
 * @Description:客源操作日志查询服务接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerActionLogQueryService {

	/**
	 * 查询客源日志--带分页
	 * @Title: auxCustomerActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询客源日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxCustomerActionLogPageList(
			int page,int limit,Map<String,Object> paramMap);
	/**
	 * 根据客源编号查询客源操作日志 --带分页
	 * @Title: auxCustomerActionLogByDemandId
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param demandId 客源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxCustomerActionLogByDemandId(int page,int limit,String demandId);
	/**
	 * 根据客源类型查询客源操作日志--带分页
	 * @Title: auxCustomerActionLogByCustomerType
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param demandType 客源类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> auxCustomerActionLogByCustomerType(
			int page,int limit,String demandType);
}
