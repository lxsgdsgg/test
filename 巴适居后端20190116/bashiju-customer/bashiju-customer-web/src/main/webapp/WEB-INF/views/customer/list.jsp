<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格实例</title>
<%@include file="/common/common.jsp" %> 


<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/bootstrap-3.3.7/css/bootstrap.min.css" >

<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/datatables/media/css/dataTables.bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/easyform/easyform.css" >
<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/laydate/theme/default/laydate.css" > --%>
<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" >
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
#xiaoqu , #needArea{
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
</style>
</head>
<body>


	<div >  
		<div class="col-md-12">
			<div class="form-inline">
				 <div id="search1" class="form-group">
					<div id="xiaoqu" >
						<span id="xiaoquTitle">选择行政区、片区、小区(可多选)</span>
						<!-- <input type="text" id="xiaoqu" style="width:300px;" class="form-control input-xs queryInput"  placeholder="选择行政区,片区,小区(可多选)">			    -->
					</div>
				 	<div class="queryCondition dropdown pull-left">
					  <input type="text" style="width:300px;"  class="form-control input-xs queryInput"  placeholder="客户电话,需求编号,客户姓名">
					  <ul class="dropdown-menu queryItem" >
					    <li dataName="需求编号" dataType="1" dataparam="2"><a href="#">需求编号<span class="queryData">【hhhh】</span><span class="jiantou">&gt;</span></a></li>
					    <li dataName="客户姓名" dataType="2" dataparam="2"><a href="#">客户姓名<span class="queryData">【gg】</span><span class="jiantou">&gt;</span></a></li>
					    <li dataName="客户电话" dataType="3" dataparam="2"><a href="#">客户电话<span class="queryData">【gg】</span><span class="jiantou">&gt;</span></a></li>
					    <li dataName="客源备注" dataType="4" dataparam="2"><a href="#">客源备注<span class="queryData">【gg】</span><span class="jiantou">&gt;</span></a></li>
					  </ul>
					</div>
				 </div>
				 <button id="queryBtn" type="button" class="btn btn-primary btn-xs">搜索</button>
				 <button id="clearBtn" type="button" class="btn btn-primary btn-xs">清空</button>
				<%--  <c:if test="${onlyBtnMenus.addHouse!=null && onlyBtnMenus.addHouse!=''}"> --%>		  					  	
				 <div id="addBtn" class="dropdown pull-right" >
				 	<button  type="button" class="btn btn-danger btn-sm dropdown-toggle" style="width:100px;">+新增房源</button>
				 	<ul class="dropdown-menu" style="min-width: 100px;background-color: #d9534f;margin-top:0;">
				 	 <c:forEach var="item" items="${houseList}">
						<li class="addLi"><a data-value="${item.value}" href="#">${item.name}</a></li>
					</c:forEach>
				 	</ul>
				 </div>
				<%--  </c:if> --%>
			</div>
			<div class="form-inline">
				<ul id="statusUl" class="list-inline">
						<li><span>状态</span></li>
						<li><a class="activeStaus" dataName="isAll" dataValue="0" href="#">不限</a></li>
						<li><a  dataName="effective" dataValue="1" href="#">有效全部</a></li>
						<li><a  dataName="effective" dataValue="2" href="#">有效公客</a></li>
						<li><a  dataName="effective" dataValue="3" href="#">有效私客</a></li>
						<li><a  dataName="effective" dataValue="4" href="#">我购</a></li>
						<li>
						<a dataName="effective" dataValue="6" href="#">失效</a>
						<div class="tooltip right hidden" role="tooltip">
					      <div class="tooltip-arrow"></div>
					      <div class="tooltip-inner">
					        <a dataName="effective" dataValue="7" href="#">他购</a>
					        <a dataName="effective" dataValue="8" href="#">暂缓</a>
					        <a dataName="effective" dataValue="9" href="#">撤单</a>
					        <a dataName="effective" dataValue="10" href="#">他租</a>
					      </div>
					    </div>
						</li>
						
					</ul>
			</div>
			<div class="form-inline houseUses">
				<ul class="list-inline checkbox">
					<li style="width:50px;"><span >用途</span></li>
					<c:forEach var="item" items="${houseList}">
						<li ><label><input type="checkbox" value="${item.value}">${item.name}</label></li>
					</c:forEach>				
				</ul>
			</div>
			<div class="queryCheckbox">
				<ul class="list-inline checkbox">
					<li><label><input type="checkbox" value="isUrgent">急切</label></li>
					<li><label><input type="checkbox" value="closeDiscPhone">封盘</label></li>
					<li><label><input type="checkbox" value="viewing">有带看</label></li>
					<li ><label><input type="checkbox" value="isBond">下定</label></li>
					<li ><label><input type="checkbox" value="mycustomer">我的客源</label></li>
					<li ><label><input type="checkbox" value="myPrivate">我的私客</label></li>
					<li><label><input type="checkbox" value="unBack">7天未回访 </label></li>	
					<li><label><input type="checkbox" value="unShowed">30天未带看 </label></li>	
					<li><label><input type="checkbox" value="myCollection">我的收藏</label></li>
					<li><label><input type="checkbox" value="isSchoolRoom">学区房</label></li>
					<li><label><input type="checkbox" value="hunting">淘宝池</label></li>
					<li><label><input type="checkbox" value="unHunting">非淘宝池</label></li>
					<li><label><input type="checkbox" value="isCancelNotice">通知撤单</label></li>
					<li><label><input type="checkbox" value="contractEnd">30天租赁到期</label></li>
					<li><label><input type="checkbox" value="one">一手</label></li>
					<li><label><input type="checkbox" value="two">二手</label></li>
				</ul>
			</div>
			
			<div class="form-inline">
				 <div class="form-group">
				 
				 <div id="priceRange" class="dropdown rangeDiv">
				  <div class="input-group rangeInput">
				  	<input type="text" id="priceInput"  class="form-control pull-left input-xs input"   placeholder="价格区间">
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input type="number" class="form-control lower"  placeholder="0">
				    ——
				    <input type="number" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>
				
				 <div id="areaRange" class="dropdown rangeDiv">
				  <div class="input-group rangeInput">
				  	<input type="text" id="mianji" class="form-control pull-left input-xs input"  placeholder="面积区间">
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input type="number" class="form-control lower"  placeholder="0">
				    ——
				    <input type="number" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>	

					<div id="more" class="dropdown rangeDiv">
					  <div class="input-group rangeInput">
					      <div  class="form-control input-xs input ">更多</div>
					      <div class="input-group-addon">▼</div>
					    </div>
					  <ul class="dropdown-menu more" >
					    <li>	
					    	<div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="几室" name=""  class="form-control input-xs input " >几室</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li>
							    <input type="number" class="form-control lower"  placeholder="0">
							    ——
							    <input type="number" class="form-control upper"  placeholder="不限">
							    </li>
							    <li  class="divider"></li>
							  </ul>
							</div>						
							<div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="楼层" name=""  class="form-control input-xs input " >楼层</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li>
							    <input type="number" class="form-control lower"  placeholder="0">
							    ——
							    <input type="number" class="form-control upper"  placeholder="不限">
							    </li>
							    <li  class="divider"></li>
							  </ul>
							</div>
							<div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="房龄" name=""  class="form-control input-xs input " >房龄</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li>
							    <input type="number" class="form-control lower"  placeholder="0">
							    ——
							    <input type="number" class="form-control upper"  placeholder="不限">
							    </li>
							    <li  class="divider"></li>
							  </ul>
							</div>
					    </li>
					    <li>
					     <div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="日期类型" name=""  class="form-control input-xs input " >日期类型</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li class="moreLi"><a data-value="1" href="#">录入日期</a></li>
							    <li class="moreLi"><a data-value="3" href="#">最近跟进日期</a></li>
							    <li class="moreLi"><a data-value="4" href="#">最近带看日期</a></li>
							     <li class="moreLi"><a data-value="4" href="#">租赁到期日期</a></li>
							  </ul>
							</div>
							<input type="text" id="startDate" name="startDate" class="form-control input-xs moreDate" placeholder="开始日期">
							<span class="">-</span>
							<input type="text" id="endDate" name="endDate" class="form-control input-xs moreDate" placeholder="结束日期">
					    </li>
					    <li>
					    	<div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="部门用户类型" name=""  class="form-control input-xs input " >部门用户类型</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li class="moreLi"><a data-value="1" href="#">录入人</a></li>
							    <li class="moreLi"><a data-value="3" href="#">维护人</a></li>
							    <li class="moreLi"><a data-value="4" href="#">封盘人</a></li>
							  </ul>
							</div>
							<div  class="dropdown rangeDiv">
								<div class="input-group rangeInput">
							      <div id="department" class="form-control input-xs input " >选中部门或用户</div>
							      <div class="input-group-addon">▼</div>
							    </div>
						    </div>
					    </li>
					    <li>
					     <div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="朝向" name=""  class="form-control input-xs input " >朝向</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl multiCheckbox" >
							    <li class="moreLi" ><label><input data-value="1" type="checkbox" value="1">东</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="2">南</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="3">西</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">北</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="4">东西</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="5">南北</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="6">东南</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="7">西南</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="8">东北</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="9">西北</label></li>
							  </ul>
							</div>
							
							<div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="装修" name=""  class="form-control input-xs input " >装修</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl multiCheckbox" >
							    <li class="moreLi" ><label><input data-value="1" type="checkbox" value="1">毛坯</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="2">简装</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="3">普装</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="4">精装</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="5">豪装</label></li>
							    </ul>
							</div>
							 <div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="配套" name=""  class="form-control input-xs input " >配套</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl multiCheckbox" >
							    <li class="addFormLi" ><label><input  type="checkbox" value="1">床</label></li>
								<li class="addFormLi"><label><input  type="checkbox" value="2">衣柜</label></li>
								<li class="addFormLi"><label><input  type="checkbox" value="3">空调</label></li>
								<li class="addFormLi"><label><input  type="checkbox" value="4">冰箱</label></li>
							  </ul>
							</div>
							
					    </li>				    
					    <li>
					    <div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="来源" name=""  class="form-control input-xs input " >来源</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl multiCheckbox" >
							    <li class="moreLi" ><label><input data-value="1" type="checkbox" value="name">上门</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">电话</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">公司网站</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">驻守</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">58</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">赶集</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">搜房</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">安居客</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">朋友介绍</label></li>
							    <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">已成交客户推荐</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">未成交客户推荐</label></li>
							     <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">陌拜</label></li>
							      <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">窗体广告</label></li>
							      <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">老客户</label></li>
							      <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">派单</label></li>
							       <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">微博</label></li>
							       <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">微店</label></li>
							       <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">微信公众号</label></li>
							       <li class="moreLi"><label><input data-value="1" type="checkbox" value="name">其他</label></li>
							  </ul>
							</div>
					    <div  class="dropdown rangeDiv moreItem">
							  <div class="input-group rangeInput">
							      <div default-title="客源等级" name=""  class="form-control input-xs input " >客源等级</div>
							      <div class="input-group-addon">▼</div>
							    </div>
							  <ul class="dropdown-menu moreUl" >
							    <li class="moreLi"><a data-value="A" href="#">A类客源</a></li>
							    <li class="moreLi"><a data-value="B" href="#">B类客源</a></li>
							    <li class="moreLi"><a data-value="C" href="#">C类客源</a></li>
							    <li class="moreLi"><a data-value="D" href="#">D类客源</a></li>
							  </ul>
							</div>
					    </li>
					    <li class="text-center">
					    	<button class="btn">确定</button>
					    	<button class="btn">取消</button>
					    </li>
					  </ul>
					</div>	
					
				 </div>
			</div>
			
		</div>
		<div  class="col-md-12">
			<div class="operator" >
				<div class="btn-group">
				  <button  type="button" class="btn btn-default">导出当前页</button>
				  <button id="transfer" type="button" class="btn btn-default">批量信息转移</button>
				 <%-- <c:if test="${onlyBtnMenus.deleteHouse!=null && onlyBtnMenus.deleteHouse!=''}"> --%>	
				  <button id="deleteBtn" type="button" class="btn btn-default">客源删除</button>
				<%-- </c:if> --%>
				  
				<div class="btn-group">
				  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    排序 <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu ulItem">
				    <li><a data-value="1" href="#">按带看次数升序</a></li>
				    <li><a data-value="2"  href="#">按带看次数降序</a></li>
				  </ul>
				</div>	
					  
				</div>
				<button id="hidColBtn" type="button" class="btn btn-default pull-right" >自定义列表</button>
			</div>
		</div>
		
		<div>
			
			<div  class="col-md-12">
				<table id="houseTable" class="table table-striped table-bordered table-hover" cellspacing="0" style="margin-top:0 !important;">
					 <thead>
			            <tr  style="background-color: #f2f2f2;color:#666;">
			            <th><input class="allData" type="checkbox"></th>
			                <th></th>
			              <th></th>
			              <th></th>
			                <th></th>
			                <th></th>
			                <th></th>
			              <th></th>
			              <th></th>
			                <th></th>
			                <th></th>
			                <th></th>
			              <th></th>
			              <th></th>
			                <th></th>
			                <th></th>
			                <th></th>
			              <th></th>
			              <th></th>
			                <th></th>
			            </tr>
			        </thead> 
				</table>
			</div>
		</div>
		
	</div>


