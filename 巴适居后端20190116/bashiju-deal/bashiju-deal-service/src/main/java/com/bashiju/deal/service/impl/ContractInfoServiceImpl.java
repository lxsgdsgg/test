package com.bashiju.deal.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.deal.mapper.ContractInfoMapper;
import com.bashiju.deal.service.ContractInfoService;
import com.bashiju.enums.MenuEnum;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 *  合同信息管理
 * @ClassName:  ContractInfoServiceImpl   
 * @Description:    合同信息管理
 * @author: wangkaifa
 * @date:   2018年10月17日 下午6:35:50       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ContractInfoServiceImpl implements ContractInfoService {
	@Autowired
	CommonSqlServie commonSqlServie;
	@Autowired
	ContractInfoMapper contractInfoMapper;
	@Autowired
	DataAuthHelper dataAuthHelper;
	
	@Override
	public Page<Map<String, Object>> queryContractInfoList(String condition,int page, int limit) {
		PageHelper.startPage(page, limit);
		dataAuthHelper.auth(MenuEnum.MENU_113.getCode(),UserThreadLocal.get().get("id").toString());
		Map<String, Object> param=null;
		if (StringUtils.isEmpty(condition)) {
			param=new HashMap<>();
		}else {
			param=JSON.parseObject(condition, Map.class);
		}
		Page<Map<String, Object>> result=contractInfoMapper.queryContractInfoList(param);
		return result;
	}

	@Override
	public Map<String, Object> queryDetailed(Long id) {
		Map<String, Object> result=contractInfoMapper.queryDetailed(id);
		return result;
	}

	@Override
	public Boolean updateStatus(Long id,Long shhid,String agreementType, String status, String approvalResult) {
		Map<String,Object> param=new HashMap<>();
		param.put("id", id);
		param.put("status", status);
		param.put("approvalResult", approvalResult);
		param.put("approvalTime", new Date());
		param.put("approver", UserThreadLocal.get().get("id"));
		commonSqlServie.commonOperationDatabase(param, "oa_agreement", "id", false);		
		if ("01".equals(status)) {
			param=new HashMap<>();
			param.put("id", shhid);
			if ("01".equals(agreementType)) {
				param.put("contractStatus", 1);
			}
			if ("02".equals(agreementType)) {
				param.put("contractStatus", 2);
			}
			commonSqlServie.commonOperationDatabase(param, "hs_secondHandHouse", "id", false);
		}
		
		return true;
	}

	@Override
	public List<Map<String, Object>> queryPayerInfo(Long houseId, String customerId) {
		List<Map<String, Object>> result = contractInfoMapper.queryPayerInfo(houseId, customerId);
		return result;
	}

	@Override
	public int queryCountByHouseId(Long houseId) {
		int result=contractInfoMapper.queryCountByHouseId(houseId);
		return result;
	}


}
