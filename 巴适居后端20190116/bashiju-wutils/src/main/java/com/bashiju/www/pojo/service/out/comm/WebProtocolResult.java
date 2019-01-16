package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *  网站介绍、用户协议类
 * @ClassName:  WebProtocolResult   
 * @Description:  网站介绍、用户协议类
 * @author: wangkaifa
 * @date:   2018年8月2日 上午11:47:31       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class WebProtocolResult implements Serializable {
	
	private static final long serialVersionUID = -3581369709493734414L;
	/**标题**/
	private String title;
	/**内容**/
	private String content;
}