<div class="checkboxDiv hidden">
 <ul class="list-inline checkbox">
	<li><label><input type="checkbox" value="labeld">标签</label></li>
	<li><label><input type="checkbox" value="customerId">需求编号</label></li>
	<li><label><input type="checkbox" value="discStatus">状态</label></li>
	<li><label><input type="checkbox" value="houseUses">需求用途</label></li>
	<li><label><input type="checkbox" value="custName">客户姓名</label></li>
	<li><label><input type="checkbox" value="sourceType">客户来源</label></li>
	<li><label><input type="checkbox" value="areaCodes">需求区域</label></li>
	<li><label><input type="checkbox" value="minSpace">需求面积</label></li>
	<li><label><input type="checkbox" value="minPrice">需求价格</label></li>
	<li><label><input type="checkbox" value="minRoom">户型</label></li>
	<li><label><input type="checkbox" value="communicateStage">沟通阶段</label></li>
	<li><label><input type="checkbox" value="remark">备注</label></li>
	<li><label><input type="checkbox" value="levelType">客源等级</label></li>
	<li><label><input type="checkbox" value="recordTime">录入日期</label></li>
	<li><label><input type="checkbox" value="allFollowTime">全员最后维护</label></li>
	<li><label><input type="checkbox" value="lastShowedTime">最后带看时间</label></li>
	<li><label><input type="checkbox" value="mainterFllowTime">维护人最后维护</label></li>
	<li><label><input type="checkbox" value="maintainer">维护人</label></li>
	<li><label><input type="checkbox" value="mainterDept">部门</label></li>
 </ul>		
 <button id="saveBtn" class="btn btn-primary">保存</button>	 
</div>

