package com.bashiju.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bashiju.manage.service.IUserService;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.util.BashijuResult;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="usercity")
public class UserOpenCityController {
	@Autowired
    private IUserService userService;
	
	//用户分配城市表格数据
	@RequestMapping(value="queryUserOpenCityList")
	@ResponseBody
	public Map<String, Object> queryUserOpenCityList(String deptId, Long userId,int page,int limit){
		Page<Map<String, Object>> results=userService.queryUserOpenCity(deptId, userId, page, limit);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("count", results.getTotal());
		map.put("data", results.getResult());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	//新增用户分配城市
	@RequestMapping(value="addUserOpenCity")
	@ResponseBody
	public BashijuResult addUserOpenCity(String jsonData) {
		if (StringUtils.isNotEmpty(jsonData)) {
			Integer result=userService.addUserOpenCity(jsonData);
			return BashijuResult.ok(result);
		}
		throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
	}
	//修改用户分配城市
	@RequestMapping(value="updateUserOpenCity")
	@ResponseBody
	public BashijuResult updateUserOpenCity(Integer id, String cityCodes, String cityNames) {
		Integer result=userService.updateUserOpenCity(id, cityCodes, cityNames);
		return BashijuResult.ok(result);
	}
	//删除用户分配城市
	@RequestMapping(value="deleteUserOpenCity")
	@ResponseBody
	public BashijuResult deleteUserOpenCity(Integer id) {
		userService.deleteUserOpenCity(id);
		return BashijuResult.ok();
	}
	/**
	 * 
		 * 公司开通城市下拉
		 * @Description: 公司开通城市下拉 
		 * @param companyId
		 * @return 
		 * BashijuResult
	 */
	@RequestMapping(value="queryCompanyOpenCity")
	@ResponseBody
	public BashijuResult queryCompanyOpenCity(String companyId) {
		List<Map<String, Object>> result=userService.queryCompanyOpenCity(companyId);
		return BashijuResult.ok(result);
	}
}
