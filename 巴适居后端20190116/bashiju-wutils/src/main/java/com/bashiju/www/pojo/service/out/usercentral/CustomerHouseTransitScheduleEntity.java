/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseTransitScheduleEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 上午10:43:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 过户进度实体类
 * @ClassName:HouseTransitScheduleEntity
 * @Description:过户进度实体类
 * @author:zuoyuntao
 * @date:2018年7月26日 上午10:43:58
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerHouseTransitScheduleEntity implements Serializable  {
	/**   
	 * @Fields serialVersionUID :
	 */ 
	private static final long serialVersionUID = 3764626386603029255L;
	/**成交ID**/
	private String id;
	/**开始时间**/
	private String startTime;
	/**成交ID**/
	private String dealSequence;
	/**房源ID**/
	private String houseSequence;
	/**具体房源详细--个人中心使用**/
	private CustomerOwnerHouseEntity houseEntity;
	/**客户ID**/
	private String custId;
	/**小区名称**/
	private String communityName;
	/**过户状态00-未启动01--过户中02--已完成**/
	private String transferStatus;
	/**过户状态，未启动，过户中，已完成**/
	private String transferStatusName;
	/**过户流程对象**/
	private List<CustomerTransactionScheduleEntity> scheduleEntity;
	/**房源ID**/
	private String shhid;
	/**过户房源详细**/
	private CustomerTransitHouseEntity transitHouseEntity;
}
