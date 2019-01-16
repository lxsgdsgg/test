package com.bashiju.manage.controller;

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
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.HousePropertyArticleClasses;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.manage.service.HousePropertyArticleClassesService;
import com.bashiju.manage.service.ICommunityService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  HousePropertyArticleClassesController   
 * @Description:房产文章分类控制层  
 * @author: wangpeng
 * @date:   2018年7月21日 下午12:00:58   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="housePropertyArticleClasses")
public class HousePropertyArticleClassesController extends BaseController {
	
	@Autowired
	private HousePropertyArticleClassesService housePropertyArticleClassesService;
	
	
	@Autowired	
	private ICommunityService communityService;
	
	/**
	 * @Title: enterHousePropertyArticleClassesPage   
	 * @Description: 进入房产文章分类页面
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterHousePropertyArticleClasses")
	public ModelAndView enterHousePropertyArticleClassesPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "housePropertyArticleClasses/housePropertyArticleClasses");
		//等级枚举
		mv.addObject("levels", JSON.toJSONString(HousePropertyArticleClasses.enumMap));
		mv.addObject("level",HousePropertyArticleClasses.enumList);
		//城市数据源
		List<Map<String,Object>> areaList = communityService.queryAreaSelect();
		if(areaList!=null && areaList.size()>0) {
			mv.addObject("areaList", JSON.toJSONString(areaList));
		}
		return mv;
	}
	/**
	 * @Title: enterhousePropertyArticleClassesAddPage   
	 * @Description: 进入房产文章分类详情页面
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterHousePropertyArticleClassesDetail")
	public ModelAndView enterhousePropertyArticleClassesAddPage(HttpServletRequest request,HttpServletResponse response,String houseAticleId) {
		ModelAndView mv = getModelAndView(request, response, "housePropertyArticleClasses/housePropertyArticleClassesDetail");
		List<Map<String,Object>> area = communityService.queryAreaSelect();
		if(area!=null && area.size()>0) {
			mv.addObject("areaList", JSON.toJSONString(area));
		}
		//等级枚举
		mv.addObject("level", HousePropertyArticleClasses.enumList);
		System.out.println( HousePropertyArticleClasses.enumList);
		mv.addObject("LEVEL", JSON.toJSONString(HousePropertyArticleClasses.enumMap));
		//类型枚举
		mv.addObject("houseArticleType", HouseArticleTypeEnum.enumList);
		//得到当前登录角色组
		mv.addObject("roleGroup",UserThreadLocal.get().get("roleGroup"));
		//得到超级管理员的枚举值
		mv.addObject("ADMIN", RoleGroupEnum.ADMIN.getCode());
		//通过id查询
		Map<String, Object> map = housePropertyArticleClassesService.queryHousePropertyArticleClassesById(houseAticleId);
		if(map!=null && map.size()>0)
			mv.addObject("houseAticle", map);
		return mv;
	}
	
	/**
	 * @Title: queryHousePropertyArticleClasses   
	 * @Description:查询房产文章分类   
	 * @param page 当前页
	 * @param limit 每页总条数
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryHousePropertyArticleClasses")
	@ResponseBody
	public Map<String,Object> queryHousePropertyArticleClasses(String name,String level,String cityCode,int page,int limit){
		Page<Map<String,Object>> pages = housePropertyArticleClassesService.queryHousePropertyArticleClasses(name,level,cityCode,page, limit);
		Map<String,Object> map = getPageResult(pages);
		return map;
	}
	/**
	 * @Title: saveOrUpdateHousePropertyArticleClasses   
	 * @Description: 新增或编辑房产文章分类   
	 * @param jsonData 保存的参数
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveOrUpdateHousePropertyArticleClasses")
	@ResponseBody
	public BashijuResult saveOrUpdateHousePropertyArticleClasses(String jsonData) {
		if(StringUtils.isEmpty(jsonData)) {
			throw new BusinessException("参数不能为空");
		}
		Map<String,Object> map = (Map<String,Object>) JSONObject.parse(jsonData);
		boolean result = housePropertyArticleClassesService.saveOrUpdateHousePropertyArticleClasses(map);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	/**
	 * @Title: queryHousePropertyArticleClassesNameByLevel   
	 * @Description: 条件查询房产文章分类名称  
	 * @param level 等级
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryHousePropertyArticleClassesNameByLevel")
	@ResponseBody
	public List<Map<String,Object>> queryHousePropertyArticleClassesNameByLevel(String level,String type){
		List<Map<String, Object>> name = housePropertyArticleClassesService.queryHousePropertyArticleClassesNameByLevel(level,type);
		return name;
	}
	/**
	 * @Title: delHousePropertyArticleClasses   
	 * @Description: 逻辑删除房产文章分类   
	 * @param houseAticleId 房产文章分类id
	 * @return: BashijuResult
	 */
	@RequestMapping(value="delHousePropertyArticleClasses")
	@ResponseBody
	public BashijuResult delHousePropertyArticleClasses(String houseAticleId) {
		boolean result = housePropertyArticleClassesService.delHousePropertyArticleClasses(houseAticleId);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_DEL_ERROR);
	}
	/**
	 * 
		 * 查询文章类型树形数据
		 * @Description: 查询文章类型树形数据 
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryArticleClassTree")
	@ResponseBody
	public BashijuResult queryArticleClassTree(String cityCode) {
		List<Map<String, Object>> result = housePropertyArticleClassesService.queryArticleClassTree();
		List<Integer> types= housePropertyArticleClassesService.queryCityArticleClassesTree(cityCode);
		Map<String, Object> map=new HashMap<>();
		map.put("root", result);
		map.put("ids", types);
		return BashijuResult.ok(map);
	}
	/**
	 * 
		 * 查询城市关联的文章类型列表
		 * @Description: 查询城市关联的文章类型列表
		 * @param name
		 * @param level
		 * @param cityCode
		 * @param page
		 * @param limit
		 * @return 
		 * Map<String,Object>
	 */
	@RequestMapping(value="queryCityArticleClasseList")
	@ResponseBody
	public Map<String,Object> queryCityArticleClasseList(String name, String level, String cityCode, int page,
			int limit){
		Page<Map<String, Object>> pageInfo = housePropertyArticleClassesService.queryCityArticleClasseList(name,level,cityCode, page, limit);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 
		 * 保存城市关联文章类型数据
		 * @Description: 保存城市关联文章类型数据 
		 * @param cityCode
		 * @param jsonArray
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="saveCityArticleClasses")
	@ResponseBody
	public BashijuResult saveCityArticleClasses(String cityCode,String jsonArray){
		housePropertyArticleClassesService.saveCityArticleClasses(cityCode,jsonArray);
		return BashijuResult.ok();
	}
	
	
}
