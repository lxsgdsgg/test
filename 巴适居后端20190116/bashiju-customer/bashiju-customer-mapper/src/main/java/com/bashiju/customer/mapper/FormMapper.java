package com.bashiju.customer.mapper;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  FormMapper   
 * @Description:关于动态表单的mapper接口
 * @author: liwen
 * @date:   2018年4月9日 上午9:46:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface FormMapper {
	
	public LinkedList<Map> queryRequiredType();
	public LinkedList<Map> queryFieldRequired(@Param("cityCode") String cityCode);
	public int queryWidgetByCode(@Param("widgetCode") String widgetCode);
	public int queryWidgetByCodeAndCity(@Param("widgetCode") String widgetCode,@Param("cityCode") String cityCode);
	public LinkedList<Map> queryRegionType(@Param("cityCode") String cityCode,@Param("type") String type);
	public Page<Map<String, Object>> querySelectType();
	public LinkedList<Map> queryWidgetType(@Param("type") String type);
	public LinkedList<Map> queryWidgetValue(@Param("tablename") String tablename,@Param("widgetCode") String widgetCode);
	public LinkedList<Map> querySqlValue(@Param("sql") String sql);
	public void delWidgetValue(@Param("widgetCode") String widgetCode);
	
	
	public Page<Map<String, Object>> queryDynamicFormList(@Param("cityCode") String cityCode);
	
	public Page<Map<String, Object>> queryDynamicFormFieldList(@Param("formId") long formId,@Param("cityCode") String cityCode);
	
	public int queryCustomFieldCount(@Param("id") long id);
	
	public void addcolumn(@Param("table") String table,@Param("column") String column);
	
	public Page<Map<String, Object>> queryCompanyDynamicFormList(@Param("cityCode") String cityCode);
	
	public Page<Map<String, Object>> queryCompanyDynamicFormFieldList(@Param("formId") long formId,@Param("companyId") String companyId);
	
	public List<Map<String, Object>> queryCompanyDynamicFormFieldSelect(@Param("formId") long formId,@Param("companyId") String companyId);
	
	public List<Map<String, Object>> queryCityDynamicFormFieldSelect(@Param("formId") long formId,@Param("cityCode") String cityCode);
	
	//调用接口
	public Map queryTableInfo(@Param("formName") String formName);
	public LinkedList<Map> queryCityCustomField(@Param("formId") Long formId,@Param("cityCode") String cityCode);
	public LinkedList<Map> queryCustomField(@Param("formId") Long formId,@Param("cityCode") String cityCode,@Param("companyId") String companyId);	
	public LinkedList<Map> queryWidgetCode(Map param);
	public LinkedList<Map> querySelectValue(@Param("widgetCode") String widgetCode);
	public LinkedList<Map> queryFormField(@Param("formName") String formName);
	public Map queryFormValue(@Param("tableName") String tableName,@Param("sourceId") String sourceId);
	public LinkedList<Map> queryRegionValue(@Param("widgetCode") String widgetCode);
	
	public List<String> queryTableFieldInfo(@Param("formName") String formName,@Param("hiddenField") String hiddenField);
	public Page<Map<String, Object>> queryTableDatas(@Param("columns") String columns,@Param("tableName") String tableName,@Param("conditions") List<Map<String, Object>> conditions);
	public Map queryTableHiddenFields(@Param("userId") long userId,@Param("formName") String formName);
	/**
	 * 
		 * 查询部门用户
		 * @Description: 查询部门用户 
		 * @param areaCode 城市编号多个城市逗号分隔，可为null
		 * @param companyId 公司id，可为null
		 * @return 
		 * LinkedList<Map>
	 */
	public LinkedList<Map> queryDept(@Param("companyId") String companyId);
	/**
	 * 
	 * @Title: searchCommunity   
	 * @Description: TODO(查询登录用户所在城市的片区、小区)   
	 * @param: @param cityCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> searchCommunity(@Param("cityCode")String cityCode);
}
