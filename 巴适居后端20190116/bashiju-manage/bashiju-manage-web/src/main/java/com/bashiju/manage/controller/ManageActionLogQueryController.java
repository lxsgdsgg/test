/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ManageActionLogQueryController.java   
 * @Package com.bashiju.manage.controller      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:04:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.LogPlatformSourceTypeEnum;
import com.bashiju.enums.LogSystemSourceTypeEnum;
import com.bashiju.manage.service.IManageActionLogQueryService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 后台日志查询控制器
 * @ClassName:ManageActionLogQueryController
 * @Description:后台日志查询控制器
 * @author:zuoyuntao
 * @date:2018年11月30日 下午4:04:19
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@RequestMapping(value="manageLogQuery")
@Controller
public class ManageActionLogQueryController extends BaseController{

	/**
	 * 后台操作日志查询服务接口
	 */
	@Autowired
	private IManageActionLogQueryService mIManageActionLogQueryService;
	
	/**
	 * 查询后台日志--带分页
	 * @Title: queryManageActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询后台日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryManageActionLogPageList")
	@ResponseBody
	public Object queryManageActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIManageActionLogQueryService.auxManageActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 查询系统日志--带分页
	 * @Title: auxSystemActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询系统日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="auxSystemActionLogPageList")
	@ResponseBody
	public Object auxSystemActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIManageActionLogQueryService.auxSystemActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 根据后台编号查询后台操作日志 --带分页
	 * @Title: queryManageActionLogByManageId
	 * @author: zuoyuntao  
	 * @Description:根据后台编号查询后台操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param dealId 后台编号
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryManageActionLogByManageId")
	@ResponseBody
	public Object queryManageActionLogByManageId(int page,int limit,String dealId) {
		if(StringUtil.isEmpty(dealId)) {
			throw new BusinessException("后台编号为空!");
		}
		return this.getPageResult(
				mIManageActionLogQueryService.auxManageActionLogByManageId(page, limit, dealId));
	}
	/**
	 * 根据后台类型查询后台操作日志--带分页
	 * @Title: queryManageActionLogByManageType
	 * @author: zuoyuntao  
	 * @Description:根据后台编号查询后台操作日志
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @param manageType 后台类型
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryManageActionLogByManageType")
	@ResponseBody
	public Object queryManageActionLogByManageType(int page,int limit,String manageType) {
		if(StringUtil.isEmpty(manageType)) {
			throw new BusinessException("后台管理类型为空!");
		}
		return this.getPageResult(
				mIManageActionLogQueryService.auxManageActionLogByManageType(page, limit, manageType));
	}
	/**
	 * 根据类型获取枚举对象（参照LogPlatformSourceTypeEnum类中的值传递）
	 * @Title: returnOperatorTypeObj
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象（参照LogPlatformSourceTypeEnum类中的值传递）
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="returnOperatorTypeObj")
	@ResponseBody
	public Object returnOperatorTypeObj() {
		List<Map<String , String>> retList = LogPlatformSourceTypeEnum.enumList;
		return retList;
	}
	/**
	 * 根据类型获取枚举对象（参照LogSystemSourceTypeEnum类中的值传递）
	 * @Title: auxSystemActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象（参照LogSystemSourceTypeEnum类中的值传递）
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="returnSystemTypeObj")
	@ResponseBody
	public Object returnSystemOperatorTypeObj() {
		List<Map<String , String>> retList = LogSystemSourceTypeEnum.enumList;
		return retList;
	}
}
