package com.bashiju.base.BaseGlobal;

public class BaseGlobal {
	
	/**房源绑定码超时时间(小时)*/
	public final static int HOUSEBINDCODEEXPTIME=48;
	/**客源绑定码超时时间(小时)*/
	public final static int CUSTOMBINDCODEEXPTIME=48;
	/**(秒转小时)*/
	public final static int SECONDSTOHOURS=60*60;
	/**短信验证码超时时间(分钟)*/
	public final static int MSGAUTHCODEEXPTIME=5;	
	/**客源前缀*/
	public final static String KY_PERFIX="KY-";
	/**买卖成交前缀*/
	public final static String MMCJ_PERFIX="MM-";
	/**租赁成交前缀*/
	public final static String ZLCJ_PERFIX="ZL-";
	/**房源前缀*/
	public final static String FY_PERFIX="";
	/**发送频率(秒)*/
	public final static int SENDSPEED=1*60;
	
    /**产品名称:阿里云云通信短信API产品,开发者无需替换*/
    public static final String smsproduct = "Dysmsapi";
    /**阿里云产品域名,开发者无需替换*/
    public static final String smsdomain = "dysmsapi.aliyuncs.com";
    /** 阿里云短信的AK*/
    public static final String smsaccessKeyId = "LTAI3YjscajRz6bC";
    public static final String smsaccessKeySecret = "5EACQzzlLaUZX8RqgqyTdshWSXeCuX";
    
 
    
    /**腾讯云点击拨号APPID*/
    public static final String TENCENTAPPID ="65739";
    /**腾讯云点击拨号请求地址*/
    public static final String CLICKDAILURL ="http://test.pstn.avc.qcloud.com:8080/201511v3/callBack?id=bashiju";
    /**腾讯云点击拨号录音推送地址*/
    public static final String PUSHTENCENTRECORDURL ="https://erp.bashiju.com/ext/saveTencentRecord";
    /**腾讯云点击拨号话单推送地址*/
    public static final String PUSHTENCENTHANGHUPURL ="https://erp.bashiju.com/ext/saveTencentRecordList";
}
