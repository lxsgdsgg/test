/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  BusinessExamineType.java   
 * @Package com.bashiju.housing.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月6日 上午10:20:00   
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
 * @ClassName:  BusinessExamineType   
 * @Description:审核状态   
 * @author: yangz
 * @date:   2018年6月6日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum ExamineStatusEnum {

	/**
	 * 未审核
	 */
	UNEXAMINED("0","未审核"),
	/**
	 * 已审核
	 */
	EXAMINED("1","已审核"),
	/**
	 * ，已驳回
	 */
	REJECT("2","已驳回");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (ExamineStatusEnum item : ExamineStatusEnum.values()) {
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
    public static ExamineStatusEnum parseOf(String value){
        for(ExamineStatusEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    ExamineStatusEnum(String code, String desc) {
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
