/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  StatisticsSaleHouseController.java   
 * @Package com.bashiju.anal.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月17日 下午2:18:46   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.anal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.bashiju.anal.mapper.AnalCommonMapper;
import com.bashiju.anal.mapper.IncomeTrendMapper;
import com.bashiju.anal.mapper.StatisticsSaleHouseMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.HouseStatisticsLongitudinalParamsEnum;
import com.bashiju.anal.pojo.HouseStatisticsTransverseParamsEnum;
import com.bashiju.anal.pojo.IncomeTransverseParamsEnum;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.anal.service.UserAchievementService;
import com.bashiju.anal.service.UserIncomeService;
import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.enums.HouseLevelEnum;
import com.bashiju.enums.HouseStatusEnum;
import com.bashiju.enums.HouseUsesEnum;
import com.bashiju.enums.pc.BuildAreaEnum;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  UserIncomeController   
 * @Description:个人实收排行
 * @author: liwen
 * @date:   2018年11月29日 上午10:18:46   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="userIncome")
public class UserIncomeController {
	
	@Autowired
	private UserIncomeService userIncomeService;
	
	@Autowired
	private AnalCommonMapper commonMapper;

	/**
	 * 
	 * @Description: 个人实收排行页面
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="userIncome")
	public ModelAndView userAchievement(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("userIncome");
		
		//封装表头
		Thead thead = new Thead();//定义表头对象
		List<Row> rows = new ArrayList<Row>(0);//定义表头行数
			
		//第一行
		List<Cell> cells1 = new ArrayList<Cell>(0);//定义表头列数
		List<List<Cell>> cells = new ArrayList<List<Cell>>(0);
		cells1.add(new Cell.Builder().setField("sort").setTitle("排行").setTotalRowText("合计").build());
		cells1.add(new Cell.Builder().setField("deptName").setTitle("门店").build());
		cells1.add(new Cell.Builder().setField("realName").setTitle("经纪人").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("sellIncome").setTitle("买卖实收").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("rentIncome").setTitle("租赁实收").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("newHouseIncome").setTitle("一手实收").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("financeIncome").setTitle("金融实收").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("incomeSum").setTitle("实收合计").setAlign("center").build());
		cells1.add(new Cell.Builder().setField("sortChange").setTitle("排行变化").setAlign("center").build());
		cells.add(cells1);
		
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		//获取部门数据信息
		List<Map<String, Object>> deptList = commonMapper.queryDeptTrees(UserThreadLocal.get().get("roleGroup").toString());
		mv.addObject("deptInfo", JSONObject.toJSONString(deptList));
		
		mv.addObject("thead", JSONObject.toJSON(thead));
		return mv;
	}
	
	/**
	 * 
	 * @Description: 获取数据信息  
	 * @param request
	 * @param response
	 * @return: Object
	 */
	@RequestMapping(value="getData")
	@ResponseBody
	public Object getData(HttpServletRequest request,HttpServletResponse response) {
		String months = request.getParameter("months");
		String userId = request.getParameter("userId");
		if(StringUtils.isEmpty(months))
			months = new SimpleDateFormat("yyyy-MM").format(new Date());
		
		List<Map<String,Object>> list = this.userIncomeService.queryUserIncome(months,UserThreadLocal.get().get("companyId").toString(),userId);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return JSONArray.toJSON(map);
	}
	
}
