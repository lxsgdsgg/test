package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName:  ResHouseReturnCycle   
 * @Description:房源回访周期设置
 * @author: wangpeng
 * @date:   2018年5月28日 上午9:50:43   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ResHouseReturnCycleMapper {
	/**
	 * @Title: queryResHouseReturnCycleData   
	 * @Description: 查询房源回访周期设置
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>>queryResHouseReturnCycleData(@Param("companyId")String companyId);
	
}
