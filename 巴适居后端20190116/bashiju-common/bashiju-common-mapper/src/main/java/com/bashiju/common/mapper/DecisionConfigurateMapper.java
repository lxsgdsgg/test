/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DecisionConfigurateMapper.java   
 * @Package com.bashiju.common.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月6日 下午3:44:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  DecisionConfigurateMapper   
 * @Description:决策配置对外服务持久层   
 * @author: yangz
 * @date:   2018年7月6日 下午3:44:50   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DecisionConfigurateMapper {

	/**     
	 * @Description: 根据id获取决策配置信息   
	 * @param id决策配置id
	 * @param cityCode 城市编码
	 * @return: Map<String,Object>      
	 */ 
	Map<String, Object> getDecisionConfigurate(@Param("id")String id, @Param("cityCode")String cityCode);

	/**
	 * @Description: 根据决策配置查询该决策涉及的所有城市决策信息   
	 * @param id 决策配置id(必填)
	 * @return: List<Map<String,Object>>    
	 */ 
	List<Map<String, Object>> queryDecisionConfigurate(@Param("id")String id);

}
