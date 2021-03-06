package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  SettlementType   
 * @Description:结算方式   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum SettlementTypeEnum {

	/**
	 * 现金
	 */
	CASH("0","现金"),
	/**
	 * 扫码支付
	 */
	SCANCODE("1","扫码支付"),
	/**
	 * 银行转账
	 */
	BANK("2","银行转账"),
	/**
	 * pos机刷卡
	 */
	POS("3","pos机刷卡"),
	/**
	 * 支票
	 */
	CHEQUE("4","支票");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    SettlementTypeEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (SettlementTypeEnum _enum : SettlementTypeEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static SettlementTypeEnum parseOf(String value){
        for(SettlementTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
