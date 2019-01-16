/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManagController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月18日 上午10:11:19   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.api.PhoneSecurityManageServiceApi;
import com.bashiju.manage.service.PhoneSecurityManagService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.service.BaseController;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

/**  
 * 电话号码安全管理服务控制器
 * @ClassName:  PhoneSecurityManagController   
 * @Description:电话号码安全管理控制器
 * @author: wangpeng
 * @date:   2018年10月18日 上午10:11:19   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="phoneSecurityManag")
public class PhoneSecurityManagController extends BaseController {
	
	@Autowired
	private PhoneSecurityManagService  phoneSecurityManagService;
	
	@Autowired
	private PhoneSecurityManageServiceApi phoneSecurityManageServiceApi;
	/**
	 * 查询电话号码
	 * @Title: queryPhoneSecurityData   
	 * @Description: 查询电话号码
	 * @param page 当前页数
	 * @param limit 每页总条数
	 * @param realNum 真实电话
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryPhoneSecurityData")
	@ResponseBody
	public Map<String,Object> queryPhoneSecurityData(String realNum,String companyId,String agentId,String agentName,String deptId,int page,int limit){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("realNum", realNum);
		paramMap.put("companyId", companyId);
		paramMap.put("agentId", agentId);
		paramMap.put("agentName", agentName);
		paramMap.put("deptId", deptId);
		Page<Map<String,Object>> pages = phoneSecurityManagService.queryPhoneSecurityData(paramMap,page, limit);
		Map<String,Object> map = getPageResult(pages);
		return map;
	}
	/**
	 * 
	 * @Title: bindViertualPhone   
	 * @Description: 绑定虚拟电话
	 * @param realPhone  真实的电话号码
	 * @param permissionArea  操作人所属部门编号
	 * @param operatorId 操作人编号
	 * @return: boolean 绑定成功返回true,否则返回false
	 */
	@RequestMapping(value="bindViertualPhone")
	@ResponseBody
	public BashijuResult bindViertualPhone(String realPhone,String userId) {
		if(StringUtils.isEmpty(realPhone))
			throw new BusinessException("电话号码不能为空");
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("用户编号不能为空");
		Map<String,Object> map = phoneSecurityManagService.queryAgentInfoByUserId(userId);
//		if(map == null)
//			throw new BusinessException("该用户不是经纪人");
		boolean result = phoneSecurityManageServiceApi.bindViertualPhone(
				realPhone,map.get("companyId").toString(),map.get("companyName").toString(), map.get("deptId").toString(), 
				map.get("deptName").toString(),map.get("agentId").toString(),map.get("agentName").toString());
		if(result) {
			return BashijuResult.ok();
		}
		throw new BusinessException("绑定异常");
	}
	
	/**
	 * 解绑电话号码
	 * @Title: unBindViertualPhone   
	 * @Description: 解绑电话号码
	 * @param realPhone 真实的电话号码
	 * @return: BashijuResult
	 */
	@RequestMapping(value="unBindViertualPhone")
	@ResponseBody
	public BashijuResult unBindViertualPhone(String realPhone) {
		boolean result = phoneSecurityManageServiceApi.unBindViertualPhone(realPhone);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException("系统异常");
	}
}
