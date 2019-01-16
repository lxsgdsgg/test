/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  TestDataController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年4月10日 下午5:35:24   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bashiju.manage.service.TestDataService;
import com.bashiju.utils.service.BaseController;

/**   
 * @ClassName:  TestDataController   
 * @Description:测试数据使用
 * @author: yangz
 * @date:   2018年4月10日 下午5:35:24   
 *     
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@RequestMapping(value="testData")
@Controller
public class TestDataController extends BaseController{
	
	@Autowired
	private TestDataService testDataService;

	@RequestMapping(value="testDataPage")
	public ModelAndView testDataPage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		ModelAndView mv = getModelAndView(request, response, "user-list");
		List<Map<String,Object>> list = testDataService.queryDataList("1");
		if(list!=null && list.size()>0) {
			mv.addObject("datas", list);
		}
		return mv;
	}
}
