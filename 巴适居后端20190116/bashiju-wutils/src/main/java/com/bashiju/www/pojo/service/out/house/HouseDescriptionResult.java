package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源特色输出对象
 * @ClassName:  HouseDescriptionResult   
 * @Description:   房源特色输出对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午6:19:06       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseDescriptionResult implements Serializable{
	
	private static final long serialVersionUID = -7894639278945158610L;
	/**描述类型id**/
	private String typeId;
	/**描述类型名称**/
	private String typeName;
	/**描述内容**/
	private String description;
	
	
}
