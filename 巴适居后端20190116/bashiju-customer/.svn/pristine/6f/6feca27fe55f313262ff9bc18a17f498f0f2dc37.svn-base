package com.bashiju.customer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
/**
 * 
 *   需求编号生成工具类
 * @ClassName:  MenuController   
 * @Description:   需求编号生成工具类
 * @author: wangkaifa
 * @date:   2018年6月14日 下午6:27:07       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class CustomerCodeTool {
	private static final int MIN_VALUE=0;//最小值
	private static final int MAX_VALUE=50;//最大值
	private static int currentValue=MIN_VALUE;//当前值
	
	/**
	 * 
		 * 需求编号生成方法
		 * @Description: 需求编号生成方法，按照当前时间（精确到秒）+自增数字（达到最大值，最小值开始从）
		 * @return 14位数字
		 * String
	 */
	public static synchronized  String createCustomerCode() {
		String time= LocalDateTime.now().format(new DateTimeFormatterBuilder().appendPattern("yyMMddHHmmss").toFormatter());
		currentValue++;
		if (currentValue>MAX_VALUE) {
			currentValue=MIN_VALUE+1;
		}
		return time +  String.format("%02d", currentValue);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 110; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(CustomerCodeTool.createCustomerCode());
				}
			}).start();
			
		}
		
	}
}
