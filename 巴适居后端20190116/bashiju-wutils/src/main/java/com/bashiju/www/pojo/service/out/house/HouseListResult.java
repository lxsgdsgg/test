package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源列表搜索返回对象
 * @ClassName:  HouseListResult   
 * @Description:  房源列表搜索返回对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午3:23:45       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseListResult implements Serializable{
	
	private static final long serialVersionUID = 1572313855470140923L;
	/**房源id**/
	protected Long id;
	/**房源标题**/
	protected String title;
	/**房源封面图片地址**/
	protected String url;
	/**是否有VR:0否,1是**/
	protected int isVR;
	/**是否有视频:0否,1是**/
	protected int isVideo;
	/**几室**/
	protected int room;
	/**几厅**/
	protected int hall;
	/**几卫**/
	protected int toilet;
	/**朝向**/
	protected String orientation;
	/**建筑面积**/
	protected int buildSpace;
	/**使用面积**/
	protected int useSpace;
	/**装修**/
	protected String decoration;
	/**所在楼层描述**/
	private String floorDes;
	/**总楼层**/
	protected int totalFloor;
	/**建筑年代**/
	protected String buildDates;
	/**价格**/
	protected int price;
	/**房源单价**/
	protected int averagePrice;
	/**关注次数**/
	protected int attentionCount;
	/**小区id**/
	protected long communityId;
	/**小区名称**/	
	protected String community;
	/**片区id**/
	protected long regionId;
	/**片区名称**/
	protected String region;
	/**区域编号**/
	protected String areaCode;
	/**区域名称**/
	protected String areaName;
	/**房源标签，逗分隔**/
	protected String labeld;
	/**地铁标签，逗号分隔**/
	protected String metroLabel;
	/**学区房标签，逗号分隔**/
	protected String schoolLabel;
	/**最近15天带看次数**/
	private int lastViewingCount;
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
	/**是否新上:0否,1是**/
	protected Integer isNew;
}
