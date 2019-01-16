/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealController.java   
 * @Package com.bashiju.deal.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月15日 上午9:58:40   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.deal.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.api.TransferProcessServiceApi;
import com.bashiju.customer.pojo.SettlementTypeEnum;
import com.bashiju.customer.pojo.TransferScheduleStatusEnum;
import com.bashiju.deal.global.IdWorker;
import com.bashiju.deal.service.DealCommissionRecordService;
import com.bashiju.deal.service.DealService;
import com.bashiju.enums.CommissionProjEnum;
import com.bashiju.enums.CommissionStatusEnum;
import com.bashiju.enums.DealTransferStatusEnum;
import com.bashiju.enums.DealTypeEnum;
import com.bashiju.enums.FtpFileTypeEnum;
import com.bashiju.enums.FtpSourceTypeEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.TransferProcessEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.bashiju.utils.util.FtpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**   
 * @ClassName:  DealController   
 * @Description:成交管理控制器 
 * @author: yangz
 * @date:   2018年6月15日 上午9:58:40   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="deal")
public class DealController extends BaseController {
	
	@Autowired
	private DealService dealService;
	
	@Autowired
	private DealCommissionRecordService commissionService;
	
	@Autowired
	private TransferProcessServiceApi transferProcessService;
	/**
	 * 基础服务接口
	 */
	@Autowired
	private RedisGetIdServiceApi mRedisGetIdServiceApi;
	/**
	 * 
	 * @Description: 进入成交管理页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="dealPage")
	public ModelAndView dealPage(HttpServletRequest request,HttpServletResponse response) {
		String pageName = "businessDeal/businessDeal";//默认为买卖成交
		String dt = request.getParameter("dt");
		if(!StringUtils.isEmpty(dt) && DealTypeEnum.LEASEDEAL.getCode().equals(dt.toString())) //租赁成交
			pageName = "leaseDeal/leaseDeal";
		else if(!StringUtils.isEmpty(dt) && DealTypeEnum.NEWHOUSEDEAL.getCode().equals(dt.toString()))//一手房成交
			pageName = "newHouseDeal/newHouseDeal";
		else
			dt = DealTypeEnum.BUSINESSDEAL.getCode();
		
		
		ModelAndView mv = this.getModelAndView(request, response, pageName);
		mv.addObject("dealType", dt);
		mv.addObject("houseUsesEnum", HouseUsesEnum.enumMap);
		mv.addObject("houseUsesEnumJson", JSON.toJSON(HouseUsesEnum.enumMap));
		mv.addObject("transferScheduleStatus",DealTransferStatusEnum.map);
		mv.addObject("transferScheduleStatusJson",JSON.toJSON(DealTransferStatusEnum.map));
//		TODO:合同状态还没有
//		mv.addObject("合同状态", 合同状态);
		return mv;
	}
	/**
	 * 页面获取成交业务的相关枚举值
	 * @Title: queryDealBussinessEnum
	 * @author: zuoyuntao  
	 * @Description:页面获取成交业务的相关枚举值 
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryDealEnum")
	@ResponseBody
	public Object queryDealBussinessEnum() {
		Map<String,Object> retObj = new HashMap<String,Object>();
		retObj.put("houseUsesEnum", HouseUsesEnum.enumList);
		retObj.put("dealStatusEnum", DealTransferStatusEnum.enumList);
		//TODO：合同还没有，还没定义
		retObj.put("contractEnum", null);
		return retObj;
	}
	
	/**
	 * 
	 * @Description: 条件查询成交信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getData")
	@ResponseBody
	public Object getData(HttpServletRequest request,HttpServletResponse response) {
		String dealType = request.getParameter("dealType");
		if(StringUtils.isEmpty(dealType)) {
			throw new BusinessException("请指定要查询的成交类型");
		}
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("dealType", dealType);
		map.put("transferStatus", request.getParameter("transferStatus"));//过户进度
		map.put("minBuildSpace", request.getParameter("minBuildSpace"));//最小面积
		map.put("maxBuildSpace", request.getParameter("maxBuildSpace"));//最大面积
		map.put("minprice", request.getParameter("minprice"));//最低价格
		map.put("maxprice", request.getParameter("maxprice"));//最高价格
		map.put("agreementStatus", request.getParameter("agreementStatus"));//状态 :0--有效，1--结案，2--违约
		map.put("houseUsesId", request.getParameter("houseUsesId"));//房源用途编号
		map.put("houseUses", request.getParameter("houseUses"));//房源用途
		map.put("beginDealTime", request.getParameter("beginDealTime"));//开始成交时间
		map.put("endDealTime", request.getParameter("endDealTime"));//截止成交时间
		map.put("beginCommissionFinishTime", request.getParameter("beginCommissionFinishTime"));//佣金结案时间
		map.put("endCommissionFinishTime", request.getParameter("endCommissionFinishTime"));//佣金结案时间
		map.put("beginFinishCaseTime", request.getParameter("beginFinishCaseTime"));//过户结案时间
		map.put("endFinishCaseTime", request.getParameter("endFinishCaseTime"));//过户结案时间
		map.put("traderId", request.getParameter("traderId"));//成交人
		map.put("traderDeptId", request.getParameter("traderDeptId"));//成交人所在部门
		map.put("referenceId", request.getParameter("referenceIdId"));//权证人
		map.put("referenceDeptId", request.getParameter("referenceDeptId"));//权证人所在部门
		map.put("operatorId", request.getParameter("operatorId"));//操作人id(录入人)
		map.put("operatorDeptId", request.getParameter("operatorDeptId"));//操作人所在部门id
		map.put("dealId", request.getParameter("dealId"));//成交编号
		map.put("agreementId", request.getParameter("agreementId"));//合同编号
		map.put("houseId", request.getParameter("houseId"));//房源编号；
		map.put("demandId", request.getParameter("demandId"));//需求编号
		map.put("owner", request.getParameter("owner"));//业主名称(模糊)
		map.put("custName", request.getParameter("custName"));//客户名称(模糊)
		map.put("communityName", request.getParameter("communityName"));//小区名称(模糊)
		map.put("buildingHouseName", request.getParameter("buildingHouseName"));//门牌号(模糊)
		Page<Map<String,Object>> pages = dealService.queryDeals(map, pageNum, pageSize);
		Map<String,Object> result = getPageResult(pages);
		return result;
	}
	
	/**
	 * 
	 * @Description: 进入详情页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="dealDetailPage")
	public ModelAndView dealDetailPage(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String dealType = request.getParameter("dealType");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(dealType))
			throw new BusinessException("成交类型不允许为空");
		else if(!DealTypeEnum.enumMap.containsKey(dealType))
			throw new BusinessException("成交类型无效");
		Map<String,Object> deal = this.dealService.getDeals(id,dealType);
		if(deal==null || deal.size()<=0)
			throw new BusinessException("未找到有效的成交信息");
		String path = "businessDeal/businessDealDetail";
		if(DealTypeEnum.LEASEDEAL.getCode().equals(deal.get("dealType")))
			path = "leaseDeal/leaseDealDetail";
		else if(DealTypeEnum.NEWHOUSEDEAL.getCode().equals(deal.get("dealType")))
			path = "newHouseDeal/newHouseDealDetail";
		ModelAndView mv = this.getModelAndView(request, response, path);
		Map<?,?> user = UserThreadLocal.get();
		List<Map<String,Object>> transfers = transferProcessService.queryTransferProcessInfo(user.get("cityCode").toString()
				, user.get("companyId").toString(), user.get("id").toString());
		mv.addObject("deal", deal);
		mv.addObject("dealType", DealTypeEnum.enumMap);
		List<Map<String,Object>> divids = this.dealService.queryDividenInfos(id);
		System.out.println(JSONArray.toJSONString(divids));
		mv.addObject("divids", divids);//分成记录
		mv.addObject("commissions", this.commissionService.queryCommissionByDealId(id));//佣金记录
		mv.addObject("followRecords", this.dealService.queryFollowRecordByDealId(id));//跟进记录
		mv.addObject("payRecords", this.dealService.queryPayRecordsByDealId(id));//跟进记录
		mv.addObject("transfers", transfers);
		mv.addObject("commissionStatus", CommissionStatusEnum.map);
		mv.addObject("commissionProj", CommissionProjEnum.enumMap);
		mv.addObject("settlementType", SettlementTypeEnum.map);
//		mv.addObject("agentDivideType", AgentTypeEnum.enumMap);
		mv.addObject("transferStatus", DealTransferStatusEnum.map);
		return mv;
	}
	
	/**
	 * 更新成交信息 
	 * @Title: updateDealTransaction
	 * @author: zuoyuntao  
	 * @Description:更新成交信息   
	 * @param jsonData 包含dealTime:成交日期,id:成交编号,supplementInfo:补充条款
	 * @return      
	 * Object
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateDealTransaction")
	@ResponseBody
	public Object updateDealTransaction(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("传入参数为空");
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap = (Map<String,Object>) JSON.parse(jsonData);
		dealService.updateDealTransaction(paramMap);
		return BashijuResult.ok();
	}
	
	/**
	 * 获取成交明细所有页面数据 
	 * @Title: queryDetailAllPage
	 * @author: zuoyuntao  
	 * @Description:获取成交明细所有页面数据
	 * @param id 成交编号
	 * @param dealType:成交类型
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryDetailAllPage")
	@ResponseBody
	public Object queryDetailAllPage(String id,String dealType) {
		if(StringUtils.isEmpty(id)) {
			throw new BusinessException("成交编号不允许为空");
		}
		if(StringUtils.isEmpty(dealType)) {
			throw new BusinessException("成交类型不允许为空");
		}else if(!DealTypeEnum.enumMap.containsKey(dealType)) {
			throw new BusinessException("成交类型无效");
		}
		Map<String,Object> deal = this.dealService.getDeals(id,dealType);
		if(deal==null || deal.size()<=0) {
			throw new BusinessException("未找到有效的成交信息");
		}
		Map<?,?> user = UserThreadLocal.get();
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<Map<String,Object>> transfers = transferProcessService.queryTransferProcessInfo(user.get("cityCode").toString()
				, user.get("companyId").toString(), user.get("id").toString());
		retMap.put("deal", deal);
		retMap.put("dealType", DealTypeEnum.enumList);
		retMap.put("divids", dealService.queryDividenInfos(id));//分成记录
		retMap.put("commissions", this.commissionService.queryCommissionByDealId(id));//佣金记录
		retMap.put("followRecords", this.dealService.queryFollowRecordByDealId(id));//跟进记录
		retMap.put("payRecords", this.dealService.queryPayRecordsByDealId(id));//跟进记录
		retMap.put("transfers", transfers);
		retMap.put("commissionStatus", CommissionStatusEnum.map);
		retMap.put("commissionProj", CommissionProjEnum.enumMap);
		retMap.put("settlementType", SettlementTypeEnum.map);
		retMap.put("transferStatus", DealTransferStatusEnum.map);
		//统计金融类佣金信息(price:应收佣金，actualPrice：实收佣金)
		retMap.put("financeCommission", this.dealService.statisticFinanceCommissions(id));
		return retMap;
	}
	
	/**
	 * 根据ID和成交类型查询成交信息（成交详细页面使用） 
	 * @Title: queryDealInfoByIdAndType
	 * @author: zuoyuntao  
	 * @Description:根据ID和成交类型查询成交信息（成交详细页面使用）
	 * @param id 成交ID
	 * @param dealType 成交类型
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="queryDealInfoByIdAndType")
	@ResponseBody
	public Object queryDealInfoByIdAndType(String id,String dealType) {
		Map<String,Object> deal = this.dealService.getDeals(id,dealType);
		return deal;
	}
	/**
	 * 
	 * @Description: 成交违约   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="dealBreach")
	@ResponseBody
	public BashijuResult dealBreach(HttpServletRequest request,HttpServletResponse response) {
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		boolean result = this.dealService.dealBreach(dealId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("违约操作失败");
	}
	
	/**
	 * 
	 * @Description: 取消成交违约   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="cancelDealBreach")
	@ResponseBody
	public BashijuResult cancelDealBreach(HttpServletRequest request,HttpServletResponse response) {
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		boolean result = this.dealService.cancelDealBreach(dealId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("取消违约操作失败");
	}
	
	/**
	 * @Description: 保存佣金记录缴费信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateDealCommission")
	@ResponseBody
	public BashijuResult updateDealCommission(HttpServletRequest request,HttpServletResponse response) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		boolean result = this.dealService.updateDealCommission(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	

/**----------------------------过户流程   开始--------------------------------**/	
	
