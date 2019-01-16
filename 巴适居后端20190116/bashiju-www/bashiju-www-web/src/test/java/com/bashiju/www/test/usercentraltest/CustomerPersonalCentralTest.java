/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonalCentralTest.java   
 * @Package com.bashiju.www.test.usercentraltest      
 * @author: zuoyuntao     
 * @date:   2018年8月3日 上午11:21:45   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test.usercentraltest;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.service.user.ICustomerPersonalCentralService;
import com.bashiju.www.test.BaseServiceTest;

/**
 * 个人中心测试类
 * @ClassName:CustomerPersonalCentralTest
 * @Description:个人中心测试类
 * @author:zuoyuntao
 * @date:2018年8月3日 上午11:21:45
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerPersonalCentralTest extends BaseServiceTest {
	@Autowired
	private ICustomerPersonalCentralService mICustomerPersonalCentralService;
	/**
	 * 获取【个人信息】 
	 * @Title: queryPersonalInfo
	 * @author: zuoyuntao  
	 * @Description:获取【个人信息】
	 * @return      
	 * CustCustomerEntity
	 */
	@Test
	public void queryPersonalInfo() {
		mICustomerPersonalCentralService.queryPersonalInfo("1");
	}
	/**
	 * 查询客户个人详细信息 
	 * @Title: queryPersonalDetailInfo
	 * @author: zuoyuntao  
	 * @Description:查询客户个人详细信息   
	 * @return      
	 * CustCustomerEntity
	 */
	@Test
	public void queryPersonalDetailInfo() {
		mICustomerPersonalCentralService.queryPersonalDetailInfo("1");
	}
	/**
	 * 获取客户房源过户进度详细
	 * @Title: queryTransitScheduleHouseDetail
	 * @author: zuoyuntao  
	 * @Description:获取客户房源过户进度详细
	 * @return      
	 * List<CustCustomerEntity>
	 */
	@Test
	public void queryTransitScheduleHouseDetail(){
		List<CustomerHouseTransitScheduleEntity> list = 
				mICustomerPersonalCentralService.queryTransitScheduleHouseDetail("1");
		System.out.println("--------------->>>" + list);
	}
	/**
	 * 发送手机验证码
	 * @Title: sendMobileVerfyCode
	 * @author: zuoyuntao  
	 * @Description:获取验证码   
	 * @param mobile 手机号码  
	 * void
	 */
	@Test
	public void sendMobileVerfyCode() {
		mICustomerPersonalCentralService.sendMobileVerfyCode("14787468304");
	}
	/**
	 * 客户修改密码
	 * @Title: modifyCustomerPassword
	 * @author: zuoyuntao  
	 * @Description:客户修改密码
	 * @param custId 客户ID
	 * @param finalPassword 客户最终密码      
	 * void 
	 */
	@Test
	public void modifyCustomerPassword() {
		mICustomerPersonalCentralService.modifyCustomerPassword("1", "123456");
	}
	/**
	 * 查询客户收藏的经纪人
	 * @Title: queryMyselfAgentList
	 * @author: zuoyuntao  
	 * @Description:查询客户收藏的经纪人
	 * @param custId 客户ID
	 * @param agentType 经纪人类型（0：我聊过的经纪人 1：带看过的经纪人）   
	 * void JSON 格式为：
	 */
	@Test
	public void queryMyselfAgentList(){
		mICustomerPersonalCentralService.queryMyselfAgentList("1", "0");
	}
	/**
	 * 查询我的预约
	 * @Title: queryMyselfApointmentList
	 * @author: zuoyuntao  
	 * @Description:查询我的预约  
	 * @param custId 客户ID
	 * @return      
	 */
	@Test
	public void queryMyselfAppointmentList(){
		List<?> list = mICustomerPersonalCentralService.queryMyselfAppointmentList("1");
		System.out.println(list);
	}
	/**
	 * 取消预约
	 * @Title: concelMyselfAppointment
	 * @author: zuoyuntao  
	 * @Description:取消预约
	 * @param id 预约ID
	 * void 
	 */
	@Test
	public void concelMyselfAppointment() {
		mICustomerPersonalCentralService.concelMyselfAppointment("1");
	}
	/**
	 * 更新个人信息 
	 * @Title: updateCustCustomerInfo
	 * @author: zuoyuntao  
	 * @Description:更新个人信息 
	 * void
	 */
	@Test
	public void updateCustCustomerInfo() {
		CustCustomerEntity custEntity = new CustCustomerEntity ();
		custEntity.setId("1");
		custEntity.setName("黄大仙");
		custEntity.setSex("0");
		custEntity.setMobile("1366666666");
		custEntity.setBirthday(new Date());
		custEntity.setProviceCode("530000");
		custEntity.setCityCode("530100");
		custEntity.setAreaCode("530101");
		custEntity.setAddress("春融置信银河广场AAAA");
		mICustomerPersonalCentralService.updateCustCustomerInfo(custEntity);
	}
}
