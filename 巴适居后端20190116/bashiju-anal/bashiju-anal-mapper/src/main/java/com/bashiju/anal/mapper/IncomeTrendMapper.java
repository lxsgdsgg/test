/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FollowRecordMapper.java   
 * @Package com.bashiju.anal.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月8日 下午3:54:59   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  IncomeTrendMapper   
 * @Description:收入走势分析   
 * @author: liwen
 * @date:   2018年11月27日 上午10:54:59   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IncomeTrendMapper {
	
	/**
	 * 
	 * @Description: 收入走势分析    
	 * @param year 年份
	 * @param deptId 部门编号
	 * @param deptTypeId 部门所属类型
	 * @param companyId 所属公司
	 * @return: List<Map<String,Object>>  
	 */
	public List<Map<String,Object>> queryIncomeTrendRecord(@Param("moneyType")String moneyType,@Param("year")String year,@Param("deptId")String deptId
			,@Param("deptTypeId")String deptTypeId,@Param("companyId")String companyId);

	
	public Map<String,Object> queryAchievement (@Param("year")String year,@Param("id")String id
			,@Param("deptTypeId")String deptTypeId);
	
	public Map<String,Object> queryIncome (@Param("year")String year,@Param("id")String id
			,@Param("deptTypeId")String deptTypeId);
}
