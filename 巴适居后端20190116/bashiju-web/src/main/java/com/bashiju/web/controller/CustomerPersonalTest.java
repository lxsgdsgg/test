/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonalTest.java   
 * @Package com.bashiju.web.controller      
 * @author: zuoyuntao     
 * @date:   2018年9月25日 下午3:24:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.wapi.ICustomerRecruitServiceApi;
import com.bashiju.wapi.usercentral.ICustomerHouseTransitSchedulePageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerMyRequestPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerSeeHouseRecordPageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.recruit.CustomerRecruitQueryParam;
import com.bashiju.www.pojo.service.out.recruit.CustReleaseRecruitEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseRequestEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerSeeHouseRecordEntity;

/**
 * 个人中心测试类
 * @ClassName:CustomerPersonalTest
 * @Description:个人中心测试类
 * @author:zuoyuntao
 * @date:2018年9月25日 下午3:24:46
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping("userCentral")
public class CustomerPersonalTest {
	@Autowired
	private ICustomerMyRequestPageServiceApi mICustomerMyRequestPageServiceApi;
	@Autowired
	private ICustomerSeeHouseRecordPageServiceApi mICustomerSeeHouseRecordPageServiceApi;
	@Autowired
	private ICustomerHouseTransitSchedulePageServiceApi mICustomerHouseTransitSchedulePageServiceApi;
	/**
	 * 招聘管理服务接口
	 */
	@Autowired
	private ICustomerRecruitServiceApi mICustomerRecruitService;
	
	/**
	 * 查询我的需求列表 
	 * @Title: test
	 * @author: zuoyuntao  
	 * @Description:查询我的需求列表   
	 * void
	 */
	@RequestMapping(value="/queryMyRequestPageList")
	@ResponseBody
	public void queryMyRequestPageList() {
		WebPage<CustomerHouseRequestEntity>  pageOn = 
				mICustomerMyRequestPageServiceApi.queryMyRequestPageList(
						"763340fc630cdcb11c79734b9b20b8b3", "0", 0, 10);
		WebPage<CustomerSeeHouseRecordEntity>  page = 
				mICustomerSeeHouseRecordPageServiceApi.querySeeHouseRecordPageList("763340fc630cdcb11c79734b9b20b8b3", 0, 10);
//		System.out.println(pageOn);
		System.out.println(page);
	}
	/**
	 * 查询过户进度
	 * @Title: queryHouseTransitScheculeDataList
	 * @author: zuoyuntao  
	 * @Description:查询过户进度        
	 * void
	 */
	@RequestMapping(value="/queryHouseTransitScheculeDataList")
	@ResponseBody
	public void queryHouseTransitScheculeDataList() {
		WebPage<CustomerHouseTransitScheduleEntity>  obj =
				mICustomerHouseTransitSchedulePageServiceApi.queryHouseTransitScheculeDataList("", 0, 10);
		System.out.println(obj);
	}
	@RequestMapping(value="/queryRecruitPositionDataWithPage")
	@ResponseBody
	public WebPage<CustReleaseRecruitEntity> queryRecruitPositionDataWithPage() {
		//查询岗位
		CustomerRecruitQueryParam paramEntity = new CustomerRecruitQueryParam();
		paramEntity.setName("经理");
//		paramEntity.setWorkCityCode("530100");
		//查询岗位列表
		WebPage<CustReleaseRecruitEntity>  page = 
				mICustomerRecruitService.queryRecruitPositionDataWithPage(paramEntity, 1, 10);
		System.out.println(page);
		return page;
	}
}
