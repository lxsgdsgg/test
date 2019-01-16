package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  CommissionProj   
 * @Description:佣金项目   
 * @author: yangz
 * @date:   2018年6月15日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum CommissionProjEnum {

	/**
	 * 中介费
	 */
	AGENCYFEE("00","中介费"),
	/**
	 * 违约金
	 */
	LIQUIDATEDDAMAGES("01","违约金"),
	/**
	 * 过户服务费
	 */
	TRANSFERSERVICE("02","过户服务费"),
	/**
	 * 房屋评估费
	 */
	ASSESSMENT("03","房屋评估费"),
	/**
	 * 贷款服务费
	 */
	LOANSERVICE("04","贷款服务费"),
	/**
	 * 代办过户费
	 */
	AGENCYTRANSFERSERVICE("05","代办过户费");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (CommissionProjEnum item : CommissionProjEnum.values()) {
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
    public static CommissionProjEnum parseOf(String value){
        for(CommissionProjEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    CommissionProjEnum(String code, String desc) {
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
