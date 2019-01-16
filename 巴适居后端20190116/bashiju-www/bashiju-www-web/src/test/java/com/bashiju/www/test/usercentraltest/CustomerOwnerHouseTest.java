/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerOwnerHouseTest.java   
 * @Package com.bashiju.www.test.usercentraltest      
 * @author: zuoyuntao     
 * @date:   2018年8月1日 下午4:21:08   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test.usercentraltest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerOwnerHouseEntity;
import com.bashiju.www.service.user.ICustomerOwnerHouseService;
import com.bashiju.www.test.BaseServiceTest;

/**
 * 我的房子测试类
 * @ClassName:CustomerOwnerHouseTest
 * @Description:我的房子测试类
 * @author:zuoyuntao
 * @date:2018年8月1日 下午4:21:08
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerOwnerHouseTest extends BaseServiceTest {
	@Autowired
	private ICustomerOwnerHouseService mICustomerOwnerHouseService;
//	@Test
	public void bindingSourcesByBindCode() {
		try {
			mICustomerOwnerHouseService.bindingSourcesByBindCode("1", "870005","0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试获取手机验证码 
	 * @Title: getMobileVerifyCode
	 * @author: zuoyuntao  
	 * @Description:测试获取手机验证码         
	 * void
	 */
	@Test
	public void getMobileVerifyCode() {
		mICustomerOwnerHouseService.sendHouseVerifyCode("14787468304");
	}
	@Test
	public void queryCustOwnerHouseList() {
		WebPage<CustomerOwnerHouseEntity>  page = 
				mICustomerOwnerHouseService.queryCustOwnerHouseList("1","2",0,10);
		System.out.println(page);
	}

}
