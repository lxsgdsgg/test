/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  NewHoseTest.java   
 * @Package com.bashiju.www.test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月20日 上午11:07:28   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.bashiju.wapi.INewHouseListPageApi;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.newhouse.NewHouseQueryParam;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseListResult;
import com.bashiju.www.service.newhouse.INewHouseService;

/**   
 * @ClassName:  NewHoseTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpeng
 * @date:   2018年8月20日 上午11:07:28   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class NewHoseTest extends BaseServiceTest {
	@Autowired
	INewHouseService newHouseService;
	@Autowired
	INewHouseListPageApi newHouseListPageApi;
	
	/*@Test
	public void queryNewHouseDynamicByProjectId() {
		List<NewHouseDynamicResult>  map = newHouseService.queryNewHouseDynamicByProjectId("1");
		System.out.println(map);
	}*/
	
//	@Test
//	public void PresaleTest(){
//		newHouseService.queryPresalePermitByProjectId("1");
//	}
//	@Test
//	public void RootTimeTest() {
//		List<RoomTimeResult> roomTime =	newHouseService.queryRoomTimeByPorjectId("1");
//	}
//	@Test
//	public void OpenTimeTest() {
//		List<OpenDateResult> openDate =	newHouseService.queryOpenDateByProjectId("1");
//	}
	/*@Test
	public void  pricetTest() {
		newHouseService.queryNewHousePriceDetailByProjectId("1");
	}*/
	/*@Test
	public void  Test() {
		NewHouseDetailResult data= newHouseService.selectNewHouseDetailInfo(24);
		System.out.println(data.toString());
		assertNotNull(data);
	}*/
	
	@Test
	public void  Test1() {
		newHouseService.selectNewHouseType(24);
	}
	
	/*@Test
	public void  querySameNewHouseList() {
		newHouseService.querySameNewHouseList(10,4);
	}*/
	
	/*@Test
	public void  queryGoodProjects() {
		List<GoodNewHouseListResult> results=newHouseService.queryGoodProjects("530100",10);
		System.out.println(JSON.toJSON(results));
		assertNotNull(results);
	}*/

	/*@Test
	public void  queryHotProjects() {
		List<HotNewHouseListResult> results=newHouseService.queryHotProjects("530100",10);
		System.out.println(JSON.toJSON(results));
		assertNotNull(results);
	}*/
	/*@Test
	public void  queryNewHouseListByName() {
		WebPage<NewHouseListResult> results=newHouseService.queryNewHouseListByName("530100","正大",1,10);
		System.out.println(JSON.toJSON(results));
		assertNotNull(results);
	}*/
	@Test
	public void  queryNewHouseList() {
/*		List<QueryCondition> conditions=newHouseListPageApi.queryConditionsSellHouse("530100");
		QueryCondition condition=null;
		for (QueryCondition queryCondition : conditions) {
			if (queryCondition.getConditionCode().equals("avgPrice")) {
				condition=queryCondition;
			}
		}*/
		NewHouseQueryParam param=new NewHouseQueryParam();
		param.setCityCode("530100");
		//param.setAreaCode("530101");
		List<String> priceCodes=new ArrayList<>();
	/*	priceCodes.add("22");
		priceCodes.add("23");
		priceCodes.add("24");
		priceCodes.add("25");
		priceCodes.add("26");*/
		priceCodes.add("27");
		param.setPriceCodes(priceCodes);
		WebPage<NewHouseListResult> results=newHouseService.queryNewHouseList(param,1,10);
		System.out.println(JSON.toJSON(results));
		assertNotNull(results);
	}
}
