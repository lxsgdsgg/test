package com.bashiju.www.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.house.HouseMapListParam;
import com.bashiju.www.pojo.service.input.house.HouseMapParam;
import com.bashiju.www.pojo.service.input.house.HouseSameParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.input.house.SellHouseParam;
import com.bashiju.www.pojo.service.out.article.RentSkillLinkResult;
import com.bashiju.www.pojo.service.out.comm.RangeResult;
import com.bashiju.www.pojo.service.out.house.AgentResult;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseInfoResult;
import com.bashiju.www.pojo.service.out.house.HouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapListResult;
import com.bashiju.www.pojo.service.out.house.HouseMapPointResult;
import com.bashiju.www.pojo.service.out.house.RentHouseInfoResult;
import com.bashiju.www.pojo.service.out.house.ShowedRecordResult;
import com.bashiju.www.service.house.IHouseService;

public class HouseServiceTest extends BaseServiceTest{
	@Autowired
	IHouseService service;
	
	/*@Test
	public void queryHouseInfo() {
		HouseInfoResult house= service.querySellHouseInfo(3);
		System.out.println(house.toString());
		assertNotNull(house);
	}
	@Test
	public void queryShowedAgents() {
		List<AgentResult> list=service.queryShowedAgents(1);
		System.out.println(list.get(0).getName());
		assertNotNull(list);
	}
	@Test
	public void queryShowedRecordByHouseId() {
		WebPage<ShowedRecordResult> list=service.queryShowedRecordByHouseId(1,1,20);
		System.out.println(list.getResult().get(0).getLeader());
		assertNotNull(list);
	}
	
	@Test
	public void querySameHouseInfo() {
		HouseSameParam param = new HouseSameParam();
		param.setCommunityId(3);
		param.setRoom(3);
		param.setSellPrice(200);
		param.setTransactionTypeId(1);
		List<HouseListResult> house= service.querySameHouseList(param);
		for(HouseListResult h : house) {
			String title = h.getTitle();
		}
		assertNotNull(house);
	}
	
	@Test
	public void addHouseTipOff() {
		HouseTipOffParam param = new HouseTipOffParam();
		param.setAddTime(new Date());
		param.setContent("111111111111");
		param.setCustId(2);
		param.setHouseId(3);
		param.setTipOffType("哈哈");
		param.setTipOffTypeId(1);
		boolean res = service.addHouseTipOff(param);
		
		assertNotNull(res);
	}*/
	/*@Test
	public void queryHouseByCoummunity() {
		
		WebPage<HouseListResult> res = service.queryHouseByCoummunity("置信", 1, 0, 10);
		
		assertNotNull(res);
	}
	
	@Test
	public void querySellHouseInfo() {
		HouseInfoResult result=service.querySellHouseInfo(2);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void queryRentHouseInfo() {
		RentHouseInfoResult result=service.queryRentHouseInfo(2);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	
	@Test
	public void queryRentSkills() {
		List<RentSkillLinkResult> result=service.queryRentSkills("530100",10);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellHouseListByArea() {
		SellHouseParam param=new SellHouseParam();
		param.setCityCode("530100");
		//param.setMaxPrice(200);
		//param.setMaxSpace(250);
		RangeResult price1=new RangeResult();
		price1.setMax(50);
		price1.setMin(0);
		RangeResult price2=new RangeResult();
		price2.setMax(200);
		price2.setMin(150);
		RangeResult space1=new RangeResult();
		space1.setMax(250);
		space1.setMin(30);
		RangeResult space2=new RangeResult();
		space2.setMax(30);
		space2.setMin(0);
		List<RangeResult> prices=new ArrayList<>();
		prices.add(price1);
		prices.add(price2);
		List<RangeResult> spaces=new ArrayList<>();
		spaces.add(space1);
		spaces.add(space2);
		param.setSpaces(spaces);
		param.setPrices(prices);
		param.setSort(2);
		param.setAsc(1);
		List<Integer> buildYears=new ArrayList<>();
		buildYears.add(5);
		buildYears.add(21);
		param.setBuildYears(buildYears);
		WebPage<HouseListResult> page=service.querySellHouseListByArea(param, 1, 10);
		System.out.println(page.toString());
		assertNotNull(page);
	}
	
	@Test
	public void querySellHouseListByMetro() {
		SellHouseParam param=new SellHouseParam();
		param.setCityCode("530100");
		param.setMetroName("1号线");
		//param.setMaxSpace(250);
		RangeResult price1=new RangeResult();
		price1.setMax(50);
		price1.setMin(0);
		RangeResult price2=new RangeResult();
		price2.setMax(200);
		price2.setMin(150);
		RangeResult space1=new RangeResult();
		space1.setMax(250);
		space1.setMin(30);
		RangeResult space2=new RangeResult();
		space2.setMax(30);
		space2.setMin(0);
		List<RangeResult> prices=new ArrayList<>();
		prices.add(price1);
		prices.add(price2);
		List<RangeResult> spaces=new ArrayList<>();
		spaces.add(space1);
		spaces.add(space2);
		param.setSpaces(spaces);
		param.setPrices(prices);
		param.setSort(2);
		param.setAsc(1);
		List<Integer> buildYears=new ArrayList<>();
		buildYears.add(5);
		buildYears.add(21);
		param.setBuildYears(buildYears);
		WebPage<HouseListResult> page=service.querySellHouseListByArea(param, 1, 10);
		System.out.println(page.toString());
		assertNotNull(page);
	}
	@Test
	public void querySellMapHouseArea() {
		HouseMapParam param=new HouseMapParam();
		param.setCityCode("530100");
		//param.setCommunityName("星");
		List<HouseMapPointResult> result=service.querySellMapHouseArea(param);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellMapHouseRegion() {
		HouseMapParam param=new HouseMapParam();
		param.setCityCode("530100");
		//param.setCommunityName("星");
		List<HouseMapPointResult> result=service.querySellMapHouseRegion(param);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellMapHouseCommunity() {
		HouseMapParam param=new HouseMapParam();
		param.setCityCode("530100");
		//param.setCommunityName("星");
		List<HouseMapPointResult> result=service.querySellMapHouseCommunity(param);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellHouseMapList() {
		HouseMapListParam param=new HouseMapListParam();
		param.setCityCode("530100");
		param.setCommunityName("正大");
		WebPage<HouseMapListResult> result=service.querySellHouseMapList(param,1,10);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellMapHouseMetro() {
		HouseMapParam param=new HouseMapParam();
		param.setCityCode("530100");
		param.setMetroName("1号线");
		List<Integer> rooms=new ArrayList<>();
		rooms.add(1);
		//param.setRooms(rooms);
		List<HouseMapPointResult> result=service.querySellMapHouseMetro(param);
		System.out.println(result.toString());
		assertNotNull(result);
	}*/
	@Test
	public void querySellMapHouseCommunityMetro() {
		HouseMapParam param=new HouseMapParam();
		param.setCityCode("530100");
		param.setMetroName("1号线");
		//param.setStationId(56);
		//param.setStationName("火车站");
		List<Integer> rooms=new ArrayList<>();
		rooms.add(1);
		//param.setRooms(rooms);
		List<HouseMapPointResult> result=service.querySellMapHouseCommunityMetro(param);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	/*@Test
	public void querySellHouseMapMetroList() {
		HouseMapListParam param=new HouseMapListParam();
		param.setCityCode("530100");
		param.setMetroName("1号线");
		param.setStationName("火车站");
		WebPage<HouseMapListResult> result=service.querySellHouseMapMetroList(param,1,10);
		System.out.println(result.toString());
		assertNotNull(result);
	}
	@Test
	public void querySellGoodHouse() {
	
		WebPage<GoodHouseListResult> result=service.querySellGoodHouse("530100",1,10);
		System.out.println(result.toString());
		assertNotNull(result);
	}*/
}
