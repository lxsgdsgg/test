package com.bashiju.common.service.housingservice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.api.ErHousingServiceApi;
import com.bashiju.common.mapper.ErHousingMapper;

@Service
public class ErHousingServiceApiImpl implements ErHousingServiceApi{
	@Autowired
	ErHousingMapper erHousingMapper;
	
	@Override
	public List<Map<String, Object>> queryAgentsByHouseId(Long houseId) {
		LinkedList<Map<String,Object>> relateagentInfo = erHousingMapper.queryRelateagentInfo(houseId);
		return relateagentInfo;
	}

	@Override
	public boolean queryCountPhoneBlack(String phone) {
		Integer count=erHousingMapper.queryCountPhoneBlack(phone);
		return count>0;
	}
}
