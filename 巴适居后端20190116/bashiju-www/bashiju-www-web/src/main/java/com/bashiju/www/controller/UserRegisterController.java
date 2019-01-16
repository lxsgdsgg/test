package com.bashiju.www.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.wutils.exception.BusinessException;
import com.bashiju.wutils.exception.ErrorCodeEnum;
import com.bashiju.wutils.util.BashijuResult;
import com.bashiju.www.pojo.CustCustomer;
import com.bashiju.www.service.comm.RandomValidateCode;
import com.bashiju.www.service.user.CustCustomerService;
import com.bashiju.www.service.user.UserRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
/**
 * 
 * @ClassName:  UserRegisterController   
 * @Description:用户注册控制层   
 * @author: wangpeng
 * @date:   2018年7月16日 下午3:29:35   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="userRegister")
public class UserRegisterController  {
	
	 private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private UserRegisterService userRegisterService;
	@Autowired
	CustCustomerService custCustomerService;
	@Autowired
	RandomValidateCode randomValidateCode;
	
	/**
	 * 
	 * @Title: enterUserRegisterPage   
	 * @Description: 用户注册信息   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterUserRegisterPage")
	public ModelAndView enterUserRegisterPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv =new ModelAndView ("userRegister/userRegister");
		return mv;
	}
	
	
	/**
	 * 
	 * @Title: saveUserRegisterInfo   
	 * @Description: 保存用户注册信息  
	 * @param jsonData
	 * @return: BashijuResult
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveUserRegisterInfo")
	@ResponseBody
	public BashijuResult saveUserRegisterInfo(String jsonData) {
		if(StringUtils.isEmpty(jsonData))
			throw new BusinessException("输入有误，请重试");
		Map<Object,Object> map = (Map<Object, Object>) JSONObject.parse(jsonData);
		//TODO:"短信验证";
		boolean result = userRegisterService.userRegister(map);
 		if(result) {
 			return BashijuResult.ok();
 		}
		throw new BusinessException(ErrorCodeEnum.SYSTEM_ADD_ERROR);	
 	
	}
	
	/**
	 * 
	 * @Title: sendMsg   
	 * @Description: 发送手机短信验证   
	 * @return      
	 * @return: String
	 */
	@RequestMapping(value="sendMsg")
	@ResponseBody
	public BashijuResult sendMsg(String mobile) {
//		//TODO:发送短信，成功后返回给页面
		String result = userRegisterService.sendMessage(mobile);
		System.out.println(result);
		if(!"".equals(result))
			return BashijuResult.build(true, "发送成功",result);
		return BashijuResult.build(false, "发送失败",result);
	}
	
	
	/**
	 * 	
	 * @Title: queryIsExistMobile   
	 * @Description: 查询手机号码是否存在   
	 * @param mobile
	 * @return      
	 * @return: BashijuResult
	 */
	@RequestMapping(value="queryIsExistMobile")
	@ResponseBody
	public BashijuResult queryIsExistMobile(String mobile) {
		if(StringUtil.isEmpty(mobile))
			throw new BusinessException("电话号码不能为空");
		 boolean result = userRegisterService.queryIsExistMobile(mobile);
		 if(result)
			 throw new BusinessException("该手机号码已注册");
		 return BashijuResult.ok();
	}
	
	

///*	public Object regCustomer(@RequestBody CustCustomer custCustomer ) {
//		return custCustomerService.doReg(custCustomer);
//	}*/
//	@RequestMapping(value="regCustomer")
//	@ResponseBody
//	public Object regCustomer(@RequestBody @Valid CustCustomer jsonData, BindingResult bindingResult) {
//
//		if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//              throw new BusinessException(fieldError.getDefaultMessage());
//        }
//		jsonData.setIsvalid(Byte.parseByte("1"));
//		custCustomerService.doReg(jsonData);
//		/*return custCustomerService.doReg(custCustomer);*/
//		return BashijuResult.ok();
//	}
//	
//	@RequestMapping(value="queryAllCustomer")
//	@ResponseBody
//	public Object queryAllCustomer() {
//		return custCustomerService.queryAllCustomer();
//	}

	
	
	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void service(HttpServletRequest request, HttpServletResponse response){
        randomValidateCode.getRandcode(request, response);//输出图片方法
    }
	@RequestMapping(value="check")
	@ResponseBody
	public BashijuResult custemerLogin(HttpServletRequest request,HttpServletResponse response,String mobile,String password,String validateCode) {
	boolean result =	custCustomerService.customerMobileLogin(request, response, mobile, validateCode);
	System.out.println(result);
		if(result)	
			 return BashijuResult.ok();
		throw new BusinessException("账号或密码有误");
	}
	
	@RequestMapping(value="loginTest")
	@ResponseBody
	public BashijuResult loginTest(HttpServletRequest request,HttpServletResponse response,String mobile,String password,String validateCode) {
		boolean result = custCustomerService.customerMobileLogin(request, response, mobile, validateCode);
		if(result)
			return BashijuResult.ok();
		throw new BusinessException("登录失败");
	}

}
