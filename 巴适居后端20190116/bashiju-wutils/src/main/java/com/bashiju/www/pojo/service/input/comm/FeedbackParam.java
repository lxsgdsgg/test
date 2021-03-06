package com.bashiju.www.pojo.service.input.comm;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *   网站反馈信息
 * @ClassName:  FeedbackParam   
 * @Description:  网站反馈信息
 * @author: wangkaifa
 * @date:   2018年7月27日 下午2:49:51       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class FeedbackParam implements Serializable{
	
	private static final long serialVersionUID = 6206927937228810295L;
	/**反馈类容**/
	private String content;
	/**反馈人联系方式**/
	private String contact;
	/**反馈时间**/
	private Date addTime;
}
