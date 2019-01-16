package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源带看经纪人反馈
 * @ClassName:  HouseFeedbackResult   
 * @Description:  房源带看经纪人反馈
 * @author: wangkaifa
 * @date:   2018年7月30日 下午6:05:40       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseFeedbackResult implements Serializable {
	/**带看反馈id**/
	private int id;
	/**带看经纪人姓名**/
	private String agentName;
	/**带看经纪人电话**/
	private String agentPhone;
	/**带看经纪人评价**/
	private String content;
	/**经纪人最后带看时间**/
	private String lastTime;
	/**经纪人带看次数**/
	private int showedCnt;
	/**有用次数**/
	private int usefulCnt;
}
