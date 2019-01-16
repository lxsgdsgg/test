package com.bashiju.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.enums.ExamineStatusEnum;
import com.bashiju.enums.OaArticleTypeEnum;
import com.bashiju.enums.OaNoticeEnum;
import com.bashiju.oa.service.ArticleService;
import com.bashiju.oa.service.PersonnelService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  PersonnelController   
 * @Description:(人事变动)   
 * @author: liwen
 * @date:   2018年11月20日 上午10:02:36   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
public class PersonnelController {
	
	@Autowired
	PersonnelService personnelService;
	
	
	/**
	 * 
	 * 保存人事信息
	 * @Description:(保存人事信息)   
	 * @param: @param jsonData
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping("/savePersonnel")
	@ResponseBody
	public BashijuResult savePersonnel(String jsonData){
		Map<String,Object> user = UserThreadLocal.get();
		Map<String,Object> data = JSON.parseObject(jsonData, Map.class);
		Long res = personnelService.savePersonnel(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("保存失败");
		}
		
	}
	
	/**
	 * 
	 * 保存人事变动
	 * @Description:(保存人事变动)   
	 * @param: @param jsonData
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping("/savePersonnelChange")
	@ResponseBody
	public BashijuResult savePersonnelChange(String jsonData){
		Map<String,Object> user = UserThreadLocal.get();
		Map<String,Object> data = JSON.parseObject(jsonData, Map.class);
		Long res = personnelService.savePersonnelChange(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("保存失败");
		}
		
	}
	
	/**
	 * 
	 * 查询人事信息 
	 * @Description: 查询人事信息列表   
	 * @param: @param request
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping("/getPersonnelList")
	@ResponseBody
	public Map<String,Object> getPersonnelList(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("isPost", request.getParameter("isPost"));
		conditions.put("deptId", request.getParameter("deptId"));
		conditions.put("postId", request.getParameter("postId"));
		conditions.put("startEntryDate", request.getParameter("startEntryDate"));
		conditions.put("endEntryDate", request.getParameter("endEntryDate"));
		conditions.put("startBirthDate", request.getParameter("startBirthDate"));
		conditions.put("endBirthDate", request.getParameter("endBirthDate"));
		conditions.put("postChangeTypeId", request.getParameter("postChangeTypeId"));
		conditions.put("vagueType", request.getParameter("vagueType"));
		conditions.put("vagueData", request.getParameter("vagueData"));
		Page<Map<String, Object>> pageInfo = personnelService.queryPersonnelList(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	
	/**
	 * 
	 * 人事变动审核   
	 * @Description: 人事变动审核列表   
	 * @param: @param request
	 * @param: @param page
	 * @param: @param limit
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping("/getPersonnelExamine")
	@ResponseBody
	public Map<String,Object> getPersonnelExamine(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("status", request.getParameter("status"));
		conditions.put("userId", request.getParameter("userId"));
		conditions.put("deptId", request.getParameter("deptId"));
		conditions.put("startChangeTime", request.getParameter("startChangeTime"));
		conditions.put("endChangeTime", request.getParameter("endChangeTime"));
		conditions.put("postChangeTypeId", request.getParameter("postChangeTypeId"));
		Page<Map<String, Object>> pageInfo = personnelService.queryPersonnelExamine(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	//同意变动
	@RequestMapping("/surePersonnel")
	@ResponseBody
	public BashijuResult surePersonnel(Long id,String reason){
		
		Long res = personnelService.surePersonnel(id,reason);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("失败");
		}
		
	}
	
	//不同意变动
	@RequestMapping("/rejectPersonnel")
	@ResponseBody
	public BashijuResult rejectPersonnel(Long id,String reason){
		
		Long res = personnelService.rejectPersonnel(id,reason);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("失败");
		}
		
	}
	
	//转上级审核
	@RequestMapping("/turnPersonnel")
	@ResponseBody
	public BashijuResult turnPersonnel(Long id,String reason){
		
		Map<String,Object> res = personnelService.turnPersonnel(id,reason);
		if(res!=null && res.size()>0) {
			return BashijuResult.ok(res);
		}else {
			throw new BusinessException("失败");
		}
		
	}
	
	//上传文章图片
	@RequestMapping(value="uploadPoto")
	@ResponseBody
	public BashijuResult uploadPoto(HttpServletRequest request) {
		
		Map<String,Object> res = personnelService.saveImg(request);
		if(res!=null && res.size()>0) {
			return BashijuResult.ok(res);
		}else{
			throw new BusinessException("上传错误");
		}
	}
	
}
