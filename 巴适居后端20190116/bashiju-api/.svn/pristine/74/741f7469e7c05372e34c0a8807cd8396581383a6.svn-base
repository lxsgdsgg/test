package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 楼层类型枚举
 * @ClassName:  FloorTypeEnum   
 * @Description:楼层类型枚举
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum FloorTypeEnum {
	/** BOTTOM("1", "底层")**/
	BOTTOM("1", "底层"),
	/** LOWFLOOR("2", "低楼层")**/
	LOWFLOOR("2", "低楼层"),
	/** MIDDLEFLOOR("3", "中楼层")**/
	MIDDLEFLOOR("3", "中楼层"),
	/** HIGHTFLOOR("4", "高楼层")**/
	HIGHTFLOOR("4", "高楼层"),
	/** TOPFLOOR("5", "顶层")**/
	TOPFLOOR("5", "顶层")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (FloorTypeEnum item : FloorTypeEnum.values()) {
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
    public static FloorTypeEnum parseOf(String value){
        for(FloorTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    FloorTypeEnum(String code, String desc) {
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
