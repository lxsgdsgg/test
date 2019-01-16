/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  ExcelController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: yangz     
 * @date:   2018年12月3日 下午3:04:04   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/
package com.bashiju.manage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bashiju.manage.ExportModelBean.TempCommunity;
import com.bashiju.manage.pojo.BeanValidation;
import com.bashiju.manage.service.ExcelService;
import com.bashiju.manage.utils.ExcelUtil;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.threadlocal.UserThreadLocal;
import com.bashiju.utils.util.BashijuResult;

/**   
 * @ClassName:  ExcelController   
 * @Description:excel管理  
 * @author: yangz
 * @date:   2018年12月3日 下午3:04:04   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="excel")
public class ExcelController {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	private ExcelService excelService;

	/**
	 * 测试导入功能使用，实际生产将不适用
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param request
	 * @param response
	 * @return      
	 * @return: ModelAndView
	 */
	@RequestMapping(value="excelExportTestPage")
	public ModelAndView excelExportTestPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("excelTest");
		return mv;
	}
	
	/**
	 * 批量导入小区信息
	 * @Description: 批量导入小区信息   
	 * @param file
	 * @param request
	 * @param response
	 * @return: BashijuResult
	 */
	@RequestMapping(value="batchExportCommunityInfo")
	@SuppressWarnings("unchecked")
	@ResponseBody
	public BashijuResult batchExportCommunityInfo(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<TempCommunity> list = (List<TempCommunity>) ExcelUtil.importData2(file, TempCommunity.class);
			if(list!=null && list.size()>0){
				Map<?,?> user = UserThreadLocal.get();
				for(int l=0;l<list.size();l++){
					TempCommunity info = list.get(l);
					
					info.setAreaCode(info.getAreaCode().split("\\.")[0]);
					info.setRegionId(info.getRegionId().split("\\.")[0]);
					info.setMatchingIds(info.getMatching().split("\\.")[0]);
					info.setMatching(info.getMatching().split("\\.")[1]);
					info.setIsHot(info.getIsHot().split("\\.")[0]);
					info.setElevatorService(info.getElevatorService().split("\\.")[0]);
					info.setWaterSupply(info.getWaterSupply().split("\\.")[0]);
					info.setHeatSupply(info.getHeatSupply().split("\\.")[0]);
					info.setPowerSupply(info.getPowerSupply().split("\\.")[0]);
					
					info.setPermissionArea(user.get("deptId").toString());
					info.setOperatorId(user.get("id").toString());
					info.setOperator(user.get("realName").toString());
					info.setAddTime(new Date());
					info = (TempCommunity) BeanValidation.validate(info);
				}
				boolean result = excelService.saveBatchExportCommunityInfo(list);
				if(result)
					return BashijuResult.ok();
				else
					throw new BusinessException("保存失败");
			}else{
				throw new BusinessException("没有要导入的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		String regx="[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,10}";
		String value="云纺一号店长";
		Pattern pattern = Pattern.compile(regx);
		System.out.println(pattern.matcher(value).matches());
	}
}
