package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 客源交易类型
 * @ClassName:  GuestTransactionEnum   
 * @Description:TODO(客源交易类型)   
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:28   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum CustomerTransactionEnum {
	/** 求购  BUY("4", "求购")**/
	BUY("4", "求购"),
	/** 求租 RENT("5", "求租")**/
	RENT("5", "求租"),
    ;
	public static  Map<String, String> enumMap;
    /** 客源交易类型下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (CustomerTransactionEnum item : CustomerTransactionEnum.values()) {
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
    public static CustomerTransactionEnum parseOf(String value){
        for(CustomerTransactionEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    CustomerTransactionEnum(String code, String desc) {
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
