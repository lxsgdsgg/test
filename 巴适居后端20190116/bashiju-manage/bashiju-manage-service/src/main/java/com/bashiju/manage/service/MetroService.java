/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MetroService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月11日 上午11:27:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  MetroService   
 * @Description:城市地铁线路管理接口
 * @author: wangpeng
 * @date:   2018年8月11日 上午11:27:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface MetroService {
	
	/**
	 * @Title: queryMetro   
	 * @Description: 条件查询城市地铁线路 
	 * @param name 地铁线路名称
	 * @param cityCode 城市编码
	 * @param page 当前页数
	 * @param limit 每页条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryMetro(String name,String cityCode,int page,int limit);
	/**
	 * @Title: queryMetroes   
	 * @Description: 条件查询
	 * @param cityCode 城市地铁线路 
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryMetroes(String cityCode);
	/**
	 * @Title: queryMetroById   
	 * @Description: 条件查询城市地铁线路
	 * @param id 城市地铁线路id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryMetroById(String id);
	
	/**
	 * @Title: delMetroById   
	 * @Description: 逻辑删除城市地铁线路 
	 * @param id 城市地铁地路id
	 * @return: boolean
	 */
	boolean delMetroById(String id);
	/**
	 * @Title: saveOrUpdateMetro   
	 * @Description: 新增或修改地铁线路  
	 * @param paramMap 参数
	 * @return: boolean
	 */
	boolean saveOrUpdateMetro(Map<String,Object> paramMap);
	
	/**
	 * 条件查询地铁线路
	 * @Title: queryMetroByCityCode   
	 * @Description: 条件查询地铁线路
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryMetroByCityCode();
}
