/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  AgentQueryParams.java   
 * @Package com.bashiju.www.pojo.service.agent   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月28日 下午3:10:36   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.agent;

/**   
 * @ClassName:  AgentQueryParams   
 * @Description:经纪人查询条件   
 * @author: yangz
 * @date:   2018年7月28日 下午3:10:36   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class AgentQueryParams {

	/**
	 * 城市代码
	 */
	private String cityCode;
	
	/**
	 * 行政区域编码
	 */
	private String areaCode;
	
	/**
	 * 片区编号
	 */
	private String regionCode;
	
	/**
	 * 小区编号
	 */
	private String communityCode;
	
	/**
	 * 标签(多个片区之间用英文逗号隔开)
	 */
	private String labels;
	
	/**
	 * 经纪人名称
	 */
	private String agentName;
	
	/**
	 * 当前页
	 */
	private int pageNum;
	
	/**
	 * 每页显示的条数
	 */
	private int pageSize;

	public AgentQueryParams() {
		super();
	}

	/**  
	 * @Title:  getCityCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**  
	 * @Title:  setCityCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**  
	 * @Title:  getAreaCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**  
	 * @Title:  setAreaCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**  
	 * @Title:  getRegionCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getRegionCode() {
		return regionCode;
	}

	/**  
	 * @Title:  setRegionCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**  
	 * @Title:  getCommunityCode <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getCommunityCode() {
		return communityCode;
	}

	/**  
	 * @Title:  setCommunityCode <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}

	/**  
	 * @Title:  getLabel <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getLabels() {
		return labels;
	}

	/**  
	 * @Title:  setLabel <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setLabels(String labels) {
		this.labels = labels;
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
	 * @Title:  getPageNum <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**  
	 * @Title:  setPageNum <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**  
	 * @Title:  getPageSize <BR>  
	 * @Description:  <BR>  
	 * @return: int <BR>  
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**  
	 * @Title:  setPageSize <BR>  
	 * @Description: <BR>  
	 * @return: int <BR>  
	 */
	public void setPageSize(int pageSize) {
		if(pageSize>60) //当每页显示的条数大于60条的时候，则默认只查询30条
			pageSize=30;
		this.pageSize = pageSize;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */
	@Override
	public String toString() {
		return "AgentQueryParams [cityCode=" + cityCode + ", areaCode=" + areaCode + ", regionCode=" + regionCode
				+ ", communityCode=" + communityCode + ", labels=" + labels + ", agentName=" + agentName + ", pageNum="
				+ pageNum + ", pageSize=" + pageSize + "]";
	}
	
}