	/**
	 * @Description: 获取过户流程信息
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="getTransferProcessDetails")
	@ResponseBody
	public Object getTransferProcessDetails(HttpServletRequest request) {
		String confId = request.getParameter("confId");
		if(StringUtils.isEmpty(confId))
			throw new BusinessException("请选择过户流程方案");
		List<Map<String,Object>> transferDetails = transferProcessService.queryTransferDetailInfo(confId, 
				TransferProcessEnum.TYPE_PROCESS_SET.getCode());
		return JSONArray.toJSON(transferDetails);
	}
	
	/**
	 * @Description: 启动过户流程
	 * @param request
	 * @return: Object
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveTransferProcess")
	@ResponseBody
	public BashijuResult saveTransferProcess(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> transferDetails = (List<Map<String, Object>>) JSONArray.parse(jsonData);
		Map<?,?> user = UserThreadLocal.get();
		for(Map<String,Object> transfer : transferDetails) {
			transfer.put("dealId", dealId);
			transfer.put("status", TransferScheduleStatusEnum.UNFINISHED.getCode());
			transfer.put("permissionArea", user.get("deptId"));
			transfer.put("operatorId", user.get("id"));
		}
		boolean result = this.dealService.saveTransferProcess(dealId,transferDetails);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 
	 * @Description: 加载过户进度信息页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="transferSchedulePage")
	public ModelAndView transferSchedulePage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "businessDeal/transferSchedule");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		List<Map<String,Object>> transferList = this.dealService.queryTransferSchedules(dealId);
		mv.addObject("transferList", transferList);
		mv.addObject("transferScheduleStatus", TransferScheduleStatusEnum.map);
		mv.addObject("dealId", dealId);
		return mv;
	}
	
	/**
	 * 
	 * @Description: 加载过户进度信息页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="queryTransferSchedulePage")
	@ResponseBody
	public Object queryTransferSchedulePage(String dealId) {
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		List<Map<String,Object>> transferList = this.dealService.queryTransferSchedules(dealId);
		return transferList;
	}
	
	/**
	 * @Description: 调整过户进度   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="updateTransferSchedule")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public BashijuResult updateTransferSchedule(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要调整的进度信息");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("transferId", UserThreadLocal.get().get("id"));
		map.put("transfer", UserThreadLocal.get().get("realName"));
		if(map.containsKey("actualFinishTime") && "".equals(map.get("actualFinishTime")))
			map.remove("actualFinishTime");
		boolean result = this.dealService.updateTransferSchedule(dealId,map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * @Description: 删除过户进度   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="deleteTransferSchedule")
	@ResponseBody
	public BashijuResult deleteTransferSchedule(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的过户进度信息");
		boolean result = this.dealService.deleteTransferScheduleById(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * @Description: 取消过户进度   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="cancelTransferSchedule")
	@ResponseBody
	public BashijuResult cancelTransferSchedule(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("请选择要取消过户的成交信息");
		boolean result = this.dealService.cancelTransferSchedule(dealId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * @Description: 批量更新过户信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="batchUpdateTransferScheduleTime")
	@ResponseBody
	public BashijuResult batchUpdateTransferScheduleTime(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要更新的信息");
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONObject.parse(jsonData);
		boolean result = this.dealService.batchUpdateTransferScheduleTime(list);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * @Description: 新增过户信息  
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addTransferSchedule")
	@ResponseBody
	public BashijuResult addTransferSchedule(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要新增的进度信息");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("dealId", dealId);
		map.put("status", TransferScheduleStatusEnum.UNFINISHED.getCode());
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		map.put("operator", UserThreadLocal.get().get("realName"));
		boolean result = this.dealService.saveTransferSchedule(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	
	/**
	 * 根据城市、当前用户查询所有过户流程配置信息
	 * @Title: queryTransferProcessInfo
	 * @author: zuoyuntao  
	 * @Description:根据城市、当前用户查询所有过户流程配置信息
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryAllTransferProcessInfo")
	@ResponseBody
	public Object queryAllTransferProcessInfo() {
		Map<?,?> user = UserThreadLocal.get();
		List<Map<String, Object>> retObj = transferProcessService.queryTransferProcessInfo(user.get("cityCode").toString()
				, user.get("companyId").toString(), user.get("id").toString());
		return retObj;
	}
	
	/**-------------------------过户进度  结束---------------------------------**/
	
	
	/**-------------------------中介类分成  开始---------------------------------**/
	
