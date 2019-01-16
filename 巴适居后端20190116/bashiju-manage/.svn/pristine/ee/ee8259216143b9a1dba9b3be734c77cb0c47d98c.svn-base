/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CityManageController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月30日 下午5:29:17   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
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
import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.ResponsibilityCityService;
import com.bashiju.manage.service.RoleService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CityManageController   
 * @Description:城市管理员责任城市管理控制器   
 * @author: yangz
 * @date:   2018年5月30日 下午5:29:17   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="responsibilityCity")
public class ResponsibilityCityController extends BaseController {

	@Autowired	
	private ICommunityService communityService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResponsibilityCityService responsibilityCityService;
	
	/**
	 * 
	 * @Description: 进入页面   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="responsibilityCityPage")
	public ModelAndView responsibilityCityPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = this.getModelAndView(request, response, "cityPermission/responsibilityCity");
		List<Map<String,Object>> roles = roleService.queryRoles(null,RoleGroupEnum.CITY_ADMIN.getCode(), null);
		//区域信息
		List<Map<String, Object>> list = communityService.queryAreaSelect();
		if(list!=null&&list.size()>0) {
			mv.addObject("areaListJson", JSON.toJSONString(list));
		}
		if(roles!=null && roles.size()>0)
			mv.addObject("roles", roles);
		return mv;
	}
	
	/**
	 * 
	 * @Description: 获取数据 
	 * @param request
	 * @param response
	 * @return      
	 * @return: Object
	 */
	@RequestMapping(value="getResponsibilityData")
	@ResponseBody
	public Object getResponsibilityData(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> pages =this.responsibilityCityService.queryResponsibilityCity(roleId, userId, pageNum, pageSize);
		Map<String,Object> map = getPageResult(pages);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * 
	 * @Description: 保存城市管理员负责区信息
	 * @param request
	 * @param response
	 * @return      
	 * @return: String
	 */
	@RequestMapping(value="saveResiposibilityCity")
	@ResponseBody
	public BashijuResult saveResiposibilityCity(HttpServletRequest request,HttpServletResponse response) {
		String dataStr = request.getParameter("dataStr");
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		String[] codes = dataStr.split(",");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(0);
		if(codes.length>0 && roleId!=null && !"".equals(roleId)) {
			for(String str : codes) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("roleId", roleId);
				map.put("userId", userId);
				map.put("cityCode", str);
				map.put("permissionArea", UserThreadLocal.get().get("deptId"));
				map.put("operatorId", UserThreadLocal.get().get("id"));
				list.add(map);
			}
			if(list.size()>0) {
				boolean result = this.responsibilityCityService.saveResponsibilityCity(roleId,userId,list);
				if(result)
					return BashijuResult.ok();
				else 
					throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
			}else {
				throw new BusinessException("没有要保存的信息");
			}
		}else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 
	 * @Description: 删除城市信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delResponsibilityCity")
	@ResponseBody
	public BashijuResult delResponsibilityCity(HttpServletRequest request,HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		if(StringUtils.isEmpty(roleId))
			throw new BusinessException("请选择要删除的角色");
		boolean result = this.responsibilityCityService.deleteResponsibilityCityByRoleId(roleId);
		if(result)
			return BashijuResult.ok();
		else 
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
}
