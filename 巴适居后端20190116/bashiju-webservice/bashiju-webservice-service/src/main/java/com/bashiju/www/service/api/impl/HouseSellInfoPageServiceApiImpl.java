package com.bashiju.www.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.IHouseSellInfoPageServiceApi;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.comm.HouseTypeEnum;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.input.house.HouseSameParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.out.comm.BusinessRateResult;
import com.bashiju.www.pojo.service.out.comm.HousePropertyResult;
import com.bashiju.www.pojo.service.out.comm.ImgResult;
import com.bashiju.www.pojo.service.out.comm.LoanRateResult;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.house.AgentResult;
import com.bashiju.www.pojo.service.out.house.GoodHouseListResult;
import com.bashiju.www.pojo.service.out.house.HouseCompareResult;
import com.bashiju.www.pojo.service.out.house.HouseFeedbackResult;
import com.bashiju.www.pojo.service.out.house.HouseInfoResult;
import com.bashiju.www.pojo.service.out.house.ShowedRecordResult;
import com.bashiju.www.pojo.service.reservation.ReservationParam;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.comm.IFormService;
import com.bashiju.www.service.comm.ImgService;
import com.bashiju.www.service.house.IHouseService;
import com.bashiju.www.service.user.ICustomerPersonalCentralService;

/**
 * 
 *   二手房详细页面接口实现
 * @ClassName:  HouseSellInfoPageService   
 * @Description:  二手房详细页面接口实现
 * @author: wangkaifa
 * @date:   2018年8月10日 上午11:02:49       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class HouseSellInfoPageServiceApiImpl implements IHouseSellInfoPageServiceApi {
	@Autowired
	IHouseService houseService;
	@Autowired
	ICommService commService;
	@Autowired
	IFormService formService;
	@Autowired
	ICustomerPersonalCentralService  customerPersonalCentralService; 
	@Autowired
	ImgService imgService;
	
	@Override
	public HouseInfoResult querySellHouseInfo(long id) {
		HouseInfoResult result=houseService.selectSellHouseInfo(id);
		return result;
	}

	@Override
	public List<AgentResult> queryShowedAgents(long houseId) {
		List<AgentResult> results=houseService.queryShowedAgents(houseId);
		return results;
	}

	@Override
	public WebPage<HouseFeedbackResult> queryFeedback(long houseId,int sort, int page, int limit) {
		WebPage<HouseFeedbackResult> result=houseService.queryFeedback(houseId,sort, page, limit);
		return result;
	}

	@Override
	public boolean addFeedbackUseCount(int custId, int agentEvaluateHouseId) {
		boolean result=houseService.addFeedbackUseCount(custId, agentEvaluateHouseId);
		return result;
	}

	@Override
	public WebPage<ShowedRecordResult> queryShowedRecordByHouseId(long houseId, int page, int limit) {
		WebPage<ShowedRecordResult> result=houseService.queryShowedRecordByHouseId(houseId, page, limit);
		return result;
	}

	@Override
	public List<GoodHouseListResult> querySellSameHouseList(int communityId, int room, int price) {
		HouseSameParam param=new HouseSameParam();
		param.setCommunityId(communityId);
		param.setRoom(room);
		param.setPrice(price);
		List<GoodHouseListResult> results=houseService.querySellSameHouseList(param);
		return results;
	}

	@Override
	public List<HouseCompareResult> queryHouseCompareList(List<Long> ids) {
		List<HouseCompareResult> results=houseService.queryHouseCompareList(ids);
		return results;
	}

	@Override
	public boolean addHouseTipOff(HouseTipOffParam param) {
		boolean result=commService.addHouseTipOff(param);
		return result;
	}

	@Override
	public boolean addAttention(int custId, Integer houseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.SECONDHOUSE.getCode());
		boolean result=commService.addAttention(custId, houseId.toString(), type);
		return result;
	}

	@Override
	public boolean cancelAttention(int custId, Integer houseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.SECONDHOUSE.getCode());
		boolean result=commService.cancelAttention(custId, houseId.toString(), type);
		return result;
	}

	@Override
	public boolean isAttention(int custId,Integer houseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.SECONDHOUSE.getCode());
		boolean result=commService.isAttention(custId, houseId.toString(), type);
		return result;
	}

	@Override
	public boolean addFeedback(FeedbackParam param) {
		boolean result=commService.addFeedback(param);
		return result;
	}

	@Override
	public List<SelectItemResult> queryTipOffTypes() {
		Map<String,List<SelectItemResult>> map=formService.querySelectByCode(WebGlobal.TIPOFF_CODE);
		if (map!=null) {
			return map.get(WebGlobal.TIPOFF_CODE);
		}
		return null;
	}

	@Override
	public LoanRateResult queryLoanRate(String cityCode) {
		LoanRateResult result=commService.queryLoanRate(cityCode);
		return result;
	}

	
	@Override
	public boolean saveReservationWatchHouse(ReservationParam reservationParam) {
		boolean result = customerPersonalCentralService.saveReservationWatchHouse(reservationParam);
		return result;
	}


	
	@Override
	public void sendMobileVerfyCode(String mobile) {
		customerPersonalCentralService.sendMobileVerfyCode(mobile);
	}

	@Override
	public List<HousePropertyResult> queryHousePropertyList(String cityCode) {
		List<HousePropertyResult> results=commService.queryHousePropertyList(cityCode);
		return results;
	}

	/*@Override
	public BusinessRateResult queryBusinessRateResult() {
		BusinessRateResult result=commService.queryBusinessRateResult();
		return result;
	}*/
	
	@Override
	public boolean queryIsExistReservationWatchHouse(String custId, String shhId) {
		boolean result = customerPersonalCentralService.queryIsExistReservationWatchHouseByCustIdAndShhId(custId, shhId);
		return result;
	}
	
	//卖房委托图片
	@Override
    public List<ImgResult> getSellWtThemeImg(String cityCode){
    	List<ImgResult> res = imgService.queryImg(cityCode, "4");
    	return res;
    }

}
