<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/Mo.css">
	
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/manage-platform/js/Mo.js"></script>
	</head>
	<script type="text/javascript">
	var path = "<%=request.getContextPath()%>";
	var formUseName = "houseinfo";
	
	function addField(type){
		//required unique candel fieldtype="1 2" englishname="phone">
		$(".formitem").removeClass("droppablecss");
		if(type==1){
			var tableid = $(".form-item1").find(".saveid").val();
			var divs = $(".form-item1").find(".newfield");
			//alert(divs.length);
			var delFieldId = $(".form-item1").find(".delField").val();
		}else if(type==2){
			var tableid = $(".form-item2").find(".saveid").val();
			var divs = $(".form-item2").find(".newfield");
			var delFieldId = $(".form-item2").find(".delField").val();
		}else if(type==3){
			var tableid = $(".form-item3").find(".saveid").val();
			var divs = $(".form-item3").find(".newfield");
			var delFieldId = $(".form-item3").find(".delField").val();
		}
			
			
			//var div2s = $(".move").not(".newfield");
			
			var fieldattributes = [];
			
			for(var i=0;i<divs.length;i++){
				
				var attribute = {};
				
				attribute.tableId = tableid
				var fieldname = $(divs[i]).find("label").text();
				attribute.fieldChinaName=fieldname;
				
				var englishname = $(divs[i]).attr("englishname");
				attribute.fileldDbName=englishname;
			
				var required = $(divs[i]).attr("required");
				if(required!=undefined){
					attribute.required="1";
				}
				
				var unique = $(divs[i]).attr("unique");
				if(unique!=undefined){
					attribute.dataUnique="1";
				}
				
				var candel = $(divs[i]).attr("candel");
				if(candel!=undefined){
					attribute.isCanDel="1";
				}
				
				var fieldtype = $(divs[i]).attr("fieldtype");
				attribute.fieldDbType=fieldtype;
				
				var defaultvalue = $(divs[i]).attr("defaultvalue");
				if(defaultvalue!=undefined){
					attribute.defaultvalue=defaultvalue;
				}
				
				
				fieldattributes.push(attribute);
				
			}
			if(type==1){
				$(".form-item1").find(".newfield").removeClass("newfield");
				var formHtml = $(".form-box1").html();
				var divs =  $(".form-item1 div.move");
				var formItem = [];
				
				//遍历general下的
				var layrow = $(".form-item1").find(".general").find(".layui-row");
				for(var i=0;i<layrow.length;i++){
					var row = [];
					var laycol = $(layrow[i]).find(".formitem");
					for(var j=0;j<laycol.length;j++){
						var col = [];
						var layitem = $(laycol[j]).find(".move");
						for(var k=0;k<layitem.length;k++){
							var name = $(layitem[k]).attr("englishname");
							var cnName = $(layitem[k]).find("label").text();
							var item = {
									name:name,
									cnName:cnName,
									type:'general'
								}
							col.push(item);
						}
						row.push(col);
						
					}
					formItem.push(row);
				}
				
				//遍历 detail下的
				var layrow = $(".form-item1").find(".detail").find(".layui-row");
				for(var i=0;i<layrow.length;i++){
					var row = [];
					var laycol = $(layrow[i]).find(".formitem");
					for(var j=0;j<laycol.length;j++){
						var col = [];
						var layitem = $(laycol[j]).find(".move");
						for(var k=0;k<layitem.length;k++){
							var name = $(layitem[k]).attr("englishname");
							var cnName = $(layitem[k]).find("label").text();
							var item = {
									name:name,
									cnName:cnName,
									type:'detail'
								}
							col.push(item);
						}
						row.push(col);
						
					}
					formItem.push(row);
				}
			}else if(type==2){
				$(".form-item2").find(".newfield").removeClass("newfield");
				var formHtml = $(".form-box2").html();
				var divs =  $(".form-item2 div.move");
				var formItem = [];
				var layrow = $(".form-item2").find(".layui-row");
				for(var i=0;i<layrow.length;i++){
					var row = [];
					var laycol = $(layrow[i]).find(".formitem");
					for(var j=0;j<laycol.length;j++){
						var col = [];
						var layitem = $(laycol[j]).find(".move");
						for(var k=0;k<layitem.length;k++){
							var name = $(layitem[k]).attr("englishname");
							var cnName = $(layitem[k]).find("label").text();
							var item = {
									name:name,
									cnName:cnName,
									type:''
								}
							col.push(item);
						}
						row.push(col);
						
					}
					formItem.push(row);
				}
				
			}else if(type==3){
				$(".form-item3").find(".newfield").removeClass("newfield");
				var formHtml = $(".form-box3").html();
				var divs =  $(".form-item3 div.move");
				var formItem = [];
				var layrow = $(".form-item3").find(".layui-row");
				for(var i=0;i<layrow.length;i++){
					var row = [];
					var laycol = $(layrow[i]).find(".formitem");
					for(var j=0;j<laycol.length;j++){
						var col = [];
						var layitem = $(laycol[j]).find(".move");
						for(var k=0;k<layitem.length;k++){
							var name = $(layitem[k]).attr("englishname");
							var cnName = $(layitem[k]).find("label").text();
							var item = {
									name:name,
									cnName:cnName,
									type:''
								}
							col.push(item);
						}
						row.push(col);
						
					}
					formItem.push(row);
				}
			}
			
			//console.log(fieldattributes);
			
			var data = {
					tableid:tableid,
					delFieldId:delFieldId,
					company:"11",
					formHtml:formHtml,
					formItem:formItem,
					fieldattributes:fieldattributes
					
					
			}
		
		
		
		//console.log(data);
			
		
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/manage/addAndDelField" ,//url	                
	            //data:data,
	            success: function (result) {
	            	console.log(result);
	            	console.log(result.msg);
	            	console.log(result.success);
	            	if(result.success){
	            		alert("添加成功！");
	            		
	            		
	            		
	            		
	            		
	            	}else{
	            		alert()
	            	}
	            	
	            },
	            error : function() {
	                alert("异常！");
	            },
	            beforeSend : function() {
	            	//表单验证
	                //return $("#form").valid();
	            }
	        }); 
		
		
	}
		
	<%-- function delField(type){
		
		
		if(type==1){
			var tableid = $(".form-item1").find(".saveid").val();
			var delFieldId = $(".form-item1").find(".delField").val();
			var html = $(".form-box1").html();
		}else if(type==2){
			var tableid = $(".form-item1").find(".saveid").val();
			var delFieldId = $(".form-item1").find(".delField").val();
			var html = $(".form-box2").html();
		}else if(type==3){
			var tableid = $(".form-item1").find(".saveid").val();
			var delFieldId = $(".form-item1").find(".delField").val();
			var html = $(".form-box3").html();
		}
		
		$.post("<%=request.getContextPath()%>/manage/delField",{tableId:tableid,delFieldId:delFieldId,companey:"11",formHtml:html},function(){
			
		})
		
	} --%>
			
