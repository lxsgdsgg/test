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
import java.util.LinkedList;
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
import com.bashiju.manage.service.NavigationBarService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CityOpenInfoController   
 * @Description:平台城市开通管理控制器   
 * @author: yangz
 * @date:   2018年7月21日 下午5:53:53   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RequestMapping(value="cityOpen")
@Controller
public class CityOpenInfoController extends BaseController{
	
	@Autowired
	private CityOpenInfoService cityOpenInfoService;
	
	@Autowired
	private NavigationBarService navigationBarService;

	/**
	 * @Description: 进入城市开通主页   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="cityOpenPage")
	public ModelAndView cityOpenPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "cityOpen/cityOpen");
		List<Map<String,Object>> bars = navigationBarService.queryNavigationBarTrees("");
		bars = getData(bars);
		mv.addObject("bars", bars);
		return mv;
	}
	
	@RequestMapping(value="getBars")
	@ResponseBody
	public BashijuResult getBars(HttpServletRequest request,String cityOpenInfoId) {
		List<Map<String,Object>> bars = navigationBarService.queryNavigationBarTrees(cityOpenInfoId);
		return BashijuResult.ok(bars);
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
		
		Page<Map<String,Object>> page =cityOpenInfoService.queryCityOpenInfos(provinceCode,cityCode,pageNum,pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * @Description: 保存城市开通信息(不做修改)   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveCityOpen")
	@ResponseBody
	public BashijuResult saveCityOpen(HttpServletRequest request) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		
		//具体的保存方法,不做新增。需要修改的时候就删除原来的数据再新增，新增的时候需要验证该城市是否已经开通，同一城市不允许开通两次；
		boolean result = cityOpenInfoService.saveCityOpen(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * @Description: 取消已开通的城市   
	 * @param request
	 * @return: BashijuResult
	 */
	@RequestMapping(value="cancelCityOpen")
	@ResponseBody
	public BashijuResult cancelCityOpen(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的城市");
		boolean result = this.cityOpenInfoService.cancelCityOpen(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * 
	 * @Description: 保存城市导航授权信息   
	 * @param request
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveNavigationBarPermission")
	@ResponseBody
	public BashijuResult saveNavigationBarPermission(HttpServletRequest request) {
		String cityOpenInfoId = request.getParameter("cityOpenInfoId");
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(cityOpenInfoId))
			throw new BusinessException("请选择要授权的城市");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONObject.parse(jsonData);
		Map<?,?> user = UserThreadLocal.get();
		for(Map<String,Object> map : list) {
			map.put("cityOpenInfoId", cityOpenInfoId);
			map.put("permissionArea", user.get("deptId"));
			map.put("operatorId", user.get("id"));
		}
		boolean result = this.cityOpenInfoService.saveNavigationBarPermission(cityOpenInfoId,list);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 
	 * @Description: 将菜单转为树形结构;     
	 * @param list 菜单列表
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	private List<Map<String,Object>> getData(List<Map<String,Object>> list) {
		List<Map<String,Object>> news = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> rd : list){
			if(rd.get("parentId") ==null || StringUtils.isEmpty(rd.get("parentId").toString()) || "0".equals(rd.get("parentId").toString().trim())){
				Map<String,Object> parent = new HashMap<String,Object>();
				parent.put("id", rd.get("id"));
				parent.put("name", rd.get("name"));
				parent.put("url", rd.get("url"));
				parent.put("datas", getTrees(list, rd.get("id").toString(),"parentId"));
				
				news.add(parent);
			}
		}
		return news;
	}
	
	/**
	 * 
	 * @Description: 获取树节点   
	 * @param list 菜单列表
	 * @param parentId 父级id
	 * @param keyStr 键名
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	private List<Map<String,Object>> getTrees(List<Map<String,Object>> list,String parentId,String keyStr){
		List<Map<String,Object>> mps = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> item : list){
			if(item.get(keyStr).toString().equals(parentId)){
				item.put("datas", getTrees(list, item.get("id").toString(),keyStr));
				mps.add(item);
			}
		}
		return mps;
	}
	
}
