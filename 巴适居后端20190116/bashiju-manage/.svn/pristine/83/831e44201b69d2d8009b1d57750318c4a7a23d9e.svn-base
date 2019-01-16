/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentAbilityidentifyMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月7日 上午11:18:00   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  AgentAbilityidentifyMapper   
 * @Description:经纪人能力认定映射接口
 * @author: wangpeng
 * @date:   2018年8月7日 上午11:18:00   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface AgentAbilityidentifyMapper {
	
	/**
	 * @Title: queryAgentAbilityidentify   
	 * @Description: 查询经纪人能力认定
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryAgentAbilityidentify(@Param("userName")String userName,@Param("name")String name);
	
	/**
	 * @Title: queryAgentNameUserIdAndAgentId   
	 * @Description:查询经纪人名称用户id和经纪人编号   
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryAgentNameUserIdAndAgentId();
	
	
	/**
	 * @Title: queryAgentAbilityidentifyById   
	 * @Description: 条件查询经纪人能力认定
	 * @param id id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryAgentAbilityidentifyById(String id);
}
