package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 底部内容类型
 * @ClassName:  BottomContentEnum   
 * @Description:(底部内容类型枚举)   
 * @author: liwen
 * @date:   2018年8月01日 上午10:23:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum BottomContentEnum {
	/**
	 * 关于我们
	 */
	GYWM("0","关于我们"),
	
	/**
	 * 用户协议
	 */
	YHXY("1","用户协议"),
	/**
	 * 服务协议
	 */
	FWXY("2","服务协议"),
	/**
	 * 隐私政策
	 */
	YSZC("3","隐私政策"),
	/**
	 * 赔付标准
	 */
	PFBZ("4","赔付标准");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (BottomContentEnum item : BottomContentEnum.values()) {
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
    public static BottomContentEnum parseOf(String value){
        for(BottomContentEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    BottomContentEnum(String code, String desc) {
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
