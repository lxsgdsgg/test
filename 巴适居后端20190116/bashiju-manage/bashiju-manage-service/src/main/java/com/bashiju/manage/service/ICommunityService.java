package com.bashiju.manage.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.bashiju.manage.pojo.controller.CommunityEntity;
import com.bashiju.manage.pojo.controller.CommunityQueryEntity;
import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  ICommunityService   
 * @Description:TODO(小区管理)   
 * @author: zhaobin
 * @date:   2018年4月20日 下午5:24:47   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface ICommunityService {

	/**
	 * 
	 * @Title: queryCommunityList   
	 * @Description: TODO(查询小区列表)   
	 * @param: @param map
	 * @param: @return      
	 * @return: Page<Map<String,Object>>      
	 * @throws
	 */
	public Page<Map<String, Object>>  queryCommunityList(CommunityQueryEntity query,int page,int limit) throws Exception;
	/**
	 * 
	 * @Title: queryCommunityById   
	 * @Description: TODO(根据id查询小区信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object>  queryCommunityById(Long id);
	/**
	 * 
	 * @Title: addCommunity   
	 * @Description: TODO(添加小区)   
	 * @param: @param name
	 * @param: @param areaCode
	 * @param: @param regionId
	 * @param: @param address
	 * @param: @param price
	 * @param: @param isHot
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String addCommunity (String name,String recordName,String pinyin,String areaCode,Long regionId,String address,int price,int isHot);
	/**
	 * 
		 * 删除小区
		 * @Description: 删除小区 
		 * @param id
		 * @return 
		 * boolean
	 */
	boolean delete(Long id);
	/**
	 * 
	 * @Title: updateCommunity   
	 * @Description: TODO(修改小区名称)   
	 * @param: @param map
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	long updateCommunity (Map<String,Object> map);
	/**
	 * 
	 * @Title: queryAreaSelect   
	 * @Description: TODO(查询行政区域下拉列表数据)   
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> queryAreaSelect();
	/**
	 * 
	 * @Title: queryRegionSelect   
	 * @Description: TODO(查询片区下拉框数据)   
	 * @param: @param code
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String, Object>> queryRegionSelect(String code);
	/**
	 * 
	 * @Title: saveImage   
	 * @Description: TODO(上传小区图片)   
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long saveImage(HttpServletRequest request);
	/**
	 * 
	 * @Title: queryImage   
	 * @Description: TODO(查询小区图片)   
	 * @param: @param communityId
	 * @param: @param attachType
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	public List queryImage(int communityId,String attachType);
	/**
	 * 
	 * @Title: updateImage   
	 * @Description: TODO(更新小区图片信息)   
	 * @param: @param data
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	public Long updateImage(Map<String,Object> data);
	
	/**
	 * 
	 * @Title: queryImageList   
	 * @Description: TODO(根据小区id查询小区图片列表)   
	 * @param: @param communityId
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> queryImageList(Long communityId);
	/**
	 * 
	 * @Title: queryImageBycode   
	 * @Description: TODO(根据图片编号查询图片流)   
	 * @param: @param code
	 * @param: @return      
	 * @return: Byte[]      
	 * @throws
	 */
	byte[] queryImageBycode(String code);
	/**
	 * 
	 * @Title: deleteImage   
	 * @Description: TODO(删除小区图片)   
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean deleteImage(String ids);
	/**
	 * 
		 * 查询片城市、区域、区列表
		 * @Description: 查询片城市、区域、区列表
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryRegionList();
}
