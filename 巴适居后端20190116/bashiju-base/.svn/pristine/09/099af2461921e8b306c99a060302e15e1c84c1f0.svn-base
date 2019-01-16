/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  MainService.java   
 * @Package com.bashiju.sso.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 上午11:39:11   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.base.service.ssoqueryservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.bashiju.api.PermissionServiceApi;
import com.bashiju.base.mapper.PermissionCertMapper;
import com.bashiju.base.service.redisservice.RedisClusterServiceImpl;
import com.bashiju.utils.global.SYSGlobal;
import com.bashiju.utils.log.SystemServiceLog;

/**   
 * @ClassName:  PermissionService   
 * @Description:系统权限服务   
 * @author: yangz
 * @date:   2018年4月10日 上午11:39:11   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
@SystemServiceLog(sourceType="权限查询 ")
public class PermissionServiceimpl extends RedisClusterServiceImpl implements PermissionServiceApi{

	@Autowired
	private PermissionCertMapper permissionMapper;
	
	
	/**
	 * 
	 * @Description: 根据菜单id查询用户权限内子一级菜单信息     
	 * @param userId 用户id
	 * @param menuId 菜单id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryFunctionMenuPermission(String userId,String menuId){
		if(StringUtils.isEmpty(userId))
			return null;
		String key = SYSGlobal.USER_LOGIN_PC+userId;
		String field = SYSGlobal.MENU_functionPermission+"_"+menuId;
		String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
		List<Map<String,Object>> result = null;
		if(StringUtils.isEmpty(jsonStr)) {
			result = permissionMapper.queryFunctionMenuPermission(userId, menuId);
			if(result!=null && result.size()>0) {
				this.setHash(key, field, JSONArray.toJSONString(result));
			}
		}else {
			result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Description: 根据用户id查询该用户的菜单权限(不包括功能中的按钮权限)
	 * @param userId 用户id
	 * @return
	 * @throws Exception      
	 * @return: List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	@SystemServiceLog(operationType="查询全部菜单权限")
	public List<Map<String,Object>> callQueryUserMenuPermission(String userId){
		if(StringUtils.isNotEmpty(userId)) {
			String key = SYSGlobal.USER_LOGIN_PC+userId;
			String field = SYSGlobal.MENU_userMenuPermission;
//			String jsonStr = this.get(key);
			String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
			List<Map<String,Object>> result = null;
			if(StringUtils.isEmpty(jsonStr)) {
				result =permissionMapper.queryUserMenuPermission(userId);
				result = getData(result);
				if(result!=null && result.size()>0) {
					this.setHash(key, field, JSONArray.toJSONString(result));
				}
			}else {
				result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
			}
			return result;
		}else 
			return null;
	}
	
	/**
	 * 
	 * @Description: 根据用户id查询该用户的菜单权限(不包括功能中的按钮权限)
	 * @param userId 用户id
	 * @return
	 * @throws Exception      
	 * @return: List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> callQueryUserMenuPermission2(String userId) {
		if(StringUtils.isNotEmpty(userId)) {
			String key = SYSGlobal.USER_LOGIN_PC+userId;
			String field = SYSGlobal.MENU_userMenuPermission;
//			String jsonStr = this.get(key);
			String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
			List<Map<String,Object>> result = null;
			if(StringUtils.isEmpty(jsonStr)) {
				result = permissionMapper.queryUserMenuPermission2(userId);
				result = getData2(result);
				if(result!=null && result.size()>0) {
					this.setHash(key, field, JSONArray.toJSONString(result));
				}
			}else {
				result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
			}
			Map<String,Object> rts = new HashMap<String,Object>();
			rts.put("code", 0);
			rts.put("msg", "success");
			rts.put("data", result);
			return rts;
		}else 
			return null;
	}
	
	/**
	 * 
	 * @Description: 根据用户id查询一级菜单     
	 * @param userId 用户id
	 * @return
	 * @throws Exception      
	 * @return: List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	@SystemServiceLog(operationType="查询一级菜单权限")
	public List<Map<String,Object>> callQueryFirstMenuByUser(String userId){
		
		if(StringUtils.isEmpty(userId))
			return null;
		String key = SYSGlobal.USER_LOGIN_PC+userId;
		String field = SYSGlobal.MENU_FirstMenuPermission;
		String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
		List<Map<String,Object>> result = null;
		if(StringUtils.isEmpty(jsonStr)) {
			result = permissionMapper.queryFirstMenuByUser(userId);
			if(result!=null && result.size()>0) {
				this.setHash(key, field, JSONArray.toJSONString(result));
			}
		}else {
			result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 根据菜单id查询用户权限内子一级菜单信息     
	 * @param userId 用户id
	 * @param menuId 菜单id
	 * @return: List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	@SystemServiceLog(operationType="查询子一级菜单权限")
	public List<Map<String,Object>> callQueryChildMenus(String userId,String menuId){
		
		if(StringUtils.isEmpty(userId))
			return null;
		String key = SYSGlobal.USER_LOGIN_PC+userId;
		String field = SYSGlobal.MENU_childMenuPermission+"_"+menuId;
		String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
		List<Map<String,Object>> result = null;
		if(StringUtils.isEmpty(jsonStr)) {
			result = permissionMapper.queryChildMenus(userId, menuId);
			if(result!=null && result.size()>0) {
				this.setHash(key, field, JSONArray.toJSONString(result));
			}
		}else {
			result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
		}
		return result;
	}
	

	/**
	 * 
	 * @Description: 查询用户菜单下的唯一按钮权限     
	 * @param userId 用户id
	 * @param menuId 菜单id
	 * @see com.bashiju.api.PermissionServiceApi#callQueryUserMenusOnlyButton(java.lang.String, java.lang.String)   
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> callQueryUserMenusOnlyButton(String userId, String menuId) {
		if(StringUtils.isEmpty(userId))
			return null;
		String key = SYSGlobal.USER_LOGIN_PC+userId;
		String field = SYSGlobal.MENU_childMenuOnlyButtonPermission+"_"+menuId;
		String jsonStr = this.getHash(key, field);//先从内存中查找是否存在
		List<Map<String,Object>> result = null;
		if(StringUtils.isEmpty(jsonStr)) {
			result = permissionMapper.callQueryUserMenusOnlyButton(userId, menuId);
			if(result!=null && result.size()>0) {
				this.setHash(key, field, JSONArray.toJSONString(result));
			}
		}else {
			result = (List<Map<String, Object>>) JSONArray.parse(jsonStr);
		}
		return result;
	}
	
	/**	
	 * 
	 * @Description: 将菜单转为树形结构;     
	 * @param list 菜单列表
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	
	private List<Map<String,Object>> getData(List<Map<String,Object>> list) {
		List<Map<String,Object>> news = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> rd : list){
			if(rd.get("menuParentId") ==null || StringUtils.isEmpty(rd.get("menuParentId").toString())){
				Map<String,Object> parent = new HashMap<String,Object>();
				parent.put("menuId", rd.get("menuId"));
				parent.put("menuName", rd.get("menuName"));
				parent.put("url", rd.get("url"));
				parent.put("datas", getTrees(list, rd.get("menuId").toString()));
				
				news.add(parent);
			}
		}
		return news;
	}
	
	/**
	 * 
	 * @Description: 获取树节点   
	 * @param list 菜单列表
	 * @param parentId 父级id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	private List<Map<String,Object>> getTrees(List<Map<String,Object>> list,String parentId){
		List<Map<String,Object>> mps = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> item : list){
			if(item.get("menuParentId").toString().equals(parentId)){
				item.put("datas", getTrees(list, item.get("menuId").toString()));
				mps.add(item);
			}
		}
		return mps;
	}
	
	/**	
	 * 
	 * @Description: 将菜单转为树形结构;     
	 * @param list 菜单列表
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	
	private List<Map<String,Object>> getData2(List<Map<String,Object>> list) {
		List<Map<String,Object>> news = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> rd : list){
			if(rd.get("pid") ==null || StringUtils.isEmpty(rd.get("pid").toString())){
				Map<String,Object> parent = new HashMap<String,Object>();
				parent.put("id", rd.get("id"));
				parent.put("title", rd.get("title"));
				parent.put("url", rd.get("url"));
				parent.put("children", getTrees2(list, rd.get("id").toString()));
//				parent.put("font", "larry-icon");
//				parent.put("icon", "larry-shouye4");
//				parent.put("spread", false);
				news.add(parent);
			}
		}
		return news;
	}
	
	/**
	 * 
	 * @Description: 获取树节点   
	 * @param list 菜单列表
	 * @param parentId 父级id
	 * @return      
	 * @return: List<Map<String,Object>>
	 */
	private List<Map<String,Object>> getTrees2(List<Map<String,Object>> list,String parentId){
		List<Map<String,Object>> mps = new LinkedList<Map<String,Object>>();
		for(Map<String,Object> item : list){
			if(item.get("pid").toString().equals(parentId)){
				List<Map<String,Object>> children = getTrees2(list, item.get("id").toString());
				if(children!=null && children.size()>0) {
					item.put("children", children);
				}
				mps.add(item);
			}
		}
		return mps;
	}

}
