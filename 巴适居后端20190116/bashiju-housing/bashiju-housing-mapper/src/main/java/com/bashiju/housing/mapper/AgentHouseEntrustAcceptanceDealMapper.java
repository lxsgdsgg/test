/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentHouseEntrustAcceptanceDealMapper.java   
 * @Package com.bashiju.manage.mapper      
 * @author: zuoyuntao     
 * @date:   2018年8月10日 上午11:08:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.housing.mapper;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 经纪人受理持久层接口
 * @ClassName:AgentHouseEntrustAcceptanceDealMapper
 * @Description:经纪人受理持久层接口
 * @author:zuoyuntao
 * @date:2018年8月10日 上午11:08:34
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface AgentHouseEntrustAcceptanceDealMapper {
	/**
	 * 查询经纪人受理数据信息
	 * @Title: queryAgentAcceptanceList
	 * @author: zuoyuntao  
	 * @Description:查询经纪人受理数据信息
	 * @param paraMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryAgentAcceptanceList(Map<String,Object> paraMap);
	/**
	 * 查询客户房源委托数据信息
	 * @Title: queryCustomerHouseEntrust
	 * @author: zuoyuntao  
	 * @Description:查询客户房源委托数据信息
	 * @param paraMap
	 * @return      
	 * Page<Map<String,Object>> 
	 */
	public Page<Map<String,Object>> queryCustomerHouseEntrust(Map<String,Object> paraMap);
}
