/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HouseAttachType.java   
 * @Package com.bashiju.housing.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年6月7日 上午11:59:57   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.housing.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  HouseAttachType   
 * @Description:房源附件类型   
 * @author: yangz
 * @date:   2018年6月7日 上午11:59:57   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum HouseAttachTypeEnum {

	/**
	 * 房源图片
	 */
	HOUSEPICTURE("01","房源图片"),
	/**
	 * 房源相关图片
	 */
	RELATIONPICTURE("02","房源相关图片"),
	/**
	 * 房源视频
	 */
	HOUSEVIDEO("03","房源视频");
	
	public static Map<String , String> enumMap;
    /**
     * 
     */
    public static List<Map<String , String>> enumList;
    static {
        enumMap = new TreeMap<String, String>();
        enumList=new ArrayList<>();
        for (HouseAttachTypeEnum item : HouseAttachTypeEnum.values()) {
            enumMap.put(item.code, item.desc);
            Map<String , String> map=new HashMap<>();
            map.put("value", item.code);
            map.put("name", item.desc);
            enumList.add(map);
        }
    }
    /**
     * 枚举转换
     */
    public static HouseAttachTypeEnum parseOf(String value){
        for(HouseAttachTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    HouseAttachTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
