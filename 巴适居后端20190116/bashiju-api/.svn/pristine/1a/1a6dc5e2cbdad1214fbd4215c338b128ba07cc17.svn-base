package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 楼龄类型枚举
 * @ClassName:  BuildAgeEnum   
 * @Description:楼龄类型枚举
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum BuildAgeEnum {
	/** ONE("1", "5年以下")**/
	ONE("1", "5年以下"),
	/** TWO("2", "5-10年")**/
	TWO("2", "5-10年"),
	/** THREE("3", "10-15年")**/
	THREE("3", "10-15年"),
	/** FOUR("4", "15-20年")**/
	FOUR("4", "15-20年"),
	/** MAX("5", "20年以上")**/
	MAX("5", "20年以上")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (BuildAgeEnum item : BuildAgeEnum.values()) {
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
    public static BuildAgeEnum parseOf(String value){
        for(BuildAgeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    BuildAgeEnum(String code, String desc) {
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
