/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReportService.java   
 * @Package com.bashiju.manage.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年11月27日 上午9:15:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

/**   
 * 报表服务接口  
 * @ClassName:  ReportService   
 * @Description:报表服务接口  
 * @author: wangpeng
 * @date:   2018年11月27日 上午9:15:06   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ReportService {
	
	/**
	 * 日报
	 * @Title: queryDailyReport   
	 * @Description: 日报
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDailyReport();
	
	/**
	 * 提交日报  
	 * @Title: dailyReportSubmit   
	 * @Description: 提交日报  
	 * @param map
	 * @return: boolean
	 */
	public boolean dailyReportSubmit(List<Map<String,Object>> map);
	
	
	 /**
	  * 分页查询日报审批
	  * @Title: queryDailyReportExam   
	  * @Description: 分页查询日报审
	  * @param page 当前页数
	  * @param limit 每页总条数
	  * @return: Page<Map<String,Object>>
	  */
	 Page<Map<String,Object>> queryDailyReportExam(Map<String,Object> paramMap,int page,int limit);
	 
	 /**
	  *  日报批示
	  * @Title: reportDailyInstructions   
	  * @Description: 日报批示
	  * @param paramMap
	  * @return: boolean
	  */
	 public boolean reportDailyInstructions(List<Map<String,Object>> paramMap);
}
