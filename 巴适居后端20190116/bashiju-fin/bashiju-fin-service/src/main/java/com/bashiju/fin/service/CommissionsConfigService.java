/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommissionsConfigService.java   
 * @Package com.bashiju.fin.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月11日 下午6:10:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  CommissionsConfigService   
 * @Description:提成方案管理 
 * @author: yangz
 * @date:   2018年9月11日 下午6:10:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommissionsConfigService {

	/**
	 * 查询提成方案信息
	 * @Description: 查询提成方案信息  
	 * @param name 提成方案名称
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommissionsConfig(String name); 
	
	/**
	 * 查询提成方案信息 
	 * @Description: 查询提成方案信息   
	 * @param name 提成方案名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryCommissionsConfig(String name,int pageNum,int pageSize);
	
	/**
	 * 保存提成方案信息
	 * @Description: 保存提成方案信息   
	 * @param map 待保存的提成方案信息
	 * @return: boolean
	 */
	public boolean saveOrUpdateCommissionsConfig(Map<String,Object> map);
	
	/**
	 * 删除提成方案
	 * @Description: 删除提成方案   
	 * @param commConfigId 要删除的提成方案编号
	 * @return: boolean
	 */
	public boolean delCommissionsConfig(String commConfigId);
	
	/**
	 * 是否存在与提成方案相关联数据
	 * @Description: 是否存在与提成方案相关联数据   
	 * @param commConfigId 待匹配的提成方案编号
	 * @return: boolean
	 */
	public boolean isRelationCommissionConfig(String commConfigId);
	
	
	/**
	 *  根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息
	 * @Description: 根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息   
	 * @param commConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommConfigActualRate(String commConfigId,String BusiTypeId);
	
	/**
	 * 保存实收提成比例信息
	 * @Description: 保存实收提成比例信息
	 * @param map 待保存的实收提成比例
	 * @return: boolean
	 */
	public boolean saveOrUpdateCommConfigActualRate(List<Map<String,Object>> editDataList,List<Map<String,Object>> addDataList,String delId);
	
	/**
	 * 根据提成配置方案编号与业务类型查询该方案下提成计算比例信息     
	 * @Description:根据提成配置方案编号与业务类型查询该方案下提成计算比例信息       
	 * @param commConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @param months 月份 (例：2018-01)
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommConfigRate(String commConfigId,String BusiTypeId,String months);
	
	
	/**
	 * 保存提成计算比例信息
	 * @Description: 保存提成计算比例信息
	 * @param map 待保存的提成比例
	 * @return: boolean
	 */
	public boolean saveOrUpdateCommConfigRate(List<Map<String,Object>> editDataList,List<Map<String,Object>> addDataList,String delId);
	/**
	 * 
	 * @Title: 查询通提比例   
	 * @Description: 查询通提比例  
	 * @param: @param commConfigId：提成方案ID
	 * @param: @param BusiTypeId  :业务类型编号    
	 * @return: void      
	 * @throws
	 */
	public Map<String,Object> queryUniversalRate(String commConfigId,String BusiTypeId);
	
	/**
	 * 
	 * @Title: 保存或更新通提比例 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param map      
	 * @return: void      
	 * @throws
	 */
	public boolean saveOrUpdateUniversalRate(Map<String,Object> map);
}
