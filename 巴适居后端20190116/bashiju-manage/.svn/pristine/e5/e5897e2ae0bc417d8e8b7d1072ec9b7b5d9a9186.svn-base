package com.bashiju.manage.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xml数据封装工具类
 * @author yz
 *2017-03-10
 */
public class DataUtil {
	
	private static StringBuffer sbstr = new StringBuffer(0);
	private static final Logger logger = LoggerFactory.getLogger(DataUtil.class);
	
	/**
	 * 数据封装的执行类
	 * @param map 数据集，数据集以map的形式传递，并且对象格式严格按照对应XSD的要求封装
	 * @param className XSD对应xml的根目录，例如：com.asdkv3.xsd.beanpcacries001.Document
	 * @return
	 * @throws Exception
	 */
	public static Object excute(Map<String,Object> map,String className)throws Exception{
		logger.info("类名："+className);
		logger.info("数据信息："+map);
		if(map==null){return null;}
		DataUtil  util = new DataUtil();
		Class<?> clazz = Class.forName(className);
		Object instance = clazz.newInstance();
		instance = util.getObject(instance,clazz,map);
		if(sbstr!=null && sbstr.length()>0){throw new RuntimeException(sbstr.toString()+"的属性不能为空");}
		return instance;
	}
	
	/**
	 * 获取封装数据后的对象
	 * @param instance
	 * @param clazz
	 * @param record
	 * @return
	 * @throws Exception
	 */
	private Object getObject(Object instance, Class<?> clazz, Map<String,Object> map)throws Exception{
		Field[] fileds = clazz.getDeclaredFields();
		for(int i=0;i<fileds.length;i++){
			Field fd = fileds[i];
			Annotation annotation = fd.getAnnotation(XmlElement.class);
//			if(fd!=null){logger.info("调试信息：field="+fd.getName()+";genericType="+fd.getGenericType()+";map="+map);}
			if(isBaseDataType(fd.getGenericType())){
				instance = getBaseDataType(instance, map, clazz, fd, annotation);
			}else if("java.util.List".equals(fd.getType().getName())){
				instance = getDataList(instance, map, clazz, fd);
			}else{
				instance = getComplexDataType(instance, map, clazz, fd,annotation);
			}
		}
		return instance;
	}

