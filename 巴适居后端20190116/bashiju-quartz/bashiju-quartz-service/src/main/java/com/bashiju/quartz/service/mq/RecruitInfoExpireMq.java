/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  RecruitInfoExpireMq.java   
 * @Package com.bashiju.quartz.service.mq      
 * @author: zuoyuntao     
 * @date:   2018年8月9日 上午11:04:48   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.quartz.service.mq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bashiju.quartz.mapper.RecruitInfoExpireMqMapper;
import com.github.pagehelper.util.StringUtil;

/**
 * 招聘岗位过期处理
 * @ClassName:RecruitInfoExpireMq
 * @Description:招聘岗位过期处理
 * @author:zuoyuntao
 * @date:2018年8月9日 上午11:04:48
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class RecruitInfoExpireMq {
	/**
	 * 发布岗位操作持久层接口
	 */
	@Autowired
	private RecruitInfoExpireMqMapper mRecruitInfoExpireMqMapper;
	/**
	 * ERP发布岗位到期处理
	 * @Title: dealRecruitInfoExpired
	 * @author: zuoyuntao  
	 * @Description:ERP发布岗位到期处理
	 * void
	 */
	public void dealRecruitInfoExpired(Map<String,Object> paraMap) {
		System.out.println("查询所有岗位信息>>>>>>>>>>");
		long startTime = System.currentTimeMillis();
		List<Map<String,Object>> resultList = 
				mRecruitInfoExpireMqMapper.queryAllRecruitInfoList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long dateNumber = Long.valueOf(sdf.format(new Date())).longValue();
        List<Object> ids = new ArrayList<Object>();
        StringBuilder idStr = new StringBuilder();
        int counter = 0;
		for(Map<String,Object> temp : resultList) {
			String endTime = String.valueOf(temp.get("endTime"));
			if(StringUtil.isEmpty(endTime)) {
				continue;
			}
			long endTimeNum = Long.valueOf(endTime.replaceAll("-","")).longValue();
			if(dateNumber > endTimeNum) {
				ids.add(temp.get("id"));
				idStr.append(temp.get("id")).append(",");
				counter++;
			}
		}
		if(idStr.length() > 0) {
			SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//去掉最后一个逗号
			idStr = idStr.delete(idStr.length() - 1, idStr.length());
			//更新岗位状态
			mRecruitInfoExpireMqMapper
				.updateRecruitInfoStatusById(idStr.toString()
						,formatTime.format(new Date()));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("处理岗位过期任务执行完成,本次作废掉【" + counter +"】条岗位信息,耗时：" 
				+(endTime - startTime) + "毫秒");
	}
}
