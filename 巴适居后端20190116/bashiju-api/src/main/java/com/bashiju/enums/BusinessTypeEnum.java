/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessTypeEnum.java   
 * @Package com.bashiju.enums   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年9月20日 下午3:14:06   
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
 * @ClassName:  BusinessTypeEnum   
 * @Description:业务类型   
 * @author: yangz
 * @date:   2018年9月20日 下午3:14:06   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum BusinessTypeEnum {

	
	/**
	 * 新房成交业务
	 */
	NEWHOUSEDEAL("1","一手房成交"),
	
	/**
	 * 二手买卖成交
	 */
	BUSINESSDEAL("2","买卖成交"),
	
	/**
	 * 二手租赁成交
	 */
	LEASEDEAL("3","租赁成交"),
	
	/**
	 * 金融业务
	 */
	FINANCEDEAL("4","金融业务");
	
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (BusinessTypeEnum item : BusinessTypeEnum.values()) {
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
    public static BusinessTypeEnum parseOf(String value){
        for(BusinessTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    BusinessTypeEnum(String code, String desc) {
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
