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

import com.bashiju.www.pojo.service.out.comm.MetroLinesResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;

/**   
 * @ClassName:  IAreaMapper   
 * @Description:行政区域、片区信息持久层
 * @author: liwen
 * @date:   2018年7月25日 下午1:58:53   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface IMetroMapper {

	public List<MetroLinesResult> queryLines(String cityCode);
	public List<MetroStationResult> queryStations(String cityCode);
}
