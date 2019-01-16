/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManageServiceApi.java   
 * @Package com.bashiju.api   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年10月13日 上午10:08:05   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.api;

/**   
 * @ClassName:  PhoneSecurityManageServiceApi   
 * @Description:电话号码隐私管理接口
 * @author: yangz
 * @date:   2018年10月13日 上午10:08:05   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PhoneSecurityManageServiceApi {

	
	/**
	 * 获取虚拟电话号码
	 * @Description: 获取虚拟电话号码   
	 * @param realPhone 真实的电话号码
	 * @return: String 虚拟电话号码包括分机号，主机号+分机号(40000000-1111),不存在虚拟号码时，则返回null
	 */
	public String getVirtualPhone(String realPhone);
	
	/**
	 * 绑定虚拟电话号码
	 * @Description: 绑定虚拟电话号码  
	 * @param realPhone 真实的电话号码
	 * @param companyId 经纪人所属公司编号
	 * @Param companyName 经纪人所属公司名称
	 * @param deptId 部门编号
	 * @param deptName 部门名称
	 * @param agentId 经纪人编号
	 * @param agentName 经纪人名称
	 * @return: boolean 绑定成功放回true,否则返回false
	 */
	public boolean bindViertualPhone(String realPhone, String companyId,String companyName,
			String deptId,String deptName,String agentId,String agentName);
	
	/**
	 * 绑定虚拟电话号码
	 * @Description: 绑定虚拟电话号码
	 * @param realPhone 真实的电话号码
	 * @param companyId 经纪人所属公司编号
	 * @Param companyName 经纪人所属公司名称
	 * @param deptId 部门编号
	 * @param deptName 部门名称
	 * @param agentId 经纪人编号
	 * @param agentName 经纪人名称
	 * @return: String 返回虚拟号码，绑定失败的话返回null 
	 */
	public String bindViertualPhoneReturn(String realPhone, String companyId,String companyName,
			String deptId,String deptName,String agentId,String agentName);
	
	/**
	 * 解绑电话号码(如果该号码不存在隐私号码，则不做任何处理，直接返回true)
	 * @Description: 解绑电话号码(如果该号码不存在隐私号码，则不做任何处理，直接返回true)
	 * @param realPhone 真实的电话号码
	 * @return: boolean 解绑成功返回true,否则返回false
	 */
	public boolean unBindViertualPhone(String realPhone);
}
