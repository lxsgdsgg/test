package com.bashiju.www.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.enums.BottomContentEnum;
import com.bashiju.enums.HouseArticleTypeEnum;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.article.ArticleInfoResult;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.service.article.IArticleService;
import com.bashiju.www.service.comm.IAreaService;
import com.github.pagehelper.Page;

/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  InterFaceTest.java   
 * @Package    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月25日 下午5:24:12   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

/**   
 * @ClassName:  InterFaceTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年7月25日 下午5:24:12   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public class ArticleServiceTest extends BaseServiceTest {
	
	
	@Autowired
	private IArticleService iArticleService;
	
	/*@Test
	public void test1() {
		String cityCode = "530100";
		String type = "1";
		List<ArticleListResult> res = iArticleService.queryArticleList(cityCode,type,5);
		for(int i=0;i<res.size();i++) {
			String a = res.get(i).getTitle();
		}
		System.out.println(res.toString());
		assertNotNull(res);
	}*/
	
	/*@Test
	public void test2() {
		String cityCode = "530100";
		String type = HouseArticleTypeEnum.PROPERTYENCYCLOPEDIA.getCode();
		List<ArticleTypeResult> res = iArticleService.queryArticleType(cityCode, type);
		System.out.println(JSON.toJSONString(res));
		res = iArticleService.queryArticleType(cityCode, HouseArticleTypeEnum.PROPERTYINFORMATION.getCode());
		System.out.println(JSON.toJSONString(res));
		assertNotNull(res);
	}*/
	
	@Test
	public void queryArticleList() {
		String cityCode = "530100";
		String type = "1";
		WebPage<ArticleListResult> res = iArticleService.queryArticleList(cityCode,"",type,0,5);
		for(int i=0;i<res.getResult().size();i++) {
			String a = res.getResult().get(i).getTitle();
		}
		System.out.println(res.toString());
		assertNotNull(res);
	}
	
	
	/*@Test
	public void test3() {
		String cityCode = "530100";
		String type = "1";
		ArticleInfoResult res = iArticleService.queryArticleInfo(7);
		String a = res.getTitle();
		System.out.println(res.toString());
		assertNotNull(res);
	}*/
	
	/*@Test
	public void test3() {
		String cityCode = "530100";
		String type = "1";
		ArticleInfoResult res = iArticleService.queryArticleInfo(6);
		String a = res.getTitle();
		System.out.println(res.toString());
		assertNotNull(res);
	}*/
	
	@Test
	public void test3() {
		String cityCode = "530100";
		int typeId = 0;
		WebPage<ArticleListResult> res = iArticleService.queryZiXunListByType(cityCode,typeId,0,5);
		
		System.out.println(res.toString());
		assertNotNull(res);
	}
	
	/*@Test
	public void test3() {
		String cityCode = "530100";
		int typeId = 35;
		WebPage<ArticleListResult> res = iArticleService.queryBaikeListByType(cityCode,typeId,0,5);
		
		System.out.println(res.toString());
		assertNotNull(res);
	}*/
	
	/*@Test
	public void test3() {
		String cityCode = "530100";
		int typeId = 35;
		List<ArticleListResult> res = iArticleService.queryHotZiXunList(cityCode,5);
		
		System.out.println(res.toString());
		assertNotNull(res);
	}*/
	
	/*@Test
	public void test3() {
		ArticleInfoResult res = iArticleService.queryArticle(BottomContentEnum.GYWM);
		System.out.println(res.getTitle());
		assertNotNull(res);
	}*/
}
