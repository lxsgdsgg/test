package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 户型枚举
 * @ClassName:  RoomTypeEnum   
 * @Description:户型枚举 
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum RoomTypeEnum {
	/** ONE("1", "一室")**/
	ONE("1", "一室"),
	/** TWO("2", "二室")**/
	TWO("2", "二室"),
	/** THREE("3", "三室")**/
	THREE("3", "三室"),
	/** FOUR("4", "四室")**/
	FOUR("4", "四室"),
	/** FIVE("5", "五室")**/
	FIVE("5", "五室"),
	/** SIX("6", "五室以上")**/
	MAX("6", "五室以上")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (RoomTypeEnum item : RoomTypeEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String, Object> map=new HashMap<>();
            map.put("name", item.desc);
            map.put("value", item.code);
            enumList.add(map);
        }
    }

    /**
     * 枚举转换
     */
    public static RoomTypeEnum parseOf(String value){
        for(RoomTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    RoomTypeEnum(String code, String desc) {
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
