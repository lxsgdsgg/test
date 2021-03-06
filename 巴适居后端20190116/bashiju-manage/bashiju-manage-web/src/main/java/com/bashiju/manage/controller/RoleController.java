/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RoleController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月18日 下午2:27:00   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.ArrayList;
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
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.service.CompanyService;
import com.bashiju.manage.service.RoleService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  RoleController   
 * @Description:角色管理控制器 
 * @author: yangz
 * @date:   2018年4月18日 下午2:27:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RequestMapping(value="role")
@Controller
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="rolePage")
	public ModelAndView rolePage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "role/role");
		return mv;
	}
	
	/**
	 * 
	 * @Description: 获取角色信息  
	 * @param request
	 * @throws Exception      
	 * @return: void
	 */
	@RequestMapping(value="getRoleData")
	@ResponseBody
	public Object getRoleData(HttpServletRequest request)throws Exception{
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page"); 
		String size = request.getParameter("limit");
		String roleName = request.getParameter("roleName");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
//		String companyId = (String) UserThreadLocal.get().get("companyId");
		Page<Map<String,Object>> page =roleService.queryRoles(null,null, roleName, pageNum, pageSize);
		return JSONObject.toJSON(getPageResult(page));
	}
	
	/**
	 * 
	 * @Description: 逻辑删除角色信息   
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delRole")
	@ResponseBody
	public BashijuResult delRole(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String roleId = request.getParameter("roleId");
		boolean flag = this.roleService.delRole(roleId);
		if(flag)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * 
	 * @Description: 进入角色编辑界面  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="detailPage")
	public ModelAndView detailPage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mv = getModelAndView(request, response, "role/detailPage");
		String roleId = request.getParameter("roleId");
		if(!StringUtils.isEmpty(roleId)) {
			Map<String,Object> role = this.roleService.getRole(roleId);
			mv.addObject("role", role);
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
			if(companys!=null && companys.size()>0)
				mv.addObject("companys", companys);
			mv.addObject("roleGroup", RoleGroupEnum.enumMap);
		}
		return mv;
	}
	
	/**
	 * 
	 * @Description: 编辑角色信息 
	 * @param response
	 * @return
	 * @throws Exception      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateRole")
	@ResponseBody
	public BashijuResult saveOrUpdateRole(HttpServletRequest request)throws Exception{
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			return BashijuResult.build(false, "没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		map.put("permissionArea", UserThreadLocal.get().get("deptId"));
		map.put("operatorId", UserThreadLocal.get().get("id"));
		if(!map.containsKey("groups") || map.get("groups")==null || "".equals(map.get("groups")))
			map.put("groups", RoleGroupEnum.ORDINARY_USER.getCode());//默认为普通管理员
		long tt =0;
		tt = this.roleService.saveOrUpdateRole(map);
		if(tt>0)
			return BashijuResult.ok();
		else
			throw new  BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
}
