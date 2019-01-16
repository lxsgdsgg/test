/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserDesertsInfoController.java   
 * @Package com.bashiju.fin.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月10日 上午11:52:42   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.fin.service.UserDesertsInfoService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  UserDesertsInfoController   
 * @Description:员工奖罚信息管理  
 * @author: yangz
 * @date:   2018年9月10日 上午11:52:42   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
/**   
 * @ClassName:  UserDesertsInfoController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年9月10日 上午11:53:15   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="userDesertsInfo")
public class UserDesertsInfoController extends BaseController {

	@Autowired
	private UserDesertsInfoService userDesertsInfoService;
	
	/**
	 * 
	 * @Description: 获取员工奖罚信息   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="userDesertsInfoDatas")
	@ResponseBody
	public Object userDesertsInfoDatas(HttpServletRequest request,HttpServletResponse response) {
		Long userId = null;
		if(request.getParameter("userId")!=null && !"".equals(request.getParameter("userId")))
			userId = Long.parseLong(request.getParameter("userId").toString());
		String deptId = request.getParameter("deptId");
		String type = request.getParameter("type");
		String classesId = request.getParameter("classesId");
		String months = request.getParameter("months");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = userDesertsInfoService.queryUserDesertsInfos(userId,deptId,type, classesId,months, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
//	public Object getUserDesertsInfo(HttpServletRequest request,HttpServletResponse response) {
//		
//	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 保存员工奖罚信息
	 * @Description: 保存员工奖罚信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="saveOrUpdateUserDesertsInfo")
	@ResponseBody
	public BashijuResult saveOrUpdateUserDesertsInfo(HttpServletRequest request,HttpServletResponse response) {
		String jsonData = request.getParameter("jsonData");
//		String jsonData = "{\"classesName\":\"全勤奖\",\"reason\":\"全勤\",\"classesId\":\"1\",\"type\":\"1\",\"desertsDate\":\"2018-09-10\"}";
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		if(!map.containsKey("id") || StringUtils.isEmpty(map.get("id").toString())) {
			Map<String,Object> user = UserThreadLocal.get();
			map.put("companyName", "");
			map.put("amount", Integer.parseInt(map.get("amount").toString())*100);
			map.put("permissionArea", user.get("deptId"));
			map.put("operatorId", user.get("id"));
			map.put("addTime", new Date());
			map.put("updateTime",null);
			map.put("operator",user.get("realName"));
			map.put("auditorId", 0);
			map.put("auditor", "");
			map.put("rejectReason", "");
		}
		
		boolean result = userDesertsInfoService.saveOrUpdateUserDesertsInfo(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 删除员工奖惩信息 
	 * @Description: 删除员工奖惩信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delUserDesertsInfo")
	@ResponseBody
	public BashijuResult delUserDesertsInfo(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("请选择要删除的员工奖惩信息");
		boolean result = userDesertsInfoService.delUserDesertsInfo(id); 
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	/**
	 * 
	 * 审核员工奖罚  
	 * @Description: TODO(审核员工奖罚  用)   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping(value="examineUserDesertsInfo")
	@ResponseBody
	public BashijuResult examineUserDesertsInfo(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> data = new HashMap<>();
		data.put("id",request.getParameter("id"));
		data.put("examineStatus",request.getParameter("examineStatus"));
		data.put("rejectReason",request.getParameter("rejectReason")==null?"":request.getParameter("rejectReason"));
		boolean res = userDesertsInfoService.saveExamineUserDeserts(data);
		if(res) {
			return BashijuResult.ok(UserThreadLocal.get().get("realName"));
		}else {
			throw new BusinessException("审核失败！");
		}
	}
	
}
