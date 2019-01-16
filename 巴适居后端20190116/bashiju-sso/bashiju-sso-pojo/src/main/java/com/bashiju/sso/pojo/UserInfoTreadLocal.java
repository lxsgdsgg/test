package com.bashiju.sso.pojo;

import java.util.Map;

public class UserInfoTreadLocal  {
	private static final ThreadLocal<Map<String,String>> LOCAL = new ThreadLocal<Map<String,String>>();

    private UserInfoTreadLocal() {

    }

    public static void set(Map<String, String> map) {
        LOCAL.set(map);
    }

    public static Map<String,String> get() {
        return LOCAL.get();
    }
    public static String get(String key) {
        return LOCAL.get().get(key);
    }
	
}
