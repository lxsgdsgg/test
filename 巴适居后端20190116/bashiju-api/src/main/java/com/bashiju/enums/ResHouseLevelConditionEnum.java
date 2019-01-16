package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @ClassName:  ResHouseLevelConditionEnum   
 * @Description:房源等级"条件"枚举   
 * @author: wangpeng
 * @date:   2018年7月2日 上午9:20:01   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ResHouseLevelConditionEnum {
	/**
	 * 淘宝池
	 */
	TAOBAOPOOL("1","淘宝池"),
	/**
	 * 图片
	 */
	PHOTO("2","图片"),
	/**
	 * 钥匙
	 */
	KEY("3","钥匙"),
	/**
	 * 独家
	 */
	SOLE("4","独家"),
	/**
	 * 封盘
	 */
	SEALING("5","封盘"),
	/**
	 * 急切
	 */
	EAGER("6","急切"),
	/**
	 * 满两年
	 */
	FORTWOYEARS("7","满两年"),
	/**
	 * 唯一住房
	 */
	ONLYHOUSING("8","唯一住房");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (ResHouseLevelConditionEnum item : ResHouseLevelConditionEnum.values()) {
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
    public static ResHouseLevelConditionEnum parseOf(String value){
        for(ResHouseLevelConditionEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    ResHouseLevelConditionEnum(String code, String desc) {
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
