package com.bashiju.housing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.housing.pojo.HousePropertyResult;
import com.bashiju.housing.pojo.LoanRateResult;
import com.bashiju.housing.service.ICommService;
import com.bashiju.utils.util.BashijuResult;

@Controller
public class CommController {
	@Autowired
	ICommService commService;
	
	@RequestMapping(value="queryLoanRate")
	@ResponseBody
	public BashijuResult  queryLoanRate(){
		LoanRateResult result=commService.queryLoanRate();
		return BashijuResult.build(true, "success", result);
	}
	
	@RequestMapping(value="queryHousePropertyList")
	@ResponseBody
	public BashijuResult  queryHousePropertyList(){
		List<HousePropertyResult> result=commService.queryHousePropertyList();
		return BashijuResult.build(true, "success", result);
	}
	/**
	 * 
		 * 点击拨号服务接口
		 * @Description: 点击拨号服务接口
		 * @param houseId 房源id
		 * @param dst 业主电话
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="tencentCloudPhone")
	@ResponseBody
	public BashijuResult tencentCloudPhone(String houseId, String dst) {
		boolean result = commService.tencentCloudClickDial(houseId, dst);
		return BashijuResult.ok(result);
	}
	
}
