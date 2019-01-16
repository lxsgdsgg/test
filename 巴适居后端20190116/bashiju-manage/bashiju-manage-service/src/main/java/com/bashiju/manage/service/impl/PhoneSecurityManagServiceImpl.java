/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PhoneSecurityManagServiceImpl.java   
 * @Package com.bashiju.manage.service.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年10月18日 上午10:07:20   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.PhoneSecurityManageMapper;
import com.bashiju.manage.service.PhoneSecurityManagService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 
 * 电话号码安全管理服务  
 * @ClassName:  PhoneSecurityManagServiceImpl   
 * @Description:电话号码安全管理服务
 * @author: wangpeng
 * @date:   2018年10月18日 上午10:07:20   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class PhoneSecurityManagServiceImpl implements PhoneSecurityManagService{
	
	@Autowired
	private PhoneSecurityManageMapper phoneSecurityManageMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Override
	public Page<Map<String, Object>> queryPhoneSecurityData(Map<String,Object> paramMap,int page, int limit) {
		PageHelper.startPage(page, limit);
//		dataAuthHelper.auth(MenuEnum.MENU_50.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> map = phoneSecurityManageMapper.queryPhoneSecurityData(paramMap);
		return map;
	}

	
	@Override
	public boolean queryIsBingding(String realNum) {
		long result = phoneSecurityManageMapper.queryIsBingding(realNum);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Map<String, Object> queryAgentInfoByUserId(String userId) {
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("用户编号不能为空");
		Map<String, Object> map = phoneSecurityManageMapper.queryAgentInfoByUserId(userId);
		if(map!=null) {
			return map;
		}
		return null;
	}

}
