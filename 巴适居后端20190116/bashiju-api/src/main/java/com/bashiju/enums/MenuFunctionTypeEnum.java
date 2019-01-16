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
 * 菜单功能类型枚举 
 * @ClassName   MenuFunctionType   
 * @Description 菜单功能类型   
 * 	<p>MENU("menu","菜单"),</p>
*	<p>ONLYBUTTON("onlyButton","唯一按钮"),</p>
*	<p>MULTIPLEBUTTON("multipleButton","重复按钮")</p>
*	<p>COMBINATIONBUTTON("combinationButton","条件按钮")</p>
 * @author  yangz
 * @date    2018年4月11日 上午9:44:46   
 *     
 * @Copyright  2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum MenuFunctionTypeEnum {

	MENU("menu","菜单"),
	ONLYBUTTON("onlyButton","唯一按钮"),
	MULTIPLEBUTTON("multipleButton","重复按钮"),
	COMBINATIONBUTTON("combinationButton","条件按钮");
	

    public static Map<String , String> enumMap;

    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (MenuFunctionTypeEnum item : MenuFunctionTypeEnum.values()) {
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
    public static MenuFunctionTypeEnum parseOf(String value){
        for(MenuFunctionTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    MenuFunctionTypeEnum(String code, String desc) {
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
