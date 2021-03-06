<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格实例</title>
<%-- <%@include file="/common/common.jsp" %>  --%>


<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/bootstrap-3.3.7/css/bootstrap.min.css" >

<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/datatables/media/css/dataTables.bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/easyform/easyform.css" >
<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" > --%>
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
</style>
</head>
<body>
<div class="checkboxDiv hidden">
 <ul class="list-inline checkbox">
	<li><label><input type="checkbox" value="labeld">标签</label></li>
	<li><label><input type="checkbox" value="houseId">房源编号</label></li>
	<li><label><input type="checkbox" value="transactionType">类型</label></li>
	<li><label><input type="checkbox" value="discStatus">状态</label></li>
	<li><label><input type="checkbox" value="houseUses">用途</label></li>
	<li><label><input type="checkbox" value="areaName">区</label></li>
	<li><label><input type="checkbox" value="regionName">片区</label></li>
	<li><label><input type="checkbox" value="communityName">小区</label></li>
	<li><label><input type="checkbox" value="buildingsName">座栋</label></li>
	<li><label><input type="checkbox" value="buildingsUnitName">单元</label></li>
	<li><label><input type="checkbox" value="buildingHouseName">房号</label></li>
	<li><label><input type="checkbox" value="floorCount">楼层</label></li>
	<li><label><input type="checkbox" value="room">房型</label></li>
	<li><label><input type="checkbox" value="useSpace">使用面积</label></li>
	<li><label><input type="checkbox" value="buildSpace">建筑面积</label></li>
	<li><label><input type="checkbox" value="orientation">朝向</label></li> 
	<li><label><input type="checkbox" value="decoration">装修</label></li>
	<li><label><input type="checkbox" value="matching">配套</label></li>
	<li><label><input type="checkbox" value="resourceType">来源</label></li>
	<li><label><input type="checkbox" value="buildDates">建筑年代</label></li>
	<li><label><input type="checkbox" value="propertyType">产权性质</label></li>
	<li><label><input type="checkbox" value="sellingPrice">售价</label></li>
	<li><label><input type="checkbox" value="unitPrice">单价</label></li>
	<li><label><input type="checkbox" value="rentPrice">租价</label></li>
	<li><label><input type="checkbox" value="levelType">等级</label></li>
	<li><label><input type="checkbox" value="remark">备注</label></li>
	<li><label><input type="checkbox" value="entrustCode">委托编号</label></li>
	<li><label><input type="checkbox" value="keyCode">钥匙编号</label></li>
	<li><label><input type="checkbox" value="addTime">录入日期</label></li>
	<li><label><input type="checkbox" value="updateTime">修改日期</label></li>
	<li><label><input type="checkbox" value="allFollowTime">全员最后跟进</label></li>
	<li><label><input type="checkbox" value="mainterFllowTime">维护人最后跟进</label></li>
	<li><label><input type="checkbox" value="operator">维护人</label></li>
	<li><label><input type="checkbox" value="mainterDept">部门</label></li>
	<li><label><input type="checkbox" value="companyName">公司</label></li>
 </ul>		
 <button id="saveBtn" class="btn btn-primary">保存</button>	 
</div>

