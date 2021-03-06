package com.bashiju.www.pojo.service.out.newhouse;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   新房列表对象
 * @ClassName:  NewHouseListResult   
 * @Description:   新房列表对象
 * @author: wangkaifa
 * @date:   2018年8月21日 上午11:45:37       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class NewHouseListResult implements Serializable {

	private static final long serialVersionUID = -1665006208122627762L;
	/**新房楼盘id**/
	private Integer id;
	/**新房楼盘名称**/
	private String name;
	/**是否有VR:0否,1是**/
	protected int isVR;
	/**是否有视频:0否,1是**/
	protected int isVideo;
	/**新房楼盘地址**/
	private String address;
	/**新房楼盘开盘时间**/
	private String openDate;
	/**新房楼盘交房时间**/
	private String deliverDate;
	/**新房楼盘均价**/
	private String avgPrice;
	/**新房楼盘标签**/
	private String labeld;
	/**新房楼盘封面图地址**/
	private String imageUrl;
	/**关注次数**/
	protected int attentionCount;
	/**最近15天带看次数**/
	private int lastViewingCount;
	/**新房楼盘房型,多个逗号连接**/
	private String huxingDesc;
}
