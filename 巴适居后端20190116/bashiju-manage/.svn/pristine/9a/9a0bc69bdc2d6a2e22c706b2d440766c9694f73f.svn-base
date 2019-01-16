/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReportController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年11月27日 上午9:33:54   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.manage.service.ReportService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  ReportController   
 * @Description:报表控制层（周月日）   
 * @author: wangpeng
 * @date:   2018年11月27日 上午9:33:54   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="report")
public class ReportController extends BaseController {
		
	@Autowired
	private ReportService reportService;
	
	/**
	 * 日报
	 * @Title: queryDailyReport   
	 * @Description: 日报   
	 * @param beginTime 起始时间
	 * @param endTime 截止时间
	 * @return: List<Map<String,Object>>
	 */
	@RequestMapping(value="queryDailyReport")
	@ResponseBody
	public List<Map<String,Object>> queryDailyReport(){
		List<Map<String,Object>> report = reportService.queryDailyReport();
		return report;
	}
	
	/**
	 * 提交日报
	 * @Title: DailyReportSubmit   
	 * @Description: 提交日报
	 * @return      
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="dailyReportSubmit")
	@ResponseBody
	public BashijuResult dailyReportSubmit(String jsonData) {
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("日报信息不能为空");
		List<Map<String,Object>> map = (List<Map<String, Object>>) JSONArray.parse(jsonData);
		boolean result = reportService.dailyReportSubmit(map);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);
	}
	
	/**
	 *  查询日报列表
	 * @Title: queryDailyReportExam   
	 * @Description: 查询日报列表
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryDailyReportExam")
	@ResponseBody
	public Map<String,Object> queryDailyReportExam(int page,int limit,String deptId){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptId", deptId);
		Page<Map<String,Object>> pages = reportService.queryDailyReportExam(paramMap,page, limit);
		Map<String,Object> map = getPageResult(pages);
		return map;
	}
	
	/**
	 *  日报批示
	 * @Title: reportDailyInstructions   
	 * @Description: 日报批示
	 * @param jsonData
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="reportDailyInstructions")
	@ResponseBody
	public BashijuResult reportDailyInstructions(String jsonData) {
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("批示信息不能为空");
		List<Map<String,Object>> paramMap = (List<Map<String, Object>>) JSONArray.parse(jsonData);
		boolean result = reportService.reportDailyInstructions(paramMap);
		if(result)
	       return BashijuResult.ok();
		throw new BusinessException(ErrorCodeEnum.SYSTEM_UPFDATE_ERROR);
	}
}
