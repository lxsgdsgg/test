/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MyEntrustmentEntity.java   
 * @Package com.bashiju.www.pojo.service.out.house      
 * @author: zuoyuntao     
 * @date:   2018年7月25日 下午6:11:08   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import lombok.Data;

/**
 * 我的客源委托实体类
 * @ClassName:MyEntrustmentEntity
 * @Description:我的客源委托实体类
 * @author:zuoyuntao
 * @date:2018年7月25日 下午6:11:08
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class CustomerDemandEntrustmentEntity {
	/**主键**/
	private int id;
	/**客户ID**/
	private String custId;
	/**交易类型**/
	private int transactionType;
	/**出租类型**/
	private int leaseType;
	/**合租类型**/
	private int joinRentStyle;
	/**城市编码**/
	private int cityCode;
	/**区域编码**/
	private String areaCode;
	/**区域名称**/
	private String areaName;
	/**片区ID**/
	private int regionId;
	/**片区名称**/
	private String regionName;
	/**小区ID**/
	private int communityId;
	/**小区名称**/
	private String communityName;
	/**卧室数量**/
	private int room;
	/**大厅数量**/
	private int hall;
	/**卫生间数量**/
	private int toilet;
	/**建筑面积**/
	private int buildSpace;
	/**楼层描述**/
	private int floorDesc;
	/**装修描述ID**/
	private int decorationId;
	/**装修描述**/
	private String decoration;
	/**朝向ID**/
	private int orientationId;
	/**朝向**/
	private String orientation;
	/**出售最低价**/
	private int minBuyPrice;
	/**出售最高价**/
	private int maxBuyPrice;
	/**出租最低价**/
	private int minRentPrice;
	/**出租最高价**/
	private int MaxRentPrice;
	/**手机号码**/
	private String mobile;
	/**客户称呼**/
	private String clienteleName;
	/**客户性别 0:先生 1 女士**/
	private int clienteleSex;
	/**添加时间**/
	private String addTime;
	/**客源描述**/
	private String descs;
	/**处理标记（0：未处理，1：已处理）**/
	private int handleFlag;
}
