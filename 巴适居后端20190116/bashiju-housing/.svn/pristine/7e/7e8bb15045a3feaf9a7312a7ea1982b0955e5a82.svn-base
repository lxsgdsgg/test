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
package com.bashiju.housing.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  BusinessExamineType   
 * @Description:房源业务审核处理方式枚举类(主要针对"房源举报"的审核类型有效)   
 * @author: yangz
 * @date:   2018年6月6日 上午10:20:00   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum BusinessExamineHandleTypeEnum {

	/**
	 * 房源失效(撤单)
	 */
	CANCEL("01","房源失效(撤单)"),
	/**
	 * 放入公盘
	 */
	PUBICDISC("02","放入公盘"),
	/**
	 * 放入淘宝
	 */
	TAOBAOPOOL("03","放入淘宝"),
	/**
	 * 转移给其他人
	 */
	TRANSFER("04","转移给其他人");
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (BusinessExamineHandleTypeEnum item : BusinessExamineHandleTypeEnum.values()) {
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
    public static BusinessExamineHandleTypeEnum parseOf(String value){
        for(BusinessExamineHandleTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    BusinessExamineHandleTypeEnum(String code, String desc) {
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
