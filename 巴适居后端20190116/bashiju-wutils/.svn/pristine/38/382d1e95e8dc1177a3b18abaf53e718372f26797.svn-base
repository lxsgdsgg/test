/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerStarCommentsEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年7月26日 上午10:54:00   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.bashiju.www.pojo.service.starRating.CustomerStarRatingHotLabelEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingServiceComment;

import lombok.Data;

/**
 * 客户对经纪人的星级评价实体类
 * @ClassName:CustomerStarCommentsEntity
 * @Description:客户对经纪人的星级评价实体类
 * @author:zuoyuntao
 * @date:2018年7月26日 上午10:54:00
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class CustomerAgentStarCommentsEntity implements Serializable  {
	/**   
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -4700234695624710965L;
	/**主键ID**/
	private int id;
	/**经纪人ID**/
	@NotEmpty(message="经纪人ID不能为空")
	private int userId;
	/**经纪人详情ID**/
	private int agentId;
	/**评价星级**/
	@NotEmpty(message="星级评价不能为空")
	private int star;
	/**评价内容**/
	private String content;
	/**登陆用户ID**/
	@NotEmpty(message="客户ID不能为空")
	private int custId;
	/**登陆用户名称**/
	private String custName;
	/**评价审核状态**/
	private int examineStatus;
	/**评价审核人**/
	private int auditorId = 0;
	/**房源标题**/
	private String titles;
	/**评价审核人**/
	private String auditor = "";
	/**评价审核时间**/
	private String auditTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	/**评价驳回原因**/
	private String reason = "";
	/**经纪人权限域**/
	@NotEmpty(message="权限域不能空")
	private String permissionArea;
	/**添加经纪人操作人ID**/
	@NotEmpty(message="操作人ID不能空")
	private String operatorId;
	/**添加经纪人操作人**/
	private String operator;
	/**评价时间**/
	private String addTime;
	/**评价更新时间**/
	private String updateTime;
	/**是否可用**/
	@NotEmpty(message="是否有效不能空")
	private int isValid;
	/**评价热门标签集合--labelId,labeld为必填项**/
	private List<CustomerStarRatingHotLabelEntity> hotRatingLabelList;
	/**服务点评标签集合--labelId,labeld为必填项**/
	private List<CustomerStarRatingServiceComment> serviceRatingLabelList;
}
