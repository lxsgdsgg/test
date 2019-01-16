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
 * @ClassName:  HouseStatisticsParamsEnum   
 * @Description:房源统计横向维度信息   
 * @author: yangz
 * @date:   2018年11月17日 下午3:20:10   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseStatisticsTransverseParamsEnum {

	T1("01","大区"),
	T2("02","区"),
	T3("03","店"),
	T4("04","组"),
	T5("05","员工"),
//	T6("06","员工(组)"),
	T7("07","行政区"),
	T8("08","用途"),
	T9("09","状态"),
	T10("10","客户来源");
	
	private String code;
    private String desc;
    
    
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	private HouseStatisticsTransverseParamsEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static Map<String , String> enumMap;
    public static List<Map<String , String>> enumList;
    
    
	static {
		
		enumMap = new TreeMap<String, String>();
		enumList = new ArrayList<Map<String,String>>();
		
		for(HouseStatisticsTransverseParamsEnum  item: HouseStatisticsTransverseParamsEnum.values()) {
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
    public static HouseStatisticsTransverseParamsEnum parseOf(String value){
        for(HouseStatisticsTransverseParamsEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
