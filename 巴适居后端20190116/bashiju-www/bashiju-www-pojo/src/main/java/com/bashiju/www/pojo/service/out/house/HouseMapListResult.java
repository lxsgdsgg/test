package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源地图搜索结果列表信息
 * @ClassName:  HouseMapListResult   
 * @Description:   房源地图搜索结果列表信息
 * @author: wangkaifa
 * @date:   2018年8月9日 下午12:04:26       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseMapListResult implements Serializable{
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
	/**朝向**/
	protected String orientation;
	/**价格**/
	protected int price;	
	/**片区名称**/
	protected String region;
	/**区域名称**/
	protected String areaName;
	/**免税标签**/
	protected String dutyFreeName;
	/**满两年标签**/
	protected String twoYearName;
	/**满五年标签**/
	protected String fiveYearName;
	/**房本在手标签**/
	protected String houseCardName;
	/**随时看房标签**/
	protected String allTimeName;
	/**地铁标签，逗号分隔**/
	protected String metroLabel;
	/**学区房标签，逗号分隔**/
	protected String schoolLabel;
}
