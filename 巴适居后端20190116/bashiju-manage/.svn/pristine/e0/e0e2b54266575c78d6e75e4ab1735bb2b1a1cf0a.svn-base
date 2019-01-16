package com.bashiju.manage.controller;

import java.util.List;
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
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseTransactionEnum;
import com.bashiju.manage.service.ResHouseReturnCycleService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;

@Controller
@RequestMapping(value="resHouseReturnCycle")
public class ResHoseReturnCycleController  extends BaseController{
	@Autowired
	private ResHouseReturnCycleService resHouseReturnCycleService;
	/**
	 * 
	 * @Title: enterResHouseReturnCyclePage   
	 * @Description: 进入房源回访周期页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterResHouseReturnCyclePage")
	public ModelAndView enterResHouseReturnCyclePage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=getModelAndView(request, response, "resHouseReturnCycle/resHouseReturnCycle");
		List<Map<String,Object>> map = resHouseReturnCycleService.queryResHouseReturnCycleData();
		if(map!=null&&map.size()>0)
			mv.addObject("map", map);
		mv.addObject("transactionTypes",HouseTransactionEnum.enumMap);
		mv.addObject("levelTypes",HouseLevelEnum.enumMap);
		return mv;
	}
	/**
	 * 查询房源回访周期设置数据
	 * @Title: queryResHouseReturnCycleData   
	 * @Description: 查询房源回访周期设置数据  
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryResHouseReturnCycleData")
	@ResponseBody
	public List<Map<String,Object>> queryResHouseReturnCycleData(){
		List<Map<String,Object>> map = resHouseReturnCycleService.queryResHouseReturnCycleData();
		return map;
	}

	/**
	 * 
	 * @Title: enterResHouseReturnCycleEditorPage   
	 * @Description: 进入房源回访周期编辑页面  
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterResHouseReturnCycleEditorPage")
	public ModelAndView enterResHouseReturnCycleEditorPage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = getModelAndView(request, response, "resHouseReturnCycle/resHouseReturnCycleDetail");
		List<Map<String,Object>> map = resHouseReturnCycleService.queryResHouseReturnCycleData();
		if(map!=null&&map.size()>0)
			mv.addObject("maps", map);
		mv.addObject("transactionType",HouseTransactionEnum.enumMap);
		mv.addObject("levelType",HouseLevelEnum.enumMap);
		return mv;
	}
	
	
	
//	/**
//	 * 
//	 * @Title: queryResHouseReturnCycleData   
//	 * @Description:查询房源回访周期数据
//	 * @param request
//	 * @param response
//	 * @return      
//	 * @return: ModelAndView
//	 */
//	@RequestMapping(value="queryResHouseReturnCycleData")
//	public ModelAndView queryResHouseReturnCycleData(HttpServletRequest request,HttpServletResponse response) {
//		List<Map<String,Object>>map=resHouseReturnCycleService.queryResHouseReturnCycleData();
//		ModelAndView mv=getModelAndView(request, response, "resHouseReturnCycle/resHouseReturnCycle");
//		if(map!=null&&map.size()>0)
//			mv.addObject("map", map);
//		return mv;
//	}
	/**
	 * 
	 * @Title: saveOrUpdateResHouseReturnCycle   
	 * @Description: 编辑或保存房源回访周期
	 * @param request
	 * @param response
	 * @param jsonData
	 * @return      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateResHouseReturnCycle")
	@ResponseBody
	public BashijuResult saveOrUpdateResHouseReturnCycle(HttpServletRequest request,HttpServletResponse response,String jsonData){
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> map= (List<Map<String, Object>>) JSONArray.parse(jsonData);
		boolean result = resHouseReturnCycleService.saveOrUpdateResHouseReturnCycle(map);
		if(result)
			return BashijuResult.ok();
		else 
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
}
