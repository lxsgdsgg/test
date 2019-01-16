/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ICommunityServiceApi.java   
 * @Package com.bashiju.wapi   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 上午9:41:09   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.wapi;

import java.util.List;

import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.community.CommunityQueryParams;
import com.bashiju.www.pojo.service.community.HotCommunity;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;

/**   
 * 小区列表页服务接口
 * @ClassName:  ICommunityServiceApi   
 * @Description:小区列表页服务接口   
 * @author: yangz
 * @date:   2018年8月15日 上午9:41:09   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ICommunityServiceApi {

	/**
	 * 条件查询小区信息
	 * @Description: 条件查询小区信息   
	 * @param params 查询参数
	 * @return: WebPage<Community>
	 */
	public WebPage<Community> queryCommunitys(CommunityQueryParams params);
	
	
	/**
	 * 查询城市下的热门小区   (返回最热门的10条房产资讯)
	 * @Description: 查询城市下的热门小区   (返回最热门的10条房产资讯)
	 * @param cityCode 城市代码
	 * @return: List<HotCommunity>
	 */
	public List<HotCommunity> queryHotCommunitys(String cityCode);
	
	/**
	 * 查询房产资讯 (返回最新6条房产资讯)  
	 * @Description: 查询房产资讯 (返回最新6条房产资讯)  
	 * @param cityCode 城市代码
	 * @return: List<ArticleListResult>
	 */
	public List<ArticleListResult> queryNewZiXunList(String cityCode);
	
	/**
	 * 小区查询条件列表 
	 * @Description: 小区查询条件列表    
	 * @param cityCode 城市代码
	 * @return: List<QueryCondition>
	 * @see com.bashiju.www.service.comm.ICommService#queryQueryConditions(java.lang.String, java.lang.String)   
	 */
	public List<QueryCondition> queryQueryConditions(String cityCode);
	
	/**
	 * 全文搜索查询小区(搜索条件：小区名称，片区，区域)
	 * @Description: 全文搜索查询小区(搜索条件：小区名称，片区，区域)  
	 * @param cityCode 城市编号
	 * @param params 搜索条件：小区名称，片区，区域(条件长度不小于两个字符不大于84个字符)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: WebPage<Community>
	 */
	public WebPage<Community> queryCommunityByFullText(String cityCode,String params,int pageNum,int pageSize);
}
