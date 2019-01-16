package com.bashiju.customer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *   客源状态
 * @ClassName:  MenuController   
 * @Description:   客源状态
 * @author: wangkaifa
 * @date:   2018年6月13日 上午10:58:38       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum CustomerStatusEnum {
	/** 有效EFFECTIVE("1","1", "有效")**/
	EFFECTIVE("1","1", "有效"),
	/** 我租MY_LEASE("2","2","我租")**/
	MY_LEASE("2","2","我租"),
	/** 我购MY_SELL("3","2","我购")**/
	MY_SELL("3","2","我购"),
	/** 他租OTHER_LEASE("4","3","他租")**/
	OTHER_LEASE("4","3","他租"),
	/** 他购OTHER_SELL("5","3","他购")**/
	OTHER_SELL("5","3","他购"),
	/** 暂缓POSTPONE("6", "3","暂缓")**/
	POSTPONE("6", "3","暂缓"),
	/** 撤单CANCEL("7","3","撤单")**/
	CANCEL("7","3","撤单")
	;
    
    private String code;
    private String desc;
    private String type;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
       
    public String getType() {
		return type;
	}

	CustomerStatusEnum(String code,String type, String desc){
        this.code = code ;
        this.desc = desc ;
        this.type=type;
    }
    /** 枚举Map对象，保存code和desc，code为key**/
    public static Map<String, String> itemMap = new TreeMap<String, String>();
    /** 枚举List对象，保存code和name**/
    public static List<Map<String, String>> itemList = new ArrayList<>();
    
    static {
        for (CustomerStatusEnum _enum : CustomerStatusEnum.values()) {
        	itemMap.put(_enum.code, _enum.desc);
        	Map<String, String> map=new HashMap<>();
        	map.put("name", _enum.desc);
        	map.put("code", _enum.code);
        	itemList.add(map);
        }
    }
    
    /**
     * 枚举转换
     */
    public static CustomerStatusEnum parseOf(String code){
        for(CustomerStatusEnum item :values())
            if(item.getCode().equals(code))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+code+"]不匹配!");
    }
}
