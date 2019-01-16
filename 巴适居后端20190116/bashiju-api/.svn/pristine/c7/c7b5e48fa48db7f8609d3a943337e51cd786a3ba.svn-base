/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DepositManageEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年6月29日 下午2:44:33   
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
 * 定金管理（下定状态）枚举类
 * @ClassName:DepositManageEnum
 * @Description:定金管理（下定状态）枚举类
 * @author:zuoyuntao
 * @date:2018年6月29日 下午2:44:33
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum DepositManageEnum {
	
	UNCONFIRM("0","未确认"),
	
	CONFIRMED("1","已确认"),
	
	RETREATED("2","已退订"),
	
	PAYOFFED("3","已支出"),
	
	REBACKED("4","已驳回");
	
	/**
	 * 编码字段
	 */
	private String code;
	/**
	 * 字段描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  DepositManageEnum   
	 * @Description: 构造器
	 * @param:  code
	 * @param:  desc
	 */
	DepositManageEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

    static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (DepositManageEnum item : DepositManageEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            
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
