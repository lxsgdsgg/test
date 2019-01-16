package com.bashiju.customer.pojo;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  ExamineStatus   
 * @Description:审核状态   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ExamineStatusEnum {

	/**
	 * 未审核
	 */
	UNEXAMINED("0","未审核"),
	/**
	 * 已审核
	 */
	EXAMINED("1","已审核"),
	/**
	 * ，已驳回
	 */
	REJECT("2","已驳回");
	
	private String code;
    private String desc;
    
    public String getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    
    ExamineStatusEnum(String value, String displayName){
        this.code = value ;
        this.desc = displayName ;
    }
    
    public static Map<String, String> map = new TreeMap<String, String>();

    static {
        for (ExamineStatusEnum _enum : ExamineStatusEnum.values()) {
        	map.put(_enum.code, _enum.desc);
        }
    }
    
    /**
     * 枚举转换
     */
    public static ExamineStatusEnum parseOf(String value){
        for(ExamineStatusEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
