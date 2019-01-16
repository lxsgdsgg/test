package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *   首付契税类型
 * @ClassName:  HouseDeedTaxEnum   
 * @Description:  首付契税类型
 * @author: wangkaifa
 * @date:   2018年12月28日 上午10:20:29       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseDeedTaxEnum {

	/**
	 * ONE_2YEAR_90AREA("one2Year90Area","首套、满两年、90平米以上"),
	 */
	ONE_2YEAR_90AREA("one2Year90Area","首套、满两年、90平米以上",1,1,0,0,1),
	
	/**
	 * ONE_90AREA("one90Area","首套、不满两年、90平米以上"),
	 */
	ONE_90AREA("one90Area","首套、不满两年、90平米以上",0,1,0,0,1),
	/**
	 * ONE_2YEAR("one2Year","首套、满两年、90平米以下"),
	 */
	ONE_2YEAR("one2Year","首套、满两年、90平米以下",1,1,0,0,0),
	/**
	 * ONE("one","首套、不满两年、90平米以下"),
	 */
	ONE("one","首套、不满两年、90平米以下",0,1,0,0,0),
	/**
	 * TWO_2YEAR_90AREA("two2Year90Area","二套、满两年、90平米以上"),
	 */
	TWO_2YEAR_90AREA("two2Year90Area","二套、满两年、90平米以上",1,0,1,0,1),
	
	/**
	 * TWO_90AREA("two90Area","二套、不满两年、90平米以上"),
	 */
	TWO_90AREA("two90Area","二套、不满两年、90平米以上",0,0,1,0,1),
	/**
	 * TWO_2YEAR("two2Year","二套、满两年、90平米以下"),
	 */
	TWO_2YEAR("two2Year","二套、满两年、90平米以下",1,0,1,0,0),
	/**
	 * TWO("two","二套、不满两年、90平米以下"),
	 */
	TWO("two","二套、不满两年、90平米以下",0,0,1,0,0),
	/**
	 * THREE_2YEAR_90AREA("three2Year90Area","三套、满两年、90平米以上"),
	 */
	THREE_2YEAR_90AREA("three2Year90Area","三套、满两年、90平米以上",1,0,0,1,1),
	
	/**
	 * THREE_90AREA("three90Area","三套、不满两年、90平米以上"),
	 */
	THREE_90AREA("three90Area","三套、不满两年、90平米以上",0,0,0,1,1),
	/**
	 * THREE_2YEAR("three2Year","三套、满两年、90平米以下"),
	 */
	THREE_2YEAR("three2Year","三套、满两年、90平米以下",1,0,0,1,0),
	/**
	 * THREE("three","三套、不满两年、90平米以下")
	 */
	THREE("three","三套、不满两年、90平米以下",0,0,0,1,0)
	;
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , Object>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseDeedTaxEnum item : HouseDeedTaxEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String , Object> map=new HashMap<>();
            map.put("value", item.code);
            map.put("name", item.desc);
            map.put("isTwoYear", item.isTwoYear);
            map.put("isFirst", item.isFirst);
            map.put("isTwo", item.isTwo);
            map.put("isThree", item.isThree);
            map.put("isLimitAreaAbove", item.isLimitAreaAbove);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static HouseDeedTaxEnum parseOf(String value){
        for(HouseDeedTaxEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    
    private String code;
    private String desc;
    private int isTwoYear;
    private int isFirst;
    private int isTwo;
    private int isThree;
    private int isLimitAreaAbove;
    
    HouseDeedTaxEnum(String code, String desc,int isTwoYear,int isFirst,int isTwo,int isThree,int isLimitAreaAbove) {
        this.code = code;
        this.desc = desc;
        this.isTwo=isTwo;
        this.isTwoYear=isTwoYear;
        this.isFirst=isFirst;
        this.isThree=isThree;
        this.isLimitAreaAbove=isLimitAreaAbove;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

	public int getIsTwoYear() {
		return isTwoYear;
	}

	public int getIsFirst() {
		return isFirst;
	}

	public int getIsTwo() {
		return isTwo;
	}

	public int getIsThree() {
		return isThree;
	}

	public int getIsLimitAreaAbove() {
		return isLimitAreaAbove;
	}

}
