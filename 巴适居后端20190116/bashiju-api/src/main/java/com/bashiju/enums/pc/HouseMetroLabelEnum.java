package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 地铁房源标签枚举
 * @ClassName:  HouseLabelEnum   
 * @Description:地铁房源标签枚举 
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseMetroLabelEnum {
	/** NEW("1", "新上")**/
	NEW("1", "新上"),
	/** DUTYFREE("2", "免税")**/
	DUTYFREE("2", "免税"),
	/** TWOYEAR("3", "满两年")**/
	TWOYEAR("3", "满两年"),
	/** FIVEYEAR("4", "满五年")**/
	FIVEYEAR("4", "满五年"),
	/** RIGHTCERTIFICATE("5", "红本在手")**/
	RIGHTCERTIFICATE("5", "红本在手"),
	/** ALLTIME("6", "随时可看")**/
	ALLTIME("6", "随时可看")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseMetroLabelEnum item : HouseMetroLabelEnum.values()) {
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
    public static HouseMetroLabelEnum parseOf(String value){
        for(HouseMetroLabelEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    HouseMetroLabelEnum(String code, String desc) {
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
