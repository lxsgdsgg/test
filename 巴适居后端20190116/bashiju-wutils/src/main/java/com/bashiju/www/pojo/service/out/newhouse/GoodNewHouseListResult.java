package com.bashiju.www.pojo.service.out.newhouse;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   首页推荐新房对象
 * @ClassName:  GoodNewHouseListResult   
 * @Description:  首页推荐新房对象
 * @author: wangkaifa
 * @date:   2018年8月21日 下午12:09:35       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class GoodNewHouseListResult implements Serializable {

	private static final long serialVersionUID = 5180661860635294453L;
	/**新房楼盘id**/
	private Integer id;
	/**新房楼盘名称**/
	private String name;
	/**新房楼盘均价**/
	private String avgPrice;
	/**新房楼盘地址**/
	private String address;
	/**新房楼盘封面图地址**/
	private String imageUrl;
}
