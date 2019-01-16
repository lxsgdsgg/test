package com.bashiju.www.service.comm;
/**
 * 
 *   图片服务
 * @ClassName:  ImgService   
 * @Description:   图片服务
 * @author: liwen
 * @date:   2018年10月17日 下午5:15:28       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

import java.util.List;

import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.ImgResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;

public interface ImgService{
	/**
	 * 
		 * 根据城市查询图片
		 *  先从redis中取值，没有再查询数据库并存入redis
		 *  redis的key为前缀(WebGlobal.COMIMG_REDIS_PREFIX)+城市代码
		 * 
		 * @Description: 根据城市查询图片
		 * @param cityCode 城市代码
		 * @param type 类型
		 * @return 
		 * List<AreaResult>
	 */
	List<ImgResult> queryImg(String cityCode,String type);
	
}
