package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  ContractTypeEnum   
 * @Description:合同类型   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ContractTypeEnum {

	/**
	 * 买卖合同
	 */
	BUSINESSCONTRACT("01","买卖合同"),
	/**
	 * 租赁合同
	 */
	LEASECONTRACT("02","租赁合同"),
	/**
	 * 委托合同
	 */
	ENTRUSTCONTRACT("03","委托合同"),
	/**
	 * 独家合同
	 */
	EXCLUSIVECONTRACT("04","独家合同");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (ContractTypeEnum item : ContractTypeEnum.values()) {
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
    public static ContractTypeEnum parseOf(String value){
        for(ContractTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    ContractTypeEnum(String code, String desc) {
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
