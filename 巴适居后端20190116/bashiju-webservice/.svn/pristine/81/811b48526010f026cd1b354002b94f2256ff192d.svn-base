package com.bashiju.wutils.util;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 巴士居ERP自定义响应结构
 */
public class BashijuResult implements Serializable{

    /**   
	 * @Fields serialVersionUID   序列化 
	 */ 
	
	private static final long serialVersionUID = -2460884668183597995L;

	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private boolean success;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static BashijuResult build(boolean success, String msg, Object data) {
        return new BashijuResult(success, msg, data);
    }

    public static BashijuResult ok(Object data) {
        return new BashijuResult(data);
    }

    public static BashijuResult ok() {
        return new BashijuResult(null);
    }

    public BashijuResult() {

    }

    public static BashijuResult build(boolean success, String msg) {
        return new BashijuResult(success, msg, null);
    }

    public BashijuResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public BashijuResult(Object data) {
        this.success = true;
        this.msg = "操作成功";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.success == 200;
//    }

    public boolean getsuccess() {
        return success;
    }

    public void setsuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为BashijuResult对象
     * 
     * @param jsonData json数据
     * @param clazz BashijuResult中的object类型
     * @return
     */
   /* public static BashijuResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, BashijuResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("success").asBoolean(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }*/

    /**
     * 将json串转化为BashijuResult对象
     * 
     * @param json
     * @return
     */
   /* public static BashijuResult format(String json) {
        try {
            return MAPPER.readValue(json, BashijuResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
   /* public static BashijuResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("success").asBoolean(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }*/

}


