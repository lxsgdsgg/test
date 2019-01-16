package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 公盘/私盘枚举
 * @ClassName  HouseDiscStatusEnum   
 * @Description 盘状态枚举   
 * @author zhaobin
 * @date   2018年5月31日 上午9:18:50   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DiscStatusEnum {


		/** 私盘 PRIVATEDISC("1", "私盘")**/
		PRIVATEDISC("1", "私盘"),
		/** 公盘 PUBLICDISC("2", "公盘")**/
		PUBLICDISC("2", "公盘");


    private String code;
    private String desc;

    DiscStatusEnum(String code, String desc) {
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
	    static {
	        enumMap = new TreeMap<String, String>();
	        for (DiscStatusEnum item : DiscStatusEnum.values()) {
	            enumMap.put(item.code, item.desc);
	        }
	    }
	    /**
	     * 枚举转换
	     */
	    public static DiscStatusEnum parseOf(String value){
	        for(DiscStatusEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }
	    
}