<div >	
<form id="addForm" class="form-horizontal hidden" style="margin:20px 0;">
		<input type="hidden" value="4" name="transactionTypeId">
		<input type="hidden" value="求购" name="transactionType">
		<input type="hidden" name="areaCodes">
		<input type="hidden" name="areaNames">
		<input type="hidden" name="regionIds">
		<input type="hidden" name="regionNames">
		<input type="hidden" name="communityIds">
		<input type="hidden" name="communityNames">
		<input type="hidden" name="formName">
	    <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>公私盘</label>
		    <div class="col-sm-9">
			 <input type="hidden" name="discStatus">
			 <select class="form-control" name="discStatusId" style="float:left;">
				  <option value="2">公客</option>
				   <option value="1">私客</option>
			 </select>
			 <button type="button" class="btn btn-link">我的私客</button>
		    </div>
	    
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>客户姓名</label>
		    <div class="col-sm-7">
		      <input type="text" id="custName" class="form-control pull-left" style="width:100px;" data-easytip="position:top;" data-easyform="length:2 6;" data-message="姓名长度必须为2-6">
			 <select id="relateTypeId" class="form-control" style="float:left;">
				  <option value="1">先生</option>
				  <option value="2">女士</option>
				  <option value="3">公司</option>
			 </select>
		    </div>
	    
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>电话</label>
		    <div class="col-sm-7">
		      <input type="text" name="phone" class="form-control pull-left" style="width:100px;" data-easytip="position:top;" data-easyform="length:7 16;char-normal;" data-message="电话格式错误">
			 <input type="hidden" name="relateName">
			 <select  name="relateType" class="form-control" style="float:left;">
				  <option value="1" >本人</option>
				  <option value="2">配偶</option>
				  <option value="3">亲戚</option>
				  <option value="4">朋友</option>
				  <option value="5">授权委托人</option>
			 </select>
		    </div>
	    
	    </div>
	  </div>
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    <label  class="col-sm-3 control-label">是否发送绑定码</label>
	    	<div class="col-sm-7">
	    		<input type="checkbox" name="isSendBindCode" value="1" checked>
	    	</div>
	    </div>
	   </div>
	  <div class="form-group form-group-sm">
	  <label  class="col-sm-9 control-label" style="color:red;font-size:12px;">* 固话与区号间请用"-"分隔，例如:022-8888888</label>
	  
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline col-sm-10 text-center">
	    	<button id="nextBtn" type="button" class="btn ">下一步</button>
	    	<button type="button" class="btn btn-link">取消</button>
	    </div>
	   </div>
