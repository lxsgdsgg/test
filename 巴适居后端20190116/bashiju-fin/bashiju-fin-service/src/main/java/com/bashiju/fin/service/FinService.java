package com.bashiju.fin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  FinService   
 * @Description:TODO(财务平台接口)   
 * @author: liwen
 * @date:   2018年6月27日 上午11:18:04   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface FinService {
	public Page<Map<String, Object>> queryCommissionList(Map<String,Object> conditions,int page,int limit);

	/**
	 * 
		 * 查询代收付款列表
		 * @Description: 查询代收付款列表
		 * @param conditions 条件 {status:审核状态(0待1通过2驳回),timeType:时间类型(1录入2审核3付款),beginTime:开始时间,endTime:结算时间,
		 * moneyTypeId:费用类型,settlementTypeId计算方式,payerType:首付款方,keywordTyp:关键字类型(billNo票据dealId成交remark备注),keyword:关键字值}
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryPayRecord(Map<String, Object> conditions,int page,int limit);
	/**
	 * 
		 * 修改收付款审核状态
		 * @Description: 修改收付款审核状态 
		 * @param id 收付款id
		 * @param status 审核状态:1通过，2驳回
		 * @param reason 驳回原因
		 * @return 
		 * boolean
	 */
	public boolean updatePayStatus(Long id,String status,String reason);
	public Long updateInfo(Long id,String type,String param);

	public boolean saveFinanceDivide(Map<String,Object> data);
	
	public List<Map<String,Object>> queryFinanceDivide(Long commissionId);
	/**
	 * 
	 *    
	 * @Description:(查日志信息)   
	 * @param: @param conditions
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>> auxQueryLog(Map<String,Object> conditions, int page, int limit);

}
