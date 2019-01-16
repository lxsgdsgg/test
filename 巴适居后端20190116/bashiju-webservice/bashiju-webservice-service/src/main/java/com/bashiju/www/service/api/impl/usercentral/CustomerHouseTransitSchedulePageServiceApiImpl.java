/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseTransitSchedulePageServiceApiImpl.java   
 * @Package com.bashiju.www.service.api.impl.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午3:02:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.api.impl.usercentral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.wapi.usercentral.ICustomerHouseTransitSchedulePageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerTransactionScheduleEntity;
import com.bashiju.www.service.user.ICustomerHouseTransitScheduleService;

/**
 * 个人中心----房源过户进度
 * @ClassName:CustomerHouseTransitSchedulePageServiceApiImpl
 * @Description:个人中心----房源过户进度
 * @author:zuoyuntao
 * @date:2018年8月15日 下午3:02:50
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerHouseTransitSchedulePageServiceApiImpl
		extends CustomerPersonalCentralBaseServiceApi implements ICustomerHouseTransitSchedulePageServiceApi {
	/**
	 * 房源过户进度接口
	 */
	@Autowired
	private ICustomerHouseTransitScheduleService mICustomerHouseTransitScheduleService;
	/**
	 * <p>Title: queryHouseTransitScheculeDataList</p>   
	 * <p>Description: 查询房源过户进度</p>   
	 * @param key 客户登陆标记
	 * @param page 当前页
	 * @param limit 最大页
	 * @return   
	 * @see com.bashiju.wapi.usercentral.ICustomerHouseTransitSchedulePageServiceApi#queryHouseTransitScheculeDataList(java.lang.String, int, int)
	 */
	@Override
	public WebPage<CustomerHouseTransitScheduleEntity> queryHouseTransitScheculeDataList(String key, int page,
			int limit) {
		String custId = this.getUserIdByRedisKey(key);
		return mICustomerHouseTransitScheduleService.queryHouseTransitScheculeDataList(custId, page, limit);
	}
	
	/**
	 * 查询房源的过户流程信息
	 * @Title: queryTransitScheculeDataList
	 * @author: zuoyuntao  
	 * @Description:查询房源的过户流程信息  
	 * @param dealId 成交编号
	 * @return      
	 * List<CustomerTransactionScheduleEntity>
	 */
	@Override
	public List<CustomerTransactionScheduleEntity> queryTransitScheculeDataList(String dealId){
		return mICustomerHouseTransitScheduleService.queryTransitScheculeDataList(dealId);
	}
}
