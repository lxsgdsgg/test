/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PerformanceTypeEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年11月15日 下午5:32:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 业绩类型枚举类
 * @ClassName:PerformanceTypeEnum
 * @Description:业绩类型枚举类
 * @author:zuoyuntao
 * @date:2018年11月15日 下午5:32:34
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum PerformanceTypeEnum {
	/**
	 * 新房成交业务
	 */
	NEWHOUSEDEAL("1","一手成交"),
	/**
	 * 二手买卖成交
	 */
	HOUSESALEDEAL("2","住宅出售"),
	/**
	 * 二手租赁成交
	 */
	BUSINESSSALEDEAL("3","商业出售"),
	/**
	 * 二手租赁成交
	 */
	HOUSELEASEDEAL("4","住宅出租"),
	/**
	 * 二手租赁成交
	 */
	BUSINESSLEASEDEAL("5","商业出租"),
	/**
	 * 金融业务
	 */
	FINANCEDEAL("6","金融业务");
	
    private String code;
    
    private String desc;
	
	public static Map<String , String> enumMap;

	public static List<Map<String , String>> enumList;
    
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (PerformanceTypeEnum item : PerformanceTypeEnum.values()) {
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
    public static PerformanceTypeEnum parseOf(String value){
        for(PerformanceTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    PerformanceTypeEnum(String code, String desc) {
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
