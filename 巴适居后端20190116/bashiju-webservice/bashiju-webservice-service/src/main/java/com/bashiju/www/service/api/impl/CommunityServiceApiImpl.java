/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityServiceApi.java   
 * @Package com.bashiju.www.service.api.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年8月15日 上午10:06:43   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.enums.pc.ConditionTypeEnum;
import com.bashiju.wapi.ICommunityServiceApi;
import com.bashiju.www.pojo.comm.QueryCondition;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.community.Community;
import com.bashiju.www.pojo.service.community.CommunityQueryParams;
import com.bashiju.www.pojo.service.community.HotCommunity;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;
import com.bashiju.www.service.article.IArticleService;
import com.bashiju.www.service.comm.ICommService;
import com.bashiju.www.service.community.CommunityService;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CommunityServiceApi   
 * @Description:小区列表页服务   
 * @author: yangz
 * @date:   2018年8月15日 上午10:06:43   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class CommunityServiceApiImpl implements ICommunityServiceApi {
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ICommService commService;

	/**
	 * @Description: 条件查询小区信息   
	 * @param params 查询参数
	 * @return: Page<Community>
	 * @see com.bashiju.wapi.ICommunityServiceApi#queryCommunitys(com.bashiju.www.pojo.service.community.CommunityQueryParams)   
	 */
	@Override
	public WebPage<Community> queryCommunitys(CommunityQueryParams params) {
		Page<Community> page = communityService.queryCommunitys(params);
		WebPage<Community> result=new WebPage<Community>(page);
		return result; 
	}

	/**
	 * @Description: 查询城市下的热门小区   (返回最热门的10条房产资讯)
	 * @param cityCode 城市代码
	 * @return: List<HotCommunity>
	 * @see com.bashiju.wapi.ICommunityServiceApi#queryHotCommunitys(java.lang.String)   
	 */
	@Override
	public List<HotCommunity> queryHotCommunitys(String cityCode) {
		int size = 10;
		return communityService.queryHotCommunitys(cityCode, size);
	}

	/**
	 * @Description: 查询房产资讯 (返回最新6条房产资讯)  
	 * @param cityCode 城市代码
	 * @return: List<ArticleListResult>
	 * @see com.bashiju.wapi.ICommunityServiceApi#queryNewZiXunList(java.lang.String)   
	 */
	@Override
	public List<ArticleListResult> queryNewZiXunList(String cityCode) {
		int size = 6;
		return articleService.queryArticleList(cityCode,HouseArticleTypeEnum.PROPERTYINFORMATION.getCode(), size);
	}

	/**
	 * 小区查询条件列表 
	 * @Description: 小区查询条件列表    
	 * @param cityCode 城市代码
	 * @return: List<QueryCondition>  
	 * @see com.bashiju.wapi.ICommunityServiceApi#queryQueryConditions(java.lang.String)   
	 */
	@Override
	public List<QueryCondition> queryQueryConditions(String cityCode) {
		return commService.queryQueryConditions(cityCode, ConditionTypeEnum.COMMUNITY);
	}

	/**
	 * 全文搜索查询小区(搜索条件：小区名称，片区，区域)
	 * @Description: 全文搜索查询小区(搜索条件：小区名称，片区，区域)  
	 * @param cityCode 城市编号
	 * @param params 搜索条件：小区名称，片区，区域(条件长度不小于两个字符不大于84个字符)
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条数
	 * @return: WebPage<Community>
	 * @see com.bashiju.wapi.ICommunityServiceApi#queryCommunityByFullText(java.lang.String, java.lang.String, int, int)   
	 */
	@Override
	public WebPage<Community> queryCommunityByFullText(String cityCode, String params, int pageNum, int pageSize) {
		Page<Community> page = this.communityService.queryCommunityByFullText(cityCode, params, pageNum, pageSize);
		WebPage<Community> result=new WebPage<Community>(page);
		return result; 
	}

}
