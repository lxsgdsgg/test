/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午6:38:04   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.api.DealServiceApi;
import com.bashiju.api.DecisionConfigurateServiceApi;
import com.bashiju.api.DivideintoServiceApi;
import com.bashiju.api.ErHousingServiceApi;
import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.AgentTypeEnum;
import com.bashiju.enums.CommissionStatusEnum;
import com.bashiju.enums.CustomerStatusEnum;
import com.bashiju.enums.CustomerTransactionEnum;
import com.bashiju.enums.DealTypeEnum;
import com.bashiju.enums.DecisionConfigurateEnum;
import com.bashiju.enums.DiscStatusEnum;
import com.bashiju.enums.DividiintoDealTypeEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.FtpSourceTypeEnum;
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.LabelTypeEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.LogBusinessTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.NumberCount;
import com.bashiju.enums.PerformanceTypeEnum;
import com.bashiju.enums.ReportAnalysisTypeEnum;
import com.bashiju.enums.TaoBaoStatusEnum;
import com.bashiju.housing.global.IdWorker;
import com.bashiju.housing.mapper.ErHousingMapper;
import com.bashiju.housing.mapper.FormMapper;
import com.bashiju.housing.pojo.BusinessExamineHandleTypeEnum;
import com.bashiju.housing.pojo.BusinessExamineTypeEnum;
import com.bashiju.housing.pojo.HouseAttachTypeEnum;
import com.bashiju.housing.pojo.HouseQueryEntity;
import com.bashiju.housing.service.BusinessExamineService;
import com.bashiju.housing.service.FormService;
import com.bashiju.housing.service.IErHousingService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.pojo.BusinessLogModel;
import com.bashiju.utils.service.BrowseRecordService;
import com.bashiju.utils.service.BusinessLogService;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;

