/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CustCurriculumVitaeEntity.java   
 * @Package com.bashiju.www.pojo.service.out.recruit      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 下午12:08:21   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.www.pojo.service.out.recruit;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 简历详情实体类
 * @ClassName:CustCurriculumVitaeEntity
 * @Description:简历详情实体类
 * @author:zuoyuntao
 * @date:2018年8月7日 下午12:08:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class CustCurriculumVitaeEntity {
	/**主键ID***/
	@NotEmpty(message="ID不可为空")
	private int id;
	/**证件类型**/
	@NotEmpty(message="证件类型不可为空")
	private String docType;
	/**婚否0否1是**/
	@NotEmpty(message="婚否不可为空")
	private int isMarry;
	/**是否有效0否1是**/
	@NotEmpty(message="是否有效不可为空")
	private int isValid;
	/**操作人ID**/
	@NotEmpty(message="操作人Id不可为空")
	private int operatorId;
	/**性别：0男1女**/
	@NotEmpty(message="性别不可为空")
	private int sex;
	/**招聘岗位ID**/
	@NotEmpty(message="岗位ID不可为空")
	private int recruitInfoId;
	/**参加工作日期**/
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private String workDate;
	/**出生日期**/
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private String birthdate;
	/**毕业日期**/
	@NotEmpty(message="毕业日期不可为空")
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private String graduatDate;
	/**家庭住址**/
	private String address;
	/**添加时间**/
	private String addTime;
	/**附件**/
	private String attachment;
	/**证件号码**/
	@NotEmpty(message="证件号码不可为空")
	private String docCode;
	/**邮箱地址**/
	private String email;
	/**户籍所在地**/
	private String householdRegister;
	/**电话号码**/
	@NotEmpty(message="手机号不可为空")
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message="手机号格式不正确")
	private String mobile;
	/**姓名**/
	@NotEmpty(message="姓名不可为空")
	private String name;
	/**民族**/
	@NotEmpty(message="民族不可为空")
	private String nationalities;
	/**操作人**/
	@NotEmpty(message="操作人名称不可为空")
	private String operator;
	/**权限域**/
	@NotEmpty(message="权限域不可为空")
	private String permissionArea;
	/**政治面貌**/
	@NotEmpty(message="政治面貌不可为空")
	private String politicalOrientation;
	/**更新时间**/
	private String updateTime;
}
