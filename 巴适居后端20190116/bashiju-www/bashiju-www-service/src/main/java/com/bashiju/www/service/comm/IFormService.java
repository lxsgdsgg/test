package com.bashiju.www.service.comm;
/**
 * 
 *   动态下拉、区间服务
 * @ClassName:  IFormService   
 * @Description:     动态下拉、区间服务
 * @author: wangkaifa
 * @date:   2018年7月23日 下午6:12:37       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;
import java.util.Map;

import com.bashiju.www.pojo.service.out.comm.FormRangeResult;
import com.bashiju.www.pojo.service.out.comm.RangeResult;
import com.bashiju.www.pojo.service.out.comm.SelectItemResult;

public interface IFormService {
	/**
	 * 
		 * 查询城市对应的区间值
		 * 先从redis中取值，没有再查询数据库并存入redis
		 * redis的key为前缀(WebGlobal.RANGE_ITEM_REDIS_PREFIX)+城市代码,
		 * value为区间值json字符串[{min:1,max:10,widgetCode:mianji},{min:10,max:20,widgetCode:mianji}] widgetCode为控件编号
		 * @Description: 查询城市对应的区间值
		 * @param codes 区间控件编号，多个用逗号分隔
		 * @param cityCode 城市代码
		 * @return {code:[{min:1,max:10},{min:10,max:20}]} code为区间控件编号，min为最小值，max为最大值
		 * Map<String,List<RangeResult>>
	 */
	Map<String,List<FormRangeResult>> queryRangeByCode(String codes,String cityCode);
	/**
	 * 
		 * 查询动态下拉值，先从redis中取值，没有再查询数据库并存入redis
		 * redis的key为(WebGlobal.SELECT_ITEM_REDIS_KEY),
		 * value为下拉值json字符串[{name:东,value:1,widgetCode:chaoxiang},{name:南,value:2,widgetCode:chaoxiang}] widgetCode为控件编号
		 * @Description: 查询动态下拉值
		 * @param codes 动态下拉编号，多个用逗号分隔
		 * @return {code:[{name:东,value:1},{name:南,value:2}]} code为动态下拉编号，name为下拉显示名称，value为下拉值
		 * Map<String,List<SelectItemResult>>
	 */
	Map<String,List<SelectItemResult>> querySelectByCode(String codes);
}
