
package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  HandleStaus   
 * @Description:预约看房处理状态   
 * @author: wangpeng
 * @date:   2018年8月4日 下午3:29:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum HandleStatusEnum {
		
	/**
	 * 未处理
	 */
	UNDISPOSED("0","未处理的"),
	/**
	 * 已处理
	 */
	ALREADYPROCESSED("1","已处理"),
	/**
	 * 已处理
	 */
	CANCELED("2","已取消");
	
	
	private String code;
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private HandleStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
	public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HandleStatusEnum item : HandleStatusEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String, String> map=new HashMap<>();
            map.put("name", item.desc);
            map.put("value", item.code);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static HandleStatusEnum parseOf(String value){
        for(HandleStatusEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
	
}
