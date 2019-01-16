package com.bashiju.manage.service;
/**
 * 
 *   首付房屋类型接口
 * @ClassName:  IHouseDeedTaxService   
 * @Description:   首付房屋类型接口
 * @author: wangkaifa
 * @date:   2018年12月28日 上午10:55:19       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.Map;

import com.github.pagehelper.Page;

public interface IHouseTransactionTaxService {
	/**
	 * 
		 * 查询首付房屋类型列表
		 * @Description: 查询首付房屋类型列表
		 * @param cityCode
		 * @return 
		 * Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryHousePropertyListByCity(String cityCode,int page,int limit);
	/**
	 * 
		 * 根据访问类型id查询首付契税列表
		 * @Description: 根据访问类型id查询首付契税列表
		 * @param housePropertyId
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryDeedTaxs(Integer housePropertyId);
	/**
	 * 
		 * 新增首付房屋类型
		 * @Description: 新增首付房屋类型 
		 * @param houseProperty
		 * @return 
		 * Integer
	 */
	Integer addHouseProperty(Map<String, Object> houseProperty);
	/**
	 * 
		 * 修改首付房屋类型
		 * @Description: 修改首付房屋类型
		 * @param houseProperty
		 * @return 
		 * Integer
	 */
	Integer updateHouseProperty(Map<String, Object> houseProperty);
	/**
	 * 
		 * 删除首付房屋类型
		 * @Description: 删除首付房屋类型 
		 * @param id
		 * @return 
		 * boolean
	 */
	boolean deleteHouseProperty(Integer id);
}
