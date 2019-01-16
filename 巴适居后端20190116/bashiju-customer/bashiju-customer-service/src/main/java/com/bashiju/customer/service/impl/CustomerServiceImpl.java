package com.bashiju.customer.service.impl;

import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import com.bashiju.api.DecisionConfigurateServiceApi;
import com.bashiju.api.DeptServiceApi;
import com.bashiju.api.ErHousingServiceApi;
import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.mapper.CustomerMapper;
import com.bashiju.customer.pojo.BusinessExamineTypeEnum;
import com.bashiju.customer.pojo.CustomerFindHouseEntity;
import com.bashiju.customer.pojo.CustomerQueryEntity;
import com.bashiju.customer.pojo.RepeatTypeEbum;
import com.bashiju.customer.service.BusinessExamineService;
import com.bashiju.customer.service.CustomerService;
import com.bashiju.customer.service.CustomerStatusEnum;
import com.bashiju.customer.service.FormService;
import com.bashiju.enums.AgentTypeEnum;
import com.bashiju.enums.CustomerTransactionEnum;
import com.bashiju.enums.DecisionConfigurateEnum;
import com.bashiju.enums.DepositManageEnum;
import com.bashiju.enums.DiscStatusEnum;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.FtpSourceTypeEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.enums.LogBusinessSourceTypeEnum;
import com.bashiju.enums.LogBusinessTypeEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.NumberCount;
import com.bashiju.enums.ResCustLevelConditionEnum;
import com.bashiju.enums.TaoBaoStatusEnum;
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

@SystemServiceLog(sourceType="客源") 
@Service
public class CustomerServiceImpl implements CustomerService{
	/**万元转分 **/
	private static final int WANYUAN=1000000;
	/**元转分 **/
	private static final int YUAN=100;
	@Autowired
	private CommonSqlServie commonSqlServie;
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	DataAuthHelper dataAuthHelper;
	@Autowired
	FormService formService;
	@Autowired
	BusinessExamineService businessExamineService;
	@Autowired
	DecisionConfigurateServiceApi decisionConfigurateServiceApi;

	@Autowired
	RedisServiceApi redisServiceApi;
	@Autowired
	RedisGetIdServiceApi redisGetIdServiceApi;

	@Autowired 
	ErHousingServiceApi erHousingServiceApi;
	@Autowired
	DeptServiceApi deptServiceApi;
	@Autowired
	BusinessLogService businessLogService;
	@Autowired
	BrowseRecordService browseRecordService;
	
	/**
	 * 基础服务接口
	 */
	@Autowired
	private RedisGetIdServiceApi mRedisGetIdServiceApi;

	@SystemServiceLog(operationType="查询客源详细信息")
	@Override
	public Map<String,Object> queryCustomerInfo(String demandId,String formName,String type,String transactionTypeId){
		Map<String,Object> res = new HashMap<>();
		Map<String,Object> user=UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		if("4".equals(transactionTypeId)) {//求购
			dataAuthHelper.auth(MenuEnum.MENU_96.getCode(),UserThreadLocal.get().get("id").toString());
		}else {//求租
			dataAuthHelper.auth(MenuEnum.MENU_97.getCode(),UserThreadLocal.get().get("id").toString());
		}
		
		//客源、需求信息
		Map<String,Object> customerInfo = customerMapper.queryCustomerInfo(demandId,userid);
		if(customerInfo!=null && customerInfo.size()>0)
			res.put("customerInfo", customerInfo);//返回客源和需求基本信息
		
		//自定义信息
		List fields = formService.queryCustomField(formName);
		if (fields!=null&&fields.size()>0) {
			Map<String,Object> customInfo = new HashMap<>();
			customInfo.put("customField", fields);//返回自定义的字段
			Map<String,Object> customField = new HashMap<>();
			for(int i=0;i<fields.size();i++) {
				Map<String,Object> m = (Map)fields.get(i);
				customField.put(m.get("field").toString(), m.get("title").toString());
			}
			Map<String,Object> customValue = customerMapper.queryCustomValue(customField,formName,demandId);
			if(customValue!=null && customValue.size()>0)
				customInfo.put("customValue", customValue);//返回自定义字段的值
			
			res.put("customInfo", customInfo);
		}
		if("showinfo".equals(type)) {
			//跟进信息
			LinkedList<Map<String,Object>> followInfo= customerMapper.queryFollowInfo(demandId);
			//经纪人信息
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_36.getCode(),user.get("cityCode").toString());
			LinkedList<Map<String,Object>> relateagentInfo = null;
			if("1".equals(decision.get("val"))) {//决策配置直接显示经济人电话
				relateagentInfo = customerMapper.queryRelateagentInfo(demandId,true);//经纪人信息
			}else if("0".equals(decision.get("val"))){
				relateagentInfo = customerMapper.queryRelateagentInfo(demandId,false);//经纪人信息
			}
			//带看和浏览记录
			Map<String,Object> nums = customerMapper.queryShowAndBrowseNum(demandId);
			if(followInfo.size()>0)
				res.put("followInfo", followInfo);//返回客源跟进信息
			if(relateagentInfo.size()>0)
				res.put("relateagentInfo", relateagentInfo);//返回经纪人信息
			if(nums!=null && nums.size()>0)
				res.put("nums", nums);//返回带看和浏览记录
		}
		