<div >	
<form id="addForm" class="form-horizontal hidden" style="margin:20px 0;">
		<input type="hidden" name="houseUsesId">
		<input type="hidden" name="houseUses">
		<div class="form-group form-group-sm">
	    <label  class="col-sm-3 control-label"><span class="bitian">*</span>交易类型</label>
	    <div class="col-sm-4">
	      <select class="form-control" style="width:100px;" id="transactionTypeId">
			  <option value="1">出售</option>
			  <option value="2">出租</option>
			  <option value="3">租售</option>
		 </select>
	    </div>
	    </div>
	    <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label">状态</label>
		    <div class="col-sm-9">
		      <input type="hidden" name="status">
		      <select class="form-control" name="statusId" style="width:100px;float:left;">
		      	 <c:forEach items="${houseStatusList}"  var="item">
		      	 <c:if test="${item.value!=11&&(item.type==1||item.type==3)}">
		      	<option value="${item.value }">${item.name}</option>
		      	</c:if>
		      	</c:forEach> 
			 </select>
			 <input type="hidden" name="discStatus">
			 <select class="form-control" name="discStatusId" style="float:left;">
				  <option value="2">公盘</option>
				   <option value="1">私盘</option>
			 </select>
			 <button type="button" class="btn btn-link">我的私盘</button>
		    </div>
	    
	    </div>
	  </div>
	
		<div class="form-group form-group-sm">
	    <label  class="col-sm-3 control-label"><span class="bitian">*</span>小区</label>
	    <div class="col-sm-7">
	      <div id="xiaoquSelect" class="dropdown rangeDiv moreItem">
				<div class="input-group rangeInput">
					<input type="hidden" name="communityIdSave">
					<input id="communityNameSave"   class="form-control input-xs input" style="background-color: #fff;"  data-easytip="position:top;" readonly  data-message="小区必填">
					<div class="input-group-addon">▼</div>
				</div>
			</div>
			<button type="button" id="showBuildBtn" style="margin-top:-20px;" class="btn btn-link">选择座栋</button>
	    </div>
	    </div>
	    
	    <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>门牌号</label>
		    <div class="col-sm-7">
		    <input type="hidden" name="buildingsId">
		    <input type="hidden" name="bulidingUnitId">
		    <input type="hidden" name="buildingHouseId">
		      <input type="text" name="buildingsName" class="form-control pull-left"  placeholder="座栋" style="width:70px;background-color: #fff;" readonly data-easytip="position:top;"  data-message="座栋必填">
			 <input type="text" name="buildingsUnitName" class="form-control pull-left"  placeholder="单元" style="width:70px;background-color: #fff;" readonly data-easytip="position:top;"   data-message="单元必填">
			 <input type="text" name="buildingHouseName" class="form-control pull-left"  placeholder="房号" style="width:70px;background-color: #fff;" readonly data-easytip="position:top;"   data-message="房号必填">
		    </div>
	    
	    </div>
	  </div>
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>楼层描述</label>
	    	<div class="col-sm-7">
	    		<input type="hidden" name="floorDes">
				 <select class="form-control" name="floorDesId" style="width:100px;">
					  <option value="1">底层</option>
					   <option value="2">低层</option>
					   <option value="3">中层</option>
					   <option value="4">高层</option>
					   <option value="5">顶层</option>
				 </select>
	    	</div>
	    </div>
	   </div>
	   <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label">是否发送绑定码</label>
	    	<div class="col-sm-7">
	    		<input type="checkbox" name="isSendBindCode" value="1">
	    	</div>
	    </div>
	   </div>
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<label  class="col-sm-3 control-label"><span class="bitian">*</span>姓名</label>
		    <div class="col-sm-7">
		      <input type="text" name="owner" class="form-control pull-left" style="width:100px;" data-easytip="position:top;" data-easyform="length:2 6;" data-message="姓名长度必须为2-6">
			   <input type="hidden" name="nameTitle">
			 <select name="nameType" class="form-control" style="float:left;">
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
	  <label  class="col-sm-9 control-label" style="color:red;font-size:12px;">* 固话与区号间请用"-"分隔，例如:022-8888888</label>
	  
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline col-sm-10 text-center">
	    	<button id="nextBtn" type="button" class="btn">下一步</button>
	    	<button type="button" class="btn btn-link">取消</button>
	    </div>
	   </div>
