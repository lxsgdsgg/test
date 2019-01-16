<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
	<head>
	<meta charset="UTF-8">
	<title>地图找房管理</title>
	<%@include file="/common/common.jsp" %>
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/bootstrap-3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/datatables/media/css/dataTables.bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/easyform/easyform.css" >
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" >
	<style type="text/css">
		.checkboxDiv{
			margin:20px;
		}
		.checkboxDiv ul{
			min-height:150px;
		}
		.checkboxDiv li{
			min-width:120px;
			
		}
		.input-xs {
		    height: 24px;
		    padding: 2px 5px;
		    font-size: 12px;
		    line-height: 1.5;
		    border-radius: 3px;
		    float:left;
		}
		ul.list-inline{
			margin-top:6px;
		}
		.list-inline li{
			font-size: 12px;
		}
		.list-inline>li {
		    display: inline-block;
		    padding-right: 2px;
		    padding-left: 2px;
		}
		.operator {
			margin-top:10px;
			border: 1px solid transparent;
			border-color:#ccc;
			border-left: 0;
			border-right: 0;
		}
		.operator .btn{
			width:100px;
			border-radius: 0px;
			border-top:0;
			border-bottom:0;
		}
		
		.tooltip {
		margin-top:-4px;
		    position: relative;
		    z-index: 1070;
		    display:inline-block;
		    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		    font-size: 12px;
		    font-style: normal;
		    font-weight: 400;
		    line-height: 1.42857143;
		    text-align: left;
		    text-align: start;
		    text-decoration: none;
		    text-shadow: none;
		    text-transform: none;
		    letter-spacing: normal;
		    word-break: normal;
		    word-spacing: normal;
		    word-wrap: normal;
		    white-space: normal;
		    filter: alpha(opacity=0);
		    opacity: 1; 
		    line-break: auto;
		}
		.tooltip.right .tooltip-arrow {
		    top: 50%;
		    left: 0;
		    margin-top: -5px;
		    border-width: 5px 5px 5px 0;
		    border-right-color: #1E9FFF;
		}
		.tooltip-inner {
		    max-width: 500px;
		    padding: 1px 8px;
		    color:#333;
		    text-align: center;
		    background-color: #fff;
		    border-radius: 4px;
		    border: 1px solid #1E9FFF;
		}
		
		.activeStaus {
			color:red !important;
		}
		
		.checkbox input[type=checkbox] , .multiCheckbox input[type=checkbox]{
		    position: absolute !important;
		    margin-left: -16px !important;
		}
		.checkbox label{
			min-width:50px;
		}
		
		.multiCheckbox li{
			padding-left:20px;
		}
		.input-group-addon{
			padding:0;
		}
		.lower,.upper{
			width:80px !important;
			height:30px;
			
		}
		.lower{
			margin-left:10px;	
		}
		.upper{
			margin-right:10px;
		}
		.rangeInput {
			width:120px;
		}
		.rangeDiv{
			display: inline-block;
			float:left;
		}
		.rangeDiv .dropdown-menu{
			min-width:220px;
		}
		.rangeDefault{
			color:#c2c2c2;
		}
		.queryData{
			display:inline-block;
			width:150px !important;
		}
		.queryItem li a{
			display:inline;
			line-height: 2;
		}
		.jiantou{
			float:right;
			padding-right:10px;
		}
		.queryCondition{
			border: 1px solid #ccc;
		    border-radius: 3px;  
		}
		.queryInput{ 
			border:0;
			height:22px;
		}
		.childItem{
			border: 1px solid #fff;
		    border-radius: 10px;
		    background-color: #d2d2d2;
		    padding: 1px 5px;
		    float: left;
		    font-size:12px;
		   	color:#444;
		}
		.closeItem {
		    float: right;
		    font-size: 18px;
		    font-weight: 700;
		    line-height: 1;
		    color: red;
		    text-shadow: 0 1px 0 #fff;
		    filter: alpha(opacity=20);
		    opacity: .5;
		    padding-left: 5px;
		}
		.closeItem:hover{
			color: #333;
			cursor: pointer;
		}
		#xiaoqu{
			border: 1px solid #ccc;
		    border-radius: 3px; 
		    height:24px;
		    font-size: 12px;
		    color: #c2c2c2;
		    float:left;
		    min-width:300px;
		}
		.more li{
			width:400px;
			margin:6px;
			float:left;
		}
		.more li select{
			margin-left:10px;
		}
		.moreItem{
			float:none !important;
		}
		.moreDate{
			width: 120px !important;
		    float: none;
		}
		.form-horizontal .form-group {
		    margin-right: 0px; 
		    margin-left: 0px; 
		}
		.bitian{
			color:red;
			margin-right:2px;
		}
		.suffix{
			color:#ccc;
			border:1px solid #ccc;
			border-left:0;
			float: left;
			height: 24px;
		    padding-top: 1px;
		}
		.prefix{
			border-right:0;
			float: left;
		}
		#addDetailForm input, #addDetailForm select{
			height: 24px !important;
			padding:0px !important;
		}
		
		#addDetailForm label{
			padding-top:2px;
			font-size:12px;
			font-weight: 500;
		}
		#addDetailForm label.check{
			float:left;
			margin-left:20px;
			cursor: pointer;
			font-size:12px;
			font-weight: 500;
			padding-top:2px;
		}
		#addDetailForm label input[type=checkbox]{
			position: absolute;
		    margin: 0 -15px;
		}
		.form-group {
		    margin-bottom: 10px;
		}
		.custom-inline li{
			display: inline-block;
		    padding-right: 10px;
		    padding-left: 10px;
		}
		.custom-inline li input{
			margin-left: 10px;
		}
		#showHouses tbody tr :hover{
			background-color: #01AAED;
			cursor: pointer;
		}
		.ulItem{
			margin-top:0px;
		}
		
		
		label {
		    display: inline-block;
		    max-width: 200px;
		    margin-bottom: 5px;
		    font-weight: 700;
		}
	</style>
	
	</head>
	<body>
		<div>
			<div class="form-inline" style="margin-top:10px;margin-left:10px;">
				<div id="search1" class="form-group">
					<div id="xiaoqu" name="xiaoqu">
						<span id="xiaoquTitle">选择行政区、片区、小区(可多选)</span>
					</div>
					<div id="priceRange" class="dropdown rangeDiv">
						<div class="input-group rangeInput">
							<div class="form-control input-xs input rangeDefault">价格区间</div>
							<div class="input-group-addon">▼</div>
						</div>
						<ul class="dropdown-menu">
							<li><input type="number" class="form-control lower"
								placeholder="0" name="priceRangeLower"> —— <input type="number"
								class="form-control upper" placeholder="不限" name="priceRangeUpper"></li>
							<li class="divider"></li>
						</ul>
					</div>
	
					<div id="areaRange" class="dropdown rangeDiv">
						<div class="input-group rangeInput">
							<div class="form-control input-xs input rangeDefault">面积区间</div>
							<div class="input-group-addon">▼</div>
						</div>
						<ul class="dropdown-menu">
							<li><input type="number" class="form-control lower"
								placeholder="0" name="areaRangeLower"> —— <input type="number"
								class="form-control upper" placeholder="不限" name="areaRangeUpper"></li>
							<li class="divider"></li>
						</ul>
					</div>
				</div>
				<button id="queryBtn" type="button" onclick="searchData()" class="btn btn-primary btn-xs">搜索</button>
			</div>
			<div class="form-inline" id="searchCom" style="margin-left: 10px;">
				<ul id="statusUI" class="list-inline">
					<li><span style="color:gray;">状态</span></li>
					<li><a data-name="status" data-value="" href="#" class="activeStaus" style="color: #7BAAF0;">不限</a></li>
					<li><a data-name="status" data-value="1" href="#" style="color: #7BAAF0;">有效出售</a></li>
					<li><a data-name="status" data-value="2" href="#" style="color: #7BAAF0;">有效出租</a></li>
					<li><a data-name="status" data-value="3" href="#" style="color: #7BAAF0;">我租</a></li>
					<li><a data-name="status" data-value="4" href="#" style="color: #7BAAF0;">我售</a></li>
					<li><a data-name="status" data-value="5" href="#" style="color: #7BAAF0;">失效</a></li>
				</ul>
				
				<ul id="effectUI" class="list-inline">
					<li><span style="color: gray;">用途</span></li>
					<li><a data-name="effect" data-value="" href="#" class="activeStaus" style="color: #7BAAF0;">不限</a></li>
					<li><a data-name="effect" data-value="house" style="color: #7BAAF0;" href="#">住宅</a></li>
					<li><a data-name="effect" data-value="villa" style="color: #7BAAF0;" href="#" >别墅</a></li>
					<li><a data-name="effect" data-value="apartment" style="color: #7BAAF0;" href="#">公寓</a></li>
					<li><a data-name="effect" data-value="shops" href="#" style="color: #7BAAF0;">商铺</a></li>
					<li><a data-name="effect" data-value="officeBuiling" href="#" style="color: #7BAAF0;">写字楼</a></li>
					<li><a data-name="effect" data-value="wareHouse" href="#" style="color: #7BAAF0;">仓库</a></li>
					<li><a data-name="effect" data-value="factory" href="#" style="color: #7BAAF0;">厂房</a></li>
					<li><a data-name="effect" data-value="parking" href="#" style="color: #7BAAF0;">车位</a></li>
					<li><a data-name="effect" data-value="land" href="#" style="color: #7BAAF0;">土地</a></li>
				</ul>
			</div>
		</div>
		<!-- 地图区域 -->
		<div style="margin-top: 20px;">
			<div id="mapArea" style="height:750px;width:100%;"></div>
		</div>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=Tg1lQwb6XKbE8wgAflqsNimxI3myaCzD"></script>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/frame/bootstrap-3.3.7/js/bootstrap.min.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/frame/easyform/easyform.js"></script>  
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/frame/datatables/media/js/jquery.dataTables.min.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/frame/datatables/media/js/dataTables.bootstrap.min.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script> 
		
		<script type="text/javascript">
			$(function () {
				var priceDataRange={};
				priceDataRange.lower=[0,150,175,200,250,300,350,450];
				priceDataRange.upper=[200,300,400,500,600,700,800];
				var areaDataRange={};
				areaDataRange.lower=[0,50,60,70,80,100,120,140];
				areaDataRange.upper=[70,90,110,130,150,170,190];
				rangeBind("priceRange","价格","万元",priceDataRange);
				rangeBind("areaRange","面积","平米",areaDataRange);
				
				$(".queryCondition .queryInput").on("keyup",function(){
					$(".queryCondition .queryItem").removeClass("show");
					if($(this).val()!=""){
						$(this).next().find(".queryData").text("\""+$(this).val()+"\"");
						$(this).next().addClass("show");
					}else{
						$(this).next().removeClass("show");
					}
					
				});
				
				$(".queryCondition .queryInput").on("focus",function(){
					$(".queryCondition .queryItem").removeClass("show");
					if($(this).parent().find(".childItem").length>0){
						$(this).next().find(".queryData").text("\""+$(this).parent().find(".childItem").attr("dataValue")+"\"");
						$(this).next().addClass("show");
					}		
				});
			});
			
			$(".queryItem li").on("click",function(){
				if($(this).parent().parent().children().first().is("span")){
					$(this).parent().parent().children().first().remove();
				}
				var val=$(this).find(".queryData").text().replace(new RegExp('"',"gm"),'');
				var item='<span dataType="'+$(this).attr("dataParam")+'" dataValue="'+val+'" class="childItem">'+$(this).attr("dataName")+val+'<span class="closeItem">x</span></span>';
				$(this).parent().parent().prepend(item);
				$(this).parent().prev().val(" ");
				var width=$(this).parent().prev().css("width").replace("px","");
				width=$(this).parent().parent().children().first().css("width").replace("px","");
				$(this).parent().prev().css("width","20px");
				$(this).parent().removeClass("show");
				$(this).parent().parent().children().first().find(".closeItem").on("click",function(){
					if($(this).parent().attr("dataType")=='1'){
						building={};
					}
					if($(this).parent().attr("dataType")=='2'){
						vagueData={};
					}
					$(this).parent().next().css("width","100px");
					$(this).parent().next().val("");
					$(this).parent().parent().children().first().remove();
					
				});
				if($(this).attr("dataParam")='1'){
					building.type=$(this).attr("dataType");
					building.value=val;
				} 
				if($(this).attr("dataParam")='2'){
					vagueData.type=$(this).attr("dataType");
					vagueData.value=val;
				}
			});
			
			var dataParam={};//后台搜索参数
			var dataAreas=[];//区域、小区、片区
			var status = {};//房源状态
			var effect = {};//房源用途
			var map;
			//小区查询控件初始化
			var communitList=JSON.parse('${communitList}');
			var m=new MultiSelection (communitList,function(code,name,level){
			  	for(var i=0;i<dataAreas.length;i++){
			  		if(dataAreas[i].type==level&&dataAreas[i].value==code){
			  			return ;
			  		}
			  	}
				var item='<span data-value="'+code+'" data-type="'+level+'"  class="childItem">'+name+'<span class="closeItem">x</span></span>';
				$("#xiaoquTitle").hide();
				$("#xiaoqu").append(item);
				$("#xiaoqu .closeItem").on("click",function(event){
					event.stopPropagation();
					$(this).parent().remove();
					if($("#xiaoqu .childItem").length==0){
						$("#xiaoquTitle").show();
					}
					var index;
					for(var i=0;i<dataAreas.length;i++){
				  		if(dataAreas[i].type==$(this).parent().attr("data-type")&&dataAreas[i].value==$(this).parent().attr("data-value")){
				  			index=i;
				  		}
				  	}
					dataAreas.splice(index,1);
				});
				dataAreas.push({"value":code,"type":level});
			},3,$("#xiaoqu"));
			
			//区间绑定
			function rangeBind(id,title,unit,datas){
				$("body").on("click",function(event){
					if($(event.target).parent().attr("class")!=null){
						if($(event.target).parent().attr("class").indexOf("data")>-1){
							return;
						}
						if($(event.target).parent().attr("class").indexOf("rangeInput")>-1){
							return;
						}
					}
					
					if($(event.target).attr("class")!=null){
						if($(event.target).attr("class").indexOf("lower")>-1){
							return;
						}
						if($(event.target).attr("class").indexOf("upper")>-1){
							return;
						}
					}
					$("#"+id+" ul.dropdown-menu").removeClass("show");
				});
				$("#"+id+" .rangeInput").on("click",function(){
					$("ul.dropdown-menu").removeClass("show");
					$(this).next().toggleClass("show");
					var rangeValue=$("#"+id+" .rangeInput .input").text();
					if(rangeValue.indexOf("以上")>0){
						$("#"+id+" .lower").val(rangeValue.replace(unit+"以上",""));
						$("#"+id+" .upper").val("");
					}else if(rangeValue.indexOf("以下")>0){
						$("#"+id+" .upper").val(rangeValue.replace(unit+"以下",""));
						$("#"+id+" .lower").val("");
					}else{
						var range=rangeValue.replace(unit,"").split("-");
						$("#"+id+" .upper").val(range[1]);
						$("#"+id+" .lower").val(range[0]);
					}
					$("#"+id+" .lower").focus();
				});
				
				$("#"+id+" .lower").on("focus",function(){
					$("#"+id+" ul.dropdown-menu>li").remove(".data");
					for(var i=0;i<datas.lower.length;i++){
						$("#"+id+" ul.dropdown-menu").append('<li class="data"><a href="#">'+datas.lower[i]+'</a></li>');
					}
					$("#"+id+" ul.dropdown-menu>li.data").on("click",function(){
						var upper=$("#"+id+" .upper").val();
						var lower=$(this).find("a").text();
						if(upper==""){
							if(lower==0){
								$("#"+id+" .rangeInput .input").text(title+"区间");
								$("#"+id+" .rangeInput .input").addClass("rangeDefault");
							}else{
								$("#"+id+" .rangeInput .input").text(lower+unit+"以上");
								$("#"+id+" .rangeInput .input").removeClass("rangeDefault");
							}				
						}else {
							if(parseInt(lower)>parseInt(upper)){
								lower=parseInt(upper)+"-"+parseInt(lower)+unit;
							}else{
								if(lower==0){
									lower=parseInt(upper)+unit+"以下";
								}else{
									lower=parseInt(lower)+"-"+parseInt(upper)+unit;
								}
							}
							$("#"+id+" .rangeInput .input").text(lower);
						}
						if($(this).find("a").text()==0){
							$("#"+id+" .lower").val("");
						}else{
							$("#"+id+" .lower").val($(this).find("a").text());
						}
						$("#"+id+" .upper").focus();
					});
				});
				
				
				$("#"+id+" .upper").on("focus",function(){
					$("#"+id+" ul.dropdown-menu>li").remove(".data");
					for(var i=0;i<datas.upper.length;i++){
						if(datas.upper[i]>$("#"+id+" .lower").val()){
							$("#"+id+" ul.dropdown-menu").append('<li class="data text-right"><a href="#">'+datas.upper[i]+'</a></li>');
						}			
					}
					$("#"+id+" ul.dropdown-menu").append('<li class="data text-right"><a href="#">不限</a></li>');
					
					$("#"+id+" ul.dropdown-menu>li.data").on("click",function(){
						var lower=$("#"+id+" .lower").val();
						if(lower==""){
							if($(this).find("a").text()!="不限"){
								$("#"+id+" .rangeInput .input").text($(this).find("a").text()+unit+"以下");
								$("#"+id+" .rangeInput .input").removeClass("rangeDefault");
							}else{
								$("#"+id+" .rangeInput .input").text(title+"区间");
								$("#"+id+" .rangeInput .input").addClass("rangeDefault");
							}			
						}else{
							var upper=$(this).find("a").text();
							if(upper!="不限"){
								if(parseInt(upper)<parseInt(lower)){
									lower=parseInt(upper)+"-"+parseInt(lower)+unit;
								}else{
									lower=parseInt(lower)+"-"+parseInt(upper)+unit;
								}
								$("#"+id+" .rangeInput .input").text(lower);
							}else{				
								$("#"+id+" .rangeInput .input").text(lower+unit+"以上");
							}
						} 
						if($(this).find("a").text()!="不限"){
							$("#"+id+" .upper").val($(this).find("a").text());
						}else{
							$("#"+id+" .upper").val("");
						}
						$(this).parent().toggleClass("show");
					});
				});
				
				
				$("#"+id+" .lower").on("keyup",function(event){
					if(event.keyCode==13){
						$("#"+id+" .upper").focus();
					}else{
						var upper=$("#"+id+" .upper").val();
						var lower=$(this).val();
						if(lower==0){
							$(this).val("");
						}
						if(upper==""){
							if($(this).val()==""){
								$("#"+id+" .rangeInput .input").text(title+"区间");
								$("#"+id+" .rangeInput .input").addClass("rangeDefault");
							}else{
								$("#"+id+" .rangeInput .input").text(parseInt($(this).val())+unit+"以上");
								$("#"+id+" .rangeInput .input").removeClass("rangeDefault");
							}				
						}else {
							if(lower==""||lower=="0"){
								lower=parseInt(upper)+unit+"以下";
							}else{
								if(parseInt(lower)>parseInt(upper)){
									lower=parseInt(upper)+"-"+parseInt(lower)+unit;
								}else{
									lower=parseInt(lower)+"-"+parseInt(upper)+unit;
								}
							}								
							$("#"+id+" .rangeInput .input").text(lower);
						}
					}
				});
				
				$("#"+id+" .upper").on("keyup",function(event){
					if(event.keyCode==13){
						$(this).parent().parent().toggleClass("show");
					}else{
						var lower=$("#"+id+" .lower").val();
						var upper=$(this).val();
						if(upper==0){
							$(this).val("");
						}
						if(lower==""){
							if($(this).val()==""){
								$("#"+id+" .rangeInput .input").text(title+"区间");
								$("#"+id+" .rangeInput .input").addClass("rangeDefault");
							}else{
								$("#"+id+" .rangeInput .input").removeClass("rangeDefault");
								$("#"+id+" .rangeInput .input").text(parseInt($(this).val())+unit+"以下");
							}				
						}else {
							if($(this).val()==""){
								lower=parseInt(lower)+unit+"以上";
							}else{
								if(parseInt(lower)>parseInt(upper)){
									lower=parseInt(upper)+"-"+parseInt(lower)+unit;
								}else{
									lower=parseInt(lower)+"-"+parseInt(upper)+unit;
								}
							}								
							$("#"+id+" .rangeInput .input").text(lower);
						}
					}
				});
			}
			
			var secondLevelHsInfo = JSON.parse('${secondLevelHsInfo}');
			var thirdLevelHsInfo = JSON.parse('${thirdLevelHsInfo}');
			var communityHsInfo = JSON.parse('${communityHsInfo}');
			layui.use(['layer','form'],function(){
				var form = layui.form;
				var $ = layui.$;
				showMap();
				$("#statusUI>li>a").on("click",function(){
					dataParam.status = {};
					$("#statusUI>li>a").removeClass("activeStaus");
					$(this).addClass("activeStaus");
					eval("dataParam.status." 
							+ $(this).attr("data-name") 
							+ "=" 
							+ $(this).attr("data-value")); 
					searchData();
				});
				$("#effectUI>li>a").on("click",function(){
					dataParam.effect = {};
					$("#effectUI>li>a").removeClass("activeStaus");
					$(this).addClass("activeStaus");
					eval("dataParam.effect." 
						+ $(this).attr("data-name") 
						+ "=" 
						+ '"' + $(this).attr("data-value") +'"'); 
					searchData();
				});
			});
			
			function searchData(){
				if(dataAreas.length>0){
					dataParam.areas = dataAreas;
				}
				$("#search1 input").each(function(){
					var value = $(this).val();
					dataParam[$(this).attr("name")] = value;
					if(Object.keys(dataParam).length>0){
						$("#dataParam").val(JSON.stringify(dataParam));
					}else{
						$("#dataParam").val("");
					}
				});
				console.log(dataParam);
				//点击进行搜索
				var curZoom = map.getZoom();
				$.ajax({
					type: 'post'
					,url: 'queryHouseInfoByMapZoom'
					,dataType: 'json'
					,data: {curZoom: curZoom,jsonData: JSON.stringify(dataParam)} 
					,success: function(data){
						if(data.success){
							var resultData = data.data;
							if(resultData.length == 0){
								layer.msg("根据条件未搜索到房源！");
							}
							if(curZoom <= 12){
								showCircleOverlay(map,resultData);
							}else if(curZoom > 12 && curZoom <= 16){
								showCircleOverlay(map,resultData);
							}else if(curZoom > 16){
								showSquareOverlay(map,resultData);
							}
						}else{
							layer.msg(data.msg);
						}
					}
					,error: function(a,b,c){
						layer.msg("系统操作异常!");
					}
				});
			}
			
			/*******
			 * 展示地图区域 
			 */
			function showMap(){
				//地图工具
				map = new BMap.Map("mapArea");	// 创建地图实例  
				var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
				var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
				var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT
					, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
				map.addControl(top_left_control);        
				map.addControl(top_left_navigation);     
				map.addControl(top_right_navigation); 
				map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放
				map.centerAndZoom("昆明市",10); //设置初始缩放级别为10级
				
				//添加地图缩放事件监听
				map.addEventListener('zoomend',function(){
// 					var curZoom = map.getZoom();
// 					//下面的数据时初始化页面时已经加载了的，但是当点击查询条件时需要更新这些数据
// 					if(curZoom <= 12){
// 						showCircleOverlay(map,secondLevelHsInfo);
// 					}else if(curZoom > 12 && curZoom <= 16){
// 						showCircleOverlay(map,thirdLevelHsInfo);
// 					}else{
// 						showSquareOverlay(map,communityHsInfo);
// 					}
					searchData();
				});
				showCircleOverlay(map,secondLevelHsInfo);
			}
			//显示圆形覆盖物
			function showCircleOverlay(map,houseInfo){
				map.clearOverlays();
				for(var i = 0,n = houseInfo.length; i < n;i++){
					var temp = houseInfo[i];
					var longitude = temp.longitude;
					var latitude = temp.latitude;
					var point;
					if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
						point = new BMap.Point(longitude,latitude); //根据经纬度创建点  	
					}
					var title = temp.areaName + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + temp.count + "套";
// 					http://sso.bashiju.com
					var myIcon = new BMap.Icon("${request.getContextPath()}/src/housing-platform/images/circle.png"
							, new BMap.Size(75, 80));
					var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
				 	var opts = {
		    			position : point,// 指定文本标注所在的地理位置
		    			offset   : new BMap.Size(-20, -10)//设置文本偏移量
		    		}
					var label = new BMap.Label(title, opts);  // 创建文本标注对象
		    		label.setStyle({
		    			color : "#23262e;",				//字体为黑色
			        	backgroundColor:'transparent',	//文本背景色
			       		borderColor:'transparent',		//文本框边框色
		    			fontSize : "12px",
		    			height : "20px",
		    			lineHeight : "20px",
		    			fontFamily:"微软雅黑",
		    			border: false
	    			});
		    		marker.addEventListener('click',function(){
		    			var curZoom = map.getZoom();
		    			if(curZoom < 16){
		    				map.setZoom(16);
		    				showCircleOverlay(map,secondLevelHsInfo);
		    			}
		    			if(curZoom >= 16 ){
		    				map.setZoom(19);
		    				showSquareOverlay(map,communityHsInfo);
		    			}
		    		});
		    		map.addOverlay(marker);
				    map.addOverlay(label);
				}
			}
			
			//显示正方形的覆盖物
			function showSquareOverlay(map,houseInfo){
				map.clearOverlays();
				for(var i = 0,n = houseInfo.length; i < n;i++){
					var temp = houseInfo[i];
					var longitude = temp.longitude;
					var latitude = temp.latitude;
					var point;
					if(longitude && latitude && longitude != 'undefined' && latitude != 'undefined'){
						point = new BMap.Point(longitude,latitude); //根据经纬度创建点  	
					}
					var content = temp.communityName 
							+ "&nbsp;&nbsp;&nbsp;&nbsp;"
							+ temp.count + "套";
					var opts = {
		    			position : point,// 指定文本标注所在的地理位置
		    			offset   : new BMap.Size(-30, -10)//设置文本偏移量
		    		}
					var label = new BMap.Label(content, opts);  // 创建文本标注对象
					label.setTitle(temp.communityId);
		    		label.setStyle({
		    			color : "#FCF9F2",//字体颜色为白色--白色
			        	backgroundColor:'#39AC6A',	//文本背景色--淡绿色
			       		borderColor:'transparent',		//文本框边框色
		    			height : "25px",
		    			lineHeight : "20px",
		    			fontFamily:"微软雅黑",
		    			border: false
	    			});
		    		label.addEventListener('click',function(){
		    			var jsonData = JSON.stringify(dataParam).replace(/\"/g,"'");  
		    			var showHsDetail = {
	    			        type: 2
	    			        ,id: "showHouseDetailIFrame"
	    			        ,title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">二手房源</span>'
	    			        ,area: ['760px','600px']
	    			      	,shade: 0.5
	    			        ,resize: true
	    			        ,maxmin: false
	    			        ,offset: 'auto'
	    			        ,content: 'getHouseInfoDetailModel?communityId='+label.getTitle()+'&jsonData='+jsonData
	    			        ,zIndex: layer.zIndex
	    			        ,success: function(layero,zIndex){
	    			        	layer.setTop(layero);
	    			        }
	    			    };
	    				common.windowLayerOpen(showHsDetail);
		    		});
		    		map.addOverlay(label);
				}
			}
		</script>
	</body>
</html>
