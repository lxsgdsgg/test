<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/houseshow.css">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/deptSelect.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
	<script type="text/javascript" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/layui/layui.js"></script>
	<%-- <script type="text/javascript" src="${staticfile_url}/housing-platform/js/deptSelect.js"></script> --%>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/AreaSelect.js"></script> 
	</head>
	
	<style>
		.btn{
			
		}
		.hasStore{
			color:red;
		}
		.housetag{
			margin-left:10px;
			padding:4px;
			border:1px solid #e400ff;
		}
		.achievement{
			margin-left:10px;
		}
	</style>
	<script type="text/javascript">
	
	
	
		
	
			
			
			
			
	</script>
	<body class="">
			<div class="layui-container">
				<div class="layui-row">
					<div class="layui-col-md12 op-btn" style="border:1px solid #eee;">
						<input value="${houseInfoPermission.updateInfo}">
						<c:if test="${houseInfoPermission.updateInfo!=NULL && houseInfoPermission.updateInfo!=''}"> 
							<div class="btn updatehouse-btn updatehouseinfo" name="updateInfo">修改信息</div>
						</c:if>
						<c:if test="${houseInfoPermission.opendisc!=NULL && houseInfoPermission.opendisc!=''}">
							<div class="btn houseopen" name="opendisc">房源开盘</div>
						</c:if>
						<div class="btn taobao" name="fromTaobao" onclick="changeExamine('fromtaobao')">淘宝</div>
						<div class="btn ">
							<c:if test="${houseInfoPermission.changeStatus!=NULL && houseInfoPermission.changeStatus!=''}">
								<div class="changestatus" name="changeStatus">变更状态</div>
							</c:if>
							<div class="changeowner">变更业主</div>
							<div class="totaobao" onclick="changeStatus('toTaobao');">进入淘宝</div>
                        </div>
						<div class="btn sipang-btn" onclick="changeStatus('setPrivatedisc');">收为私盘</div>
						<div class="btn delliangfang-btn" onclick="cancelGood();">取消靓房</div>
						<div class="btn delvido-btn">删除视频</div>
						<div class="btn writefollow-btn">写跟进</div>
						<div class="btn writetip-btn">写提醒</div>
						<div class="btn writefeedback">写反馈</div>
						<div class="btn cj-btn">租赁成交</div>
						<div class="btn fengpang-btn houseclose">申请封盘</div>
						<div class="btn jubao-btn tipoff">举报</div>
						<div class="btn updateusage-btn">更改用途</div>
						<div class="btn prev-btn">上一条</div>
						<div class="btn next-btn">下一条</div>
						<div class="btn addhouse-btn">新增类似房源</div>
						<div class="btn bindKeyCode">发送绑定码</div>
					</div>
					
				</div>
				<div class="layui-row">
					<div class="layui-col-md12" style="border:1px solid #eee;">
						<div class="layui-tab" lay-filter="myTab">
							<ul class="layui-tab-title">
								<li class="icon-ex layui-this">基本信息</li>
								<li class="icon-ex showhouseimg">房屋照片</li>
								<li class="icon-ex">相关照片</li>
								<li class="icon-ex showhousevideo">房屋视频</li>
								<li class="icon-ex matchCustomer">匹配客户</li>
								<li class="icon-ex showfollowrecord">房源跟进</li>
								<li class="icon-ex houseLookRecord">带看记录</li>
								<li class="icon-ex">日志</li>
								<li class="icon-ex houseDescription">房屋描述</li>
								<li class="icon-ex">改价历史</li>
								<li class="icon-ex agentFeedback">经纪人反馈</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
									<div class="layui-row">
										<div class="layui-col-md7 houseinfo-left">
											<div class="houseinfo-box">
												<div class="layui-row general">
													<div class="layui-col-md8">
														<div class="rowdiv1">
															<div class="show-line houseId">房源编号</div>
															<div class="show-line titles">房源名称</div>
															<div class="show-line housetag"></div>
														</div>
														<div class="rowdiv1">
															<span class="icon2 icon2-1 houseUses">住宅</span>
															<span class="icon2 icon2-2 transactionType">出售</span>
															<span class="icon2 icon2-3 status">有效</span>
															<span class="icon2 icon2-4 discStatus">公盘</span>
															<span class="icon2 icon2-5"><span class="levelType"></span>类房源</span>
															<c:if test="${houseInfo.houseTop!=NULL && houseInfo.houseTop!=''}"> 
																<span class="icon2 icon2-6" style="color:red;" name="houseTop" onclick="setTop();">置顶房源</span>
																<span class="icon2 icon2-7" style="color:red;" name="houseTop" onclick="cancelTop();">取消置顶</span>
															</c:if>
															<span class="icon2 icon2-8 store" onclick="setStore();">收藏</span>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="areaName"></span><span class="regionName"></span><span class="communityName"></span></div>
															<div class="show-line "><span class="houseno"></span><span style="color:red;" onclick="showHouseNo();">查看门牌</span></div>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="room"></span>室<span class="hall"></span>厅<span class="kitchen"></span>厨<span class="toilet"></span>卫<span class="balcony"></span>阳台</div>
															<div class="show-line buildSpace">建筑面积</div>
														</div>
														<div class="rowdiv1">
															<div class="show-line sellingPrice">售价</div>
															<div class="show-line unitPrice">单价</div>
														</div>
													</div>
													<div class="layui-col-md4">
														<div class="show-line">
															<div>坐标地图</div>
															<div>小图标</div>
														</div>
														<div class="show-line">
															二维码
														</div>
													</div>
												</div>
												<div class="layui-row detail">
													<div class="layui-col-md12">
														<div class="layui-row detail-box detail-box1">
															<div class="layui-col-md6">
																<div>楼层：<span class="floorCount"></span></div>
																<div>梯户：<span class="elevatorroomcount"></span></div>
																<div>配套：<span class="matching"></span></div>
																<div>委托编号：<span class="entrustCode"></span></div>
																<div>现状：<span class="actuality"></span></div>
																<div>建筑结构：<span class="structure"></span></div>
																<div>产权性质：<span class="propertyType"></span></div>
																<div>产证日期：<span class="certificateDate"></span></div>
																<div>房源标题：<span class="titles"></span></div>
																<div>房源税费：<span class=""></span></div>
																<div>付款方式：<span class="payType"></span></div>
															</div>
															<div class="layui-col-md6">
																<div>朝向：<span class="orientation"></span></div>
																<div>装修：<span class="decoration"></span></div>
																<div>建筑面积：<span class="buildSpace"></span></div>
																<div>使用面积：<span class="useSpace"></span></div>
																<div>房屋类型：<span class="houseType"></span></div>
																<div>建筑年代：<span class="buildDates"></span></div>
																<div>产权年限：<span class="propRightsLen"></span></div>
																<div>推荐标签：<span class="labeld"></span></div>
																<div>与外网同步：<span class="isSynchron"></span></div>
																<div>房源核验码：<span class="verificationCode"></span></div>
																<div>上次交易时间：<span class="lastTradingTime"></span></div>
															</div>
														</div>
														<div class="layui-row detail-box detail-box2">
															<div class="layui-col-md6">
																<div>来源：<span class="resourceType"></span></div>
																<div>看房方式：<span class="viewingType"></span></div>
																<div>钥匙编号：<span class="keyCode" id=""></span></div>
																<div>钥匙店：<span class="deptname"></span></div>
																<div>抵押信息：<span class="mortgageName"></span></div>
																<div>供暖方式：<span class="heatingModeName"></span></div>
																<div>交易年限：<span class="housingYears"></span></div>
															</div>
															<div class="layui-col-md6">
																<div>独家编号：<span class="entrustCode"></span></div>
																<div>独家人：<span class=""></span></div>
																<div>钥匙收据：<span class="receipt"></span></div>
																<div>钥匙状态：<span class="keystatus"></span></div>
																<div>房本备件：<span class="isUploadCertificate"></span></div>
																<div>交易权属：<span class="tradingRightsName"></span></div>
															</div>
														</div>
														<div class="layui-row detail-box detail-box3">
															<div class="layui-col-md6">
																<div>备注：<span class="remark"></span></div>
																<div>其他</div>
															</div>
															<div class="layui-col-md6">
															</div>
														</div>
														<div class="layui-row detail-box detail-box4">
															<div class="layui-col-md6">
																<div class="customfield">
																	
																
																</div>
															</div>
															<div class="layui-col-md6">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="followinfo-box">
												<div class="followinfo-div1"><span class="follow-label">跟进</span><span class="follow-more">更多</span></div>
												<div>
													<ul class="layui-timeline followinfo-ul">
													  <!-- <li class="layui-timeline-item">
													    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
													    <div class="layui-timeline-content layui-text">
													      <h3 class="layui-timeline-title">8月18日</h3>
													      <div>跟进1</div>
													    </div>
													  </li>
													  <li class="layui-timeline-item">
													    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
													    <div class="layui-timeline-content layui-text">
													      <h3 class="layui-timeline-title">8月16日</h3>
													      
													    </div>
													  </li>
													  <li class="layui-timeline-item">
													    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
													    <div class="layui-timeline-content layui-text">
													      <h3 class="layui-timeline-title">8月15日</h3>
													      <div>跟进2</div>
													    </div>
													  </li>
													  <li class="layui-timeline-item">
													    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
													    <div class="layui-timeline-content layui-text">
													      <div class="layui-timeline-title">8月12日</div>
													      <div>跟进3</div>
													    </div>
													  </li> -->
													</ul>
												</div>
												<div>
													<span class="writefollow" style="color:red;">写跟进</span>
												</div>
											
											</div>
										
										</div>
										<div class="layui-col-md5 houseinfo-right">
											<div class="rowdiv2 right-div1">
												<div><span>全景看房：</span><span class="overallview" style="color:red;">设置</span></div>
												<div><span class="panorama"></span></div>
											</div>
											<div class="rowdiv2">
												<div class="layui-row ownerinfo">
													<div class="layui-col-md7">
														<div><span>业主：</span><span class="owner">李雯</span><span class="nameTitle"></span><span style="margin-left:10px;color:red;" class="updateowner">修改</span></div>
														<div>
															<div>电话：</div>
															<div class="phonelist"></div>
														</div>
														<div class="addphone" style="color:red;">新增电话</div>
													</div>
													<div class="layui-col-md5">
														<div class="showPricedetail">改价历史</div>
														<div class="showArchives">客户档案</div>
													</div>
												</div>
											</div>	
											<div class="rowdiv2">
												<div class="show-btn showphone-btn">查看电话</div>
											</div>
											<div class="rowdiv2">
												<div>
													<div><span>出售低价：<span class="minSellingPrice"></span>万元</span></div>
													<div><span>出租低价：<span class="minRentPrice"></span>元</span></div>
												</div>
											</div>	
											<div class="rowdiv2">	
												<div class="layui-row">
													<div class="showprice-btn layui-col-md6">
														<div class="show-btn" onclick="showMinprice();">查看底价</div>
													</div>
													<div class="updateprice-btn layui-col-md6">
														<div class="show-btn updateprice" style="color:red;">修改底价</div>
													</div>
												</div>
											</div>
											<div class="rowdiv2" style="">
												相关经纪人
												<div class="relateagent-box">
													<div class="layui-row zr-row">
														<div class="layui-col-md2 typeName">录入人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setzr" id="" style="color:red;"></div>
													</div>
													<div class="layui-row kp-row">
														<div class="layui-col-md2 typeName">开盘人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setkp" id="" style="color:red;"></div>
													</div>
													<div class="layui-row wh-row">
														<div class="layui-col-md2 typeName">维护人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset updatewh" name="" id="" style="color:red;">变更维护人</div>
													</div>
													<div class="layui-row ys-row">
														<div class="layui-col-md2 typeName">拿钥匙人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setys" id="" style="color:red;">设置钥匙</div>
													</div>
													<div class="layui-row img-row">
														<div class="layui-col-md2 typeName">图片人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setimg" id="" style="color:red;">设置图片人</div>
													</div>
													<div class="layui-row dj-row">
														<div class="layui-col-md2 typeName">独家信息</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setdj" id="" style="color:red;">设置独家</div>
													</div>
													<div class="layui-row wt-row">
														<div class="layui-col-md2 typeName">委托信息</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span class="achievement"></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setwt" id="" style="color:red;">设置委托</div>
													</div>
												</div>
											</div>
											<div class="rowdiv2" style="height:200px;padding:10px;">
												查看该小区所有房源
												<div style="color:red;" onclick="showHouseSell();">查看销控</div>
											</div>
										</div>
										
									</div>
								</div>
								<div class="layui-tab-item">
									<div class="show-line">
										<div class="show-line">微信上传图片</div>
										<div class="openHouseimgUpload show-line">上传图片</div>
										<div class="show-line">绘制房型图</div>
										<div class="show-line">设置封面</div>
									</div>
									<div class="show-line" style="margin-left:50px;">
										<div class="show-line">全选</div>
										<div class="show-line" >单张下载</div>
										<div class="show-line download">批量下载</div>
										<div class="show-line">图片类型</div>
									</div>
									<div class="img-show">
									
									</div>
									<div class="layui-form-item">
									    <div class="layui-input-block">
									      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateHouseImg();">保存</button>
									    </div>
									</div>
								</div>
								<div class="layui-tab-item">
									
								</div>
								<div class="layui-tab-item">
									<div class="show-line">
										<div class="openHousevideoUpload show-line">上传视频</div>
									</div>
									<div class="show-line" style="margin-left:50px;">
										<div class="show-line" >下载</div>
									</div>
									<div class="video-show">
									
									</div>
								</div>
								<div class="layui-tab-item">
									<div class="layui-form">
										<div class="layui-form-item show-line">
										    <div class="layui-input-block ">
										      <input type="hidden" id="matchAreacode" class="layui-input">
										      <input type="text" placeholder="小区" id="matchArea" name="" class="layui-input">
										    </div>
										</div>
										<div class="layui-form-item show-line">
											<label class="layui-form-label">面积区间</label>
										    <div class="layui-input-block ">
										      <select name="" lay-filter="">
										        <option value="1">200-500</option>
											  	<option value="2">500-1000</option>
											  	<option value="3">1000-1500</option>
										      </select>
										    </div>
										</div>
										<div class="layui-form-item show-line">
											<label class="layui-form-label">价格区间</label>
										    <div class="layui-input-block ">
										      <select name="" lay-filter="">
										        <option value="1">50-100</option>
											  	<option value="2">100-150</option>
											  	<option value="3">150-200</option>
										      </select>
										    </div>
										</div>
										<div class="layui-form-item show-line">
											<label class="layui-form-label">房型</label>
										    <div class="layui-input-block ">
										      <select name="" lay-filter="">
										        <option value="1">0-3</option>
											  	<option value="2">3-5</option>
											  	<option value="3">5-8</option>
										      </select>
										    </div>
										</div>
										<div class="layui-form-item show-line">
											<label class="layui-form-label">楼层</label>
										    <div class="layui-input-block ">
										      <select name="" lay-filter="">
										        <option value="1">1-5</option>
											  	<option value="2">5-10</option>
											  	<option value="3">10-16</option>
										      </select>
										    </div>
										</div>
										
										<div class="layui-form-item show-line">
										    <div class="layui-input-block">
										      <button type="button" class="layui-btn">查询</button>
										    </div>
										</div>
									</div>
									<table id="matchCustomerTable" lay-filter="matchCustomerTable" ></table>
								</div>
								<div class="layui-tab-item">
									<table id="followrecord" lay-filter="" ></table>
								</div>
								<div class="layui-tab-item">
									<table id="lookhouse" lay-filter="" ></table>
								</div>
								<div class="layui-tab-item">
									
								</div>
								<div class="layui-tab-item">
									<div class="editDescription" style="color:red;">编辑</div>
									<table id="lookDescription" lay-filter="lookDescription" ></table>
									
								</div>
								<div class="layui-tab-item">
									改价历史
								</div>
								<div class="layui-tab-item">
									<table id="feedbackTable" lay-filter="feedbackTable" ></table>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
		<script>
		
		function selectArea(code,name){
			$("#matchArea").val(name);
			$("#matchAreacode").val(code);
		}
		
		$(function(){
			//选区域
		   var areaList=JSON.parse('${areas}');
		   
			//匹配房源的区域
			var a= new AreaObject(areaList,selectArea,3,$("#matchArea"));	
			
			
		   //选人员或部门
			var depDatas = JSON.parse('${depts}');
			
			//选封盘人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#fpdepartment").val(name);
					$("#fpdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#fpdepartment"),true);
			
			//选提醒人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#tsdepartment").val(name);
					$("#tsdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#tsdepartment"),true);
			
			//选维护人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#whdepartment").val(name);
					$("#whdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#whdepartment"),true);
			
			//选钥匙人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#ysdepartment").val(name);
					$("#ysdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#ysdepartment"),true);
			
			//选图片人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#imgdepartment").val(name);
					$("#imgdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#imgdepartment"),true);
			
			//选独家人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#djdepartment").val(name);
					$("#djdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#djdepartment"),true);
			
			//选委托人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#wtdepartment").val(name);
					$("#wtdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#wtdepartment"),true);
			
			
			info=JSON.parse('${info}');
			
			 for(var key in info){
				 var data = info[key];
				 console.log(key);
				 console.log(data);
				 //基本信息
				 if(key=="houseInfo"){
					 elevatorroomcount = "";
					 for(var key2 in data){
						 
						 $("."+key2).text(data[key2]);
						 if(key2=='elevatorCount')
							 elevatorroomcount =  data[key2]+"梯";
					     if(key2=='householdCount')
					    	 elevatorroomcount = elevatorroomcount + data[key2]+"户";
					     
					     if(key2=='ysRecordId')
					    	 $(".keyCode").attr("id",data[key2]);
					     //业主姓名类型id
					     if(key2=='nameType')
							 $(".nameTitle").attr("nametype",data[key2]);
					     
					     //标签显示
					     if(key2=='isSetTop' && data.isSetTop==1){//置顶
					    	var housetag = $(".housetag").text();
					    	housetag = housetag==""?"顶":housetag+",顶";
					    	$(".housetag").text(housetag);
					     }
						if(key2=='isGood' && data.isGood==1){//靓房
							var housetag = $(".housetag").text();
					    	housetag = housetag==""?"靓":housetag+",靓";
					    	$(".housetag").text(housetag);
					     }
						if(key2=='isUnique' && data.isUnique==1){//独家
							var housetag = $(".housetag").text();
					    	housetag = housetag==""?"独":housetag+",独";
					    	$(".housetag").text(housetag);
						}
						if(key2=='isSchoolRoom' && data.isSchoolRoom==1){//学期房
							var housetag = $(".housetag").text();
					    	housetag = housetag==""?"学":housetag+",学";
					    	$(".housetag").text(housetag);
						}
						
						if(key2=='taobaoStatusId' && data.taobaoStatusId==1){//是淘宝
							var housetag = $(".housetag").text();
					    	housetag = housetag==""?"淘":housetag+",淘";
					    	$(".housetag").text(housetag);
						}
						
					    if(key2=='isStore' && data.isStore==1){
					    	$(".store").addClass("hasStore");
					    }
					 }
					 $(".elevatorroomcount").text(elevatorroomcount);
				 }
				 //跟进记录
				 if(key=="followInfo"){
					 $(".followinfo-ul").empty();
					 for(var i=0;i<data.length;i++){
						 var li = '<li class="layui-timeline-item">'
					          +'<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'
					          +'<div class="layui-timeline-content layui-text">'
					          +' <h3 class="layui-timeline-title">'+data[i].addTime+'</h3>'
					          +'<div><div>'+data[i].content+'</div><div><span>'+data[i].operator+'</span><span></span><span>'+data[i].addTime+'</span></div></div>'
					          +'</div>'
					          +'</li>';
					          $(".followinfo-ul").append(li);  
					 }
					 
					 
				 }
				 //业主电话
				 /* if(key=='ownerInfo'){
					 $(".phonelist").empty();
					 for(var i=0;i<data.length;i++){
						var div = '<div><span class="phone" style="margin-right:10px;">'+data[i].phone+'</span><span class="relateName" style="margin-right:10px;">'+data[i].remark+'</span><span tag="'+data[i].id+'" onclick="delPhone(this);" style="margin-right:10px;color:red;">删除</span><span class="updatephone" tag="'+data[i].id+'" style="margin-right:10px;color:red;">修改</span></div>';
						$(".phonelist").append(div);
					 }
					 
				 } */
				 
				 //经纪人信息
				 if(key=="relateagentInfo"){
					// $(".relateagent-box").empty();
					// <div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
					 for(var i=0;i<data.length;i++){
						 var id = data[i].id;
						 var deptname = data[i].departmentName;
						 var realName = data[i].agentName;
						 var agentId = data[i].agentId;
						 var telPhone = data[i].mobile;
						 var addTime = data[i].addTime;
						 var agentType = data[i].agentType;
						 var money = 0;
						if(info.relateagentMoney){
							for(var j=0;j<info.relateagentMoney.length;j++){
								if(info.relateagentMoney[j].dividReason==agentType){
									money = info.relateagentMoney[j].estimateProfit/100+'元';
								}
								
							}
						}
						
						 if(data[i].agentTypeName=='房源录入人'){
							//var div2 = '<div class="layui-col-md5 agentset"></div></div>';
							$(".zr-row").find(".agentset").addClass("setzr");
							$(".zr-row").find(".agentset").attr("id",id);
							$(".zr-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".zr-row").find(".agentinfo").find(".realname").text(realName);
							$(".zr-row").find(".agentinfo").find(".achievement").text(money);
							$(".zr-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".zr-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
						 if(data[i].agentTypeName=='房源开盘人'){
							//var div2 = '<div class="layui-col-md5 agentset"></div></div>';
							$(".kp-row").find(".agentset").addClass("setkp");
							$(".kp-row").find(".agentset").attr("id",id);
							$(".kp-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".kp-row").find(".agentinfo").find(".realname").text(realName);
							$(".kp-row").find(".agentinfo").find(".achievement").text(money);
							$(".kp-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".kp-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
					 	 if(data[i].agentTypeName=='房源维护人'){
					 		//var div2 = '<div class="layui-col-md5 updatewh" id="'+data[i].id+'" style="color:red;">变更维护人</div></div>';
					 		$(".wh-row").find(".agentset").addClass("updatewh");
					 		$(".wh-row").find(".agentset").attr("id",id);
					 		$(".wh-row").find(".agentset").attr("agentId",agentId);
					 		$(".wh-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".wh-row").find(".agentinfo").find(".realname").text(realName);
							$(".wh-row").find(".agentinfo").find(".achievement").text(money);
							$(".wh-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".wh-row").find(".agentinfo").find(".agenttime").text(addTime);
					 		
						 }
					 	 if(data[i].agentTypeName=='拿钥匙人'){
							//var div2 = '<div class="layui-col-md5 setys" id="'+data[i].id+'" style="color:red;">设置钥匙</div></div>';
					 		$(".ys-row").find(".agentset").addClass("setys");
					 		$(".ys-row").find(".agentset").attr("id",id);
					 		$(".ys-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".ys-row").find(".agentinfo").find(".realname").text(realName);
							$(".ys-row").find(".agentinfo").find(".achievement").text(money);
							$(".ys-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".ys-row").find(".agentinfo").find(".agenttime").text(addTime);
							ryspeopleid = agentId;
						 }
					 	 if(data[i].agentTypeName=='传照片人'){
							//var div2 = '<div class="layui-col-md5 setimg" id="'+data[i].id+'" style="color:red;">设置图片人</div></div>';
					 		$(".img-row").find(".agentset").addClass("setimg");
					 		$(".img-row").find(".agentset").attr("id",id);
					 		$(".img-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".img-row").find(".agentinfo").find(".realname").text(realName);
							$(".img-row").find(".agentinfo").find(".achievement").text(money);
							$(".img-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".img-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
					  	 if(data[i].agentTypeName=='签独家人'){
							//var div2 = '<div class="layui-col-md5 setdj" id="'+data[i].id+'" style="color:red;">设置独家</div></div>';
					  		$(".dj-row").find(".agentset").addClass("setdj");
					 		$(".dj-row").find(".agentset").attr("id",id);
					 		$(".dj-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".dj-row").find(".agentinfo").find(".realname").text(realName);
							$(".dj-row").find(".agentinfo").find(".achievement").text(money);
							$(".dj-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".dj-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
					 	 if(data[i].agentTypeName=='房源委托人'){
							//var div2 = '<div class="layui-col-md5 setwt" id="'+data[i].id+'" style="color:red;">设置委托</div></div>';
					 		$(".wt-row").find(".agentset").addClass("setwt");
					 		$(".wt-row").find(".agentset").attr("id",id);
					 		$(".wt-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".wt-row").find(".agentinfo").find(".realname").text(realName);
							$(".wt-row").find(".agentinfo").find(".achievement").text(money);
							$(".wt-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".wt-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
								
						//$(".relateagent-box").append(div1+div2);
					 }
					
				 }
				 //字定义字段
				 
				 if(key=="customInfo"){
					 $(".customfield").empty();
					 
					 for(var i=0;i<data.customField.length;i++){
						 var div = '<div class="show-line" style="margin-right:6px;"><span>'+data.customField[i].title+'：</span><span class="customvalue '+data.customField[i].field+'">'+(data.customValue&&data.customValue[data.customField[i].field]?data.customValue[data.customField[i].field]:'')+'</span></div>';
						 $(".customfield").append(div); 
					 }
					 
				 }
				 /* if(key=="customInfo"){
					 $(".customfield").empty();
					 
					 for(var key2 in data){
						 if(key2!='id' &&  data[key2]!=0){
							 var div = '<div class="show-line" style="margin-right:6px;"><span>'+key2+'：</span><span class="customvalue">'+data[key2]+'</span></div>';
							 $(".customfield").append(div); 
						 }
					 }
				 }
				  */
				
			 }
			
			//var a= new deptObject(deptList,selectDept,6,$("#deptName"));	
			//查看电话
			$(".showphone-btn").click(function(){
				var shhId = '${houseId}';//房源主键ID
				$.post("<%=request.getContextPath()%>/house/queryHousePrivateInfo",{houseId:shhId,type:2},function(d){
					$(".phonelist").empty();
					 for(var i=0;i<d.data.length;i++){
						var div = '<div><span class="phone" style="margin-right:10px;">'+d.data[i].phone+'</span><span class="relateName" style="margin-right:10px;">'+d.data[i].remark+'</span>';
						if(d.data[i].isMainFlag==null||d.data[i].isMainFlag==0){
							div+='<span tag="'+d.data[i].id+'" onclick="delPhone(this);" style="margin-right:10px;color:red;">删除</span>';
						}					
						div+='<span class="updatephone" tag="'+d.data[i].id+'" style="margin-right:10px;color:red;">修改</span></div>';
						$(".phonelist").append(div);
					 }
					 
					 openUpdatephone();
					 
				})
				
			});
			
			//修改电话
			function openUpdatephone(){
				$(".updatephone").click(function(){
					  var phone = $(this).parent().find(".phone").text();
					  var relateName = $(this).parent().find(".relateName").text();
					  var param = {
							  phone:phone,  
							  relateName:relateName
					  }
					  openModel("修改电话",'500px','260px',$(".updatephoneModel"),param)
				  });
			
			}
		})
		
		
		
		layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  laydate = layui.laydate; 
			  upload=layui.upload;
			 
			  
			  var pictureType = $("select[name='pictureType']").val();
			  var options = $("select[name='pictureType']").find("option");
			  for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(pictureType == val)
					 var pictureName = $(options[i]).text();
			  }
			  var shhId = '${houseId}';//房源主键ID
			  var houseId = info.houseInfo.houseId;//房源编号
			  var filedata1 = {
					  shhId:shhId,
					  houseId:houseId,
					  pictureType:pictureType,
					  pictureName:encodeURIComponent(pictureName),
					  attachType:"hsPicture"
					  
			  }
			  //上传房源图片
			 var uploadvideo =  upload.render({
				  elem: '#uploadimg'
				  ,url: '<%=request.getContextPath()%>/house/uploadFile/'
				  ,auto: false //选择文件后不自动上传
				  ,bindAction: '.houseimgSureUpload' //指向一个按钮触发上传
				  ,accept:'images'
				  ,multiple:false
				  ,data:filedata1
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				    	
				    });
				  }
				  ,done: function(result){
				      //上传完毕回调
					  if(result.success){
		            		alert("成功！");
		            		 
		            	}else{
		            		alert(result.msg);
		            	}
				   }
			}); 
			  
			  var filedata2 = {
					  shhId:shhId,
					  houseId:houseId,
					  attachType:"hsVideo"
					  
			  }
			 //上传房源视频
			 var uploadvideo = upload.render({
				  elem: '#uploadvideo'
				  ,url: '<%=request.getContextPath()%>/house/uploadFile/'
				  ,auto: false //选择文件后不自动上传
				  ,bindAction: '.housevideoSureUpload' //指向一个按钮触发上传
				  ,accept:'file'
				  ,multiple:false
				  ,data:filedata2
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				    	
				    });
				  }
				  ,done: function(result){
				      //上传完毕回调
					  if(result.success){
		            		alert("成功！");
		            		 
		            	}else{
		            		alert(result.msg);
		            	}
				   }
			}); 
			  
			 //下载
			 $(".download").click(function(){
				 var houseId = '${houseId}';//房源主键ID
				 var imageIds = $(".houseimg").attr("code");
				 window.open("<%=request.getContextPath()%>/house/downloadImages?houseId="+houseId+"&imageIds="+imageIds);
			 })
			 
			  //显示图片
			 $(".showhouseimg").click(function(){
				 var shhId = '${houseId}';//房源主键ID
         		$.post("<%=request.getContextPath()%>/house/getFile",{shhId:shhId,attachType:"01"},function(d){
         			console.log(d);
         			$(".img-show").empty();
         			for(var i=0;i<d.data.length;i++){
         				var div = '<div style="margin:10px;">'
         						+'<div>'+d.data[i].pictureName+'</div>'
         						+'<div><img class="houseimg" id="'+d.data[i].id+'" code="'+d.data[i].code+'" src="'+d.data[i].path+'"></div>'
         						+'<div class="layui-form-item">'
         						+'<label class="layui-form-label">图片类型</label>'
         					    +'<div class="layui-input-block">'
         					    	+'<select name="myPictureType" lay-filter="">'
         					        	+'<option value="1">房型图</option>'
         						  		+'<option value="2">卧室</option>'
         						  		+'<option value="3">客厅</option>'
         						  		+'<option value="4">阳台</option>'
         					        +'</select>'
         					    +'</div>'
         					    +'</div>'
         				         +'</div>'
         				$(".img-show").append(div);
         			}
         		})
			 })
			 
			 //显示视频
			 
			 $(".showhousevideo").click(function(){
				 var shhId = '${houseId}';//房源主键ID
				 $.post("<%=request.getContextPath()%>/house/getFile",{shhId:shhId,attachType:"03"},function(d){
	         			console.log(d);
	         			$(".video-show").empty();
	         			for(var i=0;i<d.data.length;i++){
	         				var div = '<div style="margin:10px;">'
	         						   +'<div><video class="housevideo" controls="controls" id="'+d.data[i].id+'" code="'+d.data[i].code+'" src="'+d.data[i].path+'"></div>'
	         						
	         				           +'</div>'
	         				$(".video-show").append(div);
	         			}
	         		})
			 })
			  
			  $(".openHouseimgUpload").click(function(){
				  openModel("上传房源图片",'500px','460px',$(".uploadImgModel"))
			  })
			  
			   $(".openHousevideoUpload").click(function(){
				  openModel("上传房源图片",'500px','460px',$(".uploadVideoModel"))
			  })
			  
			  
			  $(".houseopen").click(function(){
				  openModel("房源开盘",'500px','460px',$(".houseOpenModel")) 
			  });
			  
			  $(".changestatus").click(function(){
				  var houseStatus = $(".status").text();
				  var param = {
						  houseStatus:houseStatus  
				  }
				 <%--  $.post("<%=request.getContextPath()%>/house/getHouseStatusType",{},function(){
					  
				  }) --%>
				  openModel("变更状态",'500px','260px',$(".changestatusModel"),param)
			  });
			  
			  $(".houseclose").click(function(){
				  openModel("申请封盘",'500px','460px',$(".houseCloseModel"))
			  });
			  
			  
			  $(".updateusage-btn").click(function(){
				  openModel("变更用途",'500px','260px',$(".changeUsageModel"))
			  });
			  
			  
			  $(".tipoff").click(function(){
				  openModel("举报",'500px','360px',$(".tipoffModel"))
			  });
			  
			  $(".writefollow").click(function(){
				  openModel("房源写跟进",'800px','700px',$(".followModel"))
			  });
			  
			  $(".writefollow-btn").click(function(){
				  openModel("房源写跟进",'800px','700px',$(".followModel"))
			  });
			  
			  $(".overallview").click(function(){
				  openModel("设置全景地址",'500px','260px',$(".setOverallview"))
			  });
			  $(".updateowner").click(function(){
				 // var ownerid = $(".updateowner").attr("id");
				  var ownername = $(".owner").text();
				  var nametype = $(".nameTitle").attr("nametype");
				  //var nametitle = $(".nameTitle").text();
				  //var relatetype = $(".relateName").attr("relatetype");
				  var param = {
						  ownername:ownername,
						  nametype:nametype
				  }
				  openModel("修改业主",'500px','260px',$(".updateOwnerModel"),param)
			  });
			  $(".addphone").click(function(){
				  openModel("新增电话",'500px','260px',$(".addphoneModel"))
			  });
			  
			  
			  
			  $(".changeowner").click(function(){
				  openModel("变更业主",'500px','460px',$(".changeOwnerModel"))
			  });
			  
			  $(".updateprice").click(function(){
				  var rminSellingPrice = $(".minSellingPrice").text();
				  var rminRentPrice = $(".minRentPrice").text(); 
				  var param = {
						  rminSellingPrice:rminSellingPrice,
						  rminRentPrice:rminRentPrice
				  }
				  openModel("修改低价",'500px','260px',$(".updatepriceModel"),param)
			  });
			  
			  $(".updatewh").click(function(){
				 var realname =  $(this).prev().find(".realname").text();
				 var param = {
						 realname:realname
				  }
				  openModel("变更维护人",'500px','260px',$(".updateOperatorModel"),param)
			  });
			  
			  $(".setys").click(function(){
				  var yscode = $(".keyCode").text(); 
				  var param = {
						  yscode:yscode
				  }
				  openModel("设置钥匙",'500px','460px',$(".setysModel"),param)
			  });
			  
			  $(".setimg").click(function(){
				  openModel("设置图片人",'500px','260px',$(".setimgModel"))
			  });
			  
			  $(".setdj").click(function(){
				  openModel("设置独家",'500px','460px',$(".setdjModel"))
			  });
			  $(".setwt").click(function(){
				  openModel("设置委托",'500px','460px',$(".setwtModel"))
			  });
			  
			  $(".writefeedback").click(function(){
				  openModel("写反馈",'360px','260px',$(".feedbackModel"))
			  });
			  
			  $(".showPricedetail").click(function(){
				  var shhId = '${houseId}';
				  
				  var cols = [
				  		 {field:"rentPrice",title:"当前租价",width:'100'},
				 		 {field:"rentFloat",title:"租价浮动金额",width:'100'},
				 		 {field:"sellingPrice",title:"当前售价",width:'100'},
				 		 {field:"sellFloat",title:"售价浮动金额",width:'100'},
				 		 {field:"operator",title:"操作人",width:'100'},
				 		 {field:"deptName",title:"部门"}
				 		 
				  		];
			  
				  table.render({
				    elem: '#pricedetail'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/house/getPricedetail/?shhId="+shhId //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  
				  <%-- $.post("<%=request.getContextPath()%>/house/getPricedetail",{shhId:shhId},function(d){
					  
				  }) --%>
				  
				  openModel("改价历史",'500px','460px',$(".priceDetailModel"));
			  });
			  
			  
			  $(".showArchives").click(function(){
				  var shhId = '${houseId}';
				//房源
				  var cols = [
				  		 {field:"transactionType",title:"类型",width:'80'},
				 		 {field:"status",title:"状态",width:'80'},
				 		 {field:"houseId",title:"房源编号",width:'100'},
				 		 {field:"communityName",title:"小区"},
				 		 {field:"buildingsName",title:"坐栋"},
				 		 {field:"buildingHouseName",title:"房号"},
				 		{field:"floorCount",title:"楼层"},
				 		{field:"room",title:"房型",templet: function(d){
				 	        return  d.room +"室"+d.hall+"厅"
				 	      }},
				 		{field:"buildSpace",title:"面积"},
				 		{field:"sellingPrice",title:"售价"},
				 		{field:"rentPrice",title:"租价"},
				 		{field:"maintainer",title:"维护人"}
				  		];
			  
				  table.render({
				    elem: '#customerHouse'
				    ,height: 200
				    ,url:"<%=request.getContextPath()%>/house/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{shhId:shhId,type:'house'}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  //求租
				  var cols = [
				  		 {field:"discStatus",title:"公/私",width:'80'},
				 		 {field:"status",title:"状态",width:'80'},
				 		 {field:"demandId",title:"客源编号",width:'100'},
				 		 {field:"areaNames",title:"区域"},
				 		 {field:"minSpace",title:"需求面积",templet: function(d){
					 	        return  d.minSpace +"-"+d.maxSpace+"室"
				 	      }},
				 		 {field:"minPrice",title:"需求价格",templet: function(d){
					 	        return  d.minPrice +"-"+d.maxPrice+"室"
				 	      }},
				 		 {field:"minRoom",title:"房型",templet: function(d){
					 	        return  d.minRoom +"-"+d.maxRoom+"室"
				 	      }},
				 		 {field:"orientation",title:"朝向"},
				 		 {field:"minFloorCount",title:"楼层",templet: function(d){
					 	        return  d.minFloorCount +"-"+d.maxFloorCount+"室"
				 	      }},
				 		 {field:"maintainer",title:"维护人"}
				  		];
			  
				  table.render({
				    elem: '#customerDemandRent'
				    ,height: 200
				    ,url:"<%=request.getContextPath()%>/house/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{shhId:shhId,type:'rent'}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  
				  //求购
				   var cols = [
				  		 {field:"discStatus",title:"公/私",width:'80'},
				 		 {field:"status",title:"状态",width:'80'},
				 		 {field:"demandId",title:"客源编号",width:'100'},
				 		 {field:"areaNames",title:"区域"},
				 		 {field:"minSpace",title:"需求面积",templet: function(d){
					 	        return  d.minSpace +"-"+d.maxSpace+"室"
				 	      }},
				 		 {field:"minPrice",title:"需求价格",templet: function(d){
					 	        return  d.minPrice +"-"+d.maxPrice+"室"
				 	      }},
				 		 {field:"minRoom",title:"房型",templet: function(d){
					 	        return  d.minRoom +"-"+d.maxRoom+"室"
				 	      }},
				 		 {field:"orientation",title:"朝向"},
				 		 {field:"minFloorCount",title:"楼层",templet: function(d){
					 	        return  d.minFloorCount +"-"+d.maxFloorCount+"室"
				 	      }},
				 		 {field:"maintainer",title:"维护人"}
				  		];
			  
				  table.render({
				    elem: '#customerDemandBuy'
				    ,height: 200
				    ,url:"<%=request.getContextPath()%>/house/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{shhId:shhId,type:'buy'}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  
				  openModel("客户档案",'650px','460px',$(".customerArchivesModel"));
			  });
			  
			  
			  laydate.render({
				    elem: '.remindtime' //指定元素
				  });
			  
			  laydate.render({
				    elem: '.ystime' //指定元素
				  });
			  laydate.render({
				    elem: '.djtime' //指定元素
				  });
			  laydate.render({
				    elem: '.wttime' //指定元素
				  });
			  laydate.render({
				    elem: '.fptime' //指定元素
				  });
			  
			  
			//客源匹配
			  $(".matchCustomer").click(function(){
				 var transactionTypeId = info.houseInfo.transactionTypeId;
				 var conditions = {transactionTypeId:transactionTypeId};
				  var cols = [
					  	 {field:"status",title:"状态",width:'80'},
				 		 {field:"id",title:"客源编号",width:'100'},
				 		 {field:"areas",title:"区域",width:'120'},
				 		 {field:"minSpace",title:"面积",width:'100'},
				 		 {field:"minRoom",title:"户型",width:'120'},
				 		 {field:"minPrice",title:"价格",width:'100'},
				 		 {field:"minFloorCount",title:"楼层",width:'120'},
				 		 {field:"maintainer",title:"维护人",width:'120'},
				  		];
				  table.render({
				    elem: '#matchCustomerTable'
				    ,height: 360
				    ,url:"<%=request.getContextPath()%>/house/matchingCustomer" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{conditions:JSON.stringify(conditions)}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  });
			  
			  //查看房源带看
			  $(".houseLookRecord").click(function(){
				  var shhId = '${houseId}';
				  var cols = [
				  		 {field:"companyName",title:"公司",width:'120'},
				 		 {field:"operator",title:"带看人",width:'120'},
				 		 {field:"deptname",title:"所在部门",width:'120'},
				 		 {field:"content",title:"带看内容",width:'200'},
				 		 {field:"addTime",title:"带看时间"},
				 		 {field:"visterEvaluate",title:"带看评价"}
				  		];
			  
				  table.render({
				    elem: '#lookhouse'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/house/getLookHouse/?shhId="+shhId //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  })
			  
			  //查看房源跟进
			  $(".showfollowrecord").click(function(){
				  var shhId = '${houseId}';
				  var cols = [
				  		 {field:"companyName",title:"公司",width:'120'},
				 		 {field:"operator",title:"跟进人",width:'120'},
				 		 {field:"deptname",title:"所在部门",width:'120'},
				 		 {field:"content",title:"跟进内容",width:'200'},
				 		 {field:"addTime",title:"跟进时间",width:'120'},
				 		{field:"followTypeName",title:"跟进方式"}
				  		];
			  
				  table.render({
				    elem: '#followrecord'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/house/getFollowRecord/?shhId="+shhId //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  })
			  
			  
			  //查看经纪人反馈
			  $(".agentFeedback").click(function(){
				  var shhId = parseInt('${houseId}');
				  var cols = [
				  		 {field:"content",title:"内容",width:'120'},
				 		 {field:"lastTime",title:"最近一次带看时间",width:'120'},
				 		 {field:"showedCnt",title:"带看次数",width:'120'},
				 		 {field:"usefulCnt",title:"有用次数",width:'200'},
				 		 {field:"agentName",title:"经纪人",width:'140'},
				 		 {field:"addTime",title:"填写时间"},
				 		{fixed: 'right', width:180, align:'center', toolbar: '#barFeedback'}
				  		];
			  	  var condition = {shhId:shhId}
				  table.render({
				    elem: '#feedbackTable'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/house/getAgentFeedback" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{condition:JSON.stringify(condition)}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  })
			    //查看房源描述
			 $(".houseDescription").click(function(){
				 var transactionTypeId = info.houseInfo.transactionTypeId; 
				
				  var shhId = '${houseId}';
				  var cols = [
				  		 {field:"companyName",title:"公司",width:'120'}, 
				  		 {field:"typeName",title:"描述字段",width:'120'},
				 		 {field:"operator",title:"添加人",width:'120'},
				 		 {field:"deptName",title:"所在部门",width:'120'},
				 		 {field:"addTime",title:"添加时间",width:'120'},
				 		 {fixed: 'right', width:120, align:'center', toolbar: '#barOp'}
				  		];
			  
				  table.render({
				    elem: '#lookDescription'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/house/getHouseDescription/?shhId="+shhId //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			 $.post("<%=request.getContextPath()%>/house/getDescriptionField",{transactionTypeId:transactionTypeId},function(d){
								// console.log(d.data);
								$(".description").remove();
								 for(var i=0;i<d.data.length;i++){
								 	var item = '<div class="layui-form-item description">'
									             +'<label class="layui-form-label">'+d.data[i].fieldValue+'</label>'
								                 +'<div class="layui-input-block ">'
								                 +'<input type="text" id="" placeholder="请输入内容" name="'+d.data[i].id+'" class="layui-input description-input">'
								                 +'</div>'
								              +'</div>'
								              
								    $(".saveDescriptionBtn").before(item);   
								              
								 }
								 form.render();
								 var data =  table.cache["lookDescription"];
						  			console.log(data);
							    	for(var i=0;i<data.length;i++){
							    		var inputs = $(".description-input");
							    		for(var j=0;j<inputs.length;j++){
							    			var name = $(inputs[j]).attr("name");
							    			if(data[i].typeId == name){
								    			$(inputs[j]).val(data[i].description);
								    			$(inputs[j]).attr("id",data[i].id);
								    		}
							    		}
							    		
							    }
						})
			  			
			  		}
				  }); 
			  })
			  
			  
			   //监听
			  table.on('tool(lookDescription)', function(obj){
				    var d = obj.data;
				    if(obj.event === 'show'){
				    	openModel("查看描述",'650px','460px',$(".descriptionModel"));
				    }else if(obj.event === 'del'){
				    	d.description="";
				    	table.reload('lookhouseRecord', {
							  data:d
						 });
				    	var data = [];
				    	data.push(d);
					    $.ajax({
				            type: "POST",//方法类型
				            dataType: "json",//预期服务器返回的数据类型
				            contentType: 'application/json',
				            data:JSON.stringify(data),
				            url: "<%=request.getContextPath()%>/house/saveHouseDescription" ,//url	                
				            success: function (result) {
				            	if(result.success){
				            		alert("成功！");
				            	}else{
				            		alert(result.msg);
				            	}
				            },
				            error : function() {
				            	
				            },
				            beforeSend : function() {
				            	
				            }
				       }); 
				    }
				    
				    
				    
				  });
			  table.on('tool(feedbackTable)', function(obj){
				    var d = obj.data;
				    var id = d.id;
				    if(obj.event === 'edit'){
				    	$(".feedbackId").val(d.id);
				    	$(".feedback-content").val(d.content);
				    	openModel("编辑反馈",'360px','260px',$(".feedbackModel"))
				    }else if(obj.event === 'del'){
				    	$.post("<%=request.getContextPath()%>/house/delAgentFeedback",{id:id},function(d){
				    		if(d.success){
			            		alert("已删除！");
			            	}else{
			            		alert(d.msg);
			            	}
				    	})
				    }
				    
				    
				    
				  });
			  
			  
			  
			  //编辑房源描述
			 $(".editDescription").click(function(){
				 
				 openModel("编辑描述",'650px','460px',$(".descriptionModel"));
			 })
			  //修改房源信息
			  $(".updatehouseinfo").click(function(){
				  var shhId = '${houseId}';
				  window.location="<%=request.getContextPath()%>/house/editHouse?houseId="+shhId+"&formName="+info.houseInfo.houseUsesId;

			  });
			  
		  	form.on('checkbox(cancelorder)', function(data){
			  if(data.elem.checked){//是否被选中，true或者false
				  $(".cancelorder").attr("checked",true);
			  }
			});   
			  	
			//发送绑定码
			$(".bindKeyCode").click(function(){
				var houseId = '${houseId}';
				$.post("<%=request.getContextPath()%>/house/sendBindCode",{houseId:houseId},function(d){
					if(d.success){
	            		alert("成功！");
	            	}else{
	            		alert(d.msg);
	            	}
				})
			})
			  
		});
		
		
		
		/*弹出框*/
		function openModel(title,width,height,divModel,param,myEvent) {
			if(title=='变更状态'){
				$("input[name='ystatus']").val(param.houseStatus);
			}
			if(title=='修改业主'){
				//$("input[name='ownerid']").val(param.ownerid);
				$("input[name='ownerName']").val(param.ownername);
				$("select[name='nameTitle']").val(param.nametype);
				form.render('select');
			}
			if(title=='修改电话'){
				$("input[name='upownerphone']").val(param.phone);
				$("input[name='upownerremark']").val(param.relateName);
			}
			/* if(title=='修改低价'){
				$("input[name='minSellingPrice']").val(param.rminSellingPrice);
				$("input[name='minRentPrice']").val(param.rminRentPrice);
			} */
			
			if(title=="变更维护人"){
				$("input[name='ywh']").val(param.realname);
			}
			if(title=='设置钥匙'){
				$("input[name='yscode']").val(param.yscode);
			}
			layer.open({
				type: 1,
				title:title ,
				shade: [0],
				area: [width, height],
				offset: 'auto', //弹出位置
				btnAlign: 'r',
				content:divModel  //显示的url，no代表不显示滚动条
				
			});
		}
		
		
		
		//更改房源图片信息
		function updateHouseImg(){
			var shhId = '${houseId}';
			var id = $(".houseimg").attr("id");
			var pictureType = $("select[name='myPictureType']").val();
			  var options = $("select[name='myPictureType']").find("option");
			  for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(pictureType == val)
					 var pictureName = $(options[i]).text();
			  }
			//选中的图片的信息
			var imgInfos = [];
			var imgInfo = {
					id:id,
					isCover:1,
					pictureType:pictureType,
					pictureName:pictureName
			}
			imgInfos.push(imgInfo);
			var data = {
					shhId:shhId,	
					imgInfos:imgInfos
			}
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/updateAttachment" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		       }); 
		} 
		
		
		//变更审核
		function changeExamine(type){
			//var sourceCode = $(".houseId").text();
			var sourceId = '${houseId}';
			
			var examineInfo = {
					shhId:sourceId
			 }
			
			if(type=='changestatus'){
				
				var newStateId = $("select[name='xstatus']").val();
				var options = $("select[name='xstatus']").find("option");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(newStateId == val)
						 var newStateName = $(options[i]).text();
				 }
				 var reason = $("input[name='reason']").val();
				
				// examineInfo.auditTtypeId=1;
				// examineInfo.auditTtypeName='修改状态';
				 
				 var remark = {
						 statusId:newStateId,
						 statusName:newStateName
				 }
				 examineInfo.remark = remark;
				 examineInfo.content = reason;
				 
			}
			
			if(type=="houseclose"){
				//examineInfo.auditTtypeId=6;
				//examineInfo.auditTtypeName='房源封盘';
				
				//var peopleid = $("#fpdepartmentcode").val();
				//var peoplename = $("#fpdepartment").val();
				//examineInfo.applicantId = peopleid;
				
				var fptypeid = $("select[name='fptype']").val();
				var options = $("select[name='fptype']").find("option");
				//var closeRecordid = $(".setfp").attr("id");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(fptypeid == val)
						 var fptype = $(options[i]).text();
				 }
				
				var fptime = $(".fptime").val();
				var remark = {
						closeHouseTypeId:fptypeid,
						closeHouseType:fptype,
						closeHouseEndTime:fptime
				}
				examineInfo.remark = remark;
				var reason = $(".fpreason").val();
				examineInfo.content = reason;
				
			}
			 
			if(type=="houseopen"){
				//examineInfo.auditTtypeId=5;
				//examineInfo.auditTtypeName='开盘';
				var statusId = info.houseInfo.statusId;
				var entryRecordid = $(".setzr").attr("id");
				var opendiscRecordid = $(".setkp").attr("id");
				var mainterRecordid = $(".updatewh").attr("id");
				var transactionTypeId = $("select[name='transactionType']").val();
				var options = $("select[name='transactionType']").find("option");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(transactionTypeId == val)
						 var transactionType = $(options[i]).text();
				 }
				var sellingPrice = $("input[name='sellprice']").val();
				//alert(sellingPrice)
				var rentPrice = $("input[name='rentprice']").val();
				var rentPriceTypeId = $("select[name='rentpriceunit']").val();
				var options = $("select[name='rentpriceunit']").find("option");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(rentPriceTypeId == val)
						 var rentPriceTypeName = $(options[i]).text();
				 }
				
				var remark = {
						transactionTypeId:transactionTypeId,
						transactionType:transactionType,
						sellingPrice:sellingPrice,
						rentPrice:rentPrice,
						rentPriceTypeId:rentPriceTypeId,
						rentPriceTypeName:rentPriceTypeName,
						statusId:statusId,
						entryRecordid:entryRecordid,
						opendiscRecordid:opendiscRecordid,
						mainterRecordid:mainterRecordid
				}
				examineInfo.remark = remark;
				examineInfo.content = "开盘";
			}
			
			if(type=="tipoff"){
				//examineInfo.auditTtypeId=3;
				//examineInfo.auditTtypeName='房源举报';
				var mainterRecordid = $(".updatewh").attr("id");
				var tipoffid = $("select[name='tipoffType']").val();
				var options = $("select[name='tipoffType']").find("option");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(tipoffid == val)
						 var tipoffName = $(options[i]).text();
				 }
				//examineInfo.handleType = tipoffid;
				 var reason = $(".tipoffreason").val();
				 examineInfo.content = reason;
				 var remark = {
						 mainterRecordid:mainterRecordid,
						 accusationTypeId:tipoffid,
						 accusationType:tipoffName,
						 accusationReason:reason
				 }
				examineInfo.remark = remark;
			}
			
			if(type=='fromtaobao'){
				var mainterRecordid = $(".updatewh").attr("id");
				var remark = {
					mainterRecordid:mainterRecordid,
			   }
			   examineInfo.remark = remark;
			   examineInfo.content = "从淘宝淘出";
			}
			var data = {
					type:type,
					examineInfo:examineInfo
			}
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/businessExamine" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		        }); 
		}
		
		
		//改变一些状态信息
		function changeStatus(type){
			var id = '${houseId}';
			//进淘宝池
			if(type=="toTaobao"){
				//var statusId = 1;
				//var status = '淘宝池';
				var statusInfo = {
						id:id,
						//taobaoStatusId:statusId,
						//taobaoStatus:status,
				}
			}
			//改变用途
			if(type=="changeUsage"){
				var statusId = $("select[name='xusage']").val();
				var options = $("select[name='xusage']").find("option");
				for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(statusId == val)
						 var status = $(options[i]).text();
				 }	
				var statusInfo = {
						id:id,
						houseUsesId:statusId,
						houseUses:status,
				}
			}
			//收为私盘
			if(type=="setPrivatedisc"){
				//var statusId = 1;
				//var status = '私盘';
				var recordid = $(".updatewh").attr("id");
				var statusInfo = {
						id:id,
						recordid:recordid
						//discStatusId:statusId,
						//discStatus:status,
				}
			}
			var data = {
					type:type,
					statusInfo:statusInfo
			}
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/changeSomeStatus" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
		}
		
		
		
		//设置置顶
		function setTop(){
			var shhId = '${houseId}';
			$.post("<%=request.getContextPath()%>/house/updateTag",{shhId:shhId,type:"top",status:1},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			})
		}
		
		//取消置顶
		function cancelTop(){
			var shhId = '${houseId}';
			$.post("<%=request.getContextPath()%>/house/updateTag",{shhId:shhId,type:"top",status:0},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			})
		}
		
		//设置和取消收藏
		function setStore(){
			var shhId = '${houseId}';
			if($(".store").hasClass("hasStore")){
				$(".store").removeClass("hasStore");
				var status = 0;
			}else{
				$(".store").addClass("hasStore");
				var status = 1;
			}
			$.post("<%=request.getContextPath()%>/house/updateTag",{shhId:shhId,type:"store",status:status},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			})
		}
		
		//取消靓房
		function cancelGood(){
			var shhId = '${houseId}';
			$.post("<%=request.getContextPath()%>/house/updateTag",{shhId:shhId,type:"good",status:0},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			})
		}
		
		//保存跟进
		function saveFollow(){
			var shhId = '${houseId}';
			//跟进
			var followtypeid = $("select[name='followtype']").val();
			var options = $("select[name='followtype']").find("option");
			 for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(followtypeid == val)
					 var followtype = $(options[i]).text();
			 }
			var followcontent = $(".followcontent").val();
			//提醒
			var remindpeopleid = $("#tsdepartmentcode").val();
			var remindpeople = $("#tsdepartment").val();
			var remindtime = $(".remindtime").val();
			var remindcontent = $(".remindcontent").val();
			
			var followInfo = {
					shhId:shhId,
					followType:followtypeid,
					followTypeName:followtype,
					content:followcontent
			}
			var remindInfo = {
					receivePeopleId:remindpeopleid,
					receivePeopleName:remindpeople,
					remindTime:remindtime,
					remindContent:remindcontent
			}
			
			var data = {
					followInfo:followInfo,
					remindInfo:remindInfo,
			}
			if( $(".cancelorder").is(":checked")){
				data.isCancelNotice = 1;
			}
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/saveHouseFollowAndRemind" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		if( $(".cancelorder").is(":checked")){
		            		var agentId = $(".updatewh").attr("agentId");
							var content = $(".followcontent").val();
							//alert(agentId);
							//alert(content);
							$.post("/im/user/imuser/sysMsg",{"reSessionId":"42fac217c15d4b5baadd9dbc335c99a3","msgContent":content},function(d){
								//alert(d);
							})
	            		}
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        }); 
		}
		
		//设置全景看房
		function setOverallview(){
			var houseId = '${houseId}';
			var address = $(".overallviewaddress").val();
			//alert(address);
			 $.post("<%=request.getContextPath()%>/house/setOverallview",{houseId:houseId,address:address},function(data){
				 if(data.success){
					 alert("设置成功！");
				 }else{
					 alert(data.msg); 
				 }
			 });
		}
		//修改业主
		function updateOwner(){
			// var ownerid = $(".updateowner").attr("id");
			 var ownername = $("input[name='ownerName']").val();
			 var nametype = $("select[name='nameTitle']").val();
			 var options = $("select[name='nameTitle']").find("option");
			 for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(nametype == val)
					 var nametitle = $(options[i]).text();
			 }
			 var shhId = '${houseId}';
			 var data = {
					 id:shhId,
					 owner:ownername,
					 nameType:nametype,
					 nameTitle:nametitle
			 }
			 
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/UpdateHouseOwner" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("保存成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		        });  
		}
		//新增业主电话
		function addPhone(){
			var shhId = '${houseId}';
			var ownerphone = $("input[name='ownerphone']").val();
			var ownerremark = $("input[name='ownerremark']").val();
			var data={
				shhId:shhId,
				phone:ownerphone,
				remark:ownerremark
			}
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/saveOwnerPhone" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
			
		}
		
		//删除业主电话
		function delPhone(obj){
			var phonerecordid = $(obj).attr("tag");
			$.post("<%=request.getContextPath()%>/house/delOwnerPhone",{id:phonerecordid},function(d){
				if(d.success){
            		alert("成功！");
            	}else{
            		alert(d.msg);
            	}
			});
		}
		//修改业主电话
		function updatePhone(){
			var phonerecordid = $(".updatephone").attr("tag");
			var ownerphone = $("input[name='upownerphone']").val();
			var ownerremark = $("input[name='upownerremark']").val();
			var data={
					id:phonerecordid,
					phone:ownerphone,
					remark:ownerremark
				}
				$.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/updateOwnerPhone" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("保存成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		        });  
		}
		
		//变更业主
		function saveNewOwner(){
			var ownername = $("input[name='newowner']").val();
			var nametype = $("select[name='newnameTitle']").val();
			
			 var options = $("select[name='newnameTitle']").find("option");
			 for(var i=0;i<options.length;i++){
				 
				 var val = $(options[i]).attr("value");
				 if(nametype == val)
					 var nametitle = $(options[i]).text();
			 }
			 var phone = $("input[name='newphone']").val();
			 var remark = $("input[name='newremark']").val();
			 var shhId = '${houseId}';
			 var owner = {
					 id:shhId,
					 owner:ownername,
					 nameType:nametype,
					 nameTitle:nametitle
			 }
			 
			 var ownerphone = {
					 shhId:shhId,
					 phone:phone,
					 remark:remark
			 }
			 var data = {
					 houseId:shhId,
					 owner:owner,
					 ownerphone:ownerphone
			 }
			 
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/changeHouseOwner" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("保存成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		        });  
		}
		
		//修改低价
		function updatePrice(){
			var shhId = '${houseId}';
			//var rminSellingPrice = $(".minSellingPrice").text();
			//var rminRentPrice = $(".minRentPrice").text(); 
			var minSellingPrice = $("input[name='minSellingPrice']").val();
			var minRentPrice = $("input[name='minRentPrice']").val();
			//var sellFloat = minSellingPrice-rminSellingPrice;
			//var rentFloat = minRentPrice-rminRentPrice;
			var data = {
					shhId:shhId,
					minSellingPrice:minSellingPrice,
					minRentPrice:minRentPrice
			}
			<%-- $.post("<%=request.getContextPath()%>/house/updatePrice",{shhId:shhId,minSellingPrice:minSellingPrice,minRentPrice:minRentPrice,sellFloat:sellFloat,rentFloat:rentFloat},function(data){
				if(data.success){
            		alert("保存成功！");
            	}else{
            		alert(data.msg);
            	}
			}); --%>
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updatePrice" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
			
		}
		
		//变更维护人
		function updateWh(){
			var shhId = '${houseId}';
			var yrecordid = $(".updatewh").attr("id");
			var xwhid = $("#whdepartmentcode").val();
			var xwh = $("#whdepartment").val();
			
			<%-- $.post("<%=request.getContextPath()%>/house/updateMaintenance",{shhId:shhId,yrecordid:yrecordid,xwhid:xwhid,xwh:xwh},function(data){
				
			}) --%>
			var data = {
					shhId:shhId,
					recordid:yrecordid,
					peopleid:xwhid,
					people:xwh,
					type:"wh"
			}
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updateAgent" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
			
		}
		//设置钥匙
		function updateYs(){
			var shhId = '${houseId}';
			var yscode = $("input[name='yscode']").val();
			var ystime = $("input[name='ystime']").val();
			var ysreceipt = $("input[name='ysreceipt']").val();
			var yspeople = $("input[name='yspeople']").val();
			var yspeopleid = $("#ysdepartmentcode").val();
			var ysrecordid = $(".keyCode").attr("id");
			var agentrecordid = $(".setys").attr("id");
			//原来信息
			var rkeyCode = info.houseInfo.keyCode;
			//var ryspeopleid = "13150";
			var ysInfo = {
					shhId:shhId,
					keyCode:yscode,
					receipt:ysreceipt,
					reciverId:yspeopleid,
					reciver:yspeople,
					reciveTime:ystime,
			}
			var ysHasChange = {
					keyCode:rkeyCode,
					yspeopleid:ryspeopleid
			}
			var data = {
					shhId:shhId,
					recordid:agentrecordid,
					peopleid:yspeopleid,
					people:yspeople,
					ysrecordid:ysrecordid,
					ysInfo:ysInfo,
					type:"ys"
			}
			if(ysrecordid!=""){
				data.ysHasChange = ysHasChange;
			}
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updateAgent" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
			
		}
		//设置图片人
		function updateImg(){
			var shhId = '${houseId}';
			var imgrecordid = $(".setimg").attr("id");
			var imgpeopleid = $("#imgdepartmentcode").val();
			var imgpeople = $("#imgdepartment").val();
			<%-- $.post("<%=request.getContextPath()%>/house/updateImgPeople",{shhId:shhId,imgrecordid:imgrecordid,imgpeopleid:imgpeopleid},function(result){
				if(result.success){
            		alert("保存成功！");
            	}else{
            		alert(result.msg);
            	}
			}); --%>
			var data = {
					shhId:shhId,	
					recordid:imgrecordid,
					peopleid:imgpeopleid,
					people:imgpeople,
					type:"img"
			}
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updateAgent" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
		}
		
		//设置独家
		function updateDj(){
			var shhId = '${houseId}';
			var wtrecordid = $(".setdj").attr("id");
			var wtcode = $("input[name='djcode']").val();
			var wttime = $("input[name='djtime']").val();
			var wtpeopleid=$("#djdepartmentcode").val();
			var wtpeople = $("#djdepartment").val();
			var djmoney = $("input[name='djmoney']").val();
			var data = {
					shhId:shhId,	
					recordid:wtrecordid,
					code:wtcode,
					time:wttime,
					peopleid:wtpeopleid,
					people:wtpeople,
					djmoney:djmoney,
					type:"dj"
			}
			
			<%-- $.post("<%=request.getContextPath()%>/house/updateIntrust",{shhId:shhId,wtrecordid:wtrecordid},function(result){
				if(result.success){
            		alert("保存成功！");
            	}else{
            		alert(result.msg);
            	}
			}); --%>
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updateAgent" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
		}
		//设置委托
		function updateWt(){
			var shhId = '${houseId}';
			var wtrecordid = $(".setwt").attr("id");
			var wtcode = $("input[name='wtcode']").val();
			var wttime = $("input[name='wttime']").val();
			var wtpeopleid=$("#wtdepartmentcode").val();
			var wtpeople = $("#wtdepartment").val();
			var data = {
					shhId:shhId,	
					recordid:wtrecordid,
					code:wtcode,
					time:wttime,
					peopleid:wtpeopleid,
					people:wtpeople,
					type:"wt"
			}
			<%-- $.post("<%=request.getContextPath()%>/house/updateIntrust",{shhId:shhId,wtrecordid:wtrecordid},function(result){
				if(result.success){
            		alert("保存成功！");
            	}else{
            		alert(result.msg);
            	}
			}); --%>
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/updateAgent" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
		}
		
		//保存房源描述
		function saveHouseDescription(){
			var data = [];
			var shhId = '${houseId}';
			var inputs = $(".description-input");
			for(var i=0;i<inputs.length;i++){
				var id = $(inputs[i]).attr("id");
				var typeId = $(inputs[i]).attr("name");
				var typeName = $(inputs[i]).parent().prev().text();
				var description = $(inputs[i]).val();
				var d = {
						id:id,
						shhId:shhId,
						typeId:typeId,
						typeName:typeName,
						description:description
				}
				data.push(d);
			}
			
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/saveHouseDescription" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
			
		}
		
		//查看门牌
		function showHouseNo(){
			var shhId = '${houseId}';//房源主键ID
			$.post("<%=request.getContextPath()%>/house/queryHousePrivateInfo",{houseId:shhId,type:1},function(d){
				var buildingsName = d.data.buildingsName;
				var houseno = d.data.buildingHouseName;
				$(".houseno").text(buildingsName+houseno);
				
			})
		}
		
		//查看底价
		function showMinprice(){
			var shhId = '${houseId}';//房源主键ID
			$.post("<%=request.getContextPath()%>/house/queryHousePrivateInfo",{houseId:shhId,type:3},function(d){
				var minSellingPrice = d.data.minSellingPrice;
				var minRentPrice = d.data.minRentPrice;
				$(".minSellingPrice").text(minSellingPrice);
				$(".minRentPrice").text(minRentPrice);
				
			})
		}
		
		//写反馈
		function saveFeedback(){
			var shhId = '${houseId}';//房源主键ID
			if(info.houseInfo.lastShowedTime){
				var lastTime = info.houseInfo.lastShowedTime;
			}else if(info.houseInfo.lastAllShowedTime){
				var lastTime = info.houseInfo.lastAllShowedTime;
			}
			var date = new Date(lastTime);
			var lastTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
			var showedCnt = info.houseInfo.viewingCount;
			var content = $(".feedback-content").val();
			var id = $(".feedbackId").val();
			var data = {
					id:id,
					shhId:shhId,
					lastTime:lastTime,
					showedCnt:showedCnt,
					content:content
			}
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/house/saveAgentFeedback" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("保存成功！");
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	        });  
		}
		
		//查看销控
		function showHouseSell(){
			var communityId = info.houseInfo.communityId;
			var communityName = info.houseInfo.communityName;
			$(".buildlist").empty();
			$.post("<%=request.getContextPath()%>/house/getBuild",{communityId:communityId},function(d){
				for(var i=0;i<d.data.length;i++){
					var li = '<li tag="'+d.data[i].id+'" onclick="getUnit(this);">'+communityName+d.data[i].name+'</li>';
					$(".buildlist").append(li);
				}
				
				layer.open({
					type: 1,
					title:'查看销控',
					shade: [0],
					area: ['800px', '660px'],
					offset: 'auto', //弹出位置
					btnAlign: 'r',
					content:$('.houseSellModel')  //显示的url，no代表不显示滚动条
					
				});
			})
		}
		
		function getUnit(obj){
			var buildingId = $(obj).attr("tag");
			$(".unitlist").empty();
			$.post("<%=request.getContextPath()%>/house/getUnit",{buildingId:buildingId},function(d){
				for(var i=0;i<d.data.length;i++){
					var li = '<li tag="'+d.data[i].id+'" onclick="showBuildUnitSell(this);">'+d.data[i].name+'</li>';
					$(".unitlist").append(li);
				}
			})
		}
		
		function showBuildUnitSell(obj){
			var buildIngUnitId = $(obj).attr("tag");
			$.post("<%=request.getContextPath()%>/house/queryHouseListByUnitId",{buildIngUnitId:buildIngUnitId},function(d){
				
			})
		}
		
		
		
		</script>
		<script type="text/html" id="barOp" >
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="show">查看</a>
  		  
        </script>
			
		<script type="text/html" id="barFeedback" >
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="edit">修改</a>
  		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="del">删除</a>
        </script>
	</body>
	
	
	
	<!-- 房源开盘 -->
	<div class="houseOpenModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">租售类型</label>
			    <div class="layui-input-block ">
			      <select name="transactionType" lay-filter="transactionType">
			        <option value="1">出租</option>
			        <option value="2">出售</option>
			        <option value="3">租售</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">租价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="rentprice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">租价单位</label>
			    <div class="layui-input-block ">
			      <select name="rentpriceunit" lay-filter="rentpriceunit">
			        <option value="1">元/月</option>
			        <option value="2">元/季度</option>
			        <option value="3">元/年</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">售价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="sellprice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('houseopen');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	
	<!-- 变更状态 -->
	<div class="changestatusModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">原状态</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ystatus" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">现状态</label>
			    <div class="layui-input-block ">
			      <select name="xstatus" lay-filter="xstatus">
			        <option value="4">他租</option>
			        <option value="5">他售</option>
			        <option value="6">未知</option>
			         <option value="7">自住</option>
			        <option value="8">出租中</option>
			        <option value="9">空置</option>
			        <option value="10">撤单</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">变更理由</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="reason" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('changestatus');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 申请封盘 -->
	<div class="houseCloseModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">封盘人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="fpdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="fpdepartment" id="fpdepartment" class="layui-input">
			    </div>
			</div> -->
			<div class="layui-form-item">
				<label class="layui-form-label">封盘类型</label>
			    <div class="layui-input-block ">
			      <select name="fptype" lay-filter="fptype">
			        <option value="1">封路径</option>
			        <option value="2">封电话</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">到期日期</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="fptime" class="layui-input fptime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">封盘原因</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="fpreason" class="layui-textarea fpreason"></textarea>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('houseclose');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 改变用途 -->
	<div class="changeUsageModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">房屋用途</label>
			    <div class="layui-input-block ">
			      <select name="xusage" lay-filter="xusage">
			        <option value="house">住宅</option>
			        <option value="villa">别墅</option>
			        <option value="apartment">公寓</option>
			        <option value="land">土地</option>
			      </select>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeStatus('changeUsage');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 举报 -->
	<div class="tipoffModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">举报类型</label>
			    <div class="layui-input-block ">
			      <select name="tipoffType" lay-filter="tipoffType">
			        <option value="1">照片错误</option>
			        <option value="2">信息失败</option>
			      </select>
			    </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">举报原因</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="tipoffreason" class="layui-textarea tipoffreason"></textarea>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('tipoff');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出填写跟进 -->
	<div class="followModel" style="display:none;">
		<div class="layui-row">
			<div class="layui-col-md8">
				<form class="layui-form">
					<div class="layui-row">
						<div class="layui-col-md6">
							<div class="layui-form-item" >
							    <label class="layui-form-label">跟进方式</label>
							    <div class="layui-input-block">
							      <select name="followtype" lay-filter="followtype">
							        <option value="1">实勘</option>
							         <option value="2">谈委托</option>
							         <option value="3">店面面谈</option>
							          <option value="4">派单</option>
							      </select>
							    </div>
						  	</div>
						</div>
						<div class="layui-col-md6">
							<div class="layui-form-item">
							    <div class="layui-input-block ">
							      <input type="checkbox" name="" title="通知撤单" value="" class="cancelorder" filter="cancelorder">
							    </div>
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">跟进内容</label>
					    <div class="layui-input-block ">
					      <textarea placeholder="请输入内容" name="followcontent" class="layui-textarea followcontent"></textarea>
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒人</label>
					    <div class="layui-input-block ">
					      <input type="hidden" name=""  id="tsdepartmentcode" value="" class="layui-input">
					      <input type="text" name=""  id="tsdepartment" value="" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒时间</label>
					    <div class="layui-input-block ">
					      <input type="text" name=""  value="" class="layui-input remindtime">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒内容</label>
					    <div class="layui-input-block ">
					      <textarea placeholder="请输入内容" name="" class="layui-textarea remindcontent"></textarea>
					    </div>
					</div>
					<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveFollow();">保存</button>
					      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
					      
					    </div>
					 </div>
				</form>
			</div>
			<div class="layui-col-md4">
				二维码
			</div>
		</div>
		
	</div>
	<!-- 弹出设置全景 -->
	<div class="setOverallview" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">全景地址</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="" class="layui-textarea overallviewaddress"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="setOverallview();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出修改业主-->
	<div class="updateOwnerModel" style="display:none;">
		<div class="layui-form" style="padding:10px">
			<input type="hidden" name="ownerid">
			<div class="layui-form-item">
				<label class="layui-form-label">业主</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerName" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			      <select name="nameTitle" lay-filter="aihao">
			        <option value="1">先生</option>
				  	<option value="2">女士</option>
				  	<option value="3">公司</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateOwner();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出新增电话-->
	<div class="addphoneModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="addPhone();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出修改电话-->
	<div class="updatephoneModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="upownerphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="upownerremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updatePhone();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 变更业主-->
	<div class="changeOwnerModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">业主姓名</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newowner" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">业主性别</label>
			    <div class="layui-input-block">
			      <select name="newnameTitle" lay-filter="">
			        <option value="1">先生</option>
				  	<option value="2">女士</option>
				  	<option value="3">公司</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveNewOwner();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 修改低价-->
	<div class="updatepriceModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">出售低价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="minSellingPrice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出租低价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="minRentPrice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updatePrice();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--变更维护人-->
	<div class="updateOperatorModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">原维护人</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">现维护人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="whdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="xwh" id="whdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateWh();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置钥匙-->
	<div class="setysModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">拿钥匙时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ystime" class="layui-input ystime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">钥匙编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="yscode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">钥匙收据</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ysreceipt" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">得钥匙人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="ysdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" id="ysdepartment" name="yspeople" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateYs();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!--设置图片人-->
	<div class="setimgModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">图片人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="imgdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="imgpeople" id="imgdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateImg();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置独家-->
	<div class="setdjModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">独家编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djcode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">到期时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djtime" class="layui-input djtime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">独家总额</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djmoney" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">独家人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="djdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="djpeople" id="djdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateDj();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置委托-->
	<div class="setwtModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">委托编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="wtcode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">委托时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="wttime" class="layui-input wttime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">委托人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="wtdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="wtpeople" id="wtdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateWt();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--改价历史-->
	<div class="priceDetailModel" style="display:none;">
		<table id="pricedetail" lay-filter="" ></table>
	</div>
	
	<!--经纪人反馈-->
	<div class="feedbackModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<input type="hidden" class="feedbackId"/>
			<div class="layui-form-item">
				<label class="layui-form-label">反馈内容</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="" class="layui-textarea feedback-content"></textarea>
			    </div>
			</div>
			<div class="layui-form-item saveFeedback">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveFeedback();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--客户档案-->
	<div class="customerArchivesModel" style="display:none;">
		房源：
		<table id="customerHouse" lay-filter="" ></table>
		求租：
		<table id="customerDemandRent" lay-filter="" ></table>
		求购：
		<table id="customerDemandBuy" lay-filter="" ></table>
	</div>
	<!--上传房源图片-->
	<div class="uploadImgModel" style="display:none;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">图片类型</label>
			    <div class="layui-input-block">
			      <select name="pictureType" lay-filter="">
			        <option value="1">房型图</option>
				  	<option value="2">卧室</option>
				  	<option value="3">客厅</option>
				  	<option value="4">阳台</option>
			      </select>
			    </div>
			</div>
			<div>
				<button type="button" class="layui-btn" id="uploadimg">
				  <i class="layui-icon">&#xe67c;</i>选择图片
				</button>
				<input class="layui-upload-file" type="file" name="file" multiple="false">
				<button type="button" class="layui-btn houseimgSureUpload" >
				  <i class="layui-icon"></i>上传
				</button>
			</div>
		</div>
		
	</div>
	
	<!--上传房源视频-->
	<div class="uploadVideoModel" style="display:none;">
		<div class="layui-form">
			
			<div>
				<button type="button" class="layui-btn" id="uploadvideo">
				  <i class="layui-icon">&#xe67c;</i>选择视频
				</button>
				<input class="layui-upload-file" type="file" name="file" multiple="false">
				<button type="button" class="layui-btn housevideoSureUpload" >
				  <i class="layui-icon"></i>上传
				</button>
			</div>
		</div>
		
	</div>
	<!--房源描述-->
	<div class="descriptionModel" style="display:none;">
		<div class="layui-form description-form" style="padding:10px;">
		
			<div class="layui-form-item saveDescriptionBtn">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveHouseDescription();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--查看销控-->
	<div class="houseSellModel" style="display:none;">
		<!-- <div style="display:none;" class="unitinfo"></div> -->
		<div class="layui-row">
			<div class="layui-col-md3">
				<div>坐栋名称</div>
				<ul class="buildlist">
				
				</ul>
			</div>
			<div class="layui-col-md2">
				<div>单元名称名称</div>
				<ul class="unitlist">
				
				</ul>
			</div>
			<div class="layui-col-md7">
				
			</div>
		</div>
	</div>
</html>