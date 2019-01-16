package com.bashiju.www.pojo.service.out.article;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *   房源类型对象
 * @ClassName:  ArticleTypeResult   
 * @Description:   房源类型对象
 * @author: wangkaifa
 * @date:   2018年7月30日 下午2:56:22       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class ArticleTypeResult implements Serializable {
	
	private static final long serialVersionUID = 1836516903587811748L;
	/**类型id**/
	private int id;
	/**类型名称**/
	private String name;
	/**类型父级id**/
	private int parentId;
	/**类型树形深度，从1开始**/
	private int level;
	
}
