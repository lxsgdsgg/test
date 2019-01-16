/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustCustomerUpdateBingdingParam.java   
 * @Package com.bashiju.www.pojo.service.user   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月17日 上午9:16:32   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.user;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  CustCustomerUpdateBingdingParam   
 * @Description:修改绑定第三方登录实体
 * @author: wangpeng
 * @date:   2018年10月17日 上午9:16:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustCustomerUpdateBingdingParam implements Serializable { 
	
	private static final long serialVersionUID = -3725896107115790576L;
	
	
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
	 * 账号类型 : 腾讯QQ:01 , 微信：02，微信小程序：05
	 */
	private String numType;

}
