/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AdministrativeDivisionEntity.java   
 * @Package com.bashiju.www.pojo.service.out.usercentral      
 * @author: zuoyuntao     
 * @date:   2018年9月18日 下午6:00:29   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.usercentral;

import java.io.Serializable;

import lombok.Data;

/**
 * 行政区划实体类
 * @ClassName:AdministrativeDivisionEntity
 * @Description:行政区划实体类
 * @author:zuoyuntao
 * @date:2018年9月18日 下午6:00:29
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

@Data
public class AdministrativeDivisionEntity implements Serializable{

	/**   
	 * @Fields serialVersionUID : 串行版本标识ID  
	 */ 
	private static final long serialVersionUID = -2536551363880455161L;
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 行政区划编码
	 */
	private String areaCode;
	/**
	 * 行政区划名称
	 */
	private String areaName;
	/**
	 * 行政区划等级
	 */
	private int level;
	/**
	 * 父级行政区划编码
	 */
	private String parentCode;
	/**
	 * 坐标经度
	 */
	private String longitude;
	/**
	 * 坐标纬度
	 */
	private String latitude;

}
