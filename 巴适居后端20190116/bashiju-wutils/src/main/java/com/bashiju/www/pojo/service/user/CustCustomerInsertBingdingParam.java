/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustCustomerInsertBingdingParam.java   
 * @Package com.bashiju.www.pojo.service.user   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月17日 上午9:33:01   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 绑定第三方实体   
 * @ClassName:  CustCustomerInsertBingdingParam   
 * @Description:绑定第三方实体    
 * @author: wangpeng
 * @date:   2018年10月17日 上午9:33:01   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustCustomerInsertBingdingParam implements Serializable {

	
	private static final long serialVersionUID = 9134891978547266595L;

	/**
	 * 账号类型：qq:01 , wx：02，微信小程序：05
	 */
	private String numType;
	
	/**
	 * 第三方账号
	 */
	private String code;
	
	/**
	 * 第三方账号名称
	 */
	private String name;
	
	/**
	 * 绑定的客户编号
	 */
	private String custId;
	/**
	 * 第三方头像
	 */
	private String profilePhoto;
	
	/**
	 * 当前登陆人
	 */
	private String operator;
	
	/**
	 * 添加时间
	 */
	private Date addTime;
	
	/**
	 * 是否有效
	 */
	private String isValid ;
	
	/**
	 * 电话号码
	 */
	private String mobile;
	/**
	 * 短信验证码
	 */
	private String msgCode;
}
