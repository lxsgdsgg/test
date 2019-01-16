/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MenuFunctionType.java   
 * @Package com.bashiju.manage.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月11日 上午9:44:46   
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
 * @ClassName:  AgentDivideTypeEnum   
 * @Description:中介分成类型
 * @author: yangz
 * @date:   2018年6月22日 上午16:22:46   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum AgentTypeEnum {
	
	/**
	 * 房源录入人
	 */
	HOUSERECORDER("1", "房源录入人","1"),
	/**
	 * 房源开盘人
	 */
	OPENDISCER("2", "房源开盘人","1"),
	/**
	 * 房源维护人
	 */
	HOUSEMAINTAINER("3", "房源维护人","1"),
	/**
	 * 客源录入人
	 */
	CUSTRECORDER("4", "客源录入人","1"),
	/**
	 * 客源维护人
	 */
	CUSTMAINTAINER("5", "客源维护人","1"),
	/**
	 * 合同成交人
	 */
	AGREEMENTTRADER("6", "合同成交人","1"),
	/**
	 * 房源委托人
	 */
	HOUSEENTRUSTER("7", "房源委托人","1"),
	/**
	 * 拿钥匙人
	 */
	RECIVEKEYER("8", "拿钥匙人","1"),
	/**
	 * 签独家人
	 */
	EXCLUSIVER("9", "签独家人","1"),
	/**
	 * 传照片人
	 */
	PICTURER("10", "传照片人","1"),
	/**
	 * 房源封盘人
	 */
	HOUSESEALINGPERSON("11", "房源封盘人","2"),
	/**
	 * 客源封盘人
	 */
	CUSTSEALINGPERSON("12", "客源封盘人","2");
	

    public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    
    /**
     * 分成相关的map
     */
    public static Map<String , String> enumDivideMap;
    static {
        enumMap = new TreeMap<String, String>();
        enumDivideMap = new TreeMap<String,String>();
        enumList=new ArrayList<>();
        for (AgentTypeEnum item : AgentTypeEnum.values()) {
            enumMap.put(item.code, item.desc);
            if("1".equals(item.type))
            	enumDivideMap.put(item.code, item.desc);
            Map<String , String> map=new HashMap<>();
            map.put("value", item.code);
            map.put("name", item.desc);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static AgentTypeEnum parseOf(String value){
        for(AgentTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;
    /**
     * 类型表示该经纪人是否参与分成，1表示是，2表示否
     */
    private String type;

    AgentTypeEnum(String code, String desc,String type) {
        this.code = code;
        this.desc = desc;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
