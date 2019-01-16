package com.bashiju.common.service.customerservice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.CustomerServiceApi;
import com.bashiju.common.mapper.CustomerMapper;

@Service
public class CustomerServiceApiImpl implements CustomerServiceApi {
	@Autowired
	CustomerMapper customerMapper;
	
	@Override
	public List<Map<String, Object>> queryAgentsByDemandId(String demandId){
		//经纪人信息
		LinkedList<Map<String,Object>> relateagentInfo = customerMapper.queryRelateagentInfo(demandId);
		return relateagentInfo;
	}
}
