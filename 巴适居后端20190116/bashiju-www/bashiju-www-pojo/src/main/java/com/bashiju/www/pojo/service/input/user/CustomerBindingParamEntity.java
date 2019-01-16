/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerPersonalCentralParamEntity.java   
 * @Package com.bashiju.www.pojo.service.input.user      
 * @author: zuoyuntao     
 * @date:   2018年7月25日 下午5:43:30   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.input.user;

import lombok.Data;
/**
 * C端用户个人中心绑定客源/房源输入实体类
 * @ClassName:CustomerPersonalCentralParamEntity
 * @Description:C端用户个人中心输入实体类
 * @author:zuoyuntao
 * @date:2018年7月25日 下午5:43:30
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerBindingParamEntity {
	/**登陆用户ID**/
	private String custId;
	/**登陆用户电话**/
	private String mobile;
	/**房源用户绑定码**/
	private String bindCode;
}
