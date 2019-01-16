package com.bashiju.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

public interface HouseTransactionTaxMapper {

	Page<Map<String, Object>> queryHousePropertyListByCity(@Param("cityCode")String cityCode);
	
	List<Map<String, Object>> queryDeedTaxs(@Param("housePropertyId")Integer housePropertyId);
}