		ExecutionResult.descFormat("需求id："+demandId, "查询客源详细信息");
		return res;
		
		
	}
	
	@SystemServiceLog(operationType="查询带看")
	@Override
	public Page<Map<String, Object>> queryLookHouse(String demandId,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_102.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = customerMapper.queryLookHouse(demandId);
		
		ExecutionResult.descFormat("客源id："+demandId+"", "查询带看信息");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询看房轨迹")
	@Override
	public List<Map<String, Object>> queryLookHousePath(Map<String,Object> data){
		List<Map<String, Object>> res = new ArrayList<>();
		//查出所有带看的房源编号
		List<Map<String, Object>> lookhouses = customerMapper.queryLookHouseId(data);
		for(Map<String, Object> lookhous :lookhouses) {
			String houseIds = lookhous.get("houseId").toString();
			String[] houseIdAttr = houseIds.split(",");
			for(int i=0;i<houseIdAttr.length;i++) {
				if(!"".equals(houseIdAttr[i])) {
					//查询房源区域坐标信息
					Map<String,Object> houseCommunity = customerMapper.queryHouseCommunity(houseIdAttr[i]);
					houseCommunity.put("leader", lookhous.get("leader"));
					houseCommunity.put("addTime", lookhous.get("addTime"));
					res.add(houseCommunity);
				}
				
			}
		}
		
		ExecutionResult.descFormat("客源id："+data.get("demandId")+"", "查询看房轨迹");
		return res;
	}
	
	@SystemServiceLog(operationType="查询跟进")
	@Override
	public Page<Map<String, Object>> queryFollowRecord(String demandId,int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_101.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = customerMapper.queryFollowRecord(demandId);
		
		ExecutionResult.descFormat("客源id："+demandId+"", "查询客源跟进信息");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询客户电话")
	@Override
	public List queryCustomerPhone(String demandId) {
		Map<String,Object> user=UserThreadLocal.get();
		String userId = user.get("id").toString();
		String cityCode = user.get("cityCode").toString();
		
		return limitCount(demandId,userId,cityCode,"4");
	}
	
	//查看电话限制
	public List<Map<String, Object>> limitCount(String demandId,String userId,String cityCode,String transactionTypeId) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getCustomerKey(demandId), NumberCount.CUST_LIMIT_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(userId, userId);
		if(resMap==null || resMap.size()<=0) {
			valueMap.put("num", 1);
			String limitValue = JSON.toJSONString(valueMap);
			
			//查看该客源数量加1
			redisServiceApi.setHash(NumberCount.getCustomerKey(demandId), NumberCount.CUST_LIMIT_COUNT, limitValue, true);
			LinkedList<Map<String,Object>> phones = customerMapper.queryCustomerPhone(demandId,BusinessExamineTypeEnum.CLOSEDISC.getCode());
			return phones;
		}else {
			if(resMap.containsKey(userId)) {//同一用户查看，不限制
				List<Map<String, Object>> phones=customerMapper.queryCustomerPhone(demandId,BusinessExamineTypeEnum.CLOSEDISC.getCode());
				return phones;
			}
			String decisionid = DecisionConfigurateEnum.DECISION_21.getCode();//默认为求购
			//判断是否为求租
			if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
				decisionid=DecisionConfigurateEnum.DECISION_30.getCode();
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid,cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//是否还能查看
					resMap.put(userId, userId);
					num = hasNum+1;
					valueMap.put("num", num);
					String limitValue = JSON.toJSONString(resMap);
					//查看该客源数量加1
					redisServiceApi.setHash(NumberCount.getCustomerKey(demandId), NumberCount.CUST_LIMIT_COUNT, limitValue, true);
					LinkedList<Map<String,Object>> phones = customerMapper.queryCustomerPhone(demandId,BusinessExamineTypeEnum.CLOSEDISC.getCode());
					return phones;
				}else {
					return null;
				}
				  
			}
		}
		return null;
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
		String transactionTypeId = "";
		if(data.containsKey("transactionTypeId")) {//需求类型
			transactionTypeId = data.get("transactionTypeId").toString();
		}
		if(data.containsKey("examineInfo")) {
			examineInfo = (Map)data.get("examineInfo");
		}
		String demandId = examineInfo.get("demandId").toString();
		
		Map<String,Object> user=UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		String userPhone = user.get("mobile").toString();
		String cityCode = user.get("cityCode").toString();
		Map dept = customerMapper.queryDeptName(userid);
		String deptId = dept.get("deptId").toString();
		
		examineInfo.put("operatorId", userid);
		examineInfo.put("operator", username);
		//申请人
		examineInfo.put("permissionArea", deptId);
		examineInfo.put("applyerId", userid);
		examineInfo.put("applyer", username);
		examineInfo.put("applyTime", new Date());
		if("setprivate".equals(type)) {//收为私客
			int housenum = customerMapper.queryDemandByMainter(userid);//用户下已有私盘数量
			String decisionid = DecisionConfigurateEnum.DECISION_19.getCode();//默认为求购
			//判断是否为求租
			if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
				decisionid = DecisionConfigurateEnum.DECISION_28.getCode();
			}
			//决策限制私盘数量
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid,cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				if(housenum<val) {
					//取审核类型
					examineInfo.put("examineTypeId",BusinessExamineTypeEnum.SETPRIVATE.getCode());
					Map<String,Object> remark = (Map)examineInfo.get("remark");
					remark.put("applyerId", userid);
					examineInfo.put("remark", JSONObject.toJSONString(remark));
					res = businessExamineService.saveBusinessExamine(examineInfo);
				}else {//超出限制数量
					return "私盘数量已达到上限";
				}
			}
		}
		if("changestatus".equals(type)) {//更改客源状态
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.UPDATESTATUS.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			remark.put("applyerId", userid);
			String id = examineInfo.get("demandId").toString();
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			if(CustomerStatusEnum.EFFECTIVE.getCode().equals(remark.get("statusId"))) {//是否从无效变有效，如果是：
				if(isChangeStatus(demandId,Integer.parseInt(transactionTypeId))) {//状态是否能变成有效
					res = businessExamineService.saveBusinessExamine(examineInfo);
				}
			}else {
				res = businessExamineService.saveBusinessExamine(examineInfo);
			}
			
		} 
		
		if("customerclose".equals(type)) {//封盘申请
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.CLOSEDISC.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			remark.put("applyerId", userid);
			String id = examineInfo.get("demandId").toString();
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			String endTime = remark.get("closeCustomerEndTime").toString();
			LocalDate date = LocalDate.parse(endTime);
			int examineNum = customerMapper.queryExamineInfo(id,BusinessExamineTypeEnum.CLOSEDISC.getCode());
			if(examineNum>0) {
				return "已申请封盘，不能再封";
			}else {
				String decisionid = DecisionConfigurateEnum.DECISION_24.getCode();//默认为求购
				//判断是否为求租
				if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
					decisionid=DecisionConfigurateEnum.DECISION_33.getCode();
				}
				Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid,user.get("cityCode").toString());
				if(decision!=null && decision.containsKey("val")) {
					Long day1 = Long.parseLong(decision.get("val").toString());
					long day2 = LocalDate.now().until(date, ChronoUnit.DAYS);
					if(day1<day2) {
						LocalDate realTime = LocalDate.now().plus(day1,ChronoUnit.DAYS);
						remark.put("closeCustomerEndTime", realTime.toString());
					}
				}
				res = businessExamineService.saveBusinessExamine(examineInfo); 
			}
			
		} 
		
		
		if("fromtaobao".equals(type)){//从淘宝淘出
			//取审核类型
			examineInfo.put("examineTypeId",BusinessExamineTypeEnum.TAOBAO.getCode());
			Map<String,Object> remark = (Map)examineInfo.get("remark");
			remark.put("applyerId", userid);
			examineInfo.put("remark", JSONObject.toJSONString(remark));
			String id = examineInfo.get("demandId").toString();
			Map<String,Object> map = customerMapper.queryTaobaoTime(id);
			if(map!=null) {
				int examineNum = customerMapper.queryExamineInfo(id,BusinessExamineTypeEnum.TAOBAO.getCode());
				if(examineNum>0) {
					return "已淘宝，不能再淘";
				}else {
					DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
					String taobaoTime1 = customerMapper.queryTaobaoTime(id).get("taobaoTime").toString();
					LocalDateTime taobaoTime2 = LocalDateTime.parse(taobaoTime1,df);
					long hour = taobaoTime2.until(LocalDateTime.now(), ChronoUnit.HOURS);
					String decisionid1 = DecisionConfigurateEnum.DECISION_16.getCode();//默认为求购
					String decisionid2 = DecisionConfigurateEnum.DECISION_17.getCode();//默认为求购
					String decisionid3 = DecisionConfigurateEnum.DECISION_18.getCode();//默认为求购
					//判断是否为求租
					if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
						decisionid1 = DecisionConfigurateEnum.DECISION_25.getCode();//
						decisionid2 = DecisionConfigurateEnum.DECISION_26.getCode();//
						decisionid3 = DecisionConfigurateEnum.DECISION_27.getCode();//
					}
					//本人保护
					Map<String,Object> decision1 = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid1,user.get("cityCode").toString());
					//本店保护
					Map<String,Object> decision2 = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid2,user.get("cityCode").toString());
					//本区保护
					Map<String,Object> decision3 = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid3,user.get("cityCode").toString());
					if(decision1!=null && decision1.containsKey("val")) {
						String day = decision1.get("val").toString();
						long dayLong = Long.parseLong(day);
						if(hour<dayLong*24) {
							if(userid!=Long.parseLong(map.get("maintainId").toString())) {
								return "受保护不能淘";
							}
							
						}else {
							//数量是否还能淘
							return taobaoCount(userPhone,demandId,cityCode,examineInfo,transactionTypeId);
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
							return taobaoCount(userPhone,demandId,cityCode,examineInfo,transactionTypeId);
						}
					}else if(decision3!=null && decision3.containsKey("val")) {
						String day = decision3.get("val").toString();
						long dayLong = Long.parseLong(day);
						if(hour<dayLong*24) {
							if(userid!=Long.parseLong(map.get("maintainId").toString())) {
								return "受保护不能淘";
							}
						}else {
							//数量是否还能淘
							return taobaoCount(userPhone,demandId,cityCode,examineInfo,transactionTypeId);
						}
					}else {
						//数量是否还能淘
						return taobaoCount(userPhone,demandId,cityCode,examineInfo,transactionTypeId);
					}
				}
				
			}else {
				return "不能淘";
			}
			
			
		}
		
		
		if("changestatus".equals(type)) {
			ExecutionResult.descFormat("", "更改客源状态提交审核");
		}else if("customerclose".equals(type)){
			ExecutionResult.descFormat("", "申请封盘提交审核");
		}else if("fromtaobao".equals(type)){
			ExecutionResult.descFormat("", "从淘宝淘出提交审核");
		}
		return res+"";
	}
	
	//状态是否能变成有效
	public boolean isChangeStatus(String demandId,Integer transactionTypeId) {
		if (StringUtils.isNotEmpty(demandId)) {
			Map<String, Object> user=UserThreadLocal.get();
			Map<String, Object> param=new HashMap<>();
			param.put("demandId", demandId);
			param.put("transactionTypeId", transactionTypeId);
			String id=DecisionConfigurateEnum.DECISION_23.getCode();//默认交易类型为求购
			//判断交易类型是否为求租
			if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId.toString())) {
				id=DecisionConfigurateEnum.DECISION_32.getCode();
			}
			Map<String, Object> map=decisionConfigurateServiceApi.getDecisionConfigurate(id, user.get("cityCode").toString());
			String val=map.get("val").toString();
			if (RepeatTypeEbum.SELF.getCode().equals(val)) {
				param.put("maintainId", user.get("id"));
			}else if (RepeatTypeEbum.COMPANY.getCode().equals(val)) {
				String dept=deptServiceApi.getDeptIdByCompanyAndCityCode(user.get("companyId").toString(),user.get("cityCode").toString());
				if (StringUtils.isEmpty(dept) ) {
					throw new BusinessException("该用户禁止变更状态");
				}
				param.put("mainterDeptId", dept);
			}else {
				String dept=deptServiceApi.getDeptIdByUserAndDeptType(user.get("id").toString(), val);
				if (StringUtils.isEmpty(dept) ) {
					throw new BusinessException("该用户禁止变更状态");
				}
				param.put("mainterDeptId", dept);
			}
			//查询电话数量
			Integer count=customerMapper.queryCountCustomerByDemandId(param);
			boolean result=count>0;
			//电话重复
			if (result) {
				throw new BusinessException("存在有效的相同客源，不能再更改为有效");
			}
			return true;
		}
		throw new BusinessException(ErrorCodeEnum.UNKNOWN_ERROR);
	}
	
	//用户每天能淘宝次数
	public String taobaoCount(String userPhone,String demandId,String cityCode,Map<String,Object> examineInfo,String transactionTypeId) {
		int num = 0;
		String res = redisServiceApi.getHash(NumberCount.getUserKey(userPhone), NumberCount.CUST_TAOBAO_COUNT);
		Map<String,Object> resMap = JSON.parseObject(res);
		Map<String,Object> valueMap = new HashMap<>();
		valueMap.put(demandId, demandId);
		if(resMap==null || resMap.size()<=0) {
			valueMap.put("num", 1);
			String value = JSON.toJSONString(valueMap);
			//用户淘宝加1
			redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.CUST_TAOBAO_COUNT, value, true);
			Long examineres = businessExamineService.saveBusinessExamine(examineInfo);
			return examineres+"";
		}else {
			String decisionid = DecisionConfigurateEnum.DECISION_22.getCode();//默认为求购
			//判断是否为求租
			if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
				decisionid = DecisionConfigurateEnum.DECISION_31.getCode();
			}
			Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid,cityCode);
			if(decision!=null && decision.containsKey("val")) {
				int val = Integer.parseInt(decision.get("val").toString());
				int hasNum = Integer.parseInt(resMap.get("num").toString());
				if(hasNum<val) {//还可以淘
					num = hasNum+1;
					valueMap.put("num", num);
					String value = JSON.toJSONString(valueMap);
					//淘宝加1
					redisServiceApi.setHash(NumberCount.getUserKey(userPhone), NumberCount.CUST_TAOBAO_COUNT, value, true);
					Long examineres = businessExamineService.saveBusinessExamine(examineInfo);
					return examineres+"";
				}else {
					return "已到淘宝上限，不能再淘";
				}
				  
			}
		}
		return null;
	}
	
	//放入公客、放入淘宝
	@SystemServiceLog(operationType="更改一些状态")
	@Override
	public String changeSomeStatus(Map<String,Object> data) {
		Long res = 0l;
		String type = "";
		Map<String,Object> statusInfo = new HashMap<>();
		if(data.containsKey("type")) {
			type = data.get("type").toString();
		}
		String transactionTypeId = "";
		if(data.containsKey("transactionTypeId")) {//需求类型
			transactionTypeId = data.get("transactionTypeId").toString();
		}
		if(data.containsKey("statusInfo")) {
			statusInfo = (Map)data.get("statusInfo");
		}
		
		Map<String,Object> user=UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString()); 
		String username = user.get("realName").toString();
		String cityCode = user.get("cityCode").toString();
		Map<String,Object> dept = customerMapper.queryDeptName(userid);
		String deptId = dept.get("deptId").toString();
		String deptname = dept.get("deptName").toString();
		
		if("toPublicdisc".equals(type)) {//放入公客
			//盘状态
			statusInfo.put("discStatusId", DiscStatusEnum.PUBLICDISC.getCode());
			statusInfo.put("discStatus", DiscStatusEnum.PUBLICDISC.getDesc());
			
			//因为说是不需要变更维护人，所以先注释了
			/*
			//维护人
			statusInfo.put("maintainer", username);
			statusInfo.put("maintainId", userid);
			statusInfo.put("mainterDeptId", deptId);
			statusInfo.put("mainterDept",deptname);
			statusInfo.put("permissionArea",deptId);
			//经纪人表的维护人
			Long recordid = 0l;
			if(!"".equals(statusInfo.get("recordid").toString()))
				recordid = Long.parseLong(statusInfo.get("recordid").toString());//有没有原维护人记录
			updateMyAgent(statusInfo.get("id").toString(),recordid,userid,AgentTypeEnum.CUSTMAINTAINER.getCode(),AgentTypeEnum.CUSTMAINTAINER.getDesc());
			*/
			statusInfo.remove("recordid");
			res = commonSqlServie.commonOperationDatabase(statusInfo, "demand_customerBusinessInfo", "id", false);
		}
		
		if("toTaobao".equals(type)) {//设置为放入淘宝池
			statusInfo.put("taobaoStatusId", TaoBaoStatusEnum.TAOBAO.getCode());
			statusInfo.put("taobaoStatus", TaoBaoStatusEnum.TAOBAO.getDesc());
			statusInfo.put("taobaoTime", new Date());
			res = commonSqlServie.commonOperationDatabase(statusInfo, "demand_customerBusinessInfo", "id", false);
		}
		
		if("toPublicdisc".equals(type)) {
			ExecutionResult.descFormat("", "放入公客");
		}else if("toTaobao".equals(type)){
			ExecutionResult.descFormat("", "客源放入淘宝");
		}
		return res+"";
	}
	
	//保存带看
	public Long saveLookhouse(Map<String,Object> data) {
		Map<String,Object> look = (Map)data;
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		List filePath = new ArrayList<>();
		String attachType = "";
		//Map<String,Object> dept = customerMapper.queryDeptName(userid);
		String deptId = user.get("deptId").toString();
		look.put("companyId", companyId);
		look.put("companyName", companyName);
		//带看人
		look.put("leaderId", userid);
		look.put("leader", username);
		look.put("leadTime", new Date());
		look.put("deptId", deptId);
		look.put("permissionArea", deptId);
		//操作人
		look.put("operatorId", userid);
		look.put("operator", username);
		look.put("visterEvaluate", "");//客户评价
		//是否二看、是否复看
		if(data.containsKey("isRepeat") || data.containsKey("isErLook")) {
			Map<String,Object> mm = new HashMap<>();
			mm.put("id", data.get("demandId"));
			mm.put("isRepeat", data.get("isRepeat")==null?0:data.get("isRepeat"));
			mm.put("isErLook", data.get("isErLook")==null?0:data.get("isErLook"));
			commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id",false);
			look.remove("isRepeat");
			look.remove("isErLook");
		}
		//带看图片路径
		if(data.containsKey("filePath")) {
			filePath = (List)data.get("filePath");
			attachType = data.get("attachType").toString();
			look.remove("filePath");
			look.remove("attachType");
		}
		
		List<Map<String,Object>> sourceIdList = (List)data.get("sourceId");
		for(int i=0;i<sourceIdList.size();i++) {
			look.put("sourceId", sourceIdList.get(i).get("id"));
			//保存带看记录
			Long res = commonSqlServie.commonOperationDatabase(look, "demand_showedrecord",false);
			//保存带看图片
			Map<String,Object> imgData = new HashMap<>();
			if(filePath.size()>0) {
				for(int j=0;j<filePath.size();j++) {
					imgData.put("showedRecordId", res);
					imgData.put("companyId", companyId);
					imgData.put("companyName", companyName);
					imgData.put("path", filePath.get(j));
					imgData.put("attachType", attachType);
					imgData.put("permissionArea", deptId);
					imgData.put("operatorId", userid);
					imgData.put("operator", username);
					commonSqlServie.commonOperationDatabase(imgData, "demand_showedRecordAttach",false);
				}
			}
		}
		
		
		//修改带看时间
		//****客源需求这边的*****
		Map<String,Object> mm = new HashMap<>(); //修改需求表的时间
		mm.put("id",data.get("demandId"));
		Map<String,Object> mainter =customerMapper.queryMainter(data.get("demandId").toString()) ;
		Long mainterId = Long.parseLong(mainter.get("maintainId").toString());
		//记录带看次数
		if("".equals(mainter.get("viewingCount")) || mainter.get("viewingCount")==null) {
			mm.put("viewingCount", 1);//第一次带看
		}else {
			mm.put("viewingCount", Integer.parseInt(mainter.get("viewingCount").toString())+1);
		}
		
		if(mainterId==userid) {//维护人带看时间
			mm.put("lastShowedTime", new Date());
			commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id", false);
		}else {//其他人员带看时间
			mm.put("lastAllShowedTime", new Date());
			commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id", false);
		}
		
		//****二手房源这边的*****
		if("0".equals(look.get("houseType").toString())) {
			Map<String,Object> mh = new HashMap<>();//修改房源表的时间
			for(int i=0;i<sourceIdList.size();i++) {
				mh.put("id", sourceIdList.get(i).get("id"));
				Map<String,Object> mainter1 =customerMapper.queryHsMainter(Long.parseLong(data.get("sourceId").toString())) ;
				Long mainterId1 = Long.parseLong(mainter1.get("maintainId").toString());
				//记录带看次数
				if("".equals(mainter1.get("viewingCount")) || mainter1.get("viewingCount")==null) {
					mh.put("viewingCount", 1);//第一次带看
				}else {
					mh.put("viewingCount", Integer.parseInt(mainter1.get("viewingCount").toString())+1);
				}
				
				if(mainterId1==userid) {//维护人带看时间
					mh.put("lastShowedTime", new Date());
					commonSqlServie.commonOperationDatabase(mh, "hs_secondhandhouse","id", false);
				}else {//其他人员带看时间
					mh.put("lastAllShowedTime", new Date());
					commonSqlServie.commonOperationDatabase(mh, "hs_secondhandhouse","id", false);
				}
			}
		}
		
		return 1l;
	}
	
	//删除带看
	@SystemServiceLog(operationType="删除")
	@Override
	public boolean delLookhouse(Long id,String demandId) {
		boolean res = commonSqlServie.delData("demand_showedrecord", "id", id+"", false);//删除带看记录
		customerMapper.reduceLookHouseNum(demandId);//减少带看次数
		return res;
	}
	
	//上传带看图片
	@SystemServiceLog(operationType="上传")
	@Override
	public Map<String,Object> saveImg(HttpServletRequest request){
		String filePath = "";
		String fileName = "";
		String demandId = request.getParameter("demandId");//客源编号
		if (StringUtils.isEmpty(demandId)) {
			throw new BusinessException("系统错误，上传图片未传客户编号！");
		}
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
					fileName = file.getName();
					filePath = FtpUtil.uploadFile(file,demandId,FtpSourceTypeEnum.FTP_SOURCE_KY.getCode(),FtpFileTypeEnum.FTP_FILE_TP.getCode(),true);
					
				}
			}
			Map<String,Object> res = new HashMap<>();
			res.put("fileName", fileName);
			res.put("filePath", filePath);
			return res;		
		}
		throw new BusinessException("系统错误，图片上传失败！");
	}
	
	
	//查看带看图片
	@SystemServiceLog(operationType="查看")
	@Override
	public List<Map<String,Object>> queryImg(Long id){
		List<Map<String,Object>> imgpaths = customerMapper.queryImg(id);
		return imgpaths;
	}
	
	//保存定金
	@SystemServiceLog(operationType="保存")
	@Override
	public String saveDeposit(Map<String,Object> data) {
		
		Map<String,Object> map = (Map)data;
		Long trusteesId = Long.parseLong(map.get("trusteesId").toString());
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		Map<String,Object> dept = customerMapper.queryDeptName(trusteesId);
		String deptId = "";
		if(dept!=null) {
			deptId = dept.get("deptId").toString();
		}
		map.put("price", Integer.parseInt(data.get("price").toString())*100);
		map.put("maybeDealPrice", Integer.parseInt(data.get("maybeDealPrice").toString())*1000000);
		map.put("companyId", companyId);
		map.put("companyName", companyName);
		map.put("operatorId", userid);
		map.put("operator", username);
		map.put("status", '0');
		map.put("permissionArea", deptId);
		String billNo = mRedisGetIdServiceApi.getTicketId(); //票据号
		map.put("billNo", billNo);
		Long res = commonSqlServie.commonOperationDatabase(map, "demand_bond",false);
		if(res>0) {
			return billNo;
		}else {
			return "";
		}
		
	}
	
	//定金审核后操作
	@SystemServiceLog(operationType="定金审核后操作")
	@Override
	public Long DepositSureOp(String demandId,Long houseId,String status) {
		String cityCode = customerMapper.queryDemand(demandId).get("operatorCityCode").toString();
		String transactionTypeId = customerMapper.queryDemand(demandId).get("transactionTypeId").toString();
		if(DepositManageEnum.CONFIRMED.getCode().equals(status)) {//确认下定
			//下定确认是否自动封盘
			Map<String,Object> decision1 = decisionConfigurateServiceApi.getDecisionConfigurate(DecisionConfigurateEnum.DECISION_37.getCode(), cityCode);
			if(decision1!=null && decision1.containsKey("val") && "1".equals(decision1.get("val").toString())) {//自动封盘
				String decisionid = DecisionConfigurateEnum.DECISION_24.getCode();//默认为求购
				//判断是否为求租
				if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId)) {
					decisionid=DecisionConfigurateEnum.DECISION_33.getCode();
				}
				//查出决策配置的封盘到期时间
				Map<String,Object> decision = decisionConfigurateServiceApi.getDecisionConfigurate(decisionid,cityCode);
				if(decision!=null && decision.containsKey("val")) {
					Long day = Long.parseLong(decision.get("val").toString());
					LocalDate realTime = LocalDate.now().plus(day,ChronoUnit.DAYS);
					//房源封盘
					Map<String,Object> mh = new HashMap<>();
					mh.put("id", houseId);
					mh.put("closePlanteEndDate", realTime.toString());
					mh.put("closeDiscFlg", 1);//1代表封路径
					commonSqlServie.commonOperationDatabase(mh, "hs_secondHandHouse","id",false);
					//客源封盘
					Map<String,Object> md = new HashMap<>();
					md.put("id", demandId);
					md.put("closePlanteEndDate", realTime.toString());
					md.put("isCloseDisc", 1);//1代表封盘（封电话）
					commonSqlServie.commonOperationDatabase(md, "demand_customerBusinessInfo","id",false);
				}
			}
			
		}else {//退定
			//房源解封
			Map<String,Object> mh = new HashMap<>();
			mh.put("id", houseId);
			mh.put("closeDiscFlg", 0);//未封盘
			commonSqlServie.commonOperationDatabase(mh, "hs_secondHandHouse","id",false);
			//客源解封
			Map<String,Object> md = new HashMap<>();
			md.put("id", demandId);
			md.put("isCloseDisc", 0);//未封盘
			commonSqlServie.commonOperationDatabase(md, "demand_customerBusinessInfo","id",false);
		}
		//更改下定状态
		Map<String,Object> mm = new HashMap<>();
		mm.put("id", demandId);
		mm.put("isBond", status);
		Long res = commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id",false);//更改是否下定标记
		return res;
	}
	
	//租售转换或更改信息保存
	@SystemServiceLog(operationType="保存")
	@Override
	public Long saveExchangeCustomerInfo(String dataJson,String type) {
		
		boolean res = addCustomer(dataJson,type);
		if(res) {
			return 1l;
		}else {
			return 0l;
		}
		
	}
	
	//保存收藏
	@SystemServiceLog(operationType="保存或取消收藏")
	@Override
	public Long updateStore(String demandId,int status) {
		Long res = 0l;
		Map<String,Object> user = UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		Map<String,Object> m = new HashMap<>();
		m.put("demandId", demandId);
		m.put("isValid", status);
		Map<String,Object> store = customerMapper.queryStore(demandId,userid);
		if(status==1) {
			if(store==null) {//没收藏过，新增一条
				m.put("operatorId", userid);
				m.put("operator", username);
				res = commonSqlServie.commonOperationDatabase(m, "demand_mycollection", false);
				ExecutionResult.descFormat("", "设为收藏");
			}else {//改状态为收藏
				Long storeid = Long.parseLong(store.get("id").toString());
				m.put("id", storeid);
				res = commonSqlServie.commonOperationDatabase(m, "demand_mycollection","id", false);
				ExecutionResult.descFormat("", "更改为收藏");
			}
		}else {//改状态为取消收藏
			Long storeid = Long.parseLong(store.get("id").toString());
			boolean res1 = commonSqlServie.delData("demand_mycollection", "id", storeid+"", false);
			ExecutionResult.descFormat("", "取消收藏");
			if(res1)
				res = 1l;
		}
		return res;
	}
	
	//保存跟进、提醒
	@SystemServiceLog(operationType="保存")
	@Override
	public Long saveCustomerFollow(Map<String,Object> data) {
		Long res = 0l;
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());
		String username = user.get("realName").toString();
		Map<String,Object> dept = customerMapper.queryDeptName(userid);
		String deptid = dept.get("deptId").toString();
		String demandId = "";
		if(data.containsKey("followInfo")) {//写跟进
			Map<String,Object> followInfo = (Map)data.get("followInfo");
			demandId = followInfo.get("demandId").toString();
			followInfo.put("companyId", companyId);
			followInfo.put("companyName", companyName);
			followInfo.put("operatorId", userid);
			followInfo.put("operator", username);
			followInfo.put("deptId", deptid);
			followInfo.put("followerId",userid);
			followInfo.put("follower",username);
			followInfo.put("permissionArea",deptid);
			followInfo.put("followTime",new Date());
			res = commonSqlServie.commonOperationDatabase(followInfo, "demand_followrecord", false);
			//客源表跟进时间
			Map<String,Object> mm = new HashMap<>();
			mm.put("id",demandId);
			Map<String,Object> mainter =customerMapper.queryMainter(demandId) ;
			Long mainterId = Long.parseLong(mainter.get("maintainId").toString());
			if(mainterId==userid) {//维护人跟进时间
				mm.put("mainterFllowTime", new Date());
				res = commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id", false);
			}else {//其他人员跟进时间
				mm.put("allFollowTime", new Date());
				res = commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id", false);
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
				remindInfo.put("remindType", 0);
				remindInfo.put("operator", username);
				res = commonSqlServie.commonOperationDatabase(remindInfo, "sys_remind", false);
				ExecutionResult.descFormat("", "保存提醒");
			}
			
		}
		if(data.containsKey("isCancelNotice")) {//通知撤单
			Map<String,Object> mainter =customerMapper.queryMainter(demandId) ;
			Map<String,Object> mm = new HashMap<>();
			mm.put("sendPeopleId", userid);
			mm.put("sendPeopleName", username);
			mm.put("receivePeopleId", mainter.get("maintainId"));
			mm.put("receivePeopleName", mainter.get("maintainer"));
			mm.put("remindTime",new Date());
			mm.put("status", 0);
			mm.put("operator", username);
			mm.put("remindContent", "通知撤单");
			mm.put("remindType", 2);
			res = commonSqlServie.commonOperationDatabase(mm, "sys_remind", false);
			//二手房源表撤单标志
			mm = new HashMap<>();
			mm.put("id",demandId);
			mm.put("isCancelNotice", 1);
			mm.put("cancelNoticeTime", new Date());
			res = commonSqlServie.commonOperationDatabase(mm, "demand_customerBusinessInfo","id", false);
			ExecutionResult.descFormat("", "通知维护人撤单");
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="删除跟进记录")
	@Override
	public boolean delFollow(Long id) {
		boolean res = commonSqlServie.delData("demand_followrecord", id+"", "id", false);
		return res;
	}
	
	@SystemServiceLog(operationType="查询客户档案")
	@Override
	public Page<Map<String, Object>> queryArchives(String demandId,String type,int page,int limit){
		//String companyId=UserThreadLocal.get().get("companyId").toString(); 
		String companyId = customerMapper.queryCustomerCompany(demandId);
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		if("house".equals(type)) {//查房源
			dataAuthHelper.auth(MenuEnum.MENU_84.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = customerMapper.queryCustomerHouse(demandId,companyId);		
			ExecutionResult.descFormat("客源id："+demandId+"", "查询客户房源");
		}else if("rent".equals(type)) {//查求租
			dataAuthHelper.auth(MenuEnum.MENU_97.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = customerMapper.queryCustomerDemand(demandId,CustomerTransactionEnum.RENT.getCode(),companyId);
		}else if("buy".equals(type)) {//查求购
			dataAuthHelper.auth(MenuEnum.MENU_96.getCode(), UserThreadLocal.get().get("id").toString());
			pageInfo = customerMapper.queryCustomerDemand(demandId,CustomerTransactionEnum.BUY.getCode(),companyId);
		}
		
		
		return pageInfo;
	}
	
	
	@SystemServiceLog(operationType="保存客户电话")
	@Override
	public Long saveCustomerPhone(Map<String,Object> data) {
		Map<String,Object> mm = (Map)data;
		String phone = mm.get("phone").toString();
		int transactionTypeId = Integer.parseInt(mm.get("transactionTypeId").toString());
		if(queryCustomerRepeat(phone,transactionTypeId)) {
			mm.remove("transactionTypeId");
			Long res = commonSqlServie.commonOperationDatabase(mm, "demand_custRelaterPhone", false);
			ExecutionResult.descFormat("客源id："+mm.get("demandId").toString(), "新增业主电话");
			return res;
		}else {
			throw new BusinessException("该号码已存在");
		}
		//return null;
	}
	
	@SystemServiceLog(operationType="修改客户电话")
	@Override
	public Long updateCustomerPhone(Map<String,Object> data) {
		Map<String,Object> mm = (Map)data;
		String phone = mm.get("phone").toString();
		int transactionTypeId = Integer.parseInt(mm.get("transactionTypeId").toString());
		if(queryCustomerRepeat(phone,transactionTypeId)) {
			mm.remove("transactionTypeId");
			Long res = commonSqlServie.commonOperationDatabase(mm, "demand_custRelaterPhone","id", false);
			ExecutionResult.descFormat("电话记录id："+mm.get("id").toString(), "修改业主电话");
			return res;
		}else {
			throw new BusinessException("该号码已存在");
		}
		//return null;
	}
	
	@SystemServiceLog(operationType="删除客户电话")
	@Override
	public boolean delCustomerPhone(Long id) {
		boolean res = commonSqlServie.delData("demand_custRelaterPhone","id",id+"", false);
		ExecutionResult.descFormat("电话记录id："+id, "删除业主电话");
		return res;
	}
	
	//变更经纪人具体实现
	public Long updateMyAgent(String demandId,Long recordid,Long agentId,String agentType,String agentTypeName) {
		Long res = 0l;
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		String companyName=user.get("comName").toString();
		Long userid = Long.parseLong(user.get("id").toString());//操作人id
		String username = user.get("realName").toString();//操作人
		Map<String,Object> dept = customerMapper.queryDeptName(agentId);//经纪人部门
		String deptid = dept.get("deptId").toString();
		String deptname = dept.get("deptName").toString();
		String agentName = dept.get("realName").toString();
		//经纪人信息
		Map<String,Object> m = new HashMap<>();
		m.put("demandId", demandId);
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
			res= commonSqlServie.commonOperationDatabase(m,"demand_custRelateAgent", "id",false);
		}else {//新增
			res = commonSqlServie.commonOperationDatabase(m, "demand_custRelateAgent", false);
		}
		return res;
	}
	
	@SystemServiceLog(operationType="变更维护人")
	@Override
	public Long updateAgent(Map<String,Object> data) {
		Long res = 0l;
		String id = data.get("demandId").toString();
		Long recordid = 0l;
		if(!"".equals(data.get("recordid").toString()))
			recordid = Long.parseLong(data.get("recordid").toString());
		Long peopleid = Long.parseLong(data.get("peopleid").toString());
		String people = data.get("people").toString();
		String type = data.get("type").toString();
		
		if("wh".equals(type)) {//变更维护人
			Map<String,Object> m = new HashMap<>();
			m.put("id", id);
			m.put("maintainer", people);
			m.put("maintainId", peopleid);
			m.put("operator", people);
			m.put("operatorId", peopleid);
			Map<String,Object> dept = customerMapper.queryDeptName(peopleid);
			m.put("mainterDeptId", dept.get("deptId"));
			m.put("mainterDept", dept.get("deptName"));
			m.put("permissionArea", dept.get("deptId"));
			//修改客户需求表
			res = commonSqlServie.commonOperationDatabase(m, "demand_customerbusinessinfo", "id", false);
			//变更或新增维护人
			res = updateMyAgent(id,recordid,peopleid,AgentTypeEnum.CUSTMAINTAINER.getCode(),AgentTypeEnum.CUSTMAINTAINER.getDesc());
		}
		
		
		
		if("wh".equals(type)) {
			ExecutionResult.descFormat("", "变更维护人");
		}
		
		return res;
		
	}
	
	@SystemServiceLog(operationType="客源查询二手房源表格信息")
	@Override
	public Page<Map<String, Object>> queryHouseListByCustomer(String conditions, int page, int limit) {
		PageHelper.startPage(page, limit);
		
		CustomerFindHouseEntity query=null;
		if (StringUtils.isEmpty(conditions)) {
			query=new CustomerFindHouseEntity();
		}else {
			query=JSON.parseObject(conditions, CustomerFindHouseEntity.class);
		}	
		dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> result=customerMapper.customerFindHouseList(query.toSql());
		ExecutionResult.descFormat(UserThreadLocal.get().get("id").toString(), "客源查询二手房源表格信息");
		return result;
	}
	@SystemServiceLog(operationType="客源查询一手楼盘表格信息")
	@Override
	public Page<Map<String, Object>> queryNewHouseList(String conditions, int page, int limit) {
		PageHelper.startPage(page, limit);
		Map<String,Object> query = JSON.parseObject(conditions, Map.class);
		if(query.containsKey("vagueData") && StringUtils.isNotEmpty(query.get("vagueData").toString())) {
			
		}
		//dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> result=customerMapper.queryNewHouseList(query);
		ExecutionResult.descFormat(UserThreadLocal.get().get("id").toString(), "客源查询一手楼盘表格信息");
		return result;
	}
	
	@SystemServiceLog(operationType="新增需求")
	@Override
	public boolean addCustomer(String dataJson,String type) {
		String customerId = "";
		if (StringUtils.isNotEmpty(dataJson)) {		
			
			Map<String,Object> data=JSON.parseObject(dataJson, Map.class);
			if (data.get("transactionTypeId")==null||StringUtils.isEmpty(data.get("transactionTypeId").toString())) {
				throw new BusinessException("系统错误");
			}
			/**交易类型**/
			CustomerTransactionEnum transEnum=CustomerTransactionEnum.parseOf(data.get("transactionTypeId").toString());
			String formName=data.get("formName").toString();
			Map<String,Object> user=UserThreadLocal.get();
			String companyId=user.get("companyId").toString();
			String companyName=user.get("comName").toString();
			String deptId=user.get("deptId").toString();
			String deptName=user.get("depName").toString();
			String cityCode=user.get("cityCode").toString();	
			if("add".equals(type)) { //新增
				customerId=redisGetIdServiceApi.getCustomId();
				data.put("id", customerId);
				data.put("companyId", companyId);
				data.put("companyName", companyName);
				data.put("formName", formName);
				data.put("operatorCityCode", cityCode);
				data.put("maintainId", user.get("id"));
				data.put("maintainer", user.get("realName"));
				data.put("mainterDeptId", deptId);
				data.put("mainterDept", deptName);
				data.put("permissionArea", deptId);
				data.put("operatorId", user.get("id"));
			}else {
				customerId = data.get("id").toString();
			}
			
			data.put("recordTime", new Date());
			data.put("statusId", CustomerStatusEnum.EFFECTIVE.getCode());
			data.put("status", CustomerStatusEnum.EFFECTIVE.getDesc());
			data.put("taobaoStatusId", TaoBaoStatusEnum.NOT_TAOBAO.getCode());
			data.put("taobaoStatus", TaoBaoStatusEnum.NOT_TAOBAO.getDesc());
			data.put("remark", data.get("custRemark").toString());
			data.remove("custRemark");
			//设置一手、二手
			/*String housePropType="";
			if (data.get("one")!=null&&!"".equals(data.get("one"))) {
				housePropType="01";
				data.remove("one");
			}
			if (data.get("two")!=null&&!"".equals(data.get("two"))) {
				if (!housePropType.equals("")) {
					housePropType+=",";
				}
				housePropType+="02";
				data.remove("two");
			}*/
			String transactionTypeId=data.get("transactionTypeId").toString();
			
			Integer until=WANYUAN;
			if (CustomerTransactionEnum.RENT.equals(transEnum)) {
				until=YUAN;
			}
			/*if (!housePropType.equals("")) {
				data.put("housePropType", housePropType);
			}*/
			//设置价格、面积
			if (data.get("minPrice")==null||"".equals(data.get("minPrice"))) {
				data.put("minPrice", 0);
			}else if (!"0".equals(data.get("minPrice"))) {
				data.put("minPrice", convertFen(data.get("minPrice").toString(), until));
			}
			if (data.get("maxPrice")==null||"不限".equals(data.get("maxPrice").toString())) {
				data.put("maxPrice", 0);
			}else if (!"0".equals(data.get("maxPrice"))) {
				data.put("maxPrice", convertFen(data.get("maxPrice").toString(), until));
			}
			if (data.get("minSpace")==null) {
				data.put("minSpace", 0);
			}
			if (data.get("maxSpace")==null||"不限".equals(data.get("maxSpace").toString())) {
				data.put("maxSpace", 0);
			}
			//设置客源等级			
			List<Map<String,Object>> list=customerMapper.queryCustomerLevel(cityCode, companyId, transactionTypeId);
			if (list!=null&&list.size()>0) {
				for (Map<String,Object> map : list) {
					//判断等级条件是否为急切
					if (map.get("conditions")!=null&&!map.get("conditions").equals(ResCustLevelConditionEnum.EAGER.getCode())) {
						continue;
					}
					//如果等级条件为急切，判断需求是否有急切
					if (map.get("conditions")!=null&&map.get("conditions").equals(ResCustLevelConditionEnum.EAGER.getCode())) {
						if (data.get("isUrgent")==null||!"1".equals(data.get("isUrgent").toString())) {
							continue;
						}
					}
					Integer beginPrice=0;
					Integer endPrice=0;
					Double beginSpace=0d;
					Double endSpace=0d;
					if (map.get("minPrice")!=null) {
						beginPrice=Integer.parseInt(map.get("minPrice").toString());
					}
					if (map.get("maxPrice")!=null) {
						endPrice=Integer.parseInt(map.get("maxPrice").toString());
					}
					if (map.get("minSpace")!=null) {
						beginSpace=Double.parseDouble(map.get("minSpace").toString());
					}
					if (map.get("maxSpace")!=null) {
						endSpace=Double.parseDouble(map.get("maxSpace").toString());
					}
					Integer minPrice=Integer.parseInt(data.get("minPrice").toString());
					Integer maxPrice=Integer.parseInt(data.get("maxPrice").toString());
					Integer minSpace=Integer.parseInt(data.get("minSpace").toString());
					Integer maxSpace=Integer.parseInt(data.get("maxSpace").toString());
					if (minPrice>=beginPrice&&maxPrice<=endPrice&&minSpace>=beginSpace&&maxSpace<=endSpace) {
						data.put("levelType", map.get("levelType"));
						break;
					}
				}
				if (data.get("levelType")==null||"".equals(data.get("levelType").toString())) {
					data.put("levelType", "C");
				}
			}else {
				data.put("levelType", "C");
			}
			
			if (data.get("labeld")!=null&&data.get("labeld").toString().contains("学区房")) {
				data.put("isSchoolRoom", 1);
			}
			//动态表单内容
			Map<String,Object> customData=(Map<String,Object>) data.get("customData");
			if (data.get("customData")!=null && customData.size()>0) {
				data.remove("customData");
				customData.put("keyId", customerId);
				customData.put("formName", formName);
				if("add".equals(type)) { 
					commonSqlServie.commonOperationDatabase(customData, "sys_custom_column_value", false);
				}else {
					Map<String, Object> updateParam = new HashMap<>();
					for (Object key : customData.keySet()) {
						updateParam.put(key.toString(), customData.get(key));
					}
					commonSqlServie.batchcommonUpdate(updateParam, "sys_custom_column_value", "keyId", "'"+customerId+"'", "formName='"+formName+"'");
				}
			}
			//联系信息
			Map<String,Object> custRelater=new HashMap<>();
			custRelater.put("demandId", customerId);
			custRelater.put("custName", data.get("custName"));
			custRelater.put("relateTypeId", data.get("relateTypeId"));
			data.remove("relateTypeId");
			custRelater.put("relateTypeName", data.get("relateTypeName"));
			data.put("custCallType", data.get("relateTypeName"));
			data.remove("relateTypeName");
			if (data.get("consumptIdeaId")!=null) {
				custRelater.put("consumptIdeaId", data.get("consumptIdeaId").toString().equals("")?null:data.get("consumptIdeaId"));
				custRelater.put("consumptIdeaName", data.get("consumptIdeaName"));
				data.remove("consumptIdeaId");
				data.remove("consumptIdeaName");
			}
			if (data.get("censusRegisterId")!=null) {
				custRelater.put("censusRegisterId", data.get("censusRegisterId").toString().equals("")?null:data.get("censusRegisterId"));
				custRelater.put("censusRegister", data.get("censusRegister"));
				data.remove("censusRegisterId");
				data.remove("censusRegister");
			}
			if (data.get("nationsId")!=null) {
				custRelater.put("nationsId", data.get("nationsId").toString().equals("")?null:data.get("nationsId"));
				custRelater.put("nations", data.get("nations"));
				data.remove("nationsId");
				data.remove("nations");
			}
			if (data.get("IDCard")!=null) {
				custRelater.put("IDCard", data.get("IDCard"));
				data.remove("IDCard");
			}
			if (data.get("email")!=null) {
				custRelater.put("email", data.get("email"));
				data.remove("email");
			}
			if (data.get("qqNum")!=null) {
				custRelater.put("qqNum", data.get("qqNum"));
				data.remove("qqNum");
			}
			if (data.get("wxNum")!=null) {
				custRelater.put("wxNum", data.get("wxNum"));
				data.remove("wxNum");
			}
			if (data.get("vehicleId")!=null) {
				custRelater.put("vehicleId", data.get("vehicleId").toString().equals("")?null:data.get("vehicleId"));
				custRelater.put("vehicle", data.get("vehicle"));
				data.remove("vehicleId");
				data.remove("vehicle");
			}
			if (data.get("contactAddress")!=null) {
				custRelater.put("contactAddress", data.get("contactAddress"));
				data.remove("contactAddress");
			}
			if("add".equals(type)) {
				custRelater.put("operatorId", user.get("id"));
				custRelater.put("permissionArea", deptId);
				custRelater.put("companyId", companyId);
				custRelater.put("companyName", companyName);
				commonSqlServie.commonOperationDatabase(custRelater, "demand_custRelater", false);
			}else {
				commonSqlServie.commonOperationDatabase(custRelater, "demand_custRelater","demandId", false);
			}
			
			//是否发送绑定码
			boolean isSendBindCode=false;
			if (data.get("isSendBindCode")!=null) {
				if("1".equals(data.get("isSendBindCode").toString())) {
					isSendBindCode=true;
				}				
				data.remove("isSendBindCode");
			}
			
			//电话信息
			Map<String,Object> custRelaterPhone=new HashMap<>();
			if(!"update".equals(type)) {
				custRelaterPhone.put("demandId", customerId);
				custRelaterPhone.put("operatorId", user.get("id"));
				custRelaterPhone.put("companyId", companyId);
				custRelaterPhone.put("companyName", companyName);
				custRelaterPhone.put("phone", data.get("phone"));
				data.remove("phone");
				custRelaterPhone.put("relateType", data.get("relateType"));
				data.remove("relateType");
				custRelaterPhone.put("relateName", data.get("relateName"));
				custRelaterPhone.put("remark", data.get("relateName"));
				data.remove("relateName");
				custRelaterPhone.put("ismainFlag", 1);
				if("add".equals(type)) { //新增
					commonSqlServie.commonOperationDatabase(custRelaterPhone, "demand_custRelaterPhone", false);
				}else if("exchange".equals(type)) {//租售转换
					String condition="ismainFlag=1";
					Map<String,Object> updateCustRelaterPhone = (Map)custRelaterPhone;
					commonSqlServie.batchcommonUpdate(updateCustRelaterPhone, "demand_custRelaterPhone", "demandId", "\""+customerId+"\"", condition);
				}
			}
			
			if("add".equals(type)) { //新增
				//增加需求
				commonSqlServie.idCustomOperationDatabase(data, "demand_customerBusinessInfo", false);
				//新增客源日志	
				String tradeTypeId=data.get("transactionTypeId").toString();	
				BusinessLogModel model=new BusinessLogModel();
				model.setBusinessTypeId(Integer.parseInt(LogBusinessTypeEnum.CUSTOMER.getCode())); 
				if (CustomerTransactionEnum.BUY.getCode().equals(tradeTypeId)) {
					model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.SELL_CUSTOMER.getCode()));
				}else if (CustomerTransactionEnum.RENT.getCode().equals(tradeTypeId)) {
					model.setSourceTypeId(Integer.parseInt(LogBusinessSourceTypeEnum.RENT_CUSTOMER.getCode()));
				}
				model.setOperatTypeId(Integer.parseInt(LogBusinessOperateTypeEnum.ADD_CUSTOMER.getCode()));
				model.setSourceId(customerId);
				model.setSourceCode(customerId);
				model.setRemark(formName);
				model.setLogContent("新增客源");
				businessLogService.customerLogAdd(model);
			}else {
				//修改需求
				commonSqlServie.commonOperationDatabase(data, "demand_customerBusinessInfo","id", false);
			}
			
			if("add".equals(type)) {
				
				/** 添加录入人、开盘人、维护人**/
				List<String> agentList=new ArrayList<>();
	
				String columns="companyId,companyName,demandId,agentId,agentName,operatorId,departmentId,departmentName,agentType,agentTypeName";
				StringBuilder value=new StringBuilder();
				value.append("'");
				value.append(companyId);
				value.append("','");
				value.append(companyName);
				value.append("','");
				value.append(customerId);
				value.append("',");
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
				agentList.add(value.toString()+",'"+AgentTypeEnum.CUSTRECORDER.getCode()+"','"+AgentTypeEnum.CUSTRECORDER.getDesc()+"'");
				agentList.add(value.toString()+",'"+AgentTypeEnum.CUSTMAINTAINER.getCode()+"','"+AgentTypeEnum.CUSTMAINTAINER.getDesc()+"'");
				commonSqlServie.batchAdd(columns, "demand_custRelateAgent", agentList, false);
			}
			//生成并发送房源绑定码
			if (isSendBindCode) {
				redisGetIdServiceApi.getCustomBindCode(customerId, custRelaterPhone.get("phone").toString());
			}
			ExecutionResult.descFormat(customerId, "新增需求");
			
		}		
		return true;
	}
	
	@SystemServiceLog(operationType="查询带看房源列表")
	@Override
	public Page<Map<String, Object>> queryCustomerListByIds(String ids, int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> result=customerMapper.queryCustomerListByIds(ids);
		ExecutionResult.descFormat(ids, "查询带看房源列表");
		return result;
	}
	@SystemServiceLog(operationType="查询客源列表")
	@Override
	public Page<Map<String, Object>> queryCustomerTableDatas(String conditions, int page, int limit) {
		PageHelper.startPage(page, limit);
		
		CustomerQueryEntity query=null;
		if (StringUtils.isEmpty(conditions)) {
			query=new CustomerQueryEntity();
		}else {
			query=JSON.parseObject(conditions, CustomerQueryEntity.class);
		}	
		if(query.getTransactionTypeId()!=null) {
			if (CustomerTransactionEnum.BUY.getCode().equals(query.getTransactionTypeId().toString())) {
				dataAuthHelper.auth(MenuEnum.MENU_96.getCode(),UserThreadLocal.get().get("id").toString());
			}
			if (CustomerTransactionEnum.RENT.getCode().equals(query.getTransactionTypeId().toString())) {
				dataAuthHelper.auth(MenuEnum.MENU_97.getCode(),UserThreadLocal.get().get("id").toString());
			}
		}
		Page<Map<String, Object>> result=customerMapper.queryCustomerList(query.toSql());
		ExecutionResult.descFormat(UserThreadLocal.get().get("id").toString(), "查询客源列表");
		return result;
	}
	@SystemServiceLog(operationType="批量删除客源需求")
	@Override
	public int deleteCustomer(String customerIds) {
		if (StringUtils.isNotEmpty(customerIds)) {
			String[] array=customerIds.split(",");
			StringBuilder sb=new StringBuilder();
			for (String item : array) {
				if (StringUtils.isNotEmpty(item)) {
					if (sb.length()>0) {
						sb.append(",");
					}
					sb.append("'");
					sb.append(item);
					sb.append("'");
				}
			}
			if (sb.length()>0) {
				commonSqlServie.delData("demand_customerBusinessInfo", "id", sb.toString(), false);
				ExecutionResult.descFormat(sb.toString(), "批量删除客源需求");
			}
			return 1;
		}
		return 0; 
	}
	@SystemServiceLog(operationType="批量转移客源需求")
	@Override
	public boolean customerTransfer(String customerIds, long userId) {
		ExecutionResult.descFormat(customerIds+","+userId, "批量转移客源需求");
		if (StringUtils.isNotEmpty(customerIds)&&userId>0) {
			Map dept = customerMapper.queryDeptName(userId);
			String[] array=customerIds.split(",");
			StringBuilder sb=new StringBuilder();
			for (String item : array) {
				if (StringUtils.isNotEmpty(item)) {
					if (sb.length()>0) {
						sb.append(",");
					}
					sb.append("'");
					sb.append(item);
					sb.append("'");
				}
			}
			if (sb.length()>0) {
				Map<String, Object> fields=new HashMap<>();
				fields.put("permissionArea", dept.get("deptId"));
				fields.put("mainterDept", dept.get("deptName"));
				fields.put("operatorId", userId);
				fields.put("maintainer", dept.get("realName"));
				fields.put("maintainId", userId);
				fields.put("mainterDeptId", dept.get("deptId"));
				commonSqlServie.batchcommonUpdate(fields, "demand_customerBusinessInfo", "id", sb.toString(), null);
				fields=new HashMap<>();
				fields.put("agentId", userId);
				fields.put("agentName", dept.get("realName"));
				fields.put("departmentId", dept.get("deptId"));
				fields.put("departmentName", dept.get("deptName"));
				String condition="agentType='"+AgentTypeEnum.CUSTMAINTAINER.getCode()+"'";
				commonSqlServie.batchcommonUpdate(fields, "demand_custRelateAgent", "demandId", sb.toString(), condition);
				return true;
			}
			
		}
		return false;
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
	@SystemServiceLog(operationType="匹配房源")
	@Override
	public Page<Map<String, Object>> queryMatchingHousing(String conditions, int page, int limit) {
		Map<String, Object> param=null;
		PageHelper.startPage(page, limit);
		if (StringUtils.isNotEmpty(conditions)) {
			param=JSON.parseObject(conditions, Map.class);
		}
		dataAuthHelper.auth(MenuEnum.MENU_84.getCode(),UserThreadLocal.get().get("id").toString());
		ExecutionResult.descFormat("", "匹配房源查询");
		return customerMapper.matchingHousing(param);
	}
	@SystemServiceLog(operationType="客源浏览")
	@Override
	public int addBrowseRecord(String demandId) {
		Long userId=Long.parseLong(UserThreadLocal.get().get("id").toString());
		browseRecordService.addCustomerBrowseRecord(demandId, userId);
		/*Map<String,Object> user=UserThreadLocal.get();
		String condition="demandId='"+demandId+"' and operatorId="+user.get("id")+" and addTime like '"+LocalDate.now().toString()+"%'";
		Page<Map<String, Object>> page=commonSqlServie.querySingleTable("id", "demand_browseRecord", condition, 1, 10);
		if (page.getTotal()==0) {
			
			Map<String,Object> map=new HashMap<>();
			map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			map.put("demandId", demandId);
			map.put("operatorId", user.get("id"));
			commonSqlServie.idCustomOperationDatabase(map, "demand_browseRecord", false);
			ExecutionResult.descFormat(demandId+","+user.get("id"), "添加客源浏览记录");
		} */
		
		return 1;
	}
	
	/**
	 * 
	 * @Title: doExamine   
	 * @Description: TODO(客源业务审核处理接口)   
	 * @param: @param demandId 需求id
	 * @param: @param type 审核类型
	 * @param: @param result 审核结果
	 * @param: @param remark 客源待处理内容
	 * @param: @param recipient 接收人id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@SystemServiceLog(operationType="客源审核处理接口")
	@Override
	public boolean doExamine(String demandId, BusinessExamineTypeEnum type, ExamineStatusEnum result, String remark,Long recipient) {
		Long res = 0l;
		
		JSONObject jasonObject = JSONObject.parseObject(remark);
		Map<String,Object> map = (Map)jasonObject;
		
		//修改需求表
		Map<String,Object> mm = new HashMap<>();
		mm.put("id", demandId);
		ExecutionResult.descFormat(demandId+"", "客源审核处理接口");
		
		if(result.equals(ExamineStatusEnum.EXAMINED)) {
			//type.UPDATESTATUS修改客源需求状态审核，审核通过，修改需求状态;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.UPDATESTATUS)) {
				mm.put("statusId",map.get("statusId"));
				mm.put("status", map.get("statusName"));
				if(CustomerStatusEnum.EFFECTIVE.getCode().equals(map.get("statusId"))) {//无效变有效，需改维护人
					Long applyerId = Long.parseLong(map.get("applyerId").toString());
					Map<String,Object> dept = customerMapper.queryDeptName(applyerId);
					mm.put("maintainId", applyerId);
					mm.put("maintainer", dept.get("realName"));
					mm.put("mainterDeptId", dept.get("deptId"));
					mm.put("mainterDept", dept.get("deptName"));
					mm.put("permissionArea", dept.get("deptId"));
					res = commonSqlServie.commonOperationDatabase(mm, "demand_customerbusinessinfo", "id", false);
					//判断有没有原维护人
					Long mainterRecordid = 0l;
					if(!"".equals(map.get("mainterRecordid").toString()))
						mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
					//经纪人表修改维护人
					res = updateMyAgent(demandId,mainterRecordid,applyerId,AgentTypeEnum.CUSTMAINTAINER.getCode(),AgentTypeEnum.CUSTMAINTAINER.getDesc());
				}else {
					res = commonSqlServie.commonOperationDatabase(mm, "demand_customerbusinessinfo", "id", false);
				}
				
			}
			
			//type.SETPRIVATE转为私盘审核，审核通过，修改房源状态，设置维护人;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.SETPRIVATE)) {
				Long applyerId = Long.parseLong(map.get("applyerId").toString());
				Map<String,Object> dept = customerMapper.queryDeptName(applyerId);
				
				//因为说是不需要变更维护人，所以先注释了
				/*
				mm.put("maintainId", applyerId);
				mm.put("maintainer", dept.get("realName"));
				mm.put("mainterDeptId", dept.get("deptId"));
				mm.put("mainterDept", dept.get("deptName"));
				mm.put("permissionArea", dept.get("deptId"));
				*/
				//盘状态
				mm.put("discStatusId", DiscStatusEnum.PRIVATEDISC.getCode());
				mm.put("discStatus", DiscStatusEnum.PRIVATEDISC.getDesc());
				res = commonSqlServie.commonOperationDatabase(mm, "demand_customerbusinessinfo", "id", false);
				
				//因为说是不需要变更维护人，所以先注释了
				/*
				//判断有没有原维护人
				Long mainterRecordid = 0l;
				if(!"".equals(map.get("mainterRecordid").toString()))
					mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
				//经纪人表修改维护人
				res = updateMyAgent(demandId,mainterRecordid,applyerId,AgentTypeEnum.CUSTMAINTAINER.getCode(),AgentTypeEnum.CUSTMAINTAINER.getDesc());
				*/
			}
			
			//type.CLOSEDISC封盘审核，审核通过，修改需求封盘状态;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.CLOSEDISC)) {
				mm.put("isCloseDisc",map.get("closeCustomerTypeId"));
				mm.put("closePlanteEndDate",map.get("closeCustomerEndTime"));
				res = commonSqlServie.commonOperationDatabase(mm, "demand_customerbusinessinfo", "id", false);
			}
			
			
			//type.TAOBAO淘宝审核，审核通过，修改客源维护人、权限域，,淘宝状态改为非淘宝,经纪人表;未通过，不操作
			if(type.equals(BusinessExamineTypeEnum.TAOBAO)) {
				Long applyerId = Long.parseLong(map.get("applyerId").toString());
				Map<String,Object> dept = customerMapper.queryDeptName(applyerId);
				mm.put("maintainId", applyerId);
				mm.put("maintainer", dept.get("realName"));
				mm.put("mainterDeptId", dept.get("deptId"));
				mm.put("mainterDept", dept.get("deptName"));
				mm.put("permissionArea", dept.get("deptId"));
				mm.put("taobaoStatusId", TaoBaoStatusEnum.NOT_TAOBAO.getCode());
				mm.put("taobaoStatus", TaoBaoStatusEnum.NOT_TAOBAO.getDesc());
				res = commonSqlServie.commonOperationDatabase(mm, "demand_customerbusinessinfo", "id", false);
				//判断有没有原维护人
				Long mainterRecordid = 0l;
				if(!"".equals(map.get("mainterRecordid").toString()))
					mainterRecordid = Long.parseLong(map.get("mainterRecordid").toString());
				//经纪人表修改维护人
				res = updateMyAgent(demandId,mainterRecordid,applyerId,AgentTypeEnum.CUSTMAINTAINER.getCode(),AgentTypeEnum.CUSTMAINTAINER.getDesc());
			}
			
			
		}
		return true;
		
	}
	
	@SystemServiceLog(operationType="判断客源电话能否使用")
	@Override
	public boolean queryCustomerRepeat(String phone,Integer transactionTypeId) {
		ExecutionResult.descFormat(phone, "判断客源电话能否使用");		
		if (StringUtils.isNotEmpty(phone)) {
			boolean phoneBlack=erHousingServiceApi.queryCountPhoneBlack(phone);
			if (phoneBlack) {
				throw new BusinessException("客源电话在黑名单中，禁止使用");
			}
			Map<String, Object> user=UserThreadLocal.get();
			Map<String, Object> param=new HashMap<>();
			param.put("phone", phone);
			param.put("transactionTypeId", transactionTypeId);
			String id=DecisionConfigurateEnum.DECISION_23.getCode();//默认交易类型为求购
			//判断交易类型是否为求租
			if (CustomerTransactionEnum.RENT.getCode().equals(transactionTypeId.toString())) {
				id=DecisionConfigurateEnum.DECISION_32.getCode();
			}
			Map<String, Object> map=decisionConfigurateServiceApi.getDecisionConfigurate(id, user.get("cityCode").toString());
			String val=map.get("val").toString();
			if (RepeatTypeEbum.SELF.getCode().equals(val)) {
				param.put("maintainId", user.get("id"));
			}else if (RepeatTypeEbum.COMPANY.getCode().equals(val)) {
				String dept=deptServiceApi.getDeptIdByCompanyAndCityCode(user.get("companyId").toString(),user.get("cityCode").toString());
				if (StringUtils.isEmpty(dept) ) {
					throw new BusinessException("该用户禁止添加客源");
				}
				param.put("mainterDeptId", dept);
			}else {
				String dept=deptServiceApi.getDeptIdByUserAndDeptType(user.get("id").toString(), val);
				if (StringUtils.isEmpty(dept) ) {
					throw new BusinessException("该用户禁止添加客源");
				}
				param.put("mainterDeptId", dept);
			}
			//查询电话数量
			Integer count=customerMapper.queryCountCustomerByPhone(param);
			boolean result=count>0;
			//电话重复
			if (result) {
				throw new BusinessException("客源电话"+phone+"在"+RepeatTypeEbum.parseOf(val).getDesc()+"重复");
			}
			return true;
		}
		throw new BusinessException(ErrorCodeEnum.UNKNOWN_ERROR);
	}
	@SystemServiceLog(operationType="生成客源绑定码")
	@Override
	public boolean sendBindCode(String demandId) {
		String phone=customerMapper.queryCustomerMainPhone(demandId);
		if (StringUtils.isNotEmpty(phone)) {
			String code=redisGetIdServiceApi.getCustomBindCode(demandId, phone);
			System.out.println("************绑定码*************"+code);
			ExecutionResult.descFormat(demandId, "生成客源绑定码");
			if (StringUtils.isNotEmpty(code)) {
				return true;
			}
			throw new BusinessException("客源源绑定码生成频率过快，请稍后再试!");
		}
		throw new BusinessException("客源业主电话为空，请填写业主电话后再试!");
	}

	@Override
	public Page<Map<String, Object>> queryContractCustomer(String conditions, int page, int limit) {
		
		if (StringUtils.isNotEmpty(conditions)) {
			PageHelper.startPage(page, limit);
			Map<String, Object> param = JSON.parseObject(conditions, Map.class);
			if (param.get("transactionTypeId")==null) {
				throw new BusinessException("需求类型不能为空");
			}
			String until="万元";
			if (CustomerTransactionEnum.BUY.getCode().equals(param.get("transactionTypeId").toString())) {
				if (param.get("minPrice")!=null) {
					Integer minPrice = Integer.parseInt(param.get("minPrice").toString());
					param.put("minPrice", minPrice*1000000);
				}
				if (param.get("maxPrice")!=null) {
					Integer minPrice = Integer.parseInt(param.get("maxPrice").toString());
					param.put("maxPrice", minPrice*1000000);
				}
				dataAuthHelper.auth(MenuEnum.MENU_96.getCode(), UserThreadLocal.get().get("id").toString());
			}
			if (CustomerTransactionEnum.RENT.getCode().equals(param.get("transactionTypeId").toString())) {
				until="元";
				if (param.get("minPrice")!=null) {
					Integer minPrice = Integer.parseInt(param.get("minPrice").toString());
					param.put("minPrice", minPrice*100);
				}
				if (param.get("maxPrice")!=null) {
					Integer minPrice = Integer.parseInt(param.get("maxPrice").toString());
					param.put("maxPrice", minPrice*100);
				}
				dataAuthHelper.auth(MenuEnum.MENU_97.getCode(), UserThreadLocal.get().get("id").toString());
			}
						
			Page<Map<String, Object>> pageInfo = customerMapper.queryContractCustomer(param);
			if (pageInfo.getTotal()>0) {			
				for (Map<String, Object> map : pageInfo.getResult()) {

					if (map.get("taobaoStatusId")!=null&&"1".equals(map.get("taobaoStatusId").toString())) {
						map.put("status", "淘宝池");
					}else {
						map.put("status", map.get("discStatus"));
					}
					String region = "";
					if (map.get("areaNames")!=null&&!"".equals(map.get("areaNames").toString())) {
						region+=map.get("areaNames").toString();
					}
					if (map.get("regionNames")!=null&&!"".equals(map.get("regionNames").toString())) {
						if (!region.equals("")) {
							region+=",";
						}
						region+=map.get("regionNames").toString();
					}
					if (map.get("communityNames")!=null&&!"".equals(map.get("communityNames").toString())) {
						if (!region.equals("")) {
							region+=",";
						}
						region+=map.get("communityNames").toString();
					}
					map.put("region", region);
					Integer minPrice=0;
					Integer maxPrice=0;
					Integer minSpace=0;
					Integer maxSpace=0;
					if (map.get("minPrice")!=null) {
						minPrice=Integer.parseInt(map.get("minPrice").toString());
					}
					if (map.get("maxPrice")!=null) {
						maxPrice=Integer.parseInt(map.get("maxPrice").toString());
					}
					if (map.get("minSpace")!=null) {
						minSpace=Integer.parseInt(map.get("minSpace").toString());
					}
					if (map.get("maxSpace")!=null) {
						maxSpace=Integer.parseInt(map.get("maxSpace").toString());
					}
					
					if (minPrice>0&&maxPrice>0) {
						map.put("price", minPrice+"-"+maxPrice+until);
					}
					if (minPrice>0&&maxPrice==0) {
						map.put("price", minPrice+until+"以上");
					}
					if (minPrice==0&&maxPrice>0) {
						map.put("price", maxPrice+until+"以下");
					}
					
					if (minSpace>0&&maxSpace>0) {
						map.put("space", minSpace+"-"+maxSpace+"平米");
					}
					if (minSpace>0&&maxSpace==0) {
						map.put("space", minSpace+"平米以上");
					}
					if (minSpace==0&&maxSpace>0) {
						map.put("space", maxSpace+"平米以下");
					}	
				}				
			}
			return pageInfo;
		}
		
		return null;
	}

	@Override
	public Page<Map<String, Object>> queryMyPrivateGuest(int transactionTypeId,int page, int limit) {
		PageHelper.startPage(page, limit);
		Integer userId=Integer.parseInt(UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> result=customerMapper.queryMyPrivateGuest(transactionTypeId,userId);
		return result;
	}

	@Override
	public boolean deleteImg(String demandId, String path) {
		Boolean result=FtpUtil.deleteFile(demandId, FtpSourceTypeEnum.FTP_SOURCE_KY.getCode(), FtpFileTypeEnum.FTP_FILE_TP.getCode(), path);
		return result;
	}
}
