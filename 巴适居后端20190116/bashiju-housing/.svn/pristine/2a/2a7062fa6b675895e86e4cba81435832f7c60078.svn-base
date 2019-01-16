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
package com.bashiju.housing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bashiju.housing.pojo.DeedTaxResult;
import com.bashiju.housing.pojo.HousePropertyResult;
/**   
 * @ClassName:  CommMapper   
 * @Description:
 * @author: liwen
 * @date:   2018年7月27日 下午4:58:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface CommMapper {

	/**
	 * 
		 * 查询贷款上限
		 * @Description: 查询贷款上限
		 * @param cityCode 城市代码
		 * @return 
		 * Map<String,Object>
	 */
	public Map<String, Object> queryLoanCeiling(@Param("cityCode")String cityCode);
	/**
	 * 
		 * 查询首付房源类型
		 * @Description:  查询首付房源类型
		 * @param cityCode 城市代码
		 * @return 
		 * List<HousePropertyResult>
	 */
	public List<HousePropertyResult> queryHousePropertyList(@Param("cityCode")String cityCode);
	/**
	 * 
		 * 查询契税比例
		 * @Description: 查询契税比例
		 * @param housePropertyId
		 * @return 
		 * List<DeedTaxResult>
	 */
	public List<DeedTaxResult> queryDeedTaxResult(@Param("housePropertyId")Integer housePropertyId);
	/**
	 * 
		 * 查询非住宅税费比例
		 * @Description: 进入菜单主页面（详细描述） 
		 * @return 
		 * List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryBusinessRateResult();
}
