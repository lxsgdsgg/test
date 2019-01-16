/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MenuFunctionType.java   
 * @Package com.bashiju.manage.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月11日 上午9:44:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.enums;

import java.util.Map;
import java.util.TreeMap;

/**   
 * @ClassName:  DecisionConfigurateEnum   
 * @Description:决策配置项枚举类(该枚举内容应该时刻与数据库表sys_decision_configurate时时同步)
 * @author: yangz
 * @date:   2018年7月09日 上午09:49:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DecisionConfigurateEnum {
	
	 //同步sql语句：select concat('/**',(case when type=0 then '房源--' when type=1 then '求购--' when type=2 then '求租--' when type=3 then '成交--' else '其他--' end ),name,'DECISION_',id,'("',id,'",','"',name,'")**/\n','DECISION_',id,'("',id,'",','"',name,'"),')dd from sys_decision_configurate where isvalid=1 
	
	/**房源--淘宝本人保护期限DECISION_1("1","淘宝本人保护期限")**/
	DECISION_1("1","淘宝本人保护期限"),
	/**房源--淘宝本店保护期限DECISION_2("2","淘宝本店保护期限")**/
	DECISION_2("2","淘宝本店保护期限"),
	/**房源--淘宝本区保护期限DECISION_3("3","淘宝本区保护期限")**/
	DECISION_3("3","淘宝本区保护期限"),
	/**房源--私盘数量DECISION_4("4","私盘数量")**/
	DECISION_4("4","私盘数量"),
	/**房源--同小区电话判重DECISION_5("5","同小区电话判重")**/
	DECISION_5("5","同小区电话判重"),
	/**房源--录入默认私盘DECISION_6("6","录入默认私盘")**/
	DECISION_6("6","录入默认私盘"),
	/**房源--查看本人电话次数控制DECISION_7("7","查看本人电话次数控制")**/
	DECISION_7("7","查看本人电话次数控制"),
	/**房源--免骚扰限制DECISION_8("8","免骚扰限制")**/
	DECISION_8("8","免骚扰限制"),
	/**房源--每天查看电话次数DECISION_9("9","每天查看电话次数")**/
	DECISION_9("9","每天查看电话次数"),
	/**房源--每天可淘宝数量DECISION_10("10","每天可淘宝数量")**/
	DECISION_10("10","每天可淘宝数量"),
	/**房源--通知撤单后失效DECISION_11("11","通知撤单后失效")**/
	DECISION_11("11","通知撤单后失效"),
	/**房源--每天查看门牌号次数DECISION_12("12","每天查看门牌号次数")**/
	DECISION_12("12","每天查看门牌号次数"),
	/**房源--封盘最多到期天数DECISION_13("13","封盘最多到期天数")**/
	DECISION_13("13","封盘最多到期天数"),
	/**房源--图片水印位置DECISION_14("14","图片水印位置")**/
	DECISION_14("14","图片水印位置"),
	/**房源--置顶到期时间DECISION_15("15","置顶到期时间")**/
	DECISION_15("15","置顶到期时间"),
	/**求购--淘宝本人保护期限DECISION_16("16","淘宝本人保护期限")**/
	DECISION_16("16","淘宝本人保护期限"),
	/**求购--淘宝本店保护期限DECISION_17("17","淘宝本店保护期限")**/
	DECISION_17("17","淘宝本店保护期限"),
	/**求购--淘宝本区保护期限DECISION_18("18","淘宝本区保护期限")**/
	DECISION_18("18","淘宝本区保护期限"),
	/**求购--私盘数量DECISION_19("19","私盘数量")**/
	DECISION_19("19","私盘数量"),
	/**求购--通知撤单后失效DECISION_20("20","通知撤单后失效")**/
	DECISION_20("20","通知撤单后失效"),
	/**求购--免骚扰限制DECISION_21("21","免骚扰限制")**/
	DECISION_21("21","免骚扰限制"),
	/**求购--每天可淘宝数量DECISION_22("22","每天可淘宝数量")**/
	DECISION_22("22","每天可淘宝数量"),
	/**求购--判重范围DECISION_23("23","判重范围")**/
	DECISION_23("23","判重范围"),
	/**求购--封盘最多到期天数DECISION_24("24","封盘最多到期天数")**/
	DECISION_24("24","封盘最多到期天数"),
	/**求租--淘宝本人保护期限DECISION_25("25","淘宝本人保护期限")**/
	DECISION_25("25","淘宝本人保护期限"),
	/**求租--淘宝本店保护期限DECISION_26("26","淘宝本店保护期限")**/
	DECISION_26("26","淘宝本店保护期限"),
	/**求租--淘宝本区保护期限DECISION_27("27","淘宝本区保护期限")**/
	DECISION_27("27","淘宝本区保护期限"),
	/**求租--私盘数量DECISION_28("28","私盘数量")**/
	DECISION_28("28","私盘数量"),
	/**求租--通知撤单后失效DECISION_29("29","通知撤单后失效")**/
	DECISION_29("29","通知撤单后失效"),
	/**求租--免骚扰限制DECISION_30("30","免骚扰限制")**/
	DECISION_30("30","免骚扰限制"),
	/**求租--每天可淘宝数量DECISION_31("31","每天可淘宝数量")**/
	DECISION_31("31","每天可淘宝数量"),
	/**求租--判重范围DECISION_32("32","判重范围")**/
	DECISION_32("32","判重范围"),
	/**求租--封盘最多到期天数DECISION_33("33","封盘最多到期天数")**/
	DECISION_33("33","封盘最多到期天数"),
	/**其他--5分钟查看电话次数DECISION_34("34","5分钟查看电话次数")**/
	DECISION_34("34","5分钟查看电话次数"),
	/**其他--锁定时间间隔DECISION_35("35","锁定时间间隔")**/
	DECISION_35("35","锁定时间间隔"),
	/**其他--经纪人电话显示DECISION_36("36","经纪人电话显示")**/
	DECISION_36("36","经纪人电话显示"),
	/**其他--是否开启下定自动封盘DECISION_37("37","是否开启下定自动封盘")**/
	DECISION_37("37","是否开启下定自动封盘");
	

    public static Map<String , String> enumMap;
    
    static {
        enumMap = new TreeMap<String, String>();
        for (DecisionConfigurateEnum item : DecisionConfigurateEnum.values()) {
            enumMap.put(item.code, item.desc);
        }
    }
    /**
     * 枚举转换
     */
    public static DecisionConfigurateEnum parseOf(String value){
        for(DecisionConfigurateEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }

    private String code;
    private String desc;

    DecisionConfigurateEnum(String code, String desc) {
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
