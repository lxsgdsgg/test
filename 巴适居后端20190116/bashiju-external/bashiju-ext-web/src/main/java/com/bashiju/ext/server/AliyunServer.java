package com.bashiju.ext.server;

import java.text.ParseException;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.aliyuncs.exceptions.ClientException;
import com.bashiju.utils.global.SYSGlobal;

public class AliyunServer extends HttpServlet{
	
	private static final   Logger logger=LoggerFactory.getLogger(AliyunServer.class);		   
	private static final long serialVersionUID = 1L;

	public void init(){
		System.out.println("开始启动阿里云消息接收服务.....");
		receivePhoneRecordUrl();
		receivePhoneList();
	}
	
	    /**
	     * 获取通话录音URL
	     * @Title: ReceivePhoneRecordUrl   
	     * @Description: TODO(这里用一句话描述这个方法的作用)   
	     * @param:       
	     * @return: void      
	     * @throws
	     */
	private  void receivePhoneRecordUrl() {
        //此处替换成您所需的消息类型名称
        String messageType="SecretRecording";
        String queueName="Alicom-Queue-1571538187103729-SecretRecording";//在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName
        aliqueue(messageType,queueName,true);
	}
	/**
	 * 获取话单数据
	 * @Title: PhoneList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	private  void receivePhoneList() {
        String messageType="SecretReport";
        String queueName="Alicom-Queue-1571538187103729-SecretReport";//在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName
        this.aliqueue(messageType,queueName,false);
	}
	
	private  void aliqueue(String messageType,String queueName,boolean isPhoneRecordUrl) {
		DefaultAlicomMessagePuller puller=new DefaultAlicomMessagePuller();
		//替换成你的AK
		final String accessKeyId = SYSGlobal.plsaccessKeyId;
		final String accessKeySecret = SYSGlobal.plsaccessKeySecret;
        try {
        	if(isPhoneRecordUrl) {
    			puller.startReceiveMsg(accessKeyId,accessKeySecret ,messageType,queueName ,new AliyunMessageListener.PhoneRecordUrlMessageListener());
        	}else{
    			puller.startReceiveMsg(accessKeyId,accessKeySecret ,messageType,queueName , new AliyunMessageListener.PhoneListMessageListener());
        	}
		} catch (ClientException e) {
			logger.error("接收ali消息队列消息异常，原因为：{}",e.getErrMsg());
		} catch (ParseException e) {
			logger.error("接收ali消息队列消息异常，原因为：{}",e.getMessage());
		}
	}
	
	
}
