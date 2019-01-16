package com.bashiju.www.pojo.service.out.house;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *  房源图片对象
 * @ClassName:  HouseImageResult   
 * @Description:   房源图片对象
 * @author: wangkaifa
 * @date:   2018年7月24日 下午6:29:17       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class HouseImageResult implements Serializable{
	
	private static final long serialVersionUID = 2263246380131388526L;
	/**图片类型id**/
	private int pictureTypeId;
	/**图片类型名称**/
	private String pictureTypeName;
	/**附近类型**/
	private String attachType;
	/**图片url**/
	private String url;
	/**是否封面图片**/
	private String isCover;
	

}
