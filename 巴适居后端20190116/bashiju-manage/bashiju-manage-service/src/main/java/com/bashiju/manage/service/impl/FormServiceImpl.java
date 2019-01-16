package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.manage.mapper.FormMapper;
import com.bashiju.manage.service.FormService;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName:  FormServiceImpl   
 * @Description:动态表单实现类
 * @author: liwen
 * @date:   2018年4月9日 上午9:54:20   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */


@SystemServiceLog(sourceType="动态表单") 
@Service
 public class FormServiceImpl implements FormService{
	
	@Resource
	private FormMapper formmapper;
	@Resource
	private CommonSqlServie commonSqlServie;
	
	@Autowired
	private DataAuthHelper dataAuthHelper;
	
	//****  必填项设置相关  ****//
	@SystemServiceLog(operationType="查询")
	@Override
	public LinkedList<Map> queryRequiredType(){
		LinkedList<Map> types = formmapper.queryRequiredType();
		ExecutionResult.descFormat(types.size()+"", "查询必填项类型");
		return types;
	}
	
	
	
	@SystemServiceLog(operationType="查询")
	@Override
	public Map queryFieldRequired() {
		LinkedList<Map> fields = formmapper.queryFieldRequired("530100");
		Map<String,Object> result = new HashMap<>();
		
		
		for(Map m : fields) {
			String type = m.get("useType").toString();
			if(result.containsKey(type)){
				List fieldList =(List)result.get(type);
				fieldList.add(m);
				
			}else{
				List l = new ArrayList<>();
				l.add(m);
				result.put(m.get("useType").toString(), l);
			}
		}
		ExecutionResult.descFormat(fields.size()+"", "查询必填项字段");
		return result;
	}
	
	
	@SystemServiceLog(operationType="保存")
	@Override
	public boolean saveFieldRequired(String commonfield) {
		boolean res = false;
		
		//公共字段
		res = saveRequired("11",commonfield,"dynamicform_common_field");
		return res;
	}
	
	private boolean saveRequired(String cityCode, String commonfield,String tablename){
		boolean res =false;
	   //清空fieldRequired字段
		Map<String,Object> m = new HashMap<>();
		m.put("fieldRequired", "");
		res = commonSqlServie.batchcommonUpdate(m,tablename, "cityCode", cityCode,null);
		//更新fieldRequired字段
		m = new HashMap<>();
		m.put("fieldRequired", "required");
		res = commonSqlServie.batchcommonUpdate(m,tablename, "id", commonfield,null);
		ExecutionResult.descFormat("", "设置必填项字段");
		return res;
	}
	
	
	//**********基本项配置开始*************//
	
	//验证控件编号是否被用
	@SystemServiceLog(operationType="查询")
	@Override
	public boolean validateWidgetCode(String widgetCode,String widgetType,String cityCode) {
		boolean res = true;
		int n =  formmapper.queryWidgetByCode(widgetCode);
		if(n>0)
			res = false;
		return res;
	}
	//保存一个控件
	@SystemServiceLog(operationType="保存")
	@Override
	public boolean saveWidget(Map<String,Object> formDataMap,List valueDataMap) {
		
		if("1".equals(formDataMap.get("type"))) {
			formDataMap.put("typeName", "一般下拉");
		}else if("2".equals(formDataMap.get("type"))){
			formDataMap.put("typeName", "sql下拉");
		}else {
			formDataMap.put("typeName", "区间");
		}
	    commonSqlServie.commonOperationDatabase(formDataMap, "dynamicform_widget", true);
	    if(valueDataMap!=null)
	    	commonSqlServie.batchCommonOperationDatabase(valueDataMap, "dynamicform_field_value", true);
		return true;
	}
	
	//****  下拉项设置相关  ****//
	@SystemServiceLog(operationType="查询")
	@Override
	public Page<Map<String, Object>> querySelectType(int page,int limit,Map<String,Object> conditions){
		
		PageHelper.startPage(page, limit);
		//dataAuthHelper.auth(MenuEnum.MENU_40.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> pageInfo = formmapper.querySelectType(conditions);
		
		ExecutionResult.descFormat("", "查询下拉控件");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询")
	@Override
	public List querySelectItem(String widgetCode,String type) {
		LinkedList<Map> selectItem = new LinkedList<>();
		//dataAuthHelper.auth(MenuEnum.MENU_40.getCode(),UserThreadLocal.get().get("id").toString());
		selectItem = formmapper.queryWidgetValue("dynamicform_field_value",widgetCode);
		ExecutionResult.descFormat("", "查询下拉控件的值");
		
		return selectItem;
	}
	
	@SystemServiceLog(operationType="保存")
	@Override
	public Long saveSelectItem(Map<String,Object> data){
		Long res = null;
		if(!data.containsKey("id") || StringUtils.isEmpty(data.get("id").toString())) {
			res = commonSqlServie.commonOperationDatabase(data, "dynamicform_field_value", false);
		}else {
			res = commonSqlServie.commonOperationDatabase(data, "dynamicform_field_value", "id", false);
		}
		
		ExecutionResult.descFormat("", "保存一般下拉控件的值");
		return res;
	}
	
	@SystemServiceLog(operationType="保存")
	@Override
	public Long saveSelectSql(Map<String,Object> data) {
		Long res = commonSqlServie.commonOperationDatabase(data, "dynamicform_widget","id", false);
		ExecutionResult.descFormat("", "保存sql型下拉的sql语句");
		return res;
	}
	
	@SystemServiceLog(operationType="删除")
	@Override
	public boolean delDropdownValue(Long id) {
		boolean res = commonSqlServie.delData("dynamicform_field_value", "id", id+"", false);
		return res;
	}
	
	//****  区间设置相关  ****//
	@SystemServiceLog(operationType="查询")
	@Override
	public Page<Map<String, Object>> queryRegionType(int page,int limit,Map<String,Object> conditions){
		PageHelper.startPage(page, limit);
		Map<String,Object> user=UserThreadLocal.get();
		String cityCode = user.get("areaCode").toString(); 
		cityCode=cityCode.substring(0, 4)+"00";
		if(!conditions.containsKey("cityCode") || "".equals(conditions.get("cityCode")) || conditions.get("cityCode")==null)
			conditions.put("cityCode", cityCode);
		Page<Map<String, Object>> pageInfo = formmapper.queryRegionType(conditions);
		ExecutionResult.descFormat("", "查询区间项");
		return pageInfo;
	}
	
	@SystemServiceLog(operationType="查询")
	@Override
	public List queryRegion(String widgetCode) {
		List region = formmapper.queryWidgetValue("dynamicform_field_value",widgetCode);
		ExecutionResult.descFormat("", "查询区间项的值");
		return region;
	}
	
	@SystemServiceLog(operationType="保存")
	@Override
	public boolean saveRegion(Map<String,Object> data){
		boolean res = false;
		String firstmin = "";
		String widgetCode = "";
		List maxregion = new ArrayList<>();
		List region = new ArrayList<>();
		if(data.containsKey("firstmin")) {
			firstmin = data.get("firstmin").toString();
		}
		if(data.containsKey("widgetCode")) {
			widgetCode =  data.get("widgetCode").toString();
		}
		if(data.containsKey("maxregion")) {
			maxregion =  (List)data.get("maxregion");
		}
		if(maxregion.size()>0) {
			for(int i=0;i<=maxregion.size();i++) {
				Map m = new HashMap<>();
				if(i==0) {
					m.put("fieldValue", firstmin);
					m.put("maxRegionValue", maxregion.get(i));
				}else if(i==maxregion.size()) {
					m.put("fieldValue", maxregion.get(i-1));
					m.put("maxRegionValue", "");
				}else {
					m.put("fieldValue", maxregion.get(i-1));
					m.put("maxRegionValue", maxregion.get(i));
				}
				m.put("widgetCode", widgetCode);
				region.add(m);
			}
		}
		
		formmapper.delWidgetValue(widgetCode);
		res = commonSqlServie.batchCommonOperationDatabase(region, "dynamicform_field_value", false);
		ExecutionResult.descFormat("", "设置区间值");
		return res;
	}
	
	

	@SystemServiceLog(operationType="查询动态表单列表")
	@Override
	public Page<Map<String, Object>> queryDynamicFormList(String cityCode,Integer formType, int page, int limit) {
		PageHelper.startPage(page, limit); 
		Page<Map<String, Object>> pageInfo=formmapper.queryDynamicFormList(formType);
		ExecutionResult.descFormat(cityCode, "查询动态表单列表");
		return pageInfo;
	}


	@SystemServiceLog(operationType="查询表单字段列表")
	@Override
	public List<Map<String, Object>> queryDynamicFormFieldList(Long formId,String cityCode) {
		//dataAuthHelper.auth(MenuEnum.MENU_41.getCode(),UserThreadLocal.get().get("id").toString());
		List<Map<String, Object>> pageInfo=formmapper.queryDynamicFormFieldList(formId,cityCode);
		ExecutionResult.descFormat(formId+"", "查询表单字段列表");
		return pageInfo;
	}

	@SystemServiceLog(operationType="添加表单字段")
	@Override
	public long addDynamicFormField(Map<String,Object> data) {
		String column=data.get("field").toString();
		String tableName=data.get("tableName").toString();
		String customTableName=data.get("customTableName").toString();
		Long formId=Long.parseLong(data.get("formId").toString());
		Object tableType=data.get("tableType");
		//data.put("field", column);
		data.put("isDynamic", 3);
		data.put("fieldDbType", "varchar");
		if("2".equals(data.get("fieldType").toString())) {
			data.put("fileldVerify", "number");
		}
		data.remove("customTableName");
		data.remove("tableType");
		long result=commonSqlServie.commonOperationDatabase(data, "dynamicform_common_city_field", false);
		
		if (StringUtils.isEmpty(column)) {
			if (tableType!=null&&tableType.toString().equals("2")) {
				int customCount=formmapper.queryCustomFieldCount(formId);
				customCount++;
				column="customField_"+customCount;
				Map<String,Object> map=new HashMap<>();
				map.put("id", formId);
				map.put("customFieldCount", customCount);
				commonSqlServie.commonOperationDatabase(map, "dynamicform_table_record","id", false);
			}else {
				column="customField_"+result;
			}
						
			formmapper.addcolumn(customTableName, column);
			Map<String,Object> map=new HashMap<>();
			map.put("id", result);
			map.put("field", column);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_common_city_field","id", false);
		}
		ExecutionResult.descFormat(tableName, "添加表单字段");
		return result;
	}


	@SystemServiceLog(operationType="修改表单字段")
	@Override
	public long updateDynamicFormField(Map<String,Object> data) {
		long result=commonSqlServie.commonOperationDatabase(data, "dynamicform_common_city_field","id", false);
		ExecutionResult.descFormat(data.get("id").toString(), "修改表单字段");
		return result;
	}


	@SystemServiceLog(operationType="删除表单字段")
	@Override
	public boolean delDynamicFormField(String id) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("cityCode", "");
		Long result=commonSqlServie.commonOperationDatabase(map,"dynamicform_common_city_field", "id", false);
		ExecutionResult.descFormat(id, "删除表单字段");
		return result>0;
	}


	@SystemServiceLog(operationType="查询公司配置表单列表")
	@Override
	public Page<Map<String, Object>> queryCompanyDynamicFormList(int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<Map<String, Object>> pageInfo=formmapper.queryCompanyDynamicFormList();
		ExecutionResult.descFormat("", "查询公司配置表单列表");
		return pageInfo;
	}


	@SystemServiceLog(operationType="查询公司配置字段列表")
	@Override
	public List<Map<String, Object>> queryCompanyDynamicFormFieldList(Long formId) {
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		List<Map<String, Object>> pageInfo=formmapper.queryCompanyDynamicFormFieldList(formId,companyId);
		ExecutionResult.descFormat(companyId, "查询公司配置字段列表");
		return pageInfo;
	}


	@SystemServiceLog(operationType="查询公司未使用字段列表")
	@Override
	public List<Map<String, Object>> queryCompanyDynamicFormFieldSelect(Long formId) {
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		List<Map<String, Object>> list=formmapper.queryCompanyDynamicFormFieldSelect(formId, companyId);
		ExecutionResult.descFormat(companyId, "查询公司未使用字段列表");
		return list;
	}


	@SystemServiceLog(operationType="删除公司自定义字段")
	@Override
	public void delCompanyField(long id) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("companyId", "");
		commonSqlServie.commonOperationDatabase(map, "dynamicform_custom_field", "id", false);	
		ExecutionResult.descFormat(id+"", "删除公司自定义字段");
	}


	@SystemServiceLog(operationType="添加表单公司自定义字段")
	@Override
	public long addCompanyDynamicFormField(Map<String,Object> data) {
		String column=data.get("field").toString();
		String tableName=data.get("tableName").toString();
		Long formId=Long.parseLong(data.get("formId").toString());
		if (StringUtils.isEmpty(column)) {
			
			String customTableName=data.get("customTableName").toString();
			int customCount=formmapper.queryCustomFieldCount(formId);
			customCount++;
			column="customField_"+customCount;
			formmapper.addcolumn(customTableName, column);
			Map<String,Object> map=new HashMap<>();
			map.put("tableDbName", tableName);
			map.put("customFieldCount", customCount);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_record","tableDbName", false);
		}
		Map<String,Object> user=UserThreadLocal.get();
		String companyId=user.get("companyId").toString();
		data.put("field", column);
		data.put("isDynamic", 3);
		data.put("fieldDbType", "varchar");
		data.put("companyId", companyId);
		if("2".equals(data.get("fieldType").toString())) {
			data.put("fileldVerify", "number");
		}		
		long result=commonSqlServie.commonOperationDatabase(data, "dynamicform_custom_field", false);	
		ExecutionResult.descFormat(tableName, "添加表单公司自定义字段");
		return result;
	}


	@SystemServiceLog(operationType="修改表单公司自定义字段")
	@Override
	public long updateCompanyDynamicFormField(Map<String,Object> data) {
		long result=commonSqlServie.commonOperationDatabase(data, "dynamicform_custom_field","id", false);
		ExecutionResult.descFormat(data.get("id").toString(), "修改表单公司自定义字段");
		return result;
	}
			
	//********返回接口********//
	
	//返回自定义字段
	@SystemServiceLog(operationType="查询")
	@Override
	public List queryCustomField(String formName){
		Map<String,Object> user=UserThreadLocal.get();
		String cityCode = user.get("areaCode").toString(); 
		cityCode=cityCode.substring(0, 4)+"00";
		String companyId = user.get("companyId").toString();
		LinkedList<Map> fields = new LinkedList<>();
		Map tableinfo = formmapper.queryTableInfo(formName);
		Long formId = 0l;
		int tableType = 0;
		if(tableinfo!=null && tableinfo.size()>0) {
			formId = Long.parseLong(tableinfo.get("id").toString());
			if(tableinfo.get("tableType")!=null && !"".equals(tableinfo.get("tableType"))){
				tableType = Integer.parseInt(tableinfo.get("tableType").toString());
			}
			if(tableType==2) {//用户表，到公司
				fields = formmapper.queryCustomField(formId, cityCode, companyId);
			}else {//其他表，到城市
				fields = formmapper.queryCityCustomField(formId,cityCode);
			}
		}
		
		
		
		return fields;
	}
	
	//返回表单数据值
	public Map queryFormValue(String formName,String sourceId){
		Map tableinfo = formmapper.queryTableInfo(formName);
		String tableName = "";
		Map values = new HashMap<>();
		if(tableinfo!=null && tableinfo.size()>0) {
			tableName = tableinfo.get("tableDbName").toString();
			//获取具体数据值
			values = formmapper.queryFormValue(tableName,sourceId);
		}
		
		return values;
		
	}
	
	//返回下拉值
	public List querySelectValue(String type,String param){
		LinkedList<Map> values = new LinkedList<Map>();
		if("one".equals(type)) {
			values = formmapper.queryWidgetValue("dynamicform_field_value",param);
		}else if("many".equals(type)) {
			List fieldtype = new ArrayList<>();
			fieldtype.add("4");//sql下拉
			fieldtype.add("5");//一般下拉
			fieldtype.add("6");//复选下拉
			Map m = new HashMap<>();
			m.put("fieldtypes", fieldtype);
			m.put("formName", param);
			LinkedList<Map> widgetCodes =  formmapper.queryWidgetCode(m);
			
			for(int i=0;i<widgetCodes.size();i++) {
				
				LinkedList<Map> selectValues = formmapper.querySelectValue(widgetCodes.get(i).get("value").toString());
				Map<String,Object> value = new HashMap<>();
				
				for(Map v : selectValues) {
					
					String widgettype = v.get("type").toString();
					String fieldname = widgetCodes.get(i).get("field").toString();
					if("1".equals(widgettype)) {//一般下拉（包括普通和复选）
						if(value.containsKey(fieldname)){
							List fieldList =(List)value.get(fieldname);
							fieldList.add(v);
							
						}else{
							List l = new ArrayList<>();
							l.add(v);
							value.put(fieldname, l);
						}
					}else if("2".equals(widgettype)) {//sql下拉
						String sql = v.get("sqlDescription").toString();
						List l = formmapper.querySqlValue(sql);
						value.put(fieldname, l);
					}
					
				}
				values.add(value);
			}
		}
		return values;
		
	}
	
	//返回区间值
	@SystemServiceLog(operationType="查询")
	@Override
	public Map<String,Object> queryRegionValue(String type,String tag,String widgetCode) {
		Map<String,Object> result = new HashMap<>();
		
		if("one".equals(type)) {//根据控件code查
			Map<String,Object> user=UserThreadLocal.get();
			String cityCode = user.get("areaCode").toString(); 
			cityCode=cityCode.substring(0, 4)+"00";
			LinkedList<Map> region = formmapper.queryRegionValue(widgetCode,cityCode);
			if(region.size()>0) {
				if("1".equals(tag)) {//区间是一个input情况
					//Map m = new HashMap<>();
					result.put("region", region);
					
				}else if("2".equals(tag)){//区间是两个input情况
					//Map m1 = new HashMap<>();
					//Map m2 = new HashMap<>();
					List list1 = new ArrayList<>();
					List list2 = new ArrayList<>();
					for(int i=0;i<region.size();i++) {
						list1.add(region.get(i).get("min"));
						list2.add(region.get(i).get("max"));
					}
					result.put("min", list1);
					result.put("max", list2);
					
					
				}
			}
			
			
		}
			
		return result;
	}
	
	
	
	@SystemServiceLog(operationType="查询动态表单表格数据")
	@Override
	public Page<Map<String, Object>> queryTableDatas(String formName, List<Map<String, Object>> conditions, int page,
			int limit) {
		List<String> list=formmapper.queryTableFieldInfo(formName,null);
		Map map=formmapper.queryTableInfo(formName);
		String tableName=map.get("tableDbName").toString();
		String columns=StringUtils.join(list.toArray(), ",");
		PageHelper.startPage(page, limit);
		//dataAuthHelper.auth(MenuEnum.MENU_41.getCode(),UserThreadLocal.get().get("id").toString());
		Page<Map<String, Object>> result=formmapper.queryTableDatas(columns, tableName, conditions);
		ExecutionResult.descFormat(formName, "查询动态表单表格数据");
		return result;
	}


	@SystemServiceLog(operationType="查询表格隐藏列")
	@Override
	public Map<String, Object> queryTableHiddenField(String formName) {
		Map<String,Object> user=UserThreadLocal.get();
		 long userId=Long.parseLong(user.get("id").toString());
		Map<String, Object> map=formmapper.queryTableHiddenFields(userId, formName);
		if (map==null) {
			/*map=new HashMap<>();
			map.put("id", 0);
			 List<String> list=formmapper.queryTableFieldInfo(formName, "1");
			 map.put("columns", list);*/
		}else {
			String column= map.get("columns").toString();
			String[] array=column.split(",");
			map.put("columns", array);
		}
		ExecutionResult.descFormat(formName, "查询表格隐藏列");
		return map;
	}

	@SystemServiceLog(operationType="查询表单其他城市自定义字段")
	@Override
	public List<Map<String, Object>> queryCityDynamicFormFieldSelect(long formId) {
		Map<String,Object> user=UserThreadLocal.get();
		String cityCode=user.get("areaCode").toString();
		List<Map<String, Object>> list=formmapper.queryCityDynamicFormFieldSelect(formId, cityCode);
		ExecutionResult.descFormat(formId+"", "查询表单其他城市自定义字段");
		return list;
	}


	@SystemServiceLog(operationType="查询表单其他城市自定义字段")
	@Override
	public boolean saveTableHiddenInfo(String formName, String values) {
		Map<String,Object> user=UserThreadLocal.get();
		 long userId=Long.parseLong(user.get("id").toString());
		 Map<String, Object> data=formmapper.queryTableHiddenFields(userId, formName);
		 if (data==null) {
			 Map<String,Object> map=new HashMap<>();
			map.put("userId", userId);
			map.put("formName", formName);
			map.put("columns", values);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_hidden_field", false);
		}else {
			Map<String,Object> map=new HashMap<>();
			map.put("id", data.get("id"));
			map.put("columns", values);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_hidden_field","id", false);
		}
		 
		return true;
	}


	@SystemServiceLog(operationType="查询表单下拉框数据源")
	@Override
	public Map<String, List> queryFormSelectValue(String type, String param) {
		if ("many".equals(type)) {
			List list=querySelectValue(type, param);
			Map<String, List> map=new HashMap<>();
			for (Object o : list) {
				Map<String, List> item=(Map<String, List>) o;
				for (String key : item.keySet()) {
					map.put(key, item.get(key));
					break;
				}			
			}
			return map;
		}
		return null;
	}
	
}
