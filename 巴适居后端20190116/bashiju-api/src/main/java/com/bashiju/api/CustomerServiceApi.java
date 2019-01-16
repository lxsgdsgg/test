package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**
 * 
 *   客源需求公共服务
 * @ClassName:  CustomerServiceApi  
 * @Description:   客源需求公共服务
 * @author: wangkaifa
 * @date:   2018年6月23日 上午9:20:23       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CustomerServiceApi {
	/**
	 * 
		 * 根据客源需求id查询经纪人列表
		 * @Description: 根据客源需求id查询经纪人列表 
		 * @param demandId 客源需求id
		 * @return 经纪人列表
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryAgentsByDemandId(String demandId);
}
