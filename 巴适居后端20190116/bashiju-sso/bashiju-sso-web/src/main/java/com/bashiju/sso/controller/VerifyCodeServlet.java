package com.bashiju.sso.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bashiju.sso.service.api.RandomValidateCode;

@Controller
public class VerifyCodeServlet {
	@Autowired
    RandomValidateCode randomValidateCode;
	
	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        randomValidateCode.getRandcode(request, response);//输出图片方法
    }
}
