package com.bashiju.manage.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DecisionConfigurateTypeEnum   
 * @Description:决策配置类型   
 * @author: yangz
 * @date:   2018年7月6日 上午10:06:48   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DecisionConfigurateTypeEnum {
	/**
	 * 房源
	 */
	HOUSE("0","房源"),
	/**
	 * 求购
	 */
	CUSTORMERBUY("1","求购"),
	/**
	 * 求租
	 */
	CUSTORMERRENT("2","求租"),
	/**
	 * 成交
	 */
	DEAL("3","成交"),
	/**
	 * 跟进
	 */
	FOLLOW("4","跟进"),
	/**
	 * 其他
	 */
	OTHER("4","其他");
	

    public static Map<String , String> enumMap;
    /**
     * 
     */
    static {
        enumMap = new TreeMap<String, String>();
        for (DecisionConfigurateTypeEnum item : DecisionConfigurateTypeEnum.values()) {
            enumMap.put(item.code, item.desc);
        }
    }
    /**
     * 枚举转换
     */
    public static DecisionConfigurateTypeEnum parseOf(String value){
        for(DecisionConfigurateTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    DecisionConfigurateTypeEnum(String code, String desc) {
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
