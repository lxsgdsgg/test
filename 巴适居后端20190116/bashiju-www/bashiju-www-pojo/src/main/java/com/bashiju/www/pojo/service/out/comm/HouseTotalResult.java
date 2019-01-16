package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源数量统计
 * @ClassName:  HouseTotalResult   
 * @Description: 房源数量统计
 * @author: wangkaifa
 * @date:   2018年7月28日 下午1:58:23       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseTotalResult implements Serializable{
	/**新房数量**/
	private int newHouseTotal;
	/**二手房数量**/
	private int sellHouseTotal;
	/**租房数量**/
	private int rentHouseTotal;
}
