/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  Col.java   
 * @Package com.bashiju.anal.pojo   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月8日 上午11:04:52   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.pojo;

import java.io.Serializable;

/**
 * 表头列属性
 * @ClassName:  Col   
 * @Description:表头列属性   
 * @author: yangz
 * @date:   2018年11月8日 上午10:33:02   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class Cell implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = -4851629080543585797L;

	/**
	 * 设定字段名。字段名的设定非常重要，且是表格数据列的唯一标识
	 */
	private String field;
	
	/**
	 * 设定标题名称
	 */
	private String title;
	
	/**
	 * 设定列宽，若不填写，则自动分配；若填写，则支持值为：数字、百分比 
			请结合实际情况，对不同列做不同设定。
	 */
	private String width;
	
	/**
	 * 局部定义当前常规单元格的最小宽度（默认：60），一般用于列宽自动分配的情况。其优先级高于基础参数中的 cellMinWidth
	 */
	private String minWidth;
	
	/**
	 * 设定列类型。可选值有：
		normal（常规列，无需设定）
		checkbox（复选框列）
		radio（单选框列，layui 2.4.0 新增）
		numbers（序号列）
		space（空列）
	 */
	private String type;
	
	/**
	 * 是否全选状态（默认：false）。必须复选框列开启后才有效，如果设置 true，则表示复选框默认全部选中。
	 */
	private Boolean LAY_CHECKED; 
	
	/**
	 * 固定列。可选值有：left（固定在左）、right（固定在右）。一旦设定，对应的列将会被固定在左或右，不随滚动条而滚动。 
		注意：如果是固定在左，该列必须放在表头最前面；如果是固定在右，该列必须放在表头最后面。
	 */
	private String fixed;
	
	/**
	 * 是否初始隐藏列，默认：false。layui 2.4.0 新增
	 */
	private Boolean hide;
	
	/**
	 * 是否开启该列的自动合计功能，默认：false。layui 2.4.0 新增
	 */
	private Boolean totalRow;
	
	/**
	 * 用于显示自定义的合计文本。layui 2.4.0 新增
	 */
	private String totalRowText; 
	
	/**
	 * 是否允许排序（默认：false）。如果设置 true，则在对应的表头显示排序icon，从而对列开启排序功能。
		注意：不推荐对值同时存在“数字和普通字符”的列开启排序，因为会进入字典序比对。比如：'贤心' > '2' > '100'，这可能并不是你想要的结果，但字典序排列算法（ASCII码比对）就是如此。
	 */
	private Boolean sort; 
	
	/**
	 * 是否禁用拖拽列宽（默认：false）。默认情况下会根据列类型（type）来决定是否禁用，如复选框列，会自动禁用。而其它普通列，默认允许拖拽列宽，当然你也可以设置 true 来禁用该功能。
	 */
	private Boolean unresize;
	
	/**
	 * 单元格编辑类型（默认不开启）目前只支持：text（输入框）
	 */
	private String edit;
	
	/**
	 * 自定义单元格点击事件名，以便在 tool 事件中完成对该单元格的业务处理
	 */
	private String event;
	
	/**
	 * 自定义单元格样式。即传入 CSS 样式
	 */
	private String style;
	
	/**
	 * 单元格排列方式。可选值有：left（默认）、center（居中）、right（居右）
	 */
	private String align;
	
	/**
	 * 单元格所占列数（默认：1）。一般用于多级表头
	 */
	private Integer colspan;
	
	/**
	 * 单元格所占行数（默认：1）。一般用于多级表头
	 */
	private Integer rowspan;
	
	/**
	 * 自定义列模板，模板遵循 laytpl 语法。这是一个非常实用的功能，你可借助它实现逻辑处理，以及将原始数据转化成其它格式，如时间戳转化为日期字符等
	 */
	private String templet;
	
	/**
	 * 绑定列工具条。设定后，可在每行列中出现一些自定义的操作性按钮
	 */
