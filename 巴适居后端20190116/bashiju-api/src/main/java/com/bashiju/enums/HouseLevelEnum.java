package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 房源等级枚举
 * @ClassName:  HouseLevelEnum   
 * @Description:TODO(房源等级枚举)   
 * @author: wangkaifa
 * @date:   2018年5月25日 上午10:17:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseLevelEnum {
		/** A等级 A("A", "A等级") **/
		A("A", "A等级"),
		/** B等级 B("B", "B等级")**/
		B("B", "B等级"),
		/** C等级 C("C", "C等级") **/
		C("C", "C等级"),
		/** D等级  D("D", "D等级")**/
		D("D", "D等级"),
	    ;
	    public static Map<String , String> enumMap;
	    /** 房源等级下拉数据 **/
	    public static List<Map<String, String>> enumList;
	    static {
	        enumMap = new TreeMap<String, String>();
	        enumList=new ArrayList<>();
	        for (HouseLevelEnum item : HouseLevelEnum.values()) {
	            enumMap.put(item.code, item.desc);
	            Map<String, String> map=new HashMap<>();
	            map.put("name", item.desc);
	            map.put("value", item.code);
	            enumList.add(map);
	        }
	    }

	    /**
	     * 枚举转换
	     */
	    public static HouseLevelEnum parseOf(String value){
	        for(HouseLevelEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }


	    private String code;
	    private String desc;

	    HouseLevelEnum(String code, String desc) {
	        this.code = code;
	        this.desc = desc;
	    }

	    public String getCode() {
	        return code;
	    }

	    public String getDesc() {
	        return desc;
	    }
	    
}
