package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;


public enum RepeatTypeEbum {
	/**
	 * SELF("7","本人")
	 */
	SELF("7","本人"),
	/**
	 * GROUP("5","本组")
	 */
	GROUP("5","本组"),
	/**
	 * SHOP("4","本店")
	 */
	SHOP("4","本店"),
	/**
	 * AREA("3","本区")
	 */
	AREA("3","本区"),
	/**
	 * COMPANY("99","本公司")
	 */
	COMPANY("99","本公司");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    RepeatTypeEbum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (RepeatTypeEbum _enum : RepeatTypeEbum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static RepeatTypeEbum parseOf(String value){
        for(RepeatTypeEbum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
