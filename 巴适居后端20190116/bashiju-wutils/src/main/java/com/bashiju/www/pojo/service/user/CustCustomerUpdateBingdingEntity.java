/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustCustomerUpdateBingdingEntity.java   
 * @Package com.bashiju.www.pojo.service.user   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月17日 上午9:52:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**   
 * @ClassName:  CustCustomerUpdateBingdingEntity   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpeng
 * @date:   2018年10月17日 上午9:52:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustCustomerUpdateBingdingEntity implements Serializable {

	
	private static final long serialVersionUID = 1693880175005059254L;
	
	
	/**
	 * 绑定的客户编号
	 */
	private String custId;
	
	/**
	 * 第三方账号
	 */
	private String code;
	
	/**
	 * 第三方账号名称
	 */
	private String name;
	
	/**
	 * 第三方头像
	 */
	private String profilePhoto;
	
	/**
	 * 账户类型 qq:01 , wx：02，微信小程序：05
	 */
	private String numType;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
}
