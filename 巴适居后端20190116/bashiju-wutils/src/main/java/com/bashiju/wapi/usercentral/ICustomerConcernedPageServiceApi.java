/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerConcernedPageServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 上午11:43:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerConcernedHouseEntity;

/**
 * 客户关注服务接口
 * @ClassName:CustomerConcernedPageServiceApi
 * @Description:客户关注服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 上午11:43:28
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerConcernedPageServiceApi {
	/**
	 * 获取房源关注信息
	 * @Title: queryMyConcernedListPage
	 * @author: zuoyuntao  
	 * @Description:获取房源关注信息
	 * @param key 用户登陆的key
	 * @param page 当前页
	 * @param limit 最大页
	 * @param type 关注类型 （1：新房、2：二手房、3：租房、4：小区）
	 * @return      
	 * WebPage<CustomerConcernedHouseEntity> 
	 */
	public WebPage<CustomerConcernedHouseEntity> queryMyConcernedListPage(String key,int page
			,int limit,String type);
	/**
	 * 取消关注房源
	 * @Title: cancelConcernedHouse
	 * @author: zuoyuntao  
	 * @Description:取消关注房源
	 * @param key 用户登陆标记
	 * @param sourceId 房源、小区或其他关注对象的ID
	 * @param houseType 房源类型（1：新房、2：二手房、3：租房、4：小区） 
	 * void 
	 */
	public void cancelConcernedHouse(String key,String sourceId,String houseType);
}
