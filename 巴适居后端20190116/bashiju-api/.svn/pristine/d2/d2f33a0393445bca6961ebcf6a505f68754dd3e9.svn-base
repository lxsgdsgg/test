package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 房源交易类型
 * @ClassName:  HouseTransactionEnum   
 * @Description:TODO(房源交易类型)   
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseTransactionEnum {
	/** 出售 SELL("1", "出售")**/
	SELL("1", "出售"),
	/** 出租LEASE("2", "出租")**/
	LEASE("2", "出租"),
	/** 租售 LEASE_SELL("3", "租售")**/
	LEASE_SELL("3", "租售"),
    ;
	public static Map<String , String> enumMap;
    /** 房源交易类型下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseTransactionEnum item : HouseTransactionEnum.values()) {
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
    public static HouseTransactionEnum parseOf(String value){
        for(HouseTransactionEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    HouseTransactionEnum(String code, String desc) {
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
