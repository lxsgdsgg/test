/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReportMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年11月26日 上午10:31:05   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  ReportMapper   
 * @Description:报表映射接口(周、日、月)
 * @author: wangpeng
 * @date:   2018年11月26日 上午10:31:05   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ReportMapper {
	
	/**
	 *  日报
	 * @Title: queryDaily   
	 * @Description: 日报
	 * @param operatorId 当前登录人编号
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryDaily(@Param("operatorId")String operatorId);
	
	/**
	 *  条件查询当前登录人是否提交日报
	 * @Title: queryIsExistSubmitReport   
	 * @Description: 条件查询当前登录人是否提交日报
	 * @param agentId 当前登录人编号
	 * @return: Map<String,Object> 
	 */
	Map<String,Object> queryIsExistSubmitReport(@Param("agentId")String agentId);
	
	/**
	 * 查詢日报
	 * @Title: queryDailyReport   
	 * @Description:查詢日报
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryDailyReportExam(Map<String,Object> paramMap);
}
