package com.bashiju.enums;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  ReservationTimeEnum   
 * @Description:预约看房时间段
 * @author: wangpeng
 * @date:   2018年8月3日 下午4:36:33   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum ReservationTimeEnum {
	/**
	 * 全天可看
	 */
	EVEVYTIME("0","全天可看"),
	/**
	 * 上午(08:00-12:00)
	 */
	MORNING("1","上午(08:00-12:00)"),
	/**
	 * 下午(12:00-18:00)
	 */
	AFTERNOON("2","下午(12:00-18:00)"),
	/**
	 * 晚上(18:00-21:00)
	 */
	NIGHT("3","晚上(18:00-21:00");
	
	   private String code;
       private String desc;
       
       
	private ReservationTimeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public static Map<String, String> enumMap;
	
	public static List<Map<String,String>> enumList;
	
	static {
		
		enumMap = new TreeMap<String, String>();
		enumList = new ArrayList<Map<String,String>>();
		
		for(ReservationTimeEnum  item: ReservationTimeEnum.values()) {
			enumMap.put(item.code,item.desc);
			Map<String, String> map = new  HashMap<String, String>();
			map.put("name",item.desc);
			map.put("value", item.code);
			enumList.add(map);
		}
	}
	  /**
     * 枚举转换
     */
    public static ReservationTimeEnum parseOf(String value){
        for(ReservationTimeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
	
}
