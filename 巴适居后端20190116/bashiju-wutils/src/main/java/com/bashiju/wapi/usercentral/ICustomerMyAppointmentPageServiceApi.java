/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICustomerMyServiceApi.java   
 * @Package com.bashiju.wapi.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月15日 下午1:49:45   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.wapi.usercentral;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerAppointmentEntity;

/**
 * 个人中心----我的预约页面服务接口
 * @ClassName:ICustomerMyServiceApi
 * @Description:个人中心----我的预约页面服务接口
 * @author:zuoyuntao
 * @date:2018年8月15日 下午1:49:45
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface ICustomerMyAppointmentPageServiceApi {

	/**
	 * 查询我的预约数据 
	 * @Title: queryMyAppointmentList
	 * @author: zuoyuntao  
	 * @Description:查询我的预约数据
	 * @param key 用户登陆key
	 * @param page 当前页
	 * @param limit 每页最大条数
	 * @return      
	 * List<CustomerOwnerAppointmentEntity>
	 */
	public WebPage<CustomerOwnerAppointmentEntity> queryMyAppointmentList(String key,int page,int limit);
	/**
	 * 取消预约
	 * @Title: cancelAppointment
	 * @author: zuoyuntao  
	 * @Description:取消预约   
	 * @param id 预约ID      
	 * void 
	 */
	public void cancelAppointment(String id);
	/**
	 * 删除我的预约
	 * @Title: deleteAppointment
	 * @author: zuoyuntao  
	 * @Description:删除我的预约
	 * @param id 预约ID
	 * @return      
	 * boolean 成功true/失败false
	 */
	public boolean deleteAppointment(String id);
}
