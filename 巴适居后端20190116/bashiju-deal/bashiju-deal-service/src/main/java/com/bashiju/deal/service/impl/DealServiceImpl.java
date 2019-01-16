/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealServiceImpl.java   
 * @Package com.bashiju.deal.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月16日 上午11:06:37   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.deal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.bashiju.api.SendSMSServiceApi;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.pojo.TransferScheduleStatusEnum;
import com.bashiju.deal.mapper.DealMapper;
import com.bashiju.deal.service.DealService;
import com.bashiju.enums.BusinessTypeEnum;
import com.bashiju.enums.CommissionStatusEnum;
import com.bashiju.enums.DealTransferStatusEnum;
import com.bashiju.enums.DealTypeEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.FtpSourceTypeEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  DealServiceImpl   
 * @Description:成交管理服务   
 * @author: yangz
 * @date:   2018年6月16日 上午11:06:37   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="成交管理")
@Service
public class DealServiceImpl extends CommonSqlServie implements DealService {
	
	@Autowired
	private DealMapper dealMapper;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Autowired
	private BusinessLogService BusinessLogService;
	@Autowired
	private SendSMSServiceApi sendMSService;

	/**
	 * @Description: 根据成交id获取成交信息  
	 * @param dealId 成交id
	 * @param dealType 成交类型
	 * @return   
	 * @see com.bashiju.deal.service.DealService#getDeals(java.lang.String)   
	 */
	@Override
	public Map<String, Object> getDeals(String dealId,String dealType) {
		if(StringUtils.isEmpty(dealType))
			return null;
		if(!DealTypeEnum.enumMap.containsKey(dealType))
			throw new BusinessException("成交类型无效");
		if(StringUtils.isEmpty(dealId))
			return null;
		if(DealTypeEnum.BUSINESSDEAL.getCode().equals(dealType))//买卖成交
			dataAuthHelper.auth(MenuEnum.MENU_105.getCode(), UserThreadLocal.get().get("id").toString());
		else if(DealTypeEnum.LEASEDEAL.getCode().equals(dealType))//租赁成交
			dataAuthHelper.auth(MenuEnum.MENU_106.getCode(), UserThreadLocal.get().get("id").toString());
		else
			dataAuthHelper.auth(MenuEnum.MENU_107.getCode(), UserThreadLocal.get().get("id").toString());
		Map<String,Object> map = this.dealMapper.getDeals(dealId);
		map.putAll(this.dealMapper.queryDealCommissionStatistics(dealId));
		return map;
	}

	/**
	 * @Description: 条件查询成交信息   
	 * @param params 查询信息的条件
	 * @return   
	 * @see com.bashiju.deal.service.DealService#queryDeals(java.util.Map)   
	 */
	@Override
	public List<Map<String, Object>> queryDeals(Map<String, Object> params) {
		if(!DealTypeEnum.enumMap.containsKey(params.get("dealType")))
			throw new BusinessException("成交类型无效");
		if(DealTypeEnum.BUSINESSDEAL.getCode().equals(params.get("dealType")))//买卖成交
			dataAuthHelper.auth(MenuEnum.MENU_105.getCode(), UserThreadLocal.get().get("id").toString());
		else if(DealTypeEnum.LEASEDEAL.getCode().equals(params.get("dealType")))//租赁成交
			dataAuthHelper.auth(MenuEnum.MENU_106.getCode(), UserThreadLocal.get().get("id").toString());
		else
			dataAuthHelper.auth(MenuEnum.MENU_107.getCode(), UserThreadLocal.get().get("id").toString());
		return this.dealMapper.queryDeals(params);
	}

