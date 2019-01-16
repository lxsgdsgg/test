/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FileTypeEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 下午5:48:21   
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
 * 日志资源类型 枚举类
 * @ClassName:LogSourceTypeEnum
 * @Description:文件类型 枚举类
 * @author:wangkaifa
 * @date:2018年8月7日 下午5:48:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum LogBusinessTypeEnum {
	/**HOUSE("1","房源")**/
	HOUSE("1","房源"),
	/**CUSTOMER("2","客源")**/
	CUSTOMER("2","客源"),
	/**NEWHOUSE("3","一手房源")**/
	NEWHOUSE("3","一手房源"),
	/**DEAL("4","成交")**/
	DEAL("4","成交"),
	/**FINANCE("5","财务")**/
	FINANCE("5","财务"),
	/**OA("6","办公")**/
	OA("6","办公");
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
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  TransferProcessEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	LogBusinessTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (LogBusinessTypeEnum item : LogBusinessTypeEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
    }

	/**
     * 枚举转换
     */
    public static LogBusinessTypeEnum parseOf(String value){
        for(LogBusinessTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
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
