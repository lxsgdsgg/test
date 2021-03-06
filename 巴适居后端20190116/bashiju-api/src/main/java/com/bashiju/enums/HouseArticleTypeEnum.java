package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @ClassName:  HouseArticleType   
 * @Description:房产文章类型
 * @author: wangpeng
 * @date:   2018年7月24日 上午10:13:32   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseArticleTypeEnum {
	/**
	 * 房产百科
	 */
	PROPERTYENCYCLOPEDIA("0","房产百科"),
	/**
	 * 房产资讯
	 */
	PROPERTYINFORMATION("1","房产资讯");
	 public static Map<String , String> enumMap;
	    /** 房源等级下拉数据 **/
	    public static List<Map<String, String>> enumList;
	    static {
	        enumMap = new TreeMap<String, String>();
	        enumList=new ArrayList<>();
	        for (HouseArticleTypeEnum item : HouseArticleTypeEnum.values()) {
	            enumMap.put(item.code, item.desc);
	            Map<String, String> map=new HashMap<>();
	            map.put("name", item.desc);
	            map.put("value", item.code);
	            enumList.add(map);
	        }
	    }

	    /**
	     * 枚举转换
	     */
	    public static HouseArticleTypeEnum parseOf(String value){
	        for(HouseArticleTypeEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }


	    private String code;
	    private String desc;

	    HouseArticleTypeEnum(String code, String desc) {
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
