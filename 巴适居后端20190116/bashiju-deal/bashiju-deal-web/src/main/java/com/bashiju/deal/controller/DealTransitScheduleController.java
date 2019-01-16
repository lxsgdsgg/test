/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealTransitScheduleController.java   
 * @Package com.bashiju.deal.controller      
 * @author: zuoyuntao     
 * @date:   2018年10月22日 上午11:02:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.deal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bashiju.deal.service.IDealTransitScheduleService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

/**
 * 成交过户进度查询控制器
 * @ClassName:DealTransitScheduleController
 * @Description:成交过户进度查询控制器
 * @author:zuoyuntao
 * @date:2018年10月22日 上午11:02:58
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Controller
@RequestMapping(value="transitScheduleQuery")
public class DealTransitScheduleController extends BaseController{
	/**
	 * 过户进度服务接口
	 */
	@Autowired
	private IDealTransitScheduleService mIDealTransitScheduleService;
	/**
	 * 查询过户进度数据信息 
	 * @Title: queryDealScheduleDataPageList
	 * @author: zuoyuntao  
	 * @Description:查询过户进度数据信息  
	 * @return      
	 * Object
	 */
	@RequestMapping(value="queryDealScheduleDataPageList")
	@ResponseBody
	public Object queryDealScheduleDataPageList(String jsonData,int page,int limit) {
		Map<String,Object> paramMap = null;
		if(StringUtil.isNotEmpty(jsonData)) {
			paramMap = JSON.parseObject(jsonData);
		}
		Page<Map<String,Object>> pageObj = mIDealTransitScheduleService.queryDealScheduleDataPageList(paramMap, page, limit);
		return this.getPageResult(pageObj);
	}
}
