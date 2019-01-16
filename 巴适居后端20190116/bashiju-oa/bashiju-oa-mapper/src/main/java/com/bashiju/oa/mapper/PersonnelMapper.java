package com.bashiju.oa.mapper;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.github.pagehelper.Page;

/**
 * 
 * @ClassName:  PersonnelMapper   
 * @Description:关于OA人事的mapper接口
 * @author: liwen
 * @date:   2018年11月20日 上午10:46:13   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PersonnelMapper {
	
	public Page<Map<String, Object>> queryPersonnelList(Map<String,Object> conditions);
	public Page<Map<String, Object>> queryPersonnelExamine(Map<String,Object> conditions);
	
}
