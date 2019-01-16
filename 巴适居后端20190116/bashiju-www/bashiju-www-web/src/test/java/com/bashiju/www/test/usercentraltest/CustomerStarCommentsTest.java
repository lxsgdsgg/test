/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerStarCommentsTest.java   
 * @Package com.bashiju.www.test.usercentraltest      
 * @author: zuoyuntao     
 * @date:   2018年8月3日 上午11:31:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test.usercentraltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.usercentral.CustomerAgentStarCommentsEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingHotLabelEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingServiceComment;
import com.bashiju.www.service.user.ICustomerStarCommentsService;
import com.bashiju.www.test.BaseServiceTest;

/**
 * 评价测试类
 * @ClassName:CustomerStarCommentsTest
 * @Description:评价测试类
 * @author:zuoyuntao
 * @date:2018年8月3日 上午11:31:52
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class CustomerStarCommentsTest extends BaseServiceTest {
	@Autowired
	private ICustomerStarCommentsService mICustomerStarCommentsService;

	/**
	 * 获取我的评价
	 * @Title: queryMyComments
	 * @author: zuoyuntao  
	 * @Description:获取我的评价
	 * void JSON 格式为：
	 */
//	@Test
	public void queryCustomerComments(){
		WebPage<CustomerAgentStarCommentsEntity> page =
				mICustomerStarCommentsService.queryCustomerComments("1", 0, 10);
//		for(CustomerAgentStarCommentsEntity obj : page) {
			System.out.println(page);
//		}
	}
	/**
	 * 保存客户评价
	 * @Title: saveCustomerComments
	 * @author: zuoyuntao  
	 * @Description:保存客户评价
	 * void HttpServletRequest request,HttpServletResponse response
	 */
	@Test
	public void saveCustomerComments() {
		CustomerAgentStarCommentsEntity entity = new CustomerAgentStarCommentsEntity();
		entity.setContent("这个经纪人还可以");
		entity.setCustId(1);
		entity.setCustName("左云涛");
		entity.setStar(5);
		entity.setUserId(1);
		buildData(entity);
//		mICustomerStarCommentsService.getImageVerifyCode(request, response);
		mICustomerStarCommentsService.saveCustomerComments(entity,"VWDC",null,null);
	}

	private void buildData(CustomerAgentStarCommentsEntity entity) {
		List<CustomerStarRatingHotLabelEntity> hotList = 
				new ArrayList<CustomerStarRatingHotLabelEntity>();
		CustomerStarRatingHotLabelEntity item1 = new CustomerStarRatingHotLabelEntity();
		CustomerStarRatingHotLabelEntity item2 = new CustomerStarRatingHotLabelEntity();
		CustomerStarRatingHotLabelEntity item3 = new CustomerStarRatingHotLabelEntity();
		item1.setLabelId(1);
		item1.setLabeld("房东信赖");
		item2.setLabelId(2);
		item2.setLabeld("客户热评");
		item3.setLabelId(3);
		item3.setLabeld("销售达人");
		hotList.add(item1);
		hotList.add(item2);
		hotList.add(item3);
		
		List<CustomerStarRatingServiceComment> serviceRatingLabelList 
			= new ArrayList<CustomerStarRatingServiceComment>();
		CustomerStarRatingServiceComment aa = new CustomerStarRatingServiceComment();
		CustomerStarRatingServiceComment bb = new CustomerStarRatingServiceComment();
		CustomerStarRatingServiceComment cc = new CustomerStarRatingServiceComment();
		aa.setLabelId(1);
		aa.setLabeld("服务好");
		bb.setLabelId(2);
		bb.setLabeld("态度好");
		cc.setLabelId(3);
		cc.setLabeld("为人热情");
		serviceRatingLabelList.add(aa);
		serviceRatingLabelList.add(bb);
		serviceRatingLabelList.add(cc);
		
		entity.setHotRatingLabelList(hotList);
		entity.setServiceRatingLabelList(serviceRatingLabelList);
	}
}
