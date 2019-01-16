package com.bashiju.ext.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.bashiju.ext.mapper.PhoneFollowRecordMapper;
import com.bashiju.ext.service.ExtCloudServiceApi;
import com.bashiju.utils.service.CommonSqlServie;

public class ExtCloudServiceImpl extends CommonSqlServie implements ExtCloudServiceApi{
@Autowired
PhoneFollowRecordMapper phoneFollowRecordMapper;

	final static Logger logger=LoggerFactory.getLogger(ExtCloudServiceImpl.class);
	@Override
	public boolean auxSave(Map<String,Object> map,String tableName) {		
		this.commonOperationDatabase(map,tableName, true);
		return true;
	}
	
	@Override
	public boolean saveFollowRecord(Map<String,Object> maps,String tableName) {
		Map<String,Object> usrinfoMap=phoneFollowRecordMapper.queryUserInfo(maps.get("src").toString());
		Map<String,Object> map=new HashMap<>();
		
		map.put("deptId", usrinfoMap.get("deptId"));
		//map.put("deptName", usrinfoMap.get("deptName"));
		map.put("shhId",String.valueOf(maps.get("houseId")));
		map.put("companyId",String.valueOf(maps.get("companyId")));
		map.put("companyName", usrinfoMap.get("companyName"));
		map.put("permissionArea", usrinfoMap.get("deptId"));
		map.put("followType", "90000000");
		map.put("followTypeName","隐号通话");
		map.put("followerId", usrinfoMap.get("id"));
		map.put("follower", usrinfoMap.get("realName"));
		map.put("followTime", new Date());
		map.put("operatorId", usrinfoMap.get("id"));
		map.put("operator", usrinfoMap.get("realName"));
		map.put("addTime", new Date());

		if(maps.containsKey("recordUrl")) {
			map.put("content","正常通话:"+maps.get("recordUrl").toString());
		}else {
			map.put("content",maps.get("callEndStatus").toString());
		}
		this.commonOperationDatabase(map,tableName, true);
		return true;
	}
	
	
	@Override
	public boolean auxSaveAliyunPhoneList(Map<String,Object> map) {
		map.put("call_type", call_typeFormat(Integer.parseInt(map.get("call_type").toString())));
		map.put("release_dir", release_dirFormat(Integer.parseInt(map.get("release_dir").toString())));
		map.put("release_cause", release_causeFormat(Integer.parseInt(map.get("release_cause").toString())));
		map.remove("control_type");
		map.remove("control_msg");
		map.remove("id");
		map.remove("partner_key");
		this.commonOperationDatabase(map,"aliyun_phonelist", true);
		return true;
	}
	
	/**
	 * 呼叫类型转换
	 * @Title: call_typeFormat   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param call_type
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	private String call_typeFormat(int call_type) {
		String res=null;
		switch (call_type) {
		case 0:
			res="主叫";
			break;
		case 1:
			res="被叫";
			break;
		case 2:
			res="短信发送";
			break;
		case 3:
			res="短信接收";
			break;
		case 4:
			res="呼叫拦截";
			break;
		default:
			res="短信收发拦截";
			break;
		}
		return res;
	}
	
	/**
	 * 通话释放方向转换
	 * @Title: release_dirFormat   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param release_dir
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	private String release_dirFormat(int release_dir) {
		String res=null;
		switch (release_dir) {
		case 0:
			res="平台释放";
			break;
		case 1:
			res="主叫挂断";
			break;
		default:
			res="被叫挂断";
			break;		
		}
		return res;
	}
	
	/**
	 * 释放原因转换
	 * @Title: release_causeFormat   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param release_cause
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	private String release_causeFormat(int release_cause) {
		String res=null;
		switch (release_cause) {
		case 1:
			res="未分配的号码";
			break;
		case 17:
			res="用户忙";
			break;
		case 21:
			res="呼叫拒收";
			break;
		case 28:
			res="无效的号码格式（地址不全）";
			break;
		case 31:
			res="正常";
			break;
		case 127:
			res="互通";
			break;
		default:
			res="其他错误，错误代码为:"+release_cause;
			break;		
		}
		return res;
	}
}
