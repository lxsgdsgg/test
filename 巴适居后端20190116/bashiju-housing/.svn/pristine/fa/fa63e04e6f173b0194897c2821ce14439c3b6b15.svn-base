package com.bashiju.housing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.housing.pojo.KeyStatusEnum;
import com.bashiju.housing.service.HsKeyService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HsKeyController   
 * @Description:钥匙管理控制层
 * @author: wangpeng
 * @date:   2018年6月20日 上午10:08:43   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="HsKey")
public class HsKeyController extends BaseController {
	@Autowired
	private HsKeyService hsKeyService;
	/**
	 * 
	 * @Title: enterHsKeyPage   
	 * @Description: 进入钥匙管理页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterHsKeyPage")
	public ModelAndView enterHsKeyPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=getModelAndView(request, response, "hsKey/hsKey");
		mv.addObject("houseStatus", HouseStatusEnum.enumList);
		mv.addObject("KeyStatus",KeyStatusEnum.enumMap);
		mv.addObject("KeyStatuss",JSONObject.toJSON(KeyStatusEnum.enumMap));
		return mv;
	}
	/***
	 * 钥匙管理下拉数据
	 * @Title: keyManagementDropdown   
	 * @Description: 钥匙管理下拉数据
	 * @return: Object
	 */
	@RequestMapping(value="keyManagementDropdown")
	@ResponseBody
	public Object keyManagementDropdown() {
		Map<String,Object> map = new HashMap<>();
		map.put("houseStatus", HouseStatusEnum.enumList);
		map.put("keyStatus", KeyStatusEnum.enumList);
		return map;
	}
 	
	/**
	 * 
	 * @Title: queryHsKeyData   
	 * @Description: 查询钥匙信息  
	 * @param houseStatus 房源状态
	 * @param reciver 收钥匙人
	 * @param keyStatus 钥匙状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param buildingsName 座栋名称
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="queryHsKeyData")
	@ResponseBody
	public Object queryHsKeyData(String houseStatus,String reciverId,String keyStatus,String beginTime,String endTime,String buildingsName,int page,int limit){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("houseStatus", houseStatus);
		paramMap.put("reciverId", reciverId);
		paramMap.put("keyStatus", keyStatus);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		paramMap.put("buildingsName", buildingsName);
		Page<Map<String,Object>> pages=hsKeyService.queryHsKeyData(paramMap, page, limit);
		Map<String,Object>map=getPageResult(pages);
		return JSONArray.toJSON(map);
	}
	/**
	 * 
	 * @Title: HsKeySeal   
	 * @Description:钥匙封存状态
	 * @param request
	 * @param id 钥匙Id
	 * @return      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="HsKeySeal")
	@ResponseBody
	public BashijuResult HsKeySeal(String keyStatus,String id){
		if(StringUtils.isEmpty(keyStatus))
			throw new BusinessException("当前状态不能为空");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择");
		Map<String,Object> map = new HashMap<>();
		if(KeyStatusEnum.SEAL.getCode().equals(keyStatus))//判断状态是否已存在
			throw new BusinessException("无需重复封存");
		map.put("status", KeyStatusEnum.SEAL.getCode());
		map.put("id", id);
		boolean result=hsKeyService.hsKeyStatusUpdate(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
		
	}
	/**
	 *  退还钥匙(逻辑删除)
	 * @Title: HsKeyRECIVED   
	 * @Description: 退还钥匙(逻辑删除)
	 * @param id 钥匙主键
	 * @return: BashijuResult
	 */
	@RequestMapping(value="HsKeyRECIVED")
	@ResponseBody
	public BashijuResult HsKeyRECIVED(String id){
		if(StringUtils.isEmpty(id))
			throw new BusinessException("钥匙编号不能为空");
		boolean result=hsKeyService.returnKey(id);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	//借出
	/**
	 * 
	 * @Title: HsKeyBORROWED   
	 * @Description: 钥匙借出状态
	 * @param jsonData
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="HsKeyBORROWED")
	@ResponseBody
	public BashijuResult HsKeyBORROWED(String jsonData){
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("参数不能为空");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		if(StringUtils.isEmpty(map.get("id").toString()))
			throw new BusinessException("请选择");
		//判断状态是否已存在
		if(KeyStatusEnum.BORROWED.getCode().equals(map.get("status")))
			throw new BusinessException("钥匙尚未归还");
		map.put("status", KeyStatusEnum.BORROWED.getCode());
		boolean result = hsKeyService.hsKeyStatusUpdate(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	/**
	 * 
	 * @Title: enterHsKeyLog   
	 * @Description:进入钥匙日志页面
	 * @param request
	 * @param response
	 * @param hsKeyId 钥匙id 
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterHsKeyLog")
	public ModelAndView enterHsKeyLog(HttpServletRequest request,HttpServletResponse response,String hsKeyId) {
		ModelAndView mv = getModelAndView(request, response, "hsKey/hskeyLog");
			mv.addObject("hsKeyId", hsKeyId);
		return mv;
	}
	/**
	 * 
	 * @Title: queryHsKeyLog   
	 * @Description: 查询钥匙日志
	 * @param hsKeyId 钥匙id
	 * @param page 当前页
	 * @param limit 每页条数
	 * @return      
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryHsKeyLog")
	@ResponseBody
	public Map<String,Object> queryHsKeyLog(String hsKeyId,int page,int limit){
		Page<Map<String,Object>> pages = hsKeyService.queryHsKeyLog(hsKeyId, page, limit);
		Map<String,Object>map=getPageResult(pages);
		return map;
	}
	/**
	 * 归还钥匙(清空借钥匙人的信息)
	 * @Title: keyBack   
	 * @Description: 归还钥匙(清空借钥匙人的信息)   
	 * @param id 钥匙主键编号
	 * @return: BashijuResult
	 */
	@RequestMapping(value="keyBack")
	@ResponseBody
	public BashijuResult keyBack(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("归还钥匙数据不能为空");
		boolean result = hsKeyService.keyBack(id);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);	
	}
}
