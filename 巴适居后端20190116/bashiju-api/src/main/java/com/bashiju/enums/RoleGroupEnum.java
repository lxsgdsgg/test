/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RoleGroup.java   
 * @Package com.bashiju.manage.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月25日 上午11:51:13   
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
 * @ClassName:  RoleGroup   
 * @Description:角色组   
 * @author: yangz
 * @date:   2018年5月25日 上午11:51:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum RoleGroupEnum {
	/**
	 * 超级管理员
	 */
	ADMIN("01","超级管理员"),
	
	/**
	 * 城市管理员
	 */
	CITY_ADMIN("02","城市管理员"),
	
	/**
	 * 普通用户
	 */
	ORDINARY_USER("03","普通用户");
	

    public static Map<String , String> enumMap;

    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (RoleGroupEnum item : RoleGroupEnum.values()) {
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
    public static RoleGroupEnum parseOf(String value){
        for(RoleGroupEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    RoleGroupEnum(String code, String desc) {
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
