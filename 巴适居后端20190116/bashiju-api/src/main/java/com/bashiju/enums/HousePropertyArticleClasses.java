package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @ClassName:  HousePropertyArticleClasses   
 * @Description:TODO(房产文章   类别等级)   
 * @author: wangpeng
 * @date:   2018年7月19日 下午5:57:53   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HousePropertyArticleClasses {
	/**
	 * 一级
	 */
	Level1("1","一级"),
	/**
	 * 二级
	 */
	Level2("2","二级"),
	/**
	 * 三级
	 */
	Level3("3","三级");
//	/**
//	 * 四级
//	 */
//	Level4("4","四级");
	
	
	public static Map<String,String> enumMap;
	public static List<Map<String,String>> enumList;
	
	static {
		enumMap = new TreeMap<String,String>();
		enumList = new ArrayList<>();
		for(HousePropertyArticleClasses item : HousePropertyArticleClasses.values()) {
			enumMap.put(item.code, item.desc);
			Map<String,String> map = new HashMap<>();
			map.put("name",item.desc);
			map.put("value", item.code);
			enumList.add(map);
		}
	}
	
	/**
	 * 枚举转换
	 */
	
	public static HousePropertyArticleClasses parseOf(String value) {
		for(HousePropertyArticleClasses item :values())
			if(item.getCode().equals(value))
				return item;
		throw new IllegalArgumentException("异常错误代码["+value+"]不匹配");
		}
	private String desc;
	private String code;
	
	private HousePropertyArticleClasses(String code,String desc) {
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getCode() {
		return code;
	}

}
