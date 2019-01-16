package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   不同公司同套房源连接信息
 * @ClassName:  HouseSameLinkResult   
 * @Description:  不同公司同套房源连接信息
 * @author: wangkaifa
 * @date:   2018年7月27日 上午10:27:19       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseSameLinkResult implements Serializable{
	/**房源id**/
	private long id;
	/**小区名称**/
	private String community;
	/**房源公司名称**/
	private String company;
	/**房源挂牌时间**/
	private String openPlateDate;
	/**经纪人id**/
	private long agentId;
	/**经纪人姓名**/
	private String name;
	/**经纪人头像**/
	private String profilePhoto;
	/**经纪人分机**/
	private String telPhone;
}
