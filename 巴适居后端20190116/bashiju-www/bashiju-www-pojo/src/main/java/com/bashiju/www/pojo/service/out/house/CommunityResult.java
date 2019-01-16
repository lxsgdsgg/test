package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 *   房源明细小区简介
 * @ClassName:  CommunityResult   
 * @Description:  房源明细小区简介
 * @author: wangkaifa
 * @date:   2018年7月25日 下午2:10:52       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CommunityResult implements Serializable{
	/**小区名称**/
	private String name;
	/**销售均价元/m2**/
	private int salePrice;
	/**建筑类型**/
	private String buildIngType;
	/**楼栋总数**/
	private int buildingCount;
	/**规划总户数**/
	private int houseCount;
	/**开发商**/
	private String developers;
	/**物业公司**/
	private String propertyName;
	/**物业类型**/
	private String estateType;
	/**物业费**/
	private String propertyFees;
	/**建筑年代**/
	private int buildYear;
	/**建筑面积**/
	private int buildingArea;
	/**车位数量**/
	private int parkingCount;
	/**容积率**/
	private double capacityRatio;
	/**绿化率**/
	private double greenRate;
	
	
	/**小区封面图**/
	private String imageUrl;
	/**出售房源数量**/
	private int sellHouseCount;
	/**出租房源数量**/
	private int rentHouseCount;
}
