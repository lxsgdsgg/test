package com.bashiju.enums;

import java.util.Map;
import java.util.TreeMap;

public enum FtpSourceTypeEnum {


	/** TP_SOURCE_FY("fy", "房源")**/
	FTP_SOURCE_FY("fy", "房源"),
	/**FTP_SOURCE_KY("ky", "客源"),**/
	FTP_SOURCE_KY("ky", "客源"),
	/** FTP_SOURCE_XQ("xq", "小区")**/
	FTP_SOURCE_XQ("xq", "小区"),
	/** FTP_SOURCE_YH("yh","用户")**/
	FTP_SOURCE_YH("yh","用户"),
	/** FTP_SOURCE_DYMB("dymb","打印模板")**/
	FTP_SOURCE_DYMB("dymb","打印模板")
	;	
	private String code;
	private String desc;
	
	FtpSourceTypeEnum(String code, String desc) {
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
	        for (FtpSourceTypeEnum item : FtpSourceTypeEnum.values()) {
	            enumMap.put(item.code, item.desc);
	        }
	    }
	    /**
	     * 枚举转换
	     */
	    public static FtpSourceTypeEnum parseOf(String value){
	        for(FtpSourceTypeEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }
	    
}
