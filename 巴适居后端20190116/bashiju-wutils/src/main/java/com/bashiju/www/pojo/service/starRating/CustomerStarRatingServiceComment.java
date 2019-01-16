/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerStarRatingServiceComment.java   
 * @Package com.bashiju.www.pojo.service.starRating      
 * @author: zuoyuntao     
 * @date:   2018年8月9日 下午3:23:05   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.starRating;

import java.io.Serializable;

import lombok.Data;

/**
 * 经纪人评价服务热评标签实体类
 * @ClassName:CustomerStarRatingServiceComment
 * @Description:经纪人评价服务热评标签实体类
 * @author:zuoyuntao
 * @date:2018年8月9日 下午3:23:05
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerStarRatingServiceComment implements Serializable  {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 5778363947863655827L;
	/**主键ID**/
	private int	id;
	/**经纪人ID**/
	private int	agentId;
	/**评价ID**/
	private long starRatingId;
	/**标签ID**/
	private String labelId;
	/**操作人ID**/
	private int	operatorId;
	/**标签名称****/
	private String labeld;
	/**权限域**/
	private String permissionArea;
	/**操作人名称**/
	private String operator;
	/**添加时间**/
	private String addTime;
	/**更新时间**/
	private String updateTime;
	/**是否可用**/
	private int isValid;
}
