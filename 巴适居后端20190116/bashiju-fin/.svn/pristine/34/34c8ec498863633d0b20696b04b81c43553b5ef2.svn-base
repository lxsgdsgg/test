/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommissionsConfigMapper.java   
 * @Package com.bashiju.fin.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月12日 下午3:11:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  CommissionsConfigMapper   
 * @Description:提成方案管理持久层  
 * @author: yangz
 * @date:   2018年9月12日 下午3:11:19   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommissionsConfigMapper {

	/**
	 * 查询提成方案信息
	 * @Description: 查询提成方案信息  
	 * @param name 提成方案名称
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommissionsConfig(@Param("name")String name); 
	
	
	/**
	 * 是否存在与提成方案相关联数据
	 * @Description: 是否存在与提成方案相关联数据   
	 * @param commConfigId 待匹配的提成方案编号
	 * @return: int
	 */
	public int isRelationCommissionConfig(@Param("commConfigId")String commConfigId);
	
	
	/**
	 *  根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息
	 * @Description: 根据提成配置方案编号与业务类型查询该方案下的实收提成比例信息   
	 * @param comConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommConfigActualRate(@Param("comConfigId")String comConfigId,@Param("BusiTypeId")String BusiTypeId);
	
	
	/**
	 * 根据提成配置方案编号与业务类型查询该方案下提成计算比例信息     
	 * @Description:根据提成配置方案编号与业务类型查询该方案下提成计算比例信息       
	 * @param comConfigId 提成配置方案编号
	 * @param BusiTypeId 业务类型编号
	 * @param months 月份(例：2018-01)
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryCommConfigRate(@Param("comConfigId")String comConfigId,@Param("BusiTypeId")String BusiTypeId,@Param("months")String months);
	/**
	 * 
	 * @Title: 查询通提比例 
	 * @Description: 查询通提比例   
	 * @param: @param comConfigId
	 * @param: @param BusiTypeId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public Map<String,Object> queryUniversalRate(@Param("comConfigId")String comConfigId,@Param("BusiTypeId")String BusiTypeId);
	
}
