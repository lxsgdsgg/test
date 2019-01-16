/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ReportTheadCols.java   
 * @Package com.bashiju.anal.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月8日 上午10:10:33   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.pojo;

import java.io.Serializable;
import java.util.List;

/**   
 * @ClassName:  ReportTheadCols   
 * @Description:表头行信息   
 * @author: yangz
 * @date:   2018年11月8日 上午10:10:33   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class Row implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 7787650520617221905L;
	
	
	private List<List<Cell>> cells; 
	
	public List<List<Cell>> getCells() {
		return cells;
	}
	public void setCells(List<List<Cell>> cells) {
		this.cells = cells;
	}

	@Override
	public String toString() {
		return "Row [cells=" + cells + "]";
	}
	
	public static class Builder{
		private List<List<Cell>> cells; 
		
		public Builder cells(List<List<Cell>> cells) {
			this.cells = cells;
			return this;
		}
		
		public Row build() {
			return new Row(this);
		} 
	}
	
	private Row(Builder builder) {
		this.cells = builder.cells;
	}
}
