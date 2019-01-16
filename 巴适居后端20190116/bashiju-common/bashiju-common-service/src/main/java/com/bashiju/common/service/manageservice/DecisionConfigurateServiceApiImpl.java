/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DecisionConfigurateServiceApiImpl.java   
 * @Package com.bashiju.common.service.manageservice   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月6日 下午3:27:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.service.manageservice;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.api.DecisionConfigurateServiceApi;
import com.bashiju.common.mapper.DecisionConfigurateMapper;

/**   
 * @ClassName:  DecisionConfigurateServiceApiImpl   
 * @Description:决策配置对外服务   
 * @author: yangz
 * @date:   2018年7月6日 下午3:27:47   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class DecisionConfigurateServiceApiImpl implements DecisionConfigurateServiceApi {

	@Autowired
	private DecisionConfigurateMapper decisionConfigurateMapper;
	
	
	/**
	 * @Description: 根据id及城市编码查询决策配置信息   
	 * @param id 决策配置id
	 * @param cityCode 城市编码
	 * @return: Map<String,Object>
	 * @see com.bashiju.api.DecisionConfigurateServiceApi#getDecisionConfigurate(java.lang.String)   
	 */
	@Override
	public Map<String, Object> getDecisionConfigurate(String id,String cityCode) {
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(cityCode))
			return null;
		return decisionConfigurateMapper.getDecisionConfigurate(id,cityCode);
	}


	/**
	 * @Description: 根据决策配置查询该决策涉及的所有城市决策信息   
	 * @param id 决策配置id(必填)
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.api.DecisionConfigurateServiceApi#queryDecisionConfigurate(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryDecisionConfigurate(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		return decisionConfigurateMapper.queryDecisionConfigurate(id);
	}

}
