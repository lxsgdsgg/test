/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MainController.java   
 * @Package com.bashiju.sso.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月9日 下午4:57:39   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.sso.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bashiju.api.PermissionServiceApi;
import com.bashiju.utils.service.BaseController;

import com.bashiju.utils.threadlocal.UserThreadLocal;
/**   
 * @ClassName:  MainController   
 * @Description:主页面管理控制器   
 * @author: yangz
 * @date:   2018年4月9日 下午4:57:39   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RequestMapping(value="main")
@Controller
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionServiceApi permissionService;
	
	@RequestMapping(value="{pageName}")
	public ModelAndView toIndex(@PathVariable("pageName") String pageName,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView(pageName);	
		mv.addObject("userInfo", UserThreadLocal.get());
		return mv;
	}
	/**
	 * 
	 * @Title menuData   
	 * @Description 读取菜单信息   
	 * @param @return      
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="menuData")
	@ResponseBody
	public Map<String,Object> menuData() {
		String userId = UserThreadLocal.get().get("id").toString();
		return permissionService.callQueryUserMenuPermission2(userId);	
	}
	
	/**
	 * 
	 * @Title: getSecondMenus   
	 * @Description: 根据一级菜单找到子一级菜单信息（暂时无用）   
	 * @param: @param request
	 * @param: @param response
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	/*@RequestMapping(value="getSecondMenus")
	public BashijuResult getSecondMenus(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String firstMenuId = request.getParameter("firstMenuId");
		if(StringUtils.isEmpty(firstMenuId)) {
			throw new BusinessException("请选择要访问的系统");
		}
		String userId= UserThreadLocal.get().get("id").toString();
		String mobile = UserThreadLocal.get().get("mobile").toString();
		List<Map<String,Object>> list = permissionServicApi.callQueryUserMenuPermission(mobile, userId);
		System.out.println(JSONArray.toJSONString(list));
		String str= null;
		for(Map<String,Object> map : list) {
			if(firstMenuId.equals(map.get("menuId").toString())) {
				str = JSONArray.toJSONString( map.get("datas"));
				break;
			}
		}
		return  BashijuResult.ok(str);
	}*/
	
	
}
