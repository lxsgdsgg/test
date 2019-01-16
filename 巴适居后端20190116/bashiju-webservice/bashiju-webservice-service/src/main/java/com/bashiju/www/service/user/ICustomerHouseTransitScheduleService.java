/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerHouseTransitScheduleService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:11:38   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import java.util.List;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerTransactionScheduleEntity;

/**
 * 用户房屋过户进度服务接口
 * @ClassName:ICustomerHouseTransitScheduleService
 * @Description:用户房屋过户进度服务接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:11:38
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerHouseTransitScheduleService {
	/**
	 * 读取过户进度数据
	 * @Title: queryHouseTransitScheculeDataList
	 * @author: zuoyuntao  
	 * @Description:读取过户进度数据
	 * @param custId 登陆用户ID
	 * @param page  每页显示最少条数
	 * @param limit 每页显示最大条数
	 * @return      
	 * List<CustomerHouseTransitScheduleEntity> 
	 */
	public WebPage<CustomerHouseTransitScheduleEntity> 
			queryHouseTransitScheculeDataList(String custId,int page,int limit);
	
	/**
	 * 查询房源的过户流程信息
	 * @Title: queryTransitScheculeDataList
	 * @author: zuoyuntao  
	 * @Description:查询房源的过户流程信息  
	 * @param dealId 成交编号
	 * @return      
	 * List<CustomerTransactionScheduleEntity>
	 */
	public List<CustomerTransactionScheduleEntity> queryTransitScheculeDataList(String dealId);
}
