/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealActionLogQueryMapper.java   
 * @Package com.bashiju.deal.mapper      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:05:55   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.deal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 成交操作日志查询持久层接口
 * @ClassName:DealActionLogQueryMapper
 * @Description:成交操作日志查询持久层接口
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:05:55
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DealActionLogQueryMapper {
	/**
	 * 查询成交日志--带分页
	 * @Title: auxDealActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询成交日志--带分页
	 * @param paramMap 参数对象
	 * @return      
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxDealActionLogPageList(Map<String,Object> paramMap);
	/**
	 * 根据成交编号查询成交操作日志 --带分页
	 * @Title: auxDealActionLogByDealIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据成交编号查询成交操作日志
	 * @param dealId 成交编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxDealActionLogByDealId(@Param("dealId")String dealId);
	/**
	 * 根据成交类型查询成交操作日志--带分页
	 * @Title: auxDealActionLogByDealIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据成交编号查询成交操作日志
	 * @param dealType
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public List<Map<String,Object>> auxDealActionLogByDealType(@Param("dealType")String dealType);
}
