/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  SsfConfigurateMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月8日 下午2:26:35   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

/**   
 * @ClassName:  SsfConfigurateMapper   
 * @Description:社保配置方案持久层   
 * @author: yangz
 * @date:   2018年9月8日 下午2:26:35   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface SsfConfigurateMapper {

	/**
	 * 查询社保配置方案信息(包括详情信息) 
	 * @Description: 查询社保配置方案信息(包括详情信息)   
	 * @param name 配置方案名称(模糊查询)
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> querySsfConfigurateAll(String name);
	
	
	/**
	 * 是否存在与社保配置方案关联数据
	 * @Description: 是否存在与社保配置方案关联数据   
	 * @param ssfId 社保配置方案编号 
	 * @return: long
	 */
	long isRelation(String ssfId);
	
}
