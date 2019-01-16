package com.bashiju.www.pojo.service.input.house;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 
 *   房源举报对象
 * @ClassName:  HouseTipOffParam   
 * @Description:  房源举报对象
 * @author: wangkaifa
 * @date:   2018年7月27日 下午2:58:22       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseTipOffParam implements Serializable{
	private static final long serialVersionUID = -6100350533627651886L;
	/**客户id**/
	private int custId;
	/**资源类型:0为房源，1为新房楼盘**/
	private int sourceType;
	/**资源id**/
	private int sourceId;
	/**举报类型id**/
	private String tipOffTypeId;
	/**举报类型名称**/
	private String tipOffType;
	/**举报类容**/
	private String content;
	/**举报时间**/
	private Date addTime;
}
