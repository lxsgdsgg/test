/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerAgentAcceptEntrustmentEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年7月30日 上午10:01:56   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 经纪人受理的委托实体对象
 * @ClassName:CustomerAgentAcceptEntrustmentEntity
 * @Description:经纪人受理的委托实体对象
 * @author:zuoyuntao
 * @date:2018年7月30日 上午10:01:56
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerAgentAcceptEntrustmentEntity implements Serializable  {
	/**   
	 * @Fields serialVersionUID :
	 */ 
	private static final long serialVersionUID = 8567367924647883912L;
	/**主键**/
	private int id;
	/**委托类型 0--房源委托 1--需求委托**/
	private int entrustType;
	/**资源ID（房源ID或者客源ID）**/
	private long sourceId;
	/**用户ID**/
	private int userId;
	/**状态0--未处理1--已处理2--已失效**/
	private int status;
	/**处理时间**/
	private String handelTime;
	/**处理结果ID**/
	private int resultId;
	/**处理结果**/
	private String handelResult;
	/**权限域**/
	private String permissionArea;
	/**操作人ID**/
	private int operatorId;
	/**操作人**/
	private String operator;
	/**委托时间**/
	private String addTime;
	/**更新时间**/
	private String updateTime;
	/**是否可用**/
	private int isvalid;
}
