package com.bashiju.housing.service;

import java.util.List;

import com.bashiju.housing.pojo.BusinessRateResult;
import com.bashiju.housing.pojo.HousePropertyResult;
import com.bashiju.housing.pojo.LoanRateResult;


public interface ICommService {
	/**
	 * 
		 * 查询贷款利率
		 * @Description:查询贷款利率） 
		 * @param cityCode 城市代码
		 * @return 
		 * LoanRateResult
	 */
	LoanRateResult queryLoanRate();
	/**
	 * 
		 * 房源类型列表查询
		 * @Description: 房源类型列表查询
		 * @param cityCode 城市代码
		 * @return 
		 * HousePropertyResult
	 */
	List<HousePropertyResult> queryHousePropertyList();
	/**
	 * 
		 * 非住宅交易税费比例
		 * @Description: 非住宅交易税费比例
		 * @return 
		 * BusinessRateResult
	 */
	BusinessRateResult queryBusinessRateResult();
	/**
	 * 
		 * 点击拨号服务接口
		 * @Description: 点击拨号服务接口 
		 * @param houseId 房源id
		 * @param dst 房东手机/座机号码  
		 * @return 成功-true;失败-false 
		 * boolean
	 */
	boolean tencentCloudClickDial(String houseId,String dst);
}
