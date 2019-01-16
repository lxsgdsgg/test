package com.bashiju.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.DataacquMapper;
import com.bashiju.manage.service.DataacquService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  DataacquServiceImpl   
 * @Description:TODO(数据采集配置具体实现)   
 * @author: liwen
 * @date:   2018年7月3日 上午11:21:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SystemServiceLog(sourceType="数据采集") 
@Service
public class DataacquServiceImpl implements DataacquService{
	
	@Autowired
	 private CommonSqlServie commonSqlServie;
	@Autowired
	DataacquMapper dataacquMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="保存配置模板")
	@Override
	public Long saveAnalysisTemplet(Map<String,Object> data) {
		Long res = null;
		Map<String,Object> mm = (Map)data;
		mm.put("permissionArea", UserThreadLocal.get().get("deptId"));
		mm.put("operatorId", UserThreadLocal.get().get("id"));
		mm.put("operator", UserThreadLocal.get().get("realName"));
		if(data.containsKey("id") && !"".equals(data.get("id")) && data.get("id")!=null) {//修改
			res = commonSqlServie.commonOperationDatabase(mm, "hs_houseCrawlerTemplate","id", false);
		}else {//新增
			mm.remove("id");
			res = commonSqlServie.commonOperationDatabase(mm, "hs_houseCrawlerTemplate", false);
		}
		
		return res;
	}
	
	@SystemServiceLog(operationType="查询配置模板")
	@Override
	public Page<Map<String, Object>> queryAnalysisTemplet(int page,int limit,Map<String,Object> conditions){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_46.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = dataacquMapper.queryAnalysisTemplet(conditions);
		
		ExecutionResult.descFormat("", "查询数据采集配置模板");
		return pageInfo;
	}

		
	@SystemServiceLog(operationType="删除配置模板")
	@Override
	public boolean delAnalysisTemplet(Long id) {
		boolean res = commonSqlServie.delData("hs_houseCrawlerTemplate", "id", id+"", false);
		return res;
	}
	
	public List<Map<String,Object>> queryTempletName(){
		List<Map<String,Object>> res = dataacquMapper.queryTempletName();
		return res;
	}
	
	@SystemServiceLog(operationType="保存采集地址配置")
	@Override
	public Long saveAddressConf(Map<String,Object> data) {
		Long res = null;
		Map<String,Object> mm = (Map)data;
		mm.put("permissionArea", UserThreadLocal.get().get("deptId"));
		mm.put("operatorId", UserThreadLocal.get().get("id"));
		mm.put("operator", UserThreadLocal.get().get("realName"));
		if(data.containsKey("id") && !"".equals(data.get("id")) && data.get("id")!=null) {//修改
			res = commonSqlServie.commonOperationDatabase(mm, "hs_houseCrawlerConfigure","id", false);
		}else {//新增
			mm.remove("id");
			res = commonSqlServie.commonOperationDatabase(mm, "hs_houseCrawlerConfigure", false);
		}
		
		return res;
	}
	@SystemServiceLog(operationType="查询采集地址配置")
	@Override
	public Page<Map<String, Object>> queryAddressConf(int page,int limit,Map<String,Object> conditions){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_45.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = dataacquMapper.queryAddressConf(conditions);
		
		ExecutionResult.descFormat("", "查询数据采集配置模板");
		return pageInfo;
	}
	@SystemServiceLog(operationType="删除采集地址配置")
	@Override
	public boolean delAddressConf(Long id) {
		boolean res = commonSqlServie.delData("hs_houseCrawlerConfigure", "id", id+"", false);
		return res;
	}
	
	@SystemServiceLog(operationType="保存任务配置")
	@Override
	public Long saveJobConf(Map<String,Object> data) {
		Long res = null;
		Map<String,Object> mm = (Map)data;
		if(data.containsKey("id") && !"".equals(data.get("id")) && data.get("id")!=null) {//修改
			res = commonSqlServie.commonOperationDatabase(mm, "job","id", false);
		}else {//新增
			mm.remove("id");
			res = commonSqlServie.commonOperationDatabase(mm, "job", false);
		}
		
		return res;
	}
	@SystemServiceLog(operationType="查询任务配置")
	@Override
	public Page<Map<String, Object>> queryJobConf(int page,int limit){
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_47.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = dataacquMapper.queryJobConf();
		ExecutionResult.descFormat("", "查询数据采集配置模板");
		return pageInfo;
	}
	@SystemServiceLog(operationType="删除任务配置")
	@Override
	public boolean delJobConf(Long id) {
		boolean res = commonSqlServie.delData("job", "id", id+"", false);
		return res;
	}
	
	@SystemServiceLog(operationType="启动、暂停任务配置")
	@Override
	public Long openOrStopJobConf(Long id,String status) {
		Map<String,Object> mm = new HashMap<>();
		mm.put("id", id);
		mm.put("status", status);
		Long res = commonSqlServie.commonOperationDatabase(mm, "job", "id", false);
		return res;
	}
}
