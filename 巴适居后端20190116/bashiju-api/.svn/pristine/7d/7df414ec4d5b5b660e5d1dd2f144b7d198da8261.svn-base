package com.bashiju.enums;

import java.util.Map;
import java.util.TreeMap;

public enum DeptTypeEnum {


	/** CITY("1", "城市")**/
	DEPT_CITY("1", "城市"),
	/** 公盘 DEPT_BIGAREA("2", "大区")**/
	DEPT_BIGAREA("2", "大区"),
	DEPT_AREA("3","区"),
	DEPT_STORE("4","店"),
	DEPT_GROUP("5","组"),
	DEPT_FUN("6","职能"),
	DEPT_PEOPLE("7","人"),
	;	
	private String code;
	private String desc;
	
	DeptTypeEnum(String code, String desc) {
	    this.code = code;
	    this.desc = desc;
	}
	
	public String getCode() {
	    return code;
	}
	
	public String getDesc() {
	    return desc;
	}
	    public static Map<String , String> enumMap;
	    public static Map<String,String> queryEnumMap;
	    static {
	        enumMap = new TreeMap<String, String>();
	        queryEnumMap = new TreeMap<String,String>();
	        for (DeptTypeEnum item : DeptTypeEnum.values()) {
	            enumMap.put(item.code, item.desc);
	            if(!"7".equals(item.getCode()))
	            	queryEnumMap.put(item.getCode(), item.getDesc());
	        }
	    }
	    /**
	     * 枚举转换
	     */
	    public static DeptTypeEnum parseOf(String value){
	        for(DeptTypeEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }
	    
}
