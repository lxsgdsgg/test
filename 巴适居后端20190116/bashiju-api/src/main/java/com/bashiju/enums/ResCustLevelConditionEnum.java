package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @ClassName:  ResCustLevelConditionEnum   
 * @Description:客源等级"条件"枚举 
 * @author: wangpeng
 * @date:   2018年7月2日 上午9:21:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ResCustLevelConditionEnum {
	/**
	 * 复看
	 */
	LOOKBACK("1","复看"),
	/**
	 * 
	 */
	DETERMINE("2","下定"),
	/**
	 * 急切的
	 */
	EAGER("3","急切"),
	/**
	 * 封盘
	 */
	SEALING("4","封盘"),
	/**
	 * 淘宝池
	 */
	TAOBAOPOOL("5","淘宝池");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (ResCustLevelConditionEnum item : ResCustLevelConditionEnum.values()) {
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
    public static ResCustLevelConditionEnum parseOf(String value){
        for(ResCustLevelConditionEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    ResCustLevelConditionEnum(String code, String desc) {
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