</form>
<form id="addDetailForm" class="form-horizontal hidden" style="margin:20px 0;">
	<div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<div class="col-sm-3" style="padding-right:0;padding-left:0;">
			    <div class="form-inline">
			    	<label  class="col-sm-4 control-label" style="padding-left:0;"><span class="bitian">*</span>客户姓名</label>
				    <div class="col-sm-7" style="padding-right:0;padding-left:0;">
				      <input type="text" name="custName" class="form-control pull-left" style="width:80px;" >
					   <input type="hidden" name="relateTypeName">
					 <select name="relateTypeId" class="form-control" style="float:left;">
						  <option value="1">先生</option>
						  <option value="2">女士</option>
						  <option value="3">公司</option>
					 </select>
				    </div>
			    
			    </div>
			  </div>
			  <div class="col-sm-6">
			  	<label  class="pull-left control-label"><span class="bitian">*</span>需求区域&nbsp;</label>
			    	<div  id="needArea" class="col-sm-8">
				      <span id="needAreaTitle">选择行政区、片区、小区(可多选)</span>
				    </div>
			  </div>
		    <div class="col-sm-3">
		    <label  class="pull-left control-label"><span class="bitian">*</span>用途</label>
	    	<div class="col-sm-9">
	    		<div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="hidden" name="houseUsesIds">
						<input type="text" name="houseUses" class="form-control pull-left" style="background-color: #fff;" readonly >
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" style="min-width:120px;">
					<c:forEach var="item" items="${houseList}">
						<li class="addFormLi" ><label><input  type="checkbox" value="${item.value}">${item.name}</label></li>
					</c:forEach>
					</ul>
				</div>		      
		    </div>
		    </div>
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<div class="col-sm-3">
	    	<div id="room" class="dropdown rangeDiv">
	    			<label  class="pull-left control-label"><span class="bitian">*</span>需求户型</label>
				  <div class="input-group rangeInput" style="margin-left:10px;">
				      <input type="text"  class="form-control pull-left input" style="background-color: #fff;" readonly >
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input type="number" name="minRoom" class="form-control lower"  placeholder="0">
				    ——
				    <input type="number" name="maxRoom" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>	
	    	</div>
	    	
		    <div class="col-sm-3">
		    	<div id="price" class="dropdown rangeDiv">
	    			<label  class="pull-left control-label"><span class="bitian">*</span>需求价格</label>
				  <div class="input-group rangeInput" style="margin-left:10px;">
				      <input type="text"  class="form-control pull-left input" style="background-color: #fff;" readonly   >
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input name="minPrice" type="number" class="form-control lower"  placeholder="0">
				    ——
				    <input name="maxPrice" type="number" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>
		    </div>
		    
		    <div class="col-sm-3">
		    	<div  id="space" class="dropdown rangeDiv">
	    			<label  class="pull-left control-label"><span class="bitian">*</span>需求面积</label>
				  <div class="input-group rangeInput" style="margin-left:10px;">
				      <input type="text"  class="form-control pull-left input" style="background-color: #fff;" readonly   >
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input name="minSpace" type="number" class="form-control lower"  placeholder="0">
				    ——
				    <input name="maxSpace" type="number" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>
		    </div>
		    <div class="col-sm-3">
		    	<label class="check"><input name="isUrgent" type="checkbox" value="1">急切</label>
		    	<label class="check"><input name="isFullAmount" type="checkbox" value="1">全款</label>
		    </div>
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	  	<div class="form-inline">
	  	<div class="col-sm-3">
		    	<div id="floorCount" class="dropdown rangeDiv">
	    			<label  class="pull-left control-label"><span class="bitian">*</span>需求楼层</label>
				 <div class="input-group rangeInput" style="margin-left:10px;">
				      <input type="text"  class="form-control pull-left input" style="background-color: #fff;" readonly   >
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input name="minFloorCount" type="number" class="form-control lower"  placeholder="0">
				    ——
				    <input name="maxFloorCount"  type="number" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>朝向</label>
		    	<div class="col-sm-9">
		    		<div  class="dropdown rangeDiv addFormItem">
						<div class="input-group rangeInput ">
							<input type="hidden" name="orientationId">
							<input type="text" name="orientation" class="form-control pull-left" style="background-color: #fff;" readonly >
							<div class="input-group-addon">▼</div>
						</div>
						<ul class="dropdown-menu  multiCheckbox addFormUl" style="min-width:120px;">
							 <li class="addFormLi" ><label><input data-value="1" type="checkbox" value="1">东</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="2">南</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="3">西</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="4">北</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="5">东西</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="6">南北</label></li>
							     <li class="addFormLi"><label><input data-value="1" type="checkbox" value="7">东南</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="8">西南</label></li>
							     <li class="addFormLi"><label><input data-value="1" type="checkbox" value="9">东北</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="10">西北</label></li>
						</ul>
					</div>		      
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<div id="buildDates" class="dropdown rangeDiv">
	    			<label  class="pull-left control-label"><span class="bitian">*</span>需求房龄</label>
				  <div class="input-group rangeInput" style="margin-left:10px;">
				      <input type="text"  class="form-control pull-left input" style="background-color: #fff;" readonly >
				      <div class="input-group-addon">▼</div>
				    </div>
				  <ul class="dropdown-menu" >
				    <li>
				    <input type="number" name="minBuildDates" class="form-control lower"  placeholder="0">
				    ——
				    <input type="number" name="maxBuildDates" class="form-control upper"  placeholder="不限">
				    </li>
				    <li  class="divider"></li>
				  </ul>
				</div>	
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>装修</label>
		    	<div class="col-sm-9">
		    		<div  class="dropdown rangeDiv addFormItem">
						<div class="input-group rangeInput ">
							<input type="hidden" name="decorationIds">
							<input type="text" name="decoration" class="form-control pull-left" style="background-color: #fff;" readonly >
							<div class="input-group-addon">▼</div>
						</div>
						<ul class="dropdown-menu  multiCheckbox addFormUl" style="min-width:120px;">
							 <li class="addFormLi" ><label><input data-value="1" type="checkbox" value="1">毛坯</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="2">简装</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="3">普装</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="4">精装</label></li>
							    <li class="addFormLi"><label><input data-value="1" type="checkbox" value="5">豪装</label></li>
						</ul>
					</div>		      
			    </div>
		    </div>
		    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
		  <div class="col-sm-6">
			    	<label  class="pull-left control-label">配套</label>
		    	<div class="col-sm-8">	    	
				 <div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput">
						<input type="hidden" name="matchingIds">
						<input type="text" name="matching" class="form-control pull-left" style="width:400px;background-color: #fff;" readonly >
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">床</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">衣柜</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">空调</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="4">冰箱</label></li>
					</ul>
				</div>
			    </div>
			 </div>
			 <div class="col-sm-6">
		    	<label  class="pull-left control-label">需求原因</label>
		    	<input name="reason" type="text" class="form-control pull-left" style="width:400px;" >
		    </div>
	  </div>
	<div class="form-group form-group-sm">
		<div class="col-sm-6">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>推荐标签</label>
		    	<div class="col-sm-9">
		    		<div  class="dropdown rangeDiv addFormItem">
						<div class="input-group rangeInput ">
							<input type="text" name="labeld" class="form-control pull-left" style="width:300px;background-color: #fff;" readonly >
							<div class="input-group-addon">▼</div>
						</div>
						<ul class="dropdown-menu  multiCheckbox addFormUl" >
							<li class="addFormLi" ><label><input  type="checkbox" value="1">满五年</label></li>
							<li class="addFormLi"><label><input  type="checkbox" value="2">学区房</label></li>
							<li class="addFormLi"><label><input  type="checkbox" value="3">地铁房</label></li>
							<li class="addFormLi"><label><input  type="checkbox" value="4">满两年</label></li>
						</ul>
					</div>		      
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>房屋类型</label>
		    	<div class="col-sm-8">		    	
				  <div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="hidden" name="houseTypeIds">
						<input type="text" name="houseType" class="form-control pull-left" style="width:110px;background-color: #fff;" readonly >
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">低层</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">高层</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">多层</label></li>
					</ul>
				</div>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label class="check"><input name="one" type="checkbox" value="1">一手</label>
		    	<label class="check"><input name="two" type="checkbox" value="1">二手</label>
		    </div>
	</div>  
	
	<hr>
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>沟通阶段</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="communicateStage">
			      <select  class="form-control" style="" name="communicateStageId">
					  <option value="1">客户接待</option>
					  <option value="2">信息匹配</option>
					  <option value="3">首次带看</option>
					  <option value="4">二次带看</option>
					  <option value="5">守价阶段</option>
					  <option value="6">杀价阶段</option>
					  <option value="7">逼定阶段</option>
					  <option value="8">签订合同</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>来源</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="sourceType">
			      <select  class="form-control" style="" name="sourceTypeId">
					  <option value="1">上门</option>
					  <option value="2">电话</option>
					  <option value="3">公司网站</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
			    	<label  class="pull-left control-label">消费理念</label>
		    	<div class="col-sm-8">		    	
				  <div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="hidden" name="consumptIdeaId">
						<input type="text" name="consumptIdeaName" class="form-control pull-left" style="width:110px;background-color: #fff;" readonly >
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">生存型消费</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">发展型消费</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">享受型消费</label></li>
					</ul>
				</div>
			    </div>
			    </div>
			    <div class="col-sm-3">
			    	<label  class="pull-left control-label">国籍</label>
			    	<div class="col-sm-8">
			    	<input type="hidden" name="censusRegister">
				      <select  class="form-control" name="censusRegisterId">
						 <option value="1">中国大陆</option>
					  	<option value="2">中国香港</option>
					  	<option value="3">中国澳门</option>
					  	<option value="4">中国台湾</option>
					  	<option value="5">外国</option>
					 </select>
				    </div>
			    </div>
	</div>	
  
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label">名族</label>
			    	<div class="col-sm-8">
			    	<input type="hidden"  name="nations">
				      <select  class="form-control" name="nationsId">
						 <option value="1">汉族</option>
					  	<option value="2">回族</option>
					  	<option value="3">满族</option>
					  	<option value="4">朝鲜族</option>
					  	<option value="5">其他族</option>
					 </select>
				    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">证件号码</label>
		    	<div class="col-sm-8">
			      <input name="IDCard" type="text" class="form-control pull-left " style="width:100px;" >
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">邮箱</label>
		    	<div class="col-sm-8">
			      <input name="email" type="text" class="form-control pull-left " style="width:100px;" >
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">QQ</label>
		    	<div class="col-sm-8">
			      <input name="qqNum" type="text" class="form-control pull-left " style="width:100px;">
			    </div>
		    </div>
	</div> 
		<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label">微信</label>
		    	<div class="col-sm-8">
			      <input name="wxNum" type="text" class="form-control pull-left " style="width:100px;" >
			    </div>
		    </div>
		<div class="col-sm-3">
		    	<label  class="pull-left control-label">交通工具</label>
			    	<div class="col-sm-8">
			    	<input type="hidden" name="vehicle">
				      <select  class="form-control" name="vehicleId" >
						 <option value="1">自行车</option>
					  	<option value="2">电动车</option>
					  	<option value="3">汽车</option>
					  	<option value="4">步行</option>
					 </select>
				    </div>
		    </div>
		    
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">车型</label>
		    	<div class="col-sm-8">
			      <input name="contactAddress" type="text" class="form-control pull-left " style="width:100px;" >
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">客源等级</label>
		    	<div class="col-sm-8">
			      <input name="levelType" type="text" class="form-control pull-left " readOnly style="width:100px;background-color: #fff;" >
			    </div>
		    </div>
	</div>     

	<div>
		<ul class="custom-inline">
		<c:forEach var="item" items="${dynamicColumnList}">
			<li>
				<label  class="pull-left control-label">
				<c:if test='${item.fieldRequired!=null&&item.fieldRequired=="required"}'>
				<span class="bitian">*</span>
				</c:if>
				${item.title}</label>
			      <input  type="text" name="${item.field}"   class="form-control pull-left customColumn" style="width:100px;" >
			</li>
		</c:forEach>
			
		</ul>
	</div>	     		     
	<div class="form-group form-group-sm">
		<div class="col-sm-12">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>备注</label>
		    	<div class="col-sm-11">
			      <input type="text" name="remark" class="form-control pull-left" style="width:900px;" >
			    </div>
		    </div>
	</div>
	<!-- <div class="form-group form-group-sm">
		<div class="col-sm-12">
		    	<label  class="pull-left control-label">核心备注</label>
		    	<div class="col-sm-11">
			      <input type="text" name="remark" class="form-control pull-left" style="width:900px;" >
			    </div>
		    </div>
	</div> -->
	<hr style="border-top: 2px solid blue;">
	<div class="form-group form-group-sm text-center">
		<button id="addFormBtn" type="button" class="btn btn-primary">确定</button>
		<button id="canelBtn" type="button">取消</button>
	</div>
