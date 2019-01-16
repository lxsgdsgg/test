package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DealFollowType   
 * @Description:成交跟进回访类型  
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DealFollowTypeEnum {

	/**
	 * 一般回访
	 */
	GENERALVISIT("00","一般回访"),
	/**
	 * 法务回访
	 */
	FORENSICVISIT("01","法务回访"),
	/**
	 * 客服回访
	 */
	CLIENTVISIT("02","客服回访"),
	/**
	 * 权证回访
	 */
	WARRANTVISIT("03","权证回访");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    DealFollowTypeEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (DealFollowTypeEnum _enum : DealFollowTypeEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static DealFollowTypeEnum parseOf(String value){
        for(DealFollowTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
