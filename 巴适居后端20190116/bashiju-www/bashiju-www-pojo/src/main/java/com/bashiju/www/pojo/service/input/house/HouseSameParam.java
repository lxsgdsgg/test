package com.bashiju.www.pojo.service.input.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   查询相似房源参数
 * @ClassName:  HouseSameParam   
 * @Description:  查询相似房源参数
 * @author: wangkaifa
 * @date:   2018年7月26日 下午2:39:38       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseSameParam implements Serializable {
	/** 小区id**/
	private int communityId;
	/** 几室**/
	private int room;
	/**出售价格单位万元**/
	private int sellPrice;
	/**出租价格单元元**/
	private int rentPrice;
	/**交易类型1:二手房,2:租房**/
	private int transactionTypeId;
}
