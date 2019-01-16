/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommissionsConfigController.java   
 * @Package com.bashiju.fin.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月12日 下午4:26:38   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.fin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.BusinessTypeEnum;
import com.bashiju.enums.CommissionCalculateFormulateEnum;
import com.bashiju.fin.service.BusinessTypeService;
import com.bashiju.fin.service.CommissionsConfigService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CommissionsConfigController   
 * @Description:提成配置管理   
 * @author: yangz
 * @date:   2018年9月12日 下午4:26:38   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="commissionsConfig")
public class CommissionsConfigController extends BaseController {

	@Autowired
	private CommissionsConfigService commissionsConfigService;
	@Autowired
	private BusinessTypeService businessTypeService;
	
	/**
	 * 获取提成配置信息
	 * @Description: 获取提成配置信息   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="commissionsConfig")
	@ResponseBody
	public Object commissionsConfig(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = commissionsConfigService.queryCommissionsConfig(name, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * 保存提成配置方案信息
	 * @Description: 保存提成配置方案信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateCommissionsConfig")
	@ResponseBody
	public BashijuResult saveOrUpdateCommissionsConfig(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String descs = request.getParameter("descs");
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(name))
			throw new BusinessException("方案名称不允许为空");
		Map<String,Object> map = new HashMap<String,Object>(0);
		Map<String,Object> user = UserThreadLocal.get();
		map.put("name", name);
		map.put("descs", descs);
		if(!StringUtils.isEmpty(id)) {
			map.put("id", id);
		}else {//新增
			map.put("companyId", user.get("companyId"));
			map.put("companyName", user.get("comName"));
			map.put("permissionArea", user.get("deptId"));
			map.put("operatorId", user.get("id"));
			map.put("addTime", new Date());
			map.put("updateTime",null);
			map.put("operator",user.get("realName"));
		}
		boolean result = commissionsConfigService.saveOrUpdateCommissionsConfig(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 删除提成配置方案信息   
	 * @Description: 删除提成配置方案信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delCommissionsConfig")
	@ResponseBody
	public BashijuResult delCommissionsConfig(HttpServletRequest request,HttpServletResponse response) {
		String commConfigId = request.getParameter("commConfigId");
		if(StringUtils.isEmpty(commConfigId))
			throw new BusinessException("请选择要删除的提成配置方案");
		boolean result = commissionsConfigService.delCommissionsConfig(commConfigId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * 查询业务类型
	 * @Description: 查询业务类型  
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="getBusinessType")
	@ResponseBody
	public Map<String,Object> getBusinessType(HttpServletRequest request,HttpServletResponse response,String name,String companyId) {
		Map<String,Object> res = new HashMap<>();
		res.put("businessType", BusinessTypeEnum.enumMap);
		List<Map<String, Object>> companyCommissionCalculateFormulate = businessTypeService.queryBusinessTypes(name, companyId);
		res.put("commissionCalculateFormulate", CommissionCalculateFormulateEnum.enumMap);
		res.put("companyCommissionCalculateFormulate", companyCommissionCalculateFormulate);
		return res;
	}
	
	
	/**
	 * 查询通提比例
	 * @Description: 查询通提比例
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="queryUniversalRate")
	@ResponseBody
	public BashijuResult queryUniversalRate(HttpServletRequest request,HttpServletResponse response,String name,String companyId) {
		String commConfigId = request.getParameter("commConfigId");
		String BusiTypeId = request.getParameter("BusiTypeId");
		if(StringUtils.isEmpty(commConfigId) || StringUtils.isEmpty(BusiTypeId))
			throw new BusinessException("提成配置方案编号和业务类型编号都不允许为空");
		Map<String,Object> rate = commissionsConfigService.queryUniversalRate(commConfigId, BusiTypeId);
		return BashijuResult.ok(rate);
	}
	
	/**
	 * 保存或更新通提比例
	 * @Description: 保存或更新通提比例
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateUniversalRate")
	@ResponseBody
	public BashijuResult saveOrUpdateUniversalRate(HttpServletRequest request,HttpServletResponse response) {
		String jsonData = request.getParameter("jsonData");
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("没有要保存的数据");
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		if(!map.containsKey("id") || StringUtils.isEmpty(map.get("id").toString())) {
			Map<String,Object> user = UserThreadLocal.get();
			map.put("permissionArea", user.get("deptId"));
			map.put("operatorId", user.get("id"));
			map.put("addTime", new Date());
			map.put("updateTime",null);
			map.put("operator",user.get("realName"));
		}
		boolean result = commissionsConfigService.saveOrUpdateUniversalRate(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	
	/**
	 * 查询提成配置实收比例信息   
	 * @Description: 查询提成配置实收比例信息   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="queryCommConfigActualRate")
	@ResponseBody
	public Object queryCommConfigActualRate(HttpServletRequest request,HttpServletResponse response) {
		String commConfigId = request.getParameter("commConfigId");
		String BusiTypeId = request.getParameter("BusiTypeId");
		if(StringUtils.isEmpty(commConfigId) || StringUtils.isEmpty(BusiTypeId))
			throw new BusinessException("提成配置方案编号和业务类型编号都不允许为空");
		List<Map<String,Object>> list = commissionsConfigService.queryCommConfigActualRate(commConfigId, BusiTypeId);
		return JSONObject.toJSON(list);
	}
	
	/**
	 * 保存配置方案实收比例信息
	 * @Description: 保存配置方案实收比例信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateCommConfigActualRate")
	@ResponseBody
	public BashijuResult updateCommConfigActualRate(HttpServletRequest request,HttpServletResponse response) {
		String editData = request.getParameter("editData");
		String addData = request.getParameter("addData");
		String delId = request.getParameter("delId");
		if(StringUtils.isEmpty(editData) && StringUtils.isEmpty(addData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> editDataList = (List<Map<String, Object>>) JSONObject.parse(editData);
		List<Map<String,Object>> addDataList = (List<Map<String, Object>>) JSONObject.parse(addData);
		
		boolean result = commissionsConfigService.saveOrUpdateCommConfigActualRate(editDataList,addDataList,delId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 查询提成配置比例信息
	 * @Description: 查询提成配置比例信息   
	 * @param request
	 * @param repsonse
	 * @return: Object
	 */
	@RequestMapping(value="queryCommConfigRate")
	@ResponseBody
	public Object queryCommConfigRate(HttpServletRequest request,HttpServletResponse repsonse) {
		String commConfigId = request.getParameter("commConfigId");
		String BusiTypeId = request.getParameter("BusiTypeId");
		if(StringUtils.isEmpty(commConfigId) || StringUtils.isEmpty(BusiTypeId))
			throw new BusinessException("提成配置方案编号和业务类型编号都不允许为空");
		List<Map<String,Object>> list = commissionsConfigService.queryCommConfigRate(commConfigId, BusiTypeId,new SimpleDateFormat("yyyy-MM").format(new Date()));
		return JSONObject.toJSON(list);
	}
	
	/**
	 * 保存提成配置比例信息
	 * @Description: 保存提成配置比例信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateCommConfigRate")
	@ResponseBody
	public BashijuResult updateCommConfigRate(HttpServletRequest request,HttpServletResponse response) {
		String editData = request.getParameter("editData");
		String addData = request.getParameter("addData");
		String delId = request.getParameter("delId");
		if(StringUtils.isEmpty(editData) && StringUtils.isEmpty(addData))
			throw new BusinessException("没有要保存的数据");
		List<Map<String,Object>> editDataList = (List<Map<String, Object>>) JSONObject.parse(editData);
		List<Map<String,Object>> addDataList = (List<Map<String, Object>>) JSONObject.parse(addData);
		boolean result = commissionsConfigService.saveOrUpdateCommConfigRate(editDataList,addDataList,delId);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR); 
	}
}
