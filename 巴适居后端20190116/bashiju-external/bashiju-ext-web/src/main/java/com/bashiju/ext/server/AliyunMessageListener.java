package com.bashiju.ext.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.UpdateSubscriptionResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bashiju.ext.service.ExtCloudServiceApi;
import com.bashiju.ext.service.impl.ExtCloudServiceImpl;
import com.bashiju.utils.global.SYSGlobal;
import com.bashiju.utils.util.HttpClientUtil;
import com.bashiju.utils.util.SpringContextHolder;
import com.bashiju.utils.util.TimeUtil;

public class AliyunMessageListener {

	final static  Logger logger=LoggerFactory.getLogger(AliyunMessageListener.class);
	static ExtCloudServiceApi extCloudServiceApi=(ExtCloudServiceImpl)SpringContextHolder.getBean("ExtCloudService");
	/**
	 * 接受通话录音URL队列消息并存到数据库
	 * @ClassName:  MyMessageListener   
	 * @Description:TODO(这里用一句话描述这个类的作用)   
	 * @author: zhaobin
	 * @date:   2018年10月19日 下午12:15:08   
	 *     
	 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
	 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
	 */
	@Component
		public static class PhoneRecordUrlMessageListener  implements MessageListener{
	        @Override
	        public boolean dealMessage(Message message) {
	            try{
	                Map<String,Object> contentMap=JSONObject.parseObject(message.getMessageBodyAsString());
	                String url=this.auxGetAliyunPhoneRecord(String.valueOf(contentMap.get("pool_key")),
	                		String.valueOf(contentMap.get("call_id")),String.valueOf(contentMap.get("call_time")));
	               String localUrl= new HttpClientUtil().getPhoneRecordHttpClient(url,true);
	               if(StringUtils.isEmpty(localUrl)) {
	            	   return false;
	               }
	                Map<String,Object> map =new HashMap<>();
	                map.put("call_id", contentMap.get("call_id"));
	                map.put("url", localUrl);
	                extCloudServiceApi.auxSave(map, "aliyun_phonerecord");
	            }catch(com.google.gson.JsonSyntaxException e){
	                logger.error("错误的JSON格式:"+message.getMessageBodyAsString(),e);
	            }
	            Boolean dealResult=true;
	            return dealResult;//返回true，则工具类自动删除已拉取的消息。
	        }
	        
