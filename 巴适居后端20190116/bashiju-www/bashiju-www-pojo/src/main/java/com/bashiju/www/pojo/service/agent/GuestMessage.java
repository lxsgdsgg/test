/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  GuestMessage.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月30日 下午4:34:08   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**   
 * @ClassName:  GuestMessage   
 * @Description:用户给经纪人留言实体（获取页面上要保存的数据）
 * @author: wangpeng
 * @date:   2018年7月30日 下午4:34:08   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class GuestMessage  implements Serializable{
	
	private static final long serialVersionUID = -2471606012969888843L;
	
	/**用户编号**/
	private String userCode;
	/**经纪人详情编号**/
	private  String agentCode;
	/**经纪人所在部门id**/
	private  String deptCode;
	/**经纪人所在部门名称**/
	private  String deptName;
	/**留言类型id**/
	private  String messageTypeCode;
	/**留言类型**/
	private  String messageType;
	/**真是姓名**/
	private  String realName;
	/**电话号码**/
	private  String mobile;
	/**留言内容**/
	private  String content;

	
}
