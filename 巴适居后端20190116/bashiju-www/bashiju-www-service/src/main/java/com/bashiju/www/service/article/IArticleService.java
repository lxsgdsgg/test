package com.bashiju.www.service.article;

import java.util.List;

import com.bashiju.enums.BottomContentEnum;
import com.bashiju.www.pojo.comm.WebPage;
import com.bashiju.www.pojo.service.out.article.ArticleInfoResult;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;
import com.github.pagehelper.Page;

/**
 * 
 *   文章服务
 * @ClassName:  IArticleService   
 * @Description:   文章服务
 * @author: wangkaifa
 * @date:   2018年7月30日 下午2:46:36       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IArticleService {

	/**
	 * 
		 * 根据类型查询文章列表，审核通过examineStatus=1,isValid=1,不存redis,按修改时间倒序查询
		 * @Description: 根据类型查询文章列表
		 * @param cityCode 尝试代码
		 * @param type 类型,详见 HouseArticleTypeEnum
		 * @param limit 返回条数
		 * @return 
		 * List<ArticleListResult>
	 */
	List<ArticleListResult> queryArticleList(String cityCode,String type,int limit);
	/**
	 * 
		 * 根据城市查询热门资讯列表，点击率高的的最新资讯文章
		 * 默认条件examineStatus=1,isValid=1
		 * redis的key值:WebGlobal.ARTICLE_ZIXUN_HOT_REDIS_PREFIX+城市代码
		 * @Description: 根据城市查询热门资讯列表，点击率高的的最新资讯文章 
		 * @param cityCode 城市代码
		 * @param limit 显示条数
		 * @return 
		 * List<ArticleListResult>
	 */
	List<ArticleListResult> queryHotZiXunList(String cityCode,int limit);
	
	/**
	 * 
		 * 根据城市查询热门百科列表，点击率高的的最新百科文章
		 * 默认条件examineStatus=1,isValid=1
		 * redis的key值:WebGlobal.ARTICLE_BAIKE_HOT_REDIS_PREFIX+城市代码
		 * @Description: 根据城市查询热门百科列表 
		 * @param cityCode 城市代码
		 * @param limit 显示条数
		 * @return 
		 * List<ArticleListResult>
	 */
	List<ArticleListResult> queryHotBaiKeList(String cityCode,int limit);
	/**
	 * 
		 * 根据关键字搜索文章，不需要存redis
		 * @Description: 根据关键字搜索文章 
		 * @param cityCode 城市代码
		 * @param key 搜索关键字
		 * @param type  类型,详见 HouseArticleTypeEnum
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * Page<ArticleListResult>
	 */
	WebPage<ArticleListResult> queryArticleList(String cityCode,String key,String type,int page,int limit);
	/**
	 * 
		 * 根据类型查询资讯，按时间倒序，类型为0查询全部资讯,
		 * 默认条件examineStatus=1,isValid=1
		 * redisde key值:用queryZiXunListByType+城市代码+类型id+当前页+每页条数,拼成字符串，调用Md5EncryptUtils.md5Encrypt(str)生成key值
		 * @Description: 根据类型查询资讯，按时间倒序，类型为0查询全部资讯
		 * @param cityCode 城市代码
		 * @param typeId   文章类型id
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * Page<ArticleListResult>
	 */
	WebPage<ArticleListResult> queryZiXunListByType(String cityCode,int typeId,int page,int limit);
	/**
	 * 
		 * 根据类型查询百科，按时间倒序，类型为0查询全部资讯
		 * 默认条件examineStatus=1,isValid=1
		 * redisde key值:用queryBaikeListByType+城市代码+类型id+当前页+每页条数,拼成字符串，调用Md5EncryptUtils.md5Encrypt(str)生成key值
		 * @Description: 进入菜单主页面（详细描述） 
		 * @param cityCode 城市代码
		 * @param typeId  文章类型id  
		 * @param page 当前页
		 * @param limit 每页条数
		 * @return 
		 * Page<ArticleListResult>
	 */
	WebPage<ArticleListResult> queryBaikeListByType(String cityCode,int typeId,int page,int limit);
	/**
	 * 
		 * 根据城市、类型查询文章类型
		 * redis的key:WebGlobal.ARTICLE_TYPE_REDIS_PREFIX+城市代码+类型
		 * @Description: 根据城市、类型查询文章类型 
		 * @param cityCode 城市代码
		 * @param type  类型,详见 HouseArticleTypeEnum
		 * @return 
		 * List<ArticleTypeResult>
	 */
	List<ArticleTypeResult> queryArticleType(String cityCode,String type);
	/**
	 * 
		 * 根据id查询文章详细,浏览次数加1
		 * redis的key:WebGlobal.ARTICLE_INFO_REDIS_PREFIX+文章id
		 * @Description: 根据id查询文章详细
		 * @param id 文章id
		 * @return 
		 * ArticleInfoResult
	 */
	ArticleInfoResult queryArticleInfo(int id);
	/**
	 * 
		 * 查询网站关于我们、联系我们、用户协议的详细信息
		 * 存入redis的key值:WebGlobal.HOME_PROTOCOL_REDIS_PREFIX+内容类型
		 * @Description: 查询网站关于我们、联系我们、用户协议的详细信息
		 * @param bottomContentEnum 内容类型
		 * @return 
		 * ArticleInfoResult
	 */
	ArticleInfoResult queryArticle(BottomContentEnum bottomContentEnum);
	
}