</form>
<form id="addDetailForm" class="form-horizontal hidden" style="margin:20px 0;">
	<div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<div class="col-sm-5">
	    	<label  class="pull-left control-label"><span class="bitian">*</span>房源标题</label>
		    <div class="col-sm-7">
		      <input type="text" name="titles" class="form-control pull-left" style="width:200px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
		    </div>
		    </div>
		    <div class="col-sm-6">
		    <label  class="pull-left control-label"><span class="bitian">*</span>推荐标签</label>
	    	<div class="col-sm-9">
	    		<div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="text" name="labeld" class="form-control pull-left" style="width:200px;background-color: #fff;" readonly data-easytip="position:top;"  data-message="房源标签必填">
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">满五年</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">学区房</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">地铁房</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="4">满两年</label></li>
					</ul>
				</div>		      
		      <label class="check"><input type="checkbox" name="isSynchron" value="1">与外网同步</label>
		    </div>
		    </div>
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	    <div class="form-inline">
	    	<div class="col-sm-3">
	    		<label  class="pull-left control-label"><span class="bitian">*</span>交易类型</label>
			    <div class="col-sm-7">
			      <input type="hidden" name="transactionType" >
			      <select name="transactionTypeId" class="form-control" style="width:100px;" >
					  <option value="1">出售</option>
					  <option value="2">出租</option>
					  <option value="3">租售</option>
				 </select>
			    </div>
	    	</div>
	    	
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">售价</label>
		    	<div class="col-sm-9">
			      <input type="text" name="sellingPrice" class="form-control pull-left prefix" style="width:100px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">万元</span>
			    </div>
		    </div>
		    
		    <div class="col-sm-4">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>租价</label>
		    	<div class="col-sm-9">
			      <input name="rentPrice" type="text" class="form-control pull-left" style="width:100px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <input type="hidden" name="rentPriceTypeName">
				      <select name="rentPriceTypeId" class="form-control" style="float:left;">
						  <option value="1">元/月</option>
						  <option value="2">元/季度</option>
						  <option value="3">元/年</option>
						  <option value="4">元/月</option>
						  <option value="5">元/平/月</option>
					 </select>
			    </div>
		    </div>
	    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
	  	<div class="col-sm-6">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>户型</label>
		    	<div class="col-sm-9">
			      <input type="text" name="room" class="form-control pull-left prefix" style="width:40px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">室</span>
			      <input type="text" name="hall" class="form-control pull-left prefix" style="width:40px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">厅</span>
			      <input type="text" name="toilet" class="form-control pull-left prefix" style="width:40px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">卫</span>
			      <input type="text" name="kitchen" class="form-control pull-left prefix" style="width:40px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">厨</span>
			      <input type="text" name="balcony" class="form-control pull-left prefix" style="width:40px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">阳台</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>建筑面积</label>
		    	<div class="col-sm-8">
			      <input type="text" name="buildSpace" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">平米</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>使用面积</label>
		    	<div class="col-sm-8">
			      <input type="text" name="useSpace" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">平米</span>
			    </div>
		    </div>
	  </div>
	  
	  <div class="form-group form-group-sm">
		  <div class="col-sm-3">
			    	<label  class="pull-left control-label"><span class="bitian">*</span>楼层</label>
			    	<div class="col-sm-9">
				      <input type="text" name="floorCount" class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">楼</span>
				      <span class="pull-left" style="font-size: 20px;margin-top: -2px;">/</span>
				      <input type="text" name="totalLayers" class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">楼</span>
				    </div>
			 </div>
			 <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>朝向</label>
		    	<div class="col-sm-8">
		    	  <input type="hidden" name="orientation">
			      <select  class="form-control" name="orientationId">
					  <option value="1">东</option>
					  <option value="2">西</option>
					  <option value="3">南</option>
					  <option value="4">北</option>
					  <option value="5">东南</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
			    	<label  class="pull-left control-label"><span class="bitian">*</span>梯户</label>
			    	<div class="col-sm-8">
				      <input type="text" name="elevatorCount" class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">梯</span>
				      <input type="text" name="householdCount"  class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">户</span>
				    </div>
			 </div>
			 <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>装修</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="decoration">
			      <select  class="form-control" style="" name="decorationId">
					  <option value="1">毛胚</option>
					  <option value="2">普装</option>
					  <option value="3">精装</option>
					  <option value="4">豪装</option>
					  <option value="5">简装</option>
				 </select>
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
						<input type="text" name="matching" class="form-control pull-left" style="width:200px;background-color: #fff;" readonly data-easytip="position:top;"  data-message="房源标签必填">
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
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>现状</label>
		    	<div class="col-sm-8">		    	
				  <div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="hidden" name="actualityId">
						<input type="text" name="actuality" class="form-control pull-left" style="width:110px;background-color: #fff;" readonly data-easytip="position:top;"  data-message="房源标签必填">
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">空置</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">在用</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">全新</label></li>
					</ul>
				</div>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>房屋类型</label>
		    	<div class="col-sm-8">
				 <div  class="dropdown rangeDiv addFormItem">
					<div class="input-group rangeInput ">
						<input type="hidden" name="houseTypeId">
						<input type="text" name="houseType" class="form-control pull-left" style="width:110px;background-color: #fff;" readonly data-easytip="position:top;"  data-message="房屋类型必填">
						<div class="input-group-addon">▼</div>
					</div>
					<ul class="dropdown-menu  multiCheckbox addFormUl" >
						<li class="addFormLi" ><label><input  type="checkbox" value="1">低层</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="2">多层</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="3">小高层</label></li>
						<li class="addFormLi"><label><input  type="checkbox" value="4">高层</label></li>
					</ul>
				</div>
			    </div>
		    </div>
	</div>  
	
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>建筑结构</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="structure">
			      <select  class="form-control" style="" name="structureId">
					  <option value="1">剪力墙结构</option>
					  <option value="2">钢筋结构</option>
					  <option value="3">砖结构</option>
					  <option value="4">钢混结构</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>建造年代</label>
		    	<div class="col-sm-8">
			      <input name="buildDates" type="text" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">年</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>产权性质</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="propertyType">
			      <select  class="form-control" style="" name="propertyTypeId">
					  <option value="1">商品房</option>
					  <option value="2">回迁房</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>产证日期</label>
		    	<div class="col-sm-8">
			      <input type="text" name="certificateDate" class="form-control pull-left" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			    </div>
		    </div>
	</div>
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>产权年限</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="propRightsLen">
			      <select  class="form-control" style="" name="propRightsLenId">
					  <option value="1">70年</option>
					  <option value="2">50年</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">房源税费</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="taxpayer">
			      <select  class="form-control" style="" name="taxpayerId">
					  <option value="1">买方承担</option>
					  <option value="2">卖方承担</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">交易年限</label>
		   		 <div class="col-sm-8">
		    	<input type="hidden" name="housingYears">
			      <select  class="form-control"  name="housingYearsId">
					  <option value="1">满一年</option>
					  <option value="2">满两年</option>
					  <option value="3">满三年</option>
					  <option value="4">满四年</option>
					  <option value="5">满五年</option>
				 </select>
				 </div>
			    </div>
		    <div class="col-sm-3">
		    	<label class="check"><input name="isOnly" type="checkbox" value="1">唯一住房</label>
		    	<label class="check"><input name="isUrgent" type="checkbox" value="1">急切</label>
		    	<label class="check"><input name="isFullAmount" type="checkbox" value="1">全款</label>
		    </div>
	</div>
	<hr>
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>看房方式</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="viewingType">
			      <select  class="form-control" style="" name="viewingTypeId">
					  <option value="1">提前预约</option>
					  <option value="2">有钥匙带看</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>来源</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="resourceType">
			      <select  class="form-control" style="" name="resourceTypeId">
					  <option value="1">上门</option>
					  <option value="2">电话</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
			    	<label  class="pull-left control-label">委托编号</label>
			    	<div class="col-sm-8">
				      <input name="entrustCode" type="text" class="form-control pull-left" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				    </div>
			    </div>
			    <div class="col-sm-3">
			    	<label  class="pull-left control-label">付款方式</label>
			    	<div class="col-sm-8">
			    	<input type="hidden" name="payType">
				      <select  class="form-control" style="" name="payTypeId">
						 <option value="1">押一年付</option>
					  	<option value="2">押一半年付</option>
					 </select>
				    </div>
			    </div>
	</div>	
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
		    	<label  class="pull-left control-label">首付</label>
		    	<div class="col-sm-8">
			      <input name="percentage" type="text" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">%</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">出售低价</label>
		    	<div class="col-sm-8">
			      <input name="minSellingPrice" type="text" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">万元</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label">出租低价</label>
		    	<div class="col-sm-8">
			      <input name="minRentPrice" type="text" class="form-control pull-left prefix" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			      <span class="suffix">元</span>
			    </div>
		    </div>
		    <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>上次交易日期</label>
		    	<div class="col-sm-7">
			      <input type="text" name="lastTradingTime" class="form-control pull-left" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			    </div>
		    </div>
	</div>  
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
			<label  class="pull-left control-label">房源核验码</label>
			<div class="col-sm-8">
				<input name="entrustCode" type="text" class="form-control pull-left" style="width:80px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			</div>
		</div>
		<div class="col-sm-3">
			 <label  class="pull-left control-label">抵押信息</label>
			 <div class="col-sm-8">
			 <input type="hidden" name="mortgageName">
			<select  class="form-control" style="" name="mortgageId">
				<option value="0">无抵押</option>
				<option value="1">有抵押</option>
			</select>
			</div>
		</div>
		<div class="col-sm-3">
			 <label  class="pull-left control-label">房本备件</label>
			 <div class="col-sm-8">
			<select  class="form-control" style="" name="isUploadCertificate">
				<option value="0">未上传</option>
				<option value="1">已上传</option>
			</select>
			</div>
		</div>
		<div class="col-sm-3">
			 <label  class="pull-left control-label">供暖方式</label>
			 <div class="col-sm-8">
			 <input type="hidden" name="heatingModeName">
			<select  class="form-control" style="" name="heatingModeId">
				<option value="1">集中供暖</option>
				<option value="2">无供暖</option>
			</select>
			</div>
		</div>
	</div>  
	<div class="form-group form-group-sm">
		<div class="col-sm-3">
			 <label  class="pull-left control-label">交易权属</label>
			 <div class="col-sm-8">
			 <input type="hidden" name="tradingRightsName">
			<select  class="form-control" style="" name="tradingRightsId">
				<option value="0">非共有</option>
				<option value="1">共有</option>
			</select>
			</div>
		</div>
	
	</div>
	<hr>
	<div>
		<ul class="custom-inline">
		<c:forEach var="item" items="${dynamicColumnList}">
			<li>
				<label  class="pull-left control-label">
				<c:if test='${item.fieldRequired!=null&&item.fieldRequired=="required"}'>
				<span class="bitian">*</span>
				</c:if>
				${item.title}</label>
			      <input  type="text" name="${item.field}"   class="form-control pull-left customColumn" style="width:100px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			</li>
		</c:forEach>
			
		</ul>
	</div>	     		     
	<hr>
	<div class="form-group form-group-sm">
		<div class="col-sm-12">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>备注</label>
		    	<div class="col-sm-11">
			      <input type="text" name="remark" class="form-control pull-left" style="width:900px;" data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
			    </div>
		    </div>
	</div>
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
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/bootstrap-3.3.7/js/bootstrap.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/easyform/easyform.js"></script>  
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/dataTables.bootstrap.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script> 

