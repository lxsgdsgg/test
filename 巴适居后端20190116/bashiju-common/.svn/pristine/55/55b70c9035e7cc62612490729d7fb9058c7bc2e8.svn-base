package com.bashiju.common.service.formservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bashiju.api.FormServiceApi;
import com.bashiju.common.mapper.FormMapper;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.log.SystemServiceLog;
import com.bashiju.utils.service.CommonSqlServie;

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


@SystemServiceLog(sourceType="动态表单公共服务") 
@Service
 public class FormServiceImpl implements FormServiceApi{
	
	@Resource
	private FormMapper formmapper;
	@Resource
	private CommonSqlServie commonSqlServie;
	
	
	//********返回接口********//
	
	//返回自定义字段
	@SystemServiceLog(operationType="查询")
	@Override
	public List queryCustomField(String formName,String cityCode,String companyId){
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
						String sql = v.get("fieldValue").toString();
						List l = formmapper.querySqlValue(sql);
						value.put(fieldname, l);
					}
					
				}
				values.add(value);
			}
		}
		return values;
		
	}
	
	@SystemServiceLog(operationType="查询")
	@Override
	public List queryRegionValue(String type,String tag,String param) {
		List result = new ArrayList<>();
		
		if("one".equals(type)) {//根据控件code查
			LinkedList<Map> region = formmapper.queryRegionValue(param);
			if(region.size()>0) {
				if("1".equals(tag)) {//区间是一个input情况
					Map m = new HashMap<>();
					m.put("region", region);
					result.add(m);
				}else if("2".equals(tag)){//区间是两个input情况
					Map m1 = new HashMap<>();
					Map m2 = new HashMap<>();
					List list1 = new ArrayList<>();
					List list2 = new ArrayList<>();
					for(int i=0;i<region.size();i++) {
						list1.add(region.get(i).get("min"));
						list2.add(region.get(i).get("max"));
					}
					m1.put("min", list1);
					m2.put("max", list2);
					result.add(m1);
					result.add(m2);
					
				}
			}
			
			
		}else if("many".equals(type)) {//根据表单查
			List fieldtype = new ArrayList<>();
			fieldtype.add("8");//区间
			Map m = new HashMap<>();
			m.put("fieldtypes", fieldtype);
			m.put("formName", param);
			LinkedList<Map> widgetCodes =  formmapper.queryWidgetCode(m);
			
			for(int i=0;i<widgetCodes.size();i++) {
				
				LinkedList<Map> regionValues = formmapper.queryRegionValue(widgetCodes.get(i).get("value").toString());
				Map<String,Object> value = new HashMap<>();
				String fieldname = widgetCodes.get(i).get("field").toString(); 
				if("1".equals(tag)) {//区间是一个input情况
					Map mm = new HashMap<>();
					mm.put(fieldname, regionValues);
					result.add(mm);
					
				}else if("2".equals(tag)){//区间是两个input情况
					Map m1 = new HashMap<>();
					Map m2 = new HashMap<>();
					List list1 = new ArrayList<>();
					List list2 = new ArrayList<>();
					List l = new ArrayList<>();
					for(int j=0;j<regionValues.size();j++) {
						list1.add(regionValues.get(j).get("min"));
						list2.add(regionValues.get(j).get("max"));
					}
					m1.put("min", list1);
					m2.put("max", list2);
					l.add(m1);
					l.add(m2);
					Map mm = new HashMap<>();
					mm.put(fieldname, l);
					result.add(mm);
				}
					
					
			}
			
		}
			
		return result;
	}
	
	
	
	

	@SystemServiceLog(operationType="查询表格隐藏列")
	@Override
	public Map<String, Object> queryTableHiddenField(String formName,long userId) {
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
	public boolean saveTableHiddenInfo(String formName, String values,long userId) {
		 Map<String, Object> data=formmapper.queryTableHiddenFields(userId, formName);
		 if (data==null) {
			 Map<Object, Object> map=new HashMap<>();
			map.put("userId", userId);
			map.put("formName", formName);
			map.put("columns", values);
			commonSqlServie.commonOperationDatabase(map, "dynamicform_table_hidden_field", false);
		}else {
			Map<Object, Object> map=new HashMap<>();
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
	
	@SystemServiceLog(operationType="查询片区、小区")
	@Override
	public List<Map<String, Object>> searchCommunity(String cityCode) {
		ExecutionResult.descFormat(cityCode, "查询片区、小区");
		return formmapper.searchCommunity(cityCode);
	}
	
	@SystemServiceLog(operationType="查询部门")
	@Override
	public LinkedList<Map> queryDept(String companyId){
		LinkedList<Map> depts = formmapper.queryDept(companyId);
		ExecutionResult.descFormat(companyId, "查询部门用户");
		return depts;
	}
}
