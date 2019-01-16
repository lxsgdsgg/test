package com.bashiju.manage.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.LoginRecordMapper;
import com.bashiju.manage.service.LoginRecordService;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  Login_record_ServiceImpl   
 * @Description:登陆日志服务
 * @author: wangpeng
 * @date:   2018年5月14日 上午11:56:18   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="登陆日志管理")
public class LoginRecordServiceImpl  implements LoginRecordService{
	@Autowired
	private LoginRecordMapper loginRecordMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	/**
	 * 
	 * <p>Title: queryLoginRecord</p>   
	 * <p>Description:查询登陆日志信息 </p>   
	 * @param paramMap 查询参数
	 * @param pageNum 当前页数
	 * @param pageSize 每页总条数
	 * @return   
	 * @see com.bashiju.manage.service.LoginRecordService#queryLoginRecord(java.util.Map, int, int)
	 */
	@Override
	@SystemServiceLog(operationType="查询登陆日志")
	public Page<Map<String, Object>> queryLoginRecord(Map<String,Object> paramMap,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		dataAuthHelper.auth(MenuEnum.MENU_78.getCode(), UserThreadLocal.get().get("id").toString());
		Page<Map<String,Object>> page = loginRecordMapper.queryLoginRecord(paramMap);
		return page;
	}
}
