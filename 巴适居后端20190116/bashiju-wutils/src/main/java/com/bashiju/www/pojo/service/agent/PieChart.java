/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PieChart.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月6日 下午5:56:41   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  PieChart   
 * @Description:饼图数据对象   
 * @author: yangz
 * @date:   2018年9月6日 下午5:56:41   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class PieChart implements Serializable {

	private static final long serialVersionUID = 4241595188329859344L;
	
	private String name;
	private String value;

}
