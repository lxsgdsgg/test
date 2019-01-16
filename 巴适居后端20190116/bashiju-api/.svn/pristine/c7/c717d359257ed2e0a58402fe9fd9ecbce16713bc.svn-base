/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DecisionConfigurateServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月6日 下午3:14:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  DecisionConfigurateServiceApi   
 * @Description:决策配置对外接口   
 * @author: yangz
 * @date:   2018年7月6日 下午3:14:41   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DecisionConfigurateServiceApi {

	/**
	 * @Description: 根据id及城市编码查询决策配置信息   
	 * @param id 决策配置id (必填)
	 * @param cityCode 城市编码 (必填)
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getDecisionConfigurate(String id,String cityCode);
	
	/**
	 * @Description: 根据决策配置查询该决策涉及的所有城市决策信息   
	 * @param id 决策配置id(必填)
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDecisionConfigurate(String id);
	
}
