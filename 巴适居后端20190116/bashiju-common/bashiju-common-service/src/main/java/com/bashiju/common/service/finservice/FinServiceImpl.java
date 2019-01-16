package com.bashiju.common.service.finservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bashiju.api.FinServiceApi;
import com.bashiju.api.FormServiceApi;
import com.bashiju.common.mapper.FormMapper;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;

/**
 * 
 * @ClassName:  FinServiceImpl   
 * @Description:财务平台服务实现类
 * @author: liwen
 * @date:   2018年10月30日 上午10:54:20   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */


@SystemServiceLog(sourceType="财务平台服务") 
@Service
 public class FinServiceImpl implements FinServiceApi{
	
	
	@Resource
	private CommonSqlServie commonSqlServie;
	
	/**
	 * 
	 * 保存员工工资配置方案
	 * @Description: (保存员工工资配置方案)   
	 * @param: data里是{userId,userName,companyId,companyName,permissionArea,operatorId,operator}
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveEmployeeWagesConf(Map<Object,Object> data) {
		Long res = commonSqlServie.commonOperationDatabase(data, "sys_employee_wagesconfigurate", false);
		return res;
	}
	
	/**
	 * 
	 * 删除员工工资配置方案 
	 * @Description: (删除员工工资配置方案 )   
	 * @param:  userId（多个用逗号隔开）
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	public boolean delEmployeeWagesConf(String userId) {
		boolean res = commonSqlServie.delData("sys_employee_wagesconfigurate", "userId", userId, false);
		return res;
	}
	
}