	/**
	 * 基本数据类型数据封装
	 * @param instance
	 * @param record
	 * @param clazz
	 * @param fd
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	private Object getBaseDataType(Object instance,Map<String,Object> map, Class<?> clazz,Field fd,Annotation annotation)throws RuntimeException{
		try {
			if(map==null){
				return instance;
			}
			Method l1 = clazz.getMethod(getFieldSetName(fd.getName()), fd.getType());
			if(l1!=null){
//				XmlElement xmlElement = (XmlElement)annotation;
//				boolean required = xmlElement.required();
//				if(required && (map.get(fd.getName())==null || "".equals(map.get(fd.getName())))){
//					sbstr.append("字段名称为"+fd.getName()+",");
//					logger.error("格式不正确的数据：name="+fd.getName()+";");
//				}else{
					try {
//						fd.getType().equals(int.class)?Integer.parseInt((String) map.get(fd.getName())):map.get(fd.getName())
						Object od = getData(fd,map);
						l1.invoke(instance, od);
					} catch (Exception e) {
						logger.error("数据封装异常", e);
					}
//				}
			}
		} catch (Exception e) {
			logger.error("数据封装异常", e);
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * java基本数据类型需要转换为对应的包装类
	 * @param fd 字段属性
	 * @param map 数据信息
	 * @return
	 * @throws ParseException 
	 */
	private Object getData(Field fd, Map<String,Object> map) throws ParseException {
		if(map.get(fd.getName())==null || "".equals(map.get(fd.getName()))){
			return map.get(fd.getName());
		}
		if(fd.getType().equals(int.class)){return Integer.parseInt((String) map.get(fd.getName()));}
		if(fd.getType().equals(boolean.class)){return Boolean.parseBoolean((String) map.get(fd.getName()));}
		if(fd.getType().equals(double.class)){return Double.parseDouble((String) map.get(fd.getName()));}
		if(fd.getType().equals(float.class)){return Float.parseFloat((String) map.get(fd.getName()));}
		if(fd.getType().equals(char.class)){return map.get(fd.getName());}
		if(fd.getType().equals(short.class)){return Short.parseShort((String) map.get(fd.getName()));}
		if(fd.getType().equals(long.class)){return Long.parseLong((String) map.get(fd.getName()));}
		if(fd.getType().equals(byte.class)){return Byte.parseByte((String) map.get(fd.getName()));}
		if(fd.getType().equals(Date.class)){
			if(String.valueOf(map.get(fd.getName())).length()==10){
				return dateFormat("yyyy-MM-dd").parse(String.valueOf(map.get(fd.getName())));
			}else{
				return dateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(map.get(fd.getName())));
			}
		}
		else{return String.valueOf(map.get(fd.getName()));}
	}

	/**
	 * 复杂对象的数据封装
	 * @param instance
	 * @param record
	 * @param clazz
	 * @param fd
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object getComplexDataType(Object instance,Map<String,Object> map, Class<?> clazz,Field fd,Annotation annotation)throws Exception{
		if(map==null){
			return instance;
		}
//		XmlElement xmlElement = (XmlElement)annotation;
//		boolean required = xmlElement.required();
		if("interface java.util.List".equals(fd.getGenericType()) || "interface java.util.List".equals(fd.getType())){
//			if(required && (map.get(fd.getName())==null || "".equals(map.get(fd.getName())))){
//				sbstr.append("字段名称为"+fd.getName()+",");
//				logger.error("格式不正确的数据：name="+fd.getName()+";");
//			}else{
				instance = getDataList(instance, (Map<String,Object>)map.get(fd.getName()), clazz, fd);
//			}
		}else{
			Method l1 = clazz.getMethod(getFieldSetName(fd.getName()), fd.getType());
			if(l1!=null){
				Class<?> cls = Class.forName(fd.getType().getName());
//				if(required && (map.get(fd.getName())==null || "".equals(map.get(fd.getName())))){
//					sbstr.append("字段名称为"+fd.getName()+",");
//					logger.error("格式不正确的数据：name="+fd.getName()+";");
//				}else{
					l1.invoke(instance, getObject(cls.newInstance(), cls, (Map<String,Object>)map.get(fd.getName())));
//				}
			}
		}
		return instance;
	}
	
	/**
	 * 获取数据对象的数组
	 * @param args 接口方法的参数对象
	 * @param datas 数据集
	 * @param dataObjs 实例化对象
	 * @param f 属性
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object getDataList(Object dataObjs,Map<String,Object> datas,Class<?> args,Field f) throws Exception {
		if(datas==null){
			return dataObjs;
		}
		List<Object> list = null;
		Method arr = args.getMethod(getFieldGetName(f.getName()), null);
		ParameterizedType type = (ParameterizedType) f.getGenericType();
		Class<?> gc = (Class<?>)type.getActualTypeArguments()[0];//获取list内部的对象
		list = (List<Object>) arr.invoke(dataObjs, null);
		if(datas.get(f.getName()) instanceof List){
			List<Object> objs = ((List<Object>)datas.get(f.getName()));
			for(int i=0;i<objs.size();i++){
				Object od = getObject(gc.newInstance(),gc,(Map<String,Object>) objs.get(i));
				if(!"java.lang.Object".equals(od.getClass().getName())){
					list.add(od);
				}
			}
		}else if(datas.get(f.getName()) instanceof Map){
			Object od = getObject(gc.newInstance(),gc,(Map<String,Object>) datas.get(f.getName()));
			if(!"java.lang.Object".equals(od.getClass().getName())){
				list.add(od);
			}
		}
		return dataObjs;
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
//		         ||type.isPrimitive()   
		     ); 
	}
	
	/**
	 * 根据属性名称获取set方法名称
	 * @param s
	 * @return
	 */
	private String getFieldSetName(String s){
       if(Character.isUpperCase(s.charAt(0)))
           return "set"+s;
       else
           return "set"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
   }
   /**
	 * 根据属性名称获取get方法名称
	 * @param s
	 * @return
	 */
   private String getFieldGetName(String s){
       if(Character.isUpperCase(s.charAt(0)))
           return "get"+s;
       else
           return "get"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
   }
   
   /**
	 * 获取时间格式化格式
	 * @param pattern
	 * @return
	 */
	protected SimpleDateFormat dateFormat(String pattern){
		SimpleDateFormat format = null;
		if(format==null){
			format = new SimpleDateFormat();
		}
		format.applyPattern(pattern);
		return format;
	}

}
