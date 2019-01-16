/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  SsfConfigurateController.java   
 * @Package com.bashiju.fin.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月8日 下午3:17:47   
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
import com.bashiju.fin.service.SsfConfigurateService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  SsfConfigurateController   
 * @Description:社保配置方案管理   
 * @author: yangz
 * @date:   2018年9月8日 下午3:17:47   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="ssfConfigurate")
public class SsfConfigurateController extends BaseController {

	@Autowired
	private SsfConfigurateService ssfConfigurateService;
	
	/**
	 * 获取社保方案信息(包括社保详情信息)  
	 * @Description: 获取社保方案信息(包括社保详情信息)   
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="ssfConfigurateAll")
	@ResponseBody
	public Object ssfConfigurateAll(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		int pageNum = 1;
		int pageSize = 20;
		String num = request.getParameter("page");
		String size = request.getParameter("limit");
		if(!StringUtils.isEmpty(num))
			pageNum = Integer.parseInt(num);
		if(!StringUtils.isEmpty(size))
			pageSize = Integer.parseInt(size);
		Page<Map<String,Object>> page = ssfConfigurateService.querySsfConfigurateAll(name, pageNum, pageSize);
		Map<String,Object> map = getPageResult(page);
		return JSONObject.toJSON(map);
	}
	
	/**
	 * 保存社保方案信息
	 * @Description: 保存社保方案信息  
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateSsfConfigurate")
	@ResponseBody
	public BashijuResult saveOrUpdateSsfConfigurate(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(name))
			throw new BusinessException("方案名称不允许为空");
		Map<String,Object> map = new HashMap<String,Object>(0);
		Map<String,Object> user = UserThreadLocal.get();
		map.put("name", name);
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
		boolean result = ssfConfigurateService.saveOrUpdateSsfConfigurate(map);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 保存社保详情信息
	 * @Description: 保存社保详情信息   
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrupdateSsfConfigurateDetail")
	@ResponseBody
	public BashijuResult saveOrupdateSsfConfigurateDetail(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String ssfId = request.getParameter("ssfId");
		String id = request.getParameter("id");
		String ssfCardinality = request.getParameter("ssfCardinality");
		String companyRate = request.getParameter("companyRate");
		String privateRate = request.getParameter("privateRate");
		int ssfC = 0;//缴存基数
		int cRate = 0;//公司缴存比例
		int pRate = 0;//个人缴存比例
		
		if(StringUtils.isEmpty(ssfId))
			throw new BusinessException("请选择所属社保方案");
		if(StringUtils.isEmpty(name))
			throw new BusinessException("名称不允许为空");
		if(StringUtils.isEmpty(ssfCardinality))
			throw new BusinessException("缴存基数不允许为空");
		else
			try {
				double dd = Double.parseDouble(ssfCardinality)*100;
				ssfC =  (new Double(dd)).intValue();;//单位转为分
			} catch (NumberFormatException e) {
				throw new BusinessException("请输入正确的缴存基数");
			}
		
		if(StringUtils.isEmpty(companyRate))
			throw new BusinessException("公司缴存比例不允许为空");
		else {
			try {
				cRate = Integer.parseInt(companyRate);
			} catch (NumberFormatException e) {
				throw new BusinessException("请输入正确的公司缴存比例");
			}
		}
		if(cRate>100)
			throw new BusinessException("公司缴存比例不能大于100%");
		if(!StringUtils.isEmpty(privateRate)) {
			try {
				pRate = Integer.parseInt(privateRate);
			} catch (NumberFormatException e) {
				throw new BusinessException("请输入正确的个人缴存比例");
			}
		}
		if(pRate>100)
			throw new BusinessException("个人缴存比例不能大于100%");
		
		Map<String,Object> map = new HashMap<String,Object>(0);
		Map<String,Object> user = UserThreadLocal.get();
		if(!StringUtils.isEmpty(id))
			map.put("id", id);
		map.put("name", name);
		map.put("ssfId", ssfId);
		map.put("ssfCardinality", ssfC);
		map.put("companyRate", cRate);
		map.put("companyAmount", ssfC*cRate/100);
		map.put("privateRate", pRate);
		map.put("privateAmount", ssfC*pRate/100);
		map.put("permissionArea", user.get("deptId"));
		map.put("operatorId", user.get("id"));
		map.put("addTime", new Date());
		map.put("updateTime",null);
		map.put("operator",user.get("realName"));
		long result = ssfConfigurateService.saveOrupdateSsfConfigurateDetail(map);
		if(result>0)
			return BashijuResult.ok(result);
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
	
	/**
	 * 删除社保配置方案
	 * @Description: 删除社保配置方案
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delSsfConfigurate")
	@ResponseBody
	public BashijuResult delSsfConfigurate(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("社保方案编号不允许为空");
		boolean result = ssfConfigurateService.delSsfConfigurate(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	
	/**
	 * 删除社保方案详情
	 * @Description: 删除社保方案详情
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delSsfConfigurateDetail")
	@ResponseBody
	public BashijuResult delSsfConfigurateDetail(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id))
			throw new BusinessException("社保方案详情编号不允许为空");
		boolean result = ssfConfigurateService.delSsfConfigurateDetail(id);
		if(result)
			return BashijuResult.ok();
		else
			throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
}
