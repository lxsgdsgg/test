package com.bashiju.manage.pojo;


/**
 * 
 * @ClassName:  FieldReg   
 * @Description:表单字段的正则表达式的验证
 * @author: liwen
 * @date:   2018年4月9日 上午9:45:31   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class FieldReg {
	private int id;
	private String fieldReg;
	private String regMess;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFieldReg() {
		return fieldReg;
	}
	public void setFieldReg(String fieldReg) {
		this.fieldReg = fieldReg;
	}
	public String getRegMess() {
		return regMess;
	}
	public void setRegMess(String regMess) {
		this.regMess = regMess;
	}
	
	
}
