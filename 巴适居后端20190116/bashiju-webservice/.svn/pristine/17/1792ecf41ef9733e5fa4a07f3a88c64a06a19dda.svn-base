package com.bashiju.wutils.log;

import java.util.HashMap;
import java.util.Map;


public class ExecutionResult {

    private static final ThreadLocal<Map<String,Object>> LOCAL = new ThreadLocal<Map<String,Object>>();

    private ExecutionResult() {

    }
/**
 * 
 * @Title  descFormat   
 * @Description  自定义方法执行内容  
 * @param  sourceId 资源ID
 * @param   excuteContext  执行的内容         
 */
    public static void descFormat(String sourceId,String excuteContext) {
    	Map<String,Object> map=new HashMap<>();
    	map.put("sourceId", sourceId);
    	map.put("excuteContext", excuteContext);
        LOCAL.set(map);
    }
    public static void descFormat(String operationType,String sourceId, String excuteContext) {
    	Map<String,Object> map=new HashMap<>();
    	map.put("sourceId", sourceId);
    	map.put("excuteContext", excuteContext);
    	map.put("operationType", operationType);
        LOCAL.set(map);
    }

    public static Map<String,Object> get() {
        return LOCAL.get();
    }
    
    public static void remove() {
    	LOCAL.remove();
    }

}
