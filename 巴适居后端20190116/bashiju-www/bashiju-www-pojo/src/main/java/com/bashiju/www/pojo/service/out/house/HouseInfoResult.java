package com.bashiju.www.pojo.service.out.house;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 *   房源详细返回对象
 * @ClassName:  HouseInfoResult   
 * @Description:   房源详细返回对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午4:10:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseInfoResult extends HouseListResult implements Serializable{
	/**小区id**/
	protected long communityId;
	/**房源编号**/
	private String houseCode;
	/**房源核验码**/
	private String verificationCode;
	/**公司名称**/
	private String companyName;	
	/**几厨**/
	private int kitchen;
	/**几阳**/
	private int balcony;
	/**单元电梯数量**/
	private int elevatorCount;
	/**单元同层房屋数量**/
	private int houseCount;
	/**产权年限**/
	private String propRightsLen;
	/**供暖方式**/
	private String heatingName;
	/**房源类型**/
	protected String houseType;
	/**建筑结构**/
	private String structure;
	/**挂牌时间**/
	private String openPlateDate;
	/**上次交易时间**/
	private String lastTradingTime;
	/**交易年限**/
	private String housingYears;
	/**抵押信息**/
	private String mortgageName;
	/**交易权属**/
	private String tradingRightsName;
	/**房屋用途**/
	private String houseUses;
	/**产权类型**/
	private String propertyType;
	/**房本备件**/
	private String uploadCertificate;
	
	/**带看总数量**/
	private int showedTotal;
	
	/**维护经纪人id**/
	private long agentId;
	/**房源经纪人信息**/
	private AgentResult agent;
	/**小区简介**/
	private CommunityResult communityInfo;
	/**房源特色**/
	private List<HouseDescriptionResult> houseDescriptions;
	/**房源图片**/
	private List<HouseImageResult> houseImages;
	/**其他公司的同套房源连接**/
	private List<HouseSameLinkResult> links;
}
