/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerActionLogQueryMapper.java   
 * @Package com.bashiju.deal.mapper      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.customer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 客源操作日志查询持久层接口
 * @ClassName:CustomerActionLogQueryMapper
 * @Description:客源操作日志查询持久层接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CustomerActionLogQueryMapper {
	/**
	 * 查询客源日志--带分页
	 * @Title: auxCustomerActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询客源日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxCustomerActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 根据客源编号查询客源操作日志 --带分页
	 * @Title: auxCustomerActionLogByCustomerIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param demandId 客源编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxCustomerActionLogByCustomerId(@Param("demandId")String demandId);
	/**
	 * 根据客源类型查询客源操作日志--带分页
	 * @Title: auxCustomerActionLogByCustomerIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据客源编号查询客源操作日志
	 * @param demandType 客源类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxCustomerActionLogByType(@Param("demandType")String demandType);
}
