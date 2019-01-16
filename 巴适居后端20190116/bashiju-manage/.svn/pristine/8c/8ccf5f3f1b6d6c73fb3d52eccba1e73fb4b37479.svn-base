/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CityPermissionController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月28日 下午6:10:54   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.MenuFunctionTypeEnum;
import com.bashiju.manage.service.AdminPermissionService;
import com.bashiju.manage.service.PermissionService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;

/**   
 * @ClassName:  CityPermissionController   
 * @Description:城市管理员授权管理控制器   
 * @author: yangz
 * @date:   2018年5月28日 下午6:10:54   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="cityPermission")
public class CityPermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private AdminPermissionService adminPermissionService;
	
	/**
	 * 
	 * @Description: 进入授权页面   
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="cityPermissionPage")
	public ModelAndView cityPermissionPage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "cityPermission/cityPermission");
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		List<Map<String,Object>> menus = this.permissionService.queryMenuTrees(roleId);
		menus = getData(menus);
		mv.addObject("list", menus);//菜单列表
		mv.addObject("roleId", roleId);//角色id
		mv.addObject("userId", userId);//用户id
		mv.addObject("functionTypeMap", MenuFunctionTypeEnum.enumMap);//菜单功能类型
		
		List<Map<String,Object>> responsibility = this.adminPermissionService.queryResponsibility(roleId, userId);
		if(responsibility!=null)
			mv.addObject("responsibility", responsibility);
		return mv;
	}
	
	/**
	 * @Description: 获取城市管理员的权限信息   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="cityPermission")
	@ResponseBody
	public Object cityPermission(HttpServletRequest request,HttpServletResponse response) {
//		Date begin = new Date();
//		System.out.println("加载城市管理员城市信息开始："+begin);
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		List<Map<String,Object>> menus = this.permissionService.queryMenuTrees(roleId);
		menus = getData(menus);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("menus", menus);
		map.put("functionType", MenuFunctionTypeEnum.enumMap);
		
		
		List<Map<String,Object>> responsibility = this.adminPermissionService.queryResponsibility(roleId, userId);
		if(responsibility!=null)
			map.put("responsibility", responsibility);
//		System.out.println("加载城市管理员城市信息结束："+new Date()+"；耗时："+((new Date()).getTime()-begin.getTime()));
		return JSONObject.toJSON(map);
	}
	
	/**
	 * 
	 * @Description: 根据所选城市加载权限信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getPermissions")
	@ResponseBody
	public Object getPermissions(HttpServletRequest request,HttpServletResponse response) {
		Date begin = new Date();
		System.out.println("加载城市管理员权限信息开始："+begin);
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		String permission = request.getParameter("permission");
		if(StringUtils.isEmpty(permission))
			return BashijuResult.build(false, "请选择授权城市");
		List<Map<String,Object>> userPer = this.adminPermissionService.queryUserPermissions(roleId,userId,permission);
		if(userPer==null) 
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		Map<String,Object> per = getPermissionValue(userPer);
		System.out.println("加载城市管理员权限信息结束："+new Date()+"；耗时："+((new Date()).getTime()-begin.getTime()));
		return JSON.toJSON(per);
	}
	
	/**
	 * 
	 * @Description: 保存授权信息   
	 * @param request
	 * @return
	 * @throws Exception      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="savePermissionInfo")
	@ResponseBody
	public BashijuResult savePermissionInfo(HttpServletRequest request)throws Exception{
		String data = request.getParameter("data");
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		String responsibilityCity = request.getParameter("responsibilityCity");
		if(StringUtils.isEmpty(responsibilityCity))
			return BashijuResult.build(false, "授权城市不允许为空");
		System.out.println(data);
		if(StringUtils.isEmpty(data))
			return BashijuResult.build(false, "没有能够保存的权限信息");
		Map<String,Object> dataMap = JSON.parseObject(data, Map.class);
		List<Map<String,Object>> saveData = new ArrayList<Map<String,Object>>();
		for(String key : dataMap.keySet()) {
			String[] menuIds = key.split("_");
			for(int i=0;i<menuIds.length;i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("roleId", roleId);
				map.put("userId", userId);
				map.put("permissions", responsibilityCity);
				map.put("menuId", menuIds[i].split("@@").length>1?menuIds[i].split("@@")[0]:menuIds[i]);
				map.put("operator", UserThreadLocal.get().get("realName"));
				map.put("operatorId", UserThreadLocal.get().get("id"));
				map.put("permissionArea", UserThreadLocal.get().get("deptId"));
				if(!contains(saveData, map))
					saveData.add(map);
			}
		}
		System.out.println("字符串为："+JSONArray.toJSONString(saveData));
		boolean flag = this.adminPermissionService.savePermissions(roleId,userId,saveData);
		if(flag)
			return BashijuResult.build(true, "授权成功");
		else
			return BashijuResult.build(false, "授权失败");
	}
	
	/**
	 * 
	 * @Description: 比较map中的主要属性，判断map是否包含在list列表中，如果已经存在，将其余属性赋值给list中的map   
	 * @param list
	 * @param map
	 * @return      
	 * @return: boolean
	 */
	private boolean contains(List<Map<String,Object>> list,Map<String,Object> map) {
		for(Map<String,Object> temp : list) {
			if(temp.get("roleId").equals(map.get("roleId"))
					 && temp.get("menuId").equals(map.get("menuId"))) {
				if(map.get("userId")==null) {
					//将数据权限赋值给list中的map
					if(temp.get("dataPermission")==null && map.get("dataPermission")!=null) {
						temp.put("dataPermission", map.get("dataPermission"));
					}
					return true;
				}else if(temp.get("userId").equals(map.get("userId"))){
					//将数据权限赋值给list中的map
					if(temp.get("dataPermission")==null && map.get("dataPermission")!=null) {
						temp.put("dataPermission", map.get("dataPermission"));
					}
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**     
	 * @Description: 封装页面能识别的角色用户权限   
	 * @param userPer 权限列表
	 * @return      
	 * @return: Map<String,Object>      
	 */ 
	private Map<String, Object> getPermissionValue(List<Map<String, Object>> userPer) {
		Map<String,Object> result = new HashMap<String,Object>();
		for(Map<String,Object> map : userPer) {
				result.put(map.get("parentId")+"_"+map.get("id"), map.get("permission"));
		}
		return result;
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
			if(rd.get("parentId") ==null || StringUtils.isEmpty(rd.get("parentId").toString())){
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
