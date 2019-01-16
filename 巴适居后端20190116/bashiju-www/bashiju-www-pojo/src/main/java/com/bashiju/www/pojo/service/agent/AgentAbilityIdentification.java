/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentAbilityCertification.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月30日 下午3:13:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  AgentAbilityIdentification   
 * @Description:经纪人能力人定实体
 * @author: wangpeng
 * @date:   2018年7月30日 下午3:13:47   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class AgentAbilityIdentification implements Serializable{
	
	
	private static final long serialVersionUID = -8608892199930489651L;

	/**
	 * 考核项目名称
	 */
	private String name;
	
	/**
	 * 考核项目简介
	 */
	private String introduction;
	
}
