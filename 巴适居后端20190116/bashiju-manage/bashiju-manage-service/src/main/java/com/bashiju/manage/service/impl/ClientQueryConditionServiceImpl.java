package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bashiju.manage.mapper.ClientQueryConditionMapper;
import com.bashiju.manage.service.IClientQueryConditionService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ClientQueryConditionServiceImpl implements IClientQueryConditionService {
	@Autowired
	private CommonSqlServie commonSqlServie;
	@Autowired
	private ClientQueryConditionMapper clientQueryCoditionMapper;
	
	@Override
	public Page<Map<String, Object>> queryConditionList(Integer type,int page,int limit) {
		Map<String, Object> map=new HashMap<>();
		map.put("type", type);
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> results=clientQueryCoditionMapper.queryConditionList(map);
		return results;
	}

	@Override
	public Integer addQueryCondition(String jsonData) {
		if (StringUtils.isNotEmpty(jsonData)) {
			Map user=UserThreadLocal.get();
			Map data=JSON.parseObject(jsonData, Map.class);
			data.put("permissionArea", user.get("deptId"));
			data.put("operatorId", user.get("id"));
			Long id=commonSqlServie.commonOperationDatabase(data, "sys_client_condition_mainManage", false);
			return id.intValue();
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public Integer updateQueryCondition(String jsonData) {
		if (StringUtils.isNotEmpty(jsonData)) {
			Map data=JSON.parseObject(jsonData, Map.class);
			if (data.get("id")!=null) {
				Long result=commonSqlServie.commonOperationDatabase(data, "sys_client_condition_mainManage","id", false);
				data.put("conditionId", data.get("id"));
				data.remove("id");
				result=commonSqlServie.commonOperationDatabase(data, "sys_client_conditionManage","conditionId", false);
				return result.intValue();
			}						
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public Integer deleteQueryCondition(Integer id) {
		if (id!=null) {
			commonSqlServie.delData("sys_client_condition_mainManage", "id", id.toString(), false);
			commonSqlServie.delData("sys_client_conditionManage", "conditionId", id.toString(), false);
			return 1;
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public List<Map<String, Object>> queryConditionTree() {
		Map<String, Object> map=new HashMap<>();
		map.put("type", null);
		Page<Map<String, Object>> results=clientQueryCoditionMapper.queryConditionList(map);
		List<Map<String, Object>> list=new ArrayList<>();
		for(int i=0;i<4;i++) {
			String label="";
			switch (i) {
			case 0:
				label="二手房";
				break;
			case 1:
				label="出租";
				break;
			case 2:
				label="新房";
				break;
			case 3:
				label="小区";
				break;	
			}
			Map<String, Object> item=new HashMap<>();
			item.put("id", "t-"+i);
			item.put("conditionName", label);
			item.put("children", new ArrayList<Map<String, Object>>());
			list.add(item);
		}
		for (Map<String, Object> item : results) {
			Integer type=Integer.parseInt(item.get("type").toString());
			List<Map<String, Object>> children= (List<Map<String, Object>>) list.get(type).get("children");
			children.add(item);
			list.get(type).put("children", children);
		}
		return list;
	}

	@Override
	public Page<Map<String, Object>> queryCityConditionList(String cityCode, Integer type,int page,int limit) {
		if (StringUtils.isNotEmpty(cityCode)) {
			Map<String, Object> map=new HashMap<>();
			map.put("cityCode", cityCode);
			map.put("type", type);
			PageHelper.startPage(page, limit);
			Page<Map<String, Object>> results=clientQueryCoditionMapper.queryCityConditionList(map);
			return results;
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public void saveCityCondition(String cityCode,String jsonData) {
		if (StringUtils.isNotEmpty(cityCode)&&StringUtils.isNotEmpty(jsonData)) {
			commonSqlServie.delData("sys_client_conditionManage", "cityCode", cityCode, false);
			List<Map> datas=JSON.parseArray(jsonData, Map.class);
			if (datas.size()>0) {
				Map user=UserThreadLocal.get();
				String fields="conditionId,type,cityCode,conditionCode,conditionName,valueCodes,valueNames,permissionArea,operatorId";
				List<String> values=new ArrayList<>();
				for (Map item : datas) {
					if (item.get("type")!=null) {
						StringBuffer buffer=new StringBuffer();
						buffer.append(item.get("id"));
						buffer.append(",");
						buffer.append(item.get("type"));
						buffer.append(",'");
						buffer.append(cityCode);
						buffer.append("','");
						buffer.append(item.get("conditionCode"));
						buffer.append("','");
						buffer.append(item.get("conditionName"));
						buffer.append("','");
						buffer.append(item.get("valueCodes"));
						buffer.append("','");
						buffer.append(item.get("valueNames"));
						buffer.append("','");
						buffer.append(user.get("deptId"));
						buffer.append("',");
						buffer.append(user.get("id"));
						values.add(buffer.toString());
					}										
				}
				if (values.size()>0) {
					commonSqlServie.batchAdd(fields, "sys_client_conditionManage", values, false);
				}				
			}
		}else {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
		
	}

	@Override
	public Page<Map<String, Object>> queryCityAvgPriceList(String cityCode, Integer type,int page,int limit) {
		if (StringUtils.isNotEmpty(cityCode)) {
			Map<String, Object> map=new HashMap<>();
			map.put("cityCode", cityCode);
			map.put("type", type);
			PageHelper.startPage(page, limit);
			Page<Map<String, Object>> results=clientQueryCoditionMapper.queryCityAvgPriceList(map);
			return results;
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public Integer addCityAvgPrice(String jsonData) {
		if (StringUtils.isNotEmpty(jsonData)) {
			Map user=UserThreadLocal.get();
			Map data=JSON.parseObject(jsonData, Map.class);
			data.put("permissionArea", user.get("deptId"));
			data.put("operatorId", user.get("id"));
			Long id=commonSqlServie.commonOperationDatabase(data, "sys_cityAvgPriceLabel", false);
			return id.intValue();
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public Integer updateCityAvgPrice(String jsonData) {
		if (StringUtils.isNotEmpty(jsonData)) {
			Map data=JSON.parseObject(jsonData, Map.class);
			if (data.get("id")!=null) {
				Long result=commonSqlServie.commonOperationDatabase(data, "sys_cityAvgPriceLabel","id", false);
				return result.intValue();
			}						
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public Integer deleteCityAvgPrice(Integer id) {
		if (id!=null) {
			commonSqlServie.delData("sys_cityAvgPriceLabel", "id", id.toString(), false);
			return 1;
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}

	@Override
	public List<Integer> queryCityConditionIds(String cityCode) {
		List<Integer> result=clientQueryCoditionMapper.queryCityConditionIds(cityCode);
		return result;
	}

}
