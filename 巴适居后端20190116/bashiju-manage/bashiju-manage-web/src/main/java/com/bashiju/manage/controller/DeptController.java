package com.bashiju.manage.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.MenuEnum;
import com.bashiju.enums.MenuLevelEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.service.CompanyService;
import com.bashiju.manage.service.DeptService;
import com.bashiju.manage.service.DeptTypeService;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.MenuService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DeptController   
 * @Description:部门控制层   
 * @author: wangpeng
 * @date:   2018年4月27日 上午9:09:23   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="dept")
public class DeptController extends BaseController {
	@Autowired
	private DeptService deptService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptTypeService  deptTypeService;
	
	@Autowired	
	private ICommunityService communityService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * @Title: deptPage   
	 * @Description: 进入部门页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="deptPage")
	public ModelAndView deptPage(HttpServletRequest request,HttpServletResponse response)throws Exception {
		ModelAndView mv = getModelAndView(request, response, "dept/dept");
		//查询部门类型
		List<Map<String,Object>> deptType = deptTypeService.queryAllDeptTypes();
		if(deptType!=null && deptType.size()>0 ) {
			mv.addObject("deptType",deptType);
		}
		return mv;
	}
	
	
	/**
	 * 
	 * @Title: getDeptData   
	 * @Description: 查询部门信息  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception      
	 * @return: Object
	 */
	@RequestMapping(value="getDeptData")
	@ResponseBody
	public Object getDeptData(HttpServletRequest request,HttpServletResponse response,String deptName,String deptTypeId, String companyId,int page,int limit) throws Exception{
		 Page<Map<String, Object>> pages = deptService.queryDept(deptName, companyId, deptTypeId, page, limit);
			Map<String,Object> map = getPageResult(pages);
			return JSONObject.toJSON(map);
	}
	/**
	 * @Title: queryDeptIdAndDeptName   
	 * @Description: 查询部门编号和部门名称
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryDeptIdAndDeptName")
	@ResponseBody
	public List<Map<String,Object>> queryDeptIdAndDeptName(){
		return deptService.queryDeptIdAndDeptName();
	}
	
	/**
	 * 
	 * @Title: deptDetailPage   
	 * @Description: 进入部门编辑界面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 * @throws Exception 
	 */
	@RequestMapping(value="deptDetailPage")
	public ModelAndView deptDetailPage(HttpServletRequest request,HttpServletResponse response,String deptId) throws Exception{
		ModelAndView mv=getModelAndView(request, response, "dept/deptDetil");
		if(!StringUtils.isEmpty(deptId)){
			//部门信息
			Map<String, Object> deptNow = deptService.getDept(deptId);	
			if(deptNow!=null&&deptNow.size()>0){
				mv.addObject("deptNow", JSONArray.toJSON(deptNow));
			}
		}
		
		//所有部门信息
		List<Map<String, Object>>depts = deptService.queryDepts(null, null, null, null, null, null);
		if(depts!=null&&depts.size()>0) {
			mv.addObject("depts", JSONArray.toJSON(depts));
		}
		//公司信息
		if(RoleGroupEnum.ORDINARY_USER.getCode().equals(UserThreadLocal.get().get("roleGroup"))) {
			Map<String,Object> company = companyService.getCompany(UserThreadLocal.get().get("companyId").toString());
			List<Map<String,Object>> companys = new ArrayList<Map<String,Object>>(0);
			if(company!=null)
				companys.add(company);
			mv.addObject("companys", JSONObject.toJSON(companys));
		}else {
			List<Map<String,Object>> companys = companyService.queryCompanys(null, null, null, null, null);
			if(companys!=null && companys.size()>0) {
				mv.addObject("companys", JSONArray.toJSON(companys));
			}
		}
		//部门类型
		List<Map<String, Object>> deptTypes = deptTypeService.queryAllDeptTypes();
		if(deptTypes!=null&&deptTypes.size()>0) {
			mv.addObject("deptTypes", JSONArray.toJSON(deptTypes));
		}
		//用户信息
		List<Map<String, Object>> users = deptService.queryUser();
		if(users!=null&&users.size()>0){
			mv.addObject("users",JSONArray.toJSON(users));
		}
		//区域信息
		List<Map<String, Object>> list = communityService.queryAreaSelect();
		if(list!=null&&list.size()>0) {
			mv.addObject("areaListJson", JSON.toJSONString(list));
		}
		return mv;
	}
	/**
	 * 
	 * @Title: saveOrUpdate   
	 * @Description: 保存或修改部门信息
	 * @param request
	 * @return      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateDept")
	@ResponseBody
	public BashijuResult saveOrUpdate(HttpServletRequest request){
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
//		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
//		map.put("operatorId", UserThreadLocal.get().get("id"));
		boolean result;
		result = this.deptService.saveOrUpdateDept(map);
		if(result)
			return BashijuResult.ok();
		else 
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	
	/**
	 * @Description: 更新跨店信息，将覆盖原部门的跨店信息   
	 * @param jsonData
	 * @param deptId
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveAcrossStorePermission")
	@ResponseBody
	public BashijuResult saveAcrossStorePermission(String jsonData,String deptId){
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		if(StringUtils.isEmpty(deptId))
			throw new BusinessException("待跨店编号不允许为空");
		List<Map<String,Object>> list = (List<Map<String,Object>>) JSONArray.parse(jsonData);
		
		Map<?,?> user = UserThreadLocal.get();
		for(Map<String,Object> map : list) {
			map.put("permissionArea", user.get("deptId").toString());
			map.put("operatorId", user.get("id").toString());
		}
		boolean result=this.deptService.saveAcrossStorePermission(deptId,list);
		if(result)
			return BashijuResult.ok();
		else 
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 
	 * @Title: delDept   
	 * @Description: 逻辑删除部门   
	 * @param request
	 * @param response
	 * @param deptId 部门id
	 * @return      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delDept")
	@ResponseBody
	public BashijuResult delDept(HttpServletRequest request,HttpServletResponse response,String deptId) {
		boolean result = deptService.delDept(deptId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	/**
	 * 
	 * @Title: enterAcrossPermission   
	 * @Description: 进入跨店权限页面
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterAcrossStorePermission")
	public ModelAndView enterAcrossPermission(HttpServletRequest request,HttpServletResponse response,String deptId) {
		ModelAndView mv = getModelAndView(request, response, "dept/acrossStorePermission");
		List<Map<String,Object>> depts =	deptService.queryDepts(null, null, null, null, null, null);
		mv.addObject("depts",depts);
		mv.addObject("deptId", deptId);
		
		List<Map<String,Object>> acrossStorePermission = deptService.queryAcrossStorePermission(deptId);
		if(acrossStorePermission!=null && acrossStorePermission.size()>0)
			mv.addObject("acrossStorePermission", acrossStorePermission);
		
		List<Map<String,Object>> houseBtn = menuService.queryMenus(null, MenuEnum.MENU_84.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		List<Map<String,Object>> buyBtn = menuService.queryMenus(null, MenuEnum.MENU_96.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		//TODO:这里用的应该是租赁客源的信息，但是现在还没有该菜单，所以先用其他的占位
		List<Map<String,Object>> rentBtn = menuService.queryMenus(null, MenuEnum.MENU_97.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		
		mv.addObject("houseBtn", houseBtn);
		mv.addObject("buyBtn", buyBtn);
		mv.addObject("rentBtn", rentBtn);
		mv.addObject("houseMenuId", MenuEnum.MENU_84.getCode());
		mv.addObject("buyMenuId", MenuEnum.MENU_96.getCode());
		mv.addObject("rentMenuId", MenuEnum.MENU_97.getCode());
		return mv;
	}
	
	/**
	 * 
	 * @Description: 跨店详细信息   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="enterAcrossPermissionNew")
	@ResponseBody
	public Object enterAcrossPermissionNew(HttpServletRequest request,HttpServletResponse response) {
		String deptId = request.getParameter("deptId");
		List<Map<String,Object>> depts =	deptService.queryDepts(null, null, null, null, null, null);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("depts", depts);
//		mv.addObject("depts",depts);
//		mv.addObject("deptId", deptId);
		
		List<Map<String,Object>> acrossStorePermission = deptService.queryAcrossStorePermission(deptId);
		if(acrossStorePermission!=null && acrossStorePermission.size()>0)
			map.put("acrossStorePermission", acrossStorePermission);
		
		List<Map<String,Object>> houseBtn = menuService.queryMenus(null, MenuEnum.MENU_84.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		List<Map<String,Object>> buyBtn = menuService.queryMenus(null, MenuEnum.MENU_96.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		List<Map<String,Object>> rentBtn = menuService.queryMenus(null, MenuEnum.MENU_97.getCode(),null, null, MenuLevelEnum.LEVEL4.getCode(), null);
		
		map.put("houseBtn", houseBtn);
		map.put("buyBtn", buyBtn);
		map.put("rentBtn", rentBtn);
		map.put("houseMenuId", MenuEnum.MENU_84.getCode());
		map.put("buyMenuId", MenuEnum.MENU_96.getCode());
		map.put("rentMenuId", MenuEnum.MENU_97.getCode());
		return JSONObject.toJSON(map);
	}
	/**
	 * 新增或修改责任盘
	 * @Title: saveResponsbilityDisc   
	 * @Description: 新增或修改责任盘
	 * @param jsonData
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveResponsbilityDisc")
	@ResponseBody
	public BashijuResult saveResponsbilityDisc(String jsonData) {
		List<Map<String,Object>> list = (List<Map<String, Object>>) JSONArray.parse(jsonData);
		boolean result = deptService.saveResponsbilityDisc(list);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	/**
	 * 查询部门所在城市
	 * @Title: queryCityForDept   
	 * @Description: 查询部门所在城市(部门不为市级)
	 * @param deptId 部门编号
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryCityForDept")
	@ResponseBody
	public Map<String,Object> queryCityForDept(String deptId){
		if(StringUtils.isEmpty(deptId))
			throw new BusinessException("部门编号不能为空");
		Map<String,Object> map = deptService.queryDeptCityCode(deptId);
		return map;
	}
	
	/**
	 * 条件查询部门责任盘
	 * @Title: queryResponsibilityDisk   
	 * @Description:条件查询部门责任盘
	 * @param deptId 部门编号
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryResponsibilityDisk")
	@ResponseBody
	public List<Map<String,Object>> queryResponsibilityDisk(String deptId){
		if(StringUtils.isEmpty(deptId))
			throw new BusinessException("部门编号不能为空");
		List<Map<String,Object>> map = deptService.queryResponsibilityDisk(deptId);
		return map;
	}
}
