package com.bashiju.www.service.comm;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.bashiju.www.pojo.comm.WebPage;
/**
 * 
 *  字符串转换成WebPage对象工具类
 * @ClassName:  WebPageUtils   
 * @Description:   （详细描述）
 * @author: wangkaifa
 * @date:   2018年8月1日 上午9:54:24       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class WebPageUtils {

	/**
	 * 
		 * 字符串转换成WebPage对象方法
		 * @Description: 字符串转换成WebPage对象方法 
		 * @param json 待转换字符串
		 * @param cls 数据对象类型
		 * @return 
		 * WebPage<T>
	 */
	public static <T> WebPage<T> parseWebPage(String json,Class<T> cls){
		if (StringUtils.isNotEmpty(json)&&cls!=null) {
			WebPage<T> result=JSON.parseObject(json, WebPage.class);
			List<T> data=JSON.parseArray(JSON.toJSONString(result.getResult()), cls);
			result.setResult(data);
			return result;
		}
		return null;
	}
}
