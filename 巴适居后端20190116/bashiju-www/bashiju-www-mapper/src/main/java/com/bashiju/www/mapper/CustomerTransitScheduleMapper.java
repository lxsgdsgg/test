/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerTransitScheduleMapper.java   
 * @Package com.bashiju.www.mapper      
 * @author: zuoyuntao     
 * @date:   2018年7月27日 下午4:24:33   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.mapper;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.github.pagehelper.Page;

/**
 * 房屋过户进度持久层
 * @ClassName:CustomerTransitScheduleMapper
 * @Description:房屋过户进度持久层
 * @author:zuoyuntao
 * @date:2018年7月27日 下午4:24:33
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface CustomerTransitScheduleMapper {
	/**
	 * 根据用户ID读取房屋过户进度
	 * @Title: queryHouseTransitScheculeDataList
	 * @author: zuoyuntao  
	 * @Description:根据用户ID读取房屋过户进度
	 * @param custId
	 * @return      
	 * WebPage<CustomerHouseTransitScheduleEntity> JSON 格式为：
	 */
	public Page<CustomerHouseTransitScheduleEntity> queryHouseTransitScheculeDataList(
			@Param("custId") String custId);
}
