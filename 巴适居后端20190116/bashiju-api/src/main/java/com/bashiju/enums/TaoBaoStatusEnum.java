package com.bashiju.enums;

import java.util.Map;
import java.util.TreeMap;

/**   
 * 淘宝池非淘宝池枚举
 * @ClassName  TaoBaoStatusEnum   
 * @Description 淘宝池非淘宝池枚举 
 * @author zhaobin
 * @date   2018年5月31日 上午10:10:01   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum TaoBaoStatusEnum {
			
			/** 非淘宝池 NOT_TAOBAO("0", "非淘宝池")**/
			NOT_TAOBAO("0", "非淘宝池"),
			
			/** 淘宝池 TAOBAO("1", "淘宝池")**/
			TAOBAO("1", "淘宝池");

	    private String code;
	    private String desc;

	    TaoBaoStatusEnum(String code, String desc) {
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
		        for (TaoBaoStatusEnum item : TaoBaoStatusEnum.values()) {
		            enumMap.put(item.code, item.desc);
		        }
		    }
		    /**
		     * 枚举转换
		     */
		    public static TaoBaoStatusEnum parseOf(String value){
		        for(TaoBaoStatusEnum item : values())
		            if(item.getCode().equals(value))
		                return item;
		        
		        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
		    }
		    
}
