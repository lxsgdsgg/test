package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

public interface PrintTemplateMapper {

	Page<Map<String, Object>> queryTemplateList(@Param("companyId")String companyId);
	List<Map<String, Object>> queryTemplateSelectData(@Param("companyId")String companyId);
	List<Map<String, Object>> queryDetailed(@Param("id")Integer id);
	
	List<Map<String, Object>> queryBackground(@Param("companyId")String companyId);
	
	void deleteTemplateItem(@Param("id")Integer id);
	/**
	 * 
		 * 查询房源打印信息
		 * @Description: 查询房源打印信息
		 * @param id 房源id
		 * @return 
		 * Map<String,Object>
	 */
	Map<String, Object> queryHousePrintInfo(@Param("id")Long id);
}
