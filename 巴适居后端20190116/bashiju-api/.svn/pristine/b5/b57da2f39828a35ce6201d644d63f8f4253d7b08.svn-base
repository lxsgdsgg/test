package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 房源状态枚举
 * @ClassName:  HouseStatusEnum   
 * @Description:(房源状态枚举)   
 * @author: wangkaifa
 * @date:   2018年5月30日 上午10:23:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseStatusEnum {
		/** 有效 EFFECTIVE("1","1", "有效")**/
		EFFECTIVE("1","1", "有效"),
		/**我租MY_LEASE("2","2","我租"),**/
		MY_LEASE("2","2","我租"),
		/**我售MY_SELL("3","2","我售"),**/
		MY_SELL("3","2","我售"),
		/**他租OTHER_LEASE("4","3","他租"),**/
		OTHER_LEASE("4","3","他租"),
		/**他售OTHER_SELL("5","3","他售"),	**/
		OTHER_SELL("5","3","他售"),	
		/** 未知 UNKNOWN("6", "3","未知"),**/
		UNKNOWN("6", "3","未知"),
		/** 自住SELF_LIVING("7","3", "自住"), **/
		SELF_LIVING("7","3", "自住"),
		/** 出租中 IN_LEASE("8", "3","出租中"),**/
		IN_LEASE("8", "3","出租中"),
		/** 空置 VACANT("9","3", "空置")**/
		VACANT("9","3", "空置"),
		/**撤单 CANCEL("10","4","撤单")**/
		CANCEL("10","4","撤单"),
		/**已驳回REFUSED("10","3","已驳回")**/
		REFUSED("11","3","已驳回"),
		/**待审核WAIT_CONFIRMED("11","4","待确认")**/
		WAIT_CONFIRMED("12","4","待确认");


		public static Map<String , String> enumMap;
	    /** 房源状态下拉数据 **/
	    public static List<Map<String, String>> enumList;
	    static {
	        enumMap = new TreeMap<String, String>();
	        enumList=new ArrayList<>();
	        for (HouseStatusEnum item : HouseStatusEnum.values()) {
	            enumMap.put(item.code, item.desc);
	            Map<String, String> map=new HashMap<>();
	            map.put("name", item.desc);
	            map.put("type", item.type);
	            map.put("value", item.code);
	            enumList.add(map);
	        }
	    }

	    /**
	     * 枚举转换
	     */
	    public static HouseStatusEnum parseOf(String value){
	        for(HouseStatusEnum item : values())
	            if(item.getCode().equals(value))
	                return item;
	        
	        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
	    }


	    private String code;
	    private String type;
	    private String desc;

	    HouseStatusEnum(String code, String type,String desc) {
	        this.code = code;
	        this.type=type;
	        this.desc = desc;
	    }

	    public String getCode() {
	        return code;
	    }
	    public String getType() {
	        return type;
	    }

	    public String getDesc() {
	        return desc;
	    }
}
