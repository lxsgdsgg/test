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
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  ArticleController   
 * @Description:(文章管理)   
 * @author: liwen
 * @date:   2018年11月5日 下午14:02:36   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	
	
	
	
	/**
	 * 
	 * 查询文章类型、公告类型  
	 * @Description:(查询文章类型、公告类型 的枚举数据)   
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping("/getArticleType")
	@ResponseBody
	public BashijuResult getArticleType(){
		Map<String,Object> info = new HashMap<>();
		String userId = UserThreadLocal.get().get("id").toString();
		//查出用户所能操作的城市
		List<Map<String, Object>> cityList = articleService.queryCity(Long.parseLong(userId));
		info.put("articleType", OaArticleTypeEnum.enumMap);
		info.put("noticeType", OaNoticeEnum.enumMap);
		info.put("examineStatus", ExamineStatusEnum.enumMap);
		info.put("cityInfo", cityList);
		info.put("roleGroup", UserThreadLocal.get().get("roleGroup"));
		return BashijuResult.ok(info);
	}
	
	
	/**
	 * 
	 * 保存文章   
	 * @Description:(保存OA的文章信息)   
	 * @param: @param jsonData
	 * @param: @return      
	 * @return: BashijuResult      
	 * @throws
	 */
	@RequestMapping("/saveArticle")
	@ResponseBody
	public BashijuResult saveArticle(String jsonData){
		Map<String,Object> user = UserThreadLocal.get();
		Map<String,Object> data = JSON.parseObject(jsonData, Map.class);
		data.put("companyId", user.get("companyId"));
		data.put("permissionArea", user.get("deptId"));
		data.put("operatorId", user.get("id"));
		data.put("operator",user.get("realName"));
		data.put("deptId",user.get("deptId"));
		Long res = articleService.saveArticle(data);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("保存失败");
		}
		
	}
	
	@RequestMapping("/getArticleList")
	@ResponseBody
	public Map<String,Object> getArticleList(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("type", request.getParameter("type"));
		conditions.put("cityCode", request.getParameter("cityCode"));
		conditions.put("examineStatus", request.getParameter("examineStatus"));
		conditions.put("title", request.getParameter("title"));
		conditions.put("classes", request.getParameter("classes"));
		Page<Map<String, Object>> pageInfo = articleService.queryArticleList(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	@RequestMapping(value="uploadImg")
	@ResponseBody
	public BashijuResult uploadFile(HttpServletRequest request) {
		try {
			Map<String,Object> res = articleService.saveImg(request);
			return BashijuResult.ok(res);
		}catch(Exception e){
			throw new BusinessException("上传错误");
		}
	}
	
	

	
	@RequestMapping("/delArticle")
	@ResponseBody
	public BashijuResult delArticle(int id){
		boolean res = articleService.delArticle(id);
		if(res) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("删除失败");
		}
		
	}
	
	@RequestMapping("/getExamineList")
	@ResponseBody
	public Map<String,Object> getExamineList(HttpServletRequest request,int page,int limit){
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("examineStatus", request.getParameter("examineStatus"));
		conditions.put("title", request.getParameter("title"));
		conditions.put("classes", request.getParameter("classes"));
		conditions.put("author", request.getParameter("author"));
		conditions.put("type", request.getParameter("type"));
		Page<Map<String, Object>> pageInfo = articleService.queryExamineList(conditions,page,limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
		
	}
	
	//确认
	@RequestMapping("/sureArticle")
	@ResponseBody
	public BashijuResult sureArticle(Long id){
		
		String res = articleService.sureArticle(id);
		if(!"".equals(res)) {
			return BashijuResult.ok(res);
		}else {
			throw new BusinessException("失败");
		}
		
	}
	//驳回
	@RequestMapping("/rejectArticle")
	@ResponseBody
	public BashijuResult rejectArticle(Long id,String reason){
		
		Map<String,Object> res = articleService.rejectArticle(id,reason);
		if(res!=null && res.size()>0) {
			return BashijuResult.ok(res);
		}else {
			throw new BusinessException("失败");
		}
		
	}
	
	//公告浏览
	@RequestMapping("/noticeBrowse")
	@ResponseBody
	public BashijuResult noticeBrowse(int id,int browseCnt){
		
		Long res = articleService.noticeBrowse(id,browseCnt);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("失败");
		}
		
	}
	//快讯浏览
	@RequestMapping("/newsBrowse")
	@ResponseBody
	public BashijuResult newsBrowse(int id,int browseCnt){
		
		Long res = articleService.newsBrowse(id,browseCnt);
		if(res>0) {
			return BashijuResult.ok();
		}else {
			throw new BusinessException("失败");
		}
		
	}
	//最新公告查询
	@RequestMapping("/queryNoticeInfo")
	@ResponseBody
	public BashijuResult queryNoticeInfo(String cityCode,int daynum){
		int a = 1;
		System.out.println(a);
		List<Map<String,Object>> res = articleService.queryNoticeInfo(cityCode,daynum);
		
		return BashijuResult.ok(res);
		
		
	}
	
	//最新快讯查询
	@RequestMapping("/queryNewsInfo")
	@ResponseBody
	public BashijuResult queryNewsInfo(String cityCode,int daynum){
		
		List<Map<String,Object>> res = articleService.queryNewsInfo(cityCode,daynum);
		
		return BashijuResult.ok(res);
		
		
	}
	//详细信息查询
	@RequestMapping("/queryArticleDetail")
	@ResponseBody
	public BashijuResult queryArticleDetail(int id){
		
		Map<String,Object> res = articleService.queryArticleDetail(id);
		
		return BashijuResult.ok(res);
		
		
	}
	
}
