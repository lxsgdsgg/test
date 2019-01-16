package com.bashiju.www.pojo.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *  出售房源排序枚举
 * @ClassName:  SellHouseSortEnum   
 * @Description:   出售房源排序枚举
 * @author: wangkaifa
 * @date:   2018年7月24日 下午2:45:16       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseSortEnum {
	/** 默认DEFAULT("0", "默认")**/
	DEFAULT("0", "默认"),
	/** 总价SELLPRICE("1", "价格")**/
	PRICE("1", "价格"),
	/** 单价UNITPRICE("2","单价")**/
	UNITPRICE("2","单价"),
	/** 面积BUILDSPACE("3","面积")**/
	BUILDSPACE("3","面积"),
	/** 热门房源HOT("4","热门房源")**/
	HOT("4","热门房源"),
	/** 优质房源HIGHTQUALITY("5","优质房源")**/
	HIGHTQUALITY("5","优质房源")
    ;
	public static  Map<String, String> enumMap;
    /** 区间控件编号枚举 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseSortEnum item : HouseSortEnum.values()) {
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
    public static HouseSortEnum parseOf(String value){
        for(HouseSortEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    HouseSortEnum(String code, String desc) {
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
