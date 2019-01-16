/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealComprehensiveAnalysisServiceImpl.java   
 * @Package com.bashiju.anal.service.impl      
 * @author: zuoyuntao     
 * @date:   2018年11月28日 下午2:03:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.anal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.anal.mapper.DealComprehensiveAnalysisMapper;
import com.bashiju.anal.service.IDealComprehensiveAnalysisService;

/**
 * 决策分析--成交综合分析--服务接口实现类
 * @ClassName:DealComprehensiveAnalysisServiceImpl
 * @Description:决策分析--成交综合分析--服务接口实现类
 * @author:zuoyuntao
 * @date:2018年11月28日 下午2:03:58
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Service
public class DealComprehensiveAnalysisServiceImpl implements IDealComprehensiveAnalysisService{
	/**
	 * 成交综合分析持久层对象
	 */
	@Autowired
	private DealComprehensiveAnalysisMapper mDealComprehensiveAnalysisMapper;
	/**
	 * 成交转化率分析--报表统计
	 * @Title: queryPageDataList
	 * @author: zuoyuntao
	 * @Description:成交转化率分析--报表统计
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> queryComprehensiveAnalysisPageDataList(Map<String,Object> paramMap){
		return mDealComprehensiveAnalysisMapper.queryComprehensiveAnalysisPageDataList(paramMap);
	}
}
