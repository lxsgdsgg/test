/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  CurriculumVitaeController.java   
 * @Package com.bashiju.manage.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpeng     
 * @date:   2018年8月9日 下午2:33:01   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.manage.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bashiju.manage.service.CurriculumVitaeService;
import com.bashiju.utils.service.BaseController;
import com.github.pagehelper.Page;

/**   
 * @ClassName:  CurriculumVitaeController   
 * @Description:简历管理控制层 
 * @author: wangpeng
 * @date:   2018年8月9日 下午2:33:01   
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Controller
@RequestMapping(value="curriculumVitae")
public class CurriculumVitaeController  extends BaseController{
	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	
	/**
	 * @Title: enterCurriculumVitaePage   
	 * @Description: 进入简历管理页面   
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterCurriculumVitaePage")
	public ModelAndView enterCurriculumVitaePage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = getModelAndView(request, response, "curriculumVitae/curriculumVitae");
		return mv;
	}
	/**
	 * @Title: queryCurriculumVitae   
	 * @Description:查询简历信息
	 * @param page 当前页
	 * @param limit 每页总条数
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value="queryCurriculumVitae")
	@ResponseBody
	public Map<String,Object> queryCurriculumVitae(int page,int limit){
		Page<Map<String,Object>> pages =  curriculumVitaeService.queryCurriculumVitae(page, limit);
		Map<String,Object> map = getPageResult(pages);
		return map;
	}
	/**
	 * @Title: enterCurriculumVitaeDetail   
	 * @Description:进入经纪人详情页
	 * @param request
	 * @param response
	 * @return: ModelAndView
	 */
	@RequestMapping(value="enterCurriculumVitaeDetailPage")
	public ModelAndView enterCurriculumVitaeDetailPage(HttpServletRequest request, HttpServletResponse response,String id) {
		ModelAndView mv = getModelAndView(request, response, "curriculumVitae/curriculumVitaeDetail");
		List<Map<String,Object>> map = curriculumVitaeService.queryCurriculumVitaeDetailById(id);
		mv.addObject("map",map);
		//修改
		return mv;
	}
	/**
	 * @Title: download   
	 * @Description: 下载
	 * @param path
	 * @param response
	 * @return: HttpServletResponse
	 */
	@RequestMapping(value="download")
	@ResponseBody
	public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
