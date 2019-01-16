/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseRequestTest.java   
 * @Package com.bashiju.www.test.usercentraltest      
 * @author: zuoyuntao     
 * @date:   2018年8月6日 上午9:28:06   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test.usercentraltest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.service.user.ICustomerHouseRequestService;
import com.bashiju.www.test.BaseServiceTest;
import com.github.pagehelper.Page;

/**
 * 我的需求测试类
 * @ClassName:CustomerHouseRequestTest
 * @Description:我的需求测试类
 * @author:zuoyuntao
 * @date:2018年8月6日 上午9:28:06
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerHouseRequestTest extends BaseServiceTest{
	@Autowired
	private ICustomerHouseRequestService mICustomerHouseRequestService;
	@Test
	public void test() {
//		mICustomerHouseRequestService.queryHouseVerifyCode("14787468304");
//		mICustomerHouseRequestService.bindingCustomerByBindCode("1","213066");
		Page<CustomerHouseRequestEntity> page = 
				mICustomerHouseRequestService.queryMyHouseRequestList("1", "4", 0, 10);
		System.out.println(page);
	}

}
