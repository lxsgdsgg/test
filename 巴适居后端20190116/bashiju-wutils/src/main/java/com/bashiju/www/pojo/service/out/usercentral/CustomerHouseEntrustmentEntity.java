/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseEntrustmentEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年7月30日 上午10:00:42   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 房源委托实体对象
 * @ClassName:CustomerHouseEntrustmentEntity
 * @Description:房源委托实体对象
 * @author:zuoyuntao
 * @date:2018年7月30日 上午10:00:42
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class CustomerHouseEntrustmentEntity implements Serializable  {
	/**   
	 * @Fields serialVersionUID :  
	 */ 
	private static final long serialVersionUID = -5641023047697294917L;
	/**主键**/
	private int id;
	/**用户登陆的Key**/
	private String loginKey;
	/**客户ID**/
	private String custId;
	/**交易类型（1出售 2出租）--必填**/
	private int transactionType;
	/**出租类型0--标识非出租(默认)1--整租 2--合租**/
	private int leaseType;
	/**合租类型(当出租类型为合租时有效)--0--非合租类型(默认)	1--主卧	2--次卧	3--单间**/
	private int joinRentStyle;
	/**城市编码**/
	private int cityCode;
	/**区域代码--必填**/
	private String areaCode;
	/**区域名称--必填**/
	private String areaName;
	/**片区ID--必填**/
	private int regionId;
	/**片区名称--必填**/
	private String regionName;
	/**小区ID--必填**/
	private int communityId;
	/**小区名称--必填**/
	private String communityName;
	/**座栋ID--必填**/
	private int buildingsId;
	/**座栋名称--必填**/
	private String buildingsName;
	/**单元ID--必填**/
	private int bulidingUnitId;
	/**单元名称--必填**/
	private String buildingsUnitName;
	/**房号ID--必填**/
	private int buildingHouseId;
	/**房号名称--必填**/
	private String buildingHouseName;
	/**卧室--必填**/
	private int room;
	/**大厅--必填**/
	private int hall;
	/**厨房--必填**/
	private int kitchen;
	/**卫生间--必填**/
	private int toilet;
	/**建筑面积--必填**/
	private double buildSpace;
	/**楼层描述**/
	private int floorDesc;
	/**出售价格--必填**/
	private double sellingPrice;
	/**出租价格--必填**/
	private double rentPrice;
	/**委托人手机号码--必填**/
	private String mobile;
	/**委托人称呼--必填**/
	private String clienteleName;
	/**委托人性别**/
	private int clienteleSex;
	/**委托时间**/
	private Object addTime;
	/**房源描述--备注--必填**/
	private String descs;
	/**处理标记（0未处理1已处理）**/
	private int handleFlag;
	/**付款方式ID**/
	private int payTypeId;
	/**付款方式名称**/
	private String payType;
	/**房屋交易类型: 1 出售 2 出租**/
	private String transactionTypeName;
	/***处理状态：0 受理中 1出售中 2出租中 3已出售 4已出租 5已取消**/
	private String status;
	/***处理状态：未处理 ,已处理 ,已失效**/
	private String statusName;
	/**经纪人电话**/
	private String agentMobile;
	/**经纪人名称**/
	private String agentName;
	/**经纪人标签**/
	private String agentLabel;
	/**经纪人头像**/
	private String profilePhoto;
	/**委托公司**/
	private String companyName;
	/**是否取消：0已取消1正常**/
	private String isValid;
	/**房源ID**/
	private int shhId;
	/**房源图片**/
	private String houseImg;
}
