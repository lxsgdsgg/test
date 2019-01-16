/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CityOpenInfoController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月21日 下午5:53:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.HashMap;
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
import com.bashiju.manage.service.CityOpenInfoService;
import com.bashiju.manage.service.CompanyCityOpenService;
import com.bashiju.manage.service.CompanyService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CityOpenInfoController   
 * @Description:公司城市开通管理控制器   
 * @author: yangz
 * @date:   2018年7月21日 下午5:53:53   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RequestMapping(value="companyCityOpen")
@Controller
public class CompanyCityOpenController extends BaseController{
	
	@Autowired
	private CompanyCityOpenService companyCityOpenService;
	
	@Autowired
	private CityOpenInfoService cityOpenInfoService;
	
	@Autowired
	private CompanyService companyService;

	/**
	 * @Description: 进入公司城市开通主页   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="companyCityOpenPage")
	public ModelAndView cityOpenPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "companyCityOpen/companyCityOpen");
		List<Map<String,Object>> list = cityOpenInfoService.queryCityOpenInfos(null, null);
		mv.addObject("openCityInfo", list);//已开通的城市信息
		return mv;
	}
	
	/**
	 * @Description: 获取用户角色组
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="getRoleGroup")
	@ResponseBody
	public BashijuResult getRoleGroup(HttpServletRequest request) {
		Map<String,Object> info = new HashMap<>();
		info.put("roleGroup", UserThreadLocal.get().get("roleGroup"));
		info.put("companyId", UserThreadLocal.get().get("companyId"));
		return BashijuResult.ok(info);
	}
	
	/**
	 * @Description: 条件获取数据   
	 * @param request
	 * @return: Object
	 */
	@RequestMapping(value="getData")
	@ResponseBody
	public Object getData(HttpServletRequest request) {
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		String isOpen = request.getParameter("isOpen");
		Page<Map<String,Object>> page =companyCityOpenService.queryCompanyCityOpenInfos(provinceCode,cityCode,companyId,companyName,null,pageNum,pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * @Description: 保存公司城市开通信息  
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateCityOpen")
	@ResponseBody
	public BashijuResult saveOrUpdateCityOpen(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		
		//具体的保存方法,同一公司不允许开通两次；
		boolean result = companyCityOpenService.saveOrUpdateCityOpen(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * @Description: 取消公司的开通城市权限   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="cancelCityOpen")
	@ResponseBody
	public BashijuResult cancelCityOpen(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的城市");
		boolean result = this.companyCityOpenService.cancelCompanyCityOpen(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
}
