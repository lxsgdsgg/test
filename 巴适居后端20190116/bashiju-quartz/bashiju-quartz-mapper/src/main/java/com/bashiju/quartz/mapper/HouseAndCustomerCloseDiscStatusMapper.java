package com.bashiju.quartz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName:  HouseAndCustomerCloseDiscStatusMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: zhaobin
 * @date:   2018年12月6日 下午12:13:06   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface HouseAndCustomerCloseDiscStatusMapper {

	public List<Map<String,Object>> queryCloseDiscHouseInfo(@Param("companyId")String companyId);
	
	public List<Map<String,Object>> queryCloseDiscCustomerInfo(@Param("companyId")String companyId);
}
