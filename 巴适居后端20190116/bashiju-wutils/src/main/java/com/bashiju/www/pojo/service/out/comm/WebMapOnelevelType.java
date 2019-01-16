package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   网站地图一级分类对象
 * @ClassName:  webMapOnelevelType   
 * @Description:  网站地图一级分类对象
 * @author: liwen
 * @date:   2018年8月8日 上午10:00:01       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class WebMapOnelevelType implements Serializable {
	
	private static final long serialVersionUID = 4781065474922717335L;
	/**名称**/
	private String name;
	/**id**/
	private String id;
	
}