	/**
	 * @Description: 根据成交编号查询分成信息  
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="queryDivideInfos")
	@ResponseBody
	public Object queryDivideInfos(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		List<Map<String,Object>> list = this.dealService.queryDividenInfos(dealId);
		return JSONArray.toJSON(list);
	}
	
	/**
	 * @Description: 保存修改后的分成配置信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveDivideInfos")
	@ResponseBody
	public BashijuResult saveDivideInfos(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的分成配置信息");
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONObject.parse(jsonData);
		boolean result = this.dealService.saveDivideInfos(dealId,list);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	
	
	/**-------------------------中介类分成  结束---------------------------------**/
	
/**-------------------------金融类分成  开始---------------------------------**/
	
	/**
	 * @Description: 根据成交编号查询金融类分成信息  
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="queryFinanceDivideInfos")
	@ResponseBody
	public Object queryFinanceDivideInfos(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		List<Map<String,Object>> list = this.dealService.queryFinanceDividenInfos(dealId);
		return JSONArray.toJSON(list);
	}
	
	/**
	 * @Description: 保存修改后的金融类分成配置信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveFinanceDivideInfos")
	@ResponseBody
	public BashijuResult saveFinanceDivideInfos(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的分成配置信息");
		List<Map<String,Object>> list = (List<Map<String,Object>>) JSONObject.parse(jsonData);
		boolean result = this.dealService.saveFinanceDivideInfos(dealId,list);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	
	
	/**-------------------------金融类分成  结束---------------------------------**/
	
	
	/**-------------------------佣金记录  开始---------------------------------**/
	