	        /**
		     * 获取阿里云通话录音URL    
		     * @Title: auxGetAliyunPhoneRecord   
		     * @Description: TODO(这里用一句话描述这个方法的作用)
		     * @param: @param keypool
		     * @param: @param callId
		     * @param: @param callTime
		     * @param: @return      
		     * @return: String      
		     * @throws
		     */
		    public String auxGetAliyunPhoneRecord(String keypool,String callId,String callTime) {
		   		 QueryRecordFileDownloadUrlResponse response=null;
		   		//设置超时时间-可自行调整
		   		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		   		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		   		//初始化ascClient需要的几个参数
		   		final String product = "Dyplsapi";//隐私号码产品名称（产品名称固定，无需修改）
		   		final String domain = "dyplsapi.aliyuncs.com";//隐私号码产品域名（产品域名固定，无需修改）
		   		//初始化ascClient,暂时不支持多region
		   		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SYSGlobal.plsaccessKeyId,
	        			SYSGlobal.plsaccessKeySecret);
		   		try {
		   			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		   			IAcsClient acsClient = new DefaultAcsClient(profile);
		   			 //组装请求对象-具体描述见控制台-文档部分内容
		   			        QueryRecordFileDownloadUrlRequest request = new QueryRecordFileDownloadUrlRequest();
		   			        //对应的号池Key
		   			        request.setPoolKey(keypool);
		   			        //话单回执中返回的标识每一通唯一通话行为的callId
		   			        request.setCallId(callId);
		   			        //话单回执中返回的callTime字段
		   			        request.setCallTime(TimeUtil.stampToDate(callTime));
		   			        //hint 此处可能会抛出异常，注意catch
		   			        response = acsClient.getAcsResponse(request);
		   		} catch (ClientException e) {
		   			logger.error("获取阿里云中间号录音URL过程中出错，原因是:{}",e.getErrMsg());
		   			return null;
		   		}
		   		if(response.getCode() != null && response.getCode().equals("OK")) {				 	
		   			 return response.getDownloadUrl();
		   		}else {
		   			logger.error("阿里云原因导致获取阿里云中间号录音URL未成功，失败原因为{}",response.getMessage());
		   			return null;
		   		}
		   	}
		    
		   
	    }
		
		
		
	    /**
		 * 接受通话记录队列消息并存到数据库
		 * @ClassName:  MyMessageListener   
		 * @Description:TODO(这里用一句话描述这个类的作用)   
		 * @author: zhaobin
		 * @date:   2018年10月19日 下午12:15:08   
		 *     
		 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
		 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
		 */
		public static class PhoneListMessageListener implements MessageListener{
		        @Override
		        public boolean dealMessage(Message message) {
		            try{
		            	Map<String,Object> contentMap=JSONObject.parseObject(message.getMessageBodyAsString(),HashMap.class);
		            	
		            	updateSubscription(contentMap.get("pool_key").toString(),
		            			contentMap.get("sub_id").toString(),contentMap.get("secret_no").toString(),
		            			contentMap.get("peer_no").toString());
		            	
		                extCloudServiceApi.auxSaveAliyunPhoneList(contentMap);
		            }catch(com.google.gson.JsonSyntaxException e){
		                logger.error("错误的JSON格式:"+message.getMessageBodyAsString(),e);
		            }
		            Boolean dealResult=true;
		            return dealResult;//返回true，则工具类自动删除已拉取的消息。
		        }
		        
		        /**
		         * 挂断电话后立即更新B号码，便于B用户回拨
		         * @Title: updateSubscription   
		         * @Description: TODO(这里用一句话描述这个方法的作用)   
		         * @param: @param poolkey
		         * @param: @param sub_id
		         * @param: @param phoneNoX
		         * @param: @param phoneNoB
		         * @param: @return      
		         * @return: boolean      
		         * @throws
		         */
		        private boolean updateSubscription(String poolkey,String sub_id,String phoneNoX,String phoneNoB) {
		        	//设置超时时间-可自行调整
		        	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		        	System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		        	//初始化ascClient,暂时不支持多region
		        	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SYSGlobal.plsaccessKeyId,
		        			SYSGlobal.plsaccessKeySecret);
		        	//hint 此处可能会抛出异常，注意catch
		        	 UpdateSubscriptionResponse response=null;
					try {
						DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SYSGlobal.plsproduct, SYSGlobal.plsdomain);
						IAcsClient acsClient = new DefaultAcsClient(profile);
						//组装请求对象-具体描述见控制台-文档部分内容
						UpdateSubscriptionRequest request = new UpdateSubscriptionRequest();
						//绑定关系对应的号池Key
						request.setPoolKey(poolkey);
						//必填:绑定关系ID
						request.setSubsId(sub_id);
						//必填:绑定关系对应的X号码
						request.setPhoneNoX(phoneNoX);
						//必填:操作类型指令
						request.setOperateType("updateNoB");
						//可选:需要修改的A号码
						request.setPhoneNoB(phoneNoB);
						response = acsClient.getAcsResponse(request);
					} catch (Exception e) {
						logger.error("更新B号码过程中失败，失败原因为{}",e.getMessage());
					}
		        	 if(response.getCode() != null && response.getCode().equals("OK")) {
		        	       return true;
		        	  }else {
		        		  logger.error("阿里云原因导致更新B号码失败，失败原因为{}",response.getMessage());
		        		  return false;
		        	  }
		        	 	
		    	}
		    }
		
	
	
}
