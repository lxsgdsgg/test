package com.bashiju.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.wapi.IHomePageServiceApi;
import com.bashiju.wapi.IZiXunPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerConcernedPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerPersonCentralPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerPersonInfoPageServiceApi;
import com.bashiju.wutils.picvalidatecode.RandomValidateCode;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;
@Controller
public class Btest {
	

	
	@Autowired
	IZiXunPageServiceApi izx;
	@Autowired
	RandomValidateCode randomValidateCode;
	@Autowired
	private ICustomerPersonInfoPageServiceApi mICustomerPersonInfoPageServiceApi;
	@Autowired
	private ICustomerPersonCentralPageServiceApi mICustomerPersonCentralPageServiceApi;
	@Autowired
	private ICustomerConcernedPageServiceApi mICustomerConcernedPageServiceApi;
	
	@Autowired 
    private ICustomerPersonCentralPageServiceApi customerPersonCentralPageServiceApi;
	
	@Autowired
	private IHomePageServiceApi  homePageServiceApi;
	
	@RequestMapping(value="ss")
	public void test() {
		List<ArticleTypeResult> ss=izx.queryZiXunType("530100");
		System.out.println(ss);
	}
	//生成验证码图片
	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void verifyCodeImage(HttpServletRequest request, HttpServletResponse response){
        randomValidateCode.creatRandcodeImage(request, response);//输出图片方法
    }
	//获取验证码内容
	@RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String code=randomValidateCode.getCode(request, response);//输出图片方法
        response.getWriter().println(code);
    }
	@RequestMapping(value = "/sendMobileVerfyCode")
	@ResponseBody
	public void sendMobileVerfyCode() {
		mICustomerPersonInfoPageServiceApi.queryPhoneVerifyCode("14787468304");
		System.out.println("=================>");
//		mICustomerPersonInfoPageServiceApi.queryCustomerPersonalInfo("b93b0446c98bae97ccf9e4f9ea471494");
	}
	@RequestMapping(value = "/modifyUser")
	@ResponseBody
	public void modifyUser(){
		mICustomerPersonInfoPageServiceApi.modifyUserMobile("13987144673","375090","14787468304");
		System.out.println("=======$$$$$$$$$$======");
	}
	
	@RequestMapping(value = "/queryAllPrivinceDataList")
	@ResponseBody
	public Object queryAllPrivinceDataList(){
		return JSONObject.toJSON(mICustomerPersonCentralPageServiceApi.queryAllPrivinceDataList());
	}
	@RequestMapping(value = "/queryCountryAreaListByCityCode")
	@ResponseBody
	public Object queryCountryAreaListByCityCode(){
		return JSONObject.toJSON( mICustomerPersonCentralPageServiceApi.queryCountryAreaListByCityCode("530100"));
	}
	@RequestMapping(value = "/queryCityAreaListByPrivinceCode")
	@ResponseBody
	public Object queryCityAreaListByPrivinceCode(){
		return JSONObject.toJSON( mICustomerPersonCentralPageServiceApi.queryCityAreaListByPrivinceCode("530000"));
	}
//	//查询我的关注
//	@RequestMapping(value = "/queryMyConcernedListPage")
//	@ResponseBody
//	public Object queryMyConcernedListPage(){
//		WebPage<CustomerConcernedHouseEntity> retList = 
//				mICustomerConcernedPageServiceApi.queryMyConcernedListPage("f99af974855f746c5a102d9866176ac2", 1, 10);
//		System.out.println(retList);
//		return retList;
//	}
	//查询我的关注
	@RequestMapping(value = "/queryFootPrintByCustId")
	@ResponseBody
	public Object queryFootPrintByCustId(){
		WebPage<FootPrintToQueryEntity> retList = 
				customerPersonCentralPageServiceApi.queryFootPrintByCustId("93f8c599391afa61d907c52c76f91037", "2", 1, 10);
		System.out.println(retList);
		return retList;
	}
	
}
