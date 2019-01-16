package com.bashiju.www.pojo.service.out.house;


import java.io.Serializable;

import lombok.Data;
/**
 * 
 *   带看记录返回对象
 * @ClassName:  ShowedRecordResult   
 * @Description:  带看记录返回对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午6:41:49       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class ShowedRecordResult implements Serializable{
	
	private static final long serialVersionUID = -7024727511517339684L;
	/**经纪人id**/
	private int agentId;
	/**经纪人头像**/
	private String profilePhoto;
	/**带看时间**/
	private String showedTime;
	/**带看经纪人**/
	private String leader;
	/**经纪人电话**/
	private String phone;
	/**本房带看总次数**/
	private int showedCount;
}
