package com.bashiju.enums;

import java.time.LocalDate;

public class NumberCount {
	/**同一房源电话每天被查看次数**/
	public static final String HS_LIMIT_COUNT="HS_LIMIT_COUNT";//同一房源电话每天被查看次数
	/**用户每天查看电话次数**/
	public static final String HS_LOOKPHONE_COUNT="HS_LOOKPHONE_COUNT";//用户每天查看电话次数
	/**用户5分钟查看电话次数**/
	public static final String HS_5MINLOOKPHONE_COUNT="HS_5MINLOOKPHONE_COUNT";//用户5分钟查看电话次数
	/**用户每天淘宝房源 数量**/
	public static final String HS_TAOBAO_COUNT="HS_TAOBAO_COUNT";//用户每天淘宝房源 数量
	/**用户每天查看门牌号次数**/
	public static final String HS_LOOKROOM_COUNT="HS_LOOKROOM_COUNT";//用户每天查看门牌号次数
	/**同一客源电话每天被查看次数**/
	public static final String CUST_LIMIT_COUNT="CUST_LIMIT_COUNT";//同一客源电话每天被查看次数
	/**用户每天淘宝客源数量**/
	public static final String CUST_TAOBAO_COUNT="CUST_TAOBAO_COUNT";//用户每天淘宝客源数量
	
	
	/**
	 * 
		 * 按当天生成用户redis的key值
		 * @Description: 按当天生成用户redis的key值） 
		 * @param userPhone
		 * @return 
		 * String
	 */
	public static String getUserKey(String userPhone) { 
		String date = LocalDate.now().toString().replaceAll("-", "");
		return date+"UserKey"+userPhone;
	}
	/*
	 * 按当天生成房源redis的key值
	 */
	public static String getHouseKey(Long id) { 
		String date = LocalDate.now().toString().replaceAll("-", "");
		return date+"HouseKey"+id;
	}
	/*
	 * 按当天生成客源redis的key值
	 */
	public static String getCustomerKey(String id) { 
		String date = LocalDate.now().toString().replaceAll("-", "");
		return date+"CustomerKey"+id;
	}
}