</form>
</div>
<input type="hidden" id="dataParam">
<!-- 查看座栋 --> 
<div id="showBuilding" class="layui-hide">
	<div class="layui-row">
	&nbsp;
	</div>
	<div class="layui-row">
		<div class="layui-col-md2 layui-elem-field">
			<table class="layui-table" id="buildingNameTable"  lay-filter="buildingNameTable"></table> 
		</div>
		<div class="layui-col-md2 layui-elem-field">
			<table class="layui-table" id="unitNameTable" lay-filter="unitNameTable"></table> 
		</div>
		<div class="layui-col-md8 layui-elem-field">
			<table id="showHouses" class=" layui-table"></table>
		</div>
	</div>
		
</div>

<%-- <script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>  --%>
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/bootstrap-3.3.7/js/bootstrap.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/easyform/easyform.js"></script>  
<%-- <script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/layer/layer.js"></script>  --%>
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/dataTables.bootstrap.min.js"></script> 
<%-- <script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/laydate/laydate.js"></script>  --%>
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script> 

<script>

var dataParam={};//后台搜索参数
var dataAreas=[];//区域、小区、片区
var building={};//座栋查询
var vagueData={};//门牌、电话等
var status={};//房源状态

var sellingPrice={};//价格区间
$(".moreItem .rangeInput").on("click",function(){
	$(".moreItem .moreUl").removeClass("show");
	$(this).next().toggleClass("show");
});
$(".moreItem .moreUl .moreLi").on("click",function(){
	$(this).parent().prev().find(".input").text($(this).children().first().text());
	$(this).parent().prev().find(".input").removeClass("rangeDefault");
	$(this).parent().removeClass("show");
});
$(".addFormItem .rangeInput").on("click",function(){
	$(".addFormItem .addFormUl").removeClass("show");
	$(this).next().toggleClass("show");
});
$(".addFormItem .addFormUl .addFormLi").on("click",function(){
	var values="";
	var names="";
	
	$(this).parent().find("input[type=checkbox]:checked").each(function(){
		values+=$(this).val()+";";
		names+=$(this).parent().text()+",";
	});
	$(this).parent().prev().find("input[type=text]").val(names);
	if($(this).parent().prev().find("input[type=hidden]").length>0){
		$(this).parent().prev().find("input[type=hidden]").val(values);
	}	
});
$("#more >div.rangeInput").on("click",function(event){
	//event.stopPropagation();
	$(".moreItem .moreUl").removeClass("show");
	$(this).next().toggleClass("show");
});
$(document).on('click', function(e) {
	  var e = e || window.event; //浏览器兼容性
	  var elem = e.target || e.srcElement;
	  while (elem) {
	    //循环判断至跟节点，防止点击的是div子元素
	    if (elem.id && elem.id == 'more') {
	      return;
	    }
	    elem = elem.parentNode;
	  }
	  //点击的不是div或其子元素
	  $(".moreItem .moreUl").removeClass("show");
	  $("#more >ul.more").removeClass("show");
});
$(document).on('click', function(e) {
	var e = e || window.event; //浏览器兼容性
	  var elem = e.target || e.srcElement;
	while (elem) {
	    //循环判断至跟节点，防止点击的是div子元素
	    if ($(elem).attr("class")&&$(elem).attr("class").indexOf("moreItem")>-1) {
	      return;
	    }
	    elem = elem.parentNode;
	  }
	  //点击的不是div或其子元素
	  $(".moreItem .moreUl").removeClass("show");

});
$(document).on('click', function(e) {
	var e = e || window.event; //浏览器兼容性
	  var elem = e.target || e.srcElement;
	while (elem) {
	    //循环判断至跟节点，防止点击的是div子元素
	    if ($(elem).attr("class")&&$(elem).attr("class").indexOf("addFormItem")>-1) {
	      return;
	    }
	    elem = elem.parentNode;
	  }
	  //点击的不是div或其子元素
	  $(".addFormItem .addFormUl").removeClass("show");

});
function rangeBind(id,unit,datas){
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
		var rangeValue=$("#"+id+" .rangeInput .input").val();
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
			if($(this).find("a").text()==0){
				$("#"+id+" .lower").val("");
			}else{
				$("#"+id+" .lower").val($(this).find("a").text());
			}
			if(upper==""){
				if(lower==0){
					$("#"+id+" .rangeInput .input").val("");
					$("#"+id+" .rangeInput .input").change();
				}else{
					$("#"+id+" .rangeInput .input").val(lower+unit+"以上");
					$("#"+id+" .rangeInput .input").change();
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
				
				$("#"+id+" .rangeInput .input").val(lower);
				$("#"+id+" .rangeInput .input").change();
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
			if($(this).find("a").text()!="不限"){
				$("#"+id+" .upper").val($(this).find("a").text());
			}else{
				$("#"+id+" .upper").val("");
			}
			if(lower==""){
				if($(this).find("a").text()!="不限"){
					$("#"+id+" .rangeInput .input").val($(this).find("a").text()+unit+"以下");
					$("#"+id+" .rangeInput .input").change();
				}else{
					$("#"+id+" .rangeInput .input").val("");
					$("#"+id+" .rangeInput .input").change();
				}			
			}else{
				var upper=$(this).find("a").text();
				if(upper!="不限"){
					if(parseInt(upper)<parseInt(lower)){
						lower=parseInt(upper)+"-"+parseInt(lower)+unit;
					}else{
						lower=parseInt(lower)+"-"+parseInt(upper)+unit;
					}
					
					$("#"+id+" .rangeInput .input").val(lower);
					$("#"+id+" .rangeInput .input").change();
				}else{				
					$("#"+id+" .rangeInput .input").val(lower+unit+"以上");
					$("#"+id+" .rangeInput .input").change();
				}
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
					$("#"+id+" .rangeInput .input").val("");
					$("#"+id+" .rangeInput .input").change();
				}else{
					$("#"+id+" .rangeInput .input").val(parseInt($(this).val())+unit+"以上");
					$("#"+id+" .rangeInput .input").change();
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
				$("#"+id+" .rangeInput .input").val(lower);
				$("#"+id+" .rangeInput .input").change();
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
					$("#"+id+" .rangeInput .input").val("");
					$("#"+id+" .rangeInput .input").change();
				}else{
					
					$("#"+id+" .rangeInput .input").val(parseInt($(this).val())+unit+"以下");
					$("#"+id+" .rangeInput .input").change();
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
				$("#"+id+" .rangeInput .input").val(lower);
				$("#"+id+" .rangeInput .input").change();
			}
		}
	});
}
var table ;
var layer;
var laydate;

layui.use(['table','layer','laydate'], function(){
 table = layui.table;
 layer=layui.layer;
 laydate=layui.laydate;
 laydate.render({
	  elem: '#startDate.moreDate' //指定元素
	});
laydate.render({
	  elem: '#endDate.moreDate' //指定元素
	});
	laydate.render({
		  elem: '#addDetailForm input[name=certificateDate]' //指定元素
		});
});

