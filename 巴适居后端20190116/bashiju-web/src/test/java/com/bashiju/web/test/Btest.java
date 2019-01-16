package com.bashiju.web.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.wapi.IZiXunPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerPersonCentralPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;

public class Btest extends BaseServiceTest {
	@Autowired
	IZiXunPageServiceApi izx;
	
    @Autowired 
    private ICustomerPersonCentralPageServiceApi customerPersonCentralPageServiceApi;
	@Autowired
	private ICustomerPersonInfoPageServiceApi mICustomerPersonInfoPageServiceApi;
	
//	@Test
	public void test() {
		List<ArticleTypeResult> ss=izx.queryZiXunType("530100");
		System.out.println(ss);
	}
	
//	@Test
	public void sendMobileVerfyCode() {
		mICustomerPersonInfoPageServiceApi.queryPhoneVerifyCode("14787468304");
		System.out.println("=================>");
//		mICustomerPersonInfoPageServiceApi.queryCustomerPersonalInfo("b93b0446c98bae97ccf9e4f9ea471494");
	}
	@Test
	public void footPrintTest() {
		WebPage<FootPrintToQueryEntity>  resutlt = customerPersonCentralPageServiceApi.queryFootPrintByCustId("b12983df55e967a40b7d840ddeb9b79a", "1",1, 1);
		System.out.println(resutlt);
	}

}
