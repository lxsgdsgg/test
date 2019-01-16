/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ManageActionLogQueryController.java   
 * @Package com.bashiju.oa.controller      
 * @author: zuoyuntao     
 * @date:   2018年11月30日 下午4:04:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.enums.LogBusinessOperateTypeEnum;
import com.bashiju.oa.service.IOaActionLogQueryService;
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

@RequestMapping(value="oaLogQuery")
@Controller
public class OaActionLogQueryController extends BaseController{

	/**
	 * 办公平台操作日志查询服务接口
	 */
	@Autowired
	private IOaActionLogQueryService mIOaActionLogQueryService;
	
	/**
	 * 查询后台日志--带分页
	 * @Title: queryOaActionLogPageList
	 * @author: zuoyuntao  
	 * @Description:查询后台日志--带分页
	 * @param page 当前页 
	 * @param limit 每页最大数
	 * @param paramMap 参数对象
	 * @return      
	 * Page<Map<String,Object>>
	 */
	@RequestMapping(value="queryOaActionLogPageList")
	@ResponseBody
	public Object queryOaActionLogPageList(int page,int limit,String jsonData) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSONObject.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = 
				mIOaActionLogQueryService.auxOaActionLogPageList(page, limit,paramMap);
		return this.getPageResult(pageObj);
	}
	/**
	 * 根据类型获取枚举对象（参照LogSystemSourceTypeEnum类中的值传递）
	 * @Title: returnOaOperatorTypeObj
	 * @author: zuoyuntao  
	 * @Description:根据类型获取枚举对象（参照LogSystemSourceTypeEnum类中的值传递）
	 * @return      
	 * Object 
	 */
	@RequestMapping(value="returnOaOperatorTypeObj")
	@ResponseBody
	public Object returnOaOperatorTypeObj() {
		List<Map<String , String>> retList = LogBusinessOperateTypeEnum.queryEnumByType("6");
		return retList;
	}
}
