/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  DealTransactionInfo.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年7月31日 下午3:25:58   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.agent;

import java.io.Serializable;

import lombok.Data;

/**  
 * 经纪人成交信息实体   
 * @ClassName:  DealTransactionInfo   
 * @Description:经纪人成交信息实体   
 * @author: wangpeng
 * @date:   2018年7月31日 下午3:25:58   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class DealTransactionInfo implements Serializable {  
	
	private static final long serialVersionUID = -4580665255155825357L;
	
	/**房源编号**/
	private String shhId;
	/**小区名称**/
	private String communityName;
	/**总价**/
	private String sellingPrice;
	/**单价**/
	private String averagePrice;
	/**卧室**/
	private  String room;
	/**大厅**/
	private String hall;
	/**卫生间**/
	private String toilet;
	/**使用面积**/
	private String useSpace;
	/**建筑面积**/
	private String buildSpace;
	/**朝向**/
	private String orientation;
	/**装修**/
	private String decoration;
	/**楼层描述(低层，高层...)**/
	private String floorDes;
	/**总楼层数**/
	private String totalLayers;
	/**建筑年代**/
	private String buildDates;
	/**签约时间（成交日期）**/
	private  String dealTime;
	/**小区图片**/
	private String imageUrl;

}
