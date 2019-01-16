package com.bashiju.www.pojo.comm;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

import lombok.Data;

@Data
public class WebPage<E> implements Serializable {
	 /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 包含count查询
     */
    private boolean count = true;
    /**
     * 分页合理化
     */
    private Boolean reasonable;
    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;
    /**
     * 进行count查询的列名
     */
    private String countColumn;
    /**
     * 排序
     */
    private String orderBy;
    /**
     * 只增加排序
     */
    private boolean orderByOnly;
    
    private List<E> result;
    
    public WebPage() {
    	
    }
    public WebPage(Page<E> page) {
    	this.pageNum=page.getPageNum();
    	this.pageSize=page.getPageSize();
    	this.startRow=page.getStartRow();
    	this.endRow=page.getEndRow();
    	this.total=page.getTotal();
    	this.pages=page.getPages();
    	this.count=page.isCount();
    	this.reasonable=page.getReasonable();
    	this.pageSizeZero=page.getPageSizeZero();
    	this.countColumn=page.getCountColumn();
    	this.orderBy=page.getOrderBy();
    	this.orderByOnly=page.isOrderByOnly();
    	this.result=page.getResult();
    }
}
