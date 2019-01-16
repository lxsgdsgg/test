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
 * @ClassName:  UserIncomeMapper   
 * @Description:个人实收排行
 * @author: liwen
 * @date:   2018年11月29日 上午11:15:59   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface UserIncomeMapper {
	
	/**
	 * 
	 * @Description: 个人实收排行
	 * @param months 月份
	 * @param userId 用户ID
	 * @param companyId 所属公司
	 * @return: List<Map<String,Object>>  
	 */
	public List<Map<String,Object>> queryUserIncome(@Param("months")String months,@Param("userId")String userId,@Param("companyId")String companyId);
	
	/**
	 * 
	 *   
	 * @Description: 上月个人实收查询
	 * @param: @param months
	 * @param: @param userId
	 * @param: @param companyId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> queryPerMonthUserIncome(@Param("months")String months,@Param("userId")String userId,@Param("companyId")String companyId);

	
	
}
