/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TransferProcessEnum.java   
 * @Package com.bashiju.enums   
 * @Description:过户流程管理枚举类
 * @author: zuoyuntao     
 * @date:   2018年6月23日 下午3:06:43   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 过户流程管理枚举类
 * 
 * @ClassName:TransferProcessEnum
 * @Description:过户流程管理枚举类
 * @author:zuoyuntao
 * @date:2018年6月23日 下午3:06:43
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 *             本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum TransferProcessEnum {
	TYPE_HOUSE_OWNER_SET("1", "业主详细材料"),
	TYPE_CUSTOMER_SET("2", "客户详细材料"),
	TYPE_PROCESS_SET("3", "过户流程配置"),
	
	PROC_CONTRACT("1","签买卖（定金）合同"),
	PROC_ONLINE_SIGN("2","网签（或公证）"),
	PROC_TAX("3","交税费"),
	PROC_SHOW_CITIFCATY("4","交件出具不动产登记受理通知书"),
	PROC_WAIT_NEW_CITIFICATE("5","交件后等待出新证"),
	PROC_EXCHANGE_WUYE("6","物业交割（水、电、煤、有线电视）"),
	PROC_SIGN_LOAN_CONTRACT("7","签贷款合同"),
	PROC_MAKE_CONTRACT("8","作买卖合同公证、网签、买方签商贷合同"),
	PROC_MORTGAGE("9","按揭抵押"),
	PROC_WART_MONEY("10","等到银行放款给卖方"),
	PROC_GET_CITIFCATY("11","买方领取新产权证"),
	PROC_APPOINTMENT_NOTARIZATION("12","作买卖预约合同公证"),
	PROC_APPLY_BUSSINESSS_LOAN("13","买方申请商业贷款"),
	PROC_APPLY_ACCUMULATION_FUND_LOAN("14","买方申请公积金贷款"),
	PROC_APPLY_COMBINATION_LOAN("15","买方申请组合贷款"),
	PROC_END("16","结束");
	
	/**
	 * 编码字段
	 */
	private String code;
	/**
	 * 字段描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  TransferProcessEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	TransferProcessEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

    static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (TransferProcessEnum item : TransferProcessEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
