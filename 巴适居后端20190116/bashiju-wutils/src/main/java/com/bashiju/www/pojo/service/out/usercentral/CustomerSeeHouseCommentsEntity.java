/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseStarCommentsEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年8月4日 下午5:27:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 房源评价实体类
 * @ClassName:CustomerHouseStarCommentsEntity
 * @Description:房源评价实体类
 * @author:zuoyuntao
 * @date:2018年8月4日 下午5:27:25
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 *             本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerSeeHouseCommentsEntity implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = -4687631406564567209L;
//	/**房源ID**/
//	@NotEmpty(message="房源ID不能为空")
//	private int houseId;
//	/**带看记录ID**/
//	@NotEmpty(message="带看记录ID不能为空")
//	private int showedRecordId;
	/**客户ID**/
	@NotEmpty(message="客户ID不能为空")
	private int custId;
	/**经纪人ID**/
	private int agentId;
	/**公司ID**/
	private String companyId;
	/**客户名称**/
	private String custName;
	/**权限域**/
	private String permissionArea;
	/**操作人ID**/
	private int operatorId;
	/**操作人**/
	private String operator;
	/**添加事件**/
	private String addTime;
	/**带看记录主键**/
	@NotEmpty(message="带看记录ID不能为空")
	private int id;
	/**评价内容**/
	@NotEmpty(message="评价内容不能为空")
	private String visterEvaluate;
	/**更新时间**/
	private String updateTime;

	/**房源交易类型ID**/
	private String transactionTypeId;
	/**房源交易类型**/
	private String transactionType;
	/**房源ID**/
	private String houseId;
	/**房源编号**/
	private String houseSequence;
	/**区域编码**/
	private String areaCode;
	/**区域名称**/
	private String areaName;
	/**片区编码**/
	private String regionId;
	/**片区名称**/
	private String regionName;
	/**小区ID**/
	private String communityId;
	/**小区名称**/
	private String communityName;
	/**朝向ID**/
	private String orientationId;
	/**朝向名称**/
	private String orientation;
	/**装修ID**/
	private String decorationId;
	/**装修名称**/
	private String decoration;
	/**出售价格**/
	private BigDecimal sellingPrice;
	/**出租价格**/
	private BigDecimal rentPrice;
	/**最低出售价格**/
	private BigDecimal minSellingPrice;
	/**最低出租价格**/
	private BigDecimal minRentPrice;
	/**房间数**/
	private int room;
	/**大厅数量（客厅/饭厅）**/
	private int hall;
	/**卫生间数**/
	private int toilet;
	/**厨房数量**/
	private int kitchen;
	/**阳台数量**/
	private int balcony;
	/**建筑面积**/
	private double buildSpace;
	/**使用面积**/
	private double useSpace;
	/**建筑年代**/
	private String buildDates;
	/**是否委托**/
	private String isIntrust;
	/**房源标签多个标签用逗号隔开**/
	private String labeld;
	/**所在楼层描述**/
	private String floorDes;
	/**总楼层**/
	private int totalLayers;
	/**房源封面图片地址**/
	private String imageUrl;
	/**是否有效**/
	private String isValid;
	/**座栋ID**/
	private int buildingsId;
	/**座栋名称**/
	private String buildingsName;
	/**房号ID**/
	private String buildingHouseId;
	/**房号名称**/
	private String buildingHouseName;
	/**单元ID**/
	private int bulidingUnitId;
	/**单元名称**/
	private String buildingsUnitName;
	/**配套**/
	private String matching;
}