	/**
	 * @Description: 条件查询成交信息 (分页)    
	 * @param params 查询信息的条件
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数 
	 * @return   
	 * @see com.bashiju.deal.service.DealService#queryDeals(java.util.Map, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryDeals(Map<String, Object> params, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> pages = (Page<Map<String, Object>>) this.queryDeals(params);
		return pages;
	}

	/**
	 * @Description: 更新成交信息   
	 * @param deal 待更新的成交信息
	 * @return   
	 * @see com.bashiju.deal.service.DealService#updateDeal(java.util.Map)   
	 */
	@Override
	public boolean updateDeal(Map<String, Object> deal) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @Description: 根据成交编号查询中介类分成信息  
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.deal.service.DealService#queryDividenInfos(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryDividenInfos(String dealId) {
		if(StringUtils.isEmpty(dealId))
			return null;
		else
			return this.dealMapper.queryDividenInfos(dealId);
	}

	/**
	 * @Description: 批量保存中介类分成信息   
	 * @param dividenInfos 待保存的分成信息列表
	 * @return: boolean  
	 * @see com.bashiju.deal.service.DealService#batchSaveDividenInfos(java.util.List)   
	 */
	@Override
	public boolean batchSaveDividenInfos(List<Map<String, Object>> dividenInfos) {
		if(dividenInfos==null || dividenInfos.size()<=0)
			return false;
		else {
			return this.batchCommonOperationDatabase(dividenInfos, "deal_dividenInfo", "id", false);
		}
	}

	/**     
	 * @Description: 成交违约  
	 * @param dealId 成交编号
	 * @return: boolean 
	 * @see com.bashiju.deal.service.DealService#dealBreach(java.lang.String)   
	 */
	@Override
	public boolean dealBreach(String dealId) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		Map<String,Object> deal = this.dealMapper.getDeals(dealId);
		if(deal==null || deal.size()<=0) {
			throw new BusinessException("未找到有效的成交信息");
		}
		if("1".equals(deal.get("isBreach"))) {
			throw new BusinessException("该成交信息已经违约，不允许再次操作");
		}
		Map<String,Object> newDeal = new HashMap<String,Object>();
		newDeal.put("id", dealId);
		newDeal.put("isBreach", 1);//违约
		this.commonOperationDatabase(newDeal, "deal_transactionInfo", "id", false);
		//TODO:这里将来可能还要修改相应的合同违约状态，或者增加违约金等其他操作，待定！
		return true;
	}

	/**     
	 * @Description: 取消成交违约   
	 * @param dealId 成交编号
	 * @return: boolean       
	 * @see com.bashiju.deal.service.DealService#cancelDealBreach(java.lang.String)   
	 */
	@Override
	public boolean cancelDealBreach(String dealId) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		Map<String,Object> deal = this.dealMapper.getDeals(dealId);
		if(deal==null || deal.size()<=0) {
			throw new BusinessException("未找到有效的成交信息");
		}
		if("0".equals(deal.get("isBreach"))) {
			throw new BusinessException("该成交信息已经取消违约，不允许再次操作");
		}
		Map<String,Object> newDeal = new HashMap<String,Object>();
		newDeal.put("id", dealId);
		newDeal.put("isBreach", 0);//取消违约
		this.commonOperationDatabase(newDeal, "deal_transactionInfo", "id", false);
		//TODO:这里将来可能还要修改相应的合同违约状态，或者增加违约金等其他操作，待定！
		return true;
	}

	/**     
	 * @Description: 保存佣金记录信息   
	 * @param map 待保存的佣金信息
	 * @return: boolean 
	 * @see com.bashiju.deal.service.DealService#saveDealCommission(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存佣金记录")
	public boolean updateDealCommission(Map<String,Object> map) {
		if(map==null || map.size()<=0)
			throw new BusinessException("没有要保存的佣金记录");
		map.put("price", Integer.parseInt(map.get("price").toString())*100);
		map.put("actualPrice", Integer.parseInt(map.get("actualPrice").toString())*100);
		this.commonOperationDatabase(map, "deal_commissionRecord", "id", false);
		ExecutionResult.descFormat(map.get("id").toString(), "保存佣金记录");
		return true;
	}

	/**
	 * @Description: 批量新增佣金记录信息 
	 * @param list 待保存的佣金信息列表
	 * @return: boolean
	 * @see com.bashiju.deal.service.DealService#batchSaveDealCommissions(java.util.List)   
	 */
	@Override
	@SystemServiceLog(operationType="批量新增佣金记录")
	public boolean batchSaveDealCommissions(List<Map<String, Object>> list) {
		if(list==null || list.size()<=0)
			throw new BusinessException("没有要保存的佣金记录");
		this.batchCommonOperationDatabase(list, "deal_commissionRecord", false);
		ExecutionResult.descFormat("newId", "批量新增佣金记录");
		return false;
	}

	/**
	  * @Description: 启动过户    
	  * @param dealId 成交编号
	  * @param transferDetails 待保存的过户信息 
	 * @see com.bashiju.deal.service.DealService#saveTransferProcess(java.lang.String, java.util.List)   
	 */
	@Override
	@SystemServiceLog(operationType="启动过户")
	public boolean saveTransferProcess(String dealId, List<Map<String, Object>> transferDetails) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(transferDetails ==null || transferDetails.size()<=0)
			throw new BusinessException("没有要保存的过户信息");
		boolean result = this.batchCommonOperationDatabase(transferDetails, "deal_transferSchedule", false);
		if(result) {
			Map<String,Object> deal = new HashMap<String,Object>(0);
			deal.put("id", dealId);
			deal.put("transferStatus", DealTransferStatusEnum.TRANSFERING.getCode());
			long id  = this.commonOperationDatabase(deal, "deal_transactionInfo", "id", false);
			if(id>0)
				result = true;
			else
				result = false;
		}
		if(result)
			ExecutionResult.descFormat(dealId, "启动过户");
		return result;
		
	}

	/**     
	 * @Description: 根据成交编号查询过户进度信息  
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.deal.service.DealService#queryTransferSchedules(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryTransferSchedules(String dealId) {
		if(StringUtils.isEmpty(dealId))
			return null;
		return this.dealMapper.queryTransferSchedules(dealId);
	}
	
	/**
	 * @Description: 新增过户进度   
	 * @param map 待添加的过户进度
	 * @return: boolean
	 * @see com.bashiju.deal.service.DealService#saveTransferSchedule(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="新增过户进度")
	public boolean saveTransferSchedule(Map<String,Object> map) {
		if(map==null || map.size()<=0)
			throw new BusinessException("没有要新增的过户信息");
		if(!map.containsKey("dealId") || StringUtils.isEmpty(map.get("dealId").toString()))
			throw new BusinessException("成交编号不允许为空");
		if(!map.containsKey("status") || StringUtils.isEmpty(map.get("status").toString()))
			throw new BusinessException("状态不允许为空");
		Long id = this.commonOperationDatabase(map, "deal_transferSchedule", false);
		ExecutionResult.descFormat(Long.toString(id), "新增过户进度");
		return true;
	}

	/**     
	 * @Description: 调整过户进度   
	 * @Param dealId 成交编号
	 * @param map 待调整的过户信息     
	 * @return: boolean    
	 * @see com.bashiju.deal.service.DealService#updateTransferSchedule(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="调整过户进度")
	public boolean updateTransferSchedule(String dealId,Map<String,Object> map) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(map==null || map.size()<=0)
			throw new BusinessException("没有要调整的过户信息");
		if(!map.containsKey("id") || StringUtils.isEmpty(map.get("id").toString()))
			throw new BusinessException("主键不允许为空");
		if(!map.containsKey("status") || StringUtils.isEmpty(map.get("status").toString()))
			throw new BusinessException("状态不允许为空");
		if(TransferScheduleStatusEnum.FINISHED.getCode().equals(map.get("status").toString()) && 
				(!map.containsKey("actualFinishTime") || map.get("actualFinishTime")==null || "".equals(map.get("actualFinishTime")) )) {
			throw new BusinessException("完成时间不允许为空");
		}
		
		//流程节点每完成一个，就发信息给买卖双方
		if(TransferScheduleStatusEnum.FINISHED.getCode().equals(map.get("status").toString())) {
			//发短信给甲方
			Map<String,Object> msgInfo = dealMapper.getSchedulesMsgInfo(map.get("id").toString());
			Map<String,Object> msg_A=new HashMap<>();
			msg_A.put("phone", msgInfo.get("mobileA").toString());
			msg_A.put("name", msgInfo.get("partyA").toString());
			msg_A.put("houseId",msgInfo.get("houseId").toString());
			msg_A.put("date", map.get("actualFinishTime").toString());
			msg_A.put("processNode", msgInfo.get("scheduleName").toString());
			msg_A.put("smsType", "3");  
			sendMSService.execute(msg_A);
			//发短信给乙方
			Map<String,Object> msg_B=new HashMap<>();
			msg_B.put("phone", msgInfo.get("mobileB").toString());
			msg_B.put("name", msgInfo.get("partyB").toString());
			msg_B.put("houseId",msgInfo.get("houseId").toString());
			msg_B.put("date", map.get("actualFinishTime").toString());
			msg_B.put("processNode", msgInfo.get("scheduleName").toString());
			msg_B.put("smsType", "3");  
			sendMSService.execute(msg_B);
		}
		long rt = this.commonOperationDatabase(map, "deal_transferSchedule", "id", false);
		//当过户进度全部完成时，要更新成交中的过户状态
		if(rt>0 && TransferScheduleStatusEnum.FINISHED.getCode().equals(map.get("status"))) {
			this.dealMapper.updateTransactionStatus(dealId);
		}
		ExecutionResult.descFormat(map.get("id").toString(), "调整过户进度");
		return true;
	}

	/**     
	 * @Description: 根据主键删除过户进度
	 * @param id 过户进度信息主键     
	 * @return: boolean      
	 * @see com.bashiju.deal.service.DealService#deleteTransferSchedule(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除过户进度")
	public boolean deleteTransferScheduleById(String id) {
		if(StringUtils.isEmpty(id))
			return false;
		boolean result = this.delData("deal_transferSchedule", "id", id, false);
		if(!result)
			throw new BusinessException("删除过户进度操作失败");
		ExecutionResult.descFormat(id, "删除过户进度");
		return result;
	}


	/**     
	 * @Description: 取消过户进度   
	 * @param dealId 成交编号     
	 * @return: boolean     
	 * @see com.bashiju.deal.service.DealService#cancelTransferSchedule(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="取消过户进度")
	public boolean cancelTransferSchedule(String dealId) {
		boolean result = false;
		if(StringUtils.isEmpty(dealId))
			return false;
		Map<String,Object> deal = new HashMap<String,Object>(0);
		deal.put("id", dealId);
		deal.put("transferStatus", DealTransferStatusEnum.NOTSTARTING.getCode());
		//将成交信息的过户状态置为未启动
		long id  = this.commonOperationDatabase(deal, "deal_transactionInfo", "id", false);
		result = id>0?true:false;
		if(result) {
			Map<String,Object> transferSchedule = new HashMap<String,Object>(0);
			transferSchedule.put("isValid", "0");
			//将原有的过户信息全部置为失效
			result = this.batchcommonUpdate(transferSchedule, "deal_transferSchedule", "dealId", "'"+dealId+"'", null);
		}
		if(!result)
			throw new BusinessException("取消过户进度操作失败");
		ExecutionResult.descFormat(dealId, "取消过户进度");
		return result;
	}

	/**     
	 * @Description: 批量修改过户进度的预计完成时间   
	 * @param transferSchedules 待修改的过户信息
	 * @return: boolean     
	 * @see com.bashiju.deal.service.DealService#batchUpdateTransferScheduleTime(java.util.List)   
	 */
	@Override
	@SystemServiceLog(operationType="批量修改过户进度")
	public boolean batchUpdateTransferScheduleTime(List<Map<String, Object>> transferSchedules) {
		if(transferSchedules==null || transferSchedules.size()<=0)
			throw new BusinessException("没有要修改的过户信息");
		boolean result = this.batchCommonOperationDatabase(transferSchedules, "deal_transferSchedule", "id", false);
		if(!result)
			throw new BusinessException("批量修改过户进度的预计完成时间操作失败");
		ExecutionResult.descFormat("udpateId", "批量修改过户进度");
		return result;
	}

	/**     
	 * @Description: 保存分成配置信息    
	 * @param dealId 成交编号
	 * @param list 待保存的分成配置信息
	 * @return: boolean    
	 * @see com.bashiju.deal.service.DealService#saveDivideInfos(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存分成配置")
	public boolean saveDivideInfos(String dealId, List<Map<String, Object>> list) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(list==null || list.size()<=0)
			throw new BusinessException("没有要调整的过户信息");
		List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>(0);
		List<Map<String,Object>> addList = new ArrayList<Map<String,Object>>(0);
		//统计该成交信息中应收中介类佣金，以及实收中介类佣金，以便按照分成比例分配给中介人
		Map<String,Object> statistics = this.dealMapper.getAgentCommissionStatistics(dealId);
		
		long price = Long.parseLong(statistics.get("price").toString());
		long actualPrice = Long.parseLong(statistics.get("actualPrice").toString());
		
		int rate = 0;
		for(Map<String,Object> map : list) {
			if(StringUtils.isEmpty(map.get("dividRate").toString())) {
				throw new BusinessException("分成比例不能为空");
			}else {
				int rt = Integer.parseInt(map.get("dividRate").toString());
				long estimateProfit = rt*price/100;
				long actualProfit = rt*actualPrice/100;
				//计算分成金额
				map.put("estimateProfit", estimateProfit);
				map.put("actualProfit", actualProfit);
				rate += rt;
			}
				
			if(map.containsKey("id") && map.get("id")!=null && !"".equals(map.get("id").toString()))
				updateList.add(map);
			else {
				map.put("dealId", dealId);
				map.put("permissionArea", UserThreadLocal.get().get("deptId"));
				map.put("operatorId", UserThreadLocal.get().get("id"));
				addList.add(map);
			}
		}
		if(rate!=100)
			throw new BusinessException("分成比例不为100%");
		//修改配置
		if(updateList.size()>0)
			this.batchCommonOperationDatabase(updateList, "deal_dividenInfo", "id", false);
		//新增配置
		if(addList.size()>0)
			this.batchCommonOperationDatabase(addList, "deal_dividenInfo",false);
		ExecutionResult.descFormat("udpateId", "保存分成配置");
		return true;
	}
	

	/**     
	 * @Description: 收取佣金   
	 * @param dealId 成交编号
	 * @param map 待收取的佣金信息
	 * @return: boolean 
	 * @see com.bashiju.deal.service.DealService#reciverDealCommission(java.lang.String, java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="收取佣金")
	public boolean reciverDealCommission(String dealId, Map<String,Object> map) {
		boolean result = false;
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(map == null || map.size()<=0)
			throw new BusinessException("没有待收取的佣金信息");
		if(map.get("price")==null || StringUtils.isEmpty(map.get("price").toString()))
			throw new BusinessException("费用金额不允许为空");
		if(map.get("actualPrice")==null || StringUtils.isEmpty(map.get("actualPrice").toString()))
			throw new BusinessException("实际收取金额不允许为空");
		long price = Long.parseLong(map.get("price").toString())*100;
		long actualPrice = Long.parseLong(map.get("actualPrice").toString())*100;
		map.put("price", price);
		map.put("actualPrice", actualPrice);
		if(price<actualPrice)
			throw new BusinessException("实际收取金额不能大于费用金额");
		else if(price>actualPrice) {
			//应收佣金大于实收佣金的时候，认为还有待收取的佣金，将产生一条新的佣金记录
			Map<String,Object> commission = this.dealMapper.queryCommissionById(map.get("id").toString());
			commission.remove("id");
			commission.put("status",CommissionStatusEnum.PLAN.getCode());
			commission.put("price", (price-actualPrice));
			map.put("remark",null);
			this.commonOperationDatabase(commission, "deal_commissionRecord",false);
			//将原佣金记录中的应收金额改为实收的佣金
			map.put("price", actualPrice);
		}
		map.put("status", CommissionStatusEnum.UNCONFIRMED.getCode());//佣金状态为待确认
		//1.修改佣金信息
		this.commonOperationDatabase(map, "deal_commissionRecord", "id", false);
		
		//3.修改分成金额
		if("0".equals(map.get("moneyType")))//中介类
			result = updateInfoByCommission(dealId);
		else if("1".equals(map.get("moneyType")))
			result = updateInfoByFinanceCommission(dealId);
		if(!result)
			throw new BusinessException("收取佣金失败");
		ExecutionResult.descFormat(dealId, "收取佣金");
		
		return result;
	}


	/**     
	 * @Description: 保存佣金信息   
	 * @param dealId 成交编号
	 * @param list 待保存的佣金信息
	 * @see com.bashiju.deal.service.DealService#saveDealCommission(java.util.List)
	 */
	@Override
	@SystemServiceLog(operationType="保存佣金")
	public boolean saveDealCommission(String dealId,List<Map<String, Object>> list) {
		boolean result = false;
		if(list==null || list.size()<=0)
			throw new BusinessException("没有要保存的佣金信息");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>(0);
		List<Map<String,Object>> addList = new ArrayList<Map<String,Object>>(0);
		for(Map<String,Object> map : list) {
			map.put("price", Long.parseLong(map.get("price").toString())*100);
			map.put("permissionArea", UserThreadLocal.get().get("deptId"));
			map.put("operatorId", UserThreadLocal.get().get("id"));
			if(map.containsKey("id") && map.get("id")!=null && !"".equals(map.get("id").toString()))
				updateList.add(map);
			else {
				map.put("dealId", dealId);
				map.put("status",CommissionStatusEnum.PLAN.getCode());
				addList.add(map);
			}
		}
		//1.批量修改佣金信息
		//修改配置
		if(updateList.size()>0)
			result = this.batchCommonOperationDatabase(updateList, "deal_commissionRecord", "id", false);
		//新增配置
		if(addList.size()>0)
			result = this.batchCommonOperationDatabase(addList, "deal_commissionRecord",false);
		
		//2.修改成交信息中中介类实应收佣金金额
		//3.修改分成金额
		
		result = this.updateInfoByCommission(dealId);
		result = this.updateInfoByFinanceCommission(dealId);
		result = updateInfoByCommission(dealId);
		if(!result)
			throw new BusinessException("保存佣金信息失败");
		ExecutionResult.descFormat(dealId, "保存佣金信息");
		return result;
	}

	/**     
	 * @Description: 取消佣金信息   
	 * @param dealId 成交编号
	 * @param id 佣金记录id
	 * @return: boolean    
	 * @see com.bashiju.deal.service.DealService#cancelCommission(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="取消佣金")
	public boolean cancelCommission(String dealId,String id) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("主键不允许为空");
		//1.将佣金状态置为计划,实收金额置为0
		Map<String,Object> commission = new HashMap<String,Object>();
		commission.put("id", id);
		commission.put("actualPrice", 0);
		commission.put("status", CommissionStatusEnum.PLAN.getCode());
		this.commonOperationDatabase(commission, "deal_commissionRecord", "id", false);
		
		//2.修改成交信息中中介类实应收佣金金额
		//3.修改分成金额
		boolean result = updateInfoByCommission(dealId);
		if(!result)
			throw new BusinessException("取消佣金信息失败");
		ExecutionResult.descFormat(dealId, "取消佣金信息");
		return result;
	}
	
	/**
	 * @Description: 当中介佣金发生变化时，对应的成交信息中介类实应收金额，分成金额将发生变化   
	 * @param dealId 成交编号
	 * @return: boolean
	 */
	private boolean updateInfoByCommission(String dealId) {
		boolean result = true;
		//统计该成交信息中应收中介类佣金，以及实收中介类佣金，以便按照分成比例分配给中介人
		Map<String,Object> statistics = this.dealMapper.getAgentCommissionStatistics(dealId);
		//2.修改成交信息中中介类实应收佣金金额
		Map<String,Object> deal = new HashMap<String,Object>();
		deal.put("id", dealId);
		deal.put("actualCommission", statistics.get("price").toString());
		deal.put("receivedPrice", statistics.get("actualPrice").toString());
		this.commonOperationDatabase(deal, "deal_transactionInfo", "id", false);
		
		//3.修改分成金额
		List<Map<String,Object>> divides = this.queryDividenInfos(dealId);
		for(Map<String,Object> mp : divides) {
			long priceTotal = Long.parseLong(mp.get("dividRate").toString())*Long.parseLong(statistics.get("price").toString())/100;
			long actualPriceTotal = Long.parseLong(mp.get("dividRate").toString())*Long.parseLong(statistics.get("actualPrice").toString())/100;
			mp.put("estimateProfit", priceTotal);
			mp.put("actualProfit", actualPriceTotal);
			mp.remove("dividerName");
			mp.remove("dividerDeptId");
			mp.remove("dividerDeptName");
		}
		if(divides!=null && divides.size()>0)
			result = this.batchCommonOperationDatabase(divides, "deal_dividenInfo", "id", false);
		return result;
	}
	
	/**     
	 * @Description: 当金融类佣金发生变化时，对应的分成金额将发生变化   
	 * @param dealId 成交编号
	 * @return: boolean      
	 */ 
	private boolean updateInfoByFinanceCommission(String dealId) {
		boolean result = true;
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		//统计该成交信息中应收金融类佣金，以及实收金融类佣金，以便按照分成比例分配给中介人
		Map<String,Object> statistics = this.dealMapper.statisticFinanceCommissions(dealId);
		List<Map<String,Object>> divides = this.queryFinanceDividenInfos(dealId);
		for(Map<String,Object> mp : divides) {
			long priceTotal = Long.parseLong(mp.get("dividRate").toString())*Long.parseLong(statistics.get("price").toString())/100;
			long actualPriceTotal = Long.parseLong(mp.get("dividRate").toString())*Long.parseLong(statistics.get("actualPrice").toString())/100;
			mp.put("estimateProfit", priceTotal);
			mp.put("actualProfit", actualPriceTotal);
			mp.remove("dividerName");
			mp.remove("dividerDeptId");
			mp.remove("dividerDeptName");
		}
		if(divides!=null && divides.size()>0)
			result = this.batchCommonOperationDatabase(divides, "deal_financeDividenInfo", "id", false);
		return result;
	}

	/**     
	 * @Description: 保存附件信息  
	 * @param dealType 成交类型
	 * @param files 待保存的附件列表
	 * @return: boolean  
	 * @see com.bashiju.deal.service.DealService#saveAttachments(java.util.List)   
	 */
	@Override
	@SystemServiceLog(operationType="保存附件")
	public boolean saveAttachments(String dealType,List<Map<String, Object>> files) {
		if(files==null || files.size()<=0)
			throw new BusinessException("没有要保存的附件信息");
		boolean result = this.batchCommonOperationDatabase(files, "deal_attachment", false);
		ExecutionResult.descFormat("attachId", "保存附件");
		
		BusinessLogModel model = new BusinessLogModel();
		if(DealTypeEnum.BUSINESSDEAL.getCode().equals(dealType)) //买卖成交
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELL_DEAL.getCode()));
		else if(DealTypeEnum.LEASEDEAL.getCode().equals(dealType))//租赁成交
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.RENT_DEAL.getCode()));
		else if(DealTypeEnum.NEWHOUSEDEAL.getCode().equals(dealType))//一手成交
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.NEW_DEAL.getCode()));
		
		model.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.ADD_DEAL_PICTURE.getCode()));
		model.setSourceId(String.valueOf(files.get(0).get("dealId")));
		model.setSourceCode(String.valueOf(files.get(0).get("dealId")));
		List<String> paths = new ArrayList<String>(0);
		for(Map<String,Object> map : files) {
			paths.add(map.get("path").toString());
		}
		model.setPath(StringUtils.join(paths.toArray(),','));
		model.setLogContent("批量新增成交附件");
		BusinessLogService.dealLogAdd(model);
		return result;
	}

	/**   
	 * <p>Title: queryAttachData</p>   
	 * <p>Description: </p>   
	 * @param dealId
	 * @param attachType
	 * @return   
	 * @see com.bashiju.deal.service.DealService#queryAttachData(java.lang.String, java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryAttachData(String dealId, String attachType) {
		if(StringUtils.isEmpty(dealId))
			return null;
		return this.dealMapper.queryAttachData(null,dealId,attachType);
	}

	/**
	 * @Description: 根据主键批量删除附件信息   
	 * @param id 待删除附件信息的主键
	 * @return: boolean
	 * @see com.bashiju.deal.service.DealService#deleteAttach()   
	 */
	@Override
	@SystemServiceLog(operationType="批量删除附件")
	public boolean deleteAttach(String...id) {
		String fieldValue = arrToStr(id);
		for(String d : id) {
			List<Map<String,Object>> attach = this.dealMapper.queryAttachData(d,null, null);
			String path = attach.get(0).get("path").toString();
			String dealId = attach.get(0).get("dealId").toString();
			String fileName = path.split("/")[path.split("/").length-1];
			FtpUtil.deleteFile(dealId, FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(), FtpFileTypeEnum.FTP_FILE_TP.getCode(), fileName);
		}
		boolean result = this.delData("deal_attachment", "id", fieldValue, false);
		ExecutionResult.descFormat(fieldValue, "批量删除附件");
		return result;
	}
	
	private String arrToStr(String...str) {
		StringBuffer sb = new StringBuffer(0);
		for(int i=0;i<str.length;i++) {
			if(i!=0)
				sb.append(",");
			sb.append(str[i]);
		}
		return sb.toString();
	}
	
	/**     
	 * @Description: 根据成交编号查询跟进信息(分页)
	 * @param dealId 成交编号
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: List<Map<String,Object>>    
	 * @see com.bashiju.deal.service.DealService#queryFollowRecordByDealId(java.lang.String, int, int)   
	 */
	@Override
	public Page<Map<String, Object>> queryFollowRecordByDealId(String dealId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) queryFollowRecordByDealId(dealId);
		return page;
	}

	/**     
	 * @Description: 根据成交编号查询跟进信息
	 * @param dealId
	 * @return: List<Map<String,Object>> 
	 * @see com.bashiju.deal.service.DealService#queryFollowRecordByDealId(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryFollowRecordByDealId(String dealId) {
		if(StringUtils.isEmpty(dealId))
			return null;
		return this.dealMapper.queryFollowRecordByDealId(dealId);
	}

	/**     
	 * @Description: 根据id删除跟进信息   
	 * @param id 跟进记录id     
	 * @return: boolean  
	 * @see com.bashiju.deal.service.DealService#delFollowRecord(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除跟进")
	public boolean delFollowRecord(String id) {
		if(StringUtils.isEmpty(id))
			return false;
		boolean result = this.delData("deal_followRecord", "id", id, false);
		ExecutionResult.descFormat(id, "删除跟进");
		return result;
	}

	/**     
	 * @Description: 保存跟进记录信息并且发起提醒信息   
	 * @param record 跟进记录
	 * @param remind 提醒信息
	 * @return: boolean   
	 * @see com.bashiju.deal.service.DealService#savefollowRecordAndRemindInfo(java.util.Map, java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存跟进")
	public boolean savefollowRecordAndRemindInfo(Map<String,Object> record, Map<String,Object> remind) {
		if(record==null || record.size()<=0)
			throw new BusinessException("没有要保存的跟进记录信息");
		if(remind !=null && remind.size()>0) {
			if(!remind.containsKey("receivePeopleId") || remind.get("receivePeopleId")==null || StringUtils.isEmpty(remind.get("receivePeopleId").toString())) {
				throw new BusinessException("提醒人不允许为空");
			}
			if(!remind.containsKey("remindTime") || remind.get("remindTime")==null || StringUtils.isEmpty(remind.get("remindTime").toString())) {
				throw new BusinessException("提醒时间不允许为空");
			}
			this.commonOperationDatabase(remind, "sys_remind", false);
		}
		
		long recordId = this.commonOperationDatabase(record, "deal_followRecord", false);
		ExecutionResult.descFormat(Long.toString(recordId), "保存跟进");
		
		return true;
	}

	/**     
	 * @Description: 根据成交编号查询代收付款记录   
	 * @param dealId 成交编号
	 * @return: List<Map<String,Object>>
	 * @see com.bashiju.deal.service.DealService#queryPayRecordsByDealId(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryPayRecordsByDealId(String dealId) {
		if(StringUtils.isEmpty(dealId))
			return null;
		return this.dealMapper.queryPayRecordsByDealId(dealId);
	}

	/**     
	 * @Description: 保存代收款记录   
	 * @param map 待保存的代收款信息
	 * @return: boolean 
	 * @see com.bashiju.deal.service.DealService#savePayRecordd(java.util.Map)   
	 */
	@Override
	@SystemServiceLog(operationType="保存代收款")
	public boolean savePayRecordd(Map<String,Object> map) {
		if(map== null || map.size()<=0)
			return false;
		if(!map.containsKey("transactionType") || map.get("transactionType")==null || 
				StringUtils.isEmpty(map.get("transactionType").toString()))
			throw new BusinessException("代收付款类型不允许为空");
		if(!map.containsKey("moneyTypeId") || map.get("moneyTypeId")==null || 
				StringUtils.isEmpty(map.get("moneyTypeId").toString()))
			throw new BusinessException("费用类型不允许为空");
		if(!map.containsKey("price") || map.get("price")==null || 
				StringUtils.isEmpty(map.get("price").toString()))
			throw new BusinessException("金额不允许为空");
		if(!map.containsKey("payerType") || map.get("payerType")==null || 
				StringUtils.isEmpty(map.get("payerType").toString()))
			throw new BusinessException("支付方不允许为空");
		if(!map.containsKey("tradeTime") || map.get("tradeTime")==null || 
				StringUtils.isEmpty(map.get("tradeTime").toString()))
			throw new BusinessException("支付时间不允许为空");
		if(!map.containsKey("settlementTypeId") || map.get("settlementTypeId")==null || 
				StringUtils.isEmpty(map.get("settlementTypeId").toString()))
			throw new BusinessException("结算方式不允许为空");
		long id=0;
		//修改
		if(map.containsKey("id") && map.get("id")!=null && !"".equals(map.get("id").toString())) {
			id = this.commonOperationDatabase(map, "deal_payRecord", "id", false);
		}else {//新增
			id = this.commonOperationDatabase(map, "deal_payRecord", false);
		}
		ExecutionResult.descFormat(Long.toString(id), "保存跟进");
		return true;
	}

	/**     
	 * 确认分成信息(该分成确认操作后，再次修改分成将无效) 
	 * @Description: 确认分成信息(该分成确认操作后，再次修改分成将无效)   
	 * @param dealId 成交编号
	 * @param dealType 成交类型
	 * @param moneyType 佣金类型：0--中介类费用，1--金融类费用
	 * @return: boolean
	 * @see com.bashiju.deal.service.DealService#saveSureDividInfos(java.lang.String)   
	 */
	@SuppressWarnings("unchecked")
	@Override
	@SystemServiceLog(operationType="确认分成")
	public boolean saveSureDividInfos(String dealId,String dealType,String moneyType) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(moneyType) || (!"1".equals(moneyType) && !"0".equals(moneyType))){
			throw new BusinessException("佣金类型为空或者佣金类型无效");
		}
		Map<String,Object> deal = this.getDeals(dealId, dealType);
		if(deal!=null && deal.size()>0) {
			Map<String,Object> map = new HashMap<String,Object>(0);
			map.put("id", deal.get("id"));
			map.put("isDivide", 1);
			//将是否分成置为已确认分成
			this.commonOperationDatabase(map, "deal_transactioninfo", "id", false);
			
			String businessType = dealType2BusinessType(dealType,moneyType);
			Map<String,Object> user = UserThreadLocal.get();
			//将分成信息添加到员工佣金分成确认记录
			List<Map<String,Object>> list = null;
			if("1".equals(moneyType))//金融类佣金
				list = dealMapper.queryFinanceDividenAndConfigInfos(dealId, businessType);
			else//中介类佣金
				list = dealMapper.queryAgentDividenAndConfigInfos(dealId, businessType);
			System.out.println("------saveSureDividInfos-------参数为：[dealId:"+dealId+"; dealType:"+dealType+"; moneyType"+moneyType+"; list="+JSONArray.toJSONString(list));
			List<Map<String,Object>> sureInfos = new ArrayList<Map<String,Object>>(0);
			for(Map<String,Object> divid : list) {
				if(divid.get("comConfigId")==null || StringUtils.isEmpty(divid.get("comConfigId").toString()))
					throw new BusinessException("提成配置信息不允许为空");
				if(divid.get("universalRate")==null || StringUtils.isEmpty(divid.get("universalRate").toString()))
					throw new BusinessException("通提比例不允许为空");
				if(divid.get("commissionsRate")==null || StringUtils.isEmpty(divid.get("commissionsRate").toString()))
					throw new BusinessException("员工提成比例不允许为空");
				if(divid.get("calculateFormula")==null || StringUtils.isEmpty(divid.get("calculateFormula").toString()))
					throw new BusinessException("业务提成计算方式不允许为空");
				Map<String,Object> sureInfo = new HashMap<String,Object>(0);
				sureInfo.put("userId",divid.get("dividerId"));
				sureInfo.put("companyId",divid.get("companyId"));
				sureInfo.put("companyName",divid.get("companyId"));
				sureInfo.put("dealId",dealId);
				sureInfo.put("dealType",dealType);
				sureInfo.put("businessType",businessType);
				sureInfo.put("comConfigId",divid.get("comConfigId"));
				sureInfo.put("universalRate",divid.get("universalRate"));
				sureInfo.put("commissionsRate",divid.get("commissionsRate"));
				sureInfo.put("calculateFormula",divid.get("calculateFormula"));
				sureInfo.put("dividRate",divid.get("dividRate"));
				sureInfo.put("moneyType",moneyType);
				sureInfo.put("dealTime",divid.get("dealTime"));
				sureInfo.put("permissionArea",user.get("deptId"));
				sureInfo.put("operatorId",user.get("id"));
//				sureInfo.put("operator",user.get("realName"));
//				sureInfo.put("addTime",new Date());
//				sureInfo.put("updateTime",new Date());
//				sureInfo.put("isvalid",1);
				
				sureInfos.add(sureInfo);
			}
//			
			if(sureInfos.size()>0) {
				boolean result = this.batchCommonOperationDatabase(sureInfos, "sys_employeeDividenSure_record", false);
				ExecutionResult.descFormat(dealId, "确认分成");
				return result;
			}
				
		}else
			throw new BusinessException("未找到成交信息");
		return false;
	}
	
	/**
	 * 将成交类型转为对应的业务类型(金融类业务不包括)
	 * @Description: 将成交类型转为对应的业务类型(金融类业务不包括
	 * @param dealType 成交类型
	 * @param moneyType 佣金类型：1.金融类佣金，0.中介类佣金
	 * @return: String
	 */
	private String dealType2BusinessType(String dealType,String moneyType) {
		if("1".equals(moneyType))//金融类业务
			return BusinessTypeEnum.FINANCEDEAL.getCode();
		else if(DealTypeEnum.BUSINESSDEAL.getCode().equals(dealType)) {//买卖成交
			return BusinessTypeEnum.BUSINESSDEAL.getCode();
		}else if(DealTypeEnum.LEASEDEAL.getCode().equals(dealType)) {//租赁成交
			return BusinessTypeEnum.LEASEDEAL.getCode();
		}else if(DealTypeEnum.NEWHOUSEDEAL.getCode().equals(dealType)) {//一手成交
			return BusinessTypeEnum.NEWHOUSEDEAL.getCode();
		}else 
			throw new BusinessException("无效的成交类型");
		
	}

	/**   
	 * <p>Title: queryFinanceDividenInfos</p>   
	 * <p>Description: 根据成交编号查询金融类分成信息</p>   
	 * @param dealId 成交编号
	 * @return List<Map<String, Object>>  
	 * @see com.bashiju.deal.service.DealService#queryFinanceDividenInfos(java.lang.String)   
	 */
	@Override
	public List<Map<String, Object>> queryFinanceDividenInfos(String dealId) {
		if(StringUtils.isEmpty(dealId))
			return null;
		else
			return this.dealMapper.queryFinanceDividenInfos(dealId);
	}

	/**   
	 * <p>Title: saveFinanceDivideInfos</p>   
	 * <p>Description: 保存修改后的金融类分成配置信息</p>   
	 * @param dealId 成交编号
	 * @param list 待保存的分成信息
	 * @return boolean   
	 * @see com.bashiju.deal.service.DealService#saveFinanceDivideInfos(java.lang.String, java.util.List)   
	 */
	@Override
	public boolean saveFinanceDivideInfos(String dealId, List<Map<String,Object>> list) {
		if(list==null || list.size()<=0)
			return false;
		else {
			//统计该成交信息中应收金融类佣金，以及实收金融类佣金，以便按照分成比例分配给中介人
			Map<String,Object> statistics = this.dealMapper.statisticFinanceCommissions(dealId);
			long price = 0;
			long actualPrice = 0;
			if(statistics!=null && statistics.get("price")!=null)
				price = Long.parseLong(statistics.get("price").toString());
			if(statistics!=null && statistics.get("actualPrice")!=null)
				actualPrice = Long.parseLong(statistics.get("actualPrice").toString());
			for(Map<String,Object> map : list) {
				map.put("dealId", dealId);
				long priceTotal = Long.parseLong(map.get("dividRate").toString())*price/100;
				long actualPriceTotal = Long.parseLong(map.get("dividRate").toString())*actualPrice/100;
				map.put("estimateProfit", priceTotal);
				map.put("actualProfit", actualPriceTotal);
				
				if(map.containsKey("id") && !StringUtils.isEmpty(map.get("id").toString())) {
					if(map.containsKey("isValid") && Integer.parseInt(map.get("isValid").toString())==0)
						this.delData("deal_financeDividenInfo", "id", map.get("id").toString(), false);
					else
						this.commonOperationDatabase(map, "deal_financeDividenInfo","id", false);
				}else {
					Map<?,?> user = UserThreadLocal.get();
					map.remove("id");
					map.put("commissionId", 0);
					map.put("companyId", user.get("companyId"));
					map.put("companyName", user.get("companyName"));
					map.put("permissionArea", user.get("deptId"));
					map.put("operatorId", user.get("id"));
					this.commonOperationDatabase(map, "deal_financeDividenInfo", false);
				}
			}
			return true;
//			return this.batchCommonOperationDatabase(list, "deal_financeDividenInfo", "id", false);
		}
	}

	/**     
	 * @Description: 删除代收付款记录  
	 * @param id 待删除的代收付款记录编号
	 * @return: boolean      
	 * @see com.bashiju.deal.service.DealService#delPayRecordd(java.lang.String)   
	 */
	@Override
	@SystemServiceLog(operationType="删除代收付款")
	public boolean delPayRecordd(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的代收付款记录");
		Map<String,Object> pay = this.dealMapper.getPayRecord(id);
		if(pay!=null && ExamineStatusEnum.EXAMINED.getCode().equals(pay.get("examineStatus")))
			throw new BusinessException("已审核的代收付款记录不允许删除");
		boolean result = this.delData("deal_payRecord", "id", id, false);
		ExecutionResult.descFormat(id, "删除代收付款");
		return result;
	}

	/**
	 * 修改成交信息
	 * @Description: 修改成交信息
	 * @param deal 待修改的成交信息
	 * @return   
	 * @see com.bashiju.deal.service.DealService#updateDealTransaction(java.util.Map)   
	 */
	@Override
	public boolean updateDealTransaction(Map<String,Object> deal) {
		Map<String,Object> updateDeal = new HashMap<String,Object>(0);
		updateDeal.put("dealTime", deal.get("dealTime"));
		updateDeal.put("supplementInfo", deal.get("supplementInfo"));
		updateDeal.put("id", deal.get("id"));
		long result = this.commonOperationDatabase(updateDeal, "deal_transactionInfo", "id", false);
		if(result>0)
			return true;
		return false;
	}

	/**
	 * 删除佣金记录  
	 * @Description: 删除佣金记录  
	 * @param dealId 成交编号
	 * @param commissionId 佣金记录编号
	 * @param commissionType 佣金类型 0--中介类，1--金融类
	 * @return: boolean
	 * @see com.bashiju.deal.service.DealService#delCommissions(java.lang.String)   
	 */
	@Override
	public boolean delCommissions(String dealId,String commissionId,int commissionType) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(commissionType>1)
			throw new BusinessException("佣金类型不正确");
		if(!StringUtils.isEmpty(commissionId)) {
			boolean result =  this.delData("deal_commissionRecord", "id", commissionId, false);
			//删除以后，要重新计算分成金额
			if(result) {
				if(commissionType==0)//更新中介类分成信息
					return this.updateInfoByCommission(dealId);
				else //更新金融类分成信息
					return this.updateInfoByFinanceCommission(dealId);
			}
		}
		return false;
	}


	/**
	 * 根据成交编号统计金融类佣金信息
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param dealId
	 * @return: Map<String, Object>
	 * @see com.bashiju.deal.service.DealService#statisticFinanceCommissions(java.lang.String)   
	 */
	@Override
	public Map<String, Object> statisticFinanceCommissions(String dealId) {
		if(!StringUtils.isEmpty(dealId))
			return this.dealMapper.statisticFinanceCommissions(dealId);
		return null;
	}

}
