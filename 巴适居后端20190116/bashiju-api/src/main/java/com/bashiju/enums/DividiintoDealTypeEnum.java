/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DividiintoDealTypeEnum.java   
 * @Package com.bashiju.enums   
 * @Description:(用一句话描述该文件做什么)   
 * @author: zuoyuntao     
 * @date:   2018年6月25日 上午9:56:56   
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
 * 分成配置交易类型枚举类
 * @ClassName:DividiintoDealTypeEnum
 * @Description:分成配置交易类型枚举类
 * @author:zuoyuntao
 * @date:2018年6月25日 上午9:56:56
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum DividiintoDealTypeEnum {
	/**交易类型--住宅**/
	DEAL_RESIDENCE("1","住宅"),
	/**交易类型--商业**/
	DEAL_BUSINESS("2","商业");
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
	DividiintoDealTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (DividiintoDealTypeEnum item : DividiintoDealTypeEnum.values()) {
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
