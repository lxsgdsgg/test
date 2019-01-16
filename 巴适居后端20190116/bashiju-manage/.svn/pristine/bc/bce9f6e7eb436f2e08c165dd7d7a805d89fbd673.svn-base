package com.bashiju.manage.mapper;
/**
 * 
 * @ClassName:  StoreConfiguration   
 * @Description:门店支出配置映射接口 
 * @author: wangpeng
 * @date:   2018年6月26日 上午11:56:34   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

public interface StoreConfigurationMapper {
	/**
	 * 
	 * @Title: queryFinanceCostType   
	 * @Description:条件查询费用类型   
	 * @param  cityCode 城市编号
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryFinanceCostType(@Param("cityCode")String cityCode);
	
	/**
	 * 
	 * @Title: queryFinanceCostProjByFinanceCostTypeId   
	 * @Description: 条件查询费用项目   
	 * @param FinanceCostTypeId 费用类型id
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryFinanceCostProjByFinanceCostTypeId(@Param("financeCostTypeId")String financeCostTypeId);
	/**
	 * @Title: queryFinanceCostTypeNameById   
	 * @Description: 条件查询费用类型名称 
	 * @param financeCostTypeId 费用类型id
	 * @return: Map<String,Object>
	 */
	Map<String, Object> queryFinanceCostTypeNameById(@Param("financeCostTypeId")String financeCostTypeId);

	/**
	 * 查询费用类型是否存在关联数据
	 * @Title: queryIsExistRelationData   
	 * @Description: 查询费用类型是否存在关联数据
	 * @param financeCostTypeId 费用类型编号
	 * @return: long
	 */
    long queryIsExistRelationData(@Param("financeCostTypeId")String financeCostTypeId);
}
