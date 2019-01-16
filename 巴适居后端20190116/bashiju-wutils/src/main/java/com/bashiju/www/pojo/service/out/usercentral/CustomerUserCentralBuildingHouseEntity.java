/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerUserCentralBuildingHouseEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年9月13日 上午10:18:25   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 单元、门牌号实体类
 * @ClassName:CustomerUserCentralBuildingHouseEntity
 * @Description:单元、门牌号实体类
 * @author:zuoyuntao
 * @date:2018年9月13日 上午10:18:25
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerUserCentralBuildingHouseEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -2492765971865081508L;
	/**
	 * 单元ID或者房号ID
	 */
	private int id;
	/**
	 * 单元名称或门牌号名称
	 */
	private String name;
	/**
	 * 单元ID
	 */
	private int buildingUnitId;
	/**
	 * 座栋ID
	 */
	private int buildingId;
	/**
	 * 楼层编号
	 */
	private String floor;
	/**
	 * 父级ID
	 */
	private String parentId;
}
