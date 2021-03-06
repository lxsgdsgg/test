/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerHouseRequestEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 上午11:03:35   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 客户房源需求实体类
 * @ClassName:CustomerHouseRequestEntity
 * @Description:客户房源需求实体类
 * @author:zuoyuntao
 * @date:2018年7月26日 上午11:03:35
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerHouseRequestEntity implements Serializable {
	/**   
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 7584682035567055112L;
	/**
	 * 需求编号
	 */
	private String id;
	/**
	 * 委托时间
	 */
	private String releaseTime;
	/**
	 * 客户编号
	 */
	private String custId;
	/**
	 * 客户名称
	 */
	private String custName;
	/**
	 * 最后带看时间
	 */
	private String lastShowedTime;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 经纪人名称
	 */
	private String agentName;
	/**
	 * 经纪人电话
	 */
	private String agentMobile;
	/**
	 * 经纪人头像
	 */
	private String profilePhoto;
	/**
	 * 经纪人标签
	 */
	private String label;
	/**
	 * 状态名称
	 */
	private String statusName;
	/**
	 * 状态编码（0:挂牌中 1:已成交 2:过户中 3:已完成 4:已失效）
	 */
	private String status;
}