<script>
	var depDatas=[
		{'name':'vvv',code:'1','parentCode':'0','level':1},
		{'name':'sdsd',code:'2','parentCode':'10','level':2},
		{'name':'bgnuy',code:'3','parentCode':'2','level':3},
		{'name':'nth',code:'4','parentCode':'2','level':3},
		{'name':'vfghjkl',code:'5','parentCode':'1','level':2},
		{'name':'dgds',code:'6','parentCode':'5','level':3},
		{'name':'yy4y',code:'7','parentCode':'11','level':2},
		{'name':'mmmm',code:'8','parentCode':'3','level':2},
		{'name':'wewww',code:'9','parentCode':'4','level':3},
		{'name':'tip',code:'10','parentCode':'0','level':1},
		{'name':'vbnmkjtu',code:'11','parentCode':'0','level':1}
		
	];
	var dataParam={};//后台搜索参数
	var dataAreas=[];//区域、小区、片区
	var building={};//座栋查询
	var vagueData={};//门牌、电话等
	var status={};//房源状态
	
	var sellingPrice={};//价格区间
	
	
	$(".addFormItem .rangeInput").on("click",function(){
		$(".addFormItem .addFormUl").removeClass("show");
		$(this).next().toggleClass("show");
	});
	$(".addFormItem .addFormUl .addFormLi").on("click",function(){
		var values="";
		var names="";
		
		$(this).parent().find("input[type=checkbox]:checked").each(function(){
			values+=$(this).val()+",";
			names+=$(this).parent().text()+",";
		});
		$(this).parent().prev().find("input[type=text]").val(names);
		if($(this).parent().prev().find("input[type=hidden]").length>0){
			$(this).parent().prev().find("input[type=hidden]").val(values);
		}	
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
		laydate.render({
		  	elem: '#addDetailForm input[name=lastTradingTime]' //指定元素
		});
	});

	$(function () {
		$(".operator .btn-group").hover(
		  	function () {
		    	$(this).addClass("open");
		 	},
		  	function () {
		    	$(this).removeClass("open");
		  	}
		);
	
		var language= {//国际语言转化
			"oAria": {"sSortAscending": " - click/return to sort ascending",
		        "sSortDescending": " - click/return to sort descending"},
		        "sLengthMenu": "显示 _MENU_ 记录",
		        "sZeroRecords": "对不起，查询不到任何相关数据",
		        "sEmptyTable": "未有相关数据",
		        "sLoadingRecords": "正在加载数据-请等待...",
		        "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
		        "sInfoEmpty": "当前显示0到0条，共0条记录",
		        "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
		    "oPaginate": {
		        "sFirst": "首页",
		       	"sPrevious": " 上一页 ",
		        "sNext":" 下一页 ", 
		        "sLast": " 尾页 "
		    }
		};
		 
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
		  	var columns="";
		  	if(hidCol.length>0){
			  	columns=hidCol.join(",");
		  	} 
		  	layer.close(layerId);
		  	$.ajax({
		      	type: "POST",//方法类型
		      	dataType: "json",//预期服务器返回的数据类型
		      	data: {formName:"house",columns:columns},
		      	url: "<%=request.getContextPath()%>/house/setTableColHidden" ,//url	                
		      	success: function (result) {
	
		      	},
		     	error : function(a,b,c) {	            	
					debugger;
		      	}
		  	});
	  	});
	   
	  	var communitSel=new MultiSelection (communnityInfo,function(code,name,level){
			if(level==3){
				$("#xiaoquSelect input[name=communityIdSave]").val(code);
				cid=code;
				$("#communityNameSave").val(name);
				communitSel.hidden();
			}
		},3,$("#xiaoquSelect"));
	  	var m=new MultiSelection (communnityInfo,function(code,name,level){
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
		var departmentList=JSON.parse('${departmentList}');
		var ms=new MultiSelection (departmentList,function(code,name,level){
			if(code!="0"){
				$("#department").text(name);
			}	
			ms.hidden();
		},null,$("#department"),true);
	  	function showNextForm(){
		  	layer.close(newlayerid);
		  	var layerId=layer.open({
			  	type: 1,
			  	title: "新增房源", //不显示标题
			  	area:['1000px','650px'],
			  	content: $('#addDetailForm'), 
			});
		  	$('#addDetailForm').removeClass("hidden"); 	
		  	
		  	$('#addDetailForm select[name=transactionTypeId]').val($("#transactionTypeId").val());
		  	$("#addDetailForm select[name]").each(function(){
			  	$(this).prev().val($(this).find("option:selected").text());
		  	});
		  	$.ajax({
	          	type: "POST",//方法类型
	          	dataType: "json",//预期服务器返回的数据类型
	          	data: {buildingId:$("#addForm input[name=buildingHouseId]").val()},
	          	url: "<%=request.getContextPath()%>/house/queryBuildingInfo" ,	                
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
	      	});
	  	}
	  	$("#nextBtn").click(function () {
		  	$.ajax({
	          	type: "POST",//方法类型
	          	dataType: "json",//预期服务器返回的数据类型
	          	data: {houseId:$("#addForm input[name=buildingHouseId]").val()},
	          	url: "<%=request.getContextPath()%>/house/queryCountHousing" ,	                
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
	      	});
	  	});  
	  	$("#addFormBtn").click(function () {
		  	var params={};
		  	var custom={};
		  	$("#addForm input[name]").each(function(){
		  		if($(this).attr("name")=="communityIdSave" && $(this).val()!=""){
		  			params['communityId'] = $(this).val();
		  		}else if($(this).attr("name")!="communityIdSave" && $(this).val()!=""){
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
				  	}else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")>-1){
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
	          	data: {dataJson:str},
	          	url: "<%=request.getContextPath()%>/house/addHouse" ,	                
	          	success: function (result) {
	          		if(result.success){
	          			layer.msg("保存成功！请到房源列表进行数据查看");
	          		 	layer.closeAll(); 
	          		}else{
	          			layer.alert(result.msg);
	          		}
	          	},
	          	error : function() {	            	
	              	layer.alert("异常！");
	          	}
	      	}); 
	  	});
	  	$("#addForm select[name=statusId]").on("change",function(event){
		  	event.stopImmediatePropagation();
		  	if($(this).find("option:selected").val()!="1"){
			  	$(this).next().addClass("hidden"); 
			  	$(this).next().next().addClass("hidden"); 
		  	}else{
			  	$(this).next().removeClass("hidden"); 
			 	$(this).next().next().removeClass("hidden"); 
		  	}
		  	$(this).parent().find("input[name=status][type=hidden]").val($(this).find("option:selected").text());
	  	});
	  	$("#addForm select[name]").on("change",function(){
		 	$(this).prev().val($(this).find("option:selected").text());
	  	});
	  
	  
	  	$("#addDetailForm select[name]").on("change",function(){
		  	$(this).prev().val($(this).find("option:selected").text());
	  	});
	 
	  
		$("#showBuildBtn").click(function () {
			if(cid!=null){
				showBuildingDetails(cid);
			}	  
		}); 
		var cid=1;
	});


 	var layerId; 
 	var bid,uid,bname,uname;
 	var bid,uid,bname,uname;
 	function showBuildingDetails(cid){
		layerId=layer.open({
			 title:'查看销控',
			 type:1,
			 area:['800px','600px'],
			 content: $('#showBuilding')
		}); 
	  	$('#showBuilding').removeClass("layui-hide");
		table.render({
			id:'buildingNameTable'
		    ,elem: '#buildingNameTable'
		    ,height: 'full-200'
		    ,limit:1000
		    ,url: '/house/building/queryBuildingList' //数据接口
		    ,method:'post'
		    ,where:{cid:cid}
		    ,cols: [[ //表头
		    	{field:'name', title:'座栋名称',width:125
		    		,templet:function(d){
		    			return '<span style="color:#01AAED;cursor:pointer;width:100%;display:block;" lay-event="lookUnits" >'+d.name+d.buildUnitAlias+'</span>';
		    		}
		    	}
		    ]]
		     ,done: function(res, curr, count){
		    	if(count>0){
		    		bid=res.data[0].id;
			 		bname=res.data[0].name+res.data[0].buildUnitAlias;
		    		loadUnitNameTable(res.data[0].id);
		    	}else{
		    		loadUnitNameTable(0);
		    	}	    	
		      } 
		  });
		
		 table.on('tool(buildingNameTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data //获得当前行数据
		    ,layEvent = obj.event; //获得 lay-event 对应的值
		    if(layEvent === 'lookUnits'){
		    	bid=data.id;
		 		bname=data.name+data.buildUnitAlias;
		    	loadUnitNameTable(data.id);
		    } 
		  });  
	}
	function loadUnitNameTable(bid){
		$("#buildingNameTable").parent().find("tr[data-index=0]").addClass("layui-table-click");
		table.render({
			  id:'unitNameTable',
		    elem: '#unitNameTable'
		    ,height: 'full-200'
		    ,limit:50
		    ,url: '/house/building/queryBuildingUnitList' //数据接口
		    ,method:'post'
		    ,where:{bid:bid}
		    //,page: true //开启分页
		    ,cols: [[ //表头
		    	{field:'name', title:'单元名称',width:125
			    	,templet:function(d){
			    		if(d.name==""){
			    			return '<span lay-event="lookHouse" style="color:#01AAED;cursor:pointer;font-size:6px;width:100%;display:block;>无单元</span>'
			    		}
			    		return '<span lay-event="lookHouse" style="color:#01AAED;cursor:pointer;font-size:6px;width:100%;display:block;">'+d.name+d.unitAlias+'</span>'
			    	}
		    	}
		    ]]
		    ,done: function(res, curr, count){
		    	if(count>0){
		    		uid=res.data[0].id;
			    	uname=res.data[0].name+res.data[0].unitAlias;
		    		loadHouseNO(res.data[0].id,res.data[0].unitHouseCount,res.data[0].houseStartNumber);
		    	}else{
		    		$("#showHouses").empty();
		    	}	    	
		      }
		  });
		table.on('tool(unitNameTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
		    ,layEvent = obj.event; //获得 lay-event 对应的值

		    if(layEvent === 'lookHouse'){
		    	uid=data.id;
		    	uname=data.name+data.unitAlias;
		    	loadHouseNO(data.id,data.unitHouseCount,data.houseStartNumber);
		    } 
		}); 
	}

	function loadHouseNO(uid,unitHouseCount,houseStartNumber){
		$("#unitNameTable").parent().find("tr[data-index=0]").addClass("layui-table-click");
		$("#showHouses").empty();
		$.ajax({
		    type: "POST",//方法类型
		    dataType: "json",//预期服务器返回的数据类型
		    data: {buid:uid},
		    url: "<%=request.getContextPath()%>/house/building/queryHouses" ,	                
		    success: function (result) {console.log(result);
		    	if(result.success){
		    		if(result.data!=null){	 
		    			$("#showHouses").append('<thead><tr></tr></thead>');
		    			for(var h=houseStartNumber;h<(unitHouseCount+houseStartNumber);h++){
		    				$("#showHouses>thead>tr").append('<th>第'+h+'户</th>');
		    			}
		    			$("#showHouses").append('<tbody></tbody>');
		    			var startNO=houseStartNumber;
		    			for(var i=0;i<result.data.length;i++){
		    				//判断楼层是否读完
		    				if(startNO>=(unitHouseCount+houseStartNumber)){
		    					startNO=houseStartNumber;
		    				}
		    				if(startNO==houseStartNumber){
		    					$("#showHouses tbody").append('<tr floor="'+result.data[i].floor+'"><td class="houseTd" data-id="'+result.data[i].id+'" >'+result.data[i].houseNumber+'</td></tr>');
		    				}else{
		    					$("#showHouses tbody tr[floor="+result.data[i].floor+"]").append('<td class="houseTd" data-id="'+result.data[i].id+'" >'+result.data[i].houseNumber+'</td>');
		    				}
		    				startNO++;
		    			}
		    			$("#showHouses td.houseTd").on("click",function(){
		    				$("#addForm input[name=buildingsName]").val(bname);
		    				$("#addForm input[name=buildingsId]").val(bid);
		    				$("#addForm input[name=buildingsUnitName]").val(uname);
		    				$("#addForm input[name=bulidingUnitId]").val(uid);
		    				$("#addForm input[name=buildingHouseName]").val($(this).text());
		    				$("#addForm input[name=buildingHouseId]").val($(this).attr("data-id"));
		    				layer.close(layerId);
		    			});
		    		}
		  				
		    	}else{
		    		layer.msg(result.msg);
		    	}
		    },
		    error : function() {	            	
		        layer.alert("异常！");
		    }
		});
	}
	
</script>
</body>
</html>