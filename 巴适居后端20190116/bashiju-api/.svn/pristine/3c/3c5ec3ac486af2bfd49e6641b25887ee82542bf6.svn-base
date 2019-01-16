package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 建筑结构枚举
 * @ClassName:  BulidTypeEnum   
 * @Description:建筑结构枚举
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum BulidTypeEnum {
	/** RIGID("1", "钢结构")**/
	RIGID("1", "钢结构"),
	/** CONCRETE("2", "钢、钢筋混凝土结构")**/
	RIGID_CONCRETE("2", "钢、钢筋混凝土结构"),
	/** CONCRETE("3", "钢筋混凝土结构")**/
	CONCRETE("3", "钢筋混凝土结构"),
	/** BLEND("4", "混合结构")**/
	BLEND("4", "混合结构"),
	/** BRICK("5", "砖木结构")**/
	BRICK("5", "砖木结构")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (BulidTypeEnum item : BulidTypeEnum.values()) {
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
    public static BulidTypeEnum parseOf(String value){
        for(BulidTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    BulidTypeEnum(String code, String desc) {
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
