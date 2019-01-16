package com.bashiju.quartz.mapper;
/**
 * 
 *   采集房源爬网
 * @ClassName:  HousingCrawlerMapper   
 * @Description:  采集房源爬网
 * @author: wangkaifa
 * @date:   2018年7月2日 上午9:59:22       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HousingCrawlerMapper {
	/**
	 * 
		 * 查询房源采集路径配置列表
		 * @Description: 查询房源采集路径配置列表
		 * @return 
		 * List<Map<String,Object>>
	 */
	List<Map<String, Object>> queryHouseCrawlerConfigure();
	/**
	 * 
		 * 根据url查询数据库条数
		 * @Description: 根据url查询数据库条数
		 * @param url
		 * @return 
		 * Integer
	 */
	Integer queryCountByUrl(@Param("houseUrl")String url);
	
	/**
	 * 
		 * 根据id查询解析页面模板
		 * @Description: 根据id查询解析页面模板
		 * @param id 模板id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryHouseCrawlerTemplateById(@Param("id")Long id);
	/**
	 * 
		 * 根据id查询爬网url配置
		 * @Description: 根据id查询爬网url配置
		 * @param id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryHouseCrawlerConfigureById(@Param("id")Long id);
}
