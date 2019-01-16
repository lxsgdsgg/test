package com.bashiju.wutils.picvalidatecode;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bashiju.wutils.cookie.CookieUtils;
import com.bashiju.wutils.global.WebGlobal;

public class RequestUtils {
	/**
     * 设置自定义客户端sessionid
     *
     * @param request
     * @param response
     * @return
     */
    public static String getCSESSIONID(HttpServletRequest request, HttpServletResponse response) {
        // 1：获取cookie
    	String cookie=CookieUtils.getCookieValue(request, WebGlobal.CSESSIONID);
    		if(StringUtils.isEmpty(cookie)) {
        // 2：如果没有csessionid，则创建并保存到cookie中写回到浏览器
    			cookie = getUUID();
	        CookieUtils.setCookie(request, response, WebGlobal.CSESSIONID, cookie);
    		}
        return cookie;
    }

    /**
     * 获取UUID字符串
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
