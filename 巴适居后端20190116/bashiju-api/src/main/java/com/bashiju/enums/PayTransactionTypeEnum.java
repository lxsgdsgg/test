package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 *   代收代付类型枚举
 * @ClassName:  PayTransactionTypeEnum   
 * @Description:   代收代付类型枚举
 * @author: wangkaifa
 * @date:   2018年6月28日 上午9:29:18       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum PayTransactionTypeEnum {
	/** 代付款PAYMENT("1", "代付款")**/
	PAYMENT("1", "代付款"),
	/** 代收款 RECEIVE("0", "代收款")**/
	RECEIVE("0", "代收款");


	private String code;
	private String desc;
	
	PayTransactionTypeEnum(String code, String desc) {
	    this.code = code;
	    this.desc = desc;
	}

	public String getCode() {
	    return code;
	}
	
	public String getDesc() {
	    return desc;
	}
    public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (PayTransactionTypeEnum item : PayTransactionTypeEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String, String> map=new HashMap<>();
            map.put("name", item.desc);
            map.put("value", item.code);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static PayTransactionTypeEnum parseOf(String value){
        for(PayTransactionTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
