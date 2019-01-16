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

import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.service.BlackListService;
import com.bashiju.manage.service.CompanyService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  BlackListController   
 * @Description:中介黑名单控制层 
 * @author: wangpeng
 * @date:   2018年6月12日 下午2:45:19   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="BlackList")
public class BlackListController  extends BaseController{
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BlackListService blackListService; 
	/**
	 * 
	 * @Title: enterBlackListPage   
	 * @Description: 进入中介黑名单页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterBlackListPage")
	public ModelAndView enterBlackListPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=getModelAndView(request, response, "blackList/blackList");
		return mv;
	}
	/**
	 * 
	 * @Title: queryBlackListData   
	 * @Description: 查询中介黑名单  
	 * @param page 当前页数
	 * @param limit 每页条数
	 * @param companyId 公司id
	 * @return      
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryBlackListData")
	@ResponseBody
	public Map<String,Object> queryBlackListData(int page,int limit,String companyId,String keyword){
		Page<Map<String, Object>> pages = blackListService.queryBlackList(page, limit, keyword);
		Map<String, Object> map = getPageResult(pages);
		return map;
	}
	
	/**
	 * 
	 * @Title: enterBlackListDetailPage   
	 * @Description: 进入中介黑名单新增页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterBlackListDetailPage")
	public ModelAndView enterBlackListDetailPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "blackList/blackListDetil");
		List<Map<String,Object>> companys = companyService.queryCompanys(null, null, null, null, null);
		mv.addObject("companys", companys);
		return mv;
	}
	/**
	 * 
	 * @Title: saveOrUpdateBlackList   
	 * @Description: 保存中介黑名单
	 * @param jsonData
	 * @return      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateBlackList")
	@ResponseBody
	public BashijuResult saveOrUpdateBlackList(String jsonData) {
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map =(Map<String,Object>) JSONObject.parse(jsonData);
		Map<String,Object> compantyId = companyService.getCompany(map.get("companyId").toString());//通过公司id查询
		map.put("legalName", compantyId.get("legrepname"));//根据公司id查询法定带表人的名称
		boolean result = blackListService.saveOrUpdateBlackList(map);
		if(result)
			return BashijuResult.ok();
		else 
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	/**
	 * 
	 * @Title: delBlackList   
	 * @Description: 逻辑删除中介黑名单
	 * @param id  中介黑名单编号
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delBlackList")
	@ResponseBody
	public BashijuResult delBlackList(String id) {
		if(StringUtils.isEmpty(id))
			throw new BusinessException("中介黑名单编号不能为空");
		boolean result = blackListService.delBlackList(id);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
}
