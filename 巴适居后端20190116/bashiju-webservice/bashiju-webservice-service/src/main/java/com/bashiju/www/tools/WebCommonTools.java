/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  WebCommonTools.java   
 * @Package com.bashiju.www.tools      
 * @author: zuoyuntao     
 * @date:   2018年8月2日 下午6:40:49   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.tools;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bashiju.wutils.exception.BusinessException;
import com.github.pagehelper.util.StringUtil;

/**
 * C端公共工具类
 * @ClassName:WebCommonTools
 * @Description:C端公共工具类
 * @author:zuoyuntao
 * @date:2018年8月2日 下午6:40:49
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class WebCommonTools {
	/**
	 * 返回格式化日期 
	 * @Title: formatDataTest
	 * @author: zuoyuntao  
	 * @Description:返回格式化日期 
	 * @param formatStr      
	 * String
	 */
	public static String formatDate(String formatStr) {
		if(StringUtil.isEmpty(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String dateString = sdf.format(new Date());
        return dateString;
	}
	
	/**
	 * 对象转换为Map
	 * @Title: castEntityToHashMap
	 * @author: zuoyuntao  
	 * @Description:对象转换 
	 * @param oriEntityObj 原来的实体
	 * @return      
	 * Map<String,Object> 
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> castEntityToHashMap(Object oriEntityObj){
		Map<String, Object> map = new HashMap<>();
        if (oriEntityObj == null) {
            return new HashMap<String,Object>();
        }
        Class clazz = oriEntityObj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
            	if(field.getName().equals("serialVersionUID") || field.getName().equals("loginKey") 
            			|| field.getName().equals("transactionTypeName") 
            			|| field.getName().equals("verifyCode")
            			|| field.getName().equals("shhId")
            			|| field.getName().equals("profilePhoto")
            			|| field.getName().equals("houseImg")) {
            		continue;
            	}
                field.setAccessible(true);
                String value = String.valueOf(field.get(oriEntityObj));
                if(StringUtil.isEmpty(value)) {
                	continue;
                }
                if(value.equals("null")) {
                	continue;
                }
                map.put(field.getName(), value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("数据转换异常");
        }
        return map;
	}
}
