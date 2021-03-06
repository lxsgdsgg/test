package com.bashiju.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName:  FormService   
 * @Description:动态表单接口
 * @author: liwen
 * @date:   2018年4月9日 上午10:16:27   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public interface FormServiceApi {


	
	/**
	 * 
		 * 根据表单名称查询动态字段
		 * @Description: 根据表单名称查询动态字段 
		 * @param formName 表单名称
		 * @param cityCode 城市代码
		 * @Param companyId 公司id
		 * @return field,title,value,fileldVerify,fieldRequired,fieldType,fieldUnit
		 * List
	 */
	public List queryCustomField(String formName,String cityCode,String companyId);
	/**
	 * 
		 * 查询动态下拉框的值
		 * @Description:查询动态下拉框的值
		 * @param type 类型：one查询一个下拉框的值,many查询动态表单下的下拉框值
		 * @param param type为one时，传下拉控件的编号；type为many时，传动态表单的formName
		 * @return [{"下拉控件字段":[{}]}]
		 * List
	 */
	public List querySelectValue(String type,String param);
	/**
	 * 
		 * 查询区间数据
		 * @Description: 查询区间数据 
		 * @param type one，many
		 * @param tag
		 * @param param
		 * @return 
		 * List
	 */
	public List queryRegionValue(String type,String tag,String param);
	/**
	 * 
		 * 查询动态下拉框的值
		 * @Description: 查询动态下拉框的值
		 * @param type 类型：one查询一个下拉框的值,many查询动态表单下的下拉框值
		 * @param param type为one时，传下拉控件的编号；type为many时，传动态表单的formName
		 * @return {"下拉控件字段":[{}]}
		 * Map<String,List>
	 */
	public Map<String, List> queryFormSelectValue(String type,String param);
	
	
	

	/**
	 * 查询用户自定义的表格隐藏列
	 * @Title: queryTableHiddenField   
	 * @Description: 查询用户自定义的表格隐藏列
	 * @param: formName 动态表单名称
	 * @param: userId 用户id     
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> queryTableHiddenField(String formName,long userId);
	/**
	 * 保存表单隐藏设置
	 * @Title: saveTableHiddenInfo   
	 * @Description: 保存表单隐藏设置  
	 * @param: formName 动态表单名称
	 * @param: values 隐藏的列名,用逗号连接
	 * @param:  userId  用户id  
	 * @return: boolean      
	 * @throws
	 */
	public boolean saveTableHiddenInfo(String formName,String values,long userId);
	/**
	 * 
		 * 查询小区下拉
		 * @Description: 查询小区下拉 
		 * @param cityCode 城市代码
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> searchCommunity(String cityCode);
	/**
	 * 
		 * 查询部门人员下拉
		 * @Description: 查询部门人员下拉 
		 * @param cityCode 城市代码
		 * @param companyId 公司id
		 * @return 
		 * LinkedList<Map>
	 */
	public LinkedList<Map> queryDept(String companyId);
}
