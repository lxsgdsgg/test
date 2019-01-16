package com.bashiju.www.pojo.service.out.newhouse;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   新房相似楼盘信息
 * @ClassName:  SameNewHouseList   
 * @Description:  新房相似楼盘信息
 * @author: liwen
 * @date:   2018年8月18日 下午17:05:02       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class SameNewHouseList implements Serializable{
	
	private static final long serialVersionUID = 2593752829256238125L;
	/**名称**/
	private String name;
	/**均价(单位元)**/
	private int avgPrice;
	/**图片**/
	private String imageUrl;
	/**地址**/
	private String address;
	
}
