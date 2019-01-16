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
package com.bashiju.manage.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DeptTypeLevel   
 * @Description:部门类型等级   
 * @author: yangz
 * @date:   2018年4月11日 上午9:44:46   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DeptTypeLevelEnum {
	/**
	 * 一级
	 */
	LEVEL1("1","一级"),
	/**
	 * 二级
	 */
	LEVEL2("2","二级"),
	/**
	 * 三级
	 */
	LEVEL3("3","三级"),
	/**
	 * 四级
	 */
	LEVEL4("4","四级"),
	/**
	 * 五级
	 */
	LEVEL5("5","五级"),
	/**
	 * 六级
	 */
	LEVEL6("6","六级"),
	/**
	 * 七级
	 */
	LEVEL7("7","七级");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (DeptTypeLevelEnum item : DeptTypeLevelEnum.values()) {
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
    public static DeptTypeLevelEnum parseOf(String value){
        for(DeptTypeLevelEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    DeptTypeLevelEnum(String code, String desc) {
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
