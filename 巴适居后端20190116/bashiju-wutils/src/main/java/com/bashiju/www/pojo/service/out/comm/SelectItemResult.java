package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   动态下拉项输出对象
 * @ClassName:  SelectItemResult   
 * @Description:   动态下拉项输出对象
 * @author: wangkaifa
 * @date:   2018年7月23日 下午6:41:16       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class SelectItemResult implements Serializable {
	
	private static final long serialVersionUID = -5633763423083448547L;
	/**下拉项名称**/
	private String name;
	/**下拉项值**/
	private Long value;
	/**控件编号**/
	private String widgetCode;
	
}
