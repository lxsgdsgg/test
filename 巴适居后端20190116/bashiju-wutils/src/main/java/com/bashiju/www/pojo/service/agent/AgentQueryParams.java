/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentQueryParams.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月28日 下午3:10:36   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * 经纪人查询条件实体 
 * @ClassName:  AgentQueryParams   
 * @Description:经纪人查询条件实体   
 * @author: yangz
 * @date:   2018年7月28日 下午3:10:36   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class AgentQueryParams implements Serializable {

	private static final long serialVersionUID = 2077133627326431064L;

	/**
	 * 城市代码
	 */
	private String cityCode;
	
	/**
	 * 行政区域编码
	 */
	private String areaCode;
	
	/**
	 * 片区编号
	 */
	private String regionCode;
	
	/**
	 * 小区编号
	 */
	private String communityCode;
	
	/**
	 * 标签(多个标签之间用英文逗号隔开)
	 */
	private String labels;
	
	/**
	 * 经纪人名称
	 */
	private String agentName;
	
	/**
	 * 当前页
	 */
	private int pageNum;
	
	/**
	 * 每页显示的条数
	 */
	private int pageSize;
	
	/**
	 * 排序编号：0--默认,1--综合评分,2--成交量,3--带看量
	 */
	private int sort;
	
	/**
	 * 升降序：false--升序,true--降序 默认为升序
	 */
	private boolean asc;

	public AgentQueryParams() {
		super();
	}

}
