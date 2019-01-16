package com.bashiju.common.service.dealservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

import org.apache.commons.lang3.StringUtils;

import com.bashiju.enums.DealTypeEnum;
import com.bashiju.utils.exception.BusinessException;
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
public class DealCodeTool {
	private static final int MIN_VALUE=0;//最小值
	private static final int MAX_VALUE=50;//最大值
	private static int currentValue=MIN_VALUE;//当前值
	
	/**
	 * 
		 * 成交编号生成方法
		 * @Description: 需求编号生成方法，按照当前时间（精确到秒）+自增数字（达到最大值，最小值开始从）
		 * @return 14位数字
		 * String
	 */
	public static synchronized  String createDealCode(String dealType) {
		if(StringUtils.isEmpty(dealType))
			throw new BusinessException("交易类型不允许为空");
		if(!DealTypeEnum.enumMap.containsKey(dealType))
			throw new BusinessException("交易类型不正确");
		String prefix = "MM";//默认前缀为MM
		if(DealTypeEnum.LEASEDEAL.getCode().equals(dealType))
			prefix = "ZL";
		String time= LocalDateTime.now().format(new DateTimeFormatterBuilder().appendPattern("yyMMddHHmmss").toFormatter());
		currentValue++;
		if (currentValue>MAX_VALUE) {
			currentValue=MIN_VALUE+1;
		}
		return prefix+"-"+time +  String.format("%02d", currentValue);
	}
	
	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		for (int i = 0; i < 110; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(DealCodeTool.createDealCode("00"));
				}
			}).start();
		}
		System.out.println("耗时："+Long.toString(System.currentTimeMillis()-start));
		
	}
}
