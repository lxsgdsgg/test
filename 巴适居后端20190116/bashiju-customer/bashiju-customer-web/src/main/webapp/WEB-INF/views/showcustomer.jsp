﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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
		.layui-table-box{
			height: 300px;
			
		}
		.housetag{
			margin-left:10px;
			padding:4px;
			border:1px solid #e400ff;
		}
	</style>
	<script type="text/javascript">
	
	
	
		
	
			
			
			
			
	</script>
	<body class="">
			<div class="layui-container">
				<div class="layui-row">
					<div class="layui-col-md12 op-btn" style="border:1px solid #eee;">
						<div class="btn updatehouse-btn updatecustomerinfo" name="updateInfo" onclick="updateCustomerinfo();">修改信息</div>
						<div class="btn taobao" name="fromTaobao" onclick="businessExamine('fromtaobao');">淘宝</div>
						<div class="btn ">
							<div class="changestatus" name="changeStatus">变更状态</div>
							<div class="totaobao" onclick="changeStatus('toTaobao');">进入淘宝</div>
                        </div>
						<div class="btn sipang-btn" onclick="changeStatus('setPrivatedisc');">收为私客</div>
						<div class="btn sipang-btn" onclick="changeStatus('toPublicdisc');">放入公客</div>
						<div class="btn writefollow-btn">写跟进</div>
						<div class="btn writetip-btn">写提醒</div>
						<div class="btn writelook-btn">写带看</div>
						<div class="btn givedeposit">下定</div>
						<div class="btn fengpang-btn customerclose">申请封盘</div>
						<div class="btn prev-btn">上一条</div>
						<div class="btn next-btn">下一条</div>
						<div class="btn" onclick="exchangeDemand();">租售转换</div>
						<div class="btn bindKeyCode">发送绑定码</div>
					</div>
					
				</div>
				<div class="layui-row">
					<div class="layui-col-md12" style="border:1px solid #eee;">
						<div class="layui-tab" lay-filter="myTab">
							<ul class="layui-tab-title">
								<li class="icon-ex layui-this">基本信息</li>
								<li class="icon-ex matchHouse">匹配房源</li>
								<li class="icon-ex showfollowrecord">跟进记录</li>
								<li class="icon-ex houseLookRecord">带看记录</li>
								<li class="icon-ex">日志</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
									<div class="layui-row">
										<div class="layui-col-md7 houseinfo-left">
											<div class="houseinfo-box">
												<div class="layui-row general">
													<div class="layui-col-md8">
														<div class="rowdiv1">
															<div class="show-line"><span class="demandId"></span><span class="transactionType"></span><span class="houseUses"></span><span class="minPrice"></span>-<span class="maxPrice"></span>元<span class="minSpace"></span>-<span class="maxSpace"></span>平米</div>
															<div class="show-line housetag"></div>
														</div>
														<div class="rowdiv1">
															<span class="icon2 icon2-1 houseUses">住宅</span>
															<span class="icon2 icon2-3 status">有效</span>
															<span class="icon2 icon2-4 discStatus">公盘</span>
															<span class="icon2 icon2-5"><span class="levelType"></span>类客源</span>
															<span class="icon2 icon2-8 store" onclick="setStore();">收藏</span>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="">需求区域</span><span class="areaNames"></span><span class="regionNames"></span><span class="communityNames"></span></div>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="">需求价格</span><span class="minPrice"></span>-<span class="maxPrice"></span>元</div>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="">需求面积</span><span class="minSpace"></span>-<span class="maxSpace"></span>平米</div>
														</div>
														<div class="rowdiv1">
															<div class="show-line"><span class="">需求户型</span><span class="minRoom"></span>-<span class="maxRoom"></span>室</div>
														</div>
													</div>
													<div class="layui-col-md4">
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
																<div>房龄：<span class="elevatorroomcount"></span></div>
																<div>配套：<span class="matching"></span></div>
																<div>备注：<span class="remark"></span></div>
																<div>推荐标签：<span class="labeld"></span></div>
															</div>
															<div class="layui-col-md6">
																<div>朝向：<span class="orientation"></span></div>
																<div>装修：<span class="decoration"></span></div>
																<div>房屋类型：<span class="houseType"></span></div>
																<div>需求原因：<span class="reason"></span></div>
															</div>
														</div>
														<div class="layui-row detail-box detail-box2">
															<div class="layui-col-md6">
																<div>客户姓名：<span class="custName"></span></div>
																<div>来源：<span class="sourceType"></span></div>
																<div>消费理念：<span class="consumptIdeaName"></span></div>
																<div>国籍：<span class="censusRegister" id=""></span></div>
																<div>证件号码：<span class="IDCard"></span></div>
																<div>QQ：<span class="qqNum"></span></div>
																<div>交通工具：<span class="vehicle"></span></div>
															</div>
															<div class="layui-col-md6">
																<div>客户等级：<span class="levelType"></span></div>
																<div>沟通阶段：<span class="communicateStage"></span></div>
																<div>民族：<span class="nations"></span></div>
																<div>邮箱：<span class="email"></span></div>
																<div>微信：<span class="wxNum"></span></div>
																<div>车型：<span class="contactAddress"></span></div>
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
												<div><span>最近7天</span></div>
												<div><span class="browseNum">0</span><span class="">次浏览</span><span class="showNum" style="margin-left:10px;">0</span><span class="">次带看</span></div>
											</div>
											<div class="rowdiv2">
												<div class="layui-row ownerinfo">
													<div class="layui-col-md7">
														<div><span>客户：</span><span class="custName"></span><span class="relateTypeName"></span></div>
														<div>
															<div>电话：</div>
															<div class="phonelist"></div>
														</div>
														<div class="addphone" style="color:red;">新增电话</div>
													</div>
													<div class="layui-col-md5">
														<div class="showPricedetail">在线日历</div>
														<div class="houseLookPath">看房轨迹</div>
														<div class="showArchives">客户档案</div>
													</div>
												</div>
											</div>	
											<div class="rowdiv2">
												<div class="show-btn showphone-btn">查看电话</div>
											</div>
											
											<div class="rowdiv2" style="">
												相关经纪人
												<div class="relateagent-box">
													<div class="layui-row zr-row">
														<div class="layui-col-md2 typeName">录入人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setzr" id="" style="color:red;"></div>
													</div>
													
													<div class="layui-row wh-row">
														<div class="layui-col-md2 typeName">维护人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset updatewh" name="" id="" style="color:red;">变更维护人</div>
													</div>
													
													<div class="layui-row fp-row">
														<div class="layui-col-md2 typeName">封盘人</div>
														<div class="layui-col-md5 agentinfo"><div><span class="agentdeptname"></span><span class="realname" ></span><span></span></div><div>电话：<span class="agentphone"></span></div><div class="agenttime"></div></div>
														<div class="layui-col-md5 agentset setfp" id="" style="color:red;"></div>
													</div>
												</div>
											</div>
											
										</div>
										
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
										    <label class="layui-form-label">房龄</label>
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
									<table id="matchHouseTable" lay-filter="matchHouseTable" ></table>
								</div>
								<div class="layui-tab-item">
									<table id="followrecord" lay-filter="" ></table>
								</div>
								<div class="layui-tab-item">
									<table id="lookhouseRecord" lay-filter="lookhouseRecord" ></table>
								</div>
								<div class="layui-tab-item">
									
								</div>
								
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
		<script>
		
		
		function selectArea1(code,name){
			$("#selectArea").val(name);
			$("#selectAreacode").val(code);
		}
		function selectArea2(code,name){
			$("#matchArea").val(name);
			$("#matchAreacode").val(code);
		}
		$(function(){
		   //选区域
		   var areaList=JSON.parse('${areas}');
		   
		    //选房源的区域
			var a= new AreaObject(areaList,selectArea1,3,$("#selectArea"));	
			//匹配房源的区域
			var a= new AreaObject(areaList,selectArea2,3,$("#matchArea"));	
			
			
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
			
			//选定金经办人
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#trusteesdepartment").val(name);
					$("#trusteesdepartmentcode").val(code);
				}	
				ms.hidden();
			},6,$("#trusteesdepartment"),true); 
			
			//选门店
			var ms=new MultiSelection (depDatas,function(code,name,level){
				if(code!="0"){
					$("#mddepartment").val(name);
					$("#mddepartmentcode").val(code);
				}	
				ms.hidden();
			},null,$("#mddepartment"),true); 
			
			
			info=JSON.parse('${info}');
			
			 for(var key in info){
				 var data = info[key];
				 console.log(key);
				 console.log(data);
				 //基本信息
				 if(key=="customerInfo"){
					 for(var key2 in data){
						 
						 $("."+key2).text(data[key2]);
					     
					     //标签显示
					     
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
						
					    if(key2=='isStore' && data.isStore==1){//收藏
					    	$(".store").addClass("hasStore");
					    }
					 }
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
								
						 if(data[i].agentTypeName=='客源录入人'){
							//var div2 = '<div class="layui-col-md5 agentset"></div></div>';
							$(".zr-row").find(".agentset").addClass("setzr");
							$(".zr-row").find(".agentset").attr("id",id);
							$(".zr-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".zr-row").find(".agentinfo").find(".realname").text(realName);
							$(".zr-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".zr-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
						
					 	 if(data[i].agentTypeName=='客源维护人'){
					 		//var div2 = '<div class="layui-col-md5 updatewh" id="'+data[i].id+'" style="color:red;">变更维护人</div></div>';
					 		$(".wh-row").find(".agentset").addClass("updatewh");
					 		$(".wh-row").find(".agentset").attr("id",id);
					 		$(".wh-row").find(".agentset").attr("agentId",agentId);
					 		$(".wh-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".wh-row").find(".agentinfo").find(".realname").text(realName);
							$(".wh-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".wh-row").find(".agentinfo").find(".agenttime").text(addTime);
					 		
						 }
					 	 
					 	 if(data[i].agentTypeName=='客源封盘人'){
							//var div2 = '<div class="layui-col-md5 setwt" id="'+data[i].id+'" style="color:red;">设置委托</div></div>';
					 		$(".fp-row").find(".agentset").addClass("setfp");
					 		$(".fp-row").find(".agentset").attr("id",id);
					 		$(".fp-row").find(".agentinfo").find(".agentdeptname").text(deptname);
							$(".fp-row").find(".agentinfo").find(".realname").text(realName);
							$(".fp-row").find(".agentinfo").find(".agentphone").text(telPhone);
							$(".fp-row").find(".agentinfo").find(".agenttime").text(addTime);
						 }
								
						//$(".relateagent-box").append(div1+div2);
					 }
					
				 }
				 
				 //带看和浏览记录
				 //经纪人信息
				 if(key=="nums"){ 
					for(var key2 in data){
						 $("."+key2).text(data[key2]);
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
				 
			 } 
			
			
			//查看电话
			$(".showphone-btn").click(function(){
				var demandId = info.customerInfo.demandId;//需求编号ID
				$.post("<%=request.getContextPath()%>/customer/getCustomerPhone",{demandId:demandId},function(d){
					console.log(d.data);
					$(".phonelist").empty();
					 for(var i=0;i<d.data.length;i++){
						var div = '<div><span class="phone" style="margin-right:10px;">'+d.data[i].phone+'</span><span class="relateName" style="margin-right:10px;">'+d.data[i].remark+'</span><span tag="'+d.data[i].id+'" onclick="delPhone(this);" style="margin-right:10px;color:red;">删除</span><span class="updatephone" tag="'+d.data[i].id+'" style="margin-right:10px;color:red;">修改</span></div>';
						$(".phonelist").append(div);
					 }
					
					 openUpdatephone();
				})
			})
			
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
			 
			  
			 
			  var shhId = '${houseId}';//房源主键ID
			  //var houseId = info.houseInfo.houseId;//房源编号
			  //上传图片
			  upload.render({
				  elem: '#uploadimg'
				  ,url: '<%=request.getContextPath()%>/customer/uploadImg/'
				  ,auto: false //选择文件后不自动上传
				  ,bindAction: '.houseimgSureUpload' //指向一个按钮触发上传
				  ,accept:'images'
				  ,multiple:false
				  ,data:{lookId:$(".lookId").val()}
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    //lookId = $(".lookId").val();
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				     // console.log(index); //得到文件索引
				     // console.log(file); //得到文件对象
				     // console.log(result); //得到文件base64编码，比如图片
				      
				      //这里还可以做一些 append 文件列表 DOM 的操作
				      
				      //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
				      //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
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
			 
			  
			  $(".openHouseimgUpload").click(function(){
				  openModel("上传房源图片",'500px','460px',$(".uploadImgModel"))
			  })
			  
			  
			  $(".changestatus").click(function(){
				  var houseStatus = $(".status").text();
				  var param = {
						  houseStatus:houseStatus  
				  }
				 <%--  $.post("<%=request.getContextPath()%>/house/getHouseStatusType",{},function(){
					  
				  }) --%>
				  openModel("变更状态",'500px','260px',$(".changestatusModel"),param)
			  });
			  
			  $(".customerclose").click(function(){
				  openModel("申请封盘",'500px','460px',$(".customerCloseModel"))
			  });
			  
			  $(".givedeposit").click(function(){
				  $(".givedeposit").attr("flag","1");
				  $(".writelook-btn").removeAttr("flag");
				  $(".deposit-demandId").val(info.customerInfo.demandId);//需求id
				  openModel("下定金",'600px','580px',$(".depositModel"))
			  });
			  
			  $(".updateusage-btn").click(function(){
				  openModel("变更用途",'500px','260px',$(".changeUsageModel"))
			  });
			  
			  $(".writefollow").click(function(){
				  openModel("写跟进",'800px','700px',$(".followModel"))
			  });
			  
			  $(".writefollow-btn").click(function(){
				  openModel("写跟进",'800px','700px',$(".followModel"))
			  });
			  //房源匹配
			  $(".matchHouse").click(function(){
				 var transactionTypeId = info.customerInfo.transactionTypeId;
				 var conditions = {transactionTypeId:transactionTypeId};
				  var cols = [
					  	 {field:"status",title:"状态",width:'80'},
				 		 {field:"houseId",title:"房源编号",width:'100'},
				 		 {field:"communityName",title:"小区",width:'120'},
				 		 {field:"buildSpace",title:"面积",width:'100'},
				 		 {field:"room",title:"户型",width:'120'},
				 		 {field:"price",title:"价格",width:'100'},
				 		 {field:"orientation",title:"朝向",width:'120'},
				 		 {field:"floorCount",title:"楼层",width:'120'},
				 		 {field:"maintainer",title:"维护人",width:'120'},
				  		];
				  table.render({
				    elem: '#matchHouseTable'
				    ,height: 360
				    ,url:"<%=request.getContextPath()%>/customer/matchingHousing" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{conditions:JSON.stringify(conditions)}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  });
			  
			  $(".writelook-btn").click(function(){
				  $(".writelook-btn").attr("flag","1");
				  $(".givedeposit").removeAttr("flag");
				  
				  var cols = [
					     {checkbox: true},
				 		 {field:"houseId",title:"房源编号",width:'100'},
				 		 {field:"communityName",title:"小区",width:'120'},
				 		 {field:"buildingsName",title:"坐栋",width:'120'},
				 		 {field:"buildingHouseName",title:"门牌号",width:'120'},
				 		 {field:"buildSpace",title:"面积",width:'100'},
				 		 {field:"sellingPrice",title:"售价",width:'100'},
				 		 {field:"rentPrice",title:"租价",width:'100'},
				 		 {fixed: 'right', width:80, align:'center', toolbar: '#barDemo'}
				  		];
				  table.render({
				    elem: '#customerLookhouse'
				    ,height: 300
				    ,cols: [cols]
			  		,method:'post'
			  		,data:[]
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  openModel("写带看",'800px','600px',$(".lookHouseModel"))
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
			  
			 
			  
			  $(".updatewh").click(function(){
				 var realname =  $(this).prev().find(".realname").text();
				 var param = {
						 realname:realname
				  }
				  openModel("变更维护人",'500px','260px',$(".updateOperatorModel"),param)
			  });
			  
			  //选房
			  $(".selectHouse").click(function(){
				  
				  var cols = [
					     {checkbox: true},
				  		 {field:"transactionType",title:"类型",width:'80'},
				 		 {field:"status",title:"状态",width:'80'},
				 		 {field:"houseId",title:"房源编号",width:'100'},
				 		 {field:"communityName",title:"小区"},
				 		 {field:"buildingsName",title:"坐栋"},
				 		 {field:"buildingHouseName",title:"门牌号"},
				 		 {field:"owner",title:"业主姓名"},
				 		 {field:"room",title:"房型",templet: function(d){
				 	        return  d.room +"室"+d.hall+"厅"
				 	      }},
				 		 {field:"buildSpace",title:"面积"},
				 		 {field:"sellingPrice",title:"售价"},
				 		 {field:"rentPrice",title:"租价"},
				 		 {field:"maintainer",title:"维护人"},
				 		 {field:"entrustCode",title:"委托编号"},
				 		 {field:"mainterDept",title:"部门"}
				  		];
			  	 var conditions = {};
				  table.render({
				    elem: '#selectHouse'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/customer/customerFindHouseList" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{conditions:JSON.stringify(conditions)}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				openModel("选择房源",'650px','500px',$(".selectHouseModel"))
			  });
			  
			  table.on('checkbox(selectHouse)', function(obj){
				  if($(".givedeposit").attr("flag")==1){//下定选房源
					  var shhId = obj.data.id;
					  var houseId = obj.data.houseId;
					  $(".dshhId").val(shhId);
					  $(".dhouseId").val(houseId);
				  }else if($(".writelook-btn").attr("flag")==1){//带看选房源
					  var shhId = obj.data.id;
					  var houseId = obj.data.houseId;
					  var communityName = obj.data.communityName;
					  var buildingsName = obj.data.buildingsName;
					  var buildingHouseName = obj.data.buildingHouseName;
					  var buildSpace = obj.data.buildSpace;
					  var sellingPrice = obj.data.sellingPrice;
					  var rentPrice = obj.data.rentPrice;
				 		 
					  var oldData =  table.cache["customerLookhouse"]; 
					  var newData = {"shhId":shhId,"houseId":houseId,"communityName":communityName,"buildingsName":buildingsName,"buildingHouseName":buildingHouseName,"buildSpace":buildSpace,"sellingPrice":sellingPrice,"rentPrice":rentPrice};
					  oldData.push(newData);
					  table.reload('customerLookhouse', {
						  data:oldData
					 });
					  
					  
				  }
				  
				  
				});
			  
			  //监听删除
			 //删除选中了要填写带看的房源
			  table.on('tool(customerLookhouse)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'detail'){
				    	var oldData =  table.cache["customerLookhouse"];
				    	for(var i=0;i<oldData.length;i++){
				    		if(oldData[i].shhId == data.shhId){
				    			oldData.splice(i,1);
				    		}
				    	}
				    	 table.reload('customerLookhouse', {
							  data:oldData
						 });
				    }
				  });
			 
			 //保存带看
			  $(".saveLookhouse").click(function(){
				  var houseData = table.cache["customerLookhouse"];
				  //console.log(houseData);
				  var shhId = '';
				  var houseId = '';
				  var houseCount = houseData.length;
				  for(var i=0;i<houseData.length;i++){
					  /* var shhId = shhId==''?houseData[i].shhId:shhId+","+houseData[i].shhId;
					  var houseId = houseId==''?houseData[i].houseId:houseId+","+houseData[i].houseId; */
					  //业务改成只选一个房源了
					  var shhId = houseData[0].shhId;
					  var houseId = houseData[0].houseId; 
				  }
				  var content = $(".lookhousecontent").val();
				  var demandId = info.customerInfo.demandId;
				  var custName = info.customerInfo.custName;
				  var custCallType = info.customerInfo.relateTypeName;
				  var transactionTypeId = info.customerInfo.transactionTypeId;
				  var transactionType = info.customerInfo.transactionType;
				  var houseType = 2;//二手房
				  var data = {
						  houseType:houseType,
						  sourceId:shhId,
						  demandId:demandId,
						  custName:custName,
						  custCallType:custCallType,
						  transactionTypeId:transactionTypeId,
						  transactionType:transactionType,
						  content:content
				  }
				  if( $(".repeatlook").is(":checked")){
						data.isRepeat = 1;
					}
				  $.ajax({
			            type: "POST",//方法类型
			            dataType: "json",//预期服务器返回的数据类型
			            contentType: 'application/json',
			            data:JSON.stringify(data),
			            url: "<%=request.getContextPath()%>/customer/saveLookhouse" ,//url	                
			            success: function (result) {
			            	if(result.success){
			            		alert("成功！");
			            		$(".lookId").val(result.d);
			            		
			            	}else{
			            		alert(result.msg);
			            	}
			            },
			            error : function() {
			            	
			            },
			            beforeSend : function() {
			            	
			            }
			       }); 
				  
			  });
			  
			  $(".showArchives").click(function(){
				  var demandId = info.customerInfo.demandId;
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
				    ,url:"<%=request.getContextPath()%>/customer/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{demandId:demandId,type:'house'}
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
				    ,url:"<%=request.getContextPath()%>/customer/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{demandId:demandId,type:'rent'}
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
				    ,url:"<%=request.getContextPath()%>/customer/getArchives" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{demandId:demandId,type:'buy'}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
				  
				  openModel("客户档案",'650px','460px',$(".customerArchivesModel"));
			  });
			  
			  
			  laydate.render({
				    elem: '.remindtime' //指定元素
				  });
			  
			 
			  laydate.render({
				    elem: '.fptime' //指定元素
				  });
			  
			  laydate.render({
				    elem: '.deposittime' //指定元素
				  });
			  laydate.render({
				    elem: '.dealtime' //指定元素
				  });
			  
			  //查看带看
			  $(".houseLookRecord").click(function(){
				  var demandId = info.customerInfo.demandId;
				  var cols = [
					  	 {field:"img",title:"图片",width:'80'},
				  		 {field:"companyName",title:"公司",width:'120'},
				  		 {field:"houseId",title:"房源编号",width:'220'},
				 		 {field:"leader",title:"带看人",width:'120'},
				 		 {field:"deptname",title:"所在部门",width:'120'},
				 		 {field:"content",title:"带看内容",width:'200'},
				 		 {field:"addTime",title:"带看时间"},
				 		 {field:"visterEvaluate",title:"带看评价"},
				 		{fixed: 'right', width:80, align:'center', toolbar: '#barLook'}
				  		];
			  
				  table.render({
				    elem: '#lookhouseRecord'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/customer/getLookHouse" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,where:{demandId:demandId}
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  })
			  
			   //监听删除
			  //删除带看
			  table.on('tool(lookhouseRecord)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'detail'){
				    	var oldData =  table.cache["lookhouseRecord"];
				    	for(var i=0;i<oldData.length;i++){
				    		if(oldData[i].id == data.id){
				    			oldData.splice(i,1);
				    		}
				    	}
				    	 table.reload('lookhouseRecord', {
							  data:oldData
						 });
				    }
				    
				    $.post("<%=request.getContextPath()%>/customer/delLookhouse",{id:data.id,demandId:info.customerInfo.demandId},function(result){
				    	if(result.success){
		            		alert("成功！");
		            	}else{
		            		alert(result.msg);
		            	}
				    })
				    
				  });
			  
			//看房轨迹
			  $(".houseLookPath").click(function(){
				  var demandId = info.customerInfo.demandId;
				  var startTime = '2018-06-15';
				  var endTime = '2018-06-22';
				  var data = {
						  demandId:demandId, 
						  startTime:startTime,
						  endTime:endTime
				  };
				  
				  /* //选了部门
				  if(){
					  data.deptId = '' ;
				  }
				  //选了人
				  if(){
					  data.peopleId = '' ;
				  } */
				 
			    $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/customer/getLookHousePath" ,//url	                
		            success: function (result) {
		            	console.log(result.data);
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
			  })
			  
			  //查看客源跟进
			  $(".showfollowrecord").click(function(){
				  var demandId = info.customerInfo.demandId;
				  var cols = [
				  		 {field:"companyName",title:"公司",width:'120'},
				 		 {field:"follower",title:"跟进人",width:'120'},
				 		 {field:"deptname",title:"所在部门",width:'120'},
				 		 {field:"content",title:"跟进内容",width:'200'},
				 		 {field:"followTime",title:"跟进时间",width:'120'},
				 		{field:"followTypeName",title:"跟进方式"}
				  		];
			  
				  table.render({
				    elem: '#followrecord'
				    ,height: 500
				    ,url:"<%=request.getContextPath()%>/customer/getFollowRecord" //数据接口
				    ,page: true //开启分页
				    ,where:{demandId:demandId}
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			
			  		}
				  }); 
			  })
			  
			 
			  
			  
			  form.on('checkbox(cancelorder)', function(data){
				  if(data.elem.checked){//是否被选中，true或者false
					  $(".cancelorder").attr("checked",true);
				  }
				});  
			  
			//发送绑定码
			$(".bindKeyCode").click(function(){
				var demandId = info.customerInfo.demandId;
				$.post("<%=request.getContextPath()%>/customer/sendBindCode",{customerId:demandId},function(d){
					if(d.success){
	            		alert("成功！");
	            	}else{
	            		alert(d.msg);
	            	}
				})
			})
			  
		});
	//layui结束
		
		
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
			
			if(title=="变更维护人"){
				$("input[name='ywh']").val(param.realname);
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
		
		//租售转换
		function exchangeDemand(){
			var demandId = info.customerInfo.demandId;
			window.open("<%=request.getContextPath()%>/customer/exchangeDemand?demandId="+demandId+"&formName="+info.customerInfo.formName+"&type=exchange")
		}
		//更改信息
		function updateCustomerinfo(){
			var demandId = info.customerInfo.demandId;
			window.open("<%=request.getContextPath()%>/customer/exchangeDemand?demandId="+demandId+"&formName="+info.customerInfo.formName+"&type=update")
		}
		
		
		//变更审核
		function businessExamine(type){
			//var sourceCode = $(".houseId").text();
			var demandId = info.customerInfo.demandId;
			
			var examineInfo = {
					demandId:demandId
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
				
				 var remark = {
						 statusId:newStateId,
						 statusName:newStateName
				 }
				 examineInfo.remark = remark;
				 examineInfo.content = reason;
				 
			}
			
			if(type=="customerclose"){
				//examineInfo.auditTtypeId=6;
				//examineInfo.auditTtypeName='房源封盘';
				
				//var peopleid = $("#fpdepartmentcode").val();
				//var peoplename = $("#fpdepartment").val();
				//examineInfo.applicantId = peopleid;
				
				var fptype = $("input[name='fptype']").val();
				var fptime = $(".fptime").val();
				var remark = {
						closeCustomerType:fptype,
						closeCustomerEndTime:fptime
				}
				examineInfo.remark = remark;
				var reason = $(".fpreason").val();
				examineInfo.content = reason;
				
			}
			 
			
			if(type=='fromtaobao'){
				var mainterRecordid = $(".updatewh").attr("id");
				var remark = {
					mainterRecordid:mainterRecordid,
			   }
			   examineInfo.remark = remark;
			   examineInfo.content = '淘出';
			}
			var transactionTypeId = info.customerInfo.transactionTypeId;
			var data = {
					type:type,
					transactionTypeId:transactionTypeId,
					examineInfo:examineInfo
			}
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/customer/businessExamine" ,//url	                
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
			var id = info.customerInfo.demandId;
			//进淘宝池
			if(type=="toTaobao"){
				var statusInfo = {
						id:id,
				}
			}
			//放入公客
			if(type=="toPublicdisc"){
				var statusInfo = {
						id:id,
				}
			}
			//收为私客
			if(type=="setPrivatedisc"){
				var recordid = $(".updatewh").attr("id");
				var statusInfo = {
						id:id,
						recordid:recordid
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
	            url: "<%=request.getContextPath()%>/customer/changeSomeStatus" ,//url	                
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
		
		//保存定金
		function saveDeposit(){
			//var depositInfo = decodeURIComponent($(".deposit-form").serialize());
			var data = {}
			var inputs = $(".deposit-form").find("input");
			for(var i=0;i<inputs.length;i++){
				var name = $(inputs[i]).attr("name");
				var value = $(inputs[i]).val();
				data[name]=value;
			}
			var remark = $(".dremark").val();
			data.remark = remark;
			//alert(depositInfo);
			<%-- $.post("<%=request.getContextPath()%>/customer/saveDeposit",{depositInfo:depositInfo},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			}) --%>
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/customer/saveDeposit" ,//url	                
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
		
		//设置和取消收藏
		function setStore(){
			var demandId = info.customerInfo.demandId;
			if($(".store").hasClass("hasStore")){
				$(".store").removeClass("hasStore");
				var status = 0;
			}else{
				$(".store").addClass("hasStore");
				var status = 1;
			}
			$.post("<%=request.getContextPath()%>/customer/updateStore",{demandId:demandId,status:status},function(d){
				if(d.success){
					 alert("成功！");
				 }else{
					 alert(data.msg); 
				 }
			})
		}
		
		
		//保存跟进
		function saveFollow(){
			var demandId = info.customerInfo.demandId;
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
					demandId:demandId,
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
	            url: "<%=request.getContextPath()%>/customer/saveCustomerFollowAndRemind" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		if( $(".cancelorder").is(":checked")){
		            		var agentId = $(".updatewh").attr("agentId");
							var content = $(".followcontent").val();
							alert(agentId);
							alert(content);
							$.post("/im/user/imuser/insideInformation",{"receiveuser":agentId,"msgContent":content},function(d){
								alert(d);
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
		
		
		//新增电话
		function addPhone(){
			var demandId = info.customerInfo.demandId;
			var ownerphone = $("input[name='ownerphone']").val();
			var ownerremark = $("input[name='ownerremark']").val();
			var data={
				demandId:demandId,
				phone:ownerphone,
				remark:ownerremark
			}
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/customer/saveCustomerPhone" ,//url	                
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
		
		//删除电话
		function delPhone(obj){
			var phonerecordid = $(obj).attr("tag");
			$.post("<%=request.getContextPath()%>/customer/delCustomerPhone",{id:phonerecordid},function(d){
				if(d.success){
            		alert("成功！");
            	}else{
            		alert(d.msg);
            	}
			});
		}
		//修改电话
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
		            url: "<%=request.getContextPath()%>/customer/updateCustomerPhone" ,//url	                
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
			var demandId = info.customerInfo.demandId;
			var yrecordid = $(".updatewh").attr("id");
			var xwhid = $("#whdepartmentcode").val();
			var xwh = $("#whdepartment").val();
			var data = {
					demandId:demandId,
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
	            url: "<%=request.getContextPath()%>/customer/updateAgent" ,//url	                
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
		
		</script>
		<script type="text/html" id="barDemo" >
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="detail">删除</a>
  
        </script>
        <script type="text/html" id="barLook" >
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="detail">删除</a>
  
        </script>
				
	</body>
	
	
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
			        <option value="6">暂缓</option>
			        <option value="1">有效</option>
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
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="businessExamine('changestatus');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 申请封盘 -->
	<div class="customerCloseModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			
			<div class="layui-form-item">
				<label class="layui-form-label">封盘类型</label>
			    <div class="layui-input-block ">
			      <input type="text" name="fptype" class="layui-input" value="1">
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
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="businessExamine('customerclose');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	
	<!-- 写带看 -->
	<div class="lookHouseModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<div><span>带看房源</span><span style="color:red;margin-left:10px;" class="selectHouse">选择二手房源</span><span style="color:red;margin-left:10px;">选择一手房源</span></div>
			<div class="openHouseimgUpload show-line">上传图片</div> <div class="layui-form-item show-line"><div class="layui-input-block"><input type="checkbox" title="复看" class="repeatlook layui-input"></div></div>
			<input type="hidden" class="lookId" name="" value="">
			<table id="customerLookhouse" lay-filter="customerLookhouse" ></table>
			<div class="layui-form-item">
				<label class="layui-form-label">客户评价</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="lookhousecontent" class="layui-textarea lookhousecontent"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn saveLookhouse"  lay-filter="*" >保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!--下定金 -->
	<div class="depositModel" style="display:none;">
		<form class="layui-form deposit-form" style="padding:10px;">
			<div class="layui-row">
				<div class="layui-col-md5">
					<div>客源编号：<span class="demandId"></span></div>
					<input type="hidden" class="deposit-demandId" name="demandId" value="">
					<div>客户姓名：<span class="custName"></span><span class="relateTypeName"></span></div>
					<div style="color:red;" class="selectHouse">选择房源</div>
					<input type="text" class="dshhId" name="shhId" value="">
					<input type="text" class="dhouseId" name="houseId" value="">
				</div>
				<div class="layui-col-md7">
					<div class="layui-form-item">
						<label class="layui-form-label">下定金额</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入内容" name="price" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">下定日期</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入内容" name="entrustTime" class="layui-input deposittime">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">经办人</label>
					    <div class="layui-input-block ">
					      <input type="hidden"  name="trusteesId" id="trusteesdepartmentcode" class="layui-input" value="">
					      <input type="text" placeholder="请输入内容" name="trustees" id="trusteesdepartment" value="" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">票据号</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入内容" name="billNo" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">预计成交金额</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入内容" name="maybeDealPrice" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">预计成交日期</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入内容" name="maybeDealDate" class="layui-input dealtime">
					    </div>
					</div>
				</div>
				
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="remark" class="layui-textarea dremark"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveDeposit();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</form>
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
							      <input type="checkbox" name="" title="通知撤单" value="" class="cancelorder" lay-filter="cancelorder">
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
	
	
	
	
	
	<!--选择房源-->
	<div class="selectHouseModel" style="display:none;">
		<div class="layui-form">
			<div class="layui-form-item show-line">
			    <div class="layui-input-block ">
			      <input type="hidden" id="mddepartmentcode" class="layui-input">
			      <input type="text" placeholder="门店" name="xwh" id="mddepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
				<label class="layui-form-label">面积区间</label>
			    <div class="layui-input-block ">
			      <select name="pictureType" lay-filter="">
			        <option value="1">200-500</option>
				  	<option value="2">500-1000</option>
				  	<option value="3">1000-1500</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block ">
			      <input type="hidden" id="selectAreacode" class="layui-input">
			      <input type="text" placeholder="选择区域" id="selectArea" name="" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <input type="text" placeholder="坐栋" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <input type="text" placeholder="房源编号，电话，门牌号，委托编号" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="">查询</button>
			      
			    </div>
			</div>
		</div>
		<table id="selectHouse" lay-filter="selectHouse" ></table>
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
	<!--上传看房图片-->
	<div class="uploadImgModel" style="display:none;">
		<div class="layui-form">
			
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
</html>