$(function(){
		
		
	})
	 
	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-row">
					<div class="col-md-2 mo-form-left" style="z-index:10000;">
						<div class="mo-form-left-head col-md-12">
							通用表单元素
						</div>
						
						<div class="col-md-12" style="font-weight:900;border-bottom:1px #f1f1f1 solid;">选择列</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item layoutdrag" layout="1">
							<span class=""></span> 一列式
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item layoutdrag" layout="2">
							<span class=""></span> 两列式
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item layoutdrag" layout="3">
							<span class=""></span> 三列式
						</div>
						
						<div class="col-md-12" style="font-weight:900;border-bottom:1px #f1f1f1 solid;">预设项</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="1">
							<span class="glyphicon glyphicon-pencil"></span> 梯户
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="2">
							<span class="glyphicon glyphicon-pencil"></span>  朝向
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="3">
							<span class="glyphicon glyphicon-pencil"></span> 坐落
						</div>
						<div class="col-md-12" style="font-weight:900;border-bottom:1px #f1f1f1 solid;">自定义项</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="4">
							<span class="glyphicon glyphicon-pencil"></span> 输入框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="5">
							<span class="glyphicon glyphicon-align-center"></span> 多文本
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="6">
							<span class="glyphicon glyphicon-check"></span> 多选框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="7">
							<span class="glyphicon glyphicon-record"></span> 单选框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="8">
							<span class="glyphicon glyphicon-collapse-down"></span> 下拉框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="9">
							<span class="glyphicon glyphicon-list-alt"></span> 日期
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="10">
							<span class="glyphicon glyphicon-time"></span> 时间
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="11">
							<span class="glyphicon glyphicon-file"></span> 文件上传
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="12">
							<span class="glyphicon glyphicon-edit"></span> 分隔符
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="13">
							
						</div>
					</div>
					<div class="col-md-2 mo-form-right">
						<div class="mo-form-right-head col-md-12">
							字段设置
						</div>
						<form class="layui-form">
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label >名称</label>
							    <input type="text" class="form-control fieldname_input"  placeholder="请输入名称">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label >英文名称</label>
							    <input type="text" class="form-control fieldenglishname_input"  placeholder="请输入名称">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label for="description">描述</label>
							    <textarea class="form-control description" rows="3"></textarea>
							</div>
						</div>
						
						<div class="layui-form-item">
						    <label class="layui-form-label">仅能读</label>
						    <div class="layui-input-block readonly">
						      <input type="radio" name="readonly" value="0" title="否" checked>
						      <input type="radio" name="readonly" value="1" title="是" >
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">必填项</label>
						    <div class="layui-input-block required">
						      <input type="radio" name="required" value="0" title="否" checked>
						      <input type="radio" name="required" value="1" title="是" >
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">值唯一</label>
						    <div class="layui-input-block unique">
						      <input type="radio" name="unique" value="0" title="否" checked>
						      <input type="radio" name="unique" value="1" title="是" >
						    </div>
						  </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label">可删除</label>
						    <div class="layui-input-block candel">
						      <input type="radio" name="candel" value="0" title="否" checked>
						      <input type="radio" name="candel" value="1" title="是" >
						    </div>
						  </div>
						  
						  <div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<label for="name">数据类型</label>
							<select class="fieldtype" lay-filter="fieldtype">
								<option></option>
								<option value="1-1">手机号</option>
								<option value="1-2">身份证</option>
								<option value="1-3">邮箱</option>
								<option value="3">整数</option>
								<option value="4">小数</option>
								<option value="1">简单文字</option>
								<option value="2">多文字</option>
								<option value="5">日期</option>
								<option value="6">时间</option>
								
							</select>
						</div>
						  
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom optionuse">
							<div class="form-group mo-form-right-group">
							    <label for="name">备选值（用于单选、复选、下拉等）</label>
							    <input type="text" class="form-control option-group"  placeholder="请输入">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom optionuse">
							<div class="form-group mo-form-right-group">
							    <label for="name">默认选中值</label>
							    <input type="text" class="form-control default-option"  placeholder="请输入">
							</div>
						</div>
						
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<label for="name">动态数据源</label>
							<select class="datasource" lay-filter="datasource">
								<option></option>
								<option value="1">部门</option>
								<option value="2">职位</option>
								<option value="3">其他</option>
							</select>
						</div>
						</form>
					</div>
					
					<div class="layui-col-md8 mo-form layui-col-md-offset2 " >
					<div class="layui-tab" lay-filter="myTab">
						<ul class="layui-tab-title">
							<li class="icon-ex layui-this">表单设置</li>
							<li class="icon-ex">其他设置</li>
						</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<div class="layui-form">
								<div class="layui-form-item">
								    <label class="layui-form-label">选择表单</label>
								    <div class="layui-input-block ">
								      <select name="select-form" lay-filter="selectform">
								        <option value="0">房源信息</option>
								        <option value="1">房东信息</option>
								         <option value="2">其他信息</option>
								      </select>
								    </div>
								  </div>
							</div>
							  
						 <div class="form-title1">房源信息表单</div>
						 
						 
						<div class="form-box form-box1">
						<form class="layui-form form-item form-item1" tabletype="father" name="houseinfo"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
						 
						  <input type="hidden" class="delField"  value=""/>
						  <input type="hidden" class="saveid" value="1">
						  <div class="title-tag">一、房源概要信息</div>
						  <div class="general">
						  	<div class="layui-row">
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable" flag="1" englishname="houseName" basefield="1" fieldtype="1">
									    <label class="layui-form-label">名称</label>
									    <div  class="layui-input-block">
									      <input type="text" name="houseName" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									 </div>
						  		</div>
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable"  englishname="buildArea" basefield="1" fieldtype="4">
									    <label class="layui-form-label">建筑面积</label>
									    <div  class="layui-input-block">
									      <input type="text" name="buildArea" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									 </div>
						  		</div>
						  </div>
						  
						  
						  <div class="layui-row">
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable"  englishname="usageArea" basefield="1" fieldtype="4">
									    <label class="layui-form-label">使用面积</label>
									    <div  class="layui-input-block">
									      <input type="text" name="usageArea" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									 </div>
						  		</div>
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable" englishname="sumPrice" basefield="1" fieldtype="4">
									    <label class="layui-form-label">房源售价</label>
									    <div class="layui-input-block">
									      <input type="text" name="sumPrice" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									 </div>
						  		</div>
						  		
						  		
						  </div>
						  
						  </div>  <!-- 概要信息结束 -->
						  
						  <div class="title-tag">二、房源详细信息</div>
						  <div class="detail">
						  	 <div class="layui-row">
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable" englishname="unitPrice" basefield="1" fieldtype="4">
									    <label class="layui-form-label">房源单价</label>
									    <div class="layui-input-block">
									      <input type="text" name="unitPrice" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										 </div>
						 		 	</div>
						  		</div>
						  		<div class="layui-col-md6 formitem">
						  			<div class="layui-form-item move resizable" englishname="decoration">
									    <label class="layui-form-label">装修</label>
									    <div class="layui-input-block ">
									      <input type="radio" name="decoration" value="毛坯" title="毛坯" class="value-box">
									      <input type="radio" name="decoration" value="普装" title="普装" class="value-box">
									      <input type="radio" name="decoration" value="精装" title="精装" checked class="value-box">
									    </div>
									</div>
						  		</div>
						  		
						  </div>
						  
						  <div class="layui-row">
					  		<div class="layui-col-md12 formitem">
					  			<div class="layui-form-item move resizable" englishname="infrastructure" datasource='3'>
								    <label class="layui-form-label">房源配套</label>
								    <div class="layui-input-block ">
								      <input type="checkbox" name="infrastructure" title="学校" value="学校" class="value-box">
								      <input type="checkbox" name="infrastructure" title="地铁" value="地铁" class="value-box">
								      <input type="checkbox" name="infrastructure" title="医院" value="医院" class="value-box">
								      <input type="checkbox" name="infrastructure" title="超市" value="超市" class="value-box">
								    </div>
								</div>
					  		</div>
						  		
						  </div>
						  
						  <div class="layui-row">
						  		
						  		<div class="layui-col-md12 formitem">
						  			<div class="layui-form-item move resizable" englishname="houseType" datasource='2'>
									    <label class="layui-form-label">房源户型</label>
									    <div class="layui-input-block ">
									      <input type="radio" name="houseType" value="一室两厅" title="一室两厅"  class="value-box">
									      <input type="radio" name="houseType" value="两室两厅" title="两室两厅"  class="value-box">
									      <input type="radio" name="houseType" value="三室两厅" title="三室两厅"  class="value-box">
									    </div>
									</div>
						  		
						  		</div>
						  </div>
						  
						  <div class="layui-row">
						  		<div class="layui-col-md6 formitem ">
						  			<div class="layui-form-item move resizable" englishname="floorHeight" basefield="1" fieldtype="4">
									    <label class="layui-form-label">层高</label>
									    <div class="layui-input-block">
									      <input type="text" name="floorHeight" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									</div>
						  		</div>
						  		<div class="layui-col-md6 formitem ">
						  			<div class="layui-form-item move resizable" englishname="floor" basefield="1" fieldtype="4">
									    <label class="layui-form-label">所在层</label>
									    <div class="layui-input-block">
									      <input type="text" name="floor" placeholder="请输入" autocomplete="off" class="layui-input value-box">
									    </div>
									</div>
						  		
						  		</div>
						  </div>
						  
						  <div class="layui-row">
						  		<div class="layui-col-md12 formitem ">
						  			<div class="layui-form-item layui-form-text move resizable" englishname="location">
									    <label class="layui-form-label">坐落</label>
									    <div class="layui-input-block ">
									      <textarea placeholder="请输入内容" name="location" class="layui-textarea value-box"></textarea>
									    </div>
									    <span class="glyphicon glyphicon-remove del-span" style="display:none;" onclick="delfield(this);"></span>
									</div>
						  		
						  		</div>
						  
						  </div>
						  
						  <div class="layui-row">
						  		<div class="layui-col-md12 formitem ">
						  			<div class="layui-form-item layui-form-text move resizable" englishname="description">
									    <label class="layui-form-label">房源描述</label>
									    <div class="layui-input-block ">
									      <textarea placeholder="请输入内容" name="description" class="layui-textarea value-box"></textarea>
									    </div>
									    <span class="glyphicon glyphicon-remove del-span" style="display:none;" onclick="delfield(this);"></span>
									</div>
						  		
						  		</div>
						  
						  </div>
						  
						  <p class="appendField"></p>
						  
						  </div>
						</form>	
						
						</div>
						<div class="layui-form-item save-btn move resizable btn1">
						    <div class="layui-input-block">
						      <button type="button" class="layui-btn"  lay-filter="*" onclick="addField(1);">保存</button>
						    </div>
						  </div>
						  
						 <div class="form-title2" style="display:none;">房东信息表单</div>
						<div class="form-box form-box2" style="display:none;">
						<form class="layui-form form-item form-item2" name="houseowner">
							<input type="hidden" class="delField"  value=""/>
							<input type="hidden" class="saveid" value="3">
							<div>
								<div class="layui-row">
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="fullName" flag="1">
										    <label class="layui-form-label">姓名</label>
										    <div  class="layui-input-block">
										      <input type="text" name="fullName" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										 </div>
									</div>
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="phone">
										    <label class="layui-form-label">电话</label>
										    <div  class="layui-input-block">
										      <input type="text" name="phone" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										</div>
									</div>
								</div>
								<p class="appendField"></p>
							</div>
							
						  	
						</form>
						
						</div>
						<div class="layui-form-item save-btn move resizable btn2" style="display:none;">
						    <div class="layui-input-block">
						      <button type="button" class="layui-btn"  lay-filter="*" onclick="addField(2);">保存</button>
						    </div>
						  </div>
						  
						<div class="form-title3" style="display:none;">房源其他信息表单</div>
						<div class="form-box form-box3" style="display:none;">
						<form class="layui-form form-item form-item3" name="houseother">
							<input type="hidden" class="delField"  value=""/>
							<input type="hidden" class="saveid" value="4">
							
							<div>
							
								<div class="layui-row">
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="other1">
										    <label class="layui-form-label">其他信息1</label>
										    <div  class="layui-input-block">
										      <input type="text" name="other1" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										</div>
									</div>
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="other2">
										    <label class="layui-form-label">其他信息2</label>
										    <div  class="layui-input-block">
										      <input type="text" name="other2" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										</div>
									</div>
								</div>
								<p class="appendField"></p>
							</div>
							
						</form>
						
						</div>
						<div class="layui-form-item save-btn move resizable btn3" style="display:none;">
						    <div class="layui-input-block">
						      <button type="button" class="layui-btn "  lay-filter="*" onclick="addField(3);">保存</button>
						    </div>
						  </div>
						</div>
						<!-- 表单设置结束 -->
						
						<div class="layui-tab-item">其他设置项</div>
						
						</div>
						
					</div>	
					</div><!-- mo-form结束 -->
				</div> <!-- row结束 -->
				
				
			</div>
		<script>
		
		</script>
				
	</body>

</html>