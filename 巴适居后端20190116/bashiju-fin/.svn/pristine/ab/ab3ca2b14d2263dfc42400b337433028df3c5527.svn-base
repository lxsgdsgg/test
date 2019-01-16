/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WelfareConfigurateService.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月4日 上午10:44:09   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  WelfareConfigurateService   
 * @Description:福利配置管理接口   
 * @author: yangz
 * @date:   2018年9月4日 上午10:44:09   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface WelfareConfigurateService {
	
	/**     
	 * @Description: 获取福利方案信息(包括详情信息)
	 * @param name 方案名称(模糊查询)
	 * @return: Page<Map<String,Object>>
	 */
	public List<Map<String,Object>>queryWelfareCOnfigurateAll(String name);

	/**     
	 * @Description: 获取福利方案信息(包括详情信息)
	 * @param name 方案名称(模糊查询)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>      
	 */ 
	Page<Map<String, Object>> queryWelfareCOnfigurateAll(String name, int pageNum, int pageSize);

	/**
	 * 保存配置方案信息   
	 * @Description: 保存配置方案信息   
	 * @param map 待保存的方案配置信息
	 * @return: boolean
	 */
	boolean saveOrUpdateWelfareConfig(Map<String,Object> map);
	
	/**
	 * 保存配置方案明细信息  
	 * @Description: 保存配置方案明细信息  
	 * @param map 待保存的方案配置明细信息
	 * @return: boolean
	 */
	long saveOrupdateWelfareConfigDetail(Map<String,Object> map);
	
	/**
	 * 删除福利配置方案
	 * @Description: 删除福利配置方案  
	 * @param welfareId 福利配置方案编号 
	 * @return: boolean
	 */
	boolean delWelfareConfig(String welfareId);
	
	/**
	 * 是否存在与福利配置方案关联数据
	 * @Description: 是否存在与福利配置方案关联数据   
	 * @param welfareId 福利配置方案编号 
	 * @return: boolean
	 */
	boolean isRelation(String welfareId);
	
	/**
	 * 删除福利方案详情   
	 * @Description: 删除福利方案详情   
	 * @param welfareDetailId 福利方案详情编号
	 * @return: boolean
	 */
	boolean delWelfareConfigDetail(String welfareDetailId);
	
}
