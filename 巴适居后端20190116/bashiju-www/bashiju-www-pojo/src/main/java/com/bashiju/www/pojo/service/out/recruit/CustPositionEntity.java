/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustPositionEntity.java   
 * @Package com.bashiju.www.pojo.service.input.recruit      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:18:02   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.recruit;

import lombok.Data;

/**
 * 职位信息实体类
 * @ClassName:CustPositionEntity
 * @Description:职位信息实体类
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:18:02
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustPositionEntity {
	/**职位编码**/
	private String postionCode;
	/**职位名称**/
	private String positionName;
	/**发布时间**/
	private String releaseTime;
	/**发布人**/
	private String addUser;
	
}
