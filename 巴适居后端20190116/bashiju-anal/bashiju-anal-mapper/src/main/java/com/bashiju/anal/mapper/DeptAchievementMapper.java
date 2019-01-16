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
 * @ClassName:  DeptAchievementMapper   
 * @Description:门店业绩排行
 * @author: liwen
 * @date:   2018年11月29日 下午14:35:59   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DeptAchievementMapper {
	
	
	/**
	 * 
	 *   
	 * @Description: 店的业绩统计
	 * @param: @param month 月份
	 * @param: @param companyId 公司ID
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> queryAchievement(@Param("month")String month,@Param("companyId")String companyId);

	
	
}