	/**
	 * 根据成交编号查询佣金记录
	 * @Title: queryCommissionRecordByDealId
	 * @author: zuoyuntao  
	 * @Description:根据成交编号查询佣金记录   
	 * @param dealId      
	 * void
	 */
	@RequestMapping(value="queryCommissionRecordByDealId")
	@ResponseBody
	public Object queryCommissionRecordByDealId(String dealId){
		return this.commissionService.queryCommissionByDealId(dealId);
	}
	
	/**
	 * 获取票据号
	 * @Title: getBillNoForCommission
	 * @author: zuoyuntao  
	 * @Description:获取票据号   
	 * @return      
	 * String 
	 */
	@RequestMapping(value="getBillNoForCommission")
	@ResponseBody
	public String getBillNoForCommission() {
		return mRedisGetIdServiceApi.getTicketId();
	}
	/**
	 * @Description: 收取佣金记录信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="reciverDealCommission")
	@ResponseBody
	public BashijuResult reciverDealCommission(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的佣金信息");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		
		boolean result = this.dealService.reciverDealCommission(dealId, map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	
	/**
	 * @Description: 取消佣金收取信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="cancelCommission")
	@ResponseBody
	public BashijuResult cancelCommission(HttpServletRequest request) {
		String id = request.getParameter("id");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要取消的佣金信息");
		boolean result = this.dealService.cancelCommission(dealId,id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("取消佣金收取信息失败");
	}
	
	
	/**
	 * @Description: 根据成交编号查询佣金信息  
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="queryCommissionInfos")
	@ResponseBody
	public Object queryCommissionInfos(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		List<Map<String,Object>> list = this.commissionService.queryCommissionByDealId(dealId);
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>(0);
		for(int i=0;i<list.size();i++) {
			//TODO:驳回的不知道允不允许操作
			if(CommissionStatusEnum.PLAN.getCode().equals(list.get(i).get("status"))) {
				newList.add(list.get(i));
			}
		}
		return JSONArray.toJSON(newList);
	}
	
	/**
	 * @Description: 保存佣金计划信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveCommissionnfos")
	@ResponseBody
	public BashijuResult saveCommissionnfos(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的佣金计划信息");
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONObject.parse(jsonData);
		boolean result = this.dealService.saveDealCommission(dealId, list);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("保存佣金计划信息失败");
	}
	
	/**-------------------------佣金记录  结束---------------------------------**/
	
