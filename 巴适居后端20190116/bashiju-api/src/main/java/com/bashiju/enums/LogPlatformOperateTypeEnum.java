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
 * 平台资源操作类型 枚举类
 * @ClassName:LogPlatformOperateTypeEnum
 * @Description:平台资源操作类型 枚举类
 * @author:wangkaifa
 * @date:2018年8月7日 下午5:48:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum LogPlatformOperateTypeEnum {
	//公共操作
	/**ADD("1","新增")**/
	ADD("1","新增"),
	/**UPDATE("2","修改")**/
	UPDATE("2","修改"),
	/**DELETE("3","删除")**/
	DELETE("3","删除"),
	/**AUTHORIZATION("4","授权")**/
	AUTHORIZATION("4","授权"),
	/**SETTING("5","设置")**/
	SETTING("5","设置"),
	
	
	
	//用户操作
	/**LOCK("7","锁定用户"),**/
	LOCK("7","锁定用户"),
	/**UNLOCK("8","锁定用户"),**/
	UNLOCK("8","锁定用户"),
	/**RESET_PASSWORD("9","重置密码"),**/
	RESET_PASSWORD("9","重置密码"),
	/**UPLOAD_PORTRAIT("10","上传头像"),**/
	UPLOAD_PORTRAIT("10","上传头像"),
	//部门操作
	
	//职位操作
	
	
	//角色操作
	
	
	
	//注册审核
	
	//成交设置
	/**TRANSFER_PROCESS_ADD("433","新增过户流程"),**/
	TRANSFER_PROCESS_ADD("433","新增过户流程"),
	/**TRANSFER_PROCESS_EDIT("434","修改过户流程"),**/
	TRANSFER_PROCESS_EDIT("434","修改过户流程"),
	/**TRANSFER_PROCESS_DELETE("435","删除过户流程"),**/
	TRANSFER_PROCESS_DELETE("435","删除过户流程"),
	/**TRANSFER_PROCESS_CONFIG("436","设置过户流程"),**/
	TRANSFER_PROCESS_CONFIG("436","设置过户流程"),
	;
	
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
	LogPlatformOperateTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (LogPlatformOperateTypeEnum item : LogPlatformOperateTypeEnum.values()) {
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
    public static LogPlatformOperateTypeEnum parseOf(String value){
        for(LogPlatformOperateTypeEnum item : values())
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
