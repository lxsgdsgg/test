package com.bashiju.wutils.log;

public class CustomLog {


	/**
     * 日志id
     */
    private Integer id;
	/**
     * 当前操作人姓名
     */
    private String loginUser;
    /**
     * 当前操作人所在部门Id
     */
    private String loginUserDepId;


	/**
     * 当前操作人所在部门名称
     */
    private String loginUserDepName;
    
    /**
     * 当前操作人所在公司Id
     */
    private String loginUserCompanyId;
	/**
     * 当前操作人所在公司名称
     */
    private String loginUserCompanyName;
    
    /**
     * 当前操作人ip
     */
    private String loginIp;
    
    /**
     * 当前操作人所在区域
     */
    private String loginArea;


    /**
     * 操作请求的链接
     */
    private String actionUrl;

    /**
     * 执行的资源类型
     */
    private String sourceType;

    /**
     * 执行的操作类型
     */
    private String operationType;

    /**
     * 执行方法所消耗的时间
     */
    private Long actionTime;

	/**
     *资源Id
     */
    private String sourceId;
    /**
     *执行的内容
     */
    private String excuteContext;


    /**
     * 执行方法的时间
     */
    private String gmtCreate;
    
/*    *//**
     * 执行的主机名
     *//*
    private String localName;    

	*//**
     * 执行的MAC
     *//*
    private String localMac;
*/
    /**
     * 该操作状态，1表示成功，-1表示失败！
     */
    private Short state;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginUserDepId() {
		return loginUserDepId;
	}

	public void setLoginUserDepId(String loginUserDepId) {
		this.loginUserDepId = loginUserDepId;
	}

	public String getLoginUserDepName() {
		return loginUserDepName;
	}

	public void setLoginUserDepName(String loginUserDepName) {
		this.loginUserDepName = loginUserDepName;
	}

	public String getLoginUserCompanyId() {
		return loginUserCompanyId;
	}

	public void setLoginUserCompanyId(String loginUserCompanyId) {
		this.loginUserCompanyId = loginUserCompanyId;
	}

	public String getLoginUserCompanyName() {
		return loginUserCompanyName;
	}

	public void setLoginUserCompanyName(String loginUserCompanyName) {
		this.loginUserCompanyName = loginUserCompanyName;
	}
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginArea() {
		return loginArea;
	}

	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

    public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getExcuteContext() {
		return excuteContext;
	}

	public void setExcuteContext(String excuteContext) {
		this.excuteContext = excuteContext;
	}

	public Long getActionTime() {
		return actionTime;
	}

	public void setActionTime(Long actionTime) {
		this.actionTime = actionTime;
	}


	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}


}
