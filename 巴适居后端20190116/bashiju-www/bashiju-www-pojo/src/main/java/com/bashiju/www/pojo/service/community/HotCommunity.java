/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  HotCommunity.java   
 * @Package com.bashiju.www.pojo.service.community   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月25日 下午6:21:34   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.pojo.service.community;

/**   
 * @ClassName:  HotCommunity   
 * @Description:热搜小区   
 * @author: yangz
 * @date:   2018年7月25日 下午6:21:34   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class HotCommunity {

	/**
	 * 小区编号
	 */
	private String id;
	
	/**
	 * 小区名称
	 */
	private String name;
	/**
	 * 平均售价
	 */
	private String salePrice;
	/**
	 * 在售二手房套数
	 */
	private String onSaleCnt;
	
	
	/**   
	 * @Title:  HotCommunity   
	 *   
	 */  
	public HotCommunity() {
		super();
	}

	/**  
	 * @Title:  getId <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getId() {
		return id;
	}

	/**  
	 * @Title:  setId <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**  
	 * @Title:  getName <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * @Title:  setName <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * @Title:  getSalePrice <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**  
	 * @Title:  setSalePrice <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	/**  
	 * @Title:  getOnSaleCnt <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getOnSaleCnt() {
		return onSaleCnt;
	}

	/**  
	 * @Title:  setOnSaleCnt <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setOnSaleCnt(String onSaleCnt) {
		this.onSaleCnt = onSaleCnt;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */
	@Override
	public String toString() {
		return "HotCommunity [id=" + id + ", name=" + name + ", salePrice=" + salePrice + ", onSaleCnt=" + onSaleCnt
				+ "]";
	}
	
}
