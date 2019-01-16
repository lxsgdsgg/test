/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  Doctype.java   
 * @Package com.bashiju.manage.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年5月4日 上午11:01:16   
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
 * @ClassName:  Doctype   
 * @Description:法人证件类型   
 * @author: yangz
 * @date:   2018年5月4日 上午11:01:16   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DoctypeEnum {

	/**
	 * 营业执照编码
	 */
	DOCTYPE01("01","营业执照编码"),
	/**
	 * 统一社会信用代码
	 */
	DOCTYPE02("02","统一社会信用代码"),
	/**
	 * 组织机构代码证
	 */
	DOCTYPE03("03","组织机构代码证"),
	/**
	 * 经营许可证
	 */
	DOCTYPE04("04","经营许可证"),
	/**
	 * 税务登记证
	 */
	DOCTYPE05("05","税务登记证"),
	/**
	 * 其他
	 */
	DOCTYPE99("99","其他");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (DoctypeEnum item : DoctypeEnum.values()) {
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
    public static DoctypeEnum parseOf(String value){
        for(DoctypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    DoctypeEnum(String code, String desc) {
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
