/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataMapper.java   
 * @Package com.bashiju.manage.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月11日 上午10:28:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**   
 * @ClassName:  ErHousingMapper   
 * @Description:测试服务
 * @author: zb
 * @date:   2018年4月11日 上午10:28:52   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ErHousingMapper {

	/**
	 * 
		 * 根据房源id查询经纪人列表
		 * @Description: 根据房源id查询经纪人列表
		 * @param houseId 房源id
		 * @return 经纪人列表
		 * LinkedList<Map<String,Object>>
	 */
	public LinkedList<Map<String,Object>> queryRelateagentInfo(Long houseId);
	
	/**
	 * 
		 * 判断电话是否在黑名单
		 * @Description: 判断电话是否在黑名单
		 * @param phone 电话
		 * @return  数量
		 * Integer
	 */
	public Integer queryCountPhoneBlack (@Param("phone")String phone);
}
