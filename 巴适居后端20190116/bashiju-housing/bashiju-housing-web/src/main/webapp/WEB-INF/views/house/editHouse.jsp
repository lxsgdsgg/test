<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="/common/common.jsp" %> 
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/bootstrap-3.3.7/css/bootstrap.min.css" >
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
<form id="addDetailForm" class="form-horizontal" style="margin:20px 0;">
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
				      <input type="text"  class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">楼</span>
				      <span class="pull-left" style="font-size: 20px;margin-top: -2px;">/</span>
				      <input type="text"  class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">楼</span>
				    </div>
			 </div>
			 <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>朝向</label>
		    	<div class="col-sm-8">
		    	  <input type="hidden" name="orientation">
			      <select  class="form-control" name="orientationId">
					  <option value="1">东</option>
					  <option value="2">南</option>
					  <option value="3">西</option>
					  <option value="4">北</option>
					  <option value="5">东南</option>
					  <option value="6">东北</option>
					  <option value="7">西南</option>
					  <option value="8">西北</option>
				 </select>
			    </div>
		    </div>
		    <div class="col-sm-3">
			    	<label  class="pull-left control-label"><span class="bitian">*</span>梯户</label>
			    	<div class="col-sm-8">
				      <input type="text"  class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">梯</span>
				      <input type="text"   class="form-control pull-left prefix" style="width:40px;background-color: #fff;" readonly data-easytip="position:top;" data-easyform="length:2 40;" data-message="房源标题长度只能为2-40">
				      <span class="suffix">户</span>
				    </div>
			 </div>
			 <div class="col-sm-3">
		    	<label  class="pull-left control-label"><span class="bitian">*</span>装修</label>
		    	<div class="col-sm-8">
		    	<input type="hidden" name="decoration">
			      <select  class="form-control" style="" name="decorationId">
					  <option value="1">毛胚</option>
					  <option value="2">清水</option>
					  <option value="3">简装</option>
					  <option value="4">中装</option>
					  <option value="5">精装</option>
					  <option value="6">豪装</option>
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
		    	<input type="hidden" name="houseType">
			      <select  class="form-control" style="" name="houseTypeId">
					  <option value="1">低层</option>
					  <option value="2">多层</option>
					  <option value="3">小高层</option>
				 </select>
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
			      <select  class="form-control" style="" name="housingYearsId">
					  <option value="1">满一年</option>
					  <option value="2">满两年</option>
					  <option value="3">满三年</option>
					  <option value="4">满四年</option>
					  <option value="5">满五年</option>
				 </select>
				 </div>
			    </div>
		    <div class="col-sm-3">
		    	<c:forEach var="label" items="${labelList}">
			      	<label class="check"><input color="${label.color}" class="houselabel" type="checkbox" value="${label.code}">${label.name}</label>
			    </c:forEach>
		    	<!-- <label class="check"><input name="isOnly" type="checkbox" value="1">唯一住房</label>
		    	<label class="check"><input name="isUrgent" type="checkbox" value="1">急切</label>
		    	<label class="check"><input name="isFullAmount" type="checkbox" value="1">全款</label> -->
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
		 <a href="/house/showHouseInfo?houseId=${houseId}&formName=${formName}" >返回</a> 
	</div>
	<input type="hidden" name="id">
</form>

<script>
	var data=JSON.parse('${info}');
	//alert(JSON.stringify(data.customInfo.customValue));
	console.log(data);
	$("#addDetailForm input[name]").each(function(){
		if(data.houseInfo[$(this).attr("name")]!=null){
			$(this).val(data.houseInfo[$(this).attr("name")]);
		}else if(data.customInfo!=null&&data.customInfo.customValue!=undefined&&data.customInfo.customValue[$(this).attr("name")]!=null){
			$(this).val(data.customInfo.customValue[$(this).attr("name")]);
		}
	});
	$("#addDetailForm select[name]").each(function(){
		if(data.houseInfo[$(this).attr("name")]!=null){
			$(this).val(data.houseInfo[$(this).attr("name")]);
		}
	});
	
	  $("#addDetailForm select[name]").on("change",function(){
		  $(this).prev().val($(this).find("option:selected").text());
	  });
	  
	  $(".addFormItem .addFormUl .addFormLi").on("click",function(){
			var values="";
			var names="";
			
			$(this).parent().find("input[type=checkbox]:checked").each(function(){
				values+=$(this).val()+";";
				names+=$(this).parent().text()+";";
			});
			$(this).parent().prev().find("input[type=text]").val(names);
			if($(this).parent().prev().find("input[type=hidden]").leng>0){
				$(this).parent().prev().find("input[type=hidden]").val(values);
			}	
		});
	  
	  $(".addFormItem .rangeInput").on("click",function(){
			$(".addFormItem .addFormUl").removeClass("show");
			$(this).next().toggleClass("show");
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
	  $("#addFormBtn").on("click",function(){
		  var params={};
		  var custom={};
		  var houselabelList = [];
		  $("#addDetailForm input[name]").each(function(){
			  if($(this).val()!=""){
				  if($(this).attr("class")==undefined){
					  params[$(this).attr("name")]=$(this).val();
				  }else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")==-1){
					  params[$(this).attr("name")] = $(this).val();
				  }	else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")>-1){
					  custom[$(this).attr("name")]=$(this).val();
				  }	
			  }
		  });
		  $("#addDetailForm select[name]").each(function(){
			  if($(this).attr("class")==undefined){
				  params[$(this).attr("name")]=$(this).val();
			  }else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")==-1){
				  params[$(this).attr("name")] = $(this).val();
			  }	else if($(this).attr("class").indexOf("hidden")==-1&&$(this).attr("class").indexOf("customColumn")>-1){
				  custom[$(this).attr("name")]=$(this).val();
			  }	
		  });
		  
		  //标签
		  $(".houselabel:checked").each(function(){
			  var houselabel = {};
			  houselabel.code = $(this).val();
			  houselabel.name = $(this).parent().text();
			  houselabel.color = $(this).attr("color");
			  houselabelList.push(houselabel);
		  });
		  
		  if(!$.isEmptyObject(custom)){
			  params.customData=custom;
		  }	  
		  if(houselabelList.length>0){
			  params.houselabelList=houselabelList;
		  }	
		  var str = JSON.stringify(params);
		   $.ajax({
	          type: "POST",//方法类型
	          dataType: "json",//预期服务器返回的数据类型
	          //contentType: 'application/json',
	          data: {dataJson:str,formName:data.houseInfo.houseUsesId},
	          url: "<%=request.getContextPath()%>/house/updateHousing",	                
	          //data:data,
	          success: function (result) {
	          	if(result.success){
	          		layer.msg("保存成功！");
	          	}else{
	          		layer.alert(result.msg);
	          	}
	          },
	          error : function() {	            	
	              layer.alert("异常！");
	          }
	      }); 
	  });
	 
</script>
</body>
</html>