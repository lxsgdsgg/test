package com.bashiju.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  PostService   
 * @Description: 职位管理服务接口
 * @author: wangpeng
 * @date:   2018年4月24日 下午3:06:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PostService {
	
	/**
	 * 
	 * @Title: queryPost   
	 * @Description: 通过id查询职位  
	 * @param id 职位主键
	 * @param postName 职位名称
	 * @param pageNum 当前页数
	 * @param pageSize 每一页显示条数
	 * @return: Page<Map<String,Object>>
	 */
	public Page<Map<String, Object>> queryPost(String postName,int pageNum,int pageSize);
	
	/**
	 * @Title: isRelation   
	 * @Description: 查询是否有关联数据   
	 * @param postId 角色id
	 * @return: boolean
	 */
	public boolean isRelation(String postId);
	
	/**
	 * @Title: saveOrUpdatePost   
	 * @Description: 修改和编辑职位信息   
	 * @param map
	 * @return: long
	 */
	boolean saveOrUpdatePost(Map<String,Object> map);
	
	 /**
	  * @Title: delPost   
	  * @Description: 逻辑删除职位
	  * @param postId 职位id
	  * @return: boolean
	  */
	boolean delPost(String postId);

}
