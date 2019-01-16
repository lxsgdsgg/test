package com.bashiju.enums;

import java.util.Map;
import java.util.TreeMap;

public enum FtpFileTypeEnum {


	/** FTP_FILE_TP("pic", "图片")**/
	FTP_FILE_TP("pic", "图片"),
	/** FTP_FILE_SP("video", "视频")**/
	FTP_FILE_SP("video", "视频"),
	/** FTP_FILE_SY("audio", "音频")**/
	FTP_FILE_SY("audio", "音频"),
	/** FTP_FILE_WD("doc", "文档")**/
	FTP_FILE_WD("doc", "文档"),
	/** FTP_FILE_OTHER("other", "其他")**/
	FTP_FILE_OTHER("other", "其他"),

	;	
	private String code;
	private String desc;
	
	FtpFileTypeEnum(String code, String desc) {
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
	        for (FtpFileTypeEnum item : FtpFileTypeEnum.values()) {
	            enumMap.put(item.code, item.desc);
	        }
	    }
	    /**
	     * 枚举转换
	     */
	    public static FtpFileTypeEnum parseOf(String value){
	        for(FtpFileTypeEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }
	    
}
