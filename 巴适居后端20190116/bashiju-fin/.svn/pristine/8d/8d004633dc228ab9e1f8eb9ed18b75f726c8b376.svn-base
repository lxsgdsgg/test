package com.bashiju.fin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.CommissionStatusEnum;
import com.bashiju.enums.MenuEnum;
import com.bashiju.fin.mapper.FinMapper;
import com.bashiju.fin.service.FinService;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@SystemServiceLog(sourceType="财务") 
@Service
public class FinServiceImpl implements FinService{
	
	
	/**元转分 **/
	private static final int YUAN=100;
	
	@Autowired
	FinMapper finMapper;
	@Autowired
	private CommonSqlServie commonSqlServie;
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	@SystemServiceLog(operationType="查佣金列表")
	@Override
	public Page<Map<String, Object>> queryCommissionList(Map<String,Object> conditions,int page,int limit){
		PageHelper.startPage(page, limit);
		if("0".equals(conditions.get("moneyType").toString())){
			dataAuthHelper.auth(MenuEnum.MENU_118.getCode(), UserThreadLocal.get().get("id").toString());
		}else {
			dataAuthHelper.auth(MenuEnum.MENU_117.getCode(), UserThreadLocal.get().get("id").toString());
		}
		Page<Map<String, Object>> list = finMapper.queryCommissionList(conditions);
		return list;
	}

	@Override
	public Page<Map<String, Object>> queryPayRecord(Map<String,Object> conditions, int page, int limit) {
		PageHelper.startPage(page, limit);
		if("0".equals(conditions.get("transactionType").toString())){
			dataAuthHelper.auth(MenuEnum.MENU_119.getCode(), UserThreadLocal.get().get("id").toString());
		}else {
			dataAuthHelper.auth(MenuEnum.MENU_120.getCode(), UserThreadLocal.get().get("id").toString());
		}
		Page<Map<String, Object>> pageInfo = finMapper.queryPayRecord(conditions);
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="更改")
	@Override
	public Long updateInfo(Long id,String type,String param) {
		Long res = 0l;
		if("sureStatus".equals(type)) {//确认状态
			Map<String,Object> mm = new HashMap<>();
			mm.put("id", id);
			mm.put("status", CommissionStatusEnum.CONFIRMED.getCode());
			mm.put("sureTime", new Date());
			res = commonSqlServie.commonOperationDatabase(mm, "deal_commissionRecord", "id", false);
		}else if("reject".equals(type)) {//驳回
			Map<String,Object> mm = new HashMap<>();
			mm.put("id", id);
			mm.put("status", CommissionStatusEnum.REJECT.getCode());
			mm.put("reason", param);
			res = commonSqlServie.commonOperationDatabase(mm, "deal_commissionRecord", "id", false);
		}else if("updateSuretime".equals(type)) {//修改确认时间
			Map<String,Object> mm = new HashMap<>();
			mm.put("id", id);
			mm.put("sureTime", param);
			res = commonSqlServie.commonOperationDatabase(mm, "deal_commissionRecord", "id", false);
		}
		return res;
	}

	@Override
	public boolean updatePayStatus(Long id, String status, String reason) {
		Map<String, Object> user=UserThreadLocal.get();
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("examineStatus", status);
		if (StringUtils.isNotEmpty(reason)) {
			map.put("reason", reason);
		}
		map.put("auditorId", user.get("id"));
		map.put("auditor", user.get("realName"));
		map.put("auditTime", new Date());
		commonSqlServie.commonOperationDatabase(map, "deal_payRecord", "id", false);
		return true;
	}

	
	@SystemServiceLog(operationType="保存金融分成")
	@Override
	public boolean saveFinanceDivide(Map<String,Object> data) {
		Map<String, Object> user = UserThreadLocal.get();
		Long userid = Long.parseLong(user.get("id").toString());
		String companyId=user.get("companyId").toString();
		String companyName  = user.get("comName").toString();
		String deptId = user.get("deptId").toString();
		//删除原来分成
		Long commissionId = Long.parseLong(data.get("commissionId").toString());
		commonSqlServie.delData("deal_financeDividenInfo", "commissionId", commissionId+"", false);
		List<Map<String,Object>> divideData = (List)data.get("divideData");
		for(Map<String,Object> divide:divideData) {
			divide.put("companyId", companyId);
			divide.put("companyName", companyName);
			divide.put("permissionArea", deptId);
			divide.put("operatorId", userid);
			//divide.put("operator", username);
			divide.put("estimateProfit", convertFen(divide.get("estimateProfit").toString(),YUAN));
			divide.put("actualProfit", convertFen(divide.get("actualProfit").toString(),YUAN));
		}
		boolean res = commonSqlServie.batchCommonOperationDatabase(divideData, "deal_financeDividenInfo", false);
		return res;
	}
	
	@SystemServiceLog(operationType="查询金融分成")
	@Override
	public List<Map<String,Object>> queryFinanceDivide(Long commissionId){
		return finMapper.queryFinanceDivide(commissionId);
	}
	
	@SystemServiceLog(operationType="查询日志")
	@Override
	public Page<Map<String, Object>> auxQueryLog(Map<String,Object> conditions, int page, int limit){
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo = null;
		if("wages".equals(conditions.get("type"))){//薪酬日志
			pageInfo = finMapper.queryWagesLog(conditions);
		}else if("config".equals(conditions.get("type"))) {//配置日志
			pageInfo = finMapper.queryConfigLog(conditions);
		}else if("commission".equals(conditions.get("type"))) {//佣金日志
			pageInfo = finMapper.queryCommissionLog(conditions);
		}else if("finance".equals(conditions.get("type"))) {//金融日志
			pageInfo = finMapper.queryFinanceLog(conditions);
		}else if("receiveRecord".equals(conditions.get("type"))) {//代收日志
			pageInfo = finMapper.queryReceiveRecordLog(conditions);
		}else if("payRecord".equals(conditions.get("type"))) {//代付日志
			pageInfo = finMapper.queryPayRecordLog(conditions);
		}
		
		return pageInfo;
	}
	
	
	/**
	 * 
		 * 将万元或元转换成分
		 * @Description: 将万元或元转换成分
		 * @param value 待转换的值
		 * @param unit 万元或元换算单位
		 * @return  整数
		 * Integer
	 */
	private Integer convertFen(String value,Integer unit) {
		Double d=Double.parseDouble(value);
		d=d*unit;
		d=Math.floor(d);
		return d.intValue();
	}

}
