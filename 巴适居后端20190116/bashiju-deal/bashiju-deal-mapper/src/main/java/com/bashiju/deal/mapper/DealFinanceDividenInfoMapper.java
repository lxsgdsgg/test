package com.bashiju.deal.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
/**
 * 
 * @ClassName:  DealFinanceDividenInfoMapper   
 * @Description:TODO(金融分成查询映射接口)   
 * @author: wangpeng
 * @date:   2018年6月29日 下午2:31:55   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DealFinanceDividenInfoMapper {
		/**
		 * 
		 * @Title: queryDealFinanceDividenInfoData   
		 * @Description: 条件查询金融分成   
		 * @param paramMap 查询参数
		 * @return      
		 * @return: Page<Map<String,Object>>
		 */
		public Page<Map<String,Object>>queryDealFinanceDividenInfoData(Map<String,Object> paramMap);
}