	/**-------------------------文件扫描件  开始---------------------------------**/
	
	/**
	 * @Description: 图片上传  
	 * @param request
	 * @return: BashijuResult
	 * @throws Exception 
	 */
	@RequestMapping(value="uploadImgs")
	@ResponseBody
	public BashijuResult uploadImgs(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	    List<MultipartFile> fileList = multipartRequest.getFiles("file");  
	    String dealId = request.getParameter("dealId");
	    String dealType = request.getParameter("dealType");
	    String imageType = request.getParameter("attachType");
	    if(StringUtils.isEmpty(dealId))
	    	throw new BusinessException("成交编号不允许为空");
	    if(StringUtils.isEmpty(dealType))
	    	throw new BusinessException("成交类型不允许为空");
	    if(StringUtils.isEmpty(imageType))
	    	throw new BusinessException("文件类型不允许为空");
	    List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
	    Map<?,?> user = UserThreadLocal.get();
	    for(MultipartFile file : fileList) {
	    	Map<String,Object> fl = new HashMap<String,Object>();
	    	String filePath = FtpUtil.uploadFile(file, dealId,FtpSourceTypeEnum.FTP_SOURCE_FY.getCode(),FtpFileTypeEnum.FTP_FILE_TP.getCode(), true);
	    	fl.put("dealId", dealId); 
	    	fl.put("code", new IdWorker(666).createId());
	    	fl.put("companyId", user.get("companyId"));
	    	fl.put("companyName", user.get("companyName"));
	    	fl.put("path", filePath);
	    	fl.put("attachType", imageType);
	    	fl.put("permissionArea", user.get("deptId"));
	    	fl.put("operatorId", user.get("id"));
	    	files.add(fl);
	    }
	    boolean result = this.dealService.saveAttachments(dealType,files);
	    if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("上传图片失败");
	}
	
