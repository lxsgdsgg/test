package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * PC端房源用途类型枚举
 * @ClassName:  HouseUseTypeEnum   
 * @Description:PC端房源用途类型枚举 
 * @author: zhaobin
 * @date:   2018年5月22日 下午3:32:58   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseUseTypeEnum {
	/** 住宅 HOUSE("house", "住宅")**/
	HOUSE("house", "住宅"),
	/** 别墅 VILLA("villa", "别墅")**/
	VILLA("villa", "别墅"),
	/**公寓APARTMENT("apartment","公寓")**/
	APARTMENT("apartment","公寓"),
	/** 商铺 SHOPS("shops", "商铺")**/
	SHOPS("shops", "商铺"),
	/** 写字楼 FFICE_BUILDING("officeBuiling", "写字楼")**/
	OFFICE_BUILDING("officeBuiling", "写字楼"),
	/** 车位 PARKING("parking", "车位")**/
	PARKING("parking", "车位"),
	/** OTHER("other", "其他")**/
    OTHER("other", "其他"),
    ;

    public static Map<String , String> enumMap;
    /**
     * 房源用途下拉数据源
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseUseTypeEnum item : HouseUseTypeEnum.values()) {
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
    public static HouseUseTypeEnum parseOf(String value){
        for(HouseUseTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    HouseUseTypeEnum(String code, String desc) {
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
