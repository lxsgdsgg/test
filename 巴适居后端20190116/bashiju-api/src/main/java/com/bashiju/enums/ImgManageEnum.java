package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 图片管理类型
 * @ClassName:  BottomContentEnum   
 * @Description:(图片管理类型枚举)   
 * @author: liwen
 * @date:   2018年10月17日 上午10:23:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ImgManageEnum {
	/**
	 * 轮播图
	 */
	BANNERIMG("0","首页轮播图"), 
	
	/**
	 * 二手房主题图
	 */
	ERMAINIMG("1","二手房主题图"),
	
	/**
	 * 租房主题图
	 */
	RENTMAINIMG("2","租房主题图"),
	
	/**
	 * 微店宝主题图
	 */
	ACTIVITYMAINIMG("3","微店宝主题图"), 
	
	/**
	 * 卖房委托主题图
	 */
	SELLWTMAINIMG("4","卖房委托主题图"), 
	
	/**
	 * 租房委托主题图
	 */
	RENTWTMAINIMG("5","租房委托主题图"),
	
	/**
	 * 关注、小程序图
	 */
	PROGRAMIMG("6","关注、小程序图"),
	
	/**
	 * 新房背景图
	 */
	NEWHOUSEBGIMG("7","新房背景图");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (ImgManageEnum item : ImgManageEnum.values()) {
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
    public static ImgManageEnum parseOf(String value){
        for(ImgManageEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    ImgManageEnum(String code, String desc) {
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
