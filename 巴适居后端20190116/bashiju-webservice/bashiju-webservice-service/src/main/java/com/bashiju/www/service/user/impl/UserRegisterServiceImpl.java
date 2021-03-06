package com.bashiju.www.service.user.impl;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.webservice.mapper.UserRegisterMapper;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.log.SystemServiceLog;
import com.bashiju.www.pojo.service.user.CustCustomerEntity;
import com.bashiju.www.pojo.service.user.CustCustomerParam;
import com.bashiju.www.service.user.UserRegisterService;

/**
 * 
 * @ClassName:  UserRegisterServiceImpl   
 * @Description:用户注册服务   
 * @author: wangpeng
 * @date:   2018年7月16日 下午2:55:16   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(operationType="用户注册服务")
public class UserRegisterServiceImpl   implements UserRegisterService {
	
	@Autowired
	private UserRegisterMapper userRegisterMapper;
	
	/**
	 * @Description: 用户注册
	 * @param map 用户输入参数
	 * @return: boolean
	 * @see com.bashiju.www.service.user.UserRegisterService#userRegister(java.util.Map)
	 */
	@Override
	public boolean userRegister(Map<String,Object> map) {
		long result =1; /*this.commonOperationDatabase(map, "cust_customer",false);*/
		if(result>0)
			return true;
		return false;
	}
	/**
	 * @Description:  查询手机号码是否已存在
	 * @param mobile 手机号码
	 * @return: boolean
	 * @see com.bashiju.www.service.user.UserRegisterService#queryIsExistMobile(java.lang.String)
	 */
	@Override
	public boolean queryIsExistMobile(String mobile) {
		long result = userRegisterMapper.queryIsExistMobile(mobile);
		if(result>0)
			return true;//号码已存在
		return false;
	}
	@Override
	public String sendMessage(String mobile) {
		//生成随机数
	        Random random=new Random();
	        String randomNum = random.nextInt(1000000) + "";
	        String operation = "/industrySMS/sendSMS";

	        String accountSid = Config.ACCOUNT_SID;
	        String to = mobile;
	        String smsContent = "【巴适居】尊敬的用户，您的验证码为"+randomNum+"";
		/**
		 * 验证码通知短信
		 */
			String tmpSmsContent = null;
		    try{
		      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    String url = Config.BASE_URL + operation;
		    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
		        + HttpUtil.createCommonParam();
		    // 提交请求
		    String result = HttpUtil.post(url, body);
		    System.out.println("result:" + System.lineSeparator() + result);
		    System.out.println("生成的隨機數是："+randomNum);
		    return randomNum;
	}
	
	
	/**
	 * @Description:  保存用书输入信息
	 * @param custCustomerParam 用戶输入参数
	 * @return: boolean
	 * @see  com.bashiju.www.service.user.UserRegisterService#saveCustCustomer(com.bashiju.www.pojo.service.user.CustCustomerParam) 
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean saveCustCustomer(CustCustomerParam custCustomerParam) {	
		if(!"".equals(custCustomerParam) ) {
			CustCustomerEntity custCustomerEntity = new CustCustomerEntity();
			custCustomerEntity.setMobile(custCustomerParam.getMobile());
			custCustomerEntity.setName(custCustomerParam.getName());
			custCustomerEntity.setPassword(custCustomerParam.getPassword());
//			custCustomerEntity.setSolt(custCustomerParam.getSolt());
			custCustomerEntity.setIsValid("1");
			custCustomerEntity.setAddTime(new Date());
			long result = 	userRegisterMapper.saveCustCustomer(custCustomerEntity);
			if(result>0)
				return true;
			return false;
		}else
			return false;
	}
	/**
	 * @Description:  条件验证手机号码
	 * @param mobile 手机号码
	 * @param solt 盐值
	 * @return: boolean
	 * @see  com.bashiju.www.service.user.UserRegisterService#userMobileCheck(java.lang.String, java.lang.String)    
	 */
	@Override
	public boolean userMobileCheck(String mobile, String solt) {
//	long result = userRegisterMapper.userMobileCheck(mobile, solt);
//	if(result>0)
//		return true;
	return 	false;		
	}
	
	/**
	 * @Description:  条件验证手机号码
	 * @param paramMap 跟新用户信息
	 * @return: boolean
	 * @see  com.bashiju.www.service.user.UserRegisterService#updateCustCustomer(java.util.Map)    
	 */
	
	@Override
	public boolean updateCustCustomer(Map<String,Object> paramMap) {
		if(StringUtils.isEmpty(paramMap.toString()))
			throw new BusinessException("修改参数不能为空");
		String randomNum= this.sendMessage(paramMap.get("mobile").toString());
		boolean  result =	this.userMobileCheck( paramMap.get("mobile").toString(),paramMap.get("solt").toString());
		System.out.println("手机号码验证是否成功："+result);
		if(result) {
			long rt= userRegisterMapper.updateCustCustomer(paramMap);
			System.out.println("受影响行数："+rt);
			if(rt>0)
				return true;
			return false;
		}
		return false;
	}
}
