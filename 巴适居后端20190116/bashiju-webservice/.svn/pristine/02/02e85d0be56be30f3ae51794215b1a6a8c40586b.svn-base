/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerTest.java   
 * @Package com.bashiju.www.test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月6日 下午3:38:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.bashiju.api.RedisGetIdServiceApi;
import com.bashiju.wapi.IHomePageServiceApi;
import com.bashiju.wapi.IHouseSellInfoPageServiceApi;
import com.bashiju.wapi.usercentral.ICustomerPersonCentralPageServiceApi;
import com.bashiju.wutils.redis.ICacheService;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToQueryEntity;
import com.bashiju.www.pojo.service.FootPrint.FootPrintToSaveEntity;
import com.bashiju.www.pojo.service.out.comm.NavigationBar;
import com.bashiju.www.pojo.service.reservation.ReservationParam;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.pojo.service.user.CustCustomerInsertBingdingParam;
import com.bashiju.www.pojo.service.user.CustCustomerParam;
import com.bashiju.www.pojo.service.user.CustCustomerUpdateBingdingParam;
import com.bashiju.www.service.FootPrint.FootPrintService;
import com.bashiju.www.service.agent.AgentService;
import com.bashiju.www.service.navigationbar.NavigationBarService;
import com.bashiju.www.service.user.CustCustomerService;
import com.bashiju.www.service.user.ICustomerPersonalCentralService; 

public class CustomerTest extends BaseServiceTest {
	
	
	
    @Autowired	
    private CustCustomerService custCustomerService;
    
    @Autowired
    private  NavigationBarService navigationBarService;
    @Autowired
    private FootPrintService footPrintService;
    @Autowired
    private ICacheService cacheService;
    @Autowired
    private AgentService agentService;
    @Autowired
	private RedisGetIdServiceApi redisGetIdServiceApi;
    @Autowired
    private ICustomerPersonalCentralService customerPersonalCentralService;
    @Autowired 
    private IHouseSellInfoPageServiceApi houseSellInfoPageServiceApi;
    
    @Autowired
    private IHomePageServiceApi iHomePageServiceApi;
    @Autowired 
    private ICustomerPersonCentralPageServiceApi customerPersonCentralPageServiceApi;
	@Autowired(required=false) 
	MockHttpServletRequest request;
	
	@Autowired(required=false) 
	MockHttpServletResponse response;
	
