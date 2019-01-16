package com.bashiju.oa.mapper;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ArticleMapper   
 * @Description:关于OA文章的mapper接口
 * @author: liwen
 * @date:   2018年10月05日 下午14:46:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ArticleMapper {
	
	public Page<Map<String, Object>> queryNewsList(Map<String,Object> conditions);
	public Page<Map<String, Object>> queryNoticeList(Map<String,Object> conditions);
	public Page<Map<String, Object>> queryExamineList(Map<String,Object> conditions);
	public List<Map<String,Object>> queryCityByAdmin();
	public List<Map<String,Object>> queryCityByUser(Long userId);
	public List<Map<String,Object>> queryCityByRole(Long userId);
	public String queryCityName(String cityCode);
	public List<Map<String, Object>> queryNewsInfo(@Param(value="cityCode") String cityCode,@Param(value="daynum") int daynum,@Param(value="companyId") String companyId);
	public List<Map<String, Object>> queryNoticeInfo(@Param(value="cityCode") String cityCode,@Param(value="daynum") int daynum,@Param(value="companyId") String companyId);
	public Map<String,Object> queryArticleById(int id);
	public Map<String,Object> queryArticleDetail(int id);
}