	/**
	 * @Description: 条件查询附件信息  
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="getAttachData")
	@ResponseBody
	public Object getAttachData(HttpServletRequest request) {
		String dealId = request.getParameter("dealId");
		String attachType = request.getParameter("attachType");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(attachType) || attachType.equals("0"))
			attachType = null;
		List<Map<String,Object>> list = this.dealService.queryAttachData(dealId,attachType);
//		if(list==null)
//			return null;
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("count", list==null?0:list.size());
		map.put("data", list==null?null:list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * @Description: 批量下载图片信息
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return: void
	 */
	@RequestMapping(value = "downloadImage")
	public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String jsonData = request.getParameter("jsonData");
			if (StringUtils.isEmpty(jsonData))
				throw new BusinessException("没有要下载的数据");
			String fileName = request.getParameter("dealId");
			String[] paramsArr = jsonData.split(",");
			if (paramsArr.length == 0)
				throw new BusinessException("没有要下载的数据");
			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);// 设置为下载application/x-download
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			for (String path : paramsArr) {
				if (path != null) {
					String name = path.substring(path.lastIndexOf("/") + 1);
					// 网络请求所需变量
					InputStream in = null;
					URL url = new URL(path);
					// 根据Url打开地址，以utf-8编码的形式返回输入流
					in = url.openStream();
					ZipEntry ze = new ZipEntry(name);
					zos.putNextEntry(ze);

					byte[] data = new byte[1024];
					int length = in.read(data);
					while (length != -1) {
						zos.write(data, 0, length);
						length = in.read(data);
					}
					zos.closeEntry();
					in.close();
				}
			}
			if (zos != null) {
				zos.flush();
				zos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 删除图片信息  
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="deleteAttach")
	@ResponseBody
	public BashijuResult deleteAttach(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要删除的数据");
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONArray.parse(jsonData);
		if(list.size()<=0)
			throw new BusinessException("没有要删除的数据");
		String[] str = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			str[i]=list.get(i).get("id").toString();
		}
		boolean result = this.dealService.deleteAttach(str);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("删除图片信息");
	}
	
	/**-------------------------文件扫描件  结束---------------------------------**/
	
	/**-------------------------跟进  开始---------------------------------**/
	/**
	 * 根据成交ID查询跟进记录
	 * @Title: queryFollowRecordByDealId
	 * @author: zuoyuntao  
	 * @Description:根据成交ID查询跟进记录   
	 * @param dealId 成交ID
	 * @param page 当前页
	 * @param limit 最大条数
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryFollowRecordByDealId")
	@ResponseBody
	public Object queryFollowRecordByDealId(String dealId,int page,int limit) {
		Page<Map<String, Object>> pageObj = this.dealService.queryFollowRecordByDealId(dealId,page,limit);
		if(null != pageObj) {
			Map<String,Object> result = this.getPageResult(pageObj);
			return result;
		}else {
			return new HashMap<String,Object>();
		}
	}
	
	/**
	 * 根据成交ID查询跟进记录
	 * @Title: queryFollowRecordByDealIdNoPage
	 * @author: zuoyuntao  
	 * @Description:根据成交ID查询跟进记录(不带分页)   
	 * @param dealId 成交ID
	 * @param page 当前页
	 * @param limit 最大条数
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryFollowRecordByDealIdNoPage")
	@ResponseBody
	public Object queryFollowRecordByDealIdNoPage(String dealId) {
		Page<Map<String, Object>> pageObj = this.dealService.queryFollowRecordByDealId(dealId,0,0);
		if(null != pageObj) {
			return pageObj;
		}else {
			return new HashMap<String,Object>();
		}
	}
	
	/**
	 * @Description: 删除跟进记录  
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delFollowRecord")
	@ResponseBody
	public BashijuResult delFollowRecord(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的跟进记录");
		boolean result = this.dealService.delFollowRecord(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("删除跟进信息失败");
	}
	
	/**
	 * @Description: 保存跟进信息  
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="savefollowRecord")
	@ResponseBody
	public BashijuResult savefollowRecord(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的跟进信息");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		
		Map<String,Object> record = new HashMap<String,Object>(0);//跟进记录
		Map<String,Object> remind = null;
		Map<?,?> user = UserThreadLocal.get();
		record.put("dealId", dealId);
		record.put("companyId", user.get("companyId"));
		record.put("companyName", user.get("companyName"));
		record.put("followTypeId", map.get("followTypeId"));
		record.put("followType", map.get("followType"));
		record.put("followerId", user.get("id"));
		record.put("followTime", new Date());
		record.put("content", map.get("content"));
		record.put("permissionArea", user.get("deptId"));
		record.put("operatorId", user.get("id"));
		if(!StringUtils.isEmpty(map.get("reminderId").toString()) && !StringUtils.isEmpty(map.get("remindTime").toString())) {
			remind = new HashMap<String,Object>(0);//提醒信息
			remind.put("sendPeopleId", user.get("id"));
			remind.put("sendPeopleName", user.get("realName"));
			remind.put("receivePeopleId", map.get("reminderId"));
			remind.put("receivePeopleName", map.get("reminder"));
			remind.put("remindTime", map.get("remindTime"));
			remind.put("remindType", 5);//成交提醒
			remind.put("status", 0);//未读
			remind.put("remindContent", map.get("remindContent"));
			remind.put("status", 0);//未读
		}
		
		boolean result = this.dealService.savefollowRecordAndRemindInfo(record,remind);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("保存跟进信息失败");
	}
	
	/**-------------------------跟进  结束---------------------------------**/
	
	/**-------------------------代收付款  开始---------------------------------**/
	/**
	 * 根据成交ID查询代收付款列表数据信息
	 * @Title: queryPayRecordsByDealId
	 * @author: zuoyuntao  
	 * @Description:根据成交ID查询代收付款列表数据信息
	 * @param dealId 成交ID
	 * @return      
	 * Object JSON 格式为：
	 */
	@RequestMapping(value="queryPayRecordsByDealId")
	@ResponseBody
	public Object queryPayRecordsByDealId(String dealId) {
		return this.dealService.queryPayRecordsByDealId(dealId);
	}
	
	/**
	 * @Description: 保存代收款信息  
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="savePayRecordd")
	@ResponseBody
	public BashijuResult savePayRecordd(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		String dealId = request.getParameter("dealId");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的代收款信息");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		if(map.get("price")!=null) {
			Long price = Long.parseLong(map.get("price").toString())*100;
			map.put("price", price);
		}
		map.put("dealId", dealId);
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		map.put("recorderId", UserThreadLocal.get().get("id"));
		map.put("recorderName", UserThreadLocal.get().get("realName"));
		map.put("recordTime", new Date());
		boolean result = this.dealService.savePayRecordd(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("保存代收款信息失败");
	}
	
	/**
	 * 删除代收付款记录
	 * @Description: 删除代收付款记录  
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delPayRecordd")
	@ResponseBody
	public BashijuResult delPayRecordd(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的代收付款记录");
		boolean result = this.dealService.delPayRecordd(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("删除代收款信息失败");
	}
	
	/**-------------------------代收付款  结束---------------------------------**/
	
	/**
	 * 确认分成操作(确认分成后，不允许再次修改分成信息)  
	 * @Description: 确认分成操作(确认分成后，不允许再次修改分成信息)   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="sureDividInfos")
	@ResponseBody
	public BashijuResult sureDividInfos(HttpServletRequest request,HttpServletResponse response) {
		String dealId = request.getParameter("dealId");
		String dealType = request.getParameter("dealType");
		String moneyType = request.getParameter("moneyType");//佣金类型：0--中介类费用，1--金融类费用
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(dealType) || !DealTypeEnum.enumMap.containsKey(dealType)) 
			throw new BusinessException("成交类型不正确");
		if(StringUtils.isEmpty(moneyType) || (!"1".equals(moneyType) && !"0".equals(moneyType)))
			throw new BusinessException("佣金类型为空或者佣金类型无效");
		boolean result = this.dealService.saveSureDividInfos(dealId,dealType,moneyType);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("确认分成操作失败");
	}
	
	/**
	 * 删除佣金记录
	 * @Description: 删除佣金记录
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delCommissions")
	@ResponseBody
	public BashijuResult delCommissions(HttpServletRequest request,HttpServletResponse response) {
		String commissionId = request.getParameter("commissionId");
		String dealId = request.getParameter("dealId");
		String commissionType  = request.getParameter("commissionType");
		if(StringUtils.isEmpty(dealId))
			throw new BusinessException("成交编号不允许为空");
		if(StringUtils.isEmpty(commissionId))
			throw new BusinessException("佣金记录编号不允许为空");
		if(StringUtils.isEmpty(commissionType))
			throw new BusinessException("佣金类型不允许为空");
		int comType = 0;//默认金融类佣金
		comType = Integer.parseInt(commissionType);
		boolean result = this.dealService.delCommissions(dealId,commissionId,comType);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException("删除佣金记录失败");
		
	}
}
