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
 * 系统资源类型 枚举类
 * @ClassName:LogSystemSourceTypeEnum
 * @Description:系统资源类型 枚举类
 * @author:wangkaifa
 * @date:2018年8月7日 下午5:48:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum LogSystemSourceTypeEnum {
	/**MENU("1","菜单"),**/
	MENU("1","菜单"),
	/**DEPARTMENTTYPE("2","部门类型"),**/
	DEPARTMENTTYPE("2","部门类型"),
	/**REGION("3","片区"),**/
	REGION("3","片区"),
	/**COMMUNITY("4","小区"),**/
	COMMUNITY("4","小区"),
	/**SCHOOL("5","学区"),**/
	SCHOOL("5","学区"),
	/**RANGE_SETTING("6","区间配置"),**/
	RANGE_SETTING("6","区间配置"),
	/**ADD_SELECT("7","新增动态配置"),**/
	ADD_SELECT("7","新增动态配置"),
	/**SET_SELECT_VALUE("8","设置下拉值"),**/
	SET_SELECT_VALUE("8","设置下拉值"),
	/**DYNAMICFORM("9","动态表单配置"),**/
	DYNAMICFORM("9","动态表单配置"),
	/**CITY_MANAGE("10","城市管理分配"),**/
	CITY_MANAGE("10","城市管理分配"),
	/**METRO_LINE("11","地铁线路管理"),**/
	METRO_LINE("11","地铁线路管理"),
	/**METRO_STATION("12","地铁站点管理"),**/
	METRO_STATION("12","地铁站点管理"),
	/**COLLECTION_URL("13","采集地址配置"),**/
	COLLECTION_URL("13","采集地址配置"),
	/**COLLECTION_TEMPLATE("14","采集模板配置"),**/
	COLLECTION_TEMPLATE("14","采集模板配置"),
	/**TASK_MANAGE("15","任务管理配置"),**/
	TASK_MANAGE("15","任务管理配置"),
	/**ARTICLETYPE("16","房产文章分类"),**/
	ARTICLETYPE("16","房产文章分类"),
	/**SETTING_DECISION("17","决策配置"),**/
	SETTING_DECISION("17","决策配置"),
	/**PHONE_SECURITY("18","电话号码安全管理"),**/
	PHONE_SECURITY("18","电话号码安全管理"),
	/**OPEN_CITY("19","平台开通城市"),**/
	OPEN_CITY("19","平台开通城市"),
	/**COMPANY("20","公司管理"),**/
	COMPANY("20","公司管理"),
	/**AGENCY_BLACKLIST("21","中介黑名单")**/
	AGENCY_BLACKLIST("21","中介黑名单"),
	/**COMPANY_OPEN_CITY("22","公司开通城市")**/
	COMPANY_OPEN_CITY("22","公司开通城市"),
	/**NAVIGATION("23","导航栏管理")**/
	NAVIGATION("23","导航栏管理"),
	/**PC_PICTURE("24","图片管理")**/
	PC_PICTURE("24","图片管理"),
	/**HOUSE_ZIXUN("25","房产资讯")**/
	HOUSE_ZIXUN("25","房产资讯"),
	/**HOUSE_BAIKE("26","房产百科")**/
	HOUSE_BAIKE("26","房产百科"),
	/**ARTICLE_CHECKED("27","文章内容审核")**/
	ARTICLE_CHECKED("27","文章内容审核"),
	/**BOTTOM_NAVIGATION("28","网站底部内容")**/
	BOTTOM_NAVIGATION("28","网站底部内容"),
	/**HOUSE_TIPOFF("29","网站客户举报房源")**/
	HOUSE_TIPOFF("29","网站客户举报房源"),
	/**LABEL_MANAGE("30","标签管理")**/
	LABEL_MANAGE("30","标签管理"),
	/**WEB_TIPOFF("31","网站客户反馈")**/
	WEB_TIPOFF("31","网站客户反馈"),
	/**WEB_TIPOFF("32","分成配置")**/
	DIVIDEINTO_SET("32","分成配置");
	
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
	LogSystemSourceTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (LogSystemSourceTypeEnum item : LogSystemSourceTypeEnum.values()) {
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
    public static LogSystemSourceTypeEnum parseOf(String value){
        for(LogSystemSourceTypeEnum item : values())
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