/**   
 * @ClassName:  ErHousingServiceImpl   
 * @Description: 二手房源服务实现类   
 * @author: wangkaifa
 * @date:   2018年4月10日 下午6:38:04   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="二手房源") 
@Service
public class ErHousingServiceImpl implements IErHousingService {
	/**万元转分 **/
	private static final int WANYUAN=1000000;
	/**元转分 **/
	private static final int YUAN=100;
	@Autowired
	private CommonSqlServie commonSqlServie;
	@Autowired
	RedisServiceApi redisServiceApi;
	@Autowired
	FormMapper formmapper;
	@Autowired
	ErHousingMapper erHousingMapper;
	@Autowired
	FormService formservice;
	@Autowired
	BusinessExamineService businessExamineServiceImpl;
	@Autowired
	BusinessExamineService businessExamineService;
	@Autowired
	DataAuthHelper dataAuthHelper;
	@Autowired
	DivideintoServiceApi divideintoServiceApi;
	@Autowired
	DealServiceApi dealServiceApi;
	@Autowired 
	DecisionConfigurateServiceApi decisionConfigurateServiceApi;
	@Autowired 
	ErHousingServiceApi erHousingServiceApi;
	@Autowired
	RedisGetIdServiceApi redisGetIdServiceApi;
	@Autowired
	BusinessLogService businessLogService;
	@Autowired
	BrowseRecordService browseRecordService;
	

	@Override
	public String queryTableTitle(String tableName,boolean isListShow) {
		/*DataAuthHelper.auth();
		String list=commonSqlServie.*/
		
		 return null;
	}
	
	@SystemServiceLog(operationType="查询部门")
	@Override
	public LinkedList<Map<String,Object>> queryDept(){
		LinkedList<Map<String,Object>> depts = erHousingMapper.queryDept();
		ExecutionResult.descFormat("", "查询部门用户");
		return depts;
	}
	
	@SystemServiceLog(operationType="查询房源详细信息")
	@Override
	public Map<String,Object> queryHouseInfo(Long HouseId,String formName,String type){
		long start=System.currentTimeMillis();
		Map<String,Object> res = new HashMap<>();
		Map<String,Object> user = UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		
		dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		Map<String,Object> houseInfo = erHousingMapper.queryHouseInfo(HouseId,userid);//房源信息
		if(houseInfo.get("rentPrice")!=null && StringUtils.isNotEmpty(houseInfo.get("rentPrice").toString())){
			houseInfo.put("rentPrice", Integer.parseInt(houseInfo.get("rentPrice").toString())/100);
		}
		if(houseInfo.get("sellingPrice")!=null && StringUtils.isNotEmpty(houseInfo.get("sellingPrice").toString())){
			houseInfo.put("sellingPrice", Integer.parseInt(houseInfo.get("sellingPrice").toString())/1000000);
		}
		if(houseInfo!=null && houseInfo.size()>0)
			res.put("houseInfo", houseInfo);//返回房源基本信息
		List fields = formservice.queryCustomField(formName);//查出自定义的字段
		if (fields!=null&&fields.size()>0) {
			Map<String,Object> customInfo = new HashMap<>();
			customInfo.put("customField", fields);//返回自定义的字段
			Map<String,Object> customField = new HashMap<>();
			for(int i=0;i<fields.size();i++) {
				Map<String,Object> m = (Map<String,Object>)fields.get(i);
				customField.put(m.get("field").toString(), m.get("title").toString());
			}
			Map<String,Object> customValue = erHousingMapper.queryCustomValue(customField,formName,HouseId+"");
			if(customValue!=null && customValue.size()>0)
				customInfo.put("customValue", customValue);//返回自定义字段的值
			
			res.put("customInfo", customInfo);
		}
		
		if("showinfo".equals(type)) {
			LinkedList<Map<String,Object>> followInfo= erHousingMapper.queryFollowInfo(HouseId);//跟进信息（默认查了6条）
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_36.getCode(),user.get("cityCode").toString());
			LinkedList<Map<String,Object>> relateagentInfo = null;
			if("1".equals(decision.get("val"))) {//决策配置直接显示经济人电话
				relateagentInfo = erHousingMapper.queryRelateagentInfo(HouseId,true);//经纪人信息
			}else if("0".equals(decision.get("val"))){
				relateagentInfo = erHousingMapper.queryRelateagentInfo(HouseId,false);//经纪人信息
			}
			
			Map<String,Object> relateagentMap = new HashMap<>();
			for(Map<String,Object> map:relateagentInfo) {
				String agentType = map.get("agentType").toString();
				relateagentMap.put(agentType, map.get("agentId"));
			}
			List<Map<String,Object>> relateagentMoney = queryAchievement(HouseId,relateagentMap);//查经纪人业绩
			List<Map<String,Object>> houseLable = erHousingMapper.queryHouseLable(HouseId);//房源标签
			
			List<Map<String, Object>> phoneList=erHousingMapper.queryHousePhone(HouseId,null);//电话列表
			
			if(followInfo.size()>0) {
				res.put("followInfo", followInfo);//返回房源跟进信息
			}
			if(relateagentInfo.size()>0) {
				res.put("relateagentInfo", relateagentInfo);//返回经纪人信息
			}
			if(relateagentMoney.size()>0) {
				res.put("relateagentMoney", relateagentMoney);//返回经纪人业绩
			}
			if(houseLable.size()>0) {
				res.put("houseLable", houseLable);//返回房源标签
			}
			if(phoneList.size()>0) {
				res.put("phoneList", phoneList);//返回电话列表
			}
			
		}
		
		long end=System.currentTimeMillis();
		System.out.println("service费时："+(end-start));
		//ExecutionResult.descFormat("房源id："+houseInfo.get("id").toString(), "查询房源详细信息");
		//ExecutionResult.descFormat("", sourceId, excuteContext);
		return res;
	}
	
	public String getHouseUses(String houseUses) {
		
		if(houseUses.equals(HouseUsesEnum.HOUSE.getCode()) || houseUses.equals(HouseUsesEnum.VILLA.getCode()) || houseUses.equals(HouseUsesEnum.VILLA.getCode())) {
			return DividiintoDealTypeEnum.DEAL_RESIDENCE.getCode(); //属于住宅（住宅、别墅、车位）
		}else{
			return DividiintoDealTypeEnum.DEAL_BUSINESS.getCode();//属于商业（写字楼、商铺、仓库、厂房、土地、公寓）
		}
	}
	
	@SystemServiceLog(operationType="查询分成比例")
	@Override
	public Map<String,Object> queryAchievementRate(String companyId,String achievementType) {
		Map<String,Object> commisionRate = erHousingMapper.queryAchievementRate(companyId,achievementType);
		return commisionRate;
	}
	//查经纪人业绩
	public List<Map<String,Object>> queryAchievement(Long HouseId,Map<String,Object> relateagentMap){
		List<Map<String,Object>> perMoney = new ArrayList<>();
		Map<String,Object> houseInfo = erHousingMapper.querySomeInfoByhouseId(HouseId);
		String companyId = houseInfo.get("companyId").toString();
		String cityCode = houseInfo.get("cityCode").toString();
		String transactionType = houseInfo.get("transactionTypeId").toString();
		String houseUses = houseInfo.get("houseUsesId").toString();
		String configType = "";
		String delType = getHouseUses(houseUses);
		String achievementType = "";
		if(transactionType.equals(HouseTransactionEnum.SELL.getCode()) || transactionType.equals(HouseTransactionEnum.LEASE_SELL.getCode())) {
			//出售或租售类型
			configType = DealTypeEnum.BUSINESSDEAL.getCode();
			if(DividiintoDealTypeEnum.DEAL_RESIDENCE.getCode().equals(delType)) {
				achievementType=PerformanceTypeEnum.HOUSESALEDEAL.getCode();//住宅出售
			}else {
				achievementType=PerformanceTypeEnum.BUSINESSSALEDEAL.getCode();//商业出售
			}
		}else if(transactionType.equals(HouseTransactionEnum.LEASE.getCode())){
			//出租
			configType = DealTypeEnum.LEASEDEAL.getCode();
			if(DividiintoDealTypeEnum.DEAL_RESIDENCE.getCode().equals(delType)) {
				achievementType=PerformanceTypeEnum.HOUSELEASEDEAL.getCode();//住宅出租
			}else {
				achievementType=PerformanceTypeEnum.BUSINESSLEASEDEAL.getCode();//商业出租
			}
		}
		//查出分成比例
		Map<String,Object> commisionData = this.queryAchievementRate(companyId,achievementType); 
		if(commisionData!=null) {
			if(houseInfo.get("sellingPrice")!=null) {//出售
				BigDecimal sellingPrice = new BigDecimal(houseInfo.get("sellingPrice").toString());
				if(commisionData.containsKey("commisionRate") && StringUtils.isNoneEmpty(commisionData.get("commisionRate").toString()) && Integer.parseInt(commisionData.get("commisionRate").toString())>0) {
					//通过比例
					BigDecimal sellingPriceRate = sellingPrice.multiply(new BigDecimal(Integer.parseInt(commisionData.get("commisionRate").toString()))).divide(new BigDecimal(100));
					try{
						//查出经纪人具体分得金额
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions(sellingPriceRate.toString(),configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e) {
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}else if(commisionData.containsKey("fiexdBalance") && StringUtils.isNoneEmpty(commisionData.get("fiexdBalance").toString())) {
					try {
						//查出经纪人具体分得金额（通过固定值）
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions(commisionData.get("fiexdBalance").toString(),configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e) {
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}else {
					try {
						//查出经纪人具体分得金额(没有配置佣金标准)
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions("0",configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e) {
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}
			}else if(houseInfo.get("rentPrice")!=null) {//出租
				BigDecimal rentPrice = new BigDecimal(houseInfo.get("rentPrice").toString());
				if(commisionData.containsKey("commisionRate") && StringUtils.isNoneEmpty(commisionData.get("commisionRate").toString()) && Integer.parseInt(commisionData.get("commisionRate").toString())>0) {
					//通过比例
					BigDecimal rentPriceRate = rentPrice .multiply(new BigDecimal(Integer.parseInt(commisionData.get("commisionRate").toString()))).divide(new BigDecimal(100));
					try {
						//查出经纪人具体分得金额
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions(rentPriceRate.toString(),configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e){
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}else if(commisionData.containsKey("fiexdBalance") && StringUtils.isNoneEmpty(commisionData.get("fiexdBalance").toString())) {
					try {
						//查出经纪人具体分得金额(通过固定值)
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions(commisionData.get("fiexdBalance").toString(),configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e){
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}else {
					try {
						//查出经纪人具体分得金额(没有配置佣金标准)
						perMoney = divideintoServiceApi.caculateDivideIntoByConditions("0",configType,delType,cityCode,companyId,relateagentMap);
					}catch(Exception e) {
						for (Map.Entry<String, Object> entry : relateagentMap.entrySet()) { 
							  Map<String,Object> retMap = new HashMap<>();
							  retMap.put("estimateProfit", "没有提成");
							  retMap.put("dividRate", 0);
							  retMap.put("dividReason", entry.getKey());
							  retMap.put("agentId", entry.getValue());
							  perMoney.add(retMap);
						}
					}
					
				}
				
			}
		}
		
		return perMoney;
	}
	
	@SystemServiceLog(operationType="查询改价历史")
	@Override
	public Page<Map<String, Object>> queryPricedetail(Long shhId,int page,int limit){
		
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryPricedetail(shhId);
		
		ExecutionResult.descFormat("房源id："+shhId+"", "查询改价历史");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询客户档案")
	@Override
	public Page<Map<String, Object>> queryArchives(Long shhId,String type,int page,int limit){
		//String companyId=UserThreadLocal.get().get("companyId").toString(); 
		String companyId = erHousingMapper.queryCustomerCompany(shhId);
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		if("house".equals(type)) {//查房源
			dataAuthHelper.auth(MenuEnum.MENU_84.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = erHousingMapper.queryCustomerHouse(shhId,companyId);
			ExecutionResult.descFormat("房源id："+shhId+"", "查询客户房源");
		}else if("rent".equals(type)) {//查求租
			dataAuthHelper.auth(MenuEnum.MENU_97.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = erHousingMapper.queryCustomerDemand(shhId,CustomerTransactionEnum.RENT.getCode(),companyId);
		}else if("buy".equals(type)) {//查求购
			dataAuthHelper.auth(MenuEnum.MENU_96.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = erHousingMapper.queryCustomerDemand(shhId,CustomerTransactionEnum.BUY.getCode(),companyId);
		}
		
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询带看")
	@Override
	public Page<Map<String, Object>> queryLookHouse(Long shhId,int page,int limit){
		PageHelper.startPage(page, limit);
		int houseType = 0;//二手房 (注：houseType = 1是新房楼盘)
		dataAuthHelper.auth(MenuEnum.MENU_102.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryLookHouse(shhId,houseType);
		
		ExecutionResult.descFormat("房源id："+shhId+"", "查询房源带看信息");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询跟进")
	@Override
	public Page<Map<String, Object>> queryFollowRecord(Long shhId,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_88.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryFollowRecord(shhId);
		
		ExecutionResult.descFormat("房源id："+shhId+"", "查询房源跟进信息");
		return pageInfo;
	}
	
	
	
	@SystemServiceLog(operationType="提交审核")
	@Override
	public String businessExamine(Map<String,Object> data) {
		Long res = 0l;
		String type = "";
		Map<String,Object> examineInfo = new HashMap<>();
		if(data.containsKey("type")) {//需要审核的类型
			type = data.get("type").toString();
		}
		if(data.containsKey("examineInfo")) {
			examineInfo = (Map)data.get("examineInfo");
		}
		Long houseId = Long.parseLong(examineInfo.get("shhId").toString());
		Map<String,Object> user=UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		String userPhone = user.get("mobile").toString();
		String cityCode = user.get("cityCode").toString();
		Map dept = erHousingMapper.queryDeptName(userid);
		String deptId = dept.get("deptId").toString();
		String deptname = dept.get("deptName").toString();
		
		examineInfo.put("operatorId", userid);
		examineInfo.put("operator", username);
		//申请人
		examineInfo.put("permissionArea", deptId);
		examineInfo.put("applyerId", userid);
		examineInfo.put("applyer", username);
		examineInfo.put("applyTime", new Date());
		
		if("setPrivatedisc".equals(type)) {//收为私盘，要变更维护人
			
			int housenum = erHousingMapper.queryHouseByMainter(userid);//用户下已有私盘数量
			//决策限制私盘数量
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_4.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				if(housenum<val) {
					//取审核类型
					examineInfo.put("examineTypeId",BusinessExamineTypeEnum.SETPRIVATEDISC.getCode());
					Map<String,Object> remark = (Map)examineInfo.get("remark");
					remark.put("applyerId", userid);
					examineInfo.put("remark", JSONObject.toJSONString(remark));
					res = businessExamineService.saveBusinessExamine(examineInfo);
				}else {//超出限制数量
					return "私盘数量已达到上限";
				}
			}
			
		}
		
		if("changestatus".equals(type)) {//更改房源状态
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.UPDATESTATUS.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			examineInfo.put("remark", JSONObject.toJSONString(remark));
		} 
		
		if("houseclose".equals(type)) {//封盘申请
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.CLOSEDISC.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");	
			Long id = Long.parseLong(examineInfo.get("shhId").toString());
			String endTime = remark.get("closeHouseEndTime").toString();
			LocalDate date = LocalDate.parse(endTime);
			int examineNum = erHousingMapper.queryExamineInfo(id,BusinessExamineTypeEnum.CLOSEDISC.getCode());
			if(examineNum>0) {
				return "已申请封盘，不能再封";
			}else {
				Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_13.getCode(),user.get("cityCode").toString());
				if(decision!=null && decision.containsKey("val")) {
					Long day1 = Long.parseLong(decision.get("val").toString());
					long day2 = LocalDate.now().until(date, ChronoUnit.DAYS);
					if(day1<day2) {
						LocalDate realTime = LocalDate.now().plus(day1,ChronoUnit.DAYS);
						remark.put("closeHouseEndTime", realTime.toString());
					}
				}
				examineInfo.put("remark", JSONObject.toJSONString(remark));
				res = businessExamineService.saveBusinessExamine(examineInfo);
			}
			
		} 
		
		if("houseopen".equals(type)){//房源开盘
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.OPENDISC.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			remark.put("statusId", HouseStatusEnum.EFFECTIVE.getCode());
			remark.put("statusName", HouseStatusEnum.EFFECTIVE.getDesc());
			remark.put("applyerId", userid);
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			
		}
		
		if("tipoff".equals(type)){//房源举报
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.ACCUSATION.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			examineInfo.put("remark", JSONObject.toJSONString(remark));
		}
		
		if("fromtaobao".equals(type)){//从淘宝淘出
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.TAOBAO.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			remark.put("applyerId", userid);
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			Long id = Long.parseLong(examineInfo.get("shhId").toString());
			Map<String,Object> map = erHousingMapper.queryTaobaoTime(id);
			if(map!=null) {
				int examineNum = erHousingMapper.queryExamineInfo(id,BusinessExamineTypeEnum.TAOBAO.getCode());
				if(examineNum>0) {
					return "已淘宝，不能再淘";
				}else {
					DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
					String taobaoTime1 = erHousingMapper.queryTaobaoTime(id).get("taobaoTime").toString();
					LocalDateTime taobaoTime2 = LocalDateTime.parse(taobaoTime1,df);
					long hour = taobaoTime2.until(LocalDateTime.now(), ChronoUnit.HOURS);
					//本人保护
					Map<String,Object> decision1 = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_1.getCode(),user.get("cityCode").toString());
					//本店保护
					Map<String,Object> decision2 = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_2.getCode(),user.get("cityCode").toString());
					//本区保护
					Map<String,Object> decision3 = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_3.getCode(),user.get("cityCode").toString());
					if(decision1!=null && decision1.containsKey("val")) {
						String day = decision1.get("val").toString();
						long dayLong = Long.parseLong(day);
						if(hour<dayLong*24) {
							if(userid!=Long.parseLong(map.get("maintainId").toString())) {
								return "受保护不能淘";
							}
						}else {
							//数量是否还能淘
							return taobaoCount(userPhone,houseId,cityCode,examineInfo);
						}
					}else if(decision2!=null && decision2.containsKey("val")){
						String day = decision2.get("val").toString();
						long dayLong = Long.parseLong(day);
						if(hour<dayLong*24) {
							if(userid!=Long.parseLong(map.get("maintainId").toString())) {
								return "受保护不能淘";
							}
						}else {
							//数量是否还能淘
							return taobaoCount(userPhone,houseId,cityCode,examineInfo);
						}
					}else if(decision3!=null && decision3.containsKey("val")) {
						String day = decision3.get("val").toString();
						long dayLong = Long.parseLong(day);
						if(hour<dayLong*24) {
							if(userid!=Long.parseLong(map.get("maintainId").toString())) {
								return "受保护不能淘";
							}
							//数量是否还能淘
							return taobaoCount(userPhone,houseId,cityCode,examineInfo);
						}
					}else {
						//数量是否还能淘
						return taobaoCount(userPhone,houseId,cityCode,examineInfo);
					}
				}
				
			}else {
				return "不能淘";
			}
			
		}
		if("agentfeedback".equals(type)) {//经纪人对房源的评价反馈
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.AGENTFEEDBACK.getCode());
			Map<String,Object> remark = new HashMap<>();
			remark.put("agentfeedbackId", data.get("agentfeedbackId"));
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			
		}
		//Long res = commonSqlServie.commonOperationDatabase(examineInfo, "hs_examine", false);
		if(!"fromtaobao".equals(type) && !"setPrivatedisc".equals(type) && !"houseclose".equals(type)) {
			res = businessExamineService.saveBusinessExamine(examineInfo);
		}
		 
		if("changestatus".equals(type)) {
			ExecutionResult.descFormat("", "更改房源状态提交审核");
		}else if("houseclose".equals(type)){
			ExecutionResult.descFormat("", "申请封盘提交审核");
		}else if("houseopen".equals(type)){
			ExecutionResult.descFormat("", "房源开盘提交审核");
		}else if("tipoff".equals(type)){
			ExecutionResult.descFormat("", "房源举报提交审核");
		}else if("fromtaobao".equals(type)){
			ExecutionResult.descFormat("", "从淘宝淘出提交审核");
		}else if("agentfeedback".equals(type)) {
			ExecutionResult.descFormat("", "经纪人对房源的反馈提交审核");
		}
		return res+"";
	}
	
	
	//用户每天能淘宝次数
	public String taobaoCount(String userPhone,Long houseId,String cityCode,Map<String,Object> examineInfo) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.HS_TAOBAO_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(houseId+"", houseId);
		if(resMap==null || resMap.size()<=0) {
			valueMap.put("num", 1);
			String value = JSON.toJSONString(valueMap);
			//用户淘宝加1
			redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_TAOBAO_COUNT, value, true);
			Long examineres = businessExamineService.saveBusinessExamine(examineInfo);
			return examineres+"";
		}else {
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_10.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//还可以淘
					num = hasNum+1;
					valueMap.put("num", num);
					String value = JSON.toJSONString(valueMap);
					//淘宝加1
					redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_TAOBAO_COUNT, value, true);
					Long examineres = businessExamineService.saveBusinessExamine(examineInfo);
					return examineres+"";
				}else {
					return "已到淘宝上限，不能再淘";
				}
				  
			}
		}
		return null;
	}
	
	@SystemServiceLog(operationType="更改一些状态")
	@Override
	public String changeSomeStatus(Map<String,Object> data) {
		Long res = 0l;
		String type = "";
		Map<String,Object> statusInfo = new HashMap<>();
		if(data.containsKey("type")) {//要更改的类型
			type = data.get("type").toString();
		}
		if(data.containsKey("statusInfo")) {
			statusInfo = (Map)data.get("statusInfo");
		}
		
		if("changeUsage".equals(type)) {//改变房源用途,要修改动态字段存值表的formName字段
			Long houseId = Long.parseLong(statusInfo.get("id").toString());
			String houseUsesId = statusInfo.get("houseUsesId").toString();
			Map<String,Object> mm = new HashMap<>();
			mm.put("formName", houseUsesId);
			mm.put("keyId", houseId+"");
			res = commonSqlServie.commonOperationDatabase(mm, "sys_custom_column_value", "keyId", false);
			res = commonSqlServie.commonOperationDatabase(statusInfo, "hs_secondhandhouse", "id", false);
		}
		
		
		if("toTaobao".equals(type)) {//设置为放入淘宝池
			statusInfo.put("taobaoStatusId", TaoBaoStatusEnum.TAOBAO.getCode());
			statusInfo.put("taobaoStatus", TaoBaoStatusEnum.TAOBAO.getDesc());
			statusInfo.put("discStatusId", DiscStatusEnum.PUBLICDISC.getCode());
			statusInfo.put("discStatus", DiscStatusEnum.PUBLICDISC.getDesc());
			statusInfo.put("taobaoTime", new Date());
			res = commonSqlServie.commonOperationDatabase(statusInfo, "hs_secondhandhouse", "id", false);
		}
		
		if("changeUsage".equals(type)) {
			ExecutionResult.descFormat("", "更改房源用途");
		}else if("toTaobao".equals(type)){
			ExecutionResult.descFormat("", "房源放入淘宝");
		}
		return res+"";
	}
	
	@SystemServiceLog(operationType="修改推荐标签状态")
	@Override
	public Long updateTag(Long shhId,String type,int status) {
		Long res = 0l;
		Map<String,Object> m = new HashMap<>();
		m.put("id", shhId);
		//设置或取消置顶
		if("top".equals(type)) {
			m.put("isSetTop",status);
			if(status==1) {//设置置顶，添加置顶时间
				m.put("setTopTime", new Date());
			}
			res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
			ExecutionResult.descFormat("", "设置或取消置顶");
		}
		//设置或取消靓房
		if("good".equals(type)) {
			m.put("isGood", status);
			res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
			houseSynchron(shhId.toString());
			ExecutionResult.descFormat("", "设置或取消靓房");
		}
		//设置或取消收藏
		if("store".equals(type)) {
			Map<String,Object> user=UserThreadLocal.get();
			Long userid = Long.parseLong(user.get("id").toString());
			String username = user.get("realName").toString();
			m = new HashMap<>();
			m.put("shhId", shhId);
			m.put("isValid", status);
			Map<String,Object> store = erHousingMapper.queryStore(shhId,userid);
			if(status==1) {//设状态为收藏
				
				if(store==null) {//没收藏过，新增一条
					m.put("operatorId", userid);
					m.put("operator", username);
					res = commonSqlServie.commonOperationDatabase(m, "hs_mycollection", false);
					ExecutionResult.descFormat("", "设为收藏");
				}else {//改状态为收藏
					Long storeid = Long.parseLong(store.get("id").toString());
					m.put("id", storeid);
					res = commonSqlServie.commonOperationDatabase(m, "hs_mycollection","id", false);
					ExecutionResult.descFormat("", "更改为收藏");
				}
			}else {//改状态为取消收藏
				Long storeid = Long.parseLong(store.get("id").toString());
				boolean res1 = commonSqlServie.delData("hs_mycollection", "id", storeid+"", false);
				ExecutionResult.descFormat("", "取消收藏");
				if(res1)
					res = 1l;
			}
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="保存")
	@Override
	public Long saveHouseFollow(Map<String,Object> data) {
		Long res = 0l;
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		Map<String,Object> dept = erHousingMapper.queryDeptName(userid);
		String deptid = dept.get("deptId").toString();
		Long shhId = 0l;
		if(data.containsKey("followInfo")) {//写跟进
			Map<String,Object> followInfo = (Map)data.get("followInfo");
			shhId = Long.parseLong(followInfo.get("shhId").toString());
			followInfo.put("companyId", companyId);
			followInfo.put("companyName", companyName);
			followInfo.put("operatorId", userid);
			followInfo.put("operator", username);
			followInfo.put("deptId", deptid);
			followInfo.put("followerId",userid);
			followInfo.put("follower",username);
			followInfo.put("permissionArea",deptid);
			followInfo.put("followTime",new Date());
			res = commonSqlServie.commonOperationDatabase(followInfo, "hs_followrecord", false);
			//二手房源表跟进时间
			Map<String,Object> mm = new HashMap<>();
			mm.put("id", shhId);
			Map<String,Object> mainter =erHousingMapper.queryMainter(shhId) ;
			Long mainterId = Long.parseLong(mainter.get("maintainId").toString());
			if(mainterId==userid) {//维护人跟进时间
				mm.put("mainterFllowTime", new Date());
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse","id", false);
			}else {//其他人员跟进时间
				mm.put("allFollowTime", new Date());
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse","id", false);
			}
			ExecutionResult.descFormat("", "保存房源跟进信息");
		}
		if(data.containsKey("remindInfo")) {//写提醒
			Map<String,Object> remindInfo = (Map)data.get("remindInfo");
			String receivePeopleName = remindInfo.get("receivePeopleName").toString();
			if(!"".equals(receivePeopleName)) {
				remindInfo.put("sendPeopleId", userid);
				remindInfo.put("sendPeopleName", username);
				remindInfo.put("status", 0);
				remindInfo.put("operator", username);
				res = commonSqlServie.commonOperationDatabase(remindInfo, "sys_remind", false);
				ExecutionResult.descFormat("", "保存提醒");
			}
			
		}
		if(data.containsKey("isCancelNotice")) {//通知撤单
			Map<String,Object> mainter =erHousingMapper.queryMainter(shhId) ;
			Map<String,Object> mm = new HashMap<>();
			mm.put("sendPeopleId", userid);
			mm.put("sendPeopleName", username);
			mm.put("receivePeopleId", mainter.get("maintainId"));
			mm.put("receivePeopleName", mainter.get("maintainer"));
			mm.put("remindTime",new Date());
			mm.put("status", 0);
			mm.put("operator", username);
			mm.put("remindContent", "通知撤单");
			res = commonSqlServie.commonOperationDatabase(mm, "sys_remind", false);
			//二手房源表撤单标志
			mm = new HashMap<>();
			mm.put("id",shhId);
			mm.put("isCancelNotice", 1);
			mm.put("cancelNoticeTime", new Date());
			commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse","id", false);
			ExecutionResult.descFormat("", "通知维护人撤单");
		}
		
		return res;
	}
	public boolean delHouseFollow(Long id) {
		boolean res = commonSqlServie.delData("hs_followrecord", "id", id+"", false);
		return res;
	}
	@SystemServiceLog(operationType="设置全景看房")
	@Override
	public Long saveOverallview(Long houseId,String address) {
		Map<String,Object> m = new HashMap<>();
		m.put("id", houseId);
		m.put("panorama", address);
		Long res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse","id", false);
		ExecutionResult.descFormat("房源id："+houseId+"", "设置全景看房地址");
		return res;
	}
	
	@SystemServiceLog(operationType="保存业主修改")
	@Override
	public Long UpdateHouseOwner(Map<String,Object> data) {
		Map<String,Object> mm = (Map)data;
		Long  res = commonSqlServie.commonOperationDatabase(mm, "hs_secondhandhouse","id", false);
		ExecutionResult.descFormat("房源id："+mm.get("id").toString(), "修改房源业主姓名信息");
		return res;
	}
	
	@SystemServiceLog(operationType="保存业主电话")
	@Override
	public String saveOwnerPhone(Map<String,Object> data) {
		Map<String,Object> mm = (Map)data;
		String phone = mm.get("phone").toString();
		if (StringUtils.isNotEmpty(phone)) {
			boolean idBlack=erHousingServiceApi.queryCountPhoneBlack(phone);
			if (idBlack) {//是黑名单
				return "该电话是黑名单，不能添加！";
			}
		}
		Long res = commonSqlServie.commonOperationDatabase(mm, "hs_houseownerrelate", false);
		ExecutionResult.descFormat("房源id："+mm.get("shhId").toString(), "新增业主电话");
		return res+"";
	}
	
	@SystemServiceLog(operationType="修改业主电话")
	@Override
	public String updateOwnerPhone(Map<String,Object> data) {
		Map<String,Object> mm = (Map)data;
		String phone = mm.get("phone").toString();
		if (StringUtils.isNotEmpty(phone)) {
			boolean idBlack=erHousingServiceApi.queryCountPhoneBlack(phone);
			if (idBlack) {//是黑名单
				return "该电话是黑名单，不能添加！";
			}
		}
		Long res = commonSqlServie.commonOperationDatabase(mm, "hs_houseownerrelate","id", false);
		ExecutionResult.descFormat("电话记录id："+mm.get("id").toString(), "修改业主电话");
		return res+"";
	}
	
	@SystemServiceLog(operationType="删除业主电话")
	@Override
	public boolean delOwnerPhone(Long id) {
		boolean res = commonSqlServie.delData("hs_houseownerrelate","id",id+"", false);
		ExecutionResult.descFormat("电话记录id："+id, "删除业主电话");
		return res;
	}
	
	@SystemServiceLog(operationType="变更业主")
	@Override
	public String changeHouseOwner(Map<String,Object> data) {
		Long houseId = 0l;
		Map<String,Object> owner = new HashMap<>();
		Map<String,Object> ownerphone = new HashMap<>();
		if(data.containsKey("houseId")) {
			houseId = Long.parseLong(data.get("houseId").toString());
		}
		if(data.containsKey("owner")) {
			owner =  (Map)data.get("owner");
		}
		if(data.containsKey("ownerphone")) {
			ownerphone =  (Map)data.get("ownerphone");
		}
		String phone = ownerphone.get("phone").toString();
		if (StringUtils.isNotEmpty(phone)) {
			boolean idBlack=erHousingServiceApi.queryCountPhoneBlack(phone);
			if (idBlack) {//是黑名单
				return "该电话是黑名单，不能添加！";
			}
		}
		//修改二手房源主表的业主信息
		Long  res = commonSqlServie.commonOperationDatabase(owner, "hs_secondhandhouse","id", false);
		//把原业主的电话设置为无效
		boolean res1 =  commonSqlServie.delData("hs_houseownerrelate", "shhId", houseId+"", false);
		//添加新业主电话
		res = commonSqlServie.commonOperationDatabase(ownerphone, "hs_houseownerrelate", false);
		ExecutionResult.descFormat("房源id："+houseId, "变更业主");
		return res + "";
		
	}
	
	@SystemServiceLog(operationType="修改低价")
	@Override
	public Long updatePrice(Map<String,Object> data) {
		
		//修改二手房源主表
		Map<String,Object> m = new HashMap<>();
		m.put("id", data.get("shhId"));
		int run=0;
		if (data.get("minSellingPrice")!=null&&!"".equals(data.get("minSellingPrice"))) {
			m.put("minSellingPrice",convertFen(data.get("minSellingPrice").toString(), WANYUAN) );
			run++;
		}
		if (data.get("minRentPrice")!=null&&!"".equals(data.get("minRentPrice"))) {
			m.put("minRentPrice",convertFen(data.get("minRentPrice").toString(), YUAN) );
			run++;
		}
		if (run==0) {
			return 1l;
		}
		
		Long res = commonSqlServie.commonOperationDatabase(m,"hs_secondhandhouse","id",false);
		
		return res;
	}
	
	//修改价格存入改价表
	public boolean saveUpdatePrice(Map<String,Object> data){
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		List<Map<String,Object>> l = new ArrayList<>();
		Map<String,Object> mm = new HashMap<>();
		
		if (data.get("sellFloat")!=null&&!"0".equals(data.get("sellFloat").toString())) {
			//添加出售价修改记录						
			mm.put("sellingPrice", data.get("sellingPrice"));
			mm.put("sellFloat", data.get("sellFloat"));
		}
		
		if (data.get("rentFloat")!=null&&!"0".equals(data.get("rentFloat").toString())) {
			//添加出租价修改记录
			mm.put("rentPrice", data.get("rentPrice"));
			mm.put("rentFloat", data.get("rentFloat"));
		}
		
		if (mm.size()>0) {
			mm.put("companyId", companyId);
			mm.put("companyName", companyName);
			mm.put("shhId", data.get("shhId"));
			mm.put("deptId", user.get("deptId"));
			commonSqlServie.commonOperationDatabase(mm, "hs_changepricerecord", false);
			ExecutionResult.descFormat("房源id："+data.get("shhId").toString(), "修改房源价格");
		}		
		return true;
	}
	
	//变更经纪人具体实现
	public Long updateMyAgent(Long shhId,Long recordid,Long agentId,String agentType,String agentTypeName) {
		Long res = 0l;
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());//操作人id
		String username = user.get("realName").toString();//操作人
		Map<String,Object> dept = erHousingMapper.queryDeptName(agentId);//经纪人部门
		String deptid = dept.get("deptId").toString();
		String deptname = dept.get("deptName").toString();
		String agentName = dept.get("realName").toString();
		//经纪人信息
		Map<String,Object> m = new HashMap<>();
		m.put("shhId", shhId);
		m.put("agentType", agentType);
		m.put("agentTypeName", agentTypeName);
		m.put("agentId", agentId);
		m.put("agentName", agentName);
		m.put("companyId", companyId);
		m.put("companyName", companyName);
		m.put("departmentId", deptid);
		m.put("departmentName", deptname);
		m.put("operatorId", userid);
		m.put("operator", username);
		if(recordid!=0) {//修改
			m.put("id", recordid);
			res= commonSqlServie.commonOperationDatabase(m,"hs_houserelateagent", "id",false);
		}else {//新增
			res = commonSqlServie.commonOperationDatabase(m, "hs_houserelateagent", false);
		}
		return res;
	}
	
	
	@SystemServiceLog(operationType="设置经纪人")
	@Override
	public Long updateAgent(Map<String,Object> data) {
		Long res = 0l;
		Long id = Long.parseLong(data.get("shhId").toString());
		Long recordid = 0l;
		if(!"".equals(data.get("recordid").toString()))
			recordid = Long.parseLong(data.get("recordid").toString());
		Long peopleid = Long.parseLong(data.get("peopleid").toString());
		String people = data.get("people").toString();
		String type = data.get("type").toString();
		
		//查操作人
		Map<String,Object> user=UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		
		if("wh".equals(type)) {//变更维护人
			Map<String,Object> m = new HashMap<>();
			m.put("id", id);
			m.put("maintainer", people);
			m.put("maintainId", peopleid);
			m.put("operator", people);
			m.put("operatorId", peopleid);
			Map dept = erHousingMapper.queryDeptName(peopleid);
			m.put("mainterDeptId", dept.get("deptId"));
			m.put("mainterDept", dept.get("deptName"));
			m.put("permissionArea", dept.get("deptId"));
			//修改二手房主表
			res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
			//变更或新增维护人
			res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
		}
		if("img".equals(type)) {//设置图片人
			res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.PICTURER.getCode(),AgentTypeEnum.PICTURER.getDesc());
		}
		if("ys".equals(type)) {//设置钥匙
			
			res = saveKey(id,recordid,peopleid,userid,username,data);
		}
		if("dj".equals(type)) {//设置独家
			
			res = saveUnique(id,recordid,peopleid,userid,username,data);
		}
		if("wt".equals(type)) {
			
			res = saveEntrust(id,recordid,peopleid,userid,username,data);
		}
		
		if("wh".equals(type)) {
			
		}else if("img".equals(type)) {
			
		}else if("ys".equals(type)) {
			
		}else if("wh".equals(type)) {
			
		}else if("dj".equals(type)) {
			
		}else if("wt".equals(type)) {
			
		}
		
		return res;
		
	}
	//保存钥匙人
	public Long saveKey(Long id,Long recordid,Long peopleid,Long userid,String username,Map<String,Object> data) {
		Long res = 0l;
		//变更或新增钥匙人
		res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.RECIVEKEYER.getCode(),AgentTypeEnum.RECIVEKEYER.getDesc());
		
		//变更或新增钥匙管理记录
		Long ysrecordid = 0l;
		if(!"".equals(data.get("ysrecordid").toString()))
			ysrecordid = Long.parseLong(data.get("ysrecordid").toString());
		Map<String,Object> ysInfo = (Map)data.get("ysInfo");
		
		//操作人
		ysInfo.put("operatorId", userid);
		ysInfo.put("operator", username);
		
		//钥匙被接收到哪个店
		Map<String,Object> dept = erHousingMapper.queryDeptName(peopleid);
		String ysDeptid = dept.get("deptId").toString();
		String ysDeptname = dept.get("deptName").toString();
		ysInfo.put("deptId", dept.get("deptId"));
		ysInfo.put("permissionArea", dept.get("deptId"));//权限域
		
		//1、把钥匙编号存入二手房源表
		Map<String,Object> m = new HashMap<>();
		m.put("id", id);
		m.put("keyCode", ysInfo.get("keyCode"));
		m.put("isKeys", 1);//标志设置为有钥匙
		res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
		
		//2、新增或修改钥匙记录
		if(ysrecordid!=0){//是否有原纪录，有的话，修改
			//res1= commonSqlServie.delData("hs_key", "id", ysrecordid+"", false);//删除
			ysInfo.put("id", ysrecordid);
			res = commonSqlServie.commonOperationDatabase(ysInfo, "hs_key","id", false);
		}else {//新增
			ysInfo.put("status", "01");
			res = commonSqlServie.commonOperationDatabase(ysInfo, "hs_key",false);
		}
		
		//3.记录日志
		res = saveKeylog(userid,username,data,ysInfo,ysDeptid,ysDeptname,res);
		
		return res;
	}
	//记录钥匙日志
	public Long saveKeylog(Long userid,String username,Map<String,Object> data,Map<String,Object> ysInfo,String ysDeptid,String ysDeptname,Long keyId) {
		Long res = 0l;
		Map<String,Object> log = new HashMap<>();
		Map<String,Object> dept = erHousingMapper.queryDeptName(userid);
		log.put("permissionArea", dept.get("deptId"));
		log.put("operatorId", userid);
		log.put("operator", username);
		log.put("keyId", keyId);
		if(data.containsKey("ysHasChange")) {//修改编号
			Map<String,Object> change = (Map)data.get("ysHasChange");
			if(change.containsKey("keyCode")) {
				log.put("operatType", "修改钥匙编号");
				String xKeyCode = ysInfo.get("keyCode").toString();
				String yKeyCode = change.get("keyCode").toString();
				String content = "钥匙编号：" + yKeyCode +"->" + xKeyCode;
				log.put("content", content);
				res = commonSqlServie.commonOperationDatabase(log, "hs_keylog", false);
			}
			if(change.containsKey("yspeopleid")) {//修改钥匙人或钥匙店
				dept = erHousingMapper.queryDeptName(Long.parseLong(change.get("yspeopleid").toString()));
				log.put("operatType", "修改钥匙人");
				String ypeople = dept.get("realName").toString();
				String content = "钥匙所在员工：" + ypeople +"->" + ysInfo.get("reciver");
				log.put("content", content);
				res = commonSqlServie.commonOperationDatabase(log, "hs_keylog", false);
				String ydeptid = dept.get("deptId").toString();
				if(!ysDeptid.equals(ydeptid)) {//所在店不一样
					log.put("operatType", "修改钥匙店");
					content = "钥匙所在店设置为："+ ysDeptname;
					log.put("content", content);
					res = commonSqlServie.commonOperationDatabase(log, "hs_keylog", false);
				}
				
			}
			
		}else {//新增
			log.put("operatType", "新增钥匙");
			String content = "钥匙人=>" + ysInfo.get("reciver")+";"+"钥匙编号："+ysInfo.get("keyCode");
			log.put("content", content);
			res = commonSqlServie.commonOperationDatabase(log, "hs_keylog", false);
		}
		return res;
	}
	//保存独家人
	public Long saveUnique(Long id,Long recordid,Long peopleid,Long userid,String username,Map<String,Object> data) {
		Long res = 0l;
		String code = data.get("code").toString();
		//Date time = (Date)data.get("time");
		int djmoney = Integer.parseInt(data.get("djmoney").toString()) ;
		
		//更改二手房源主表独家编号
		Map<String,Object> m = new HashMap<>();
		m.put("id", id);
		m.put("entrustCode", code);
		m.put("isUnique", 1);//设置为独家
		res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
		
		//变更或新增独家人
		res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.EXCLUSIVER.getCode(),AgentTypeEnum.EXCLUSIVER.getDesc());
		
		//合同纪录
		m = new HashMap<>();
		m.put("code", code);
		m.put("shhId", id);
		m.put("agreementType", "1");//合同类型，1为独家
		m.put("signPerson", peopleid);
		Map<String,Object> dept = erHousingMapper.queryDeptName(peopleid);
		String companyName = dept.get("companyName").toString();
		m.put("partyB", companyName);
		m.put("beginTime", new Date());
		//m.put("endTime", time);
		m.put("permissionArea", dept.get("deptId"));
		m.put("operatorId", userid);
		m.put("operator", username);
		m.put("totalMoney", djmoney);
		//查询房源下是否存在有效的合同
		/*Map<String,Object> agreement = erHousingMapper.queryAgreement(id);
		if(agreement!=null) {//存在的话，修改
			String agreementid = agreement.get("id").toString();
			//res1= commonSqlServie.delData("oa_agreement", "id", agreementid+"", false);
			m.put("id", agreementid);
			res = commonSqlServie.commonOperationDatabase(m, "oa_agreement","id", false);
		}else {
			//res1 = true;
			res = commonSqlServie.commonOperationDatabase(m, "oa_agreement", false);
		}*/
		return res;
	}
	
	//保存委托人
	public Long saveEntrust(Long id,Long recordid,Long peopleid,Long userid,String username,Map<String,Object> data) {
		Long res = 0l;
		String code = data.get("code").toString();
		//Date time = (Date)data.get("time");
		
		//更改二手房源主表委托编号
		Map<String,Object> m = new HashMap<>();
		m.put("id", id);
		m.put("entrustCode", code);
		m.put("isIntrust", 1);
		res = commonSqlServie.commonOperationDatabase(m, "hs_secondhandhouse", "id", false);
		
		//变更或新增委托人
		res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.HOUSEENTRUSTER.getCode(),AgentTypeEnum.HOUSEENTRUSTER.getDesc());
		
		//合同纪录
		m = new HashMap<>();
		m.put("code", code);
		m.put("shhId", id);
		m.put("agreementType", "2");//合同类型，2为委托
		m.put("signPerson", peopleid);
		Map<String,Object> dept = erHousingMapper.queryDeptName(peopleid);
		String companyName = dept.get("companyName").toString();
		m.put("partyB", companyName);
		//m.put("beginTime", time);
		m.put("permissionArea", dept.get("deptId"));
		m.put("operatorId", userid);
		m.put("operator", username);
		//查询房源下是否存在有效的合同
		/*Map<String,Object> agreement = erHousingMapper.queryAgreement(id);
		if(agreement!=null) {//存在的话，修改
			String agreementid = agreement.get("id").toString();
			//res1= commonSqlServie.delData("oa_agreement", "id", agreementid+"", false);
			m.put("id", agreementid);
			res = commonSqlServie.commonOperationDatabase(m, "oa_agreement","id",false);
		}else {
			//res1 = true;
			res = commonSqlServie.commonOperationDatabase(m, "oa_agreement",false);
		}*/
		return res;
	}
	
	
	@SystemServiceLog(operationType="上传")
	@Override
	public String saveFile(HttpServletRequest request){
		
		String  filepath = "";
		String type = FtpFileTypeEnum.FTP_FILE_TP.getCode(); //上传ftp类型(默认设为图片)
		Long res = 0l;
		int pictureType = 0;
		if(request.getParameter("pictureType")!=null)
			pictureType = Integer.parseInt(request.getParameter("pictureType"));//图片类型id
		String pictureName = "";
		if(request.getParameter("pictureName")!=null) {//图片类型名称
			try {
				pictureName =  java.net.URLDecoder.decode(request.getParameter("pictureName"),"UTF-8");
			}catch(Exception e) {
				throw new BusinessException("上传错误");
			}
		}
			
		Long shhId = Long.parseLong(request.getParameter("shhId"));//房源主键ID
		String houseId = request.getParameter("houseId");//房源编号
		String tradeTypeId = request.getParameter("transactionTypeId");//交易类型	
		
		String attachType = request.getParameter("attachType");//附件类型
		
		
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			if (iter.hasNext()) {
				// 一次遍历所有文件
				//List<MultipartFile> files = multiRequest.getFiles(iter.next().toString());
				
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					if(HouseAttachTypeEnum.HOUSEVIDEO.getCode().equals(attachType)) {//视频
						filepath = FtpUtil.uploadFile(file,shhId+"",FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(),type,false);//无水印
					}else {
						filepath = FtpUtil.uploadFile(file,shhId+"",FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(),type,false);
					}
					Map<String,Object> user=UserThreadLocal.get();
					String companyId=user.get("companyId").toString();
					String companyName=user.get("comName").toString();
					Long userid = Long.parseLong(user.get("id").toString());//操作人id
					String username = user.get("realName").toString();//操作人
					/*Map<String,Object> dept = erHousingMapper.queryDeptName(userid);//部门
					String deptid = dept.get("deptId").toString();*/
					String deptid = user.get("deptId").toString();
					//保存房源附件表
					Map<String,Object> mm = new HashMap<>();
					mm.put("code", new IdWorker(666).createId());
					mm.put("companyId", companyId);
					mm.put("companyName", companyName);
					mm.put("shhId", shhId);
					mm.put("houseId", houseId);
					mm.put("pictureName", pictureName);
					mm.put("pictureType", pictureType);
					mm.put("path", filepath);
					mm.put("attachType", attachType);//上传的附件类型
					mm.put("examineStatus", ExamineStatusEnum.UNEXAMINED.getCode());//待审核
					mm.put("operatorId", userid);
					mm.put("operator", username);
					mm.put("applyerId", userid);
					mm.put("applyer", username);
					mm.put("applyTime", new Date());
					mm.put("departmentId", deptid);
					mm.put("permissionArea", deptid);
					res = commonSqlServie.commonOperationDatabase(mm, "hs_houseattachment", false);
					//updateIsPriorityStatus(shhId);
					//houseSynchron(shhId+"");//需要同步
					ExecutionResult.descFormat("", "保存房源附件");
					System.out.println("------------>"+res);
				}
			}
		}
		
		return filepath;
	}
	
	@SystemServiceLog(operationType="查询")
	@Override
	public List queryFile(Long shhId,String attachType){
		List res =  erHousingMapper.queryFile(attachType,shhId);
		return res;
	}
	
	@SystemServiceLog(operationType="查询单个文件")
	@Override
	public String queryFileById(Long imgId){
		String path =  erHousingMapper.queryFileById(imgId);
		return path;
	}
	
	@SystemServiceLog(operationType="更改附件")
	@Override
	public Long updateAttachment(Map<String,Object> data){
		if(data==null || data.size()<=0) {
			throw new BusinessException("没有要保存的数据");
		}
		Long res = 0l;
		//把之前的封面取消
		Long shhId = Long.parseLong(data.get("shhId").toString());
		Map<String,Object> mm = new HashMap<>();
		mm.put("isCover", 0);
		mm.put("shhId", shhId);
		res = commonSqlServie.commonOperationDatabase(mm,"hs_houseattachment","shhId",false);
		//更改新的信息
		Map<String,Object> attachments = (Map)data.get("imgInfos");
		String oldPath = erHousingMapper.queryFileById(Long.parseLong(attachments.get("id").toString()));
		
		if(StringUtils.isNotEmpty(oldPath)){
			//删除原来的
			FtpUtil.deleteFile(shhId+"", FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(), FtpFileTypeEnum.FTP_FILE_TP.getCode(),oldPath);
		}
		res = commonSqlServie.commonOperationDatabase(attachments, "hs_houseattachment","id" ,false);
		updateIsPriorityStatus(shhId);
		houseSynchron(shhId+"");//需要同步
		
		return res;
	}
	
	@SystemServiceLog(operationType="删除附件")
	@Override
	public boolean delFile(String imgIds,Long shhId) {
		boolean res = commonSqlServie.delData("hs_houseattachment", "id", imgIds, false);
		String[] imgIdArr = imgIds.split(",");
		List ids = Arrays.asList(imgIdArr);
		List<Map<String,Object>> paths = erHousingMapper.queryFileByIds(ids);
		for(Map<String,Object> o : paths) {
			if(StringUtils.isNotEmpty(o.get("path").toString())){
				FtpUtil.deleteFile(shhId+"", FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(), FtpFileTypeEnum.FTP_FILE_TP.getCode(), o.get("path").toString());
			}
		}
		return res;
	}
	
	@SystemServiceLog(operationType="保存房源描述")
	@Override
	public boolean saveHouseDescription(List<Map<String,Object>> data) {
		boolean res = false;
		boolean add = true;
		for(int i=0;i<data.size();i++) {
			Map<String,Object> mm = (Map)data.get(i);
			Map<String,Object> user=UserThreadLocal.get();
			String companyId=user.get("companyId").toString();
			String companyName=user.get("comName").toString();
			Long userid = Long.parseLong(user.get("id").toString());//操作人id
			String username = user.get("realName").toString();//操作人
			Map<String,Object> dept = erHousingMapper.queryDeptName(userid);//部门
			String deptid = dept.get("deptId").toString();
			mm.put("companyId",companyId);
			mm.put("companyName",companyName);
			mm.put("operatorId",userid);
			mm.put("permissionArea", deptid);
			mm.remove("operator");
			mm.remove("deptName");
			mm.remove("addTime");
			if(StringUtil.isNotEmpty(data.get(i).get("id").toString())) {
				add = false;
			}else {
				mm.remove("id");
			}
		}
		
		
		if(add) {//新增
			res = commonSqlServie.batchCommonOperationDatabase(data, "hs_house_description", false);
			ExecutionResult.descFormat("", "新增房源描述");
		}else{//修改
			res = commonSqlServie.batchCommonOperationDatabase(data, "hs_house_description","id", false);
			ExecutionResult.descFormat("", "修改房源描述");
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="查询")
	@Override
	public Page<Map<String, Object>> queryHouseDescription(Long shhId,int page,int limit) {
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryHouseDescription(shhId);
		ExecutionResult.descFormat("房源id："+shhId+"", "查询房源描述信息");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="保存经纪人反馈")
	@Override
	public Long saveAgentFeedback(Map<String,Object> data) {
		Long res = null;
		Long shhId = Long.parseLong(data.get("shhId").toString());
		Map<String,Object> mm = (Map)data;
		//查看该经纪人是否带看该房源
		//int aa = Long.parseLong(UserThreadLocal.get().get("id").toString());
		int n = erHousingMapper.queryLookHouseByAgent(shhId,Long.parseLong(UserThreadLocal.get().get("id").toString()));
		if(n>0) {
			mm.put("userId", UserThreadLocal.get().get("id"));
			mm.put("operatorId", UserThreadLocal.get().get("id"));
			mm.put("operator", UserThreadLocal.get().get("realName"));
			mm.put("permissionArea", UserThreadLocal.get().get("deptId"));
			mm.put("examineStatus", ExamineStatusEnum.UNEXAMINED.getCode());
			mm.put("usefulCnt", 0);
			mm.put("showedCnt", n);
			mm.put("reason", "");
			if(mm.containsKey("id") && StringUtil.isNotEmpty(mm.get("id").toString())) {//修改
				res = commonSqlServie.commonOperationDatabase(mm, "hs_agentEvaluateHouse","id", false) ;
			}else {
				mm.remove("id");
				res = commonSqlServie.commonOperationDatabase(mm, "hs_agentEvaluateHouse", false) ;
			}
			//提交审核
			Map<String,Object> m = new HashMap<>();
			m.put("type", "agentfeedback");
			Map<String,Object> examineInfo = new HashMap<>();
			examineInfo.put("shhId", shhId);
			examineInfo.put("content", "经纪人对带看房源的反馈");
			m.put("examineInfo", examineInfo);
			m.put("agentfeedbackId", res);
			businessExamine(m);
			return res;
		}else {
			return 0l;
		}
		
		
	}
	@SystemServiceLog(operationType="删除经纪人反馈")
	@Override
	public boolean delAgentFeedback(int id) {
		boolean res = commonSqlServie.delData("hs_agentEvaluateHouse", "id", id+"", false);
		return res;
	}
	@SystemServiceLog(operationType="查询经纪人反馈")
	@Override
	public Page<Map<String, Object>> queryAgentFeedback(String conditions,int page,int limit) {
		PageHelper.startPage(page, limit);
		Map<String,Object> conditionsMap = JSON.parseObject(conditions);
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryAgentFeedback(conditionsMap);
		
		ExecutionResult.descFormat("", "查询经纪人反馈");
		return pageInfo;
		}
	
	@SystemServiceLog(operationType="新增房源")
	@Override
     public boolean addHousing(Map<String,Object> data,String formName) {

		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		if (data.get("houseUsesId")==null||"".equals(data.get("houseUsesId").toString())) {
			throw new BusinessException("房源用途不能为空");
		}
		long cid=Long.parseLong(data.get("communityId").toString());
		Map<String, Object> community=erHousingMapper.getAreaInfo(cid);			
		String code=community.get("areaCode").toString();
		/*Integer max=erHousingMapper.getmaxHouseCount(code);
		max++;			
		String count = String.format("%06d", max); 
		*//** 生成房源编号**//*
		String houseCode=code+count;*/
		String houseCode=redisGetIdServiceApi.getHouseId(code);
		long communityId=Long.parseLong(community.get("communityId").toString());
		boolean school=checkSchool(communityId);
		long schoolBiaozhi=0;
		if (school) {
			schoolBiaozhi=1;
		}
		int metroCount=erHousingMapper.queryIsMetroRoom(communityId);
		int isSubwayRoom=0;
		if (metroCount>0) {
			isSubwayRoom=1;
		}
		if (HouseUsesEnum.PARKING.getCode().equals(data.get("houseUsesId").toString())) {
			
			/** 添加房源基础表**/
			Map<String,Object> houseBase =new HashMap<>();
			houseBase.put("id", houseCode);
			String cityCode=code.substring(0, 4)+"00";
			houseBase.put("cityCode", cityCode);
			houseBase.put("areaCode", code);
			houseBase.put("operatorId", user.get("id"));
			//houseBase.put("operator", user.get("realName"));
			houseBase.put("permissionArea", user.get("deptId"));
			houseBase.put("areaName", community.get("areaName"));
			houseBase.put("isSchoolRoom", schoolBiaozhi);
			houseBase.put("isSubwayRoom", isSubwayRoom);
			houseBase.put("regionId", community.get("regionId"));
			houseBase.put("regionName", community.get("regionName"));
			houseBase.put("communityId", community.get("communityId"));
			houseBase.put("communityName", community.get("communityName"));	
			if (data.get("floorCount")!=null&&!"".equals(data.get("floorCount").toString())) {
				houseBase.put("floorCount", Long.parseLong(data.get("floorCount").toString()));
			}
			if (data.get("totalLayers")!=null&&!"".equals(data.get("totalLayers").toString())) {
				houseBase.put("totalLayers", Long.parseLong(data.get("totalLayers").toString()));
			}	
			houseBase.put("floorDesId", Long.parseLong(data.get("floorDesId").toString()));
			houseBase.put("floorDes", data.get("floorDes").toString());
			commonSqlServie.idCustomOperationDatabase(houseBase, "hs_housebaseinfo", false);
			data.put("houseId", houseCode);
			data.put("isPriority", 1);//优先显示
		}else {
			Long houseId=Long.parseLong(data.get("buildingHouseId").toString());
			Map<String, Object>base=erHousingMapper.getErHousingCode(houseId);
			if (base!=null&&base.get("id")!=null) {
				data.put("houseId", base.get("id"));
				data.put("isPriority", 0);//不优先显示
			}else {
			
				/** 添加房源基础表**/
				Map<String,Object> houseBase =new HashMap<>();
				houseBase.put("id", houseCode);
				String cityCode=code.substring(0, 4)+"00";
				houseBase.put("cityCode", cityCode);
				houseBase.put("areaCode", code);
				houseBase.put("operatorId", user.get("id"));
				//houseBase.put("operator", user.get("realName"));
				houseBase.put("permissionArea", user.get("deptId"));
				houseBase.put("areaName", community.get("areaName"));
				houseBase.put("isSchoolRoom", schoolBiaozhi);
				houseBase.put("isSubwayRoom", isSubwayRoom);
				houseBase.put("regionId", community.get("regionId"));
				houseBase.put("regionName", community.get("regionName"));
				houseBase.put("communityId", community.get("communityId"));
				houseBase.put("communityName", community.get("communityName"));
				houseBase.put("buildingsId", Long.parseLong(data.get("buildingsId").toString()));
				houseBase.put("buildingsName", data.get("buildingsName"));
				houseBase.put("bulidingUnitId", Long.parseLong(data.get("bulidingUnitId").toString()));
				houseBase.put("buildingsUnitName", data.get("buildingsUnitName"));
				houseBase.put("buildingHouseId", Long.parseLong(data.get("buildingHouseId").toString()));
				houseBase.put("buildingHouseName", data.get("buildingHouseName"));
				long hid=Long.parseLong(data.get("buildingHouseId").toString());
				//Map<String, Object> buildingInfo=erHousingMapper.getBuildingInfo(hid);
				houseBase.put("elevatorCount", Long.parseLong(data.get("elevatorCount").toString()));
				houseBase.put("householdCount", Long.parseLong(data.get("householdCount").toString()));
				houseBase.put("floorCount", Long.parseLong(data.get("floorCount").toString()));
				houseBase.put("totalLayers", Long.parseLong(data.get("totalLayers").toString()));
				houseBase.put("floorDesId", Long.parseLong(data.get("floorDesId").toString()));
				houseBase.put("floorDes", data.get("floorDes").toString());
				commonSqlServie.idCustomOperationDatabase(houseBase, "hs_housebaseinfo", false);
				data.put("houseId", houseCode);
				/** 修改最大房源数**/
				/*Map<String,Object> areaMap =new HashMap<>();
				areaMap.put("code", code);
				areaMap.put("maxHouseCount", max);
				commonSqlServie.commonOperationDatabase(areaMap, "sys_area", "code", false);*/
				data.put("isPriority", 1);//优先显示
			}	
		}
		
			/** 添加房源主表**/			
			data.put("companyId", companyId);
			data.put("companyName", companyName);
			data.put("operatorId", user.get("id"));
			data.put("maintainId", user.get("id"));
			data.put("maintainer", user.get("realName"));
			data.put("recordTime", new Date());
			data.put("permissionArea", user.get("deptId"));
			data.put("levelType", HouseLevelEnum.D.getCode());
			data.put("mainterDept", user.get("depName"));
			data.put("mainterDeptId", user.get("deptId"));
			data.put("mainterFllowTime", new Date());
			data.put("allFollowTime", new Date());
			data.put("openPlateDate", new Date());
			//售价转换成分
			if (data.get("sellingPrice")!=null&&!"".equals(data.get("sellingPrice").toString())) {
				data.put("sellingPrice", convertFen(data.get("sellingPrice").toString(),WANYUAN));
			}
			//最低售价转换成分
			if (data.get("minSellingPrice")!=null&&!"".equals(data.get("minSellingPrice").toString())) {
				data.put("minSellingPrice", convertFen(data.get("minSellingPrice").toString(),WANYUAN));
			}
			//租价转换成分
			if (data.get("rentPrice")!=null&&!"".equals(data.get("rentPrice").toString())) {
				data.put("rentPrice", convertFen(data.get("rentPrice").toString(),YUAN));
			}
			//最低租价转换成分
			if (data.get("minRentPrice")!=null&&!"".equals(data.get("minRentPrice").toString())) {
				data.put("minRentPrice", convertFen(data.get("minRentPrice").toString(),YUAN));
			}
			if (HouseStatusEnum.EFFECTIVE.getCode().equals(data.get("statusId"))) {
				data.put("examineStatus", ExamineStatusEnum.UNEXAMINED.getCode());
			}else {
				data.put("examineStatus", ExamineStatusEnum.EXAMINED.getCode());
			}			
			data.put("taobaoStatusId", TaoBaoStatusEnum.NOT_TAOBAO.getCode());
			data.put("taobaoStatus", TaoBaoStatusEnum.NOT_TAOBAO.getDesc());
			if (data.get("discStatusId")==null) {
				data.put("discStatusId", DiscStatusEnum.PUBLICDISC.getCode());
				data.put("discStatus", DiscStatusEnum.PUBLICDISC.getDesc());
			}
			boolean isSendBindCode=false;
			if (data.get("isSendBindCode")!=null) {
				if ("1".equals(data.get("isSendBindCode").toString())) {
					isSendBindCode=true;
				}	
				data.remove("isSendBindCode");
			}
			
			data.remove("buildingsId");
			data.remove("communityId");
			data.remove("buildingsName");
			data.remove("bulidingUnitId");
			data.remove("buildingsUnitName");
			data.remove("buildingHouseId");
			data.remove("buildingHouseName");
			data.remove("elevatorCount");
			data.remove("householdCount");
			data.remove("floorCount");
			data.remove("totalLayers");
			data.remove("floorDesId");
			data.remove("floorDes");
			
			Map<String,Object> houseOwnerRelate =new HashMap<>();
			houseOwnerRelate.put("companyId", companyId);
			houseOwnerRelate.put("companyName", companyName);
			houseOwnerRelate.put("phone", data.get("phone"));
			houseOwnerRelate.put("relateType", data.get("relateType"));
			houseOwnerRelate.put("relateName", data.get("relateName"));
			houseOwnerRelate.put("remark", data.get("relateName"));
			houseOwnerRelate.put("isMainFlag", 1);
			
			data.remove("phone");
			data.remove("relateType");
			data.remove("relateName");
			Map<String,Object> customData=(Map<String,Object>) data.get("customData");
			if (data.get("customData")!=null) {
				data.remove("customData");
			}
			//添加房源标签
			List<Map<String,Object>> houselabelList = null;
			if (data.get("houselabelList")!=null) {
				houselabelList=(List) data.get("houselabelList");
				data.remove("houselabelList");
			}
			long id=commonSqlServie.commonOperationDatabase(data, "hs_secondHandHouse", false);
			//新增价格变动
			Map<String, Object> priceMap=new HashMap<>();
			priceMap.put("shhId", id);
			priceMap.put("sellingPrice", data.get("sellingPrice"));
			priceMap.put("sellFloat", 0);
			priceMap.put("rentPrice", data.get("rentPrice"));
			priceMap.put("rentFloat", 0);
			saveUpdatePrice(priceMap);
			
			if(houselabelList!=null && houselabelList.size()>0) {
				//新增标签
				for(Map<String,Object> m : houselabelList) {
					m.put("type", 0);//房源为0，小区为1
					m.put("sourceId", id);
					m.put("permissionArea", UserThreadLocal.get().get("deptId"));
					m.put("operatorId", UserThreadLocal.get().get("id"));
				}
				commonSqlServie.batchCommonOperationDatabase(houselabelList, "sys_labeld", false);
			}
			if (customData!=null) {
				customData.put("keyId", id);
				customData.put("formName", formName);
				commonSqlServie.commonOperationDatabase(customData, "sys_custom_column_value", false);
			}
			/** 添加业主联系人**/
			houseOwnerRelate.put("shhId", id);
			commonSqlServie.commonOperationDatabase(houseOwnerRelate, "hs_houseOwnerRelate", false);
			/** 添加录入人、开盘人、维护人**/
			List<String> agentList=new ArrayList<>();

			String columns="companyId,companyName,shhId,agentId,agentName,operatorId,departmentId,departmentName,agentType,agentTypeName";
			StringBuilder value=new StringBuilder();
			value.append("'");
			value.append(companyId);
			value.append("','");
			value.append(companyName);
			value.append("',");
			value.append(id);
			value.append(",");
			value.append(user.get("id"));
			value.append(",'");
			value.append(user.get("realName"));
			value.append("',");
			value.append(user.get("id"));
			value.append(",'");
			value.append(user.get("deptId"));
			value.append("','");
			value.append(user.get("depName"));
			value.append("'");
			agentList.add(value.toString()+",'"+AgentTypeEnum.HOUSERECORDER.getCode()+"','"+AgentTypeEnum.HOUSERECORDER.getDesc()+"'");
			agentList.add(value.toString()+",'"+AgentTypeEnum.OPENDISCER.getCode()+"','"+AgentTypeEnum.OPENDISCER.getDesc()+"'");
			agentList.add(value.toString()+",'"+AgentTypeEnum.HOUSEMAINTAINER.getCode()+"','"+AgentTypeEnum.HOUSEMAINTAINER.getDesc()+"'");
			commonSqlServie.batchAdd(columns, "hs_houseRelateAgent", agentList, false);
			
			if (HouseStatusEnum.EFFECTIVE.getCode().equals(data.get("statusId"))) {
				/** 添加新增房源待审核记录**/
				Map<String,Object> examineMap =new HashMap<>();
				//examineMap.put("transactionTypeId", data.get("transactionTypeId"));
				examineMap.put("examineTypeId", BusinessExamineTypeEnum.ADD.getCode());
				examineMap.put("operatorId", user.get("id"));
				examineMap.put("applyerId", user.get("id"));
				examineMap.put("applyer", user.get("realName"));
				examineMap.put("applyTime", new Date());
				examineMap.put("shhId", id);

				examineMap.put("permissionArea", user.get("deptId"));
				examineMap.put("content", "新增状态为待确认");
				businessExamineServiceImpl.saveBusinessExamine(examineMap);
			}
			//生成并发送房源绑定码
			if (isSendBindCode) {
				redisGetIdServiceApi.getHouseBindCode(id+"",houseOwnerRelate.get("phone").toString());
			}
		//新增房源日志	
		/*String tradeTypeId=data.get("transactionTypeId").toString();	
		BusinessLogModel model=new BusinessLogModel();
		model.setBusinessTypeId(Integer.parseInt(LogBusinessTypeEnum.HOUSE.getCode())); 
		if (HouseTransactionEnum.SELL.getCode().equals(tradeTypeId)) {
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELL_HOUSE.getCode()));
		}else if (HouseTransactionEnum.LEASE.getCode().equals(tradeTypeId)) {
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.RENT_HOUSE.getCode()));
		}else {
			model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELLANDRENT_HOUSE.getCode()));
		}
		model.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.ADD_HOUSE.getCode()));
		model.setSourceId(id+"");
		model.setSourceCode(data.get("houseId").toString());
		HouseUsesEnum houseUsesEnum=HouseUsesEnum.parseOf(data.get("houseUsesId").toString());
		model.setLogContent("新增"+houseUsesEnum.getDesc()+"房源");
		businessLogService.houseLogAdd(model);*/
		ExecutionResult.descFormat(formName, "新增房源");
		return true;
	}
	/**
	 * 
	 * @Title: checkSchool   
	 * @Description: TODO(判断小区是否学区房)   
	 * @param: @param communityId 小区id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	private boolean checkSchool(long communityId) {
		Page<Map<String, Object>> page=commonSqlServie.querySingleTable("schoolId", "school_block_community", "communityId="+communityId, 1, 10);
		if (page!=null&&page.getTotal()>0) {
			return true;
		}
		return false;
	}
	@SystemServiceLog(operationType="查询二手房源表格信息")
	@Override
	public Page<Map<String, Object>> queryHousingTableDatas(String conditions, int page, int limit) {
		PageHelper.startPage(page, limit);
		
		HouseQueryEntity query=null;
		if (StringUtils.isEmpty(conditions)) {
			query=new HouseQueryEntity();
		}else {
			query=JSON.parseObject(conditions, HouseQueryEntity.class);
		}	
		dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> result=erHousingMapper.searchHouseList(query.toSql());
		ExecutionResult.descFormat(UserThreadLocal.get().get("id").toString(), "查询二手房源表格信息");
		return result;
	}
	@SystemServiceLog(operationType="查询二手房源表格隐藏列")
	@Override
	public Map<String, Object> queryTableHiddenField(String formName) {
		Map<String,Object> user=UserThreadLocal.get();
		 long userId=Long.parseLong(user.get("id").toString());
		Map<String, Object> map=formmapper.queryTableHiddenFields(userId, formName);
		if (map==null) {
			/*map=new HashMap<>();
			map.put("id", 0);
			 List<String> list=formmapper.queryTableFieldInfo(formName, "1");
			 map.put("columns", list);*/
		}else {
			String column= map.get("columns").toString();
			if (StringUtils.isNotEmpty(column)) {
				String[] array=column.split(",");
				map.put("columns", array);
			}			
		}
		ExecutionResult.descFormat(formName, "查询表格隐藏列");
		return map;
	}
	@SystemServiceLog(operationType="保存二手房源表格隐藏的列")
	@Override
	public boolean saveTableHiddenInfo(String formName, String values) {
		Map<String,Object> user=UserThreadLocal.get();
		 long userId=Long.parseLong(user.get("id").toString());
		 Map<String, Object> data=formmapper.queryTableHiddenFields(userId, formName);
		 if (data==null) {
			 Map<String,Object> map=new HashMap<>();
			map.put("userId", userId);
			map.put("formName", formName);
			map.put("columns", values);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_hidden_field", false);
		}else {
			Map<String,Object> map=new HashMap<>();
			map.put("id", data.get("id"));
			map.put("columns", values);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_hidden_field","id", false);
		}
		 ExecutionResult.descFormat(userId+"", "保存二手房源表格隐藏的列");
		return true;
	}
	
	@SystemServiceLog(operationType="查询座栋信息")
	@Override
	public Map<String, Object> queryBuildingInfo(Long buildingId,String discStatus) {
		Map<String, Object>result=new HashMap<>();
		result.put("type", 1);
		Map<String, Object> user=UserThreadLocal.get();
		Integer houseCount=erHousingMapper.queryCountHouseByHouseId(user.get("companyId").toString(), buildingId);
		if (houseCount>0) {
			throw new BusinessException("该房号已经存在，禁止添加");
		}
		//判断是否私盘
		if (DiscStatusEnum.PRIVATEDISC.getCode().equals(discStatus)) {
			//判断私盘数量是否超过限制
			Map<String, Object> map=decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_4.getCode(), user.get("cityCode").toString());
			Integer count=erHousingMapper.queryCountPrivateDisc(Long.parseLong(user.get("id").toString()));
			Integer limit=Integer.parseInt(map.get("val").toString());
			if (count!=null&&count>=limit) {
				throw new BusinessException("私盘数量已经达到上限，禁止添加私盘");
			}
		}
		
		Map<String, Object> buildingInfo=erHousingMapper.getBuildingInfo(buildingId);
		ExecutionResult.descFormat(buildingId.toString(), "查询座栋信息");
		return buildingInfo;
	}
	@SystemServiceLog(operationType="查询片区、小区")
	@Override
	public List<Map<String, Object>> queryCommunity() {
		Map<String,Object> user=UserThreadLocal.get();
		String cityCode=user.get("areaCode").toString();
		cityCode=cityCode.substring(0,4);
		ExecutionResult.descFormat(cityCode, "查询片区、小区");
		return erHousingMapper.searchCommunity(cityCode);
	}
	
	@SystemServiceLog(operationType="判断房源是否已添加")
	@Override
	public long getHousingCount(Long houseId) {
		Map<String, Object>base=erHousingMapper.getErHousingCode(houseId);
		ExecutionResult.descFormat(houseId+"", "判断房源是否已添加");
		if (base!=null&&base.get("id")!=null) {
			Map<String,Object> user=UserThreadLocal.get();
			String  companyId=user.get("companyId").toString();
			String condition="houseId='"+base.get("id").toString()+"' and companyId='"+companyId+"' and isValid=1";
			Page<Map<String, Object>> page=commonSqlServie.querySingleTable("id", "hs_secondhandhouse", condition, 1, 10);
			return page.getTotal();
		}
		return 0;
	}

	@SystemServiceLog(operationType="批量删除房源")
	@Override
	public int deleteHousing(String houseIds) {
		if (StringUtils.isNotEmpty(houseIds)) {
			commonSqlServie.delData("hs_secondhandhouse", "id", houseIds, false);
			ExecutionResult.descFormat(houseIds, "批量删除房源");
			String[] ids=houseIds.split(",");
			for (String id : ids) {
				if (StringUtils.isNotEmpty(id)) {
					updateIsPriorityStatus(Long.parseLong(id));
				}				
			}
			houseSynchron(houseIds);
		}	
		return 1;
	}
	@SystemServiceLog(operationType="修改房源")
	@Override
	public int updateHousing(Map<String,Object> data, String formName) {
		if (StringUtils.isNotEmpty(formName)&&data!=null&&data.get("id")!=null) {			
			Map<String,Object> customData=(Map<String, Object>) data.get("customData");
			if (data.get("customData")!=null) {
				customData.put("keyId", "'"+data.get("id")+"'");
				data.remove("customData");
			}
			if (data.get("sellingPrice")!=null&&!"".equals(data.get("sellingPrice").toString())) {
				data.put("sellingPrice", convertFen(data.get("sellingPrice").toString(),WANYUAN));
			}
			if (data.get("minSellingPrice")!=null&&!"".equals(data.get("minSellingPrice").toString())) {
				data.put("minSellingPrice", convertFen(data.get("minSellingPrice").toString(),WANYUAN));
			}
			if (data.get("rentPrice")!=null&&!"".equals(data.get("rentPrice").toString())) {
				data.put("rentPrice", convertFen(data.get("rentPrice").toString(),YUAN));
			}
			if (data.get("minRentPrice")!=null&&!"".equals(data.get("minRentPrice").toString())) {
				data.put("minRentPrice", convertFen(data.get("minRentPrice").toString(),YUAN));
			}
			//查询房源修改前的价格
			Map<String, Object> houseMap =erHousingMapper.getRowInfo("sellingPrice,rentPrice", "hs_secondHandHouse", "id", data.get("id"));
			int sellPriceOld=0;
			int rentPriceOld=0;
			int sellPrice=0;
			int rentPrice=0;
			if (houseMap.get("sellingPrice")!=null) {
				sellPriceOld=Integer.parseInt(houseMap.get("sellingPrice").toString());
			}
			if (houseMap.get("rentPrice")!=null) {
				rentPriceOld=Integer.parseInt(houseMap.get("rentPrice").toString());
			}
			if (data.get("sellingPrice")!=null&&!"".equals(data.get("sellingPrice").toString())) {
				sellPrice=Integer.parseInt(data.get("sellingPrice").toString());
			}
			if (data.get("rentPrice")!=null&&!"".equals(data.get("rentPrice").toString())) {
				rentPrice=Integer.parseInt(data.get("rentPrice").toString());
			}
			//房源价格变化
			int sellFloat = sellPrice-sellPriceOld;
			int rentFloat = rentPrice-rentPriceOld;
			Map<String, Object> priceMap=new HashMap<>();
			priceMap.put("shhId", data.get("id"));
			priceMap.put("sellingPrice", sellPrice);
			priceMap.put("sellFloat", sellFloat);
			priceMap.put("rentPrice", rentPrice);
			priceMap.put("rentFloat", rentFloat);
			saveUpdatePrice(priceMap);
			//判断房源变价标志
			if (sellFloat!=0||rentFloat!=0) {
				if ( sellFloat>0) {
					data.put("changePriceFlg", 1);
				}else if (sellFloat<0) {
					data.put("changePriceFlg", 2);
				}else if (rentFloat>0) {
					data.put("changePriceFlg", 1);
				}else {
					data.put("changePriceFlg", 2);
				}
			}
			//修改房源标签
			if (data.get("houselabelList")!=null) {
				List<Map<String,Object>> houselabelList=(List) data.get("houselabelList");
				//先删除原来标签
				commonSqlServie.delData("sys_labeld", "sourceId", data.get("id").toString(), false);
				//新增标签
				for(Map<String,Object> m : houselabelList) {
					m.put("type", 0);//房源为0，小区为1
					m.put("sourceId", data.get("id"));
					m.put("permissionArea", UserThreadLocal.get().get("deptId"));
					m.put("operatorId", UserThreadLocal.get().get("id"));
				}
				data.remove("houselabelList");
				if (houselabelList.size()>0) {
					commonSqlServie.batchCommonOperationDatabase(houselabelList, "sys_labeld", false);
				}				
			}
			//修改房源表信息
			commonSqlServie.commonOperationDatabase(data, "hs_secondHandHouse","id", false);
			//修改动态表单信息
			if (customData!=null) {
				if (formservice.queryCountCustomValue(data.get("id").toString(), formName)) {
					commonSqlServie.batchcommonUpdate(customData, "sys_custom_column_value", "keyId", customData.get("keyId").toString(), "formName='"+formName+"'");
				}else {
					Map<String,Object> param=new HashMap<>();
					param.putAll(customData);
					param.put("keyId", data.get("id").toString());
					param.put("formName", formName);
					commonSqlServie.commonOperationDatabase(param, "sys_custom_column_value", false);
				}
				
			}
			updateIsPriorityStatus(Long.parseLong(data.get("id").toString()));
			houseSynchron(data.get("id").toString());//需要同步
			
			ExecutionResult.descFormat(formName, "修改房源信息");
			return 1;
		}
		throw new BusinessException("修改房源，参数错误");
	}
	@SystemServiceLog(operationType="添加房源浏览记录")
	@Override
	public int addHousingSee(Long houseId) {
		Long userId=Long.parseLong(UserThreadLocal.get().get("id").toString());
		browseRecordService.addHouseBrowseRecord(houseId, userId);
		/*Map<String,Object> user=UserThreadLocal.get();
		String condition="shhId="+houseId+" and operatorId="+user.get("id");
		Page<Map<String, Object>> page=commonSqlServie.querySingleTable("id", "hs_todaySee", condition, 1, 10);
		if (page.getTotal()==0) {
			
			Map<String,Object> map=new HashMap<>();
			map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			map.put("shhId", houseId);
			map.put("operatorId", user.get("id"));
			commonSqlServie.idCustomOperationDatabase(map, "hs_todaySee", false);
			ExecutionResult.descFormat(houseId+"", "添加房源浏览记录");
		} */
		
		return 1;
	}
	/**
	 * 
	 * @Title: doExamine   
	 * @Description: TODO(房源业务审核处理接口)   
	 * @param: @param houseId 房源id
	 * @param: @param type 审核类型
	 * @param: @param result 审核结果
	 * @param: @param remark 房源待处理内容
	 * @param: @param recipient 接收人id
	 * @param: @param handleType 处理方式
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@SystemServiceLog(operationType="房源审核处理接口")
	@Override
	public boolean doExamine(long houseId, BusinessExamineTypeEnum type, ExamineStatusEnum result, String remark,
		Long recipient, BusinessExamineHandleTypeEnum handleType) {
		Long res = 0l;
		
		JSONObject jasonObject = JSONObject.parseObject(remark);
		Map<String,Object> map = (Map)jasonObject;
		
		//修改房源主表
		Map<String,Object> mm = new HashMap<>();
		mm.put("id", houseId);
		ExecutionResult.descFormat(houseId+"", "房源审核处理接口");
		//type.ADD新增房源审核，审核通过，修改房源待审核状态；未通过，修改房源状态和审核状态为已驳回
		if(type.equals(BusinessExamineTypeEnum.ADD)) {
			if(result.equals(ExamineStatusEnum.EXAMINED)) {
				mm.put("examineStatus", ExamineStatusEnum.EXAMINED.getCode());
				mm.put("levelType", HouseLevelEnum.C.getCode());
			}else if(result.equals(ExamineStatusEnum.REJECT)) {
				mm.put("statusId", HouseStatusEnum.REFUSED.getCode());
				mm.put("status", HouseStatusEnum.REFUSED.getDesc());
				mm.put("examineStatus", ExamineStatusEnum.REJECT.getCode());
				mm.put("openPlateDate", new Date());
			}
			res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
			updateIsPriorityStatus(houseId);
			houseSynchron(houseId+"");//需要同步
			return true;
		}
		
		if(result.equals(ExamineStatusEnum.EXAMINED)) {
			//type.UPDATESTATUS修改房源状态审核，审核通过，修改房源状态;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.UPDATESTATUS)) {
				mm.put("statusId",map.get("statusId"));
				mm.put("status", map.get("statusName"));
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				Map<String, Object> param=new HashMap<>();
				param.put("id", houseId);
				param.put("status", "02");
				param.put("approvalResult", "由于房源状态失效，系统自动拒绝审批");
				param.put("approvalTime", new Date());
				commonSqlServie.batchcommonUpdate(param, "oa_agreement", "id", houseId+"", "status='00'");
				updateIsPriorityStatus(houseId);
				houseSynchron(houseId+"");//需要同步
			}
			
			//type.SETPRIVATEDISC转为私盘审核，审核通过，修改房源状态，设置维护人;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.SETPRIVATEDISC)) {
				Long applyerId = Long.parseLong(map.get("applyerId").toString());
				Map<String,Object> dept = erHousingMapper.queryDeptName(applyerId);
				mm.put("maintainId", applyerId);
				mm.put("maintainer", dept.get("realName"));
				mm.put("mainterDeptId", dept.get("deptId"));
				mm.put("mainterDept", dept.get("deptName"));
				mm.put("permissionArea", dept.get("deptId"));
				//盘状态
				mm.put("discStatusId", DiscStatusEnum.PRIVATEDISC.getCode());
				mm.put("discStatus", DiscStatusEnum.PRIVATEDISC.getDesc());
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				//判断有没有原维护人
				Long mainterRecordid = 0l;
				if(!"".equals(map.get("mainterRecordid").toString()))
					mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
				//经纪人表修改维护人
				res = updateMyAgent(houseId,mainterRecordid,applyerId,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
			}
			
			//type.CLOSEDISC封盘审核，审核通过，修改房源封盘状态;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.CLOSEDISC)) {
				mm.put("closeDiscFlg",map.get("closeHouseTypeId"));
				mm.put("closePlanteEndDate",map.get("closeHouseEndTime"));
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
			}
			
			//type.OPENDISC开盘审核，审核通过，修改房源状态为有效，交易类型、及价格,改维护人;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.OPENDISC)) {
				Long applyerId = Long.parseLong(map.get("applyerId").toString());
				mm.put("statusId", HouseStatusEnum.EFFECTIVE.getCode());
				mm.put("status", HouseStatusEnum.EFFECTIVE.getDesc());
				mm.put("transactionTypeId",map.get("transactionTypeId"));
				mm.put("transactionType",map.get("transactionType"));
				if (map.get("sellingPrice")!=null&&!"".equals(map.get("sellingPrice").toString())) {
					mm.put("sellingPrice",convertFen(map.get("sellingPrice").toString(),WANYUAN));
				}
				if (map.get("rentPrice")!=null&&!"".equals(map.get("rentPrice").toString())) {
					mm.put("rentPrice",convertFen(map.get("rentPrice").toString(),YUAN));
				}
				mm.put("rentPriceTypeId",map.get("rentPriceTypeId"));
				mm.put("rentPriceTypeName",map.get("rentPriceTypeName"));
				Map<String,Object> dept = erHousingMapper.queryDeptName(applyerId);
				mm.put("maintainId", applyerId);
				mm.put("maintainer", dept.get("realName"));
				mm.put("mainterDeptId", dept.get("deptId"));
				mm.put("mainterDept", dept.get("deptName"));
				mm.put("permissionArea", dept.get("deptId"));
				mm.put("openPlateDate", new Date());
				mm.put("contractStatus", 0);
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				String statusid = map.get("statusId").toString();
				if(statusid.equals(HouseStatusEnum.MY_LEASE.getCode()) || statusid.equals(HouseStatusEnum.MY_SELL.getCode())
						|| statusid.equals(HouseStatusEnum.OTHER_LEASE.getCode())|| statusid.equals(HouseStatusEnum.OTHER_SELL.getCode())) {//成交的我租或我售
					//作废经纪人
					Map<String, Object> param=new HashMap<>();
					param.put("isValid", 0);
					commonSqlServie.batchcommonUpdate(param, "hs_houseRelateAgent", "shhId", houseId+"", null);
					//1、增加录入人	
					//经纪人表修改录入人
					res = updateMyAgent(houseId,0l,applyerId,AgentTypeEnum.HOUSERECORDER.getCode(),AgentTypeEnum.HOUSERECORDER.getDesc());
					//2、增加开盘人
					//经纪人表修改开盘人
					res = updateMyAgent(houseId,0l,applyerId,AgentTypeEnum.OPENDISCER.getCode(),AgentTypeEnum.OPENDISCER.getDesc());
					//3、增加维护人
					//经纪人表修改维护人
					res = updateMyAgent(houseId,0l,applyerId,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
					//作废图片、视频
					commonSqlServie.batchcommonUpdate(param, "hs_houseAttachment", "shhId", houseId+"", null);
					//作废钥匙管理记录
					commonSqlServie.batchcommonUpdate(param, "hs_key", "shhId", houseId+"", null);
					//作废改价历史
					commonSqlServie.batchcommonUpdate(param, "hs_changePriceRecord", "shhId", houseId+"", null);
					//作废跟进记录
					commonSqlServie.batchcommonUpdate(param, "hs_followRecord", "shhId", houseId+"", null);
					if (statusid.equals(HouseStatusEnum.MY_SELL.getCode())||statusid.equals(HouseStatusEnum.OTHER_SELL.getCode())) {
						//作废业主电话
						commonSqlServie.batchcommonUpdate(param, "hs_houseOwnerRelate", "shhId", houseId+"", null);
						//TODO:出售成交开盘，重新设置业主信息
					}
				}else {//其他开盘情况
					//1、修改开盘人
					//判断有没有原开盘人
					Long opendiscRecordid = 0l;
					if(!"".equals(map.get("opendiscRecordid").toString()))
						opendiscRecordid = Long.parseLong(map.get("mainterRecordid").toString());
					//经纪人表修改开盘人
					res = updateMyAgent(houseId,opendiscRecordid,applyerId,AgentTypeEnum.OPENDISCER.getCode(),AgentTypeEnum.OPENDISCER.getDesc());
					//2、修改维护人
					//判断有没有原维护人
					Long mainterRecordid = 0l;
					if(!"".equals(map.get("mainterRecordid").toString()))
						mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
					//经纪人表修改维护人
					res = updateMyAgent(houseId,mainterRecordid,applyerId,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
					
					//3、修改录入人
					//判断有没有原录入人
					Long entryRecordid = 0l;
					if(!"".equals(map.get("entryRecordid").toString()))
						entryRecordid = Long.parseLong(map.get("entryRecordid").toString());
					//经纪人表修改录入人
					res = updateMyAgent(houseId,entryRecordid,applyerId,AgentTypeEnum.HOUSERECORDER.getCode(),AgentTypeEnum.HOUSERECORDER.getDesc());
				}
				updateIsPriorityStatus(houseId);
				houseSynchron(houseId+"");//需要同步
			}
			
			//type.TAOBAO淘宝审核，审核通过，修改房源维护人、权限域，,淘宝状态改为非淘宝,经纪人表;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.TAOBAO)) {
				Long applyerId = Long.parseLong(map.get("applyerId").toString());
				Map<String,Object> dept = erHousingMapper.queryDeptName(applyerId);
				mm.put("maintainId", applyerId);
				mm.put("maintainer", dept.get("realName"));
				mm.put("mainterDeptId", dept.get("deptId"));
				mm.put("mainterDept", dept.get("deptName"));
				mm.put("permissionArea", dept.get("deptId"));
				mm.put("taobaoStatusId", TaoBaoStatusEnum.NOT_TAOBAO.getCode());
				mm.put("taobaoStatus", TaoBaoStatusEnum.NOT_TAOBAO.getDesc());
				res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				//判断有没有原维护人
				Long mainterRecordid = 0l;
				if(!"".equals(map.get("mainterRecordid").toString()))
					mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
				//经纪人表修改维护人
				res = updateMyAgent(houseId,mainterRecordid,applyerId,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
			}
			
			//type.ACCUSATION举报审核，未通过，不操作；审核通过，判断处理方式
			if(type.equals(BusinessExamineTypeEnum.ACCUSATION)) {
				//handleType.CANCEL撤单处理，修改房源状态为撤单
				if(handleType.equals(BusinessExamineHandleTypeEnum.CANCEL)) {
					mm.put("statusId", HouseStatusEnum.CANCEL.getCode());
					mm.put("status", HouseStatusEnum.CANCEL.getDesc());
					res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
					updateIsPriorityStatus(houseId);
					houseSynchron(houseId+"");//需要同步
				}
				//handleType.PUBICDISC放入公盘处理，修改房源盘状态为公盘，修改淘宝状态为非淘宝
				if(handleType.equals(BusinessExamineHandleTypeEnum.PUBICDISC)) {
					mm.put("discStatusId", DiscStatusEnum.PUBLICDISC.getCode());
					mm.put("discStatus", DiscStatusEnum.PUBLICDISC.getDesc());
					mm.put("taobaoStatusId", TaoBaoStatusEnum.NOT_TAOBAO.getCode());
					mm.put("taobaoStatus", TaoBaoStatusEnum.NOT_TAOBAO.getDesc());
					res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				}
				//handleType.TAOBAOPOOL放入淘宝池处理，修改房源淘宝状态为淘宝
				if(handleType.equals(BusinessExamineHandleTypeEnum.TAOBAOPOOL)) {
					mm.put("taobaoStatusId", TaoBaoStatusEnum.TAOBAO.getCode());
					mm.put("taobaoStatus", TaoBaoStatusEnum.TAOBAO.getDesc());
					res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
				}
				//handleType.TRANSFER移交处理，修改房源维护人、权限域，经纪人表
				if(handleType.equals(BusinessExamineHandleTypeEnum.TRANSFER)) {
					Map<String,Object> dept = erHousingMapper.queryDeptName(recipient);
					mm.put("maintainId", recipient);
					mm.put("maintainer", dept.get("realName"));
					mm.put("mainterDeptId", dept.get("deptId"));
					mm.put("mainterDept", dept.get("deptName"));
					mm.put("permissionArea", dept.get("deptId"));
					res = commonSqlServie.commonOperationDatabase(mm, "hs_secondHandHouse", "id", false);
					//判断有没有原维护人
					Long mainterRecordid = 0l;
					if(!"".equals(map.get("mainterRecordid").toString()))
						mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
					//经纪人表修改维护人
					res = updateMyAgent(houseId,mainterRecordid,recipient,AgentTypeEnum.HOUSEMAINTAINER.getCode(),AgentTypeEnum.HOUSEMAINTAINER.getDesc());
				}
			}
			//type.AGENTFEEDBACK经纪人评价房源审核，审核通过，修改审核状态;未通过，填写驳回原因
			if(type.equals(BusinessExamineTypeEnum.AGENTFEEDBACK)) {
				Map<String,Object> back = new HashMap<>();
				back.put("id", map.get("agentfeedbackId"));
				if(result.equals(ExamineStatusEnum.EXAMINED)) {
					back.put("examineStatus", ExamineStatusEnum.EXAMINED.getCode());
					res = commonSqlServie.commonOperationDatabase(back, "hs_agentEvaluateHouse","id", false) ;
				}
			}
			
		}
		return true;
		
	}
	@SystemServiceLog(operationType="批量下载房源图片")
	@Override
	public Map<String, Object> queryImagePath(Long houseId, String imageIds) {
		if (StringUtils.isNotEmpty(imageIds)) {
			ExecutionResult.descFormat(houseId+"", "批量下载房源图片");
			List<String> params=new ArrayList<>();
			String[] idArr=imageIds.split(",");
			for (String param : idArr) {
				if (StringUtils.isNotEmpty(param)) {
					params.add(param);
				}			
			}
			if (params.size()>0) {
				List<Map<String,Object>> list=erHousingMapper.queryFileByIds(params);
				Map<String, Object> map =erHousingMapper.getRowInfo("titles,houseId", "hs_secondHandHouse", "id", houseId);
				map.put("paths", list);
				return map;
			}
		}
		return null;
	}
	@SystemServiceLog(operationType="查询房源销控")
	@Override
	public List<Map<String, Object>> queryHouseListByUnitId(Long buildIngUnitId) {
		ExecutionResult.descFormat(buildIngUnitId+"", "查询房源销控");
		//dataAuthHelper.auth(MenuEnum.MENU_84.getCode(), UserThreadLocal.get().get("id").toString());
		List<Map<String, Object>> list=erHousingMapper.queryHouseListByUnitId(buildIngUnitId);
		return list;
	}
	@SystemServiceLog(operationType="查询房源私密数据")
	@Override
	public Object queryHousePrivateInfo(Long houseId, Integer type) {
		Map<String,Object> user=UserThreadLocal.get();
		String userId = user.get("id").toString();
		String cityCode = user.get("cityCode").toString();
		String userPhone = user.get("mobile").toString();
		ExecutionResult.descFormat(houseId+","+type, "查询房源私密数据");
		if (type!=null) {
			if (type.intValue()==1) {//查门牌号
				return lookRoomCount(userPhone,houseId,cityCode);
			}
			if (type.intValue()==2) {//查看业主电话
				List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,BusinessExamineTypeEnum.CLOSEDISC.getCode());
				if(Integer.parseInt(list.get(0).get("examineNum").toString())>0) {
					List<Map<String,Object>> listRes = new ArrayList<>();
					Map<String,Object> mm = new HashMap<>();
					mm.put("examineNum", 1);
					listRes.add(mm);
					return listRes;
				}else {
					//查看电话限制
					return limitCount(houseId,userId,userPhone,cityCode);
				}
				
				
			}
			if (type.intValue()==3) {//查底价
				Map<String, Object> map=erHousingMapper.queryHousePrice(houseId);
				return map;
			}
		}
		return null;
	}
	
	//查看电话限制
	public List<Map<String, Object>> limitCount(Long houseId,String userId,String userPhone,String cityCode) {
		int num = 0;
		Map<String,Object> _5mindecision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_34.getCode(),cityCode);
		if(_5mindecision!=null && _5mindecision.containsKey("val")) {
			int val = Integer.parseInt(_5mindecision.get("val").toString());
			String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.HS_5MINLOOKPHONE_COUNT);
			Map<String,Object> resMap = JSON.parseObject(res);
			if(resMap!=null && resMap.size()>0) {
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if (resMap.get("date")!=null&&!resMap.get("date").toString().equals("")) {
					String timestamp=resMap.get("date").toString();
					LocalDateTime dateTime=LocalDateTime.parse(timestamp);
					long timeDifference = dateTime.until(LocalDateTime.now(),ChronoUnit.MINUTES);
					if(timeDifference < 5) {//是否5分钟内
						if(hasNum>val) {//是否还能看
							throw new BusinessException("访问太频繁，请稍后再试！");
						}
					}
				}
			}
		}
		String res = redisServiceApi.getHash(NumberCount.getHouseKey(houseId), NumberCount.HS_LIMIT_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(userId, userId);
		if(resMap==null || resMap.size()<=0) {
			
			valueMap.put("num", 1);
			String limitValue = JSON.toJSONString(valueMap);
			
			return lookPhoneCount(userPhone,houseId,cityCode,limitValue);
		}else {
			//用户在免骚扰名单
			if(resMap.containsKey(userId)) {//同一用户查看，不限制
				List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
				return list;
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_8.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//是否还能查看
					num = hasNum+1;
					valueMap.put("num", num);
					String limitValue = JSON.toJSONString(valueMap);
					return lookPhoneCount(userPhone,houseId,cityCode,limitValue);
				}else {
					throw new BusinessException("该房源今日被查看次数超过限制，禁止查看！");
				}
				  
			}
		}
		return null;
	}
	
	//用户每天查看电话次数
	public List<Map<String, Object>> lookPhoneCount(String userPhone,Long houseId,String cityCode,String limitValue) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKPHONE_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(houseId+"", houseId);
		if(resMap==null || resMap.size()<=0) {
			valueMap.put("num", 1);
			String value = JSON.toJSONString(valueMap);
			return lookPhoneCountFor5min(userPhone,houseId,cityCode,limitValue,value);
		}else {
			if(resMap.containsKey(houseId+"")) {//查看同一房源，不限制
				List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
				return list;
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_9.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//是否还能查看
					num = hasNum+1;
					valueMap.put("num", num);
					String value = JSON.toJSONString(valueMap);
					return lookPhoneCountFor5min(userPhone,houseId,cityCode,limitValue,value);
				}else {
					throw new BusinessException("今日查看房源电话次数已超过限制，禁止查看房源电话！");
				}
				  
			}
		}
		return null;
	}
	
	//用户5分钟查看电话次数
	public List<Map<String, Object>> lookPhoneCountFor5min(String userPhone,Long houseId,String cityCode,String limitValue,String lookphonevalue) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.HS_5MINLOOKPHONE_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(houseId+"", houseId);
		if(resMap==null || resMap.size()<=0) {//第一次看
			valueMap.put("date", LocalDateTime.now().toString());
			//查看该房源数量加1(免骚扰决策)
			redisServiceApi.setHash(NumberCount.getHouseKey(houseId), NumberCount.HS_LIMIT_COUNT, limitValue, true);
			
			//用户查看加1(用户每天限看决策)
			redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKPHONE_COUNT, lookphonevalue, true);
			
			valueMap.put("num", 1);
			String value = JSON.toJSONString(valueMap);
			//5分钟查看加1(用户5分钟内限看决策)
			redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_5MINLOOKPHONE_COUNT, value, true);
			
			//返回电话列表
			List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
			return list;
		}else {
			if(resMap.containsKey(houseId+"")) {//查看同一房源，不限制
				List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
				return list;
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_34.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if (resMap.get("date")!=null&&!resMap.get("date").toString().equals("")) {
					String timestamp=resMap.get("date").toString();
					LocalDateTime dateTime=LocalDateTime.parse(timestamp);
					long timeDifference = dateTime.until(LocalDateTime.now(),ChronoUnit.MINUTES);
					if(timeDifference < 5) {//是否5分钟内
						if(hasNum<val) {//是否还能看
							//查看该房源数量加1(免骚扰决策)
							redisServiceApi.setHash(NumberCount.getHouseKey(houseId), NumberCount.HS_LIMIT_COUNT, limitValue, true);
							
							//用户查看加1(用户每天限看决策)
							redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKPHONE_COUNT, lookphonevalue, true);
							
							num = hasNum+1;
							resMap.put("num", num);
							resMap.put(houseId+"", houseId);
							String value = JSON.toJSONString(resMap);
							//5分钟查看加1(用户5分钟内限看决策)
							redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_5MINLOOKPHONE_COUNT, value, true);
							
							//返回电话列表
							List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
							return list;
						}else {
							throw new BusinessException("访问太频繁，请稍后再试！");
						}
					}else {//超过5分钟
						valueMap.put("date", LocalDateTime.now().toString());  //重新计时
						valueMap.put("num", 1);
						String value = JSON.toJSONString(valueMap);
						
						//查看该房源数量加1(免骚扰决策)
						redisServiceApi.setHash(NumberCount.getHouseKey(houseId), NumberCount.HS_LIMIT_COUNT, limitValue, true);
						
						//用户查看加1(用户每天限看决策)
						redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKPHONE_COUNT, lookphonevalue, true);
						
						//5分钟查看加1(用户5分钟内限看决策)
						redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_5MINLOOKPHONE_COUNT, value, true);
						
						//返回电话列表
						List<Map<String, Object>> list=erHousingMapper.queryHousePhone(houseId,"");
						return list;
					}
				}  
			}
		}
		throw new BusinessException("已超过今天的访问次数！");
	}
	
	
	//用户每天查看门牌号次数
	public Map<String, Object> lookRoomCount(String userPhone,Long houseId,String cityCode) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKROOM_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(houseId+"", houseId);
		if(resMap==null || resMap.size()<=0) {
			valueMap.put("num", 1);
			String value = JSON.toJSONString(valueMap);
			//用户查看加1
			redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKROOM_COUNT, value, true);
			Map<String, Object> map = erHousingMapper.queryHouseName(houseId);
			return map;
		}else {
			if(resMap.containsKey(houseId+"")) {//查看同一房源，不限制
				Map<String, Object> map=erHousingMapper.queryHouseName(houseId);
				return map;
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_12.getCode(),cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//是否还能查看
					num = hasNum+1;
					valueMap.put("num", num);
					String value = JSON.toJSONString(valueMap);
					//查看该房源数量加1
					redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.HS_LOOKROOM_COUNT, value, true);
					Map<String, Object> map=erHousingMapper.queryHouseName(houseId);
					return map;
				}else {
					return null;
				}
				  
			}
		}
		return null;
	}
	
	@SystemServiceLog(operationType="批量转移维护人")
	@Override
	public boolean houseTransfer(String houseIds, long userId) {
		Map<String,Object> dept = erHousingMapper.queryDeptName(userId);
		Map<String, Object> fields=new HashMap<>();
		fields.put("permissionArea", dept.get("deptId"));
		fields.put("mainterDept", dept.get("deptName"));
		fields.put("operatorId", userId);
		fields.put("maintainer", dept.get("realName"));
		fields.put("maintainId", userId);
		fields.put("mainterDeptId", dept.get("deptId"));

		commonSqlServie.batchcommonUpdate(fields, "hs_secondHandHouse", "id", houseIds, null);
		fields=new HashMap<>();
		fields.put("agentId", userId);
		fields.put("agentName", dept.get("realName"));
		fields.put("departmentId", dept.get("deptId"));
		fields.put("departmentName", dept.get("deptName"));
		String condition="agentType='"+AgentTypeEnum.HOUSEMAINTAINER.getCode()+"'";
		commonSqlServie.batchcommonUpdate(fields, "hs_houseRelateAgent", "shhId", houseIds, condition);
		ExecutionResult.descFormat(houseIds+":"+userId, "批量转移维护人");
		return true;
	}
	@SystemServiceLog(operationType="批量修改房源是否与外网同步")
	@Override
	public boolean houseSynchron(String houseIds, Integer state) {		
		if (StringUtils.isNotEmpty(houseIds)) {
			Map<String, Object> fields=new HashMap<>();
			fields.put("isSynchron", state);
			commonSqlServie.batchcommonUpdate(fields, "hs_secondHandHouse", "id", houseIds, null);
			ExecutionResult.descFormat(houseIds+":"+state, "批量修改房源是否与外网同步");
			String[] ids=houseIds.split(",");
			for (String id : ids) {
				if (StringUtils.isNotEmpty(id)) {
					updateIsPriorityStatus(Long.parseLong(id));
				}
			}
			houseSynchron(houseIds);
		}	
		return true;
	}
	
	/**
	 * @Description: 将房源数据分交易类型同步到客户端查询表中   
	 * @param houseIds 待同步的房源编号(多个用英文逗号隔开)
	 * @return: boolean
	 */
	@SystemServiceLog(operationType="同步房源信息到外网")
	@Override
	public boolean houseSynchron(String houseIds) {
		this.erHousingMapper.houseSynchron(houseIds);
		return true;
	}
	
	
	/**
	 * 
		 * 批量修改房源信息
		 * @Description: 批量修改房源信息
		 * @param houseIds 房源id逗号分隔
		 * @param fields 修改的列名及修改值
		 * @return 
		 * boolean
	 */
	@SystemServiceLog(operationType="批量修改房源信息")
	@Override
	public boolean batchUpdateHouseInfo(String houseIds, Map<String, Object> fields) {
		commonSqlServie.batchcommonUpdate(fields, "hs_secondHandHouse", "id", houseIds, null);
		ExecutionResult.descFormat(houseIds, "批量修改房源信息");
		return true;
	}

	@SystemServiceLog(operationType="搜索学校表格数据")
	@Override
	public Page<Map<String, Object>> querySchoolTable(String name, int page, int limit) {
		Map<String,Object> user=UserThreadLocal.get();
		String cityCode=user.get("areaCode").toString();
		cityCode=cityCode.substring(0,4);
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = erHousingMapper.querySchoolTable(cityCode,name);
		ExecutionResult.descFormat(name, "搜索学校表格数据");
		return pageInfo;
	}
	/**
	 * 
		 * 将万元或元转换成分
		 * @Description: 将万元或元转换成分
		 * @param value 待转换的值
		 * @param unit 万元或元换算单位
		 * @return  整数
		 * Integer
	 */
	private Integer convertFen(String value,Integer unit) {
		Double d=Double.parseDouble(value);
		d=d*unit;
		d=Math.floor(d);
		return d.intValue();
	}
	@SystemServiceLog(operationType="房源匹配客源")
	@Override
	public Page<Map<String, Object>> queryMatchingCustomer(String conditions, int page, int limit) {
		Map<String, Object> param=null;
		PageHelper.startPage(page, limit);		
		if (StringUtils.isNotEmpty(conditions)) {
			param=JSON.parseObject(conditions, Map.class);
		}
		if (param.get("transactionTypeId")!=null) {
			if ("1".equals(param.get("transactionTypeId").toString())) {
				dataAuthHelper.auth(MenuEnum.MENU_96.getCode(), UserThreadLocal.get().get("id").toString());
			}
			if ("2".equals(param.get("transactionTypeId").toString())) {
				dataAuthHelper.auth(MenuEnum.MENU_97.getCode(), UserThreadLocal.get().get("id").toString());
			}
		}
		ExecutionResult.descFormat("", "房源匹配客源");
		return erHousingMapper.matchingCustomer(param);
	}
	@SystemServiceLog(operationType="房源成交")
	@Override
	public boolean dealHousing(String dataJson, String commissionsJson,String crossJson) {
		if (StringUtils.isNotEmpty(dataJson)) {
//			Map user = UserThreadLocal.get();
			Map<String,Object> data = JSON.parseObject(dataJson, Map.class);
			String dealType = data.get("dealType").toString();//成交类型
			Long houseId=Long.parseLong(data.get("shhid").toString());
			Map<String,Object> houseInfo = erHousingMapper.queryHouseDealInfoByHouseId(houseId);
			String tradeTypeId=houseInfo.get("transactionTypeId").toString();
			houseInfo.remove("transactionTypeId");
			data.putAll(houseInfo);
			String signPersonName=data.get("operator").toString();
			String signDeptName=data.get("deptName").toString();

			List<Map<String, Object>> commList=new ArrayList<>();
			if (StringUtils.isNotEmpty(commissionsJson)) {
				List<Object> commissions = JSON.parseArray(commissionsJson);
				commList=new ArrayList<>();
				//补充佣金城市信息
				for (Object item : commissions) {
					Map<String, Object> param= (Map<String, Object>) item;
					Double dprice=Double.parseDouble(param.get("price").toString())*YUAN;
					Integer price=dprice.intValue();
					param.put("companyId", data.get("companyId"));
					param.put("companyName", data.get("companyName"));
					param.put("price", price);
					param.put("permissionArea", data.get("permissionArea"));
					param.put("operatorId", data.get("operatorId"));
					//param.put("operator", data.get("operator"));
					param.put("status", CommissionStatusEnum.PLAN.getCode());
					commList.add(param);
				}
			}
//			data.put("permissionArea", data.get("deptId"));
//			data.put("operatorId", data.get("id"));
						
			String demandId = data.get("demandId").toString();
			DealTypeEnum dealTypeEnum=DealTypeEnum.parseOf(dealType);
			HouseStatusEnum dealHouseStatus=HouseStatusEnum.MY_SELL;
			CustomerStatusEnum dealCoutomerStatus=CustomerStatusEnum.MY_SELL;
			Double cPrice=Double.parseDouble(data.get("commissionPrice").toString())*YUAN;
			data.put("commissionPrice", cPrice.intValue());
			Double aPrice=Double.parseDouble(data.get("actualCommission").toString())*YUAN;
			data.put("actualCommission", aPrice.intValue());
			Double price = Double.parseDouble(data.get("price").toString());
			data.put("price",new Double(price*WANYUAN).intValue());
			
			//判断成交类型是否为我租
			if (dealTypeEnum.equals(DealTypeEnum.LEASEDEAL)) {
				dealHouseStatus=HouseStatusEnum.MY_LEASE;
				dealCoutomerStatus=CustomerStatusEnum.MY_LEASE;
				data.put("price", new Double(price*YUAN).intValue());
			}
			
			//跨店操作
			if(!StringUtils.isEmpty(crossJson)) {
				Map<String,Object> deal_B = new HashMap<>();
				deal_B.putAll(data);
				JSONObject cross = JSONObject.parseObject(crossJson);
				String houseCompanyId = cross.getString("houseCompanyId");//房源公司编号
				String houseCompanyName = cross.getString("houseCompanyName");//房源公司名称
				String custCompanyId = cross.getString("custCompanyId");//客源公司编号
				String custCompanyName = cross.getString("custCompanyName");//客源公司名称
				Integer houseRate = cross.getInteger("houseRate");//房源公司佣金占比
				Integer custRate = cross.getInteger("custRate");//客源公司佣金占比
				String houseMaintainDeptId = cross.getString("houseMaintainDeptId");//房源维护人所属部门
				String houseMaintainDeptName = cross.getString("houseMaintainDeptName");//房源维护人所属部门
				String custMaintainDeptId = cross.getString("custMaintainDeptId");//客源维护人所属部门
				String custMaintainDeptName = cross.getString("custMaintainDeptName");//客源维护人所属部门
				String houseMaintainId = cross.getString("houseMaintainId");//房源维护人
				String houseMaintainer = cross.getString("houseMaintainName");//房源维护人
				String custMaintainId = cross.getString("custMaintainId");//客源维护人
				String custMaintainer = cross.getString("custMaintainName");//客源维护人
				
				//新增房源成交日志					
				BusinessLogModel model=new BusinessLogModel();
				model.setBusinessTypeId(Integer.parseInt(LogBusinessTypeEnum.HOUSE.getCode())); 
				if (HouseTransactionEnum.SELL.getCode().equals(tradeTypeId)) {
					model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELL_HOUSE.getCode()));
				}else if (HouseTransactionEnum.LEASE.getCode().equals(tradeTypeId)) {
					model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.RENT_HOUSE.getCode()));
				}else {
					model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELLANDRENT_HOUSE.getCode()));
				}
				model.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.DEAL.getCode()));
				model.setSourceId(houseId.toString());
				model.setSourceCode(data.get("houseId").toString());
				
				
				
				if(StringUtils.isEmpty(houseCompanyId)) 
					throw new BusinessException("房源所属公司不允许为空");
				if(StringUtils.isEmpty(custCompanyId))
					throw new BusinessException("客源所属公司不允许为空");
				if(houseRate==null || houseRate<=0)
					throw new BusinessException("房源所属公司占比不允许为空或者小于零");
				if(custRate == null || custRate<=0)
					throw new BusinessException("客源所属公司占比不允许为空或者小于零");
				if((houseRate+custRate)!=100)
					throw new BusinessException("跨店成交的佣金比例之和不为100%");
				if(StringUtils.isEmpty(houseMaintainDeptId))
					throw new BusinessException("房源维护人所属部门不允许为空");
				if(StringUtils.isEmpty(custMaintainDeptId))
					throw new BusinessException("客源维护人所属部门不允许为空");
				if(StringUtils.isEmpty(houseMaintainId))
					throw new BusinessException("房源维护人不允许为空");
				if(StringUtils.isEmpty(custMaintainId))
					throw new BusinessException("客源维护人不允许为空");
				//应收佣金
				Double cross_commission = Double.parseDouble(data.get("commissionPrice").toString())*YUAN;
				//实应收佣金
				Double cross_actualCommission = Double.parseDouble(data.get("actualCommission").toString())*YUAN;
				if(data.get("companyId").toString().equals(houseCompanyId)) {//当前操作人为房源所属公司
					data.put("commissionPrice", new Double(cross_commission*houseRate/100).intValue());
					data.put("actualCommission", new Double(cross_actualCommission*houseRate/100).intValue());
					deal_B.put("commissionPrice", new Double(cross_commission*custRate/100).intValue());
					deal_B.put("actualCommission", new Double(cross_actualCommission*custRate/100).intValue());
					deal_B.put("permissionArea", custMaintainDeptId);
					deal_B.put("deptName", custMaintainDeptName);
					deal_B.put("operatorId", custMaintainId);
					deal_B.put("operator", custMaintainer);
					deal_B.put("companyId", custCompanyId);
					deal_B.put("companyName", custCompanyName);
				}else if(data.get("companyId").toString().equals(custCompanyId)) {//当前操作人为客源所属公司
					data.put("commissionPrice", new Double(cross_commission*custRate/100).intValue());
					data.put("actualCommission", new Double(cross_actualCommission*custRate/100).intValue());
					
					deal_B.put("permissionArea", houseMaintainDeptId);
					deal_B.put("deptName", houseMaintainDeptName);
					deal_B.put("operatorId", houseMaintainId);
					deal_B.put("operator", houseMaintainer);
					deal_B.put("companyId", houseCompanyId);
					deal_B.put("companyName", houseCompanyName);				
					deal_B.put("commissionPrice", new Double(cross_commission*houseRate/100).intValue());
					deal_B.put("actualCommission", new Double(cross_actualCommission*houseRate/100).intValue());

				}else
					throw new BusinessException("当前成交人既不属于房源公司，也不属于客源公司，不允许交易");
				
				//调用成交接口增加成交信息
				dealServiceApi.saveDealInfo(deal_B, null);
				model.setOperator(deal_B.get("operator").toString());
				model.setOperatorId(Integer.parseInt(deal_B.get("operatorId").toString()));
				model.setCityCode(data.get("cityCode").toString());
				model.setCompanyId(deal_B.get("companyId").toString());
				model.setCompanyName(deal_B.get("companyName").toString());
				model.setLogContent("二手房"+dealTypeEnum.getDesc());
				model.setDepartmentId(deal_B.get("permissionArea").toString());
				model.setDepartmentName(deal_B.get("deptName").toString());
				businessLogService.houseLogAdd(model);
			}
			
			//调用成交接口增加成交信息
			dealServiceApi.saveDealInfo(data, commList);
			//新增房源成交日志					
			BusinessLogModel model=new BusinessLogModel();
			model.setBusinessTypeId(Integer.parseInt(LogBusinessTypeEnum.HOUSE.getCode())); 
			if (HouseTransactionEnum.SELL.getCode().equals(tradeTypeId)) {
				model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELL_HOUSE.getCode()));
			}else if (HouseTransactionEnum.LEASE.getCode().equals(tradeTypeId)) {
				model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.RENT_HOUSE.getCode()));
			}else {
				model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELLANDRENT_HOUSE.getCode()));
			}
			model.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.DEAL.getCode()));
			model.setOperator(data.get("operator").toString());
			model.setOperatorId(Integer.parseInt(data.get("operatorId").toString()));
			model.setSourceId(houseId.toString());
			model.setSourceCode(data.get("houseId").toString());
			model.setDepartmentId(data.get("permissionArea").toString());
			model.setDepartmentName(data.get("deptName").toString());
			model.setCityCode(data.get("cityCode").toString());
			model.setCompanyId(data.get("companyId").toString());
			model.setCompanyName(data.get("companyName").toString());
			model.setLogContent("二手房"+dealTypeEnum.getDesc());
			businessLogService.houseLogAdd(model);
			
			Map<String,Object> updateParam=new HashMap<>();
			updateParam.put("id", houseId);
			updateParam.put("statusId", dealHouseStatus.getCode());
			updateParam.put("status", dealHouseStatus.getDesc());
			//修改房源成交状态
			commonSqlServie.commonOperationDatabase(updateParam, "hs_secondHandHouse", "id", false);
			updateParam.put("id", demandId);
			updateParam.put("statusId", dealCoutomerStatus.getCode());
			updateParam.put("status", dealCoutomerStatus.getDesc());
			//修改客源成交状态
			commonSqlServie.commonOperationDatabase(updateParam, "demand_customerBusinessInfo", "id", false);
			ExecutionResult.descFormat(houseId+","+demandId, "房源成交");
		}
		return true;
	}
	@SystemServiceLog(operationType="查询采集房源列表")
	@Override
	public Page<Map<String, Object>> queryCrawlerList(String condition, int page, int limit) {
		PageHelper.startPage(page, limit);
		Map<String, Object> param=JSON.parseObject(condition, Map.class);
		Page<Map<String, Object>> pageInfo = erHousingMapper.queryCrawlerList(param);
		ExecutionResult.descFormat(page+"", "查询采集房源列表");
		return pageInfo;
	}
	@SystemServiceLog(operationType="判断电话是否黑名单")
	@Override
	public List<Map<String, Object>> queryHouseByPhone(String phone, Long communityId) {
		Map<String, Object> result=new HashMap<>();
		result.put("type", 0);
		ExecutionResult.descFormat(phone, "判断电话是否黑名单");
		if (StringUtils.isNotEmpty(phone)) {
			boolean idBlack=erHousingServiceApi.queryCountPhoneBlack(phone);
			if (idBlack) {
				throw new BusinessException("业主电话已加入电话黑名单，禁止添加");
			}
			Map<String, Object> map=decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_5.getCode(), UserThreadLocal.get().get("cityCode").toString());
			if (map!=null&&map.get("val")!=null&&"1".equals(map.get("val").toString())) {
				List<Map<String, Object>> list=erHousingMapper.queryHouseByphone(phone, communityId);
				return list;
			}
			return new ArrayList<Map<String, Object>>();
		}					
		throw new BusinessException(ErrorCodeEnum.UNKNOWN_ERROR);
	}
	@SystemServiceLog(operationType="查询销控")
	@Override
	public Map<String, Object> queryHouseSalesStatusByUnit(Long buildingUnitId,String companyId) {
		Map<String, Object> result=erHousingMapper.queryBulidingunitInfo(buildingUnitId);
		if (result!=null&&result.size()>0) {
			//String companyId=UserThreadLocal.get().get("companyId").toString();
			List<Map<String, Object>> data=erHousingMapper.queryHouseSalesStatus(companyId, buildingUnitId);
			Map<String, Object> houses=new HashMap<>();
			for (Map<String, Object> map : data) {
				houses.put(map.get("buildingHouseName").toString(), map);
			}
			result.put("houseList", houses);
		}
		ExecutionResult.descFormat(buildingUnitId.toString(), "查询销控");
		return result;
	}
	@SystemServiceLog(operationType="生成房源绑定码")
	@Override
	public boolean sendBindCode(Long houseId) {
		List<String> phones=erHousingMapper.queryHouseOwerPhone(houseId);
		if (phones!=null&&phones.size()>0) {
			String phone=phones.get(0);
			String code=redisGetIdServiceApi.getHouseBindCode(houseId.toString(),phone);
			System.out.println("************绑定码*************"+code);
			ExecutionResult.descFormat(houseId.toString(), "生成房源绑定码");
			if (StringUtils.isNotEmpty(code)) {
				return true;
			}
			throw new BusinessException("房源绑定码生成频率过快，请稍后再试!");
		}
		throw new BusinessException("房源业主电话为空，请填写业主电话后再试!");
	}
	
	@SystemServiceLog(operationType="查询标签")
	@Override
	public List<Map<String,Object>> queryLabel(){
		String type = LabelTypeEnum.HS.getCode();
		List<Map<String,Object>> res = erHousingMapper.queryLabel(type);
		return res;
	}
	@SystemServiceLog(operationType="查询楼栋")
	@Override
	public List<Map<String,Object>> queryBuild(Long communityId){
		List<Map<String,Object>> res = erHousingMapper.queryBuild(communityId);
		return res;
	}
	@SystemServiceLog(operationType="查询单元")
	@Override
	public List<Map<String,Object>> queryUnit(Long buildId){
		List<Map<String,Object>> res = erHousingMapper.queryUnit(buildId);
		return res;
	}

	@SystemServiceLog(operationType="设置房源的外网优先显示状态")
	@Override
	public void updateIsPriorityStatus(Long houseId) {
		String houseCode=erHousingMapper.queryHouseCodeById(houseId);
		List<Map<String, Object>> list=erHousingMapper.queryShowHouseListByHouseCode(houseCode);
		Map<String, Object> param=new HashMap<>();
		param.put("isPriority", 0);
		//判断是否有外网能显示的房源
		if (list!=null&&list.size()>0) {
			for (Map<String, Object> map : list) {
				//如果外网能显示的房源有优先显示标记的，不做任何处理
				if (map.get("isPriority")!=null&&map.get("isPriority").toString().equals("1")) {
					return;
				}
			}
			//将优先显示标记全部改为否
			commonSqlServie.batchcommonUpdate(param, "hs_secondhandhouse", "houseId", houseCode, null);
			Map<String,Object> mapParam=new HashMap<>();
			mapParam.put("id", list.get(0).get("id"));
			mapParam.put("isPriority", 1);
			//将开盘时间最早的房源设置为优先显示
			commonSqlServie.commonOperationDatabase(mapParam, "hs_secondhandhouse", "id", false);
		}else {
			//将优先显示标记全部改为否
			commonSqlServie.batchcommonUpdate(param, "hs_secondhandhouse", "houseId", houseCode, null);
		}
		ExecutionResult.descFormat(houseCode, "设置房源的外网优先显示状态");
	}
	
	/**
	 * 
		 * 录入房源是否默认私盘
		 * @Description: 录入房源是否默认私盘
		 * @return 
		 * List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> queryHouseDiscStatus(){
		Map<String, Object> map=decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_6.getCode(), UserThreadLocal.get().get("cityCode").toString());
		List<Map<String, Object>> list=new ArrayList<>();
		Map<String, Object> privateDisc=new HashMap<>();
		privateDisc.put("value", 1);
		privateDisc.put("label", "私盘");
		list.add(privateDisc);
		if (map!=null&&map.get("val")!=null&&"1".equals(map.get("val").toString())) {
			return list;
		}
		Map<String, Object> publicDisc=new HashMap<>();
		publicDisc.put("value", 2);
		publicDisc.put("label", "公盘");
		list.add(publicDisc);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryHouseByTime(Integer limit) {
		dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		List<Map<String, Object>> result=erHousingMapper.queryHouseByTime(limit);
		return result;
	}

	@Override
	public Integer queryUnFollowCountByUser() {
		Integer userId=Integer.parseInt(UserThreadLocal.get().get("id").toString());
		Integer result=erHousingMapper.queryUnFollowCountByUser(userId);
		return result;
	}

	@Override
	public Map<String, Object> queryMonthtaskByUserId() {
		Integer userId=Integer.parseInt(UserThreadLocal.get().get("id").toString());
		int sellHouseAdd=0;
		int sellHouseShowed=0;
		int sellCustomerAdd=0;
		int dealMoney=0;
		Map<String, Object> task=erHousingMapper.queryTaskCurrMonth(userId);
		if (task!=null&&task.get("sellHouseAdd")!=null) {
			sellHouseAdd=Integer.parseInt(task.get("sellHouseAdd").toString());
		}
		if (task!=null&&task.get("sellHouseShowed")!=null) {
			sellHouseShowed=Integer.parseInt(task.get("sellHouseShowed").toString());
		}
		if (task!=null&&task.get("sellCustomerAdd")!=null) {
			sellCustomerAdd=Integer.parseInt(task.get("sellCustomerAdd").toString());
		}
		if (task!=null&&task.get("dealMoney")!=null) {
			dealMoney=Integer.parseInt(task.get("dealMoney").toString());
		}
		int doSellHouseAdd=0;
		int doSellHouseShowed=0;
		int doSellCustomerAdd=0;
		List<Map<String, Object>> doTasks=erHousingMapper.queryDoTaskMonth(userId);
		for (Map<String, Object> map : doTasks) {
			if (map.get("total")==null) {
				map.put("total", 0);
			}
			if (ReportAnalysisTypeEnum.SELLHOUSE_ADD.getCode().equals(map.get("operatTypeId").toString())) {
				doSellHouseAdd=Integer.parseInt(map.get("total").toString());
			}
			if (ReportAnalysisTypeEnum.SELLCUSTOMER_ADD.getCode().equals(map.get("operatTypeId").toString())) {
				doSellCustomerAdd=Integer.parseInt(map.get("total").toString());
			}
			if (ReportAnalysisTypeEnum.SELLCUSTOMER_SHOWED.getCode().equals(map.get("operatTypeId").toString())) {
				doSellHouseShowed=Integer.parseInt(map.get("total").toString());
			}
		}
		Integer doDealMoney=erHousingMapper.queryMonthDealMonth(userId);
		if (doDealMoney==null) {
			doDealMoney=0;
		}
		Map<String, Object> result=new HashMap<>();
		result.put("sellHouseAdd", sellHouseAdd);
		result.put("sellCustomerAdd", sellCustomerAdd);
		result.put("sellHouseShowed", sellHouseShowed);
		result.put("dealMoney", dealMoney);
		result.put("doSellHouseAdd", doSellHouseAdd);
		result.put("doSellHouseShowed", doSellHouseShowed);
		result.put("doSellCustomerAdd", doSellCustomerAdd);
		result.put("doDealMoney", doDealMoney);
		return result;
	}

	@Override
	public Integer queryCommissionPrice(Long houseId, String contractType, Integer dealPrice) {
		if (dealPrice==null) {
			dealPrice=0;
		}
		Map<String, Object> useMap=erHousingMapper.queryHouseUses(houseId);
		String companyId=useMap.get("companyId").toString();
		String houseType=getHouseUses(useMap.get("houseUsesId").toString());
		String achievementType="";
		if("01".equals(contractType)) {
			//出售
			if(DividiintoDealTypeEnum.DEAL_RESIDENCE.getCode().equals(houseType)) {
				achievementType=PerformanceTypeEnum.HOUSESALEDEAL.getCode();//住宅出售
			}else {
				achievementType=PerformanceTypeEnum.BUSINESSSALEDEAL.getCode();//商业出售
			}
		}else if("02".equals(contractType)){
			//出租
			if(DividiintoDealTypeEnum.DEAL_RESIDENCE.getCode().equals(houseType)) {
				achievementType=PerformanceTypeEnum.HOUSELEASEDEAL.getCode();//住宅出租
			}else {
				achievementType=PerformanceTypeEnum.BUSINESSLEASEDEAL.getCode();//商业出租
			}
		}
		Map<String,Object> commisionRate = erHousingMapper.queryAchievementRate(companyId,achievementType);
		if (commisionRate!=null) {
			if (commisionRate.get("commisionRate")!=null&&!"".equals(commisionRate.get("commisionRate").toString())) {
				Integer rate=Integer.parseInt(commisionRate.get("commisionRate").toString());
				Integer commision=dealPrice*rate/100;
				return commision;
			}else if (commisionRate.get("fiexdBalance")!=null&&!"".equals(commisionRate.get("fiexdBalance").toString())) {
				Integer commision=Integer.parseInt(commisionRate.get("fiexdBalance").toString());
				return commision;
			}
		}
		return 0;
	}
	
}
