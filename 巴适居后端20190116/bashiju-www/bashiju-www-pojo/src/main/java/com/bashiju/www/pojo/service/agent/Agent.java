/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  Agent.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月27日 下午6:11:54   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.agent;

/**   
 * @ClassName:  Agent   
 * @Description:经纪人信息实体   
 * @author: yangz
 * @date:   2018年7月27日 下午6:11:54   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class Agent {

	/**
	 * 系统用户编号
	 */
	private String userId;
	
	/**
	 * 经纪人编号
	 */
	private String agentId;
	
	/**
	 * 经纪人名称
	 */
	private String agentName;
	
	/**
	 * 经纪人所属部门
	 */
	private String deptId;
	
	/**
	 * 头像地址
	 */
	private String profileUrl;
	
	/**
	 * 职位名称
	 */
	private String postName;
	
	/**
	 * 主营区域代码(多个用英文逗号隔开)
	 */
	private String mainAreaCodes;
	
	/**
	 * 与主营区域代码对应的名称(多个用英文逗号隔开)
	 */
	private String mainAreaNames;
	
	/**
	 * 主营片区代码(多个用英文逗号隔开)
	 */
	private String mainRegionsCodes;
	
	/**
	 * 与主营片区代码对应的名称(多个用英文逗号隔开)
	 */
	private String mainRegionsNames;
	/**
	 * 最近30天带看记录
	 */
	private String showedRdCnt;
	
	/**
	 * 历史成交记录
	 */
	private String histShowedRdCnt;
	
	/**
	 * 星级评分
	 */
	private String StarRating;
	
	/**
	 * 客户历史评价记录数
	 */
	private String commentCnt;
	
	/**
	 * 固定电话号码
	 */
	private String telPhone;
	
	/**
	 * 经纪人标签
	 */
	private String label;

	
	public Agent() {
		super();
	}

	/**  
	 * @Title:  getUserId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getUserId() {
		return userId;
	}

	/**  
	 * @Title:  setUserId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**  
	 * @Title:  getAgentId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAgentId() {
		return agentId;
	}

	/**  
	 * @Title:  setAgentId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	/**  
	 * @Title:  getAgentName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAgentName() {
		return agentName;
	}

	/**  
	 * @Title:  setAgentName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	/**  
	 * @Title:  getDeptId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getDeptId() {
		return deptId;
	}

	/**  
	 * @Title:  setDeptId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**  
	 * @Title:  getProfileUrl <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getProfileUrl() {
		return profileUrl;
	}

	/**  
	 * @Title:  setProfileUrl <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	/**  
	 * @Title:  getPostName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getPostName() {
		return postName;
	}

	/**  
	 * @Title:  setPostName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	/**  
	 * @Title:  getMainAreaCodes <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMainAreaCodes() {
		return mainAreaCodes;
	}

	/**  
	 * @Title:  setMainAreaCodes <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMainAreaCodes(String mainAreaCodes) {
		this.mainAreaCodes = mainAreaCodes;
	}

	/**  
	 * @Title:  getMainAreaNames <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMainAreaNames() {
		return mainAreaNames;
	}

	/**  
	 * @Title:  setMainAreaNames <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMainAreaNames(String mainAreaNames) {
		this.mainAreaNames = mainAreaNames;
	}

	/**  
	 * @Title:  getMainRegionsCodes <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMainRegionsCodes() {
		return mainRegionsCodes;
	}

	/**  
	 * @Title:  setMainRegionsCodes <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMainRegionsCodes(String mainRegionsCodes) {
		this.mainRegionsCodes = mainRegionsCodes;
	}

	/**  
	 * @Title:  getMainRegionsNames <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMainRegionsNames() {
		return mainRegionsNames;
	}

	/**  
	 * @Title:  setMainRegionsNames <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMainRegionsNames(String mainRegionsNames) {
		this.mainRegionsNames = mainRegionsNames;
	}

	/**  
	 * @Title:  getShowedRdCnt <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getShowedRdCnt() {
		return showedRdCnt;
	}

	/**  
	 * @Title:  setShowedRdCnt <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setShowedRdCnt(String showedRdCnt) {
		this.showedRdCnt = showedRdCnt;
	}

	/**  
	 * @Title:  getHistShowedRdCnt <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getHistShowedRdCnt() {
		return histShowedRdCnt;
	}

	/**  
	 * @Title:  setHistShowedRdCnt <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setHistShowedRdCnt(String histShowedRdCnt) {
		this.histShowedRdCnt = histShowedRdCnt;
	}

	/**  
	 * @Title:  getStarRating <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getStarRating() {
		return StarRating;
	}

	/**  
	 * @Title:  setStarRating <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setStarRating(String starRating) {
		StarRating = starRating;
	}

	/**  
	 * @Title:  getCommentCnt <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCommentCnt() {
		return commentCnt;
	}

	/**  
	 * @Title:  setCommentCnt <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCommentCnt(String commentCnt) {
		this.commentCnt = commentCnt;
	}

	/**  
	 * @Title:  getTelPhone <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**  
	 * @Title:  setTelPhone <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**  
	 * @Title:  getLabel <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getLabel() {
		return label;
	}

	/**  
	 * @Title:  setLabel <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */
	@Override
	public String toString() {
		return "Agent [userId=" + userId + ", agentId=" + agentId + ", agentName=" + agentName + ", profileUrl="
				+ profileUrl + ", postName=" + postName + ", mainRegionsCodes=" + mainRegionsCodes
				+ ", mainRegionsNames=" + mainRegionsNames + ", showedRdCnt=" + showedRdCnt + ", histShowedRdCnt="
				+ histShowedRdCnt + ", StarRating=" + StarRating + ", commentCnt=" + commentCnt + ", telPhone="
				+ telPhone + ", label=" + label + "]";
	}
	
}
