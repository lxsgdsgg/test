/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FootPrintToSaveEntity.java   
 * @Package com.bashiju.www.pojo.service.FootPrint   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月11日 下午6:57:36   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.FootPrint;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**   
 * @ClassName:  FootPrintToSaveEntity   
 * @Description:添加我的足迹实体
 * @author: wangpeng
 * @date:   2018年8月11日 下午6:57:36   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class FootPrintToSaveEntity implements Serializable{
	/**用户id**/
	private String cust_id;
	/**房源类型**/
	private String type;
	/**房源id**/
	private String sourceId;
	/**浏览时间**/
	private Date browsingTime;
	/**是否有效**/
	private String isValid;
}
