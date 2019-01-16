/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerConcernedHouseService.java   
 * @Package com.bashiju.www.service.user      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 下午4:36:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.service.user;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerConcernedHouseEntity;

/**
 * 个人中心--用户房源关注服务接口
 * @ClassName:ICustomerConcernedHouseService
 * @Description:个人中心--用户房源关注服务接口
 * @author:zuoyuntao
 * @date:2018年7月26日 下午4:36:02
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerConcernedHouseService {
	/**
	 * 取消关注房源
	 * @Title: cancelConcernedHouse
	 * @author: zuoyuntao  
	 * @Description:取消关注房源
	 * @param custId 用户ID
	 * @param sourceId 房源、小区或其他关注对象的ID
	 * @param houseType 房源类型（1：新房、2：二手房、3：租房、4：小区）
	 * void 
	 */
	public void cancelConcernedHouse(String custId,String sourceId,String houseType);
	/**
	 * 获取房源关注信息
	 * @Title: queryAllConcernedHouseList
	 * @author: zuoyuntao  
	 * @Description:获取房源关注信息
	 * @param custId 登陆用户Id
	 * @param page 当前页
	 * @param limit 最大页
	 * @param type 关注类型 （1：新房、2：二手房、3：租房、4：小区）
	 * @return      
	 * WebPage<CustomerConcernedHouseEntity>
	 */
	public WebPage<CustomerConcernedHouseEntity> queryAllConcernedHouseList(String custId,int page
			,int limit,String type);
	/**
	 * 统计我的关注信息
	 * @Title: countMyConcerned
	 * @author: zuoyuntao  
	 * @Description:统计我的关注信息
	 * @param custId
	 * @return      
	 * long 
	 */
	public long countMyConcerned(String custId);
}
