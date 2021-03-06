/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustomerRecruitQueryParam.java   
 * @Package com.bashiju.www.pojo.service.input.recruit      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:13:47   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.input.recruit;

import java.io.Serializable;

import lombok.Data;

/**
 * 招聘管理查询参数实体类
 * @ClassName:CustomerRecruitQueryParam
 * @Description:招聘管理查询参数实体类
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:13:47
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustomerRecruitQueryParam implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = -8306563263945534475L;
	/**工作地点编码**/
	private String workCityCode;
	/**职位名称**/
	private String name;
	/**职位ID**/
	private int id;
}
