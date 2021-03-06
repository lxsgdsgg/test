package com.bashiju.deal.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DealCommissionRecordService   
 * @Description:佣金记录查询接口
 * @author: wangpeng
 * @date:   2018年6月21日 下午3:16:12   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DealCommissionRecordService {
	/**
	 * 
	 * @Title: queryDealCommissionRecord   
	 * @Description: 条件佣金记录查询
	 * @param paramMap 查询参数
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return      
	 * @return: Page<Map<String,Object>>
	 */
	Page<Map<String,Object>> queryDealCommissionRecord (Map<String,Object> paramMap ,int page,int limit);
	
	
	/**
	 * @Description: 根据成交编号查询佣金记录信息   
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryCommissionByDealId(String dealId);
}
