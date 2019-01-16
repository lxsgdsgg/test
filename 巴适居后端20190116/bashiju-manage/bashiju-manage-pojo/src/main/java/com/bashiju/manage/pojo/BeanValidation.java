package com.bashiju.manage.pojo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 
 * Description:验证实体bean格式是否正确
 * </br>注：该方法需要实体对象实现BeanAnnotation注解对象
 * @author yz
 * @Date 2017年4月21日
 */
public class BeanValidation {
	
	
	/**
	 * 验证对象属性格式是否正确
	 * </br>注：该方法需要实体对象实现BeanAnnotation注解对象
	 * @param obj 实体bean
	 * @return
	 * @throws Exception
	 */
	public static Object validate(Object obj)throws Exception{
		BeanValidation bv = new BeanValidation();
		String str = bv.index(obj);
		if(str.length()>1){
			throw new RuntimeException(str);
		}else{
			return bv.returnValiDateObj(obj);
		}
	}
	
	/**
	 * XSS攻击防范
	 * @param obj
	 * @return
	 */
	private Object returnValiDateObj(Object obj)throws Exception {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field fd : fields){
			Method md = null;
			try {
				md = clazz.getMethod(getFieldGetName(fd.getType(),fd.getName()));
			} catch (NoSuchMethodException e) {
				continue;
			}
			Object value = md.invoke(obj, null);
			//XSS防攻击字符串转译
			if(!isnull(value) && value.getClass().equals(String.class)){
				String val = ((String) value).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&amp;").replaceAll("\"", "&quot;");
				value = val;
			}
			Method setMethod = clazz.getMethod(getFieldSetName(fd.getName()),fd.getType());
			setMethod.invoke(obj, value);
		}
		return obj;
	}

	/**
	 * 根据注解验证
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private String index(Object obj)throws Exception{
		StringBuffer errMsg = new StringBuffer(0);
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		boolean keyFlag = false;
		for(Field fd : fields){
			Annotation[] anns = fd.getAnnotations();
			Method md = null;
			try {
				md = clazz.getMethod(getFieldGetName(fd.getType(),fd.getName()));
			} catch (NoSuchMethodException e) {
				continue;
			}
			Object value = md.invoke(obj, null);
			for(Annotation ann:anns){
				if(BeanAnnotation.class.getName().equals(ann.annotationType().getName())){
					BeanAnnotation bann = (BeanAnnotation) ann;
					if(bann.Primarykey()){//判断主键
						if(keyFlag){
							errMsg.append("一个实体对象中只能存在一个主键,");
						}else if(isnull(value)){
							errMsg.append("主键"+fd.getName()+"不允许为空,");
						}
						keyFlag=true;
					}else{
						if(bann.minOccurs()>0 && isnull(value)){
							if(bann.errMsg()!=null && !"".equals(bann.errMsg())){
								errMsg.append(bann.errMsg()+",");
							}else{
								errMsg.append(fd.getName()+"属性不允许为空,");
							}
						}
					}
					
					//只允许字符串匹配正则表达式
					if(!isnull(value) && !"".equals(bann.regex())){
						if(String.class.equals(fd.getType())){
							Pattern pattern = Pattern.compile(bann.regex());
							if(!pattern.matcher((String)value).matches()){
								if(bann.errMsg()!=null && !"".equals(bann.errMsg())){
									errMsg.append(bann.errMsg()+",");
								}else{
									errMsg.append(fd.getName()+"属性格式不正确,");
								}
							}
						}else{
							errMsg.append(fd.getName()+"属性类型为"+fd.getGenericType()+",不适用正则表达式");
						}
					}
				}
			}
		}
		//这里暂不验证主键
//		if(!keyFlag){
//			errMsg.append("该对象没有定义主键属性");
//		}
		return errMsg.toString();
	}
	
	/**
	 * 判断该对象是否为空
	 * @param obj
	 * @return
	 */
	private boolean isnull(Object obj){
		return obj==null||"".equals(obj)?true:false;
	}
	
	
    /**
	 * 根据属性名称获取get方法名称
	 * @param s
	 * @return
	 */
    private String getFieldGetName(Type type,String s){
    	if(type.equals(boolean.class)){
    		if(Character.isUpperCase(s.charAt(0)))
    			return "is"+s;
    		else
    			return "is"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    	}else{
    		if(Character.isUpperCase(s.charAt(0)) || Character.isUpperCase(s.charAt(1)))
    			return "get"+s;
    		else
    			return "get"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    	}
    }
    
    /**
	 * 根据属性名称获取set方法名称
	 * @param s
	 * @return
	 */
    private String getFieldSetName(String s){
    	if(Character.isUpperCase(s.charAt(0)) || Character.isUpperCase(s.charAt(1)))
            return "set"+s;
        else
            return "set"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**  
	  * 判断一个类是否为基本数据类型。  
	  * @param type 要判断的类。  
	  * @return true 表示为基本数据类型。  
	  */ 
	private boolean isBaseDataType(Type type) {
		return 
		     (   
		         type.equals(String.class) ||   
		         type.equals(Integer.class)||   
		         type.equals(Byte.class) ||   
		         type.equals(Long.class) ||   
		         type.equals(Double.class) ||   
		         type.equals(Float.class) ||   
		         type.equals(Character.class) ||   
		         type.equals(Short.class) ||   
		         type.equals(BigDecimal.class) ||   
		         type.equals(BigInteger.class) ||   
		         type.equals(Boolean.class) ||   
		         type.equals(Date.class) ||
		         type.equals(int.class) ||
		         type.equals(boolean.class) ||
		         type.equals(double.class) ||
		         type.equals(float.class) ||
		         type.equals(char.class) ||
		         type.equals(short.class) ||
		         type.equals(long.class) ||
		         type.equals(byte.class) 
		     ); 
	}
}
