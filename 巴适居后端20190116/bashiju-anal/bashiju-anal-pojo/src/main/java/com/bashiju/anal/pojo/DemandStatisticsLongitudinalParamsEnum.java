/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseStatisticsParamsEnum.java   
 * @Package com.bashiju.anal.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月17日 下午3:20:10   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DemandStatisticsLongitudinalParamsEnum   
 * @Description:需求统计纵向维度信息   
 * @author: yangz
 * @date:   2018年11月17日 下午3:20:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DemandStatisticsLongitudinalParamsEnum {

	L1("01","状态"),
	L2("02","行政区"),
	L3("03","面积区间"),
	L4("04","客户来源"),
	L5("05","价格区间"),
	L6("06","用途"),
	L7("07","客源等级");
	
	private String code;
    private String desc;
    
    
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	private DemandStatisticsLongitudinalParamsEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    
    
	static {
		
		enumMap = new TreeMap<String, String>();
		enumList = new ArrayList<Map<String,String>>();
		
		for(DemandStatisticsLongitudinalParamsEnum  item: DemandStatisticsLongitudinalParamsEnum.values()) {
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
    public static DemandStatisticsLongitudinalParamsEnum parseOf(String value){
        for(DemandStatisticsLongitudinalParamsEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
