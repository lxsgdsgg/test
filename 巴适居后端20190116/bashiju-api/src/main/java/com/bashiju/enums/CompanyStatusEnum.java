
package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  CompanyStatusEnum   
 * @Description:公司状态  
 * @author: wangpeng
 * @date:   2018年11月12日 上午10:34:52   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum CompanyStatusEnum {
	/**
	 * 有效
	 */
	EFFECTIVE("01","有效"),
	/**
	 * 无效
	 */
	INVALID("02","无效");
	
	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	private CompanyStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    
    static {
		
		enumMap = new TreeMap<String, String>();
		enumList = new ArrayList<Map<String,String>>();
		
		for(CompanyStatusEnum  item: CompanyStatusEnum.values()) {
			enumMap.put(item.code,item.desc);
			Map<String, String> map = new  HashMap<String, String>();
			map.put("name",item.desc);
			map.put("value", item.code);
			enumList.add(map);
		}
	}
    
    /**
     * 枚举转换
     */
    public static CompanyStatusEnum parseOf(String value){
        for(CompanyStatusEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
	
}
