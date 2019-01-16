package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * OA公告类型
 * @ClassName:  OaNoticeEnum   
 * @Description:(OA公告类型枚举)   
 * @author: liwen
 * @date:   2018年10月05日 下午14:23:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum OaNoticeEnum {
	/**
	 * 系统公告
	 */
	SNOTICE("0","系统公告"),
	
	/**
	 * 公司公告
	 */
	CNOTICE("1","公司公告"),
	/**
	 * 部门公告
	 */
	DNOTICE("2","部门公告");
	
	
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (OaNoticeEnum item : OaNoticeEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String , String> map=new HashMap<>();
            map.put("value", item.code);
            map.put("name", item.desc);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static OaNoticeEnum parseOf(String value){
        for(OaNoticeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    OaNoticeEnum(String code, String desc) {
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
