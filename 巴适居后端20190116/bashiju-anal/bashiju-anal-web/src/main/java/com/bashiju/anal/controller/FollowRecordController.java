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
import com.bashiju.anal.mapper.FollowRecordMapper;
import com.bashiju.anal.pojo.Cell;
import com.bashiju.anal.pojo.Row;
import com.bashiju.anal.pojo.Thead;
import com.bashiju.enums.DeptTypeEnum;
import com.bashiju.enums.RoleGroupEnum;
import com.bashiju.utils.threadlocal.UserThreadLocal;

/**   
 * @ClassName:  FollowRecordController   
 * @Description:跟进漏斗转化分析   
 * @author: yangz
 * @date:   2018年11月8日 上午10:45:42   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="followRecord")
public class FollowRecordController {
	
	@Autowired
	private FollowRecordMapper followRecordMapper;
	
	@Autowired
	private AnalCommonMapper analCommonMapper;

	@RequestMapping(value="followRecord")
	public ModelAndView followRecord(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("followRecord");
		
		//封装表头
		Thead thead = new Thead();//定义表头对象
		List<Row> rows = new ArrayList<Row>(0);//定义表头行数
			
		//第一行
		List<Cell> cells1 = new ArrayList<Cell>(0);//定义表头列数
		List<List<Cell>> cells = new ArrayList<List<Cell>>(0);
		cells1.add(new Cell.Builder().setField("areaName").setTitle("区").setTotalRowText("合计").setRowspan(2).build());
		cells1.add(new Cell.Builder().setField("deptName").setTitle("门店").setRowspan(2).build());
		cells1.add(new Cell.Builder().setTitle("房源").build());
		cells1.add(new Cell.Builder().setTitle("求购客源").setColspan(7).setAlign("center").build());
		cells1.add(new Cell.Builder().setTitle("求租客源").setColspan(7).setAlign("center").build());
		cells.add(cells1);
		
		//第二行
		List<Cell> cells2 = new ArrayList<Cell>(0);//定义表头列数
		cells2.add(new Cell.Builder().setField("houseFollowCnt").setTitle("跟进量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustFollowCnt").setTitle("跟进量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustShowedCnt").setTitle("带看量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustNewShowedCnt").setTitle("一手带看量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustDiscCnt").setTitle("带盘量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustFirstShowdCnt").setTitle("一看").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustSecondShowdCnt").setTitle("二看").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("buyCustRepeatShowdCnt").setTitle("复看").setTotalRow(true).build());
		
		cells2.add(new Cell.Builder().setField("leaseCustFollowCnt").setTitle("跟进量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustShowedCnt").setTitle("带看量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustNewShowedCnt").setTitle("一手带看量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustDiscCnt").setTitle("带盘量").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustFirstShowdCnt").setTitle("一看").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustSecondShowdCnt").setTitle("二看").setTotalRow(true).build());
		cells2.add(new Cell.Builder().setField("leaseCustRepeatShowdCnt").setTitle("复看").setTotalRow(true).build());
		cells.add(cells2);
		rows.add(new Row.Builder().cells(cells).build());
		thead.setRows(rows);
		//获取部门数据信息
		List<Map<String, Object>> deptList = followRecordMapper.queryDeptTrees(UserThreadLocal.get().get("roleGroup").toString());
		mv.addObject("deptInfo", JSONObject.toJSONString(deptList));
		mv.addObject("thead", JSONObject.toJSON(thead));
		String roleGroup = UserThreadLocal.get().get("roleGroup").toString();
		if(!RoleGroupEnum.ORDINARY_USER.getCode().equals(roleGroup)) {//非普通用户才返回
			List<Map<String, Object>> list = analCommonMapper.queryCompanyByRoleGroup(roleGroup);
			mv.addObject("companys", list);
		}
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
		String begin = request.getParameter("beginDate");
		String end = request.getParameter("endDate");
		String companyId = request.getParameter("companyId");
		String deptId = request.getParameter("deptId");
		if(StringUtils.isEmpty(begin))
			begin = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(StringUtils.isEmpty("end"))
			end = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(StringUtils.isEmpty(companyId))
			companyId = UserThreadLocal.get().get("companyId").toString();
		String deptTypeId = DeptTypeEnum.DEPT_AREA.getCode();//默认为区
		if(!StringUtils.isEmpty(deptId)) {
			Integer dyd = analCommonMapper.getDeptTypeById(deptId);
			if(dyd!=null)
				deptTypeId = dyd.toString();
		}
		List<Map<String,Object>> list = this.followRecordMapper.queryFollowRecord(begin,end,deptId,deptTypeId,companyId);
		Map<String,Object> map = new HashMap<String,Object>(0);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return JSONArray.toJSON(map);
	}
	
}
