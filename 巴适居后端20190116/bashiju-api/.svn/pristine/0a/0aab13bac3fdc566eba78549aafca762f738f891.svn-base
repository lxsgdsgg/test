package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 朝向枚举
 * @ClassName:  RoomTypeEnum   
 * @Description:朝向枚举
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum OrientationEnum {
	/** EAST("1", "东")**/
	EAST("1", "东"),
	/** SOUTH("2", "南")**/
	SOUTH("2", "南"),
	/** WEST("3", "西")**/
	WEST("3", "西"),
	/** NORTH("4", "北")**/
	NORTH("4", "北"),
	/** EASTSOUTH("5", "东南")**/
	EASTSOUTH("5", "东南"),
	/** EASTNORTH("6", "东北")**/
	EASTNORTH("6", "东北"),
	/** WESTOUTH("7", "西南")**/
	WESTOUTH("7", "西南"),
	/** WESTNORTH("8", "西北")**/
	WESTNORTH("8", "西北")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (OrientationEnum item : OrientationEnum.values()) {
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
    public static OrientationEnum parseOf(String value){
        for(OrientationEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    OrientationEnum(String code, String desc) {
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
