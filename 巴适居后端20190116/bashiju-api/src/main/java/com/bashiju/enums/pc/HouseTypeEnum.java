package com.bashiju.enums.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 建筑类型枚举
 * @ClassName:  HouseTypeEnum   
 * @Description:建筑类型枚举
 * @author: wangkaifa
 * @date:   2018年5月30日 上午9:49:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseTypeEnum {
	/** LOW("1", "低层住宅")**/
	LOW("1", "低层住宅"),
	/** MANY("2", "多层住宅")**/
	MANY("2", "多层住宅"),
	/** MIDDLE("3", "中高层住宅")**/
	MIDDLE("3", "中高层住宅"),
	/** HIGHT("4", "高层住宅")**/
	HIGHT("4", "高层住宅"),
	/** OTHER("5", "其他形式住宅")**/
	OTHER("5", "其他形式住宅")
    ;
	public static Map<String , String> enumMap;
    /** 户型枚举下拉数据 **/
    public static List<Map<String, Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseTypeEnum item : HouseTypeEnum.values()) {
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
    public static HouseTypeEnum parseOf(String value){
        for(HouseTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }


    private String code;
    private String desc;

    HouseTypeEnum(String code, String desc) {
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
