package com.bashiju.www.pojo.service.out.newhouse;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 
 *   新房楼盘返回对象
 * @ClassName:  NewHouseInfoResult   
 * @Description:   新房楼盘返回对象
 * @author: liwen
 * @date:   2018年8月18日 下午17:10:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class NewHouseDetailResult implements Serializable{
	
	private static final long serialVersionUID = -8034222945621191964L;
	/**id**/
	private int id;
	/**楼盘名称**/
	private String name;
	/**近期开盘**/
	protected String openDate;
	/**交房时间**/
	private String deliverDate;	
	/**预售证号**/
	private String preSellNo;
	/**楼盘地址**/
	protected String address;
	/**平均价格（单位元）**/
	private int avgPrice;
	/**售楼地址**/
	private String salesDeptAdd;
	/**开发商**/
	private String developers;
	/**经度**/
	private String longitude;
	/**纬度**/
	private String latitude;
	/**产权年限**/
	private int propertyLimit;
	/**地铁标签**/
	private String metroLabel;
	/**学校标签**/
	private String schoolLabel;
	/**经纪人id**/
	private String agentId;
	/**经纪人名字**/
	private String agentName;
	/**经纪人电话**/
	private String agentPhone;
	/**经纪人头像**/
	private String agentUrl;
	/**片区id**/
	protected long regionId;
	/**片区名称**/
	protected String region;
	/**区域编号**/
	protected String areaCode;
	/**所属区域**/
	private String areaName;
	/**物业用途**/
	private String propertyType;
	/**装修情况**/
	private String decoration;
	/**车位数量**/
	private int parkCnt;
	/**容积率**/
	private BigDecimal capacityRatio;
	/**绿化率**/
	private BigDecimal greenRate;
	/**占地面积**/
	private double floorArea;
	/**物业公司**/
	private String propertyName;
	/**楼盘图片列表**/
	private List<NewHouseImgList> imgList;
	/**价格详情列表**/
	private List<NewHousePriceDetailResult> priceList;
	/**预售证号列表**/
	private List<PresalePermitResult> preSellNoList;
	/**开盘时间列表**/
	private List<OpenDateResult> openDateList;
	/**交房时间列表**/
	private List<RoomTimeResult> deliverDateList;
	/**户型图列表**/
	private List<NewHouseTypeList> roomTypeList;
}
