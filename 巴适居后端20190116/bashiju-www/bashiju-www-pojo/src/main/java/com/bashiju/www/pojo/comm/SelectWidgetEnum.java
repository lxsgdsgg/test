package com.bashiju.www.pojo.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *   动态下拉控件编号枚举
 * @ClassName:  SelectWidgetEnum   
 * @Description:   动态下拉控件编号枚举
 * @author: wangkaifa
 * @date:   2018年7月24日 上午11:29:07       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum SelectWidgetEnum {
	/** 朝向ORIENTATIONS("orientations", "朝向")**/
	ORIENTATIONS("orientations", "朝向"),
	/** 楼龄BUILDYEARS("buildyears", "楼龄")**/
	BUILDYEARS("buildyears", "楼龄"),
	/** 楼层HOUSETYPES("housetypes","楼层")**/
	HOUSETYPES("housetypes","楼层"),
	/** 用途HOUSEUSES("houseuses","用途")**/
	HOUSEUSES("houseuses","用途")
    ;
	public static  Map<String, String> enumMap;
    /** 区间控件编号枚举 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (SelectWidgetEnum item : SelectWidgetEnum.values()) {
        	enumMap.put(item.code, item.desc);
            Map<String, Object> map=new HashMap<>();
            map.put("name", item.desc);
            map.put("value", item.code);
            enumList.add(map);
        }
    }

    /**
     * 枚举转换
     */
    public static SelectWidgetEnum parseOf(String value){
        for(SelectWidgetEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    SelectWidgetEnum(String code, String desc) {
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
