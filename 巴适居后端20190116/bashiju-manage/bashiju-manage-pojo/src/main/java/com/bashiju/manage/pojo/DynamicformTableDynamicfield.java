package com.bashiju.manage.pojo;

import java.util.Date;

public class DynamicformTableDynamicfield {
	private long id;
	private long addFieldId;
	private long delFieldId;
	private long tableId;
	private String companyId;
	private String operator;
	private Date addTime;
	private Date updateTime;
	private int status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAddFieldId() {
		return addFieldId;
	}
	public void setAddFieldId(long addFieldId) {
		this.addFieldId = addFieldId;
	}
	public long getDelFieldId() {
		return delFieldId;
	}
	public void setDelFieldId(long delFieldId) {
		this.delFieldId = delFieldId;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}

