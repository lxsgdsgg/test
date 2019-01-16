package com.bashiju.manage.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.ErrorLogMapper;
import com.bashiju.manage.service.ErrorLogService;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  Error_logServiceImpl   
 * @Description:错误日志服务
 * @author: wangpeng
 * @date:   2018年5月14日 下午12:04:16   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="错误信息管理")
public class ErrorLogServiceImpl implements ErrorLogService {
	@Autowired
	private ErrorLogMapper errorLogMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@Override
	@SystemServiceLog(operationType="查询错误信息")
	public Page<Map<String, Object>> queryErrorLog(Map<String,Object> paramMap, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		dataAuthHelper.auth(MenuEnum.MENU_37.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> page = errorLogMapper.queryErrorLog(paramMap);
		return page;
	}
	

}
