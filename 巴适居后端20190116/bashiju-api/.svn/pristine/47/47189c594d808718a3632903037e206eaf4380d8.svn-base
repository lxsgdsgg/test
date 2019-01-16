/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  UserTypeEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年7月11日 上午9:19:16   
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
 * 用户类型枚举类
 * @ClassName:UserTypeEnum
 * @Description:用户类型枚举类
 * @author:zuoyuntao
 * @date:2018年7月11日 上午9:19:16
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum UserTypeEnum {

	INDEPENDENT_HOST("0","独立主机"),
	
	HOST("1","主机"),
	
	EXTENSION("2","分机");
	
	/**
	 * 编码字段
	 */
	private String code;
	/**
	 * 字段描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  UserTypeEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	UserTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

    static {
    	
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (UserTypeEnum item : UserTypeEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
    }
    
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
}
