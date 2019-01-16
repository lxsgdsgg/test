/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  IDealComprehensiveAnalysisService.java   
 * @Package com.bashiju.anal.service      
 * @author: zuoyuntao     
 * @date:   2018年11月28日 下午2:02:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.anal.service;

import java.util.List;
import java.util.Map;

/**
 * 决策分析--成交综合分析--服务接口
 * @ClassName:IDealComprehensiveAnalysisService
 * @Description:决策分析--成交综合分析--服务接口
 * @author:zuoyuntao
 * @date:2018年11月28日 下午2:02:57
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface IDealComprehensiveAnalysisService {
	/**
	 * 成交转化率分析--报表统计
	 * @Title: queryPageDataList
	 * @author: zuoyuntao
	 * @Description:成交转化率分析--报表统计
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryComprehensiveAnalysisPageDataList(Map<String,Object> paramMap);
}
