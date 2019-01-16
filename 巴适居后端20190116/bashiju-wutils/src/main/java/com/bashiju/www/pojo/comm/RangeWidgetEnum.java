package com.bashiju.www.pojo.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**		
 * 
 *  pc端页面使用的区间控件编号枚举
 * @ClassName:  RangeEnum   
 * @Description:  pc端页面使用的区间控件编号枚举
 * @author: wangkaifa
 * @date:   2018年7月24日 上午11:11:53       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum RangeWidgetEnum {
	/** 售价SELLPRICE("sellprice", "售价")**/
	SELLPRICE("sellprice", "售价"),
	/** 租金RENTPRICE("rentprice", "租金")**/
	RENTPRICE("rentprice", "租金"),
	/** 面积BUILDSPACE("buildspace","面积")**/
	BUILDSPACE("buildspace","面积")
    ;
	public static  Map<String, String> enumMap;
    /** 区间控件编号枚举 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (RangeWidgetEnum item : RangeWidgetEnum.values()) {
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
    public static RangeWidgetEnum parseOf(String value){
        for(RangeWidgetEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    RangeWidgetEnum(String code, String desc) {
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
