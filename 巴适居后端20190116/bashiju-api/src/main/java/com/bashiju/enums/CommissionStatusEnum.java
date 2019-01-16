package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  CommissionStatus   
 * @Description:佣金交易状态   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum CommissionStatusEnum {

	/**
	 * 计划
	 */
	PLAN("00","计划"),
	/**
	 * 待确认
	 */
	UNCONFIRMED("01","待确认"),
	/**
	 * 已确认
	 */
	CONFIRMED("02","已确认"),
	/**
	 * 驳回
	 */
	REJECT("03","驳回");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    CommissionStatusEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();
    public static List<Map<String , String>> enumList;
    static {
        enumList = new ArrayList<>();
        for (CommissionStatusEnum _enum : CommissionStatusEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        	 Map<String , String> map = new HashMap<>();
             map.put("value", _enum.code);
             map.put("name", _enum.desc);
             enumList.add(map);
        }
    }
    
    /**
     * 枚举转换
     */
    public static CommissionStatusEnum parseOf(String value){
        for(CommissionStatusEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
