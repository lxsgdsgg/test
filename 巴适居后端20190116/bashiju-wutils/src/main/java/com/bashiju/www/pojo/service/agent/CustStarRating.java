/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserRating.java   
 * @Package com.bashiju.www.pojo.service.out.comm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月30日 上午9:44:42   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * 客户星级评价实体类  
 * @ClassName:  CustStarRating   
 * @Description:客户星级评价实体类  
 * @author: wangpeng
 * @date:   2018年7月30日 上午9:44:42   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustStarRating  implements Serializable{
	
	private static final long serialVersionUID = 2379266306586804888L;
	/**评价条数**/
	private String starNum;
	/**星级**/
	private  String star;
}
