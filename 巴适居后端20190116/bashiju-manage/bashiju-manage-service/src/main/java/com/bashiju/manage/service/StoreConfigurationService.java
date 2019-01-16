package com.bashiju.manage.service;


import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  StoreConfigurationService   
 * @Description:门店支出项配置接口
 * @author: wangpeng
 * @date:   2018年6月26日 下午12:08:23   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface StoreConfigurationService {
	/**
	 * @Title: queryFinanceCostType   
	 * @Description: 查询费用类型
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @param 城市编码
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryFinanceCostType(int page,int limit,String cityCode);
	
	
	
	
	/***
	 * 逻辑删除费用项目
	 * @Title: deleteFinanceCostType   
	 * @Description: 逻辑删除费用项目
	 * @param id 费用项目编号
	 * @return: boolean
	 */
	public boolean deleteFinanceCostType(String id);
	
	
	/**
	 * @Title: queryFinanceCostProj   
	 * @Description: 查询费用项目
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String, Object>> queryFinanceCostProjByFinanceCostTypeId(String financeCostTypeId,int page,int limit);

	/**
	 * @Title: saveOrUpdateFinanceCostType   
	 * @Description: 新增费用项目
	 * @param map
	 * @return: boolean
	 */
	 boolean saveOrUpdateFinanceCostType(Map<String,Object> map);
	 
	 /**
	  * @Title: saveOrUpdateFinanceCostProj   
	  * @Description: 新增费用项目
	  * @param map
	  * @return: boolean
	  */
	 boolean saveOrUpdateFinanceCostProj(Map<String,Object> map);
	 
	 /**
	  * @Title: delFinanceCostProjById   
	  * @Description: 逻辑删除费用项目
	  * @param financeCostProjId
	  * @return: boolean
	  */
	 boolean delFinanceCostProjById(String financeCostProjId);
	 
	 /**
	  * @Title: queryFinanceCostTypeNameById   
	  * @Description: 条件查询费用类型名称  
	  * @param financeCostTypeId 费用类型Id
	  * @return: Map<String,Object>
	  */
	 Map<String, Object> queryFinanceCostTypeNameById(String financeCostTypeId);
	 
	 	/**
		 * 查询费用类型是否存在关联数据
		 * @Title: queryIsExistRelationData   
		 * @Description: 查询费用类型是否存在关联数据
		 * @param financeCostTypeId 费用类型编号
		 * @return: boolean
		 */
	 boolean queryIsExistRelationData(String financeCostTypeId);
}
