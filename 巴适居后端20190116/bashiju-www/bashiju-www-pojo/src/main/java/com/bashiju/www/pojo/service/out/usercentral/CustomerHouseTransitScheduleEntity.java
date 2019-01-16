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
public class CustomerHouseTransitScheduleEntity {
	/**进度名称**/
	private String scheduleName;
	/**过户状态**/
	private String status;
	/**过户预计完成时间**/
	private String estimateFinishTime;
	/**过户实际完成时间**/
	private String actualFinishTime;
	/**过户流程ID**/
	private int transferId;
	/**过户流程名称**/
	private String transfer;
	/**过户备注**/
	private String remark;
	/**成交ID**/
	private String dealSequence;
	/**房源ID**/
	private String houseSequence;
	/**具体房源详细--个人中心使用**/
	private CustomerHouseBaseEntity houseEntity;
}
