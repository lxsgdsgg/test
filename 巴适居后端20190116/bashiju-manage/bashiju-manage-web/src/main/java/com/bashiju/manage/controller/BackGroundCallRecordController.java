
package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.manage.service.BackGroundCallRecordService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;

/**  
 * 后台通话记录控制器 
 * @ClassName:  BackGroundCallRecordController   
 * @Description:后台通话记录控制器
 * @author: wangpeng
 * @date:   2019年1月4日 上午10:22:39   
 * @Copyright: 2019 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="backGroundCallRecord")
public class BackGroundCallRecordController extends BaseController {
	
	@Autowired
	private BackGroundCallRecordService backGroundCallRecordService;
	
	/**
	 * 条件查询后台通话记录
	 * @Title: queryBackGroundCallRecord   
	 * @Description: 条件查询后台通话记录
	 * @param companyId 公司编号
	 * @param companyName 公司名称
	 * @param followerId 跟进人编号
	 * @param page 当前页
	 * @param limit  每页总条数
	 * @return: Object
	 */
	@RequestMapping(value="queryBackGroundCallRecord")
	@ResponseBody
	public Object queryBackGroundCallRecord(String companyId,String companyName,String followerId,int page,int limit) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("companyId", companyId);
		paramMap.put("companyName", companyName);
		paramMap.put("followerId", followerId);
		Page<Map<String,Object>> map = backGroundCallRecordService.queryBackGroundCallRecord(paramMap, page, limit);
		return this.getPageResult(map);
	}
}