//	private String toolbar;

	/**  
	 * @Title:  getField <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getField() {
		return field;
	}

	/**  
	 * @Title:  setField <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**  
	 * @Title:  getTitle <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getTitle() {
		return title;
	}

	/**  
	 * @Title:  setTitle <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**  
	 * @Title:  getWidth <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getWidth() {
		return width;
	}

	/**  
	 * @Title:  setWidth <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**  
	 * @Title:  getMinWidth <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getMinWidth() {
		return minWidth;
	}

	/**  
	 * @Title:  setMinWidth <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	/**  
	 * @Title:  getType <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getType() {
		return type;
	}

	/**  
	 * @Title:  setType <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**  
	 * @Title:  getLAY_CHECKED <BR>  
	 * @Description:  <BR>  
	 * @return: Boolean <BR>  
	 */
	public Boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}

	/**  
	 * @Title:  setLAY_CHECKED <BR>  
	 * @Description: <BR>  
	 * @return: Boolean <BR>  
	 */
	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

	/**  
	 * @Title:  getFixed <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getFixed() {
		return fixed;
	}

	/**  
	 * @Title:  setFixed <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	/**  
	 * @Title:  getHide <BR>  
	 * @Description:  <BR>  
	 * @return: Boolean <BR>  
	 */
	public Boolean getHide() {
		return hide;
	}

	/**  
	 * @Title:  setHide <BR>  
	 * @Description: <BR>  
	 * @return: Boolean <BR>  
	 */
	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	/**  
	 * @Title:  getTotalRow <BR>  
	 * @Description:  <BR>  
	 * @return: Boolean <BR>  
	 */
	public Boolean getTotalRow() {
		return totalRow;
	}

	/**  
	 * @Title:  setTotalRow <BR>  
	 * @Description: <BR>  
	 * @return: Boolean <BR>  
	 */
	public void setTotalRow(Boolean totalRow) {
		this.totalRow = totalRow;
	}

	/**  
	 * @Title:  getTotalRowText <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getTotalRowText() {
		return totalRowText;
	}

	/**  
	 * @Title:  setTotalRowText <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setTotalRowText(String totalRowText) {
		this.totalRowText = totalRowText;
	}

	/**  
	 * @Title:  getSort <BR>  
	 * @Description:  <BR>  
	 * @return: Boolean <BR>  
	 */
	public Boolean getSort() {
		return sort;
	}

	/**  
	 * @Title:  setSort <BR>  
	 * @Description: <BR>  
	 * @return: Boolean <BR>  
	 */
	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	/**  
	 * @Title:  getUnresize <BR>  
	 * @Description:  <BR>  
	 * @return: Boolean <BR>  
	 */
	public Boolean getUnresize() {
		return unresize;
	}

	/**  
	 * @Title:  setUnresize <BR>  
	 * @Description: <BR>  
	 * @return: Boolean <BR>  
	 */
	public void setUnresize(Boolean unresize) {
		this.unresize = unresize;
	}

	/**  
	 * @Title:  getEdit <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getEdit() {
		return edit;
	}

	/**  
	 * @Title:  setEdit <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setEdit(String edit) {
		this.edit = edit;
	}

	/**  
	 * @Title:  getEvent <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getEvent() {
		return event;
	}

	/**  
	 * @Title:  setEvent <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**  
	 * @Title:  getStyle <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getStyle() {
		return style;
	}

	/**  
	 * @Title:  setStyle <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**  
	 * @Title:  getAlign <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getAlign() {
		return align;
	}

	/**  
	 * @Title:  setAlign <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**  
	 * @Title:  getColspan <BR>  
	 * @Description:  <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getColspan() {
		return colspan;
	}

	/**  
	 * @Title:  setColspan <BR>  
	 * @Description: <BR>  
	 * @return: Integer <BR>  
	 */
	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	/**  
	 * @Title:  getRowspan <BR>  
	 * @Description:  <BR>  
	 * @return: Integer <BR>  
	 */
	public Integer getRowspan() {
		return rowspan;
	}

	/**  
	 * @Title:  setRowspan <BR>  
	 * @Description: <BR>  
	 * @return: Integer <BR>  
	 */
	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	/**  
	 * @Title:  getTemplet <BR>  
	 * @Description:  <BR>  
	 * @return: String <BR>  
	 */
	public String getTemplet() {
		return templet;
	}

	/**  
	 * @Title:  setTemplet <BR>  
	 * @Description: <BR>  
	 * @return: String <BR>  
	 */
	public void setTemplet(String templet) {
		this.templet = templet;
	}

	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */
	@Override
	public String toString() {
		return "Col [field=" + field + ", title=" + title + ", width=" + width + ", minWidth=" + minWidth
				+ ", type=" + type + ", LAY_CHECKED=" + LAY_CHECKED + ", fixed=" + fixed + ", hide=" + hide
				+ ", totalRow=" + totalRow + ", totalRowText=" + totalRowText + ", sort=" + sort + ", unresize="
				+ unresize + ", edit=" + edit + ", event=" + event + ", style=" + style + ", align=" + align
				+ ", colspan=" + colspan + ", rowspan=" + rowspan + ", templet=" + templet + "]";
	}
	
	public static class Builder{
		
		private String field;
		private String title;
		private String width;
		private String minWidth;
		private String type;
		private Boolean LAY_CHECKED; 
		private String fixed;
		private Boolean hide;
		private Boolean totalRow;
		private String totalRowText; 
		private Boolean sort; 
		private Boolean unresize;
		private String edit;
		private String event;
		private String style;
		private String align;
		private Integer colspan;
		private Integer rowspan;
		private String templet;
//		private String toolbar;


		public Builder setField(String field) {
			this.field = field;
			return this;
		}
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		public Builder setWidth(String width) {
			this.width = width;
			return this;
		}
		public Builder setMinWidth(String minWidth) {
			this.minWidth = minWidth;
			return this;
		}
		public Builder setType(String type) {
			this.type = type;
			return this;
		}
		public Builder setLAY_CHECKED(Boolean lAY_CHECKED) {
			this.LAY_CHECKED = lAY_CHECKED;
			return this;
		}
		public Builder setFixed(String fixed) {
			this.fixed = fixed;
			return this;
		}
		public Builder setHide(Boolean hide) {
			this.hide = hide;
			return this;
		}
		public Builder setTotalRow(Boolean totalRow) {
			this.totalRow = totalRow;
			return this;
		}
		public Builder setTotalRowText(String totalRowText) {
			this.totalRowText = totalRowText;
			return this;
		}
		public Builder setSort(Boolean sort) {
			this.sort = sort;
			return this;
		}
		public Builder setUnresize(Boolean unresize) {
			this.unresize = unresize;
			return this;
		}
		public Builder setEdit(String edit) {
			this.edit = edit;
			return this;
		}
		public Builder setEvent(String event) {
			this.event = event;
			return this;
		}
		public Builder setStyle(String style) {
			this.style = style;
			return this;
		}
		public Builder setAlign(String align) {
			this.align = align;
			return this;
		}
		public Builder setColspan(Integer colspan) {
			this.colspan = colspan;
			return this;
		}
		public Builder setRowspan(Integer rowspan) {
			this.rowspan = rowspan;
			return this;
		}
		public Builder setTemplet(String templet) {
			this.templet = templet;
			return this;
		}
		
		public Cell build() {
			return new Cell(this);
		}

	}
	
	private Cell(Builder builder) {
		this.field = builder.field;
		this.title = builder.title;
		this.width = builder.width;
		this.minWidth = builder.minWidth;
		this.type = builder.type;
		this.LAY_CHECKED = builder.LAY_CHECKED;
		this.fixed = builder.fixed;
		this.hide = builder.hide;
		this.totalRow = builder.totalRow;
		this.totalRowText = builder.totalRowText;
		this.sort = builder.sort;
		this.unresize = builder.unresize;
		this.edit = builder.edit;
		this.event = builder.event;
		this.style = builder.style;
		this.align = builder.align;
		this.colspan = builder.colspan;
		this.rowspan = builder.rowspan;
		this.templet = builder.templet;
	}
	
	
	
}
