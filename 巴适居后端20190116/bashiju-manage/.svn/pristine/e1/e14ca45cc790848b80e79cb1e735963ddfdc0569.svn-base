/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WageSchemeSettingController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:工资方案配置控制器
 * @author: zuoyuntao     
 * @date:   2018年6月22日 上午9:29:54   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.manage.service.IWageSchemaSettingService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 工资方案配置控制器
 * @ClassName:WageSchemeSettingController
 * @Description:工资方案配置控制器
 * @author:zuoyuntao
 * @date:2018年6月22日 上午9:29:54
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="wageSchemeSetting")
public class WageSchemeSettingController extends BaseController {
	/**
	 * 构造树接口
	 */
	@Autowired
	private ICommunityService communityService;
	/**
	 * 工资方案配置接口
	 */
	@Autowired
	private IWageSchemaSettingService mIWageSchemaSettingService;
	/**
	 * 获取主页面视图
	 * @Title: getWageSchemeSettingPage
	 * @author: zuoyuntao  
	 * @Description:获取主页面视图
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return      
	 * ModelAndView JSON 格式为：{}     
	 * @throws
	 */
	@RequestMapping(value="getWageSchemeSettingPage")
	public ModelAndView getWageSchemeSettingPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = this.getModelAndView(request, response, "wageSchemaSetting/wage_schema_set");
		List<Map<String, Object>> cityList = communityService.queryAreaSelect();
		model.addObject("cityList",JSONObject.toJSON(cityList));
		return model;
	}
	
	/**
	 * 查询工资方案数据
	 * @Title: queryWageSchemaSetAllDataList
	 * @author: zuoyuntao  
	 * @Description:查询工资方案数据
	 * @param jsonData 请求参数集合
	 * @param page
	 * @param limit
	 * @return      
	 * Object JSON 格式为：{}     
	 * @throws
	 */
	@RequestMapping(value="queryWageSchemaSetAllDataList")
	@ResponseBody
	public Object queryWageSchemaSetAllDataList(String jsonData, int page, int limit
			,String extractName,String areaCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(jsonData)) {
			paraMap = JSON.parseObject(jsonData);
		}
		paraMap.put("extractName", extractName);
		paraMap.put("areaCode", areaCode);
		Page<Map<String, Object>> pageObj = mIWageSchemaSettingService
				.queryWageSchemaInfoWithPage(paraMap, page,limit);
		return getPageResult(pageObj);
	}
	/**
	 * 保存或修改工资方案配置信息
	 * @Title: saveOrUpdateWageSchema
	 * @author: zuoyuntao  
	 * @Description:保存或修改工资方案配置信息  
	 * @param jsonData
	 * @return      
	 * BashijuResult    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateWageSchemaInfo")
	@ResponseBody
	public BashijuResult saveOrUpdateWageSchemaInfo(String jsonData) {
		if(StringUtil.isEmpty(jsonData)) {
			throw new BusinessException("数据错误!");
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map<String,Object> jurgeParaMap = new HashMap<String,Object>();
		paraMap = (Map<String,Object>) JSONObject.parse(jsonData);
		paraMap.put("companyId", UserThreadLocal.get().get("companyId"));
		paraMap.put("operatorId", UserThreadLocal.get().get("id"));
		paraMap.put("permissionArea", UserThreadLocal.get().get("deptId"));
		
		jurgeParaMap.put("areaCode", paraMap.get("areaCode"));
		jurgeParaMap.put("extractName", paraMap.get("extractName"));
		jurgeParaMap.put("companyId", UserThreadLocal.get().get("companyId"));
		if(mIWageSchemaSettingService.jurgeWageDataExists(jurgeParaMap) 
				&& "null".equals(String.valueOf(paraMap.get("id")))) {
			throw new BusinessException("此方案已存在");
		}
		if(StringUtil.isEmpty(String.valueOf(paraMap.get("id")))) {
			paraMap.remove("id");
		}
		mIWageSchemaSettingService.saveOrUpdateWageSchemaInfo(paraMap);
		return BashijuResult.ok();
	}
	
	/**
	 * 删除工资方案配置信息
	 * @Title: deleteWageSchema
	 * @author: zuoyuntao  
	 * @Description:删除工资方案配置信息
	 * @param wageId 方案id
	 * @param areaCode 区划编码
	 * @return      
	 * BashijuResult    
	 * @throws
	 */
	@RequestMapping(value="deleteWageSchemaInfo")
	@ResponseBody
	public BashijuResult deleteWageSchemaInfo(String wageId) {
		if(StringUtil.isEmpty(wageId)) {
			throw new BusinessException("传入参数错误");
		}
		mIWageSchemaSettingService.deleteWageSchema(wageId);
		return BashijuResult.ok();
	}
}
