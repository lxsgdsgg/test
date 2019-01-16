package com.bashiju.www.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.wapi.IZiXunPageServiceApi;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.article.ArticleInfoResult;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;
import com.bashiju.www.service.article.IArticleService;

/**
 * 
 *  资讯页面接口实现
 * @ClassName:  ZiXunPageServiceImpl   
 * @Description:   资讯页面接口实现
 * @author: wangkaifa
 * @date:   2018年7月31日 上午11:22:21       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class ZiXunPageServiceApiImpl implements IZiXunPageServiceApi {
	@Autowired
	private IArticleService articleService;
	
	@Override
	public List<ArticleListResult> queryHotZiXunList(String cityCode, int limit) {		
		return articleService.queryHotZiXunList(cityCode, limit);
	}

	@Override
	public List<ArticleListResult> queryNewZiXunList(String cityCode, int limit) {
		List<ArticleListResult> results=articleService.queryArticleList(cityCode, HouseArticleTypeEnum.PROPERTYINFORMATION.getCode(), limit);
		return results;
	}

	@Override
	public List<ArticleTypeResult> queryZiXunType(String cityCode) {
		List<ArticleTypeResult> results=articleService.queryArticleType(cityCode, HouseArticleTypeEnum.PROPERTYINFORMATION.getCode());
		return results;
	}

	@Override
	public WebPage<ArticleListResult> queryZiXunListByTypeId(String cityCode, int typeId, int page, int limit) {
		WebPage<ArticleListResult> result=articleService.queryZiXunListByType(cityCode, typeId, page, limit);
		return result;
	}

	@Override
	public WebPage<ArticleListResult> queryZiXunList(String cityCode, String key, int page, int limit) {
		WebPage<ArticleListResult> result=articleService.queryArticleList(cityCode, key, HouseArticleTypeEnum.PROPERTYINFORMATION.getCode(), page, limit);
		return result;
	}

	@Override
	public ArticleInfoResult queryZiXunArticleInfo(int id) {
		ArticleInfoResult result=articleService.queryArticleInfo(id);
		return result;
	}

}
