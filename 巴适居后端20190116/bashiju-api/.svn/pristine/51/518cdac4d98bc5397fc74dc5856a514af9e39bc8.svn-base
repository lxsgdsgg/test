/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  EducationEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年8月8日 下午5:22:00   
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
 * （简单描述）
 * @ClassName:EducationEnum
 * @Description:（详细描述）
 * @author:zuoyuntao
 * @date:2018年8月8日 下午5:22:00
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum EducationEnum {
	/**高中及以上**/
	HighSchoolOrOver("0","高中及以上"),
	/**大专及以上**/
	JuniorCollegeOrOver("1","大专及以上"),
	/**本科及以上**/
	UndergraduateCollegeOrOver("2","本科及以上"),
	/**硕士及以上**/
	MasterCollegeOrOver("3","硕士及以上"),
	/**博士及以上**/
	DoctorCollegeOrOver("4","博士及以上");
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String,Object>> enumList;
	/**
	 * @Title:  TransferProcessEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	EducationEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (EducationEnum item : EducationEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
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
	
}
