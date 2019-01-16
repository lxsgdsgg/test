package com.bashiju.www.service.user.impl;

import java.net.URLEncoder;
import java.util.Random;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public  class IndustrySMS
{

	//生成随机数
    public  static String  getRandomNum(){
        Random random=new Random();
        String randomNum = random.nextInt(1000000) + "";
        if(randomNum.length()!=6){
            System.out.println("6位伪随机数："+randomNum);
            return  getRandomNum();
        }
        System.out.println("6位随机数："+randomNum);
        return randomNum;
    }
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String to = "13577000337";
	private static String smsContent = "【巴适居】尊敬的用户，您的验证码为"+getRandomNum()+"";
	/**
	 * 验证码通知短信
	 */
	public static void execute()
	{
		String tmpSmsContent = null;
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
	        + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + result);
	    System.out.println("+++++++++++++++++++++++");
	    System.out.println(result);
	}
}