$(function () {
	var priceDataRange={};
	priceDataRange.lower=[0,150,175,200,250,300,350,450];
	priceDataRange.upper=[200,300,400,500,600,700,800];
	var areaDataRange={};
	areaDataRange.lower=[0,50,60,70,80,100,120,140];
	areaDataRange.upper=[70,90,110,130,150,170,190];
	var roomRange={};
	roomRange.lower=[0,1,2,3,4,5];
	roomRange.upper=[2,3,4,5,6];
	var buildDatesRange={};
	buildDatesRange.lower=[0,2005,2006,2007,2008,2009,2010,2011];
	buildDatesRange.upper=[2006,2007,2008,2009,2010,2011,2012,2013];
	var floorRange={};
	floorRange.lower=[0,1,2,3,4,5,6,7];
	floorRange.upper=[2,3,4,5,6,7,8,9,10];
	rangeBind("priceRange","万元",priceDataRange);
	rangeBind("areaRange","平米",areaDataRange);
	rangeBind("room","室",roomRange);
	rangeBind("buildDates","年",buildDatesRange);
	rangeBind("price","万元",priceDataRange);
	rangeBind("space","平米",areaDataRange);
	rangeBind("floorCount","层",floorRange);
	 $("#priceInput").on("change",function(){
		sellingPrice={};
		if($("#priceRange .upper").val()!=""&&$("#priceRange .upper").val()!="不限"){
			sellingPrice.max=$("#priceRange .upper").val();
		}
		if($("#priceRange .lower").val()!=""){
			sellingPrice.min=$("#priceRange .lower").val();
		}
		dataParam.sellingPrice=sellingPrice;
		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	}); 
	 $("#mianji").on("change",function(){
		  	var buildSpace={};
			if($("#areaRange .upper").val()!=""&&$("#areaRange .upper").val()!="不限"){
				buildSpace.max=$("#areaRange .upper").val();
			}
			if($("#areaRange .lower").val()!=""){
				buildSpace.min=$("#areaRange .lower").val();
			}
			dataParam.buildSpace=buildSpace;
			$("#dataParam").val(JSON.stringify(dataParam));
			dataTable.ajax.reload();
		}); 
	 
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
			$(this).parent().next().css("width","300px");
			$(this).parent().next().val("");
			$(this).parent().parent().children().first().remove();
			
		});
		if($(this).attr("dataParam")=='1'){
			building.type=$(this).attr("dataType");
			building.value=val;
		} 
		if($(this).attr("dataParam")=='2'){
			vagueData.type=$(this).attr("dataType");
			vagueData.value=val;
		}
	});

	$(".operator .btn-group").hover(
	  function () {
	    $(this).addClass("open");
	  },
	  function () {
	    $(this).removeClass("open");
	  }
	);
	$("#statusUl>li>a").on("click",function(){
		$("#statusUl>li>div[role=tooltip]").addClass("hidden");
		$("#statusUl>li>a").removeClass("activeStaus");
		$("#statusUl .tooltip a").removeClass("activeStaus");
		$(this).next().removeClass("hidden");
		$(this).addClass("activeStaus");
		dataParam.status={"value":1};
		dataParam.status.type=$(this).attr("dataValue");
		console.log(JSON.stringify(dataParam));

		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	});
	
	$("#statusUl .tooltip a").on("click",function(){
		$("#statusUl .tooltip a").removeClass("activeStaus");
		$(this).addClass("activeStaus");
		dataParam.status={"value":1};
		dataParam.status.type=$(this).attr("dataValue");
		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	});
	$(".houseUses ul li label").on("click",function(){
		dataParam.houseUsesIds="";
		$(".houseUses ul li label input:checked").each(function(){
			dataParam.houseUsesIds+=$(this).val()+",";
		});	
		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	});
	
	$(".queryCheckbox label").on("click",function(){
		$(".queryCheckbox label input").each(function(){
			dataParam[$(this).val()]="";
		});
		$(".queryCheckbox label input:checked").each(function(){
			dataParam[$(this).val()]="1";
		});	
		console.log(JSON.stringify(dataParam));	
		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	});
	$(".allData").on("click",function(){
		$("input.column").attr("checked",$(this).prop("checked"));
	});
	$("#deleteBtn").on("click",function(){
		if($("input.column:checked").length>0){
			layer.confirm('您确定要删除房源吗?', {icon: 3, title:'提示'}, function(index){
				var ids="";
				$("input.column:checked").each(function(){
					if(ids!=""){
						ids+=",";
					}
					ids+=$(this).val();
				});
				$.ajax({
				      type: "POST",//方法类型
				      dataType: "json",//预期服务器返回的数据类型
				      //contentType: 'application/json',
				      data: {customerIds:ids},
				      url: "<%=request.getContextPath()%>/customer/deleteCustomer" ,//url	                
				      //data:data,
				      success: function (result) {
				      	if(result.success){
				      		dataTable.ajax.reload();
				      	}
				      },
				      error : function(a,b,c) {	            	
				      }
				  }); 
				  
				  layer.close(index);
				});
		}
	});
	$("#transfer").on("click",function(){
		if($("input.column:checked").length>0){
			var ids="";
			$("input.column:checked").each(function(){
				if(ids!=""){
					ids+=",";
				}
				ids+=$(this).val();
			});
			layerId=layer.open({
				  type: 0,
				  title: "批量信息转移", //不显示标题
				  area:['350px','200px'],
				  content: '<form id="transferForm" class="layui-form">'
						+'<div class="layui-form-item">'
						+'<label class="layui-form-label">转移给</label>'
						+'<div class="layui-input-block" style="margin-left:80px;">'
						+'<input type="text" id="people"    placeholder="请选择用户"  readonly class="layui-input">'
						+'</div>'
						+'</div>'
						+'</form>'
				  ,yes: function(index, layero){
					    if($("#people").attr("data-value")){
					    	$.ajax({
							      type: "POST",//方法类型
							      dataType: "json",//预期服务器返回的数据类型
							      //contentType: 'application/json',
							      data: {customerIds:ids,userId:$("#people").attr("data-value")},
							      url: "<%=request.getContextPath()%>/customer/customerTransfer" ,//url	                
							      //data:data,
							      success: function (result) {
							      	if(result.success){
							      		layer.msg("转移成功");
							      		layer.close(index);
							      		dataTable.ajax.reload();
							      	}
							      },
							      error : function(a,b,c) {	            	
							      }
							  }); 
					    }
					  }
				});
			  $('#transferForm').removeClass("hidden");
			  $("#people").attr("data-value","");
			  var dp=new MultiSelection (departmentList,function(code,name,level){
					if(level==6){
						$("#people").val(name);
						$("#people").attr("data-value",code);
						dp.hidden();
					}	
					
				},null,$("#people"),true);
		}
	});
				
	$(".ulItem li").on("click",function(){
		var sort=$(this).find("a").attr("data-value");
		dataParam.sort=sort;
		$("#dataParam").val(JSON.stringify(dataParam));
		dataTable.ajax.reload();
	});		
	
	 var language= {//国际语言转化
	            "oAria": {
	                "sSortAscending": " - click/return to sort ascending",
	                "sSortDescending": " - click/return to sort descending"
	            },
	            "sLengthMenu": "显示 _MENU_ 记录",
	            "sZeroRecords": "对不起，查询不到任何相关数据",
	            "sEmptyTable": "未有相关数据",
	            "sLoadingRecords": "正在加载数据-请等待...",
	            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
	            "sInfoEmpty": "当前显示0到0条，共0条记录",
	            "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
	            //"sProcessing": "<img src='../resources/user_share/row_details/select2-spinner.gif'/> 正在加载数据...",
	           // "sSearch": "模糊查询：",
	            //"sUrl": "",
	            //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
	            "oPaginate": {
	                "sFirst": "首页",
	                "sPrevious": " 上一页 ",
	                "sNext":" 下一页 ", 
	                "sLast": " 尾页 "
	            }
	        };
	 
	 var columns=[
		 	{ "data":"id",
			   "render": function(data, type, row, meta) {
				return '<input class="column" value="'+data+'" type="checkbox">';
			}},
		   { "data":"labeld","name":"labeld","title":"标签"},
			{ "data":"id","name":"customerId","title":"需求编号",
			   "render": function(data, type, row, meta) {
				return '<a  href="/customer/showCustomerInfo?demandId='+row.id+'&formName='+row.formName+'">'+data+'</a>';
			}},
			{ "data":"discStatus","name":"discStatus","title":"状态"},
			{ "data":"houseUses","name":"houseUses","title":"需求用途"},
			{ "data":"custName","name":"custName","title":"客户姓名"},
			{ "data":"sourceType","name":"sourceType","title":"客户来源"},
			{ "data":"areaCodes","name":"areaCodes","title":"需求区域",
				 "render": function(data, type, row, meta) {
					 var names="";
					 if(row.areaNames){
						 names+=row.areaNames;
					 }
					 if(row.regionNames){
						 names+=row.regionNames;
					 }
					 if(row.communityNames){
						 names+=row.communityNames;
					 }
						return names;
					}
			},
			{ "data":"minSpace","name":"minSpace","title":"需求面积",
				"render": function(data, type, row, meta) {
					return row.minSpace+"-"+row.maxSpace+"平米";
				}
			},
			{ "data":"minPrice","name":"minPrice","title":"需求价格",
				"render": function(data, type, row, meta) {
					var result="";
					if(row.minPrice&&row.maxPrice){
						result=(row.minPrice/1000000) +"-"+ (row.maxPrice/1000000)+"万元";
					}
					if(row.minPrice&&!row.maxPrice){
						result=(row.minPrice/1000000)+"万元以上";
					}
					if(!row.minPrice&&row.maxPrice){
						result=(row.maxPrice/1000000)+"万元以下";
					}
					return result;
				}
			},
			{ "data":"minRoom","name":"minRoom","title":"户型",
				"render": function(data, type, row, meta) {
					return row.minRoom+"-"+row.maxRoom+"室";
				}	
			},
			{ "data":"communicateStage","name":"communicateStage","title":"沟通阶段"},
			{ "data":"remark","name":"remark","title":"备注"},
			{ "data":"levelType","name":"levelType","title":"客源等级"},
			{ "data":"recordTime","name":"recordTime","title":"录入日期"},
			{ "data":"allFollowTime","name":"allFollowTime","title":"全员最后维护"},
			{ "data":"lastShowedTime","name":"lastShowedTime","title":"最后带看时间"},
			{ "data":"mainterFllowTime","name":"mainterFllowTime","title":"维护人最后维护"},
			{ "data":"maintainer","name":"maintainer","title":"维护人"},
			{ "data":"mainterDept","name":"mainterDept","title":"部门"}
	   ];
    var dataTable = $('#houseTable').DataTable({

        "autoWidth": true,//自动宽度
        "lengthChange": false,
        "pageLength": 10,
        "searching": false,
        "ordering": false,
        "oLanguage":language, 
        //"processing": true,
        "serverSide": true,
        "scrollY":"500px",
        "scrollX":true,
        "paging": true,
        "pagingType": "full_numbers",
         "ajax": {
        	"url":"<%=request.getContextPath()%>/customer/searchCustomerList",
        	"type": "POST",
        	"data": function ( d ) {
        	      return $.extend( {}, d, {
        	        "condition": $("#dataParam").val()
        	      } );
        	    }
        }, 
        "columnDefs": [
        	{"targets":0,
        	"orderable": false	},
        	{
        	"width":"100px",
            "targets": [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19],
            "render": function ( data,type,full, meta ) {
            	if(!data){
            		return "";
            	}
                return data;
              }
          } ],
        "columns": columns
    });

   

  

   //复选框单机事件
  $(".checkboxDiv :checkbox").on("click",function(){
	  var col=dataTable.column($(this).val()+':name');
	  col.visible(!col.visible());
  });
   
   //页面加载时初始化隐藏列
   $.ajax({
      type: "POST",//方法类型
      dataType: "json",//预期服务器返回的数据类型
      //contentType: 'application/json',
      data: {formName:"customer"},
      url: "<%=request.getContextPath()%>/customer/getTableColHiddenList" ,//url	                
      //data:data,
      success: function (result) {
      	if(result.success){
      		$(".checkboxDiv :checkbox").attr("checked","true");
      		if(result.data!=null&&result.data.columns!=null&&result.data.columns.length>0){
      			var cols=result.data.columns;
      			
      			for(var i=0;i<cols.length;i++){
      				if(cols[i]!=""){
      					$(".checkboxDiv input[value="+cols[i]+"]").removeAttr("checked");
          				if(dataTable.column(cols[i])!=null){
          					dataTable.column(cols[i]+':name').visible(false);
          				} 
      				}   				    			  
      		  	}
      		}
      	}
      },
      error : function(a,b,c) {	            	
      }
  }); 
   
   //保存自定义列表设置
  $("#saveBtn").on("click",function (){
	  var showCol=[];
	  var hidCol=[];
	  $(".checkboxDiv :checkbox").each(function(){
		  if($(this).is(':checked')) {
			  showCol.push($(this).val());
		    }else{
		    hidCol.push($(this).val());
		    }
	  });
	   /* for(var i=0;i<showCol.length;i++){
		  dataTable.column(showCol[i]+':name').visible(true);
	  }
	  
	  for(var i=0;i<hidCol.length;i++){
		  dataTable.column(hidCol[i]+':name').visible(false);
	  } */ 
	  var columns="";
	  if(hidCol.length>0){
		  columns=hidCol.join(",");
	  } 
	  layer.close(layerId);
	  $.ajax({
	      type: "POST",//方法类型
	      dataType: "json",//预期服务器返回的数据类型
	      //contentType: 'application/json',
	      data: {formName:"customer",columns:columns},
	      url: "<%=request.getContextPath()%>/customer/setTableColHidden" ,//url	                
	      //data:data,
	      success: function (result) {

	      },
	      error : function(a,b,c) {	            	
			debugger;
	      }
	  });
  });
   
   //显示自定义列表
  $("#hidColBtn").on("click",function(){
	  layerId=layer.open({
		  type: 1,
		  title: "自定义列表", //不显示标题
		  area:['670px','280px'],
		  content: $('.checkboxDiv'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	  $('.checkboxDiv').removeClass("hidden");
	  
  });
  
  //显示自定义列表
  $("#addBtn").hover(
	function(){
	  $(this).find("ul.dropdown-menu").addClass("show");	  
  	},
  	function(){
  	  $(this).find("ul.dropdown-menu").removeClass("show");	  
    }
  );
   var formName;
  $("#addBtn").find("li.addLi").on("click",function(){
	  var houseType=$(this).find("a").attr("data-value");
	  var houseTypeName=$(this).find("a").text();
	  formName=houseType+"_customer"
	  $("#addForm input[name=formName]").val(formName);
	  $("#addDetailForm input[name=houseUses]").val(houseTypeName);
	  $("#addDetailForm input[type=checkbox][value="+houseType+"]").attr("checked","true");
	  $("#addDetailForm input[name=houseUsesIds]").val(houseType);
	  layerId=layer.open({
		  type: 1,
		  title: "新增房源", //不显示标题
		  area:['550px','300px'],
		  content: $('#addForm'), 
		});
	  $('#addForm').removeClass("hidden");
	  $("#addForm select[name]").each(function(){
		  $(this).prev().val($(this).find("option:selected").text());
	  });
  });
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
		$("#xiaoqu .closeItem:last").on("click",function(event){
			event.stopPropagation();
			
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
			$(this).parent().remove();
		});
		dataAreas.push({"value":code,"type":level});
	},3,$("#xiaoqu"));
  var needAreas=[];
  var mAreas=new MultiSelection (communitList,function(code,name,level){
	  	for(var i=0;i<needAreas.length;i++){
	  		if(needAreas[i].type==level&&needAreas[i].value==code){
	  			return ;
	  		}
	  	}
		var item='<span data-value="'+code+'" data-type="'+level+'"  class="childItem">'+name+'<span class="closeItem">x</span></span>';
		$("#needAreaTitle").hide();
		$("#needArea").append(item);
		$("#needArea .closeItem:last").on("click",function(event){
			event.stopPropagation();
			
			if($("#needArea .childItem").length==0){
				$("#needAreaTitle").show();
			}
			var index;
			for(var i=0;i<dataAreas.length;i++){
		  		if(dataAreas[i].type==$(this).parent().attr("data-type")&&dataAreas[i].value==$(this).parent().attr("data-value")){
		  			index=i;
		  		}
		  	}
			needAreas.splice(index,1);
			$(this).parent().remove();
		});
		needAreas.push({"value":code,"type":level,"name":name});
	},3,$("#needArea"));
	var departmentList=JSON.parse('${departmentList}');
	var ms=new MultiSelection (departmentList,function(code,name,level){
		if(code!="0"){
			$("#department").text(name);
		}	
		ms.hidden();
	},null,$("#department"),true);
  //var ef = $("#addForm").easyform();
 /*  ef.success=function(ef){	  
	 
  }; */
  function showNextForm(){
	  layer.closeAll();
	  layerId=layer.open({
		  type: 1,
		  title: "新增房源", //不显示标题
		  area:['1000px','550px'],
		  content: $('#addDetailForm'), 
		});
	  $('#addDetailForm').removeClass("hidden"); 
	 
	  $("#addDetailForm select[name]").each(function(){
		  $(this).prev().val($(this).find("option:selected").text());
	  });
	  $('#addDetailForm input[name=custName]').val($("#custName").val());
	  $('#addDetailForm select[name=relateTypeId]').val($("#relateTypeId").val());
	  $('#addDetailForm input[name=relateTypeName]').val($("#relateTypeId option:selected").text());
	  
	 <%--  $.ajax({
          type: "POST",//方法类型
          dataType: "json",//预期服务器返回的数据类型
          //contentType: 'application/json',
          data: {buildingId:$("#addForm input[name=buildingHouseId]").val()},
          url: "<%=request.getContextPath()%>/house/queryBuildingInfo" ,	                
          //data:data,
          success: function (result) {
          	if(result.success){
          		  $("#addDetailForm input[name=floorCount]").val(result.data.floor);
          		$("#addDetailForm input[name=totalLayers]").val(result.data.floorTotal);
          		$("#addDetailForm input[name=elevatorCount]").val(result.data.unitElevatorCount);
          		$("#addDetailForm input[name=householdCount]").val(result.data.unitHouseCount);
          	}else{
          		layer.alert(result.msg);
          	}
          },
          error : function() {	            	
              layer.alert("异常！");
          }
      }); --%>
  }
  $("#nextBtn").click(function () {
	  showNextForm();
	  <%-- $.ajax({
          type: "POST",//方法类型
          dataType: "json",//预期服务器返回的数据类型
          //contentType: 'application/json',
          data: {houseId:$("#addForm input[name=buildingHouseId]").val()},
          url: "<%=request.getContextPath()%>/house/queryCountHousing" ,	                
          //data:data,
          success: function (result) {
          	if(result.success){
          		  if(result.data>0){
          			  layer.alert("该房源已经存在！请重新选择房号。")
          		  }else{
          			showNextForm();
          		  }
          	}else{
          		layer.alert(result.msg);
          	}
          },
          error : function() {	            	
              layer.alert("异常！");
          }
      }); --%>
  });  
  $("#addFormBtn").click(function () {
	  var params={};
	  var custom={};
	  $("#addForm input[name=areaCodes]").val("");
	  $("#addForm input[name=areaNames]").val("");
	  $("#addForm input[name=regionIds]").val("");
	  $("#addForm input[name=regionNames]").val("");
	  $("#addForm input[name=communityIds]").val("");
	  $("#addForm input[name=communityNames]").val("");
	  for(var i=0;i<needAreas.length;i++){
		  var code="";
		  var name="";
		  if(needAreas[i].type==1){
			 code= $("#addForm input[name=areaCodes]").val()+needAreas[i].value+",";
			 name= $("#addForm input[name=areaNames]").val()+needAreas[i].name+",";
			 $("#addForm input[name=areaCodes]").val(code);
			 $("#addForm input[name=areaNames]").val(name);
		  }
		  if(needAreas[i].type==2){
			 code= $("#addForm input[name=regionIds]").val()+needAreas[i].value+",";
			 name= $("#addForm input[name=regionNames]").val()+needAreas[i].name+",";
			 $("#addForm input[name=regionIds]").val(code);
			 $("#addForm input[name=regionNames]").val(name);
		 }
		 if(needAreas[i].type==3){
			 code= $("#addForm input[name=communityIds]").val()+needAreas[i].value+",";
			 name= $("#addForm input[name=communityNames]").val()+needAreas[i].name+",";
			 $("#addForm input[name=communityIds]").val(code);
			 $("#addForm input[name=communityNames]").val(name);
		 }
	  }
	  $("#addForm input[name]").each(function(){
		  if($(this).val()!=""){
			  if($(this).attr("class")==undefined){
				  params[$(this).attr("name")]=$(this).val();
			  }else if($(this).attr("class").indexOf("hidden")==-1){
				  params[$(this).attr("name")]=$(this).val();
			  }		  
		  }		  
	  });
	  $("#addForm select[name]").each(function(){
		  if($(this).attr("class")==undefined){
			  params[$(this).attr("name")]=$(this).val();
		  }else if($(this).attr("class").indexOf("hidden")==-1){
			  params[$(this).attr("name")]=$(this).val();
		  }	
	  });
	  $("#addDetailForm input[name]").each(function(){
		  if($(this).val()!=""){
			  if($(this).attr("class")==undefined){
				  params[$(this).attr("name")]=$(this).val();
			  }else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")==-1){
				  params[$(this).attr("name")]=$(this).val();
			  }	else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")>-1){
				  custom[$(this).attr("name")]=$(this).val();
			  }	
		  }
	  });
	  $("#addDetailForm select[name]").each(function(){
		  if($(this).attr("class")==undefined){
			  params[$(this).attr("name")]=$(this).val();
		  }else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")==-1){
			  params[$(this).attr("name")]=$(this).val();
		  }	else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")>-1){
			  custom[$(this).attr("name")]=$(this).val();
		  }	
	  });
	  
	  if(!$.isEmptyObject(custom)){
		  params.customData=custom;
	  }	  
	  var str = JSON.stringify(params);

	    $.ajax({
          type: "POST",//方法类型
          dataType: "json",//预期服务器返回的数据类型
          //contentType: 'application/json',
          data: {dataJson:str},
          url: "<%=request.getContextPath()%>/customer/addCustomer" ,	                
          //data:data,
          success: function (result) {
          	if(result.success){
          		layer.msg("保存成功！");
          		 layer.close(layerId); 
          		dataTable.ajax.reload();
          	}else{
          		layer.alert(result.msg);
          	}
          },
          error : function() {	            	
              layer.alert("异常！");
          }
      });  
  });

  $("#addForm select[name]").on("change",function(){
	  $(this).prev().val($(this).find("option:selected").text());
  });
  
  
  $("#addDetailForm select[name]").on("change",function(){
	  $(this).prev().val($(this).find("option:selected").text());
  });
 

var cid=1;
	
	$("#queryBtn").on("click",function(){

		dataParam.areas=dataAreas;
		dataParam.vagueData=vagueData;
		if(Object.keys(dataParam).length>0){
			$("#dataParam").val(JSON.stringify(dataParam));
		}else{
			$("#dataParam").val("");
		}
		dataTable.ajax.reload();
	});
});
 var layerId; 
 


	

	
</script>

</body>
</html>