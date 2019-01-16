package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DealAttachType   
 * @Description:成交附件类型 
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DealAttachTypeEnum {

	/**
	 * 合同扫描件
	 */
	agreement("00","合同扫描件"),
	/**
	 * 业主材料扫描件
	 */
	OWNERMATERIAL("01","业主材料扫描件"),
	/**
	 * 客户材料扫描件
	 */
	CUSTOMERMATERIAL("02","客户材料扫描件");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    DealAttachTypeEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (DealAttachTypeEnum _enum : DealAttachTypeEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static DealAttachTypeEnum parseOf(String value){
        for(DealAttachTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
