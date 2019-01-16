package com.bashiju.api;

import java.util.List;
import java.util.Map;

/**
 * 
 *   房源公共服务
 * @ClassName:  ErHousingServiceApi   
 * @Description:  房源公共服务
 * @author: wangkaifa
 * @date:   2018年6月23日 上午9:57:37       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ErHousingServiceApi {
	/**
	 * 
		 * 根据房源id查询经纪人列表
		 * @Description: 根据房源id查询经纪人列表 
		 * @param houseId 房源id
		 * @return 经纪人列表 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryAgentsByHouseId(Long houseId);
	/**
	 * 
		 * 判断电话是否在黑名单
		 * @Description: 判断电话是否在黑名单 
		 * @param phone 电话
		 * @return true:在黑名单，false:不在黑名单
		 * boolean
	 */
	public boolean queryCountPhoneBlack (String phone);
}
