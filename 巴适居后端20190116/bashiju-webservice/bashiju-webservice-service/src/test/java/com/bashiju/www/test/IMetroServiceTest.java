package com.bashiju.www.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.bashiju.api.RedisServiceApi;
import com.bashiju.www.pojo.service.out.comm.AreaResult;
import com.bashiju.www.pojo.service.out.comm.MetroLinesResult;
import com.bashiju.www.pojo.service.out.comm.MetroStationResult;
import com.bashiju.www.pojo.service.out.comm.RegionResult;
import com.bashiju.www.service.comm.IAreaService;
import com.bashiju.www.service.comm.IMetroService;
import com.github.pagehelper.Page;

/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  InterFaceTest.java   
 * @Package    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年7月25日 下午5:24:12   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

/**   
 * @ClassName:  InterFaceTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: yangz
 * @date:   2018年7月25日 下午5:24:12   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {
		"classpath*:/bashiju-www-servlet.xml",
		"classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-transaction.xml",
		"classpath*:/spring/applicationContext-mybatis.xml",
		"classpath*:/dubbo/dubbo-*.xml"
		
		})  
public class IMetroServiceTest {
	
	
	@Autowired
	private IMetroService iMetroService;
	
	@Test
	public void test1() {
		String cityCode = "530100";
		List<MetroLinesResult> res = iMetroService.queryMetroLines(cityCode);
		System.out.println(res.toString());
		assertNotNull(res);
	}
	
	@Test
	public void test2() {
		String cityCode = "530100";
		Integer lineId = 1;
		List<MetroStationResult> res = iMetroService.queryMetroStationsByLine(cityCode, lineId);
		System.out.println(res.toString());
		assertNotNull(res);
	}
}
