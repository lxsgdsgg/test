package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   推荐房源信息
 * @ClassName:  GoodHouseListResult   
 * @Description:   推荐房源信息
 * @author: wangkaifa
 * @date:   2018年8月7日 下午5:38:07       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class GoodHouseListResult implements Serializable {

	private static final long serialVersionUID = 6726126189233156063L;
	/**房源id**/
	protected Long id;
	/**房源标题**/
	protected String title;
	/**房源封面图片地址**/
	protected String url;
	/**几室**/
	protected int room;
	/**几厅**/
	protected int hall;
	/**几卫**/
	protected int toilet;
	/**建筑面积**/
	protected int buildSpace;
	/**价格**/
	protected int price;
	/**装修**/
	protected String decoration;
	/**小区名称**/	
	protected String community;
	/**片区名称**/
	protected String region;
	/**区域名称**/
	protected String areaName;
}