	@Autowired
	IHomePageServiceApi homePageServiceApi;
//	@Test
//	public void checkImg() {
//		String ss = custCustomerService.imageCheck(request, response);
//		System.out.println("**********"+ss);
//	}
	
//	@Test
	public void userLoginTest() {
		String mobile ="18302392883";
//		String password = "123456"; 
//		String user = custCustomerService.customerLogin(mobile, password, "123123");
		String user = cacheService.getCache("d22ddf709dbf75514ecb632eba158c75");
		System.out.println(user);
	}

//	@Test
	public void Test() {
		CustCustomerParam custCustomerParam = new CustCustomerParam();
		custCustomerParam.setMobile("15696703407");
		custCustomerParam.setName("waper97");
		custCustomerParam.setPassword("123456");
		custCustomerParam.setMsgCode("352275");
		custCustomerService.saveCustCustomer(custCustomerParam);
	}
	
//	@Test
	public void msgTest() {
		String mobile ="18302392883";
		String user = custCustomerService.sendMobileMsgVerfyCode(mobile);
//		String user = homePageServiceApi.sendMobileMsgVerfyCode(mobile);
		System.out.println(user);
	}
	
//	@Test
	public void Bind() {
		CustCustomerInsertBingdingParam custCustomerInsertBingdingParam = new CustCustomerInsertBingdingParam();
		custCustomerInsertBingdingParam.setCode("jj");
		custCustomerInsertBingdingParam.setName("林俊杰");
		custCustomerInsertBingdingParam.setNumType("02");
		custCustomerInsertBingdingParam.setProfilePhoto("hhe");
//		custCustomerInsertBingdingParam.setMobile("18302392883");
//		custCustomerInsertBingdingParam.setMsgCode("350286");
//		custCustomerService.bindingThirdparty(custCustomerInsertBingdingParam);
	}
	
//	@Test
	public void updateCust() {
		CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
		custCustomerEntity.setMsgCode("856060");
		custCustomerEntity.setMobile("15696703407");
		custCustomerEntity.setNewPassword("wangpeng");
		custCustomerService.updateCustCustomer(custCustomerEntity);
	}
	
//	@Test
	public void customerMobileLogin() {
		custCustomerService.customerMobileLogin("15696703407", "893188");
	}

//	@Test
	public void reWathc() {
		ReservationParam reservationParam = new ReservationParam();
		reservationParam.setCustId("11");
		reservationParam.setMobile("18302392883");
		reservationParam.setMsg("893280");
		reservationParam.setName("胜多负少");
		reservationParam.setRemark("希望看下101平米以下的新房");
		reservationParam.setReservationDate(new Date());
		reservationParam.setReservationTime("0");
		reservationParam.setShhId("1");
		reservationParam.setType("0");
		reservationParam.setMaintainId("1");
		boolean result = customerPersonalCentralService.saveReservationWatchHouse(reservationParam);
		System.out.println(result);
	}
//	@Test
	public void test9() {
		houseSellInfoPageServiceApi.sendMobileVerfyCode("18302392883");
	}
//	@Test
	public void test10() {
//		customerPersonCentralPageServiceApi.queryFootPrintByCustId("2", "1");
	}
//	@Test
	public void addFoot() {
		FootPrintToSaveEntity footPrintToSaveEntity = new FootPrintToSaveEntity();
		footPrintToSaveEntity.setBrowsingTime(new Date());
		footPrintToSaveEntity.setKey("46c95ece34c06692306099ccf8cc3724");
		footPrintToSaveEntity.setSourceId("36");
		footPrintToSaveEntity.setType("1");
		customerPersonCentralPageServiceApi.saveFootPrint(footPrintToSaveEntity);
	}
//	@Test
	public void footCount() {
		FootPrintToQueryEntity  footPrintToQueryEntity = customerPersonCentralPageServiceApi.queryFootPrintCountByCustId("");
		System.out.println(footPrintToQueryEntity );
	}
		
//	@Test
	public void isEistRe() {
		boolean result = houseSellInfoPageServiceApi.queryIsExistReservationWatchHouse("1","1");
		System.out.println(result);
	}
//	@Test
	public void na() {
		 List<NavigationBar> result = navigationBarService.queryHeadNavigationBar("530100");
		System.out.println(result);
	}
	
//	@Test
	public void log11in() {
		custCustomerService.customerMobileLogin("18302392883", "403276");
	}
	
//	@Test
	public void update() {
		CustCustomerUpdateBingdingParam custCustomerUpdateBingdingParam = new CustCustomerUpdateBingdingParam();
		custCustomerUpdateBingdingParam.setCode("zz");
		custCustomerUpdateBingdingParam.setName("fuck");
		custCustomerUpdateBingdingParam.setProfilePhoto("zz");
		custCustomerUpdateBingdingParam.setCustId("29");
		custCustomerUpdateBingdingParam.setNumType("wx");
		custCustomerService.updateBingding(custCustomerUpdateBingdingParam);
	}
//	@Test
	public void footDelete() {
		boolean result = customerPersonCentralPageServiceApi.deleteFootPrint("158");
		System.out.println(result);
	}
	
//	@Test
	public void login12() {
		custCustomerService.customerLogin("13577000337", "14E1B600B1FD579F47433B88E8D85291");
	}
	
	
//	@Test
	public void queryFoot() {
		footPrintService.queryFootPrintByCustId("a18f9de30933738904234a597313e660", "2", 1, 10);
//		customerPersonCentralPageServiceApi.queryFni ootPrintCountByCustId("3f29c1304b41057eb225aa03b87f2f87");
//
//		customerPersonCentralPageServiceApi.queryFootPrintByCustId("34a95fe6639954cad437873651fd8a83","1", 1, 10);
	}
	
	
	
//	@Test
	public void msgLoin() {
		custCustomerService.customerMobileLogin("18302392883", "538263");
	}
//	@Test
	public void addFootPrint() {
		FootPrintToSaveEntity ft = new FootPrintToSaveEntity();
		ft.setKey("706e8b4651b95ccceb199abec7518ee9");
		ft.setSourceId("9");
		ft.setType("3");
		ft.setBrowsingTime(new Date());
		footPrintService.saveFootPrint(ft);
	}
	
//	@Test
	public void testMsgCode() {
		customerPersonalCentralService.sendMobileVerfyCode("18302392883");
	}	
//	@Test
	public void thirdLogin() {
		CustCustomerInsertBingdingParam param = new CustCustomerInsertBingdingParam();
		param.setCode("oe6Pk1NXOgp8slF_VQMqhAwqe2JY");
		param.setNumType("02");
		String key = iHomePageServiceApi.thirdPartyLogin(param);
		System.out.println(key);
		
	}
	
}
