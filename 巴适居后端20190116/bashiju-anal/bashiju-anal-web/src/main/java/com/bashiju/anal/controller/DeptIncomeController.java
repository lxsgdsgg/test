/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FollowRecordController.java   
 * @Package com.bashiju.anal.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年11月8日 上午10:45:42   
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bashiju.anal.mapper.AnalCommonMapper;
import com.bashiju.anal.mapper.DeptIncomeMapper;
import com.bashiju.anal.mapper.FollowRecordMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  DeptIncomeController   
 * @Description:门店损益财务表   
 * @author: liwen
 * @date:   2018年11月26日 上午10:45:42   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="deptIncome")
public class DeptIncomeController {
	
	@Autowired
	private DeptIncomeMapper deptIncomeMapper;
	
	@Autowired
	private AnalCommonMapper analCommonMapper;

	@RequestMapping(value="deptIncome")
	public ModelAndView deptIncome(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("deptIncome");
		
		//封装表头
		Thead thead = new Thead();//定义表头对象
		List<Row> rows = new ArrayList<Row>(0);//定义表头行数
			
		//第一行
		List<Cell> cells1 = new ArrayList<Cell>(0);//定义表头列数
		List<List<Cell>> cells = new ArrayList<List<Cell>>(0);
		cells1.add(new Cell.Builder().setField("门店").setTitle("").setTotalRowText("合计").setRowspan(2).build());
		cells1.add(new Cell.Builder().setField("deptName").setTitle("门店").setRowspan(2).build());
		
		cells1.add(new Cell.Builder().setTitle("门店收入").setColspan(4).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("门店支出").setColspan(8).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("合计（元）").setColspan(3).setAlign("center").build());
		cells.add(cells1);
		
		//第二行
		List<Cell> cells2 = new ArrayList<Cell>(0);//定义表头列数
		cells2.add(new Cell.Builder().setField("sellActualPrice").setTitle("买卖").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("rentActualPrice").setTitle("租赁").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("newActualPrice").setTitle("一手").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("finceActualPrice").setTitle("金融").setTotalRow(true).build());
		
		cells2.add(new Cell.Builder().setField("buyCustDiscCnt").setTitle("门店支出").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustFirstShowdCnt").setTitle("计提（固定）").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustSecondShowdCnt").setTitle("计提（业绩）").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustRepeatShowdCnt").setTitle("门店分摊").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("basePay").setTitle("员工工资").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("ssfAmount").setTitle("员工社金").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("commissionPrice").setTitle("员工提成").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustDiscCnt").setTitle("薪酬变动").setTotalRow(true).build());
		
		cells2.add(new Cell.Builder().setField("leaseCustFirstShowdCnt").setTitle("收入合计").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustSecondShowdCnt").setTitle("支出合计").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustRepeatShowdCnt").setTitle("利润").setTotalRow(true).build());
		cells.add(cells2);
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		
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
		
		if(StringUtils.isEmpty(months))
			months = new SimpleDateFormat("yyyy-MM").format(new Date());
		
		List<Map<String,Object>> list = this.deptIncomeMapper.queryDeptIncome(months,UserThreadLocal.get().get("companyId").toString());
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return JSONArray.toJSON(map);
	}
	
}
