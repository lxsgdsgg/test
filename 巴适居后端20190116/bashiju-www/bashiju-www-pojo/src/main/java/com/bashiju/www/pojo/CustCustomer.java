package com.bashiju.www.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
	

@Data
public class CustCustomer implements Serializable {
  
   /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;

	private Integer id;


	@NotEmpty(message="姓名不能空")
	@Size(max=4,message="姓名长度不能超过{max}位")

	 private String username;
	
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message="手机号格式不正确")
    private String mobile;
	
	@NotEmpty(message="密码不能空")
	@Size(max=2,message="密码长度不能超过{max}位")
    private String password;
	
	@NotEmpty(message="验证码不能空")
	@Size(max=6,message="验证码长度不能超过{max}位")
    private String solt;

    private String sex;
    private String operator;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") 
    private Date addtime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") 
    private Date updatetime;
	

    private byte isvalid;
    
}