package com.bashiju.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.cert.interceptors.DataAuthHelper;
import com.bashiju.enums.MenuEnum;
import com.bashiju.manage.mapper.CommMapper;
import com.bashiju.manage.service.ICommService;
import com.bashiju.utils.threadlocal.UserThreadLocal;

@Service
public class CommService implements ICommService {
	@Autowired
	CommMapper commMapper;
	@Autowired
	DataAuthHelper dataAuthHelper;

	@Override
	public List<Map<String, Object>> queryAllCity() {
		List<Map<String, Object>> result=commMapper.queryAllCity();
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCity() {
		/*List<Map<String, Object>> result=commMapper.queryOpenCity();
		return result;*/
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCity();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCity(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyOpenCity(companyId,cityCodes);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCounty() {
		/*List<Map<String, Object>> result=commMapper.queryOpenCityCounty();
		return result;*/
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityCounty();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityCounty(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyOpenCityCounty(companyId,cityCodes);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCountyRegion() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityRegion();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityRegion(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyOpenCityRegion(companyId,cityCodes);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCountyRegionCommunity() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		List<Integer> regionIds=new ArrayList<>();
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityRegion();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityRegion(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyOpenCityRegion(companyId,cityCodes);
		}
		if (result!=null&&result.size()>0) {
			for (Map<String, Object> item : result) {
				if ("4".equals(item.get("level").toString())) {
					regionIds.add(Integer.parseInt(item.get("code").toString()));
					item.put("code", "r-"+item.get("code").toString());
					item.put("leaf", 0);
				}
			}
		}
		if (regionIds.size()>0) {
			List<Map<String, Object>> communitys=commMapper.queryCommunityByRegionIds(regionIds);
			result.addAll(communitys);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenProvinceCity() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		List<Integer> regionIds=new ArrayList<>();
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenProvinceCity();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenProvinceCity(userId, roleId);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCompanyDepart() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityCompanyDepart();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityCompanyDepart(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyDepartment(companyId,cityCodes);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCompanyDepartUser() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityCompanyDepartUser();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityCompanyDepartUser(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyDepartmentUser(companyId,cityCodes);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCompany() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityCompany();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityCompany(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			result=new ArrayList<>();
			String companyId=user.get("companyId").toString();
			String companyName=user.get("comName").toString();
			Map<String, Object> data=new HashMap<>();
			data.put("code", companyId);
			data.put("name", companyName);
			data.put("parentCode", "0");
			data.put("dataType", "company");
			data.put("leaf", "1");
			result.add(data);
		}
		return result;
	}
	
	/**
	 * 根据城市区划编码查询 行政区、片区
	 * @Title: queryAreaAndRegion
	 * @author: zuoyuntao  
	 * @Description:根据城市区划编码查询 行政区、片区 
	 * @param cityCode 城市编码
	 * @param deptId 部门ID
	 * @return      
	 * List<Map<String,Object>> 
	 */
	@Override
	public List<Map<String,Object>> queryAreaAndRegion(String cityCode,String deptId) {
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>(0);
		retList = commMapper.queryAreaAndRegionByDeptIdAndArea(cityCode,deptId);
		if(retList.size() > 0) {
			return retList;
		}
		return commMapper.queryAreaAndRegion(cityCode);
	}
	/**
	 * 根据片区ID查询小区信息
	 * @Title: queryCommunityByRegionCode
	 * @author: zuoyuntao  
	 * @Description:根据片区ID查询小区信息   
	 * @param regionId
	 * @return      
	 * Object 
	 */
	@Override
	public List<Map<String,Object>> queryCommunityByRegionId(String regionId) {
		return commMapper.queryCommunityByRegionId(regionId);
	}

	@Override
	public List<Map<String, Object>> queryOpenCityCountyRegionAllCommunity() {
		Map user = UserThreadLocal.get();
		String roleGroup=user.get("roleGroup").toString();
		List<Map<String, Object>> result=null;
		List<Integer> regionIds=new ArrayList<>();
		if ("01".equals(roleGroup)) {
			result=commMapper.queryAllOpenCityRegion();
		}
		if ("02".equals(roleGroup)) {
			Integer userId=Integer.parseInt(user.get("id").toString());
			Integer roleId=Integer.parseInt(user.get("roleId").toString());
			result=commMapper.queryManagerOpenCityRegion(userId, roleId);
		}
		if ("03".equals(roleGroup)) {
			String userManageCityCodes=user.get("userManageCityCodes")!=null?user.get("userManageCityCodes").toString():null;
			String companyId=user.get("companyId").toString();
			List<String> cityCodes=null;
			if (StringUtils.isNotEmpty(userManageCityCodes)) {
				String[] citys=userManageCityCodes.split(",");
				cityCodes=Arrays.asList(citys);
			}else {
				cityCodes=new ArrayList<>();
				cityCodes.add(user.get("cityCode").toString());
			}
			result=commMapper.queryCompanyOpenCityRegion(companyId,cityCodes);
		}
		if (result!=null&&result.size()>0) {
			for (Map<String, Object> item : result) {
				if ("4".equals(item.get("level").toString())) {
					regionIds.add(Integer.parseInt(item.get("code").toString()));
					item.put("code", "r-"+item.get("code").toString());
					item.put("leaf", 0);
				}
			}
		}
		if (regionIds.size()>0) {
			List<Map<String, Object>> communitys=commMapper.queryAllCommunityByRegionIds(regionIds);
			result.addAll(communitys);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryDepartmentUserList() {
		dataAuthHelper.auth(MenuEnum.MENU_64.getCode(),UserThreadLocal.get().get("id").toString());
		List<Map<String, Object>> results=commMapper.queryDepartmentList();
		if (results!=null&&results.size()>0) {
			List<String> deptIds=new ArrayList<>();
			for (Map<String, Object> map : results) {
				deptIds.add(map.get("code").toString());
			}
			List<Map<String, Object>> users=commMapper.queryDepartmentUserList(deptIds);
			results.addAll(users);
		}
		return results;
	}

	
	@Override
	public List<Map<String, Object>> queryAreaByCityCode(String cityCode) {
		List<Map<String, Object>> map = commMapper.queryAreaByCityCode(cityCode);
		return map;
	}

	
	@Override
	public List<Map<String, Object>> queryRegionByAreaCode(String areaCode) {
		List<Map<String, Object>> map = commMapper.queryRegionByAreaCode(areaCode);
		return map;
	}
}
