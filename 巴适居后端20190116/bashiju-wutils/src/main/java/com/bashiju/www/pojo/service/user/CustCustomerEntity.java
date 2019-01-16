/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustCustomer.java   
 * @Package com.bashiju.www.pojo.service.user   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月31日 下午4:56:40   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bashiju.www.pojo.service.out.usercentral.CustomerConcernedHouseEntity;
import com.bashiju.www.pojo.service.out.usercentral.CustomerHouseTransitScheduleEntity;

import lombok.Data;

/**   
 * @ClassName:  CustCustomer   
 * @Description:后台保存用户输入信息实体 
 * @author: wangpeng
 * @date:   2018年7月31日 下午4:56:40   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustCustomerEntity implements Serializable {
	
	private static final long serialVersionUID = -141638135870965923L;
	
	/**用户名称**/
	private String name;
	/**用户电话号码**/
	private String mobile;
	/**密码**/
	private String password;
	/**短信验证码**/
	private String msgCode;
	/**盐值**/
	private String solt;
	/**性别0:女， 1 男**/
	private String sex;
	/**添加时间**/
	private Date  addTime;
	/**修改时间**/
	private Date updateTime;
	/**是否有效**/
	private  String isValid;
	/**操作人**/
	private String operator;
	/**省**/
	private String proviceCode;
	/**省级区划名称**/
	private String proviceName;
	/**城市代码**/
	private String cityCode;
	/**城市名称**/
	private String cityName;
	/**区域代码**/
	private String areaCode;
	/**县区名称**/
	private String areaName;
	/**地址**/
	private String address;
	/**出生年月日**/
	private Date birthday;
	/**要修改的电话**/
	private String newMobile;
	/**要修改的密码**/
	private String newPassword;
	/**add by zuoyuntao 个人中心使用**/
	/**主键ID**/
	private String id;
	/**我的预约数量**/
	private long reservationCount;
	/**我的足迹数量**/
	private String footprintCount;
	/**我的关注数量**/
	private long concernedCount;
	/**房源过户进度集合--个人中心使用**/
	private List<CustomerHouseTransitScheduleEntity> transitScheduleList;
	/**我的关注数据集合--个人中心使用**/
	private List<CustomerConcernedHouseEntity> concernedHouseList;
	//201811-07
	/**账号类型：qq:01 , wx：02，微信小程序：05**/
	private String numType;
	/**头像信息**/
	private String profilePhoto;
}
