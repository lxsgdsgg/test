/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  Legdoctype.java   
 * @Package com.bashiju.manage.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月4日 上午11:04:49   
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
 * @ClassName:  Legdoctype   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年5月4日 上午11:04:49   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum LegdoctypeEnum {
	
	/**
	 * 身份证
	 */
	LEGDOCTYPE01("01","身份证"),
	/**
	 * 护照
	 */
	LEGDOCTYPE02("02","护照"),
	/**
	 * 军官证
	 */
	LEGDOCTYPE03("03","军官证"),
	/**
	 * 户口簿
	 */
	LEGDOCTYPE04("04","户口簿"),
	/**
	 * 士兵证
	 */
	LEGDOCTYPE05("05","士兵证"),
	/**
	 * 港澳来往内地通行证
	 */
	LEGDOCTYPE06("06","港澳来往内地通行证"),
	/**
	 * 台湾同胞来往内地通行证
	 */
	LEGDOCTYPE07("07","台湾同胞来往内地通行证"),
	/**
	 * 临时身份证
	 */
	LEGDOCTYPE08("08","临时身份证"),
	/**
	 * 外国人居留证
	 */
	LEGDOCTYPE09("09","外国人居留证"),
	/**
	 * 警官证
	 */
	LEGDOCTYPE10("10","警官证"),
	/**
	 * 其他
	 */
	LEGDOCTYPE99("99","其他");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (LegdoctypeEnum item : LegdoctypeEnum.values()) {
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
    public static LegdoctypeEnum parseOf(String value){
        for(LegdoctypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    LegdoctypeEnum(String code, String desc) {
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
