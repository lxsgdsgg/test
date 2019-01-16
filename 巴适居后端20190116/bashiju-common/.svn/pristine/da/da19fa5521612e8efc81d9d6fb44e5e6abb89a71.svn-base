/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManageMapper.java   
 * @Package com.bashiju.common.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年10月13日 上午10:47:07   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**   
 * @ClassName:  PhoneSecurityManageMapper   
 * @Description:电话号码隐私管理持久层   
 * @author: yangz
 * @date:   2018年10月13日 上午10:47:07   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PhoneSecurityManageMapper {

	/**
	 * 获取虚拟电话号码
	 * @Description: 获取虚拟电话号码   
	 * @param realPhone 真实的电话号码
	 * @return: String 虚拟电话号码包括分机号，主机号+分机号(40000000-1111),不存在虚拟号码时，则返回null  
	 */ 
	String getVirtualPhone(@Param("realPhone")String realPhone);

	/**     
	 * 获取电话号码隐私信息  
	 * @Description: 获取电话号码隐私信息   
	 * @param realPhone 真实的电话号码
	 * @return: Map<String,Object>      
	 */ 
	Map<String, Object> getPhoneSecurityInfo(@Param("realPhone")String realPhone);

}
