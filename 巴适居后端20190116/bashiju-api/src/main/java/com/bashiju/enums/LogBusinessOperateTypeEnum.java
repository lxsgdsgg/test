/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  FileTypeEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 下午5:48:21   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 日志操作类型 枚举类
 * @ClassName:LogOperateTypeEnum
 * @Description:文件类型 枚举类
 * @author:wangkaifa
 * @date:2018年8月7日 下午5:48:21
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum LogBusinessOperateTypeEnum {
	/**ADD_PHONE("1","1",新增电话")**/
	ADD_PHONE("1","1","新增电话"),
	/**ADD_PICTURE("2","新增照片")**/
	ADD_PICTURE("2","1","新增照片"),
	/**ADD_HOUSE("3","新增")**/
	ADD_HOUSE("3","1","新增"),
	/**UPDATE_HOUSE("4","修改")**/
	UPDATE_HOUSE("4","1","修改"),
	/**DEL_HOUSE("5","房源删除")**/
	DEL_HOUSE("5","1","房源删除"),
	/**DEPOSIT("6","下定")**/
	DEPOSIT("6","1","下定"),
	/**DEAL("7","成交")**/
	DEAL("7","1","成交"),
	/**UPDATE_STATUS("8","修改状态")**/
	UPDATE_STATUS("8","1","修改状态"),
	/**EXAMINE_UPDATE_STATUS("9","处理修改状态")**/
	EXAMINE_UPDATE_STATUS("9","1","处理修改状态"),
	/**OPENPLATE("10","开盘")**/
	OPENPLATE("10","1","开盘"),
	/**SET_PERSONAL("11","设置私盘")**/
	SET_PERSONAL("11","1","设置私盘"),
	/**CLOSEDISC("12","封盘")**/
	//CLOSEDISC("12","1","封盘"),
	/**TIPOFF("13","举报")**/
	TIPOFF("13","1","举报"),
	/**MAINER_TRANSFER("14","信息转移")**/
	MAINER_TRANSFER("14","1","信息转移"),
	/**MAINER_CHANGE("15","变更维护人")**/
	MAINER_CHANGE("15","1","变更维护人"),
	/**UPDATE_DESCRIBE("16","修改房源描述")**/
	UPDATE_DESCRIBE("16","1","修改房源描述"),
	/**DEL_PICTURE("17","删除图片")**/
	DEL_PICTURE("17","1","删除图片"),
	/**ENTRUST_CHANGE("18","变更委托")**/
	ENTRUST_CHANGE("18","1","变更委托"),
	/**OWER_CHANGE("19","变更业主")**/
	OWER_CHANGE("19","1","变更业主"),
	/**SET_PUBLICK("20","设置公盘")**/
	SET_PUBLICK("20","1","设置公盘"),
	/**ADD_KEY("21","新增钥匙")**/
	ADD_KEY("21","1","新增钥匙"),
	/**RETURN_KEY("22","钥匙退还")**/
	RETURN_KEY("22","1","钥匙退还"),
	/**DEL_PHONE("23","删除电话")**/
	DEL_PHONE("23","1","删除电话"),
	/**TAOBAO("24","淘宝")**/
	TAOBAO("24","1","淘宝"),
	/**KEEP_KEY("25","钥匙封存")**/
	KEEP_KEY("25","1","钥匙封存"),
	/**UPDATE_PHONE("26","修改电话")**/
	UPDATE_PHONE("26","1","修改电话"),
	/**CANCEL_ORDER("27","通知撤单")**/
	CANCEL_ORDER("27","1","通知撤单"),
	/**ADD_RELEVANT_PICTURE("28","新增相关照片")**/
	ADD_RELEVANT_PICTURE("28","1","新增相关照片"),
	/**EXPIRATION_EXECUTION("29","到期执行")**/
	EXPIRATION_EXECUTION("29","1","到期执行"),
	/**TIMING_EXECUTION("30","定时执行")
	TIMING_EXECUTION("30","1","定时执行"),**/
	/**DEL_FOLLOW("31","删除跟进")**/
	DEL_FOLLOW("31","1","删除跟进"),
	/**UPDATE_VRURL("32","修改VR看房地址")**/
	UPDATE_VRURL("32","1","修改VR看房地址"),
	/**UPLOAD_VIDEO("33","上传视频")**/
	UPLOAD_VIDEO("33","1","上传视频"),
	/**SET_UNIQUE("34","设置独家")**/
	SET_UNIQUE("34","1","设置独家"),
	/**SET_PICTURE_PEOPLE("35","设置图片人")**/
	SET_PICTURE_PEOPLE("35","1","设置图片人"),
	/**ADD_FEEDBACK("36","写带看反馈")**/
	ADD_FEEDBACK("36","1","写带看反馈"),
	/**ADD_HOUSE_FOLLOW_RECORD("37","1","房源跟进"),**/
	ADD_HOUSE_FOLLOW_RECORD("37","1","房源跟进"),
	/**ADD_HOUSE_REALEXPLORATION("38","1","房源实勘"),**/
	ADD_HOUSE_REALEXPLORATION("38","1","房源实勘"),
	/**LOOK_HOUSE_PHONE("39","1","查看电话"),**/
	LOOK_HOUSE_PHONE("39","1","查看电话"),
	/**LEND_KEY("40","钥匙借出")**/
	LEND_KEY("40","1","钥匙借出"),
	/**HOUSE_RELATIONPICEXAM("41","相关图片审核")**/
	HOUSE_RELATIONPICEXAM("41","1","相关图片审核"),
	/**HOUSE_CRAWLERLIST("42","房源采集")**/
	HOUSE_CRAWLERLIST("42","1","房源采集"),
	/**HOUSE_PICTRUE_CHECK("43","房屋图片审核")**/
	HOUSE_PICTRUE_CHECK("43","1","房屋图片审核"),
	/**HOUSE_ENTRUST_DEAL("44","房源委托受理")**/
	HOUSE_ENTRUST_DEAL("44","1","房源委托受理"),
	/**HOUSE_ADD_EXAM("45,"房源新增审核")**/
	HOUSE_ADD_EXAM("45","1","房源新增审核"),
	/**HOUSE_UPDSTATUS_EXAM("46,"房源修改状态审核")**/
	HOUSE_UPDSTATUS_EXAM("46","1","房源修改状态审核"),
	/**HOUSE_REPORT_EXAM("47,"房源举报审核")**/
	HOUSE_REPORT_EXAM("47","1","房源举报审核"),
	/**TAOBAO_EXAM("48,"淘宝审核")**/
	TAOBAO_EXAM("48","1","淘宝审核"),
	/**OPENING_EXAM("49,"开盘审核")**/
	OPENING_EXAM("49","1","开盘审核"),
	/**SEALED_EXAM("50,"开盘审核")**/
	SEALED_EXAM("50","1","封盘审核"),
	/**HOUSE_UPD_REJECT("51","1","驳回房源修改审核"),**/
	HOUSE_UPD_REJECT("51","1","驳回房源修改审核"),
	/**AGENT_FEEDBACK("52","1","经纪人反馈审核")**/
	AGENT_FEEDBACK("52","1","经纪人反馈审核"),
	/**PUBLICDISK_TURN_PRIVATEDISK("53","1","公盘转私盘审核")**/
	PUBLICDISK_TURN_PRIVATEDISK("53","1","公盘转私盘审核"),
	/**PUBLICDISK_TURN_PRIVATEDISK("53","1","封盘")**/
	HOUSE_SEALED("54","1","封盘"),
	/**ADD_CONTRACT("55","1","新增合同审核"),**/
	ADD_CONTRACT_EXAM("55","1","新增合同审核"),
	/**SET_GOOD("56","1","设置靓房"),**/
	SET_GOOD("56","1","设置靓房"),
	/**CANCEL_GOOD("57","1","取消靓房"),**/
	CANCEL_GOOD("57","1","取消靓房"),
	/**HOUSE_REMIND("58","1","写提醒"),**/
	HOUSE_REMIND("58","1","写提醒"),
	/**HOUSE_BINDCODE("59","1","发送绑定码"),**/
	HOUSE_BINDCODE("59","1","发送绑定码"),
	/**HOUSE_SETTOP("60","1","设置置顶"),**/
	HOUSE_SETTOP("60","1","设置置顶"),
	/**HOUSE_CANCELTOP("61","1","取消置顶"),**/
	HOUSE_CANCELTOP("61","1","取消置顶"),
	/**HOUSE_STORE("62","1","收藏"),**/
	HOUSE_STORE("62","1","收藏"),
	/**HOUSE_CANCELSTORE("63","1","取消收藏"),**/
	HOUSE_CANCELSTORE("63","1","取消收藏"),
	/**HOUSE_LOOK_NUMBER("64","1","查看门牌"),**/
	HOUSE_LOOK_NUMBER("64","1","查看门牌"),
	/**HOUSE_LOOK_ARCHIVES("65","1","查看客户档案"),**/
	HOUSE_LOOK_ARCHIVES("65","1","查看客户档案"),
	/**HOUSE_CALL_PHONE("66","1","拨打隐私电话"),**/
	HOUSE_CALL_PHONE("66","1","拨打隐私电话"),
	/**HOUSE_LOOK_MINPRICE("67","1","查看底价"),**/
	HOUSE_LOOK_MINPRICE("67","1","查看底价"),
	/**HOUSE_UPDATE_MINPRICE("68","1","修改底价"),**/
	HOUSE_UPDATE_MINPRICE("68","1","修改底价"),
	/**HOUSE_UPDATE_USES("69","1","改变用途"),**/
	HOUSE_UPDATE_USES("69","1","改变用途"),
	/**HOUSE_SET_COVER("70","1","设置图片封面"),**/
	HOUSE_SET_COVER("70","1","设置图片封面"),
	/**HOUSE_UPDATE_PICTURETYPE("71","1","修改图片类型"),**/
	HOUSE_UPDATE_PICTURETYPE("71","1","修改图片类型"),
	/**HOUSE_SET_SYNCHRO("72","1","外网同步"),**/
	HOUSE_SET_SYNCHRO("72","1","外网同步"),
	/**HOUSE_CANCEL_SYNCHRO("73","1","取消外网同步"),**/
	HOUSE_CANCEL_SYNCHRO("73","1","取消外网同步"),
	/**PRINT_CONTRACT("74","1","打印合同"),**/
	PRINT_CONTRACT("74","1","打印合同"),
	/**HOUSE_SYSTEM_OPERATE("999","1","定时执行"),**/
	HOUSE_SYSTEM_OPERATE("199","1","定时执行"),
	
	
	/**ADD_CUSTOMER("200","2","新增")**/
	ADD_CUSTOMER("200","2","新增"),
	/**ADD_CUSTOMER_PHONE("201","2","新增电话")**/
	ADD_CUSTOMER_PHONE("201","2","新增电话"),
	/**UPDATE_CUSTOMER("202","2","修改")**/
	UPDATE_CUSTOMER("202","2","修改"),
	/**UPDATE_CUSTOMER_STATUS("203","2","修改状态")**/
	UPDATE_CUSTOMER_STATUS("203","2","修改状态"),
	/**DELETE_CUSTOMER("204","2","客源删除")**/
	DELETE_CUSTOMER("204","2","客源删除"),
	/**CUSTOMER_SETPUBLIC("205","2","放入公盘")**/
	CUSTOMER_SETPUBLIC("205","2","放入公盘"),
	/**CUSTOMER_CLOSEDISC("206","2","封盘")**/
	CUSTOMER_CLOSEDISC("206","2","封盘"),
	/**CUSTOMER_MAINER_TRANSFER("207","2","信息转移")**/
	CUSTOMER_MAINER_TRANSFER("207","2","信息转移"),
	/**CUSTOMER_MAINER_CHANGE("208","2","变更维护人")**/
	CUSTOMER_MAINER_CHANGE("208","2","变更维护人"),
	/**CUSTOMER_TAOBAO("209","2","放入淘宝池")**/
	CUSTOMER_TAOBAO("209","2","放入淘宝池"),
	/**CUSTOMER_DEPOSIT("210","2","下定")**/
	CUSTOMER_DEPOSIT("210","2","下定"),
	/**CUSTOMER_DEAL("211","2","成交")**/
	CUSTOMER_DEAL("211","2","成交"),
	/**CUSTOMER_DELETE_PHONE("212","2","删除电话")**/
	CUSTOMER_DELETE_PHONE("212","2","删除电话"),
	/**CUSTOMER_DELETE_DEPOSIT("213","2","下定删除")**/
	CUSTOMER_DELETE_DEPOSIT("213","2","下定删除"),
	/**CUSTOMER_SETPRIVE("214","2","设置私盘")**/
	CUSTOMER_SETPRIVE("214","2","设置私盘"),
	/**CUSTOMER_CANCEL_CLOSEDISC("215","2","取消设置")**/
	CUSTOMER_CANCEL_CLOSEDISC("215","2","取消设置"),
	/**CUSTOMER_CHECKED_DEPOSIT("216","2","定金确认")**/
	CUSTOMER_CHECKED_DEPOSIT("216","2","定金确认"),
	/**CUSTOMER_DEPOSIT_PAY("217","2","下定支出")**/
	CUSTOMER_DEPOSIT_PAY("217","2","下定支出"),
	/**UPDATE_CUSTOMER_PHONE("218","2","修改电话")**/
	UPDATE_CUSTOMER_PHONE("218","2","修改电话"),
	/**ADD_ERHOUSE_SHOWED("219","2","写二手房带看"),**/
	ADD_ERHOUSE_SHOWED("219","2","写二手房带看"),
	/**ADD_NEWHOUSE_SHOWED("220","2","写一手房带看"),**/
	ADD_NEWHOUSE_SHOWED("220","2","写一手房带看"),
	/**ADD_CUSTOMER_FOLLOW_RECORD("221","2","客源跟进"),**/
	ADD_CUSTOMER_FOLLOW_RECORD("221","2","客源跟进"),
	/**LOOK_CUSTOMER_PHONE("222","2","查看电话"),**/
	LOOK_CUSTOMER_PHONE("222","2","查看电话"),

	/**CUSTOMER_STORE("223","2","客源收藏"),**/
	CUSTOMER_STORE("223","2","客源收藏"),
	/**CUSTOMER_CANCELSTORE("224","2","客源取消收藏"),**/
	CUSTOMER_CANCELSTORE("224","2","客源取消收藏"),
	/**CUSTOMER_REMIND("225","2","写提醒"),**/
	CUSTOMER_REMIND("225","2","写提醒"),
	/**CUSTOMER_ARCHIVES("226","2","查看客户档案"),**/
	CUSTOMER_ARCHIVES("226","2","查看客户档案"),
	/**CUSTOMER_LOOKHOUSEPATH("227","2","查看看房轨迹"),**/
	CUSTOMER_LOOKHOUSEPATH("227","2","查看看房轨迹"),
	/**CUSTOMER_FOLLOW_RECONNAISSANCE("228","2","实勘"),**/
	CUSTOMER_FOLLOW_RECONNAISSANCE("228","2","实勘"),
	/**	CUSTOMER_EXAMINE("229","2","客源业务审批")**/
	CUSTOMER_EXAMINE("229","2","客源业务审批"),
	/**CUSTOMER_TAKELOOK("230","2","删除带看记录")**/
	CUSTOMER_TAKELOOK("230","2","删除带看记录"),
	/**CUSTOMER_UPDATETAKELOOK("231","2","编辑带看记录")**/
	CUSTOMER_UPDATETAKELOOK("231","2","修改带看记录"),
	/**CUSTOMER_DEPOSITMANGER("232","2","定金管理**/
	CUSTOMER_DEPOSITMANGER("232","2","定金管理"),
	/**CUSTOMER_UPDSTATUS_EXAM("233","2","修改状态审核**/
	CUSTOMER_UPDSTATUS_EXAM("233","2","修改状态审核"),
	/**CUSTOMER_SEAL_EXAM("234","2","封盘审核**/
	CUSTOMER_SEAL_EXAM("234","2","封盘审核"),
	/**CUSTOMER_TAOBAO_EXAM("235","2","淘宝审核**/
	CUSTOMER_TAOBAO_EXAM("235","2","淘宝审核"),
	/**CUSTOMER_RECEIVED_PRIVATE_DISK("236","2","收为私客"),**/
	CUSTOMER_RECEIVED_PRIVATE_DISK("236","2","收为私客"),
	/**CUSTOMER_UPDSTATUS("237","2","修改状态"),**/
	CUSTOMER_UPDSTATUS("237","2","修改状态"),
	/**CUSTOMER_SYSTEM_OPERATE("999","2","定时执行"),**/
	CUSTOMER_SYSTEM_OPERATE("299","2","定时执行"),
	
	
	/**ADD_NEWHOUSE("300","3","新增")**/
	ADD_NEWHOUSE("300","3","新增"),
	/**UPDATE_NEWHOUSE("301","3","修改")**/
	UPDATE_NEWHOUSE("301","3","修改"),
	/**DELETE_NEWHOUSE("302","3","删除")**/
	DELETE_NEWHOUSE("302","3","删除"),
	/**NEWHOUSE_DEAL("303","3","成交")**/
	NEWHOUSE_DEAL("303","3","成交"),
	/**NEWHOUSE_ADD_DYNAMIC("304","3","发布动态")**/
	NEWHOUSE_ADD_DYNAMIC("304","3","发布动态"),
	/**NEWHOUSE_ADD_HUXING("305","3","新增户型图")**/
	NEWHOUSE_ADD_HUXING("305","3","新增户型图"),
	/**NEWHOUSE_UPDATE_HUXING("306","3","修改户型图")**/
	NEWHOUSE_UPDATE_HUXING("306","3","修改户型图"),
	/**NEWHOUSE_SYNCHRO("307","3","外网同步")**/
	NEWHOUSE_SYNCHRO("307","3","外网同步"),
	/**NEWHOUSE_SETGOOD("308","3","项目推荐")**/
	NEWHOUSE_SETGOOD("308","3","项目推荐"),
	/**NEWHOUSE_UPDATE_HUXING_PICTURE("309","3","替换户型图"),**/
	NEWHOUSE_UPDATE_HUXING_PICTURE("309","3","替换户型图"),
	/**NEWHOUSE_ADD_HUXING_VIDEO("310","3","上传户型图视频"),**/
	NEWHOUSE_ADD_HUXING_VIDEO("310","3","上传户型图视频"),
	/**NEWHOUSE_ADD_SHOWED("311","3","一手带看"),**/
	NEWHOUSE_ADD_SHOWED("311","3","一手带看"),
	/**NEWHOUSE_CANCEL_SYNCHRO("312","3","取消外网同步"),**/
	NEWHOUSE_CANCEL_SYNCHRO("312","3","取消外网同步"),
	/**NEWHOUSE_CANCELGOOD("313","3","取消推荐"),**/
	NEWHOUSE_CANCELGOOD("313","3","取消推荐"),
	/**NEWHOUSE_SETVRURL("314","3","设置全景看房"),**/
	NEWHOUSE_SETVRURL("314","3","设置全景看房"),
	/**NEWHOUSE_SET_CUSTOMERSERVICE("315","3","修改客服信息"),**/
	NEWHOUSE_SET_CUSTOMERSERVICE("315","3","修改客服信息"),
	/**NEWHOUSE_SET_COMMISSION("316","3","修改佣金"),**/
	NEWHOUSE_SET_COMMISSION("316","3","修改佣金"),
	/**NEWHOUSE_DEL_HUXING("317","3","删除户型图"),**/
	NEWHOUSE_DEL_HUXING("317","3","删除户型图"),
	/**NEWHOUSE_ADD_PRICE("318","3","新增价格"),**/
	NEWHOUSE_ADD_PRICE("318","3","新增价格"),
	/**NEWHOUSE_UPDATE_PRICE("319","3","修改价格"),**/
	NEWHOUSE_UPDATE_PRICE("319","3","修改价格"),
	/**NEWHOUSE_DEL_PRICE("320","3","删除价格"),**/
	NEWHOUSE_DEL_PRICE("320","3","删除价格"),
	/**NEWHOUSE_ADD_PERMIT("321","3","新增预售证号"),**/
	NEWHOUSE_ADD_PERMIT("321","3","新增预售证号"),
	/**NEWHOUSE_UPDATE_PERMIT("322","3","修改预售证号"),**/
	NEWHOUSE_UPDATE_PERMIT("322","3","修改预售证号"),
	/**NEWHOUSE_DEL_PERMIT("323","3","删除预售证号"),**/
	NEWHOUSE_DEL_PERMIT("323","3","删除预售证号"),
	/**NEWHOUSE_ADD_OPENTIME("324","3","新增开盘时间"),**/
	NEWHOUSE_ADD_OPENTIME("324","3","新增开盘时间"),
	/**NEWHOUSE_UPDATE_OPENTIME("325","3","修改开盘时间"),**/
	NEWHOUSE_UPDATE_OPENTIME("325","3","修改开盘时间"),
	/**NEWHOUSE_DEL_OPENTIME("326","3","删除开盘时间"),**/
	NEWHOUSE_DEL_OPENTIME("326","3","删除开盘时间"),
	/**NEWHOUSE_ADD_DELIVER("327","3","新增交房时间"),**/
	NEWHOUSE_ADD_DELIVER("327","3","新增交房时间"),
	/**NEWHOUSE_UPDATE_DELIVER("328","3","修改交房时间"),**/
	NEWHOUSE_UPDATE_DELIVER("328","3","修改交房时间"),
	/**NEWHOUSE_DEL_DELIVER("329","3","删除交房时间"),**/
	NEWHOUSE_DEL_DELIVER("329","3","删除交房时间"),
	/**NEWHOUSE_UPDATE_DYNAMIC("330","3","修改动态"),**/
	NEWHOUSE_UPDATE_DYNAMIC("330","3","修改动态"),
	/**NEWHOUSE_DEL_DYNAMIC("331","3","删除动态"),**/
	NEWHOUSE_DEL_DYNAMIC("331","3","删除动态"),
	
	

	/************以下是成交模块**************/
	/**ADD_DEAL("400","4","新增")**/
	ADD_DEAL("400","4","新增"),
	/**ADD_DEAL_PICTURE("401","4","上传照片")**/
	ADD_DEAL_PICTURE("401","4","上传照片"),
	/**UPDATE_DEAL("402","4","修改")**/
	UPDATE_DEAL("402","4","修改"),
	/**DELETE_DEAL("403","4","成交删除")**/
	DELETE_DEAL("403","4","成交删除"),
	/**DEAL_STARTTRANSFER("404","4","启动过户")**/
	DEAL_STARTTRANSFER("404","4","启动过户"),
	/**DEAL_STOPTRANSFER("405","4","取消过户")**/
	DEAL_STOPTRANSFER("405","4","取消过户"),
	/**DEAL_SETDIVIDE("406","4","设置分成")**/
	DEAL_SETDIVIDE("406","4","修改分成"),
	/**DEAL_COMPLETETRANSFER("407","4","过户完成")**/
	DEAL_COMPLETETRANSFER("407","4","过户完成"),
	/**DEAL_DELETE_PICTURE("408","4","删除图片")**/
	DEAL_DELETE_PICTURE("408","4","删除图片"),
	/**DEAL_DELETE_TRANSFER("409","4","删除过户进度")**/
	DEAL_DELETE_TRANSFER("409","4","删除过户进度"),
	/**DEAL_DELETE_TRANSFER("410","4","合同打印")**/
	DEAL_PRINT_CONTRACT("410","4","合同打印"),
	/**DEAL_DELETE_TRANSFER("411","4","确认分成")**/
	DEAL_CONFIRM_DIVIDE("411","4","确认分成"),
	/**DEAL_DELETE_TRANSFER("412","4","违约")**/
	DEAL_BREACH("412","4","违约"),
	/**DEAL_DELETE_TRANSFER("413","4","取消违约")**/
	DEAL_CANCEL_BREACH("413","4","取消违约"),
	/**DEAL_ADJUST_TRANSFER_DATE("414","4","调整过户时间")**/
	DEAL_ADJUST_TRANSFER_DATE("414","4","调整过户日期"),
	/**DEAL_MODIFY_TRANSFER("415","4","修改过户")**/
	DEAL_MODIFY_TRANSFER("415","4","修改过户"),
	/**DEAL_RECEIVE_COMMISSION("416","4","收取佣金")**/
	DEAL_RECEIVE_COMMISSION("416","4","收取中介类佣金"),
	/**DEAL_MODIFY_COMMISSION("417","4","修改佣金")**/
	DEAL_MODIFY_COMMISSION("417","4","修改佣金"),
	/**DEAL_PRINT_RECEIPT("418","4","打印收据")**/
	DEAL_PRINT_RECEIPT("418","4","打印收据"),
	/**DEAL_DELETE_COMMISSION("419","4","删除佣金")**/
	DEAL_DELETE_COMMISSION("419","4","删除佣金"),
	/**DEAL_ADJUST_PLAN("420","4","调整计划")**/
	DEAL_ADJUST_PLAN("420","4","调整计划"),
	/**DEAL_CANCEL_RECEIVE_COMMISSION("421","4","取消收取佣金")**/
	DEAL_CANCEL_RECEIVE_COMMISSION("421","4","取消收取佣金"),
	/**DEAL_DOWNLOAD_SINGLE_PICTRUE("422","4","单张下载图片")**/
	DEAL_DOWNLOAD_SINGLE_PICTRUE("422","4","单张下载图片"),
	/**DEAL_DOWNLOAD_BATCH_PICTRUE("423","4","批量下载图片")**/
	DEAL_DOWNLOAD_BATCH_PICTRUE("423","4","批量下载图片"),
	/**DEAL_ADD_FOLLOW_RECORD("424","4","新增跟进记录")**/
	DEAL_ADD_FOLLOW_RECORD("424","4","新增跟进记录"),
	/**DEAL_DELETE_FOLLOW_RECORD("425","4","删除跟进记录")**/
	DEAL_DELETE_FOLLOW_RECORD("425","4","删除跟进记录"),
	/**DEAL_ADD_RECEIVE("426","4","新增代收款")**/
	DEAL_ADD_RECEIVE("426","4","新增代收款"),
	/**DEAL_MODIFY_RECEIVE("427","4","修改代收款")**/
	DEAL_MODIFY_RECEIVE("427","4","修改代收款"),
	/**DEAL_DELETE_RECEIVE("428","4","删除代收款")**/
	DEAL_DELETE_RECEIVE("428","4","删除代收款"),
	/**DEAL_EXPORT_CURENT_PAGE("429","4","导出当前页")**/
	DEAL_EXPORT_CURENT_PAGE("429","4","导出当前页"),
	/**DEAL_EXPORT_BATCH_PAGES("430","4","导出全部页")**/
	DEAL_EXPORT_BATCH_PAGES("430","4","导出全部页"),
	/**DEAL_SEE_PHONENO("431","4","查看电话")**/
	DEAL_SEE_PHONENO("431","4","查看电话"),
	/**DEAL_SEE_PHONENO("432","4","结案")**/
	DEAL_SETTLE_CASE("432","4","结案"),
	/**DEAL_FINALNCE_COMMISION("433","4","收取金融类佣金")**/
	DEAL_FINALNCE_COMMISION("433","4","收取金融类佣金"),
	/**DEAL_MODIFY_FINANCE_COMMISSION("434","4","修改金融类佣金")**/
	DEAL_MODIFY_FINANCE_COMMISSION("434","4","修改金融类佣金"),
	/**DEAL_PRINT_FINANCE_RECEIPT("435","4","打印金融类收据")**/
	DEAL_PRINT_FINANCE_RECEIPT("435","4","打印金融类收据"),
	/**DEAL_DELETE_FINANCE_COMMISSION("436","4","删除金融类佣金")**/
	DEAL_DELETE_FINANCE_COMMISSION("436","4","删除金融类佣金"),
	/**DEAL_ADJUST_FINANCE_PLAN("437","4","金融类调整计划")**/
	DEAL_ADJUST_FINANCE_PLAN("437","4","金融类调整计划"),
	/**DEAL_CANCEL_RECEIVE_COMMISSION("438","4","取消收取金融类佣金")**/
	DEAL_CANCEL_RECEIVE_FINANCE_COMMISSION("438","4","取消收取金融类佣金"),
	/**DEAL_ADD_PAYMENT("439","4","新增代付款")**/
	DEAL_ADD_PAYMENT("439","4","新增代付款"),
	/**DEAL_MODIFY_PAYMENT("440","4","修改代付款")**/
	DEAL_MODIFY_PAYMENT("440","4","修改代付款"),
	/**DEAL_DELETE_PAYMENT("441","4","删除代付款")**/
	DEAL_DELETE_PAYMENT("441","4","删除代付款"),
	
	

	/************以下是财务模块**************/
	/**COMMISSION_SURE("500","12","佣金确认")**/
	COMMISSION_SURE("500","12","佣金确认"),
	/**UPDATE_COMMISSION_SURETIME("501","12","修改佣金确认时间")**/
	UPDATE_COMMISSION_SURETIME("501","12","修改佣金确认时间"),
	/**COMMISSION_REJECT("502","12","佣金驳回")**/
	COMMISSION_REJECT("502","12","佣金驳回"),

	
	/**FINANCE_SURE("503","12","金融确认")**/
	FINANCE_SURE("503","12","金融确认"),
	/**UPDATE_FINANCE_SURETIME("504","12","修改金融确认时间")**/
	UPDATE_FINANCE_SURETIME("504","12","修改金融确认时间"),
	/**FINANCE_REJECT("505","12","金融驳回")**/
	FINANCE_REJECT("505","12","金融驳回"),
	
	/**RECEIVERECORD_SURE("506","12","代收确认")**/
	RECEIVERECORD_SURE("506","12","代收确认"),
	/**RECEIVERECORD_REJECT("507","12","代收驳回")**/
	RECEIVERECORD_REJECT("507","12","代收驳回"),
	/**PAYRECORD_SURE("508","12","代付确认")**/
	PAYRECORD_SURE("508","12","代付确认"),
	/**PAYRECORD_REJECT("509","12","代付驳回")**/
	PAYRECORD_REJECT("509","12","代付驳回"),
	
	/**ADD_WELFARECONFIG("510","5","新增福利配置")**/
	ADD_WELFARECONFIG("510","5","新增福利配置"),
	/**EDIT_WELFARECONFIG("511","5","编辑福利配置")**/
	EDIT_WELFARECONFIG("511","5","编辑福利配置"),
	/**DEL_WELFARECONFIG("512","5","删除福利配置")**/
	DEL_WELFARECONFIG("512","5","删除福利配置"),
	/**ADD_WELFAREDETAIL("513","5","新增福利明细项")**/
	ADD_WELFAREDETAIL("513","5","新增福利明细项"),
	/**EDIT_WELFAREDETAIL("514","5","编辑福利明细项")**/
	EDIT_WELFAREDETAIL("514","5","编辑福利明细项"),
	/**DEL_WELFAREDETAIL("515","5","删除福利明细项")**/
	DEL_WELFAREDETAIL("515","5","删除福利明细项"),
	
	/**ADD_SSFCONFIG("516","5","新增社保配置")**/
	ADD_SSFCONFIG("516","5","新增社保配置"),
	/**EDIT_SSFCONFIG("517","5","编辑社保配置")**/
	EDIT_SSFCONFIG("517","5","编辑社保配置"),
	/**DEL_SSFCONFIG("518","5","删除社保配置")**/
	DEL_SSFCONFIG("518","5","删除社保配置"),
	/**ADD_SSFDETAIL("519","5","新增社保明细项")**/
	ADD_SSFDETAIL("519","5","新增社保明细项"),
	/**EDIT_SSFDETAIL("520","5","编辑社保明细项")**/
	EDIT_SSFDETAIL("520","5","编辑社保明细项"),
	/**DEL_SSFDETAIL("521","5","删除社保明细项")**/
	DEL_SSFDETAIL("521","5","删除社保明细项"),
	
	/**EDIT_WAGESCONFIG("522","5","编辑薪资配置")**/
	EDIT_WAGESCONFIG("522","5","编辑薪资配置"),
	/**ADD_COMMISSIONCONFIG("523","5","新增提成方案")**/
	ADD_COMMISSIONCONFIG("523","5","新增提成方案"),
	/**EDIT_COMMISSIONCONFIG("524","5","编辑提成方案")**/
	EDIT_COMMISSIONCONFIG("524","5","编辑提成方案"),
	/**DEL_COMMISSIONCONFIG("525","5","删除提成方案")**/
	DEL_COMMISSIONCONFIG("525","5","删除提成方案"),
	/**ADD_COMMISSIONRATE("526","5","新增提成比例")**/
	ADD_COMMISSIONRATE("526","5","设置提成比例"),
	/**EDIT_COMMISSIONRATE("527","5","编辑提成比例")**/
	EDIT_COMMISSIONRATE("527","5","编辑提成比例"),
	/**DEL_COMMISSIONRATE("528","5","删除提成比例")**/
	DEL_COMMISSIONRATE("528","5","删除提成比例"),
	/**EDIT_COMMONRATE("529","5","编辑通提比例")**/
	EDIT_COMMONRATE("529","5","设置通提比例"),
	/**ADD_INCOMERATE("530","5","新增实收比例")**/
	ADD_INCOMERATE("530","5","设置实收比例"),
	/**EDIT_INCOMERATE("531","5","编辑实收比例")**/
	EDIT_INCOMERATE("531","5","编辑实收比例"),
	/**DEL_INCOMERATE("532","5","删除实收比例")**/
	DEL_INCOMERATE("532","5","删除实收比例"),
	/**EDIT_BUSINESSTYPE("533","5","编辑业务类型")**/
	EDIT_BUSINESSTYPE("533","5","编辑业务类型"),
	/**ADD_HEADDEPTCOMMISSION("534","5","新增总部计提")**/
	ADD_HEADDEPTCOMMISSION("534","5","新增总部计提"),
	/**EDIT_HEADDEPTCOMMISSION("535","5","编辑总部计提")**/
	EDIT_HEADDEPTCOMMISSION("535","5","编辑总部计提"),
	/**DEL_HEADDEPTCOMMISSION("536","5","删除总部计提")**/
	DEL_HEADDEPTCOMMISSION("536","5","删除总部计提"),
	/**ADD_DESERTSINFO("537","5","新增奖罚")**/
	ADD_DESERTSINFO("537","5","新增奖罚"),
	/**EDIT_DESERTSINFO("538","5","编辑奖罚")**/
	EDIT_DESERTSINFO("538","5","编辑奖罚"),
	/**DEL_DESERTSINFO("539","5","删除奖罚")**/
	DEL_DESERTSINFO("539","5","删除奖罚"),
	/**WAGES_GENERATE("540","5","生成上月工资")**/
	WAGES_GENERATE("540","5","生成上月工资"),
	/**WAGES_SURE("541","5","上月工资通过")**/
	WAGES_SURE("541","5","上月工资通过"),
	/**WAGES_REJECT("542","5","上月工资驳回")**/
	WAGES_REJECT("542","5","上月工资驳回"),
	/**EDIT_WAGES("543","5","编辑基本工资")**/
	EDIT_WAGES("543","5","编辑基本工资"),
	/**EDIT_WELFARE("544","5","编辑福利明细")**/
	EDIT_WELFARE("544","5","编辑福利明细"),
	/**COMMISSION_GENERATE("545","5","生成上月提成")**/
	COMMISSION_GENERATE("545","5","生成上月提成"),
	/**DESERTSINFO_SURE("546","5","奖罚确认")**/
	DESERTSINFO_SURE("546","5","奖罚确认"),
	/**DESERTSINFO_REJECT("547","5","奖罚驳回")**/
	DESERTSINFO_REJECT("547","5","奖罚驳回"),
	
	
	/************以下是OA模块**************/
	/**ADD_NEWS("600","6","新增快讯")**/
	ADD_NEWS("600","6","新增快讯"),
	/**EDIT_NEWS("601","6","编辑快讯")**/
	EDIT_NEWS("601","6","编辑快讯"),
	/**DEL_NEWS("602","6","删除快讯")**/
	DEL_NEWS("602","6","删除快讯"),
	/**ADD_NOTICE("603","6","新增公告")**/
	ADD_NOTICE("603","6","新增公告"),
	/**EDIT_NOTICE("604","6","编辑公告")**/
	EDIT_NOTICE("604","6","编辑公告"),
	/**DEL_NOTICE("605","6","删除公告")**/
	DEL_NOTICE("605","6","删除公告"),
	/**NOTICE_SURE("606","6","公告通过")**/
	NOTICE_SURE("606","6","公告通过"),
	/**NOTICE_REJECT("607","6","公告驳回")**/
	NOTICE_REJECT("607","6","公告驳回"),
	/**NEWS_SURE("608","6","快讯通过")**/
	NEWS_SURE("608","6","快讯通过"),
	/**NEWS_REJECT("609","6","快讯驳回")**/
	NEWS_REJECT("609","6","快讯驳回"),
	/**PERSONNEL_EDIT("610","6","人事档案编辑")**/
	PERSONNEL_EDIT("610","6","人事档案编辑"),
	/**PERSONNEL_CHANGE("611","6","人事档案变动")**/
	PERSONNEL_CHANGE("611","6","人事档案变动"),
	/**SURE_CHANGE("612","6","同意变动")**/
	SURE_CHANGE("612","6","同意变动"),
	/**REJECT_CHANGE("613","6","不同意变动")**/
	REJECT_CHANGE("613","6","不同意变动"),
	/**DOWNLOAD_CURRICULUMVITAE("614","6","下载简历")**/
	DOWNLOAD_CURRICULUMVITAE("614","6","下载简历"),
	/**RECRUITINFO_DEL("615","6","删除招聘信息")**/
	RECRUITINFO_DEL("615","6","删除招聘信息"),
	/**RECRUITINFO_ADD("616","6","新增信息编辑")**/
	RECRUITINFO_ADD("616","6","新增招聘信息"),
	/**RECRUITINFO_UPD("617","6","修改招聘信息")**/
	RECRUITINFO_UPD("617","6","修改招聘信息"),
	/**TASK_ADD("618","6","新增任务")**/
	TASK_ADD("618","6","新增任务"),
	/**TASK_DEL("619","6","删除任务")**/
	TASK_DEL("619","6","删除任务"),
	/**TASK_UPD("620","6","修改任务")**/
	TASK_UPD("620","6","修改任务"),
	/**TASK_DISTRIBUTE("621","6","任务分配")**/
	TASK_DISTRIBUTE("621","6","任务分配"),
	/**TASK_DISTRIBUTE_DEL("622","6","删除任务分配")**/
	TASK_DISTRIBUTE_DEL("622","6","删除任务分配"),
	/**TASK_DISTRIBUTE_UPD("623","6","修改任务分配")**/
	TASK_DISTRIBUTE_UPD("623","6","修改任务分配"),
	;
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  TransferProcessEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	LogBusinessOperateTypeEnum(String code,String type,String desc){
		this.code = code;
		this.type = type;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (LogBusinessOperateTypeEnum item : LogBusinessOperateTypeEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            map.put("type", item.getType());
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
    }
	 /**
     * 枚举转换
     */
    public static LogBusinessOperateTypeEnum parseOf(String value){
        for(LogBusinessOperateTypeEnum item : values())
            if(item.getCode().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
    /**
     * 
    	 * 根据类型查询枚举列表
    	 * @Description: 根据类型查询枚举列表
    	 * @param type
    	 * @return 
    	 * List<Map<String,String>>
     */
    public static List<Map<String , String>> queryEnumByType(String type){
    	List<Map<String, String>> datas=new ArrayList<>();
    	for (LogBusinessOperateTypeEnum item : LogBusinessOperateTypeEnum.values()) {
    		if (item.getType().equals(type)) {
    			Map<String,String> map = new HashMap<String,String>();
                map.put("type", item.getType());
                map.put("value", item.getCode());
                map.put("name", item.getDesc());
                //将枚举值放入List集合中
                datas.add(map);
			}
    	}
    	return datas;
    }
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
