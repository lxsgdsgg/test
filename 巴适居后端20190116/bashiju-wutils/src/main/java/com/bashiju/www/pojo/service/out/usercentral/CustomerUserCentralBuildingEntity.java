/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerUserCentralBuildingEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年9月13日 上午10:18:01   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 个人中心--房源委托--座栋信息实体类
 * @ClassName:CustomerUserCentralBuildingEntity
 * @Description:个人中心--房源委托--座栋信息实体类
 * @author:zuoyuntao
 * @date:2018年9月13日 上午10:18:01
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class CustomerUserCentralBuildingEntity implements Serializable{

	/**   
	 * @Fields serialVersionUID :
	 */ 
	
	private static final long serialVersionUID = 8661941881027143270L;
	/**
	 * 座栋ID
	 */
	private int id;
	/**
	 * 座栋名称
	 */
	private String buildingName;
	/**
	 * 小区ID
	 */
	private int communityId;
	/**
	 * 行政区划编码
	 */
	private String areaCode;
	/**
	 * 行政区划名称
	 */
	private String areaName;
	/**
	 * 是否有效：0否1是
	 */
	private int isValid;
	
}
