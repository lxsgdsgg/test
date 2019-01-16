package com.bashiju.customer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.customer.mapper.DemandShowedRecordMapper;
import com.bashiju.customer.service.DemandShowedRecordService;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName:  DemandShowedRecordServiceImpl   
 * @Description:客源带看记录服务
 * @author: wangpeng
 * @date:   2018年12月18日 下午3:41:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="客源带看记录")
public class DemandShowedRecordServiceImpl  extends CommonSqlServie implements DemandShowedRecordService{
	@Autowired
	private DemandShowedRecordMapper  demandShowedRecordMapper;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	@Override
	@SystemServiceLog(operationType="查询客源带记录")
	public Page<Map<String, Object>> queryDemandShowedRecordData(Map<String,Object> paramMap,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		dataAuthHelper.auth(MenuEnum.MENU_102.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String,Object>> pages = demandShowedRecordMapper.queryDemandShowedRecordData(paramMap);
		return pages;
	}
	@Override
	@SystemServiceLog(operationType="逻辑删除客源带看记录")
	public boolean delDemandShowedRecord(String id) {
		boolean result=	this.delData("demand_showedrecord", "id", id, false);
		if(result)
			return true;
		return false;
	}
	@Override
	@SystemServiceLog(operationType="条件查询客源带看记录")
	public Map<String, Object> queryDemandShowedRecordById(String id) {
		Map<String,Object>pages = demandShowedRecordMapper.queryDemandShowedRecordById(id);
		return pages;
	}
	@Override
	@SystemServiceLog(operationType="保存或编辑客源带看记录")
	public boolean saveOrUpdateDemandShowedRecord(Map<String,Object> map) {
		Long result = this.commonOperationDatabase(map, "demand_showedrecord", "id", false);
		if(result>0)
			return true;
		else 
			return false;
	}
	@SystemServiceLog(operationType="条件查询一手房信息")
	@Override
	public Map<String, Object> queryNewHouseInfoById(String newHouseId) {
		Map<String, Object>  map = demandShowedRecordMapper.queryNewHouseInfoById(newHouseId);
		return map;
	}
	
	
}
