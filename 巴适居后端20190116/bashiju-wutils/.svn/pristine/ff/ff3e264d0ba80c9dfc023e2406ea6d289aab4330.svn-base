/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  QueryCondition.java   
 * @Package com.bashiju.www.pojo.comm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月16日 下午5:29:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.comm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**   
 * 查询条件实体
 * @ClassName:  QueryCondition   
 * @Description:查询条件实体   
 * @author: yangz
 * @date:   2018年8月16日 下午5:29:02   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class QueryCondition implements Serializable {

	private static final long serialVersionUID = -542991900375717786L;

	/**
	 * 条件编号
	 */
	private String conditionCode;
	
	/**
	 * 条件描述
	 */
	private String conditionName;
	
	/**
	 * 条件详情
	 */
	private List<QueryConditionDetail> datas; 
	
	@Data
	public class QueryConditionDetail implements Serializable{
		
		private static final long serialVersionUID = -7783805778156265675L;

		/**
		 * 编号
		 */
		private String code;
		
		/**
		 * 名称
		 */
		private String name;
	}
}
