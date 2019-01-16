package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DealMoneyType   
 * @Description:成交费用类型   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DealMoneyTypeEnum {

	/**
	 * 评估费
	 */
	ASSESSMENT("00","评估费"),
	/**
	 * 托管房费
	 */
	TRUSTEESHIPHOUSE("01","托管房费"),
	/**
	 * 托管定金
	 */
	TRUSTEESHIPEARNEST("02","托管定金"),
	/**
	 * 托管租金
	 */
	TRUSTEESHIPRENT("03","托管租金"),
	/**
	 * 过户费
	 */
	TRANSFERFEE("04","过户费");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    DealMoneyTypeEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (DealMoneyTypeEnum _enum : DealMoneyTypeEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static DealMoneyTypeEnum parseOf(String value){
        for(DealMoneyTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
