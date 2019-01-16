
package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.ReportMapper;
import com.bashiju.manage.service.ReportService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**   
 * @ClassName:  ReportServiceImpl   
 * @Description:日报服务接口
 * @author: wangpeng
 * @date:   2018年11月27日 下午4:07:30   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ReportServiceImpl extends CommonSqlServie  implements ReportService  {
	
	@Autowired
	private ReportMapper reportMapper; 
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**   
	 * <p>Title: queryDailyReport</p>   
	 * <p>Description: </p>   
	 * @param beginTime
	 * @param endTime
	 * @return   
	 * @see com.bashiju.manage.service.ReportService#queryDailyReport(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public List<Map<String, Object>> queryDailyReport() {
		String agentId = UserThreadLocal.get().get("id").toString();
		Map<String,Object> map = reportMapper.queryIsExistSubmitReport(agentId);
			//未上报
			String operatorId = UserThreadLocal.get().get("id").toString();
			List<Map<String, Object>> report =  reportMapper.queryDaily(operatorId);
			for(int i=0;i<report.size();i++) {
				report.get(i).put("agentId", agentId);
				report.get(i).put("deptId", UserThreadLocal.get().get("deptId").toString());
				report.get(i).put("companyId", UserThreadLocal.get().get("companyId").toString());
				report.get(i).put("agentName", UserThreadLocal.get().get("realName").toString());
				report.get(i).put("nowadays", new Date());
				if(map!=null) {
					if(map.get("experience")!=null) {
						report.get(i).put("isSubmit", true);//已上报
						report.get(i).put("experience", map.get("experience"));
						report.get(i).put("approval", map.get("approval"));
						report.get(i).put("id", map.get("id"));
					}else {
						report.get(i).put("isSubmit", false);//未上报
					}	
				}
		     }
			return  report;
	}
	/**   
	 * <p>Title: DailyReportSubmit</p>   
	 * <p>Description: </p>   
	 * @param map
	 * @return   
	 * @see com.bashiju.manage.service.ReportService#DailyReportSubmit(java.util.Map)   
	 */
	
	@Override
	public boolean dailyReportSubmit(List<Map<String, Object>> map) {
		List<Map<String,Object>> addArr = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> updateArr = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> paramMap: map ) {
			if(!paramMap.containsKey("id") || StringUtils.isEmpty(paramMap.get("id").toString()) 
					|| paramMap.get("id")== null) {
				paramMap.put("permissionArea", UserThreadLocal.get().get("deptId").toString());
				paramMap.put("operatorId", UserThreadLocal.get().get("id").toString());
				addArr.add(paramMap);//新增
			}else {
				updateArr.add(paramMap);//修改
			}
		}
		if(addArr.size()>0)
			this.batchCommonOperationDatabase(addArr, "report_daily", false);
		if(updateArr.size()>0)
			this.batchCommonOperationDatabase(updateArr, "report_daily", "id", false);
		ExecutionResult.descFormat("", "提交日报");
		return true;
	}
	/**   
	 * <p>Title: queryDailyReportExam</p>   
	 * <p>Description: </p>   
	 * @param page
	 * @param limit
	 * @return   
	 * @see com.bashiju.manage.service.ReportService#queryDailyReportExam(int, int)   
	 */
	
	@Override
	public Page<Map<String, Object>> queryDailyReportExam(Map<String,Object> paramMap ,int page, int limit) {
		PageHelper.startPage(page, limit);
		//TODO:权限？
		Page<Map<String, Object>> map = (Page<Map<String, Object>>) reportMapper.queryDailyReportExam(paramMap);
		return map;
	}
	/**   
	 * <p>Title: ReportDailyInstructions</p>   
	 * <p>Description: 日报批示</p>   
	 * @param paramMap
	 * @see com.bashiju.manage.service.ReportService#ReportDailyInstructions(java.util.Map)   
	 */
	
	@Override
	public boolean reportDailyInstructions(List<Map<String,Object>> paramMap) {
		dataAuthHelper.auth(MenuEnum.MENU_852.getCode(), UserThreadLocal.get().get("id").toString());
		for(Map<String,Object> map: paramMap) {
			map.put("approvaler", UserThreadLocal.get().get("id"));
			map.put("approvalTime", new Date());
		}
		boolean result = this.batchCommonOperationDatabase(paramMap, "report_daily", "id", false);
		if(result)
			return true;
		return false;
	}
	

}
