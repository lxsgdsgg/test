/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CommunityMapper.java   
 * @Package com.bashiju.webservice.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月24日 下午4:58:53   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.webservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bashiju.www.pojo.service.out.article.ArticleInfoResult;
import com.bashiju.www.pojo.service.out.article.ArticleListResult;
import com.bashiju.www.pojo.service.out.article.ArticleTypeResult;
import com.bashiju.www.pojo.service.out.article.RentSkillLinkResult;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  ArticleMapper   
 * @Description:资讯、百科等
 * @author: liwen
 * @date:   2018年7月30日 下午2:58:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ArticleMapper {

	public List<ArticleListResult> queryArticleList(@Param("cityCode")String cityCode,@Param("type")String type,@Param("limit")int limit);
	public List<ArticleTypeResult> queryArticleType(@Param("cityCode")String cityCode,@Param("type")String type);
	public List<ArticleListResult> queryHotList(@Param("cityCode")String cityCode,@Param("limit")int limit,@Param("type")String type);
	public Page<ArticleListResult> queryZiXunListByType(@Param("cityCode")String cityCode,@Param("typeId")int typeId,@Param("type")String bigtype);
	public Page<ArticleListResult> queryBaikeListByType(@Param("cityCode")String cityCode,@Param("typeId")int typeId,@Param("type")String bigtype);
	public ArticleInfoResult queryArticleInfo(int id);
	public Page<ArticleListResult> queryArticleListForPage(@Param("cityCode")String cityCode,@Param("key")String key,@Param("type")String type);
	public List<ArticleInfoResult> queryArticle(String classesId);
	public List<RentSkillLinkResult> queryRentSkills(@Param("cityCode")String cityCode,@Param("classesId")int classesId,@Param("limit")int limit);
	public void addBrowseCnt(int id);
}
