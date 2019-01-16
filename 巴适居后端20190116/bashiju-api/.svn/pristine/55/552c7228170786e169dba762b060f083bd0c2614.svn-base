package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 底部内容类型
 * @ClassName:  BottomContentEnum   
 * @Description:(底部内容类型枚举)   
 * @author: liwen
 * @date:   2018年8月01日 上午10:23:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum LabelTypeEnum {
	/**
	 * 房源
	 */
	HS("1","房源"),
	
	/**
	 * 热评
	 */
	RP("2","热评"),
	/**
	 * 点评
	 */
	DP("3","点评"),
	/**
	 * 楼盘
	 */
	LP("4","楼盘");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (LabelTypeEnum item : LabelTypeEnum.values()) {
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
    public static LabelTypeEnum parseOf(String value){
        for(LabelTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    LabelTypeEnum(String code, String desc) {
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
