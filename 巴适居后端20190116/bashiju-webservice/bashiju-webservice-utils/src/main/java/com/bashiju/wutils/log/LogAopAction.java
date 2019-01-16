package com.bashiju.wutils.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bashiju.wutils.cookie.CookieUtils;
import com.bashiju.wutils.util.SpringContextHolder;


/**
 * 
 */
@Aspect
@Component  
public class LogAopAction extends HttpServlet{
	
    /**   
	 * @Fields serialVersionUID   序列化   
	 */ 
	
	private static final long serialVersionUID = 3585525873490917200L;
	
	//获取开始时间
    private long BEGIN_TIME ;

    //获取结束时间
    private long END_TIME;

    
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	

  //Service层切点    
    @Pointcut("@annotation(com.bashiju.wutils.log.SystemServiceLog)")    
     public  void serviceAspect() {    
    }    
   

    /**
     * Service层方法开始执行
     */
    @Before("serviceAspect()")
    public void doBefore(){
        BEGIN_TIME = new Date().getTime();
    }

    /**
     * Service层方法结束执行
     */
    @After("serviceAspect()")
    public void after(){
        END_TIME = new Date().getTime();
    }
    
    /**
     * 方法结束执行后的操作
     * @throws Exception 
     */
	@AfterReturning("serviceAspect()")
    public void doAfter(JoinPoint joinPoint) throws Exception{
   	 Map<String,Object> map=new HashMap<String,Object>();
         try {   
        	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
        	 map.put("loginIp",CookieUtils.getCookieValue(request, "USER_IP_CLIENT_TOKEN"));
        	 map.put("loginArea",CookieUtils.getCookieValue(request,"USER_AREANAME_CLIENT_TOKEN",true));
         }  catch (Exception e) {
        }
     	/*==========service层数据库日志=========*/ 

/*    	 map.put("loginUser",UserThreadLocal.get()!=null?UserThreadLocal.get().get("realName").toString():"未知的用户姓名");
    	 map.put("deptId",UserThreadLocal.get()!=null?UserThreadLocal.get().get("deptId").toString():"");
    	 map.put("depName",UserThreadLocal.get()!=null?UserThreadLocal.get().get("depName").toString():"");
    	 map.put("companyId",UserThreadLocal.get()!=null?UserThreadLocal.get().get("companyId").toString():"");
    	 map.put("comName",UserThreadLocal.get()!=null?UserThreadLocal.get().get("comName").toString():"");*/
    	 map.put("sourceType",getServiceClassSourceType(joinPoint));
    	 map.put("operationType",ExecutionResult.get()!=null&&ExecutionResult.get().containsKey("operationType")?ExecutionResult.get().get("operationType").toString():getServiceMethodOperationType(joinPoint));
    	 map.put("actionTime", END_TIME-BEGIN_TIME+"毫秒");
    	 map.put("sourceId", ExecutionResult.get()!=null?ExecutionResult.get().get("sourceId").toString():"");
    	 map.put("excuteContext",ExecutionResult.get()!=null?ExecutionResult.get().get("excuteContext").toString():"");
 		/*消息队列发送消息*/
    	 RabbitTemplate template = (RabbitTemplate) SpringContextHolder.getBean("reRabbitTemplate");
 		 template.convertAndSend("USER.LOG",map); 
    }    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
   /* @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {            
         try {  
        	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
              ========控制台输出=========    
            System.out.println("=====异常通知开始=====");    
            System.out.println("异常代码:" + e.getClass().getName());    
            System.out.println("异常信息:" + e.getMessage());    
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            System.out.println("方法描述:" + getServiceMethodOperationType(joinPoint));    
            System.out.println("执行时长:" + (END_TIME-BEGIN_TIME)+"毫秒");    
            System.out.println("请求IP:" +CookieUtils.getCookieValue(request, "USER_IP_CLIENT_TOKEN"));
            System.out.print("请求区域"+ CookieUtils.getCookieValue(request,"USER_AREA_CLIENT_TOKEN",true));
            System.out.print("资源类型"+ getServiceClassSourceType(joinPoint));
            System.out.print("操作类型"+ getServiceMethodOperationType(joinPoint));
            System.out.print("操作系统其他信息:"+CookieUtils.getCookieValue(request,"systemOtherInfo",true));
            
               ==========数据库日志=========             
			ThreadContext.put("login_ip",CookieUtils.getCookieValue(request, "USER_IP_CLIENT_TOKEN"));
			ThreadContext.put("login_area",CookieUtils.getCookieValue(request,"USER_IP_CLIENT_TOKEN",true));
			ThreadContext.put("systemOtherInfo",CookieUtils.getCookieValue(request,"systemOtherInfo",true));
	    	ThreadContext.put("login_localHostName",CookieUtils.getCookieValue(request,"hostName",true));
			ThreadContext.put("login_mac", CookieUtils.getCookieValue(request,"mac"));
			ThreadContext.put("log_method", joinPoint.getSignature().getName());		
			ThreadContext.put("sourceType", getServiceClassSourceType(joinPoint));
			ThreadContext.put("operationType",getServiceMethodOperationType(joinPoint));
			ThreadContext.put("executionTime",(END_TIME-BEGIN_TIME)+"毫秒");
			ThreadContext.put("login_username", UserThreadLocal.get()!=null?UserThreadLocal.get().get("realName").toString():"未知的用户姓名");
			System.out.println("请求人:" +  UserThreadLocal.get()!=null?UserThreadLocal.get().get("realName").toString():"未知的用户姓名");			
			System.out.println("=====异常通知结束=====");
            ==========记录本地异常日志==========    
            logger.error("异常信息:{}", e.getMessage());    
            ThreadContext.removeStack();
        }  catch (Exception ex) {  
            //记录本地异常日志    
            logger.error("异常日志记录过程中出现异常:{}", ex.getMessage()); 
			ThreadContext.removeStack();
        }    

    }   */ 

/*
    private static String getControllerClassSourceType(JoinPoint joinPoint) throws Exception {
    	
    	String targetName = joinPoint.getTarget().getClass().getName();    
        Class targetClass = Class.forName(targetName);
        SystemControllerLog ss=(SystemControllerLog) targetClass.getAnnotation(SystemControllerLog.class);
    	return ss.sourceType();
    }
    private static String getControllerMethodOperationType(JoinPoint joinPoint) throws Exception {
    	Method method= getMthodDescription(joinPoint);
    	
    	return method.getAnnotation(SystemControllerLog.class).operationType();
    }
*/
    private static String getServiceClassSourceType(JoinPoint joinPoint) throws Exception {
    	String targetName = joinPoint.getTarget().getClass().getName();    
        Class targetClass = Class.forName(targetName);
        SystemServiceLog ss=(SystemServiceLog) targetClass.getAnnotation(SystemServiceLog.class);
    	return ss.sourceType();
    }
       
    private static String getServiceMethodOperationType(JoinPoint joinPoint) throws Exception {
    	Method method= getMthodDescription(joinPoint);
    	
    	return method.getAnnotation(SystemServiceLog.class).operationType();
    }
    
    /**  
     * 获取注解中对方法的注解值信息 sourceType的值,operationType的值
     *  
     * @param joinPoint 切点  
     * @return 注解值
     * @throws Exception  
     */    
     private  static Method getMthodDescription(JoinPoint joinPoint)    
             throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();     
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {
                     return method;
                }    
            }    
        }    
         return null;    
    }
}
