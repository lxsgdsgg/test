/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WelfareConfigurateMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月4日 上午11:01:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  WelfareConfigurateMapper   
 * @Description:福利配置管理持久层  
 * @author: yangz
 * @date:   2018年9月4日 上午11:01:58   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface WelfareConfigurateMapper {

	/**     
	 * @Description: 获取福利方案信息(包括详情信息)
	 * @param name 方案名称(模糊查询)
	 * @return: Page<Map<String,Object>>
	 */
	public List<Map<String,Object>>queryWelfareCOnfigurateAll(@Param("name")String name);

	/**
	 * 是否存在与福利配置方案关联数据
	 * @Description: 是否存在与福利配置方案关联数据   
	 * @param welfareId 福利配置方案编号
	 * @return: long      
	 */ 
	public long isRelation(@Param("welfareId")String welfareId);
}
