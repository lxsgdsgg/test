/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityPriceTrend.java   
 * @Package com.bashiju.www.pojo.service.community   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 下午1:35:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.community;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  CommunityPriceTrend   
 * @Description:小区价格趋势   
 * @author: yangz
 * @date:   2018年8月15日 下午1:35:34   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CommunityPriceTrend implements Serializable {

	private static final long serialVersionUID = -267352645292903581L;

	/**
	 * 月份(1900-01)
	 */
	private String months;
	
	/**
	 * 成交量
	 */
	private String saleDealCnt;
	
	/**
	 * 成交均价
	 */
	private String saleDealAvgPrice;
	
	/**
	 * 挂牌均价
	 */
	private String saleAvgPrice;
}
