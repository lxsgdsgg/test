package com.bashiju.wutils.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.wutils.cookie.CookieUtils;
import com.bashiju.wutils.util.SpringContextHolder;
/**
 * 
 * @ClassName  BashijuExceptionResolver   
 * @Description 统一异常处理 
 * @author zhaobin
 * @date   2018年4月23日 下午2:19:04   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Component
public class BashijuExceptionResolver implements HandlerExceptionResolver{

	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
/*		// 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("errorMsg", exception.getMessage());
            }else if(exception.getClass().getName().equals(NullPointerException.class.getName())){
            	map.put("errorMsg", "系统存在空指针，页面不可见！");
            } 
            else {
                map.put("errorMsg", "系统异常！");
            }
            //这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
            System.out.println("异常信息:"+exception.getClass().getName()+":"+exception.getMessage());
            exception.printStackTrace();
            //对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("/error", map);
        } else {
            // 如果是ajax请求，JSON格式返回
*/            
		HandlerMethod 	handlerMethod=(HandlerMethod)handler;
		Map<String,Object> map=new HashMap<String,Object>();
		 System.out.println("===========================错误信息提示开始==============================");
		 System.out.println("======错误类====="+handlerMethod.getBean().getClass().getName());
		 System.out.println("======错误方法====="+handlerMethod.getMethod().getName());
        
		try {
	   	 map.put("loginIp",CookieUtils.getCookieValue(request, "USER_IP_CLIENT_TOKEN"));
/*	   	 map.put("loginUser",UserThreadLocal.get()!=null?UserThreadLocal.get().get("realName").toString():"未知的用户姓名");
	   	 map.put("deptId",UserThreadLocal.get()!=null?UserThreadLocal.get().get("deptId").toString():"");
	   	 map.put("depName",UserThreadLocal.get()!=null?UserThreadLocal.get().get("depName").toString():"");
	   	 map.put("companyId",UserThreadLocal.get()!=null?UserThreadLocal.get().get("companyId").toString():"");
	   	 map.put("comName",UserThreadLocal.get()!=null?UserThreadLocal.get().get("comName").toString():"");*/
	   	 map.put("loginArea",CookieUtils.getCookieValue(request,"USER_AREA_CLIENT_TOKEN",true));
	   	 map.put("className",handlerMethod.getBean().getClass().getName());
	   	 map.put("methodName",handlerMethod.getMethod().getName());

                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> maps = new HashMap<String, Object>();
                maps.put("success", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof BusinessException) {
                	
                    maps.put("msg", exception.getMessage());
                 	map.put("errorMessage",exception.getMessage());
                    System.out.println("======错误原因====="+exception.getMessage());  

                }else if (exception instanceof NullPointerException) {
                	
               	 	maps.put("msg","空值");
                 	map.put("errorMessage","空值");
                    System.out.println("======错误原因=====空值");

               }else if (exception instanceof CannotCreateTransactionException) {

                    maps.put("msg","网络故障，请检查");
                  	map.put("errorMessage",exception.getCause().getMessage());
                    System.out.println("======错误原因====="+exception.getCause().getMessage());
               }else if (exception instanceof SQLException) {

                   maps.put("msg","SQL语句有误");
                 	map.put("errorMessage",exception.getCause().getMessage());
                   System.out.println("======错误原因====="+exception.getCause().getMessage());

              }else if (exception instanceof NumberFormatException) {

                  maps.put("msg","数字类型转换失败");
                	map.put("errorMessage",exception.getMessage());
                  System.out.println("======错误原因====="+exception.getMessage());

             }else {
                	
                	maps.put("msg","系统异常,请联系管理员！");
                 	map.put("errorMessage",exception.getMessage());
                    System.out.println("======错误原因====="+exception.getMessage());
           	   	 }                                               
       	   	 	System.out.println("===========================错误信息提示结束==============================");
                writer.write(JSONObject.toJSONString(maps));
                writer.flush();
                writer.close();
                
                /*消息队列发送消息*/
        	 RabbitTemplate template = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
     		 template.convertAndSend("ERROR.LOG",map);
            } catch (IOException e) {
            	 System.out.println("===========================统一异常方法异常："+e.getMessage());
            }
        /*}*/
        return null;
    }
}
