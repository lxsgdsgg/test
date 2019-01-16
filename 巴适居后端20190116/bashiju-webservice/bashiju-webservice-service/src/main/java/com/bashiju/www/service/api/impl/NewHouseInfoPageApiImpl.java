package com.bashiju.www.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.wapi.INewHouseInfoPageApi;
import com.bashiju.www.global.WebGlobal;
import com.bashiju.www.pojo.comm.HouseTypeEnum;
import com.bashiju.www.pojo.service.input.comm.FeedbackParam;
import com.bashiju.www.pojo.service.input.house.HouseTipOffParam;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDetailResult;
import com.bashiju.www.pojo.service.out.newhouse.NewHouseDynamicResult;
import com.bashiju.www.pojo.service.out.newhouse.SameNewHouseList;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.comm.IFormService;
import com.bashiju.www.service.newhouse.INewHouseService;

/**
 * 
 *   新房详情页接口实现
 * @ClassName:  NewHouseInfoPageApiImpl   
 * @Description:  新房详情页接口实现
 * @author: wangkaifa
 * @date:   2018年8月22日 上午11:10:54       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class NewHouseInfoPageApiImpl implements INewHouseInfoPageApi {
	@Autowired
	ICommService commService;
	@Autowired
	IFormService formService;
	@Autowired
	INewHouseService newHouseService; 
	
	@Override
	public NewHouseDetailResult selectNewHouseDetailInfo(int newHouseId) {
		NewHouseDetailResult result=newHouseService.selectNewHouseDetailInfo(newHouseId);
		return result;
	}

	@Override
	public List<SameNewHouseList> querySameNewHouseList(int avgPrice, int limit) {
		List<SameNewHouseList> results=newHouseService.querySameNewHouseList(avgPrice, limit);
		return results;
	}

	@Override
	public List<NewHouseDynamicResult> queryNewHouseDynamicList(String newHouseId) {
		List<NewHouseDynamicResult> results=newHouseService.queryNewHouseDynamicByProjectId(newHouseId);
		return results;
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
	public boolean addHouseTipOff(HouseTipOffParam param) {
		boolean result=commService.addHouseTipOff(param);
		return result;
	}

	@Override
	public boolean addAttention(int custId, Integer newHouseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.NEWHOUSE.getCode());
		boolean result=commService.addAttention(custId, newHouseId.toString(), type);
		return result;
	}

	@Override
	public boolean cancelAttention(int custId, Integer newHouseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.NEWHOUSE.getCode());
		boolean result=commService.cancelAttention(custId, newHouseId.toString(), type);
		return result;
	}

	@Override
	public boolean isAttention(int custId, Integer newHouseId) {
		Integer type=Integer.parseInt(HouseTypeEnum.NEWHOUSE.getCode());
		boolean result=commService.isAttention(custId, newHouseId.toString(), type);
		return result;
	}

	@Override
	public boolean addFeedback(FeedbackParam param) {
		boolean result=commService.addFeedback(param);
		return result;
	}

}
