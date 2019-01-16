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
 * @ClassName:  DeptIncomeMapper   
 * @Description:门店损益财务表
 * @author: Liwen
 * @date:   2018年11月26日 下午3:54:59   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptIncomeMapper {
	
	/**
	 * 
	 * @Description: 门店损益财务表
	 * @param months 查询月份
	 * @param companyId 所属公司
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryDeptIncome(@Param("months")String months,@Param("companyId")String companyId);

	/**
	 * 
	 * @Description:  查询部门树列表 
	 * @param roleGroup 所属角色组
	 * @return: List<Map<String,Object>>
	 */ 
	public List<Map<String, Object>> queryDeptTrees(@Param("roleGroup")String roleGroup);

}
