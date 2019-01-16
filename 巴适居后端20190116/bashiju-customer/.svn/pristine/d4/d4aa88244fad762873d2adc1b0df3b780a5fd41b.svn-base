package com.bashiju.customer.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DemandShowedRecordMapper   
 * @Description:客源带看记录映射接口
 * @author: wangpeng
 * @date:   2018年6月20日 上午10:07:03   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DemandShowedRecordMapper {
	/**
	 * 查询客源带看记录
	 * @Title: queryDemandShowedRecordData   
	 * @Description: 查询客源带看记录  
	 * @param paramMap
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryDemandShowedRecordData(Map<String,Object> paramMap);
	/**
	 * 条件查询客源带看  
	 * @Title: queryDemandShowedRecordById   
	 * @Description: 条件查询客源带看  
	 * @param id 客源带看记录id
	 * @return: Map<String,Object>
	 */
	Map<String,Object> queryDemandShowedRecordById(@Param("id")String id);
	
	/**
	 * 条件查询新房信息
	 * @Title: queryNewHouseInfoById   
	 * @Description: 条件查询新房信息
	 * @param newHouseId 新房编号
	 * @return: Map<String,Object>
	 */
	 Map<String,Object> queryNewHouseInfoById(@Param("newHouseId")String newHouseId);
		
}
