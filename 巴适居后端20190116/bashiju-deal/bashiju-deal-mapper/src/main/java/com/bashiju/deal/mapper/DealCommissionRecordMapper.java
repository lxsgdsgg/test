package com.bashiju.deal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  DealCommissionRecord   
 * @Description:佣金记录查询映射接口   
 * @author: wangpeng
 * @date:   2018年6月21日 下午3:05:17   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DealCommissionRecordMapper {
	/**
	 * 
	 * @Title: queryDealCommissionRecordData   
	 * @Description: 条件查询佣金记录
	 * @param paramMap 查询参数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryDealCommissionRecordData (Map<String,Object> paramMap);

	/**     
	 * @Description: 根据成交编号查询佣金信息  
	 * @param dealId 成交编号     
	 * @return: List<Map<String, Object>>      
	 */ 
	List<Map<String, Object>> queryCommissionByDealId(@Param("dealId")String dealId);
}
