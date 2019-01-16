/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerAgentStarCommentsParamsEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年9月25日 下午3:17:48   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.bashiju.www.pojo.service.starRating.CustomerStarRatingHotLabelEntity;
import com.bashiju.www.pojo.service.starRating.CustomerStarRatingServiceComment;

import lombok.Data;

/**
 * 经纪人评价参数实体类
 * @ClassName:CustomerAgentStarCommentsParamsEntity
 * @Description:经纪人评价参数实体类
 * @author:zuoyuntao
 * @date:2018年9月25日 下午3:17:48
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerAgentStarCommentsParamsEntity implements Serializable{

	/**   
	 * @Fields serialVersionUID :  
	 */ 
	private static final long serialVersionUID = -3522335973474311362L;
	/**经纪人详情ID--必填**/
	@NotEmpty(message="经纪人ID不能为空")
	private int agentId;
	/**评价星级--必填**/
	@NotEmpty(message="星级评价不能为空")
	private int star;
	/**用户名称--必填**/
	@NotEmpty(message="客户名称不能为空")
	private String custName;
	/**评价内容--必填**/
	@NotEmpty(message="评价内容不能为空")
	private String content;
	/**评价热门标签集合--labelId,labeld为必填项**/
	private List<CustomerStarRatingHotLabelEntity> hotRatingLabelList;
	/**服务点评标签集合--labelId,labeld为必填项**/
	private List<CustomerStarRatingServiceComment> serviceRatingLabelList;
}
