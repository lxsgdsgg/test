/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  NewHousePriceDetailResult.java   
 * @Package com.bashiju.www.pojo.service.out.newhouse   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月21日 上午11:10:16   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.newhouse;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  NewHousePriceDetailResult   
 * @Description:楼盘价格详细实体
 * @author: wangpeng
 * @date:   2018年8月21日 上午11:10:16   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class NewHousePriceDetailResult  implements Serializable{
	
	private static final long serialVersionUID = 7120817455960997381L;
	/**价格（单位元）**/
	private String price;
	/**价格描述**/
	private String remark; 

}
