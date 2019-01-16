/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealConversionRateAnalysisMapper.java   
 * @Package com.bashiju.anal.mapper      
 * @author: zuoyuntao     
 * @date:   2018年11月24日 上午10:37:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.anal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 决策分析--成交转化率分析--持久层接口
 * 
 * @ClassName:DealConversionRateAnalysisMapper
 * @Description:决策分析--成交转化率分析--持久层接口
 * @author:zuoyuntao
 * @date:2018年11月24日 上午10:37:32
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 *             本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface DealConversionRateAnalysisMapper {
	/**
	 * 成交转化率分析--报表统计
	 * 
	 * @Title: queryPageDataList
	 * @author: zuoyuntao
	 * @Description:成交转化率分析--报表统计
	 * @param requestType
	 *            需求类型0：求购 1求租
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            截止日期
	 * @param deptId
	 *            部门ID
	 * @param deptTypeId
	 *            部门类型ID
	 * @param companyId
	 *            公司ID
	 * @return List<Map<String,Object>> JSON 格式为：
	 */
	public List<Map<String, Object>> queryPageDataList(
			  @Param("requestType") String requestType
			, @Param("beginDate") String beginDate
			, @Param("endDate") String endDate
			, @Param("deptId") String deptId
			, @Param("deptTypeId") String deptTypeId
			, @Param("companyId") String companyId);

}
