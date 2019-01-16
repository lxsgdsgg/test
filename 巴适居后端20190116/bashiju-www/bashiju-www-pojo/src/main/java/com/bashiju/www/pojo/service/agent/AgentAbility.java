/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentaBility.java   
 * @Package com.bashiju.www.pojo.service.agentability   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月28日 下午5:54:50   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  AgentAbility   
 * @Description:经纪人能力概览实体
 * @author: wangpeng
 * @date:   2018年7月28日 下午5:54:50   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class AgentAbility implements Serializable {
	
	private static final long serialVersionUID = 4049280550939827951L;
	/**
	 * 所属区
	 */
	private String community ;
	/**
	 * 所属大区
	 */
	private String areaName;
	
	/**
	 * 所属商圈
	 */
	private String region;
	
	/**
	 * 成交量
	 */
	private String dealCnt; 
	
	/**
	 * 成交排名
	 */
	private String sortNo;
}
