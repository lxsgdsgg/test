package com.bashiju.www.pojo.service.out.house;


import java.io.Serializable;
import java.util.List;

import com.bashiju.www.pojo.service.out.article.RentSkillLinkResult;

import lombok.Data;

/**
 * 
 *   出租房源详细返回对象
 * @ClassName:  HouseInfoResult   
 * @Description:   出租房源详细返回对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午4:10:39       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class RentHouseInfoResult extends HouseListResult implements Serializable{
	
	private static final long serialVersionUID = 5262957831008011402L;

	/**房源编号**/
	private String houseCode;
	/**房源核验码**/
	private String verificationCode;
	/**房屋现状**/
	private String actuality;
	/**付款方式**/
	private String payType;
	/**公司名称**/
	private String companyName;
	/**房源配套:1电梯,2热水器,3抽油烟机,4电视机,5空调,6冰箱,7洗衣机,8煤气/天然气,9沙发,10衣柜,11餐桌,12床,13橱柜,14马桶;多个用逗号分隔**/
	private String matchingIds;
	/**视频地址**/
	private String videoUrl;
	/**VR地址**/
	private String vRUrl;
	/**带看总数量**/
	private int showedTotal;
	/**租房类型:1整租、2合租、3公寓**/
	private Integer rentType;
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
	/**同价位房源**/
	private List<GoodHouseListResult> samePriceHouses;
}
