/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealService.java   
 * @Package com.bashiju.deal.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月15日 下午4:17:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.deal.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  DealService   
 * @Description:成交管理服务接口   
 * @author: yangz
 * @date:   2018年6月15日 下午4:17:52   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DealService {

	/**
	 * 
	 * @Description: 根据成交id获取成交信息    
	 * @param dealId 成交id
	 * @param dealType 成交类型
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> getDeals(String dealId,String dealType);
	
	/**
	 * @Description: 条件查询成交信息   
	 * @param params 查询信息的条件
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeals(Map<String,Object> params);
	
	/**
	 * @Description: 条件查询成交信息 (分页)    
	 * @param params 查询信息的条件
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数 
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String,Object>> queryDeals(Map<String,Object> params,int pageNum,int pageSize);
	
	/**
	 * @Description: 更新成交信息   
	 * @param deal 待更新的成交信息
	 * @return: boolean
	 */
	public boolean updateDeal(Map<String,Object> deal);
	
	/**
	 * @Description: 根据成交编号查询中介类分成信息  
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDividenInfos(String dealId);
	
	/**
	 * @Description: 批量保存中介类分成信息   
	 * @param dividenInfos 待保存的分成信息列表
	 * @return: boolean
	 */
	public boolean batchSaveDividenInfos(List<Map<String,Object>> dividenInfos);

	/**     
	 * @Description: 成交违约  
	 * @param dealId 成交编号
	 * @return: boolean      
	 */ 
	public boolean dealBreach(String dealId);

	/**     
	 * @Description: 取消成交违约   
	 * @param dealId 成交编号
	 * @return: boolean      
	 */ 
	public boolean cancelDealBreach(String dealId);

	/**     
	 * @Description: 保存佣金记录信息   
	 * @param map 待保存的佣金信息
	 * @return: boolean      
	 */ 
	public boolean updateDealCommission(Map<String,Object> map);
	
	/**
	 * @Description: 批量新增佣金记录信息 
	 * @param list 待保存的佣金信息列表
	 * @return: boolean
	 */
	public boolean batchSaveDealCommissions(List<Map<String, Object>> list);

	
	 /**
	  * @Description: 启动过户    
	  * @param dealId 成交编号
	  * @param transferDetails 待保存的过户信息           
	  * @return: boolean
	  */
	public boolean saveTransferProcess(String dealId,List<Map<String, Object>> transferDetails);

	/**     
	 * @Description: 根据成交编号查询过户进度信息  
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryTransferSchedules(String dealId);
	
	/**
	 * @Description: 新增过户进度   
	 * @param map 待添加的过户进度
	 * @return: boolean
	 */
	public boolean saveTransferSchedule(Map<String,Object> map);

	/**     
	 * @Description: 调整过户进度   
	 * @param dealId 成交编号
	 * @param map 待调整的过户信息     
	 * @return: boolean      
	 */ 
	public boolean updateTransferSchedule(String dealId,Map<String,Object> map);
	
	/**     
	 * @Description: 根据主键删除过户进度   
	 * @param id 过户进度信息主键     
	 * @return: boolean      
	 */ 
	public boolean deleteTransferScheduleById(String id);
	
	/**     
	 * @Description: 取消过户进度   
	 * @param dealId 成交编号     
	 * @return: boolean      
	 */ 
	public boolean cancelTransferSchedule(String dealId);

	/**     
	 * @Description: 批量修改过户进度的预计完成时间   
	 * @param transferSchedules 待修改的过户信息
	 * @return: boolean      
	 */ 
	public boolean batchUpdateTransferScheduleTime(List<Map<String, Object>> transferSchedules);


	/**     
	 * @Description: 保存分成配置信息  
	 * @param dealId 成交编号
	 * @param list 待保存的分成配置信息
	 * @return: boolean      
	 */ 
	public boolean saveDivideInfos(String dealId, List<Map<String, Object>> list);

	/**     
	 * @Description: 收取佣金   
	 * @param dealId 成交编号
	 * @param map 待保存的佣金信息
	 * @return: boolean      
	 */ 
	public boolean reciverDealCommission(String dealId, Map<String,Object> map);
	
	/**     
	 * @Description: 保存佣金信息   
	 * @param dealId 成交编号
	 * @param list 待保存的佣金信息
	 * @return: boolean      
	 */ 
	public boolean saveDealCommission(String dealId, List<Map<String, Object>> list);

	/**     
	 * @Description: 取消佣金信息   
	 * @param dealId 成交编号
	 * @param id 佣金记录id
	 * @return: boolean      
	 */ 
	public boolean cancelCommission(String dealId,String id);

	/**     
	 * @Description: 保存附件信息  
	 * @param dealType 成交类型
	 * @param files 待保存的附件列表
	 * @return: boolean      
	 */ 
	public boolean saveAttachments(String dealType,List<Map<String, Object>> files);

	/**     
	 * @Description: 条件查询附件信息  
	 * @param dealId 成交编号
	 * @param attachType 附件类型
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryAttachData(String dealId, String attachType);

	/**
	 * @Description: 根据主键批量删除附件信息   
	 * @param id 待删除附件信息的主键
	 * @return: boolean
	 */
	public boolean deleteAttach(String...id);
	
	/**     
	 * @Description: 根据成交编号查询跟进信息(分页)
	 * @param dealId 成交编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: List<Map<String,Object>>      
	 */ 
	public Page<Map<String,Object>> queryFollowRecordByDealId(String dealId,int pageNum,int pageSize);

	/**     
	 * @Description: 根据成交编号查询跟进信息
	 * @param dealId
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String,Object>> queryFollowRecordByDealId(String dealId);

	/**     
	 * @Description: 根据id删除跟进信息   
	 * @param id 跟进记录id     
	 * @return: boolean      
	 */ 
	public boolean delFollowRecord(String id);

	/**     
	 * @Description: 保存跟进记录信息并且发起提醒信息   
	 * @param record 跟进记录
	 * @param remind 提醒信息
	 * @return: boolean      
	 */ 
	public boolean savefollowRecordAndRemindInfo(Map<String,Object> record, Map<String,Object> remind);

	/**     
	 * @Description: 根据成交编号查询代收付款记录   
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String,Object>> queryPayRecordsByDealId(String dealId);

	/**     
	 * @Description: 保存代收款记录   
	 * @param map 待保存的代收款信息
	 * @return: boolean      
	 */ 
	public boolean savePayRecordd(Map<String,Object> map);

	/**     
	 * 确认分成信息(该分成确认操作后，再次修改分成将无效) 
	 * @Description: 确认分成信息(该分成确认操作后，再次修改分成将无效)   
	 * @param dealId 成交编号
	 * @param dealType 成交类型
	 * @param moneyType 佣金类型：0--中介类费用，1--金融类费用
	 * @return: boolean      
	 */ 
	public boolean saveSureDividInfos(String dealId,String dealType,String moneyType);

	/**     
	 * @Description: 根据成交编号查询金融类分成信息 
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>      
	 */ 
	public List<Map<String, Object>> queryFinanceDividenInfos(String dealId);

	/**     
	 * @Description: 保存修改后的金融类分成配置信息   
	 * @param dealId 成交编号
	 * @param list 待保存的分成信息
	 * @return: boolean      
	 */ 
	public boolean saveFinanceDivideInfos(String dealId, List<Map<String,Object>> list);

	/**     
	 * @Description: 删除代收付款记录  
	 * @param id 待删除的代收付款记录编号
	 * @return: boolean      
	 */ 
	public boolean delPayRecordd(String id);

	/**
	 * 修改成交信息
	 * @Description: 修改成交信息
	 * @param deal 待修改的成交信息
	 * @return: boolean
	 */
	public boolean updateDealTransaction(Map<String,Object> deal);
	
	/**
	 * 删除佣金记录  
	 * @Description: 删除佣金记录  
	 * @param dealId 成交编号
	 * @param commissionId 佣金记录编号
	 * @param commissionType 佣金类型 0--中介类，1--金融类
	 * @return: boolean
	 */
	public boolean delCommissions(String dealId,String commissionId,int commissionType);
	
	/**
	 * 根据成交编号统计金融类佣金信息
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param dealId
	 * @return: Map<String, Object>
	 */
	public Map<String,Object> statisticFinanceCommissions(String dealId);
}
