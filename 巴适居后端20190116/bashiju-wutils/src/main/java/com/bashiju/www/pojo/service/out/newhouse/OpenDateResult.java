/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  OpenDateResult.java   
 * @Package com.bashiju.www.pojo.service.out.house   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月20日 下午3:17:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.newhouse;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  OpenDateResult   
 * @Description:楼盘开盘时间  
 * @author: wangpeng
 * @date:   2018年8月20日 下午3:17:58   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class OpenDateResult  implements Serializable{
	
	private static final long serialVersionUID = 4497516007242277685L;
	/**开盘时间**/
	private String openDate;	
	/**备注信息**/
	private String remark;
}
