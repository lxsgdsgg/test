package com.bashiju.sso.service.api;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bashiju.utils.cookie.CookieUtils;
import com.bashiju.utils.util.UUIDUtil;

public class RequestUtils {
	/**
     * 设置自定义客户端sessionid
     *
     * @param request
     * @param response
     * @return
	 * @throws UnsupportedEncodingException 
     */
    public static String getCSESSIONID(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
       /* // 1：获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 2：从cookie中获取csessionid
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constants.CSESSIONID)) {
                    // 3：如果有直接使用
                    return cookie.getValue();
                }
            }
        }
        String csessionid = getUUID();
        // 设置cookie
        Cookie cookie = new Cookie(Constants.CSESSIONID, csessionid);
        // 设置路径
        cookie.setPath("/");
        // 设置cookie有效期 -1：关闭浏览器销毁 0：立即失效 >0：到时间才消失
        cookie.setMaxAge(-1);
        //cookie.setMaxAge(60 * 30);
        response.addCookie(cookie);*/
        
        String csessionid =CookieUtils.getCookieValue(request, Constants.CSESSIONID);
        if (StringUtils.isNotEmpty(csessionid)) {
			return csessionid;
		}
        // 4：如果没有csessionid，则创建并保存到cookie中写回到浏览器
        csessionid = getUUID();
        CookieUtils.setCookie(request, response, Constants.CSESSIONID, csessionid);
        return csessionid;
    }
    public static String getCookiteValue(HttpServletRequest request,String key) throws UnsupportedEncodingException {
    	/*// 1：获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 2：从cookie中获取csessionid
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    // 3：如果有直接使用
                    return cookie.getValue();
                }
            }
        }
        return null;*/
        String csessionid =CookieUtils.getCookieValue(request, key);
        return csessionid;
    }
    
    public static void delCookiteValue(HttpServletRequest request, HttpServletResponse response,String key) {
    	/*// 1：获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 2：从cookie中获取csessionid
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                	cookie.setMaxAge(0);
                	response.addCookie(cookie);
                }
            }
        }*/
        CookieUtils.deleteCookie(request, response, key);
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
