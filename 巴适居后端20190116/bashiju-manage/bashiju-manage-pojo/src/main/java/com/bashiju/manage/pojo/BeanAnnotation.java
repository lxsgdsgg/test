package com.bashiju.manage.pojo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description:实体bean对象注解
 * <br/>字段包括：Primarykey:主键,minOccurs:至少出现次数,regex:字符格式,errMsg:格式错误时的描述信息
 * @author yz
 * @Date 2017年4月21日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanAnnotation {

	/**
	 * 主键标识
	 * @return
	 */
	boolean Primarykey() default false;
	/**
	 * 至少出现次数，默认0
	 * @return
	 */
	int minOccurs() default 0;
	/**
	 * 至多出现次数，默认1
	 * @return
	 */
//	int maxOccurs() default 1;
	/**
	 * 字符格式
	 * @return
	 */
	String regex() default ""; 
	
	/**
	 * 格式错误时的描述信息
	 * @return
	 */
	String errMsg() default "";
	
}
