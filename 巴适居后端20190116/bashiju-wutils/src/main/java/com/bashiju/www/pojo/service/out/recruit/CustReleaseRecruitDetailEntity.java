/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustReleaseRecruitDetailEntity.java   
 * @Package com.bashiju.www.pojo.service.out.recruit      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 上午11:43:33   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.recruit;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 招聘详细页实体类
 * @ClassName:CustReleaseRecruitDetailEntity
 * @Description:招聘详细页实体类
 * @author:zuoyuntao
 * @date:2018年8月7日 上午11:43:33
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustReleaseRecruitDetailEntity implements Serializable  {
	/**   
	 * @Fields serialVersionUID :  
	 */ 
	private static final long serialVersionUID = -2637621983104570513L;
	/**主键ID**/
	@NotEmpty(message="主键不可为空")
	private int id;
	/**招聘人数**/
	@NotEmpty(message="招聘人数不可为空")
	private int count;
	/**学历要求0--高中及以上 1--大专及以上2--本科及以上3--硕士及以上4--博士及以上**/
	@NotEmpty(message="学历要求不可为空")
	private int education;
	/**最低工作年限**/
	private int minWorking;
	/**最高工作年限**/
	private int maxWorking;
	/**期望薪资**/
	private int wages;
	@NotEmpty(message="截止时间不可为空")
	/**截止时间**/
	private String endTime;
	@NotEmpty(message="发布人ID不可为空")
	/**发布人ID**/
	private int publisherId;
	@NotEmpty(message="发布时间不可为空")
	/**发布时间**/
	private String publishTime;
	@NotEmpty(message="操作人ID不可为空")
	/**操作人ID**/
	private int operatorId;
	@NotEmpty(message="是否可用不可为空")
	/**是否有效**/
	private int isValid;
	@NotEmpty(message="工作地点不可为空")
	/**工作地点编码**/
	private String workCityCode;
	/**工作地点名称**/
	private String workCityName;
	@NotEmpty(message="职位名称不可为空")
	/**职位名称**/
	private String name;
	@NotEmpty(message="所需专业不可为空")
	/**所需专业**/
	private String majorSubject;
	@NotEmpty(message="电话号码不可为空")
	/**手机号码**/
	private String mobile;
	@NotEmpty(message="任职要求不可为空")
	/**任职要求**/
	private String requirement;
	@NotEmpty(message="主要职责不可为空")
	/**主要职责**/
	private String duty;
	@NotEmpty(message="发布人不可为空")
	/**职位发布人**/
	private String publisher;
	@NotEmpty(message="权限域不可为空")
	/**权限域**/
	private String permissionArea;
	@NotEmpty(message="操作人不可为空")
	/**操作人**/
	private String operator;
	@NotEmpty(message="添加时间不可为空")
	/**添加时间**/
	private String addTime;
	/**更新时间**/
	private String updateTime;
	
}
